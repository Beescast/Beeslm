package org.bees.biz.persistence.manager;

import java.util.Date;
import java.util.List;

import org.bees.biz.persistence.dao.AdminDao;
import org.bees.biz.persistence.model.Admin;
import org.bees.biz.persistence.model.AdminExample;
import org.bees.biz.persistence.model.AdminExample.Criteria;
import org.bees.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseManager;
import projects.commons.utils.ValidateUtils;

@Component
public class AdminManager extends BaseManager<AdminDao> {

	@Autowired
	private AdminDao dao;

	@Override
	public AdminDao getDao() {
		return dao;
	}

	public Admin getInfoById(Integer id) {
		AdminExample example = new AdminExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		crit.andIdEqualTo(id);
		return this.getDao().getByExample(example);
	}

	public List<Admin> getList(int start, int limit) {
		AdminExample example = new AdminExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		if(!ValidateUtils.isNull(start)){
			example.setLimitStart(start);
			example.setLimitEnd(limit);
		}
		
		example.setOrderByClause("id desc");
		return this.getDao().list(example);
	}

	public Admin getInfoByName(String name) {
		AdminExample example = new AdminExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		crit.andNameEqualTo(name);
		return this.getDao().getByExample(example);
		
	}

	public int getListCount() {
		AdminExample example = new AdminExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		return this.getDao().count(example);
	}

	public boolean add(String name, String password, String ops) {
		Admin admin=new Admin();
		admin.setName(name);
		admin.setPasswd(password);
		admin.setOps(ops);
		admin.setAddTime(new Date());
		int res=this.getDao().insert(admin);
		if(res>0){
			return true;
		}
		return false;
	}
	
	public boolean edit(int id,String name, String password, String ops) {
		Admin admin=new Admin();
		admin.setId(id);
		admin.setName(name);
		if(!ValidateUtils.isNull(password)&&!password.equals("")){
			admin.setPasswd(password);
		}
		admin.setOps(ops);
		admin.setAddTime(new Date());
		int res=this.getDao().update(admin);
		if(res>0){
			return true;
		}
		return false;
	}
	
	public boolean del(Integer id) {
		Admin admin=new Admin();
		admin.setId(id);
		admin.setDelFlag(Const.DEL_FLAG_DEL);		
		int res= this.getDao().update(admin);
		if(res>0){
			return true;
		}
		return false;
	}



}