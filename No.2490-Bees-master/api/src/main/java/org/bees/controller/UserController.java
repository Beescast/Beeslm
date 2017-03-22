package org.bees.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bees.biz.persistence.model.User;
import org.bees.biz.persistence.model.UserAuth;
import org.bees.biz.service.AdminService;
import org.bees.biz.service.UserService;
import org.bees.biz.service.dto.UserInfoDto;
import org.bees.biz.service.dto.UserSubInfoDto;
import org.bees.utils.FilesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import projects.commons.exception.ServiceException;
import projects.commons.service.cache.CacheService;
import projects.commons.utils.ValidateUtils;
import projects.commons.utils.date.DateTimeUtils;
import projects.commons.utils.response.FormatType;
import projects.commons.utils.response.Res;




@Controller
@RequestMapping("/user/")
public class UserController {
 
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CacheService cacheService;
	
	@RequestMapping(value = "/info")
	@ResponseBody
	public ModelAndView info ( 
			@RequestParam(value = "uid", required = false) Integer uid,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,
			@RequestParam(value = "adminSessionKey", required = false) String adminSessionKey,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			UserInfoDto user=new UserInfoDto();
			if(!ValidateUtils.isNull(sessionKey)){
				Integer uids=this.userService.getUserId(sessionKey);
				if(!ValidateUtils.isNull(uids)){
					user=this.userService.getUser(uids);
				}else{
					throw new ServiceException("登陆失效，请重新登陆");
				}
				
			}
			if(!ValidateUtils.isNull(adminSessionKey)){
				Integer ops=this.adminService.getUserId(adminSessionKey);
				if(!ValidateUtils.isNull(ops)){
					user=this.userService.getUser(uid);
				}else{
					throw new ServiceException("登陆失效，请重新登陆");
				}
			}
			
			res.addResponse("result", user);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	
	@RequestMapping(value = "/list")
	@ResponseBody
	public ModelAndView list ( 
			@RequestParam(value = "limit", required = false) int limit,
			@RequestParam(value = "start", required = false) int start,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,
			@RequestParam(value = "adminSessionKey", required = false) String adminSessionKey,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			
			List<UserSubInfoDto> dtos=this.userService.getUserSubInfo(limit,start,order);
			res.addResponse("resultList", dtos);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	
	@RequestMapping(value = "/setPassword")
	@ResponseBody
	public ModelAndView setPassword ( 
			@RequestParam(value = "newPassword", required = false) String newPassword,
			@RequestParam(value = "oldPassword", required = false) String oldPassword,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,			
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		boolean flag;
		try {			
			flag=this.userService.setPassword(sessionKey,oldPassword,newPassword);
			res.addResponse("result", flag);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	
	
	
	@RequestMapping(value = "/setPayPassword")
	@ResponseBody
	public ModelAndView setPayPassword ( 
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "newPass", required = false) String newPass,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response)  {
		Res res = new Res();
		boolean flag;
		try {
			if (ValidateUtils.isNull(id)||ValidateUtils.isNull(sessionKey)||ValidateUtils.isNull(newPass)) {
				throw new ServiceException("缺少参数");
			} else {
				Integer userId=this.userService.getUserId(sessionKey);
				if(this.cacheService.getAttribute("SESSION_ID_"+userId,String.class).equals(id)){
					flag = userService.setPayPassword(userId, newPass);
				}else{
					throw new ServiceException("参数不正确");
				}
				res.addResponse("result", flag);
				res.commit();
			}
		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/edit")
	@ResponseBody
	public ModelAndView edit (
			@RequestParam(value = "nickname", required = false) String nickname,
			@RequestParam(value = "avatar", required = false) String avatar,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "contactMobile", required = false) String contactMobile,
			@RequestParam(value = "contactAddress", required = false) String contactAddress,
			@RequestParam(value = "agencyName", required = false) String agencyName,
			@RequestParam(value = "agencyMobile", required = false) String agencyMobile,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "taskCat", required = false) String taskCat,
			@RequestParam(value = "bankName", required = false) String bankName,
			@RequestParam(value = "subBankName", required = false) String subBankName,
			@RequestParam(value = "bankCard", required = false) String bankCard,
			@RequestParam(value = "accountName", required = false) String accountName,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,			
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		boolean flag;
		try {
			Integer uid=this.userService.getUserId(sessionKey);
			if(!ValidateUtils.isNull(uid)){
				flag=this.userService.editById(uid,email,contactMobile,contactAddress,agencyName,agencyMobile,description,taskCat,bankName,subBankName,bankCard,accountName,avatar,nickname);
			}else{
				flag=false;
			}
			res.addResponse("result", flag);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/editAvatar")
	@ResponseBody
	public ModelAndView editAvatar (
			@RequestParam(value = "dataX", required = false) Integer dataX,
			@RequestParam(value = "dataY", required = false) Integer dataY,
			@RequestParam(value = "dataHeight", required = false) Integer dataHeight,
			@RequestParam(value = "dataWidth", required = false) Integer dataWidth,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,			
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		boolean flag;
		try {
			Integer uid=this.userService.getUserId(sessionKey);			
			if(!ValidateUtils.isNull(uid)){
				String filepath=FilesUtil.uploads(request, uid, dataX, dataY,dataWidth,dataHeight);
				flag=this.userService.editById(uid,null,null,null,null,null,null,null,null,null,null,null,filepath,null);
				res.addResponse("avatar", filepath);
			}else{
				flag=false;
			}
			res.addResponse("result", flag);
			
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/changeMobile")
	@ResponseBody
	public ModelAndView changeMobile(
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "newMobile", required = false) String newMobile,
			@RequestParam(value = "mobileCode", required = false) String mobileCode,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response,HttpSession httpSession)  {
		Res res = new Res();
		boolean flag;
		try {
			if (ValidateUtils.isNull(id)||ValidateUtils.isNull(sessionKey)||ValidateUtils.isNull(newMobile)) {
				throw new ServiceException("缺少参数");
			} else {
				Integer userId=this.userService.getUserId(sessionKey);
				if((this.cacheService.getAttribute("SESSION_ID_"+userId,String.class).equals(id))){
					flag = userService.changeMobile(userId,mobileCode, newMobile);
				}else{
					throw new ServiceException("参数不正确");
				}
				res.addResponse("result", flag);
				res.commit();
			}
		}catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	/**
	 * 用户验证
	 * @param userId
	 * @param authStatus
	 * @param adminSessionKey
	 * @param formatType
	 * @param callback
	 * @param request
	 * @param response
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/auth")
	@ResponseBody
	public ModelAndView auth(
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "authStatus", required = false) Byte authStatus,
			@RequestParam(value = "reson", required = false) String reson,
			@RequestParam(value = "adminSessionKey", required = false) String adminSessionKey,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response,HttpSession httpSession)  {
		Res res = new Res();
		boolean flag;
		try {
			if (ValidateUtils.isNull(id)||ValidateUtils.isNull(authStatus)||ValidateUtils.isNull(adminSessionKey)) {
				throw new ServiceException("缺少参数");
			} else {
				
				Integer adminId=this.adminService.getUserId(adminSessionKey);
				int op=3;
				this.adminService.hasOp(adminId,op);
				if(!ValidateUtils.isNull(adminId)){
					flag=this.userService.auth(adminId,id,authStatus,reson);
				}else{
					throw new ServiceException("登陆失效");
				}
				res.addResponse("result", flag);
				res.commit();
			}
		}catch (ServiceException e) {
			res.addError(e.getMessage());
		} catch (Exception e) {
			res.addError(e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/authList")
	@ResponseBody
	public ModelAndView authList (
			@RequestParam(value = "mobile", required = false) String mobile,
			@RequestParam(value = "truename", required = false) String truename,
			@RequestParam(value = "plat", required = false) String plat,
			@RequestParam(value = "liveRoom", required = false) String liveRoom,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "authStatus", required = false) Byte authStatus,
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "start", required = false) Integer start,
			@RequestParam(value = "adminSessionKey", required = false) String adminSessionKey,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			if (ValidateUtils.isNull(adminSessionKey)) {
				throw new ServiceException("缺少参数");
			} 
			Integer adminId=this.adminService.getUserId(adminSessionKey);
			int op=2;
			this.adminService.hasOp(adminId,op);
			Date startDate=null;
			Date endDate=null;
			if(!ValidateUtils.isNull(startTime)){
				startDate=DateTimeUtils.strDateTimeToDate(startTime);
			}
			if(!ValidateUtils.isNull(endTime)){
				endDate=DateTimeUtils.strDateTimeToDate(endTime);
			}
			List<UserAuth> dtos=this.userService.authList(mobile,truename,plat,liveRoom,startDate,endDate,authStatus,id,limit,start);
			Integer total=this.userService.authListCount(mobile, truename, plat, liveRoom, startDate,authStatus, endDate, id);
			res.addResponse("total", total);
			res.addResponse("resultList", dtos);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/adminList")
	@ResponseBody
	public ModelAndView adminList (
			@RequestParam(value = "mobile", required = false) String mobile,
			@RequestParam(value = "nickName", required = false) String nickName,
			@RequestParam(value = "plat", required = false) String plat,
			@RequestParam(value = "liveRoom", required = false) String liveRoom,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "authStatus", required = false) Byte authStatus,
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "balance", required = false) Integer balance,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "start", required = false) Integer start,
			@RequestParam(value = "adminSessionKey", required = false) String adminSessionKey,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			if (ValidateUtils.isNull(adminSessionKey)) {
				throw new ServiceException("缺少参数");
			} 
			Integer adminId=this.adminService.getUserId(adminSessionKey);
			int op=2;
			this.adminService.hasOp(adminId,op);
			Date startDate=null;
			Date endDate=null;
			if(!ValidateUtils.isNull(startTime)){
				startDate=DateTimeUtils.strDateTimeToDate(startTime);
			}
			if(!ValidateUtils.isNull(endTime)){
				endDate=DateTimeUtils.strDateTimeToDate(endTime);
			}
			List<User> dtos=this.userService.adminList(mobile,nickName,plat,liveRoom,startDate,endDate,authStatus,id,balance,limit,start);
			Integer total=this.userService.adminListCount(mobile, nickName, plat, liveRoom, startDate,authStatus, endDate, id,balance);
			res.addResponse("total", total);
			res.addResponse("resultList", dtos);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/authInfo")
	@ResponseBody
	public ModelAndView authInfo ( 
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,
			@RequestParam(value = "adminSessionKey", required = false) String adminSessionKey,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			UserAuth user=new UserAuth();			
			if(!ValidateUtils.isNull(adminSessionKey)){
				Integer ops=this.adminService.getUserId(adminSessionKey);
				if(!ValidateUtils.isNull(ops)){
					user=this.userService.getUserAuth(id);
				}else{
					throw new ServiceException("登陆失效，请重新登陆");
				}
			}
			
			res.addResponse("result", user);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	
	@RequestMapping(value = "/unbindBank")
	@ResponseBody
	public ModelAndView unbindBank (
			@RequestParam(value = "sessionKey", required = false) String sessionKey,			
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		boolean flag;
		try {
			Integer uid=this.userService.getUserId(sessionKey);
			if(!ValidateUtils.isNull(uid)){
				flag=this.userService.unbindBank(uid);
			}else{
				flag=false;
			}
			res.addResponse("result", flag);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
}