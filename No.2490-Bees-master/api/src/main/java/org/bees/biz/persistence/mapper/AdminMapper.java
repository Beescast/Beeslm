package org.bees.biz.persistence.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.bees.biz.persistence.model.Admin;
import org.bees.biz.persistence.model.AdminExample;

public interface AdminMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Wed Aug 17 15:40:19 GMT+08:00 2016
     */
    int countByExample(AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Wed Aug 17 15:40:19 GMT+08:00 2016
     */
    int deleteByExample(AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Wed Aug 17 15:40:19 GMT+08:00 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Wed Aug 17 15:40:19 GMT+08:00 2016
     */
    int insert(Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Wed Aug 17 15:40:19 GMT+08:00 2016
     */
    int insertSelective(Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Wed Aug 17 15:40:19 GMT+08:00 2016
     */
    List<Admin> selectByExample(AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Wed Aug 17 15:40:19 GMT+08:00 2016
     */
    Admin selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Wed Aug 17 15:40:19 GMT+08:00 2016
     */
    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Wed Aug 17 15:40:19 GMT+08:00 2016
     */
    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Wed Aug 17 15:40:19 GMT+08:00 2016
     */
    int updateByPrimaryKeySelective(Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Wed Aug 17 15:40:19 GMT+08:00 2016
     */
    int updateByPrimaryKey(Admin record);
}