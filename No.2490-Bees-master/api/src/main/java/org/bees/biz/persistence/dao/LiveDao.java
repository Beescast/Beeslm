package org.bees.biz.persistence.dao;

import org.bees.biz.persistence.mapper.LiveMapper;
import org.bees.biz.persistence.model.Live;
import org.bees.biz.persistence.model.LiveExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseDAO;



@Component
public class LiveDao extends
		BaseDAO<Live, Integer, LiveMapper, LiveExample> {
	
	@Autowired
	private LiveMapper mapper;

	@Override
	public LiveMapper getMapper() {
		return mapper;
	}

}