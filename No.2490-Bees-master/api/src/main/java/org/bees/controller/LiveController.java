package org.bees.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bees.biz.service.AdminService;
import org.bees.biz.service.LiveService;
import org.bees.biz.service.UserService;
import org.bees.biz.service.dto.LiveDto;
import org.bees.biz.service.dto.LiveGiftDto;
import org.bees.biz.service.dto.LivePeopleDto;
import org.bees.biz.service.dto.MarketDto;
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
@RequestMapping("/live/")
public class LiveController {
 
	@Autowired
    private LiveService liveService;
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private AdminService adminService;
	
	@RequestMapping(value = "/market")
	@ResponseBody
	public ModelAndView market ( 
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "uid", required = false) Integer uid,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "startDay", required = false) String startDay,
			@RequestParam(value = "endDay", required = false) String endDay,
			@RequestParam(value = "startTime", required = false) Integer startTime,
			@RequestParam(value = "endTime", required = false) Integer endTime,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,
			@RequestParam(value = "adminSessionKey", required = false) String adminSessionKey,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "start", required = false) Integer start,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			List<MarketDto> dtos=new LinkedList<MarketDto>();
			int total=0;
			if(!ValidateUtils.isNull(adminSessionKey)){
				Integer adminId=this.adminService.getUserId(adminSessionKey);
				
				if(ValidateUtils.isNull(adminId)){
					throw new ServiceException("登陆失效");
				}
				int op=11;
				this.adminService.hasOp(adminId,op);
				Date startDate=null;
				Date endDate=null;
				if(!ValidateUtils.isNull(startDay)){
					startDate=DateTimeUtils.strDateTimeToDate(startDay);
				}
				if(!ValidateUtils.isNull(endDay)){
					endDate=DateTimeUtils.strDateTimeToDate(endDay);
				}
				dtos=this.liveService.market(uid,id,name,startDate,endDate,startTime,endTime,start,limit);
				total=this.liveService.marketCount(uid, adminId, name, startDate, endDate, startTime, endTime);
			}
			Integer userId=null;
			if(!ValidateUtils.isNull(sessionKey)){
				userId=this.userService.getUserId(sessionKey);
				dtos=this.liveService.market(userId,null,null,null,null,null,null,start,limit);
				total=this.liveService.marketCount(userId,null,null,null,null,null,null);
			}
			res.addResponse("total", total);
			res.addResponse("resultList", dtos);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/people")
	@ResponseBody
	public ModelAndView people ( 
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "uid", required = false) Integer uid,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "startDay", required = false) String startDay,
			@RequestParam(value = "endDay", required = false) String endDay,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,
			@RequestParam(value = "adminSessionKey", required = false) String adminSessionKey,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "start", required = false) Integer start,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			List<LivePeopleDto> dtos=new LinkedList<LivePeopleDto>();
			int total=0;
			if(!ValidateUtils.isNull(adminSessionKey)){
				Integer adminId=this.adminService.getUserId(adminSessionKey);
				
				if(ValidateUtils.isNull(adminId)){
					throw new ServiceException("登陆失效");
				}
				int op=11;
				this.adminService.hasOp(adminId,op);
				Date startDate=null;
				Date endDate=null;
				if(!ValidateUtils.isNull(startDay)){
					startDate=DateTimeUtils.strDateTimeToDate(startDay);
				}
				if(!ValidateUtils.isNull(endDay)){
					endDate=DateTimeUtils.strDateTimeToDate(endDay);
				}
				dtos=this.liveService.people(uid,id,name,startDate,endDate,start,limit);
				total=this.liveService.peopleCount(uid,id,name,startDate,endDate);
			}
			Integer userId=null;
			if(!ValidateUtils.isNull(sessionKey)){
				userId=this.userService.getUserId(sessionKey);
				dtos=this.liveService.people(userId,null,null,null,null,start,limit);
				total=this.liveService.peopleCount(userId,null,null,null,null);
			}
			res.addResponse("total", total);
			res.addResponse("resultList", dtos);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/gift")
	@ResponseBody
	public ModelAndView gift ( 
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "startDay", required = false) String startDay,
			@RequestParam(value = "endDay", required = false) String endDay,
			@RequestParam(value = "startTime", required = false) Integer startTime,
			@RequestParam(value = "endTime", required = false) Integer endTime,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,
			@RequestParam(value = "adminSessionKey", required = false) String adminSessionKey,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "start", required = false) Integer start,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			List<LiveGiftDto> dtos=new LinkedList<LiveGiftDto>();
			int total=0;
			if(!ValidateUtils.isNull(adminSessionKey)){
				Integer adminId=this.adminService.getUserId(adminSessionKey);
				
				if(ValidateUtils.isNull(adminId)){
					throw new ServiceException("登陆失效");
				}
				int op=13;
				this.adminService.hasOp(adminId,op);
				Date startDate=null;
				Date endDate=null;
				if(!ValidateUtils.isNull(startDay)){
					startDate=DateTimeUtils.strDateTimeToDate(startDay);
				}
				if(!ValidateUtils.isNull(endDay)){
					endDate=DateTimeUtils.strDateTimeToDate(endDay);
				}
				dtos=this.liveService.gift(id,startDate,endDate,startTime,endTime,start,limit);
				total=this.liveService.giftCount(id,startDate,endDate,startTime,endTime);
			}
			/*Integer userId=null;
			if(!ValidateUtils.isNull(sessionKey)){
				userId=this.userService.getUserId(sessionKey);
				dtos=this.liveService.people(userId,null,null,null,null,start,limit);
			}*/
			res.addResponse("total", total);
			res.addResponse("resultList", dtos);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/live")
	@ResponseBody
	public ModelAndView live ( 
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "uid", required = false) Integer uid,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "startDay", required = false) String startDay,
			@RequestParam(value = "endDay", required = false) String endDay,
			@RequestParam(value = "startTime", required = false) Integer startTime,
			@RequestParam(value = "endTime", required = false) Integer endTime,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,
			@RequestParam(value = "adminSessionKey", required = false) String adminSessionKey,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "start", required = false) Integer start,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			List<LiveDto> dtos=new LinkedList<LiveDto>();
			int total=0;
			if(!ValidateUtils.isNull(adminSessionKey)){
				Integer adminId=this.adminService.getUserId(adminSessionKey);
				
				if(ValidateUtils.isNull(adminId)){
					throw new ServiceException("登陆失效");
				}
				int op=12;
				this.adminService.hasOp(adminId,op);
				Date startDate=null;
				Date endDate=null;
				if(!ValidateUtils.isNull(startDay)){
					startDate=DateTimeUtils.strDateTimeToDate(startDay);
				}
				if(!ValidateUtils.isNull(endDay)){
					endDate=DateTimeUtils.strDateTimeToDate(endDay);
				}
				dtos=this.liveService.live(uid,id,name,startDate,endDate,startTime,endTime,start,limit);
				total=this.liveService.liveCount(uid,id,name,startDate,endDate,startTime,endTime);
			}
			Integer userId=null;
			if(!ValidateUtils.isNull(sessionKey)){
				userId=this.userService.getUserId(sessionKey);
				dtos=this.liveService.live(userId,null,null,null,null,null,null,start,limit);
				total=this.liveService.liveCount(userId,null,null,null,null,null,null);
			}
			res.addResponse("total", total);
			res.addResponse("resultList", dtos);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
}