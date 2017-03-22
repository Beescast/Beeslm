package org.bees.biz.persistence.manager;

import java.util.Date;
import java.util.List;

import org.bees.biz.persistence.dao.ProductDao;
import org.bees.biz.persistence.model.Product;
import org.bees.biz.persistence.model.ProductExample;
import org.bees.biz.persistence.model.ProductExample.Criteria;
import org.bees.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseManager;
import projects.commons.utils.ValidateUtils;

@Component
public class ProductManager extends BaseManager<ProductDao> {

	@Autowired
	private ProductDao dao;

	@Override
	public ProductDao getDao() {
		return dao;
	}

	public Product getInfoById(Integer id) {
		ProductExample example = new ProductExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		crit.andIdEqualTo(id);
		return this.getDao().getByExample(example);
	}

	public List<Product> getList(Byte position, Integer limit, Integer start) {
		ProductExample example = new ProductExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		if(!ValidateUtils.isNull(position)){
			crit.andPositionEqualTo(position);
		}
		example.setLimitStart(start);
		example.setLimitEnd(limit);
		example.setOrderByClause("id desc");
		return this.getDao().list(example);
	}

	public boolean add(String name, String pic, String href, Byte position) {
		Product product=new Product();
		product.setAddTime(new Date());
		product.setHref(href);
		product.setPic(pic);
		product.setPosition(position);
		product.setName(name);
		int res= this.getDao().insert(product);
		if(res>0){
			return true;
		}
		return false;
	}

	public boolean del(Integer id) {
		Product product=new Product();
		product.setId(id);
		product.setDelFlag(Const.DEL_FLAG_DEL);		
		int res= this.getDao().update(product);
		if(res>0){
			return true;
		}
		return false;
	}

	public boolean edit(Integer id,  String name, String pic, String href, Byte position) {
		Product product=new Product();
		product.setId(id);
		product.setHref(href);
		product.setPic(pic);
		product.setPosition(position);
		product.setName(name);
		int res= this.getDao().update(product);
		if(res>0){
			return true;
		}
		return false;
	}

	public Integer getListCount(Byte position) {
		ProductExample example = new ProductExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);	
		if(!ValidateUtils.isNull(position)){
			crit.andPositionEqualTo(position);
		}
		return this.getDao().count(example);
	}
	
	


}