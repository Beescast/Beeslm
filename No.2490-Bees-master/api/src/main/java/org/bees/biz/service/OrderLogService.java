package org.bees.biz.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bees.biz.persistence.manager.OrderLogManager;
import org.bees.biz.persistence.manager.UserManager;
import org.bees.biz.persistence.model.OrderLog;
import org.bees.biz.persistence.model.User;
import org.bees.biz.service.dto.OrderLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projects.commons.utils.ValidateUtils;

@Service
public class OrderLogService {
	
	@Autowired
	private OrderLogManager orderLogManager;
	
	@Autowired
	private UserManager userManager;
	
	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(OrderLogService.class);

	public OrderLog getInfoById(Integer id) {		
		return this.orderLogManager.getInfoById(id);
	}

	public List<OrderLogDto> getList(Integer uid, Byte type, Byte incomeType,
			String mobile, String nickname, Date startDate, Date endDate,
			String startMoney, String endMoney, String orderId, Integer start,
			Integer limit) {
		List<User> users=this.userManager.adminList(mobile, nickname,null, null, null, null, null,null, uid, null,null,null, null);
		List<Integer> uids=null;
		if(!ValidateUtils.isNull(users)){
			uids=new LinkedList<Integer>();
			for (User user : users) {
				uids.add(user.getId());
			}
		}
		List<OrderLog> OrderLogs=this.orderLogManager.getList(uids,type,incomeType,startDate,endDate,startMoney,endMoney,orderId,start,limit);
		List<OrderLogDto> dtos=new LinkedList<OrderLogDto>();

		for (OrderLog orderLog : OrderLogs) {
			OrderLogDto dto=new OrderLogDto();
			User user=this.userManager.getInfoById(orderLog.getUid());
			try {
				PropertyUtils.copyProperties(dto, user);
				PropertyUtils.copyProperties(dto, orderLog);
			} catch (IllegalAccessException | InvocationTargetException
					| NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dtos.add(dto);
		}		
		return dtos;
	}

	public boolean del(Integer id) {		
		return this.orderLogManager.del(id);
	}

	public int getListCount(Integer uid, Byte type, Byte incomeType,
			String mobile, String nickname, Date startDate, Date endDate,
			String startMoney, String endMoney, String orderId) {
		List<User> users=this.userManager.adminList(mobile, nickname,null, null, null, null, null,null, uid, null,null,null, null);
		List<Integer> uids=null;
		if(!ValidateUtils.isNull(users)){
			uids=new LinkedList<Integer>();
			for (User user : users) {
				uids.add(user.getId());
			}
		}
		return this.orderLogManager.getListCount(uids,type,incomeType,startDate,endDate,startMoney,endMoney,orderId);
	}


	
	
}