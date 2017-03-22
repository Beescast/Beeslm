package org.bees.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import projects.commons.utils.config.GlobalConfigure;

public class UploadFilePathUtil{
/**
     * 获取图片上传路径(处理高宽)
     * 
     * @return 
     */
    public static UploadFilePathVO initFileUpload(Integer userID, String imageType, String suffix) {
    	String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
        Random random = new Random();   
        StringBuffer sb = new StringBuffer();   
        for (int i = 0; i < 6; i++) {   
            int number = random.nextInt(base.length());   
            sb.append(base.charAt(number));   
        }   
        
        String randomKey =sb.toString();
        Date date = new Date();
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(date);
        String timeStr = new SimpleDateFormat("HHmmssSSS").format(date);
        String relativePath = "/user_images/"+ userID+  "/"+ dateStr+  "/";
        String realPath =GlobalConfigure.FILE_UPLOADPATH+  relativePath;

        File logoSaveFile = new File(realPath);
        if (!logoSaveFile.exists()) {
            logoSaveFile.mkdirs();
        }
        // 图片文件名: 时间戳 + 随机串 + 高宽
        String fileName = timeStr+ randomKey+  '.'+  suffix;
        UploadFilePathVO uploadFile = new UploadFilePathVO();
        uploadFile.setRelativePath(relativePath+fileName);
        uploadFile.setRealPath(realPath+fileName);
        return uploadFile;
    }
}

