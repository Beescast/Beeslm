package org.bees.biz.persistence.manager;

import java.util.Date;
import java.util.List;

import org.bees.biz.persistence.dao.UserDao;
import org.bees.biz.persistence.model.User;
import org.bees.biz.persistence.model.UserExample;
import org.bees.biz.persistence.model.UserExample.Criteria;
import org.bees.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseManager;
import projects.commons.exception.ManagerException;
import projects.commons.utils.ValidateUtils;

@Component
public class UserManager extends BaseManager<UserDao> {

	@Autowired
	private UserDao dao;

	@Override
	public UserDao getDao() {
		return dao;
	}

	public User getInfoById(Integer id) {
		UserExample example = new UserExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		crit.andIdEqualTo(id);
		return this.getDao().getByExample(example);
	}
	
	public User getByMobile(String mobile) throws ManagerException {
        try {
            UserExample example = new UserExample();
            example.createCriteria().andMobileEqualTo(mobile).andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
            return this.getDao().getByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ManagerException(e);
        }
    }

	public List<User> getUserLists(int start, int limit, String order) {
		UserExample example = new UserExample();
		example.setLimitStart(start);
		example.setLimitEnd(limit);
		example.createCriteria().andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		return this.getDao().list(example);
	}

	public boolean setPassword(Integer uid, String safePass) {
		User user=new User();
		user.setId(uid);
		user.setPasswd(safePass);
		int res=this.getDao().update(user);
		if(res<=0){
			return false;
		}
		return true;
	}

	public boolean setPayPassword(Integer uid, String safePass) {
		User user=new User();
		user.setId(uid);
		user.setPayPasswd(safePass);
		int res=this.getDao().update(user);
		if(res<=0){
			return false;
		}
		return true;
	}

	public boolean editById(Integer uid, String email, String contactMobile,
			String contactAddress, String agencyName, String agencyMobile,
			String description, String taskCat, String bankName, 
			String subBankName, String bankCard, String accountName, String avatar, String nickname) {
		User user=new User();
		user.setId(uid);
		if(!ValidateUtils.isNull(avatar)){
			user.setAvatar(avatar);
		}
		if(!ValidateUtils.isNull(nickname)){
			user.setNickname(nickname);
		}
		if(!ValidateUtils.isNull(email)){
			user.setEmail(email);
		}
		if(!ValidateUtils.isNull(contactAddress)){
			user.setContactAddress(contactAddress);
		}
		if(!ValidateUtils.isNull(contactMobile)){
			user.setContactMobile(contactMobile);
		}
		if(!ValidateUtils.isNull(agencyMobile)){
			user.setAgencyMobile(agencyMobile);
		}
		if(!ValidateUtils.isNull(agencyName)){
			user.setAgencyName(agencyName);
		}
		if(!ValidateUtils.isNull(description)){
			user.setDescription(description);
		}
		if(!ValidateUtils.isNull(taskCat)){
			user.setTaskCat(taskCat);
		}
		if(!ValidateUtils.isNull(bankCard)){
			user.setBankCard(bankCard);
		}
		if(!ValidateUtils.isNull(subBankName)){
			user.setSubBankName(subBankName);
		}
		if(!ValidateUtils.isNull(bankName)){
			user.setBankName(bankName);
		}
		if(!ValidateUtils.isNull(accountName)){
			user.setAccountName(accountName);
		}
		int res=this.getDao().update(user);
		if(res<=0){
			return false;
		}
		return true;
	}

	public boolean changeMobile(Integer id, String newMobile) {
		User user=new User();
		user.setId(id);
		user.setMobile(newMobile);
		int res=this.getDao().update(user);
		if(res<=0){
			return false;
		}
		return true;
	}

	public boolean auth(Integer adminId, Integer userId, Byte authStatus) {
		User user=new User();
		user.setId(userId);
		user.setAuthStatus(authStatus);
		user.setAuthOpid(adminId);
		user.setAuthTime(new Date());		
		int res=this.getDao().update(user);
		if(res<=0){
			return false;
		}
		return true;
	}


	public List<User> adminList(String mobile, String nickName,String truename, String plat,
			String liveRoom, Date startDate, Date endDate, Byte authStatus, Integer id, 
			String accountName,Integer balance, Integer limit, Integer start) {
		UserExample example = new UserExample();
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
		if(!ValidateUtils.isNull(nickName)){
			crit.andNicknameLike("%"+nickName+"%");
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
		if(!ValidateUtils.isNull(accountName)){
			crit.andAccountNameEqualTo(accountName);
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
		if(!ValidateUtils.isNull(balance)){
			if(balance.intValue()==1){
				crit.andBalanceEqualTo("0.0");
				example.setOrderByClause("id desc");
			}else{
				crit.andBalanceNotEqualTo("0.0");
				example.setOrderByClause("(balance+0) desc");
			}
		}else{
			example.setOrderByClause("id desc");
		}
		
		return this.getDao().list(example);
	}

	public void setMoney(Integer userId, String balance) {
		User user=new User();
		user.setId(userId);
		user.setBalance(balance);
		this.getDao().update(user);
	}

	public List<User> getTaskUser(Integer catId) {
		UserExample example = new UserExample();
		Criteria crit=example.createCriteria();
		crit.andTaskCatInSet(catId);
		return this.getDao().list(example);
	}

	public Integer adminListCount(String mobile, String nickName, String plat,
			String liveRoom, Date startDate, Date endDate, Byte authStatus, Integer id,
			String accountName, Integer balance) {
		UserExample example = new UserExample();
		Criteria crit=example.createCriteria();
		
		if(!ValidateUtils.isNull(mobile)){
			crit.andMobileEqualTo(mobile);
		}
		if(!ValidateUtils.isNull(nickName)){
			crit.andNicknameEqualTo(nickName);
		}
		if(!ValidateUtils.isNull(plat)){
			crit.andPlatEqualTo(plat);
		}
		if(!ValidateUtils.isNull(liveRoom)){
			crit.andLiveRoomEqualTo(liveRoom);
		}
		if(!ValidateUtils.isNull(accountName)){
			crit.andAccountNameEqualTo(accountName);
		}
		if(!ValidateUtils.isNull(startDate)){
			crit.andAddTimeGreaterThanOrEqualTo(startDate);
		}
		if(!ValidateUtils.isNull(endDate)){
			crit.andAddTimeLessThanOrEqualTo(endDate);
		}
		if(!ValidateUtils.isNull(authStatus)){
			crit.andAuthStatusEqualTo(authStatus);
		}
		if(!ValidateUtils.isNull(id)){
			crit.andIdEqualTo(id);
		}
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		if(!ValidateUtils.isNull(balance)){
			if(balance.intValue()==1){
				crit.andBalanceEqualTo("0.0");
			}else{
				crit.andBalanceNotEqualTo("0.0");
			}
		}
		return this.getDao().count(example);
	}

	public User getByOpenidType(String openid, String type) {
		UserExample example = new UserExample();
		Criteria crit=example.createCriteria();
		if(type.equals(Const.LOGIN_QQ)){
			crit.andQqIdEqualTo(openid);
			return this.getDao().getByExample(example);
		}else if (type.equals(Const.LOGIN_WEIXIN)) {
			crit.andWeixinIdEqualTo(openid);
			return this.getDao().getByExample(example);
		}
		return null;
	}

	public boolean bindUnion(Integer uid, String type, String openid) {
		User user=new User();
		user.setId(uid);
		if(type.equals(Const.LOGIN_QQ)){
			user.setQqId(openid);
		}else if (type.equals(Const.LOGIN_WEIXIN)) {
			user.setWeixinId(openid);
		}
		int res=this.getDao().update(user);
		if(res>0){
			return true;
		}
		return false;
	}

	public User getByIdcard(String idCard) {
		UserExample example = new UserExample();
		Criteria crit=example.createCriteria();
		crit.andIdCardEqualTo(idCard);
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		return this.getDao().getByExample(example);
	}

	public boolean unbindBank(Integer uid) {
		User user=new User();
		user.setId(uid);
		user.setBankName("");
		user.setSubBankName("");
		user.setBankName("");
		user.setAccountName("");
		user.setBankCard("");
		int res=this.getDao().update(user);
		if(res>0){
			return true;
		}
		return false;
	}

	public void setTonji(Integer uid,Integer catNum	, Integer SignNum, Integer finishNum, String balanceNum) {
		User user=new User();
		user.setId(uid);
		if(!ValidateUtils.isNull(catNum)){
			user.setCatNum(catNum);
		}
		if(!ValidateUtils.isNull(SignNum)){
			user.setSignNum(SignNum);
		}
		if(!ValidateUtils.isNull(finishNum)){
			user.setFinishNum(finishNum);
		}
		if(!ValidateUtils.isNull(balanceNum)){
			user.setBalanceNum(balanceNum);
		}
		this.getDao().update(user);		
	}


}