package org.bees.biz.service.dto;

import java.io.Serializable;
import java.util.Date;

public class LivePeopleDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5465959385861122927L;

	private Integer id;
	
	private Integer uid;
	
	private String name;
	
	private Date dates;
	
	private String visitType;
	
	private Integer age;
	
	private Byte sex;
	
	private String focus;
	
	private Integer mostType;
	
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
	 * @return the visitType
	 */
	public String getVisitType() {
		return visitType;
	}

	/**
	 * @param visitType the visitType to set
	 */
	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}

	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @return the sex
	 */
	public Byte getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(Byte sex) {
		this.sex = sex;
	}

	/**
	 * @return the focus
	 */
	public String getFocus() {
		return focus;
	}

	/**
	 * @param focus the focus to set
	 */
	public void setFocus(String focus) {
		this.focus = focus;
	}

	/**
	 * @return the mostType
	 */
	public Integer getMostType() {
		return mostType;
	}

	/**
	 * @param mostType the mostType to set
	 */
	public void setMostType(Integer mostType) {
		this.mostType = mostType;
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
		return "LivePeopleDto [id=" + id + ", uid=" + uid + ", name=" + name
				+ ", dates=" + dates + ", visitType=" + visitType + ", age="
				+ age + ", sex=" + sex + ", focus=" + focus + ", mostType="
				+ mostType + ", addTime=" + addTime + "]";
	}

	
	
	
}