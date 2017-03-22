package org.bees.biz.service.dto;

import java.io.Serializable;

import org.bees.utils.Const;

public class TaskDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5465959385861122927L;

	private Integer id;
	
	private String title;
	
	private String price;
	
	private Integer num;
	
	private String illustration;
	
	private String startTime;
	
	private String statusText;
	
	private String payText;
	
	private Byte payStatus;
	
	private Byte type;
	
	private String typeText;
	
	private int sign;
	
	private int catId;
	
	private String catName;

	

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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the num
	 */
	public Integer getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(Integer num) {
		this.num = num;
	}

	/**
	 * @return the illustration
	 */
	public String getIllustration() {
		return illustration;
	}

	/**
	 * @param illustration the illustration to set
	 */
	public void setIllustration(String illustration) {
		this.illustration = illustration;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the statusText
	 */
	public String getStatusText() {
		return statusText;
	}

	/**
	 * @param statusText the statusText to set
	 */
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	/**
	 * @return the payText
	 */
	public String getPayText() {
		return payText;
	}

	/**
	 * @param payText the payText to set
	 */
	public void setPayText(String payText) {
		this.payText = payText;
	}

	/**
	 * @return the payStatus
	 */
	public Byte getPayStatus() {
		return payStatus;
	}

	/**
	 * @param payStatus the payStatus to set
	 */
	public void setPayStatus(Byte payStatus) {
		this.payStatus = payStatus;
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
	 * @return the typeText
	 */
	public String getTypeText() {
		return typeText;
	}

	/**
	 * @param typeText the typeText to set
	 */
	public void setTypeText(String typeText) {
		this.typeText = typeText;
	}

	/**
	 * @return the sign
	 */
	public int getSign() {
		return sign;
	}

	/**
	 * @param sign the sign to set
	 */
	public void setSign(int sign) {
		this.sign = sign;
	}

	/**
	 * @return the catId
	 */
	public int getCatId() {
		return catId;
	}

	/**
	 * @param catId the catId to set
	 */
	public void setCatId(int catId) {
		this.catId = catId;
	}

	/**
	 * @return the catName
	 */
	public String getCatName() {
		return catName;
	}

	/**
	 * @param catName the catName to set
	 */
	public void setCatName(String catName) {
		this.catName = catName;
	}
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TaskDto [id=" + id + ", title=" + title + ", price=" + price
				+ ", num=" + num + ", illustration=" + illustration
				+ ", startTime=" + startTime + ", statusText=" + statusText
				+ ", payText=" + payText + ", payStatus=" + payStatus
				+ ", type=" + type + ", typeText=" + typeText + ", sign="
				+ sign + ", catId=" + catId + ", catName=" + catName + "]";
	}

	public void setStatus(Byte status) {
		if(status.intValue()==Const.TASK_STATUS_ZERO.intValue()){
			this.statusText= "招募中";
		}else if (status.intValue()==Const.TASK_STATUS_ONE.intValue()) {
			this.statusText= "招募完成";
		}		
	}
	
	public void setPayStatusText(Byte status) {
		this.payStatus=status;
		if(status.intValue()==0){
			this.payText= "待完成";
		}else if (status.intValue()==1) {
			this.payText= "待付款";
		}else if (status.intValue()==2) {
			this.payText= "已完成";
		}else if (status.intValue()==3) {
			this.payText= "未完成";
		}		
	}
	
	public void setTypeText(Byte type) {
		if(type.intValue()==Const.TASK_TYPE_NOMAL.intValue()){
			this.typeText= "普通任务";
		}else if (type.intValue()==Const.TASK_TYPE_SENIOR.intValue()) {
			this.typeText= "高级任务";
		}		
	}
	
}