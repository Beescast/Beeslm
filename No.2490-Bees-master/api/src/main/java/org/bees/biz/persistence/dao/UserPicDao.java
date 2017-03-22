package org.bees.biz.persistence.dao;

import org.bees.biz.persistence.mapper.UserPicMapper;
import org.bees.biz.persistence.model.UserPic;
import org.bees.biz.persistence.model.UserPicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseDAO;



@Component
public class UserPicDao extends
		BaseDAO<UserPic, Integer, UserPicMapper, UserPicExample> {
	
	@Autowired
	private UserPicMapper mapper;

	@Override
	public UserPicMapper getMapper() {
		return mapper;
	}

}