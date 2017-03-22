package org.bees.biz.service;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bees.biz.persistence.manager.BannerManager;
import org.bees.biz.persistence.model.Banner;
import org.bees.biz.service.dto.BannerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannerService {
	
	@Autowired
	private BannerManager bannerManager;
	
	
	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(BannerService.class);

	public Banner getInfoById(Integer id) {		
		return this.bannerManager.getInfoById(id);
	}

	public List<BannerDto> getList(String page, String position, Integer start, Integer limit) {
		List<Banner> Banners=this.bannerManager.getList(page,position,start,limit);
		List<BannerDto> dtos=new LinkedList<BannerDto>();
		for (Banner banner : Banners) {
			BannerDto dto=new BannerDto();
			try {
				PropertyUtils.copyProperties(dto, banner);
				dto.setUrl(banner.getPicHref());
				dto.setImgUrl(banner.getPicUrl());
			} catch (IllegalAccessException | InvocationTargetException
					| NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dtos.add(dto);
		}		
		return dtos;
	}

	public boolean add(Integer userId, String title, String content,
			String page, String position, int picOrder, String picHref,
			String picUrl) {		
		return this.bannerManager.add(userId,title,content,page,position,picOrder,picHref,picUrl);
	}

	public boolean del(Integer id) {		
		return this.bannerManager.del(id);
	}

	public boolean edit(Integer id, String title, String content, String page,
			String position, int picOrder, String picHref, String picUrl) {
		return this.bannerManager.edit(id,title,content,page,position,picOrder,picHref,picUrl);
	}

	public int getListCount(String page, String position) {
		return this.bannerManager.getListCount(page, position);
	}

	
	
}