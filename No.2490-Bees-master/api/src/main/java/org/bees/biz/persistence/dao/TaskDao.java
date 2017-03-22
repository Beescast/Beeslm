package org.bees.biz.persistence.dao;

import org.bees.biz.persistence.mapper.TaskMapper;
import org.bees.biz.persistence.model.Task;
import org.bees.biz.persistence.model.TaskExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseDAO;



@Component
public class TaskDao extends
		BaseDAO<Task, Integer, TaskMapper, TaskExample> {
	
	@Autowired
	private TaskMapper mapper;

	@Override
	public TaskMapper getMapper() {
		return mapper;
	}

}