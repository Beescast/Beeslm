package org.bees.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bees.biz.persistence.model.PackagesOrder;
import org.bees.biz.persistence.model.User;
import org.bees.biz.service.AdminService;
import org.bees.biz.service.OrderService;
import org.bees.biz.service.UserService;
import org.bees.biz.service.dto.PackagesOrderDto;
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
@RequestMapping("/order/")
public class OrderController {
	
	@Autowired
    private OrderService orderService;
 	
	@Autowired
    private AdminService adminService;
	
	@Autowired
    private UserService userService;
	
	
	@RequestMapping(value = "/list")
	@ResponseBody
	public ModelAndView list (
			@RequestParam(value = "uid", required = false) Integer uid,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "start", required = false) Integer start,			
			@RequestParam(value = "mobile", required = false) String mobile,
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "payType", required = false) Byte payType,
			@RequestParam(value = "status", required = false) Byte status,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,
			@RequestParam(value = "adminSessionKey", required = false) String adminSessionKey,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			
			Integer userId=null;
			Integer adminId=null;
			if(!ValidateUtils.isNull(sessionKey)){
				userId=this.userService.getUserId(sessionKey);
			}
			if(!ValidateUtils.isNull(adminSessionKey)){				
				adminId=this.adminService.getUserId(adminSessionKey);
				int op=1;
				this.adminService.hasOp(adminId,op);
				if(!ValidateUtils.isNull(uid)){
					userId=uid;
				}
			}
			if(ValidateUtils.isNull(userId)&&ValidateUtils.isNull(adminId)){
				throw new ServiceException("登陆失效");
			}
			Date startDate = null;
			Date endDate = null;
			if(!ValidateUtils.isNull(startTime)){
				startDate=DateTimeUtils.strDateTimeToDate(startTime);
			}
			if(!ValidateUtils.isNull(endTime)){
				endDate=DateTimeUtils.strDateTimeToDate(endTime);
			}
			if(!ValidateUtils.isNull(mobile)){
				User user=this.userService.getByMobile(mobile);
				if(ValidateUtils.isNull(user)||(!ValidateUtils.isNull(userId)&&userId!=user.getId())){
					throw new ServiceException("无该手机号或手机号和uid不匹配");
				}else{
					userId=user.getId();
				}
			}
			
			List<PackagesOrderDto> orders=this.orderService.getList(userId,id,payType,status,startDate,endDate,null,start,limit);
			Integer total=this.orderService.getListCount(userId, id, payType, status, startDate, endDate, null);
			res.addResponse("total", total);
			res.addResponse("resultList", orders);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/info")
	@ResponseBody
	public ModelAndView info (
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,
			@RequestParam(value = "adminSessionKey", required = false) String adminSessionKey,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			
			Integer userId=null;
			Integer adminId=null;
			if(!ValidateUtils.isNull(sessionKey)){
				userId=this.userService.getUserId(sessionKey);
			}
			if(!ValidateUtils.isNull(adminSessionKey)){
				adminId=this.adminService.getUserId(adminSessionKey);
			}
			if(ValidateUtils.isNull(userId)&&ValidateUtils.isNull(adminId)){
				throw new ServiceException("登陆失效");
			}
			
			PackagesOrder order=this.orderService.getInfoById(id);
			res.addResponse("result", order);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/add")
	@ResponseBody
	public ModelAndView add ( 
			@RequestParam(value = "packageId", required = false) Integer packageId,
			@RequestParam(value = "packagePriceId", required = false) Integer packagePriceId,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,			
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			Integer userId=this.userService.getUserId(sessionKey);
			if(ValidateUtils.isNull(userId)){
				throw new ServiceException("登陆失效");
			}
			res=this.orderService.add(userId,packageId,packagePriceId);
		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/del")
	@ResponseBody
	public ModelAndView del ( 
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			Integer userId=this.userService.getUserId(sessionKey);
			if(ValidateUtils.isNull(userId)){
				throw new ServiceException("登陆失效");
			}
			boolean result=this.orderService.del(id,userId);
			res.addResponse("result", result);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/realDel")
	@ResponseBody
	public ModelAndView realDel ( 
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "adminSessionKey", required = false) String adminSessionKey,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			Integer userId=this.adminService.getUserId(adminSessionKey);
			if(ValidateUtils.isNull(userId)){
				throw new ServiceException("登陆失效");
			}
			boolean result=this.orderService.realDel(id);
			res.addResponse("result", result);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	
	
	
	
	@RequestMapping(value = "/startPackage")
	@ResponseBody
	public ModelAndView startPackage ( 
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "adminSessionKey", required = false) String adminSessionKey,
			@RequestParam(value = "endDate", required = false) String endDate,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			if(ValidateUtils.isNull(adminSessionKey)||ValidateUtils.isNull(id)||ValidateUtils.isNull(endDate)){
				throw new ServiceException("缺少参数");
			}
			boolean flag;
			Integer userId=this.adminService.getUserId(adminSessionKey);
			if(ValidateUtils.isNull(userId)){
				throw new ServiceException("登陆失效");
			}
			Date endDates=DateTimeUtils.strDateTimeToDate(endDate);
			flag=this.orderService.startPackage(id,endDates);
			res.addResponse("result", flag);
			res.commit();
		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	
	
}