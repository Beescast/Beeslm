package org.bees.biz.persistence.manager;

import org.bees.biz.persistence.dao.TaskSignPicDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseManager;

@Component
public class TaskSignPicManager extends BaseManager<TaskSignPicDao> {

	@Autowired
	private TaskSignPicDao dao;

	@Override
	public TaskSignPicDao getDao() {
		return dao;
	}

}