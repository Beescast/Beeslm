package org.bees.biz.persistence.dao;

import org.bees.biz.persistence.mapper.LivePeopleMapper;
import org.bees.biz.persistence.model.LivePeople;
import org.bees.biz.persistence.model.LivePeopleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseDAO;



@Component
public class LivePeopleDao extends
		BaseDAO<LivePeople, Integer, LivePeopleMapper, LivePeopleExample> {
	
	@Autowired
	private LivePeopleMapper mapper;

	@Override
	public LivePeopleMapper getMapper() {
		return mapper;
	}

}