package org.bees.biz.persistence.dao;

import org.bees.biz.persistence.mapper.PackagesPriceMapper;
import org.bees.biz.persistence.model.PackagesPrice;
import org.bees.biz.persistence.model.PackagesPriceExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseDAO;



@Component
public class PackagesPriceDao extends
		BaseDAO<PackagesPrice, Integer, PackagesPriceMapper, PackagesPriceExample> {
	
	@Autowired
	private PackagesPriceMapper mapper;

	@Override
	public PackagesPriceMapper getMapper() {
		return mapper;
	}

}