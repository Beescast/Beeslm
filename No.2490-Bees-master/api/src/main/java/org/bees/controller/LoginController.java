package org.bees.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bees.biz.service.UserService;
import org.bees.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import projects.commons.exception.ServiceException;
import projects.commons.service.cache.CacheService;
import projects.commons.utils.HttpUtils;
import projects.commons.utils.ValidateUtils;
import projects.commons.utils.config.GlobalConfigure;
import projects.commons.utils.response.FormatType;
import projects.commons.utils.response.Res;

import com.alibaba.fastjson.JSONObject;




@Controller
@RequestMapping("/auth/")
public class LoginController {
 
	@Autowired
	private UserService userService;
	
	@Autowired
	private CacheService cacheService;
		
	@RequestMapping(value = "/login")
	@ResponseBody
	public ModelAndView login(
			@RequestParam(value = "mobile", required = false) String mobile,
			@RequestParam(value = "sk", required = false) String sk,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "mobileCode", required = false) String mobileCode,
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response,HttpSession httpSession)  {
		Res res = new Res();
		try {
			response.setHeader("Access-Control-Allow-Origin", "*");
			if (ValidateUtils.isNull(password)&&ValidateUtils.isNull(mobileCode)) {
				throw new ServiceException("密码不能为空");
			} else {
				System.out.println("auth_CODE_"+sk+":::"+code);
				System.out.println("auth_CODE::"+this.cacheService.getAttribute("CODE_"+sk,String.class).toLowerCase());
				System.out.println(code.toLowerCase());
				if(code.toLowerCase().equals(this.cacheService.getAttribute("CODE_"+sk,String.class).toLowerCase())){
					res = userService.login(mobile, password,mobileCode, request, response,httpSession);
				}else{
					throw new ServiceException("图形验证码不正确");
				}
			}
		}catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	

	@RequestMapping(value = "/unionLogin")
	@ResponseBody
	public ModelAndView unionLogin ( 
			@RequestParam(value = "openid", required = false) String openid,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			res = userService.unionLogin(openid,type);
		}catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/bindUnion")
	@ResponseBody
	public ModelAndView bindUnion(
			@RequestParam(value = "openid", required = false) String openid,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response,HttpSession httpSession)  {
		Res res = new Res();
		try {
			Integer uid=this.userService.getUserId(sessionKey);
			if(ValidateUtils.isNull(uid)){
				throw new ServiceException("用户登陆失效");
			}
			res.addResponse("result", this.userService.bindUnion(uid,type,openid));
			res.commit();
		}catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	
	@RequestMapping(value = "/reg")
	@ResponseBody
	public ModelAndView reg ( 
			@RequestParam(value = "mobile", required = false) String mobile,
			@RequestParam(value = "mobileCode", required = false) String mobileCode,			
			@RequestParam(value = "truename", required = false) String truename,
			@RequestParam(value = "idCard", required = false) String idCard,
			@RequestParam(value = "frontPic", required = false) String frontPic,
			@RequestParam(value = "backPic", required = false) String backPic,
			@RequestParam(value = "handPic", required = false) String handPic,
			@RequestParam(value = "plat", required = false) String plat,
			@RequestParam(value = "liveRoom", required = false) String liveRoom,
			@RequestParam(value = "platPic", required = false) String platPic,
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			res=this.userService.register(mobile, mobileCode, truename, idCard, frontPic, backPic, handPic, plat, liveRoom, platPic,code, request, response);

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/code")
	@ResponseBody
	public void code(
			@RequestParam(value = "mobile", required = false) String mobile,
			@RequestParam(value = "sk", required = false) String sk,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response,HttpSession httpSession)  {			
			int width = 172;//定义图片的width
			int height = 60;//定义图片的height
			int codeCount = 4;//定义图片上显示验证码的个数
			int xx = 30;
		  	int fontHeight = 55;
		  	int codeY = 50;
		  	
		// 定义图像buffer
	    BufferedImage buffImg = new BufferedImage(width, height,
	        BufferedImage.TYPE_INT_RGB);
//			Graphics2D gd = buffImg.createGraphics();
	    //Graphics2D gd = (Graphics2D) buffImg.getGraphics();
	    Graphics gd = buffImg.getGraphics();

	    // 将图像填充为白色
	    gd.setColor(new Color(247,244,239));
	    gd.fillRect(0, 0, width, height);

	    // 创建字体，字体的大小应该根据图片的高度来定。
	    Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
	    // 设置字体。
	    gd.setFont(font);

	    // 画边框。
	    //gd.setColor(Color.GRAY);
	    gd.drawRect(0, 0, width - 1, height - 1);

	    // 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
	    gd.setColor(Color.BLACK);
	    String codes=StringUtil.code(codeCount);
	    // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
	    for (int i = 0; i < codeCount; i++) {
	    	gd.drawString(String.valueOf(codes.charAt(i)), (i + 1) * xx, codeY);
		}
	    
	    // 将四位数字的验证码保存到Session中。
	    cacheService.setAttribute(
				"CODE_"+httpSession.getId(), codes);
	    if(!ValidateUtils.isNull(sk)){
	    	cacheService.setAttribute(
					"CODE_"+sk, codes);
	    }
	    System.out.println("CODE_"+sk+":::"+codes);
	    // 禁止图像缓存。
	    response.setHeader("Pragma", "no-cache");
	    response.setHeader("Cache-Control", "no-cache");
	    response.setDateHeader("Expires", 0);

	    response.setContentType("image/jpeg");
	    
	    // 将图像输出到Servlet输出流中。
	    ServletOutputStream sos;
		try {
			sos = response.getOutputStream();
			ImageIO.write(buffImg, "jpeg", sos);
		    sos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    
	}
	
	@RequestMapping(value = "/authCode")
	@ResponseBody
	public ModelAndView authCode(
			@RequestParam(value = "mobile", required = false) String mobile,
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "sk", required = false) String sk,
			@RequestParam(value = "mobileCode", required = false) String mobileCode,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response,HttpSession httpSession)  {
		Res res = new Res();
		try {
			if (ValidateUtils.isNull(mobile)&&ValidateUtils.isNull(mobileCode)&&ValidateUtils.isNull(code)) {
				throw new ServiceException("验证码不能为空");
			} else {
				if(code.toLowerCase().equals(this.cacheService.getAttribute("CODE_"+sk,String.class).toLowerCase())){
					res = userService.authCode(mobile, mobileCode,request, response,httpSession);
				}else{
					throw new ServiceException("图形验证码不正确");
				}
				
 
			}
		}catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/authMobileCode")
	@ResponseBody
	public ModelAndView authMobileCode(
			@RequestParam(value = "mobile", required = false) String mobile,
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "sk", required = false) String sk,
			@RequestParam(value = "mobileCode", required = false) String mobileCode,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response,HttpSession httpSession)  {
		Res res = new Res();
		try {
			if (ValidateUtils.isNull(mobile)&&ValidateUtils.isNull(mobileCode)) {
				throw new ServiceException("验证码不能为空");
			} else {
				if(!ValidateUtils.isNull(code)){
					if(!code.toLowerCase().equals(this.cacheService.getAttribute("CODE_"+sk,String.class).toLowerCase())){
						throw new ServiceException("图形验证码不正确");
					}
				}
				res = userService.authCode(mobile, mobileCode,request, response,httpSession);
			}
		}catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/findPass")
	@ResponseBody
	public ModelAndView findPass(
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "newPass", required = false) String newPass,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response,HttpSession httpSession)  {
		Res res = new Res();
		boolean flag;
		try {
			response.setHeader("Access-Control-Allow-Origin", "*");
			if (ValidateUtils.isNull(id)||ValidateUtils.isNull(sessionKey)||ValidateUtils.isNull(newPass)) {
				throw new ServiceException("缺少参数");
			} else {
				//Integer userId=this.userService.getUserId(sessionKey);
				if(this.cacheService.getAttribute("SESSION_ID_"+sessionKey,String.class).equals(id)){
					Integer userId=this.cacheService.getAttribute("FIND_PASS_"+id,Integer.class);
					if(!ValidateUtils.isNull(userId)){						
						flag = userService.setPassword(userId, newPass);
					}else{
						flag=false;
					}
					this.cacheService.removeAttribute("SESSION_ID_"+sessionKey);
					this.cacheService.removeAttribute("FIND_PASS_"+id);
				}else{
					throw new ServiceException("参数不正确");
				}
				res.addResponse("result", flag);
				res.commit();
			}
		}catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	@RequestMapping(value = "/getMobileCode")
	@ResponseBody
	public ModelAndView getMobileCode(			
			@RequestParam(value = "mobile", required = false) String mobile,
			@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response,HttpSession httpSession)  {
		Res res = new Res();
		try {
			if (ValidateUtils.isNull(mobile)&&ValidateUtils.isNull(sessionKey)) {
				throw new ServiceException("缺少参数");
			} else {
				Integer userId=0;
				if(!ValidateUtils.isNull(sessionKey)){
					userId=this.userService.getUserId(sessionKey);
				}
				
				res = userService.getMobileCode(mobile,action,userId);
				
			}
		}catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	
	@RequestMapping(value = "/wxlogin")
	@ResponseBody
	public ModelAndView wxlogin ( 
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {

			System.out.println("https://api.weixin.qq.com/sns/oauth2/access_token?appid="+GlobalConfigure.WEIXIN_APPID+"&secret="+GlobalConfigure.WEIXIN_SEC+"&code="+code+"&grant_type=authorization_code");
			JSONObject result=HttpUtils.loadJSON("https://api.weixin.qq.com/sns/oauth2/access_token?appid="+GlobalConfigure.WEIXIN_APPID+"&secret="+GlobalConfigure.WEIXIN_SEC+"&code="+code+"&grant_type=authorization_code");
			System.out.println(result.toJSONString());
			String openid=result.getString("openid");
			
			res = userService.unionLogin(openid,"weixin");
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res.toView(formatType, "index", callback, response);
	}
}