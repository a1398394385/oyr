/*
 Navicat Premium Data Transfer

 Source Server         : OYW
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : 106.14.96.160:3306
 Source Schema         : oyr

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 24/04/2020 03:17:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for phone
-- ----------------------------
DROP TABLE IF EXISTS `phone`;
CREATE TABLE `phone`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `brand` enum('1','2','3','4','5','6','7','8','9','10') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌；null=未知，1=华为，2=荣耀，3=苹果，4=小米，5=OPPO，6=三星，7=VIVO，8=魅族，9=酷派，10=金立，11=锤子，12=一加',
  `model` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '具体型号',
  `color` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '颜色',
  `storage` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '存储规格',
  `price` decimal(10, 2) NOT NULL COMMENT '价格',
  `image` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of phone
-- ----------------------------
INSERT INTO `phone` VALUES (1, '4', '小米 10 Pro（5G）', '珍珠白-星空蓝', '12+512-12+256-8+256', 3549.00, '/image/具体机型/小米/小米 10 Pro（5G）.png');
INSERT INTO `phone` VALUES (2, '4', '小米 10（5G）', '蜜桃金-钛银黑-冰海蓝', '12+256-8+256-8+128', 3217.00, '/image/具体机型/小米/小米 10（5G）.png');
INSERT INTO `phone` VALUES (3, '4', '红米 K30 Pro变焦版（5G）', '天际蓝-太空灰-月幕白-星环紫', '8+256-8+128', 2478.00, '/image/具体机型/小米/红米 K30 Pro变焦版（5G）.png');
INSERT INTO `phone` VALUES (4, '4', '小米9 Pro （5G）', '梦之白-钛银黑', '12+512-12+256-8+256-8+128', 2337.00, '/image/具体机型/小米/小米9 Pro （5G）.png');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `password` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `telephone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `telphone`(`telephone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '123', '123', '123', '123', '2020-04-21 04:17:06');

SET FOREIGN_KEY_CHECKS = 1;


-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `price` decimal(10, 2)  NOT NULL COMMENT '回收价格',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户id',
  `telephone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `state` tinyint(1) NOT NULL COMMENT '订单状态',
  `phone_id` bigint(20) NOT NULL COMMENT '手机id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `telphone`(`telephone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `name` varchar(30) NOT NULL COMMENT '管理员name',
  `password` varchar(60) NOT NULL COMMENT '管理员密码',
  `authority` enum('0','1','2') NOT NULL COMMENT '管理员权限，0：无权限、1：普通权限、2：超级权限',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
