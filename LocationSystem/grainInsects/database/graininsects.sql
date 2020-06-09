/*
Navicat MySQL Data Transfer

Source Server         : aa64
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : graininsects

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2016-05-29 15:19:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_admin`
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
INSERT INTO `t_admin` VALUES ('aa', '1', 'c4ca4238a0b923820dcc509a6f75849b', 'szy', null, '北京邮电大学', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_admin` VALUES ('bb', '1', 'c4ca4238a0b923820dcc509a6f75849b', 'bb', 'bb', '年度', 'bb', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_admin` VALUES ('ccc', '2', 'c4ca4238a0b923820dcc509a6f75849b', 'ccc', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_admin` VALUES ('dd', '1', 'c4ca4238a0b923820dcc509a6f75849b', 'ee', 'dd', '宿舍', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_admin` VALUES ('ee', '1', 'c4ca4238a0b923820dcc509a6f75849b', '李四', '副教授', '湖南大学', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_admin` VALUES ('ff', '1', 'c4ca4238a0b923820dcc509a6f75849b', 'ff', '教授', '中山大学', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_admin` VALUES ('张三', '1', 'c4ca4238a0b923820dcc509a6f75849b', '张三', 'www', '武汉大学', '前期', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_admin` VALUES ('李四', '1', 'c4ca4238a0b923820dcc509a6f75849b', '李四', null, '普天工业园', null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `t_bintype_pic`
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
-- Table structure for `t_bin_antidrugkinds`
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
-- Table structure for `t_bin_antiphosphine`
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
-- Table structure for `t_bin_ca`
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
  PRIMARY KEY (`ID`),
  KEY `AK_Key_2` (`lcbm`,`Annual`),
  CONSTRAINT `FK_Reference_41` FOREIGN KEY (`lcbm`) REFERENCES `t_grainbin` (`lcbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bin_ca
-- ----------------------------

-- ----------------------------
-- Table structure for `t_bin_cleankill`
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
  PRIMARY KEY (`ID`),
  KEY `AK_Key_2` (`lcbm`,`Annual`),
  CONSTRAINT `FK_Reference_47` FOREIGN KEY (`lcbm`) REFERENCES `t_grainbin` (`lcbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bin_cleankill
-- ----------------------------

-- ----------------------------
-- Table structure for `t_bin_flyline`
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
-- Table structure for `t_bin_inertaerosol`
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
  PRIMARY KEY (`ID`),
  KEY `AK_Key_2` (`lcbm`,`Annual`),
  CONSTRAINT `FK_Reference_75` FOREIGN KEY (`lcbm`) REFERENCES `t_grainbin` (`lcbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bin_inertaerosol
-- ----------------------------

-- ----------------------------
-- Table structure for `t_bin_insects`
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
-- Table structure for `t_bin_invest`
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
  PRIMARY KEY (`sm`),
  KEY `AK_Key_2` (`lcbm`,`Annual`),
  CONSTRAINT `FK_Reference_39` FOREIGN KEY (`lcbm`) REFERENCES `t_grainbin` (`lcbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bin_invest
-- ----------------------------

-- ----------------------------
-- Table structure for `t_bin_phosphinefumigation`
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
  PRIMARY KEY (`ID`),
  KEY `AK_Key_2` (`lcbm`,`Annual`),
  CONSTRAINT `FK_Reference_44` FOREIGN KEY (`lcbm`) REFERENCES `t_grainbin` (`lcbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bin_phosphinefumigation
-- ----------------------------

-- ----------------------------
-- Table structure for `t_bin_preprotect`
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
-- Table structure for `t_bin_protect`
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
-- Table structure for `t_bin_protected`
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
  PRIMARY KEY (`ID`),
  KEY `AK_Key_2` (`lcbm`,`Annual`),
  CONSTRAINT `FK_Reference_45` FOREIGN KEY (`lcbm`) REFERENCES `t_grainbin` (`lcbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bin_protected
-- ----------------------------

-- ----------------------------
-- Table structure for `t_bin_use_drug`
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
-- Table structure for `t_catalog_index`
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
  PRIMARY KEY (`ID`),
  KEY `AK_Key_2` (`BM`),
  KEY `AK_Key_3` (`MC`),
  KEY `AK_Key_4` (`ywm`),
  KEY `FK_fk_flb_flb` (`PID`),
  CONSTRAINT `FK_fk_flb_flb` FOREIGN KEY (`PID`) REFERENCES `t_catalog_index` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_catalog_index
-- ----------------------------

-- ----------------------------
-- Table structure for `t_catalog_pic`
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
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_6` (`PID`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`PID`) REFERENCES `t_catalog_index` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_catalog_pic
-- ----------------------------

-- ----------------------------
-- Table structure for `t_depots_insects`
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
-- Table structure for `t_depotuser`
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
  PRIMARY KEY (`username`),
  KEY `FK_Reference_58` (`lkbm`),
  CONSTRAINT `FK_Reference_58` FOREIGN KEY (`lkbm`) REFERENCES `t_graindepot` (`lkbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_depotuser
-- ----------------------------
INSERT INTO `t_depotuser` VALUES ('aa', ' 100002', 'b59c67bf196a4758191e42f76670ceba', '张三', '高级工程师', '1360087889', '', '', null, null, '2016-05-19 09:32:40');
INSERT INTO `t_depotuser` VALUES ('bb', ' 100002', 'b59c67bf196a4758191e42f76670ceba', 'bbb', '', '', '', '', null, null, null);

-- ----------------------------
-- Table structure for `t_depotuser_grainbin`
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
-- Table structure for `t_depot_antidrugkinds`
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
-- Table structure for `t_depot_antiphosphine`
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
-- Table structure for `t_depot_ca`
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
  PRIMARY KEY (`ID`),
  KEY `AK_Key_2` (`lkbm`,`Annual`),
  CONSTRAINT `FK_fk_qtzb_lk` FOREIGN KEY (`lkbm`) REFERENCES `t_graindepot` (`lkbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_depot_ca
-- ----------------------------

-- ----------------------------
-- Table structure for `t_depot_cleankill`
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
  PRIMARY KEY (`ID`),
  KEY `AK_Key_2` (`lkbm`,`Annual`),
  CONSTRAINT `FK_Reference_69` FOREIGN KEY (`lkbm`) REFERENCES `t_graindepot` (`lkbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_depot_cleankill
-- ----------------------------

-- ----------------------------
-- Table structure for `t_depot_flyline`
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
-- Table structure for `t_depot_graintemperature`
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
-- Table structure for `t_depot_humidity`
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
-- Table structure for `t_depot_inertaerosol`
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
  PRIMARY KEY (`ID`),
  KEY `AK_Key_2` (`lkbm`,`Annual`),
  CONSTRAINT `FK_Reference_74` FOREIGN KEY (`lkbm`) REFERENCES `t_graindepot` (`lkbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_depot_inertaerosol
-- ----------------------------

-- ----------------------------
-- Table structure for `t_depot_invest`
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
-- Table structure for `t_depot_invest_tdepot_humidities`
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
-- Table structure for `t_depot_phosphinefumigation`
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
  PRIMARY KEY (`ID`),
  KEY `AK_Key_2` (`lkbm`,`Annual`),
  CONSTRAINT `FK_Reference_67` FOREIGN KEY (`lkbm`) REFERENCES `t_graindepot` (`lkbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_depot_phosphinefumigation
-- ----------------------------

-- ----------------------------
-- Table structure for `t_depot_preprotect`
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
-- Table structure for `t_depot_protect`
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
-- Table structure for `t_depot_protected`
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
  PRIMARY KEY (`ID`),
  KEY `AK_Key_2` (`lkbm`,`Annual`),
  CONSTRAINT `FK_Reference_68` FOREIGN KEY (`lkbm`) REFERENCES `t_graindepot` (`lkbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_depot_protected
-- ----------------------------

-- ----------------------------
-- Table structure for `t_depot_use_drug`
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
-- Table structure for `t_depo_envitemperature`
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
-- Table structure for `t_device_realdata`
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
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_13` (`lcbm`),
  KEY `FK_Reference_14` (`deviceno`),
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`lcbm`) REFERENCES `t_grainbin` (`lcbm`),
  CONSTRAINT `FK_Reference_14` FOREIGN KEY (`deviceno`) REFERENCES `t_device_register` (`deviceno`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_device_realdata
-- ----------------------------
INSERT INTO `t_device_realdata` VALUES ('1', '1000001', null, '15', '30', '20', '10', '甲虫', '成虫', '10', '1', '8', '10', '143560.98', '123400.789', '1000', '20160406', null, null, null);
INSERT INTO `t_device_realdata` VALUES ('2', '1000001', null, '15', '30', '20', '10', '甲虫', '成虫', '10', '1', '8', '10', '143560.98', '123400.789', '1000', '20160406', null, null, null);
INSERT INTO `t_device_realdata` VALUES ('3', '1000001', null, '15', '30', '20', '10', '甲虫', '成虫', '10', '1', '8', '10', '143560.98', '123400.789', '1000', '20160406', null, null, null);
INSERT INTO `t_device_realdata` VALUES ('4', '1000001', null, '15', '30', '20', '10', '甲虫', '成虫', '10', '1', '8', '10', '143560.98', '123400.789', '1000', '20160406', null, null, null);
INSERT INTO `t_device_realdata` VALUES ('5', '1000001', null, '15', '30', '20', '10', '甲虫', '成虫', '10', '1', '8', '10', '143560.98', '123400.789', '1000', '20160406', 'App', null, null);
INSERT INTO `t_device_realdata` VALUES ('6', '1000001', null, '15', '30', '20', '10', '甲虫', '成虫', '10', '1', '8', '10', '143560.98', '123400.789', '1000', '20160406', 'App', null, null);
INSERT INTO `t_device_realdata` VALUES ('7', '1000001', null, '15', '30', '20', '10', '甲虫', '成虫', '10', '1', '8', '10', '143560.98', '123400.789', '1000', '20160406', 'App', 'aa', '2016-05-13 15:24:12');
INSERT INTO `t_device_realdata` VALUES ('8', '1000001', null, '15', '30', '20', '10', '甲虫', '成虫', '10', '1', '8', '10', '143560.98', '123400.789', '1000', '20160406', 'App', 'aa', '2016-05-19 15:08:34');
INSERT INTO `t_device_realdata` VALUES ('9', '1000001', null, '15', '30', '20', '10', '甲虫', '成虫', '10', '1', '8', '10', '143560.98', '123400.789', '1000', '20160406', 'App', 'aa', '2016-05-19 15:15:20');
INSERT INTO `t_device_realdata` VALUES ('10', '1000001', null, '15', '30', '20', '10', '甲虫', '成虫', '10', '1', '8', '10', '143560.98', '123400.789', '1000', '20160406', 'App', 'aa', '2016-05-19 15:18:23');

-- ----------------------------
-- Table structure for `t_device_register`
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
-- Table structure for `t_diagnosisapplypic`
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
-- Table structure for `t_digital_feature`
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_digital_feature
-- ----------------------------

-- ----------------------------
-- Table structure for `t_digital_pic`
-- ----------------------------
DROP TABLE IF EXISTS `t_digital_pic`;
CREATE TABLE `t_digital_pic` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PID` bigint(20) DEFAULT NULL,
  `pic` varchar(255) NOT NULL,
  `source` varchar(100) DEFAULT NULL,
  `modifer` varchar(30) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_56` (`PID`),
  CONSTRAINT `FK_Reference_56` FOREIGN KEY (`PID`) REFERENCES `t_digital_feature` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_digital_pic
-- ----------------------------

-- ----------------------------
-- Table structure for `t_expert`
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
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_expert
-- ----------------------------

-- ----------------------------
-- Table structure for `t_factoryuser`
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
  PRIMARY KEY (`username`),
  KEY `FK_Reference_59` (`sm_factory`),
  CONSTRAINT `FK_Reference_59` FOREIGN KEY (`sm_factory`) REFERENCES `t_factory_info` (`sm_factory`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_factoryuser
-- ----------------------------

-- ----------------------------
-- Table structure for `t_factory_info`
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
-- Table structure for `t_factory_pic`
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
-- Table structure for `t_farmer`
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
-- Table structure for `t_farmeruser`
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
  PRIMARY KEY (`username`),
  KEY `FK_Fk_fameruser_direct` (`direct`),
  CONSTRAINT `FK_Fk_fameruser_direct` FOREIGN KEY (`direct`) REFERENCES `xx_area` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_farmeruser
-- ----------------------------

-- ----------------------------
-- Table structure for `t_field_pic`
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
-- Table structure for `t_grain`
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
-- Table structure for `t_grainbin`
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
  PRIMARY KEY (`lcbm`),
  KEY `FK_liangku_liangcang` (`lkbm`),
  CONSTRAINT `FK_liangku_liangcang` FOREIGN KEY (`lkbm`) REFERENCES `t_graindepot` (`lkbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_grainbin
-- ----------------------------
INSERT INTO `t_grainbin` VALUES (' 1111111', ' 100002', '平房仓', '5000', '钢架结构', '砖混结构', '1000', '100', '100', '100', '100', '', '100000', 'asdfaeee', '通风装置，制氮设备等<img src=\"http://localhost:8080/grainInsects/upload/image/201605/f1a662cd-8ee2-4f48-9140-786fc9c2cff9-thumbnail.jpg\" alt=\"\" /><img src=\"http://localhost:8080/grainInsects/upload/image/201605/6143af04-4c35-4f09-bde4-c94bb1936de3-thumbnail.jpg\" alt=\"\" /><br />', 'zhangsan', '', '', null, null);
INSERT INTO `t_grainbin` VALUES (' 123334', ' 100002', '圆筒仓', '1000', '砖混', '钢架', '1000', '100', '100', '100', '100', '无', '', '', 'adsfafdas<img src=\"http://localhost:8080/grainInsects/upload/image/201605/6143af04-4c35-4f09-bde4-c94bb1936de3-thumbnail.jpg\" alt=\"\" /><br />', '', '', '', null, null);
INSERT INTO `t_grainbin` VALUES (' 18902344', ' 100002', '平房仓', null, '', '', '1000', '100', '100', '100', '100', '', '', '', '平房仓，6000吨<br />', '', '', '', null, null);
INSERT INTO `t_grainbin` VALUES ('1000001', ' 100002', '圆筒仓', '1000', '钢结构', '钢结构', '1000', '100', '100', '100', '100', '', '', '', '呜呜呜呜，没有什么外围设备<img src=\"http://localhost:8080/grainInsects/upload/image/201605/5dbf3ffd-6eca-4a78-86b1-7b39ea347fa2-thumbnail.jpg\" alt=\"\" /><br />', '', '', '', null, null);

-- ----------------------------
-- Table structure for `t_graindepot`
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
  PRIMARY KEY (`lkbm`),
  KEY `FK_Reference_80` (`direct`),
  CONSTRAINT `FK_Reference_80` FOREIGN KEY (`direct`) REFERENCES `xx_area` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_graindepot
-- ----------------------------
INSERT INTO `t_graindepot` VALUES (' 100002', '1', ' 上地粮库', '10000', '10000', '10000', '10000', '0', '10000', '10000', '10000', '10000', null, null);
INSERT INTO `t_graindepot` VALUES (' 5650001', '30', ' 东莞粮库', '东莞市了山区26号', '10000', '10000', '10000', '0', '8', '56700', '10000', '10000', null, null);
INSERT INTO `t_graindepot` VALUES (' 700000', '31', ' 上海外滩库', '10000', '10000', '10000', '10000', '1', '10', '760000', '10000', '10000', null, null);
INSERT INTO `t_graindepot` VALUES ('10000', '1', '北京通州库', '北京市通州区果园', '116.404', '39.915', '200', '1', '18', '100876', '张三', '136011890', null, null);
INSERT INTO `t_graindepot` VALUES ('10056', '32', ' 昌平粮库', '北京市昌平区下关环岛', '10000', '10000', '10000', '0', '30', '10000', '10000', '10000', null, null);

-- ----------------------------
-- Table structure for `t_graindirection`
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
-- Table structure for `t_grain_pic`
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
-- Table structure for `t_info_report`
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_info_report
-- ----------------------------

-- ----------------------------
-- Table structure for `t_insectpic_indepot`
-- ----------------------------
DROP TABLE IF EXISTS `t_insectpic_indepot`;
CREATE TABLE `t_insectpic_indepot` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `sm_collection` varchar(30) DEFAULT NULL,
  `pic` varchar(255) NOT NULL,
  `collecttime` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_52` (`sm_collection`),
  CONSTRAINT `FK_Reference_52` FOREIGN KEY (`sm_collection`) REFERENCES `t_insects_inbin_on_depot` (`sm_collection`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_insectpic_indepot
-- ----------------------------

-- ----------------------------
-- Table structure for `t_insectscollectdistribution`
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
-- Table structure for `t_insectsdistribution`
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
-- Table structure for `t_insectsinvestuser`
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
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_insectsinvestuser
-- ----------------------------

-- ----------------------------
-- Table structure for `t_insects_collection_on_farmer`
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
-- Table structure for `t_insects_collect_pic`
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
-- Table structure for `t_insects_identify`
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
-- Table structure for `t_insects_inbin_on_depot`
-- ----------------------------
DROP TABLE IF EXISTS `t_insects_inbin_on_depot`;
CREATE TABLE `t_insects_inbin_on_depot` (
  `sm_collection` varchar(30) NOT NULL,
  `lcbm` varchar(60) DEFAULT NULL,
  `collector` varchar(30) DEFAULT NULL,
  `mobile` varchar(30) DEFAULT NULL,
  `company_collector` varchar(30) DEFAULT NULL,
  `date_collection` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`sm_collection`),
  KEY `FK_Reference_37` (`lcbm`),
  CONSTRAINT `FK_Reference_37` FOREIGN KEY (`lcbm`) REFERENCES `t_grainbin` (`lcbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_insects_inbin_on_depot
-- ----------------------------

-- ----------------------------
-- Table structure for `t_insects_index`
-- ----------------------------
DROP TABLE IF EXISTS `t_insects_index`;
CREATE TABLE `t_insects_index` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `jsbmc` varchar(100) NOT NULL,
  `modifer` varchar(30) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_insects_index
-- ----------------------------

-- ----------------------------
-- Table structure for `t_insects_infactory`
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
-- Table structure for `t_insects_info_on_farmer`
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
-- Table structure for `t_insects_ondepot`
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
-- Table structure for `t_insects_onfactory`
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
-- Table structure for `t_insects_specification`
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_insects_specification
-- ----------------------------

-- ----------------------------
-- Table structure for `t_insects_spec_pic`
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
-- Table structure for `t_insect_onfield`
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
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_81` (`direct`),
  CONSTRAINT `FK_Reference_81` FOREIGN KEY (`direct`) REFERENCES `xx_area` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_insect_onfield
-- ----------------------------

-- ----------------------------
-- Table structure for `t_miteidentify`
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
-- Table structure for `t_preventprocess`
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
  CONSTRAINT `FK_fk_fzgy_flb` FOREIGN KEY (`catalogid`) REFERENCES `t_catalog_index` (`ID`),
  CONSTRAINT `FK_Reference_26` FOREIGN KEY (`proctypeid`) REFERENCES `t_proctype` (`sm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='防治工艺的有效性如何进行描述比较合理';

-- ----------------------------
-- Records of t_preventprocess
-- ----------------------------

-- ----------------------------
-- Table structure for `t_preventprocessdetach`
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
-- Table structure for `t_proctype`
-- ----------------------------
DROP TABLE IF EXISTS `t_proctype`;
CREATE TABLE `t_proctype` (
  `sm` varchar(10) NOT NULL,
  `PID` varchar(10) DEFAULT NULL,
  `proctype` varchar(20) DEFAULT NULL COMMENT '综合防治\r\n            化学防治\r\n            物理防治\r\n            天敌昆虫防治\r\n            ',
  `source` varchar(100) DEFAULT NULL,
  `modifer` varchar(30) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  PRIMARY KEY (`sm`),
  KEY `FK_Reference_25` (`PID`),
  CONSTRAINT `FK_Reference_25` FOREIGN KEY (`PID`) REFERENCES `t_proctype` (`sm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_proctype
-- ----------------------------

-- ----------------------------
-- Table structure for `t_realdata_pic`
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
-- Table structure for `t_remotediagnosisapply`
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
-- Table structure for `t_storageprocess`
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
-- Table structure for `t_userrank`
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
-- Table structure for `xx_ad`
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
-- Table structure for `xx_ad_position`
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
-- Table structure for `xx_area`
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
-- Table structure for `xx_article`
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
-- Table structure for `xx_article_category`
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
-- Table structure for `xx_article_tag`
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
-- Table structure for `xx_attribute`
-- ----------------------------
DROP TABLE IF EXISTS `xx_attribute`;
CREATE TABLE `xx_attribute` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `orders` int(11) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `property_index` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xx_attribute
-- ----------------------------

-- ----------------------------
-- Table structure for `xx_attribute_option`
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
-- Table structure for `xx_friend_link`
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
-- Table structure for `xx_log`
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
-- Table structure for `xx_op_register`
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
  CONSTRAINT `FK_Reference_76` FOREIGN KEY (`pid`) REFERENCES `xx_op_register` (`id`)
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
INSERT INTO `xx_op_register` VALUES ('40000', null, '广告管理', '', 'bell', '1', null);
INSERT INTO `xx_op_register` VALUES ('40100', '40000', '广告位', 'admin/ad_position/list', 'bell_link', '1', null);
INSERT INTO `xx_op_register` VALUES ('40101', '40100', '广告位-操作', 'admin/ad_position', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('40200', '40000', '广告', 'admin/ad/list', 'bell_add', '1', null);
INSERT INTO `xx_op_register` VALUES ('40201', '40200', '广告-操作', 'admin/ad', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('50000', null, '文章管理', '', 'text_dropcaps', '1', null);
INSERT INTO `xx_op_register` VALUES ('50100', '50000', '类别管理', 'admin/article_category/list', 'text_list_numbers', '1', null);
INSERT INTO `xx_op_register` VALUES ('50101', '50100', '类别管理-操作', 'admin/article_category', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('50200', '50000', '标签管理', 'admin/tag/list', 'wand', '1', null);
INSERT INTO `xx_op_register` VALUES ('50201', '50200', '类别管理-操作', 'admin/tag', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('50300', '50000', '文章管理', 'admin/article/list', 'text_list_numbers', '1', null);
INSERT INTO `xx_op_register` VALUES ('50301', '50300', '文章管理-操作', 'admin/article', '', '0', null);

-- ----------------------------
-- Table structure for `xx_plugin_config`
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
-- Table structure for `xx_plugin_config_attribute`
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
-- Table structure for `xx_role`
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xx_role
-- ----------------------------
INSERT INTO `xx_role` VALUES ('1', '2016-04-06 10:35:56', '2016-04-06 11:50:13', '管理员aaaa', '', 'admin');
INSERT INTO `xx_role` VALUES ('2', '2016-04-06 11:49:45', '2016-04-06 11:49:45', 'bbb', '', 'bb');
INSERT INTO `xx_role` VALUES ('3', '2016-04-21 16:25:07', '2016-04-23 07:10:08', '管理员', '', '管理员');

-- ----------------------------
-- Table structure for `xx_role_authority`
-- ----------------------------
DROP TABLE IF EXISTS `xx_role_authority`;
CREATE TABLE `xx_role_authority` (
  `roles` bigint(20) NOT NULL,
  `ops` int(11) NOT NULL,
  PRIMARY KEY (`roles`,`ops`),
  KEY `FK_Reference_77` (`ops`),
  CONSTRAINT `FKE06165D939B03AB0` FOREIGN KEY (`roles`) REFERENCES `xx_role` (`id`),
  CONSTRAINT `FK_Reference_77` FOREIGN KEY (`ops`) REFERENCES `xx_op_register` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xx_role_authority
-- ----------------------------
INSERT INTO `xx_role_authority` VALUES ('1', '-1');
INSERT INTO `xx_role_authority` VALUES ('2', '-1');
INSERT INTO `xx_role_authority` VALUES ('1', '1');
INSERT INTO `xx_role_authority` VALUES ('2', '1');
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
INSERT INTO `xx_role_authority` VALUES ('1', '40000');
INSERT INTO `xx_role_authority` VALUES ('1', '40100');
INSERT INTO `xx_role_authority` VALUES ('1', '40101');
INSERT INTO `xx_role_authority` VALUES ('1', '40200');
INSERT INTO `xx_role_authority` VALUES ('1', '40201');
INSERT INTO `xx_role_authority` VALUES ('1', '50000');
INSERT INTO `xx_role_authority` VALUES ('1', '50100');
INSERT INTO `xx_role_authority` VALUES ('1', '50101');
INSERT INTO `xx_role_authority` VALUES ('1', '50200');
INSERT INTO `xx_role_authority` VALUES ('1', '50201');
INSERT INTO `xx_role_authority` VALUES ('1', '50300');
INSERT INTO `xx_role_authority` VALUES ('1', '50301');

-- ----------------------------
-- Table structure for `xx_tag`
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
