package org.bees.biz.service;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bees.biz.persistence.manager.LeaveMsgManager;
import org.bees.biz.persistence.manager.UserManager;
import org.bees.biz.persistence.model.LeaveMsg;
import org.bees.biz.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projects.commons.utils.ValidateUtils;

@Service
public class LeaveMsgService {
	
	@Autowired
	private LeaveMsgManager leaveMsgManager;
	
	@Autowired
	private UserManager userManager;
	
	
	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(LeaveMsgService.class);

	public LeaveMsg getInfoById(Integer id) {		
		return this.leaveMsgManager.getInfoById(id);
	}

	public List<LeaveMsg> getList(Integer uid, String name, String content, Integer start, Integer limit) {
		List<Integer> uids=null;
		if(!ValidateUtils.isNull(uid)||!ValidateUtils.isNull(name)){
			List<User> users=this.userManager.adminList(null, name,null, null, null, null, null,null, uid, null,null,null, null);
			uids=new LinkedList<Integer>();
			if(!ValidateUtils.isNull(users)){				
				for (User user : users) {
					uids.add(user.getId());
				}
			}else{
				uids.add(-1);
			}
		}
		
		List<LeaveMsg> leaveMsgs=this.leaveMsgManager.getList(uids,content,start,limit);
		for (LeaveMsg leaveMsg : leaveMsgs) {
			User user=this.userManager.getInfoById(leaveMsg.getUid());
			leaveMsg.setName(user.getNickname());
		}
		return leaveMsgs;
	}

	public boolean add(Integer userId, String content) {		
		return this.leaveMsgManager.add(userId,content);
	}

	public int getListCount(Integer uid, String name, String content) {
		List<Integer> uids=null;
		if(!ValidateUtils.isNull(uid)||!ValidateUtils.isNull(name)){
			List<User> users=this.userManager.adminList(null, name,null, null, null, null, null,null, uid, null,null, null,null);
			uids=new LinkedList<Integer>();
			if(!ValidateUtils.isNull(users)){				
				for (User user : users) {
					uids.add(user.getId());
				}
			}else{
				uids.add(-1);
			}
		}
		return this.leaveMsgManager.getListCount(uids,content);
	}

	
	
}