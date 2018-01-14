/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.0.18-nt : Database - security
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`security` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `security`;

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `id` bigint(16) NOT NULL auto_increment,
  `title` varchar(64) default NULL,
  `describe` varchar(512) default NULL,
  `content` text,
  `status` int(2) default NULL,
  `scope` int(4) default NULL,
  `opt1` int(4) default NULL,
  `opt2` int(4) default NULL,
  `opt3` int(4) default NULL,
  `opt4` int(4) default NULL,
  `createtime` datetime default NULL,
  `type` bigint(16) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `article` */

/*Table structure for table `type` */

DROP TABLE IF EXISTS `type`;

CREATE TABLE `type` (
  `id` bigint(16) default NULL,
  `name` varchar(16) default NULL,
  `status` int(2) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `type` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
