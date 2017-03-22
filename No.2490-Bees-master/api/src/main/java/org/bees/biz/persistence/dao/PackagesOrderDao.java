package org.bees.biz.persistence.dao;

import org.bees.biz.persistence.mapper.PackagesOrderMapper;
import org.bees.biz.persistence.model.PackagesOrder;
import org.bees.biz.persistence.model.PackagesOrderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseDAO;



@Component
public class PackagesOrderDao extends
		BaseDAO<PackagesOrder, Integer, PackagesOrderMapper, PackagesOrderExample> {
	
	@Autowired
	private PackagesOrderMapper mapper;

	@Override
	public PackagesOrderMapper getMapper() {
		return mapper;
	}

}