package org.bees.biz.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.bees.biz.persistence.manager.TaskManager;
import org.bees.biz.persistence.manager.UserAuthManager;
import org.bees.biz.persistence.manager.UserManager;
import org.bees.biz.persistence.manager.UserPicManager;
import org.bees.biz.persistence.model.User;
import org.bees.biz.persistence.model.UserAuth;
import org.bees.biz.persistence.model.UserPic;
import org.bees.biz.service.dto.UserInfoDto;
import org.bees.biz.service.dto.UserSubInfoDto;
import org.bees.utils.Const;
import org.bees.utils.SMSUtil;
import org.bees.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import projects.commons.exception.ServiceException;
import projects.commons.service.cache.CacheService;
import projects.commons.service.cache.MobileCode;
import projects.commons.utils.MD5Utils;
import projects.commons.utils.RandomStringUtils;
import projects.commons.utils.ValidateUtils;
import projects.commons.utils.config.GlobalKeys;
import projects.commons.utils.response.Res;

@Service
public class UserService {
	
	@Autowired
	private CacheService cacheService;
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private TaskManager taskManager;
	
	@Autowired
	private UserPicManager userPicManager;
	
	@Autowired
	private UserAuthManager userAuthManager;
	

	/**
	 * 注册
	 * 
	 * @param accountBak
	 * @param UserMobile
	 * @param password
	 * @param password1
	 * @param mobileCode
	 * @param devKey
	 * @param inviteCode
	 * @param request
	 * @param response
	 * @return
	 * @throws ServiceException
	 */
	@Transactional
	public Res register(String mobile, String mobileCode, String truename,
			String idCard, String frontPic, String backPic, String handPic,
			String plat, String liveRoom, String platPic,String code,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Res res=new Res();
	
		try {

			if (ValidateUtils.isNull(mobile)) {
				throw new ServiceException("请输入手机号码");
			} else if (!ValidateUtils.isMobile(mobile)) {
				throw new ServiceException("请输入正确手机号码");
			}
			/*if(!cacheService.getAttribute("CODE_"+mobile).equals(code)){
				throw new ServiceException("请输入正确验证码");
			}*/
			
			String userAgent = request.getHeader("User-Agent");
			if (userAgent != null) {
				if (userAgent.indexOf("TianTian") > 0) {
					throw new ServiceException(
							"您的所有访问记录已经被记录在档，有关伪造数据后果将追究法律责任!");
				}
			}

			
			/*if (ValidateUtils.isNull(password1)) {
				throw new ServiceException("确认密码不能为空");
			}
			if (!password.equals(password1)) {
				throw new ServiceException("两次输入的密码不一致");
			}*/
			if (ValidateUtils.isNull(mobileCode)) {
				throw new ServiceException("请输入短信验证码");
			}
			
			User user=this.userManager.getByMobile(mobile);
			if(!ValidateUtils.isNull(user)){
				throw new ServiceException("该手机已存在");
			}
			user=this.userManager.getByIdcard(idCard);
			if(!ValidateUtils.isNull(user)){
				throw new ServiceException("该身份证号已存在");
			}
			UserAuth auth=this.userAuthManager.getByMobile(mobile);
			if(!ValidateUtils.isNull(auth)&&auth.getAuthStatus()!=Const.AUTH_STATUS_TWO){
				throw new ServiceException("该手机号尚未审核，请耐心等待");
			}
			
			auth=this.userAuthManager.getByIdcard(idCard);
			if(!ValidateUtils.isNull(auth)&&auth.getAuthStatus()!=Const.AUTH_STATUS_TWO){
				throw new ServiceException("该手机号尚未审核，请耐心等待");
			}
			
			// 验证手机验证码
			MobileCode mCode = cacheService.getAttribute(
					GlobalKeys.PASSPORT_REG_MCODE_KEY + "__" + mobile,
					MobileCode.class);
			if (mCode != null) {
				if (mCode.getExpires().getTime() <= System
						.currentTimeMillis()) {
					// 已经过期
					throw new ServiceException("短信验证码已过期,请重新获取");
				} else {
					if (!mCode.getMobile().equals(mobile)
							|| !mCode.getCode().equals(mobileCode)) {
						throw new ServiceException("短信验证码不正确");
					}
				}
			} else {
				throw new ServiceException("短信验证码已过期,请重新获取");
			}

			UserAuth entity = new UserAuth();
			entity.setMobile(mobile);
			entity.setTruename(truename);
			entity.setIdCard(idCard);
			entity.setFrontPic(frontPic);
			entity.setBackPic(backPic);
			entity.setHandPic(handPic);
			entity.setPlat(plat);
			entity.setLiveRoom(liveRoom);
			entity.setPlatPic(platPic);
			//String password=StringUtil.code(6);
			/*entity.setPasswd(this.getSafePass(password));*/
			entity.setAddTime(new Date());
			entity.setRegTime(new Date());
			userAuthManager.getDao().insert(entity);
			

			String sessionId = RandomStringUtils.randomAlphanumeric(32)
					.toUpperCase() + "_" + entity.getId();
			cacheService.setAttribute(
					GlobalKeys.PASSPORT_LOGIN_KEY + sessionId, entity);

			res.addResponse("sessionKey", sessionId);
			res.addResponse("flag", true);
			Map<String, Object> UserMap = new HashMap<>();
			UserMap.put("UserId", entity.getId());
			UserMap.put("UserMobile", entity.getMobile());
			res.addResponse("user", UserMap);

			Cookie cookie = new Cookie(GlobalKeys.PASSPORT_LOGIN_KEY, sessionId);
			cookie.setMaxAge(-1);
			cookie.setPath("/");
			//cookie.setDomain(GlobalConfigure.AUTH_DOMAIN);
			response.addCookie(cookie);

			cacheService.removeAttribute(GlobalKeys.PASSPORT_REG_MCODE_KEY
					+ "__" + mobile);

			res.commit();
			return res;
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 登陆验证
	 * 
	 * @param account
	 * @param mobile
	 * @param password
	 * @param mobileCode 
	 * @param loginType
	 * @param request
	 * @param response
	 * @param httpSession 
	 * @return
	 * @throws ServiceException
	 */
	public Res login(String mobile, String password,			
			String mobileCode, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession)
			throws ServiceException {
		Res res = new Res();
		try {			
			if (ValidateUtils.isNull(mobile)) {
				throw new ServiceException("请输入手机号码");
			}
			
			User user = null;
			user = userManager.getByMobile(mobile);
			if(ValidateUtils.isNull(user)){
				UserAuth auth=this.userAuthManager.getByMobile(mobile);
				if(!ValidateUtils.isNull(auth)&&auth.getAuthStatus().intValue()!=1){
					throw new ServiceException("审核中");
				}else{
					throw new ServiceException("请输入正确的手机号码");
				}
				
			}
			Integer errorNum = cacheService
					.getAttribute(GlobalKeys.PASSPORT_LOGIN_ERROR_KEY + mobile,
							Integer.class);
			if (errorNum == null) {
				errorNum = 0;
			}
			if (errorNum >= 500) {
				throw new ServiceException("登录失败超过500次，请15分钟后重试");
			}
			
			if(!ValidateUtils.isNull(password)){
				if (!user.getPasswd().equals(
						this.getSafePass(password))) {
					cacheService.setAttribute(GlobalKeys.PASSPORT_LOGIN_ERROR_KEY
							+ mobile, errorNum + 1);
					cacheService.setExpire(GlobalKeys.PASSPORT_LOGIN_ERROR_KEY
							+ mobile, 900);// 15分钟过期
					throw new ServiceException("用户名或密码错误，请重新输入");
				}
			}else{
				MobileCode mCode = cacheService.getAttribute(
						GlobalKeys.PASSPORT_REG_MCODE_KEY + "__" + mobile,
						MobileCode.class);
				if (mCode != null) {
					if (mCode.getExpires().getTime() <= System
							.currentTimeMillis()) {
						// 已经过期
						throw new ServiceException("短信验证码已过期,请重新获取");
					} else {
						if (!mCode.getMobile().equals(mobile)
								|| !mCode.getCode().equals(mobileCode)) {
							throw new ServiceException("短信验证码不正确");
						}
					}
				} else {
					throw new ServiceException("短信验证码已过期,请重新获取");
				}
				this.cacheService.setAttribute("SESSION_ID_"+mobile, httpSession.getId());
			}
			

			user.setPasswd("");// 清除密码
			

			String sessionId = RandomStringUtils.randomAlphanumeric(32)
					.toUpperCase() + "_" + user.getId();
			cacheService.setAttribute(
					GlobalKeys.PASSPORT_LOGIN_KEY + sessionId, user);

			res.addResponse("sessionKey", sessionId);
			res.addResponse("flag", true);
			res.addResponse("userid", user.getId());
			res.addResponse("nickname", user.getNickname());
			res.addResponse("avatar", user.getAvatar());
			Map<String, Object> UserMap = new HashMap<>();
			UserMap.put("UserId", user.getId());
			UserMap.put("UserMobile", user.getMobile());			
			UserMap.put("avatar", user.getAvatar());
			res.addResponse("user", UserMap);
			Cookie cookie = new Cookie(GlobalKeys.PASSPORT_LOGIN_KEY, sessionId);
			cookie.setMaxAge(-1);
			cookie.setPath("/");
			//cookie.setDomain(GlobalConfigure.AUTH_DOMAIN);
			response.addCookie(cookie);
			// res.setRedirect("index.html");
			res.commit();
			return res;
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {			
			throw new ServiceException(e);
		}
	}
	
	public String getSafePass(String password) {
		try {
			return MD5Utils
					.getMD5String(MD5Utils
							.getMD5String(password) + GlobalKeys.MD5KEY);
		} catch (NoSuchAlgorithmException | IOException e) {
			
		}
		return password;
	}
	
	public Integer getUserId(String sessionKey) {
		if (!ValidateUtils.isNull(sessionKey)) {
			User User = cacheService.getAttribute(GlobalKeys.PASSPORT_LOGIN_KEY
					+ sessionKey, User.class);
			if (User != null) {
				return User.getId();
			}
		}
		return null;
	}

	public List<UserSubInfoDto> getUserSubInfo(int limit, int start, String order) {
		List<User> users=this.userManager.getUserLists(start,limit,order);
		List<UserSubInfoDto> dtos=new LinkedList<UserSubInfoDto>();
		for (User user : users) {
			UserSubInfoDto dto=new UserSubInfoDto();
			dto.setDescription(user.getDescription());
			dto.setHeadImg(user.getAvatar());
			dto.setId(user.getId());
			dto.setTitle(user.getNickname());
			List<UserPic> userPics=this.userPicManager.getUserPicLists(user.getId());
			List<String> pics=new LinkedList<String>();
			for (UserPic userPic : userPics) {
				pics.add(userPic.getPic());
			}
			dto.setImgList(pics);
			dtos.add(dto);
		}
		return dtos;
	}

	public Res authCode(String mobile, String mobileCode, 
			HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {
		Res res=new Res();
		MobileCode mCode = cacheService.getAttribute(
				GlobalKeys.PASSPORT_REG_MCODE_KEY + "__" + mobile,
				MobileCode.class);
		if (mCode != null) {
			if (mCode.getExpires().getTime() <= System
					.currentTimeMillis()) {
				// 已经过期
				throw new ServiceException("短信验证码已过期,请重新获取");
			} else {
				if (!mCode.getMobile().equals(mobile)
						|| !mCode.getCode().equals(mobileCode)) {
					throw new ServiceException("短信验证码不正确");
				}
			}
		} else {
			throw new ServiceException("短信验证码已过期,请重新获取");
		}
		
		User user=this.userManager.getByMobile(mobile);
		String sessionId = RandomStringUtils.randomAlphanumeric(32)
				.toUpperCase() + "_" + user.getId();
		this.cacheService.setAttribute("SESSION_ID_"+user.getId(), httpSession.getId());
		this.cacheService.setAttribute("SESSION_ID_"+sessionId, httpSession.getId());
		this.cacheService.setAttribute("FIND_PASS_"+httpSession.getId(), user.getId());
		res.addResponse("sessionKey", sessionId);
		res.addResponse("id", httpSession.getId());
		res.addResponse("flag", true);
		
		return res;
	}

	public boolean setPassword(String sessionKey, String oldPassword,String newPassword) {
		Integer uid=this.getUserId(sessionKey);
		if(!ValidateUtils.isNull(uid)){
			User user=this.userManager.getInfoById(uid);
			if(!user.getPasswd().equals(this.getSafePass(oldPassword))){
				throw new ServiceException("用户名老密码错误，请重新输入");
			}
			boolean res=this.userManager.setPassword(uid,this.getSafePass(newPassword));
			if(res){
				SMSUtil.doPost(user.getMobile(), 11, "", null);
				this.cacheService.removeAttribute(GlobalKeys.PASSPORT_LOGIN_KEY + sessionKey);
				return true;
			}
		}
		return false;
		
	}

	

	public boolean setPayPassword(Integer uid, String password) {
		return this.userManager.setPayPassword(uid,this.getSafePass(password));
	}

	public boolean editById(Integer uid, String email, String contactMobile,
			String contactAddress, String agencyName, String agencyMobile,
			String description, String taskCat, String bankName, String subBankName, String bankCard, String accountName, String avatar, String nickname) {
		
		boolean res=this.userManager.editById(uid,email,contactMobile,contactAddress,agencyName,agencyMobile,description,taskCat,bankName,subBankName,bankCard,accountName,avatar,nickname);
		if(res&&!ValidateUtils.isNull(taskCat)){
			String[] taskStrings=taskCat.split(",");
			List<Integer> catIds=new LinkedList<Integer>();
			for (int i = 0; i < taskStrings.length; i++) {
				catIds.add(Integer.valueOf(taskStrings[i]));
			}			
			int count=this.taskManager.getListCount(null, null, null, -1, catIds);
			this.userManager.setTonji(uid, count, null, null, null);
		}
		return res;
	}

	public boolean authPayPassword(Integer uid, String password) {
		User user=this.userManager.getInfoById(uid);
		if(this.getSafePass(password).equals(user.getPayPasswd())){
			return true;
		}else{
			return false;
		}
	}

	public UserInfoDto getUser(Integer uid) {
		
		User user=this.userManager.getInfoById(uid);
		UserInfoDto userDto=new UserInfoDto();
		try {
			PropertyUtils.copyProperties(userDto, user);
			if(!user.getPayPasswd().equals("")){
				userDto.setPayPass(1);
			}
			if(!user.getTaskCat().equals("")){
				userDto.setTasks(user.getTaskCat().split(","));
			}
		} catch (IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userDto;
	}

	public boolean isAdmin(String sessionKey) {
		String[] keys=sessionKey.split("_");
		if(keys[keys.length-1].equals("ADMIN")){
			return true;
		}
		return false;
	}

	public boolean setPassword(Integer id, String newPass) {
		return this.userManager.setPassword(id,this.getSafePass(newPass));
	}

	public Res getMobileCode(String mobile, String action, Integer userId) {
		Res res = new Res();
		try {
			if(action.toLowerCase().equals("changemobile")){
				User user =this.userManager.getInfoById(userId);
				mobile=user.getMobile();
			}
			if (!ValidateUtils.isMobile(mobile)) {
				throw new ServiceException("请输入正确手机号码");
			}
			if (action.toLowerCase().equals("login")&&ValidateUtils.isNull(userManager.getByMobile(mobile))) {
				throw new ServiceException("该手机号码不存在");
			}
			if(action.toLowerCase().equals("register")&&!ValidateUtils.isNull(userManager.getByMobile(mobile))){
				throw new ServiceException("该手机号码已注册");
			}
			String code = RandomStringUtils.randomNumeric(4);
			MobileCode mCode = new MobileCode();
			mCode.setMobile(mobile);
			mCode.setCode(code);
			mCode.setExpires(new Date(new Date().getTime()+180000));
			cacheService.setAttribute(GlobalKeys.PASSPORT_REG_MCODE_KEY + "__"+ mobile, mCode);
			SMSUtil.doPost(mobile,1, code, null);
			res.addResponse("flag", true);
			res.commit();
			return res;
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public boolean changeMobile(Integer id, String mobileCode, String newMobile) {
		MobileCode mCode = cacheService.getAttribute(
				GlobalKeys.PASSPORT_REG_MCODE_KEY + "__" + newMobile,
				MobileCode.class);
		if (mCode != null) {
			if (mCode.getExpires().getTime() <= System
					.currentTimeMillis()) {
				// 已经过期
				throw new ServiceException("短信验证码已过期,请重新获取");
			} else {
				if (!mCode.getMobile().equals(newMobile)
						|| !mCode.getCode().equals(mobileCode)) {
					throw new ServiceException("短信验证码不正确");
				}
			}
		} else {
			throw new ServiceException("短信验证码已过期,请重新获取");
		}
		User user=this.userManager.getByMobile(newMobile);
		if(ValidateUtils.isNull(user)&&ValidateUtils.isMobile(newMobile)){
			User oldUser=this.userManager.getInfoById(id);
			this.userAuthManager.changeMobile(oldUser.getMobile(),newMobile);
			return this.userManager.changeMobile(id,newMobile);
		}else{
			throw new ServiceException("请输入正确手机号码");
		}
	}

	public boolean auth(Integer adminId, Integer id, Byte authStatus, String reson) throws Exception {
		UserAuth userAuth=this.userAuthManager.getInfoById(id);
		userAuth.setAuthOpid(adminId);
		userAuth.setAuthStatus(authStatus);
		userAuth.setAuthTime(new Date());
		int res=this.userAuthManager.getDao().update(userAuth);
		if(res>0){
			if(authStatus.intValue()==1){
				User user = new User();
				PropertyUtils.copyProperties(user, userAuth);	
				String password=StringUtil.code(6);
				user.setPasswd(this.getSafePass(password));
				user.setNickname(user.getLiveRoom());
				user.setAddTime(new Date());
				res=this.userManager.getDao().insert(user);				
				SMSUtil.doPost(userAuth.getMobile(), 2, userAuth.getMobile()+","+password, null);
			}else{				
				SMSUtil.doPost(userAuth.getMobile(), 3, reson, null);
			}
		}else{
			return false;
		}
		
		
		return true;
	}

	public User getByMobile(String mobile) {

		return this.userManager.getByMobile(mobile);
	}

	public List<User> adminList(String mobile, String nickName, String plat,
			String liveRoom, Date startDate, Date endDate, Byte authStatus, Integer id, Integer balance,
			Integer limit, Integer start) {
		
		List<User> users=this.userManager.adminList(mobile,nickName,null,plat,liveRoom,startDate,endDate,authStatus,id,null,balance,limit,start);
		for (User user : users) {
			user.setPasswd("");
			user.setPayPasswd("");			
		}
		return users;
	}

	public Integer adminListCount(String mobile, String nickName, String plat,
			String liveRoom, Date startDate, Byte authStatus, Date endDate, Integer id, Integer balance) {
		return this.userManager.adminListCount(mobile,nickName,plat,liveRoom,startDate,endDate,authStatus,id,null,balance);
	}

	public Res unionLogin(String openid, String type) {
		Res res=new Res();
		User user=this.userManager.getByOpenidType(openid,type);
		if(!ValidateUtils.isNull(user)){
			String sessionId = RandomStringUtils.randomAlphanumeric(32)
					.toUpperCase() + "_" + user.getId();
			cacheService.setAttribute(
					GlobalKeys.PASSPORT_LOGIN_KEY + sessionId, user);

			res.addResponse("sessionKey", sessionId);
			res.addResponse("flag", true);
			res.addResponse("userid", user.getId());
			res.addResponse("nickname", user.getNickname());
			res.addResponse("avatar", user.getAvatar());
			Map<String, Object> UserMap = new HashMap<>();
			UserMap.put("UserId", user.getId());
			UserMap.put("UserMobile", user.getMobile());
			UserMap.put("avatar", user.getAvatar());
			res.addResponse("user", UserMap);
			res.commit();
		}else{
			res.addResponse("result", -1);
			res.commit();
		}
		return res;
	}

	public boolean bindUnion(Integer uid, String type, String openid) {
		
		return this.userManager.bindUnion(uid,type,openid);
	}

	public User getUserById(Integer userId) {
		return this.userManager.getInfoById(userId);
	}

	public List<UserAuth> authList(String mobile, String truename, String plat,
			String liveRoom, Date startDate, Date endDate, Byte authStatus,
			Integer id, Integer limit, Integer start) {
		List<UserAuth> users=this.userAuthManager.authList(mobile,truename,plat,liveRoom,startDate,endDate,authStatus,id,limit,start);		
		return users;
	}

	public Integer authListCount(String mobile, String truename, String plat,
			String liveRoom, Date startDate, Byte authStatus, Date endDate,
			Integer id) {
		return this.userAuthManager.authListCount(mobile,truename,plat,liveRoom,startDate,endDate,authStatus,id);
	}

	public UserAuth getUserAuth(Integer id) {

		return this.userAuthManager.getInfoById(id);
	}

	public boolean unbindBank(Integer uid) {
		return this.userManager.unbindBank(uid);
	}

	
	
	
}