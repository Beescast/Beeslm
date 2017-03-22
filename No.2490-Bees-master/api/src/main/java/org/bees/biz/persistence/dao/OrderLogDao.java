package org.bees.biz.persistence.dao;

import org.bees.biz.persistence.mapper.OrderLogMapper;
import org.bees.biz.persistence.model.OrderLog;
import org.bees.biz.persistence.model.OrderLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseDAO;



@Component
public class OrderLogDao extends
		BaseDAO<OrderLog, Integer, OrderLogMapper, OrderLogExample> {
	
	@Autowired
	private OrderLogMapper mapper;

	@Override
	public OrderLogMapper getMapper() {
		return mapper;
	}

}