package org.bees.biz.persistence.dao;

import org.bees.biz.persistence.mapper.MarketMapper;
import org.bees.biz.persistence.model.Market;
import org.bees.biz.persistence.model.MarketExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseDAO;



@Component
public class MarketDao extends
		BaseDAO<Market, Integer, MarketMapper, MarketExample> {
	
	@Autowired
	private MarketMapper mapper;

	@Override
	public MarketMapper getMapper() {
		return mapper;
	}

}