package org.bees.biz.persistence.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.bees.biz.persistence.model.LiveGift;
import org.bees.biz.persistence.model.LiveGiftExample;

public interface LiveGiftMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	int countByExample(LiveGiftExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	int deleteByExample(LiveGiftExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	int insert(LiveGift record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	int insertSelective(LiveGift record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	List<LiveGift> selectByExample(LiveGiftExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	LiveGift selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	int updateByExampleSelective(@Param("record") LiveGift record,
			@Param("example") LiveGiftExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	int updateByExample(@Param("record") LiveGift record,
			@Param("example") LiveGiftExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	int updateByPrimaryKeySelective(LiveGift record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	int updateByPrimaryKey(LiveGift record);
}