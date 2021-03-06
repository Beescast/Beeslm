package org.bees.biz.persistence.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class LiveGiftExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	protected List<Criteria> oredCriteria;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	protected int limitStart;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	protected int limitEnd;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	protected String groupByClause;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public LiveGiftExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public void setLimitStart(int limitStart) {
		this.limitStart = limitStart;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public int getLimitStart() {
		return limitStart;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public void setLimitEnd(int limitEnd) {
		this.limitEnd = limitEnd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public int getLimitEnd() {
		return limitEnd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public void setGroupByClause(String groupByClause) {
		this.groupByClause = groupByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public String getGroupByClause() {
		return groupByClause;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		protected void addCriterionForJDBCDate(String condition, Date value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			addCriterion(condition, new java.sql.Date(value.getTime()),
					property);
		}

		protected void addCriterionForJDBCDate(String condition,
				List<Date> values, String property) {
			if (values == null || values.size() == 0) {
				throw new RuntimeException("Value list for " + property
						+ " cannot be null or empty");
			}
			List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
			Iterator<Date> iter = values.iterator();
			while (iter.hasNext()) {
				dateList.add(new java.sql.Date(iter.next().getTime()));
			}
			addCriterion(condition, dateList, property);
		}

		protected void addCriterionForJDBCDate(String condition, Date value1,
				Date value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			addCriterion(condition, new java.sql.Date(value1.getTime()),
					new java.sql.Date(value2.getTime()), property);
		}

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andLiveIdIsNull() {
			addCriterion("live_id is null");
			return (Criteria) this;
		}

		public Criteria andLiveIdIsNotNull() {
			addCriterion("live_id is not null");
			return (Criteria) this;
		}

		public Criteria andLiveIdEqualTo(Integer value) {
			addCriterion("live_id =", value, "liveId");
			return (Criteria) this;
		}

		public Criteria andLiveIdNotEqualTo(Integer value) {
			addCriterion("live_id <>", value, "liveId");
			return (Criteria) this;
		}

		public Criteria andLiveIdGreaterThan(Integer value) {
			addCriterion("live_id >", value, "liveId");
			return (Criteria) this;
		}

		public Criteria andLiveIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("live_id >=", value, "liveId");
			return (Criteria) this;
		}

		public Criteria andLiveIdLessThan(Integer value) {
			addCriterion("live_id <", value, "liveId");
			return (Criteria) this;
		}

		public Criteria andLiveIdLessThanOrEqualTo(Integer value) {
			addCriterion("live_id <=", value, "liveId");
			return (Criteria) this;
		}

		public Criteria andLiveIdIn(List<Integer> values) {
			addCriterion("live_id in", values, "liveId");
			return (Criteria) this;
		}

		public Criteria andLiveIdNotIn(List<Integer> values) {
			addCriterion("live_id not in", values, "liveId");
			return (Criteria) this;
		}

		public Criteria andLiveIdBetween(Integer value1, Integer value2) {
			addCriterion("live_id between", value1, value2, "liveId");
			return (Criteria) this;
		}

		public Criteria andLiveIdNotBetween(Integer value1, Integer value2) {
			addCriterion("live_id not between", value1, value2, "liveId");
			return (Criteria) this;
		}

		public Criteria andNameIsNull() {
			addCriterion("name is null");
			return (Criteria) this;
		}

		public Criteria andNameIsNotNull() {
			addCriterion("name is not null");
			return (Criteria) this;
		}

		public Criteria andNameEqualTo(String value) {
			addCriterion("name =", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotEqualTo(String value) {
			addCriterion("name <>", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThan(String value) {
			addCriterion("name >", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThanOrEqualTo(String value) {
			addCriterion("name >=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThan(String value) {
			addCriterion("name <", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThanOrEqualTo(String value) {
			addCriterion("name <=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLike(String value) {
			addCriterion("name like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotLike(String value) {
			addCriterion("name not like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameIn(List<String> values) {
			addCriterion("name in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotIn(List<String> values) {
			addCriterion("name not in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameBetween(String value1, String value2) {
			addCriterion("name between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotBetween(String value1, String value2) {
			addCriterion("name not between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andNumIsNull() {
			addCriterion("num is null");
			return (Criteria) this;
		}

		public Criteria andNumIsNotNull() {
			addCriterion("num is not null");
			return (Criteria) this;
		}

		public Criteria andNumEqualTo(Integer value) {
			addCriterion("num =", value, "num");
			return (Criteria) this;
		}

		public Criteria andNumNotEqualTo(Integer value) {
			addCriterion("num <>", value, "num");
			return (Criteria) this;
		}

		public Criteria andNumGreaterThan(Integer value) {
			addCriterion("num >", value, "num");
			return (Criteria) this;
		}

		public Criteria andNumGreaterThanOrEqualTo(Integer value) {
			addCriterion("num >=", value, "num");
			return (Criteria) this;
		}

		public Criteria andNumLessThan(Integer value) {
			addCriterion("num <", value, "num");
			return (Criteria) this;
		}

		public Criteria andNumLessThanOrEqualTo(Integer value) {
			addCriterion("num <=", value, "num");
			return (Criteria) this;
		}

		public Criteria andNumIn(List<Integer> values) {
			addCriterion("num in", values, "num");
			return (Criteria) this;
		}

		public Criteria andNumNotIn(List<Integer> values) {
			addCriterion("num not in", values, "num");
			return (Criteria) this;
		}

		public Criteria andNumBetween(Integer value1, Integer value2) {
			addCriterion("num between", value1, value2, "num");
			return (Criteria) this;
		}

		public Criteria andNumNotBetween(Integer value1, Integer value2) {
			addCriterion("num not between", value1, value2, "num");
			return (Criteria) this;
		}

		public Criteria andMoneyIsNull() {
			addCriterion("money is null");
			return (Criteria) this;
		}

		public Criteria andMoneyIsNotNull() {
			addCriterion("money is not null");
			return (Criteria) this;
		}

		public Criteria andMoneyEqualTo(String value) {
			addCriterion("money =", value, "money");
			return (Criteria) this;
		}

		public Criteria andMoneyNotEqualTo(String value) {
			addCriterion("money <>", value, "money");
			return (Criteria) this;
		}

		public Criteria andMoneyGreaterThan(String value) {
			addCriterion("money >", value, "money");
			return (Criteria) this;
		}

		public Criteria andMoneyGreaterThanOrEqualTo(String value) {
			addCriterion("money >=", value, "money");
			return (Criteria) this;
		}

		public Criteria andMoneyLessThan(String value) {
			addCriterion("money <", value, "money");
			return (Criteria) this;
		}

		public Criteria andMoneyLessThanOrEqualTo(String value) {
			addCriterion("money <=", value, "money");
			return (Criteria) this;
		}

		public Criteria andMoneyLike(String value) {
			addCriterion("money like", value, "money");
			return (Criteria) this;
		}

		public Criteria andMoneyNotLike(String value) {
			addCriterion("money not like", value, "money");
			return (Criteria) this;
		}

		public Criteria andMoneyIn(List<String> values) {
			addCriterion("money in", values, "money");
			return (Criteria) this;
		}

		public Criteria andMoneyNotIn(List<String> values) {
			addCriterion("money not in", values, "money");
			return (Criteria) this;
		}

		public Criteria andMoneyBetween(String value1, String value2) {
			addCriterion("money between", value1, value2, "money");
			return (Criteria) this;
		}

		public Criteria andMoneyNotBetween(String value1, String value2) {
			addCriterion("money not between", value1, value2, "money");
			return (Criteria) this;
		}

		public Criteria andDatesIsNull() {
			addCriterion("dates is null");
			return (Criteria) this;
		}

		public Criteria andDatesIsNotNull() {
			addCriterion("dates is not null");
			return (Criteria) this;
		}

		public Criteria andDatesEqualTo(Date value) {
			addCriterionForJDBCDate("dates =", value, "dates");
			return (Criteria) this;
		}

		public Criteria andDatesNotEqualTo(Date value) {
			addCriterionForJDBCDate("dates <>", value, "dates");
			return (Criteria) this;
		}

		public Criteria andDatesGreaterThan(Date value) {
			addCriterionForJDBCDate("dates >", value, "dates");
			return (Criteria) this;
		}

		public Criteria andDatesGreaterThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("dates >=", value, "dates");
			return (Criteria) this;
		}

		public Criteria andDatesLessThan(Date value) {
			addCriterionForJDBCDate("dates <", value, "dates");
			return (Criteria) this;
		}

		public Criteria andDatesLessThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("dates <=", value, "dates");
			return (Criteria) this;
		}

		public Criteria andDatesIn(List<Date> values) {
			addCriterionForJDBCDate("dates in", values, "dates");
			return (Criteria) this;
		}

		public Criteria andDatesNotIn(List<Date> values) {
			addCriterionForJDBCDate("dates not in", values, "dates");
			return (Criteria) this;
		}

		public Criteria andDatesBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("dates between", value1, value2, "dates");
			return (Criteria) this;
		}

		public Criteria andDatesNotBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("dates not between", value1, value2,
					"dates");
			return (Criteria) this;
		}

		public Criteria andTimesIsNull() {
			addCriterion("times is null");
			return (Criteria) this;
		}

		public Criteria andTimesIsNotNull() {
			addCriterion("times is not null");
			return (Criteria) this;
		}

		public Criteria andTimesEqualTo(Integer value) {
			addCriterion("times =", value, "times");
			return (Criteria) this;
		}

		public Criteria andTimesNotEqualTo(Integer value) {
			addCriterion("times <>", value, "times");
			return (Criteria) this;
		}

		public Criteria andTimesGreaterThan(Integer value) {
			addCriterion("times >", value, "times");
			return (Criteria) this;
		}

		public Criteria andTimesGreaterThanOrEqualTo(Integer value) {
			addCriterion("times >=", value, "times");
			return (Criteria) this;
		}

		public Criteria andTimesLessThan(Integer value) {
			addCriterion("times <", value, "times");
			return (Criteria) this;
		}

		public Criteria andTimesLessThanOrEqualTo(Integer value) {
			addCriterion("times <=", value, "times");
			return (Criteria) this;
		}

		public Criteria andTimesIn(List<Integer> values) {
			addCriterion("times in", values, "times");
			return (Criteria) this;
		}

		public Criteria andTimesNotIn(List<Integer> values) {
			addCriterion("times not in", values, "times");
			return (Criteria) this;
		}

		public Criteria andTimesBetween(Integer value1, Integer value2) {
			addCriterion("times between", value1, value2, "times");
			return (Criteria) this;
		}

		public Criteria andTimesNotBetween(Integer value1, Integer value2) {
			addCriterion("times not between", value1, value2, "times");
			return (Criteria) this;
		}

		public Criteria andAddTimeIsNull() {
			addCriterion("add_time is null");
			return (Criteria) this;
		}

		public Criteria andAddTimeIsNotNull() {
			addCriterion("add_time is not null");
			return (Criteria) this;
		}

		public Criteria andAddTimeEqualTo(Date value) {
			addCriterion("add_time =", value, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeNotEqualTo(Date value) {
			addCriterion("add_time <>", value, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeGreaterThan(Date value) {
			addCriterion("add_time >", value, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("add_time >=", value, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeLessThan(Date value) {
			addCriterion("add_time <", value, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeLessThanOrEqualTo(Date value) {
			addCriterion("add_time <=", value, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeIn(List<Date> values) {
			addCriterion("add_time in", values, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeNotIn(List<Date> values) {
			addCriterion("add_time not in", values, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeBetween(Date value1, Date value2) {
			addCriterion("add_time between", value1, value2, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeNotBetween(Date value1, Date value2) {
			addCriterion("add_time not between", value1, value2, "addTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeIsNull() {
			addCriterion("update_time is null");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeIsNotNull() {
			addCriterion("update_time is not null");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeEqualTo(Date value) {
			addCriterion("update_time =", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeNotEqualTo(Date value) {
			addCriterion("update_time <>", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeGreaterThan(Date value) {
			addCriterion("update_time >", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("update_time >=", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeLessThan(Date value) {
			addCriterion("update_time <", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
			addCriterion("update_time <=", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeIn(List<Date> values) {
			addCriterion("update_time in", values, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeNotIn(List<Date> values) {
			addCriterion("update_time not in", values, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeBetween(Date value1, Date value2) {
			addCriterion("update_time between", value1, value2, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
			addCriterion("update_time not between", value1, value2,
					"updateTime");
			return (Criteria) this;
		}

		public Criteria andDelFlagIsNull() {
			addCriterion("del_flag is null");
			return (Criteria) this;
		}

		public Criteria andDelFlagIsNotNull() {
			addCriterion("del_flag is not null");
			return (Criteria) this;
		}

		public Criteria andDelFlagEqualTo(Byte value) {
			addCriterion("del_flag =", value, "delFlag");
			return (Criteria) this;
		}

		public Criteria andDelFlagNotEqualTo(Byte value) {
			addCriterion("del_flag <>", value, "delFlag");
			return (Criteria) this;
		}

		public Criteria andDelFlagGreaterThan(Byte value) {
			addCriterion("del_flag >", value, "delFlag");
			return (Criteria) this;
		}

		public Criteria andDelFlagGreaterThanOrEqualTo(Byte value) {
			addCriterion("del_flag >=", value, "delFlag");
			return (Criteria) this;
		}

		public Criteria andDelFlagLessThan(Byte value) {
			addCriterion("del_flag <", value, "delFlag");
			return (Criteria) this;
		}

		public Criteria andDelFlagLessThanOrEqualTo(Byte value) {
			addCriterion("del_flag <=", value, "delFlag");
			return (Criteria) this;
		}

		public Criteria andDelFlagIn(List<Byte> values) {
			addCriterion("del_flag in", values, "delFlag");
			return (Criteria) this;
		}

		public Criteria andDelFlagNotIn(List<Byte> values) {
			addCriterion("del_flag not in", values, "delFlag");
			return (Criteria) this;
		}

		public Criteria andDelFlagBetween(Byte value1, Byte value2) {
			addCriterion("del_flag between", value1, value2, "delFlag");
			return (Criteria) this;
		}

		public Criteria andDelFlagNotBetween(Byte value1, Byte value2) {
			addCriterion("del_flag not between", value1, value2, "delFlag");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table live_gift
	 * @mbggenerated  Thu Nov 10 17:12:03 GMT+08:00 2016
	 */
	public static class Criterion {
		private String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue,
				String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table live_gift
     *
     * @mbggenerated do_not_delete_during_merge Sat Sep 03 03:56:36 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}