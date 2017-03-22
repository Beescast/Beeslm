package org.bees.biz.persistence.manager;

import java.util.Date;
import java.util.List;

import org.bees.biz.persistence.dao.MarketDao;
import org.bees.biz.persistence.model.Market;
import org.bees.biz.persistence.model.MarketExample;
import org.bees.biz.persistence.model.MarketExample.Criteria;
import org.bees.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseManager;
import projects.commons.utils.ValidateUtils;

@Component
public class MarketManager extends BaseManager<MarketDao> {

	@Autowired
	private MarketDao dao;

	@Override
	public MarketDao getDao() {
		return dao;
	}


	public List<Market> lists(List<Integer> uids, Integer id, Date startDate,
			Date endDate, Integer startTime, Integer endTime, Integer start,
			Integer limit) {
		MarketExample example = new MarketExample();
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
		MarketExample example = new MarketExample();
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