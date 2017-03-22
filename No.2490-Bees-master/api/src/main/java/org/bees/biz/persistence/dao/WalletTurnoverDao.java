package org.bees.biz.persistence.dao;

import org.bees.biz.persistence.mapper.WalletTurnoverMapper;
import org.bees.biz.persistence.model.WalletTurnover;
import org.bees.biz.persistence.model.WalletTurnoverExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseDAO;



@Component
public class WalletTurnoverDao extends
		BaseDAO<WalletTurnover, Integer, WalletTurnoverMapper, WalletTurnoverExample> {
	
	@Autowired
	private WalletTurnoverMapper mapper;

	@Override
	public WalletTurnoverMapper getMapper() {
		return mapper;
	}

}