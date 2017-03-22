package org.bees.biz.persistence.manager;

import java.util.Date;
import java.util.List;

import org.bees.biz.persistence.dao.FriendLinkDao;
import org.bees.biz.persistence.model.FriendLink;
import org.bees.biz.persistence.model.FriendLinkExample;
import org.bees.biz.persistence.model.FriendLinkExample.Criteria;
import org.bees.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseManager;
import projects.commons.utils.ValidateUtils;

@Component
public class FriendLinkManager extends BaseManager<FriendLinkDao> {

	@Autowired
	private FriendLinkDao dao;

	@Override
	public FriendLinkDao getDao() {
		return dao;
	}

	public FriendLink getInfoById(Integer id) {
		FriendLinkExample example = new FriendLinkExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		crit.andIdEqualTo(id);
		return this.getDao().getByExample(example);
	}

	public List<FriendLink> getList(Integer start, Integer limit) {
		FriendLinkExample example = new FriendLinkExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		if(!ValidateUtils.isNull(start)){
			example.setLimitStart(start);
			example.setLimitEnd(limit);
		}
		example.setOrderByClause("orders asc");
		return this.getDao().list(example);
	}

	public boolean add(String title, String href,
			int orders) {
		FriendLink friendLink=new FriendLink();
		friendLink.setAddTime(new Date());
		friendLink.setTitle(title);
		friendLink.setHref(href);
		friendLink.setOrders(orders);
		int res=this.getDao().insert(friendLink);
		if(res>0){
			return true;
		}
		return false;
	}
	
	public boolean del(Integer id) {
		FriendLink friendLink=new FriendLink();
		friendLink.setId(id);
		
		friendLink.setDelFlag(Const.DEL_FLAG_DEL);		
		int res= this.getDao().update(friendLink);
		if(res>0){
			return true;
		}
		return false;
	}

	public boolean edit(Integer id, String title, String href,
			int orders) {
		FriendLink friendLink=new FriendLink();
		friendLink.setId(id);
		friendLink.setTitle(title);
		friendLink.setHref(href);
		friendLink.setOrders(orders);
		int res=this.getDao().update(friendLink);
		if(res>0){
			return true;
		}
		return false;
	}

	public int getListCount() {
		FriendLinkExample example = new FriendLinkExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		return this.getDao().count(example);
	}
	


}