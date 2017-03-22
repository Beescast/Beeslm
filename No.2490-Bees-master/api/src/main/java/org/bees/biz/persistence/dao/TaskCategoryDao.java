package org.bees.biz.persistence.dao;

import org.bees.biz.persistence.mapper.TaskCategoryMapper;
import org.bees.biz.persistence.model.TaskCategory;
import org.bees.biz.persistence.model.TaskCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseDAO;



@Component
public class TaskCategoryDao extends
		BaseDAO<TaskCategory, Integer, TaskCategoryMapper, TaskCategoryExample> {
	
	@Autowired
	private TaskCategoryMapper mapper;

	@Override
	public TaskCategoryMapper getMapper() {
		return mapper;
	}

}