package org.bees.biz.persistence.dao;

import org.bees.biz.persistence.mapper.FriendLinkMapper;
import org.bees.biz.persistence.model.FriendLink;
import org.bees.biz.persistence.model.FriendLinkExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseDAO;



@Component
public class FriendLinkDao extends
		BaseDAO<FriendLink, Integer, FriendLinkMapper, FriendLinkExample> {
	
	@Autowired
	private FriendLinkMapper mapper;

	@Override
	public FriendLinkMapper getMapper() {
		return mapper;
	}

}