package org.bees.biz.persistence.dao;

import org.bees.biz.persistence.mapper.LeaveMsgMapper;
import org.bees.biz.persistence.model.LeaveMsg;
import org.bees.biz.persistence.model.LeaveMsgExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseDAO;



@Component
public class LeaveMsgDao extends
		BaseDAO<LeaveMsg, Integer, LeaveMsgMapper, LeaveMsgExample> {
	
	@Autowired
	private LeaveMsgMapper mapper;

	@Override
	public LeaveMsgMapper getMapper() {
		return mapper;
	}

}