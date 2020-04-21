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
DROP TABLE IF EXISTS `phone`;
CREATE TABLE `phone`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `brand` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '品牌',
  `model` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '型号',
  `color` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '颜色',
  `storage` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '存储规格',
  `price` decimal(10, 2) NOT NULL COMMENT '价格',
  `image` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of phone
-- ----------------------------
INSERT INTO `phone` VALUES (1, 4, '小米 10 Pro（5G）', '珍珠白-星空蓝', '12+512-12+256-8+256', 3549.00, '/image/具体机型/小米/小米 10 Pro（5G）.png');
INSERT INTO `phone` VALUES (2, 4, '小米 10（5G）', '蜜桃金-钛银黑-冰海蓝', '12+256-8+256-8+128', 3217.00, '/image/具体机型/小米/小米 10（5G）.png');
INSERT INTO `phone` VALUES (3, 4, '红米 K30 Pro变焦版（5G）', '天际蓝-太空灰-月幕白-星环紫', '8+256-8+128', 2478.00, '/image/具体机型/小米/红米 K30 Pro变焦版（5G）.png');
INSERT INTO `phone` VALUES (4, 4, '小米9 Pro （5G）', '梦之白-钛银黑', '12+512-12+256-8+256-8+128', 2337.00, '/image/具体机型/小米/小米9 Pro （5G）.png');

SET FOREIGN_KEY_CHECKS = 1;
