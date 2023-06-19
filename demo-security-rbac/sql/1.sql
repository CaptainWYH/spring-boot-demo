/*
SQLyog Community v13.1.9 (64 bit)
MySQL - 8.0.12 : Database - spring-boot-demo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`spring-boot-demo` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;

USE `spring-boot-demo`;

/*Table structure for table `sec_permission` */

DROP TABLE IF EXISTS `sec_permission`;

CREATE TABLE `sec_permission` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '权限名',
  `url` varchar(1000) DEFAULT NULL COMMENT '类型为页面时，代表前端路由地址，类型为按钮时，代表后端接口地址',
  `type` int(2) NOT NULL COMMENT '权限类型，页面-1，按钮-2',
  `permission` varchar(50) DEFAULT NULL COMMENT '权限表达式',
  `method` varchar(50) DEFAULT NULL COMMENT '后端接口访问方式',
  `sort` int(11) NOT NULL COMMENT '排序',
  `parent_id` bigint(64) NOT NULL COMMENT '父级id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

/*Data for the table `sec_permission` */

insert  into `sec_permission`(`id`,`name`,`url`,`type`,`permission`,`method`,`sort`,`parent_id`) values 
(1072806379288399872,'测试页面','/test',1,'page:test',NULL,1,0),
(1072806379313565696,'测试页面-查询','/**/test',2,'btn:test:query','GET',1,1072806379288399872),
(1072806379330342912,'测试页面-添加','/**/test',2,'btn:test:insert','POST',2,1072806379288399872),
(1072806379342925824,'监控在线用户页面','/monitor',1,'page:monitor:online',NULL,2,0),
(1072806379363897344,'在线用户页面-查询','/**/api/monitor/online/user',2,'btn:monitor:online:query','GET',1,1072806379342925824),
(1072806379384868864,'在线用户页面-踢出','/**/api/monitor/online/user/kickout',2,'btn:monitor:online:kickout','DELETE',2,1072806379342925824);

/*Table structure for table `sec_role` */

DROP TABLE IF EXISTS `sec_role`;

CREATE TABLE `sec_role` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '角色名',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `create_time` bigint(13) NOT NULL COMMENT '创建时间',
  `update_time` bigint(13) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `sec_role` */

insert  into `sec_role`(`id`,`name`,`description`,`create_time`,`update_time`) values 
(1072806379208708096,'管理员','超级管理员',1544611947239,1544611947239),
(1072806379238068224,'普通用户','普通用户',1544611947246,1544611947246);

/*Table structure for table `sec_role_permission` */

DROP TABLE IF EXISTS `sec_role_permission`;

CREATE TABLE `sec_role_permission` (
  `role_id` bigint(64) NOT NULL COMMENT '角色主键',
  `permission_id` bigint(64) NOT NULL COMMENT '权限主键',
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限关系表';

/*Data for the table `sec_role_permission` */

insert  into `sec_role_permission`(`role_id`,`permission_id`) values 
(1072806379208708096,1072806379288399872),
(1072806379208708096,1072806379313565696),
(1072806379208708096,1072806379330342912),
(1072806379208708096,1072806379342925824),
(1072806379208708096,1072806379363897344),
(1072806379208708096,1072806379384868864),
(1072806379238068224,1072806379288399872),
(1072806379238068224,1072806379313565696);

/*Table structure for table `sec_user` */

DROP TABLE IF EXISTS `sec_user`;

CREATE TABLE `sec_user` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(60) NOT NULL COMMENT '密码',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `birthday` bigint(13) DEFAULT NULL COMMENT '生日',
  `sex` int(2) DEFAULT NULL COMMENT '性别，男-1，女-2',
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '状态，启用-1，禁用-0',
  `create_time` bigint(13) NOT NULL COMMENT '创建时间',
  `update_time` bigint(13) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=1670684989316386819 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `sec_user` */

insert  into `sec_user`(`id`,`username`,`password`,`nickname`,`phone`,`email`,`birthday`,`sex`,`status`,`create_time`,`update_time`) values 
(1670684832399085570,'weiyuhui','$2a$10$1wnzSTwxh495YKl0By9ZUe62cIkUaY5N0ovINHdk3o5/d9bEY5a2y','陆静','18196681868','b.bskigyw@otkwstq.ni',NULL,NULL,1,1687157278502,NULL),
(1670684989316386818,'admin','$2a$10$aUs5gI7D6C2Q8SPYMx8BYeRePi1WA7Qw6lHNriSx6wp9PMcWoBC3S','史桂英','18653155908','j.mtruorox@lgxjpkp.by',NULL,NULL,1,1687157315944,NULL);

/*Table structure for table `sec_user_role` */

DROP TABLE IF EXISTS `sec_user_role`;

CREATE TABLE `sec_user_role` (
  `user_id` bigint(64) NOT NULL COMMENT '用户主键',
  `role_id` bigint(64) NOT NULL COMMENT '角色主键',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';

/*Data for the table `sec_user_role` */

insert  into `sec_user_role`(`user_id`,`role_id`) values 
(1072806377661009920,1072806379208708096),
(1072806378780889088,1072806379238068224);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
