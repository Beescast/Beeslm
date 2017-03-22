package org.bees.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bees.biz.persistence.model.Notice;
import org.bees.biz.service.AdminService;
import org.bees.biz.service.NoticeService;
import org.bees.biz.service.UserService;
import org.bees.biz.service.dto.NoticeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import projects.commons.exception.ServiceException;
import projects.commons.utils.ValidateUtils;
import projects.commons.utils.date.DateTimeUtils;
import projects.commons.utils.response.FormatType;
import projects.commons.utils.response.Res;





@Controller
@RequestMapping("/notice/")
public class NoticeController {
 
	@Autowired
    private NoticeService NoticeService;
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private AdminService adminService;
	
	@RequestMapping(value = "/info")
	@ResponseBody
	public ModelAndView info ( 
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,			
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			Notice Notice=this.NoticeService.getInfoById(id);
			res.addResponse("result", Notice);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/list")
	@ResponseBody
	public ModelAndView list ( 
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "start", required = false) Integer start,
			@RequestParam(value = "status", required = false) Byte status,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,
			@RequestParam(value = "adminSessionKey", required = false) String adminSessionKey,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			
			if(!ValidateUtils.isNull(adminSessionKey)){
				Integer adminId=this.adminService.getUserId(adminSessionKey);
				
				if(ValidateUtils.isNull(adminId)){
					throw new ServiceException("登陆失效");
				}
				int op=6;
				this.adminService.hasOp(adminId,op);
				
			}else{
				status=new Byte("1");
			}
			Date startDate=null;
			Date endDate=null;
			if(!ValidateUtils.isNull(startTime)){
				startDate=DateTimeUtils.strDateTimeToDate(startTime);
			}
			if(!ValidateUtils.isNull(endTime)){
				endDate=DateTimeUtils.strDateTimeToDate(endTime);
			}
			List<NoticeDto> notices=this.NoticeService.getList(title,status,startDate,endDate,start,limit);
			Integer count=this.NoticeService.getListCount(title, status, startDate, endDate);
			res.addResponse("total", count);
			res.addResponse("resultList", notices);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/add")
	@ResponseBody
	public ModelAndView add ( 
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "content", required = false) String content,
			@RequestParam(value = "status", required = false) Byte status,
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
			int op=7;
			this.adminService.hasOp(adminId,op);
			boolean result=this.NoticeService.add(adminId,title,content,status);
			res.addResponse("result", result);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/del")
	@ResponseBody
	public ModelAndView del ( 
			@RequestParam(value = "id", required = false) Integer id,
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
			int op=7;
			this.adminService.hasOp(adminId,op);
			boolean result=this.NoticeService.del(id);
			res.addResponse("result", result);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/edit")
	@ResponseBody
	public ModelAndView edit ( 
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "content", required = false) String content,
			@RequestParam(value = "status", required = false) Byte status,
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
			int op=7;
			this.adminService.hasOp(adminId,op);
			boolean result=this.NoticeService.edit(id,title,content,status);
			res.addResponse("result", result);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	
}