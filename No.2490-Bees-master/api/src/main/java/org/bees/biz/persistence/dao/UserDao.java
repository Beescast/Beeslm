package org.bees.biz.persistence.dao;

import org.bees.biz.persistence.mapper.UserMapper;
import org.bees.biz.persistence.model.User;
import org.bees.biz.persistence.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseDAO;



@Component
public class UserDao extends
		BaseDAO<User, Integer, UserMapper, UserExample> {
	
	@Autowired
	private UserMapper mapper;

	@Override
	public UserMapper getMapper() {
		return mapper;
	}

}