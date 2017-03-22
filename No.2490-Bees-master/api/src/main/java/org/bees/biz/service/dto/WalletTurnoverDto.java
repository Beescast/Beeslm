package org.bees.biz.service.dto;

import java.io.Serializable;
import java.util.Date;

import org.bees.utils.Const;

public class WalletTurnoverDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5465959385861122927L;

	private Integer id;
	
	private Integer uid;
	
	private String businessTypeText;
	
	private Byte businessType;
	
	private String money;
	
	private String payTypeText;
	
	private Byte payType;
	
	private String currentBalance;
	
	private String businessNo;
	
	private Date addTime;
	
	private String mobile;

	private String nickname;
	
	

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
	 * @return the businessTypeText
	 */
	public String getBusinessTypeText() {
		return businessTypeText;
	}

	/**
	 * @param businessTypeText the businessTypeText to set
	 */
	public void setBusinessTypeText(String businessTypeText) {
		this.businessTypeText = businessTypeText;
	}

	/**
	 * @return the businessType
	 */
	public Byte getBusinessType() {
		return businessType;
	}

	/**
	 * @param businessType the businessType to set
	 */
	public void setBusinessType(Byte businessType) {
		this.businessType = businessType;
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
	 * @return the payTypeText
	 */
	public String getPayTypeText() {
		return payTypeText;
	}

	/**
	 * @param payTypeText the payTypeText to set
	 */
	public void setPayTypeText(String payTypeText) {
		this.payTypeText = payTypeText;
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
	 * @return the currentBalance
	 */
	public String getCurrentBalance() {
		return currentBalance;
	}

	/**
	 * @param currentBalance the currentBalance to set
	 */
	public void setCurrentBalance(String currentBalance) {
		this.currentBalance = currentBalance;
	}

	/**
	 * @return the businessNo
	 */
	public String getBusinessNo() {
		return businessNo;
	}

	/**
	 * @param businessNo the businessNo to set
	 */
	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
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
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WalletTurnoverDto [id=" + id + ", uid=" + uid
				+ ", businessTypeText=" + businessTypeText + ", businessType="
				+ businessType + ", money=" + money + ", payTypeText="
				+ payTypeText + ", payType=" + payType + ", currentBalance="
				+ currentBalance + ", businessNo=" + businessNo + ", addTime="
				+ addTime + ", mobile=" + mobile + ", nickname=" + nickname
				+ "]";
	}

	public void setBusiness(Byte type) {
		
		if(type.intValue()==Const.BUSINESS_TYPE_ONE){
			this.businessTypeText= "充值";
		}else if (type.intValue()==Const.BUSINESS_TYPE_TWO) {
			this.businessTypeText= "提现";
		}else if (type.intValue()==Const.BUSINESS_TYPE_THREE) {
			this.businessTypeText= "购买套餐";
		}else if (type.intValue()==Const.BUSINESS_TYPE_FOUR) {
			this.businessTypeText= "任务收益";
		}else if(type.intValue()==Const.BUSINESS_TYPE_FIVE){
			this.businessTypeText="提现不通过";
		}
	}
	
	public void setPay(Byte type) {
		if(type.intValue()==Const.PAY_TYPE_ONE){
			this.payTypeText= "+";
		}else if (type.intValue()==Const.PAY_TYPE_TWO) {
			this.payTypeText= "-";
		}
	}

}