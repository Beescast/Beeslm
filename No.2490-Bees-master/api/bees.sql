/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.7.10 : Database - bees
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `config` */

/*Table structure for table `live` */

DROP TABLE IF EXISTS `live`;

CREATE TABLE `live` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(64) NOT NULL DEFAULT '' COMMENT 'UID',
  `dates` varchar(64) NOT NULL DEFAULT '' COMMENT '年月',
  `times` varchar(64) NOT NULL DEFAULT '' COMMENT '时间点',
  `focus` varchar(64) NOT NULL DEFAULT '' COMMENT '每小时关注增长量',
  `popular ` varchar(64) NOT NULL DEFAULT '' COMMENT '每小时人气增长量',
  `gift_num` varchar(64) NOT NULL DEFAULT '' COMMENT '每小时礼物数量',
  `gift_total` varchar(64) NOT NULL DEFAULT '' COMMENT '每小时礼物总金额',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `live` */

/*Table structure for table `live_gift` */

DROP TABLE IF EXISTS `live_gift`;

CREATE TABLE `live_gift` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `live_id` varchar(64) NOT NULL DEFAULT '' COMMENT '直播流水号ID',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '礼品名称',
  `num` varchar(64) NOT NULL DEFAULT '' COMMENT '礼品数量',
  `money` varchar(64) NOT NULL DEFAULT '' COMMENT '礼品金额',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `live_gift` */

/*Table structure for table `live_people` */

DROP TABLE IF EXISTS `live_people`;

CREATE TABLE `live_people` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(64) NOT NULL DEFAULT '' COMMENT 'UID',
  `dates` varchar(64) NOT NULL DEFAULT '' COMMENT '年月',
  `visit_type` varchar(64) NOT NULL DEFAULT '' COMMENT '主要访客类型',
  `age` varchar(64) NOT NULL DEFAULT '' COMMENT '年龄主要分布',
  `sex` varchar(64) NOT NULL DEFAULT '' COMMENT '性别主要分布',
  `focus` varchar(64) NOT NULL DEFAULT '' COMMENT '网站专注度',
  `most_type` varchar(64) NOT NULL DEFAULT '' COMMENT '多数网民类型',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `live_people` */

/*Table structure for table `market` */

DROP TABLE IF EXISTS `market`;

CREATE TABLE `market` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(64) NOT NULL DEFAULT '' COMMENT 'UID',
  `dates` varchar(64) NOT NULL DEFAULT '' COMMENT '年月',
  `times` varchar(64) NOT NULL DEFAULT '' COMMENT '时间点',
  `show` varchar(64) NOT NULL DEFAULT '' COMMENT '展现率',
  `click` varchar(64) NOT NULL DEFAULT '' COMMENT '点击率',
  `visit_area` varchar(64) NOT NULL DEFAULT '' COMMENT '访问地区',
  `new_visit` varchar(64) NOT NULL DEFAULT '' COMMENT '新访客',
  `old_visit` varchar(64) NOT NULL DEFAULT '' COMMENT '老访客',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `market` */

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

/*Data for the table `message` */

/*Table structure for table `notice` */

DROP TABLE IF EXISTS `notice`;

CREATE TABLE `notice` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(32) NOT NULL DEFAULT '' COMMENT '公告标题',
  `status` varchar(32) NOT NULL DEFAULT '' COMMENT '公告状态',
  `content` varchar(1024) NOT NULL DEFAULT '' COMMENT '公告内容',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `notice` */

/*Table structure for table `order_log` */

DROP TABLE IF EXISTS `order_log`;

CREATE TABLE `order_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(64) NOT NULL DEFAULT '' COMMENT 'UID',
  `type` varchar(64) NOT NULL DEFAULT '' COMMENT '业务类型',
  `pay_type` varchar(64) NOT NULL DEFAULT '' COMMENT '支付方式',
  `income_type` varchar(64) NOT NULL DEFAULT '' COMMENT '收支类型',
  `money` varchar(64) NOT NULL DEFAULT '' COMMENT '发生金额',
  `business_id` varchar(64) NOT NULL DEFAULT '' COMMENT '业务单编号ID',
  `order_id` varchar(64) NOT NULL DEFAULT '' COMMENT '第三方流水号',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `order_log` */

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `packages` */

insert  into `packages`(`id`,`name`,`title_one`,`title_two`,`title_three`,`pic`,`content`,`add_time`,`update_time`,`del_flag`) values (1,'天字第一号','一标题','二标题','三标题','http://www.baidu.com/pic','wefqwef;lqkwe;ngfkjwqenbgkjqwbelgrkjqwegf\r\nsdf\r\new\r\n\r\nfw\r\ne\r\ng\r\nwqeg\r\nqw\r\nreg\r\nqrew\r\ngerg','2016-07-31 00:20:38','2016-07-31 00:20:40',0);

/*Table structure for table `packages_order` */

DROP TABLE IF EXISTS `packages_order`;

CREATE TABLE `packages_order` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL DEFAULT '0' COMMENT 'UID',
  `package_id` int(11) NOT NULL DEFAULT '0' COMMENT '套餐ID',
  `package_name` varchar(256) NOT NULL DEFAULT '' COMMENT '套餐种类名称',
  `price` varchar(64) NOT NULL DEFAULT '' COMMENT '订单金额',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '订单状态0-待付款；1-待启动；2-上线中；3-已结束；4-已评价；9-已取消',
  `pay_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '支付方式ID 0-钱包；1-支付宝；2-微信',
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

/*Data for the table `packages_order` */

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `packages_price` */

insert  into `packages_price`(`id`,`package_id`,`name`,`price`,`add_time`,`update_time`,`del_flag`) values (1,1,'年套餐','1000','2016-07-31 01:04:45','2016-07-31 01:02:25',0),(2,1,'季套餐','260','2016-07-31 01:04:49','2016-07-31 01:02:26',0),(3,1,'月套餐','100','2016-07-31 01:04:51','2016-07-31 01:02:26',0);

/*Table structure for table `pictures` */

DROP TABLE IF EXISTS `pictures`;

CREATE TABLE `pictures` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `page` varchar(32) NOT NULL DEFAULT '' COMMENT '页面名称',
  `position` varchar(64) NOT NULL DEFAULT '' COMMENT '图片位置',
  `order` varchar(64) NOT NULL DEFAULT '' COMMENT '显示顺序',
  `pic_href` varchar(64) NOT NULL DEFAULT '' COMMENT '链接地址',
  `pic_url` varchar(64) NOT NULL DEFAULT '' COMMENT '图片地址',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `pictures` */

/*Table structure for table `task` */

DROP TABLE IF EXISTS `task`;

CREATE TABLE `task` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `cat_id` varchar(64) NOT NULL DEFAULT '' COMMENT '任务分类ID',
  `type` varchar(64) NOT NULL DEFAULT '' COMMENT '任务类型',
  `title` varchar(64) NOT NULL DEFAULT '' COMMENT '任务标题',
  `price` varchar(64) NOT NULL DEFAULT '' COMMENT '任务总赏金',
  `num` varchar(64) NOT NULL DEFAULT '' COMMENT '所需人数',
  `illustration` varchar(64) NOT NULL DEFAULT '' COMMENT '任务说明',
  `status` varchar(64) NOT NULL DEFAULT '' COMMENT '任务状态',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `task` */

/*Table structure for table `task_category` */

DROP TABLE IF EXISTS `task_category`;

CREATE TABLE `task_category` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '分类名称',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `task_category` */

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

/*Data for the table `task_pic` */

/*Table structure for table `task_sign` */

DROP TABLE IF EXISTS `task_sign`;

CREATE TABLE `task_sign` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `task_id` varchar(64) NOT NULL DEFAULT '' COMMENT '任务ID',
  `uid` varchar(64) NOT NULL DEFAULT '' COMMENT '报名者UID',
  `bid_status` varchar(64) NOT NULL DEFAULT '' COMMENT '中标标志',
  `bid_time` varchar(64) NOT NULL DEFAULT '' COMMENT '中标时间',
  `single_price` varchar(64) NOT NULL DEFAULT '' COMMENT '单人赏金',
  `pay_status` varchar(64) NOT NULL DEFAULT '' COMMENT '交付状态',
  `pay_time` varchar(64) NOT NULL DEFAULT '' COMMENT '交付时间',
  `op_id` varchar(64) NOT NULL DEFAULT '' COMMENT '完成审核人员ID',
  `op_time` varchar(64) NOT NULL DEFAULT '' COMMENT '完成审核时间',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `task_sign` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `mobile` varchar(16) NOT NULL DEFAULT '' COMMENT '手机号码',
  `passwd` varchar(32) NOT NULL DEFAULT '' COMMENT '登录密码',
  `pay_passwd` varchar(32) NOT NULL DEFAULT '' COMMENT '支付密码',
  `nickname` varchar(128) NOT NULL DEFAULT '' COMMENT '昵称',
  `avatar` varchar(128) NOT NULL DEFAULT '' COMMENT '头像',
  `balance` varbinary(32) NOT NULL DEFAULT '' COMMENT '钱包余额',
  `reg_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '注册时间',
  `truename` varchar(128) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `id_card` varchar(32) NOT NULL DEFAULT '' COMMENT '身份证号码',
  `front_pic` varchar(128) NOT NULL DEFAULT '' COMMENT '身份证正面照地址',
  `back_pic` varchar(128) NOT NULL DEFAULT '' COMMENT '身份证反面照地址',
  `hand_pic` varchar(128) NOT NULL DEFAULT '' COMMENT '手持身份证照地址',
  `plat` varchar(16) NOT NULL DEFAULT '' COMMENT '平台',
  `live_room` varchar(32) NOT NULL DEFAULT '' COMMENT '直播房间',
  `plat_pic` varchar(128) NOT NULL DEFAULT '' COMMENT '平台截图地址',
  `auth_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '认证通过时间',
  `email` varchar(128) NOT NULL DEFAULT '' COMMENT '邮箱',
  `contact_mobile` varchar(32) NOT NULL DEFAULT '' COMMENT '联系电话',
  `contact_address` varchar(256) NOT NULL DEFAULT '' COMMENT '联系地址',
  `agency_name` varchar(128) NOT NULL DEFAULT '' COMMENT '经纪公司名称',
  `agency_mobile` varchar(32) NOT NULL DEFAULT '' COMMENT '经纪人电话',
  `account_name` varchar(128) NOT NULL DEFAULT '' COMMENT '开户人名称',
  `bank_name` varchar(128) NOT NULL DEFAULT '' COMMENT '银行名称',
  `sub_bank_name` varchar(128) NOT NULL DEFAULT '' COMMENT '支行名称',
  `bank_card` varchar(128) NOT NULL DEFAULT '' COMMENT '银行卡账号',
  `qq_id` varchar(64) NOT NULL DEFAULT '' COMMENT 'QQ第三方登录',
  `weixin_id` varchar(64) NOT NULL DEFAULT '' COMMENT '微信第三方登录',
  `note` varchar(128) NOT NULL DEFAULT '' COMMENT '备注',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

/*Table structure for table `wallet_cash` */

DROP TABLE IF EXISTS `wallet_cash`;

CREATE TABLE `wallet_cash` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(64) NOT NULL DEFAULT '' COMMENT 'UID',
  `money` varchar(64) NOT NULL DEFAULT '' COMMENT '提现金额',
  `status` varchar(64) NOT NULL DEFAULT '' COMMENT '处理状态',
  `apply_time` varchar(64) NOT NULL DEFAULT '' COMMENT '申请时间',
  `op_id` varchar(64) NOT NULL DEFAULT '' COMMENT '后台人员ID',
  `op_time` varchar(64) NOT NULL DEFAULT '' COMMENT '处理时间',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wallet_cash` */

/*Table structure for table `wallet_turnover` */

DROP TABLE IF EXISTS `wallet_turnover`;

CREATE TABLE `wallet_turnover` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(64) NOT NULL DEFAULT '' COMMENT 'UID',
  `business_type` varchar(64) NOT NULL DEFAULT '' COMMENT '业务类型',
  `pay_type` varchar(64) NOT NULL DEFAULT '' COMMENT '收支类型',
  `money` varchar(64) NOT NULL DEFAULT '' COMMENT '金额',
  `current_balance` varchar(64) NOT NULL DEFAULT '' COMMENT '实时余额',
  `business_no` varchar(64) NOT NULL DEFAULT '' COMMENT '业务编号',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wallet_turnover` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
