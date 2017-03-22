package org.bees.biz.persistence.manager;

import java.util.Date;
import java.util.List;

import org.bees.biz.persistence.dao.TaskSignDao;
import org.bees.biz.persistence.model.TaskSign;
import org.bees.biz.persistence.model.TaskSignExample;
import org.bees.biz.persistence.model.TaskSignExample.Criteria;
import org.bees.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseManager;
import projects.commons.utils.ValidateUtils;

@Component
public class TaskSignManager extends BaseManager<TaskSignDao> {

	@Autowired
	private TaskSignDao dao;

	@Override
	public TaskSignDao getDao() {
		return dao;
	}

	public TaskSign getInfoById(Integer id) {
		TaskSignExample example = new TaskSignExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		crit.andIdEqualTo(id);
		return this.getDao().getByExample(example);
	}

	public List<TaskSign> getList(Integer id, List<Integer> taskIds, Byte bidStatus, Byte payStatus, Date startDate, Date endDate, String timeType, Integer start, Integer limit) {
		TaskSignExample example = new TaskSignExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		if(!ValidateUtils.isNull(id)){
			crit.andIdEqualTo(id);
		}
		if(!ValidateUtils.isNull(start)){
			example.setLimitStart(start);
			example.setLimitEnd(limit);
		}
		if(!ValidateUtils.isNull(bidStatus)){
			crit.andBidStatusNotEqualTo(new Byte("0"));
			//crit.andBidStatusEqualTo(bidStatus);
		}
		if(!ValidateUtils.isNull(payStatus)){
			crit.andPayStatusEqualTo(payStatus);
		}
		if(!ValidateUtils.isNull(taskIds)){
			crit.andTaskIdIn(taskIds);
		}
		if(!ValidateUtils.isNull(startDate)||!ValidateUtils.isNull(endDate)){
			if(timeType.equals("bid")){
				if(!ValidateUtils.isNull(startDate)){
					crit.andBidTimeGreaterThanOrEqualTo(startDate);
				}
				if(!ValidateUtils.isNull(endDate)){
					crit.andBidTimeLessThanOrEqualTo(endDate);
				}
			}else if (timeType.equals("pay")) {
				if(!ValidateUtils.isNull(startDate)){
					crit.andPayTimeGreaterThanOrEqualTo(startDate);
				}
				if(!ValidateUtils.isNull(endDate)){
					crit.andPayTimeLessThanOrEqualTo(endDate);
				}
			}else if (timeType.equals("op")) {
				if(!ValidateUtils.isNull(startDate)){
					crit.andOpTimeGreaterThanOrEqualTo(startDate);
				}
				if(!ValidateUtils.isNull(endDate)){
					crit.andOpTimeLessThanOrEqualTo(endDate);
				}
			}
		}
		example.setOrderByClause("id desc");
		return this.getDao().list(example);
	}

	public boolean add(Integer taskId, Integer userId,  String price,Byte taskBidYes) {
		TaskSign taskSign=new TaskSign();
		taskSign.setTaskId(taskId);
		taskSign.setUid(userId);
		taskSign.setSinglePrice(price);
		if(!ValidateUtils.isNull(taskBidYes)){
			taskSign.setBidStatus(taskBidYes);
			taskSign.setBidTime(new Date());
		}
		taskSign.setAddTime(new Date());
		int res=this.getDao().insert(taskSign);
		if(res>0){
			return true;
		}
		return false;
	}
	
	public boolean del(Integer id) {
		TaskSign TaskSign=new TaskSign();
		TaskSign.setId(id);
		TaskSign.setDelFlag(Const.DEL_FLAG_DEL);		
		int res= this.getDao().update(TaskSign);
		if(res>0){
			return true;
		}
		return false;
	}

	public boolean edit(Integer id, String title, String content, String page,
			String position, int picOrder, String picHref, String picUrl) {
		TaskSign TaskSign=new TaskSign();
		TaskSign.setId(id);
		
		int res=this.getDao().update(TaskSign);
		if(res>0){
			return true;
		}
		return false;
	}

	public List<TaskSign> getListByUserId(Integer userId) {
		TaskSignExample example = new TaskSignExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		crit.andUidEqualTo(userId);
		example.setOrderByClause("id desc");
		return this.getDao().list(example);
	}

	public boolean sign(Integer taskId, List<Integer> uid,String price) {
		TaskSignExample example = new TaskSignExample();
		Criteria crit = example.createCriteria();
		crit.andUidIn(uid);
		crit.andTaskIdEqualTo(taskId);
		TaskSign taskSign=new TaskSign();
		taskSign.setSinglePrice(price);
		taskSign.setBidStatus(Const.BID_STATUS_BID);
		taskSign.setBidTime(new Date());
		int res=this.getDao().update(taskSign,example);
		if (res>0) {
			return true;
		}
		return false;
		
	}

	public int getNum(Integer taskId, Byte bidStatus) {
		TaskSignExample example = new TaskSignExample();
		Criteria crit = example.createCriteria();
		crit.andTaskIdEqualTo(taskId);
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		if(!ValidateUtils.isNull(bidStatus)){
			crit.andBidStatusEqualTo(bidStatus);
		}
		return this.getDao().count(example);
	}

	public TaskSign getByTaskUid(Integer taskId, Integer userId) {
		TaskSignExample example=new TaskSignExample();
		Criteria crit = example.createCriteria();
		crit.andUidEqualTo(userId);
		crit.andTaskIdEqualTo(taskId);
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		return this.getDao().getByExample(example);
	}

	public int getSignListCount(Integer id, List<Integer> taskIds, Byte bidStatus, Byte payStatus, Date startDate, Date endDate, String timeType) {
		TaskSignExample example = new TaskSignExample();
		Criteria crit = example.createCriteria();
		if(!ValidateUtils.isNull(id)){
			crit.andIdEqualTo(id);
		}
		if(!ValidateUtils.isNull(taskIds)){
			crit.andTaskIdIn(taskIds);
		}
		if(!ValidateUtils.isNull(bidStatus)){
			crit.andBidStatusEqualTo(bidStatus);
		}
		if(!ValidateUtils.isNull(payStatus)){
			crit.andPayStatusEqualTo(payStatus);
		}
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		if(!ValidateUtils.isNull(startDate)||!ValidateUtils.isNull(endDate)){
			if(timeType.equals("bid")){
				if(!ValidateUtils.isNull(startDate)){
					crit.andBidTimeGreaterThanOrEqualTo(startDate);
				}
				if(!ValidateUtils.isNull(endDate)){
					crit.andBidTimeLessThanOrEqualTo(endDate);
				}
			}else if (timeType.equals("pay")) {
				if(!ValidateUtils.isNull(startDate)){
					crit.andPayTimeGreaterThanOrEqualTo(startDate);
				}
				if(!ValidateUtils.isNull(endDate)){
					crit.andPayTimeLessThanOrEqualTo(endDate);
				}
			}else if (timeType.equals("op")) {
				if(!ValidateUtils.isNull(startDate)){
					crit.andOpTimeGreaterThanOrEqualTo(startDate);
				}
				if(!ValidateUtils.isNull(endDate)){
					crit.andOpTimeLessThanOrEqualTo(endDate);
				}
			}
		}
		return this.getDao().count(example);
	}

	public List<TaskSign> getByTaskUids(Integer taskId, List<Integer> uid) {
		TaskSignExample example=new TaskSignExample();
		Criteria crit = example.createCriteria();
		crit.andUidIn(uid);
		crit.andTaskIdEqualTo(taskId);
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		return this.getDao().list(example);
	}

	public boolean unsign(Integer id) {
		TaskSignExample example = new TaskSignExample();
		Criteria crit = example.createCriteria();
		crit.andIdEqualTo(id);
		TaskSign taskSign=new TaskSign();
		taskSign.setBidStatus(Const.BID_STATUS_UNBID);
		int res=this.getDao().update(taskSign,example);
		if (res>0) {
			return true;
		}
		return false;
	}

	public List<TaskSign> getListByUserId(Integer userId, Byte bidStatus) {
		TaskSignExample example = new TaskSignExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		crit.andUidEqualTo(userId);
		if(!ValidateUtils.isNull(bidStatus)&&bidStatus.intValue()==1){
			crit.andBidStatusNotEqualTo(new Byte("0"));
		}		
		return this.getDao().list(example);
	}


}