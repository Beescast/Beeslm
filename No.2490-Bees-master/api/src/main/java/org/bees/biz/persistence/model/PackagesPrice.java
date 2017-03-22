package org.bees.biz.persistence.model;

import java.io.Serializable;
import java.util.Date;

public class PackagesPrice implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column packages_price.id
     *
     * @mbggenerated Sun Jul 31 00:22:21 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column packages_price.package_id
     *
     * @mbggenerated Sun Jul 31 00:22:21 CST 2016
     */
    private Integer packageId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column packages_price.name
     *
     * @mbggenerated Sun Jul 31 00:22:21 CST 2016
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column packages_price.price
     *
     * @mbggenerated Sun Jul 31 00:22:21 CST 2016
     */
    private String price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column packages_price.add_time
     *
     * @mbggenerated Sun Jul 31 00:22:21 CST 2016
     */
    private Date addTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column packages_price.update_time
     *
     * @mbggenerated Sun Jul 31 00:22:21 CST 2016
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column packages_price.del_flag
     *
     * @mbggenerated Sun Jul 31 00:22:21 CST 2016
     */
    private Byte delFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table packages_price
     *
     * @mbggenerated Sun Jul 31 00:22:21 CST 2016
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column packages_price.id
     *
     * @return the value of packages_price.id
     *
     * @mbggenerated Sun Jul 31 00:22:21 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column packages_price.id
     *
     * @param id the value for packages_price.id
     *
     * @mbggenerated Sun Jul 31 00:22:21 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column packages_price.package_id
     *
     * @return the value of packages_price.package_id
     *
     * @mbggenerated Sun Jul 31 00:22:21 CST 2016
     */
    public Integer getPackageId() {
        return packageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column packages_price.package_id
     *
     * @param packageId the value for packages_price.package_id
     *
     * @mbggenerated Sun Jul 31 00:22:21 CST 2016
     */
    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column packages_price.name
     *
     * @return the value of packages_price.name
     *
     * @mbggenerated Sun Jul 31 00:22:21 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column packages_price.name
     *
     * @param name the value for packages_price.name
     *
     * @mbggenerated Sun Jul 31 00:22:21 CST 2016
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column packages_price.price
     *
     * @return the value of packages_price.price
     *
     * @mbggenerated Sun Jul 31 00:22:21 CST 2016
     */
    public String getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column packages_price.price
     *
     * @param price the value for packages_price.price
     *
     * @mbggenerated Sun Jul 31 00:22:21 CST 2016
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column packages_price.add_time
     *
     * @return the value of packages_price.add_time
     *
     * @mbggenerated Sun Jul 31 00:22:21 CST 2016
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column packages_price.add_time
     *
     * @param addTime the value for packages_price.add_time
     *
     * @mbggenerated Sun Jul 31 00:22:21 CST 2016
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column packages_price.update_time
     *
     * @return the value of packages_price.update_time
     *
     * @mbggenerated Sun Jul 31 00:22:21 CST 2016
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column packages_price.update_time
     *
     * @param updateTime the value for packages_price.update_time
     *
     * @mbggenerated Sun Jul 31 00:22:21 CST 2016
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column packages_price.del_flag
     *
     * @return the value of packages_price.del_flag
     *
     * @mbggenerated Sun Jul 31 00:22:21 CST 2016
     */
    public Byte getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column packages_price.del_flag
     *
     * @param delFlag the value for packages_price.del_flag
     *
     * @mbggenerated Sun Jul 31 00:22:21 CST 2016
     */
    public void setDelFlag(Byte delFlag) {
        this.delFlag = delFlag;
    }
}