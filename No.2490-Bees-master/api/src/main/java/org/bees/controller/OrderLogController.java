package org.bees.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bees.biz.service.AdminService;
import org.bees.biz.service.OrderLogService;
import org.bees.biz.service.UserService;
import org.bees.biz.service.dto.OrderLogDto;
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
@RequestMapping("/orderLog/")
public class OrderLogController {
 
	@Autowired
    private OrderLogService orderLogService;
	
	@Autowired
    private AdminService adminService;
	
	@Autowired
    private UserService userService;
	
	
	@RequestMapping(value = "/adminList")
	@ResponseBody
	public ModelAndView list ( 
			@RequestParam(value = "uid", required = false) Integer uid,
			@RequestParam(value = "type", required = false) Byte type,
			@RequestParam(value = "incomeType", required = false) Byte incomeType,
			@RequestParam(value = "mobile", required = false) String mobile,
			@RequestParam(value = "nickname", required = false) String nickname,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "startMoney", required = false) String startMoney,
			@RequestParam(value = "endMoney", required = false) String endMoney,
			@RequestParam(value = "orderId", required = false) String orderId,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "start", required = false) Integer start,
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
			int op=14;
			this.adminService.hasOp(adminId,op);
			Date startDate = null;
			Date endDate = null;
			if(!ValidateUtils.isNull(startTime)){
				startDate=DateTimeUtils.strDateTimeToDate(startTime);
			}
			if(!ValidateUtils.isNull(endTime)){
				endDate=DateTimeUtils.strDateTimeToDate(endTime);
			}
			List<OrderLogDto> OrderLogs=this.orderLogService.getList(uid,type,incomeType,mobile,nickname,startDate,endDate,startMoney,endMoney,orderId,start,limit);
			int total=this.orderLogService.getListCount(uid,type,incomeType,mobile,nickname,startDate,endDate,startMoney,endMoney,orderId);
			res.addResponse("total", total);
			res.addResponse("resultList", OrderLogs);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	
		
}