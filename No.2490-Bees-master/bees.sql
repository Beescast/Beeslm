/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.7.14 : Database - bees
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bees` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bees`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '登录账号',
  `passwd` varchar(64) NOT NULL DEFAULT '' COMMENT '登录密码',
  `nickname` varchar(64) NOT NULL DEFAULT '' COMMENT '用户名称',
  `ops` varchar(64) NOT NULL DEFAULT '' COMMENT '权限',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Table structure for table `banner` */

DROP TABLE IF EXISTS `banner`;

CREATE TABLE `banner` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `page` varchar(32) NOT NULL DEFAULT '' COMMENT '页面名称',
  `position` varchar(64) NOT NULL DEFAULT '' COMMENT '图片位置',
  `pic_order` int(11) NOT NULL DEFAULT '0' COMMENT '显示顺序',
  `title` varchar(512) DEFAULT '' COMMENT '标题',
  `content` varchar(2048) DEFAULT '' COMMENT '内容',
  `pic_href` varchar(64) NOT NULL DEFAULT '' COMMENT '链接地址',
  `pic_url` varchar(64) NOT NULL DEFAULT '' COMMENT '图片地址',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `page` (`page`),
  KEY `position` (`position`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Table structure for table `config` */

DROP TABLE IF EXISTS `config`;

CREATE TABLE `config` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(64) NOT NULL DEFAULT '' COMMENT '参数名称',
  `value` varchar(64) NOT NULL DEFAULT '' COMMENT '参数值',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `friend_link` */

DROP TABLE IF EXISTS `friend_link`;

CREATE TABLE `friend_link` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(128) NOT NULL DEFAULT '',
  `href` varchar(256) NOT NULL DEFAULT '',
  `orders` int(11) NOT NULL DEFAULT '0',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Table structure for table `leave_msg` */

DROP TABLE IF EXISTS `leave_msg`;

CREATE TABLE `leave_msg` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL DEFAULT '0',
  `name` varchar(128) NOT NULL DEFAULT '',
  `content` varchar(2048) NOT NULL DEFAULT '',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  KEY `del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Table structure for table `live` */

DROP TABLE IF EXISTS `live`;

CREATE TABLE `live` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL DEFAULT '0' COMMENT 'UID',
  `dates` date NOT NULL DEFAULT '0000-00-00' COMMENT '年月',
  `times` int(32) NOT NULL DEFAULT '0' COMMENT '时间点',
  `focus` int(11) NOT NULL DEFAULT '0' COMMENT '每小时关注增长量',
  `popular` int(11) NOT NULL DEFAULT '0' COMMENT '每小时人气增长量',
  `gift_num` int(11) NOT NULL DEFAULT '0' COMMENT '每小时礼物数量',
  `gift_total` varchar(32) NOT NULL DEFAULT '' COMMENT '每小时礼物总金额',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Table structure for table `live_gift` */

DROP TABLE IF EXISTS `live_gift`;

CREATE TABLE `live_gift` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `live_id` int(11) NOT NULL DEFAULT '0' COMMENT '直播流水号ID',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '礼品名称',
  `num` int(11) NOT NULL DEFAULT '0' COMMENT '礼品数量',
  `money` varchar(64) NOT NULL DEFAULT '' COMMENT '礼品金额',
  `dates` date NOT NULL DEFAULT '0000-00-00',
  `times` int(32) NOT NULL DEFAULT '0',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `live_people` */

DROP TABLE IF EXISTS `live_people`;

CREATE TABLE `live_people` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL DEFAULT '0' COMMENT 'UID',
  `dates` date NOT NULL DEFAULT '0000-00-00' COMMENT '年月',
  `visit_type` varchar(64) NOT NULL DEFAULT '' COMMENT '主要访客类型',
  `age` int(11) NOT NULL DEFAULT '0' COMMENT '年龄主要分布',
  `sex` tinyint(4) NOT NULL DEFAULT '0' COMMENT '性别主要分布',
  `focus` varchar(64) NOT NULL DEFAULT '' COMMENT '网站专注度',
  `most_type` int(11) NOT NULL DEFAULT '0' COMMENT '多数网民类型',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `market` */

DROP TABLE IF EXISTS `market`;

CREATE TABLE `market` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL DEFAULT '0' COMMENT 'UID',
  `dates` date NOT NULL DEFAULT '0000-00-00' COMMENT '年月',
  `times` int(11) NOT NULL DEFAULT '0' COMMENT '时间点',
  `shows` varchar(64) NOT NULL DEFAULT '' COMMENT '展现率',
  `click` varchar(64) NOT NULL DEFAULT '' COMMENT '点击率',
  `visit_area` varchar(64) NOT NULL DEFAULT '' COMMENT '访问地区',
  `new_visit` varchar(64) NOT NULL DEFAULT '' COMMENT '新访客',
  `old_visit` varchar(64) NOT NULL DEFAULT '' COMMENT '老访客',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nickname` varchar(64) NOT NULL DEFAULT '' COMMENT '用户昵称',
  `type` varchar(64) NOT NULL DEFAULT '' COMMENT '类型',
  `title` varchar(64) NOT NULL DEFAULT '' COMMENT '标题',
  `price` varchar(64) NOT NULL DEFAULT '' COMMENT '金额',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `notice` */

DROP TABLE IF EXISTS `notice`;

CREATE TABLE `notice` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `create_id` int(11) NOT NULL DEFAULT '0',
  `title` varchar(32) NOT NULL DEFAULT '' COMMENT '公告标题',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '公告状态',
  `content` varchar(2048) NOT NULL DEFAULT '' COMMENT '公告内容',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;

/*Table structure for table `order_log` */

DROP TABLE IF EXISTS `order_log`;

CREATE TABLE `order_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL DEFAULT '0' COMMENT 'UID',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '业务类型1-购买套餐；2-充值；3-提现',
  `pay_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '支付方式0-钱包；1-支付宝；2-微信;3-银行',
  `income_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '收支类型1-收入；2-支出（购买和充值是收入，提现是支出）',
  `money` varchar(64) NOT NULL DEFAULT '' COMMENT '发生金额',
  `business_id` varchar(64) NOT NULL DEFAULT '' COMMENT '业务单编号ID',
  `order_id` varchar(64) NOT NULL DEFAULT '' COMMENT '第三方流水号',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

/*Table structure for table `packages` */

DROP TABLE IF EXISTS `packages`;

CREATE TABLE `packages` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL DEFAULT '' COMMENT '套餐名称',
  `title_one` varchar(256) NOT NULL DEFAULT '' COMMENT '副标题一',
  `title_two` varchar(256) NOT NULL DEFAULT '' COMMENT '副标题二',
  `title_three` varchar(256) NOT NULL DEFAULT '' COMMENT '副标题三',
  `pic` varchar(256) NOT NULL DEFAULT '' COMMENT '主图',
  `content` text COMMENT '详细内容',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Table structure for table `packages_order` */

DROP TABLE IF EXISTS `packages_order`;

CREATE TABLE `packages_order` (
  `id` bigint(15) unsigned NOT NULL,
  `uid` int(11) NOT NULL DEFAULT '0' COMMENT 'UID',
  `package_id` int(11) NOT NULL DEFAULT '0' COMMENT '套餐ID',
  `package_name` varchar(256) NOT NULL DEFAULT '' COMMENT '套餐种类名称',
  `package_price_id` int(11) NOT NULL DEFAULT '0' COMMENT '套餐价格id',
  `package_pic` varchar(512) NOT NULL DEFAULT '',
  `price` varchar(64) NOT NULL DEFAULT '' COMMENT '订单金额',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '订单状态0-待付款；1-待启动；2-上线中；3-已结束；4-已评价；9-已取消',
  `pay_type` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '支付方式ID 0-钱包；1-支付宝；2-微信',
  `submit_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '下单时间',
  `pay_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '支付时间',
  `start_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '启动时间',
  `end_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束时间',
  `comment` varchar(1024) NOT NULL DEFAULT '' COMMENT '评语',
  `comment_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '评价时间',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  KEY `package_id` (`package_id`),
  KEY `del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `packages_price` */

DROP TABLE IF EXISTS `packages_price`;

CREATE TABLE `packages_price` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `package_id` int(11) NOT NULL DEFAULT '0' COMMENT '套餐id',
  `name` varchar(256) NOT NULL DEFAULT '' COMMENT '种类名称',
  `price` varchar(64) NOT NULL DEFAULT '' COMMENT '种类价格',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `package_id` (`package_id`),
  KEY `del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL DEFAULT '',
  `pic` varchar(256) NOT NULL DEFAULT '',
  `href` varchar(512) NOT NULL DEFAULT '',
  `position` tinyint(4) NOT NULL DEFAULT '0',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Table structure for table `task` */

DROP TABLE IF EXISTS `task`;

CREATE TABLE `task` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `cat_id` int(11) NOT NULL DEFAULT '0' COMMENT '任务分类ID',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '任务类型0-普通任务；5-高级任务',
  `title` varchar(64) NOT NULL DEFAULT '' COMMENT '任务标题',
  `price` varchar(32) NOT NULL DEFAULT '' COMMENT '任务总赏金',
  `num` int(11) NOT NULL DEFAULT '0' COMMENT '所需人数',
  `illustration` varchar(2048) NOT NULL DEFAULT '' COMMENT '任务说明',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '任务状态:0-招募中；1-招募完成',
  `sign_num` int(11) NOT NULL DEFAULT '0' COMMENT '报名人数',
  `bid_num` int(11) NOT NULL DEFAULT '0' COMMENT '中标人数',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8;

/*Table structure for table `task_category` */

DROP TABLE IF EXISTS `task_category`;

CREATE TABLE `task_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '分类名称',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Table structure for table `task_pic` */

DROP TABLE IF EXISTS `task_pic`;

CREATE TABLE `task_pic` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `task_id` varchar(64) NOT NULL DEFAULT '' COMMENT '任务ID',
  `uid` varchar(64) NOT NULL DEFAULT '' COMMENT '中标者UID',
  `pic` varchar(64) NOT NULL DEFAULT '' COMMENT '交付截图地址',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `task_sign` */

DROP TABLE IF EXISTS `task_sign`;

CREATE TABLE `task_sign` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `task_id` int(11) NOT NULL DEFAULT '0' COMMENT '任务ID',
  `uid` int(11) NOT NULL DEFAULT '0' COMMENT '报名者UID',
  `bid_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '中标标志1-中标；2-取消中标',
  `bid_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '中标时间',
  `single_price` varchar(64) NOT NULL DEFAULT '' COMMENT '单人赏金',
  `pay_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '交付状态0-待完成；1-待付款；2-已结束；3-未完成',
  `pay_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '交付时间',
  `op_id` int(11) NOT NULL DEFAULT '0' COMMENT '完成审核人员ID',
  `op_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '完成审核时间',
  `reason` varchar(512) NOT NULL DEFAULT '' COMMENT '原因',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `task_id` (`task_id`),
  KEY `uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8;

/*Table structure for table `task_sign_pic` */

DROP TABLE IF EXISTS `task_sign_pic`;

CREATE TABLE `task_sign_pic` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `task_id` int(11) NOT NULL DEFAULT '0',
  `user_id` int(11) NOT NULL DEFAULT '0',
  `pic_url` varchar(512) NOT NULL DEFAULT '',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `mobile` varchar(16) NOT NULL DEFAULT '' COMMENT '手机号码',
  `passwd` varchar(32) NOT NULL DEFAULT '' COMMENT '登录密码',
  `pay_passwd` varchar(32) NOT NULL DEFAULT '' COMMENT '支付密码',
  `nickname` varchar(128) NOT NULL DEFAULT '' COMMENT '昵称',
  `avatar` varchar(128) NOT NULL DEFAULT '' COMMENT '头像',
  `balance` varchar(32) NOT NULL DEFAULT '0.0' COMMENT '钱包余额',
  `reg_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '注册时间',
  `truename` varchar(128) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `id_card` varchar(32) NOT NULL DEFAULT '' COMMENT '身份证号码',
  `front_pic` varchar(128) NOT NULL DEFAULT '' COMMENT '身份证正面照地址',
  `back_pic` varchar(128) NOT NULL DEFAULT '' COMMENT '身份证反面照地址',
  `hand_pic` varchar(128) NOT NULL DEFAULT '' COMMENT '手持身份证照地址',
  `plat` varchar(16) NOT NULL DEFAULT '' COMMENT '平台',
  `live_room` varchar(32) NOT NULL DEFAULT '' COMMENT '直播房间',
  `plat_pic` varchar(128) NOT NULL DEFAULT '' COMMENT '平台截图地址',
  `auth_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '认证状态0=>''待审核'',1=>''已通过'',2=>''未通过''',
  `auth_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '认证通过时间',
  `auth_opid` int(11) NOT NULL DEFAULT '0' COMMENT '认证人',
  `email` varchar(128) NOT NULL DEFAULT '' COMMENT '邮箱',
  `contact_mobile` varchar(32) NOT NULL DEFAULT '' COMMENT '联系电话',
  `contact_address` varchar(1024) NOT NULL DEFAULT '' COMMENT '联系地址',
  `agency_name` varchar(128) NOT NULL DEFAULT '' COMMENT '经纪公司名称',
  `agency_mobile` varchar(32) NOT NULL DEFAULT '' COMMENT '经纪人电话',
  `account_name` varchar(128) NOT NULL DEFAULT '' COMMENT '开户人名称',
  `bank_name` varchar(1024) NOT NULL DEFAULT '' COMMENT '银行名称',
  `sub_bank_name` varchar(1024) NOT NULL DEFAULT '' COMMENT '支行名称',
  `bank_card` varchar(128) NOT NULL DEFAULT '' COMMENT '银行卡账号',
  `task_cat` varchar(256) NOT NULL DEFAULT '' COMMENT '关注的赏金类型',
  `qq_id` varchar(64) NOT NULL DEFAULT '' COMMENT 'QQ第三方登录',
  `weixin_id` varchar(64) NOT NULL DEFAULT '' COMMENT '微信第三方登录',
  `description` varchar(2048) NOT NULL DEFAULT '',
  `note` varchar(2048) NOT NULL DEFAULT '' COMMENT '备注',
  `cat_num` int(11) NOT NULL DEFAULT '0' COMMENT '关注数量',
  `sign_num` int(11) NOT NULL DEFAULT '0' COMMENT '中标数量',
  `finish_num` int(11) NOT NULL DEFAULT '0' COMMENT '完成数量',
  `balance_num` varchar(32) NOT NULL DEFAULT '0.0' COMMENT '完成总金额',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `mobile` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

/*Table structure for table `user_auth` */

DROP TABLE IF EXISTS `user_auth`;

CREATE TABLE `user_auth` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `mobile` varchar(16) NOT NULL DEFAULT '' COMMENT '手机号码',
  `reg_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '注册时间',
  `truename` varchar(128) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `id_card` varchar(32) NOT NULL DEFAULT '' COMMENT '身份证号码',
  `front_pic` varchar(128) NOT NULL DEFAULT '' COMMENT '身份证正面照地址',
  `back_pic` varchar(128) NOT NULL DEFAULT '' COMMENT '身份证反面照地址',
  `hand_pic` varchar(128) NOT NULL DEFAULT '' COMMENT '手持身份证照地址',
  `plat` varchar(16) NOT NULL DEFAULT '' COMMENT '平台',
  `live_room` varchar(32) NOT NULL DEFAULT '' COMMENT '直播房间',
  `plat_pic` varchar(128) NOT NULL DEFAULT '' COMMENT '平台截图地址',
  `auth_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '认证状态',
  `auth_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '认证通过时间',
  `auth_opid` int(11) NOT NULL DEFAULT '0' COMMENT '认证人',
  `email` varchar(128) NOT NULL DEFAULT '' COMMENT '邮箱',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

/*Table structure for table `user_pic` */

DROP TABLE IF EXISTS `user_pic`;

CREATE TABLE `user_pic` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL DEFAULT '0',
  `pic` varchar(128) NOT NULL DEFAULT '',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  KEY `del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Table structure for table `wallet_cash` */

DROP TABLE IF EXISTS `wallet_cash`;

CREATE TABLE `wallet_cash` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL DEFAULT '0' COMMENT 'UID',
  `money` varchar(64) NOT NULL DEFAULT '' COMMENT '提现金额',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '处理状态:0-待处理；1-已通过；2-未通过',
  `apply_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '申请时间',
  `op_id` int(11) NOT NULL DEFAULT '0' COMMENT '后台人员ID',
  `op_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '处理时间',
  `third_order_id` varchar(128) NOT NULL DEFAULT '' COMMENT '第三方交易流水',
  `reseaon` varchar(512) NOT NULL DEFAULT '' COMMENT '不通过理由',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='提现';

/*Table structure for table `wallet_recharge` */

DROP TABLE IF EXISTS `wallet_recharge`;

CREATE TABLE `wallet_recharge` (
  `id` bigint(15) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL DEFAULT '0',
  `money` varchar(32) NOT NULL DEFAULT '',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0-未支付，1-已支付',
  `order_id` varchar(64) NOT NULL DEFAULT '' COMMENT '第三方order_id',
  `pay_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '1-支付宝；2-微信',
  `pay_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充值表';

/*Table structure for table `wallet_turnover` */

DROP TABLE IF EXISTS `wallet_turnover`;

CREATE TABLE `wallet_turnover` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL DEFAULT '0' COMMENT 'UID',
  `business_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '业务类型：1，充值，2，提现，3购买套餐，4，赏金收益,5,提现未通过',
  `pay_type` tinyint(64) NOT NULL DEFAULT '0' COMMENT '收支类型：1，收，2，支',
  `money` varchar(64) NOT NULL DEFAULT '' COMMENT '金额',
  `current_balance` varchar(64) NOT NULL DEFAULT '' COMMENT '实时余额',
  `business_no` varchar(64) NOT NULL DEFAULT '' COMMENT '业务编号',
  `third_order_id` varchar(128) NOT NULL DEFAULT '' COMMENT '第三方交易流水号',
  `reseaon` varchar(512) NOT NULL DEFAULT '' COMMENT '不通过理由',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8 COMMENT='钱包流水';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
