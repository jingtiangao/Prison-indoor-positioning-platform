/*
Navicat MySQL Data Transfer

Source Server         : test_db
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : graininsects

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-01-13 11:23:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ls_area
-- ----------------------------
DROP TABLE IF EXISTS `ls_area`;
CREATE TABLE `ls_area` (
  `area_id` int(10) NOT NULL AUTO_INCREMENT,
  `area_name` varchar(30) NOT NULL,
  `x1` int(10) DEFAULT NULL,
  `y1` int(10) DEFAULT NULL,
  `x2` int(10) DEFAULT NULL,
  `y2` int(10) DEFAULT NULL,
  `shape` int(1) DEFAULT NULL,
  `createtime` datetime NOT NULL,
  `remark` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`area_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of ls_area
-- ----------------------------
INSERT INTO `ls_area` VALUES ('1', '1', '1', '1', null, '1', '1', '2016-12-11 22:13:55', '1');

-- ----------------------------
-- Table structure for ls_device
-- ----------------------------
DROP TABLE IF EXISTS `ls_device`;
CREATE TABLE `ls_device` (
  `device_id` int(10) NOT NULL AUTO_INCREMENT,
  `device_mac` varchar(30) NOT NULL,
  `device_code` varchar(30) NOT NULL,
  `createtime` datetime NOT NULL,
  `isvalid` tinyint(1) NOT NULL,
  PRIMARY KEY (`device_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of ls_device
-- ----------------------------
INSERT INTO `ls_device` VALUES ('1', '1', '1', '2016-12-11 21:54:05', '1');
INSERT INTO `ls_device` VALUES ('2', '2', '2', '2016-12-11 22:11:54', '1');
INSERT INTO `ls_device` VALUES ('3', '3', '3', '2016-12-13 12:42:14', '1');
INSERT INTO `ls_device` VALUES ('4', '4', '4', '2016-12-13 12:42:22', '1');

-- ----------------------------
-- Table structure for ls_device_info
-- ----------------------------
DROP TABLE IF EXISTS `ls_device_info`;
CREATE TABLE `ls_device_info` (
  `device_id` int(10) NOT NULL AUTO_INCREMENT,
  `device_mac` varchar(30) NOT NULL DEFAULT '',
  `accuracy` int(5) NOT NULL,
  `build_id` varchar(30) NOT NULL,
  `error_code` int(10) NOT NULL,
  `floor_id` int(10) NOT NULL,
  `info` varchar(100) DEFAULT NULL,
  `nearest_tag_id` varchar(100) DEFAULT NULL,
  `timestamp_millisecond` int(20) NOT NULL,
  `x_millimeter` int(10) NOT NULL,
  `y_millimeter` int(10) NOT NULL,
  `compass` int(5) DEFAULT NULL,
  `alarm` tinyint(1) NOT NULL,
  PRIMARY KEY (`device_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ls_device_info
-- ----------------------------
INSERT INTO `ls_device_info` VALUES ('1', '1', '1', '1', '1', '1', '1', '1', '1', '1000', '500', '1', '1');
INSERT INTO `ls_device_info` VALUES ('3', '3', '3', '3', '3', '3', '3', '3', '3', '3000', '500', '1', '1');

-- ----------------------------
-- Table structure for ls_device_info_history
-- ----------------------------
DROP TABLE IF EXISTS `ls_device_info_history`;
CREATE TABLE `ls_device_info_history` (
  `device_id` int(10) NOT NULL,
  `device_mac` varchar(30) NOT NULL,
  `timestamp_millisecond` int(20) NOT NULL,
  `accuracy` int(5) NOT NULL,
  `build_id` varchar(30) NOT NULL,
  `floor_id` int(10) NOT NULL,
  `error_code` int(10) NOT NULL,
  `nearest_tag_id` varchar(100) DEFAULT NULL,
  `x_millimeter` int(10) NOT NULL,
  `y_millimeter` int(10) NOT NULL,
  `alarm` tinyint(1) NOT NULL,
  `info` varchar(255) DEFAULT NULL,
  `compass` int(11) DEFAULT NULL,
  `user_id` int(10) NOT NULL,
  PRIMARY KEY (`device_id`,`timestamp_millisecond`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of ls_device_info_history
-- ----------------------------

-- ----------------------------
-- Table structure for ls_device_mac_code
-- ----------------------------
DROP TABLE IF EXISTS `ls_device_mac_code`;
CREATE TABLE `ls_device_mac_code` (
  `device_mac` varchar(255) NOT NULL,
  `device_code` varchar(255) NOT NULL,
  PRIMARY KEY (`device_mac`),
  UNIQUE KEY `device_mac` (`device_mac`),
  UNIQUE KEY `device_code` (`device_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ls_device_mac_code
-- ----------------------------

-- ----------------------------
-- Table structure for ls_group_info
-- ----------------------------
DROP TABLE IF EXISTS `ls_group_info`;
CREATE TABLE `ls_group_info` (
  `group_id` int(10) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(30) NOT NULL,
  `max_distance` int(10) NOT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ls_group_info
-- ----------------------------
INSERT INTO `ls_group_info` VALUES ('1', 'one', '1', '2016-12-11 22:13:28');
INSERT INTO `ls_group_info` VALUES ('2', 'two', '2', '2016-12-11 22:19:20');

-- ----------------------------
-- Table structure for ls_manager
-- ----------------------------
DROP TABLE IF EXISTS `ls_manager`;
CREATE TABLE `ls_manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `telephone` varchar(11) NOT NULL,
  `password` varchar(20) NOT NULL,
  `register_time` datetime NOT NULL,
  `isvalid` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of ls_manager
-- ----------------------------

-- ----------------------------
-- Table structure for ls_region
-- ----------------------------
DROP TABLE IF EXISTS `ls_region`;
CREATE TABLE `ls_region` (
  `region_id` int(10) NOT NULL AUTO_INCREMENT,
  `regionname` varchar(30) NOT NULL,
  `manager` varchar(30) DEFAULT NULL,
  `createtime` datetime NOT NULL,
  `remark` varchar(300) DEFAULT NULL,
  `region_name` varchar(30) NOT NULL,
  PRIMARY KEY (`region_id`),
  UNIQUE KEY `region_name` (`region_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of ls_region
-- ----------------------------
INSERT INTO `ls_region` VALUES ('1', '1', '1', '2016-12-11 22:13:44', '1', '');

-- ----------------------------
-- Table structure for ls_user_area
-- ----------------------------
DROP TABLE IF EXISTS `ls_user_area`;
CREATE TABLE `ls_user_area` (
  `user_id` int(10) NOT NULL,
  `area_id` int(10) NOT NULL,
  `starttime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of ls_user_area
-- ----------------------------
INSERT INTO `ls_user_area` VALUES ('1', '1', '2016-12-11 22:14:25', '2016-12-11 22:14:27', '1', '2016-12-11 22:14:29');

-- ----------------------------
-- Table structure for ls_user_info
-- ----------------------------
DROP TABLE IF EXISTS `ls_user_info`;
CREATE TABLE `ls_user_info` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(30) NOT NULL,
  `user_name` varchar(30) NOT NULL,
  `head_image` varchar(255) NOT NULL,
  `stop_time` int(20) DEFAULT NULL,
  `start_time` int(20) NOT NULL,
  `device_id` int(10) NOT NULL,
  `group_id` int(10) NOT NULL,
  `region_id` int(10) NOT NULL,
  `state` int(1) NOT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of ls_user_info
-- ----------------------------
INSERT INTO `ls_user_info` VALUES ('1', '1', '张三', 'http://localhost:8080/grainInsects/upload/image/user3.png', '1', '1', '1', '1', '1', '1', '2016-12-11 22:06:58');
INSERT INTO `ls_user_info` VALUES ('2', '2', '李四', 'http://localhost:8080/grainInsects/upload/image/user4.png', '1', '1', '2', '2', '1', '2', '2016-12-11 22:07:32');
INSERT INTO `ls_user_info` VALUES ('3', '3', '帮主', 'http://localhost:8080/grainInsects/upload/image/user2.png', '1', '1', '3', '1', '3', '3', '2016-12-13 12:44:20');
INSERT INTO `ls_user_info` VALUES ('4', '4', '囚徒', 'http://localhost:8080/grainInsects/upload/image/user1.png', '1', '1', '4', '2', '4', '2', '2016-12-13 12:45:23');

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `username` varchar(10) NOT NULL,
  `roleid` bigint(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(60) DEFAULT NULL,
  `title` varchar(40) DEFAULT NULL,
  `company` varchar(100) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `isLocked` bit(1) DEFAULT NULL,
  `lockedDate` datetime DEFAULT NULL,
  `loginDate` datetime DEFAULT NULL,
  `loginIp` varchar(60) DEFAULT NULL,
  `loginFailureCount` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_enabled` bit(1) DEFAULT NULL,
  `is_locked` bit(1) DEFAULT NULL,
  `locked_date` datetime DEFAULT NULL,
  `login_date` datetime DEFAULT NULL,
  `login_failure_count` int(11) DEFAULT NULL,
  `login_ip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `FK_fk_manager_role` (`roleid`),
  CONSTRAINT `FK_fk_manager_role` FOREIGN KEY (`roleid`) REFERENCES `xx_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('a', '1', 'c4ca4238a0b923820dcc509a6f75849b', 'szy', null, '北京邮电大学', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_admin` VALUES ('ccc', '2', 'c4ca4238a0b923820dcc509a6f75849b', 'ccc', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_admin` VALUES ('dd', '1', 'c4ca4238a0b923820dcc509a6f75849b', 'ee', 'dd', '宿舍', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_admin` VALUES ('ee', '1', 'c4ca4238a0b923820dcc509a6f75849b', '李四', '副教授', '湖南大学', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_admin` VALUES ('ff', '1', 'c4ca4238a0b923820dcc509a6f75849b', 'ff', '教授', '中山大学', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_admin` VALUES ('张三', '1', 'c4ca4238a0b923820dcc509a6f75849b', '张三', 'www', '武汉大学', '前期', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_admin` VALUES ('李四', '1', 'c4ca4238a0b923820dcc509a6f75849b', '李四', null, '普天工业园', null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for t_bintype_pic
-- ----------------------------
DROP TABLE IF EXISTS `t_bintype_pic`;
CREATE TABLE `t_bintype_pic` (
  `grainbin` varchar(60) NOT NULL,
  `large` varchar(255) DEFAULT NULL,
  `medium` varchar(255) DEFAULT NULL,
  `orders` int(11) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  KEY `FKAD1419C17A8D3B03` (`grainbin`),
  CONSTRAINT `FKAD1419C17A8D3B03` FOREIGN KEY (`grainbin`) REFERENCES `t_grainbin` (`lcbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bintype_pic
-- ----------------------------
INSERT INTO `t_bintype_pic` VALUES (' 1111111', 'http://localhost:8080/grainInsects/upload/image/201605/13427726-7972-4fb7-a579-61384cd67458-large.jpg', 'http://localhost:8080/grainInsects/upload/image/201605/13427726-7972-4fb7-a579-61384cd67458-medium.jpg', null, 'http://localhost:8080/grainInsects/upload/image/201605/13427726-7972-4fb7-a579-61384cd67458-source.jpg', 'http://localhost:8080/grainInsects/upload/image/201605/13427726-7972-4fb7-a579-61384cd67458-thumbnail.jpg', '');
INSERT INTO `t_bintype_pic` VALUES (' 18902344', 'http://localhost:8080/grainInsects/upload/image/201605/36985214-1769-46d7-9bbd-38536ab32256-large.jpg', 'http://localhost:8080/grainInsects/upload/image/201605/36985214-1769-46d7-9bbd-38536ab32256-medium.jpg', null, 'http://localhost:8080/grainInsects/upload/image/201605/36985214-1769-46d7-9bbd-38536ab32256-source.jpg', 'http://localhost:8080/grainInsects/upload/image/201605/36985214-1769-46d7-9bbd-38536ab32256-thumbnail.jpg', '');
INSERT INTO `t_bintype_pic` VALUES (' 123334', 'http://localhost:8080/grainInsects/upload/image/201605/6efb3ce7-46be-489c-8952-4ff23abbe6b3-large.jpg', 'http://localhost:8080/grainInsects/upload/image/201605/6efb3ce7-46be-489c-8952-4ff23abbe6b3-medium.jpg', null, 'http://localhost:8080/grainInsects/upload/image/201605/6efb3ce7-46be-489c-8952-4ff23abbe6b3-source.jpg', 'http://localhost:8080/grainInsects/upload/image/201605/6efb3ce7-46be-489c-8952-4ff23abbe6b3-thumbnail.jpg', '');

-- ----------------------------
-- Table structure for t_bin_antidrugkinds
-- ----------------------------
DROP TABLE IF EXISTS `t_bin_antidrugkinds`;
CREATE TABLE `t_bin_antidrugkinds` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `pID` bigint(20) DEFAULT NULL,
  `kind` varchar(60) DEFAULT NULL,
  `densityafter` float DEFAULT NULL,
  `ifsecond` char(1) DEFAULT NULL,
  `othermeasures` varchar(100) DEFAULT NULL,
  `Resistancevalue` float DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_fk_binantidrugkinds_binantiPhosphine` (`pID`),
  CONSTRAINT `FK_fk_binantidrugkinds_binantiPhosphine` FOREIGN KEY (`pID`) REFERENCES `t_bin_antiphosphine` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bin_antidrugkinds
-- ----------------------------

-- ----------------------------
-- Table structure for t_bin_antiphosphine
-- ----------------------------
DROP TABLE IF EXISTS `t_bin_antiphosphine`;
CREATE TABLE `t_bin_antiphosphine` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `lcbm` varchar(60) DEFAULT NULL,
  `Annual` int(11) NOT NULL,
  `device` varchar(100) DEFAULT NULL,
  `CAscale` int(11) DEFAULT NULL,
  `proportion` float DEFAULT NULL,
  `totaldays` int(11) DEFAULT NULL,
  `circulationway` varchar(20) DEFAULT NULL,
  `highestppm` float DEFAULT NULL,
  `targetdays` int(11) DEFAULT NULL,
  `avgtemperature` float DEFAULT NULL,
  `highesttemperature` float DEFAULT NULL,
  `lowesttemperature` float DEFAULT NULL,
  `kindbefca` float DEFAULT NULL,
  `densitybefca` float DEFAULT NULL,
  `kindafterca` float DEFAULT NULL,
  `densityafterca` float DEFAULT NULL,
  `varconcentration` varchar(60) DEFAULT NULL,
  `noninsects` int(11) DEFAULT NULL,
  `ifcage` char(1) DEFAULT NULL,
  `cagekind` varchar(60) DEFAULT NULL,
  `cagenumbef` int(11) DEFAULT NULL,
  `hasliveinsectsafter` char(1) DEFAULT NULL,
  `startdate` date DEFAULT NULL,
  `enddate` date DEFAULT NULL,
  `reporter` varchar(60) DEFAULT NULL,
  `reportdate` date DEFAULT NULL,
  `modifier` varchar(60) DEFAULT NULL,
  `modifytime` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `AK_Key_2` (`lcbm`,`Annual`),
  CONSTRAINT `FK_Reference_73` FOREIGN KEY (`lcbm`) REFERENCES `t_grainbin` (`lcbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bin_antiphosphine
-- ----------------------------

-- ----------------------------
-- Table structure for t_bin_ca
-- ----------------------------
DROP TABLE IF EXISTS `t_bin_ca`;
CREATE TABLE `t_bin_ca` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `lcbm` varchar(60) DEFAULT NULL,
  `Annual` int(11) NOT NULL,
  `Nitrogendevice` float DEFAULT NULL,
  `controlsystem` float DEFAULT NULL,
  `Aircompressor` float DEFAULT NULL,
  `Nitrogenroom` float DEFAULT NULL,
  `Respirator` float DEFAULT NULL,
  `pipleandfee` float DEFAULT NULL,
  `airpump` float DEFAULT NULL,
  `cableandfee` float DEFAULT NULL,
  `Hypoxiaalarm` float DEFAULT NULL,
  `auxiliaryandfee` float DEFAULT NULL,
  `oxygendetectdevice` float DEFAULT NULL,
  `electricityfee` float DEFAULT NULL,
  `chargetime` float DEFAULT NULL,
  `chargepro` varchar(200) DEFAULT NULL,
  `maxconcentration` float DEFAULT NULL,
  `keeplong` float DEFAULT NULL,
  `avggraintemperature` float DEFAULT NULL,
  `highgraintemperature` float DEFAULT NULL,
  `lowestgraintemperture` float DEFAULT NULL,
  `kindbefca` float DEFAULT NULL,
  `densitybefca` float DEFAULT NULL,
  `kindafterca` float DEFAULT NULL,
  `densityafterca` float DEFAULT NULL,
  `varconcentration` varchar(60) DEFAULT NULL,
  `noninsects` int(11) DEFAULT NULL,
  `fattyacidvaluebef` float DEFAULT NULL,
  `fattyacidvalueafter` float DEFAULT NULL,
  `elsecharacter` varchar(100) DEFAULT NULL,
  `startdate` date DEFAULT NULL,
  `enddate` date DEFAULT NULL,
  `reporter` varchar(60) DEFAULT NULL,
  `reportdate` date DEFAULT NULL,
  `modifier` varchar(60) DEFAULT NULL,
  `modifytime` datetime DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `AK_Key_2` (`lcbm`,`Annual`),
  CONSTRAINT `FK_Reference_41` FOREIGN KEY (`lcbm`) REFERENCES `t_grainbin` (`lcbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bin_ca
-- ----------------------------

-- ----------------------------
-- Table structure for t_bin_cleankill
-- ----------------------------
DROP TABLE IF EXISTS `t_bin_cleankill`;
CREATE TABLE `t_bin_cleankill` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `lcbm` varchar(60) DEFAULT NULL,
  `Annual` int(11) NOT NULL,
  `deviceandway` varchar(100) DEFAULT NULL,
  `device` float DEFAULT NULL,
  `brand` varchar(60) DEFAULT NULL,
  `Dosage` float DEFAULT NULL,
  `drugfee` float DEFAULT NULL,
  `protective` float DEFAULT NULL,
  `subsidy` float DEFAULT NULL,
  `keeplong` int(11) DEFAULT NULL,
  `laborfee` float DEFAULT NULL,
  `otherfee` float DEFAULT NULL,
  `applymethod` varchar(200) DEFAULT NULL,
  `applyonsurface` float DEFAULT NULL,
  `otherdeal` varchar(200) DEFAULT NULL,
  `avggraintemperature` float DEFAULT NULL,
  `densitybef` float DEFAULT NULL,
  `kindbef` varchar(100) DEFAULT NULL,
  `densityafter` float DEFAULT NULL,
  `noninsects` int(11) DEFAULT NULL,
  `startdate` date DEFAULT NULL,
  `enddate` date DEFAULT NULL,
  `reporter` varchar(20) DEFAULT NULL,
  `reportdate` date DEFAULT NULL,
  `modifier` varchar(20) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  `drug` varchar(100) DEFAULT NULL,
  `drugfactory` varchar(100) DEFAULT NULL,
  `kindaf` varchar(100) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `cssname` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `AK_Key_2` (`lcbm`,`Annual`),
  CONSTRAINT `FK_Reference_47` FOREIGN KEY (`lcbm`) REFERENCES `t_grainbin` (`lcbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bin_cleankill
-- ----------------------------

-- ----------------------------
-- Table structure for t_bin_flyline
-- ----------------------------
DROP TABLE IF EXISTS `t_bin_flyline`;
CREATE TABLE `t_bin_flyline` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `lcbm` varchar(60) DEFAULT NULL,
  `Annual` int(11) NOT NULL,
  `deviceandway` varchar(100) DEFAULT NULL,
  `device` float DEFAULT NULL,
  `brand` varchar(60) DEFAULT NULL,
  `Dosage` float DEFAULT NULL,
  `drugfee` float DEFAULT NULL,
  `protective` float DEFAULT NULL,
  `subsidy` float DEFAULT NULL,
  `keeplong` int(11) DEFAULT NULL,
  `laborfee` float DEFAULT NULL,
  `otherfee` float DEFAULT NULL,
  `applymethod` varchar(200) DEFAULT NULL,
  `applyonsurface` float DEFAULT NULL,
  `otherdeal` varchar(200) DEFAULT NULL,
  `avggraintemperature` float DEFAULT NULL,
  `densitybef` float DEFAULT NULL,
  `kindbef` varchar(100) DEFAULT NULL,
  `densityafter` float DEFAULT NULL,
  `noninsects` int(11) DEFAULT NULL,
  `startdate` date DEFAULT NULL,
  `enddate` date DEFAULT NULL,
  `reporter` varchar(20) DEFAULT NULL,
  `reportdate` date DEFAULT NULL,
  `modifier` varchar(20) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `AK_Key_2` (`lcbm`,`Annual`),
  CONSTRAINT `FK_Reference_50` FOREIGN KEY (`lcbm`) REFERENCES `t_grainbin` (`lcbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bin_flyline
-- ----------------------------

-- ----------------------------
-- Table structure for t_bin_inertaerosol
-- ----------------------------
DROP TABLE IF EXISTS `t_bin_inertaerosol`;
CREATE TABLE `t_bin_inertaerosol` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `lcbm` varchar(60) DEFAULT NULL,
  `Annual` int(11) NOT NULL,
  `device` float DEFAULT NULL,
  `brand` varchar(60) DEFAULT NULL,
  `factory` varchar(60) DEFAULT NULL,
  `Dosage` float DEFAULT NULL,
  `drugfee` float DEFAULT NULL,
  `protective` float DEFAULT NULL,
  `subsidy` float DEFAULT NULL,
  `keeplong` int(11) DEFAULT NULL,
  `laborfee` float DEFAULT NULL,
  `otherfee` float DEFAULT NULL,
  `applymethod` varchar(200) DEFAULT NULL,
  `applyonsurface` float DEFAULT NULL,
  `applyeverylayer` float DEFAULT NULL,
  `applyonwhole` float DEFAULT NULL,
  `dealonsurface` float DEFAULT NULL,
  `otherdeal` varchar(200) DEFAULT NULL,
  `avggraintemperature` float DEFAULT NULL,
  `highestgraintemperature` float DEFAULT NULL,
  `lowestgraintemperature` float DEFAULT NULL,
  `densitybef` float DEFAULT NULL,
  `kindbef` varchar(100) DEFAULT NULL,
  `densityafter` float DEFAULT NULL,
  `noninsects` int(11) DEFAULT NULL,
  `fattyvaluebef` float DEFAULT NULL,
  `fattyvalueafter` float DEFAULT NULL,
  `residues` float DEFAULT NULL,
  `elsecharacter` varchar(200) DEFAULT NULL,
  `startdate` date DEFAULT NULL,
  `enddate` date DEFAULT NULL,
  `reporter` varchar(20) DEFAULT NULL,
  `reportdate` date DEFAULT NULL,
  `modifier` varchar(20) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `AK_Key_2` (`lcbm`,`Annual`),
  CONSTRAINT `FK_Reference_75` FOREIGN KEY (`lcbm`) REFERENCES `t_grainbin` (`lcbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bin_inertaerosol
-- ----------------------------

-- ----------------------------
-- Table structure for t_bin_insects
-- ----------------------------
DROP TABLE IF EXISTS `t_bin_insects`;
CREATE TABLE `t_bin_insects` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `sm` bigint(20) DEFAULT NULL,
  `kinds` varchar(100) NOT NULL,
  `density` float DEFAULT NULL,
  `foundloc` varchar(60) NOT NULL,
  `damageloc` varchar(60) NOT NULL,
  `startinsectsdate` varchar(10) NOT NULL,
  `startkilldate` varchar(10) NOT NULL,
  `endkilldate` varchar(10) NOT NULL,
  `noninsectsdate` int(11) NOT NULL,
  `killdifficultlevel` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_fk_fchc_fcdzb` (`sm`),
  CONSTRAINT `FK_fk_fchc_fcdzb` FOREIGN KEY (`sm`) REFERENCES `t_bin_invest` (`sm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bin_insects
-- ----------------------------

-- ----------------------------
-- Table structure for t_bin_invest
-- ----------------------------
DROP TABLE IF EXISTS `t_bin_invest`;
CREATE TABLE `t_bin_invest` (
  `sm` bigint(20) NOT NULL AUTO_INCREMENT,
  `lcbm` varchar(60) NOT NULL,
  `Annual` int(11) NOT NULL,
  `reporter` varchar(20) DEFAULT NULL,
  `reportdate` varchar(10) DEFAULT NULL,
  `modifer` varchar(20) DEFAULT NULL,
  `modifydate` varchar(10) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sm`),
  KEY `AK_Key_2` (`lcbm`,`Annual`),
  CONSTRAINT `FK_Reference_39` FOREIGN KEY (`lcbm`) REFERENCES `t_grainbin` (`lcbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bin_invest
-- ----------------------------

-- ----------------------------
-- Table structure for t_bin_invest_tbin_insectses
-- ----------------------------
DROP TABLE IF EXISTS `t_bin_invest_tbin_insectses`;
CREATE TABLE `t_bin_invest_tbin_insectses` (
  `t_bin_invest` bigint(20) NOT NULL,
  `tbin_insectses` bigint(20) NOT NULL,
  UNIQUE KEY `tbin_insectses` (`tbin_insectses`),
  KEY `FK9BF14D94CA6E84DA` (`tbin_insectses`),
  KEY `FK9BF14D949F42778B` (`t_bin_invest`),
  CONSTRAINT `FK9BF14D949F42778B` FOREIGN KEY (`t_bin_invest`) REFERENCES `t_bin_invest` (`sm`),
  CONSTRAINT `FK9BF14D94CA6E84DA` FOREIGN KEY (`tbin_insectses`) REFERENCES `t_bin_insects` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bin_invest_tbin_insectses
-- ----------------------------

-- ----------------------------
-- Table structure for t_bin_invest_tbin_use_drugs
-- ----------------------------
DROP TABLE IF EXISTS `t_bin_invest_tbin_use_drugs`;
CREATE TABLE `t_bin_invest_tbin_use_drugs` (
  `t_bin_invest` bigint(20) NOT NULL,
  `tbin_use_drugs` bigint(20) NOT NULL,
  UNIQUE KEY `tbin_use_drugs` (`tbin_use_drugs`),
  KEY `FKA7C5473658C16FB8` (`tbin_use_drugs`),
  KEY `FKA7C547369F42778B` (`t_bin_invest`),
  CONSTRAINT `FKA7C5473658C16FB8` FOREIGN KEY (`tbin_use_drugs`) REFERENCES `t_bin_use_drug` (`ID`),
  CONSTRAINT `FKA7C547369F42778B` FOREIGN KEY (`t_bin_invest`) REFERENCES `t_bin_invest` (`sm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bin_invest_tbin_use_drugs
-- ----------------------------

-- ----------------------------
-- Table structure for t_bin_phosphinefumigation
-- ----------------------------
DROP TABLE IF EXISTS `t_bin_phosphinefumigation`;
CREATE TABLE `t_bin_phosphinefumigation` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `lcbm` varchar(60) DEFAULT NULL,
  `Annual` int(11) NOT NULL,
  `fansys` float DEFAULT NULL,
  `brand` varchar(100) DEFAULT NULL,
  `Dosage` float DEFAULT NULL,
  `drugfee` float DEFAULT NULL,
  `breathing` float DEFAULT NULL,
  `subsidy` float DEFAULT NULL,
  `pump` float DEFAULT NULL,
  `otherfee` float DEFAULT NULL,
  `alarmer` float DEFAULT NULL,
  `detecter` float DEFAULT NULL,
  `powerfee` float DEFAULT NULL,
  `elsefee` float DEFAULT NULL,
  `totaldays` int(11) DEFAULT NULL,
  `Generator` char(1) DEFAULT NULL,
  `inbin` char(1) DEFAULT NULL,
  `giver` char(1) DEFAULT NULL,
  `fancrossing` char(1) DEFAULT NULL,
  `othergiveway` varchar(60) DEFAULT NULL,
  `circulationway` varchar(20) DEFAULT NULL,
  `highestppm` float DEFAULT NULL,
  `targetdays` int(11) DEFAULT NULL,
  `avgtemperature` float DEFAULT NULL,
  `highesttemperature` float DEFAULT NULL,
  `lowesttemperature` float DEFAULT NULL,
  `kindbefca` float DEFAULT NULL,
  `densitybefca` float DEFAULT NULL,
  `kindafterca` varchar(60) DEFAULT NULL,
  `densityafterca` float DEFAULT NULL,
  `varconcentration` varchar(60) DEFAULT NULL,
  `noninsects` int(11) DEFAULT NULL,
  `ifcage` char(1) DEFAULT NULL,
  `cagekind` varchar(60) DEFAULT NULL,
  `cagenum` int(11) DEFAULT NULL,
  `hasliveInsectsafter` char(1) DEFAULT NULL,
  `fattyvaluebef` float DEFAULT NULL,
  `fattyvalueafter` float DEFAULT NULL,
  `elsecharacter` varchar(100) DEFAULT NULL,
  `startdate` date DEFAULT NULL,
  `enddate` date DEFAULT NULL,
  `reporter` varchar(60) DEFAULT NULL,
  `reportdate` date DEFAULT NULL,
  `modifier` varchar(60) DEFAULT NULL,
  `modifytime` datetime DEFAULT NULL,
  `haslive_insectsafter` char(1) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `AK_Key_2` (`lcbm`,`Annual`),
  CONSTRAINT `FK_Reference_44` FOREIGN KEY (`lcbm`) REFERENCES `t_grainbin` (`lcbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bin_phosphinefumigation
-- ----------------------------

-- ----------------------------
-- Table structure for t_bin_preprotect
-- ----------------------------
DROP TABLE IF EXISTS `t_bin_preprotect`;
CREATE TABLE `t_bin_preprotect` (
  `sm` bigint(20) NOT NULL,
  `checkbeforeinput` char(1) DEFAULT NULL,
  `clearbinkill` char(1) DEFAULT NULL,
  `checkafteroutput` char(1) DEFAULT NULL,
  `inputcheck` char(1) DEFAULT NULL,
  `breakagerate` float DEFAULT NULL,
  `watercontent` float DEFAULT NULL,
  `Noperfect` float DEFAULT NULL,
  `impurity` float DEFAULT NULL,
  `acceptelse` varchar(60) DEFAULT NULL,
  `flynet` char(1) DEFAULT NULL,
  `flyline` char(1) DEFAULT NULL,
  `detectfrequency` varchar(60) DEFAULT NULL,
  `direct` char(1) DEFAULT NULL,
  `sample` char(1) DEFAULT NULL,
  `trap` char(1) DEFAULT NULL,
  `elsedetect` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`sm`),
  CONSTRAINT `FK_Reference_62` FOREIGN KEY (`sm`) REFERENCES `t_bin_invest` (`sm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bin_preprotect
-- ----------------------------

-- ----------------------------
-- Table structure for t_bin_protect
-- ----------------------------
DROP TABLE IF EXISTS `t_bin_protect`;
CREATE TABLE `t_bin_protect` (
  `sm` bigint(20) NOT NULL,
  `flynet` char(1) NOT NULL,
  `flyline` char(1) NOT NULL,
  `protectant` char(1) DEFAULT NULL,
  `Inertpowder` char(1) DEFAULT NULL,
  `sanitary` char(1) DEFAULT NULL,
  `otherpreprotectway` varchar(60) DEFAULT NULL,
  `isolateprotect` char(1) DEFAULT NULL,
  `Fumigation` char(1) DEFAULT NULL,
  `controlledatmosphere` char(1) DEFAULT NULL,
  `frozen` char(1) DEFAULT NULL,
  `highertemperature` char(1) DEFAULT NULL,
  `Radiation` char(1) DEFAULT NULL,
  `Biologicalcontrol` char(1) DEFAULT NULL,
  `Insecticidaldevice` char(1) DEFAULT NULL,
  `otherkillway` varchar(60) DEFAULT NULL,
  `Generator` char(1) DEFAULT NULL,
  `inbin` char(1) DEFAULT NULL,
  `giver` char(1) DEFAULT NULL,
  `fancrossing` char(1) DEFAULT NULL,
  `othergiveway` varchar(60) DEFAULT NULL,
  `Artificialscreening` char(1) NOT NULL,
  `Carbontubetrap` char(1) DEFAULT NULL,
  `Corrugatedpapertraps` char(1) DEFAULT NULL,
  `Frequencyvibrationlighttrap` char(1) DEFAULT NULL,
  `otherdetectway` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`sm`),
  CONSTRAINT `FK_Reference_31` FOREIGN KEY (`sm`) REFERENCES `t_bin_invest` (`sm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bin_protect
-- ----------------------------

-- ----------------------------
-- Table structure for t_bin_protected
-- ----------------------------
DROP TABLE IF EXISTS `t_bin_protected`;
CREATE TABLE `t_bin_protected` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `lcbm` varchar(60) DEFAULT NULL,
  `Annual` int(11) NOT NULL,
  `device` float DEFAULT NULL,
  `brand` varchar(60) DEFAULT NULL,
  `factory` varchar(60) DEFAULT NULL,
  `Dosage` float DEFAULT NULL,
  `drugfee` float DEFAULT NULL,
  `protective` float DEFAULT NULL,
  `subsidy` float DEFAULT NULL,
  `keeplong` int(11) DEFAULT NULL,
  `laborfee` float DEFAULT NULL,
  `otherfee` float DEFAULT NULL,
  `applymethod` varchar(200) DEFAULT NULL,
  `applyonsurface` float DEFAULT NULL,
  `applyeverylayer` float DEFAULT NULL,
  `applyonwhole` float DEFAULT NULL,
  `dealonsurface` float DEFAULT NULL,
  `otherdeal` varchar(200) DEFAULT NULL,
  `avggraintemperature` float DEFAULT NULL,
  `highestgraintemperature` float DEFAULT NULL,
  `lowestgraintemperature` float DEFAULT NULL,
  `densitybef` float DEFAULT NULL,
  `kindbef` varchar(100) DEFAULT NULL,
  `densityafter` float DEFAULT NULL,
  `noninsects` int(11) DEFAULT NULL,
  `fattyvaluebef` float DEFAULT NULL,
  `fattyvalueafter` float DEFAULT NULL,
  `residues` float DEFAULT NULL,
  `elsecharacter` varchar(200) DEFAULT NULL,
  `startdate` date DEFAULT NULL,
  `enddate` date DEFAULT NULL,
  `reporter` varchar(20) DEFAULT NULL,
  `reportdate` date DEFAULT NULL,
  `modifier` varchar(20) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `AK_Key_2` (`lcbm`,`Annual`),
  CONSTRAINT `FK_Reference_45` FOREIGN KEY (`lcbm`) REFERENCES `t_grainbin` (`lcbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bin_protected
-- ----------------------------

-- ----------------------------
-- Table structure for t_bin_use_drug
-- ----------------------------
DROP TABLE IF EXISTS `t_bin_use_drug`;
CREATE TABLE `t_bin_use_drug` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `sm` bigint(20) DEFAULT NULL,
  `drugname` varchar(60) DEFAULT NULL,
  `factory` varchar(60) DEFAULT NULL,
  `value` float DEFAULT NULL,
  `usemethod` varchar(100) DEFAULT NULL,
  `protectkind` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_fk_fcsyyj_fcdcb` (`sm`),
  CONSTRAINT `FK_fk_fcsyyj_fcdcb` FOREIGN KEY (`sm`) REFERENCES `t_bin_invest` (`sm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bin_use_drug
-- ----------------------------

-- ----------------------------
-- Table structure for t_catalogindex_protectprocess
-- ----------------------------
DROP TABLE IF EXISTS `t_catalogindex_protectprocess`;
CREATE TABLE `t_catalogindex_protectprocess` (
  `catalog_indexs` bigint(20) NOT NULL,
  `t_preventprocesses` varchar(10) NOT NULL,
  PRIMARY KEY (`catalog_indexs`,`t_preventprocesses`),
  KEY `FKA5D7B81B28C54410` (`t_preventprocesses`),
  KEY `FKA5D7B81BEE211CC9` (`catalog_indexs`),
  CONSTRAINT `FKA5D7B81B28C54410` FOREIGN KEY (`t_preventprocesses`) REFERENCES `t_preventprocess` (`sm`),
  CONSTRAINT `FKA5D7B81BEE211CC9` FOREIGN KEY (`catalog_indexs`) REFERENCES `t_catalog_index` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_catalogindex_protectprocess
-- ----------------------------

-- ----------------------------
-- Table structure for t_catalog_icon
-- ----------------------------
DROP TABLE IF EXISTS `t_catalog_icon`;
CREATE TABLE `t_catalog_icon` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL,
  `cssname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_catalog_icon
-- ----------------------------
INSERT INTO `t_catalog_icon` VALUES ('1', '暗褐郭公虫', 'anheguogongchong');
INSERT INTO `t_catalog_icon` VALUES ('2', '白带圆皮蠹', 'baidaiyuanpidu');
INSERT INTO `t_catalog_icon` VALUES ('3', '白腹皮蠹', 'baifupidu');
INSERT INTO `t_catalog_icon` VALUES ('4', '巴西豆象', 'baxidouxiang');
INSERT INTO `t_catalog_icon` VALUES ('5', '毕氏粉盗', 'bishifendao');
INSERT INTO `t_catalog_icon` VALUES ('6', '波纹毛皮蠹', 'bowenmaopidu');
INSERT INTO `t_catalog_icon` VALUES ('7', '波纹皮蠹', 'bowenpidu');
INSERT INTO `t_catalog_icon` VALUES ('8', '波纹蕈甲', 'bowenxunjia');
INSERT INTO `t_catalog_icon` VALUES ('9', '伯氏嗜木螨', 'boshishimuman');
INSERT INTO `t_catalog_icon` VALUES ('10', '菜豆象', 'laidouxiang');
INSERT INTO `t_catalog_icon` VALUES ('11', '蚕豆象', 'candouxiang');
INSERT INTO `t_catalog_icon` VALUES ('12', '仓储阎虫', 'cangchuyanchong');
INSERT INTO `t_catalog_icon` VALUES ('13', '仓潜', 'cangqian');
INSERT INTO `t_catalog_icon` VALUES ('14', '赤颈郭公虫', 'chijingguogongchong');
INSERT INTO `t_catalog_icon` VALUES ('15', '赤毛皮蠹', 'chimaopidu');
INSERT INTO `t_catalog_icon` VALUES ('16', '赤拟谷盗', 'chinigudao');
INSERT INTO `t_catalog_icon` VALUES ('17', '赤足郭公虫', 'chizuguogongchong');
INSERT INTO `t_catalog_icon` VALUES ('18', '粗脚粉螨', 'cujiaofenman');
INSERT INTO `t_catalog_icon` VALUES ('19', '大谷盗', 'dagudao');
INSERT INTO `t_catalog_icon` VALUES ('20', '大谷蠹', 'dagudu');
INSERT INTO `t_catalog_icon` VALUES ('21', '大理窃蠹', 'daliqiedu');
INSERT INTO `t_catalog_icon` VALUES ('22', '大眼锯谷盗', 'dayanjugudao');
INSERT INTO `t_catalog_icon` VALUES ('23', '大腋露尾甲', 'dayeluweijia');
INSERT INTO `t_catalog_icon` VALUES ('24', '地中海螟', 'dingzhonghaiming');
INSERT INTO `t_catalog_icon` VALUES ('25', '东方薪甲', 'dongfangxinjia');
INSERT INTO `t_catalog_icon` VALUES ('26', '短角露尾甲', 'duanjiaoluweijia');
INSERT INTO `t_catalog_icon` VALUES ('27', '断镰螯螨', 'duanlianaoman');
INSERT INTO `t_catalog_icon` VALUES ('28', '二带黑菌虫', 'erdaiheijunchong');
INSERT INTO `t_catalog_icon` VALUES ('29', '粉斑螟', 'fenbanming');
INSERT INTO `t_catalog_icon` VALUES ('30', '粉尘螨', 'fenchenman');
INSERT INTO `t_catalog_icon` VALUES ('31', '弗氏无爪螨', 'fushiwuzhuaman');
INSERT INTO `t_catalog_icon` VALUES ('32', '腐食酪螨', 'fushilaoman');
INSERT INTO `t_catalog_icon` VALUES ('33', '甘薯小象虫', 'ganshuxiaoxiangchong');
INSERT INTO `t_catalog_icon` VALUES ('34', '干果露尾甲', 'ganguoluweijia');
INSERT INTO `t_catalog_icon` VALUES ('35', '干向酪螨', 'ganxianglaoman');
INSERT INTO `t_catalog_icon` VALUES ('36', '拱殖嗜渣螨', 'gongzhishizhaman');
INSERT INTO `t_catalog_icon` VALUES ('37', '钩纹皮蠹', 'gouwenpidu');
INSERT INTO `t_catalog_icon` VALUES ('38', '谷斑皮蠹', 'gubanpidu');
INSERT INTO `t_catalog_icon` VALUES ('39', '谷蠹', 'gudu');
INSERT INTO `t_catalog_icon` VALUES ('40', '谷蛾', 'gue');
INSERT INTO `t_catalog_icon` VALUES ('41', '谷跗线螨', 'gufuxianman');
INSERT INTO `t_catalog_icon` VALUES ('42', '谷象', 'guxiang');
INSERT INTO `t_catalog_icon` VALUES ('43', '害嗜鳞螨', 'haishilinman');
INSERT INTO `t_catalog_icon` VALUES ('44', '河野脂螨', 'heyezhiman');
INSERT INTO `t_catalog_icon` VALUES ('45', '赫氏蒲螨', 'heshipuman');
INSERT INTO `t_catalog_icon` VALUES ('46', '褐粉蠹', 'hefendu');
INSERT INTO `t_catalog_icon` VALUES ('47', '褐蕈甲', 'hexunjia');
INSERT INTO `t_catalog_icon` VALUES ('48', '褐蛛甲', 'hezhujia');
INSERT INTO `t_catalog_icon` VALUES ('49', '黑矮阎虫', 'heiaiyanchong');
INSERT INTO `t_catalog_icon` VALUES ('50', '黑粉虫', 'heifenchong');
INSERT INTO `t_catalog_icon` VALUES ('51', '黑菌虫', 'heijunchong');
INSERT INTO `t_catalog_icon` VALUES ('52', '黑毛皮蠹', 'heimaopidu');
INSERT INTO `t_catalog_icon` VALUES ('53', '红带皮蠹', 'hongdaipidu');
INSERT INTO `t_catalog_icon` VALUES ('54', '红颈小蕈甲', 'hongjingxiaoxunjia');
INSERT INTO `t_catalog_icon` VALUES ('55', '花斑皮蠹', 'huabanpidu');
INSERT INTO `t_catalog_icon` VALUES ('56', '滑菌甲螨', 'huajunjiaman');
INSERT INTO `t_catalog_icon` VALUES ('57', '黄斑露尾甲', 'huangbanluweijia');
INSERT INTO `t_catalog_icon` VALUES ('58', '黄粉虫', 'huangfenchong');
INSERT INTO `t_catalog_icon` VALUES ('59', '灰豆象', 'huidouxiang');
INSERT INTO `t_catalog_icon` VALUES ('60', '火腿皮蠹', 'huotuipidu');
INSERT INTO `t_catalog_icon` VALUES ('61', '姬粉盗', 'jifendao');
INSERT INTO `t_catalog_icon` VALUES ('62', '脊胸露尾甲', 'jixiongluweijia');
INSERT INTO `t_catalog_icon` VALUES ('63', '家食甜螨', 'jiashitianman');
INSERT INTO `t_catalog_icon` VALUES ('64', '锯谷盗', 'jugudao');
INSERT INTO `t_catalog_icon` VALUES ('65', '菌食嗜菌螨', 'junshishijunman');
INSERT INTO `t_catalog_icon` VALUES ('66', '咖啡豆象', 'kafeidouxiang');
INSERT INTO `t_catalog_icon` VALUES ('67', '阔鼻谷象', 'kuobiguxiang');
INSERT INTO `t_catalog_icon` VALUES ('68', '阔角谷盗', 'kuojiaogudao');
INSERT INTO `t_catalog_icon` VALUES ('69', '酪阳厉螨', 'laoyangliman');
INSERT INTO `t_catalog_icon` VALUES ('70', '鳞翅触足螨', 'linchichuzuman');
INSERT INTO `t_catalog_icon` VALUES ('71', '鳞毛粉蠹', 'linmaofendu');
INSERT INTO `t_catalog_icon` VALUES ('72', '隆胸露尾甲', 'longxiongluweijia');
INSERT INTO `t_catalog_icon` VALUES ('73', '裸蛛甲', 'luozhujia');
INSERT INTO `t_catalog_icon` VALUES ('74', '绿豆象', 'lvdouxiang');
INSERT INTO `t_catalog_icon` VALUES ('75', '马六甲肉食螨', 'maliujiaroushiman');
INSERT INTO `t_catalog_icon` VALUES ('76', '麦蛾', 'maie');
INSERT INTO `t_catalog_icon` VALUES ('77', '毛蕈甲', 'maoxunjia');
INSERT INTO `t_catalog_icon` VALUES ('78', '蒙古沙潜', 'menggushaqian');
INSERT INTO `t_catalog_icon` VALUES ('79', '米扁虫', 'mibianchong');
INSERT INTO `t_catalog_icon` VALUES ('80', '米淡墨虫', 'midanmochong');
INSERT INTO `t_catalog_icon` VALUES ('81', '米蛾', 'mie');
INSERT INTO `t_catalog_icon` VALUES ('82', '米象', 'mixiang');
INSERT INTO `t_catalog_icon` VALUES ('83', '棉兰皱皮螨', 'mianlanzhoupiman');
INSERT INTO `t_catalog_icon` VALUES ('84', '纳氏皱皮螨', 'nashizhoupiman');
INSERT INTO `t_catalog_icon` VALUES ('85', '拟白腹皮蠹', 'nibaifupidu');
INSERT INTO `t_catalog_icon` VALUES ('86', '拟裸蛛甲', 'niluozhujia');
INSERT INTO `t_catalog_icon` VALUES ('87', '鸟翼巨须螨', 'niaoyijuxuman');
INSERT INTO `t_catalog_icon` VALUES ('88', '牛真扇毛螨', 'niuzhenshanmaoman');
INSERT INTO `t_catalog_icon` VALUES ('89', '普通肉食螨', 'putongroushiman');
INSERT INTO `t_catalog_icon` VALUES ('90', '青蓝郭公虫', 'qinglanguogongchong');
INSERT INTO `t_catalog_icon` VALUES ('91', '日本琵琶甲', 'ribenpipajia');
INSERT INTO `t_catalog_icon` VALUES ('92', '日本蛛甲', 'ribenzhujia');
INSERT INTO `t_catalog_icon` VALUES ('93', '沙潜', 'shaqian');
INSERT INTO `t_catalog_icon` VALUES ('94', '深沟粉盗', 'shengoufendao');
INSERT INTO `t_catalog_icon` VALUES ('95', '湿薪甲', 'shixinjia');
INSERT INTO `t_catalog_icon` VALUES ('96', '食虫狭螨', 'baxidouxiang');
INSERT INTO `t_catalog_icon` VALUES ('97', '瘦隐甲', 'shouyinjia');
INSERT INTO `t_catalog_icon` VALUES ('98', '书窃蠹', 'shuqiedu');
INSERT INTO `t_catalog_icon` VALUES ('99', '双齿锯谷盗', 'shuangchijugudao');
INSERT INTO `t_catalog_icon` VALUES ('100', '双齿长蠹', 'shuangchichangdu');
INSERT INTO `t_catalog_icon` VALUES ('101', '水芋根螨', 'shuiyugenman');
INSERT INTO `t_catalog_icon` VALUES ('102', '四点谷蛾', 'sidiangue');
INSERT INTO `t_catalog_icon` VALUES ('103', '四行薪甲', 'sixingxinjia');
INSERT INTO `t_catalog_icon` VALUES ('104', '四纹豆象', 'siwendouxiang');
INSERT INTO `t_catalog_icon` VALUES ('105', '四纹露尾甲', 'siwenluweijia');
INSERT INTO `t_catalog_icon` VALUES ('106', '速生薄口螨', 'sushengbokouman');
INSERT INTO `t_catalog_icon` VALUES ('107', '甜果螨', 'tianguoman');
INSERT INTO `t_catalog_icon` VALUES ('108', '头角薪甲', 'toujiaoxinjia');
INSERT INTO `t_catalog_icon` VALUES ('109', '土耳其扁谷盗', 'tuerqibiangudao');
INSERT INTO `t_catalog_icon` VALUES ('110', '椭圆食粉螨', 'tuoyuanshifenman');
INSERT INTO `t_catalog_icon` VALUES ('111', '椭圆薪甲', 'tuoyuanxinjia');
INSERT INTO `t_catalog_icon` VALUES ('112', '豌豆象', 'wandouxaing');
INSERT INTO `t_catalog_icon` VALUES ('113', '屋尘螨', 'wuchenman');
INSERT INTO `t_catalog_icon` VALUES ('114', '无色书虱', 'wuseshushi');
INSERT INTO `t_catalog_icon` VALUES ('115', '细颈露尾甲', 'xijingluweijia');
INSERT INTO `t_catalog_icon` VALUES ('116', '暹逻谷盗', 'xianluogudao');
INSERT INTO `t_catalog_icon` VALUES ('117', '小斑螟', 'xiaobanming');
INSERT INTO `t_catalog_icon` VALUES ('118', '小粉盗', 'xiaofendao');
INSERT INTO `t_catalog_icon` VALUES ('119', '小菌虫', 'xiaojunchong');
INSERT INTO `t_catalog_icon` VALUES ('120', '小蕈甲', 'xiaoxunjia');
INSERT INTO `t_catalog_icon` VALUES ('121', '小隐甲', 'xiaoyinjia');
INSERT INTO `t_catalog_icon` VALUES ('122', '小圆虫', 'xiaoyuanchong');
INSERT INTO `t_catalog_icon` VALUES ('123', '小圆皮蠹', 'xiaoyuanpidu');
INSERT INTO `t_catalog_icon` VALUES ('124', '锈赤扁谷盗', 'xiuchibiangudao');
INSERT INTO `t_catalog_icon` VALUES ('125', '亚扁粉盗', 'yabianfendao');
INSERT INTO `t_catalog_icon` VALUES ('126', '烟草甲', 'yancaojia');
INSERT INTO `t_catalog_icon` VALUES ('127', '烟草螟', 'yancaoming');
INSERT INTO `t_catalog_icon` VALUES ('128', '阳罩单梳螨', 'yangzhaodanshuman');
INSERT INTO `t_catalog_icon` VALUES ('129', '洋虫', 'yangchong');
INSERT INTO `t_catalog_icon` VALUES ('130', '药材甲', 'yaocaijia');
INSERT INTO `t_catalog_icon` VALUES ('131', '一点谷蛾', 'yidiangue');
INSERT INTO `t_catalog_icon` VALUES ('132', '印度谷螟', 'yinduguming');
INSERT INTO `t_catalog_icon` VALUES ('133', '鹰嘴豆象', 'yingzuidouxiang');
INSERT INTO `t_catalog_icon` VALUES ('134', '玉米象', 'yumixiang');
INSERT INTO `t_catalog_icon` VALUES ('135', '圆锤皮蠹', 'yuanzhuipidu');
INSERT INTO `t_catalog_icon` VALUES ('136', '杂拟谷盗', 'zanigudao');
INSERT INTO `t_catalog_icon` VALUES ('137', '皂荚豆象', 'zaojiadouxiang');
INSERT INTO `t_catalog_icon` VALUES ('138', '长角扁谷盗', 'changjiaobiangudao');
INSERT INTO `t_catalog_icon` VALUES ('139', '长食酪螨', 'changshilaoman');
INSERT INTO `t_catalog_icon` VALUES ('140', '长头谷盗', 'changtougudao');
INSERT INTO `t_catalog_icon` VALUES ('141', '针吸螨', 'zhenximan');
INSERT INTO `t_catalog_icon` VALUES ('142', '栉角窃蠹', 'zhijiaoqiedu');
INSERT INTO `t_catalog_icon` VALUES ('143', '中华垫甲', 'zhonghuadianjia');
INSERT INTO `t_catalog_icon` VALUES ('144', '中华粉蠹', 'zhonghuafendu');
INSERT INTO `t_catalog_icon` VALUES ('145', '中华龙甲', 'zhonghualongjia');
INSERT INTO `t_catalog_icon` VALUES ('146', '中华真扇毛螨', 'zhonghuazhenshanmaoman');
INSERT INTO `t_catalog_icon` VALUES ('147', '竹蠹', 'zhudu');
INSERT INTO `t_catalog_icon` VALUES ('148', '紫斑谷螟', 'zibanguming');
INSERT INTO `t_catalog_icon` VALUES ('149', '棕脊足螨', 'zongjizuman');

-- ----------------------------
-- Table structure for t_catalog_index
-- ----------------------------
DROP TABLE IF EXISTS `t_catalog_index`;
CREATE TABLE `t_catalog_index` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PID` bigint(20) DEFAULT NULL,
  `BM` varchar(30) NOT NULL,
  `FLM` varchar(6) NOT NULL COMMENT '门 \r\n            纲\r\n            目\r\n            科\r\n            属\r\n            种',
  `MC` varchar(60) NOT NULL,
  `ywm` varchar(200) NOT NULL,
  `zylb` varchar(100) DEFAULT NULL COMMENT '检疫害虫\r\n            蛀食害虫\r\n            粉食类\r\n            其他重要害虫',
  `YMC` varchar(200) DEFAULT NULL,
  `TZ` longtext,
  `shxx` longtext,
  `fb` longtext,
  `wh` longtext,
  `keywords` varchar(500) DEFAULT NULL,
  `MS` longtext,
  `source` varchar(100) DEFAULT NULL,
  `modifer` varchar(30) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `auditor` varchar(255) DEFAULT NULL,
  `audittime` varchar(255) DEFAULT NULL,
  `grade` int(11) NOT NULL,
  `hits` bigint(20) NOT NULL,
  `icon_cls` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `is_top` bit(1) DEFAULT NULL,
  `month_hits` bigint(20) NOT NULL,
  `passaudit` bit(1) DEFAULT NULL,
  `tree_path` varchar(255) NOT NULL,
  `week_hits` bigint(20) NOT NULL,
  `auditadvice` longtext,
  PRIMARY KEY (`ID`),
  KEY `AK_Key_2` (`BM`),
  KEY `AK_Key_3` (`MC`),
  KEY `AK_Key_4` (`ywm`),
  KEY `FK_fk_flb_flb` (`PID`),
  CONSTRAINT `FK_fk_flb_flb` FOREIGN KEY (`PID`) REFERENCES `t_catalog_index` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_catalog_index
-- ----------------------------
INSERT INTO `t_catalog_index` VALUES ('2', '9', '23', 'dfa', 'sfa', 'afs', '检疫害虫', null, null, null, null, null, null, null, null, null, null, '2016-08-06 21:44:33', '2016-08-10 16:26:34', 'expert', '2016-08-08', '1', '0', 'anheguogongchong', null, null, '0', '\0', ',9,', '0', null);
INSERT INTO `t_catalog_index` VALUES ('3', '2', 'dsvsa', 'xv', 'zv', 'daf', '检疫害虫', null, null, null, null, null, null, null, null, null, null, '2016-08-07 16:55:51', '2016-08-10 16:26:34', 'expert', '2016-08-08', '2', '0', null, null, null, '0', '\0', ',9,2,', '0', '<p>\r\n	正常发大水解放路撒酒疯教科书江东父老大奖赛反抗军阿联扣分记录老师家搭 \r\n</p>\r\n<p>\r\n	多久啊饭卡积分卡阿斯利康<span style=\"background-color:#E53333;\"><span style=\"color:#006600;background-color:#E56600;\">大飞机三分撒东发风刀霜剑发史可</span>法</span>萨芬萨科 \r\n</p>\r\n<p>\r\n	大水法拮抗剂发大水剑法可 \r\n</p>');
INSERT INTO `t_catalog_index` VALUES ('4', null, 'dsaf', 'adsf', 'asdf', 'dsaf', '蛀食害虫', null, null, 'ddaf', 'adsf', null, null, 'dsfa', null, null, null, '2016-08-08 15:39:53', '2016-08-10 16:10:40', 'expert', '2016-08-08', '0', '0', null, null, null, '0', '', ',', '0', null);
INSERT INTO `t_catalog_index` VALUES ('7', '3', '345', '325', '2345', '3452', '蛀食害虫', null, null, null, null, null, null, null, null, null, null, '2016-08-08 16:55:21', '2016-08-10 16:26:34', 'expert', '2016-08-08', '3', '0', null, null, null, '0', '\0', ',9,2,3,', '0', null);
INSERT INTO `t_catalog_index` VALUES ('8', '14', '阿斯顿发', '235', '435', '234', '检疫害虫', null, null, null, null, null, null, null, null, null, null, '2016-08-08 19:26:04', '2016-08-09 16:13:20', null, null, '1', '0', null, null, null, '0', '\0', ',14,', '0', null);
INSERT INTO `t_catalog_index` VALUES ('9', null, 'df', 'asdf', 'dsaf', 'dsaf', '检疫害虫', null, null, null, null, null, null, null, null, null, null, '2016-08-09 12:43:00', '2016-08-09 12:43:00', null, null, '0', '0', null, null, null, '0', '\0', ',', '0', null);
INSERT INTO `t_catalog_index` VALUES ('10', null, 'afs', 'af', 'asdf', 'asdf', '检疫害虫', null, null, null, null, null, null, null, null, null, null, '2016-08-09 12:43:11', '2016-08-11 23:01:47', null, null, '0', '0', 'baidaiyuanpidu', null, null, '0', '\0', ',', '0', null);
INSERT INTO `t_catalog_index` VALUES ('11', null, '436546', '34', '456', '456', '蛀食害虫', null, null, null, null, null, null, null, null, null, null, '2016-08-09 12:43:22', '2016-08-09 12:43:22', null, null, '0', '0', null, null, null, '0', '\0', ',', '0', null);
INSERT INTO `t_catalog_index` VALUES ('12', '17', '85', '564', '45', '457', '粉食类', null, null, null, null, null, null, null, null, null, null, '2016-08-09 12:43:34', '2016-08-10 16:26:59', null, null, '2', '0', null, null, null, '0', '\0', ',9,17,', '0', null);
INSERT INTO `t_catalog_index` VALUES ('13', null, '7658', '685', '658', '658', '检疫害虫', null, null, null, null, null, null, null, null, null, null, '2016-08-09 12:43:45', '2016-08-09 12:43:45', null, null, '0', '0', null, null, null, '0', '\0', ',', '0', null);
INSERT INTO `t_catalog_index` VALUES ('14', null, 'asf', 'afds', 'saf', 'asfd', '粉食类', null, null, null, null, null, null, null, null, null, null, '2016-08-09 12:44:01', '2016-08-09 12:44:01', null, null, '0', '0', null, null, null, '0', '\0', ',', '0', null);
INSERT INTO `t_catalog_index` VALUES ('15', '11', 'sdfa', 'saf', 'asd', 'asdf', '蛀食害虫', null, null, null, null, null, null, null, null, null, null, '2016-08-09 12:44:22', '2016-08-09 12:44:22', null, null, '1', '0', null, null, null, '0', '\0', ',11,', '0', null);
INSERT INTO `t_catalog_index` VALUES ('16', null, 'asfd', 'saf', 'asdf', 'asdf', '粉食类', null, null, null, null, null, null, null, null, null, null, '2016-08-09 12:44:35', '2016-08-09 12:44:35', null, null, '0', '0', null, null, null, '0', '\0', ',', '0', null);
INSERT INTO `t_catalog_index` VALUES ('17', '9', 'fdg', 'gsd', 'sdfgf', '4', '其他重要害虫', null, null, null, null, null, null, null, null, null, null, '2016-08-09 12:45:30', '2016-08-09 12:45:30', null, null, '1', '0', null, null, null, '0', '\0', ',9,', '0', null);
INSERT INTO `t_catalog_index` VALUES ('18', '10', '456', '456', '456', '456', '蛀食害虫', null, null, null, null, null, null, null, null, null, null, '2016-08-09 12:45:49', '2016-08-09 12:45:49', null, null, '1', '0', null, null, null, '0', '\0', ',10,', '0', null);
INSERT INTO `t_catalog_index` VALUES ('19', null, '1', '1', '1', '1', '检疫害虫', null, null, null, null, null, null, null, null, null, null, '2016-08-10 01:50:55', '2016-08-10 01:50:55', null, null, '0', '0', null, null, null, '0', '\0', ',', '0', null);
INSERT INTO `t_catalog_index` VALUES ('20', null, '2', '2', '2', '2', '蛀食害虫', null, null, null, null, null, null, null, null, null, null, '2016-08-10 01:51:05', '2016-08-10 01:51:05', null, null, '0', '0', null, null, null, '0', '\0', ',', '0', null);
INSERT INTO `t_catalog_index` VALUES ('21', null, '3', '3', '3', '3', '粉食类', null, null, null, null, null, null, null, null, null, null, '2016-08-10 01:51:17', '2016-08-10 01:51:17', null, null, '0', '0', null, null, null, '0', '\0', ',', '0', null);
INSERT INTO `t_catalog_index` VALUES ('22', null, '44', '4', '4', '4', '蛀食害虫', null, null, null, null, null, null, null, null, null, null, '2016-08-10 09:07:44', '2016-08-10 09:07:44', null, null, '0', '0', null, null, null, '0', '\0', ',', '0', null);
INSERT INTO `t_catalog_index` VALUES ('23', null, '5', '5', '5', '5', '粉食类', null, null, null, null, null, null, null, null, null, null, '2016-08-10 09:07:56', '2016-08-10 09:07:56', null, null, '0', '0', null, null, null, '0', '\0', ',', '0', null);
INSERT INTO `t_catalog_index` VALUES ('24', null, '6', '6', '6', '6', '粉食类', null, null, null, null, null, null, null, null, null, null, '2016-08-10 09:08:07', '2016-08-10 09:08:07', null, null, '0', '0', null, null, null, '0', '\0', ',', '0', null);
INSERT INTO `t_catalog_index` VALUES ('25', null, '7', '7', '7', '7', '其他重要害虫', null, null, null, null, null, null, null, null, null, null, '2016-08-10 09:09:29', '2016-08-10 09:09:29', null, null, '0', '0', null, null, null, '0', '\0', ',', '0', null);
INSERT INTO `t_catalog_index` VALUES ('26', '20', '8', '8', '8', '8', '粉食类', null, null, null, null, null, null, null, null, null, null, '2016-08-10 09:09:41', '2016-08-10 09:56:06', null, null, '1', '0', null, null, null, '0', '\0', ',20,', '0', null);
INSERT INTO `t_catalog_index` VALUES ('27', null, '9', '9', '9', '9', '粉食类', null, null, null, null, null, null, null, null, null, null, '2016-08-10 09:09:52', '2016-08-10 09:09:52', null, null, '0', '0', null, null, null, '0', '\0', ',', '0', null);
INSERT INTO `t_catalog_index` VALUES ('28', null, '10', '1', '0', '10', '蛀食害虫', null, null, null, null, null, null, null, null, null, null, '2016-08-10 09:54:55', '2016-08-10 09:54:55', null, null, '0', '0', null, null, null, '0', '\0', ',', '0', null);
INSERT INTO `t_catalog_index` VALUES ('29', null, '11', '11', '11', '11', '蛀食害虫', null, null, null, null, null, null, null, '234', null, null, '2016-08-10 09:55:20', '2016-08-10 09:55:20', null, null, '0', '0', null, null, null, '0', '\0', ',', '0', null);
INSERT INTO `t_catalog_index` VALUES ('30', null, '34', '23', '131', '3', '其他重要害虫', null, null, null, null, null, null, null, null, null, null, '2016-08-10 09:55:33', '2016-08-10 09:55:33', null, null, '0', '0', null, null, null, '0', '\0', ',', '0', null);
INSERT INTO `t_catalog_index` VALUES ('31', null, 'sdf', 'sfd', 'sdaf', 'ds', '蛀食害虫', null, null, null, null, null, null, null, null, null, null, '2016-08-10 09:55:47', '2016-08-10 09:55:47', null, null, '0', '0', null, null, null, '0', '\0', ',', '0', null);
INSERT INTO `t_catalog_index` VALUES ('32', '28', '234', '687', '68', '68', '粉食类', null, null, null, null, null, null, null, null, null, null, '2016-08-10 15:22:47', '2016-08-10 16:22:19', 'expert', '2016-08-10', '1', '0', null, null, null, '0', '', ',28,', '0', null);
INSERT INTO `t_catalog_index` VALUES ('33', '28', '324', '234', '234', '234', '蛀食害虫', null, null, null, null, null, null, null, null, null, null, '2016-08-10 15:23:05', '2016-08-10 15:23:05', null, null, '1', '0', null, null, null, '0', '\0', ',28,', '0', null);
INSERT INTO `t_catalog_index` VALUES ('34', '28', '234', '234', '234', '234', '其他重要害虫', null, null, null, null, null, null, null, null, null, null, '2016-08-10 15:23:21', '2016-08-10 15:23:21', null, null, '1', '0', null, null, null, '0', '\0', ',28,', '0', null);
INSERT INTO `t_catalog_index` VALUES ('35', '28', '456', '456456', '456', '456', '蛀食害虫', null, null, null, null, null, null, null, null, null, null, '2016-08-10 15:24:01', '2016-08-10 16:22:35', 'expert', '2016-08-10', '1', '0', null, null, null, '0', '', ',28,', '0', null);
INSERT INTO `t_catalog_index` VALUES ('36', null, '23221', '123', '123', '大水法', '蛀食害虫', null, null, null, null, null, null, null, null, null, null, '2016-08-14 14:18:28', '2016-08-14 14:18:28', null, null, '0', '0', 'baidaiyuanpidu', null, null, '0', '\0', ',', '0', null);

-- ----------------------------
-- Table structure for t_catalog_pic
-- ----------------------------
DROP TABLE IF EXISTS `t_catalog_pic`;
CREATE TABLE `t_catalog_pic` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PID` bigint(20) DEFAULT NULL,
  `js` varchar(200) DEFAULT NULL,
  `tp` varchar(255) DEFAULT NULL,
  `source` varchar(100) DEFAULT NULL,
  `modifer` varchar(30) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  `tcatalog_index` bigint(20) NOT NULL,
  `large` varchar(255) DEFAULT NULL,
  `medium` varchar(255) DEFAULT NULL,
  `orders` int(11) DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `fromwhere` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_6` (`PID`),
  KEY `FKB247DD19DE9F3D3A` (`tcatalog_index`),
  CONSTRAINT `FKB247DD19DE9F3D3A` FOREIGN KEY (`tcatalog_index`) REFERENCES `t_catalog_index` (`ID`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`PID`) REFERENCES `t_catalog_index` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_catalog_pic
-- ----------------------------

-- ----------------------------
-- Table structure for t_company
-- ----------------------------
DROP TABLE IF EXISTS `t_company`;
CREATE TABLE `t_company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `coding` varchar(10) DEFAULT NULL,
  `company` varchar(100) NOT NULL,
  `modifer` varchar(30) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of t_company
-- ----------------------------

-- ----------------------------
-- Table structure for t_consultation
-- ----------------------------
DROP TABLE IF EXISTS `t_consultation`;
CREATE TABLE `t_consultation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `advicedate` varchar(255) DEFAULT NULL,
  `describle` varchar(255) DEFAULT NULL,
  `expertadvice` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `type` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `expertuser` varchar(10) DEFAULT NULL,
  `experttime` datetime DEFAULT NULL,
  `consulttime` datetime NOT NULL,
  `hasshare` bit(1) NOT NULL,
  `sfzw` bit(1) DEFAULT NULL,
  `zwid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK8CD19874D5B38557` (`expertuser`),
  KEY `FK8CD19874C7033F6A` (`zwid`),
  CONSTRAINT `FK8CD19874C7033F6A` FOREIGN KEY (`zwid`) REFERENCES `t_consultation` (`id`),
  CONSTRAINT `FK8CD19874D5B38557` FOREIGN KEY (`expertuser`) REFERENCES `t_expert` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_consultation
-- ----------------------------

-- ----------------------------
-- Table structure for t_depots_insects
-- ----------------------------
DROP TABLE IF EXISTS `t_depots_insects`;
CREATE TABLE `t_depots_insects` (
  `sm` bigint(20) NOT NULL AUTO_INCREMENT,
  `psm` bigint(20) DEFAULT NULL,
  `kinds` varchar(100) NOT NULL,
  `density` float DEFAULT NULL,
  `foundloc` varchar(60) DEFAULT NULL,
  `damagegrain` varchar(60) DEFAULT NULL,
  `startinsectsdate` varchar(10) DEFAULT NULL,
  `startkilldate` varchar(10) DEFAULT NULL,
  `endkilldate` varchar(10) DEFAULT NULL,
  `noninsectsdate` int(11) DEFAULT NULL,
  `killdifficultlevel` int(11) DEFAULT NULL,
  PRIMARY KEY (`sm`),
  KEY `FK_Reference_63` (`psm`),
  CONSTRAINT `FK_Reference_63` FOREIGN KEY (`psm`) REFERENCES `t_depot_invest` (`sm`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_depots_insects
-- ----------------------------
INSERT INTO `t_depots_insects` VALUES ('1', '5', 'dfag', '12', '12', '12', '', '', '', '3', '2');
INSERT INTO `t_depots_insects` VALUES ('2', '5', 'dfag', '12', '12', '23', '', '', '', '3', '2');
INSERT INTO `t_depots_insects` VALUES ('3', '6', 'adfsdf', '12', '', '', '', '', '', null, null);

-- ----------------------------
-- Table structure for t_depotuser
-- ----------------------------
DROP TABLE IF EXISTS `t_depotuser`;
CREATE TABLE `t_depotuser` (
  `username` varchar(10) NOT NULL,
  `lkbm` varchar(60) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `realname` varchar(60) DEFAULT NULL,
  `title` varchar(40) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `manager` bit(1) NOT NULL DEFAULT b'0' COMMENT '0: 库管员\r\n            1：所长',
  `hasaudit` bit(1) DEFAULT NULL,
  `point` bigint(20) DEFAULT NULL,
  `modifer` varchar(30) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  `roleid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `FK_Reference_58` (`lkbm`),
  KEY `FK_depotuser_role` (`roleid`),
  CONSTRAINT `FK_Reference_58` FOREIGN KEY (`lkbm`) REFERENCES `t_graindepot` (`lkbm`),
  CONSTRAINT `FK_depotuser_role` FOREIGN KEY (`roleid`) REFERENCES `xx_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_depotuser
-- ----------------------------
INSERT INTO `t_depotuser` VALUES ('aa', ' 100002', 'c4ca4238a0b923820dcc509a6f75849b', '张三', '高级工程师', '1360087889', '\0', '', null, null, '2016-05-19 09:32:40', null);
INSERT INTO `t_depotuser` VALUES ('bb', ' 100002', 'c4ca4238a0b923820dcc509a6f75849b', 'bbb', '', '', '', '', null, null, null, null);

-- ----------------------------
-- Table structure for t_depotuser_grainbin
-- ----------------------------
DROP TABLE IF EXISTS `t_depotuser_grainbin`;
CREATE TABLE `t_depotuser_grainbin` (
  `depotusers` varchar(20) NOT NULL,
  `grainbins` varchar(60) NOT NULL,
  PRIMARY KEY (`depotusers`,`grainbins`),
  KEY `FKEAA7D4972360C39E` (`grainbins`),
  KEY `FKEAA7D4973E15473B` (`depotusers`),
  CONSTRAINT `FKEAA7D4972360C39E` FOREIGN KEY (`grainbins`) REFERENCES `t_grainbin` (`lcbm`),
  CONSTRAINT `FKEAA7D4973E15473B` FOREIGN KEY (`depotusers`) REFERENCES `t_depotuser` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_depotuser_grainbin
-- ----------------------------
INSERT INTO `t_depotuser_grainbin` VALUES ('aa', ' 123334');
INSERT INTO `t_depotuser_grainbin` VALUES ('aa', '1000001');
INSERT INTO `t_depotuser_grainbin` VALUES ('bb', ' 1111111');
INSERT INTO `t_depotuser_grainbin` VALUES ('bb', ' 123334');

-- ----------------------------
-- Table structure for t_depot_antidrugkinds
-- ----------------------------
DROP TABLE IF EXISTS `t_depot_antidrugkinds`;
CREATE TABLE `t_depot_antidrugkinds` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `pID` bigint(20) DEFAULT NULL,
  `kind` varchar(60) DEFAULT NULL,
  `densityafter` float DEFAULT NULL,
  `ifsecond` char(1) DEFAULT NULL,
  `othermeasures` varchar(100) DEFAULT NULL,
  `Resistancevalue` float DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_fk_depotantidrugkinds_depotantiPhosphine` (`pID`),
  CONSTRAINT `FK_fk_depotantidrugkinds_depotantiPhosphine` FOREIGN KEY (`pID`) REFERENCES `t_depot_antiphosphine` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_depot_antidrugkinds
-- ----------------------------

-- ----------------------------
-- Table structure for t_depot_antiphosphine
-- ----------------------------
DROP TABLE IF EXISTS `t_depot_antiphosphine`;
CREATE TABLE `t_depot_antiphosphine` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `lkbm` varchar(60) NOT NULL,
  `Annual` int(11) NOT NULL,
  `device` varchar(100) DEFAULT NULL,
  `CAscale` int(11) DEFAULT NULL,
  `proportion` float DEFAULT NULL,
  `totaldays` int(11) DEFAULT NULL,
  `circulationway` varchar(20) DEFAULT NULL,
  `highestppm` float DEFAULT NULL,
  `targetdays` int(11) DEFAULT NULL,
  `avgtemperature` float DEFAULT NULL,
  `highesttemperature` float DEFAULT NULL,
  `lowesttemperature` float DEFAULT NULL,
  `kindbefca` float DEFAULT NULL,
  `densitybefca` float DEFAULT NULL,
  `kindafterca` float DEFAULT NULL,
  `densityafterca` float DEFAULT NULL,
  `varconcentration` varchar(60) DEFAULT NULL,
  `noninsects` int(11) DEFAULT NULL,
  `ifcage` char(1) DEFAULT NULL,
  `cagekind` varchar(60) DEFAULT NULL,
  `cagenumbef` int(11) DEFAULT NULL,
  `hasliveinsectsafter` char(1) DEFAULT NULL,
  `suggestion` longtext,
  `reporter` varchar(60) DEFAULT NULL,
  `reportdate` date DEFAULT NULL,
  `modifier` varchar(60) DEFAULT NULL,
  `modifytime` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `AK_Key_2` (`lkbm`,`Annual`),
  CONSTRAINT `FK_Reference_71` FOREIGN KEY (`lkbm`) REFERENCES `t_graindepot` (`lkbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_depot_antiphosphine
-- ----------------------------

-- ----------------------------
-- Table structure for t_depot_ca
-- ----------------------------
DROP TABLE IF EXISTS `t_depot_ca`;
CREATE TABLE `t_depot_ca` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `lkbm` varchar(60) NOT NULL,
  `Annual` int(11) NOT NULL,
  `factory` varchar(100) DEFAULT NULL,
  `CAscale` int(11) DEFAULT NULL,
  `finishdate` varchar(10) DEFAULT NULL,
  `ifnormalrun` char(1) DEFAULT NULL,
  `problem` longblob,
  `material` varchar(60) DEFAULT NULL,
  `iftest` char(1) DEFAULT NULL,
  `intelligentscale` varchar(60) DEFAULT NULL,
  `intelligentifnoraluse` char(1) DEFAULT NULL,
  `Nitrogendevice` float DEFAULT NULL,
  `controlsystem` float DEFAULT NULL,
  `Aircompressor` float DEFAULT NULL,
  `Nitrogenroom` float DEFAULT NULL,
  `Respirator` float DEFAULT NULL,
  `pipleandfee` float DEFAULT NULL,
  `airpump` float DEFAULT NULL,
  `cableandfee` float DEFAULT NULL,
  `Hypoxiaalarm` float DEFAULT NULL,
  `auxiliaryandfee` float DEFAULT NULL,
  `oxygendetectdevice` float DEFAULT NULL,
  `electricityfee` float DEFAULT NULL,
  `chargetime` float DEFAULT NULL,
  `chargepro` varchar(200) DEFAULT NULL,
  `maxconcentration` float DEFAULT NULL,
  `keeplong` float DEFAULT NULL,
  `avggraintemperature` float DEFAULT NULL,
  `highgraintemperature` float DEFAULT NULL,
  `lowestgraintemperture` float DEFAULT NULL,
  `kindbefca` float DEFAULT NULL,
  `densitybefca` float DEFAULT NULL,
  `kindafterca` float DEFAULT NULL,
  `densityafterca` float DEFAULT NULL,
  `varconcentration` varchar(60) DEFAULT NULL,
  `noninsects` int(11) DEFAULT NULL,
  `fattyacidvaluebef` float DEFAULT NULL,
  `fattyacidvalueafter` float DEFAULT NULL,
  `elsecharacter` varchar(100) DEFAULT NULL,
  `suggestion` longtext,
  `reporter` varchar(60) DEFAULT NULL,
  `reportdate` date DEFAULT NULL,
  `modifier` varchar(60) DEFAULT NULL,
  `modifytime` datetime DEFAULT NULL,
  `sm` bigint(20) NOT NULL,
  `maingrain` varchar(255) DEFAULT NULL,
  `modifer` varchar(255) DEFAULT NULL,
  `modifydate` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `AK_Key_2` (`lkbm`,`Annual`),
  CONSTRAINT `FK_fk_qtzb_lk` FOREIGN KEY (`lkbm`) REFERENCES `t_graindepot` (`lkbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_depot_ca
-- ----------------------------

-- ----------------------------
-- Table structure for t_depot_cleankill
-- ----------------------------
DROP TABLE IF EXISTS `t_depot_cleankill`;
CREATE TABLE `t_depot_cleankill` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `lkbm` varchar(60) NOT NULL,
  `Annual` int(11) NOT NULL,
  `drug` varchar(100) DEFAULT NULL,
  `totaluse` int(11) DEFAULT NULL,
  `proportion` float DEFAULT NULL,
  `deviceandway` varchar(100) DEFAULT NULL,
  `device` float DEFAULT NULL,
  `brand` varchar(60) DEFAULT NULL,
  `Dosage` float DEFAULT NULL,
  `drugfee` float DEFAULT NULL,
  `protective` float DEFAULT NULL,
  `subsidy` float DEFAULT NULL,
  `keeplong` int(11) DEFAULT NULL,
  `laborfee` float DEFAULT NULL,
  `otherfee` float DEFAULT NULL,
  `applymethod` varchar(200) DEFAULT NULL,
  `applyonsurface` float DEFAULT NULL,
  `otherdeal` varchar(200) DEFAULT NULL,
  `avggraintemperature` float DEFAULT NULL,
  `densitybef` float DEFAULT NULL,
  `kindbef` varchar(100) DEFAULT NULL,
  `densityafter` float DEFAULT NULL,
  `noninsects` int(11) DEFAULT NULL,
  `suggestion` longtext,
  `reporter` varchar(20) DEFAULT NULL,
  `reportdate` date DEFAULT NULL,
  `modifier` varchar(20) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  `kindaf` varchar(100) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `AK_Key_2` (`lkbm`,`Annual`),
  CONSTRAINT `FK_Reference_69` FOREIGN KEY (`lkbm`) REFERENCES `t_graindepot` (`lkbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_depot_cleankill
-- ----------------------------

-- ----------------------------
-- Table structure for t_depot_flyline
-- ----------------------------
DROP TABLE IF EXISTS `t_depot_flyline`;
CREATE TABLE `t_depot_flyline` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `lkbm` varchar(60) NOT NULL,
  `Annual` int(11) NOT NULL,
  `drug` varchar(100) DEFAULT NULL,
  `totaluse` int(11) DEFAULT NULL,
  `proportion` float DEFAULT NULL,
  `deviceandway` varchar(100) DEFAULT NULL,
  `device` float DEFAULT NULL,
  `brand` varchar(60) DEFAULT NULL,
  `Dosage` float DEFAULT NULL,
  `drugfee` float DEFAULT NULL,
  `protective` float DEFAULT NULL,
  `subsidy` float DEFAULT NULL,
  `keeplong` int(11) DEFAULT NULL,
  `laborfee` float DEFAULT NULL,
  `otherfee` float DEFAULT NULL,
  `applymethod` varchar(200) DEFAULT NULL,
  `applyonsurface` float DEFAULT NULL,
  `otherdeal` varchar(200) DEFAULT NULL,
  `avggraintemperature` float DEFAULT NULL,
  `densitybef` float DEFAULT NULL,
  `kindbef` varchar(100) DEFAULT NULL,
  `densityafter` float DEFAULT NULL,
  `noninsects` int(11) DEFAULT NULL,
  `suggestion` longtext,
  `reporter` varchar(20) DEFAULT NULL,
  `reportdate` date DEFAULT NULL,
  `modifier` varchar(20) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `AK_Key_2` (`lkbm`,`Annual`),
  CONSTRAINT `FK_Reference_70` FOREIGN KEY (`lkbm`) REFERENCES `t_graindepot` (`lkbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_depot_flyline
-- ----------------------------

-- ----------------------------
-- Table structure for t_depot_graintemperature
-- ----------------------------
DROP TABLE IF EXISTS `t_depot_graintemperature`;
CREATE TABLE `t_depot_graintemperature` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `January` float DEFAULT NULL,
  `February` float DEFAULT NULL,
  `March` float DEFAULT NULL,
  `April` float DEFAULT NULL,
  `May` float DEFAULT NULL,
  `June` float DEFAULT NULL,
  `July` float DEFAULT NULL,
  `August` float DEFAULT NULL,
  `September` float DEFAULT NULL,
  `October` float DEFAULT NULL,
  `November` float DEFAULT NULL,
  `December` float DEFAULT NULL,
  `Reporter` varchar(20) DEFAULT NULL,
  `Modifier` varchar(20) DEFAULT NULL,
  `sm` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK5232DCEFB4EC24BA` (`sm`),
  CONSTRAINT `FK5232DCEFB4EC24BA` FOREIGN KEY (`sm`) REFERENCES `t_depot_invest` (`sm`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_depot_graintemperature
-- ----------------------------
INSERT INTO `t_depot_graintemperature` VALUES ('4', '12', null, '14', null, '25', null, '23', null, '324', null, null, null, null, null, '5');
INSERT INTO `t_depot_graintemperature` VALUES ('5', '12', null, '14', null, '25', null, null, null, null, null, null, null, null, null, '5');
INSERT INTO `t_depot_graintemperature` VALUES ('6', '123', null, null, null, null, null, null, null, null, null, null, null, null, null, '6');

-- ----------------------------
-- Table structure for t_depot_humidity
-- ----------------------------
DROP TABLE IF EXISTS `t_depot_humidity`;
CREATE TABLE `t_depot_humidity` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `sm` bigint(20) DEFAULT NULL,
  `January` float DEFAULT NULL,
  `February` float DEFAULT NULL,
  `March` float DEFAULT NULL,
  `April` float DEFAULT NULL,
  `May` float DEFAULT NULL,
  `June` float DEFAULT NULL,
  `July` float DEFAULT NULL,
  `August` float DEFAULT NULL,
  `September` float DEFAULT NULL,
  `October` float DEFAULT NULL,
  `November` float DEFAULT NULL,
  `December` float DEFAULT NULL,
  `Reporter` varchar(20) DEFAULT NULL,
  `Modifier` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKA711D589B4EC24BA` (`sm`),
  CONSTRAINT `FKA711D589B4EC24BA` FOREIGN KEY (`sm`) REFERENCES `t_depot_invest` (`sm`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_depot_humidity
-- ----------------------------
INSERT INTO `t_depot_humidity` VALUES ('4', '5', '12', null, null, '34', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_depot_humidity` VALUES ('5', '6', null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for t_depot_inertaerosol
-- ----------------------------
DROP TABLE IF EXISTS `t_depot_inertaerosol`;
CREATE TABLE `t_depot_inertaerosol` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `lkbm` varchar(60) NOT NULL,
  `Annual` int(11) NOT NULL,
  `drug` varchar(100) DEFAULT NULL,
  `totaluse` int(11) DEFAULT NULL,
  `proportion` float DEFAULT NULL,
  `deviceandway` varchar(100) DEFAULT NULL,
  `device` float DEFAULT NULL,
  `brand` varchar(60) DEFAULT NULL,
  `Dosage` float DEFAULT NULL,
  `drugfee` float DEFAULT NULL,
  `protective` float DEFAULT NULL,
  `subsidy` float DEFAULT NULL,
  `keeplong` int(11) DEFAULT NULL,
  `laborfee` float DEFAULT NULL,
  `otherfee` float DEFAULT NULL,
  `applymethod` varchar(200) DEFAULT NULL,
  `applyonsurface` float DEFAULT NULL,
  `applyeverylayer` float DEFAULT NULL,
  `applyonwhole` float DEFAULT NULL,
  `dealonsurface` float DEFAULT NULL,
  `otherdeal` varchar(200) DEFAULT NULL,
  `avggraintemperature` float DEFAULT NULL,
  `highestgraintemperature` float DEFAULT NULL,
  `lowestgraintemperature` float DEFAULT NULL,
  `densitybef` float DEFAULT NULL,
  `kindbef` varchar(100) DEFAULT NULL,
  `densityafter` float DEFAULT NULL,
  `noninsects` int(11) DEFAULT NULL,
  `fattyvaluebef` float DEFAULT NULL,
  `fattyvalueafter` float DEFAULT NULL,
  `residues` float DEFAULT NULL,
  `elsecharacter` varchar(200) DEFAULT NULL,
  `suggestion` longtext,
  `reporter` varchar(20) DEFAULT NULL,
  `reportdate` date DEFAULT NULL,
  `modifier` varchar(20) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `AK_Key_2` (`lkbm`,`Annual`),
  CONSTRAINT `FK_Reference_74` FOREIGN KEY (`lkbm`) REFERENCES `t_graindepot` (`lkbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_depot_inertaerosol
-- ----------------------------

-- ----------------------------
-- Table structure for t_depot_invest
-- ----------------------------
DROP TABLE IF EXISTS `t_depot_invest`;
CREATE TABLE `t_depot_invest` (
  `sm` bigint(20) NOT NULL AUTO_INCREMENT,
  `lkbm` varchar(60) NOT NULL,
  `Annual` int(11) DEFAULT NULL,
  `maingrain` varchar(200) DEFAULT NULL,
  `suggestion` longtext,
  `reporter` varchar(20) DEFAULT NULL,
  `reportdate` varchar(10) DEFAULT NULL,
  `modifer` varchar(20) DEFAULT NULL,
  `modifydate` varchar(10) DEFAULT NULL,
  `construction_scale` int(11) DEFAULT NULL,
  `grain_scale` int(11) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sm`),
  KEY `AK_Key_2` (`Annual`,`lkbm`),
  KEY `FK_fk_lk_hcdczb` (`lkbm`),
  KEY `FK3175F64BF7794E11` (`sm`),
  KEY `FK3175F64B6159BC1E` (`sm`),
  CONSTRAINT `FK_fk_lk_hcdczb` FOREIGN KEY (`lkbm`) REFERENCES `t_graindepot` (`lkbm`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_depot_invest
-- ----------------------------
INSERT INTO `t_depot_invest` VALUES ('5', ' 100002', '2015', '', '&nbsp;没哟意见 <img src=\"http://localhost:8080/grainInsects/upload/image/watermark.png\" alt=\"\" /><br />', 'qewrq', null, null, null, '20100', null, '');
INSERT INTO `t_depot_invest` VALUES ('6', ' 100002', '2016', '', '', '', null, null, null, null, null, '');

-- ----------------------------
-- Table structure for t_depot_invest_tdepot_humidities
-- ----------------------------
DROP TABLE IF EXISTS `t_depot_invest_tdepot_humidities`;
CREATE TABLE `t_depot_invest_tdepot_humidities` (
  `t_depot_invest` bigint(20) NOT NULL,
  `tdepot_humidities` int(11) NOT NULL,
  UNIQUE KEY `tdepot_humidities` (`tdepot_humidities`),
  KEY `FK40F92AFCE6620CAB` (`t_depot_invest`),
  CONSTRAINT `FK40F92AFCE6620CAB` FOREIGN KEY (`t_depot_invest`) REFERENCES `t_depot_invest` (`sm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_depot_invest_tdepot_humidities
-- ----------------------------

-- ----------------------------
-- Table structure for t_depot_phosphinefumigation
-- ----------------------------
DROP TABLE IF EXISTS `t_depot_phosphinefumigation`;
CREATE TABLE `t_depot_phosphinefumigation` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `lkbm` varchar(60) NOT NULL,
  `Annual` int(11) NOT NULL,
  `device` varchar(100) DEFAULT NULL,
  `CAscale` int(11) DEFAULT NULL,
  `ifcirculation` char(1) DEFAULT NULL,
  `proportion` float DEFAULT NULL,
  `fansys` float DEFAULT NULL,
  `brand` varchar(100) DEFAULT NULL,
  `Dosage` float DEFAULT NULL,
  `drugfee` float DEFAULT NULL,
  `breathing` float DEFAULT NULL,
  `subsidy` float DEFAULT NULL,
  `pump` float DEFAULT NULL,
  `otherfee` float DEFAULT NULL,
  `alarmer` float DEFAULT NULL,
  `detecter` float DEFAULT NULL,
  `powerfee` float DEFAULT NULL,
  `elsefee` float DEFAULT NULL,
  `totaldays` int(11) DEFAULT NULL,
  `Generator` char(1) DEFAULT NULL,
  `inbin` char(1) DEFAULT NULL,
  `giver` char(1) DEFAULT NULL,
  `fancrossing` char(1) DEFAULT NULL,
  `othergiveway` varchar(60) DEFAULT NULL,
  `circulationway` varchar(20) DEFAULT NULL,
  `highestppm` float DEFAULT NULL,
  `targetdays` int(11) DEFAULT NULL,
  `avgtemperature` float DEFAULT NULL,
  `highesttemperature` float DEFAULT NULL,
  `lowesttemperature` float DEFAULT NULL,
  `kindbefca` float DEFAULT NULL,
  `densitybefca` float DEFAULT NULL,
  `kindafterca` varchar(60) DEFAULT NULL,
  `densityafterca` float DEFAULT NULL,
  `varconcentration` varchar(60) DEFAULT NULL,
  `noninsects` int(11) DEFAULT NULL,
  `ifcage` char(1) DEFAULT NULL,
  `cagekind` varchar(60) DEFAULT NULL,
  `cagenum` int(11) DEFAULT NULL,
  `hasliveInsectsafter` char(1) DEFAULT NULL,
  `fattyvaluebef` float DEFAULT NULL,
  `fattyvalueafter` float DEFAULT NULL,
  `elsecharacter` varchar(100) DEFAULT NULL,
  `suggestion` longtext,
  `reporter` varchar(60) DEFAULT NULL,
  `reportdate` date DEFAULT NULL,
  `modifier` varchar(60) DEFAULT NULL,
  `modifytime` datetime DEFAULT NULL,
  `haslive_insectsafter` char(1) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `AK_Key_2` (`lkbm`,`Annual`),
  CONSTRAINT `FK_Reference_67` FOREIGN KEY (`lkbm`) REFERENCES `t_graindepot` (`lkbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_depot_phosphinefumigation
-- ----------------------------

-- ----------------------------
-- Table structure for t_depot_preprotect
-- ----------------------------
DROP TABLE IF EXISTS `t_depot_preprotect`;
CREATE TABLE `t_depot_preprotect` (
  `sm` bigint(20) NOT NULL AUTO_INCREMENT,
  `checkbeforeinput` char(1) DEFAULT NULL,
  `clearbinkill` char(1) DEFAULT NULL,
  `checkafteroutput` char(1) DEFAULT NULL,
  `inputcheck` char(1) DEFAULT NULL,
  `breakagerate` float DEFAULT NULL,
  `watercontent` float DEFAULT NULL,
  `Noperfect` float DEFAULT NULL,
  `impurity` float DEFAULT NULL,
  `acceptelse` varchar(60) DEFAULT NULL,
  `flynet` char(1) DEFAULT NULL,
  `flyline` char(1) DEFAULT NULL,
  `detectfrequency` varchar(60) DEFAULT NULL,
  `direct` char(1) DEFAULT NULL,
  `sample` char(1) DEFAULT NULL,
  `trap` char(1) DEFAULT NULL,
  `elsedetect` varchar(60) DEFAULT NULL,
  `psm` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sm`),
  KEY `FK5AEB3222B4EDC92A` (`psm`),
  CONSTRAINT `FK5AEB3222B4EDC92A` FOREIGN KEY (`psm`) REFERENCES `t_depot_invest` (`sm`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_depot_preprotect
-- ----------------------------
INSERT INTO `t_depot_preprotect` VALUES ('3', '1', null, null, '1', null, null, null, null, '', null, null, '', null, null, null, '', '5');
INSERT INTO `t_depot_preprotect` VALUES ('4', null, null, null, null, null, null, null, null, '', null, null, '', null, null, null, '', '5');
INSERT INTO `t_depot_preprotect` VALUES ('5', null, null, null, null, null, null, null, null, '', null, null, '', null, null, null, '', '6');

-- ----------------------------
-- Table structure for t_depot_protect
-- ----------------------------
DROP TABLE IF EXISTS `t_depot_protect`;
CREATE TABLE `t_depot_protect` (
  `sm` bigint(20) NOT NULL AUTO_INCREMENT,
  `flynet` char(1) DEFAULT NULL,
  `flyline` char(1) DEFAULT NULL,
  `protectant` char(1) DEFAULT NULL,
  `Inertpowder` char(1) DEFAULT NULL,
  `sanitary` char(1) DEFAULT NULL,
  `otherpreprotectway` varchar(60) DEFAULT NULL,
  `isolateprotect` char(1) DEFAULT NULL,
  `Fumigation` char(1) DEFAULT NULL,
  `controlledatmosphere` char(1) DEFAULT NULL,
  `frozen` char(1) DEFAULT NULL,
  `highertemperature` char(1) DEFAULT NULL,
  `Radiation` char(1) DEFAULT NULL,
  `Biologicalcontrol` char(1) DEFAULT NULL,
  `Insecticidaldevice` char(1) DEFAULT NULL,
  `otherkillway` varchar(60) DEFAULT NULL,
  `Generator` char(1) DEFAULT NULL,
  `inbin` char(1) DEFAULT NULL,
  `giver` char(1) DEFAULT NULL,
  `fancrossing` char(1) DEFAULT NULL,
  `othergiveway` varchar(60) DEFAULT NULL,
  `Artificialscreening` char(1) DEFAULT NULL,
  `Carbontubetrap` char(1) DEFAULT NULL,
  `Corrugatedpapertraps` char(1) DEFAULT NULL,
  `Frequencyvibrationlighttrap` char(1) DEFAULT NULL,
  `otherdetectway` varchar(60) DEFAULT NULL,
  `psm` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sm`),
  KEY `FK760BCB39B4EDC92A` (`psm`),
  CONSTRAINT `FK760BCB39B4EDC92A` FOREIGN KEY (`psm`) REFERENCES `t_depot_invest` (`sm`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_depot_protect
-- ----------------------------
INSERT INTO `t_depot_protect` VALUES ('2', '1', '1', '1', '1', '1', '', '1', null, null, null, null, null, null, null, '', null, null, null, null, '1', '1', '1', null, null, '', '5');
INSERT INTO `t_depot_protect` VALUES ('3', null, null, '1', '1', null, '', null, null, null, null, null, null, null, null, '', null, null, null, null, '', null, null, null, null, '', '6');

-- ----------------------------
-- Table structure for t_depot_protected
-- ----------------------------
DROP TABLE IF EXISTS `t_depot_protected`;
CREATE TABLE `t_depot_protected` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `lkbm` varchar(60) NOT NULL,
  `Annual` int(11) NOT NULL,
  `drug` varchar(100) DEFAULT NULL,
  `totaluse` int(11) DEFAULT NULL,
  `proportion` float DEFAULT NULL,
  `deviceandway` varchar(100) DEFAULT NULL,
  `device` float DEFAULT NULL,
  `brand` varchar(60) DEFAULT NULL,
  `Dosage` float DEFAULT NULL,
  `drugfee` float DEFAULT NULL,
  `protective` float DEFAULT NULL,
  `subsidy` float DEFAULT NULL,
  `keeplong` int(11) DEFAULT NULL,
  `laborfee` float DEFAULT NULL,
  `otherfee` float DEFAULT NULL,
  `applymethod` varchar(200) DEFAULT NULL,
  `applyonsurface` float DEFAULT NULL,
  `applyeverylayer` float DEFAULT NULL,
  `applyonwhole` float DEFAULT NULL,
  `dealonsurface` float DEFAULT NULL,
  `otherdeal` varchar(200) DEFAULT NULL,
  `avggraintemperature` float DEFAULT NULL,
  `highestgraintemperature` float DEFAULT NULL,
  `lowestgraintemperature` float DEFAULT NULL,
  `densitybef` float DEFAULT NULL,
  `kindbef` varchar(100) DEFAULT NULL,
  `densityafter` float DEFAULT NULL,
  `noninsects` int(11) DEFAULT NULL,
  `fattyvaluebef` float DEFAULT NULL,
  `fattyvalueafter` float DEFAULT NULL,
  `residues` float DEFAULT NULL,
  `elsecharacter` varchar(200) DEFAULT NULL,
  `suggestion` longtext,
  `reporter` varchar(20) DEFAULT NULL,
  `reportdate` date DEFAULT NULL,
  `modifier` varchar(20) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `AK_Key_2` (`lkbm`,`Annual`),
  CONSTRAINT `FK_Reference_68` FOREIGN KEY (`lkbm`) REFERENCES `t_graindepot` (`lkbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_depot_protected
-- ----------------------------

-- ----------------------------
-- Table structure for t_depot_use_drug
-- ----------------------------
DROP TABLE IF EXISTS `t_depot_use_drug`;
CREATE TABLE `t_depot_use_drug` (
  `sm` bigint(20) NOT NULL AUTO_INCREMENT,
  `psm` bigint(20) DEFAULT NULL,
  `drugname` varchar(60) DEFAULT NULL,
  `factory` varchar(60) DEFAULT NULL,
  `value` float DEFAULT NULL,
  `usemethod` varchar(100) DEFAULT NULL,
  `protectkind` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`sm`),
  KEY `FK_Reference_65` (`psm`),
  CONSTRAINT `FK_Reference_65` FOREIGN KEY (`psm`) REFERENCES `t_depot_invest` (`sm`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_depot_use_drug
-- ----------------------------
INSERT INTO `t_depot_use_drug` VALUES ('1', '5', 'adsfas', 'dsfa', '12', '', '');

-- ----------------------------
-- Table structure for t_depo_envitemperature
-- ----------------------------
DROP TABLE IF EXISTS `t_depo_envitemperature`;
CREATE TABLE `t_depo_envitemperature` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `January` float DEFAULT NULL,
  `February` float DEFAULT NULL,
  `March` float DEFAULT NULL,
  `April` float DEFAULT NULL,
  `May` float DEFAULT NULL,
  `June` float DEFAULT NULL,
  `July` float DEFAULT NULL,
  `August` float DEFAULT NULL,
  `September` float DEFAULT NULL,
  `October` float DEFAULT NULL,
  `November` float DEFAULT NULL,
  `December` float DEFAULT NULL,
  `Reporter` varchar(20) DEFAULT NULL,
  `Modifier` varchar(20) DEFAULT NULL,
  `sm` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK9BB3A5C4B4EC24BA` (`sm`),
  CONSTRAINT `FK9BB3A5C4B4EC24BA` FOREIGN KEY (`sm`) REFERENCES `t_depot_invest` (`sm`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_depo_envitemperature
-- ----------------------------
INSERT INTO `t_depo_envitemperature` VALUES ('4', null, null, '12', null, '24', null, null, null, null, null, null, null, null, null, '5');
INSERT INTO `t_depo_envitemperature` VALUES ('5', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '6');

-- ----------------------------
-- Table structure for t_device_realdata
-- ----------------------------
DROP TABLE IF EXISTS `t_device_realdata`;
CREATE TABLE `t_device_realdata` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `lcbm` varchar(60) DEFAULT NULL,
  `deviceno` varchar(30) DEFAULT NULL,
  `temperature` float DEFAULT NULL,
  `humidity` float DEFAULT NULL,
  `co2` float DEFAULT NULL,
  `o2` float DEFAULT NULL,
  `kind` varchar(30) DEFAULT NULL,
  `stage` varchar(30) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `X` int(11) DEFAULT NULL,
  `Y` int(11) DEFAULT NULL,
  `Z` int(11) DEFAULT NULL,
  `longtitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `altitude` double DEFAULT NULL,
  `collecttime` varchar(60) DEFAULT NULL,
  `source` varchar(100) DEFAULT NULL COMMENT '移动设备：手机号码\r\n            固定设备\r\n            ',
  `modifier` varchar(255) DEFAULT NULL,
  `modifytime` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `trapsource` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_13` (`lcbm`),
  KEY `FK_Reference_14` (`deviceno`),
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`lcbm`) REFERENCES `t_grainbin` (`lcbm`),
  CONSTRAINT `FK_Reference_14` FOREIGN KEY (`deviceno`) REFERENCES `t_device_register` (`deviceno`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_device_realdata
-- ----------------------------
INSERT INTO `t_device_realdata` VALUES ('1', '1000002', null, '15', '30', '20', '10', '甲虫', '成虫', '10', '1', '8', '10', '143560.98', '123400.789', '1000', '20160406', null, null, null, null, null);
INSERT INTO `t_device_realdata` VALUES ('2', '1000001', null, '15', '30', '20', '10', '甲虫', '成虫', '10', '1', '8', '10', '143560.98', '123400.789', '1000', '20160406', null, null, null, null, null);
INSERT INTO `t_device_realdata` VALUES ('3', '1000004', null, '15', '30', '20', '10', '甲虫', '成虫', '10', '1', '8', '10', '143560.98', '123400.789', '1000', '2016-06-06', null, null, null, null, null);
INSERT INTO `t_device_realdata` VALUES ('4', '1000003', null, '15', '30', '20', '10', '甲虫', '成虫', '10', '1', '8', '10', '143560.98', '123400.789', '1000', '2016-06-06', null, null, null, null, null);
INSERT INTO `t_device_realdata` VALUES ('5', '1000003', null, '15', '30', '20', '10', '甲虫', '成虫', '10', '1', '8', '10', '143560.98', '123400.789', '1000', '2016-07-06', 'App', null, null, null, null);
INSERT INTO `t_device_realdata` VALUES ('6', '1000002', null, '15', '30', '20', '10', '甲虫', '成虫', '10', '1', '8', '10', '143560.98', '123400.789', '1000', '20160406', 'App', null, null, null, null);
INSERT INTO `t_device_realdata` VALUES ('7', '1000002', null, '15', '30', '20', '10', '甲虫', '成虫', '10', '1', '8', '10', '143560.98', '123400.789', '1000', '20160406', 'App', 'aa', '2016-05-13 15:24:12', null, null);
INSERT INTO `t_device_realdata` VALUES ('8', '1000004', null, '15', '30', '20', '100', '甲虫', '成虫', '100', '1', '8', '10', '143560.98', '123400.789', '1000', '2016-07-06', 'App', 'aa', '2016-05-19 15:08:34', null, null);
INSERT INTO `t_device_realdata` VALUES ('9', '1000003', null, '15', '30', '20', '10', '甲虫', '成虫', '10', '1', '8', '10', '143560.98', '123400.789', '1000', '2016-05-06', 'App', 'aa', '2016-05-19 15:15:20', null, null);
INSERT INTO `t_device_realdata` VALUES ('10', '1000002', null, '15', '30', '20', '10', '甲虫', '成虫', '10', '1', '8', '10', '143560.98', '123400.789', '1000', '20160406', 'App', 'aa', '2016-05-19 15:18:23', null, null);
INSERT INTO `t_device_realdata` VALUES ('11', '1000001', null, null, null, null, null, '', null, null, null, null, null, null, null, null, '2016-06-12 10:12:00', 'App', 'bb', '2016-06-12 22:12:08', null, null);
INSERT INTO `t_device_realdata` VALUES ('12', '1000001', null, null, null, null, null, '', null, null, null, null, null, null, null, null, '2016-06-12 10:17:04', 'App', 'bb', '2016-06-12 22:17:11', null, null);
INSERT INTO `t_device_realdata` VALUES ('13', '1000002', null, null, null, null, null, '', null, null, null, null, null, null, null, null, '2016-06-12 10:46:10', 'App', 'bb', '2016-06-12 22:46:18', null, null);
INSERT INTO `t_device_realdata` VALUES ('14', '1000004', null, '55', '32', null, null, '麦蛾', null, null, null, null, null, '113.569225', '34.831668', null, '2016-06-13 12:05:12', 'App', 'bb', '2016-06-13 00:05:25', null, null);
INSERT INTO `t_device_realdata` VALUES ('15', '1000003', null, null, null, null, null, '麦蛾', null, null, null, null, null, null, null, null, '2016-06-13 12:38:04', 'App', 'bb', '2016-06-13 00:38:12', null, null);
INSERT INTO `t_device_realdata` VALUES ('16', '1000004', null, null, null, null, null, '', null, null, null, null, null, null, null, null, '2016-06-13 01:06:12', 'App', 'bb', '2016-06-13 01:06:20', null, null);
INSERT INTO `t_device_realdata` VALUES ('17', '1000003', null, null, null, null, null, '', null, null, null, null, null, null, null, null, '2016-06-13 01:06:17', 'App', 'bb', '2016-06-13 01:06:26', null, null);
INSERT INTO `t_device_realdata` VALUES ('18', '1000002', null, null, null, null, null, '', null, null, null, null, null, null, null, null, '2016-06-13 01:06:48', 'App', 'bb', '2016-06-13 01:06:56', null, null);
INSERT INTO `t_device_realdata` VALUES ('19', '1000004', null, '5', '250', null, null, '麦蛾', null, null, null, null, null, '113.550504', '34.834604', null, '2016-06-13 11:04:46', 'App', 'bb', '2016-06-13 11:04:54', null, null);
INSERT INTO `t_device_realdata` VALUES ('20', '1000003', null, '5', '5', null, null, '麦蛾', null, null, null, null, null, '113.550555', '34.834543', null, '2016-06-13 02:31:28', 'App', 'bb', '2016-06-13 14:31:36', null, null);
INSERT INTO `t_device_realdata` VALUES ('21', '1000002', null, '5', '5', null, null, '赤拟谷盗', null, '2', null, null, null, '113.550459', '34.834594', null, '2016-06-13 02:34:33', 'App', 'bb', '2016-06-13 14:34:41', null, null);
INSERT INTO `t_device_realdata` VALUES ('22', '1000004', null, '5', '5', null, null, '麦蛾', null, '2', null, null, null, null, null, null, '2016-06-13 02:36:56', 'App', 'bb', '2016-06-13 14:37:04', null, null);
INSERT INTO `t_device_realdata` VALUES ('23', '1000002', null, '5', '5', null, null, '', null, '2', null, null, null, '113.550463', '34.834599', null, '2016-06-13 03:02:46', 'App', 'bb', '2016-06-13 15:02:57', null, null);
INSERT INTO `t_device_realdata` VALUES ('24', '1000004', null, null, null, null, null, '麦蛾', null, null, null, null, null, '116.365606', '39.969814', null, '2016-06-17 11:31:27', 'App', 'bb', '2016-06-17 11:31:37', null, null);
INSERT INTO `t_device_realdata` VALUES ('25', '1000002', null, '23', '42', null, null, '', null, null, null, null, null, '116.365538', '39.969887', null, '2016-06-18 10:10:18', 'App', 'bb', '2016-06-18 10:10:24', null, null);
INSERT INTO `t_device_realdata` VALUES ('26', '1000004', null, null, null, null, null, '玉米象', null, null, null, null, null, '116.365495', '39.969877', null, '2016-06-18 02:41:42', 'App', 'bb', '2016-06-18 14:41:47', null, null);
INSERT INTO `t_device_realdata` VALUES ('27', '1000003', null, '58', '42', null, null, '麦蛾', null, '5', null, null, null, '116.365436', '39.969864', null, '2016-06-21 04:57:31', 'App', 'bb', '2016-06-21 16:57:39', null, null);
INSERT INTO `t_device_realdata` VALUES ('28', ' 1111111', null, '5', '5', null, null, '麦蛾', null, '5', null, null, null, '116.36542', '39.969859', null, '2016-06-22 07:27:12', 'APP', 'bb', '2016-06-22 19:27:23', null, null);
INSERT INTO `t_device_realdata` VALUES ('29', null, null, null, null, null, null, '麦蛾', null, null, null, null, null, null, null, null, '2016-06-22 07:30:13', 'APP', 'bb', '2016-06-22 19:30:26', null, null);
INSERT INTO `t_device_realdata` VALUES ('30', null, null, null, null, null, null, '麦蛾', null, null, null, null, null, null, null, null, '2016-06-27 02:36:05', 'APP', 'bb', '2016-06-27 14:36:11', null, null);
INSERT INTO `t_device_realdata` VALUES ('31', null, null, '0', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, 'bb', '2016-06-27 14:57:31', null, null);
INSERT INTO `t_device_realdata` VALUES ('32', '1000003', null, '27', '443.1', null, null, null, null, null, '34', '234', '23', null, null, null, '2016-07-12 04:48:24', 'device2', 'bb', '2016-06-27 14:57:48', null, null);
INSERT INTO `t_device_realdata` VALUES ('33', '1000002', null, '27', '443.4', null, null, null, null, null, '2', '5', '7', null, null, null, '2016-07-08 03:32:35', 'device1', 'bb', '2016-06-27 14:58:00', null, null);
INSERT INTO `t_device_realdata` VALUES ('34', null, null, '0', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, 'bb', '2016-06-27 15:35:51', null, null);
INSERT INTO `t_device_realdata` VALUES ('35', '1000002', null, '27', '448.1', null, null, null, null, null, '2', '5', '7', null, null, null, '2016-07-07 12:44:06', 'device1', 'bb', '2016-06-27 15:36:03', null, null);
INSERT INTO `t_device_realdata` VALUES ('36', '1000002', null, '26.9', '448.1', null, null, null, null, null, '2', '5', '7', null, null, null, '2016-07-06 09:43:03', 'device1', 'bb', '2016-06-27 15:36:15', null, null);
INSERT INTO `t_device_realdata` VALUES ('37', '1000003', null, '27', '448.1', null, null, null, null, null, '23', '3', '3', null, null, null, '2016-07-06 09:43:03', 'device1', 'bb', '2016-06-27 15:36:27', null, null);
INSERT INTO `t_device_realdata` VALUES ('38', '1000002', null, '27', '448.4', null, null, null, null, null, '34', '23', '2', null, null, null, '2016-06-27 05:44:38', 'device2', 'bb', '2016-06-27 15:36:40', null, null);
INSERT INTO `t_device_realdata` VALUES ('39', '1000002', null, '27', '448.4', null, null, null, null, null, '2', '5', '7', null, null, null, '2016-06-27 05:44:38', 'device1', 'bb', '2016-06-27 15:36:51', null, null);
INSERT INTO `t_device_realdata` VALUES ('40', ' 18902344', null, '25', null, null, null, '玉米象', null, '5', null, null, null, '116.365521', '39.969853', null, '2016-06-27 05:44:38', 'APP', 'bb', '2016-06-27 17:44:48', null, null);
INSERT INTO `t_device_realdata` VALUES ('41', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'bb', '2016-07-05 12:22:36', null, null);
INSERT INTO `t_device_realdata` VALUES ('42', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'bb', '2016-07-05 12:23:40', null, null);
INSERT INTO `t_device_realdata` VALUES ('43', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'bb', '2016-07-05 14:12:09', null, null);
INSERT INTO `t_device_realdata` VALUES ('45', null, null, null, null, null, null, '', null, null, null, null, null, null, null, null, '2016-07-06 09:43:03', 'APP', 'bb', '2016-07-06 09:43:12', null, null);
INSERT INTO `t_device_realdata` VALUES ('46', '1000002', null, '25', '30', null, null, '赤拟谷盗', null, '2', null, null, null, '116.365507', '39.969811', null, '2016-07-06 04:39:51', 'App', 'bb', '2016-07-06 16:40:01', null, null);
INSERT INTO `t_device_realdata` VALUES ('47', ' 1111111', null, null, null, null, null, '', null, null, null, null, null, null, null, null, '2016-07-06 05:14:35', 'APP', 'bb', '2016-07-06 17:14:45', null, null);
INSERT INTO `t_device_realdata` VALUES ('48', '1000004', null, '20', '40', null, null, '麦蛾', null, '5', null, null, null, '116.362724', '39.969968', null, '2016-07-07 12:44:06', 'App', 'bb', '2016-07-07 00:44:14', null, null);
INSERT INTO `t_device_realdata` VALUES ('49', ' 123334', null, '5', '25', null, null, '麦蛾', null, null, null, null, null, '116.365445', '39.969867', null, '2016-07-08 03:32:35', 'APP', 'bb', '2016-07-08 15:32:37', null, null);
INSERT INTO `t_device_realdata` VALUES ('50', '1000003', null, '5', '25', null, null, '玉米象', null, '5', null, null, null, '116.356869', '39.934605', null, '2016-07-12 04:37:35', 'App', 'bb', '2016-07-12 16:37:55', null, null);
INSERT INTO `t_device_realdata` VALUES ('51', '1000002', null, '20', '70', null, null, '麦蛾', null, '5', null, null, null, '116.356747', '39.934388', null, '2016-07-12 04:48:24', 'App', 'bb', '2016-07-12 16:48:38', null, null);
INSERT INTO `t_device_realdata` VALUES ('52', ' 18902344', null, '20', '40', null, null, '赤拟谷盗', null, '50', null, null, null, '116.362659', '39.969824', null, '2016-07-13 12:39:09', 'APP', 'bb', '2016-07-13 00:39:20', null, null);
INSERT INTO `t_device_realdata` VALUES ('53', ' 18902344', null, '5', '25', null, null, '麦蛾', null, '5', null, null, null, '116.363692', '39.970047', null, '2016-07-19 06:49:26', 'APP', 'bb', '2016-07-19 06:49:39', null, null);

-- ----------------------------
-- Table structure for t_device_register
-- ----------------------------
DROP TABLE IF EXISTS `t_device_register`;
CREATE TABLE `t_device_register` (
  `deviceno` varchar(30) NOT NULL,
  `name` varchar(100) NOT NULL,
  `kind` varchar(100) DEFAULT NULL,
  `productor` varchar(100) DEFAULT NULL,
  `registerdate` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`deviceno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_device_register
-- ----------------------------

-- ----------------------------
-- Table structure for t_diagnosisapplypic
-- ----------------------------
DROP TABLE IF EXISTS `t_diagnosisapplypic`;
CREATE TABLE `t_diagnosisapplypic` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PID` bigint(20) DEFAULT NULL,
  `pic` varchar(255) NOT NULL,
  `modifer` varchar(30) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_18` (`PID`),
  CONSTRAINT `FK_Reference_18` FOREIGN KEY (`PID`) REFERENCES `t_remotediagnosisapply` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_diagnosisapplypic
-- ----------------------------

-- ----------------------------
-- Table structure for t_digital_feature
-- ----------------------------
DROP TABLE IF EXISTS `t_digital_feature`;
CREATE TABLE `t_digital_feature` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PID` bigint(20) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `value` longtext,
  `indentifyindex` int(11) NOT NULL DEFAULT '-1',
  `note` longtext,
  `source` varchar(100) DEFAULT NULL,
  `modifer` varchar(30) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_24` (`PID`),
  CONSTRAINT `FK_Reference_24` FOREIGN KEY (`PID`) REFERENCES `t_catalog_index` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_digital_feature
-- ----------------------------
INSERT INTO `t_digital_feature` VALUES ('1', '7', 'saf', 'sadf', '23', null, null, null, null);
INSERT INTO `t_digital_feature` VALUES ('2', '7', 'ewrq', '324', '234', null, null, null, null);
INSERT INTO `t_digital_feature` VALUES ('3', '7', 'saf', 'sadf', '23', null, null, null, null);
INSERT INTO `t_digital_feature` VALUES ('4', '4', 'sdfa', 'sadf', '2', 'dfa', null, null, null);

-- ----------------------------
-- Table structure for t_digital_pic
-- ----------------------------
DROP TABLE IF EXISTS `t_digital_pic`;
CREATE TABLE `t_digital_pic` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PID` bigint(20) DEFAULT NULL,
  `pic` varchar(255) NOT NULL,
  `source` varchar(100) DEFAULT NULL,
  `modifer` varchar(30) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  `large` varchar(255) DEFAULT NULL,
  `medium` varchar(255) DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_56` (`PID`),
  KEY `FK309C539829960B76` (`ID`),
  CONSTRAINT `FK309C539829960B76` FOREIGN KEY (`ID`) REFERENCES `t_digital_pic` (`ID`),
  CONSTRAINT `FK_Reference_56` FOREIGN KEY (`PID`) REFERENCES `t_digital_feature` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_digital_pic
-- ----------------------------

-- ----------------------------
-- Table structure for t_expert
-- ----------------------------
DROP TABLE IF EXISTS `t_expert`;
CREATE TABLE `t_expert` (
  `username` varchar(10) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(60) DEFAULT NULL,
  `title` varchar(40) DEFAULT NULL,
  `company` varchar(100) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `point` bigint(20) DEFAULT NULL,
  `contact` varchar(20) DEFAULT NULL,
  `Specialty` varchar(200) DEFAULT NULL,
  `hasaudit` bit(1) DEFAULT NULL,
  `modifer` varchar(30) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  `roleid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `FK_expert_role` (`roleid`),
  CONSTRAINT `FK_expert_role` FOREIGN KEY (`roleid`) REFERENCES `xx_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_expert
-- ----------------------------
INSERT INTO `t_expert` VALUES ('expert', 'c4ca4238a0b923820dcc509a6f75849b', 'eee', null, null, null, null, null, null, '', null, null, '4');

-- ----------------------------
-- Table structure for t_expertopinion
-- ----------------------------
DROP TABLE IF EXISTS `t_expertopinion`;
CREATE TABLE `t_expertopinion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `opinion` varchar(255) DEFAULT NULL,
  `optype` int(11) DEFAULT NULL,
  `topic` varchar(50) DEFAULT NULL,
  `username` varchar(10) DEFAULT NULL,
  `createdate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK9028598DCA545938` (`username`),
  CONSTRAINT `FK9028598DCA545938` FOREIGN KEY (`username`) REFERENCES `t_expert` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_expertopinion
-- ----------------------------
INSERT INTO `t_expertopinion` VALUES ('1', 'asdf', '1', 'dsaf', 'expert', '2016-08-12');
INSERT INTO `t_expertopinion` VALUES ('2', 'dfgadfs', '0', 'sdaf', 'expert', '2016-08-12');
INSERT INTO `t_expertopinion` VALUES ('3', '', '1', 'sad', 'expert', '2016-08-12');
INSERT INTO `t_expertopinion` VALUES ('4', 'asf', '0', 'sadsd', 'expert', '2016-08-12');
INSERT INTO `t_expertopinion` VALUES ('5', '', null, '', 'expert', '2016-08-12');
INSERT INTO `t_expertopinion` VALUES ('6', '', '0', 'dsaf', 'expert', '2016-08-14');

-- ----------------------------
-- Table structure for t_factoryuser
-- ----------------------------
DROP TABLE IF EXISTS `t_factoryuser`;
CREATE TABLE `t_factoryuser` (
  `username` varchar(10) NOT NULL,
  `sm_factory` varchar(30) DEFAULT NULL,
  `pass` varchar(255) DEFAULT NULL,
  `realname` varchar(60) DEFAULT NULL,
  `title` varchar(40) DEFAULT NULL,
  `company` varchar(100) DEFAULT NULL,
  `moblie` varchar(20) DEFAULT NULL,
  `manager` int(11) DEFAULT '0' COMMENT '0: 库管员\r\n            1：所长',
  `hasaudit` bit(1) DEFAULT NULL,
  `point` bigint(20) DEFAULT NULL,
  `modifer` varchar(30) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  `roleid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `FK_Reference_59` (`sm_factory`),
  KEY `FK_factory_role` (`roleid`),
  CONSTRAINT `FK_Reference_59` FOREIGN KEY (`sm_factory`) REFERENCES `t_factory_info` (`sm_factory`),
  CONSTRAINT `FK_factory_role` FOREIGN KEY (`roleid`) REFERENCES `xx_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_factoryuser
-- ----------------------------
INSERT INTO `t_factoryuser` VALUES ('factory', '12000', 'c4ca4238a0b923820dcc509a6f75849b', 'factory', '', '', '', null, '', '0', '', '2016-07-31 17:49:00', '5');

-- ----------------------------
-- Table structure for t_factory_info
-- ----------------------------
DROP TABLE IF EXISTS `t_factory_info`;
CREATE TABLE `t_factory_info` (
  `sm_factory` varchar(30) NOT NULL,
  `direct` bigint(20) DEFAULT NULL,
  `name_factory` varchar(30) DEFAULT NULL,
  `constructiondate` varchar(10) DEFAULT NULL,
  `Annualpurchase` float DEFAULT NULL,
  `majorkindofpurchase` varchar(60) DEFAULT NULL COMMENT '小麦、玉米、豆类、油料、其他',
  `address` varchar(100) DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `altitude` double DEFAULT NULL,
  `postcode` varchar(10) DEFAULT NULL,
  `contacts` varchar(30) DEFAULT NULL,
  `phone` varchar(60) DEFAULT NULL,
  `modifer` varchar(30) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  `main_intype` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`sm_factory`),
  KEY `FK_Fk_factory_direct` (`direct`),
  CONSTRAINT `FK_Fk_factory_direct` FOREIGN KEY (`direct`) REFERENCES `xx_area` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_factory_info
-- ----------------------------
INSERT INTO `t_factory_info` VALUES ('12000', null, 'dsddd', null, null, null, null, null, null, null, '', '', '11333', null, null, null);
INSERT INTO `t_factory_info` VALUES ('23303', null, 'adfs', null, null, null, null, null, null, null, '', '', 'adf', null, null, null);

-- ----------------------------
-- Table structure for t_factory_pic
-- ----------------------------
DROP TABLE IF EXISTS `t_factory_pic`;
CREATE TABLE `t_factory_pic` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `sm_collection` varchar(30) DEFAULT NULL,
  `collecttime` datetime DEFAULT NULL,
  `large` varchar(255) DEFAULT NULL,
  `medium` varchar(255) DEFAULT NULL,
  `orders` int(11) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_57` (`sm_collection`),
  CONSTRAINT `FK_Reference_57` FOREIGN KEY (`sm_collection`) REFERENCES `t_insects_onfactory` (`sm_collect`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_factory_pic
-- ----------------------------

-- ----------------------------
-- Table structure for t_farmer
-- ----------------------------
DROP TABLE IF EXISTS `t_farmer`;
CREATE TABLE `t_farmer` (
  `sm_farmer` varchar(30) NOT NULL,
  `direct` bigint(20) DEFAULT NULL,
  `name_famer` varchar(30) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `altitude` double DEFAULT NULL,
  `postcode` varchar(10) DEFAULT NULL,
  `phone` varchar(60) NOT NULL,
  `population` int(11) DEFAULT NULL,
  `Economic` varchar(60) DEFAULT NULL,
  `traffic` varchar(60) DEFAULT NULL,
  `geography` varchar(60) DEFAULT NULL,
  `modifer` varchar(20) DEFAULT NULL,
  `modifydate` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`sm_farmer`),
  KEY `FK_Fk_famer_direct` (`direct`),
  CONSTRAINT `FK_Fk_famer_direct` FOREIGN KEY (`direct`) REFERENCES `xx_area` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_farmer
-- ----------------------------
INSERT INTO `t_farmer` VALUES ('10002', null, 'adfa', '', null, null, null, '', '12334', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for t_farmeruser
-- ----------------------------
DROP TABLE IF EXISTS `t_farmeruser`;
CREATE TABLE `t_farmeruser` (
  `username` varchar(10) NOT NULL,
  `sm_farmer` varchar(30) NOT NULL COMMENT '为了便于今后参与虫害调研的农户数据的浏览',
  `realname` varchar(30) NOT NULL,
  `moblie` varchar(20) DEFAULT NULL,
  `direct` bigint(20) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `altitude` double DEFAULT NULL,
  `postcode` varchar(10) DEFAULT NULL,
  `modifer` varchar(30) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `roleid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `FK_Fk_fameruser_direct` (`direct`),
  KEY `FK_farmer_role` (`roleid`),
  CONSTRAINT `FK_Fk_fameruser_direct` FOREIGN KEY (`direct`) REFERENCES `xx_area` (`id`),
  CONSTRAINT `FK_farmer_role` FOREIGN KEY (`roleid`) REFERENCES `xx_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_farmeruser
-- ----------------------------
INSERT INTO `t_farmeruser` VALUES ('farmer', '10002', 'farmer', null, '30', null, null, null, null, null, null, '2016-07-31 17:49:18', 'c4ca4238a0b923820dcc509a6f75849b', '6');

-- ----------------------------
-- Table structure for t_field_pic
-- ----------------------------
DROP TABLE IF EXISTS `t_field_pic`;
CREATE TABLE `t_field_pic` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) DEFAULT NULL,
  `pic` varchar(255) NOT NULL,
  `collecttime` datetime DEFAULT NULL,
  `large` varchar(255) DEFAULT NULL,
  `medium` varchar(255) DEFAULT NULL,
  `orders` int(11) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_54` (`pid`),
  KEY `FK6A506EBADAC4DAC3` (`ID`),
  CONSTRAINT `FK6A506EBADAC4DAC3` FOREIGN KEY (`ID`) REFERENCES `t_insect_onfield` (`ID`),
  CONSTRAINT `FK_Reference_54` FOREIGN KEY (`pid`) REFERENCES `t_insect_onfield` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_field_pic
-- ----------------------------

-- ----------------------------
-- Table structure for t_grain
-- ----------------------------
DROP TABLE IF EXISTS `t_grain`;
CREATE TABLE `t_grain` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `lcbm` varchar(60) NOT NULL,
  `indate` varchar(10) NOT NULL,
  `clxs` varchar(20) NOT NULL COMMENT '散粮\r\n            成品粮\r\n            其他',
  `grainname` varchar(30) DEFAULT NULL,
  `harvestdate` varchar(10) DEFAULT NULL,
  `source` varchar(30) DEFAULT NULL,
  `water` float DEFAULT NULL,
  `impurity` float DEFAULT NULL,
  `grainheight` float DEFAULT NULL,
  `dryingmethod` varchar(30) DEFAULT NULL,
  `reserveperiod` int(11) DEFAULT NULL,
  `innum` int(11) DEFAULT NULL,
  `container` varchar(60) DEFAULT NULL,
  `emptybin500Pa` int(11) DEFAULT NULL,
  `halfemptybin500pa` int(11) DEFAULT NULL,
  `fullbin300pa` int(11) DEFAULT NULL,
  `halffullbin` int(11) DEFAULT NULL,
  `storetechnology` longtext,
  `storemethod` varchar(60) DEFAULT NULL,
  `controltemperaturemeasures` longtext,
  `controlhumiditymeasures` longtext,
  `modifier` varchar(255) DEFAULT NULL,
  `modifytime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `AK_Key_2` (`lcbm`,`indate`),
  CONSTRAINT `FK_liangcang_liangshi` FOREIGN KEY (`lcbm`) REFERENCES `t_grainbin` (`lcbm`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_grain
-- ----------------------------
INSERT INTO `t_grain` VALUES ('1', '1000001', '2016-02-04', '散装粮', '玉米', '2015-08-09', 'hunan', '20', '10', '10', '风干', '8', '1000', '无', '50', '50', '200', '180', '长文本', '冷藏', '长文本', '长文本', null, null);
INSERT INTO `t_grain` VALUES ('2', '1000001', '2016-02-04', '散装粮', '水稻', '2015-08-09', '江苏', '20', '10', '10', '风干', '8', '1000', '无', null, '50', '200', null, '长文本', '冷藏', '长文本', '长文本', null, null);
INSERT INTO `t_grain` VALUES ('3', '1000001', '2016-02-04', '散装粮', '水稻', '2015-08-09', '江苏', '20', '10', '10', '风干', '8', '1000', '无', null, '50', '200', '180', '长文本', '冷藏', '长文本', '长文本', null, null);
INSERT INTO `t_grain` VALUES ('4', '1000001', '2016-02-04', '散装粮', '水稻', '2015-08-09', '江苏', '20', '10', '10', '风干', '8', '1000', '无', null, '50', '200', '180', '长文本', '冷藏', '长文本', '长文本', null, null);
INSERT INTO `t_grain` VALUES ('5', '1000001', '2016-02-04', '散装粮', '水稻', '2015-08-09', '江苏', '20', '10', '10', '风干', '8', '1000', '无', null, '50', '200', '180', '长文本', '冷藏', '长文本', '长文本', null, null);
INSERT INTO `t_grain` VALUES ('6', '1000001', '2016-02-04', '散装粮', '水稻', '2015-08-09', '江苏', '20', '10', '10', '风干', '8', '1000', '无', null, '50', '200', '180', '长文本', '冷藏', '长文本', '长文本', null, null);
INSERT INTO `t_grain` VALUES ('7', '1000001', '2016-02-04', '散装粮', '水稻', '2015-08-09', '江苏', '20', '10', '10', '风干', '8', '1000', '无', '50', '50', '200', '180', '长文本', '冷藏', '长文本', '长文本', null, null);
INSERT INTO `t_grain` VALUES ('8', '1000001', '2016-02-04', '散装粮', '水稻', '2015-08-09', '江苏', '20', '10', '10', '风干', '8', '1000', '无', '50', '50', '200', '180', '长文本', '冷藏', '长文本', '长文本', null, null);
INSERT INTO `t_grain` VALUES ('9', '1000001', '2016-02-04', '散装粮', '水稻', '2015-08-09', '江苏', '20', '10', '10', '风干', '8', '1000', '无', '50', '50', '200', '180', '长文本', '冷藏', '长文本', '长文本', null, null);
INSERT INTO `t_grain` VALUES ('10', '1000001', '2016-02-04', '散装粮', '水稻', '2015-08-09', '江苏', '20', '10', '10', '风干', '8', '1000', '无', '50', '50', '200', '180', '长文本', '冷藏', '长文本', '长文本', null, null);
INSERT INTO `t_grain` VALUES ('11', '1000001', '2016-02-04', '散装粮', '水稻', '2015-08-09', '江苏', '20', '10', '10', '风干', '8', '1000', '无', '50', '50', '200', '180', '长文本', '冷藏', '长文本', '长文本', null, null);

-- ----------------------------
-- Table structure for t_grainbin
-- ----------------------------
DROP TABLE IF EXISTS `t_grainbin`;
CREATE TABLE `t_grainbin` (
  `lcbm` varchar(60) NOT NULL,
  `lkbm` varchar(60) DEFAULT NULL,
  `typebin` varchar(20) NOT NULL COMMENT '平房仓\r\n            浅圆仓\r\n            圆筒仓\r\n            其他仓',
  `capacity` int(11) DEFAULT NULL,
  `structureofbody` varchar(60) DEFAULT NULL,
  `structureofroof` varchar(60) DEFAULT NULL,
  `designcapacity` int(11) DEFAULT NULL,
  `designgrainheapheight` float DEFAULT NULL,
  `longth` int(11) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `Circulatedevice` varchar(100) DEFAULT NULL,
  `Circulatefan` varchar(100) DEFAULT NULL,
  `fanway` varchar(100) DEFAULT NULL,
  `elsedevice` longtext,
  `contract` varchar(20) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `note` varchar(400) DEFAULT NULL,
  `modifer` varchar(20) DEFAULT NULL,
  `modifydate` varchar(10) DEFAULT NULL,
  `binname` varchar(30) DEFAULT NULL,
  `granarynum` int(11) DEFAULT NULL,
  `orientation` float DEFAULT NULL,
  PRIMARY KEY (`lcbm`),
  KEY `FK_liangku_liangcang` (`lkbm`),
  CONSTRAINT `FK_liangku_liangcang` FOREIGN KEY (`lkbm`) REFERENCES `t_graindepot` (`lkbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_grainbin
-- ----------------------------
INSERT INTO `t_grainbin` VALUES (' 1111111', ' 100002', '平房仓', '5000', '钢架结构', '砖混结构', '1000', '100', '100', '100', '100', '', '100000', 'asdfaeee', '通风装置，制氮设备等<img src=\"http://localhost:8080/grainInsects/upload/image/201605/f1a662cd-8ee2-4f48-9140-786fc9c2cff9-thumbnail.jpg\" alt=\"\" /><img src=\"http://localhost:8080/grainInsects/upload/image/201605/6143af04-4c35-4f09-bde4-c94bb1936de3-thumbnail.jpg\" alt=\"\" /><br />', 'zhangsan', '', '', null, null, null, null, null);
INSERT INTO `t_grainbin` VALUES (' 123334', ' 100002', '圆筒仓', '1000', '砖混', '钢架', '1000', '100', '100', '100', '100', '无', '', '', 'adsfafdas<img src=\"http://localhost:8080/grainInsects/upload/image/201605/6143af04-4c35-4f09-bde4-c94bb1936de3-thumbnail.jpg\" alt=\"\" /><br />', '', '', '', null, null, null, null, null);
INSERT INTO `t_grainbin` VALUES (' 18902344', ' 100002', '平房仓', null, '', '', '1000', '100', '100', '100', '100', '', '', '', '平房仓，6000吨<br />', '', '', '', null, null, null, null, null);
INSERT INTO `t_grainbin` VALUES ('1000001', ' 100002', '圆筒仓', '1000', '钢结构', '钢结构', '1000', '100', '100', '100', '100', '', '', '', '呜呜呜呜，没有什么外围设备<img src=\"http://localhost:8080/grainInsects/upload/image/201605/5dbf3ffd-6eca-4a78-86b1-7b39ea347fa2-thumbnail.jpg\" alt=\"\" /><br />', '', '', '', null, null, null, null, null);
INSERT INTO `t_grainbin` VALUES ('1000002', '10000', '圆筒仓', '1000', '钢结构', '钢结构', '1000', '100', '100', '100', '100', '无', '100000', 'asdfaeee', '测试粮仓', 'lxq', '', '', '', '', '', null, null);
INSERT INTO `t_grainbin` VALUES ('1000003', '10000', '园筒仓', '1000', '钢结构', '钢结构', '1000', '100', '100', '100', '100', '无', '100000', 'asdfaeee', '测试粮仓', 'lxq', null, null, null, null, null, null, null);
INSERT INTO `t_grainbin` VALUES ('1000004', ' 700000', '平房仓', '1000', '钢结构', '钢结构', '1000', '100', '100', '100', '100', '无', '100000', 'asdfaeee', '测试粮仓', '1', null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for t_graindepot
-- ----------------------------
DROP TABLE IF EXISTS `t_graindepot`;
CREATE TABLE `t_graindepot` (
  `lkbm` varchar(60) NOT NULL,
  `direct` bigint(20) DEFAULT NULL,
  `lkmc` varchar(100) DEFAULT NULL,
  `lkdz` varchar(200) DEFAULT NULL,
  `longtitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `altitude` double DEFAULT NULL,
  `hasreal` tinyint(1) DEFAULT NULL,
  `totalbin` int(11) DEFAULT NULL,
  `postcode` varchar(10) DEFAULT NULL,
  `contact` varchar(20) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `modifier` varchar(20) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  `pic` longtext,
  PRIMARY KEY (`lkbm`),
  KEY `FK_Reference_80` (`direct`),
  CONSTRAINT `FK_Reference_80` FOREIGN KEY (`direct`) REFERENCES `xx_area` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_graindepot
-- ----------------------------
INSERT INTO `t_graindepot` VALUES (' 100002', '1', ' 上地粮库', '10000', '10000', '10000', '10000', '0', '10000', '10000', '10000', '10000', null, null, null);
INSERT INTO `t_graindepot` VALUES (' 5650001', '30', ' 东莞粮库', '东莞市了山区26号', '10000', '10000', '10000', '0', '8', '56700', '10000', '10000', null, null, null);
INSERT INTO `t_graindepot` VALUES (' 700000', '31', ' 上海外滩库', '10000', '10000', '10000', '10000', '1', '10', '760000', '10000', '10000', null, null, null);
INSERT INTO `t_graindepot` VALUES ('10000', '1', '北京通州库', '北京市通州区果园', '116.404', '39.915', '200', '1', '18', '100876', '张三', '136011890', null, null, null);
INSERT INTO `t_graindepot` VALUES ('10056', '32', ' 昌平粮库', '北京市昌平区下关环岛', '10000', '10000', '10000', '0', '30', '10000', '10000', '10000', null, null, null);

-- ----------------------------
-- Table structure for t_graindirection
-- ----------------------------
DROP TABLE IF EXISTS `t_graindirection`;
CREATE TABLE `t_graindirection` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `fullname` varchar(60) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_graindirection
-- ----------------------------
INSERT INTO `t_graindirection` VALUES ('1', '高寒区');
INSERT INTO `t_graindirection` VALUES ('2', '低温区');
INSERT INTO `t_graindirection` VALUES ('3', '干燥区');
INSERT INTO `t_graindirection` VALUES ('4', '高温区');
INSERT INTO `t_graindirection` VALUES ('5', '高湿区');
INSERT INTO `t_graindirection` VALUES ('7', '高温高湿区');

-- ----------------------------
-- Table structure for t_grain_pic
-- ----------------------------
DROP TABLE IF EXISTS `t_grain_pic`;
CREATE TABLE `t_grain_pic` (
  `PID` bigint(20) DEFAULT NULL,
  `pic` varchar(255) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  UNIQUE KEY `id` (`id`),
  KEY `FK_Reference_12` (`PID`),
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`PID`) REFERENCES `t_grain` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_grain_pic
-- ----------------------------
INSERT INTO `t_grain_pic` VALUES ('10', '/upload/image/201605/eef4828d-c33b-46db-924b-c465f0f5cd82.jpg', '1');
INSERT INTO `t_grain_pic` VALUES ('11', '/upload/image/201605/3905837a-0635-4448-aae4-66646544705d.jpg', '2');
INSERT INTO `t_grain_pic` VALUES ('2', '/upload/image/201605/5d994f41-86ca-42ac-b0ee-12e4418937cf.jpg', '3');

-- ----------------------------
-- Table structure for t_info_report
-- ----------------------------
DROP TABLE IF EXISTS `t_info_report`;
CREATE TABLE `t_info_report` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `proc_sm` varchar(10) DEFAULT NULL,
  `username` varchar(10) DEFAULT NULL,
  `lcbm` varchar(60) NOT NULL,
  `proname` varchar(60) DEFAULT NULL,
  `startdate` date DEFAULT NULL,
  `enddate` date DEFAULT NULL,
  `process` longtext NOT NULL,
  `note` longtext,
  `reporttime` datetime DEFAULT NULL,
  `source` varchar(100) DEFAULT NULL COMMENT '移动设备：手机号码\r\n            固定设备\r\n            ',
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_60` (`proc_sm`),
  KEY `FK_Reference_61` (`username`),
  CONSTRAINT `FK_Reference_60` FOREIGN KEY (`proc_sm`) REFERENCES `t_proctype` (`sm`),
  CONSTRAINT `FK_Reference_61` FOREIGN KEY (`username`) REFERENCES `t_depotuser` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_info_report
-- ----------------------------
INSERT INTO `t_info_report` VALUES ('1', 'fdasf', 'aa', '24342', 'dfa', '2016-08-01', '2016-08-10', '231', '123', '2016-08-12 16:51:28', '231');
INSERT INTO `t_info_report` VALUES ('2', 'ad', 'bb', '123334', 'dfsa', '2016-08-02', null, 'fdasf', null, null, null);
INSERT INTO `t_info_report` VALUES ('3', 'ad', 'aa', '1000001', 'dsaf', null, null, 'safsd', null, null, null);
INSERT INTO `t_info_report` VALUES ('4', '2', 'bb', '123334', '234', '2016-08-01', '2016-08-09', 'dsaf', null, null, null);
INSERT INTO `t_info_report` VALUES ('5', '2', 'bb', '123334', 'af', '2016-08-02', null, 'asdf', null, null, null);
INSERT INTO `t_info_report` VALUES ('7', null, 'bb', '123334', 'dfa', null, null, 'daf', null, null, null);
INSERT INTO `t_info_report` VALUES ('8', null, 'bb', '123334', 'dsfa', null, null, 'ggw', null, null, null);
INSERT INTO `t_info_report` VALUES ('9', null, 'bb', '123334', 'sdaf', null, null, 'czv', null, null, null);
INSERT INTO `t_info_report` VALUES ('10', null, 'bb', '123334', 'sfa', null, null, 'fags', null, null, null);
INSERT INTO `t_info_report` VALUES ('11', null, 'bb', '1111111', 'cxvz', null, null, 'rterf', null, null, null);
INSERT INTO `t_info_report` VALUES ('12', null, 'bb', '1111111', 'cxvz', null, null, 'zvc', null, null, null);
INSERT INTO `t_info_report` VALUES ('13', null, 'bb', '1111111', '边防部队', null, null, '反对vx', null, null, null);
INSERT INTO `t_info_report` VALUES ('14', '4', 'bb', '123334', '从小vz', null, null, '在线vzxc', null, null, null);

-- ----------------------------
-- Table structure for t_insectpic_indepot
-- ----------------------------
DROP TABLE IF EXISTS `t_insectpic_indepot`;
CREATE TABLE `t_insectpic_indepot` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `sm_collection` varchar(30) DEFAULT NULL,
  `pic` varchar(255) NOT NULL,
  `collecttime` datetime DEFAULT NULL,
  `large` varchar(255) DEFAULT NULL,
  `medium` varchar(255) DEFAULT NULL,
  `orders` int(11) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_52` (`sm_collection`),
  CONSTRAINT `FK_Reference_52` FOREIGN KEY (`sm_collection`) REFERENCES `t_insects_inbin_on_depot` (`sm_collection`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_insectpic_indepot
-- ----------------------------

-- ----------------------------
-- Table structure for t_insectscollectdistribution
-- ----------------------------
DROP TABLE IF EXISTS `t_insectscollectdistribution`;
CREATE TABLE `t_insectscollectdistribution` (
  `sm_insects` varchar(30) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`sm_insects`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_insectscollectdistribution
-- ----------------------------

-- ----------------------------
-- Table structure for t_insectsdistribution
-- ----------------------------
DROP TABLE IF EXISTS `t_insectsdistribution`;
CREATE TABLE `t_insectsdistribution` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `company` varchar(100) NOT NULL,
  `sm_insect` varchar(30) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `collectyear` varchar(4) NOT NULL,
  `collectmonth` char(2) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `AK_Key_2` (`company`,`collectyear`,`collectmonth`),
  KEY `FK_Reference_8` (`sm_insect`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`sm_insect`) REFERENCES `t_insectscollectdistribution` (`sm_insects`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_insectsdistribution
-- ----------------------------

-- ----------------------------
-- Table structure for t_insectsinvestuser
-- ----------------------------
DROP TABLE IF EXISTS `t_insectsinvestuser`;
CREATE TABLE `t_insectsinvestuser` (
  `username` varchar(10) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `realname` varchar(60) DEFAULT NULL,
  `title` varchar(40) DEFAULT NULL,
  `company` varchar(100) DEFAULT NULL,
  `moblie` varchar(20) DEFAULT NULL,
  `point` bigint(20) DEFAULT NULL,
  `hasaudit` bit(1) DEFAULT NULL,
  `modifer` varchar(30) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  `roleid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `FK_insectsinvestuser_role` (`roleid`),
  CONSTRAINT `FK_insectsinvestuser_role` FOREIGN KEY (`roleid`) REFERENCES `xx_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_insectsinvestuser
-- ----------------------------
INSERT INTO `t_insectsinvestuser` VALUES ('insect', 'c4ca4238a0b923820dcc509a6f75849b', 'investor', null, null, null, '0', '', null, '2016-07-31 17:49:53', '7');

-- ----------------------------
-- Table structure for t_insects_collection_on_farmer
-- ----------------------------
DROP TABLE IF EXISTS `t_insects_collection_on_farmer`;
CREATE TABLE `t_insects_collection_on_farmer` (
  `sm_collection` varchar(30) NOT NULL,
  `sm_farmer` varchar(30) NOT NULL,
  `collector` varchar(30) NOT NULL,
  `mobile` varchar(30) DEFAULT NULL,
  `company` varchar(30) DEFAULT NULL,
  `date_collection` varchar(10) NOT NULL,
  `grainname` varchar(30) DEFAULT NULL,
  `harvestdate` varchar(10) DEFAULT NULL,
  `dryingmethod` varchar(30) DEFAULT NULL,
  `entrydate` varchar(10) DEFAULT NULL,
  `storeperiod_` int(11) DEFAULT NULL,
  `innum` int(11) DEFAULT NULL,
  `storetechnology` varchar(30) DEFAULT NULL,
  `bintype` varchar(30) DEFAULT NULL,
  `container` varchar(30) DEFAULT NULL,
  `temperature` float DEFAULT NULL,
  `humidity` float DEFAULT NULL,
  `totalinsects` int(11) NOT NULL,
  `controltemperaturemeasures` varchar(30) DEFAULT NULL,
  `controlhumiditymeasures` varchar(30) DEFAULT NULL,
  `modifer` varchar(20) DEFAULT NULL,
  `modifydate` varchar(10) DEFAULT NULL,
  `impurity` float DEFAULT NULL,
  `moisture` float DEFAULT NULL,
  `purpose` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`sm_collection`),
  KEY `AK_Key_2` (`sm_farmer`,`collector`,`date_collection`),
  CONSTRAINT `FK_Reference_34` FOREIGN KEY (`sm_farmer`) REFERENCES `t_farmer` (`sm_farmer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_insects_collection_on_farmer
-- ----------------------------
INSERT INTO `t_insects_collection_on_farmer` VALUES ('100001', '10002', '', '', '', '2016-05-24', 'adsfqfs', null, null, null, null, null, '', 'qwqew', 'adsafas', null, null, '1', '', '', null, null, null, null, null);

-- ----------------------------
-- Table structure for t_insects_collect_pic
-- ----------------------------
DROP TABLE IF EXISTS `t_insects_collect_pic`;
CREATE TABLE `t_insects_collect_pic` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `sm_collection` varchar(30) DEFAULT NULL,
  `collecttime` datetime DEFAULT NULL,
  `large` varchar(255) DEFAULT NULL,
  `medium` varchar(255) DEFAULT NULL,
  `orders` int(11) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_51` (`sm_collection`),
  CONSTRAINT `FK_Reference_51` FOREIGN KEY (`sm_collection`) REFERENCES `t_insects_collection_on_farmer` (`sm_collection`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_insects_collect_pic
-- ----------------------------
INSERT INTO `t_insects_collect_pic` VALUES ('5', '100001', null, 'http://localhost:8080/grainInsects/upload/image/201605/f67cd7b2-cebe-4e8d-86e8-9de4fe26e321-large.jpg', 'http://localhost:8080/grainInsects/upload/image/201605/f67cd7b2-cebe-4e8d-86e8-9de4fe26e321-medium.jpg', null, 'http://localhost:8080/grainInsects/upload/image/201605/f67cd7b2-cebe-4e8d-86e8-9de4fe26e321-source.jpg', 'http://localhost:8080/grainInsects/upload/image/201605/f67cd7b2-cebe-4e8d-86e8-9de4fe26e321-thumbnail.jpg', '');

-- ----------------------------
-- Table structure for t_insects_identify
-- ----------------------------
DROP TABLE IF EXISTS `t_insects_identify`;
CREATE TABLE `t_insects_identify` (
  `sm` varchar(30) NOT NULL,
  `kind` varchar(30) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `longtitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `altitude` double DEFAULT NULL,
  `bintype` varchar(30) DEFAULT NULL,
  `host` varchar(30) DEFAULT NULL,
  `Habitlocation` varchar(30) DEFAULT NULL,
  `stage` varchar(30) DEFAULT NULL,
  `collectdate` varchar(30) DEFAULT NULL,
  `collector` varchar(30) DEFAULT NULL,
  `identifier` varchar(30) DEFAULT NULL,
  `Reviewer` varchar(30) DEFAULT NULL,
  `retainplace` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`sm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_insects_identify
-- ----------------------------

-- ----------------------------
-- Table structure for t_insects_inbin_on_depot
-- ----------------------------
DROP TABLE IF EXISTS `t_insects_inbin_on_depot`;
CREATE TABLE `t_insects_inbin_on_depot` (
  `sm_collection` varchar(30) NOT NULL,
  `lcbm` varchar(60) DEFAULT NULL,
  `collector` varchar(30) DEFAULT NULL,
  `mobile` varchar(30) DEFAULT NULL,
  `company_collector` varchar(30) DEFAULT NULL,
  `date_collection` varchar(10) DEFAULT NULL,
  `modifer` varchar(20) DEFAULT NULL,
  `modifydate` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`sm_collection`),
  KEY `FK_Reference_37` (`lcbm`),
  CONSTRAINT `FK_Reference_37` FOREIGN KEY (`lcbm`) REFERENCES `t_grainbin` (`lcbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_insects_inbin_on_depot
-- ----------------------------

-- ----------------------------
-- Table structure for t_insects_index
-- ----------------------------
DROP TABLE IF EXISTS `t_insects_index`;
CREATE TABLE `t_insects_index` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `jsbmc` varchar(100) NOT NULL,
  `modifer` varchar(30) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  `auditor` varchar(255) DEFAULT NULL,
  `audittime` varchar(255) DEFAULT NULL,
  `passaudit` bit(1) DEFAULT NULL,
  `auditadvice` longtext,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_insects_index
-- ----------------------------
INSERT INTO `t_insects_index` VALUES ('1', 'dfdsa', 'dfa', '2016-08-04 00:00:00', null, null, null, '大撒发放范德萨啊啊卡洛斯积分卡沙拉酱大幅拉开<strong><span style=\"background-color:#E56600;\">解放党数据卢卡斯开发萨拉丁</span></strong>法拉克房间啊  等等等等等等等等等等等等等等等等等等等等等等等等等等等等顶点的度');

-- ----------------------------
-- Table structure for t_insects_infactory
-- ----------------------------
DROP TABLE IF EXISTS `t_insects_infactory`;
CREATE TABLE `t_insects_infactory` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `sm_collect` varchar(30) DEFAULT NULL,
  `kind` varchar(30) DEFAULT NULL,
  `stage` varchar(30) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `food` varchar(30) DEFAULT NULL,
  `harm` varchar(30) DEFAULT NULL,
  `protectmeasure` varchar(100) DEFAULT NULL,
  `loc_collect` varchar(30) DEFAULT NULL,
  `host` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_36` (`sm_collect`),
  CONSTRAINT `FK_Reference_36` FOREIGN KEY (`sm_collect`) REFERENCES `t_insects_onfactory` (`sm_collect`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_insects_infactory
-- ----------------------------
INSERT INTO `t_insects_infactory` VALUES ('1', '100021', '', '', null, '', '', '', '122', 'qwerq');
INSERT INTO `t_insects_infactory` VALUES ('2', '100021', '', '', null, '', '', '', '122', 'qwerq');

-- ----------------------------
-- Table structure for t_insects_info_on_farmer
-- ----------------------------
DROP TABLE IF EXISTS `t_insects_info_on_farmer`;
CREATE TABLE `t_insects_info_on_farmer` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `sm_collection` varchar(30) DEFAULT NULL,
  `kind` varchar(30) DEFAULT NULL,
  `stage` varchar(30) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `food` varchar(30) DEFAULT NULL,
  `Harm` varchar(30) DEFAULT NULL,
  `protectmeasure` varchar(100) DEFAULT NULL,
  `loc_collect` varchar(30) DEFAULT NULL,
  `host` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_3` (`sm_collection`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`sm_collection`) REFERENCES `t_insects_collection_on_farmer` (`sm_collection`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_insects_info_on_farmer
-- ----------------------------
INSERT INTO `t_insects_info_on_farmer` VALUES ('3', '100001', '212', '', null, '', '', '', 'adf', '21');

-- ----------------------------
-- Table structure for t_insects_ondepot
-- ----------------------------
DROP TABLE IF EXISTS `t_insects_ondepot`;
CREATE TABLE `t_insects_ondepot` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `sm_collection` varchar(30) DEFAULT NULL,
  `kind` varchar(30) DEFAULT NULL,
  `stage` varchar(30) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `food` varchar(30) DEFAULT NULL,
  `harm` varchar(30) DEFAULT NULL,
  `measure_ctrl` varchar(100) DEFAULT NULL,
  `loc_collect` varchar(30) DEFAULT NULL,
  `host` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_38` (`sm_collection`),
  CONSTRAINT `FK_Reference_38` FOREIGN KEY (`sm_collection`) REFERENCES `t_insects_inbin_on_depot` (`sm_collection`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_insects_ondepot
-- ----------------------------

-- ----------------------------
-- Table structure for t_insects_onfactory
-- ----------------------------
DROP TABLE IF EXISTS `t_insects_onfactory`;
CREATE TABLE `t_insects_onfactory` (
  `sm_collect` varchar(30) NOT NULL,
  `sm_factory` varchar(30) NOT NULL,
  `collector` varchar(30) NOT NULL,
  `mobile` varchar(30) DEFAULT NULL,
  `company` varchar(30) DEFAULT NULL,
  `collectdate` varchar(10) NOT NULL,
  `grainkind` varchar(30) DEFAULT NULL,
  `harvestdate` varchar(10) DEFAULT NULL,
  `drymethod` varchar(30) DEFAULT NULL,
  `indate` varchar(10) DEFAULT NULL,
  `saveperiod` int(11) DEFAULT NULL,
  `innum` int(11) DEFAULT NULL,
  `storetechnology` varchar(30) DEFAULT NULL,
  `bintype` varchar(30) DEFAULT NULL,
  `container` varchar(30) DEFAULT NULL,
  `temperature` float DEFAULT NULL,
  `humidity` float DEFAULT NULL,
  `totalinsects` int(11) NOT NULL,
  `controltemperaturemeasures` varchar(30) DEFAULT NULL,
  `controlhumiditymeasures` varchar(30) DEFAULT NULL,
  `modifer` varchar(20) DEFAULT NULL,
  `modifydate` varchar(10) DEFAULT NULL,
  `grain_moisture` float DEFAULT NULL,
  `grain_stack_temperature` float DEFAULT NULL,
  `quality_change` varchar(30) DEFAULT NULL,
  `storage_form` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`sm_collect`),
  KEY `AK_Key_2` (`sm_factory`,`collector`,`collectdate`),
  CONSTRAINT `FK_Reference_35` FOREIGN KEY (`sm_factory`) REFERENCES `t_factory_info` (`sm_factory`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_insects_onfactory
-- ----------------------------
INSERT INTO `t_insects_onfactory` VALUES ('100021', '23303', '', null, null, '2016-05-24', 'adfsdf', null, null, null, '24', '22444', '', '', '', null, null, '2', '', '', null, null, null, null, '', null);

-- ----------------------------
-- Table structure for t_insects_specification
-- ----------------------------
DROP TABLE IF EXISTS `t_insects_specification`;
CREATE TABLE `t_insects_specification` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PID` bigint(20) DEFAULT NULL,
  `jsjg` bigint(20) DEFAULT NULL,
  `xh` int(11) DEFAULT NULL,
  `TZ` longtext,
  `zqxh` int(11) DEFAULT NULL,
  `source` varchar(100) DEFAULT NULL,
  `modifer` varchar(30) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_2` (`PID`),
  KEY `FK_Reference_5` (`jsjg`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`PID`) REFERENCES `t_insects_index` (`ID`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`jsjg`) REFERENCES `t_catalog_index` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_insects_specification
-- ----------------------------
INSERT INTO `t_insects_specification` VALUES ('1', '1', '10', '2', '213', '2', '31', '213', '2015-09-08 00:00:00');

-- ----------------------------
-- Table structure for t_insects_spec_pic
-- ----------------------------
DROP TABLE IF EXISTS `t_insects_spec_pic`;
CREATE TABLE `t_insects_spec_pic` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PID` bigint(20) DEFAULT NULL,
  `js` varchar(200) DEFAULT NULL,
  `tp` varchar(255) DEFAULT NULL,
  `source` varchar(100) DEFAULT NULL,
  `modifer` varchar(30) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_7` (`PID`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`PID`) REFERENCES `t_insects_specification` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_insects_spec_pic
-- ----------------------------

-- ----------------------------
-- Table structure for t_insect_onfield
-- ----------------------------
DROP TABLE IF EXISTS `t_insect_onfield`;
CREATE TABLE `t_insect_onfield` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `direct` bigint(20) DEFAULT NULL,
  `collector` varchar(30) DEFAULT NULL,
  `mobile` varchar(30) DEFAULT NULL,
  `company` varchar(30) DEFAULT NULL,
  `collectdate` varchar(10) DEFAULT NULL,
  `longtitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `altitude` double DEFAULT NULL,
  `temperature` float DEFAULT NULL,
  `humidity` float DEFAULT NULL,
  `kind` varchar(30) DEFAULT NULL,
  `stage` varchar(30) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `food` varchar(30) DEFAULT NULL,
  `harm` varchar(30) DEFAULT NULL,
  `host` varchar(30) DEFAULT NULL,
  `modifer` varchar(20) DEFAULT NULL,
  `modifydate` varchar(10) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `grainkind` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_81` (`direct`),
  CONSTRAINT `FK_Reference_81` FOREIGN KEY (`direct`) REFERENCES `xx_area` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_insect_onfield
-- ----------------------------

-- ----------------------------
-- Table structure for t_miteidentify
-- ----------------------------
DROP TABLE IF EXISTS `t_miteidentify`;
CREATE TABLE `t_miteidentify` (
  `sm` varchar(30) NOT NULL,
  `kind` varchar(30) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `longtitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `altitude` double DEFAULT NULL,
  `bintype` varchar(30) DEFAULT NULL,
  `host` varchar(30) DEFAULT NULL,
  `habitlocation` varchar(30) DEFAULT NULL,
  `stage` varchar(30) DEFAULT NULL,
  `collectdate` varchar(30) DEFAULT NULL,
  `collector` varchar(30) DEFAULT NULL,
  `Slideproducer` varchar(30) DEFAULT NULL,
  `identifier` varchar(30) DEFAULT NULL,
  `reviewer` varchar(30) DEFAULT NULL,
  `retianplace` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_miteidentify
-- ----------------------------

-- ----------------------------
-- Table structure for t_opinionobject
-- ----------------------------
DROP TABLE IF EXISTS `t_opinionobject`;
CREATE TABLE `t_opinionobject` (
  `t_expertopinion` bigint(20) NOT NULL,
  `grain_depots` varchar(60) NOT NULL,
  PRIMARY KEY (`t_expertopinion`,`grain_depots`),
  KEY `FKB34024B6889DA0DC` (`t_expertopinion`),
  KEY `FKB34024B6A7039687` (`grain_depots`),
  CONSTRAINT `FKB34024B6889DA0DC` FOREIGN KEY (`t_expertopinion`) REFERENCES `t_expertopinion` (`id`),
  CONSTRAINT `FKB34024B6A7039687` FOREIGN KEY (`grain_depots`) REFERENCES `t_graindepot` (`lkbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_opinionobject
-- ----------------------------
INSERT INTO `t_opinionobject` VALUES ('1', '10000');
INSERT INTO `t_opinionobject` VALUES ('2', ' 5650001');
INSERT INTO `t_opinionobject` VALUES ('2', '10000');
INSERT INTO `t_opinionobject` VALUES ('6', ' 100002');
INSERT INTO `t_opinionobject` VALUES ('6', ' 700000');

-- ----------------------------
-- Table structure for t_preventprocess
-- ----------------------------
DROP TABLE IF EXISTS `t_preventprocess`;
CREATE TABLE `t_preventprocess` (
  `sm` varchar(10) NOT NULL,
  `proname` varchar(60) NOT NULL,
  `catalogid` bigint(20) DEFAULT NULL,
  `proctypeid` varchar(10) DEFAULT NULL,
  `keywords` varchar(200) NOT NULL,
  `material` varchar(200) DEFAULT NULL,
  `procway` varchar(200) DEFAULT NULL,
  `area` varchar(200) NOT NULL,
  `seasion` varchar(60) NOT NULL,
  `process` longtext NOT NULL,
  `note` longtext,
  `source` varchar(100) DEFAULT NULL,
  `modifer` varchar(30) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  PRIMARY KEY (`sm`),
  KEY `FK_Reference_26` (`proctypeid`),
  KEY `FK_fk_fzgy_flb` (`catalogid`),
  CONSTRAINT `FK_Reference_26` FOREIGN KEY (`proctypeid`) REFERENCES `t_proctype` (`sm`),
  CONSTRAINT `FK_fk_fzgy_flb` FOREIGN KEY (`catalogid`) REFERENCES `t_catalog_index` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='防治工艺的有效性如何进行描述比较合理';

-- ----------------------------
-- Records of t_preventprocess
-- ----------------------------
INSERT INTO `t_preventprocess` VALUES ('34', '234', null, '4', 'sdfa', 'af', null, 'saf', 'asf', 'asfa', null, null, 'a', '2016-08-06 22:26:54');
INSERT INTO `t_preventprocess` VALUES ('80', '90', null, '4', 'dsfa', null, null, 'ads', '2432', 'dsaf', null, null, 'a', '2016-08-06 22:27:20');
INSERT INTO `t_preventprocess` VALUES ('dsfa', 'ads', null, 'afs', 'sadf', null, null, 'asfd', 'fas', 'asdfa', null, null, 'a', '2016-08-06 21:39:27');
INSERT INTO `t_preventprocess` VALUES ('sadf', 'asdf', null, 'czv', 'afs', null, null, 'dsf', 'afds', 'asfas<br />', null, null, 'a', '2016-08-06 22:12:19');
INSERT INTO `t_preventprocess` VALUES ('sdaf', 'a', null, 'ad', 'dsfa', null, null, 'sadf', 'dsaf', 'fdsaf', null, null, 'a', '2016-08-06 22:28:14');
INSERT INTO `t_preventprocess` VALUES ('sdf', 'as', null, null, 'sdfa', null, null, 'sadf', 'saf', 'af', null, null, 'a', '2016-08-06 21:48:28');
INSERT INTO `t_preventprocess` VALUES ('wer', 'wer', null, '1', 'wer', null, null, 'dfa', 'sadf', 'hsfg', null, null, 'a', '2016-08-06 22:13:03');

-- ----------------------------
-- Table structure for t_preventprocessdetach
-- ----------------------------
DROP TABLE IF EXISTS `t_preventprocessdetach`;
CREATE TABLE `t_preventprocessdetach` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PID` varchar(10) DEFAULT NULL,
  `detach` varchar(255) NOT NULL,
  `source` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_53` (`PID`),
  CONSTRAINT `FK_Reference_53` FOREIGN KEY (`PID`) REFERENCES `t_preventprocess` (`sm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_preventprocessdetach
-- ----------------------------

-- ----------------------------
-- Table structure for t_proctype
-- ----------------------------
DROP TABLE IF EXISTS `t_proctype`;
CREATE TABLE `t_proctype` (
  `sm` varchar(10) NOT NULL,
  `PID` varchar(10) DEFAULT NULL,
  `proctype` varchar(20) DEFAULT NULL COMMENT '综合防治\r\n            化学防治\r\n            物理防治\r\n            天敌昆虫防治\r\n            ',
  `source` varchar(100) DEFAULT NULL,
  `modifer` varchar(30) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  `auditor` varchar(255) DEFAULT NULL,
  `audittime` varchar(255) DEFAULT NULL,
  `full_name` longtext NOT NULL,
  `passaudit` bit(1) DEFAULT NULL,
  `tree_path` varchar(255) NOT NULL,
  PRIMARY KEY (`sm`),
  KEY `FK_Reference_25` (`PID`),
  CONSTRAINT `FK_Reference_25` FOREIGN KEY (`PID`) REFERENCES `t_proctype` (`sm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_proctype
-- ----------------------------
INSERT INTO `t_proctype` VALUES ('1', 'ad', '1', null, null, null, null, null, 'sadag1', null, ',ad,');
INSERT INTO `t_proctype` VALUES ('2', null, '2', null, null, null, null, null, '2', null, ',');
INSERT INTO `t_proctype` VALUES ('3', 'ad', '3', null, null, null, null, null, 'sadag3', null, ',ad,');
INSERT INTO `t_proctype` VALUES ('4', 'ad', '4', null, null, null, null, null, 'sadag4', null, ',ad,');
INSERT INTO `t_proctype` VALUES ('ad', null, 'sadag', null, null, null, null, null, 'sadag', null, ',');
INSERT INTO `t_proctype` VALUES ('afs', 'saf', 'asfd', null, null, null, null, null, 'asdfasfasfd', null, ',sdaf,saf,');
INSERT INTO `t_proctype` VALUES ('czv', 'ad', 'dasf', null, null, null, null, null, 'sadagdasf', null, ',ad,');
INSERT INTO `t_proctype` VALUES ('fdasf', 'sdaf', 'asfags', null, null, null, null, null, 'asdfasfags', null, ',sdaf,');
INSERT INTO `t_proctype` VALUES ('saf', 'sdaf', 'asf', null, null, null, null, null, 'asdfasf', null, ',sdaf,');
INSERT INTO `t_proctype` VALUES ('sdaf', null, 'asdf', null, null, null, null, null, 'asdf', null, ',');

-- ----------------------------
-- Table structure for t_realdata_pic
-- ----------------------------
DROP TABLE IF EXISTS `t_realdata_pic`;
CREATE TABLE `t_realdata_pic` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PID` bigint(20) DEFAULT NULL,
  `pic` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_15` (`PID`),
  CONSTRAINT `FK_Reference_15` FOREIGN KEY (`PID`) REFERENCES `t_device_realdata` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_realdata_pic
-- ----------------------------
INSERT INTO `t_realdata_pic` VALUES ('1', '2', 'realdata/201623/23d7d6a9-1b7a-46f5-a006-81458c01e3d6.xls');
INSERT INTO `t_realdata_pic` VALUES ('2', '9', '/upload/image/201605/c35d7f69-537a-4a40-9110-6d3ecc696fa4.jpg');
INSERT INTO `t_realdata_pic` VALUES ('3', '10', '/upload/image/201605/f4b60071-f711-403e-afc9-34a5cd587e9a.jpg');

-- ----------------------------
-- Table structure for t_remotediagnosisapply
-- ----------------------------
DROP TABLE IF EXISTS `t_remotediagnosisapply`;
CREATE TABLE `t_remotediagnosisapply` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `applier` varchar(60) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `content` varchar(400) DEFAULT NULL,
  `applytime` varchar(30) DEFAULT NULL,
  `expert` varchar(40) DEFAULT NULL COMMENT '如有多个专家，则专家间以逗号间开',
  `result` varchar(400) DEFAULT NULL,
  `pushtime` datetime DEFAULT NULL,
  `PushState` char(1) DEFAULT '0' COMMENT '0: 没有推送\r\n            1：推送成功',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_remotediagnosisapply
-- ----------------------------

-- ----------------------------
-- Table structure for t_storageprocess
-- ----------------------------
DROP TABLE IF EXISTS `t_storageprocess`;
CREATE TABLE `t_storageprocess` (
  `prono` varchar(10) NOT NULL,
  `proname` varchar(60) NOT NULL,
  `process` longtext NOT NULL,
  `note` longtext,
  `source` varchar(100) DEFAULT NULL,
  `modifer` varchar(30) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  PRIMARY KEY (`prono`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_storageprocess
-- ----------------------------

-- ----------------------------
-- Table structure for t_task
-- ----------------------------
DROP TABLE IF EXISTS `t_task`;
CREATE TABLE `t_task` (
  `insects_id` bigint(20) NOT NULL,
  `distribution_id` bigint(20) NOT NULL,
  `catalog_id` bigint(20) NOT NULL,
  PRIMARY KEY (`insects_id`,`distribution_id`),
  KEY `FKCB6316708E0590A3` (`insects_id`),
  KEY `FKCB6316709C5F90B8` (`distribution_id`),
  KEY `FKCB6316702CFD8B86` (`distribution_id`),
  KEY `FKCB631670F02B3A55` (`catalog_id`),
  CONSTRAINT `FKCB6316702CFD8B86` FOREIGN KEY (`distribution_id`) REFERENCES `t_insects_index` (`ID`),
  CONSTRAINT `FKCB6316708E0590A3` FOREIGN KEY (`insects_id`) REFERENCES `t_task_distribution` (`id`),
  CONSTRAINT `FKCB6316709C5F90B8` FOREIGN KEY (`distribution_id`) REFERENCES `t_catalog_index` (`ID`),
  CONSTRAINT `FKCB631670F02B3A55` FOREIGN KEY (`catalog_id`) REFERENCES `t_task_distribution` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of t_task
-- ----------------------------

-- ----------------------------
-- Table structure for t_task_distribution
-- ----------------------------
DROP TABLE IF EXISTS `t_task_distribution`;
CREATE TABLE `t_task_distribution` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deadline` datetime DEFAULT NULL,
  `distributetime` datetime DEFAULT NULL,
  `modifytime` datetime DEFAULT NULL,
  `tasktype` int(11) DEFAULT NULL,
  `username` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of t_task_distribution
-- ----------------------------

-- ----------------------------
-- Table structure for t_userrank
-- ----------------------------
DROP TABLE IF EXISTS `t_userrank`;
CREATE TABLE `t_userrank` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rankvalue` int(11) DEFAULT NULL,
  `rankname` varchar(100) NOT NULL,
  `usertype` int(11) NOT NULL COMMENT '0  专家\r\n            1  研究员\r\n            2  直属库\r\n            3  加工所',
  `modifer` varchar(30) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_userrank
-- ----------------------------

-- ----------------------------
-- Table structure for user_device
-- ----------------------------
DROP TABLE IF EXISTS `user_device`;
CREATE TABLE `user_device` (
  `device_id` varchar(30) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of user_device
-- ----------------------------

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` varchar(255) NOT NULL,
  `accuracy` varchar(255) DEFAULT NULL,
  `build_id` varchar(255) DEFAULT NULL,
  `floor_id` int(11) DEFAULT NULL,
  `error_code` int(11) DEFAULT NULL,
  `nearest_tag_id` varchar(255) DEFAULT NULL,
  `timestamp_millisecond` double DEFAULT NULL,
  `x_millimeter` int(11) DEFAULT NULL,
  `y_millimeter` int(11) DEFAULT NULL,
  `alarm` bit(1) DEFAULT NULL,
  `info` varchar(255) DEFAULT NULL,
  `compass` int(11) DEFAULT NULL,
  `device_id` int(11) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  `region_id` int(11) DEFAULT NULL,
  `start_time` double DEFAULT NULL,
  `stop_time` double DEFAULT NULL,
  `user_code` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('0', null, null, null, null, null, null, null, null, null, null, null, '1', '1', '1', '0', '0', '11', 'ZZ');

-- ----------------------------
-- Table structure for xx_ad
-- ----------------------------
DROP TABLE IF EXISTS `xx_ad`;
CREATE TABLE `xx_ad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `orders` int(11) DEFAULT NULL,
  `begin_date` datetime DEFAULT NULL,
  `content` longtext,
  `end_date` datetime DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `type` int(11) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `ad_position` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6D301C280531B1C` (`ad_position`),
  CONSTRAINT `FK6D301C280531B1C` FOREIGN KEY (`ad_position`) REFERENCES `xx_ad_position` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xx_ad
-- ----------------------------

-- ----------------------------
-- Table structure for xx_ad_position
-- ----------------------------
DROP TABLE IF EXISTS `xx_ad_position`;
CREATE TABLE `xx_ad_position` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `height` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `template` longtext NOT NULL,
  `width` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xx_ad_position
-- ----------------------------

-- ----------------------------
-- Table structure for xx_area
-- ----------------------------
DROP TABLE IF EXISTS `xx_area`;
CREATE TABLE `xx_area` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `graindirect` int(11) NOT NULL,
  `full_name` longtext NOT NULL,
  `name` varchar(100) NOT NULL,
  `tree_path` varchar(255) NOT NULL,
  `parent` bigint(20) DEFAULT NULL,
  `orders` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_79` (`graindirect`),
  KEY `FK9E19DA6CB5FC5462` (`parent`),
  CONSTRAINT `FK9E19DA6CB5FC5462` FOREIGN KEY (`parent`) REFERENCES `xx_area` (`id`),
  CONSTRAINT `FK_Reference_79` FOREIGN KEY (`graindirect`) REFERENCES `t_graindirection` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xx_area
-- ----------------------------
INSERT INTO `xx_area` VALUES ('1', '2016-04-06 10:59:16', '2016-04-22 13:10:08', '1', '北京市', '北京市', ',', null, '1');
INSERT INTO `xx_area` VALUES ('30', '2016-04-23 22:22:39', '2016-04-23 22:22:39', '7', '广州', '广州', ',', null, '200');
INSERT INTO `xx_area` VALUES ('31', '2016-04-23 22:36:42', '2016-04-23 22:36:42', '5', '上海', '上海', ',', null, '100');
INSERT INTO `xx_area` VALUES ('32', '2016-04-23 23:33:40', '2016-04-23 23:33:40', '1', '北京市昌平', '昌平', ',1,', '1', null);
INSERT INTO `xx_area` VALUES ('33', '2016-04-23 23:33:50', '2016-04-23 23:33:50', '1', '北京市通州', '通州', ',1,', '1', null);
INSERT INTO `xx_area` VALUES ('34', '2016-04-23 23:44:44', '2016-04-23 23:44:44', '1', '北京市海淀区', '海淀区', ',1,', '1', null);

-- ----------------------------
-- Table structure for xx_article
-- ----------------------------
DROP TABLE IF EXISTS `xx_article`;
CREATE TABLE `xx_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `content` longtext,
  `hits` bigint(20) NOT NULL,
  `is_publication` bit(1) NOT NULL,
  `is_top` bit(1) NOT NULL,
  `seo_description` varchar(255) DEFAULT NULL,
  `seo_keywords` varchar(255) DEFAULT NULL,
  `seo_title` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `article_category` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK636982B7CA3B1F7` (`article_category`),
  CONSTRAINT `FK636982B7CA3B1F7` FOREIGN KEY (`article_category`) REFERENCES `xx_article_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xx_article
-- ----------------------------

-- ----------------------------
-- Table structure for xx_article_category
-- ----------------------------
DROP TABLE IF EXISTS `xx_article_category`;
CREATE TABLE `xx_article_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent` bigint(20) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `orders` int(11) DEFAULT NULL,
  `grade` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `seo_description` varchar(255) DEFAULT NULL,
  `seo_keywords` varchar(255) DEFAULT NULL,
  `seo_title` varchar(255) DEFAULT NULL,
  `tree_path` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK57108986F587647A` (`parent`),
  CONSTRAINT `FK57108986F587647A` FOREIGN KEY (`parent`) REFERENCES `xx_article_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xx_article_category
-- ----------------------------
INSERT INTO `xx_article_category` VALUES ('1', null, '2016-05-28 18:37:06', '2016-05-28 18:37:06', null, '0', '新闻', null, null, null, ',');

-- ----------------------------
-- Table structure for xx_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `xx_article_tag`;
CREATE TABLE `xx_article_tag` (
  `articles` bigint(20) NOT NULL,
  `tags` bigint(20) NOT NULL,
  PRIMARY KEY (`articles`,`tags`),
  KEY `FKB9183E12C842716F` (`tags`),
  CONSTRAINT `FKB9183E1229F6DEEF` FOREIGN KEY (`articles`) REFERENCES `xx_article` (`id`),
  CONSTRAINT `FKB9183E12C842716F` FOREIGN KEY (`tags`) REFERENCES `xx_tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xx_article_tag
-- ----------------------------

-- ----------------------------
-- Table structure for xx_attribute
-- ----------------------------
DROP TABLE IF EXISTS `xx_attribute`;
CREATE TABLE `xx_attribute` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `orders` int(11) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `property_index` int(11) NOT NULL,
  `insect_category` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE81F41D33D2DE77` (`insect_category`),
  CONSTRAINT `FKE81F41D33D2DE77` FOREIGN KEY (`insect_category`) REFERENCES `t_catalog_index` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xx_attribute
-- ----------------------------

-- ----------------------------
-- Table structure for xx_attribute_option
-- ----------------------------
DROP TABLE IF EXISTS `xx_attribute_option`;
CREATE TABLE `xx_attribute_option` (
  `attribute` bigint(20) NOT NULL,
  `options` varchar(255) DEFAULT NULL,
  KEY `FK96E026D77BC11CED` (`attribute`),
  CONSTRAINT `FK96E026D77BC11CED` FOREIGN KEY (`attribute`) REFERENCES `xx_attribute` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xx_attribute_option
-- ----------------------------

-- ----------------------------
-- Table structure for xx_catalogindex_tag
-- ----------------------------
DROP TABLE IF EXISTS `xx_catalogindex_tag`;
CREATE TABLE `xx_catalogindex_tag` (
  `catalog_indexs` bigint(20) NOT NULL,
  `tags` bigint(20) NOT NULL,
  PRIMARY KEY (`catalog_indexs`,`tags`),
  KEY `FK62853E13D672E728` (`tags`),
  KEY `FK62853E13EE211CC9` (`catalog_indexs`),
  CONSTRAINT `FK62853E13D672E728` FOREIGN KEY (`tags`) REFERENCES `xx_tag` (`id`),
  CONSTRAINT `FK62853E13EE211CC9` FOREIGN KEY (`catalog_indexs`) REFERENCES `t_catalog_index` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xx_catalogindex_tag
-- ----------------------------

-- ----------------------------
-- Table structure for xx_friend_link
-- ----------------------------
DROP TABLE IF EXISTS `xx_friend_link`;
CREATE TABLE `xx_friend_link` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `orders` int(11) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `type` int(11) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xx_friend_link
-- ----------------------------

-- ----------------------------
-- Table structure for xx_log
-- ----------------------------
DROP TABLE IF EXISTS `xx_log`;
CREATE TABLE `xx_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `content` longtext,
  `ip` varchar(255) NOT NULL,
  `operation` varchar(255) NOT NULL,
  `operator` varchar(255) DEFAULT NULL,
  `parameter` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xx_log
-- ----------------------------

-- ----------------------------
-- Table structure for xx_navigation
-- ----------------------------
DROP TABLE IF EXISTS `xx_navigation`;
CREATE TABLE `xx_navigation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `orders` int(11) DEFAULT NULL,
  `is_blank_target` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `position` int(11) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xx_navigation
-- ----------------------------

-- ----------------------------
-- Table structure for xx_op_register
-- ----------------------------
DROP TABLE IF EXISTS `xx_op_register`;
CREATE TABLE `xx_op_register` (
  `id` int(11) NOT NULL,
  `pid` int(11) DEFAULT NULL,
  `opname` varchar(60) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `Icon` varchar(60) DEFAULT NULL,
  `hasdisplay` int(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_76` (`pid`),
  KEY `FKD3DA4C82D6D6E767` (`pid`),
  CONSTRAINT `FKD3DA4C82D6D6E767` FOREIGN KEY (`pid`) REFERENCES `xx_op_register` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xx_op_register
-- ----------------------------
INSERT INTO `xx_op_register` VALUES ('-1', null, '获取菜单', 'admin/menu/display', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('1', null, '文件操作', 'admin/file', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('900', null, '系统管理', null, 'computer_key', '1', null);
INSERT INTO `xx_op_register` VALUES ('901', '900', '角色管理', 'admin/role/manager', 'icon_group', '1', null);
INSERT INTO `xx_op_register` VALUES ('902', '901', '角色管理-获取列表', 'admin/role/dataGrid', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('903', '901', '角色管理-增加角色', 'admin/role/add', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('904', '901', '角色管理-修改角色', 'admin/role/edit', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('905', '901', '角色管理-删除角色', 'admin/role/delete', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('906', '901', '角色管理-授权-进入授权页面', 'admin/role/grantPage', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('907', '901', '角色管理-授权-权限列表', 'admin/role/tree', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('908', '901', '角色管理-授权-授权', 'admin/role/grant', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('909', '900', '用户管理', 'admin/listentrance', 'icon_user', '1', null);
INSERT INTO `xx_op_register` VALUES ('910', '909', '用户管理-ajax获取用户列表', 'admin/list', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('911', '909', '用户管理-删除用户', 'admin/remove', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('912', '909', '用户管理-修改用户', 'admin/edit', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('913', '909', '用户管理-进入新增用户界面', 'admin/addEntrance', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('914', '900', '注销用户', 'admin/logout', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('915', '909', '角色管理-授权-alltree', 'admin/role/allTree', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('916', '909', '用户管理-检查重名', 'admin/checkName', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('917', '909', '用户管理-add', 'admin/add', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('920', '900', '导航管理', 'admin/navigation/list', 'thumb_up', '1', null);
INSERT INTO `xx_op_register` VALUES ('921', '920', '导航管理', 'admin/navigation', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('10000', null, '基础资料', null, 'text_padding_left', '1', null);
INSERT INTO `xx_op_register` VALUES ('10100', '10000', '储粮区', 'admin/graindirection/list', 'world_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('10101', '10100', '储粮区操作', 'admin/graindirection', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('10200', '10000', '地区', 'admin/area/list', 'map', '1', null);
INSERT INTO `xx_op_register` VALUES ('10201', '10200', '地区操作', 'admin/area', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('10300', '10000', '粮库信息', 'admin/depot/depot/entrance', 'house', '1', null);
INSERT INTO `xx_op_register` VALUES ('10301', '10300', '粮库信息操作', 'admin/depot/depot', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('10400', '10000', '库管员', 'admin/depot/user/entrance', 'icon_group', '1', null);
INSERT INTO `xx_op_register` VALUES ('10401', '10400', '库管员操作', 'admin/depot/user', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('10500', '10000', '专家用户', 'admin/expert/entrance', 'icon_group', '1', null);
INSERT INTO `xx_op_register` VALUES ('10501', '10500', '专家用户-操作', 'admin/expert', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('10600', '10000', '虫调用户', 'admin/investor/entrance', 'icon_group', '1', null);
INSERT INTO `xx_op_register` VALUES ('10601', '10600', '虫调用户-操作', 'admin/investor', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('10700', '10000', '友情链接', 'admin/friend_link/list', 'link', '1', null);
INSERT INTO `xx_op_register` VALUES ('10701', '10700', '友情链接-操作', 'admin/friend_link', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('10800', '10000', '插件管理', null, 'plugin', '1', null);
INSERT INTO `xx_op_register` VALUES ('10810', '10800', '存储插件', 'admin/storage_plugin/list', 'plugin_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('10811', '10810', '存储插件-操作', 'admin/storage_plugin', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('10900', '10000', '缓存管理', 'admin/cache/clear', 'color_wheel', '1', null);
INSERT INTO `xx_op_register` VALUES ('10901', '10900', '缓存管理-操作', 'admin/cache', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('11000', '10000', '静态化管理', 'admin/static/build', 'color_wheel', '1', null);
INSERT INTO `xx_op_register` VALUES ('11001', '11000', '静态化管理-操作', 'admin/static', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('11100', '10000', '系统设置', 'admin/setting/edit', 'computer', '1', null);
INSERT INTO `xx_op_register` VALUES ('11101', '11100', '系统设置-操作', 'admin/setting', '', '0', '');
INSERT INTO `xx_op_register` VALUES ('11200', '10000', '图标管理', 'admin/catalogIcon/list', '', '1', '');
INSERT INTO `xx_op_register` VALUES ('40000', null, '广告管理', '', 'bell', '1', null);
INSERT INTO `xx_op_register` VALUES ('40100', '40000', '广告位', 'admin/ad_position/list', 'bell_link', '1', null);
INSERT INTO `xx_op_register` VALUES ('40101', '40100', '广告位-操作', 'admin/ad_position', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('40200', '40000', '广告', 'admin/ad/list', 'bell_add', '1', null);
INSERT INTO `xx_op_register` VALUES ('40201', '40200', '广告-操作', 'admin/ad', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('40300', '40000', '导航设置', 'admin/navigation/list', 'connect', '1', null);
INSERT INTO `xx_op_register` VALUES ('50000', null, '文章管理', '', 'text_dropcaps', '1', null);
INSERT INTO `xx_op_register` VALUES ('50100', '50000', '类别管理', 'admin/article_category/list', 'text_list_numbers', '1', null);
INSERT INTO `xx_op_register` VALUES ('50101', '50100', '类别管理-操作', 'admin/article_category', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('50200', '50000', '标签管理', 'admin/tag/list', 'wand', '1', null);
INSERT INTO `xx_op_register` VALUES ('50201', '50200', '类别管理-操作', 'admin/tag', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('50300', '50000', '文章管理', 'admin/article/list', 'text_list_numbers', '1', null);
INSERT INTO `xx_op_register` VALUES ('50301', '50300', '文章管理-操作', 'admin/article', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('60000', null, '知识库', '', 'book_open', '1', null);
INSERT INTO `xx_op_register` VALUES ('60100', '60000', '害虫类别', 'admin/catalogIndex/list', 'palette', '1', null);
INSERT INTO `xx_op_register` VALUES ('60101', '60100', '害虫类别-操作', 'admin/catalogIndex', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('60200', '60000', '防治工艺类型', 'admin/proctype/list', 'brick', '1', null);
INSERT INTO `xx_op_register` VALUES ('60201', '60200', '防治工艺类型-操作', 'admin/proctype', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('60300', '60000', '防治工艺', 'admin/process/list', 'paintbrush', '1', null);
INSERT INTO `xx_op_register` VALUES ('60301', '60300', '防治工艺-操作', 'admin/process', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('60400', '60000', '检索表', 'admin/insectsIndex/list', 'book_open', '1', null);
INSERT INTO `xx_op_register` VALUES ('100000', null, '虫调', null, 'icon_group', '1', null);
INSERT INTO `xx_op_register` VALUES ('110000', null, '虫调信息采集', null, 'world_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('110100', '110000', '农户', 'investor/farmer/list', 'icon_group', '1', null);
INSERT INTO `xx_op_register` VALUES ('110200', '110000', '加工厂', 'investor/factory/list', 'box', '1', null);
INSERT INTO `xx_op_register` VALUES ('110300', '110000', '直属库', 'investor/depot/list', 'bricks', '1', null);
INSERT INTO `xx_op_register` VALUES ('110400', '110000', '野外', 'investor/field/list', 'rainbow', '1', null);
INSERT INTO `xx_op_register` VALUES ('110500', '110000', '昆虫采集分布登记', 'investor/register/collectDistribution/list', 'tab_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('110600', '110000', '我的足迹', 'investor/common/myfootprint', 'map', '1', null);
INSERT INTO `xx_op_register` VALUES ('110700', '110000', '昆虫鉴定登记', 'investor/register/insectsIdentify/list', 'tab_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('110800', '110000', '螨鉴定登记', 'investor/register/miteIdentify/list', 'tab_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('120000', null, '个人中心', null, 'icon_group', '1', null);
INSERT INTO `xx_op_register` VALUES ('120100', '120000', '消息中心', '', 'bell', '1', null);
INSERT INTO `xx_op_register` VALUES ('120200', '120000', '修改个人信息', 'investor/common/user/editInvestorEntrance', 'cog_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('120300', '120000', '注销', null, 'cog_delete', '1', null);
INSERT INTO `xx_op_register` VALUES ('130000', null, '其他', null, null, '1', null);
INSERT INTO `xx_op_register` VALUES ('200000', null, '粮库', '', '', '1', null);
INSERT INTO `xx_op_register` VALUES ('210000', null, '实时监控', 'resources/views/portal/location_main.jsp', 'brick', '1', null);
INSERT INTO `xx_op_register` VALUES ('220000', null, '数据维护', null, 'database_table', '1', null);
INSERT INTO `xx_op_register` VALUES ('220100', '220000', '人员管理', 'resources/views/portal/location_personmanage.html', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` VALUES ('220110', '220000', '分组管理', '', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` VALUES ('220200', '220000', '区域管理', '', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` VALUES ('220210', '220000', '设备管理', '', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` VALUES ('220300', '220000', '监区管理', '', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` VALUES ('300000', null, '专家', null, '', '1', null);
INSERT INTO `xx_op_register` VALUES ('310000', null, '虫调工作', null, 'text_signature', '1', null);
INSERT INTO `xx_op_register` VALUES ('310100', '310000', '足迹分布', null, 'map', '1', null);
INSERT INTO `xx_op_register` VALUES ('310200', '310000', '昆虫分布', null, 'map', '1', null);
INSERT INTO `xx_op_register` VALUES ('320000', null, '防治工艺效果', null, 'color_wheel', '1', null);
INSERT INTO `xx_op_register` VALUES ('330000', null, '监测预报', null, 'bell', '1', null);
INSERT INTO `xx_op_register` VALUES ('330100', '330000', '示范库分布', 'expert/monitor/depotLoc', 'bricks', '1', null);
INSERT INTO `xx_op_register` VALUES ('330200', '330000', '数据监控', 'expert/monitor/insectsTemperatureHunmidity', 'contrast', '1', null);
INSERT INTO `xx_op_register` VALUES ('330300', '330000', '专家意见', 'expert/opinion/list', 'comments', '1', null);
INSERT INTO `xx_op_register` VALUES ('340000', null, '数据审核', null, 'book_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('340100', '340000', '虫螨基础数据审核', 'expert/audit/catalogIndex/list', 'pencil', '1', null);
INSERT INTO `xx_op_register` VALUES ('340200', '340000', '检索表审核', 'expert/audit/insectsIndex/list', 'pencil', '1', null);
INSERT INTO `xx_op_register` VALUES ('350000', null, '处理咨询', null, 'images', '1', null);
INSERT INTO `xx_op_register` VALUES ('400000', null, '农户', null, '', '1', null);
INSERT INTO `xx_op_register` VALUES ('410000', null, '虫调录入', null, 'cup_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('420000', null, '虫害咨询', null, 'comments', '1', null);
INSERT INTO `xx_op_register` VALUES ('500000', null, '加工厂', null, '', '1', null);
INSERT INTO `xx_op_register` VALUES ('510000', null, '虫调录入', null, 'cup_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('520000', null, '虫调咨询', null, 'comments', '1', null);

-- ----------------------------
-- Table structure for xx_plugin_config
-- ----------------------------
DROP TABLE IF EXISTS `xx_plugin_config`;
CREATE TABLE `xx_plugin_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `orders` int(11) DEFAULT NULL,
  `is_enabled` bit(1) NOT NULL,
  `plugin_id` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `plugin_id` (`plugin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xx_plugin_config
-- ----------------------------
INSERT INTO `xx_plugin_config` VALUES ('1', '2016-05-03 16:16:35', '2016-05-03 16:16:41', '100', '', 'filePlugin');

-- ----------------------------
-- Table structure for xx_plugin_config_attribute
-- ----------------------------
DROP TABLE IF EXISTS `xx_plugin_config_attribute`;
CREATE TABLE `xx_plugin_config_attribute` (
  `plugin_config` bigint(20) NOT NULL,
  `attributes` varchar(255) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`plugin_config`,`name`),
  CONSTRAINT `FK42CB712CE174C3E7` FOREIGN KEY (`plugin_config`) REFERENCES `xx_plugin_config` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xx_plugin_config_attribute
-- ----------------------------

-- ----------------------------
-- Table structure for xx_role
-- ----------------------------
DROP TABLE IF EXISTS `xx_role`;
CREATE TABLE `xx_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_system` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xx_role
-- ----------------------------
INSERT INTO `xx_role` VALUES ('1', '2016-04-06 10:35:56', '2016-04-06 11:50:13', '管理员aaaa', '', 'admin');
INSERT INTO `xx_role` VALUES ('2', '2016-04-06 11:49:45', '2016-04-06 11:49:45', 'bbb', '\0', 'bb');
INSERT INTO `xx_role` VALUES ('3', '2016-04-21 16:25:07', '2016-04-23 07:10:08', '管理员', '\0', '管理员');
INSERT INTO `xx_role` VALUES ('4', '2016-07-30 23:20:31', '2016-07-30 23:20:34', '粮库', '', 'depotuser');
INSERT INTO `xx_role` VALUES ('5', '2016-07-31 21:23:21', '2016-07-31 21:23:24', '虫调人员', '', 'insectsinvestuser');
INSERT INTO `xx_role` VALUES ('6', '2016-07-31 21:24:14', '2016-07-31 21:24:16', '专家', '', 'expert');
INSERT INTO `xx_role` VALUES ('7', '2016-07-31 21:25:32', '2016-07-31 21:25:36', '农户', '', 'farmeruser');
INSERT INTO `xx_role` VALUES ('8', '2016-08-03 14:45:03', '2016-08-03 14:45:06', '加工厂', '', 'factoryuser');

-- ----------------------------
-- Table structure for xx_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `xx_role_authority`;
CREATE TABLE `xx_role_authority` (
  `roles` bigint(20) NOT NULL,
  `ops` int(11) NOT NULL,
  PRIMARY KEY (`roles`,`ops`),
  KEY `FK_Reference_77` (`ops`),
  CONSTRAINT `FKE06165D939B03AB0` FOREIGN KEY (`roles`) REFERENCES `xx_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xx_role_authority
-- ----------------------------
INSERT INTO `xx_role_authority` VALUES ('1', '-1');
INSERT INTO `xx_role_authority` VALUES ('2', '-1');
INSERT INTO `xx_role_authority` VALUES ('4', '-1');
INSERT INTO `xx_role_authority` VALUES ('5', '-1');
INSERT INTO `xx_role_authority` VALUES ('6', '-1');
INSERT INTO `xx_role_authority` VALUES ('7', '-1');
INSERT INTO `xx_role_authority` VALUES ('8', '-1');
INSERT INTO `xx_role_authority` VALUES ('1', '1');
INSERT INTO `xx_role_authority` VALUES ('2', '1');
INSERT INTO `xx_role_authority` VALUES ('4', '1');
INSERT INTO `xx_role_authority` VALUES ('5', '1');
INSERT INTO `xx_role_authority` VALUES ('6', '1');
INSERT INTO `xx_role_authority` VALUES ('7', '1');
INSERT INTO `xx_role_authority` VALUES ('8', '1');
INSERT INTO `xx_role_authority` VALUES ('1', '900');
INSERT INTO `xx_role_authority` VALUES ('1', '901');
INSERT INTO `xx_role_authority` VALUES ('1', '902');
INSERT INTO `xx_role_authority` VALUES ('1', '903');
INSERT INTO `xx_role_authority` VALUES ('1', '904');
INSERT INTO `xx_role_authority` VALUES ('1', '905');
INSERT INTO `xx_role_authority` VALUES ('1', '906');
INSERT INTO `xx_role_authority` VALUES ('1', '907');
INSERT INTO `xx_role_authority` VALUES ('1', '908');
INSERT INTO `xx_role_authority` VALUES ('1', '909');
INSERT INTO `xx_role_authority` VALUES ('1', '910');
INSERT INTO `xx_role_authority` VALUES ('1', '911');
INSERT INTO `xx_role_authority` VALUES ('1', '912');
INSERT INTO `xx_role_authority` VALUES ('1', '913');
INSERT INTO `xx_role_authority` VALUES ('1', '914');
INSERT INTO `xx_role_authority` VALUES ('1', '915');
INSERT INTO `xx_role_authority` VALUES ('1', '916');
INSERT INTO `xx_role_authority` VALUES ('1', '917');
INSERT INTO `xx_role_authority` VALUES ('1', '920');
INSERT INTO `xx_role_authority` VALUES ('1', '921');
INSERT INTO `xx_role_authority` VALUES ('1', '10000');
INSERT INTO `xx_role_authority` VALUES ('2', '10000');
INSERT INTO `xx_role_authority` VALUES ('1', '10100');
INSERT INTO `xx_role_authority` VALUES ('2', '10100');
INSERT INTO `xx_role_authority` VALUES ('1', '10101');
INSERT INTO `xx_role_authority` VALUES ('2', '10101');
INSERT INTO `xx_role_authority` VALUES ('1', '10200');
INSERT INTO `xx_role_authority` VALUES ('1', '10201');
INSERT INTO `xx_role_authority` VALUES ('1', '10300');
INSERT INTO `xx_role_authority` VALUES ('1', '10301');
INSERT INTO `xx_role_authority` VALUES ('1', '10400');
INSERT INTO `xx_role_authority` VALUES ('1', '10401');
INSERT INTO `xx_role_authority` VALUES ('1', '10500');
INSERT INTO `xx_role_authority` VALUES ('1', '10501');
INSERT INTO `xx_role_authority` VALUES ('1', '10600');
INSERT INTO `xx_role_authority` VALUES ('1', '10601');
INSERT INTO `xx_role_authority` VALUES ('1', '10700');
INSERT INTO `xx_role_authority` VALUES ('1', '10701');
INSERT INTO `xx_role_authority` VALUES ('1', '10800');
INSERT INTO `xx_role_authority` VALUES ('1', '10810');
INSERT INTO `xx_role_authority` VALUES ('1', '10811');
INSERT INTO `xx_role_authority` VALUES ('1', '10900');
INSERT INTO `xx_role_authority` VALUES ('1', '10901');
INSERT INTO `xx_role_authority` VALUES ('1', '11000');
INSERT INTO `xx_role_authority` VALUES ('1', '11001');
INSERT INTO `xx_role_authority` VALUES ('1', '11100');
INSERT INTO `xx_role_authority` VALUES ('1', '11101');
INSERT INTO `xx_role_authority` VALUES ('1', '11200');
INSERT INTO `xx_role_authority` VALUES ('1', '40000');
INSERT INTO `xx_role_authority` VALUES ('1', '40100');
INSERT INTO `xx_role_authority` VALUES ('1', '40101');
INSERT INTO `xx_role_authority` VALUES ('1', '40200');
INSERT INTO `xx_role_authority` VALUES ('1', '40201');
INSERT INTO `xx_role_authority` VALUES ('1', '40300');
INSERT INTO `xx_role_authority` VALUES ('1', '50000');
INSERT INTO `xx_role_authority` VALUES ('1', '50100');
INSERT INTO `xx_role_authority` VALUES ('1', '50101');
INSERT INTO `xx_role_authority` VALUES ('1', '50200');
INSERT INTO `xx_role_authority` VALUES ('1', '50201');
INSERT INTO `xx_role_authority` VALUES ('1', '50300');
INSERT INTO `xx_role_authority` VALUES ('1', '50301');
INSERT INTO `xx_role_authority` VALUES ('1', '60000');
INSERT INTO `xx_role_authority` VALUES ('1', '60100');
INSERT INTO `xx_role_authority` VALUES ('1', '60101');
INSERT INTO `xx_role_authority` VALUES ('1', '60200');
INSERT INTO `xx_role_authority` VALUES ('1', '60201');
INSERT INTO `xx_role_authority` VALUES ('1', '60300');
INSERT INTO `xx_role_authority` VALUES ('1', '60301');
INSERT INTO `xx_role_authority` VALUES ('1', '60400');
INSERT INTO `xx_role_authority` VALUES ('5', '110000');
INSERT INTO `xx_role_authority` VALUES ('5', '110100');
INSERT INTO `xx_role_authority` VALUES ('5', '110200');
INSERT INTO `xx_role_authority` VALUES ('5', '110300');
INSERT INTO `xx_role_authority` VALUES ('5', '110400');
INSERT INTO `xx_role_authority` VALUES ('5', '110500');
INSERT INTO `xx_role_authority` VALUES ('5', '110600');
INSERT INTO `xx_role_authority` VALUES ('5', '110700');
INSERT INTO `xx_role_authority` VALUES ('5', '110800');
INSERT INTO `xx_role_authority` VALUES ('5', '120000');
INSERT INTO `xx_role_authority` VALUES ('5', '120100');
INSERT INTO `xx_role_authority` VALUES ('5', '120200');
INSERT INTO `xx_role_authority` VALUES ('5', '120300');
INSERT INTO `xx_role_authority` VALUES ('5', '130000');
INSERT INTO `xx_role_authority` VALUES ('4', '210000');
INSERT INTO `xx_role_authority` VALUES ('4', '210100');
INSERT INTO `xx_role_authority` VALUES ('4', '210200');
INSERT INTO `xx_role_authority` VALUES ('4', '220000');
INSERT INTO `xx_role_authority` VALUES ('4', '220100');
INSERT INTO `xx_role_authority` VALUES ('4', '220110');
INSERT INTO `xx_role_authority` VALUES ('4', '220200');
INSERT INTO `xx_role_authority` VALUES ('4', '220210');
INSERT INTO `xx_role_authority` VALUES ('4', '220300');
INSERT INTO `xx_role_authority` VALUES ('4', '220310');
INSERT INTO `xx_role_authority` VALUES ('4', '220400');
INSERT INTO `xx_role_authority` VALUES ('4', '220410');
INSERT INTO `xx_role_authority` VALUES ('4', '220500');
INSERT INTO `xx_role_authority` VALUES ('4', '220510');
INSERT INTO `xx_role_authority` VALUES ('4', '220600');
INSERT INTO `xx_role_authority` VALUES ('4', '220610');
INSERT INTO `xx_role_authority` VALUES ('4', '220700');
INSERT INTO `xx_role_authority` VALUES ('4', '220710');
INSERT INTO `xx_role_authority` VALUES ('4', '220800');
INSERT INTO `xx_role_authority` VALUES ('4', '220810');
INSERT INTO `xx_role_authority` VALUES ('4', '221000');
INSERT INTO `xx_role_authority` VALUES ('4', '230000');
INSERT INTO `xx_role_authority` VALUES ('4', '230100');
INSERT INTO `xx_role_authority` VALUES ('4', '230200');
INSERT INTO `xx_role_authority` VALUES ('4', '230300');
INSERT INTO `xx_role_authority` VALUES ('4', '230400');
INSERT INTO `xx_role_authority` VALUES ('4', '240000');
INSERT INTO `xx_role_authority` VALUES ('4', '240100');
INSERT INTO `xx_role_authority` VALUES ('4', '240200');
INSERT INTO `xx_role_authority` VALUES ('4', '250000');
INSERT INTO `xx_role_authority` VALUES ('6', '310000');
INSERT INTO `xx_role_authority` VALUES ('6', '310100');
INSERT INTO `xx_role_authority` VALUES ('6', '310200');
INSERT INTO `xx_role_authority` VALUES ('6', '320000');
INSERT INTO `xx_role_authority` VALUES ('6', '330000');
INSERT INTO `xx_role_authority` VALUES ('6', '330100');
INSERT INTO `xx_role_authority` VALUES ('6', '330200');
INSERT INTO `xx_role_authority` VALUES ('6', '330300');
INSERT INTO `xx_role_authority` VALUES ('6', '340000');
INSERT INTO `xx_role_authority` VALUES ('6', '340100');
INSERT INTO `xx_role_authority` VALUES ('6', '340200');
INSERT INTO `xx_role_authority` VALUES ('6', '350000');
INSERT INTO `xx_role_authority` VALUES ('7', '410000');
INSERT INTO `xx_role_authority` VALUES ('7', '420000');
INSERT INTO `xx_role_authority` VALUES ('8', '510000');
INSERT INTO `xx_role_authority` VALUES ('8', '520000');

-- ----------------------------
-- Table structure for xx_tag
-- ----------------------------
DROP TABLE IF EXISTS `xx_tag`;
CREATE TABLE `xx_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `orders` int(11) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xx_tag
-- ----------------------------
INSERT INTO `xx_tag` VALUES ('1', '2016-05-28 18:54:54', '2016-05-28 18:54:54', '100', 'http://localhost:8080/grainInsects/upload/image/default_large.jpg', null, '热点新闻', '0');
INSERT INTO `xx_tag` VALUES ('3', '2016-05-28 20:18:36', '2016-05-28 20:18:36', null, null, null, 'qqw', '0');
INSERT INTO `xx_tag` VALUES ('4', '2016-05-28 20:18:43', '2016-05-28 20:18:43', null, null, null, 'www', '0');
INSERT INTO `xx_tag` VALUES ('5', '2016-05-28 20:18:48', '2016-05-28 20:18:48', null, null, null, 'wweee', '0');
INSERT INTO `xx_tag` VALUES ('6', '2016-05-28 20:18:53', '2016-05-28 20:18:53', null, null, null, 'sd', '0');
INSERT INTO `xx_tag` VALUES ('7', '2016-05-28 20:18:58', '2016-05-28 20:18:58', null, null, null, 'fdsg', '0');
INSERT INTO `xx_tag` VALUES ('8', '2016-05-28 20:19:03', '2016-05-28 20:19:03', null, null, null, 'rrr', '0');
INSERT INTO `xx_tag` VALUES ('9', '2016-05-28 20:19:08', '2016-05-28 20:19:08', null, null, null, 'ggg', '0');
INSERT INTO `xx_tag` VALUES ('10', '2016-05-28 20:19:13', '2016-05-28 20:19:13', null, null, null, 'hhh', '0');
INSERT INTO `xx_tag` VALUES ('11', '2016-05-28 20:19:17', '2016-05-28 20:19:17', null, null, null, 'jjj', '0');
INSERT INTO `xx_tag` VALUES ('12', '2016-05-28 20:19:25', '2016-05-28 20:19:25', null, null, null, 'rrrtt', '0');

-- ----------------------------
-- View structure for v_user
-- ----------------------------
DROP VIEW IF EXISTS `v_user`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_user` AS select `a`.`username` AS `username`,'admin' AS `role` from `t_admin` `a` union select `b`.`username` AS `username`,'factoryuser' AS `role` from `t_factoryuser` `b` union select `c`.`username` AS `username`,'farmeruser' AS `role` from `t_farmeruser` `c` union select `d`.`username` AS `username`,'expert' AS `role` from `t_expert` `d` union select `e`.`username` AS `username`,'depotuser' AS `role` from `t_depotuser` `e` union select `f`.`username` AS `username`,'insectsinvestuser' AS `role` from `t_insectsinvestuser` `f` ;
