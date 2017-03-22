package org.bees.biz.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bees.biz.persistence.manager.FriendLinkManager;
import org.bees.biz.persistence.model.FriendLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendLinkService {
	
	@Autowired
	private FriendLinkManager friendLinkManager;
	
	
	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(FriendLinkService.class);

	public FriendLink getInfoById(Integer id) {		
		return this.friendLinkManager.getInfoById(id);
	}

	public List<FriendLink> getList(Integer start, Integer limit) {
		List<FriendLink> friendLinks=this.friendLinkManager.getList(start,limit);
		return friendLinks;
	}

	public boolean add(String title, String href,
			int orders) {		
		return this.friendLinkManager.add(title,href,orders);
	}

	public boolean del(Integer id) {		
		return this.friendLinkManager.del(id);
	}

	public boolean edit(Integer id, String title, String href,
			int orders) {
		return this.friendLinkManager.edit(id,title,href,orders);
	}

	public int getListCount() {
		return this.friendLinkManager.getListCount();
	}

	
	
}