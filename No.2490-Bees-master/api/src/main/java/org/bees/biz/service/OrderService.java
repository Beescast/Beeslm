package org.bees.biz.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bees.biz.persistence.manager.OrderLogManager;
import org.bees.biz.persistence.manager.PackagesManager;
import org.bees.biz.persistence.manager.PackagesOrderManager;
import org.bees.biz.persistence.manager.PackagesPriceManager;
import org.bees.biz.persistence.manager.UserManager;
import org.bees.biz.persistence.manager.WalletTurnoverManager;
import org.bees.biz.persistence.model.Packages;
import org.bees.biz.persistence.model.PackagesOrder;
import org.bees.biz.persistence.model.PackagesPrice;
import org.bees.biz.persistence.model.User;
import org.bees.biz.service.dto.PackagesOrderDto;
import org.bees.utils.Const;
import org.bees.utils.SMSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projects.commons.exception.ServiceException;
import projects.commons.utils.date.DateUtils;
import projects.commons.utils.response.Res;

@Service
public class OrderService {
	
	@Autowired
	private PackagesOrderManager packagesOrderManager;
	
	@Autowired
	private PackagesManager packagesManager;
	
	@Autowired
	private PackagesPriceManager packagesPriceManager;
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private OrderLogManager orderLogManager;
	
	@Autowired
	private WalletTurnoverManager walletTurnoverManager;
	
	
	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(OrderService.class);

	public PackagesOrder getInfoById(Long id) {		
		PackagesOrder order=this.packagesOrderManager.getInfoById(id);
		Packages packages=this.packagesManager.getInfoById(order.getPackageId());
		order.setPackagePic(packages.getPic());
		return order;
	}

	public List<PackagesOrderDto> getList(Integer uid, Long id, Byte payType, Byte status, Date startDate, Date endDate, Integer pid,Integer start, Integer limit) {
		List<PackagesOrder> PackagesOrders=this.packagesOrderManager.getOrders(uid,id,payType,status,startDate,endDate,pid,start,limit);
		List<PackagesOrderDto> dtos=new LinkedList<PackagesOrderDto>();
		for (PackagesOrder packagesOrder : PackagesOrders) {
			PackagesOrderDto dto=new PackagesOrderDto();
			try {
				PropertyUtils.copyProperties(dto, packagesOrder);
				User user=this.userManager.getInfoById(packagesOrder.getUid());
				dto.setName(user.getNickname());
				dto.setMobile(user.getMobile());
				dto.setPay(dto.getPayType());
				dto.setStat(dto.getStatus());
			} catch (IllegalAccessException | InvocationTargetException
					| NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dtos.add(dto);
		}		
		return dtos;
	}

	public boolean del(Long id, Integer userId) {
		PackagesOrder order=this.packagesOrderManager.getInfoById(id);
		if(order.getUid().intValue()!=userId.intValue()){
			throw new ServiceException("您无权操作该订单");
		}
		
		return this.packagesOrderManager.del(id);
	}
	
	public boolean realDel(Long id) {
		PackagesOrder order=this.packagesOrderManager.getInfoById(id);
		/*if(order.getUid().intValue()!=userId.intValue()){
			throw new ServiceException("您无权操作该订单");
		}*/
		if(order.getStatus().intValue()!=Const.ORDER_STATUS_ZERO){
			throw new ServiceException("订单状态不正确");
		}
		return this.packagesOrderManager.realDel(id);
	}


	public Res add(Integer userId, Integer packageId, Integer packagePriceId) {
		Packages packages=this.packagesManager.getInfoById(packageId);
		PackagesPrice price=this.packagesPriceManager.getInfoById(packagePriceId);
		PackagesOrder order=new PackagesOrder();
		order.setId(createOrderId());
		order.setAddTime(new Date());
		order.setUid(userId);
		order.setPackageId(packageId);
		order.setPackagePriceId(packagePriceId);
		order.setPackageName(packages.getName()+"/"+price.getName());
		order.setPrice(price.getPrice());
		order.setSubmitTime(new Date());
		boolean result=this.packagesOrderManager.add(order);
		Res res=new Res();
		res.addResponse("result", result);
		res.addResponse("orderId", order.getId());
		res.commit();
		return res;
		
	}
	
	private Long createOrderId() {
		String dateString=DateUtils.toDateFormat(new Date(), "yyyyMMdd");
		Random rnd = new Random();
		String randString= rnd.nextInt(89999) + 10000+"";		
		return Long.parseLong(dateString+randString);
	}

	public boolean startPackage(Long id, Date endDates) {
		PackagesOrder order =this.packagesOrderManager.getInfoById(id);
		if(order.getStatus().intValue()!=Const.ORDER_STATUS_ONE.intValue()){
			throw new ServiceException("订单状态不正确");
		}
		boolean res=this.packagesOrderManager.startPackage(id,endDates);
		if(res){
			User user=this.userManager.getInfoById(order.getUid());
			SMSUtil.doPost(user.getMobile(), 4, DateUtils.toDateFormat(endDates, "yyyy-MM-dd"), null);
		}
		return res;
	}

	public Integer getListCount(Integer uid, Long id, Byte payType,
			Byte status, Date startDate, Date endDate, Integer pid) {
		
		return this.packagesOrderManager.getOrdersCount(uid,id,payType,status,startDate,endDate,pid);
	}


	public boolean pay(Long orderId, Byte payType, Integer userId, String balance,String money,String thirdOrderId) {
		boolean res=this.packagesOrderManager.pay(orderId,payType);
		if(res&&payType.intValue()==0){
			this.userManager.setMoney(userId, balance);
		}
		this.orderLogManager.add(userId,new Byte("1"),payType,new Byte("1"),money,thirdOrderId,orderId+"");
		if(payType.intValue()==0){
			this.walletTurnoverManager.add(userId, new Byte("3"), new Byte("2"), money, balance, orderId+"", thirdOrderId, "");
		}
		return true;
	}
	
	
}