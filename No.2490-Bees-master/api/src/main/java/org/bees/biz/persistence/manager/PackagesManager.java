package org.bees.biz.persistence.manager;

import java.util.Date;
import java.util.List;

import org.bees.biz.persistence.dao.PackagesDao;
import org.bees.biz.persistence.model.Packages;
import org.bees.biz.persistence.model.PackagesExample;
import org.bees.biz.persistence.model.PackagesExample.Criteria;
import org.bees.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseManager;
import projects.commons.utils.ValidateUtils;

@Component
public class PackagesManager extends BaseManager<PackagesDao> {

	@Autowired
	private PackagesDao dao;

	@Override
	public PackagesDao getDao() {
		return dao;
	}

	public Packages getInfoById(Integer id) {
		PackagesExample example = new PackagesExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		crit.andIdEqualTo(id);
		return this.getDao().getByExample(example);
	}

	public int add(String name, String content, String titleOne,
			String titleTwo, String titleThree, String pic) {
		Packages packages=new Packages();
		packages.setAddTime(new Date());
		packages.setName(name);
		packages.setContent(content);
		packages.setTitleOne(titleOne);
		packages.setTitleThree(titleThree);
		packages.setTitleTwo(titleTwo);
		packages.setPic(pic);
		int res=this.getDao().insert(packages);
		if(res>0){
			return packages.getId();
		}
		return 0;
		
	}

	public boolean del(Integer id) {
		Packages packages=new Packages();
		packages.setId(id);
		packages.setDelFlag(Const.DEL_FLAG_DEL);
		int res=this.getDao().insert(packages);
		if(res>0){
			return true;
		}
		return false;
	}

	public boolean edit(Integer id, String name, String content, String titleOne,
			String titleTwo, String titleThree, String pic) {
		Packages packages=new Packages();
		packages.setAddTime(new Date());
		packages.setName(name);
		packages.setContent(content);
		packages.setTitleOne(titleOne);
		packages.setTitleThree(titleThree);
		packages.setTitleTwo(titleTwo);
		packages.setPic(pic);
		packages.setId(id);
		int res=this.getDao().update(packages);
		if(res>0){
			return true;
		}
		return false;
		
	}

	public List<Packages> getList(Integer start, Integer limit) {
		PackagesExample example = new PackagesExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		if(!ValidateUtils.isNull(start)){
			example.setLimitStart(start);
			example.setLimitEnd(limit);
		}
		example.setOrderByClause("id desc");
		return this.getDao().list(example);
	}

	
}