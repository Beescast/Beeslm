package org.bees.biz.service.dto;

import java.io.Serializable;
import java.util.Date;

public class MarketDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5465959385861122927L;

	private Integer id;
	private Integer uid;
	private String name;
	private Date dates;
	private Integer times;
	private String shows;
	private String click;
	private String visitArea;
	private String newVisit;
	private String oldVisit;
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
	 * @return the show
	 */
	public String getShows() {
		return shows;
	}
	/**
	 * @param show the show to set
	 */
	public void setShows(String shows) {
		this.shows = shows;
	}
	/**
	 * @return the click
	 */
	public String getClick() {
		return click;
	}
	/**
	 * @param click the click to set
	 */
	public void setClick(String click) {
		this.click = click;
	}
	/**
	 * @return the visitArea
	 */
	public String getVisitArea() {
		return visitArea;
	}
	/**
	 * @param visitArea the visitArea to set
	 */
	public void setVisitArea(String visitArea) {
		this.visitArea = visitArea;
	}
	/**
	 * @return the newVisit
	 */
	public String getNewVisit() {
		return newVisit;
	}
	/**
	 * @param newVisit the newVisit to set
	 */
	public void setNewVisit(String newVisit) {
		this.newVisit = newVisit;
	}
	/**
	 * @return the oldVisit
	 */
	public String getOldVisit() {
		return oldVisit;
	}
	/**
	 * @param oldVisit the oldVisit to set
	 */
	public void setOldVisit(String oldVisit) {
		this.oldVisit = oldVisit;
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
		return "MarketDto [id=" + id + ", uid=" + uid + ", name=" + name
				+ ", dates=" + dates + ", times=" + times + ", shows=" + shows
				+ ", click=" + click + ", visitArea=" + visitArea
				+ ", newVisit=" + newVisit + ", oldVisit=" + oldVisit
				+ ", addTime=" + addTime + "]";
	}
	
	
}