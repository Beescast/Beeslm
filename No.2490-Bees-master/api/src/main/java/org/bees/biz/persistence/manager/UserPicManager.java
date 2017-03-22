package org.bees.biz.persistence.manager;

import java.util.List;

import org.bees.biz.persistence.dao.UserPicDao;
import org.bees.biz.persistence.model.UserPic;
import org.bees.biz.persistence.model.UserPicExample;
import org.bees.biz.persistence.model.UserPicExample.Criteria;
import org.bees.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseManager;

@Component
public class UserPicManager extends BaseManager<UserPicDao> {

	@Autowired
	private UserPicDao dao;

	@Override
	public UserPicDao getDao() {
		return dao;
	}

		

	public List<UserPic> getUserPicLists(int uid) {
		UserPicExample example = new UserPicExample();
		Criteria criteria=example.createCriteria();
		criteria.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		criteria.andUidEqualTo(uid);
		return this.getDao().list(example);
	}


}