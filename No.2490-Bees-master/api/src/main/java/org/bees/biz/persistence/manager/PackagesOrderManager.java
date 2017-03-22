package org.bees.biz.persistence.manager;

import java.util.Date;
import java.util.List;

import org.bees.biz.persistence.dao.PackagesOrderDao;
import org.bees.biz.persistence.model.PackagesOrder;
import org.bees.biz.persistence.model.PackagesOrderExample;
import org.bees.biz.persistence.model.PackagesOrderExample.Criteria;
import org.bees.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseManager;
import projects.commons.utils.ValidateUtils;

@Component
public class PackagesOrderManager extends BaseManager<PackagesOrderDao> {

	@Autowired
	private PackagesOrderDao dao;

	@Override
	public PackagesOrderDao getDao() {
		return dao;
	}

	public PackagesOrder getInfoById(Long id) {
		PackagesOrderExample example = new PackagesOrderExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		crit.andIdEqualTo(id);
		return this.getDao().getByExample(example);
	}

	public List<PackagesOrder> getOrders(Integer uid, Long id, Byte payType, Byte status, Date startDate, Date endDate, Integer pid, Integer start, Integer limit) {
		PackagesOrderExample example = new PackagesOrderExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		if(!ValidateUtils.isNull(uid)){
			crit.andUidEqualTo(uid);
		}
		if(!ValidateUtils.isNull(pid)){
			crit.andPackageIdEqualTo(pid);
		}
		if(!ValidateUtils.isNull(id)){
			crit.andIdEqualTo(id);
		}
		if(!ValidateUtils.isNull(payType)){
			crit.andPayTypeEqualTo(payType);
		}
		if(!ValidateUtils.isNull(status)){
			crit.andStatusEqualTo(status);
		}
		if(!ValidateUtils.isNull(startDate)){
			crit.andSubmitTimeGreaterThanOrEqualTo(startDate);
		}
		if(!ValidateUtils.isNull(endDate)){
			crit.andSubmitTimeLessThanOrEqualTo(endDate);
		}
		if(!ValidateUtils.isNull(start)){
			example.setLimitStart(start);
			example.setLimitEnd(limit);
		}
		example.setOrderByClause("add_time desc");		
		return this.getDao().list(example);
	}

	public boolean del(Long id) {
		PackagesOrder order=new PackagesOrder();
		order.setId(id);
		order.setStatus(Const.ORDER_STATUS_NIGHT);
		int res=this.getDao().update(order);
		if(res<=0){
			return false;
		}
		return true;
	}
	
	public boolean realDel(Long id) {
		PackagesOrder order=new PackagesOrder();
		order.setId(id);
		order.setDelFlag(Const.DEL_FLAG_DEL);
		int res=this.getDao().update(order);
		if(res<=0){
			return false;
		}
		return true;
	}

	public boolean add(PackagesOrder order) {
		int res=this.getDao().insert(order);
		if(res>0){
			return true;
		}
		return false;
	}
	
	public boolean startPackage(Long id, Date endDates) {
		PackagesOrder order=new PackagesOrder();
		order.setId(id);
		order.setStatus(Const.ORDER_STATUS_TWO);
		order.setStartTime(new Date());
		order.setEndTime(endDates);
		int res=this.getDao().update(order);
		if(res<=0){
			return false;
		}
		return true;
	}

	public Integer getOrdersCount(Integer uid, Long id, Byte payType,
			Byte status, Date startDate, Date endDate, Integer pid) {
		PackagesOrderExample example = new PackagesOrderExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		if(!ValidateUtils.isNull(uid)){
			crit.andUidEqualTo(uid);
		}
		if(!ValidateUtils.isNull(pid)){
			crit.andPackageIdEqualTo(pid);
		}
		if(!ValidateUtils.isNull(id)){
			crit.andIdEqualTo(id);
		}
		if(!ValidateUtils.isNull(payType)){
			crit.andPayTypeEqualTo(payType);
		}
		if(!ValidateUtils.isNull(status)){
			crit.andStatusEqualTo(status);
		}
		if(!ValidateUtils.isNull(startDate)){
			crit.andSubmitTimeGreaterThanOrEqualTo(startDate);
		}
		if(!ValidateUtils.isNull(endDate)){
			crit.andSubmitTimeLessThanOrEqualTo(endDate);
		}
			
		return this.getDao().count(example);
	}

	public int getCommentCount(Integer pid) {
		PackagesOrderExample example = new PackagesOrderExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		if(!ValidateUtils.isNull(pid)){
			crit.andPackageIdEqualTo(pid);
		}
		return this.getDao().count(example);
	}

	public boolean pay(Long orderId, Byte payType) {
		PackagesOrder order=new PackagesOrder();
		order.setId(orderId);
		order.setStatus(Const.ORDER_STATUS_ONE);
		order.setPayType(payType);
		order.setPayTime(new Date());
		int res=this.getDao().update(order);
		if(res<=0){
			return false;
		}
		return true;
	}

	public boolean addComment(Long orderId, String comment) {
		
		PackagesOrder order=new PackagesOrder();
		order.setId(orderId);
		order.setStatus(Const.ORDER_STATUS_FOUR);
		order.setComment(comment);
		order.setCommentTime(new Date());
		int res=this.getDao().update(order);
		if(res<=0){
			return false;
		}
		return true;
		
	}


}