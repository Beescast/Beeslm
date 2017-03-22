package org.bees.biz.persistence.manager;

import java.util.Date;
import java.util.List;

import org.bees.biz.persistence.dao.BannerDao;
import org.bees.biz.persistence.model.Banner;
import org.bees.biz.persistence.model.BannerExample;
import org.bees.biz.persistence.model.BannerExample.Criteria;
import org.bees.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projects.commons.database.BaseManager;
import projects.commons.utils.ValidateUtils;

@Component
public class BannerManager extends BaseManager<BannerDao> {

	@Autowired
	private BannerDao dao;

	@Override
	public BannerDao getDao() {
		return dao;
	}

	public Banner getInfoById(Integer id) {
		BannerExample example = new BannerExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		crit.andIdEqualTo(id);
		return this.getDao().getByExample(example);
	}

	public List<Banner> getList(String page, String position, Integer start, Integer limit) {
		BannerExample example = new BannerExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		if(!ValidateUtils.isNull(start)){
			example.setLimitStart(start);
			example.setLimitEnd(limit);
		}
		if(!ValidateUtils.isNull(page)){
			crit.andPageEqualTo(page);
		}
		if(!ValidateUtils.isNull(position)){
			crit.andPositionEqualTo(position);
		}
		example.setOrderByClause("id desc");
		return this.getDao().list(example);
	}

	public boolean add(Integer userId, String title, String content,
			String page, String position, int picOrder, String picHref,
			String picUrl) {
		Banner banner=new Banner();
		banner.setPage(page);
		banner.setPosition(position);
		banner.setPicOrder(picOrder);
		banner.setPicHref(picHref);
		banner.setPicUrl(picUrl);
		banner.setTitle(title);
		banner.setContent(content);
		banner.setAddTime(new Date());
		int res=this.getDao().insert(banner);
		if(res>0){
			return true;
		}
		return false;
	}
	
	public boolean del(Integer id) {
		Banner banner=new Banner();
		banner.setId(id);
		banner.setDelFlag(Const.DEL_FLAG_DEL);		
		int res= this.getDao().update(banner);
		if(res>0){
			return true;
		}
		return false;
	}

	public boolean edit(Integer id, String title, String content, String page,
			String position, int picOrder, String picHref, String picUrl) {
		Banner banner=new Banner();
		banner.setId(id);
		banner.setPage(page);
		banner.setPosition(position);
		banner.setPicOrder(picOrder);
		banner.setPicHref(picHref);
		banner.setPicUrl(picUrl);
		banner.setTitle(title);
		banner.setContent(content);
		int res=this.getDao().update(banner);
		if(res>0){
			return true;
		}
		return false;
	}

	public int getListCount(String page, String position) {
		BannerExample example = new BannerExample();
		Criteria crit = example.createCriteria();
		crit.andDelFlagEqualTo(Const.DEL_FLAG_NOT_DEL);
		if(!ValidateUtils.isNull(page)){
			crit.andPageEqualTo(page);
		}
		if(!ValidateUtils.isNull(position)){
			crit.andPositionEqualTo(position);
		}
		return this.getDao().count(example);
	}
	


}