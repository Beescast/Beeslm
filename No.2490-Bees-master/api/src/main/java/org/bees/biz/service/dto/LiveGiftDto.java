package org.bees.biz.service.dto;

import java.io.Serializable;
import java.util.Date;

public class LiveGiftDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5465959385861122927L;

	private Integer id;
	
	private Integer uid;
	
	private String username;
	
	private String name;
	
	private Date dates;
	
	private Integer times;
	
	private Integer num;
	
	private String money;
	
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LiveGiftDto [id=" + id + ", uid=" + uid + ", username="
				+ username + ", name=" + name + ", dates=" + dates + ", times="
				+ times + ", num=" + num + ", money=" + money + ", addTime="
				+ addTime + "]";
	}

	
	
	
}