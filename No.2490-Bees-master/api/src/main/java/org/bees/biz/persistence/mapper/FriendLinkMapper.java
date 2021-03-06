package org.bees.biz.persistence.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.bees.biz.persistence.model.FriendLink;
import org.bees.biz.persistence.model.FriendLinkExample;

public interface FriendLinkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend_link
     *
     * @mbggenerated Tue Aug 30 11:11:37 GMT+08:00 2016
     */
    int countByExample(FriendLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend_link
     *
     * @mbggenerated Tue Aug 30 11:11:37 GMT+08:00 2016
     */
    int deleteByExample(FriendLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend_link
     *
     * @mbggenerated Tue Aug 30 11:11:37 GMT+08:00 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend_link
     *
     * @mbggenerated Tue Aug 30 11:11:37 GMT+08:00 2016
     */
    int insert(FriendLink record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend_link
     *
     * @mbggenerated Tue Aug 30 11:11:37 GMT+08:00 2016
     */
    int insertSelective(FriendLink record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend_link
     *
     * @mbggenerated Tue Aug 30 11:11:37 GMT+08:00 2016
     */
    List<FriendLink> selectByExample(FriendLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend_link
     *
     * @mbggenerated Tue Aug 30 11:11:37 GMT+08:00 2016
     */
    FriendLink selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend_link
     *
     * @mbggenerated Tue Aug 30 11:11:37 GMT+08:00 2016
     */
    int updateByExampleSelective(@Param("record") FriendLink record, @Param("example") FriendLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend_link
     *
     * @mbggenerated Tue Aug 30 11:11:37 GMT+08:00 2016
     */
    int updateByExample(@Param("record") FriendLink record, @Param("example") FriendLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend_link
     *
     * @mbggenerated Tue Aug 30 11:11:37 GMT+08:00 2016
     */
    int updateByPrimaryKeySelective(FriendLink record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend_link
     *
     * @mbggenerated Tue Aug 30 11:11:37 GMT+08:00 2016
     */
    int updateByPrimaryKey(FriendLink record);
}