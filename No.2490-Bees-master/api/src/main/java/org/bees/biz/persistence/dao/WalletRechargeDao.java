package org.bees.biz.persistence.dao;

import org.bees.biz.persistence.mapper.WalletRechargeMapper;
import org.bees.biz.persistence.model.WalletRecharge;
import org.bees.biz.persistence.model.WalletRechargeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseDAO;



@Component
public class WalletRechargeDao extends
		BaseDAO<WalletRecharge, Integer, WalletRechargeMapper, WalletRechargeExample> {
	
	@Autowired
	private WalletRechargeMapper mapper;

	@Override
	public WalletRechargeMapper getMapper() {
		return mapper;
	}

}