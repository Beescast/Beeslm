package org.bees.biz.persistence.manager;

import java.util.Date;
import java.util.List;

import org.bees.biz.persistence.dao.LiveDao;
import org.bees.biz.persistence.model.Live;
import org.bees.biz.persistence.model.LiveExample;
import org.bees.biz.persistence.model.LiveExample.Criteria;
import org.bees.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseManager;
import projects.commons.utils.ValidateUtils;

@Component
public class LiveManager extends BaseManager<LiveDao> {

	@Autowired
	private LiveDao dao;

	@Override
	public LiveDao getDao() {
		return dao;
	}
	
	public Live getInfoById(Integer id) {
		LiveExample example = new LiveExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		crit.andIdEqualTo(id);
		return this.getDao().getByExample(example);
	}

	public List<Live> lists(List<Integer> uids, Integer id, Date startDate,
			Date endDate, Integer startTime, Integer endTime, Integer start,
			Integer limit) {
		LiveExample example = new LiveExample();
		Criteria crit = example.createCriteria();
		if(!ValidateUtils.isNull(id)){
			crit.andIdEqualTo(id);
		}
		if(!ValidateUtils.isNull(uids)){
			crit.andUidIn(uids);
		}
		if(!ValidateUtils.isNull(startDate)){
			crit.andDatesGreaterThanOrEqualTo(startDate);
		}
		if(!ValidateUtils.isNull(endDate)){
			crit.andDatesLessThanOrEqualTo(endDate);
		}
		if(!ValidateUtils.isNull(startTime)){
			crit.andTimesGreaterThanOrEqualTo(startTime);
		}
		if(!ValidateUtils.isNull(endTime)){
			crit.andTimesLessThanOrEqualTo(endTime);
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
			Date endDate, Integer startTime, Integer endTime) {
		LiveExample example = new LiveExample();
		Criteria crit = example.createCriteria();
		
		if(!ValidateUtils.isNull(uids)){
			crit.andUidIn(uids);
		}
		if(!ValidateUtils.isNull(startDate)){
			crit.andDatesGreaterThanOrEqualTo(startDate);
		}
		if(!ValidateUtils.isNull(endDate)){
			crit.andDatesLessThanOrEqualTo(endDate);
		}
		if(!ValidateUtils.isNull(startTime)){
			crit.andTimesGreaterThanOrEqualTo(startTime);
		}
		if(!ValidateUtils.isNull(endTime)){
			crit.andTimesLessThanOrEqualTo(endTime);
		}
		
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		
		return this.getDao().count(example);
	}

}