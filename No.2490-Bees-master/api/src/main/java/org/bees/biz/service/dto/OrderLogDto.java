package org.bees.biz.service.dto;

import java.io.Serializable;
import java.util.Date;

public class OrderLogDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5465959385861122927L;


	private Integer id;
	
	private Integer uid;

	private String mobile;

	private String nickname;

	private Byte type;

	private Byte payType;
		
	private Byte incomeType;
	
	private String money;
	
	private Date addTime;

    private String businessId;

    private String orderId;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the uid
	 */
	public Integer getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the type
	 */
	public Byte getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Byte type) {
		this.type = type;
	}

	/**
	 * @return the payType
	 */
	public Byte getPayType() {
		return payType;
	}

	/**
	 * @param payType the payType to set
	 */
	public void setPayType(Byte payType) {
		this.payType = payType;
	}

	/**
	 * @return the incomeType
	 */
	public Byte getIncomeType() {
		return incomeType;
	}

	/**
	 * @param incomeType the incomeType to set
	 */
	public void setIncomeType(Byte incomeType) {
		this.incomeType = incomeType;
	}

	/**
	 * @return the money
	 */
	public String getMoney() {
		return money;
	}

	/**
	 * @param money the money to set
	 */
	public void setMoney(String money) {
		this.money = money;
	}

	/**
	 * @return the addTime
	 */
	public Date getAddTime() {
		return addTime;
	}

	/**
	 * @param addTime the addTime to set
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	/**
	 * @return the businessId
	 */
	public String getBusinessId() {
		return businessId;
	}

	/**
	 * @param businessId the businessId to set
	 */
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderLogDto [id=" + id + ", uid=" + uid + ", mobile=" + mobile
				+ ", nickname=" + nickname + ", type=" + type + ", payType="
				+ payType + ", incomeType=" + incomeType + ", money=" + money
				+ ", addTime=" + addTime + ", businessId=" + businessId
				+ ", orderId=" + orderId + "]";
	}
    
    
    
}