package org.bees.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bees.biz.persistence.model.Packages;
import org.bees.biz.persistence.model.PackagesPrice;
import org.bees.biz.service.AdminService;
import org.bees.biz.service.PackagesService;
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
@RequestMapping("/package/")
public class PackagesController {
 
	@Autowired
    private PackagesService packagesService;
	
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
			Packages packages=this.packagesService.getInfoById(id);
			List<PackagesPrice> prices=this.packagesService.getPriceByPid(id);
			res.addResponse("result", packages);
			res.addResponse("prices", prices);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/price")
	@ResponseBody
	public ModelAndView price ( 
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,			
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			List<PackagesPrice> prices=this.packagesService.getPriceByPid(id);
			res.addResponse("resultList", prices);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/singlePrice")
	@ResponseBody
	public ModelAndView singlePrice ( 
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,			
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			PackagesPrice price=this.packagesService.getPriceByid(id);
			res.addResponse("result", price);
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
			@RequestParam(value = "order", required = false) Integer order,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			List<Packages> prices=this.packagesService.getPackagesList(start, limit, order);
			res.addResponse("resultList", prices);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	@RequestMapping(value = "/add")
	@ResponseBody
	public ModelAndView add ( 
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "content", required = false) String content,
			@RequestParam(value = "pic", required = false) String pic,
			@RequestParam(value = "titleOne", required = false) String titleOne,
			@RequestParam(value = "titleTwo", required = false) String titleTwo,
			@RequestParam(value = "titleThree", required = false) String titleThree,
			@RequestParam(value = "prices", required = false) String prices,
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
			int op=5;
			this.adminService.hasOp(adminId,op);
			
			boolean result=this.packagesService.add(name,content,titleOne,titleTwo,titleThree,pic,prices);
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
			int op=5;
			this.adminService.hasOp(adminId,op);
			boolean result=this.packagesService.del(id);
			res.addResponse("result", result);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/delPrice")
	@ResponseBody
	public ModelAndView delPrice ( 
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
			int op=5;
			this.adminService.hasOp(adminId,op);
			boolean result=this.packagesService.delPrice(id);
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
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "content", required = false) String content,
			@RequestParam(value = "pic", required = false) String pic,
			@RequestParam(value = "titleOne", required = false) String titleOne,
			@RequestParam(value = "titleTwo", required = false) String titleTwo,
			@RequestParam(value = "titleThree", required = false) String titleThree,
			@RequestParam(value = "prices", required = false) String prices,
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
			int op=5;
			this.adminService.hasOp(adminId,op);
			boolean result=this.packagesService.edit(id,name,content,titleOne,titleTwo,titleThree,pic,prices);
			res.addResponse("result", result);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
}