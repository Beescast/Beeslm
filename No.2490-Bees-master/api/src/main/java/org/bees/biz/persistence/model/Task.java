package org.bees.biz.persistence.model;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column task.id
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column task.cat_id
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	private Integer catId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column task.type
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	private Byte type;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column task.title
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	private String title;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column task.price
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	private String price;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column task.num
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	private Integer num;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column task.illustration
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	private String illustration;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column task.status
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	private Byte status;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column task.sign_num
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	private Integer signNum;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column task.bid_num
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	private Integer bidNum;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column task.add_time
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	private Date addTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column task.update_time
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	private Date updateTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column task.del_flag
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	private Byte delFlag;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table task
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column task.id
	 * @return  the value of task.id
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column task.id
	 * @param id  the value for task.id
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column task.cat_id
	 * @return  the value of task.cat_id
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	public Integer getCatId() {
		return catId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column task.cat_id
	 * @param catId  the value for task.cat_id
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column task.type
	 * @return  the value of task.type
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	public Byte getType() {
		return type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column task.type
	 * @param type  the value for task.type
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	public void setType(Byte type) {
		this.type = type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column task.title
	 * @return  the value of task.title
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column task.title
	 * @param title  the value for task.title
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column task.price
	 * @return  the value of task.price
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column task.price
	 * @param price  the value for task.price
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column task.num
	 * @return  the value of task.num
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	public Integer getNum() {
		return num;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column task.num
	 * @param num  the value for task.num
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	public void setNum(Integer num) {
		this.num = num;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column task.illustration
	 * @return  the value of task.illustration
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	public String getIllustration() {
		return illustration;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column task.illustration
	 * @param illustration  the value for task.illustration
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	public void setIllustration(String illustration) {
		this.illustration = illustration;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column task.status
	 * @return  the value of task.status
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	public Byte getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column task.status
	 * @param status  the value for task.status
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	public void setStatus(Byte status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column task.sign_num
	 * @return  the value of task.sign_num
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	public Integer getSignNum() {
		return signNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column task.sign_num
	 * @param signNum  the value for task.sign_num
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	public void setSignNum(Integer signNum) {
		this.signNum = signNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column task.bid_num
	 * @return  the value of task.bid_num
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	public Integer getBidNum() {
		return bidNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column task.bid_num
	 * @param bidNum  the value for task.bid_num
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	public void setBidNum(Integer bidNum) {
		this.bidNum = bidNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column task.add_time
	 * @return  the value of task.add_time
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	public Date getAddTime() {
		return addTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column task.add_time
	 * @param addTime  the value for task.add_time
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column task.update_time
	 * @return  the value of task.update_time
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column task.update_time
	 * @param updateTime  the value for task.update_time
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column task.del_flag
	 * @return  the value of task.del_flag
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	public Byte getDelFlag() {
		return delFlag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column task.del_flag
	 * @param delFlag  the value for task.del_flag
	 * @mbggenerated  Wed Aug 24 16:35:56 GMT+08:00 2016
	 */
	public void setDelFlag(Byte delFlag) {
		this.delFlag = delFlag;
	}
}