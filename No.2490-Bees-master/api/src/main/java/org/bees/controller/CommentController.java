package org.bees.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bees.biz.service.PackagesService;
import org.bees.biz.service.UserService;
import org.bees.biz.service.dto.IndexCommentDto;
import org.bees.biz.service.dto.PackagesCommentDto;
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
@RequestMapping("/comment/")
public class CommentController {
 
	@Autowired
    private PackagesService packagesService;
	
	@Autowired
    private UserService userService;
	
	
	@RequestMapping(value = "/list")
	@ResponseBody
	public ModelAndView list ( 
			
			@RequestParam(value = "limit", required = false) int limit,
			@RequestParam(value = "start", required = false) int start,
			@RequestParam(value = "order", required = false) Integer order,
			@RequestParam(value = "pid", required = false) Integer pid,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,			
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			if(ValidateUtils.isNull(pid)){
				List<IndexCommentDto> notices=this.packagesService.getCommentList(start,limit,order);
				
				res.addResponse("resultList", notices);
			}else{
				List<PackagesCommentDto> comments=this.packagesService.getCommentsByPid(pid);
				res.addResponse("resultList", comments);
			}
			int total=this.packagesService.getCommentCount(pid);
			res.addResponse("total", total);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/addComment")
	@ResponseBody
	public ModelAndView add ( 
			@RequestParam(value = "sessionKey", required = false) String sessionKey,
			@RequestParam(value = "orderId", required = false) Long orderId,
			@RequestParam(value = "intro", required = false) String comment,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			Integer userId=this.userService.getUserId(sessionKey);
			
			if(ValidateUtils.isNull(userId)){
				throw new ServiceException("登陆失效");
			}
			
			boolean result=this.packagesService.addComment(userId,orderId,comment);
			res.addResponse("result", result);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
}