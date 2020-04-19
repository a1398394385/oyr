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
-- Table structure for phone
-- ----------------------------
CREATE TABLE `phone` (
    `id` BIGINT(20) UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '主键',
    `model` VARCHAR(30)  NOT NULL COMMENT '型号',
    `color` VARCHAR(100)  NOT NULL COMMENT '颜色',
    `storage` VARCHAR(100)  NOT NULL COMMENT '存储规格',
    `price` decimal(10, 2)  NOT NULL COMMENT '价格',
	`image` VARCHAR(100)  NOT NULL COMMENT '图片',
	PRIMARY KEY (`id`)
)  ENGINE=INNODB CHARSET=UTF8 COLLATE = utf8_general_ci;
SET FOREIGN_KEY_CHECKS = 1;