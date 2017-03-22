package org.bees.biz.persistence.dao;

import org.bees.biz.persistence.mapper.NoticeMapper;
import org.bees.biz.persistence.model.Notice;
import org.bees.biz.persistence.model.NoticeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseDAO;



@Component
public class NoticeDao extends
		BaseDAO<Notice, Integer, NoticeMapper, NoticeExample> {
	
	@Autowired
	private NoticeMapper mapper;

	@Override
	public NoticeMapper getMapper() {
		return mapper;
	}

}