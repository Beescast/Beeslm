package org.bees.biz.persistence.model;

import java.io.Serializable;
import java.util.Date;

public class Live implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column live.id
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column live.uid
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	private Integer uid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column live.dates
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	private Date dates;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column live.times
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	private Integer times;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column live.focus
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	private Integer focus;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column live.popular
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	private Integer popular;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column live.gift_num
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	private Integer giftNum;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column live.gift_total
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	private String giftTotal;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column live.add_time
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	private Date addTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column live.update_time
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	private Date updateTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column live.del_flag
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	private Byte delFlag;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table live
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column live.id
	 * @return  the value of live.id
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column live.id
	 * @param id  the value for live.id
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column live.uid
	 * @return  the value of live.uid
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public Integer getUid() {
		return uid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column live.uid
	 * @param uid  the value for live.uid
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column live.dates
	 * @return  the value of live.dates
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public Date getDates() {
		return dates;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column live.dates
	 * @param dates  the value for live.dates
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public void setDates(Date dates) {
		this.dates = dates;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column live.times
	 * @return  the value of live.times
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public Integer getTimes() {
		return times;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column live.times
	 * @param times  the value for live.times
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public void setTimes(Integer times) {
		this.times = times;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column live.focus
	 * @return  the value of live.focus
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public Integer getFocus() {
		return focus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column live.focus
	 * @param focus  the value for live.focus
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public void setFocus(Integer focus) {
		this.focus = focus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column live.popular
	 * @return  the value of live.popular
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public Integer getPopular() {
		return popular;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column live.popular
	 * @param popular  the value for live.popular
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public void setPopular(Integer popular) {
		this.popular = popular;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column live.gift_num
	 * @return  the value of live.gift_num
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public Integer getGiftNum() {
		return giftNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column live.gift_num
	 * @param giftNum  the value for live.gift_num
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public void setGiftNum(Integer giftNum) {
		this.giftNum = giftNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column live.gift_total
	 * @return  the value of live.gift_total
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public String getGiftTotal() {
		return giftTotal;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column live.gift_total
	 * @param giftTotal  the value for live.gift_total
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public void setGiftTotal(String giftTotal) {
		this.giftTotal = giftTotal;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column live.add_time
	 * @return  the value of live.add_time
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public Date getAddTime() {
		return addTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column live.add_time
	 * @param addTime  the value for live.add_time
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column live.update_time
	 * @return  the value of live.update_time
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column live.update_time
	 * @param updateTime  the value for live.update_time
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column live.del_flag
	 * @return  the value of live.del_flag
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public Byte getDelFlag() {
		return delFlag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column live.del_flag
	 * @param delFlag  the value for live.del_flag
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public void setDelFlag(Byte delFlag) {
		this.delFlag = delFlag;
	}
	
}