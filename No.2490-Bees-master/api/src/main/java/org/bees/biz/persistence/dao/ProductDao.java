package org.bees.biz.persistence.dao;

import org.bees.biz.persistence.mapper.ProductMapper;
import org.bees.biz.persistence.model.Product;
import org.bees.biz.persistence.model.ProductExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseDAO;



@Component
public class ProductDao extends
		BaseDAO<Product, Integer, ProductMapper, ProductExample> {
	
	@Autowired
	private ProductMapper mapper;

	@Override
	public ProductMapper getMapper() {
		return mapper;
	}

}