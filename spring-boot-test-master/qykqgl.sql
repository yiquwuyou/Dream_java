/*
 Navicat Premium Data Transfer

 Source Server         : 8088
 Source Server Type    : MySQL
 Source Server Version : 50540
 Source Host           : localhost:3306
 Source Schema         : db2021

 Target Server Type    : MySQL
 Target Server Version : 50540
 File Encoding         : 65001

 Date: 17/05/2021 16:45:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for check
-- ----------------------------
DROP TABLE IF EXISTS `check`;
CREATE TABLE `check`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ryzj` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scdk` timestamp NULL DEFAULT NULL,
  `zhdk` timestamp NULL DEFAULT NULL,
  `lb` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `zt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bz` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ljcqts` int(255) NULL DEFAULT NULL,
  `zgsc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `kqrq` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of check
-- ----------------------------

-- ----------------------------
-- Table structure for com
-- ----------------------------
DROP TABLE IF EXISTS `com`;
CREATE TABLE `com`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gh` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `xm` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `kqhdrx` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `xb` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of com
-- ----------------------------
INSERT INTO `com` VALUES ('27172233692577828', '', '李哥', '湖南', '15245621223', '男');
INSERT INTO `com` VALUES ('27172233692577837', '', '立国', '云南', '18869696969', '男');

-- ----------------------------
-- Table structure for czrzxx
-- ----------------------------
DROP TABLE IF EXISTS `czrzxx`;
CREATE TABLE `czrzxx`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `czsj` timestamp NULL DEFAULT NULL,
  `bz` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `czyh` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of czrzxx
-- ----------------------------

-- ----------------------------
-- Table structure for dlrzxx
-- ----------------------------
DROP TABLE IF EXISTS `dlrzxx`;
CREATE TABLE `dlrzxx`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dlsj` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dlyh` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dlrzxx
-- ----------------------------

-- ----------------------------
-- Table structure for sta
-- ----------------------------
DROP TABLE IF EXISTS `sta`;
CREATE TABLE `sta`  (
  `gh` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `xm` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `xb` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lxdh` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `jrsj` date NULL DEFAULT NULL,
  `zjhm` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ssbm` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bmbh` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cjsj` timestamp NULL DEFAULT NULL,
  `cjyh` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `xgsj` timestamp NULL DEFAULT NULL,
  `xgyh` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bz` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nl` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`gh`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sta
-- ----------------------------
INSERT INTO `sta` VALUES ('27172233692577802', '马云', '男', '19901323333', '2021-05-05', '630021197005112121', '技术部', '10', '2021-05-08 14:35:55', '超级管理员', NULL, '', '1', 45);
INSERT INTO `sta` VALUES ('27172233692577803', '程序猿', '男', '15231533323', '2021-05-08', '431126200301123321', '技术部', '10', '2021-05-08 15:05:46', '谭卓子', NULL, '', '', 18);
INSERT INTO `sta` VALUES ('27172233692577813', '马化腾', '男', '15321633333', '2021-05-09', '132123199801230316', '市场部', '20', '2021-05-08 16:33:06', '超级管理员', '2021-05-11 12:07:07', '谭卓子', '1', 50);
INSERT INTO `sta` VALUES ('27172233692577869', '机动车', '女', '15362421222', '2021-05-13', '411301198002141251', '技术部', '10', '2021-05-13 15:03:27', '超级管理员', NULL, '', '', 20);
INSERT INTO `sta` VALUES ('27172233692577912', '鲁智深', '男', '18998632132', '2021-05-17', '431132198304131352', '技术部', '10', '2021-05-17 14:20:47', '王莉莉', NULL, '', '花和尚', 38);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ssbm` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `zjhm` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bmbh` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `lxdh` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('27172233692577797', '超级管理员', 'admin', '管理部', '456123416313523213', 'admin', '50', '15231353111');
INSERT INTO `user` VALUES ('27172233692577834', '测试', '123', '管理部', '433213198001223136', '测试', '50', '18863212321');

SET FOREIGN_KEY_CHECKS = 1;
