package org.bees.utils;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import projects.commons.utils.ValidateUtils;

public class FilesUtil{

	
    public static String uploads(HttpServletRequest request, int userId,
			Integer dataX, Integer dataY, Integer dataWidth, Integer dataHeight) {
    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("Filedata");
        if(ValidateUtils.isNull(multipartFile)){
        	multipartFile=multipartRequest.getFile("file");
        }
        /** 拼成完整的文件保存路径加文件 **/	
        String suffix = ""; // 后缀
        String originalFilename = multipartFile.getOriginalFilename(); // 文件全名
        if ((originalFilename != null) && (originalFilename.length() > 0)) { 
            int dot = originalFilename.lastIndexOf('.'); 
            if ((dot >-1) && (dot < (originalFilename.length() - 1))) { 
            	suffix=originalFilename.substring(dot + 1); 
            } 
        }
        
        //创建图片的文件流 迭代器
	   Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(suffix);
	   ImageReader reader=it.next();
	   //获取图片流 建立文图 文件流
	   ImageInputStream iis = null;
		try {
			iis = ImageIO.createImageInputStream(multipartFile.getInputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		UploadFilePathVO uploadFile = UploadFilePathUtil.initFileUpload(userId, "Uploads", suffix);
	   //获取图片默认参数
	   reader.setInput(iis, true);
	   ImageReadParam param=reader.getDefaultReadParam();
	   try {
		   if(!ValidateUtils.isNull(dataHeight)){
			   //定义裁剪区域
				Rectangle rect=new Rectangle(dataX,dataY,dataWidth,dataHeight);
				param.setSourceRegion(rect);
				BufferedImage bi = null;
			
				bi = reader.read(0,param);
				ImageIO.write(bi, suffix, new File(uploadFile.getRealPath()));
			
			
		   }else{
			   File files = new File(uploadFile.getRealPath());
			   multipartFile.transferTo(files);
		   }
	   } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	   
	   
        // 文件名转码
        // fileName = Base64.StringToBase64(fileName);
        // fileName = StringUtil.join(fileName, suffix);
        
        
        return uploadFile.getRelativePath();
    }

}

