package org.bees.biz.service.dto;

import java.io.Serializable;
import java.util.Date;

public class LiveDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5465959385861122927L;

	private Integer id;
	
	private Integer uid;
	
	private String name;
	
	private Date dates;
	
	private Integer times;
	
	private Integer focus;
	
	private Integer popular;
	
	private Integer giftNum;
	
	private String giftTotal;
	
	private Date addTime;

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
	 * @return the dates
	 */
	public Date getDates() {
		return dates;
	}

	/**
	 * @param dates the dates to set
	 */
	public void setDates(Date dates) {
		this.dates = dates;
	}

	/**
	 * @return the times
	 */
	public Integer getTimes() {
		return times;
	}

	/**
	 * @param times the times to set
	 */
	public void setTimes(Integer times) {
		this.times = times;
	}

	/**
	 * @return the focus
	 */
	public Integer getFocus() {
		return focus;
	}

	/**
	 * @param focus the focus to set
	 */
	public void setFocus(Integer focus) {
		this.focus = focus;
	}

	/**
	 * @return the popular
	 */
	public Integer getPopular() {
		return popular;
	}

	/**
	 * @param popular the popular to set
	 */
	public void setPopular(Integer popular) {
		this.popular = popular;
	}

	/**
	 * @return the giftNum
	 */
	public Integer getGiftNum() {
		return giftNum;
	}

	/**
	 * @param giftNum the giftNum to set
	 */
	public void setGiftNum(Integer giftNum) {
		this.giftNum = giftNum;
	}

	/**
	 * @return the giftTotal
	 */
	public String getGiftTotal() {
		return giftTotal;
	}

	/**
	 * @param giftTotal the giftTotal to set
	 */
	public void setGiftTotal(String giftTotal) {
		this.giftTotal = giftTotal;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LiveDto [id=" + id + ", uid=" + uid + ", name=" + name
				+ ", dates=" + dates + ", times=" + times + ", focus=" + focus
				+ ", popular=" + popular + ", giftNum=" + giftNum
				+ ", giftTotal=" + giftTotal + ", addTime=" + addTime + "]";
	}

	
	
}