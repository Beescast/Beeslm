package org.bees.biz.persistence.model;

import java.io.Serializable;
import java.util.Date;

public class OrderLog implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_log.id
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_log.uid
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    private Integer uid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_log.type
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    private Byte type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_log.pay_type
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    private Byte payType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_log.income_type
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    private Byte incomeType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_log.money
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    private String money;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_log.business_id
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    private String businessId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_log.order_id
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    private String orderId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_log.add_time
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    private Date addTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_log.update_time
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_log.del_flag
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    private Byte delFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table order_log
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_log.id
     *
     * @return the value of order_log.id
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_log.id
     *
     * @param id the value for order_log.id
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_log.uid
     *
     * @return the value of order_log.uid
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_log.uid
     *
     * @param uid the value for order_log.uid
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_log.type
     *
     * @return the value of order_log.type
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    public Byte getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_log.type
     *
     * @param type the value for order_log.type
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_log.pay_type
     *
     * @return the value of order_log.pay_type
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    public Byte getPayType() {
        return payType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_log.pay_type
     *
     * @param payType the value for order_log.pay_type
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_log.income_type
     *
     * @return the value of order_log.income_type
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    public Byte getIncomeType() {
        return incomeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_log.income_type
     *
     * @param incomeType the value for order_log.income_type
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    public void setIncomeType(Byte incomeType) {
        this.incomeType = incomeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_log.money
     *
     * @return the value of order_log.money
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    public String getMoney() {
        return money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_log.money
     *
     * @param money the value for order_log.money
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    public void setMoney(String money) {
        this.money = money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_log.business_id
     *
     * @return the value of order_log.business_id
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    public String getBusinessId() {
        return businessId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_log.business_id
     *
     * @param businessId the value for order_log.business_id
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_log.order_id
     *
     * @return the value of order_log.order_id
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_log.order_id
     *
     * @param orderId the value for order_log.order_id
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_log.add_time
     *
     * @return the value of order_log.add_time
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_log.add_time
     *
     * @param addTime the value for order_log.add_time
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_log.update_time
     *
     * @return the value of order_log.update_time
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_log.update_time
     *
     * @param updateTime the value for order_log.update_time
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_log.del_flag
     *
     * @return the value of order_log.del_flag
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    public Byte getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_log.del_flag
     *
     * @param delFlag the value for order_log.del_flag
     *
     * @mbggenerated Thu Aug 25 04:28:06 CST 2016
     */
    public void setDelFlag(Byte delFlag) {
        this.delFlag = delFlag;
    }
}