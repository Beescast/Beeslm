package org.bees.biz.persistence.dao;

import org.bees.biz.persistence.mapper.UserAuthMapper;
import org.bees.biz.persistence.model.UserAuth;
import org.bees.biz.persistence.model.UserAuthExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseDAO;



@Component
public class UserAuthDao extends
		BaseDAO<UserAuth, Integer, UserAuthMapper, UserAuthExample> {
	
	@Autowired
	private UserAuthMapper mapper;

	@Override
	public UserAuthMapper getMapper() {
		return mapper;
	}

}