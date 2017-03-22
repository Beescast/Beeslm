package org.bees.biz.persistence.dao;

import org.bees.biz.persistence.mapper.WalletCashMapper;
import org.bees.biz.persistence.model.WalletCash;
import org.bees.biz.persistence.model.WalletCashExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseDAO;



@Component
public class WalletCashDao extends
		BaseDAO<WalletCash, Integer, WalletCashMapper, WalletCashExample> {
	
	@Autowired
	private WalletCashMapper mapper;

	@Override
	public WalletCashMapper getMapper() {
		return mapper;
	}

}