package org.bees.biz.persistence.manager;

import java.util.Date;
import java.util.List;

import org.bees.biz.persistence.dao.LeaveMsgDao;
import org.bees.biz.persistence.model.LeaveMsg;
import org.bees.biz.persistence.model.LeaveMsgExample;
import org.bees.biz.persistence.model.LeaveMsgExample.Criteria;
import org.bees.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseManager;
import projects.commons.utils.ValidateUtils;

@Component
public class LeaveMsgManager extends BaseManager<LeaveMsgDao> {

	@Autowired
	private LeaveMsgDao dao;

	@Override
	public LeaveMsgDao getDao() {
		return dao;
	}

	public LeaveMsg getInfoById(Integer id) {
		LeaveMsgExample example = new LeaveMsgExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		crit.andIdEqualTo(id);
		return this.getDao().getByExample(example);
	}

	public List<LeaveMsg> getList(List<Integer> uids, String content, Integer start, Integer limit) {
		LeaveMsgExample example = new LeaveMsgExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		if(!ValidateUtils.isNull(uids)){
			crit.andUidIn(uids);
		}
		if(!ValidateUtils.isNull(content)){
			crit.andContentLike("%"+content+"%");
		}
		if(!ValidateUtils.isNull(start)){
			example.setLimitStart(start);
			example.setLimitEnd(limit);
		}
		example.setOrderByClause("id desc");
		return this.getDao().list(example);
	}

	public boolean add(Integer userId, String content) {
		LeaveMsg leaveMsg=new LeaveMsg();
		leaveMsg.setUid(userId);
		leaveMsg.setContent(content);
		leaveMsg.setAddTime(new Date());
		int res=this.getDao().insert(leaveMsg);
		if(res>0){
			return true;
		}
		return false;
	}
	
	public boolean del(Integer id) {
		LeaveMsg LeaveMsg=new LeaveMsg();
		LeaveMsg.setId(id);
		LeaveMsg.setDelFlag(Const.DEL_FLAG_DEL);		
		int res= this.getDao().update(LeaveMsg);
		if(res>0){
			return true;
		}
		return false;
	}

	public boolean edit(Integer id, String title, String content, String page,
			String position, int picOrder, String picHref, String picUrl) {
		LeaveMsg LeaveMsg=new LeaveMsg();
		LeaveMsg.setId(id);
		LeaveMsg.setContent(content);
		int res=this.getDao().update(LeaveMsg);
		if(res>0){
			return true;
		}
		return false;
	}

	public int getListCount(List<Integer> uids,  String content) {
		LeaveMsgExample example = new LeaveMsgExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		if(!ValidateUtils.isNull(uids)){
			crit.andUidIn(uids);
		}
		
		if(!ValidateUtils.isNull(content)){
			crit.andContentLike("%"+content+"%");
		}
		return this.getDao().count(example);
	}
	


}