package org.bees.biz.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bees.biz.persistence.manager.ConfigManager;
import org.bees.biz.persistence.manager.UserManager;
import org.bees.biz.persistence.model.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {
	
	@Autowired
	private ConfigManager configManager;	
		
	@Autowired
	private UserManager userManager;
	
	

	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(ConfigService.class);

	public Config getInfoById(Integer id) {		
		return this.configManager.getInfoById(id);
	}

	public List<Config> getList(Integer limit, Integer start) {
		return this.configManager.getList(start,limit);
	}

	public boolean add(Integer userId, String title, String content, Byte status) {

		return this.configManager.add(userId,title,content,status);
	}

	public boolean del(Integer id) {
		return this.configManager.del(id);
	}

	public boolean edit(Integer id, String value) {
		return this.configManager.edit(id,value);
	}

	public Integer getListCount() {
		return this.configManager.getListCount();
	}

	
	
}