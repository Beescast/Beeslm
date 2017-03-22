package org.bees.biz.persistence.dao;

import org.bees.biz.persistence.mapper.PackagesMapper;
import org.bees.biz.persistence.model.Packages;
import org.bees.biz.persistence.model.PackagesExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseDAO;



@Component
public class PackagesDao extends
		BaseDAO<Packages, Integer, PackagesMapper, PackagesExample> {
	
	@Autowired
	private PackagesMapper mapper;

	@Override
	public PackagesMapper getMapper() {
		return mapper;
	}

}