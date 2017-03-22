package org.bees.biz.persistence.dao;

import org.bees.biz.persistence.mapper.LiveGiftMapper;
import org.bees.biz.persistence.model.LiveGift;
import org.bees.biz.persistence.model.LiveGiftExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseDAO;



@Component
public class LiveGiftDao extends
		BaseDAO<LiveGift, Integer, LiveGiftMapper, LiveGiftExample> {
	
	@Autowired
	private LiveGiftMapper mapper;

	@Override
	public LiveGiftMapper getMapper() {
		return mapper;
	}

}