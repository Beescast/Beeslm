package projects.commons.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取ip相关信息
 * 
 * @author luinstein
 * 
 */
public class IpUtils {

	/***
	 * 
	 * 防止代理产生错误的IP 
	 * @param request
	 * @return
	 */
	public static String getRealIp(HttpServletRequest request){
		if(request==null){
			return "127.0.0.1";
		}
		String ip = request.getHeader("x-forwarded-for");  
		
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    }
	   
	    return ip;
	
	}
}
