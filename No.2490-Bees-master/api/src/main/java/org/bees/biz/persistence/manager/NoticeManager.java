package org.bees.biz.persistence.manager;

import java.util.Date;
import java.util.List;

import org.bees.biz.persistence.dao.NoticeDao;
import org.bees.biz.persistence.model.Notice;
import org.bees.biz.persistence.model.NoticeExample;
import org.bees.biz.persistence.model.NoticeExample.Criteria;
import org.bees.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseManager;
import projects.commons.utils.ValidateUtils;

@Component
public class NoticeManager extends BaseManager<NoticeDao> {

	@Autowired
	private NoticeDao dao;

	@Override
	public NoticeDao getDao() {
		return dao;
	}

	public Notice getInfoById(Integer id) {
		NoticeExample example = new NoticeExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		crit.andIdEqualTo(id);
		return this.getDao().getByExample(example);
	}

	public List<Notice> getList(String title, Byte status, Date startDate, Date endDate, Integer limit, Integer start) {
		NoticeExample example = new NoticeExample();
		Criteria crit = example.createCriteria();
		if(!ValidateUtils.isNull(title)){
			crit.andTitleLike("%"+title+"%");
		}
		if(!ValidateUtils.isNull(status)){
			crit.andStatusEqualTo(status);
		}
		if(!ValidateUtils.isNull(startDate)){
			crit.andAddTimeGreaterThanOrEqualTo(startDate);
		}
		if(!ValidateUtils.isNull(endDate)){
			crit.andAddTimeLessThanOrEqualTo(endDate);
		}
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		example.setLimitStart(start);
		example.setLimitEnd(limit);
		example.setOrderByClause("id desc");
		return this.getDao().list(example);
	}

	public boolean add(Integer userId, String title, String content, Byte status) {
		Notice notice=new Notice();
		notice.setAddTime(new Date());
		notice.setContent(content);
		notice.setCreateId(userId);
		notice.setTitle(title);
		notice.setStatus(status);
		int res= this.getDao().insert(notice);
		if(res>0){
			return true;
		}
		return false;
	}

	public boolean del(Integer id) {
		Notice notice=new Notice();
		notice.setId(id);
		notice.setDelFlag(Const.DEL_FLAG_DEL);		
		int res= this.getDao().update(notice);
		if(res>0){
			return true;
		}
		return false;
	}

	public boolean edit(Integer id, String title, String content, Byte status) {
		Notice notice=new Notice();
		notice.setId(id);		
		notice.setContent(content);		
		notice.setTitle(title);
		notice.setStatus(status);
		int res= this.getDao().update(notice);
		if(res>0){
			return true;
		}
		return false;
	}

	public Integer getListCount(String title, Byte status, Date startDate,
			Date endDate) {
		NoticeExample example = new NoticeExample();
		Criteria crit = example.createCriteria();
		if(!ValidateUtils.isNull(title)){
			crit.andTitleLike("%"+title+"%");
		}
		if(!ValidateUtils.isNull(status)){
			crit.andStatusEqualTo(status);
		}
		if(!ValidateUtils.isNull(startDate)){
			crit.andAddTimeGreaterThanOrEqualTo(startDate);
		}
		if(!ValidateUtils.isNull(endDate)){
			crit.andAddTimeLessThanOrEqualTo(endDate);
		}
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);		
		return this.getDao().count(example);
	}
	
	


}