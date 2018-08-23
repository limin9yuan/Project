/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : dxcms

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2018-08-22 16:22:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `ct_requirement_plan`
-- ----------------------------
DROP TABLE IF EXISTS `ct_requirement_plan`;
CREATE TABLE `ct_requirement_plan` (
  `ID` bigint(20) NOT NULL,
  `CORPORATION_ID` bigint(20) NOT NULL,
  `REQUIREMENT_PLAN_NO` varchar(20) NOT NULL,
  `STATUS_ID` bigint(20) NOT NULL,
  `OWNER_ID` bigint(20) NOT NULL,
  `PURCHASE_DEPT_ID` bigint(20) NOT NULL,
  `AUTHOR_DEPT_ID` bigint(20) NOT NULL,
  `GUID` bigint(20) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `APPLY_REASON` varchar(255) NOT NULL,
  `STOCK_DATE` datetime NOT NULL,
  `PERIOD_DATE` datetime NOT NULL,
  `MAKE_DATE` datetime NOT NULL,
  `MAKE_USER` varchar(20) NOT NULL,
  `TOTAL_MONEY` decimal(18,5) NOT NULL,
  `BUDGET_MONEY` decimal(18,5) NOT NULL,
  `IS_SUBMIT` bit(1) NOT NULL,
  `IS_APPROVE_BEGIN` bit(1) NOT NULL,
  `IS_APPROVE_FINISH` bit(1) NOT NULL,
  `IS_CONFIRMED` bit(1) NOT NULL,
  `IS_ACTIVED` bit(1) NOT NULL,
  `REMARK` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ct_requirement_plan
-- ----------------------------
