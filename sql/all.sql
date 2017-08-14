CREATE DATABASE  IF NOT EXISTS `bonus` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bonus`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: bonus
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `equity_balance`
--

DROP TABLE IF EXISTS `equity_balance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equity_balance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department` varchar(45) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `month` int(11) DEFAULT NULL,
  `equity` decimal(15,2) DEFAULT NULL,
  `pro_bonus` decimal(15,2) DEFAULT NULL,
  `expense` decimal(15,2) DEFAULT NULL,
  `dir_bonus` decimal(15,2) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `type` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equity_balance`
--

LOCK TABLES `equity_balance` WRITE;
/*!40000 ALTER TABLE `equity_balance` DISABLE KEYS */;
INSERT INTO `equity_balance` VALUES (1,'一所',2017,7,7332192.00,5021134.80,1264336.00,1046721.20,8,'0'),(2,'三所',2017,8,1045457.60,15456.55,27273.30,1002727.75,3,'0'),(3,'一所',2017,8,2992000.00,1969000.00,558000.00,465000.00,4,'0'),(4,'一所',2016,12,3488190.42,1978353.20,796427.99,713409.23,NULL,'1'),(5,'二所',2016,12,628677.98,724033.72,-337706.04,242350.30,NULL,'1'),(6,'三所',2016,12,528655.84,569114.16,-234934.85,194476.53,NULL,'1'),(7,'四所',2016,12,2368689.05,1234588.65,650374.67,483725.73,NULL,'1'),(8,'五所',2016,12,574022.52,403185.29,-199042.57,369879.80,NULL,'1');
/*!40000 ALTER TABLE `equity_balance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equity_detail`
--

DROP TABLE IF EXISTS `equity_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equity_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department` varchar(3) DEFAULT NULL COMMENT '设计所',
  `rec_date` date DEFAULT NULL COMMENT '登记时间',
  `rec_employee` varchar(45) DEFAULT NULL COMMENT '登记人',
  `check_date` date DEFAULT NULL COMMENT '审核时间',
  `check_employee` varchar(45) DEFAULT NULL COMMENT '审核人',
  `status` varchar(3) DEFAULT NULL COMMENT '状态：0-草稿；1-已审核；9-已删除',
  `type` varchar(3) DEFAULT NULL COMMENT '项目类型：1-运行卡  2-结算卡   3-其他入账    11-提取项目奖金   12-提取所长奖金  13-成本报账 14-冲预发  15-其他出账',
  `account_date` date DEFAULT NULL COMMENT '到账时间',
  `account_item` varchar(45) DEFAULT NULL COMMENT '项目名称',
  `cardno` varchar(45) DEFAULT NULL COMMENT '卡号',
  `income` decimal(15,2) DEFAULT NULL COMMENT '到账金额',
  `account_rate` decimal(8,4) DEFAULT NULL COMMENT '核算比例',
  `prize_rate` decimal(8,4) DEFAULT NULL COMMENT '奖励系数',
  `dir_count` int(11) DEFAULT NULL COMMENT '所长人数',
  `card_discount` decimal(8,4) DEFAULT NULL COMMENT '结算卡已核算系数',
  `pro_bonus_rate` decimal(8,4) DEFAULT NULL COMMENT '项目奖金比例',
  `pro_bonus_amount` decimal(15,2) DEFAULT NULL COMMENT '项目奖金金额',
  `dir_rate` decimal(8,4) DEFAULT NULL COMMENT '所长奖金比例',
  `dir_amount` decimal(15,2) DEFAULT NULL COMMENT '所长奖金金额',
  `expense_rate` decimal(8,4) DEFAULT NULL COMMENT '报账成本比例',
  `expense_amount` decimal(15,2) DEFAULT NULL COMMENT '报账成本金额',
  `equity` decimal(15,2) DEFAULT NULL COMMENT '所权益',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `pro_id` varchar(45) DEFAULT NULL,
  `check_remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='所权益明细表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equity_detail`
--

LOCK TABLES `equity_detail` WRITE;
/*!40000 ALTER TABLE `equity_detail` DISABLE KEYS */;
INSERT INTO `equity_detail` VALUES (2,'一所','2017-07-19','陈苹','2017-07-28','陈苹','1','1','2017-07-17','龙泉总体规划','67222',2000000.00,0.3200,1.2200,3,NULL,0.2100,512400.00,0.0500,122000.00,0.0600,120000.00,754400.00,'运行卡测试','ZG8901','审核通过'),(3,'四所','2017-07-19','陈苹','2017-08-02','陈苹','1','2','2017-07-01','慢行交通规划','782221',990000.00,0.3200,2.6400,3,2.2000,0.2100,91476.00,0.0500,21780.00,0.0600,0.00,113256.00,'结算卡测试入账',NULL,'undefined'),(4,'五所','2017-08-02','陈苹','2017-08-02','陈苹','1','12','2017-07-05','灯光专项规划',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0.00,NULL,87000.00,NULL,0.00,87000.00,'所长奖金补发',NULL,'undefined'),(5,'二所','2017-08-02','陈苹',NULL,NULL,'1','15','2017-03-14','老城有机更新规划',NULL,NULL,NULL,NULL,NULL,NULL,NULL,118000.00,NULL,8500.00,NULL,0.00,126500.00,'补发','ZX88-4',NULL),(6,'三所','2017-08-02','陈苹','2017-08-02','陈苹','1','11','2017-07-10','测试负数',NULL,NULL,NULL,NULL,NULL,NULL,NULL,-3899000.00,NULL,0.00,NULL,0.00,-3899000.00,'测试修改',NULL,'undefined'),(7,'三所','2017-08-02','陈苹',NULL,NULL,'1','12','2017-08-02','补发所长奖金',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0.00,NULL,980000.00,NULL,0.00,980000.00,'补发所长奖金','undefined',NULL),(8,'四所','2017-08-02','陈苹','2017-08-02','陈苹','1','15','2017-07-03','扣除所长奖金',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0.00,NULL,-99000.00,NULL,0.00,-99000.00,NULL,NULL,'undefined'),(9,'一所','2017-08-02','陈苹','2017-08-02','陈苹','1','1','2017-08-02','龙泉分区规划','XX902',8000000.00,0.3200,1.0000,3,NULL,0.2100,1680000.00,0.0500,400000.00,0.0600,480000.00,2560000.00,'龙泉项目补录',NULL,NULL),(10,'一所','2017-08-02','陈苹',NULL,NULL,'1','13','2017-07-10','打印成本',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0.00,NULL,0.00,NULL,-80000.00,-80000.00,NULL,NULL,NULL),(11,'五所','2017-08-02','陈苹','2017-08-02','陈苹','1','1','2017-08-02','运行卡项目','342222',800000.00,0.3200,1.0000,3,NULL,0.2100,168000.00,0.0500,40000.00,0.0600,48000.00,256000.00,NULL,NULL,NULL),(12,'五所','2017-08-01','陈苹','2017-08-01','陈苹','1','1','2017-08-21','绿道规划','yx554',2000000.00,0.3200,1.0000,2,NULL,0.2205,441000.00,0.0395,79000.00,0.0600,120000.00,640000.00,'完成时间提前5周',NULL,NULL),(13,'三所','2017-08-02','陈苹','2017-08-02','陈苹','1','1','2017-08-01','成都市总体城市设计','CD992',454555.00,0.3200,1.0000,3,NULL,0.2100,95456.55,0.0500,22727.75,0.0600,27273.30,145457.60,'补录完成',NULL,'项目名称修改'),(14,'一所','2017-08-01','陈苹','2017-08-02','陈苹','1','1','2017-07-28','龙泉分区规划-改','运行251',800000.00,0.3400,1.5000,2,NULL,0.2405,288600.00,0.0395,47400.00,0.0600,48000.00,384000.00,'正式阶段','undefined',NULL),(15,'一所','2017-08-09','陈苹','2017-08-09','陈苹','1','1','2017-07-28','大熊猫主题旅游创意规划','YX-22',9800000.00,0.3200,1.0000,2,NULL,0.2205,2160900.00,0.0395,387100.00,0.0600,588000.00,3136000.00,'无','X889','undefined'),(16,'一所','2017-08-09','陈苹','2017-08-09','陈苹','1','1','2017-07-15','公众参与调研','ss-34',5600.00,0.3200,1.0000,2,NULL,0.2205,1234.80,0.0395,221.20,0.0600,336.00,1792.00,'测试生成','ZX9882','undefined'),(17,'一所','2017-08-09','陈苹','2017-08-09','陈苹','1','1','2017-07-11','射洪县总规','XXd44',1000000.00,0.3200,1.0000,3,NULL,0.2100,210000.00,0.0500,50000.00,0.0600,60000.00,320000.00,'测试','SD-094','undefined'),(18,'一所','2017-08-09','陈苹','2017-08-09','陈苹','1','1','2017-07-08','测试','123',800000.00,0.3200,1.0000,3,NULL,0.2100,168000.00,0.0500,40000.00,0.0600,48000.00,256000.00,NULL,'二','undefined'),(19,'一所','2017-08-09','陈苹','2017-08-09','陈苹','1','1','2017-07-06','胜多负少','dffg',8000000.00,0.3200,1.0000,3,NULL,0.2100,1680000.00,0.0500,400000.00,0.0600,480000.00,2560000.00,'fh ','发发发','undefined'),(20,'三所','2017-08-09','陈苹','2017-08-09','陈苹','1','11','2017-08-09','sdfsd',NULL,NULL,NULL,NULL,NULL,NULL,NULL,-80000.00,NULL,0.00,NULL,0.00,-80000.00,'sdf',NULL,'undefined'),(21,'一所','2017-08-09','陈苹','2017-08-09','陈苹','1','1','2017-08-11','水电费','ddsf34',500000.00,0.3200,1.0000,3,NULL,0.2100,105000.00,0.0500,25000.00,0.0600,30000.00,160000.00,NULL,'sss','undefined'),(22,'一所','2017-08-09','陈苹','2017-08-09','陈苹','1','2','2017-08-04','asdsad','aaaa',800000.00,0.3200,1.0000,3,1.0000,0.2100,0.00,0.0500,0.00,0.0600,0.00,0.00,NULL,'asdfsa','undefined'),(23,'一所','2017-08-09','陈苹','2017-08-09','陈苹','1','1','2017-08-08','胜多负少','sdfds',800000.00,0.3400,1.0000,3,NULL,0.2300,184000.00,0.0500,40000.00,0.0600,48000.00,272000.00,NULL,NULL,NULL);
/*!40000 ALTER TABLE `equity_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(245) DEFAULT NULL,
  `nickname` varchar(45) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'chen','123','陈苹',1),(2,'liying','123','李颖',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-14 20:09:33
