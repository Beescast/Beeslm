package org.bees.biz.persistence.manager;

import java.util.Date;
import java.util.List;

import org.bees.biz.persistence.dao.LiveGiftDao;
import org.bees.biz.persistence.model.LiveGift;
import org.bees.biz.persistence.model.LiveGiftExample;
import org.bees.biz.persistence.model.LiveGiftExample.Criteria;
import org.bees.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseManager;
import projects.commons.utils.ValidateUtils;

@Component
public class LiveGiftManager extends BaseManager<LiveGiftDao> {

	@Autowired
	private LiveGiftDao dao;

	@Override
	public LiveGiftDao getDao() {
		return dao;
	}

	public LiveGift getInfoById(Integer id) {
		LiveGiftExample example = new LiveGiftExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		crit.andIdEqualTo(id);
		return this.getDao().getByExample(example);
	}

	public List<LiveGift> lists(Integer id,
			Date startDate, Date endDate, Integer startTime, Integer endTime,
			Integer start, Integer limit) {
		LiveGiftExample example = new LiveGiftExample();
		Criteria crit = example.createCriteria();
		if(!ValidateUtils.isNull(id)){
			crit.andIdEqualTo(id);
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

	public int listsCount(Integer id, Date startDate, Date endDate,
			Integer startTime, Integer endTime) {
		LiveGiftExample example = new LiveGiftExample();
		Criteria crit = example.createCriteria();
		if(!ValidateUtils.isNull(id)){
			crit.andIdEqualTo(id);
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