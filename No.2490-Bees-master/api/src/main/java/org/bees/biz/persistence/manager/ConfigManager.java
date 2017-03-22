package org.bees.biz.persistence.manager;

import java.util.Date;
import java.util.List;

import org.bees.biz.persistence.dao.ConfigDao;
import org.bees.biz.persistence.model.Config;
import org.bees.biz.persistence.model.ConfigExample;
import org.bees.biz.persistence.model.ConfigExample.Criteria;
import org.bees.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseManager;

@Component
public class ConfigManager extends BaseManager<ConfigDao> {

	@Autowired
	private ConfigDao dao;

	@Override
	public ConfigDao getDao() {
		return dao;
	}

	public Config getInfoById(Integer id) {
		ConfigExample example = new ConfigExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		crit.andIdEqualTo(id);
		return this.getDao().getByExample(example);
	}

	public List<Config> getList(Integer limit, Integer start) {
		ConfigExample example = new ConfigExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		example.setLimitStart(start);
		example.setLimitEnd(limit);
		example.setOrderByClause("id desc");
		return this.getDao().list(example);
	}

	public boolean add(Integer userId, String title, String content, Byte status) {
		Config config=new Config();
		config.setAddTime(new Date());
		int res= this.getDao().insert(config);
		if(res>0){
			return true;
		}
		return false;
	}

	public boolean del(Integer id) {
		Config config=new Config();
		config.setId(id);
		config.setDelFlag(Const.DEL_FLAG_DEL);		
		int res= this.getDao().update(config);
		if(res>0){
			return true;
		}
		return false;
	}

	public boolean edit(Integer id, String value) {
		Config config=new Config();
		config.setId(id);
		config.setValue(value);		
		int res= this.getDao().update(config);
		if(res>0){
			return true;
		}
		return false;
	}

	public Integer getListCount() {
		ConfigExample example = new ConfigExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);		
		return this.getDao().count(example);
	}
	
	


}