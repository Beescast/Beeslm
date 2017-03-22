package org.bees.biz.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bees.biz.persistence.manager.LiveGiftManager;
import org.bees.biz.persistence.manager.LiveManager;
import org.bees.biz.persistence.manager.LivePeopleManager;
import org.bees.biz.persistence.manager.MarketManager;
import org.bees.biz.persistence.manager.UserManager;
import org.bees.biz.persistence.model.Live;
import org.bees.biz.persistence.model.LiveGift;
import org.bees.biz.persistence.model.LivePeople;
import org.bees.biz.persistence.model.Market;
import org.bees.biz.persistence.model.User;
import org.bees.biz.service.dto.LiveDto;
import org.bees.biz.service.dto.LiveGiftDto;
import org.bees.biz.service.dto.LivePeopleDto;
import org.bees.biz.service.dto.MarketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projects.commons.utils.ValidateUtils;

@Service
public class LiveService {
	
	@Autowired
	private LiveManager liveManager;
	
	@Autowired
	private LivePeopleManager livePeopleManager;
	
	@Autowired
	private LiveGiftManager liveGiftManager;
	
	@Autowired
	private MarketManager marketManager;
	
	@Autowired
	private UserManager userManager;
	
	
	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(LiveService.class);


	public List<MarketDto> market(Integer uid, Integer id, String name,
			Date startDate, Date endDate, Integer startTime, Integer endTime,
			Integer start, Integer limit) {
		List<Integer> uids=new LinkedList<Integer>();
		if(!ValidateUtils.isNull(name)||!ValidateUtils.isNull(uid)){
			List<User> users=this.userManager.adminList(null,null, name, null, null, null, null,null, uid, null,null, null, null);
			if(!ValidateUtils.isNull(users)){
				for (User user : users) {
					uids.add(user.getId());
				}
			}else{
				uids.add(-1);
			}			
		}
		List<Market> markets=this.marketManager.lists(uids,id,startDate,endDate,startTime,endTime,start,limit);
		List<MarketDto> dtos=new LinkedList<MarketDto>();
		for (Market market : markets) {
			MarketDto dto=new MarketDto();
			try {				
				PropertyUtils.copyProperties(dto, market);
				User user=this.userManager.getInfoById(market.getUid());
				dto.setName(user.getTruename());
				
			} catch (IllegalAccessException | InvocationTargetException
					| NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dtos.add(dto);
		}
		return dtos;
	}


	public List<LivePeopleDto> people(Integer uid, Integer id, String name,
			Date startDate, Date endDate, Integer start, Integer limit) {
		List<Integer> uids=new LinkedList<Integer>();
		if(!ValidateUtils.isNull(name)||!ValidateUtils.isNull(uid)){
			List<User> users=this.userManager.adminList(null,null, name, null, null, null, null,null, uid, null, null,null, null);
			if(!ValidateUtils.isNull(users)){
				for (User user : users) {
					uids.add(user.getId());
				}
			}else{
				uids.add(-1);
			}			
		}
		List<LivePeople> peoples=this.livePeopleManager.lists(uids,id,startDate,endDate,start,limit);
		List<LivePeopleDto> dtos=new LinkedList<LivePeopleDto>();
		for (LivePeople people : peoples) {
			LivePeopleDto dto=new LivePeopleDto();
			try {				
				PropertyUtils.copyProperties(dto, people);
				User user=this.userManager.getInfoById(people.getUid());
				dto.setName(user.getTruename());				
			} catch (IllegalAccessException | InvocationTargetException
					| NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dtos.add(dto);
		}
		return dtos;
	}


	public List<LiveDto> live(Integer uid, Integer id, String name,
			Date startDate, Date endDate, Integer startTime, Integer endTime,
			Integer start, Integer limit) {
		List<Integer> uids=new LinkedList<Integer>();
		if(!ValidateUtils.isNull(name)||!ValidateUtils.isNull(uid)){
			List<User> users=this.userManager.adminList(null,null, name, null, null, null, null,null, uid, null,null, null, null);
			if(!ValidateUtils.isNull(users)){
				for (User user : users) {
					uids.add(user.getId());
				}
			}else{
				uids.add(-1);
			}			
		}
		
		List<Live> lives=this.liveManager.lists(uids,id,startDate,endDate,startTime,endTime,start,limit);
		List<LiveDto> dtos=new LinkedList<LiveDto>();
		for (Live live : lives) {
			LiveDto dto=new LiveDto();
			try {				
				PropertyUtils.copyProperties(dto, live);
				User user=this.userManager.getInfoById(live.getUid());
				dto.setName(user.getTruename());
				
			} catch (IllegalAccessException | InvocationTargetException
					| NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dtos.add(dto);
		}
		return dtos;
	}


	public int marketCount(Integer uid, Integer id, String name,
			Date startDate, Date endDate, Integer startTime, Integer endTime) {
		List<Integer> uids=new LinkedList<Integer>();
		if(!ValidateUtils.isNull(name)||!ValidateUtils.isNull(uid)){
			List<User> users=this.userManager.adminList(null,null, name, null, null, null, null,null, uid, null,null, null, null);
			if(!ValidateUtils.isNull(users)){
				for (User user : users) {
					uids.add(user.getId());
				}
			}else{
				uids.add(-1);
			}			
		}
		return this.marketManager.listsCount(uids,id,startDate,endDate,startTime,endTime);
	}


	public int liveCount(Integer uid, Integer id, String name, Date startDate,
			Date endDate, Integer startTime, Integer endTime) {
		List<Integer> uids=new LinkedList<Integer>();
		if(!ValidateUtils.isNull(name)||!ValidateUtils.isNull(uid)){
			List<User> users=this.userManager.adminList(null,null, name, null, null, null, null,null, uid, null, null,null, null);
			if(!ValidateUtils.isNull(users)){
				for (User user : users) {
					uids.add(user.getId());
				}
			}else{
				uids.add(-1);
			}			
		}
		return this.liveManager.listsCount(uids,id,startDate,endDate,startTime,endTime);
	}


	public int peopleCount(Integer uid, Integer id, String name,
			Date startDate, Date endDate) {
		List<Integer> uids=new LinkedList<Integer>();
		if(!ValidateUtils.isNull(name)||!ValidateUtils.isNull(uid)){
			List<User> users=this.userManager.adminList(null,null, name, null, null, null, null,null, uid, null, null,null, null);
			if(!ValidateUtils.isNull(users)){
				for (User user : users) {
					uids.add(user.getId());
				}
			}else{
				uids.add(-1);
			}			
		}
		return this.livePeopleManager.listsCount(uids,id,startDate,endDate);
	}


	public List<LiveGiftDto> gift(Integer id, Date startDate, Date endDate,
			Integer startTime, Integer endTime, Integer start, Integer limit) {
		
		List<LiveGift> gifts=this.liveGiftManager.lists(id,startDate,endDate,startTime,endTime,start,limit);
		List<LiveGiftDto> dtos=new LinkedList<LiveGiftDto>();
		for (LiveGift gift : gifts) {
			LiveGiftDto dto=new LiveGiftDto();
			try {				
				PropertyUtils.copyProperties(dto, gift);
				Live live=this.liveManager.getInfoById(gift.getLiveId());
				User user=this.userManager.getInfoById(live.getUid());
				dto.setUsername(user.getTruename());
				dto.setUid(user.getId());
			} catch (IllegalAccessException | InvocationTargetException
					| NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dtos.add(dto);
		}
		return dtos;
	}


	public int giftCount(Integer id, Date startDate, Date endDate,
			Integer startTime, Integer endTime) {
		return this.liveGiftManager.listsCount(id,startDate,endDate,startTime,endTime);
	}


	
}