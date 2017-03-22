package org.bees.biz.service.dto;

import java.io.Serializable;
import java.util.Date;

public class PackagesOrderDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5465959385861122927L;

	private Long id;
	
	private Integer uid;
	
	private String name;
	
	private String mobile;
	
	private String packageName;
	
	private String price;
	
	private String payTypeString;
	
	private Byte payType;
	
	private String type;
	
	private Byte status;
	
	private String statusString;

	private String comment;	

	private Date commentTime;
	
	private Date submitTime;
	
	private Date payTime;
	
	private Date startTime;
	
	private Date endTime;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @param packageName the packageName to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
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
	 * @return the payTypeString
	 */
	public String getPayTypeString() {
		return payTypeString;
	}

	/**
	 * @param payTypeString the payTypeString to set
	 */
	public void setPayTypeString(String payTypeString) {
		this.payTypeString = payTypeString;
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the stats
	 */
	public Byte getStatus() {
		return status;
	}

	/**
	 * @param stats the stats to set
	 */
	public void setStatus(Byte status) {
		this.status = status;
	}

	/**
	 * @return the statusString
	 */
	public String getStatusString() {
		return statusString;
	}

	/**
	 * @param statusString the statusString to set
	 */
	public void setStatusString(String statusString) {
		this.statusString = statusString;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the commentTime
	 */
	public Date getCommentTime() {
		return commentTime;
	}

	/**
	 * @param commentTime the commentTime to set
	 */
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	/**
	 * @return the submitTime
	 */
	public Date getSubmitTime() {
		return submitTime;
	}

	/**
	 * @param submitTime the submitTime to set
	 */
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	/**
	 * @return the payTime
	 */
	public Date getPayTime() {
		return payTime;
	}

	/**
	 * @param payTime the payTime to set
	 */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PackagesOrderDto [id=" + id + ", uid=" + uid + ", name=" + name
				+ ", mobile=" + mobile + ", packageName=" + packageName
				+ ", price=" + price + ", payTypeString=" + payTypeString
				+ ", payType=" + payType + ", type=" + type + ", status="
				+ status + ", statusString=" + statusString + ", comment="
				+ comment + ", commentTime=" + commentTime + ", submitTime="
				+ submitTime + ", payTime=" + payTime + ", startTime="
				+ startTime + ", endTime=" + endTime + "]";
	}
	
	

	public void setPay(Byte payType) {
		if(payType.intValue()==0){
			this.payTypeString= "钱包";
		}else if (payType.intValue()==1) {
			this.payTypeString= "支付宝";
		}else if (payType.intValue()==2) {
			this.payTypeString= "微信";
		}else if(payType.intValue()==-1){
			this.payTypeString="未支付";
		}
	}

	public void setStat(Byte stats) {
		if(stats.intValue()==0){
			this.statusString= "待付款";
		}else if (stats.intValue()==1) {
			this.statusString= "待启动";
		}else if (stats.intValue()==2) {
			this.statusString= "上线中";
		}else if (stats.intValue()==3) {
			this.statusString= "已结束";
		}else if (stats.intValue()==4) {
			this.statusString= "已评价";
		}else if (stats.intValue()==9) {
			this.statusString= "已取消";
		}
	}
	
	
}