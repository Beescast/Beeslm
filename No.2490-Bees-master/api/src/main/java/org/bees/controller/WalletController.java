package org.bees.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bees.biz.service.AdminService;
import org.bees.biz.service.UserService;
import org.bees.biz.service.WalletService;
import org.bees.biz.service.dto.WalletDto;
import org.bees.biz.service.dto.WalletTurnoverDto;
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
@RequestMapping("/wallet/")
public class WalletController {
 
	@Autowired
    private WalletService walletService;
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private AdminService adminService;
	
	/**
	 * 钱包流水
	 * @param limit
	 * @param start
	 * @param sessionKey
	 * @param formatType
	 * @param callback
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public ModelAndView list ( 
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "start", required = false) Integer start,			
			@RequestParam(value = "sessionKey", required = false) String sessionKey,			
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			Integer uid=this.userService.getUserId(sessionKey);
			if(ValidateUtils.isNull(uid)){
				throw new ServiceException("登陆失效，请重新登陆");
			}
			List<WalletTurnoverDto> wallets=this.walletService.getList(uid,start,limit);
			int total=this.walletService.getListCount(uid);
			res.addResponse("total", total);
			res.addResponse("resultList", wallets);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	/**
	 * 提现
	 * @param password
	 * @param money
	 * @param sessionKey
	 * @param formatType
	 * @param callback
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/cash")
	@ResponseBody
	public ModelAndView cash ( 
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "money", required = false) String money,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			Integer uid=this.userService.getUserId(sessionKey);
			if(ValidateUtils.isNull(uid)){
				throw new ServiceException("登陆失效，请重新登陆");
			}
			if(!this.userService.authPayPassword(uid,password)){
				throw new ServiceException("密码错误");
			}
			boolean result=this.walletService.cash(uid,money);
			res.addResponse("result", result);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	/**
	 * 钱包流水
	 * @param id
	 * @param uid
	 * @param payType
	 * @param mobile
	 * @param businessNo
	 * @param startTime
	 * @param endTime
	 * @param businessType
	 * @param limit
	 * @param start
	 * @param adminSessionKey
	 * @param formatType
	 * @param callback
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/adminList")
	@ResponseBody
	public ModelAndView adminList (
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "uid", required = false) Integer uid,
			@RequestParam(value = "payType", required = false) Byte payType,
			@RequestParam(value = "mobile", required = false) String mobile,
			@RequestParam(value = "businessNo", required = false) String businessNo,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "businessType", required = false) Byte businessType,
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
			int op=15;
			this.adminService.hasOp(adminId,op);
			Date startDate = null;
			Date endDate = null;
			if(!ValidateUtils.isNull(startTime)){
				startDate=DateTimeUtils.strDateTimeToDate(startTime);
			}
			if(!ValidateUtils.isNull(endTime)){
				endDate=DateTimeUtils.strDateTimeToDate(endTime);
			}
			List<WalletTurnoverDto> OrderLogs=this.walletService.adminTurnOverList(id,uid,payType,mobile,businessNo,startDate,endDate,businessType,start,limit);
			int total=this.walletService.adminTurnOverCount(id, uid, payType, mobile, businessNo, startDate, endDate, businessType);
			res.addResponse("resultList", OrderLogs);
			res.addResponse("total", total);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	/**
	 * 提现列表
	 * @param id
	 * @param uid
	 * @param accountName
	 * @param status
	 * @param mobile
	 * @param nickname
	 * @param startTime
	 * @param endTime
	 * @param startMoney
	 * @param endMoney
	 * @param limit
	 * @param start
	 * @param adminSessionKey
	 * @param formatType
	 * @param callback
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/adminCashList")
	@ResponseBody
	public ModelAndView adminCashList (
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "uid", required = false) Integer uid,
			@RequestParam(value = "accountName", required = false) String accountName,
			@RequestParam(value = "status", required = false) Byte status,
			@RequestParam(value = "mobile", required = false) String mobile,
			@RequestParam(value = "nickname", required = false) String nickname,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "startMoney", required = false) String startMoney,
			@RequestParam(value = "endMoney", required = false) String endMoney,
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
			int op=15;
			this.adminService.hasOp(adminId,op);
			Date startDate = null;
			Date endDate = null;
			if(!ValidateUtils.isNull(startTime)){
				startDate=DateTimeUtils.strDateTimeToDate(startTime);
			}
			if(!ValidateUtils.isNull(endTime)){
				endDate=DateTimeUtils.strDateTimeToDate(endTime);
			}
			List<WalletDto> OrderLogs=this.walletService.adminList(id,status,accountName,mobile,nickname,startDate,endDate,uid,startMoney,endMoney,start,limit);
			int total=this.walletService.adminListCount(id,status,accountName,mobile,nickname,startDate,endDate,uid,startMoney,endMoney);
			res.addResponse("total", total);
			res.addResponse("resultList", OrderLogs);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	/**
	 * 提现审核
	 * @param id
	 * @param status
	 * @param orderId
	 * @param reseaon
	 * @param adminSessionKey
	 * @param formatType
	 * @param callback
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/cashOp")
	@ResponseBody
	public ModelAndView cashOp (
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "status", required = false) Byte status,
			@RequestParam(value = "thirdOrderId", required = false) String orderId,
			@RequestParam(value = "reseaon", required = false) String reseaon,
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
			int op=15;
			this.adminService.hasOp(adminId,op);
			
			boolean result=this.walletService.cashOp(adminId,id,status,orderId,reseaon);
			res.addResponse("result", result);
			res.commit();

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}	
}