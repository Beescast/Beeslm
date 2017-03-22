package org.bees.biz.persistence.manager;

import java.util.Date;
import java.util.List;

import org.bees.biz.persistence.dao.TaskDao;
import org.bees.biz.persistence.model.Task;
import org.bees.biz.persistence.model.TaskExample;
import org.bees.biz.persistence.model.TaskExample.Criteria;
import org.bees.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseManager;
import projects.commons.utils.ValidateUtils;

@Component
public class TaskManager extends BaseManager<TaskDao> {

	@Autowired
	private TaskDao dao;

	@Override
	public TaskDao getDao() {
		return dao;
	}

	public Task getInfoById(Integer id) {
		TaskExample example = new TaskExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		crit.andIdEqualTo(id);
		return this.getDao().getByExample(example);
	}

	

	public boolean add(String title, Integer catId, Byte type, String price, Integer num, String illustration) {
		Task task=new Task();
		task.setAddTime(new Date());
		task.setTitle(title);
		task.setCatId(catId);
		task.setType(type);
		task.setPrice(price);
		task.setNum(num);
		task.setIllustration(illustration);
		int res= this.getDao().insert(task);
		if(res>0){
			return true;
		}
		return false;
	}

	public boolean del(Integer id) {
		Task task=new Task();
		task.setId(id);
		task.setDelFlag(Const.DEL_FLAG_DEL);		
		int res= this.getDao().update(task);
		if(res>0){
			return true;
		}
		return false;
	}

	public boolean edit(Integer id, String title, Integer catId, Byte type, String price, Integer num, String illustration) {
		Task task=new Task();
		task.setId(id);		
		task.setTitle(title);
		task.setTitle(title);
		task.setCatId(catId);
		task.setType(type);
		task.setPrice(price);
		task.setNum(num);
		task.setIllustration(illustration);
		int res= this.getDao().update(task);
		if(res>0){
			return true;
		}
		return false;
	}

	public List<Task> getList(List<Integer> taskIds, Byte status, Byte sign,
			Integer catId, List<Integer> catIds,Byte type,String title, Integer start, Integer limit) {
		TaskExample example = new TaskExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		if(!ValidateUtils.isNull(sign)){
			if(taskIds.size()>0){
				if(sign.intValue()==0){
					crit.andIdNotIn(taskIds);
				}else if (sign.intValue()==1) {
					crit.andIdIn(taskIds);
				}
			}else{
				if (sign.intValue()==1) {
					crit.andIdEqualTo(-1);
				}
			}
			
		}
		if(!ValidateUtils.isNull(title)){
			crit.andTitleLike("%"+title+"%");
		}
		if(!ValidateUtils.isNull(type)){
			crit.andTypeEqualTo(type);
		}		
		if(!ValidateUtils.isNull(status)){
			crit.andStatusEqualTo(status);
		}
		if(!ValidateUtils.isNull(catId)){
			if(catId==-1){
				crit.andCatIdIn(catIds);
			}else{
				crit.andCatIdEqualTo(catId);
			}
			
		}
		if(!ValidateUtils.isNull(start)){
			example.setLimitStart(start);
			example.setLimitEnd(limit);
		}
		
		example.setOrderByClause("id desc");
		return this.getDao().list(example);
	}

	public List<Task> adminList(Integer id, String title, Byte status,
			Byte type, Integer catId, Date startDate, Date endDate,
			Integer start, Integer limit) {
		TaskExample example = new TaskExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		if(!ValidateUtils.isNull(id)){
			crit.andIdEqualTo(id);
		}
		if(!ValidateUtils.isNull(title)){
			crit.andTitleLike("%"+title+"%");
		}
		if(!ValidateUtils.isNull(type)){
			crit.andTypeEqualTo(type);
		}
		if(!ValidateUtils.isNull(status)){
			crit.andStatusEqualTo(status);
		}
		if(!ValidateUtils.isNull(catId)){
			crit.andCatIdEqualTo(catId);
			
		}
		if(!ValidateUtils.isNull(startDate)){
			crit.andAddTimeGreaterThanOrEqualTo(startDate);
		}
		if(!ValidateUtils.isNull(endDate)){
			crit.andAddTimeLessThanOrEqualTo(endDate);
		}
		if(!ValidateUtils.isNull(start)){
			example.setLimitStart(start);
			example.setLimitEnd(limit);
		}
		example.setOrderByClause("id desc");
		return this.getDao().list(example);
	}

	public void updateNum(Integer taskId, Integer signNum, Integer bidNum) {
		Task task=new Task();
		task.setId(taskId);
		if(!ValidateUtils.isNull(signNum)){
			task.setSignNum(signNum);
		}
		if(!ValidateUtils.isNull(bidNum)){
			task.setBidNum(bidNum);
		}
		this.getDao().update(task);
	}

	public int getListCount(List<Integer> taskIds, Byte status, Byte sign,
			Integer catId, List<Integer> catIds) {
		TaskExample example = new TaskExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		if(!ValidateUtils.isNull(sign)){
			if(taskIds.size()>0){
				if(sign.intValue()==0){
					crit.andIdNotIn(taskIds);
				}else if (sign.intValue()==1) {
					crit.andIdIn(taskIds);
				}
			}else{
				if (sign.intValue()==1) {
					crit.andIdEqualTo(-1);
				}
			}
			
		}
		crit.andTypeEqualTo(Const.TASK_TYPE_NOMAL);
		if(!ValidateUtils.isNull(status)){
			crit.andStatusEqualTo(status);
		}
		if(!ValidateUtils.isNull(catId)){
			if(catId==-1){
				crit.andCatIdIn(catIds);
			}else{
				crit.andCatIdEqualTo(catId);
			}
			
		}
		return this.getDao().count(example);
	}

	public int adminListCount(Integer id, String title, Byte status, Byte type,
			Integer catId, Date startDate, Date endDate) {
		TaskExample example = new TaskExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		if(!ValidateUtils.isNull(id)){
			crit.andIdEqualTo(id);
		}
		if(!ValidateUtils.isNull(title)){
			crit.andTitleLike("%"+title+"%");
		}
		if(!ValidateUtils.isNull(type)){
			crit.andTypeEqualTo(type);
		}
		if(!ValidateUtils.isNull(status)){
			crit.andStatusEqualTo(status);
		}
		if(!ValidateUtils.isNull(catId)){
			crit.andCatIdEqualTo(catId);
			
		}
		if(!ValidateUtils.isNull(startDate)){
			crit.andAddTimeGreaterThanOrEqualTo(startDate);
		}
		if(!ValidateUtils.isNull(endDate)){
			crit.andAddTimeLessThanOrEqualTo(endDate);
		}
		return this.getDao().count(example);
	}
	
	


}