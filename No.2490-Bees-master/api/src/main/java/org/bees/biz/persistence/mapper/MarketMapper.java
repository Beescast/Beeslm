package org.bees.biz.persistence.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.bees.biz.persistence.model.Market;
import org.bees.biz.persistence.model.MarketExample;

public interface MarketMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table market
	 * @mbggenerated  Wed Nov 02 10:11:27 GMT+08:00 2016
	 */
	int countByExample(MarketExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table market
	 * @mbggenerated  Wed Nov 02 10:11:27 GMT+08:00 2016
	 */
	int deleteByExample(MarketExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table market
	 * @mbggenerated  Wed Nov 02 10:11:27 GMT+08:00 2016
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table market
	 * @mbggenerated  Wed Nov 02 10:11:27 GMT+08:00 2016
	 */
	int insert(Market record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table market
	 * @mbggenerated  Wed Nov 02 10:11:27 GMT+08:00 2016
	 */
	int insertSelective(Market record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table market
	 * @mbggenerated  Wed Nov 02 10:11:27 GMT+08:00 2016
	 */
	List<Market> selectByExample(MarketExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table market
	 * @mbggenerated  Wed Nov 02 10:11:27 GMT+08:00 2016
	 */
	Market selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table market
	 * @mbggenerated  Wed Nov 02 10:11:27 GMT+08:00 2016
	 */
	int updateByExampleSelective(@Param("record") Market record,
			@Param("example") MarketExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table market
	 * @mbggenerated  Wed Nov 02 10:11:27 GMT+08:00 2016
	 */
	int updateByExample(@Param("record") Market record,
			@Param("example") MarketExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table market
	 * @mbggenerated  Wed Nov 02 10:11:27 GMT+08:00 2016
	 */
	int updateByPrimaryKeySelective(Market record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table market
	 * @mbggenerated  Wed Nov 02 10:11:27 GMT+08:00 2016
	 */
	int updateByPrimaryKey(Market record);
}