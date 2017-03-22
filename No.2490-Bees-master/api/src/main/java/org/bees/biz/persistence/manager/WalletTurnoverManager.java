package org.bees.biz.persistence.manager;

import java.util.Date;
import java.util.List;

import org.bees.biz.persistence.dao.WalletTurnoverDao;
import org.bees.biz.persistence.model.WalletTurnover;
import org.bees.biz.persistence.model.WalletTurnoverExample;
import org.bees.biz.persistence.model.WalletTurnoverExample.Criteria;
import org.bees.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseManager;
import projects.commons.utils.ValidateUtils;

@Component
public class WalletTurnoverManager extends BaseManager<WalletTurnoverDao> {

	@Autowired
	private WalletTurnoverDao dao;

	@Override
	public WalletTurnoverDao getDao() {
		return dao;
	}
	
	public List<WalletTurnover> getList(Integer uid,int start, int limit) {
		WalletTurnoverExample example = new WalletTurnoverExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		if(!ValidateUtils.isNull(start)){
			example.setLimitStart(start);
			example.setLimitEnd(limit);
		}
		crit.andUidEqualTo(uid);		
		example.setOrderByClause("id desc");
		return this.getDao().list(example);
	}

	public int getListCount(Integer uid) {
		WalletTurnoverExample example = new WalletTurnoverExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		
		crit.andUidEqualTo(uid);		
		return this.getDao().count(example);
	}

	public List<WalletTurnover> getAdminList(List<Integer> uids, Integer id,
			Byte payType, String businessNo, Date startDate, Date endDate,
			Byte businessType, Integer start, Integer limit) {
		WalletTurnoverExample example = new WalletTurnoverExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		if(!ValidateUtils.isNull(start)){
			example.setLimitStart(start);
			example.setLimitEnd(limit);
		}
		if(!ValidateUtils.isNull(uids)){
			crit.andUidIn(uids);
		}
		if(!ValidateUtils.isNull(payType)){
			crit.andPayTypeEqualTo(payType);
		}
		if(!ValidateUtils.isNull(businessNo)){
			crit.andBusinessNoEqualTo(businessNo);
		}
		if(!ValidateUtils.isNull(startDate)){
			crit.andAddTimeGreaterThanOrEqualTo(startDate);
		}
		if(!ValidateUtils.isNull(endDate)){
			crit.andAddTimeLessThanOrEqualTo(endDate);
		}
		if(!ValidateUtils.isNull(businessType)){
			crit.andBusinessTypeEqualTo(businessType);
		}
		if(!ValidateUtils.isNull(id)){
			crit.andIdEqualTo(id);
		}
		example.setOrderByClause("id desc");
		return this.getDao().list(example);
	}

	public int getAdminListCount(List<Integer> uids, Integer id, Byte payType,
			String businessNo, Date startDate, Date endDate, Byte businessType) {
		WalletTurnoverExample example = new WalletTurnoverExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		
		if(!ValidateUtils.isNull(uids)){
			crit.andUidIn(uids);
		}
		if(!ValidateUtils.isNull(payType)){
			crit.andPayTypeEqualTo(payType);
		}
		if(!ValidateUtils.isNull(businessNo)){
			crit.andBusinessNoEqualTo(businessNo);
		}
		if(!ValidateUtils.isNull(startDate)){
			crit.andAddTimeGreaterThanOrEqualTo(startDate);
		}
		if(!ValidateUtils.isNull(endDate)){
			crit.andAddTimeLessThanOrEqualTo(endDate);
		}
		if(!ValidateUtils.isNull(businessType)){
			crit.andBusinessTypeEqualTo(businessType);
		}
		if(!ValidateUtils.isNull(id)){
			crit.andIdEqualTo(id);
		}
		return this.getDao().count(example);
	}

	public void add(Integer uid, Byte businessType, Byte payType, String money,
			String currentBalance, String businessNo, String thirdOrderId, String reseaon) {
		WalletTurnover over =new WalletTurnover();
		over.setUid(uid);
		over.setBusinessNo(businessNo);
		over.setBusinessType(businessType);
		over.setPayType(payType);
		over.setMoney(money);
		over.setCurrentBalance(currentBalance);
		over.setThirdOrderId(thirdOrderId);
		over.setReseaon(reseaon);
		over.setAddTime(new Date());
		this.getDao().insert(over);
		if(ValidateUtils.isNull(businessNo)){
			over.setBusinessNo(over.getId()+"");
			this.getDao().update(over);
		}
	}
	
	


}