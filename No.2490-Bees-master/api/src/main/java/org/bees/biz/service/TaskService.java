package org.bees.biz.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bees.biz.persistence.manager.AdminManager;
import org.bees.biz.persistence.manager.NoticeManager;
import org.bees.biz.persistence.manager.TaskCategoryManager;
import org.bees.biz.persistence.manager.TaskManager;
import org.bees.biz.persistence.manager.TaskSignManager;
import org.bees.biz.persistence.manager.TaskSignPicManager;
import org.bees.biz.persistence.manager.UserManager;
import org.bees.biz.persistence.manager.WalletTurnoverManager;
import org.bees.biz.persistence.model.Admin;
import org.bees.biz.persistence.model.Task;
import org.bees.biz.persistence.model.TaskCategory;
import org.bees.biz.persistence.model.TaskSign;
import org.bees.biz.persistence.model.TaskSignPic;
import org.bees.biz.persistence.model.TaskSignPicExample;
import org.bees.biz.persistence.model.TaskSignPicExample.Criteria;
import org.bees.biz.persistence.model.User;
import org.bees.biz.service.dto.TaskDto;
import org.bees.biz.service.dto.TaskSignDto;
import org.bees.utils.Const;
import org.bees.utils.SMSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projects.commons.exception.ServiceException;
import projects.commons.utils.ValidateUtils;
import projects.commons.utils.date.DateUtils;

@Service
public class TaskService {
	
	@Autowired
	private TaskManager taskManager;
	
	@Autowired
	private TaskSignManager taskSignManager;
	
	@Autowired
	private TaskSignPicManager taskSignPicManager;
	
	@Autowired
	private TaskCategoryManager taskCategoryManager;
	
	@Autowired
	private WalletTurnoverManager walletTurnoverManager;
		
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private NoticeManager noticeManager;
	
	@Autowired
	private AdminManager adminManager;	
	
	

	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(TaskService.class);

	public Task getInfoById(Integer id) {		
		return this.taskManager.getInfoById(id);
	}

	

	public boolean add(Integer userId, String title, Integer catId, Byte type, String price, Integer num, String illustration, Integer notice, Integer msg) {
		boolean res=this.taskManager.add(title,catId,type,price,num,illustration);
		if(!ValidateUtils.isNull(notice)&&notice.intValue()==1){
			this.noticeManager.add(userId, title+"任务已发布，请速速报名！", "", (byte) 1);
		}
		
		List<User> users=this.userManager.getTaskUser(catId);
		if(!ValidateUtils.isNull(users)&&users.size()>0){
			for (User user : users) {
				this.userManager.setTonji(user.getId(), user.getCatNum()+1, null, null, null);
				if(!ValidateUtils.isNull(msg)&&msg==1){
					SMSUtil.doPost(user.getMobile(), 10, String.valueOf(Double.valueOf(price)/Double.valueOf(num)), null);
				}
			}
		}
		return res; 
	}

	public boolean del(Integer id) {
		Task task =this.taskManager.getInfoById(id);
		List<User> users=this.userManager.getTaskUser(task.getCatId());
		if(!ValidateUtils.isNull(users)&&users.size()>0){
			for (User user : users) {
				this.userManager.setTonji(user.getId(), user.getCatNum()-1, null, null, null);
			}
		}
		return this.taskManager.del(id);
	}

	public boolean edit(Integer id, String title, Integer catId, Byte type, String price, Integer num, String illustration) {
		return this.taskManager.edit(id,title,catId,type,price,num,illustration);
	}



	public List<TaskDto> getList(Integer userId, Byte status, Byte sign,
			Integer catId,Byte bidStatus, Integer start, Integer limit) {
		//用户已报名的task
		List<Integer> taskIds=new LinkedList<Integer>();
		List<TaskSign> signs = null;
		if(!ValidateUtils.isNull(userId)&&userId>0){
			signs=this.taskSignManager.getListByUserId(userId,bidStatus);
			for (TaskSign taskSign : signs) {
				taskIds.add(taskSign.getTaskId());
			}
		}
		List<Integer> filterTasks=null;
		if(!ValidateUtils.isNull(sign)){
			filterTasks=taskIds;
		}
		List<Integer> catIds =new LinkedList<Integer>();
		if(!ValidateUtils.isNull(catId)&&catId==-1&&!ValidateUtils.isNull(userId)){
			User user=this.userManager.getInfoById(userId);
			String[] taskCats=user.getTaskCat().split(",");
			for (String cat : taskCats) {
				catIds.add(Integer.valueOf(cat));
			}
		}
		Byte type=Const.TASK_TYPE_NOMAL;
		if(!ValidateUtils.isNull(bidStatus))type=null;
		List<Task> tasks=this.taskManager.getList(filterTasks,status,sign,catId,catIds,type,null,start,limit);
		List<TaskCategory> categories=this.taskCategoryManager.getList(null, null,null);
		List<TaskDto> dtos=new LinkedList<TaskDto>();
		for (Task task : tasks) {
			TaskDto dto = new TaskDto();
			try {
				PropertyUtils.copyProperties(dto, task);
				dto.setStartTime(DateUtils.toDateFormat(task.getAddTime(), "yyyy-MM-dd HH:ss"));
				dto.setStatus(task.getStatus());
				dto.setTypeText(task.getType());
				if(taskIds.contains(task.getId())){
					dto.setSign(1);
				}
				for (TaskCategory cat : categories) {
					if(cat.getId()==task.getCatId()){
						dto.setCatName(cat.getName());
					}
				}
				if(!ValidateUtils.isNull(bidStatus)&&bidStatus.intValue()==1){
					for (TaskSign taskSign : signs) {
						if(taskSign.getTaskId()==task.getId()){
							dto.setPayStatusText(taskSign.getPayStatus());
							
						}
					}
				}
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dtos.add(dto);
		}
		return dtos;
	}



	public List<TaskCategory> getcatList(Integer start, Integer limit, Integer gt) {
		return this.taskCategoryManager.getList(start, limit,gt);
	}



	public boolean addCat(Integer userId, String name) {		
		return this.taskCategoryManager.add(name);
	}



	public boolean delCat(Integer id) {
		return this.taskCategoryManager.del(id);
	}



	public boolean editCat(Integer id, String name) {
		return this.taskCategoryManager.edit(id, name);
	}



	public List<Task> adminList(Integer id, String title, Byte status,
			Byte type, Integer catId, Date startDate, Date endDate,
			Integer start, Integer limit) {
		
		return this.taskManager.adminList(id,title,status,type,catId,startDate,endDate,start,limit);
	}



	public List<TaskSignDto> getSignList(Integer id, String title, Integer catId, Integer taskId, Byte bidStatus, Byte payStatus, Date startDate, Date endDate, String timeType, Integer start, Integer limit) {
		List<Integer> taskIds=new LinkedList<Integer>();
		if(!ValidateUtils.isNull(catId)||!ValidateUtils.isNull(title)){
			List<Task> tasks=this.taskManager.getList(null, null, null, catId, null,null,title, null, null);
			if(!ValidateUtils.isNull(tasks)){
				for (Task task : tasks) {
					taskIds.add(task.getId());
				}
			}
		}
		if(!ValidateUtils.isNull(taskId)){
			taskIds.add(taskId);
		}else{
			if(taskIds.size()==0){
				taskIds=null;
			}
		}
		
		List<TaskSign> taskSigns=this.taskSignManager.getList(id,taskIds,bidStatus,payStatus,startDate,endDate,timeType, start, limit);
		List<TaskSignDto> dtos=new LinkedList<TaskSignDto>();
		for (TaskSign taskSign : taskSigns) {
			
			TaskSignDto dto=new TaskSignDto();
			User user=this.userManager.getInfoById(taskSign.getUid());
			Task task=this.taskManager.getInfoById(taskSign.getTaskId());
			
			
			try {
				PropertyUtils.copyProperties(dto, user);
				PropertyUtils.copyProperties(dto, taskSign);
				PropertyUtils.copyProperties(dto, task);
				dto.setTaskId(task.getId());
				if(taskSign.getOpId()!=0){
					Admin admin=this.adminManager.getInfoById(taskSign.getOpId());
					dto.setOpName(admin.getName());
				}
				dto.setId(taskSign.getId());
				
			} catch (IllegalAccessException | InvocationTargetException
					| NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dtos.add(dto);
		}
		return dtos;
	}



	public boolean sign(Integer taskId, String uids) {
		String[] uidsString=uids.split(",");
		Task task=this.taskManager.getInfoById(taskId);
		if(task.getNum()-task.getBidNum()<uidsString.length){
			throw new ServiceException("中标人数超过总人数");
		}
		String price=String.valueOf(Double.valueOf(task.getPrice())/Double.valueOf(task.getNum()));
		List<Integer> uid=new LinkedList<Integer>();
		for (String string : uidsString) {
			uid.add(Integer.valueOf(string));
		}
		if(task.getType().intValue()==Const.TASK_TYPE_NOMAL){
			boolean res=this.taskSignManager.sign(taskId,uid,price);
			if(res){
				//统计
				for (Integer integer : uid) {
					User user=this.userManager.getInfoById(integer);
					this.userManager.setTonji(user.getId(), null, user.getSignNum()+1, null, null);
					SMSUtil.doPost(user.getMobile(), 7, price, null);
				}
			}
		}else{
			List<TaskSign> signs=this.taskSignManager.getByTaskUids(taskId, uid);
			if(ValidateUtils.isNull(signs)||signs.size()==0){
				for (Integer uidInt : uid) {
					this.taskSignManager.add(taskId, uidInt, price,Const.TASK_BID_YES);
					User user=this.userManager.getInfoById(uidInt);
					this.userManager.setTonji(user.getId(), null, user.getSignNum()+1, null, null);
					SMSUtil.doPost(user.getMobile(), 7, price, null);
				}
			}else{
				throw new ServiceException("该用户已报名："+signs.get(0).getUid());
			}
			
		}
		this.updateBidNum(taskId);
		return true;
	}



	private void updateBidNum(Integer taskId) {
		Integer count=this.taskSignManager.getNum(taskId,Const.BID_STATUS_BID);
		Task task=this.taskManager.getInfoById(taskId);
		task.setBidNum(count);
		if(task.getNum()==count){
			task.setStatus(Const.TASK_STATUS_ONE);
		}else{
			task.setStatus(Const.TASK_STATUS_ZERO);
		}
		this.taskManager.getDao().update(task);
	}
	
	private void updateSignNum(Integer taskId) {
		Integer count=this.taskSignManager.getNum(taskId,null);
		this.taskManager.updateNum(taskId,count,null);
	}



	public boolean addSign(Integer userId, Integer taskId) {
		Task task=this.taskManager.getInfoById(taskId);
		if(!ValidateUtils.isNull(task)&&task.getStatus().intValue()==Const.TASK_STATUS_ZERO.intValue()){
			TaskSign tasksign=this.taskSignManager.getByTaskUid(taskId,userId);
			if(ValidateUtils.isNull(tasksign)){
				this.taskSignManager.add(taskId, userId, String.valueOf(Double.valueOf(task.getPrice())/Double.valueOf(task.getNum())),null);
				this.updateSignNum(taskId);
				return true;
			}else{
				throw new ServiceException("您已报名");
			}
			
		}
		return false;
	}



	public boolean auth(Integer adminId, Integer id, Byte status, String reson) {
		TaskSign sign=this.taskSignManager.getInfoById(id);
		if(!ValidateUtils.isNull(sign)){
			if(sign.getBidStatus().intValue()==Const.BID_STATUS_BID.intValue()){
				sign.setOpId(adminId);
				sign.setOpTime(new Date());
				try {
					User user=this.userManager.getInfoById(sign.getUid());
					if(status.intValue()==Const.TASK_AUTH_YES.intValue()){
						//通过按钮将把交付状态改为已结束，
						//该用户的钱包余额加上本次赏金金额，
						//在该用户流水中加入一条赏金任务收益的记录，
						//发送一条任务完成的短信通知给用户，检查该任务中标的人数是否达到招募的人数要求，如果达到则该任务的状态为招募完成
						//用户总金额，完成任务总数
						sign.setPayStatus(Const.TASK_PAY_TWO);
						String currentBalence=String.valueOf(Double.valueOf(user.getBalance())+Double.valueOf(sign.getSinglePrice()));
						this.userManager.setMoney(sign.getUid(),currentBalence);
						this.walletTurnoverManager.add(sign.getUid(),new Byte("4"),new Byte("1"),sign.getSinglePrice(),currentBalence,sign.getTaskId()+"","","");
						userManager.setTonji(user.getId(),null,null,user.getFinishNum()+1,String.valueOf(Double.valueOf(sign.getSinglePrice())+Double.valueOf(user.getBalanceNum())));
						SMSUtil.doPost(user.getMobile(), 8, sign.getSinglePrice(), null);
					}else{
						//不通过按钮，必须添加不通过的理由，
						//把中标者的状态改为取消中标，交付状态改为未完成，发送一条任务未完成的通知给用户（含未通过的原因），
						//同时，如果当前任务的状态改为招募中
						sign.setBidStatus(Const.BID_STATUS_UNBID);
						sign.setPayStatus(Const.TASK_PAY_THREE);
						sign.setReason(reson);
						SMSUtil.doPost(user.getMobile(), 9, reson, null);
						Task task=this.taskManager.getInfoById(sign.getTaskId());
						this.userManager.setTonji(user.getId(), null, user.getSignNum()-1, null, null);
						if(task.getStatus().intValue()==Const.TASK_STATUS_ONE.intValue()){
							task.setStatus(Const.TASK_STATUS_ZERO);
							this.taskManager.getDao().update(task);
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.taskSignManager.getDao().update(sign);
				this.updateBidNum(sign.getTaskId());
				return true;
			}else{
				throw new ServiceException("该用户未中标");
			}
		}else{
			throw new ServiceException("错误的id");
		}
	}



	public int getListCount(Integer userId, Byte status, Byte sign,
			Integer catId, Byte bidStatus) {
		//用户已报名的task
		List<Integer> taskIds=new LinkedList<Integer>();
		if(!ValidateUtils.isNull(userId)&&userId>0){
			List<TaskSign> signs=this.taskSignManager.getListByUserId(userId,bidStatus);
			for (TaskSign taskSign : signs) {
				taskIds.add(taskSign.getTaskId());
			}			
		}
		List<Integer> catIds =new LinkedList<Integer>();
		if(!ValidateUtils.isNull(catId)&&catId==-1&&!ValidateUtils.isNull(userId)){
			User user=this.userManager.getInfoById(userId);
			String[] taskCats=user.getTaskCat().split(",");
			for (String cat : taskCats) {
				catIds.add(Integer.valueOf(cat));
			}
		}
		return this.taskManager.getListCount(taskIds,status,sign,catId,catIds);
	}



	public int adminListCount(Integer id, String title, Byte status,
			Byte type, Integer catId, Date startDate, Date endDate) {
		
		return this.taskManager.adminListCount(id, title, status, type, catId, startDate, endDate);
	}



	public int getSignListCount(Integer id, String title, Integer catId, Integer taskId, Byte bidStatus, Byte payStatus, Date startDate, Date endDate, String timeType) {
		List<Integer> taskIds=new LinkedList<Integer>();
		if(!ValidateUtils.isNull(catId)||!ValidateUtils.isNull(title)){
			List<Task> tasks=this.taskManager.getList(null, null, null, catId, null,null,title, null, null);
			if(!ValidateUtils.isNull(tasks)){
				for (Task task : tasks) {
					taskIds.add(task.getId());
				}
			}
		}
		if(!ValidateUtils.isNull(taskId)){
			taskIds.add(taskId);
		}else{
			if(taskIds.size()==0){
				taskIds=null;
			}
		}
		
		return this.taskSignManager.getSignListCount(id,taskIds,bidStatus,payStatus,startDate,endDate,timeType);
	}



	public boolean unsign(Integer id) {
		TaskSign taskSign=this.taskSignManager.getInfoById(id);
		taskSign.setBidStatus(Const.BID_STATUS_UNBID);
		taskSign.setPayStatus(Const.TASK_PAY_THREE);
		this.taskSignManager.getDao().update(taskSign);
		this.updateBidNum(taskSign.getTaskId());
		return true;
	}



	public boolean finish(Integer id) {
		Task task=this.taskManager.getInfoById(id);
		task.setStatus(Const.TASK_STATUS_ONE);
		int res=this.taskManager.getDao().update(task);
		if(res>0){
			return true;
		}
		return false;
	}



	public boolean finishTask(Integer userId, Integer taskId, String[] pics) {
		TaskSign sign=this.taskSignManager.getByTaskUid(taskId, userId);
		if(ValidateUtils.isNull(sign)){
			throw new ServiceException("您未中标些任务");
		}
		if(pics.length<3){
			throw new ServiceException("请至少上传3张图片");
		}
		sign.setPayStatus(new Byte("1"));
		this.taskSignManager.getDao().update(sign);
		for (String pic : pics) {
			TaskSignPic signPic=new TaskSignPic();
			signPic.setPicUrl(pic);
			signPic.setAddTime(new Date());
			signPic.setTaskId(taskId);
			signPic.setUserId(userId);
			this.taskSignPicManager.getDao().insert(signPic);
		}
		return true;
	}

	public List<TaskSignPic> getSignPicList(Integer taskId, Integer uid) {
		TaskSignPicExample example =new TaskSignPicExample();
		Criteria criteria=example.createCriteria();
		criteria.andTaskIdEqualTo(taskId);
		criteria.andUserIdEqualTo(uid);
		return this.taskSignPicManager.getDao().list(example);		
	}

	
	
}