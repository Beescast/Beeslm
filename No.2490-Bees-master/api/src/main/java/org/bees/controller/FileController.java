package org.bees.controller;

import java.rmi.ServerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bees.biz.service.UserService;
import org.bees.utils.FilesUtil;
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
@RequestMapping("/file")
public class FileController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/uploadFiles")
	@ResponseBody
	public ModelAndView uploadFiles(
			@RequestParam(value = "dataX", required = false) Integer dataX,
			@RequestParam(value = "dataY", required = false) Integer dataY,
			@RequestParam(value = "dataHeight", required = false) Integer dataHeight,
			@RequestParam(value = "dataWidth", required = false) Integer dataWidth,
			@RequestParam(value = "sessionKey", required = false) String sessionKey,
			@RequestParam(value = "formatType", defaultValue = "json", required = false) FormatType formatType,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback,
			HttpServletRequest request, HttpServletResponse response) throws ServerException {
		Res res = new Res();
		
		try {
			int userId=0;
			if(!ValidateUtils.isNull(sessionKey)){
				userId=this.userService.getUserId(sessionKey);
			}
			String filepath=FilesUtil.uploads(request, userId, dataX, dataY,dataWidth,dataHeight);

			res.addResponse("urls", filepath);			
			
			res.commit();

			

		} catch (ServiceException e) {
			res.addError(e.getMessage());
		}
		return res.toView(formatType, "index", callback, response);
	}
	
}