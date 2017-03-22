package org.bees.biz.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bees.biz.persistence.manager.NoticeManager;
import org.bees.biz.persistence.manager.UserManager;
import org.bees.biz.persistence.model.Notice;
import org.bees.biz.service.dto.NoticeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {
	
	@Autowired
	private NoticeManager noticeManager;	
		
	@Autowired
	private UserManager userManager;
	
	

	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(NoticeService.class);

	public Notice getInfoById(Integer id) {		
		return this.noticeManager.getInfoById(id);
	}

	public List<NoticeDto> getList(String title, Byte status, Date startDate, Date endDate, Integer limit, Integer start) {
		List<Notice> notices=this.noticeManager.getList(title,status,startDate,endDate,start,limit);
		List<NoticeDto> dtos=new LinkedList<NoticeDto>();
		for (Notice notice : notices) {
			NoticeDto dto=new NoticeDto();
			try {
				PropertyUtils.copyProperties(dto, notice);
			} catch (IllegalAccessException | InvocationTargetException
					| NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dtos.add(dto);
		}		
		return dtos;
	}

	public boolean add(Integer userId, String title, String content, Byte status) {

		return this.noticeManager.add(userId,title,content,status);
	}

	public boolean del(Integer id) {
		return this.noticeManager.del(id);
	}

	public boolean edit(Integer id, String title, String content, Byte status) {
		return this.noticeManager.edit(id,title,content,status);
	}

	public Integer getListCount(String title, Byte status, Date startDate,
			Date endDate) {
		return this.noticeManager.getListCount(title, status, startDate, endDate);
	}

	
	
}