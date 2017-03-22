package org.bees.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import projects.commons.utils.ValidateUtils;
import projects.commons.utils.config.GlobalConfigure;

public class SMSUtil{

	/**
	 * 发送短信
	 * 
	 * @param name			用户名
	 * @param pwd			密码
	 * @param mobileString	电话号码字符串，中间用英文逗号间隔
	 * @param contextString	内容字符串
	 * @param sign			签名
	 * @param stime			追加发送时间，可为空，为空为及时发送
	 * @param extno			扩展码，必须为数字 可为空
	 * @return				
	 * @throws Exception
	 */
    public static String doPost(String mobileString,Integer type, String contextString,
    		String stime) {
    	 OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        String resultBuffer = "";
    	try {
	    	StringBuffer param = new StringBuffer();
	    	
	    	param.append("name="+GlobalConfigure.SMS_NAME);
	    	param.append("&pwd="+GlobalConfigure.SMS_PASSWORD);
	    	param.append("&mobile=").append(mobileString);
	    	param.append("&content=").append(getContext(type,contextString));
	    	if(!ValidateUtils.isNull(stime)){
	    		param.append("&stime="+stime);
	    	}    	
	    	param.append("&sign=").append(URLEncoder.encode("晴蜂科技","UTF-8"));
	    	param.append("&type=pt");
	    	//param.append("&extno=").append(extno);
        
	    	URL localURL;
		
			localURL = new URL("http://web.wasun.cn/asmx/smsservice.aspx?");
		
	        URLConnection connection = localURL.openConnection();
	        HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
	        
	        httpURLConnection.setDoOutput(true);
	        httpURLConnection.setRequestMethod("POST");
	        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
	        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(param.length()));
	        
	       
	       
        
            outputStream = httpURLConnection.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream);
            
            outputStreamWriter.write(param.toString());
            outputStreamWriter.flush();
            
            if (httpURLConnection.getResponseCode() >= 300) {
                throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
            }
            
            inputStream = httpURLConnection.getInputStream();
            resultBuffer = convertStreamToString(inputStream);
            
        }catch(Exception e){
        	
        }finally {
            
        }

        return resultBuffer;
    }
	
	
	private static Object getContext(Integer type, String contextString) {
		String context = "";
		switch (type) {
		case 1:	
			context="您本次操作的验证码为："+contextString;
			break;
		case 2:
			String[] con=contextString.split(",");
			context="您已通过Bees主播认证，您的账号为："+con[0]+"，密码为："+con[1]+"，登录后请更改密码，官网www.beeslm.com。";
			break;
		case 3:
			context="您因【"+contextString+"】未通过Bees主播认证，请到官网www.beeslm.com重新申请。";
			break;
		case 4:
			context="您购买的广告套餐已启动，结束时间为："+contextString+"。";
			break;
		case 5:
			context="您的提现申请已经处理，请到银行查收。";
			break;
		case 6:
			context="您的提现申请未通过，请到官网www.beeslm.com查看。";
			break;
		case 7:
			context="您已中标一笔赏金任务，赏金："+contextString+"，速去官网www.beeslm.com完成任务。";
			break;
		case 8:
			context="您的任务已完成，赏金（"+contextString+"）已发放，请到官网www.beeslm.com查看个人钱包。";
			break;
		case 9:
			context="您的任务因【"+contextString+"】未完成，请到官网www.beeslm.com查看。";
			break;
		case 10:
			context="有一笔赏金任务适合您，赏金："+contextString+"元，请到官网www.beeslm.com查看。";
			break;
		case 11:
			context="您已修改网站密码，如非本人操作，请联系客服400-900-9090。";
			break;
		default:
			break;
		}
		try {
			return URLEncoder.encode(context,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		
	}


	/**
	 * 转换返回值类型为UTF-8格式.
	 * @param is
	 * @return
	 */
	public static String convertStreamToString(InputStream is) {    
        StringBuilder sb1 = new StringBuilder();    
        byte[] bytes = new byte[4096];  
        int size = 0;  
        
        try {    
        	while ((size = is.read(bytes)) > 0) {  
                String str = new String(bytes, 0, size, "UTF-8");  
                sb1.append(str);  
            }  
        } catch (IOException e) {    
            e.printStackTrace();    
        } finally {    
            try {    
                is.close();    
            } catch (IOException e) {    
               e.printStackTrace();    
            }    
        }    
        return sb1.toString();    
    }

}

