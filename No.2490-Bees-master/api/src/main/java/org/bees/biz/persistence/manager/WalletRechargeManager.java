package org.bees.biz.persistence.manager;

import java.util.Date;

import org.bees.biz.persistence.dao.WalletRechargeDao;
import org.bees.biz.persistence.model.WalletRecharge;
import org.bees.biz.persistence.model.WalletRechargeExample;
import org.bees.biz.persistence.model.WalletRechargeExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseManager;

@Component
public class WalletRechargeManager extends BaseManager<WalletRechargeDao> {

	@Autowired
	private WalletRechargeDao dao;

	@Override
	public WalletRechargeDao getDao() {
		return dao;
	}

	public WalletRecharge getInfoById(Long id) {
		WalletRechargeExample example = new WalletRechargeExample();
		Criteria crit = example.createCriteria();
		crit.andIdEqualTo(id);
		return this.getDao().getByExample(example);
	}

	public boolean addCash(Integer uid, String money) {
		WalletRecharge cash =new WalletRecharge();
		cash.setAddTime(new Date());
		cash.setUid(uid);
		cash.setMoney(money);
		int res= this.getDao().insert(cash);
		if(res>0){
			return true;
		}
		return false;
	}

	public boolean pay(Long orderId, Byte payType) {
		WalletRecharge recharge =new WalletRecharge();
		recharge.setId(orderId);
		recharge.setPayTime(new Date());
		recharge.setPayType(payType);
		int res= this.getDao().update(recharge);
		if(res>0){
			return true;
		}
		return false;
	}

}