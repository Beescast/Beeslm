package org.bees.biz.persistence.dao;

import org.bees.biz.persistence.mapper.ConfigMapper;
import org.bees.biz.persistence.model.Config;
import org.bees.biz.persistence.model.ConfigExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseDAO;



@Component
public class ConfigDao extends
		BaseDAO<Config, Integer, ConfigMapper, ConfigExample> {
	
	@Autowired
	private ConfigMapper mapper;

	@Override
	public ConfigMapper getMapper() {
		return mapper;
	}

}