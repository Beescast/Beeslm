package org.bees.biz.persistence.manager;

import java.util.Date;
import java.util.List;

import org.bees.biz.persistence.dao.LivePeopleDao;
import org.bees.biz.persistence.model.LivePeople;
import org.bees.biz.persistence.model.LivePeopleExample;
import org.bees.biz.persistence.model.LivePeopleExample.Criteria;
import org.bees.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseManager;
import projects.commons.utils.ValidateUtils;

@Component
public class LivePeopleManager extends BaseManager<LivePeopleDao> {

	@Autowired
	private LivePeopleDao dao;

	@Override
	public LivePeopleDao getDao() {
		return dao;
	}

	public LivePeople getInfoById(Integer id) {
		LivePeopleExample example = new LivePeopleExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		crit.andIdEqualTo(id);
		return this.getDao().getByExample(example);
	}


	public List<LivePeople> lists(List<Integer> uids, Integer id, Date startDate,
			Date endDate, Integer start, Integer limit) {
		LivePeopleExample example = new LivePeopleExample();
		Criteria crit = example.createCriteria();
		
		if(!ValidateUtils.isNull(uids)){
			crit.andUidIn(uids);
		}
		if(!ValidateUtils.isNull(id)){
			crit.andIdEqualTo(id);
		}
		if(!ValidateUtils.isNull(startDate)){
			crit.andDatesGreaterThanOrEqualTo(startDate);
		}
		if(!ValidateUtils.isNull(endDate)){
			crit.andDatesLessThanOrEqualTo(endDate);
		}
		
		
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		if(!ValidateUtils.isNull(start)){
			example.setLimitStart(start);
			example.setLimitEnd(limit);
		}
		
		example.setOrderByClause("id desc");
		return this.getDao().list(example);
	}

	public int listsCount(List<Integer> uids, Integer id, Date startDate,
			Date endDate) {
		LivePeopleExample example = new LivePeopleExample();
		Criteria crit = example.createCriteria();
		
		if(!ValidateUtils.isNull(uids)){
			crit.andUidIn(uids);
		}
		if(!ValidateUtils.isNull(id)){
			crit.andIdEqualTo(id);
		}
		if(!ValidateUtils.isNull(startDate)){
			crit.andDatesGreaterThanOrEqualTo(startDate);
		}
		if(!ValidateUtils.isNull(endDate)){
			crit.andDatesLessThanOrEqualTo(endDate);
		}
		
		
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		return this.getDao().count(example);
	}


}