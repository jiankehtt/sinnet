/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.1.73 : Database - sinnet
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sinnet` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sinnet`;

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `feed_id` varchar(32) NOT NULL,
  `feed_content` varchar(255) DEFAULT NULL,
  `user_guid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`feed_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `need` varchar(500) DEFAULT NULL,
  `position` varchar(150) DEFAULT NULL,
  `industry` varchar(150) DEFAULT NULL,
  `guid` varchar(32) NOT NULL,
  `company_name` varchar(150) DEFAULT NULL,
  `department` varchar(150) DEFAULT NULL,
  `username` varchar(150) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `email` varchar(25) DEFAULT NULL,
  `addtime` date DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `wechat_openid` varchar(150) DEFAULT NULL,
  `wechat_nickname` varchar(130) DEFAULT NULL,
  `wechat_sex` tinyint(3) DEFAULT NULL,
  `wechat_province` varchar(250) DEFAULT NULL,
  `wechat_city` varchar(250) DEFAULT NULL,
  `wechat_country` varchar(250) DEFAULT NULL,
  `wechat_headimgurl` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
