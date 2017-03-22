package org.bees.biz.persistence.dao;

import org.bees.biz.persistence.mapper.TaskSignPicMapper;
import org.bees.biz.persistence.model.TaskSignPic;
import org.bees.biz.persistence.model.TaskSignPicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseDAO;



@Component
public class TaskSignPicDao extends
		BaseDAO<TaskSignPic, Integer, TaskSignPicMapper, TaskSignPicExample> {
	
	@Autowired
	private TaskSignPicMapper mapper;

	@Override
	public TaskSignPicMapper getMapper() {
		return mapper;
	}

}