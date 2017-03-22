package org.bees.biz.persistence.manager;

import java.util.Date;
import java.util.List;

import org.bees.biz.persistence.dao.TaskCategoryDao;
import org.bees.biz.persistence.model.TaskCategory;
import org.bees.biz.persistence.model.TaskCategoryExample;
import org.bees.biz.persistence.model.TaskCategoryExample.Criteria;
import org.bees.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseManager;
import projects.commons.utils.ValidateUtils;

@Component
public class TaskCategoryManager extends BaseManager<TaskCategoryDao> {

	@Autowired
	private TaskCategoryDao dao;

	@Override
	public TaskCategoryDao getDao() {
		return dao;
	}

	public TaskCategory getInfoById(Integer id) {
		TaskCategoryExample example = new TaskCategoryExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		crit.andIdEqualTo(id);
		return this.getDao().getByExample(example);
	}

	public List<TaskCategory> getList(Integer start, Integer limit, Integer gt) {
		TaskCategoryExample example = new TaskCategoryExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		if(!ValidateUtils.isNull(start)){
			example.setLimitStart(start);
			example.setLimitEnd(limit);
		}
		if(!ValidateUtils.isNull(gt)){
			crit.andIdGreaterThan(0);
		}
		example.setOrderByClause("FIND_IN_SET(id,'-1,0') desc");
		return this.getDao().list(example);
	}

	public boolean add(String name) {
		TaskCategory taskCategory=new TaskCategory();
		taskCategory.setAddTime(new Date());
		taskCategory.setName(name);
		int res= this.getDao().insert(taskCategory);
		if(res>0){
			return true;
		}
		return false;
	}

	public boolean del(Integer id) {
		TaskCategory taskCategory=new TaskCategory();
		taskCategory.setId(id);
		taskCategory.setDelFlag(Const.DEL_FLAG_DEL);		
		int res= this.getDao().update(taskCategory);
		if(res>0){
			return true;
		}
		return false;
	}

	public boolean edit(Integer id, String name) {
		TaskCategory taskCategory=new TaskCategory();
		taskCategory.setId(id);
		taskCategory.setName(name);
		int res= this.getDao().update(taskCategory);
		if(res>0){
			return true;
		}
		return false;
	}
	
	


}