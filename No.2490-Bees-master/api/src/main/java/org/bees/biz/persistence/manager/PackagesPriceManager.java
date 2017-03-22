package org.bees.biz.persistence.manager;

import java.util.Date;
import java.util.List;

import org.bees.biz.persistence.dao.PackagesPriceDao;
import org.bees.biz.persistence.model.PackagesPrice;
import org.bees.biz.persistence.model.PackagesPriceExample;
import org.bees.biz.persistence.model.PackagesPriceExample.Criteria;
import org.bees.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseManager;

@Component
public class PackagesPriceManager extends BaseManager<PackagesPriceDao> {

	@Autowired
	private PackagesPriceDao dao;

	@Override
	public PackagesPriceDao getDao() {
		return dao;
	}

	public List<PackagesPrice> getPriceByPid(Integer id) {
		PackagesPriceExample example = new PackagesPriceExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		crit.andPackageIdEqualTo(id);
		return this.getDao().list(example);
	}
	
	public PackagesPrice getInfoById(Integer id) {
		PackagesPriceExample example = new PackagesPriceExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		crit.andIdEqualTo(id);
		return this.getDao().getByExample(example);
	}

	public void add(int id, String name, String price) {
		PackagesPrice packagePrice=new PackagesPrice();
		packagePrice.setPackageId(id);
		packagePrice.setAddTime(new Date());
		packagePrice.setName(name);
		packagePrice.setPrice(price);
		this.getDao().insert(packagePrice);
		
	}

	public void edit(Integer id, String name, String price) {
		PackagesPrice packagePrice=new PackagesPrice();
		packagePrice.setId(id);
		packagePrice.setName(name);
		packagePrice.setPrice(price);
		this.getDao().update(packagePrice);
		
	}

	public void dels(List<Integer> ids) {
		PackagesPriceExample packagePrice=new PackagesPriceExample();
		Criteria crit=packagePrice.createCriteria();
		crit.andIdIn(ids);
		PackagesPrice price=new PackagesPrice();
		price.setDelFlag(Const.DEL_FLAG_DEL);
		this.getDao().update(price,packagePrice);
		
	}


}