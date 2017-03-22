package org.bees.biz.persistence.dao;

import org.bees.biz.persistence.mapper.AdminMapper;
import org.bees.biz.persistence.model.Admin;
import org.bees.biz.persistence.model.AdminExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseDAO;



@Component
public class AdminDao extends
		BaseDAO<Admin, Integer, AdminMapper, AdminExample> {
	
	@Autowired
	private AdminMapper mapper;

	@Override
	public AdminMapper getMapper() {
		return mapper;
	}

}