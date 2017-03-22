package org.bees.biz.persistence.manager;

import java.util.Date;
import java.util.List;

import org.bees.biz.persistence.dao.OrderLogDao;
import org.bees.biz.persistence.model.OrderLog;
import org.bees.biz.persistence.model.OrderLogExample;
import org.bees.biz.persistence.model.OrderLogExample.Criteria;
import org.bees.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseManager;
import projects.commons.utils.ValidateUtils;

@Component
public class OrderLogManager extends BaseManager<OrderLogDao> {

	@Autowired
	private OrderLogDao dao;

	@Override
	public OrderLogDao getDao() {
		return dao;
	}

	public OrderLog getInfoById(Integer id) {
		OrderLogExample example = new OrderLogExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		crit.andIdEqualTo(id);
		return this.getDao().getByExample(example);
	}

	public List<OrderLog> getList(List<Integer> uids, Byte type,
			Byte incomeType, Date startDate, Date endDate, String startMoney,
			String endMoney, String orderId, Integer start, Integer limit) {
		OrderLogExample example = new OrderLogExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		if(!ValidateUtils.isNull(start)){
			example.setLimitStart(start);
			example.setLimitEnd(limit);
		}
		if(!ValidateUtils.isNull(uids)){
			crit.andUidIn(uids);
		}
		if(!ValidateUtils.isNull(type)){
			crit.andTypeEqualTo(type);
		}
		if(!ValidateUtils.isNull(incomeType)){
			crit.andIncomeTypeEqualTo(incomeType);
		}
		if(!ValidateUtils.isNull(startDate)){
			crit.andAddTimeGreaterThanOrEqualTo(startDate);
		}
		if(!ValidateUtils.isNull(endDate)){
			crit.andAddTimeLessThanOrEqualTo(endDate);
		}
		if(!ValidateUtils.isNull(startMoney)){
			crit.andMoneyGreaterThanOrEqualTo(startMoney);
		}
		if(!ValidateUtils.isNull(endMoney)){
			crit.andMoneyLessThanOrEqualTo(endMoney);
		}
		if(!ValidateUtils.isNull(orderId)){
			crit.andOrderIdEqualTo(orderId);
		}
		example.setOrderByClause("id desc");
		return this.getDao().list(example);
	}

	public boolean add(Integer userId, Byte type, Byte payType, Byte incomeType, String money, String thirdOrderId, String businessId) {
		OrderLog orderLog=new OrderLog();
		orderLog.setUid(userId);
		orderLog.setType(type);
		orderLog.setPayType(payType);
		orderLog.setIncomeType(incomeType);
		orderLog.setMoney(money);
		orderLog.setBusinessId(businessId);
		if(!ValidateUtils.isNull(thirdOrderId)){
			orderLog.setOrderId(thirdOrderId);
		}
		orderLog.setAddTime(new Date());
		int res=this.getDao().insert(orderLog);
		if(res>0){
			return true;
		}
		return false;
	}
	
	public boolean del(Integer id) {
		OrderLog orderLog=new OrderLog();
		orderLog.setId(id);
		orderLog.setDelFlag(Const.DEL_FLAG_DEL);		
		int res= this.getDao().update(orderLog);
		if(res>0){
			return true;
		}
		return false;
	}

	public int getListCount(List<Integer> uids, Byte type, Byte incomeType,
			Date startDate, Date endDate, String startMoney, String endMoney,
			String orderId) {
		OrderLogExample example = new OrderLogExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		
		if(!ValidateUtils.isNull(uids)){
			crit.andUidIn(uids);
		}
		if(!ValidateUtils.isNull(type)){
			crit.andTypeEqualTo(type);
		}
		if(!ValidateUtils.isNull(incomeType)){
			crit.andIncomeTypeEqualTo(incomeType);
		}
		if(!ValidateUtils.isNull(startDate)){
			crit.andAddTimeGreaterThanOrEqualTo(startDate);
		}
		if(!ValidateUtils.isNull(endDate)){
			crit.andAddTimeLessThanOrEqualTo(endDate);
		}
		if(!ValidateUtils.isNull(startMoney)){
			crit.andMoneyGreaterThanOrEqualTo(startMoney);
		}
		if(!ValidateUtils.isNull(endMoney)){
			crit.andMoneyLessThanOrEqualTo(endMoney);
		}
		if(!ValidateUtils.isNull(orderId)){
			crit.andOrderIdEqualTo(orderId);
		}
		return this.getDao().count(example);
	}

}