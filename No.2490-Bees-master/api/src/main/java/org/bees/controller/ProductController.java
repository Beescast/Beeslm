package org.bees.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bees.biz.persistence.model.Product;
import org.bees.biz.service.AdminService;
import org.bees.biz.service.ProductService;
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
@RequestMapping("/product/")
public class ProductController {
 
	@Autowired
    private ProductService ProductService;
	
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
			
			Product product=this.ProductService.getInfoById(id);
			res.addResponse("result", product);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/list")
	@ResponseBody
	public ModelAndView list (
			@RequestParam(value = "position", required = false) Byte position,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "start", required = false) Integer start,
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
			}
			List<Product> products=this.ProductService.getList(position,start,limit);
			Integer count=this.ProductService.getListCount(position);
			res.addResponse("total", count);
			res.addResponse("resultList", products);
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
			@RequestParam(value = "pic", required = false) String pic,
			@RequestParam(value = "href", required = false) String href,
			@RequestParam(value = "position", required = false) Byte position,
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
			
			boolean result=this.ProductService.add(name,pic,href,position);
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
			int op=19;
			this.adminService.hasOp(adminId,op);
			boolean result=this.ProductService.del(id);
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
			@RequestParam(value = "pic", required = false) String pic,
			@RequestParam(value = "href", required = false) String href,
			@RequestParam(value = "position", required = false) Byte position,
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
			boolean result=this.ProductService.edit(id,name,pic,href,position);
			res.addResponse("result", result);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	
}