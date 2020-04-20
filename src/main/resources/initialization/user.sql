/*
 author.nama = 欧阳伟
 author.email = oyw1006@163.com

 Source Server         : Ubuntu
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : 106.14.96.160:3306
 Source Schema         : oyr

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 19/04/2020 17:01:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE `user` (
    `id` BIGINT(20) UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '主键',
    `username` VARCHAR(30) NOT NULL COMMENT '昵称',
    `password` VARCHAR(60) NOT NULL COMMENT '密码',
	`telephone` INT(11) UNSIGNED NOT NULL COMMENT '手机号',
    `address` VARCHAR(100) NULL COMMENT '地址',
    `create_time` DATETIME NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
)  ENGINE=INNODB CHARSET=UTF8 COLLATE = utf8_general_ci;

SET FOREIGN_KEY_CHECKS = 1;
