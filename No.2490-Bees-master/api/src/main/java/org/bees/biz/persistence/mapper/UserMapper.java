package org.bees.biz.persistence.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.bees.biz.persistence.model.User;
import org.bees.biz.persistence.model.UserExample;

public interface UserMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated  Mon Nov 14 10:02:57 CST 2016
	 */
	int countByExample(UserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated  Mon Nov 14 10:02:57 CST 2016
	 */
	int deleteByExample(UserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated  Mon Nov 14 10:02:57 CST 2016
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated  Mon Nov 14 10:02:57 CST 2016
	 */
	int insert(User record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated  Mon Nov 14 10:02:57 CST 2016
	 */
	int insertSelective(User record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated  Mon Nov 14 10:02:57 CST 2016
	 */
	List<User> selectByExample(UserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated  Mon Nov 14 10:02:57 CST 2016
	 */
	User selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated  Mon Nov 14 10:02:57 CST 2016
	 */
	int updateByExampleSelective(@Param("record") User record,
			@Param("example") UserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated  Mon Nov 14 10:02:57 CST 2016
	 */
	int updateByExample(@Param("record") User record,
			@Param("example") UserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated  Mon Nov 14 10:02:57 CST 2016
	 */
	int updateByPrimaryKeySelective(User record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated  Mon Nov 14 10:02:57 CST 2016
	 */
	int updateByPrimaryKey(User record);
}