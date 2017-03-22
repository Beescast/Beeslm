package org.bees.biz.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bees.biz.persistence.manager.AdminManager;
import org.bees.biz.persistence.model.Admin;
import org.bees.biz.service.dto.AdminDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projects.commons.exception.ServiceException;
import projects.commons.service.cache.CacheService;
import projects.commons.utils.MD5Utils;
import projects.commons.utils.RandomStringUtils;
import projects.commons.utils.ValidateUtils;
import projects.commons.utils.config.GlobalKeys;
import projects.commons.utils.response.Res;

@Service
public class AdminService {
	
	@Autowired
	private AdminManager adminManager;
	
	@Autowired
	private CacheService cacheService;
	
	
	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(AdminService.class);

	public Admin getInfoById(Integer id) {		
		return this.adminManager.getInfoById(id);
	}

	public List<AdminDto> getList(int start, int limit) {
		List<Admin> Admins=this.adminManager.getList(start,limit);
		List<AdminDto> dtos=new LinkedList<AdminDto>();
		for (Admin Admin : Admins) {
			AdminDto dto=new AdminDto();
			try {
				PropertyUtils.copyProperties(dto, Admin);
				
			} catch (IllegalAccessException | InvocationTargetException
					| NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dtos.add(dto);
		}		
		return dtos;
	}

	public Res login(String name, String password, HttpServletRequest request,
			HttpServletResponse response) {
		Res res = new Res();
		try {
			Integer errorNum = cacheService
					.getAttribute(GlobalKeys.PASSPORT_LOGIN_ERROR_KEY + name,
							Integer.class);
			if (errorNum == null) {
				errorNum = 0;
			}
			if (errorNum >= 10) {
				throw new ServiceException("登录失败超过10次，请15分钟后重试");
			}
			
			Admin admin=this.adminManager.getInfoByName(name);
			if(ValidateUtils.isNull(admin)){
				throw new ServiceException("用户名或密码错误，请重新输入");
			}
			if (!admin.getPasswd().equals(
					this.getSafePass(password))) {
				cacheService.setAttribute(GlobalKeys.PASSPORT_LOGIN_ERROR_KEY
						+ name, errorNum + 1);
				cacheService.setExpire(GlobalKeys.PASSPORT_LOGIN_ERROR_KEY
						+ name, 900);// 15分钟过期
				throw new ServiceException("用户名或密码错误，请重新输入");
			}
			admin.setPasswd("");// 清除密码
			
	
			String sessionId = RandomStringUtils.randomAlphanumeric(32)
					.toUpperCase() + "_" + admin.getId()+"_ADMIN";
			cacheService.setAttribute(
					GlobalKeys.PASSPORT_LOGIN_KEY + sessionId, admin);
			
			res.addResponse("ops", admin.getOps());
			res.addResponse("sessionKey", sessionId);
			res.addResponse("flag", true);
			
			
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
			Admin admin = cacheService.getAttribute(GlobalKeys.PASSPORT_LOGIN_KEY
					+ sessionKey, Admin.class);
			if (admin != null&&isAdmin(sessionKey)) {
				return admin.getId();
			}
		}
		return null;
	}
	
	public boolean isAdmin(String sessionKey) {
		String[] keys=sessionKey.split("_");
		if(keys[keys.length-1].equals("ADMIN")){
			return true;
		}
		return false;
	}

	public void hasOp(Integer adminId, int op) {
		Admin admin=this.getInfoById(adminId);
		if(ValidateUtils.isNull(admin)){
			throw new ServiceException("用户错误");
		}
		boolean hasop=false;
		String[] ops=admin.getOps().split(",");
		for (String string : ops) {
			if(string.equals(op+"")){
				hasop=true;
				break;
			}
		}
		if(!hasop){
			throw new ServiceException("无权限");
		}
		
	}

	public int getListCount() {
		return this.adminManager.getListCount();
	}

	public boolean setPassword(Integer adminId, String oldPassword,
			String newPassword) {
		Admin admin=this.adminManager.getInfoById(adminId);
		if(!ValidateUtils.isNull(admin)&&admin.getPasswd().equals(this.getSafePass(oldPassword))){
			admin.setPasswd(this.getSafePass(newPassword));
			int res=this.adminManager.getDao().update(admin);
			if(res>0){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	public boolean add(String name, String password, String ops) {
		password=this.getSafePass(password);
		
		return this.adminManager.add(name, password, ops);
	}
	
	public boolean eidt(int id,String name, String password, String ops) {
		if(!ValidateUtils.isNull(password)&&!password.equals("")){
			password=this.getSafePass(password);		
		}
		return this.adminManager.edit(id,name, password, ops);
	}

	public boolean del(Integer id) {
		
		return this.adminManager.del(id);
	}
}