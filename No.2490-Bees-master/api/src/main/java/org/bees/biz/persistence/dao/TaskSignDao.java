package org.bees.biz.persistence.dao;

import org.bees.biz.persistence.mapper.TaskSignMapper;
import org.bees.biz.persistence.model.TaskSign;
import org.bees.biz.persistence.model.TaskSignExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseDAO;



@Component
public class TaskSignDao extends
		BaseDAO<TaskSign, Integer, TaskSignMapper, TaskSignExample> {
	
	@Autowired
	private TaskSignMapper mapper;

	@Override
	public TaskSignMapper getMapper() {
		return mapper;
	}

}