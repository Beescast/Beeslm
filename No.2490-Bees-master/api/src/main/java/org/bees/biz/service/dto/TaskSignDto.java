package org.bees.biz.service.dto;

import java.io.Serializable;
import java.util.Date;

public class TaskSignDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5465959385861122927L;
	
	private Integer id;
	
	private Integer uid;
	
	private String nickname;
	
	private String truename;
	
	private String mobile;
	
	private String plat;
	
	private String liveRoom;
	
	private Date addTime;
	
	private String singlePrice;
	
	private Date bidTime;
	
	private Byte bidStatus;
	
	private Byte payStatus;
	
	private Integer taskId;
	
	private Integer catId;
	
	private Byte type;
	
	private String title;
	
	private Date payTime;
	
	private int opId;
	
	private String opName;
	
	private Date opTime;

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
	 * @return the truename
	 */
	public String getTruename() {
		return truename;
	}

	/**
	 * @param truename the truename to set
	 */
	public void setTruename(String truename) {
		this.truename = truename;
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
	 * @return the plat
	 */
	public String getPlat() {
		return plat;
	}

	/**
	 * @param plat the plat to set
	 */
	public void setPlat(String plat) {
		this.plat = plat;
	}

	/**
	 * @return the liveRoom
	 */
	public String getLiveRoom() {
		return liveRoom;
	}

	/**
	 * @param liveRoom the liveRoom to set
	 */
	public void setLiveRoom(String liveRoom) {
		this.liveRoom = liveRoom;
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
	 * @return the singlePrice
	 */
	public String getSinglePrice() {
		return singlePrice;
	}

	/**
	 * @param singlePrice the singlePrice to set
	 */
	public void setSinglePrice(String singlePrice) {
		this.singlePrice = singlePrice;
	}

	/**
	 * @return the bidTime
	 */
	public Date getBidTime() {
		return bidTime;
	}

	/**
	 * @param bidTime the bidTime to set
	 */
	public void setBidTime(Date bidTime) {
		this.bidTime = bidTime;
	}

	/**
	 * @return the bidStatus
	 */
	public Byte getBidStatus() {
		return bidStatus;
	}

	/**
	 * @param bidStatus the bidStatus to set
	 */
	public void setBidStatus(Byte bidStatus) {
		this.bidStatus = bidStatus;
	}

	/**
	 * @return the taskId
	 */
	public Integer getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	/**
	 * @return the catId
	 */
	public Integer getCatId() {
		return catId;
	}

	/**
	 * @param catId the catId to set
	 */
	public void setCatId(Integer catId) {
		this.catId = catId;
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
	 * @return the opId
	 */
	public int getOpId() {
		return opId;
	}

	/**
	 * @param opId the opId to set
	 */
	public void setOpId(int opId) {
		this.opId = opId;
	}

	/**
	 * @return the opName
	 */
	public String getOpName() {
		return opName;
	}

	/**
	 * @param string the opName to set
	 */
	public void setOpName(String string) {
		this.opName = string;
	}

	/**
	 * @return the opTime
	 */
	public Date getOpTime() {
		return opTime;
	}

	/**
	 * @param opTime the opTime to set
	 */
	public void setOpTime(Date opTime) {
		this.opTime = opTime;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TaskSignDto [id=" + id + ", uid=" + uid + ", nickname="
				+ nickname + ", truename=" + truename + ", mobile=" + mobile
				+ ", plat=" + plat + ", liveRoom=" + liveRoom + ", addTime="
				+ addTime + ", singlePrice=" + singlePrice + ", bidTime="
				+ bidTime + ", bidStatus=" + bidStatus + ", payStatus="
				+ payStatus + ", taskId=" + taskId + ", catId=" + catId
				+ ", type=" + type + ", title=" + title + ", payTime="
				+ payTime + ", opId=" + opId + ", opName=" + opName
				+ ", opTime=" + opTime + "]";
	}

	
	
	
}