package org.bees.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bees.biz.persistence.model.LeaveMsg;
import org.bees.biz.service.AdminService;
import org.bees.biz.service.LeaveMsgService;
import org.bees.biz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import projects.commons.exception.ServiceException;
import projects.commons.utils.ValidateUtils;
import projects.commons.utils.response.FormatType;
import projects.commons.utils.response.Res;

@Controller
@RequestMapping("/msg/")
public class LeaveMsgController {
 
	@Autowired
    private LeaveMsgService leaveMsgService;
	
	@Autowired
    private AdminService adminService;
	
	@Autowired
    private UserService userService;
	
	
	@RequestMapping(value = "/list")
	@ResponseBody
	public ModelAndView list ( 
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "start", required = false) Integer start,
			@RequestParam(value = "uid", required = false) Integer uid,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "content", required = false) String content,
			@RequestParam(value = "adminSessionKey", required = false) String adminSessionKey,			
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			Integer adminId=this.adminService.getUserId(adminSessionKey);
			
			if(ValidateUtils.isNull(adminId)){
				throw new ServiceException("登陆失效");
			}
			List<LeaveMsg> LeaveMsgs=this.leaveMsgService.getList(uid,name,content,start,limit);
			int total=this.leaveMsgService.getListCount(uid,name,content);
			res.addResponse("total", total);
			res.addResponse("resultList", LeaveMsgs);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/add")
	@ResponseBody
	public ModelAndView add ( 
			@RequestParam(value = "content", required = false) String content,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,			
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			Integer adminId=this.userService.getUserId(sessionKey);
			
			if(ValidateUtils.isNull(adminId)){
				throw new ServiceException("登陆失效");
			}			
			boolean result=this.leaveMsgService.add(adminId,content);
			res.addResponse("result", result);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	
}