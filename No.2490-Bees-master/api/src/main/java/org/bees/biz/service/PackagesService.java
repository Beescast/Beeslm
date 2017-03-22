package org.bees.biz.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bees.biz.persistence.manager.PackagesManager;
import org.bees.biz.persistence.manager.PackagesOrderManager;
import org.bees.biz.persistence.manager.PackagesPriceManager;
import org.bees.biz.persistence.manager.UserManager;
import org.bees.biz.persistence.model.Packages;
import org.bees.biz.persistence.model.PackagesOrder;
import org.bees.biz.persistence.model.PackagesPrice;
import org.bees.biz.persistence.model.User;
import org.bees.biz.service.dto.IndexCommentDto;
import org.bees.biz.service.dto.PackagesCommentDto;
import org.bees.utils.Const;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projects.commons.exception.ServiceException;
import projects.commons.utils.ValidateUtils;

@Service
public class PackagesService {
	
	@Autowired
	private PackagesManager packagesManager;
	
	@Autowired
	private PackagesPriceManager packagesPriceManager;
	
	@Autowired
	private PackagesOrderManager packagesOrderManager;
	
	@Autowired
	private UserManager userManager;
	
	

	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(PackagesService.class);

	public Packages getInfoById(Integer id) {		
		return this.packagesManager.getInfoById(id);
	}

	public List<PackagesPrice> getPriceByPid(Integer id) {
		return this.packagesPriceManager.getPriceByPid(id);
	}

	public List<PackagesCommentDto> getCommentsByPid(Integer pid) {
		List<PackagesOrder> orders=this.packagesOrderManager.getOrders(0,null,null,null,null,null,pid,null,null);
		List<PackagesCommentDto> dtos=new LinkedList<PackagesCommentDto>();
		for (PackagesOrder order : orders) {
			PackagesCommentDto dto = new PackagesCommentDto();
			try {
				PropertyUtils.copyProperties(dto, order);
				User user=this.userManager.getInfoById(order.getUid());
				dto.setName(user.getNickname());
				dto.setAvatar(user.getAvatar());
				
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

	public List<IndexCommentDto> getCommentList(int start, int limit, Integer order) {
		List<PackagesOrder> orders=this.packagesOrderManager.getOrders(null,null,null,Const.ORDER_STATUS_FOUR,null,null,null,start,limit);
		List<IndexCommentDto> dtos=new LinkedList<IndexCommentDto>();
		for (PackagesOrder tempOrder : orders) {
			IndexCommentDto dto = new IndexCommentDto();
			try {
				PropertyUtils.copyProperties(dto, tempOrder);
				User user=this.userManager.getInfoById(tempOrder.getUid());
				dto.setName(user.getNickname());
				dto.setImgUrl(user.getAvatar());
				dto.setRoomNo(user.getLiveRoom());
				dto.setId(tempOrder.getId());
				dto.setDesc(user.getDescription());
				dto.setIntro(tempOrder.getComment());
				dto.setUid(user.getId());
				String[] names=tempOrder.getPackageName().split("/");
				dto.setType(names[names.length-1]);
				
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

	public List<Packages> getPackagesList(int start, int limit, Integer order) {
		return this.packagesManager.getList(start,limit);
	}

	@SuppressWarnings("unchecked")
	public boolean add(String name, String content, String titleOne,
			String titleTwo, String titleThree, String pic, String prices) {
		int id=this.packagesManager.add(name,content,titleOne,titleTwo,titleThree,pic);
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, String>> priecLists = null;
		try {
			priecLists = objectMapper.readValue(prices, List.class);
			for (Map<String, String> map : priecLists) {
				this.packagesPriceManager.add(id,map.get("name"),map.get("price"));
			}		
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return true;
	}

	public boolean del(Integer id) {
		return this.packagesManager.del(id);
	}

	@SuppressWarnings("unchecked")
	public boolean edit(Integer id, String name, String content,
			String titleOne, String titleTwo, String titleThree, String pic, String prices) {
		this.packagesManager.edit(id,name,content,titleOne,titleTwo,titleThree,pic);
		List<PackagesPrice> oldPrices=this.packagesPriceManager.getPriceByPid(id);
		List<Integer> ids = new LinkedList<Integer>();
		if(!ValidateUtils.isNull(oldPrices)){
			for (PackagesPrice packagesPrice : oldPrices) {
				ids.add(packagesPrice.getId());
			}
		}
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, String>> priecLists = null;
		try {
			priecLists = objectMapper.readValue(prices, List.class);
			for (Map<String, String> map : priecLists) {
				if(ValidateUtils.isNull(map.get("id"))){
					this.packagesPriceManager.add(id,map.get("name"),map.get("price"));
				}else{
					this.packagesPriceManager.edit(Integer.valueOf(map.get("id")),map.get("name"),map.get("price"));
					ids.remove(ids.indexOf(Integer.valueOf(map.get("id"))));
				}
			}
			if(!ids.isEmpty()){
				this.packagesPriceManager.dels(ids);
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return false;
	}

	public PackagesPrice getPriceByid(Integer id) { 
		return this.packagesPriceManager.getInfoById(id);
	}

	public int getCommentCount(Integer pid) {

		return this.packagesOrderManager.getCommentCount(pid);
	}

	public boolean delPrice(Integer id) {
		List<Integer> ids=new LinkedList<Integer>();
		ids.add(id);
		this.packagesPriceManager.dels(ids);
		return true;
	}

	public boolean addComment(Integer userId, Long orderId, String comment) {
		PackagesOrder order=this.packagesOrderManager.getInfoById(orderId);
		if(order.getUid().intValue()!=userId.intValue()){
			throw new ServiceException("评论失效");
		}else{
			boolean res=this.packagesOrderManager.addComment(orderId,comment);
			return res;
		}
		
	}
	
	


}