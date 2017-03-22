package org.bees.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bees.alipay.config.AlipayConfig;
import org.bees.alipay.util.AlipayNotify;
import org.bees.alipay.util.AlipaySubmit;
import org.bees.biz.persistence.model.PackagesOrder;
import org.bees.biz.persistence.model.User;
import org.bees.biz.persistence.model.WalletRecharge;
import org.bees.biz.service.AdminService;
import org.bees.biz.service.OrderService;
import org.bees.biz.service.UserService;
import org.bees.biz.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import projects.commons.exception.ServiceException;
import projects.commons.utils.MD5Utils;
import projects.commons.utils.ValidateUtils;
import projects.commons.utils.config.GlobalConfigure;
import projects.commons.utils.config.GlobalKeys;
import projects.commons.utils.date.DateUtils;
import projects.commons.utils.response.FormatType;
import projects.commons.utils.response.Res;

@Controller
@RequestMapping("/pay/")
public class PayController {
	
	@Autowired
    private OrderService orderService;
	
	@Autowired
    private WalletService walletService;
 	
	@Autowired
    private AdminService adminService;
	
	@Autowired
    private UserService userService;
	
	/**
	 * 支付宝付款页面
	 * @param orderId
	 * @param type
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/alipay.html")
	public String alipay(
			@RequestParam(value = "orderId",required  = false) String orderId,
			@RequestParam(value = "type",required  = false) String type,
			@RequestParam(value = "totalFee",required  = false) String totalFee,
			HttpServletRequest request,Model model){
		String subject="";
		if(type.equals("package")){
			PackagesOrder packageOrder=orderService.getInfoById(Long.valueOf(orderId));
			subject=packageOrder.getPackageName();
			totalFee=packageOrder.getPrice();
		}else if (type.equals("wallet")) {
			String dateString=DateUtils.toDateFormat(new Date(), "yyyyMMdd");
			Random rnd = new Random();
			String randString= rnd.nextInt(89999) + 10000+"";		
			orderId=dateString+randString;
			subject="充值";
		}
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", AlipayConfig.service);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_id", AlipayConfig.seller_id);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", AlipayConfig.payment_type);
		sParaTemp.put("notify_url", AlipayConfig.notify_url);
		sParaTemp.put("return_url", AlipayConfig.return_url);
		sParaTemp.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
		sParaTemp.put("out_trade_no", orderId);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", totalFee);
		sParaTemp.put("extra_common_param", type);
		
		//sParaTemp.put("body", body);
		//其他业务参数根据在线开发文档，添加参数.文档地址:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.O9yorI&treeId=62&articleId=103740&docType=1
        //如sParaTemp.put("参数名","参数值");
		
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
		//out.println(sParaTemp.toString());
		model.addAttribute("htmls", sHtmlText);
		return "alipay";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/aliPayRes")
	@ResponseBody
	public void aliPayRes ( 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号

		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//支付宝交易号

		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		
		String extra_common_param=new String(request.getParameter("extra_common_param").getBytes("ISO-8859-1"),"UTF-8");
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		
		//计算得出通知验证结果
		boolean verify_result = AlipayNotify.verify(params);
		
		if(verify_result){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码
			Long orderId=Long.valueOf(out_trade_no);
			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
				this.finishOrder(extra_common_param,orderId,trade_no,new Byte("1"));
			}
			
			//该页面可做页面美工编辑
			System.out.println("验证成功<br />");
			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
			response.sendRedirect(GlobalConfigure.URL_FRONT+"buyMeal_cancel.html");
			//System.out.println("跳走了<br />");
			//////////////////////////////////////////////////////////////////////////////////////////
		}else{
			System.out.println("验证失败<br />");
			//该页面可做页面美工编辑
			//out.println("验证失败");
		}
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/aliPayResNotify")
	@ResponseBody
	public void aliPayResNotify ( 
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号

		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//支付宝交易号

		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

		String extra_common_param=new String(request.getParameter("extra_common_param").getBytes("ISO-8859-1"),"UTF-8");
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		if(AlipayNotify.verify(params)){//验证成功
			
			Long orderId=Long.valueOf(out_trade_no);
			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
				this.finishOrder(extra_common_param,orderId,trade_no,new Byte("1"));
			}

			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				
			System.out.print("success");	//请不要修改或删除

			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			System.out.print("fail");
		}
	}
	
	@RequestMapping(value = "/weixinPayRes")
	@ResponseBody
	public void weixinPayRes ( 
			@RequestParam(value = "time",required  = false) String time,
			@RequestParam(value = "key",required  = false) String key,
			@RequestParam(value = "orderId",required  = false) Long orderId,
			@RequestParam(value = "type",required  = false) String type,
			@RequestParam(value = "tradeNo",required  = false) String tradeNo,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String keys= MD5Utils
					.getMD5String(MD5Utils
							.getMD5String(time) + GlobalKeys.MD5KEY+"123");
			String ip=this.getIpAddr(request);
			if((ip.equals("127.0.0.1")||ip.equals(GlobalConfigure.BACKEND_URL))&&keys.equals(key)){
				this.finishOrder(type, orderId, tradeNo,new Byte("2"));
			}
			System.out.println("ip:"+ip);
		} catch (NoSuchAlgorithmException | IOException e) {
		}
	}
	
	@RequestMapping(value = "/wallet")
	@ResponseBody
	public ModelAndView wallet ( 
			@RequestParam(value = "orderId", required = false) Long orderId,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Res res = new Res();
		try {
			if(ValidateUtils.isNull(sessionKey)||ValidateUtils.isNull(orderId)){
				throw new ServiceException("缺少参数");
			}
			
			Integer userId=this.userService.getUserId(sessionKey);
			if(ValidateUtils.isNull(userId)){
				throw new ServiceException("登陆失效");
			}
			User user=this.userService.getUserById(userId);
			if(user.getPayPasswd().equals(userService.getSafePass(password))){
				PackagesOrder packageOrder=orderService.getInfoById(orderId);
				if(packageOrder.getUid().intValue()!=userId.intValue()){
					throw new ServiceException("支付失败");
				}
				String price=packageOrder.getPrice();
				if(Double.valueOf(price)<=Double.valueOf(user.getBalance())){
					//flag=this.orderService.pay(orderId,new Byte("0"),userId,Double.valueOf(user.getBalance())-Double.valueOf(price)+"",price,null);
					//this.walletService.pay(orderId, new Byte("0"), userId, Double.valueOf(user.getBalance())-Double.valueOf(price)+"", price, null);
					this.finishOrder("package", orderId, "", new Byte("0"));
					res.addResponse("result", true);
				}else{
					throw new ServiceException("余额不足");
				}
				
			}else{
				throw new ServiceException("支付密码错误");
				/*res.addResponse("result", false);
				res.addResponse("msg", "支付密码错误");*/
			}
			
			res.commit();
		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
	private void finishOrder(String type,Long orderId,String tradeNo,Byte payType) {
		if(type=="wallet"){
			WalletRecharge recharge=this.walletService.getRechargeById(orderId);
			if(recharge.getStatus().intValue()==0){
				User user=this.userService.getUserById(recharge.getUid());
				String balance=Double.valueOf(user.getBalance())+Double.valueOf(recharge.getMoney())+"";
				this.walletService.pay(orderId,payType, recharge.getUid(), balance,recharge.getMoney(),tradeNo);
			}
		}else{
			PackagesOrder packageOrder=orderService.getInfoById(orderId);
			if(packageOrder.getStatus().intValue()==0){
				User user=this.userService.getUserById(packageOrder.getUid());
				String balance=Double.valueOf(user.getBalance())-Double.valueOf(packageOrder.getPrice())+"";
				this.orderService.pay(orderId, payType, packageOrder.getUid(), balance,packageOrder.getPrice(),tradeNo);
			}
		}

	}
	public String getIpAddr(HttpServletRequest request){  
        String ip = request.getHeader("X-Real-IP");  
        if (!ValidateUtils.isNull(ip) && !"unknown".equalsIgnoreCase(ip)) {  
            return ip;  
        }  
        ip = request.getHeader("X-Forwarded-For");  
        if (!ValidateUtils.isNull(ip) && !"unknown".equalsIgnoreCase(ip)) {  
            // 多次反向代理后会有多个IP值，第一个为真实IP。  
            int index = ip.indexOf(',');  
            if (index != -1) {  
                return ip.substring(0, index);  
            } else {  
                return ip;  
            }  
        } else {  
            return request.getRemoteAddr();  
        }  
    }  
	
}