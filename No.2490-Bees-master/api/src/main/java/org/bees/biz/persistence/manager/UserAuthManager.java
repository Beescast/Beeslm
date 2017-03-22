package org.bees.biz.persistence.manager;

import java.util.Date;
import java.util.List;

import org.bees.biz.persistence.dao.UserAuthDao;
import org.bees.biz.persistence.model.UserAuth;
import org.bees.biz.persistence.model.UserAuthExample;
import org.bees.biz.persistence.model.UserAuthExample.Criteria;
import org.bees.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseManager;
import projects.commons.utils.ValidateUtils;

@Component
public class UserAuthManager extends BaseManager<UserAuthDao> {

	@Autowired
	private UserAuthDao dao;

	@Override
	public UserAuthDao getDao() {
		return dao;
	}

	public UserAuth getInfoById(Integer id) {
		UserAuthExample example = new UserAuthExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		crit.andIdEqualTo(id);
		return this.getDao().getByExample(example);
	}

	public List<UserAuth> getList(int start, int limit) {
		UserAuthExample example = new UserAuthExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		if(!ValidateUtils.isNull(start)){
			example.setLimitStart(start);
			example.setLimitEnd(limit);
		}
		
		example.setOrderByClause("id desc");
		return this.getDao().list(example);
	}

	public UserAuth getInfoByName(String name) {
		UserAuthExample example = new UserAuthExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		return this.getDao().getByExample(example);
		
	}

	public int getListCount() {
		UserAuthExample example = new UserAuthExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		return this.getDao().count(example);
	}

	public boolean add(String name, String password, String ops) {
		UserAuth userAuth=new UserAuth();
		
		userAuth.setAddTime(new Date());
		int res=this.getDao().insert(userAuth);
		if(res>0){
			return true;
		}
		return false;
	}
	
	public boolean edit(int id,String name, String password, String ops) {
		UserAuth userAuth=new UserAuth();
		userAuth.setId(id);
		
		userAuth.setAddTime(new Date());
		int res=this.getDao().update(userAuth);
		if(res>0){
			return true;
		}
		return false;
	}
	
	public boolean del(Integer id) {
		UserAuth userAuth=new UserAuth();
		userAuth.setId(id);
		userAuth.setDelFlag(Const.DEL_FLAG_DEL);		
		int res= this.getDao().update(userAuth);
		if(res>0){
			return true;
		}
		return false;
	}

	public UserAuth getByMobile(String mobile) {
		UserAuthExample example = new UserAuthExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		crit.andMobileEqualTo(mobile);
		example.setOrderByClause("id desc");
		return this.getDao().getByExample(example);
	}

	public List<UserAuth> authList(String mobile, String truename, String plat,
			String liveRoom, Date startDate, Date endDate, Byte authStatus,
			Integer id, Integer limit, Integer start) {
		UserAuthExample example = new UserAuthExample();
		Criteria crit=example.createCriteria();
		if(!ValidateUtils.isNull(start)){
			example.setLimitStart(start);
			example.setLimitEnd(limit);
		}
		if(!ValidateUtils.isNull(mobile)){
			crit.andMobileEqualTo(mobile);
		}
		if(!ValidateUtils.isNull(authStatus)){
			crit.andAuthStatusEqualTo(authStatus);
		}
		if(!ValidateUtils.isNull(truename)){
			crit.andTruenameLike("%"+truename+"%");
		}		
		if(!ValidateUtils.isNull(plat)){
			crit.andPlatLike("%"+plat+"%");
		}
		if(!ValidateUtils.isNull(liveRoom)){
			crit.andLiveRoomLike("%"+liveRoom+"%");
		}
		if(!ValidateUtils.isNull(startDate)){
			crit.andAddTimeGreaterThanOrEqualTo(startDate);
		}
		if(!ValidateUtils.isNull(endDate)){
			crit.andAddTimeLessThanOrEqualTo(endDate);
		}
		if(!ValidateUtils.isNull(id)){
			crit.andIdEqualTo(id);
		}
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		example.setOrderByClause("id desc");
		return this.getDao().list(example);
	}

	public Integer authListCount(String mobile, String truename, String plat,
			String liveRoom, Date startDate, Date endDate, Byte authStatus,
			Integer id) {
		UserAuthExample example = new UserAuthExample();
		Criteria crit=example.createCriteria();
		if(!ValidateUtils.isNull(mobile)){
			crit.andMobileEqualTo(mobile);
		}
		if(!ValidateUtils.isNull(authStatus)){
			crit.andAuthStatusEqualTo(authStatus);
		}
		if(!ValidateUtils.isNull(truename)){
			crit.andTruenameLike("%"+truename+"%");
		}		
		if(!ValidateUtils.isNull(plat)){
			crit.andPlatLike("%"+plat+"%");
		}
		if(!ValidateUtils.isNull(liveRoom)){
			crit.andLiveRoomLike("%"+liveRoom+"%");
		}
		if(!ValidateUtils.isNull(startDate)){
			crit.andAddTimeGreaterThanOrEqualTo(startDate);
		}
		if(!ValidateUtils.isNull(endDate)){
			crit.andAddTimeLessThanOrEqualTo(endDate);
		}
		if(!ValidateUtils.isNull(id)){
			crit.andIdEqualTo(id);
		}
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		example.setOrderByClause("id desc");
		return this.getDao().count(example);
	}

	public UserAuth getByIdcard(String idCard) {
		UserAuthExample example = new UserAuthExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		crit.andIdCardEqualTo(idCard);
		example.setOrderByClause("id desc");
		return this.getDao().getByExample(example);
	}

	public void changeMobile(String mobile, String newMobile) {
		UserAuthExample example = new UserAuthExample();
		Criteria crit=example.createCriteria();
		crit.andMobileEqualTo(mobile);
		UserAuth auth=new UserAuth();
		auth.setMobile(newMobile);
		this.getDao().update(auth, example);
	}

}