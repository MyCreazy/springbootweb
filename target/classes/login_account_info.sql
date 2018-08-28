/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : hadoop_dw

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 20/08/2018 17:55:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for login_account_info
-- ----------------------------
DROP TABLE IF EXISTS `login_account_info`;
CREATE TABLE `login_account_info` (
  `keyid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account` varchar(60) NOT NULL DEFAULT '' COMMENT '账号',
  `password` varchar(80) NOT NULL DEFAULT '' COMMENT '密码',
  `loginerflag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0：普通登陆者；1：数仓管理员',
  `isdelete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `addtime` datetime NOT NULL COMMENT '添加时间',
  `modifytime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`keyid`)
) ENGINE=InnoDB AUTO_INCREMENT=272 DEFAULT CHARSET=latin1 COMMENT='登录账号信息表';

-- ----------------------------
-- Records of login_account_info
-- ----------------------------
BEGIN;
INSERT INTO `login_account_info` VALUES (270, 'admin', '123', 1, 0, '2018-08-14 14:55:59', '2018-08-14 14:56:01');
INSERT INTO `login_account_info` VALUES (271, 'user', '123', 0, 0, '2018-08-20 10:34:03', '2018-08-20 10:33:58');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
