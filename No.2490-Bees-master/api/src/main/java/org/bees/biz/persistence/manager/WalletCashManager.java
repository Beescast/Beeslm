package org.bees.biz.persistence.manager;

import java.util.Date;
import java.util.List;

import org.bees.biz.persistence.dao.WalletCashDao;
import org.bees.biz.persistence.model.WalletCash;
import org.bees.biz.persistence.model.WalletCashExample;
import org.bees.biz.persistence.model.WalletCashExample.Criteria;
import org.bees.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseManager;
import projects.commons.utils.ValidateUtils;

@Component
public class WalletCashManager extends BaseManager<WalletCashDao> {

	@Autowired
	private WalletCashDao dao;

	@Override
	public WalletCashDao getDao() {
		return dao;
	}

	public WalletCash getInfoById(Integer id) {
		WalletCashExample example = new WalletCashExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		crit.andIdEqualTo(id);
		return this.getDao().getByExample(example);
	}

	public WalletCash addCash(Integer uid, String money) {
		WalletCash cash =new WalletCash();
		cash.setAddTime(new Date());
		cash.setApplyTime(new Date());
		cash.setUid(uid);
		cash.setMoney(money);
		int res= this.getDao().insert(cash);
		if(res>0){
			return cash;
		}
		return null;
	}

	public List<WalletCash> getList(Integer id, List<Integer> uids,
			Byte status, Date startDate, Date endDate, String startMoney,
			String endMoney, Integer start, Integer limit) {
		WalletCashExample example = new WalletCashExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		if(!ValidateUtils.isNull(start)){
			example.setLimitStart(start);
			example.setLimitEnd(limit);
		}
		if(!ValidateUtils.isNull(id)){
			crit.andIdEqualTo(id);
		}
		if(!ValidateUtils.isNull(uids)){
			crit.andUidIn(uids);
		}
		if(!ValidateUtils.isNull(status)){
			crit.andStatusEqualTo(status);
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
		
		example.setOrderByClause("id desc");
		return this.getDao().list(example);
	}

	public boolean cashOp(Integer adminId, Integer id, Byte status, String orderId, String reseaon) {
		WalletCash cash =new WalletCash();
		cash.setOpTime(new Date());
		cash.setId(id);
		cash.setOpId(adminId);
		cash.setStatus(status);
		if(!ValidateUtils.isNull(orderId)){
			cash.setThirdOrderId(orderId);
		}
		if(!ValidateUtils.isNull(reseaon)){
			cash.setReseaon(reseaon);
		}
		int res= this.getDao().update(cash);
		if(res>0){
			return true;
		}
		return false;
	}

	public int getListCount(Integer id, List<Integer> uids, Byte status,
			Date startDate, Date endDate, String startMoney, String endMoney) {
		WalletCashExample example = new WalletCashExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		
		if(!ValidateUtils.isNull(id)){
			crit.andIdEqualTo(id);
		}
		if(!ValidateUtils.isNull(uids)){
			crit.andUidIn(uids);
		}
		if(!ValidateUtils.isNull(status)){
			crit.andStatusEqualTo(status);
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
		
		return this.getDao().count(example);
	}

}