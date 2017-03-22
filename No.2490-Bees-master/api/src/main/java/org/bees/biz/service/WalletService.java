package org.bees.biz.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bees.biz.persistence.manager.AdminManager;
import org.bees.biz.persistence.manager.OrderLogManager;
import org.bees.biz.persistence.manager.UserManager;
import org.bees.biz.persistence.manager.WalletCashManager;
import org.bees.biz.persistence.manager.WalletRechargeManager;
import org.bees.biz.persistence.manager.WalletTurnoverManager;
import org.bees.biz.persistence.model.Admin;
import org.bees.biz.persistence.model.User;
import org.bees.biz.persistence.model.WalletCash;
import org.bees.biz.persistence.model.WalletRecharge;
import org.bees.biz.persistence.model.WalletTurnover;
import org.bees.biz.service.dto.WalletDto;
import org.bees.biz.service.dto.WalletTurnoverDto;
import org.bees.utils.SMSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projects.commons.exception.ServiceException;
import projects.commons.utils.ValidateUtils;

@Service
public class WalletService {
	
	@Autowired
	private WalletCashManager walletCashManager;
	
	@Autowired
	private WalletTurnoverManager walletTurnoverManager;
	
	@Autowired
	private WalletRechargeManager walletRechargeManager;
	
	@Autowired
	private OrderLogManager orderLogManager;
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private AdminManager adminManager;
	
	
	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(WalletService.class);

	public List<WalletTurnoverDto> getList(int uid, int start, int limit) {
		List<WalletTurnover> Wallets=this.walletTurnoverManager.getList(uid,start,limit);
		List<WalletTurnoverDto> dtos=new LinkedList<WalletTurnoverDto>();
		for (WalletTurnover wallet : Wallets) {
			WalletTurnoverDto dto=new WalletTurnoverDto();
			try {
				PropertyUtils.copyProperties(dto, wallet);
				dto.setBusiness(wallet.getBusinessType());
				dto.setPay(wallet.getPayType());
			} catch (IllegalAccessException | InvocationTargetException
					| NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dtos.add(dto);
		}		
		return dtos;
	}

	public boolean cash(Integer uid, String money) {
		User user=this.userManager.getInfoById(uid);
		if(Double.valueOf(user.getBalance())<Double.valueOf(money)){
			throw new ServiceException("余额不足");
		}else{
			WalletCash cash=this.walletCashManager.addCash(uid,money);
			if(!ValidateUtils.isNull(cash)){
				String balance=Double.valueOf(user.getBalance())-Double.valueOf(money)+"";
				this.userManager.setMoney(uid, balance);
				this.walletTurnoverManager.add(user.getId(),new Byte("2"),new Byte("2"),money,balance,cash.getId()+"","","");
			}
			return true;
		}
	}

	public List<WalletDto> adminList(Integer id, Byte status,
			String accountName, String mobile, String nickname, Date startDate,
			Date endDate, Integer uid, String startMoney, String endMoney,
			Integer start, Integer limit) {
		List<User> users=this.userManager.adminList(mobile, nickname,null, null, null, null, null,null, uid,accountName,null, null, null);
		List<Integer> uids=null;
		if(!ValidateUtils.isNull(users)){
			uids=new LinkedList<Integer>();
			for (User user : users) {
				uids.add(user.getId());
			}
		}
		List<WalletCash> wallets=this.walletCashManager.getList(id,uids,status,startDate,endDate,startMoney,endMoney,start,limit);
		List<WalletDto> dtos=new LinkedList<WalletDto>();
		for (WalletCash walletCash : wallets) {
			WalletDto dto=new WalletDto();
			User user=this.userManager.getInfoById(walletCash.getUid());
			Admin admin=new Admin();
			if(walletCash.getOpId()!=0){
				admin=this.adminManager.getInfoById(walletCash.getOpId());
			}
			
			try {
				PropertyUtils.copyProperties(dto, user);
				PropertyUtils.copyProperties(dto, walletCash);
				dto.setOpName(admin.getName());
			} catch (IllegalAccessException | InvocationTargetException
					| NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dtos.add(dto);
		}
				
		
		return dtos;
	}

	public boolean cashOp(Integer adminId, Integer id, Byte status, String orderId, String reseaon) {
		boolean res=this.walletCashManager.cashOp(adminId,id,status,orderId,reseaon);
		if(res){
			WalletCash cash=this.walletCashManager.getInfoById(id);
			User user=this.userManager.getInfoById(cash.getUid());
			if(status.intValue()==1){
				//审核通过
				//财务流水增加一条
				this.orderLogManager.add(user.getId(), new Byte("3"), new Byte("3"), new Byte("2"), cash.getMoney(), orderId,"");
				SMSUtil.doPost(user.getMobile(), 5, "", null);
			}else{
				String balance=Double.valueOf(user.getBalance())+Double.valueOf(cash.getMoney())+"";
				this.userManager.setMoney(user.getId(), balance);
				this.walletTurnoverManager.add(user.getId(),new Byte("5"),new Byte("1"),cash.getMoney(),balance,id+"","",reseaon);
				SMSUtil.doPost(user.getMobile(), 6, "", null);
			}
			
		}
		
		return res; 
	}

	public int adminListCount(Integer id, Byte status, String accountName,
			String mobile, String nickname, Date startDate, Date endDate,
			Integer uid, String startMoney, String endMoney) {
		List<User> users=this.userManager.adminList(mobile, nickname,null, null, null, null, null,null, uid,accountName,null, null, null);
		List<Integer> uids=null;
		if(!ValidateUtils.isNull(users)){
			uids=new LinkedList<Integer>();
			for (User user : users) {
				uids.add(user.getId());
			}
		}
		return this.walletCashManager.getListCount(id,uids,status,startDate,endDate,startMoney,endMoney);
	}

	public int getListCount(Integer uid) {
		return this.walletTurnoverManager.getListCount(uid);
	}

	public List<WalletTurnoverDto> adminTurnOverList(Integer id, Integer uid,
			Byte payType, String mobile, String businessNo, Date startDate,
			Date endDate, Byte businessType, Integer start, Integer limit) {
		List<User> users=this.userManager.adminList(mobile, null,null, null, null, null, null,null, uid, null, null,null, null);
		List<Integer> uids=null;
		if(!ValidateUtils.isNull(users)){
			uids=new LinkedList<Integer>();
			for (User user : users) {
				uids.add(user.getId());
			}
		}
		List<WalletTurnover> turnovers=this.walletTurnoverManager.getAdminList(uids,id,payType,businessNo,startDate,endDate,businessType,start,limit);
		List<WalletTurnoverDto> dtos=new LinkedList<WalletTurnoverDto>();
		for (WalletTurnover walletTurnover : turnovers) {
			WalletTurnoverDto dto=new WalletTurnoverDto();
			try {
				User user=this.userManager.getInfoById(walletTurnover.getUid());
				PropertyUtils.copyProperties(dto, user);
				PropertyUtils.copyProperties(dto, walletTurnover);
			} catch (IllegalAccessException | InvocationTargetException
					| NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dtos.add(dto);
		}
		
		return dtos;
	}

	public int adminTurnOverCount(Integer id, Integer uid, Byte payType,
			String mobile, String businessNo, Date startDate, Date endDate,
			Byte businessType) {
		List<User> users=this.userManager.adminList(mobile, null,null, null, null, null, null,null, uid, null,null, null, null);
		List<Integer> uids=null;
		if(!ValidateUtils.isNull(users)){
			uids=new LinkedList<Integer>();
			for (User user : users) {
				uids.add(user.getId());
			}
		}
		return this.walletTurnoverManager.getAdminListCount(uids,id,payType,businessNo,startDate,endDate,businessType);
	}

	public WalletRecharge getRechargeById(Long orderId) {

		return this.walletRechargeManager.getInfoById(orderId);
	}

	public void pay(Long orderId, Byte payType, Integer uid, String balance,
			String money, String thirdOrderId) {
		this.walletRechargeManager.pay(orderId,payType);
		this.userManager.setMoney(uid, balance);
		this.orderLogManager.add(uid,new Byte("1"),payType,new Byte("1"),money,thirdOrderId,orderId.toString());
		this.walletTurnoverManager.add(uid, new Byte("1"), payType, money, balance, "", thirdOrderId, "");
	}

	
	
}