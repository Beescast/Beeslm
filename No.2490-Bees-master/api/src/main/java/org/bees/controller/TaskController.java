package org.bees.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bees.biz.persistence.model.Task;
import org.bees.biz.persistence.model.TaskCategory;
import org.bees.biz.persistence.model.TaskSignPic;
import org.bees.biz.service.AdminService;
import org.bees.biz.service.TaskService;
import org.bees.biz.service.UserService;
import org.bees.biz.service.dto.TaskDto;
import org.bees.biz.service.dto.TaskSignDto;
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
@RequestMapping("/task/")
public class TaskController {
 
	@Autowired
    private TaskService taskService;
	
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
			Task Task=this.taskService.getInfoById(id);
			res.addResponse("result", Task);
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
			@RequestParam(value = "status", required = false) Byte status,
			@RequestParam(value = "sign", required = false) Byte sign,
			@RequestParam(value = "catId", required = false) Integer catId,
			@RequestParam(value = "bidStatus", required = false) Byte bidStatus ,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,			
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			Integer userId=null;
			if(!ValidateUtils.isNull(sessionKey)){
				userId=this.userService.getUserId(sessionKey);
			}
			
			List<TaskDto> Tasks=this.taskService.getList(userId,status,sign,catId,bidStatus,start,limit);
			int total=this.taskService.getListCount(userId,status,sign,catId,bidStatus);
			res.addResponse("total", total);
			res.addResponse("resultList", Tasks);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/addSign")
	@ResponseBody
	public ModelAndView addSign (
			@RequestParam(value = "taskId", required = false) Integer taskId,
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
			
			boolean result=this.taskService.addSign(userId,taskId);
			res.addResponse("result", result);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/add")
	@ResponseBody
	public ModelAndView add (
			@RequestParam(value = "catId", required = false) Integer catId,
			@RequestParam(value = "type", required = false) Byte type,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "price", required = false) String price,			
			@RequestParam(value = "num", required = false) Integer num,
			@RequestParam(value = "notice", required = false) Integer notice,
			@RequestParam(value = "msg", required = false) Integer msg,
			@RequestParam(value = "illustration", required = false) String illustration,			
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
			int op=8;
			this.adminService.hasOp(adminId,op);
			boolean result=this.taskService.add(adminId,title,catId,type,price,num,illustration,notice,msg);
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
			int op=8;
			this.adminService.hasOp(adminId,op);
			boolean result=this.taskService.del(id);
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
			@RequestParam(value = "catId", required = false) Integer catId,
			@RequestParam(value = "type", required = false) Byte type,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "price", required = false) String price,
			@RequestParam(value = "num", required = false) Integer num,
			@RequestParam(value = "illustration", required = false) String illustration,
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
			int op=8;
			this.adminService.hasOp(adminId,op);
			
			boolean result=this.taskService.edit(id,title,catId,type,price,num,illustration);
			res.addResponse("result", result);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	
	@RequestMapping(value = "/catList")
	@ResponseBody
	public ModelAndView catList ( 
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "start", required = false) Integer start,
			@RequestParam(value = "gt", required = false) Integer gt,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,			
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			
			List<TaskCategory> Tasks=this.taskService.getcatList(start,limit,gt);
			res.addResponse("resultList", Tasks);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/selectListForDsj")
	@ResponseBody
	public ModelAndView selectList ( 
			@RequestParam(value = "sessionKey", required = false) String sessionKey,			
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			Integer userId=null;
			if(!ValidateUtils.isNull(sessionKey)){
				userId=this.userService.getUserId(sessionKey);
			}
			List<TaskCategory> Tasks=this.taskService.getcatList(null,null,1);
			List<Map<String, Object>> typeLists=new LinkedList<Map<String,Object>>();
			Map<String, Object>  type=new HashMap<String, Object>();
			
			for (TaskCategory taskCategory : Tasks) {
				Map<String, Object>  type1=new HashMap<String, Object>();
				type1.put("title", taskCategory.getName());
				type1.put("id", taskCategory.getId());
				typeLists.add(type1);
			}
			type.put("title", "任务类型");
			type.put("list", typeLists);
			List<Map<String, Object>> results=new LinkedList<Map<String,Object>>();
			Map<String, Object>  status=new HashMap<String, Object>();
			status.put("title", "任务状态");
			List<Map<String, Object>> statusLists=new LinkedList<Map<String,Object>>();
			Map<String, Object>  statusList1=new HashMap<String, Object>();
			Map<String, Object>  statusList2=new HashMap<String, Object>();
			Map<String, Object>  statusList3=new HashMap<String, Object>();
			statusList1.put("title", "全部任务");
			statusList2.put("title", "招募中");
			statusList2.put("id", 0);
			statusList3.put("title", "招募完成");
			statusList3.put("id", 1);
			statusLists.add(statusList1);
			statusLists.add(statusList2);
			statusLists.add(statusList3);
			status.put("list", statusLists);
			results.add(status);
			if(!ValidateUtils.isNull(userId)){
				Map<String, Object>  bind=new HashMap<String, Object>();
				status.put("title", "报名状态");
				List<Map<String, Object>> bindLists=new LinkedList<Map<String,Object>>();
				Map<String, Object>  bindList1=new HashMap<String, Object>();
				Map<String, Object>  bindList2=new HashMap<String, Object>();
				Map<String, Object>  bindList3=new HashMap<String, Object>();
				bindList1.put("title", "全部状态");
				bindList2.put("title", "已报名");
				bindList2.put("id", 1);
				bindList3.put("title", "未报名");
				bindList3.put("id", 0);
				bindLists.add(bindList1);
				bindLists.add(bindList2);
				bindLists.add(bindList3);
				bind.put("list", bindLists);
				results.add(bind);
			}
			results.add(type);
			res.addResponse("resultList", results);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/addCat")
	@ResponseBody
	public ModelAndView addCat (
			@RequestParam(value = "name", required = false) String name,
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
			
			if(!this.userService.isAdmin(adminSessionKey)){
				throw new ServiceException("没有权限");
			}
			boolean result=this.taskService.addCat(userId,name);
			res.addResponse("result", result);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/delCat")
	@ResponseBody
	public ModelAndView delCat ( 
			@RequestParam(value = "id", required = false) Integer id,
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
			
			if(!this.userService.isAdmin(adminSessionKey)){
				throw new ServiceException("没有权限");
			}
			boolean result=this.taskService.delCat(id);
			res.addResponse("result", result);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/editCat")
	@ResponseBody
	public ModelAndView editCat (
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "name", required = false) String name,
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
			
			if(!this.userService.isAdmin(adminSessionKey)){
				throw new ServiceException("没有权限");
			}
			boolean result=this.taskService.editCat(id,name);
			res.addResponse("result", result);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	
	@RequestMapping(value = "/adminList")
	@ResponseBody
	public ModelAndView adminList ( 
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "start", required = false) Integer start,
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "status", required = false) Byte status,
			@RequestParam(value = "type", required = false) Byte type,
			@RequestParam(value = "catId", required = false) Integer catId,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime,
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
			Date startDate = null;
			Date endDate = null;
			if(!ValidateUtils.isNull(startTime)){
				startDate=DateTimeUtils.strDateTimeToDate(startTime);
			}
			if(!ValidateUtils.isNull(endTime)){
				endDate=DateTimeUtils.strDateTimeToDate(endTime);
			}
			
			List<Task> Tasks=this.taskService.adminList(id,title,status,type,catId,startDate,endDate,start,limit);
			int total=this.taskService.adminListCount(id, title, status, type, catId, startDate, endDate);
			res.addResponse("resultList", Tasks);
			res.addResponse("total", total);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/getSignList")
	@ResponseBody
	public ModelAndView getSignList ( 
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "start", required = false) Integer start,
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "taskId", required = false) Integer taskId,
			@RequestParam(value = "catId", required = false) Integer catId,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "bidStatus", required = false) Byte bidStatus,
			@RequestParam(value = "payStatus", required = false) Byte payStatus,
			@RequestParam(value = "startTime", required = false) String startTime,			
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "timeType", required = false) String timeType,
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
			Date startDate = null;
			Date endDate = null;
			if(!ValidateUtils.isNull(startTime)){
				startDate=DateTimeUtils.strDateTimeToDate(startTime);
			}
			if(!ValidateUtils.isNull(endTime)){
				endDate=DateTimeUtils.strDateTimeToDate(endTime);
			}
			List<TaskSignDto> Tasks=this.taskService.getSignList(id,title,catId,taskId,bidStatus,payStatus,startDate,endDate,timeType,start,limit);
			res.addResponse("total", this.taskService.getSignListCount(id,title,catId,taskId,bidStatus,payStatus,startDate,endDate,timeType));
			res.addResponse("resultList", Tasks);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/getSignPicList")
	@ResponseBody
	public ModelAndView getSignPicList ( 
			@RequestParam(value = "taskId", required = false) Integer taskId,
			@RequestParam(value = "uid", required = false) Integer uid,
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
						
			List<TaskSignPic> pics=this.taskService.getSignPicList(taskId,uid);
			res.addResponse("resultList", pics);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/finishTask")
	@ResponseBody
	public ModelAndView finishTask (
			@RequestParam(value = "taskId", required = false) Integer taskId,
			@RequestParam(value = "pics[]", required = false) String[] pics,
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
			
			boolean result=this.taskService.finishTask(userId,taskId,pics);
			
			res.addResponse("result", result);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/sign")
	@ResponseBody
	public ModelAndView sign (
			@RequestParam(value = "taskId", required = false) Integer taskId,
			@RequestParam(value = "uids", required = false) String uids,
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
			int op=9;
			this.adminService.hasOp(adminId,op);
			
			boolean result=this.taskService.sign(taskId,uids);
			res.addResponse("result", result);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/unsign")
	@ResponseBody
	public ModelAndView unsign (
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
			int op=9;
			this.adminService.hasOp(adminId,op);
			
			boolean result=this.taskService.unsign(id);
			res.addResponse("result", result);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/auth")
	@ResponseBody
	public ModelAndView auth (
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "status", required = false) Byte status,
			@RequestParam(value = "reson", required = false) String reson,
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
			int op=10;
			this.adminService.hasOp(adminId,op);
			
			boolean result=this.taskService.auth(adminId,id,status,reson);
			res.addResponse("result", result);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/finish")
	@ResponseBody
	public ModelAndView finish (
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
			int op=10;
			this.adminService.hasOp(adminId,op);
			
			boolean result=this.taskService.finish(id);
			res.addResponse("result", result);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
}