/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : dxcms

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2018-08-22 16:22:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `ct_purchase_allot_plan_item`
-- ----------------------------
DROP TABLE IF EXISTS `ct_purchase_allot_plan_item`;
CREATE TABLE `ct_purchase_allot_plan_item` (
  `ID` bigint(20) NOT NULL,
  `PURCHASE_PLAN_ITEM_ID` bigint(20) NOT NULL,
  `SUPPLIER_ID` bigint(20) NOT NULL,
  `CONTRACT_ID` bigint(20) NOT NULL,
  `MATERIAL_RATIO` decimal(18,4) NOT NULL,
  `MATERIAL_QTY` decimal(18,3) NOT NULL,
  `UNIT_PRICE` decimal(18,5) NOT NULL,
  `SOURCE_TYPE` int(11) NOT NULL,
  `IS_OPTIONAL_ALLOTED` bit(1) NOT NULL,
  `IS_INTENT_PROTOCOL` bit(1) NOT NULL,
  `IS_ACTIVED` bit(1) NOT NULL,
  `REMARK` varchar(200) DEFAULT NULL,
  `RELATIVE_PRICE_BILL_ITEM_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ct_purchase_allot_plan_item
-- ----------------------------
