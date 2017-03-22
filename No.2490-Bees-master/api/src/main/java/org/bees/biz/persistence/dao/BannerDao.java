package org.bees.biz.persistence.dao;

import org.bees.biz.persistence.mapper.BannerMapper;
import org.bees.biz.persistence.model.Banner;
import org.bees.biz.persistence.model.BannerExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseDAO;



@Component
public class BannerDao extends
		BaseDAO<Banner, Integer, BannerMapper, BannerExample> {
	
	@Autowired
	private BannerMapper mapper;

	@Override
	public BannerMapper getMapper() {
		return mapper;
	}

}