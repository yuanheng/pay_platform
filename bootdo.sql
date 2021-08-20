-- MySQL dump 10.13  Distrib 5.7.24, for macos10.14 (x86_64)
--
-- Host: localhost    Database: bootdo
-- ------------------------------------------------------
-- Server version	5.7.24

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
-- Table structure for table `QRTZ_BLOB_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_BLOB_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_BLOB_TRIGGERS`
--

LOCK TABLES `QRTZ_BLOB_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_BLOB_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_BLOB_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_CALENDARS`
--

DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_CALENDARS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_CALENDARS`
--

LOCK TABLES `QRTZ_CALENDARS` WRITE;
/*!40000 ALTER TABLE `QRTZ_CALENDARS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_CALENDARS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_CRON_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_CRON_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_CRON_TRIGGERS`
--

LOCK TABLES `QRTZ_CRON_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_CRON_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_CRON_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_FIRED_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_FIRED_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_FIRED_TRIGGERS`
--

LOCK TABLES `QRTZ_FIRED_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_FIRED_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_FIRED_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_JOB_DETAILS`
--

DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_JOB_DETAILS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_JOB_DETAILS`
--

LOCK TABLES `QRTZ_JOB_DETAILS` WRITE;
/*!40000 ALTER TABLE `QRTZ_JOB_DETAILS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_JOB_DETAILS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_LOCKS`
--

DROP TABLE IF EXISTS `QRTZ_LOCKS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_LOCKS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_LOCKS`
--

LOCK TABLES `QRTZ_LOCKS` WRITE;
/*!40000 ALTER TABLE `QRTZ_LOCKS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_LOCKS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_PAUSED_TRIGGER_GRPS`
--

DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_PAUSED_TRIGGER_GRPS`
--

LOCK TABLES `QRTZ_PAUSED_TRIGGER_GRPS` WRITE;
/*!40000 ALTER TABLE `QRTZ_PAUSED_TRIGGER_GRPS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_PAUSED_TRIGGER_GRPS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_SCHEDULER_STATE`
--

DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_SCHEDULER_STATE` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_SCHEDULER_STATE`
--

LOCK TABLES `QRTZ_SCHEDULER_STATE` WRITE;
/*!40000 ALTER TABLE `QRTZ_SCHEDULER_STATE` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_SCHEDULER_STATE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_SIMPLE_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_SIMPLE_TRIGGERS`
--

LOCK TABLES `QRTZ_SIMPLE_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_SIMPLE_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_SIMPLE_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_SIMPROP_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_SIMPROP_TRIGGERS`
--

LOCK TABLES `QRTZ_SIMPROP_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_SIMPROP_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_SIMPROP_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_TRIGGERS`
--

LOCK TABLES `QRTZ_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_evt_log`
--

DROP TABLE IF EXISTS `act_evt_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_evt_log` (
  `LOG_NR_` bigint(20) NOT NULL AUTO_INCREMENT,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_STAMP_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DATA_` longblob,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp NULL DEFAULT NULL,
  `IS_PROCESSED_` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`LOG_NR_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_evt_log`
--

LOCK TABLES `act_evt_log` WRITE;
/*!40000 ALTER TABLE `act_evt_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_evt_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ge_bytearray`
--

DROP TABLE IF EXISTS `act_ge_bytearray`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ge_bytearray` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTES_` longblob,
  `GENERATED_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_BYTEARR_DEPL` (`DEPLOYMENT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ge_bytearray`
--

LOCK TABLES `act_ge_bytearray` WRITE;
/*!40000 ALTER TABLE `act_ge_bytearray` DISABLE KEYS */;
INSERT INTO `act_ge_bytearray` VALUES ('135065',1,'æµç¨‹å®¡æ‰¹æµ‹è¯•æµç¨‹.bpmn20.xml','135064',_binary '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.activiti.org/test\">\n  <process id=\"salary\" name=\"æµç¨‹å®¡æ‰¹æµ‹è¯•æµç¨‹\" isExecutable=\"true\">\n    <startEvent id=\"start\" name=\"å¯åŠ¨å®¡æ‰¹\" activiti:initiator=\"apply\" activiti:formKey=\"/act/salary/form\"></startEvent>\n    <endEvent id=\"end\" name=\"ç»“æŸå®¡æ‰¹\"></endEvent>\n    <userTask id=\"modify\" name=\"å‘˜å·¥è–ªé…¬æ¡£çº§ä¿®æ”¹\" activiti:assignee=\"${apply}\"></userTask>\n    <userTask id=\"audit\" name=\"è–ªé…¬ä¸»ç®¡åˆå®¡\" activiti:assignee=\"admin\"></userTask>\n    <exclusiveGateway id=\"sid-C28BB5F6-013D-4570-B432-61B380C1F46F\"></exclusiveGateway>\n    <userTask id=\"audit2\" name=\"é›†å›¢äººåŠ›èµ„æºéƒ¨éƒ¨é•¿å®¡æ ¸\" activiti:assignee=\"admin\"></userTask>\n    <exclusiveGateway id=\"sid-ED46FE41-A0FD-496D-86DC-2C97AF5735F0\"></exclusiveGateway>\n    <sequenceFlow id=\"sid-EF2F51BB-1D99-4F0B-ACF2-B6C1300A7D2B\" sourceRef=\"audit2\" targetRef=\"sid-ED46FE41-A0FD-496D-86DC-2C97AF5735F0\"></sequenceFlow>\n    <userTask id=\"audit3\" name=\"é›†å›¢äººåŠ›èµ„æºéƒ¨åˆ†ç®¡é¢†å¯¼å®¡æ ¸\" activiti:assignee=\"admin\"></userTask>\n    <exclusiveGateway id=\"sid-FE485B2D-9A23-4236-BD0D-D788CA6E30E4\"></exclusiveGateway>\n    <sequenceFlow id=\"sid-3DBCD661-5720-4480-8156-748BE0275FEF\" sourceRef=\"audit3\" targetRef=\"sid-FE485B2D-9A23-4236-BD0D-D788CA6E30E4\"></sequenceFlow>\n    <userTask id=\"audit4\" name=\"é›†å›¢æ€»ç»ç†å®¡æ‰¹\" activiti:assignee=\"admin\"></userTask>\n    <exclusiveGateway id=\"sid-3F53B6BD-F8F3-496B-AC08-50630BD11477\"></exclusiveGateway>\n    <userTask id=\"apply_end\" name=\"è–ªé…¬æ¡£çº§å…‘ç°\" activiti:assignee=\"admin\"></userTask>\n    <sequenceFlow id=\"sid-02DB2AD9-1332-4198-AC8D-22A35169D15C\" sourceRef=\"audit4\" targetRef=\"sid-3F53B6BD-F8F3-496B-AC08-50630BD11477\"></sequenceFlow>\n    <sequenceFlow id=\"sid-2AB7C01A-50EE-4AAC-8C8F-F6E1935B3DA7\" sourceRef=\"audit\" targetRef=\"sid-C28BB5F6-013D-4570-B432-61B380C1F46F\"></sequenceFlow>\n    <sequenceFlow id=\"sid-7D723190-1432-411D-A4A4-774225E54CD9\" name=\"æ˜¯\" sourceRef=\"sid-3F53B6BD-F8F3-496B-AC08-50630BD11477\" targetRef=\"apply_end\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${pass==1}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"sid-D44CAD43-0271-4920-A524-9B8533E52550\" name=\"æ˜¯\" sourceRef=\"sid-FE485B2D-9A23-4236-BD0D-D788CA6E30E4\" targetRef=\"audit4\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${pass==1}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"sid-53258502-43EE-4DE8-B1A4-DBD11922B8AF\" name=\"å¦\" sourceRef=\"sid-C28BB5F6-013D-4570-B432-61B380C1F46F\" targetRef=\"modify\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${pass==0}]]></conditionExpression>\n    </sequenceFlow>\n    <exclusiveGateway id=\"sid-5FED02D6-C388-48C6-870E-097DB2131EA0\"></exclusiveGateway>\n    <sequenceFlow id=\"sid-163DBC60-DBC9-438B-971A-67738FB7715A\" sourceRef=\"modify\" targetRef=\"sid-5FED02D6-C388-48C6-870E-097DB2131EA0\"></sequenceFlow>\n    <sequenceFlow id=\"sid-72258A41-203E-428C-B71D-CA3506252D73\" name=\"æ˜¯\" sourceRef=\"sid-C28BB5F6-013D-4570-B432-61B380C1F46F\" targetRef=\"audit2\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${pass==1}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"sid-8448EF4A-B62E-4899-ABC2-0E2DB2AE6838\" name=\"é‡æ–°ç”³è¯·\" sourceRef=\"sid-5FED02D6-C388-48C6-870E-097DB2131EA0\" targetRef=\"audit\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${pass==1}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"sid-A7589084-4623-4FEA-A774-00A70DDC1D20\" name=\"æ˜¯\" sourceRef=\"sid-ED46FE41-A0FD-496D-86DC-2C97AF5735F0\" targetRef=\"audit3\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${pass==1}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"sid-FA618636-3708-4D0C-8514-29A4BB8BC926\" name=\"å¦\" sourceRef=\"sid-ED46FE41-A0FD-496D-86DC-2C97AF5735F0\" targetRef=\"modify\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${pass==0}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"sid-1525BFF4-3E9D-4D8A-BF80-1F63AFE16289\" name=\"å¦\" sourceRef=\"sid-FE485B2D-9A23-4236-BD0D-D788CA6E30E4\" targetRef=\"modify\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${pass==0}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"sid-35CC8C6C-1067-4398-991C-CCF955115965\" name=\"å¦\" sourceRef=\"sid-3F53B6BD-F8F3-496B-AC08-50630BD11477\" targetRef=\"modify\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${pass==0}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"sid-BDB0AAB2-7E50-4D35-80EE-CE0BECDD9F57\" sourceRef=\"apply_end\" targetRef=\"end\"></sequenceFlow>\n    <sequenceFlow id=\"sid-44AFB9C1-4057-4C48-B1F2-1EC897A52CB7\" name=\"é”€æ¯\" sourceRef=\"sid-5FED02D6-C388-48C6-870E-097DB2131EA0\" targetRef=\"end\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${pass==0}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"sid-36E50C8B-6C7C-4968-B02D-EBAA425BF4BE\" sourceRef=\"start\" targetRef=\"audit\"></sequenceFlow>\n  </process>\n  <bpmndi:BPMNDiagram id=\"BPMNDiagram_salary\">\n    <bpmndi:BPMNPlane bpmnElement=\"salary\" id=\"BPMNPlane_salary\">\n      <bpmndi:BPMNShape bpmnElement=\"start\" id=\"BPMNShape_start\">\n        <omgdc:Bounds height=\"30.0\" width=\"30.0\" x=\"30.0\" y=\"240.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"end\" id=\"BPMNShape_end\">\n        <omgdc:Bounds height=\"28.0\" width=\"28.0\" x=\"975.0\" y=\"356.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"modify\" id=\"BPMNShape_modify\">\n        <omgdc:Bounds height=\"58.0\" width=\"102.0\" x=\"209.0\" y=\"135.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"audit\" id=\"BPMNShape_audit\">\n        <omgdc:Bounds height=\"57.0\" width=\"96.0\" x=\"105.0\" y=\"225.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"sid-C28BB5F6-013D-4570-B432-61B380C1F46F\" id=\"BPMNShape_sid-C28BB5F6-013D-4570-B432-61B380C1F46F\">\n        <omgdc:Bounds height=\"40.0\" width=\"40.0\" x=\"240.0\" y=\"240.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"audit2\" id=\"BPMNShape_audit2\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"210.0\" y=\"330.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"sid-ED46FE41-A0FD-496D-86DC-2C97AF5735F0\" id=\"BPMNShape_sid-ED46FE41-A0FD-496D-86DC-2C97AF5735F0\">\n        <omgdc:Bounds height=\"40.0\" width=\"40.0\" x=\"345.0\" y=\"350.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"audit3\" id=\"BPMNShape_audit3\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"420.0\" y=\"330.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"sid-FE485B2D-9A23-4236-BD0D-D788CA6E30E4\" id=\"BPMNShape_sid-FE485B2D-9A23-4236-BD0D-D788CA6E30E4\">\n        <omgdc:Bounds height=\"40.0\" width=\"40.0\" x=\"555.0\" y=\"350.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"audit4\" id=\"BPMNShape_audit4\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"630.0\" y=\"330.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"sid-3F53B6BD-F8F3-496B-AC08-50630BD11477\" id=\"BPMNShape_sid-3F53B6BD-F8F3-496B-AC08-50630BD11477\">\n        <omgdc:Bounds height=\"40.0\" width=\"40.0\" x=\"765.0\" y=\"350.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"apply_end\" id=\"BPMNShape_apply_end\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"840.0\" y=\"330.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"sid-5FED02D6-C388-48C6-870E-097DB2131EA0\" id=\"BPMNShape_sid-5FED02D6-C388-48C6-870E-097DB2131EA0\">\n        <omgdc:Bounds height=\"40.0\" width=\"40.0\" x=\"240.0\" y=\"45.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-02DB2AD9-1332-4198-AC8D-22A35169D15C\" id=\"BPMNEdge_sid-02DB2AD9-1332-4198-AC8D-22A35169D15C\">\n        <omgdi:waypoint x=\"730.0\" y=\"370.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"765.0\" y=\"370.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-8448EF4A-B62E-4899-ABC2-0E2DB2AE6838\" id=\"BPMNEdge_sid-8448EF4A-B62E-4899-ABC2-0E2DB2AE6838\">\n        <omgdi:waypoint x=\"240.0\" y=\"65.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"153.0\" y=\"65.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"153.0\" y=\"225.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-36E50C8B-6C7C-4968-B02D-EBAA425BF4BE\" id=\"BPMNEdge_sid-36E50C8B-6C7C-4968-B02D-EBAA425BF4BE\">\n        <omgdi:waypoint x=\"59.9965176371898\" y=\"255.32320080051775\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"91.4000015258789\" y=\"256.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"105.0\" y=\"255.44805199630667\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-7D723190-1432-411D-A4A4-774225E54CD9\" id=\"BPMNEdge_sid-7D723190-1432-411D-A4A4-774225E54CD9\">\n        <omgdi:waypoint x=\"805.0\" y=\"370.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"840.0\" y=\"370.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-53258502-43EE-4DE8-B1A4-DBD11922B8AF\" id=\"BPMNEdge_sid-53258502-43EE-4DE8-B1A4-DBD11922B8AF\">\n        <omgdi:waypoint x=\"260.0\" y=\"240.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"260.0\" y=\"193.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-A7589084-4623-4FEA-A774-00A70DDC1D20\" id=\"BPMNEdge_sid-A7589084-4623-4FEA-A774-00A70DDC1D20\">\n        <omgdi:waypoint x=\"385.0\" y=\"370.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"420.0\" y=\"370.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-35CC8C6C-1067-4398-991C-CCF955115965\" id=\"BPMNEdge_sid-35CC8C6C-1067-4398-991C-CCF955115965\">\n        <omgdi:waypoint x=\"785.0\" y=\"350.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"785.0\" y=\"164.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"311.0\" y=\"164.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-2AB7C01A-50EE-4AAC-8C8F-F6E1935B3DA7\" id=\"BPMNEdge_sid-2AB7C01A-50EE-4AAC-8C8F-F6E1935B3DA7\">\n        <omgdi:waypoint x=\"201.0\" y=\"256.4158878504673\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"241.14537444933922\" y=\"258.8546255506608\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-1525BFF4-3E9D-4D8A-BF80-1F63AFE16289\" id=\"BPMNEdge_sid-1525BFF4-3E9D-4D8A-BF80-1F63AFE16289\">\n        <omgdi:waypoint x=\"575.0\" y=\"350.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"575.0\" y=\"164.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"311.0\" y=\"164.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-3DBCD661-5720-4480-8156-748BE0275FEF\" id=\"BPMNEdge_sid-3DBCD661-5720-4480-8156-748BE0275FEF\">\n        <omgdi:waypoint x=\"520.0\" y=\"370.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"555.0\" y=\"370.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-163DBC60-DBC9-438B-971A-67738FB7715A\" id=\"BPMNEdge_sid-163DBC60-DBC9-438B-971A-67738FB7715A\">\n        <omgdi:waypoint x=\"260.0\" y=\"135.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"260.0\" y=\"85.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-44AFB9C1-4057-4C48-B1F2-1EC897A52CB7\" id=\"BPMNEdge_sid-44AFB9C1-4057-4C48-B1F2-1EC897A52CB7\">\n        <omgdi:waypoint x=\"280.0\" y=\"65.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"989.0\" y=\"65.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"989.0\" y=\"356.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-BDB0AAB2-7E50-4D35-80EE-CE0BECDD9F57\" id=\"BPMNEdge_sid-BDB0AAB2-7E50-4D35-80EE-CE0BECDD9F57\">\n        <omgdi:waypoint x=\"940.0\" y=\"370.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"975.0\" y=\"370.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-72258A41-203E-428C-B71D-CA3506252D73\" id=\"BPMNEdge_sid-72258A41-203E-428C-B71D-CA3506252D73\">\n        <omgdi:waypoint x=\"260.0\" y=\"280.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"260.0\" y=\"330.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-FA618636-3708-4D0C-8514-29A4BB8BC926\" id=\"BPMNEdge_sid-FA618636-3708-4D0C-8514-29A4BB8BC926\">\n        <omgdi:waypoint x=\"365.0\" y=\"350.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"365.0\" y=\"164.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"311.0\" y=\"164.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-D44CAD43-0271-4920-A524-9B8533E52550\" id=\"BPMNEdge_sid-D44CAD43-0271-4920-A524-9B8533E52550\">\n        <omgdi:waypoint x=\"595.0\" y=\"370.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"630.0\" y=\"370.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-EF2F51BB-1D99-4F0B-ACF2-B6C1300A7D2B\" id=\"BPMNEdge_sid-EF2F51BB-1D99-4F0B-ACF2-B6C1300A7D2B\">\n        <omgdi:waypoint x=\"310.0\" y=\"370.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"345.0\" y=\"370.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n    </bpmndi:BPMNPlane>\n  </bpmndi:BPMNDiagram>\n</definitions>',0),('135066',1,'æµç¨‹å®¡æ‰¹æµ‹è¯•æµç¨‹.salary.png','135064',_binary '‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0õ\0\0¤\0\0\0pö‚\0\0B•IDATx\Ú\í\İõğ9Ç¹&rˆ\ÍB\È¡…	‡K¡pA>À<\Ö+\ÉB%Sˆ\ã€\ã0¶1s–9ÁaÊ˜`Œ	GJ\Ç)ŠlVzXA˜Â²x*€9±¤#2·Fv§“ùoZ½3;³O\Í\ì~>Uÿ\Úİ™\Ù\ÙG÷÷\ßı\ëÿ¿»+\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0`¤>\â_\0\0\0\0gZŞ²\ÚG\0\0\0 Cœ–·my[Pû8İ¿\0\0\0:§ O…|Œ\ÔoU\Ø\0\0@gô\Í\0\0\0\ÚÀ´&…{z\Ş9ö\0\0\0Ğ†}³‚İˆ=\0\0\0´‘\Ó*C\ê\ë\0\0€1,\è§\ãûúö\0\0\0\ĞYıh}?\0\0\00\Í.Š7\Ş\ï\0\0\0¡­©óF\ì\0\0`”/r÷;y»°öq(\Ê\ß\ç\ây\0\0\00ıôBaşı¼eµ­ö¾Oa\0\0\0\ãPĞ‡k…y6„Â¾XĞ§6»ğ¼s\ì\0\0`5*´\ë\èƒöõ^O\×;\Ç\0\0\0F± Ÿ6„B½^a\ßjA_.\ìM\Å\0\0€ahµ°nV\Øµ /şü­#ö\0\0\00¬‚¾Õ‚ºQa¿\Ï0ú\Ä9ö\0\0\00†ı`…ıK#(\èGúû\0\0\0À¤2Ò‘ñz…ıH\núra\ï{\0\0€Nö\Ù\Ï~6\ÓÆ®\åÿ\â#\\D1\å¾<BÿR\íñ‘X`ùhš¦iš¦iZuŸ:»¨÷_3Í®v\ß\Ì`#õ­\ÜÇ¾#õ\0\0 \ÂJL‹t_elÎ©Na\ïœz\0\0Pa%f…t«#\ãn[W\ï\ê÷C)\ì]ı\0\0\ÔCX‰Aaß¬ nvúf÷±odJ\Å}\ê\0@=„•˜ak6RŞ¬ ¯³°O§\0œm\0\0€z+1\Ã\×hÄ¾Õ‚~¨…}úy\nz\0\0Pa%fÄˆ}y*ü…•¡ß‡¾^a?»ôsœC\0\0\ê!¬ÄŒAa_¼x^±@o¥ ¯4ù>·­\0\0õVb\ÆPy*~\ä³+C¿M]ùû¦)\è\0@=„•˜ñ/\ìGª\Ş\Ô~\0\0@=„•˜6/\ìGû\0\0\0¨‡ÀJLF:eŞ”{\0\0Pa%¦\r\nû¡´»(\0\0¨‡°\Ó†:…Ş”{\0\0Pa%¦û)y\ë\Ë\Û\Ùşe\0\0 \ÂJLû˜V+Ø§\ròüV=\0\0¨‡°Ó\ZØ›r\0\0\ê!°w`a¯ \0\0õX‰;°°_  \0\0õX‰;\Ì\ïÿş\ï_ˆe\å¶u\0\0 +q‡-§EŸù\Ìg²©S§\î\ë¿\0\0\ê!°wˆ®®®O\ä\Ëiw,«\î\în#õ\0\0 +qõc9\Õ\Ú:ÿ\0\0P•¸3–Ï”4JŸZ^\ä»P\0\0¨‡ÀJ\Ü\ËgQ± O£õÎ­\0\0õX‰\Û{\Ù¥O­»»ûlÿ!\0\0Pa%¶·\ï²YR¯ ¯µµşC\0\0 \ÂJl%n\Ï\å\Òp”Ş¹õ\0\0 +q{/—Eƒô®„\0\0\ê!°[V\0\0`¬\ÄXV\0\0`¬\ÄXV\0\0`¬Ä–\0\0`+1–\0\0\Ø\Ç+1–\0\0\Ø\Ç+±e\0\0\Ø\Ç\ÆJŒe\0\0ö±ÁJŒe\0\0ö±ÁJŒe\0\0ö±ÁJlY\0\0ö±±cY\0€}l°cY\0€}l°[V\0\0€}l¬\ÄXV\0\0`¬\ÄXV\0\0`¬\ÄXV\0\0`¬Ä–\0\0`+1–\0\0\Ø\Ç+1–\0\0\Ø\Ç+±e\0\0\Ø\Ç\ÆJŒe\0\0ö±ÁJŒe\0\0ö±ÁJlY\0\0ö±ÁJlY\0€}lû\ØX‰±¬\0\0À>6X‰±¬\0\0À>6X‰-+\0\0À>6Vb,+\0\0°\rVb,+\0\0°\rVb\Ë\n\0\0°•\ØJlY\0€}l°cY\0€}l°cY\0€}l°[V\0\0€}l¬\ÄXV\0\0`¬\ÄXV\0\0`¬Ä–\0\0`+1–\0\0\Ø\Ç+1–\0\0\Ø\Ç+ñeY¶ï«¯¾úÈ“O>ù›eË–e===\ÛbYuò\ï¿t\é\ÒlÕªUŸ·Y’\0€z¬\ÄMEA¿zõ\êlË–-Ù®]»´½Ü¶nİš­\\¹òİ¼À?Oú\0\0P•xP1B¯ o»\Â~WOO\Ï\Ò\0€z¬ÄƒŠ)÷\n\éökyQ¿[ú\0\0P•xPq.·\"º-‹z\'\0\0\ê!°NQÿ\ë_½™½º\î\ÙsK¿Vmñy<¦\0W\Ô\0 +qõ}\Ûz³\r?¾>ûù\âköhñX<§W\Ô\0 +q›õ›û\Û}jo<·H®¨\0@=V\âv-\ê_x\â›\r‹úxN®¨\0@=V\â6-\ê7ô\ÜØ°¨\ç\áŠz\0\0\ÔC`%V\ÔkŠz\0\0\ÔC`%Í¢>®vß¨¨\ç\áŠz\0\0\ÔC`%nÓ¢~\ãš\ï4,\ê\ã9E¸¢\0\0õX‰Û´¨§w}¶\áñN½\Ï‹\ç\áŠz\0\0\ÔC`%nÓ¢>\ÚkO\ß? ¨\Ç\àŠz\0\0\ÔC`%n\ç¢~\ç\Îl\ãO¿;p\ê}şX<§W\Ô\0 +qõ}\Ûz³—\×\Ü\Ñğœúx.^£W\Ô\0 +q»õ;wfon\\ı\âG_nXĞ§¯‰\×\ZµW\Ô\0 +ñ^.ê›\ÎµW\Ô\0 +q›õ­Œ\Î6j¯(W\Ô\0 +ñ^*\ê‡[Ğ§¦(W\Ô\0 +ñ^*\ê5E=\0\0(\ê±+\ê5E=\0\0\ê!°+\êõ\0\0 +±¢~õ\İ\İ\İÿ%_7wH(\0\0\ê!P\ÔkR\Ô×Šù±^\ê`\0P¢^\İE}¹˜W\Ô\0 +±¢¾Í‹ú|ıûó˜f_.\æõ\0\0¨‡ÀJ¬¨o\Óv\Ë-·dõF\æõ\0\0¨‡ÀJ¬¨oóÖ¬˜\×4M\Ó4md\Í^.¨‡°Oø¢>{\ì±Ç²Ù³gW[|Kö\ÄO»Xİ±cGv\â‰\'V[z¿s\Ï=·ÿó|0{\àªŸoŞ¼9Û¸qcv\æ™gV[úö\ßÿl\åÊ•Ù²eË²mÛ¶U\ß7^¿dÉ’şŸ__{\íµ{ü\ìx\ìö\Ûo¯~ş\Æo\ìñ\ÜÚµk«\ÏoÚ´)›:ujµÿ\æ\×^{m\×oØ°¡úxü~ñuü\ìıö\ÛoHÿ‹øş;\ï¼s\Ğ\é÷\\pÁ\Í\×Á÷\'\Ê‰\È\È\ÈX‰Ç©¨¢ó‘Gğ\ØÂ…÷x\ì™g\É\Î9\çœjK\0\Ê®¸\âŠş×¿÷\Ş{\Õ\ï\ÏõõõUø\á‡û_³u\ë\Öş\×Æ€x\İ!‡\Òÿü	\'œ\ĞÿŞ¯¼òJõ\ã‹/¾¸\Ç\ïy\Ï=÷ô\Åw||şù\ç³E‹U?/ÿ]w\ÜqGõóø}\Òû¾ó\Î;\ÕÏ‹¿[½ƒñõ±\Ç[ı\âVü¿\Æ\ïš~\ßc9¦ús~ú\é=\n´p¡¼\Ö+\î\å	l—\09yE}Ã¢ş\ä“OP ¯Y³fÀ\è{ñ\ë#<²Z\Ä6z\ß\Ç¼ÿ½\î½÷\Ş\ê\Ç9?şø\ã«÷GQ)O¯¿\ï¾ûª¯‰–f\Ä\ë\Ò{\Ä€ô{Åˆzzü®»\îÊ:\ê¨\ê(úu\×]·\Çkcd>½ÿ\êÕ«³Å‹÷ÿÍ©\à÷ŠW_}õ€¿7\ï‰\Z1c f\Ä\ÏH\ï\Ã\r7\Ôı\Û?øÁV_\ã¿kñ\àD³[Ú•G\î\å	l—\09yEı€…gšs\ç\Îİ£¨?ø\àƒ³£>z\Ği\åÍŠú}¯FØ£hiô•\ÚHz½ƒ•\Ú\Ôù9\éùQD§ñ=•\Ú\Ôù\â÷D\áEvü\Şw\ß}wõ±SO=µ¿ˆ\×\Å÷\Ä\ç÷\ßõo»êª«ú¿?N3¨Ô¦\ÈÇ†ô\Ş\é½.»\ì²jaıÕ¯~µúx¼.x(şşQü‹÷4C!¦ğ7šÑ¨¨O\ÒÈ½<\í G / ¨¯[HG!ŸGñ],¶\ë¡­õQ”§\â6\Ş\ã€¨¦\Çhy<v\ÑE\r8w?>¿õ\Ö[«\Ó\ï\ãû\Ó\È}pˆ\Çw\\õ<ûòAƒJm@y&Az\Ïôyüm1ÊŸK#ñq`#Šş\Ã;,ûÔ§>U}î­·ŞªNÅ¿1Šú8¸¯\r§Ÿ~zõoŠ\ïY¯¿şzÿÏŠk¤sù£ÈŸW|¾|  YQ/O`»\È\ÈX‰-\ê\çÌ™Sı<F±£ø¢>³^¾P\\:w=Š\æòû§¢8~ùó\çgx`õ\Üô\Ûn»­D¾R©O#\é©Pƒõi\Æ@\Ìˆ‹ó­_¿¾ÿÔƒô\Ú(\Ú\ãóx¾xmSN9¥zÀ!$\ÄL‚¸X^ùi>½¶xN~ñ:q€ø\Óøõ€¼€•xÔŠú4RŸ§i\é£Q\Ô\Ç\Èx\ZQ÷«\Ùt\ĞAu\ß?¾>é¤“ª\ß\Åq\ØqQº\é®\Î}\">Š\å(®\ã\ë(\Î\ã\ë\âü‹\×(\ê\ÇÏŒ¢=\nó4õ>‹	õş?qEş¸rœƒ§$Ä‰8?‹\ë\Ô+\ê\ÓEø\Ò\ïSşË£ôŠz@@@^ÀJ<\â¢ş\ÑG\íA\ÓE\Z…ôSO=5¢s\êc4>\Ö1\ÊSÒ‹…|½¢>¾\éœúJ\íªù•\Â9õQH\ÏSOW¿o6ı>½&Fÿc\Ä<‹¿3}®Š_¯¨OgPˆó\æÍ«¨f!¤ƒ‡zhu\Ê~zô?ˆişŠz@@@^ÀJ<jE}´4\Ó\Ü\ÓE\åfÍšµ\Çğ£€‹\ÏEK£\à1\â£\ï\é\âpi:|úY•\Â-\æbúy,ˆ‚·\ÑL€Ji¤>\ÎW?\ãŒ3¼WñV{•Ú”ö˜®_o¤>M½«Ñ§¿7\Z¤\è¥ó÷\ã¢|\å¢>ı=q½x}ü·\ÜrKö¡}¨ÿz\0Å»¿?~F¼wº\Ò~ùÀ¢##°¹¨\ë9¢:.÷\î»\ï\Ö¥¾ò\Ê+\Ş\Òn°–\Ş/F\Õ+…[Ì•/\ÄWü½*…\Ñ\ì4]>Š\áò\ïT<w>¾ƒ	ñúF3Š\ç\Ô\ÇÇ¸\Ø^üñ³‹3\rR\á]ü™•\Ú9üq0#\Î\é?ë¬³ª\îkö¿ˆSbª~ñwM\ÒÈ¾¢##°«¨\×Ú£)\ê99y+±¢^Q/O G€¼`%V\ÔkŠzy99y+±¢^S\ÔrròVbE½¢^Àv	#P\ÔkŠzy99\ä+±¢^S\Ô\Û(€¼€•XQ¯¨—\'@@@^ÀJ¬¨W\Ô\Ë\È G /X‰õš¢^@@@^ÀJ¬¨\×õ€¼€•XQ¯¨—\'°]\ä\äõš¢^@@\0yÁJ¬¨\×õ6\n G G /`%V\Ô+\ê\å	##°—,[¶L\İ~mG^\Ô\ï–\'@@@^ÀJ<¨U«V½¹e\Ë…tµ\Ş\ŞŞ¿Î‹ú\ä	##°jùò\åg­X±\âWo¿ıövõ\Ş¡‚~\éÒ¥¯\ç\í<y\ä\ä\ä¬\ÄM\å\äôu1\å;\Î\å\î\Äö\ĞCe±¬:õ÷¯µøÿ¿0Qz##°\Óh9-Še5u\ê\Ô}ı7\ä	\ä#°wˆ®®®O\ä\Ëiw,«\î\î\îiş#òr\È\ÈX‰;§¨_Ë©\Ö\Öù\È\È G /`%\îŒ\å3%Ò§–ù\Óıg\ä	\ä#°·ÿòYT,\è\Óh½s\ë\å	\ä#°·÷²0JŸZww÷\ÙşCòr\È\ÈVb+qû.›%õ\núZ[\ë?$O G€¼`%¶·\çri8J\ï\Üzy9\ä\ä¬\Ä\í½\\\rVĞ»¾<ròVb\Ë\n\Ë\ä\ä\ä¬\ÄXVXF G G /`%Æ²²Œ@\09y+±e…err\ÈVb,+,###°cYYF€¼€•Ø²\Â29\ä\ä+1––\È\È\ÈX‰±¬,#@@@^ÀJŒeeròVb\Ë\n\Ë\ä\ä¬\ÄXVXF G G /°7V\ØE±\Ò6i\ëü§t8XF G G /\Ğ~+ì”¼\í¬¨\ï\ê\êš\î?¥\ÃÁ299yö\\i—RÔ¯:u\ê¾şK:,###hÏ•vJ£¢¾»»ûlÿ!–\È\È\È´÷Š[\ï\ÜúuF\éu8XF G G /\Ğş+\î€s\ëK¯\ÃÁ299y‘ñ\Ò\ëp°Œ@@@^ 3‹úOÎ¥Ÿ\æ?¢\ÃÁ299y\ÎZ«\ç\Ö¥\×\á`¼@\ç­ÀS¬\Ä:,###&­,\Ëö}õ\ÕWyò\É\'³lÙ²¬§§§£Z¬Äö;/]º4[µj\Õ\ß\çm–\Ë\ä#†-\núÕ«Wg[¶l\Év\íÚ¥SÛºuk¶r\å\Êwóÿ<–\È G /KŒ\Ğ+\è÷Za¿«§§\ç–\È G /KL¹W`ï½–õ»u8XF G€¼0,q·\âz¯õ™\Ë\ä#Æ´¨ÿõ¯\Ş\Ì^]÷ƒì¹¥_«¶ø<S˜+\êu8– G G /´qQß·­7\Ûğ\ãë³Ÿ/¾f\ÅsŠsE½\Ç2\ä\ä\ä…6-\ê7=÷·\nú\Ô\Şxn‘\â\\Q¯Ã±Œ\099y¡]‹úøfÃ¢>Sœ+\êu8– G G /´iQ¿¡\çÆ†E}<§8W\Ô\ëp,#@@@^P\Ô+\êu8XF G Grò\ÂhõqµûFE}<§8W\Ô\ëp,#@@@^hÓ¢~\ãš\ï4,\ê\ã9Å¹¢^‡crròB›õ\ïô®\Ï6<~\ÃÀ©÷ùcñœ\â\\Q¯Ã±Œ\099y¡M‹úh¯=}ÿ€¢>S˜+\êu8– G G /\ã.Ë²\ß[¿~ı¼şğ‡+\çÏŸÿ\îW\\±{Îœ9YüSfÍšõ\Û?ş\ã?\Şu\İu×½ú\Ío~ó¼ı\ëI]\Ô\ïÜ™mü\éwN½\Ï‹\ç\çŠúL«u8\Ót½6\n G€¼Œ¨˜ô\ÑG\ï¿é¦›v_y\å•\Ù÷¾÷½\ì\É\'Ÿ\Ìz{{³;vd!>nÙ²%û\Ù\Ï~–\åEö§ú§¿ı³?û³_^|ñ\ÅM¶¢¾o[oöòš;\ZS\Ï\Åk\èŠúAœ–·my[Pû8]÷k£\0r\È\ÈË\å\ÅûÜ¯ı\ë;¯¹\æšjÁşşû\ïg­zö\Ùg³/~ñ‹\ïÏ›7\ï…ó\Ï?ÿ\È	_\Ô\ïÜ™½¹qyö‹}¹aAŸZ¼&^k\Ô^Q?HAŸ\nù©ßª°·Q\09\ä\ä¥eyM¾\ïc=¶ü’K.\É-Z4¤b¾\ìG?úQ6w\î\Ü\íeÔ¾^Q\ßltŞ¨½¢~˜}³Ç±Q\09\ä\äe`A¿bÅŠ§.¿üò\ì\å—_\ÎF\Ã/ù\Ë\ì’K.\Ù~\ÑEı\ÉD,\ê[l\Ô^±®¨¯ü¿ùÁ\n÷ô¼s\ìm@\09yi,Fè£ s\äGS¼\ß\Å_\Ü\×\İ\İ=k¢õ\Ã-\èSS¬Oú¢¾Õ‚İˆ½\È G /\Å9ô1\å~´F\è\ë\ØÏ={[^\Ø=‘ŠzMQ?§U†6?\Ô\×c£\0rrL†¼\ÄU\î¿öµ¯\íŠs\è\Ç\Ò\âÅ‹·Ïœ9s­¢^S\Ô{\ä=¾¯Oao£\0r\È\ÈK¿…şU\\\åşÿñ³±vé¥—¾Õ©\ÓğõŠú½\\Ğ\Ö÷c£\0rrò2Q\Ä(}Ü‡>n[7~şóŸ\ï\ê\Ô\ÑzE½¢~4»(\Şx¿6\n G G /lıúõó\â\âx#¹u\İPÍ;÷­üzx;üı\İ\İ\İM:u_E½¢~úÑš:o\Ä\ŞF\ä\äH`²\ç\å‡?ü\á\Ê{\î¹\'O,x6ÿ‡^\İ&ö¼¼mÊ‹ûoD¯¨WÔ‘òE\î~\'o\Ö>Eùû\\<\ÏF\ä\ä˜\Ìy™?ş»O>ù\ä¸õ?ı\éO™ÿCµ\ÃßŸò·\Öv\ä\íy›ª¨WÔAA?½P˜?oY\íc«…}£\ïS\Ø\Û(€“5/W\\q\Å\î7\ß|s\\‹ú·\ŞzkKŒ·É‚=¼P\Ô\Ûkyû\Êù\çŸÿOõ“»¨;\àt‘zS\ä/¬\æ\Ù\nûbAŸ\Ú\ì\ÂóÎ±·Q\099&c^\æÌ™“\íØ±c\\‹úøy1*\Ş^´ÿóE}j[óöİ¼MNQ\ë‰\'Xm=öXµ;÷\Üsû?ğÁ³x úù\æÍ›³7fgyfµÍ=»úºı÷\ß?[¹re¶lÙ²lÛ¶m\Õ÷\×/Y²dÀ\ÏÛ°aCõ¹5k\Öú{\Å)‹/\Ş\ã±ø¾…\Ö}}<şğ\Ã«÷ıÖ·¾Õ±E}¾\ì—\ä­//\î¯\Ìóò{Cø\ÖF…v½}°Â¾\Ş\ë\ï©óz\ç\Ø\Û(€“-/ñ‡·\ßşö·©`\Ş\Ù\ÕÕµ-ÿøfmdü…¼=“·Ÿ\æ\Ôòü¹\Åù\ç\æ\í¯òöı¼}\'\ì[ùs_Qôü\ãò’·\Ï\ç\Ï\Ì\ÛgfÌ˜ñŸó\ÇO\Íûyûwùcÿ&/Ü˜5k\Ö!yAö‘(\Êò¯?·š¿ö_\å¯9±IQŸ\å\ï±;ÿø\ë\áÔ¿÷\Ş{\Õ…qd}}}\ÕÇ‹EòÖ­[û_\âu‡rHÿó\'œpBÿ€W^y¥úñ\Å_lX°\Çóo¼ñÆ …öQGU}Ï}\ìc\ÙSO=5\à€Cü.q \á\ÔSO\í\ì¸ã«8ˆ¯‹-<<ıô\Ó\Õ÷ñX:(\Ç)§œ\Òÿ\ÚG}4;\âˆ#:©¨¢°>l\Ê×‡\Ï¡ Ÿ6„B½^a\ßjA_.\ìMÅ·Q\099&C^.¼ğ\Âß÷Hıö\í\Û\ã\ê÷;¢ÀB»6Z~x\àQˆ\çEùI\\pÁŒ=\nõü±y›|òùc\×\åŸ\ß~úy»\'ü8\0P;°,\Ô<Ÿ·W\ãÀA@\È_·£Vœ½“·yû_y{¿AA¿=\Ş7ÿ¾\â Àp‹ú\Ç¼¿(¾÷\Ş{û\î\ã?>[´hQµÀ:ujÿ\ë\ï»\ï¾ş.\Çñºôq\0 Rb:ŠñT\ÇÁƒ“N:)»\ä’Kú¿\'>O\ïÿı÷Wóø]bfÀO<\Ñ€!^Ï—J¤\ï»ë®»ª\Òky\ä‘şÏ‹\ïQ<`_\ßt\ÓMı_\ï·\ß~Ù—¾ô¥!ıw\îÜ™\å\ëCZn/×–ï¢¼İ—/£o\ç¿œ·‹ó\ÏÏk\"Ìœ9ó\ß\æÿ\Ågœ±ÿ(„cucQş3>>\ÂÂºYa?Ô‚¾øó·VŒ\Ø\Û(€?/—_~ù®ñ>§~ó\æ\Í/\í\Ís\êo¼ñ\Æ}Z)\Ú\Ê\çQd¤¾Ra¢=¦\Ñ\Ç\ç\Ï?ÿ|\İQôh·\ß~{ö\Î;\ïT§\ç\Çôıt@ ¾\'>nÚ´©Šñû<ò\È\ê\È~£\ß\'FÊ£Ø\Ñóx\Ï(°>ø\à\êcG}tõ”€Ji¤?>Çy\æ™=,¤\éúñyüñy\ÌDˆS\Ò\È|<7w\î\Ü\ê\ç\çœsNõ \Åp§\ß\ç\ßûÏºººŠ?ù\Ç\éù2šS›±ñ\çyû^ş\Ø\Ï?®\È\Û/ò¶9o»b\ê|\Ş\â\âŒ?\Ë\Û\ãy{0ow\ä\í\Æü{/ƒFù÷ı§3füûX\æôGô\á\Òú\Ñ\×\à O<ş•80U§ oµ nT\Ø\ï3Ì‚>q½\È\È0òrıõ\×o\ï«\ßÿø\Ç?şŸ\írõû\Ú\Â0½ºŞ½\ë‡S\ÔGQ\n\Û\Õ>\à€ª\Ó\ŞW¯^]}ì¢‹.\ê>F\ÌS|ë­·V§­\Ç÷§‘û(Œ+µ)ğ1İ½\Ñ\Ï¬¨ë­·ª\ï#õñ¾i&@š*\Øa‡õ4ˆQütP\âŠ+®\È>ü\ágŸşô§«~<—T4\Z/\â\Ç{\Ç\çq€øŒ\ç9õq±\Ã(Ö£h\â=/\ä»ó—EQ_+\î£\ÈÿqŞ®ÿ}µƒ›kšñfşŞŸ\Ûw\ß}?5\ÌBº^aÿ\Ò\nú\á`ÀF\ä\ä\ä¥\Óü\Å_ü\Å=\ã}Ÿún¸aq»Ü§¾¶p—4uqQŸF®Sq{\çwöûñu*²Sœ^£\å1\İ}şüùÙ˜s\Ì1\Ùm·\İV}n\íÚµ{ŒÔ§‚7D\ÑüÑ~´Ú¢¾û\î»ûÏO¯}÷\İwûNÿ© üb¡^o–A*ö\ã<û\ÓO?}ß¹\\ÔŸ|ò\ÉF\ê\ã@\ÅYg\Õö\Ê;ÿüó7®\ÃSù›õq\êÆ¯>ù\ÉOşúw÷w³|\àŸæ¬WØ¤ /öÎ±·Q\099y™ˆ¾ò•¯ü\Ë\Ë.»\ì7\ï¿ÿş¸]ü~ÆŒ¯Œ\àa£®\Õ[–\r·¨‘\ïT\Ü\Æ\èx¶q1¹ƒ:hÀ¨vú:Î‰\ï‰B9¦µ{\ì±\Ùë¯¿^}\îµ\×^p…û˜ÎŸ\n÷f\Ó\ïë¦§iüqÁ¼\âkR&\Ò…˜QŸ?û\ì³{şi4~\"õ…\àv\Ë\Ãk\ç\ÕÇ lŸ}ö¹m„?.¦Ü—G\è_ª=>j\ïõ]¹\È\È\È\Ët\ÕUWı\ïŸı\ìg\ã5õ>.h·ºÿO\Ã-\êc4>·1­>Ÿ^œ^.\êSœÎ©¯Ô®š_)œS\à\êôûrQŸF\ê\Óh}\\\Ä.\ë\å\Ù•\Ú÷\Ó\ì‚õ\ë\×WŸ‹\Ç*µ)ı\éõ\Å\Û\íÿxm½\ë´kQ\ß\İ\İı‡…B>\î‚ğ\ßòÇ¦\Õ9=£\Ù\Õ\î›l¤¾•û\Ø7b¤\ŞF\ä\ä˜\èyùü\ç?ş¾ğ…\İ\ã0Z¿mÆŒ\Ï\Ä\Èød*\ê+µ)\ë©(‹\Òz\è¡\rG\Ì+¥‘ú¸Jşgœ1\à½\â\"u1?¦ÁGK…y\Ì\0ˆñt¡ºt½˜Ğ¬¨¯4¸\Ç}º°_ñ\ïˆ1J\ÒEò\Zıı\ÅQüNºO}üój\×YX¯·G·P@÷U\Æ\æœú\áöÎ©·Q\099&K^\æÍ›·>n›6–\âüı¥¯wº‰ZÔ§ó\×cT½R¸j|q´:¾.¾w|W/NmO÷€/¾&.¶W\ï\ê÷C9\ØË¼üX½¢>]`Îœ9ıSün\é<ÿÁŠöN.\êóBş ¸õ\â0\n\éVG\Æİ¶®\Ş\Õ\ï‡RØ»ú½\È\È0™ò\çÏ;wû\Ë/¿<&ıúõ\ë‹{Å·0\Ú9¡Šzmô\Ú\Ş(ê‡©\Õòf÷¡ovûF¦TÜ§\ŞF\ä\ä˜|y™5k\Ö\Ìyóæ½·eË–Q-\è\ßxãŸÌ˜1\ãõNv¯¨W\ÔC³‘òf}e˜…}:\àlİ¶\È\È0	ó2s\æÌ¹_|q\ßh\Ø\Ç}ô]]]_\ìôÿ¢^Q?DF\ì[-\è‡ZØ§Ÿ§ ·Q\099&s^òü³³f\Íz÷oş\æo~=‚‹\çmûö·¿ıƒ˜rŸ·9\áÿ¢¨W\ÔCŒØ—§\Â_Xú}\è\ëö³K?\Ç9ô6\n G G€¼ôÿÁ‡Ï˜1cÙ¼yó\Şz\æ™g¶\å>ô?şøC³f\ÍZ\Å\ë\äs\èõŠúQ,\ì‹\Ï+\è­ô•&\ß\ç¶u6\n G G€¼4ü\ÃÏ‹\â~Îœ9[\æÏŸÿ\ÜO~ò“\×{{{ßŒ+®‡\íÛ·¿µyóæ—–.]ú?n¼ñ\Æ%3g\Î|5Šù\ïÔ«\Ü+\êõc <?\nòÙ•¡ß¦®ü}\Óô6\n G G€¼4•\ë\ï\î\î¾2ÿG<’·\×ò¶#ş)µ›ò¶(oW\ÇÿDı(\êõ£\\ØT½©ı\Ø(€ò‚¢^Q\ß\æ…ıh ÀF\ä\ä\äE½¦¨oÁH§Ì›ro£\0r\È\ÈŠzE}öCiwQ<#@@^P\Ô+\ê\ÛÀP§Ğ›ro£\0r\È\ÈŠzE}öSòÖ—·³%\ÑF\ä#õŠúö1­V°O\äù­\nz#@@^P\Ô+\ê\ÛS£{S\îm@\09yAQ¯¨\ïÀ\Â^Ao£\0r\È\È£eÙ²eŠ\ë½\×v\äEı\îI°š¥B~‚\ŞF\ä#FÑªU«\ŞÜ²e‹{/´\Ş\ŞŞ¿Î‹ú&Éª6­\Ö\á¸m\È G /Œ–\åË—ŸµbÅŠ_½ıö\Û\Û\Ú\ã7BıÒ¥K_\Ï\Ûy:,##@@^¶¼°œ\Ş\ÓÓ³.¦‚\Ç9\ŞÚ˜·ø?¿0™\nzer\È\È \ÃÁ299\ä\Ğ\á`¼\0\èp,#@@@^\0–\ÈÈ‘¼\0:,###\0e\È\È\È \ÃÁ29\ä\ä\Ğ\á`¼\È \ÃÁ299y\Ğ\áXF Grrò\èp°Œ@@\0yt8XF G G /\0:\Ë##@‡ƒer\È\È \ÃÁ299y\Ğ\áXF–\È\È\È€\Ç29\ä\ä\Ğ\á`ò\èp°Œ@@@^\0t8– G G /€\Ë\ä#@‡ƒerrò Ã±Œ\099y\Ğ\áXF G€¼\0:,###@^\0–\È\È\È€\Ç2\ä\ä\ä\Ğ\á`rò\èp°Œ@@@^\0t8– G G /\0:\Ë\ä#@‡ƒerr\È \ÃÁ299y\Ğ\áXF€¼\0:,###@^\0–\È\È\È€\Ç2\ä\ä\ä˜4Ë¢\è`š´uşS6\n G€¼\0\í×¹L\É\Û\îÁŠú®®®\éşS6\n G€¼\0\í\ÙÁ,¤¨_;u\ê\Ô}ı—l@\09yÚ³ƒ™Ò¨¨\ï\î\î>\Û\ÈF\ä# ½;™z\çÖ¯3Jo£\0r\È\È\ĞşÌ€s\ëKo£\0r\È\È\Ğ!ò\"~¡Qz#@@^€\Î,\ê?Q8—~šÿˆ\È G /@gu6\Õs\ë\Ò\Û(€ròt^g3E‡c£\0r\È\ÈL:Y–\íûê«¯>ò\ä“OşfÙ²eYOO6\Îm\éÒ¥ÙªU«ş>o³l\äH“#9’#9’#9’#yZÿ\êÕ«³-[¶d»v\í\ÒöRÛºuk¶r\å\Êwó\rÁyv¢\äH“#9²>Ë‘É‘\ÉĞ’8’«\ão›\rÀ®\ìDÉ‘&Gr¤É‘É‘\ÉĞ’˜š¥\ãmŸ–wş»\íDÉ‘&Gr¤É‘É‘\ÉĞ’8÷G§\ÛVÿ„\í8\'òFA\äH\äH\äH´‰˜#E=L \Îÿ×¿z3{u\İ²\ç–~­\Ú\âóxL‡­ó·Q#9’#9’#9’#MQ´q\çß·­7\Ûğ\ãë³Ÿ/¾f\Ås:m¿(9’#9’#9’#9\Òõ@›vş›û\Ûjo<·H§­ó·%Gr$Gr$Gr$Gš¢h\×\Îÿ…\'¾Ù°ó\çt\Ú:;Qr$Gr$Gr$Gr¤)\ê6\íü7ô\ÜØ°ó\çt\Ú:;Qr$Gr$Gr$Gr¤)\ê¿\Î\ßN”ir$Gr$Gr$Gr$/ÀhvşqU\ÔF<§\Ó\ÖùÛ‰’#9’#9’#9’#MQ´i\ç¿q\Íw\ZvşñœN[\ço\'J\äH\äH\äH4E=Ğ¦ÿ;½\ë³\r\ß0pŠVşX<§\Ó\ÖùÛ‰’#9’#9’#9’#MQ´i\çíµ§\ï\Ğù\Çc:l¿‚É‘É‘É‘iŠz ;ÿ;³?ı\îÀ)ZùcñœN[\ço\'J\äH\äH\äH4E=Ğ†ß¶\Ş\ì\å5w4<÷*‹\×\è¸uşv¢\äH\äH\äH\äH\ë\Ü\å\ÙXùh\ÒÖ©  S:ÿ;³77.\Ï~ñ£/7\ìøS‹\×\Äkİµe\'J\äH\äH\äH´-\ê§\äm÷`E}WW\×tt@\ç\ß\ì(®£»:;Qr$Gr$Gr$Gr¤M¼\åùX2HQ¿v\êÔ©ûª  :ÿV\âvtW\'n\'\ÊN”É‘É‘É‘\ÉQGõS\Zõ\İ\İ\İg« C:ÿ\ávü©\é\Ä\íD\Õ1­¶5M\äH\äH\äH\äHÚº°¯wnı:£ô\ĞA¿¦óe§\åm[\Ş\Ô>N—#M\äH\äH\äHÚ¶¨pn½s\éA\ç¯MŞ¨\ÓJ;N12²u¢\íHÉ‘É‘É‘É‘6‘r”ñÒƒ\Î_³uZƒ‘\Ó&\Ú‰É‘É‘É‘i¬¨ÿD\á\\ú	yº\n(\ê5ÿ\à¦5\ÙQJ\ÏO“#M\äH\äH\äH\ÚO:·\Ş(=\èüµÉ·\Õ\êÒ„!‘#9’#9’#9’#­\Ós4s\æÌwuu]–ò\ämc\ŞŞ«\Ö\ï\È\Ûkµ)ùW\Ç\ëTQ ó\×&\îN\ÔiCñ\ê\ë\åH“#9\Ò\äH\äHFQÜª.o=y\Û5\È=\ê\Ë\í‰üõ³TS ó\×&\ÖN\ÔpG:\âûú:yGJ\äH\äH\äH´N\ËQ^˜\æ?\ZB!_¯­>ÿüóTU\Î_\ëü¨‘N]\ìè©r$Gr$Gr$Gr¤uRººº>;cÆŒÿS,\ĞgÎœ™}\ã\ßÈ–/_ı\İ\ßı]öÿğYx\ï½÷²\Ş\Ş\ŞlÍš5Ù‚²\Ï}\îs\åÂ¾Ï¨=\èüµ\ÎŞ‰jv¢ñ~9’#9’#M\äH“£\Æıô¼¿X\Ì?ğÀÙ–-[²VlÛ¶­úúø¾bqŸ¿\çEª+\Ğù\ëü;o\'j´¯\ZÜ‘#$r$Gr$Gr$Gr¤uBb„¾X\Ğ_u\ÕU\ÕQù\áˆ\ï»\æškÊ…½{\Ğù\ëü;hõ)_T\èwòva\í\ãP”¿¯\ã.V$Gr$Gr$Gr$GZ»\ç(Î¡/N¹¿\á†ú§\ØWLÍ¿ù\æ›\ËSñVe\Î_\ç\ß9;P\Ó;B\ß\Ï[Vû\Ø\êT£\ï\ë¨)9’#9’#9’#9\Ò\Ú=GyA¿´8B?Ò‚¾XØ—F\ìW«²@\ç¯ó\ï¬¨Jmd#+´Vv¤Š;P©\Í.<\ß1\ç4Ê‘É‘É‘É‘\Ö\Î9Š\Û\ÖÏ¡\î”ûÁ¦\âÏ±7\rtş:ÿö\ÕhÇ¦\Ş\Ñ`;Rõ^O\×w\Ä9r$Gr$Gr$Gr¤µyQß“\n\î¸\È\İXxè¡‡Œ\ÖC\'tş±±[»vmö\â‹/V?ß¼ys\İ\×=ıô\ÓÙ¹\ç[m³g\Ï\Î{\ì±\ìœs\Î\É\æÎ[ı|\áÂ…\Õ\ï\ì\çÌ˜1£\å\ß\ë\È#Ì=öØ†Ï¿ö\Úk\Õ÷|ê©§ªS„\î¹\ç\ìÀÌ®¾ú\ê\ê\ï“ş®ôú\'x¢ÿ÷‹\ç£\Åß‘Zú{\ï½wÀ\ï}\Ë-·\ìÑ™\Ç\ß¯ÿğ‡?œmØ°a¯wşÑ©\ç\íG°5m;Fõv¤Zİ*\ïHû\Ô\ÇVÿWr$Gr4ş9\Ò:¿‘#9’£ñY7fÎœùñtq¼M\çwÆ¤¨«\â—nww¸jÚ¬¨OÈ§>õ©şñ‹.º¨úùA”\İz\ë­ı¯=\à€²³\Î:k\ïÿ\Ø\Ç>–M:µúy___\Ób\ä\ÔSOmùwû\èG?šr\Ê)ƒ¾&\n’•+Wf?şx¶hÑ¢jÁô\Ì3\Ïdo½õVõ\ç­_¿~À\ï°xñ\â\ê\Ç(N\â±(X\â\ë\×_}\×\Æû\Æÿá¦›nª>ÿğ\ÃWÿ\Öh©`‹\Ç\ã\È\è\Ş\îüÓ¢zººº>\Ñâ·µº#\ÓlGj¨;PÅŸ¿u¼GHZı_É‘\É\Ñø\æH›ÅˆÉ‘Ïº‘?~Yz]4KqûBQµjÚ¬¨O½4\âŸ_pÁıŸ\ïØ±cbdÉ’%Ù¬Y³ª-^#z‡vXöÕ¯~µº\ãß¬9ø\àƒ>;øQ¬D‹÷\Şo¿ı²#8¢ÿw{\ä‘Göxÿ5k\ÖT¿¾ÿşû«EUúGuTõheñ½_y\å•\êÇ­[·ö4¾ñ\Æ\Õ\ïıÀ>P}ÿø»£¥\ï‰\â*~Fú:şöô36n\ÜX}.=\Ö.¡-ir„w¨S\íH\í3\Ì¨ò\ÈÌ¸\íHµú¿’#9’£ñÍ‘6±Š9’#9\Z\Ûu#\ì‘ôüŠ+Æ´¨mu\áwY¤Ú‚6*ê£¸ˆ©¶1õµ8\Õ6¦\Í\Æ\à(Ê¯Æ˜¢_\Ç\ÇxıüÁ4aŒ¢&\ÛÿııŠ\ÅOyô.–‹‘w\ß}·Z\Ä\ç\×_}µ‰Ï;\î¸jÁ‘Š‘x,¦øFQ©M\'œpBõo?\äC²3\Î8c@SŠ\Ó\å…L\ÅH|Ü´iS»vşıGx\ë\ì ÷\Âz;R/`j¤¿Ï˜ş¯\äH\ähür¤M\èbD\äH\Æ`\İÈ¿Ş˜\í\ä•õööMª-h“¢>v¢£xˆğTL¤\ï\ÉkT¼§\r_w\İu\Õ\ÃøØ¬ISscD²Xp4ı,#ñ{\Åhfú:\Î¾\ï¾ûª#Š© H\çô\Ş~û\í\Õóˆ¿ô¥/\íñ~—^zia‘\n”(¼\Òbüi´3\Ş\ç˜c\É\î¸\ã\ìCúPöè£Vş\ÑG]}~Ù²e¦\Z·[\ç_<\Â[›¾5Ò‘ˆz;R#Ù*\ïHMk—ÿ•É‘O´\ÉQŒÈ‘\É\Ñ\è®yÛ¾­\Û\Ø\rv{»\Â\ÏŞ¡Ú‚6\Z©;\Ø1\êw\å•WÖ6\Ï\×+Fb\ç<Î•)¾Q\04+F*µó€‹\Ù\Z¬E¡Q©M\íME\áó\ÉO~r\×‹©øùÅŸ—ŠŒò\ï‘F	\Ó4\ç\âc±ØŠ‘\Ê(<N?ıôşi\ÌiT2\Î=N\ï\×l\êpó¸´\Ï|\æ3iggÁW±}êŒˆ¼T{|$´\Ëÿ*šÉ‘o´±or$G\Ú\Ä\ÉQ±‡\â\ÏSmA›õ1\Ê;\Øq®¸:v|Sncƒ£rÅ«X§óaÓ¹ÀqÑ­´£#€ñºÁŠ‘4e8]\r;>Ÿ?~\Ó\Ñ\ÅÙ«7…·Q1ÏŸ|ò\ÉıŸ©˜\æ_\Ç\ã\é÷Œ)\Äñ¿J_\ÇEFŠ#†1¹\\x¤&|V\Ú\ì_uÚº®®®\éS§Nİ·ö\ÒfW\É\ÈH+÷\rnç‘‘=şWr$Gr4>9\Z¯;HÄ¬’\â\ï¯¹ù\æ›\ëşŒX_\ãùŠSV\Ò\Ï9ó\Ì3³9s\æŒj?\ç[\ßúV\Ó\×\Ä\é=ñy\\l2\Í\Ôi%;r4¹rTo\Ûq\â‰\'V[\ÚEv\Ò\ç>ø`uJ™‹~>\Öóhi½ƒ\Öq\0:fVÅ•\Ğ\ëŒ¯¯½ö\Ú\ëm\ÌøŠÏ‹—‹3\Ïb\Æ[ºxjq›\Û\×z¹L\×~I\ï_¾ğl¼_º†Ll\Ë\â\Ú5q\Ø\â,´J\í:2:Rß¿n\äŸ÷©‡I^\ÔG\'_œ¾\ÅAL¿sa\ë\ÌE‹)³i\Ç?v\Ô\ï¾û\îj\ÇWµ¬)\Ï>û\ì j\Z\å¬÷xL.>¿Ot0\åG±)~ÿI\'TİD§¦GQ\ã*\áq\Å\ï\â¾RS\Ü\È\Ä\ÇØ©ŒW|şüóÏ·c\ç_\Şy*\ï°ôU\Æ\æ\Æ\á\ìH\í\ís\ëş¯\äH\äh|r4wH¢0)l«÷ºø™±#[¾FEñt”¸CDº(eF‘øˆ\È\è?øÁw„H\Ï\Ç\ì–8È—\n‘\È]\\Ü²üûÄµ;\Ò\ç)÷•\Ú%Ú¼‘£q\ÊQ£\ëq´tÀ+\Í\È*®;q\Ôô\Út9\Ö\Íô|šU)œvU.°‹\ëhºfJô\íQT—3Y<\èœ2ï›®\ç\Òh½®\ÔnÁš\î\Î\Z\ZOÛ°Jé”¸¡\æ¦]s\äœzP\Ô˜›\Î}‹b\ÅQ\Îx,¦\è¦N>ZŒF\ÕUWõ\ïŒD1p\É%—ô\ïˆ\Ô\ë´c¥üs\ÓQ\á\Ø	K‹;>qt¸8\Z’Ø‰×¯^½º\î\ßï—Š‰¸‚x½Ÿ›v„\â|\ä´Ñˆ«´1‰\rOq\Ä$\íô¥\rFq#?¯|eğ6\éü\×vwwŸ\İ`\çi$#nT\ïj\ÃCÙ‘Ú›Wô%Gr$Gã—£\Ê\ßA\":E¶\âô“4ò_GöŠ¢E\á“~\ï\â\èc£¦‹]Æˆ~z]\ä<ˆ‹õü\ĞCmXP\êE‹STÒƒ4(õ%)\Ã\Å;U¤¿7Œ“£É½=j4ó¤x\×Jí´¬\ã?¾Zp\ÇA¤tP,Zø-f2\Ö\ã4\Ú\Å6-+®£w\İuWõÀv\ä3®S|mLK\ïÛ b^\â\0oñ€oš•R\Îp\æiV\\*\Ú\ã\Î+õ\nùbN\Ò\Ä4;®Šú†ë†«ßƒ¢¾º#;3Å€˜šU\ì\ÔkVÿ¨^œÿ…H£÷,DA\ÅA£\Ñ\ÊtA­x}üœØ¸\Ä\ë\ëqMG„\Ë;^±ˆb\"6 iJrñ¶^\å‘\Ê89v\ãoO÷\İNHºZwq„q°Ï´1j£\Î°‘‘H4»\ïo³û72¥²w\î\Ü\ÒÿJ\äH\Æ\'G\ãq‰\ÈIù}\êú§ÿtp-×•\ÚT\ã(\Æ\ãó\ÈAy}NEB¥0s%¦6§¥»<\Ä\ï-ÀH§\ÄAƒò\é1õFA#\ç\é(\È\"\Ãr4¹s\Ôl¤¾8\ÂÛ£tÀµQAœE_¦bÛ–¤>¿x\ç’T¸G\ërš…–f²·k\é\àpdK\ÛÃ”«øx\çw˜­’¾¯|PºR:%fÀ¥Ÿ3g\"k\é F|L³\Ü:`¤¾\éº\á>õ ¨\×&\ØURG \Ù\ÈD³¨\Ê0w¤Ò”Ë³\åH“£É›£ñºƒD«E}:Q\ØGq£˜Qœ\ÇkŠ£Œ\é\"‘qÀ¡X@D\ÑÓ‰\Ó\ï^i0R_o1(\Ş=¢üú©O#š\é÷©Ô¹n†\Ù•>§u4Ö¹X¿\â nÇ•\Âi.ÅƒU\é\0p\Ì*‹\ïO#÷qğ©R»\ÕiñÖª\é A*\ëİ¥˜\É8(n§Zœ.xô	1\ë&NÅ©w¹\Şµrú€â¶tÀ.e¨İ‹úVÌœ9ó\ãy½;\n\íüó\ê²\Z\Ñ/Îš5ë·…¢şp\Õ(F#\í§\ÑI«;PCİ‘J?\ïl9\Ò\äH\Æ\ãiFKœ\æ¯+^0+~fyšo<{\0Q|¤Qõ4u?š^ñb©\0‰‘úx>N9)\"\Å\ëL¤‘úò\İ#¢‰\Ùñšù¬w@ \İÎ©—£ö\Û¥s\è+µQğT\ì\Ç\×\é\Üôò9\ç±\î\Æhz\\+&No‰[“\Şv\Ûmı•*…‘ú4*Ÿ\nõz§x3™fD±YIwñ\"­\å@¦L¥\ê3\\o¦J¥Áôûtº\\œN0¶Gq\ßúTl\Çÿl,<ô\ĞC\ÅQú\Õ*-˜\à\ÅH¥v\å\ëb§7cÆŒj§\Ó*cG©üú\Ø@”¯’\İhtf¨W\n¤ò{\ïÿM\ìD¥’ò\Ô\Ã+C¿\ïo½©\ÙC‰™9J£xi\'ª|\à\Ô\â5\å)\é•·•+^\\,vvb\'+Š hÅT¤sÿczozM£×µc\î\ähb\æh¼\î #ƒišq<\çŞ§;:”ó˜ş\çQ˜\Äbš\æ_irñ®ôó‹\ç/\Ç\ï·s,O©oµ¨‚+@\ÅQ\Çz\ÅJ£~Elb\İO\ëZŒº\Çz…p\\„²Ñ…\äb\êz|O¬ó±{\ì±ı³TÒ¹\ï‘\É\ÈHL\ÓO\ëb|\Î\Ã/ogÊ£úñ3cû³h\Ò\Ôûx¬Ş]b\Ú|l+µ\Ób&Md%ıo\ê\å8N	‹\ß)H\Ä\ë\ÒA‰‘\\ù¾\r‹ú³SÁ£õ£}Á¼x¿|_¾”>ÿy³TZ0A‹út%ß¸\ĞO\Ú\éI·\'I·$ªwÔµ\\\Ô\Ç\×q?\ëF\ï?\Ô+\Çm‡¢¨H¯M£4\éó\âU[õ{\ì\àL«³C\Ô\ÊT¥\É÷\Ûm‚\Ú)Giª_\Ôi\İ\ìŠ\Û1-29iD¡xû¡\âñbG(\nœ\ØñO\Ó\ÓNOñu\Å{\ÔÇˆf½Ÿ[\Îg»\åN&f\Æ\ã1š…vW\\qEÿÁ¦(¢8ˆ\Å\äòÏ‰b ˜—ÁŠúÉŒ÷\Ü\ÄûG;]£\Ş\ÈG£sœ‹\ç\Ô\Ç\ïV¼¸XBõ¦<Ë‘\íQ:8”ú\à˜VŸ\Î//*õiL\ç\ÔW\n·2M\ç\ÔG®Ò€Mqm6ı>½&Š\í¸K<\ÒçƒR’\Öÿâ¼ôx\ä/]\Ï\"NyI·\ß+ó_)\\,¯xM€N\Ş\å\Åö©\èkŒ\Ö\í\íb?\â_ø\Âû\ÅQú!^+è¤¢>:ùb‘’vV*µ«\\\Çs\Å˜bÏ¥¬\Ø\ÙjÔ·z¥\àt–¸\ZjU\Ä\Øp¤\rE+ğš\ÄE}qGgza‡hve\è·*\ßH\ïGÜ±9ª”f°¤\â¢üº9ˆ‘¸Q\ä&F-\Òz_od¡¼sEv\ìPE‘é¥—\Ö=®ZŸ®¼_A‰÷)t´[\î\ähb\çh¬\î ‘f\ÇÄ¶&\Öı¸Z~9wÅ‚#D\ÆûE±\\¼G}ù\0[i=D\Ä\ïŠ+~o½[D–¨z\Û×”õt±\Í4Ê™\ÎC¾\á†\ÜjOl\Ê\ëXº\ëH´q€·|G†ÁF\êc›•.„Z|¯â·\Ô\×G.RXo¤>e&\îÒ’òz\Ê{:¿|\á\Öhq\êK0N\ÛÒ”…\È~¥p!\Ø\â=\î\Ó#\'Åƒ¸uhQxŞ¶¦ÿ{ü­#-\ìc\ß\á\æ›o.G\ß\×\İ\İ}´*&pQ_¼‡vš’•:\ê\Ôñ\×;\Zš6•Ú…K;h0”+§\â\ÆJíŠ§is¸Gg\'A1RoGj4F\\\Æıª\Âí£F«\âñ‰/ŸS\ët\ì°\Ä\ÎLì §\Ûo\Å–\Ø©¦Ø¦÷ŒE\Ş\æÍ›W},]¸«\Ş\Ôı\áŒ©üûDÁ“v|\Òû·[\î\ähb\æh¬\ï Q¾U]±H™Iwqh4“ \Õó•[}ıPŠú\Ô7TJ\Ç+^¸O\äh°–f¤\"8\İb®8#¤R:MªR¸\riš._\ŞO«”Î¯\ÔN\ãª7K¬\Ş9õ©H\ß#~v\Üvµ<\Ã->Æ¶)\Ä\ãÁ\îS¯ENb;Zü\â}£H­NÃ™9\ê\ê\êúlºh^\Z±\îTüø¾\Ò½i÷0Ñ‹ú4ªS¨bºT\ìl\ÅHQˆ\Äó±aˆ¢#=£½©HˆNºx\îU|n™’¦\îõJÁ©¸¨\Ô.„cT\'E\îÑ­¨“©\Ñ\Ş!\ë˜Åº#\Åó\n\Ó:£1¢P©À§\ÅH}.\åLDÒˆ|ñ1\ÒRœÎŸF4*…ók\Óm{Š;A\é\Ö>ñ{Å•…\ã\Âñ»\Ä\Ç5)\Ï h§\Ü\É\Ñ\äÌ‘f{$Gr$G­É‹\ï9\Å\Â>Î±Yq`¤Õ«\Ü\ÇEñŠ\ç\ĞG\ë\ê\êú¢\ê\n&A\ç#-QÀ\ÇÅ€b§?¦å¦«”¯…@ñü\ÉJmJdL\×jvm¥Å‹\nEK÷Ÿ\ÅP7\éHsš£4v¢Z\Z\Ñ\ÉÅ›\â8Z9Š\"½x._q\êa*~\Ë„,‡#© O£tid#µ(\î\Ó4\å(\ĞSŸfÉ¤\â>ŠúXÿ£O\Ó)‹\ç\ÊWj÷\"N·\â*\ßw¸]r\'G“/Gš\í‘É‘\rMŒ\Ø\çm[±(\â>N\\±bEu>MÍmtooou_ü/ÿò/³/¼ğ7\Å\ï‹)÷q @e“¤óO\ç\ÄøTP¤‹oUš\\(¯R¸\Í\ÊH‹úx,\ÎKS\Ç\ÒT³(Š\Òô³ò•ø\íD5\İ\Z\ê\ÈFG]„h¬s\ëÜµ\×^»\Çù}\å«\Ü7:·1¤Qùb‹qñ\0Yš®˜.T<\×>½¾Ñ¹“Å©\Ï\íš;9’#\ÍöH49j.Î±/\Ş\ên˜mµs\èa’uş\éü\Å\â\Î:·>\n(f*¥ó·\ÊE}º˜QŒ8\äJÁñs£x‰vEb\Ô0KS‚\Çûby¾U\Ü!š>F¯Ÿ\Ğ9J\ëvZ‡\ã\ÜÀJmD½Q!F\ê\Óhz\Ì~‰‘\ï\â\ë¢hu:\Ş7](Š\ê˜öŸŠöt ­|[ J\á\nüƒõ\í”;9²=\Òl\äH“£!÷\çÕŠû\İC)\æct\ŞU\îavşi\ä1\ĞU\nÈ‹\Ñù(j\Z/q.oœ\Å}¸ò\Ê+«\ÇıŠ]T«\Ò\ä¢B\é53f\ÌØ£¨JW\Ï\Ï<óLÿ\Ø\ìHM\É[_\ŞÎ–£ÿ?‹%®JŸF\Ê\ã¾À\ÅµR\ÔW\Z\Ü/]1<]\Å7\ÎGv\"[\é\0[ùû\âüù¸€P\ZAotU\îvÊ\Ùi¶Gr¤\É\Ñ\ĞÍœ9ó\ãyqe^¬?’·\×ò¶£VÀ\Ç\ÇMy[”·«c„_:ÿº;ø¦o™R~<F0Ë£’\Ã).\âÿ£¢õ~Fe/˜7Av¢Â´\ÚÒ´A\ß:v F#GQ„\ÇÁ©T\Ì\ÇTù¸%\\¥\ÉU\à+µ\ÍÕ»ıc½\×\Ç(~\\\Ï\"\Öõt`-µxŸF\â÷I÷÷ne}o\çNl4\Û#9\Ò\äP\Ôk:ÿ±!™S\åH\äH\äH\äHS\Ô:m¢wş\å¦	·%Gr$Gr$Gr$Gš¢\Ğùk¹óO;N&\â”É‘É‘É‘iŠz@\ç¯Mô\Î?\ÎY\Ì*|› 9’#9’#9’#9\Ò\ä\Ğùk“¹óÿˆir$Gr$Gr$Gr ó\×ù#GšÉ‘&GÈ‘\èüuşÈ‘!GšÉ‘&G€\Î_\ÓùË‘&GÈ‘!Gš:?r¤É‘ir„\É€\Î_\ç\Ér¤É‘ir\èü5?r$GÈ‘!Gš:M\ç/Gš!Gr„\É€\Î_\çir$Gš!Gr\èü5?r$GÈ‘&Gr¤\É ó\×tşr¤\Ér$GÈ‘\É ó\×ù#GšÉ‘&GÈ‘\èüuşÈ‘!GšÉ‘&G€\Î_\Óù#Gr„\Ér¤\É ó\×tşr¤\Ér$GÈ‘\èüuşÈ‘&Gr¤É‘Y\å˜l–-[¦\ÓmŸ¶#\ïüw[+\åH“#9\Ò\ä9’#€–¬Zµ\ê\Í-[¶\èxÛ õööşu\Şù¿`­”#M\äH“#\äH\0Z²|ùò³V¬Xñ«·\ß~{»x\ïÉ\éÒ¥¯\ç\í<k¥ir$G\Öi9B\ä ey‡3½§§g]LŠs´qoñA\Ç/GšÉ‘\Ér$G\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\00ªş/E¸>K\È\0\0\0\0IEND®B`‚',1),('142502',3,'source',NULL,_binary '{\"resourceId\":\"142501\",\"properties\":{\"process_id\":\"process\",\"name\":\"\",\"documentation\":\"\",\"process_author\":\"\",\"process_version\":\"\",\"process_namespace\":\"http://www.activiti.org/processdef\",\"executionlisteners\":\"\",\"eventlisteners\":\"\",\"signaldefinitions\":\"\",\"messagedefinitions\":\"\"},\"stencil\":{\"id\":\"BPMNDiagram\"},\"childShapes\":[{\"resourceId\":\"sid-450D3E1C-0BC3-46D1-9A34-B5B1E25358E2\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"executionlisteners\":\"\",\"initiator\":\"\",\"formkeydefinition\":\"\",\"formproperties\":\"\"},\"stencil\":{\"id\":\"StartNoneEvent\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-01195158-26A2-436D-A9A9-B148E4CA82F9\"}],\"bounds\":{\"lowerRight\":{\"x\":116,\"y\":99},\"upperLeft\":{\"x\":86,\"y\":69}},\"dockers\":[]},{\"resourceId\":\"sid-1E824AD2-3B5E-46E0-9DBF-8AC0C77ED5E1\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"asynchronousdefinition\":\"false\",\"exclusivedefinition\":\"false\",\"executionlisteners\":\"\",\"multiinstance_type\":\"None\",\"multiinstance_cardinality\":\"\",\"multiinstance_collection\":\"\",\"multiinstance_variable\":\"\",\"multiinstance_condition\":\"\",\"isforcompensation\":\"false\",\"usertaskassignment\":\"\",\"formkeydefinition\":\"\",\"duedatedefinition\":\"\",\"prioritydefinition\":\"\",\"formproperties\":\"\",\"tasklisteners\":\"\"},\"stencil\":{\"id\":\"UserTask\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-BA3E24A9-4796-4754-BC74-659051F2504D\"}],\"bounds\":{\"lowerRight\":{\"x\":261,\"y\":124},\"upperLeft\":{\"x\":161,\"y\":44}},\"dockers\":[]},{\"resourceId\":\"sid-01195158-26A2-436D-A9A9-B148E4CA82F9\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-1E824AD2-3B5E-46E0-9DBF-8AC0C77ED5E1\"}],\"bounds\":{\"lowerRight\":{\"x\":160.15625,\"y\":84},\"upperLeft\":{\"x\":116.609375,\"y\":84}},\"dockers\":[{\"x\":15,\"y\":15},{\"x\":50,\"y\":40}],\"target\":{\"resourceId\":\"sid-1E824AD2-3B5E-46E0-9DBF-8AC0C77ED5E1\"}},{\"resourceId\":\"sid-6671953E-684E-41C2-8C80-8BEAD9289719\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"executionlisteners\":\"\"},\"stencil\":{\"id\":\"EndNoneEvent\"},\"childShapes\":[],\"outgoing\":[],\"bounds\":{\"lowerRight\":{\"x\":334,\"y\":98},\"upperLeft\":{\"x\":306,\"y\":70}},\"dockers\":[]},{\"resourceId\":\"sid-BA3E24A9-4796-4754-BC74-659051F2504D\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-6671953E-684E-41C2-8C80-8BEAD9289719\"}],\"bounds\":{\"lowerRight\":{\"x\":305.375,\"y\":84},\"upperLeft\":{\"x\":261.390625,\"y\":84}},\"dockers\":[{\"x\":50,\"y\":40},{\"x\":14,\"y\":14}],\"target\":{\"resourceId\":\"sid-6671953E-684E-41C2-8C80-8BEAD9289719\"}}],\"bounds\":{\"lowerRight\":{\"x\":1200,\"y\":1050},\"upperLeft\":{\"x\":0,\"y\":0}},\"stencilset\":{\"url\":\"stencilsets/bpmn2.0/bpmn2.0.json\",\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\"},\"ssextensions\":[]}',NULL),('142503',2,'source-extra',NULL,_binary '‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0€\0\0\0®\0\0\0?\ÚM\0\0\0 cHRM\0\0z&\0\0€„\0\0ú\0\0\0€\è\0\0u0\0\0\ê`\0\0:˜\0\0pœºQ<\0\0\0gAMA\0\0±|ûQ“\0\0\0sRGB\0®\Î\é\0\0\0bKGD\0ÿ\0ÿ\0ÿ ½§“\0\0\0	pHYs\0\0\Ä\0\0\Ä•+\0\0\ßIDATx\Ú\í\İ\rP\Ôg~Àñv\Ù_€cÀp\ÖD\Ía\Å\Z£\Éx\ÚI‰’œ‘DsMft\nh/™$4\Í\ÙIt\ZcÆ¤^LœÖ¤Éµ—\Ü%\Ú‚\'¾E41*\Ú9œp±\ç\Ç&©Rõ„Uy•=\İş~pö<ö…\åû™ùÏ²\Ë\î\Ï\ïù=Ï³\Ïÿù\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÀEPE\'úÔ©S?illÌ¾r\å\ÊØ¦¦¦h·\ÛMÁô6\à#\"<v»ı’\Íf;\Ø\ÒÒ²*--\í7”\n	\0\ÒğÿCuuõ›.—kPrr²‰‹‹3C‡5‘‘‘N/i•¤j\ê\ë\ë\Ó\é¼\Ú\Ö\ÖöMsssvFF†ƒ\Ò!\0Au\æÌ™Â“\'O>v\Çw˜\áÃ‡k•Bñ£\Ê\ÊJSQQ\Ñ,#®y>ø\à6Jd`±P¥\Æ_\ÇÆg’’’hü@GV			Q.\\˜]–——wœR!\0¥\Ó>\Ò]¢lll§\ïÿ±ñ¼q~½\Õ8¿\ÙfªOı\Æ\\¹|\Ê\Ø3V{,…\×K6›M“€\İ\étÎš?şüüü”\ÊÀÀ¤*‚N?ğ\Õ9ö\é®ñ?{ø¿Lcõ1\ãn»j<\îVs¥\æ„9shqÕŸ£\0ûÀ\àÁƒÍ¨Q£\Ü‹\å\ç”	\0d\ïÿ\'ú¯\Îùw¥\æô¦õjS§\Ç5TŸ*¦\0ûÈˆ#¬V\ë˜={öü5¥A\0B—z\êjŸ\î\æü›o\Ğ\Ë\×\ÑúÎ°aÃœr3‡’ \0¡\ëüu©gw<m-İ¿\ØÃ¹})11ñ/\ä&’¬‚MOò\Ò\Õ(>ù?|Wn’(	F\0@@\è\ÉIœ\ä\"\rÂ·ÿ:†$\0 4D\Å|§û!¬‘@@\ØJ¸\ã>cµ\r\îô¸%*\Æ$ŒL£€\0\0\ÂÕ¤qfp\â\Ø.:l<\0\Î\ÚZš;=\Öú\Ç\n  \\]mº\è=\ã·ñ\â7¾\×t©Üœù\İ\Ş\ç\0  Œ\ÔU–\ÆµwßŸ\î\\©=m*¾g.Ÿ\Ùo<œ\0\Ü–{!${ı\ç\î\í\á÷„n	¡\ÏoQÂ°¿œe¢½K\Ù0@¿£=ú6ş2\Z‘\Â\éÿù\0\Ğ_işV\éN¡\0z†) „œ\Ô¹À\0\0@\0\0\0\0\0$\0\0À-\âC\à \Òká–••ıı‘#G~TUU5©²²rP}}½\Õ\år«\Õ\ê:thKBBÂ¹\Ø\Ø\ØıòôŸ-]ºô¥Üšùó\çhmm}<\"\"bºÜ$G²C\äp\É\á”x<*\ßû­\Åb)X·n\İH\0ğ[\Ãÿ\É\'Ÿ¼Ÿ››;\ïÒ¥K\Öq\ãÆ™ôôtsû\í·iğMtt´‘$\Ñ\Ğ\Ğ`;{ö\ìh‡\Ã1úğ\á\Ã?z\á…\Î455\å¾ÿşû«)E g\æÍ›÷¸\Ü<\çv»§Io\ë\â)\ÑrŒ’ï’\Û\Ç\Û\Ú\Úş-++«X\î¯Y¿~}>	\0}\æÀO¾şú\ë\ï\Ö\Õ\ÕÙ¥’™‰\'\Z\éqt®‘’ôHJJ2÷\Ş{¯Y¸paDii\éÈ‚‚‚_\æ\ä\ä,©©©ù\á¦M›şº&ñ¥ú/¥Ã•q/O—×¥\Ë{\äHBx\"\\c¸^¿uûö\íEk×®}`ö\ì\Ù\æ‘G\é²\á¿MrXv\ïŞºq\ã\Æ\ß?ó\Ì3ÿ\Äh\0\è,333+22ò]\éõ_»š\Æ\Û]w\İe¦NjRRRL||¼^S¯ImjkkMEE…9xğ ‘Ñ¶ii¹v\ê4y\İE<£@€\Zÿ}ûö\íß»w\ï÷/^lÆŒÓ«÷\Ë\È\È0©©©1+W®üùSO=e_½zõ/(e\àZ\ãÿ¨4şù\Òø[:\Z~\ípÍœ9\Ó;¢¾Ş Aƒ¼\Çğ\á\Ã\Í}÷\İ\çM;v\ì0;w\î4\Òû×§‘şX’@´$°\êp±\n(\0´\ç¿eË–\ï¿ò\Ê+½nü;Œ9Ò¼ö\Úk1R\ÑWJ\ÅÌ¦”k=ÿBi°½¿6\êo¼ñ†~\0\Üe\ã\ß\èóõuú¹œOG\îƒp‹5€Ÿéœ¿ô$X´hQ+`O\éû½üò\ËC¢¢¢şC*f*¥L\çü¥·ÿnG\ã?v\ìX³bÅŠ?i\Äo†¾N_?a\Â\ß$ğ^8\Å\Z	ÀtµOQQ\Ñ{:\ç\ßW=ÿ®F\Ò\ë±K¯\çCJ™\ÄÀ\ê9\íù/Y²\Ä;\Ç\ß:5ôüó\Ïû&ú€€?«°°p.\å\Ô9{š5kVL\\\\\Üh¦‚0P\éROiü _ëœ¿6Ú½mü}“€\à}m¤…K¬‘\0ü\Øûw8§K=£¢¢üşórrr¾#= \ç‚4ô^®˜Î¢«]«ûú\ï­NûtG\ß\ï\ÑGõ\ïœp(w\ÖOô\ß.Xu\éf \Ü}÷\İ6»\İ>Z\çA\n\n*ü\çşL\Â\Üv\Ûmòó#\"\"Öµ¶¶–lÚ´©‘š€›©C\É\É\ÉM™™™…ºŠ§§uH\Ïğ•\Şÿ½M\0şğğ\Ã›\Ï?ÿ¼c‰hZb\Ğ\èöİ\ä\å/&Lp~ù\å—s\å\ËUÁø›¥\á\×\Ó\ê³%³%€k¥G·]î¯—\ÄT¼v\íZµ=0X\ê\Ñ|©Có%vê¥‘\İ&÷}£:Ô¾½ƒ7\Ğ4\æôlz\Ğ\ÕAzR¦\ÄX\ÇCA‹5@ˆÓ½}t{‡@š:uj¼T\Î\é¡P)% \ã%ˆÊ—].W³òyl›”\Ëg\Å\Å\Å\\¶=+\Ç©G¤¹¤C±Q\îo½¾µ\ï\í\ã5e\Ê¿şBúş>	 $b‚tc7]¡Hw\Şy\ç ó\í&W¡&FK ÿX†øu’vIĞ®ed€›\İÑ¡:¤#ƒ\"İ«G\ë\ä†I>1\à\×_bÔ¨Q¾w\'õ÷B%ø‰\î\ê©C\Æ@jiiI’ i•\àX\ÂE\'G¦ü™º\ëiffæ‰‹/RaBDuuµw+„¯C:2˜+uh®\Ö!\á\îø†¿c\îº÷O$ KZ1u3·@:p\à€‡õú/ı¥œ\ä÷ı\î¿2g\Î*Mø\â‹/ôó+ÓŸ\êñY\Í\ØWK?»£KB}G%$\0tI†¦\İ\Ò9I`Ê”)Õ›7o—Fõõ\0ÿ¹=i,®¶\ßÚ®K\0\'\'O|\'5&4\Üÿı\æô\éÓš~\Z‚u¨MzıöNor\Ë\Ã\Ş$\Ğ\Ğ\Ğ\à\×$ \ÇùöóH\0\èzŒ\Z\ÛR[[kKNN\ØÏŒŠŠª‘`ptø••\Õ]ğ6k`\Ê\ïd\ïhø\å~­\Üß«KE%I\î\Ò\Ï\0|òI5&4$&&zWº,]º4$\êNiÊ»}‹\ÜvªC\Ò\Ñ*“\ï¥\èóu#7&\0}\Õ$\0t)>>şŒô¤R™\0\Ê\Ê\ÊNh/)\Èº®Ûc°1\Z°\í\í’cCLL\Ì^iôk©!ø3£Å¶öúcõ©W]\Ö!IG;ÀÉ“\'ûü$0_ºe´£$\0t7\Ø\çp8Rt\ïñ@Ù¿¿ö¤÷ùO\â°›\å\Øı«}pl7S‡dD°O…®\É÷\î\ç?}út¿ıbúş>öõ÷‚&øI]]\İ2x¢­­-2@\'ƒ¹\Ê\Ë\Ëu—\Â\ç‚õ7\ë\Ğ\\nŠ###?j_\âIO~¯CV«µP\â\ì\ßõ\Ë\Ò\ÒRSSSã—“Átú\çĞ¡Cö®iON$\0t–››{bñ\âÅ§¤B~O\çTı­¨¨h‹\Û\í¤S\Ó_‘X…ô\Ò6\Ó\ÓG \ë^À}Ş¼yÅ’<f\è\\ôB.ºŸ_Ûµk—u\Ü\Ñø—ô÷m H\0ş,ûµ˜8q¢\ÕÏ£€Ú>ú\è¯$€\Ş\n\Æ\ß)\ËA®CzU¼ú…&€iÓ¦õ\éggÏ5Ÿ~ú\éµŞ¿\ÄÚ¯Â¡\Ü\Ù\rÔÖ¬Y³©±±ñ­ş´j\Õ*\íı7UUUP\êˆÖ¯__(7\Åúµ\Ş~ûm\ï’Ğ¾ K?\ßy\ç6‰±k½ÿp‰5€ÿG»u\ë\Ö\æòòr¿¼YY\ÙöC‡Í–\É\Óì±ƒ\î	Ó¾4³²²Ò¼ùæ›½N\ÚøK2ñœ;w®c\ßN±FğÿĞ¶\â\êÕ«OKe¼\Ò\×[H¥ü\íÊ•+\'I…üg\éı/¥kg‘|\émœ;æ½¤£N\ß\Ü\n}İ«¯¾\Ú&¬¿Nı<N±F€üüüu‹–/_\Ş\ØW#\íù¿øâ‹·\Ëp÷=©ù”2`Ì†\rtj\æ\é$ #eË–\é\Å×Ÿ\Ä\Õ-}$ó\ÒK/ùöüu…Ò²p‹5U&0¤Á>’’’rª¤¤d¦\Åbi3fŒ-2ò–òo\í[o½U }ºT\È\årûŸı½l.\\¸\"\Ğ;§¢{º\Ä\Çü\Óşúû;£\ãÇ×\Ö\é±\ë.¢\æøñ\ã\ŞU<\'NœĞ•<\Ş\ëthü\Ù\ív\ï4\Î%Fõ2®\æ\Ã?tõ\ÕWº\ëDû[\êù9\ák\×cP€{\'YYY6nÜ¸f\çÎ\ãsrrb\ï¹ç˜¾ÜµgÏÂ¼¼¼©À)R±`\Ú¸q¬Iœ| \ËCõ1ıpXw:\Õ\ã&fFJt\Î?\\c`\ík‡ •s\îªU«µ\ÙlRSS/¦¥¥\r=z´-!!!Y7knn>/CÑº¯¿şº\\F\r–òòò±RG\Èk\át:óùÀ\èQ¬=¤±&x$‚ô›hóJ\äX\î±F^\åÔ³7\ëõLKKK\ç=zôo\äşdó\íƒº…¨î›¯Õµ\Çÿ-Ç³\áp\â	\ÌXs»\İzXKn59]=¤{û\èö›J¬‘\0‚L\Ïb”›·\Û\0\ÄZÀ°\n\0H\0\0\0\0\0€\0\0 \0\0H\0\0\0\0\0€\0\0 \0\0H\0\0\0\0\0€\0\0 \0\0H\0\0\0\0N%ŒŒô¸\İn\n\"\Èÿ\á´i¿.H\0€\ßEGG_hhh  BÀ\åË—\æ\Û£€\0ø_TTTI]]œNg½\ÜS$\0  \\.×¿VUUµx<\n#¸*jjj&\Ê\íŠ‚\0Ä´i\ÓJ\ãÿû\Ê\ÊJ\n#ˆ\Çù?T>ô\ĞC(\r\0\ÈQ@vEEÅ•úúz\n#jkk·I\ïÿ‡n·ûJc\à°Pùùù\Õ,8x\éÒ¥\Ç\â\â\â¢\ív;…ÀÆ¿¬¬lBDD\Ä?Îœ9³„!\0———w2;;ûw\çÏŸ\\\Z£¦\Ø\Ø\Ø¹¥`ü§\Â\ápl’‘×ƒ\ÚøÏ˜1\ã3Šd`!ºrv\ïŞ\Zù\Õj½\'))\é‰‰‰ß•QA²<F\áô’®ó×¥º\ÚG?ğ\Õ9ö\É\È\ÈpP:$\0 dM’\éù2]\ÉrDS*½¦\'y\é:ÿb9¶ğ/\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿiüV1\ìW›Q\0\0\0\0IEND®B`‚',NULL),('145002',1,'new-process.bpmn20.xml','145001',_binary '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.activiti.org/processdef\">\n  <process id=\"process\" isExecutable=\"true\">\n    <startEvent id=\"sid-450D3E1C-0BC3-46D1-9A34-B5B1E25358E2\"></startEvent>\n    <userTask id=\"sid-1E824AD2-3B5E-46E0-9DBF-8AC0C77ED5E1\"></userTask>\n    <sequenceFlow id=\"sid-01195158-26A2-436D-A9A9-B148E4CA82F9\" sourceRef=\"sid-450D3E1C-0BC3-46D1-9A34-B5B1E25358E2\" targetRef=\"sid-1E824AD2-3B5E-46E0-9DBF-8AC0C77ED5E1\"></sequenceFlow>\n    <endEvent id=\"sid-6671953E-684E-41C2-8C80-8BEAD9289719\"></endEvent>\n    <sequenceFlow id=\"sid-BA3E24A9-4796-4754-BC74-659051F2504D\" sourceRef=\"sid-1E824AD2-3B5E-46E0-9DBF-8AC0C77ED5E1\" targetRef=\"sid-6671953E-684E-41C2-8C80-8BEAD9289719\"></sequenceFlow>\n  </process>\n  <bpmndi:BPMNDiagram id=\"BPMNDiagram_process\">\n    <bpmndi:BPMNPlane bpmnElement=\"process\" id=\"BPMNPlane_process\">\n      <bpmndi:BPMNShape bpmnElement=\"sid-450D3E1C-0BC3-46D1-9A34-B5B1E25358E2\" id=\"BPMNShape_sid-450D3E1C-0BC3-46D1-9A34-B5B1E25358E2\">\n        <omgdc:Bounds height=\"30.0\" width=\"30.0\" x=\"86.0\" y=\"69.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"sid-1E824AD2-3B5E-46E0-9DBF-8AC0C77ED5E1\" id=\"BPMNShape_sid-1E824AD2-3B5E-46E0-9DBF-8AC0C77ED5E1\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"161.0\" y=\"44.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"sid-6671953E-684E-41C2-8C80-8BEAD9289719\" id=\"BPMNShape_sid-6671953E-684E-41C2-8C80-8BEAD9289719\">\n        <omgdc:Bounds height=\"28.0\" width=\"28.0\" x=\"306.0\" y=\"70.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-BA3E24A9-4796-4754-BC74-659051F2504D\" id=\"BPMNEdge_sid-BA3E24A9-4796-4754-BC74-659051F2504D\">\n        <omgdi:waypoint x=\"261.0\" y=\"84.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"306.0\" y=\"84.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-01195158-26A2-436D-A9A9-B148E4CA82F9\" id=\"BPMNEdge_sid-01195158-26A2-436D-A9A9-B148E4CA82F9\">\n        <omgdi:waypoint x=\"116.0\" y=\"84.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"161.0\" y=\"84.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n    </bpmndi:BPMNPlane>\n  </bpmndi:BPMNDiagram>\n</definitions>',0),('145003',1,'new-process.process.png','145001',_binary '‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0X\0\0\0†\0\0\0˜/’«\0\0\ŞIDATx\Ú\í\İ\İOSgÀq/¼ğØ…É–\Ì?a»ğ0\Ù.$  $V(¢$dJ ¨4†\Ä-YLHŒ†…%\Æ cL^\n–WÉ˜˜„6DÁ2†Š+È³\ç\×Qs, \Ğöœ~?\É7Šb\ì9\ß>}z^¶m\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0˜B)µ}ll\ìfOO\ÏÛ––\Õ\Ü\ÜÌ°x¸\İn\Õ\Ş\Şş\él‘€ƒH\\;::”\×\ëU~¿Ÿ£133£\Ú\Ú\Ú^\è\Ø\îe«Bf®\Ä\Õ6‘õ\ë\í0[%\à²,@\Ü\ì3t`—\Ø*‡5@\Âf«À*¶J Áû\êù”\Zë½¦†Ü¥!ÿ-_#Š@Ÿ›T\Z‹\Ô@}Á{C¾&F \ÌÀ>úyM\\ƒcb¨0X\0\ávønù†•\Ç#f`4—lXyŒ0X\0–À°W`å¨+F \ÌÀv}¿a`\å1\ÂH`„\Ø\Ù\ÉAõ ©x\íò€şš<F	,€0+\ã\á\ï7\ÖV¾F	,€Hûúµ\Z\íşa\íò€şš<F	,€0+gjt}·\á\Z¬<\Æ\Ù\\ÀV«g¦S£­ê†o7Œkp\È÷\È÷2›%°\0>Ø\ÍZ™\ÍX\0av3³\Ö\Íf	%°A`ÃkpJ`\Ü&°\0,@`\0%°€9”R;]×¯_o+++{q\ìØ±¥\Ì\ÌL•’’¢\Ò\Ó\ÓW9\â?}úôXyyy•ŸX\r_ZZ\Ú\'\É\É\É9zÿº©Ç¨ó²¯é±¨\ÇCıXşõ”|uŠó°Şºu\ë\ÆÙ³g—òòò\ÔÕ«WUOOšœœT‹‹‹JÈ¯^¯Wİ¿_\é\0«\ãÇ¯œ8qbüĞ¡C\Ù–A`7oß¾}_\ëÑ¬‡5¨›wõ÷§S«8£Cšu\îÜ¹\×x.//«\Í\ê\ï\ïW………\Ë.—k8))\é3\Ë °Ó‘Ü©#Ù°…¨®7:œ´¯9yÖº½¶¶¶õğ\áÃª®®nKa\r\Õ\ĞĞ ²²²|N™\ÍXmú\í~Jjj\ê¿\ÆX\ê·ş\êÂ…ªµµU=~üX½|ù2°?-,,\Ş=vuu©ŠŠ\nµÿş\Ğ\È\Î3›µy\\=\Ïo¹¹¹jddDE\Ãøø¸Ò±öeggC`ö½¸\î\ÑA\\6†µªª*°\ä¶sss\ï—\ßg­ş3³©™\r\É\ÌU\âº\Ù\'x³\ä\ÏÓ³Ø¸u%°6š3Wc\\óóó³\Õp\Èï“¥¼\È2“µÛš«,Dk\æº\ŞLöÀsú‰\ßE`‰XYs5.¿[—,œ?>t¹`e³\Ç\ÒÀ\Ò\ÒR¿¬¹š©¾¾Ş§\ß\Î\Ü#°ŒD¬«\Û8s4®\ÆÈ†\Ìd;¨›\r\Ô\Ô\Ôü(OÌ›7o”Ù=:¯o_,”Še\\s\rwY\àC\Ë\Æ5Y–\nl0{•\ã\\\åP,+ø\ãuK`	l\ÛŒŸ|@e†\ê\êjf±v!gh\É[‘µUYYYÓ²e§^/	,5s’3¯‚l\É,svvÖ”ıK.9„k\'¥‹9ıµ²²RY©¢¢¢_Nõ³Ñ‡Á·S\Í\É\É\É_Xk\Æ6´zúk\àû.^¼hö>f\ì)J#rm9ı\ÕJ\İ\İ\İ\ãúI¯³\Û\ÎawÖ›X\É6´zmÀ\ã\Ç\Ô}LNF0ü]\ê(]ŒÈ…[¦¦¦,\r\ìôô´W?\éOl¼s¼›wK`#Ù†V/\Üøz´?\Ü\n%g|şO(]Œ\ÈU±‚n±Šü<¹2\İw\ãlD\ŞöX\É6¤‡/øÿ\Ñ:4\ëC‡l~ö\"¥‹\á†^\Ø\"&ƒÀ\Úk\Ä\ã6V\ïc”.F222V¬Áú|¾\é8™Áö\Êù\â»w\ï\Ş\Î3\ØH·!\Ãu]™Á&Š\Ü\Ü\\¿\Õk°OŸ>ı\Ó\æk°ï…•5Xmˆ5\ØTTT4jõQ¿\Úô(‚{r¦MhX	,\Æ6\ÄQ	\èÒ¥K•V[\\\\\\o³\ã`×±X\Ímˆ\ã`Ğ™3g>\Í\É\Éyk\á™\\‹©©©\Ç\ã\Ù%–ÀFB\Î\ä\Ò\Ûı’grÉ½ò8“\Ë&òóóÿ²\êZUñz~4%°‘\âZ	\è\àÁƒI\'O\\²`;§g¯}\\M‹ÁÕ´Ì»š–\Ş\ÇV¸š–Í¸\\®A³¯+\ë½òŠú±µN\Ëpø·\ïšu=X=QZ6\Î^\ãu_sY§‘šuGƒÁÁÁZı3¦¸£ƒÀ¦\ì\ÔcÆ„;\Z¬pGKOOO\Ó3Ù…hß“kbb¢S¿my\Ä=¹örO®\à^Ñ¸\'W\ÈÌ•¥»JKKË’Fk&+3W‰«Ş \n\ãıß†À\Ø(\Ïd3‘\r\ŞUV\Ø\ì\Ñò–q\ÍU†ö5G“WW=›}qûö\íW|ğ5wùò\åk², ’ş],5c_\Óc\ÎH	­\'+\'#\È\ì4¸| \Ë\0r†–œDp\å\Ê•‘‘ñ6\ä\ì±y§\ìk	±&«_[\\.\×t__Ÿo+Ç¹655U\ë@÷\Ê\"»“Ö,5k_3¾\æ\è`\Í5>Ÿü½\Ú\Ì\ÌLoYY\ÙPgg\ç#ı*:¼@Œ\\¸E®-\àv»)))¹£_}\Ç\äÉ–WR§}‚I`	¬\Ùû\Újh—¶V\'\îk‰¸6+÷\Ê[=Ÿú¡\\¡\'x¥¹˜„œ\ï,§\ä9ù¬K`\Ù\×\0K`X@`	,\0K`X@`	,\0K`X@`	,\0K`X@`	,\0K`X@`	,\0K`X@`	,\0K`X@`	,\0+µ´´6ûŒE\Ø%¶JÀ!\Ú\ÛÛ§¼^/q³Á˜œœüIv˜­pˆ\Ö\ÖÖ¯<\ÏógÏùˆ\\\ìf®W·\ÛıH½l•€ƒ\èz9õ\Ê\ÛSYdX>\ä\ß}˜¸\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0˜\ä?®#Š\r\0\0\0\0IEND®B`‚',1),('145006',2,'source',NULL,_binary '{\"resourceId\":\"145005\",\"properties\":{\"process_id\":\"process\",\"name\":\"\",\"documentation\":\"\",\"process_author\":\"\",\"process_version\":\"\",\"process_namespace\":\"http://www.activiti.org/processdef\",\"executionlisteners\":\"\",\"eventlisteners\":\"\",\"signaldefinitions\":\"\",\"messagedefinitions\":\"\"},\"stencil\":{\"id\":\"BPMNDiagram\"},\"childShapes\":[],\"bounds\":{\"lowerRight\":{\"x\":1200,\"y\":1050},\"upperLeft\":{\"x\":0,\"y\":0}},\"stencilset\":{\"url\":\"stencilsets/bpmn2.0/bpmn2.0.json\",\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\"},\"ssextensions\":[]}',NULL),('145007',1,'source-extra',NULL,_binary '‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\02\0\0\02\0\0\0?ˆ±\0\0\0 cHRM\0\0z&\0\0€„\0\0ú\0\0\0€\è\0\0u0\0\0\ê`\0\0:˜\0\0pœºQ<\0\0\0gAMA\0\0±|ûQ“\0\0\0sRGB\0®\Î\é\0\0\0bKGD\0ÿ\0ÿ\0ÿ ½§“\0\0\0	pHYs\0\0\Ä\0\0\Ä•+\0\0\0 IDATx\Ú\íÁ\0\0\0‚ ÿ¯nH@\0\0\0\0\0\0\0\0À£\'B\0ü¦•ş\0\0\0\0IEND®B`‚',NULL),('147502',1,'source',NULL,_binary '{\"id\":\"canvas\",\"resourceId\":\"canvas\",\"stencilset\":{\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\"}}',NULL),('57502',8,'source',NULL,_binary '{\"resourceId\":\"57501\",\"properties\":{\"process_id\":\"salary\",\"name\":\"æµç¨‹å®¡æ‰¹æµ‹è¯•æµç¨‹\",\"documentation\":\"\",\"process_author\":\"bootdo\",\"process_version\":\"\",\"process_namespace\":\"http://www.activiti.org/test\",\"executionlisteners\":\"{\\\"executionListeners\\\":\\\"[]\\\"}\",\"eventlisteners\":\"{\\\"eventListeners\\\":\\\"[]\\\"}\",\"signaldefinitions\":\"\\\"[]\\\"\",\"messagedefinitions\":\"\\\"[]\\\"\",\"messages\":[]},\"stencil\":{\"id\":\"BPMNDiagram\"},\"childShapes\":[{\"resourceId\":\"start\",\"properties\":{\"overrideid\":\"start\",\"name\":\"å¯åŠ¨å®¡æ‰¹\",\"documentation\":\"\",\"executionlisteners\":\"\",\"initiator\":\"apply\",\"formkeydefinition\":\"/act/salary/form\",\"formproperties\":\"\"},\"stencil\":{\"id\":\"StartNoneEvent\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-36E50C8B-6C7C-4968-B02D-EBAA425BF4BE\"}],\"bounds\":{\"lowerRight\":{\"x\":60,\"y\":270},\"upperLeft\":{\"x\":30,\"y\":240}},\"dockers\":[]},{\"resourceId\":\"end\",\"properties\":{\"overrideid\":\"end\",\"name\":\"ç»“æŸå®¡æ‰¹\",\"documentation\":\"\",\"executionlisteners\":\"\"},\"stencil\":{\"id\":\"EndNoneEvent\"},\"childShapes\":[],\"outgoing\":[],\"bounds\":{\"lowerRight\":{\"x\":1003,\"y\":384},\"upperLeft\":{\"x\":975,\"y\":356}},\"dockers\":[]},{\"resourceId\":\"modify\",\"properties\":{\"overrideid\":\"modify\",\"name\":\"å‘˜å·¥è–ªé…¬æ¡£çº§ä¿®æ”¹\",\"documentation\":\"\",\"asynchronousdefinition\":false,\"exclusivedefinition\":true,\"executionlisteners\":{\"executionListeners\":[]},\"multiinstance_type\":\"None\",\"multiinstance_cardinality\":\"\",\"multiinstance_collection\":\"\",\"multiinstance_variable\":\"\",\"multiinstance_condition\":\"\",\"isforcompensation\":\"false\",\"usertaskassignment\":{\"assignment\":{\"assignee\":\"${apply}\"}},\"formkeydefinition\":\"\",\"duedatedefinition\":\"\",\"prioritydefinition\":\"\",\"formproperties\":\"\",\"tasklisteners\":{\"taskListeners\":[]}},\"stencil\":{\"id\":\"UserTask\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-163DBC60-DBC9-438B-971A-67738FB7715A\"}],\"bounds\":{\"lowerRight\":{\"x\":311,\"y\":193},\"upperLeft\":{\"x\":209,\"y\":135}},\"dockers\":[]},{\"resourceId\":\"audit\",\"properties\":{\"overrideid\":\"audit\",\"name\":\"è–ªé…¬ä¸»ç®¡åˆå®¡\",\"documentation\":\"\",\"asynchronousdefinition\":false,\"exclusivedefinition\":true,\"executionlisteners\":{\"executionListeners\":[]},\"multiinstance_type\":\"None\",\"multiinstance_cardinality\":\"\",\"multiinstance_collection\":\"\",\"multiinstance_variable\":\"\",\"multiinstance_condition\":\"\",\"isforcompensation\":\"false\",\"usertaskassignment\":{\"assignment\":{\"assignee\":\"admin\"}},\"formkeydefinition\":\"\",\"duedatedefinition\":\"\",\"prioritydefinition\":\"\",\"formproperties\":\"\",\"tasklisteners\":{\"taskListeners\":[]}},\"stencil\":{\"id\":\"UserTask\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-2AB7C01A-50EE-4AAC-8C8F-F6E1935B3DA7\"}],\"bounds\":{\"lowerRight\":{\"x\":201,\"y\":282},\"upperLeft\":{\"x\":105,\"y\":225}},\"dockers\":[]},{\"resourceId\":\"sid-C28BB5F6-013D-4570-B432-61B380C1F46F\",\"properties\":{\"overrideid\":\"sid-C28BB5F6-013D-4570-B432-61B380C1F46F\",\"name\":\"\",\"documentation\":\"\",\"asynchronousdefinition\":\"false\",\"exclusivedefinition\":\"false\",\"sequencefloworder\":\"\"},\"stencil\":{\"id\":\"ExclusiveGateway\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-53258502-43EE-4DE8-B1A4-DBD11922B8AF\"},{\"resourceId\":\"sid-72258A41-203E-428C-B71D-CA3506252D73\"}],\"bounds\":{\"lowerRight\":{\"x\":280,\"y\":280},\"upperLeft\":{\"x\":240,\"y\":240}},\"dockers\":[]},{\"resourceId\":\"audit2\",\"properties\":{\"overrideid\":\"audit2\",\"name\":\"é›†å›¢äººåŠ›èµ„æºéƒ¨éƒ¨é•¿å®¡æ ¸\",\"documentation\":\"\",\"asynchronousdefinition\":false,\"exclusivedefinition\":true,\"executionlisteners\":{\"executionListeners\":[]},\"multiinstance_type\":\"None\",\"multiinstance_cardinality\":\"\",\"multiinstance_collection\":\"\",\"multiinstance_variable\":\"\",\"multiinstance_condition\":\"\",\"isforcompensation\":\"false\",\"usertaskassignment\":{\"assignment\":{\"assignee\":\"admin\"}},\"formkeydefinition\":\"\",\"duedatedefinition\":\"\",\"prioritydefinition\":\"\",\"formproperties\":\"\",\"tasklisteners\":{\"taskListeners\":[]}},\"stencil\":{\"id\":\"UserTask\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-EF2F51BB-1D99-4F0B-ACF2-B6C1300A7D2B\"}],\"bounds\":{\"lowerRight\":{\"x\":310,\"y\":410},\"upperLeft\":{\"x\":210,\"y\":330}},\"dockers\":[]},{\"resourceId\":\"sid-ED46FE41-A0FD-496D-86DC-2C97AF5735F0\",\"properties\":{\"overrideid\":\"sid-ED46FE41-A0FD-496D-86DC-2C97AF5735F0\",\"name\":\"\",\"documentation\":\"\",\"asynchronousdefinition\":\"false\",\"exclusivedefinition\":\"false\",\"sequencefloworder\":\"\"},\"stencil\":{\"id\":\"ExclusiveGateway\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-A7589084-4623-4FEA-A774-00A70DDC1D20\"},{\"resourceId\":\"sid-FA618636-3708-4D0C-8514-29A4BB8BC926\"}],\"bounds\":{\"lowerRight\":{\"x\":385,\"y\":390},\"upperLeft\":{\"x\":345,\"y\":350}},\"dockers\":[]},{\"resourceId\":\"sid-EF2F51BB-1D99-4F0B-ACF2-B6C1300A7D2B\",\"properties\":{\"overrideid\":\"sid-EF2F51BB-1D99-4F0B-ACF2-B6C1300A7D2B\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-ED46FE41-A0FD-496D-86DC-2C97AF5735F0\"}],\"bounds\":{\"lowerRight\":{\"x\":345.1328125,\"y\":370},\"upperLeft\":{\"x\":310.21875,\"y\":370}},\"dockers\":[{\"x\":50,\"y\":40},{\"x\":20,\"y\":20}],\"target\":{\"resourceId\":\"sid-ED46FE41-A0FD-496D-86DC-2C97AF5735F0\"}},{\"resourceId\":\"audit3\",\"properties\":{\"overrideid\":\"audit3\",\"name\":\"é›†å›¢äººåŠ›èµ„æºéƒ¨åˆ†ç®¡é¢†å¯¼å®¡æ ¸\",\"documentation\":\"\",\"asynchronousdefinition\":false,\"exclusivedefinition\":true,\"executionlisteners\":{\"executionListeners\":[]},\"multiinstance_type\":\"None\",\"multiinstance_cardinality\":\"\",\"multiinstance_collection\":\"\",\"multiinstance_variable\":\"\",\"multiinstance_condition\":\"\",\"isforcompensation\":\"false\",\"usertaskassignment\":{\"assignment\":{\"assignee\":\"admin\"}},\"formkeydefinition\":\"\",\"duedatedefinition\":\"\",\"prioritydefinition\":\"\",\"formproperties\":\"\",\"tasklisteners\":{\"taskListeners\":[]}},\"stencil\":{\"id\":\"UserTask\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-3DBCD661-5720-4480-8156-748BE0275FEF\"}],\"bounds\":{\"lowerRight\":{\"x\":520,\"y\":410},\"upperLeft\":{\"x\":420,\"y\":330}},\"dockers\":[]},{\"resourceId\":\"sid-FE485B2D-9A23-4236-BD0D-D788CA6E30E4\",\"properties\":{\"overrideid\":\"sid-FE485B2D-9A23-4236-BD0D-D788CA6E30E4\",\"name\":\"\",\"documentation\":\"\",\"asynchronousdefinition\":\"false\",\"exclusivedefinition\":\"false\",\"sequencefloworder\":\"\"},\"stencil\":{\"id\":\"ExclusiveGateway\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-D44CAD43-0271-4920-A524-9B8533E52550\"},{\"resourceId\":\"sid-1525BFF4-3E9D-4D8A-BF80-1F63AFE16289\"}],\"bounds\":{\"lowerRight\":{\"x\":595,\"y\":390},\"upperLeft\":{\"x\":555,\"y\":350}},\"dockers\":[]},{\"resourceId\":\"sid-3DBCD661-5720-4480-8156-748BE0275FEF\",\"properties\":{\"overrideid\":\"sid-3DBCD661-5720-4480-8156-748BE0275FEF\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-FE485B2D-9A23-4236-BD0D-D788CA6E30E4\"}],\"bounds\":{\"lowerRight\":{\"x\":555.1328125,\"y\":370},\"upperLeft\":{\"x\":520.21875,\"y\":370}},\"dockers\":[{\"x\":50,\"y\":40},{\"x\":20,\"y\":20}],\"target\":{\"resourceId\":\"sid-FE485B2D-9A23-4236-BD0D-D788CA6E30E4\"}},{\"resourceId\":\"audit4\",\"properties\":{\"overrideid\":\"audit4\",\"name\":\"é›†å›¢æ€»ç»ç†å®¡æ‰¹\",\"documentation\":\"\",\"asynchronousdefinition\":false,\"exclusivedefinition\":true,\"executionlisteners\":{\"executionListeners\":[]},\"multiinstance_type\":\"None\",\"multiinstance_cardinality\":\"\",\"multiinstance_collection\":\"\",\"multiinstance_variable\":\"\",\"multiinstance_condition\":\"\",\"isforcompensation\":\"false\",\"usertaskassignment\":{\"assignment\":{\"assignee\":\"admin\"}},\"formkeydefinition\":\"\",\"duedatedefinition\":\"\",\"prioritydefinition\":\"\",\"formproperties\":\"\",\"tasklisteners\":{\"taskListeners\":[]}},\"stencil\":{\"id\":\"UserTask\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-02DB2AD9-1332-4198-AC8D-22A35169D15C\"}],\"bounds\":{\"lowerRight\":{\"x\":730,\"y\":410},\"upperLeft\":{\"x\":630,\"y\":330}},\"dockers\":[]},{\"resourceId\":\"sid-3F53B6BD-F8F3-496B-AC08-50630BD11477\",\"properties\":{\"overrideid\":\"sid-3F53B6BD-F8F3-496B-AC08-50630BD11477\",\"name\":\"\",\"documentation\":\"\",\"asynchronousdefinition\":\"false\",\"exclusivedefinition\":\"false\",\"sequencefloworder\":\"\"},\"stencil\":{\"id\":\"ExclusiveGateway\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-7D723190-1432-411D-A4A4-774225E54CD9\"},{\"resourceId\":\"sid-35CC8C6C-1067-4398-991C-CCF955115965\"}],\"bounds\":{\"lowerRight\":{\"x\":805,\"y\":390},\"upperLeft\":{\"x\":765,\"y\":350}},\"dockers\":[]},{\"resourceId\":\"apply_end\",\"properties\":{\"overrideid\":\"apply_end\",\"name\":\"è–ªé…¬æ¡£çº§å…‘ç°\",\"documentation\":\"\",\"asynchronousdefinition\":false,\"exclusivedefinition\":true,\"executionlisteners\":{\"executionListeners\":[]},\"multiinstance_type\":\"None\",\"multiinstance_cardinality\":\"\",\"multiinstance_collection\":\"\",\"multiinstance_variable\":\"\",\"multiinstance_condition\":\"\",\"isforcompensation\":\"false\",\"usertaskassignment\":{\"assignment\":{\"assignee\":\"admin\"}},\"formkeydefinition\":\"\",\"duedatedefinition\":\"\",\"prioritydefinition\":\"\",\"formproperties\":\"\",\"tasklisteners\":{\"taskListeners\":[]}},\"stencil\":{\"id\":\"UserTask\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-BDB0AAB2-7E50-4D35-80EE-CE0BECDD9F57\"}],\"bounds\":{\"lowerRight\":{\"x\":940,\"y\":410},\"upperLeft\":{\"x\":840,\"y\":330}},\"dockers\":[]},{\"resourceId\":\"sid-02DB2AD9-1332-4198-AC8D-22A35169D15C\",\"properties\":{\"overrideid\":\"sid-02DB2AD9-1332-4198-AC8D-22A35169D15C\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-3F53B6BD-F8F3-496B-AC08-50630BD11477\"}],\"bounds\":{\"lowerRight\":{\"x\":765.1328125,\"y\":370},\"upperLeft\":{\"x\":730.21875,\"y\":370}},\"dockers\":[{\"x\":50,\"y\":40},{\"x\":20,\"y\":20}],\"target\":{\"resourceId\":\"sid-3F53B6BD-F8F3-496B-AC08-50630BD11477\"}},{\"resourceId\":\"sid-2AB7C01A-50EE-4AAC-8C8F-F6E1935B3DA7\",\"properties\":{\"overrideid\":\"sid-2AB7C01A-50EE-4AAC-8C8F-F6E1935B3DA7\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-C28BB5F6-013D-4570-B432-61B380C1F46F\"}],\"bounds\":{\"lowerRight\":{\"x\":241.44715254815893,\"y\":258.8729578650751},\"upperLeft\":{\"x\":201.64659745184107,\"y\":256.4551671349249}},\"dockers\":[{\"x\":48,\"y\":28.5},{\"x\":20,\"y\":20}],\"target\":{\"resourceId\":\"sid-C28BB5F6-013D-4570-B432-61B380C1F46F\"}},{\"resourceId\":\"sid-7D723190-1432-411D-A4A4-774225E54CD9\",\"properties\":{\"overrideid\":\"sid-7D723190-1432-411D-A4A4-774225E54CD9\",\"name\":\"æ˜¯\",\"documentation\":\"\",\"conditionsequenceflow\":\"${pass==1}\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"apply_end\"}],\"bounds\":{\"lowerRight\":{\"x\":839.78125,\"y\":370},\"upperLeft\":{\"x\":804.8671875,\"y\":370}},\"dockers\":[{\"x\":20,\"y\":20},{\"x\":50,\"y\":40}],\"target\":{\"resourceId\":\"apply_end\"}},{\"resourceId\":\"sid-D44CAD43-0271-4920-A524-9B8533E52550\",\"properties\":{\"overrideid\":\"sid-D44CAD43-0271-4920-A524-9B8533E52550\",\"name\":\"æ˜¯\",\"documentation\":\"\",\"conditionsequenceflow\":\"${pass==1}\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"audit4\"}],\"bounds\":{\"lowerRight\":{\"x\":629.78125,\"y\":370},\"upperLeft\":{\"x\":594.8671875,\"y\":370}},\"dockers\":[{\"x\":20,\"y\":20},{\"x\":50,\"y\":40}],\"target\":{\"resourceId\":\"audit4\"}},{\"resourceId\":\"sid-53258502-43EE-4DE8-B1A4-DBD11922B8AF\",\"properties\":{\"overrideid\":\"sid-53258502-43EE-4DE8-B1A4-DBD11922B8AF\",\"name\":\"å¦\",\"documentation\":\"\",\"conditionsequenceflow\":\"${pass==0}\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"modify\"}],\"bounds\":{\"lowerRight\":{\"x\":260,\"y\":239.5},\"upperLeft\":{\"x\":260,\"y\":193.5}},\"dockers\":[{\"x\":20,\"y\":20},{\"x\":51,\"y\":29}],\"target\":{\"resourceId\":\"modify\"}},{\"resourceId\":\"sid-5FED02D6-C388-48C6-870E-097DB2131EA0\",\"properties\":{\"overrideid\":\"sid-5FED02D6-C388-48C6-870E-097DB2131EA0\",\"name\":\"\",\"documentation\":\"\",\"asynchronousdefinition\":\"false\",\"exclusivedefinition\":\"false\",\"sequencefloworder\":\"\"},\"stencil\":{\"id\":\"ExclusiveGateway\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-8448EF4A-B62E-4899-ABC2-0E2DB2AE6838\"},{\"resourceId\":\"sid-44AFB9C1-4057-4C48-B1F2-1EC897A52CB7\"}],\"bounds\":{\"lowerRight\":{\"x\":280,\"y\":85},\"upperLeft\":{\"x\":240,\"y\":45}},\"dockers\":[]},{\"resourceId\":\"sid-163DBC60-DBC9-438B-971A-67738FB7715A\",\"properties\":{\"overrideid\":\"sid-163DBC60-DBC9-438B-971A-67738FB7715A\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-5FED02D6-C388-48C6-870E-097DB2131EA0\"}],\"bounds\":{\"lowerRight\":{\"x\":260,\"y\":134.3828125},\"upperLeft\":{\"x\":260,\"y\":84.5625}},\"dockers\":[{\"x\":51,\"y\":29},{\"x\":20,\"y\":20}],\"target\":{\"resourceId\":\"sid-5FED02D6-C388-48C6-870E-097DB2131EA0\"}},{\"resourceId\":\"sid-72258A41-203E-428C-B71D-CA3506252D73\",\"properties\":{\"overrideid\":\"sid-72258A41-203E-428C-B71D-CA3506252D73\",\"name\":\"æ˜¯\",\"documentation\":\"\",\"conditionsequenceflow\":\"${pass==1}\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"audit2\"}],\"bounds\":{\"lowerRight\":{\"x\":260,\"y\":329.46875},\"upperLeft\":{\"x\":260,\"y\":279.90625}},\"dockers\":[{\"x\":20,\"y\":20},{\"x\":50,\"y\":40}],\"target\":{\"resourceId\":\"audit2\"}},{\"resourceId\":\"sid-8448EF4A-B62E-4899-ABC2-0E2DB2AE6838\",\"properties\":{\"overrideid\":\"sid-8448EF4A-B62E-4899-ABC2-0E2DB2AE6838\",\"name\":\"é‡æ–°ç”³è¯·\",\"documentation\":\"\",\"conditionsequenceflow\":\"${pass==1}\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"audit\"}],\"bounds\":{\"lowerRight\":{\"x\":239.7734375,\"y\":224.51953125},\"upperLeft\":{\"x\":153,\"y\":65}},\"dockers\":[{\"x\":20,\"y\":20},{\"x\":153,\"y\":65},{\"x\":48,\"y\":28.5}],\"target\":{\"resourceId\":\"audit\"}},{\"resourceId\":\"sid-A7589084-4623-4FEA-A774-00A70DDC1D20\",\"properties\":{\"overrideid\":\"sid-A7589084-4623-4FEA-A774-00A70DDC1D20\",\"name\":\"æ˜¯\",\"documentation\":\"\",\"conditionsequenceflow\":\"${pass==1}\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"audit3\"}],\"bounds\":{\"lowerRight\":{\"x\":419.78125,\"y\":370},\"upperLeft\":{\"x\":384.8671875,\"y\":370}},\"dockers\":[{\"x\":20,\"y\":20},{\"x\":50,\"y\":40}],\"target\":{\"resourceId\":\"audit3\"}},{\"resourceId\":\"sid-FA618636-3708-4D0C-8514-29A4BB8BC926\",\"properties\":{\"overrideid\":\"sid-FA618636-3708-4D0C-8514-29A4BB8BC926\",\"name\":\"å¦\",\"documentation\":\"\",\"conditionsequenceflow\":\"${pass==0}\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"modify\"}],\"bounds\":{\"lowerRight\":{\"x\":365,\"y\":349.6875},\"upperLeft\":{\"x\":311.859375,\"y\":164}},\"dockers\":[{\"x\":20,\"y\":20},{\"x\":365,\"y\":164},{\"x\":51,\"y\":29}],\"target\":{\"resourceId\":\"modify\"}},{\"resourceId\":\"sid-1525BFF4-3E9D-4D8A-BF80-1F63AFE16289\",\"properties\":{\"overrideid\":\"sid-1525BFF4-3E9D-4D8A-BF80-1F63AFE16289\",\"name\":\"å¦\",\"documentation\":\"\",\"conditionsequenceflow\":\"${pass==0}\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"modify\"}],\"bounds\":{\"lowerRight\":{\"x\":575,\"y\":349.6875},\"upperLeft\":{\"x\":311.44921875,\"y\":164}},\"dockers\":[{\"x\":20,\"y\":20},{\"x\":575,\"y\":164},{\"x\":51,\"y\":29}],\"target\":{\"resourceId\":\"modify\"}},{\"resourceId\":\"sid-35CC8C6C-1067-4398-991C-CCF955115965\",\"properties\":{\"overrideid\":\"sid-35CC8C6C-1067-4398-991C-CCF955115965\",\"name\":\"å¦\",\"documentation\":\"\",\"conditionsequenceflow\":\"${pass==0}\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"modify\"}],\"bounds\":{\"lowerRight\":{\"x\":785,\"y\":349.6875},\"upperLeft\":{\"x\":311.244140625,\"y\":164}},\"dockers\":[{\"x\":20,\"y\":20},{\"x\":785,\"y\":164},{\"x\":51,\"y\":29}],\"target\":{\"resourceId\":\"modify\"}},{\"resourceId\":\"sid-BDB0AAB2-7E50-4D35-80EE-CE0BECDD9F57\",\"properties\":{\"overrideid\":\"sid-BDB0AAB2-7E50-4D35-80EE-CE0BECDD9F57\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"end\"}],\"bounds\":{\"lowerRight\":{\"x\":974.078125,\"y\":370},\"upperLeft\":{\"x\":940.5,\"y\":370}},\"dockers\":[{\"x\":50,\"y\":40},{\"x\":14,\"y\":14}],\"target\":{\"resourceId\":\"end\"}},{\"resourceId\":\"sid-44AFB9C1-4057-4C48-B1F2-1EC897A52CB7\",\"properties\":{\"overrideid\":\"sid-44AFB9C1-4057-4C48-B1F2-1EC897A52CB7\",\"name\":\"é”€æ¯\",\"documentation\":\"\",\"conditionsequenceflow\":\"${pass==0}\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"end\"}],\"bounds\":{\"lowerRight\":{\"x\":989,\"y\":355.89453125},\"upperLeft\":{\"x\":280.2216796875,\"y\":65}},\"dockers\":[{\"x\":20,\"y\":20},{\"x\":989,\"y\":65},{\"x\":14,\"y\":14}],\"target\":{\"resourceId\":\"end\"}},{\"resourceId\":\"sid-36E50C8B-6C7C-4968-B02D-EBAA425BF4BE\",\"properties\":{\"overrideid\":\"sid-36E50C8B-6C7C-4968-B02D-EBAA425BF4BE\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"audit\"}],\"bounds\":{\"lowerRight\":{\"x\":104.83832369973481,\"y\":256},\"upperLeft\":{\"x\":60.49976831931648,\"y\":255.3340467200345}},\"dockers\":[{\"x\":15,\"y\":15},{\"x\":91.4000015258789,\"y\":256},{\"x\":48,\"y\":28.5}],\"target\":{\"resourceId\":\"audit\"}}],\"bounds\":{\"lowerRight\":{\"x\":1200,\"y\":1050},\"upperLeft\":{\"x\":0,\"y\":0}},\"stencilset\":{\"url\":\"stencilsets/bpmn2.0/bpmn2.0.json\",\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\"},\"ssextensions\":[]}',NULL),('62501',3,'source-extra',NULL,_binary '‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0\0\Ì\0\0\0™_Ğ³\0\0\0 cHRM\0\0z&\0\0€„\0\0ú\0\0\0€\è\0\0u0\0\0\ê`\0\0:˜\0\0pœºQ<\0\0\0gAMA\0\0±|ûQ“\0\0\0sRGB\0®\Î\é\0\0\0bKGD\0ÿ\0ÿ\0ÿ ½§“\0\0\0	pHYs\0\0\Ä\0\0\Ä•+\0\0 \0IDATx\Ú\ì\İ	xT\Õıÿñ3“… û¾ˆQYT\\\Ë\ßb‹¢¨EE ŠVjkµˆ±ŠşXA\êA…„Ek•*²*P!D ABB6²\ÌıŸ\ïÍ½ñfHB¶\Éú~=\Ï\á\Îrg\æ\æÌœó™s\ÎU\n\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0¨!ÆŒÓŒZ\0\0\0\0PY\ÜT\01eÊ”QYYY\É\á\áá£©\r\0\0\0\0•Á*\0ğô\ÓO‡>|ø½ë¯¿\Şı\ÇÁƒ\Ç}ıõ\×\Ñ\Ô\0\0\0€Š t@}¨\Ët«\Ë]Ò½\î—Q@Ctù±ˆ\Ç\ér.‰\Å<÷Õº\\¡\Ë\Å\Ü\ß\Î\Úf{\İ>S—5U]“\'O\Z¿xÂ„	~7\Şx£\êÚµ«kİºu·0\à›6m\Ú]E‡ñ[]\Òt\Éòº­—.û‹Ø¿ƒ.}u‰s\Ü\ÖJ—«~\Óøˆ\0\0\0„@U‘ \àB]:\ë2^—«t9¢\Ë\n]Rt\Éó\n$$txP—/t¡\Ën\ëq­u	³ö/.T\è­K°\Ê3Š\"ë•º¼¯\Ëÿ\Ó\åb]~£\Ëp]~Ñ¥§.÷\êò¹¯+EF8$$$DJ\àpé¥—š·µm\ÛVu\ë\ÖÍ½~ıúÛ®»îºª\n\æ\éò©.™º\Üj½\'=¬\ßQ\'¬û?²ö¦Kw]\âu¹D—	\ÖcWYû\Î\Õ\å>ò\0\0\0\0¡P¤c..]t\édun©üo\Ì\ï\Ö\åJ]6Zû_§\Ë(]>\Óe´@\ĞeŒ.\ÓuY_D\à°\È\n$<¸\Üz@\ëúk\ÖsÙ£)<º4\Ôe›2\ÈsÉ·ùƒty\Ñ\n6ZûùŒ¬\İ·püøñn;p°µi\Ó\Æ6l\Øp\Ûoû\Û\İ[¶lùÑ‡‡\ÒD\å*I\Ğ\å>]\"U~\à²Áºÿ\Z]^Vù£\Ä\rº¼§\Ë\ÏV½İ¬\Ë\'ºü^\å òC\0\0\0\0„€\Ï\ÅYş•?]¢…\ÕY• @F,|e]¶IÀğG]>Tù\Ãû§[¿½„\Î\ì0+0\çm¤K’µ¯\\¿\É\nœöªü\éş\Öóvµö“¢.©Ê‡S$pˆ]ğğ\Ã»/¹\ä’\"÷‘İ»wwù\å—wô\é\Óg\×Î;÷ú\èpdT‡ŒdØª\Ë\×*D\Ê]\ÚX¿£6©ü\Ñ#û¬ı%d”DB£ş*\Ô\ÃÿT~\È#\Ïq.kUş4˜,>ş\0\0\0\0¡\àk\×\ë2T\åtõZªü)xZ¡ \Ãû“Uş\è!½ò\Ö\Öõ\ï¬\ïCV7\Óz\Ü_Uş\ZEt\äd-ù\æ^¦V\Èt\0ù†?ÅºOÂŠŸ¬\Ë\Òe³\ãù+3p!CŸ>}J\ÜWF<t\ï\Ş\İ\ï›o¾¹£oß¾;v\îÜ¹¿’\Çm\Õ÷¥\Ö{r³$µ\ê%Íº}¸U_Bz–\Êv$¬Kˆ.¯\ëb¨üò\Ëô‹“|ô\0\0€\ê\Ã)3Q_H°\ào\Ëş^S²/S%\ŞVù£dj\Ä*ø¾Œv8duhQù\ë\Ø~±ö‘²E—\ë	ı\äùUş”Šx+Œ\èjİ·\ÆÑ©\êõü•b\âÄ‰·<xĞœRq¶ÀÁÖ«W/õ\Øcz<aaa7Vò!«\Ë]\ÚZ¡\ÂÿY·7¶‚„\ërº\×\ï­.rÂªÏ»­\0BB¢ó­÷\n\0\0\0\0¡P%¶Y!€\ßY—wªü\éN7XW	¤“nu}E¹\\\ë(2½ ¹²_DF8¤¤¤,w.\ZYZ<L<9À\ß\ßÅˆ#n©\Ä\Ã:¥ò§Wt²\êú)\ëö5\ê\×\Ğ\ÆûŒoXAƒ™>!\Ófd“\Öşuùw\0\0\0€\Ğ¨*\ÒAm­~ı&üJ\ë²,0y\Øk\ßuV\Ç?\Ö*¯¨ü‘±%<{\ëù¤\\n½†}½u	\íONûb]Î©\ì~Ê”)£bcc\ß-O\à\à}ôÑ ÀÀÀe•<Èº2ªDF˜¬\Ö\åŸ\Ö\í78\ê\ï¯\Çt´¶2BF@|j]oaÕ½Œj9\ÂG\0\0\0 t\0ª‚L¡hk…	{¬\á€uY¾E¿\Ûk9%\æ6\ëòV§X{ü¤„×¸K?½\âú\"ö—õdZAŒu|!\â_Ù\Ã\áÃ‡\Ê\Z\å\rl2%cò\äÉ•<8õµ.—4\ÒA\ŞY\ë!\×\ÚÊ©5%¨‘…;\å¬/ğ‘\0\0\0€ª\"\Óe*=™\ç\Ü*‡¬¬\éÿ­µo Uş\ëh\ê\ÒY\åO\É­Šx’¾Y/\êÔ—\èa\Û	G[Ê±\ÊúÁeJ…8”v\r‡³±¦Z,­\Ä5¤\Î_R¿.\è\é\Ô\ÈqYyÍ‰\Öu	ˆö[—u0$’¨{\0\0\0 úùS¨\ãZXB®\ã¶\ÆV\'u°£Sû;•¿°\ã)«\Ó*\ë,tUù§µ¼J—\Ç_|–\×t«³zò~†\×mrŠ\ÈIª\èP£\Ì\ìE#\'L˜P8¤¤¤¨x\àŒ}\çÎ«Zµúõe322Ô½÷\Ş{\Æ~ó\ç\ÏWÍ›77ƒ‡\Ç<xúô\é„……\rŒŒ\\]ÁÃ•PhƒU\'\Çû%\Ó\\^uÔ¿Ô«LA‘5\æX\ï\ã@]šªü3Š	m\æ\ë\"?(\Ó,\0\0\0€j\ä¢\n€³\Ş#­0¢8g»ß›¿*ŠT˜Œp°‡3\Öpøê«¯\ÔÂ…\Í`Áü\İnuÁ\È4Œ‚}\æÌ™£¶oß®rrr\Ì\ë\çœsºù\æ›\Í\â´s\çN5cÆŒô\Ü\Ü\Ü\Ğ%K–|\ì\Ã:¯Œ}\0\0\0T1?ª\0(‘Q\ÄmygyL^_£R;\Í\á\á\á£ccc#Š[4²K—.jË–-*99ÿ†a¨´´4Õ¡CÕ¾}{µw\ï^µb\Å\nuúôió~sÄ¸q\ãÌ€Â©mÛ¶\ê¼ó\ÎS\ß~û\í^x\á®={ö\ìóQWÆ¾\0\0\0\0\0”—8,5.¹\ä’b÷»ğ\Â\Õ\×_]0’!77W}ÿı÷\êw¿ûš6mšJMM-Ø·q\ã\ÆJ?ŸjÑ¢E‘\ÏÕ¦M?;x¸øâ‹·\Ç\Ä\Äüw\0\0\0€ t\0êˆ§Ÿ~:,..®T‹FJ­bcc\ÍÀAx<­\Í\Ë\"$$D]s\Í5\êº\ë®+ñù$x\èŞ½{Ş·\ß~{Û…^¸§’F<\0\0\0\0¨\å€:`ò\ä\ÉC\ã\ã\ãË”Š’F88\É:_|ñ…\Ê\Ì\Ì4¯K\Ğ #òò~Ò¨Q#5i\Ò$p\Ö\çkÛ¶m@·n\İò6m\Ú$S-v<\0\0\0\0 t\0j9\á`E­\áPYŸ¡{÷\î\æúö4§fÍš™g°5 J«]»v\çw^Î·\ß~;”\à\0\0\0\0g¯€O…††vÕ›1Ô„o´o\ß>%99y\Öøñ\ã\İe	œ^~ùeµyó\æ‚)B	Y÷á©§*\×sFGG\'MŸ>]\Î\â±\Ê0\Öx\0\0\0@}[p\Ş{ø\Ø]¦R\r¾qô\èQu\ÓM7©òbÔ¨Q\ê»\ï¾+t›œ\Ñ\âö\Ûo/÷sö\íÛ·y‡R><’w	\0\0\00=ShBT¥pª ru\î\ÜùÔ†\r¦÷\é\Óç¬‹Ggñ\â\Å\Ê\år™AƒMF:¬Zµ\Ê\íP;v\ì8v\äÈ‘Fúy1\Ò\0\0\0õX½ÿ–\ĞU%<**\êª¡ò=ù\ä“?Ï=;ò¡‡*ó‹½{÷ª]»všZ!d1\ÉC‡©­[·ª~ıú•\é9%p˜1cF°~Î°%K–|\Ì;\0\0€ú*44T6õ:xpó1\0j·iÓ¦-=\çœs†Î›7/o\çÎ¥~œ¯¼òŠJKK+¸MF<\ØN<©\Şy\ç•‘‘Q\ê\çÜºu«8Œ$p\0\0\0\0@\è\0\Ôs\æ\ÌYÙ¬Y³;õ6oûö\í¥zÌ‡~X(P\Óbv\îÜ¹\Ğ\é1\å¬K—.-\Õó\íÛ·/~ö\ìÙ\0\0\0\0l„@!ÁC£F†Í;7\çlÁCBB‚Z½zu¡\Ğ!88\Ø<[EHHHÁm§NR›6mR(ñùdJExxxS\Ã0\Æ8\0\0\0\0°:\0u\ÈüùóWdff~ùå—³K\n^{\í5•]p½aÃ†j\ìØ±f\à0~üxTpŸ~>s™Q{\r\Ã0FFFF®\ä]\0\0\0\0`#t\0ê˜¥K—®\Í\É\É2oŞ¼¬¢‚‡7ªÃ‡«\Ü\ÜÜ‚Ûºté¢®¼òJórÏ=U\ïŞ½\Í3X	\'\Ò\Ó\ÓÕš5k\Îx.\ç\Z\0\0\0\0¼:\0uPdd\ä\êÓ§O\ß>gÎœ,\ç\â’)))2\Z\Âœ\î¿ÿşB\×ÇW\èŒIII\ê\İw\ß5·6\áğ\ÒK/\ë‹#˜R\0\0\0 (œ2¨£$x>|ø­³f\Íú÷Ä‰ƒ\ätšÍš5“\Û\ÏúX™nQ\Ò~2\ÂÁ\äu¨m\0\0\0\0Ea¤P‡\ÉT‹Ó§O\ß*#¶mÛ–W\Ï)ƒ}–\n\0\0\0\0%!t\0\ê8Gğpº¢Áƒ=¥\"//\ï¦T\0\0\0\08B \à!//o¨\Ñ\Ñ\Ñ\å\n\ìE#k8\0\0\0\0(%B ©<Ìœ9S¦Zd—\å±\ÎE#™R\0\0\0 ´€zDƒœœœ\ÛfÏS\Ú\àA\Çi1	\0\0\0\0”\Z¡P\Ï\ÈT	^z\é¥\ì­[·¦—´\ï¾}û\âgÎœ¢/fJ\0\0\0€²\"t\0\ê!	\Ã6{öl\×ö\í\ÛS‹\ÚG\Öp\Òû\É‡•\Ô\Z\0\0\0€²\"t\0\ê)™*‘››;dÖ¬Y®-[¶\nœg© p\0\0\0\0P^„@=&#rss\ï˜3gkÕªU©\Ï=÷œúøã“\ì5˜R\0\0\0 \"€z\Î¢¢¢\Z\ïÙ³G-Z´¨\0\0\0€\Ê@\è\0@5l\Øp£\ã÷;88x-µ\0\0\0 ¢\0¨¬¬¬«œ\×OŸ>=€Z\0\0\0PQ„\0”\Ë\åú“óºa÷R+\0\0\0\0*Š\Ğ€òx<7y\İ4`Ø°a©\0\0\0\0A\è\0\Ôsaaa7º\\®V›rui\é\ç\çw#µ\0\0\0 \"€z\Î\ãñòº\Éem‡P;\0\0\0\0*‚\Ğ¨\Ç\à¯7÷y\İ\ìgmo3fLµ\0\0\0 ¼€z¬]»vW¹\\®3\Ön0#[o\ZŸ>}š)\0\0\0\0Ê\Ğ¨\ÇÃ¸¿¨\Û].—Ÿu?S,\0\0\0\0”¡PO\É\Ô\n\Ã0Š\É`‡2Å¢µ\0\0\0 <€zªu\ë\Ö¼\ÎZ\á-S\ß\ß,33³?µ\0\0\0 <€ú\Úø\İ\î³M0\Ïb\ár¹FS[\0\0\0\0\Ê\Õ\ï \n€zë³\Üo¹\Â0Œ\ÖY.\0\0\0\0 L€zhøğ\áõ¦Iiö•)mÚ´¹“Z\0\0\0PV„@=\är¹ú•qÿ¨5\0\0\0\0eÅi Z²dÉ‹zó¢ó¶\Ğ\Ğ\Ğgôfª.\áQQQ\ÏPK\0\0\0\0*Š‘\0\0\0\0\0À\'\0\0\0\0\0€O:\0\0\0\0\0\0Ÿ t\0\0\0\0\0\0>A\è\0\0\0\0\0\0|‚\Ğ\0\0\0\0\0ø¡\0\0\0\0\0ğ	B\0\0\0\0\0\à„\0\0\0\0\0À\'\0\0\0\0\0€O:\0\0\0\0\0\0Ÿ t\0\0\0\0\0\0>A\è\0\0\0\0\0\0|‚\Ğ\0\0\0\0\0ø¡\0\0\0\0\0ğ	B\0\0\0\0\0\à„\0\0\0\0\0À\'\0\0\0\0\0€O:\0\0\0\0\0\0Ÿ t\0\0\0\0\0\0>A\è\0\0\0\0\0\0|‚\Ğ\0\0\0\0\0ø¡\0\0\0\0\0ğ	B\0\0\0\0\0\à„\0\0\0\0\0À\'\0\0\0\0\0€O:\0\0\0\0\0\0Ÿ t\0\0\0\0\0\0>A\è\0\0\0\0\0\0|‚\Ğ\0\0\0\0\0ø¡\0\0\0\0\0ğ	B\0\0\0\0\0\à„\0\0\0\0\0À\'\0\0\0\0\0€OøS@\å3#\è\àÁƒ\ã\Ó\Ò\ÒFeddôHOOòx<5ú˜õ±ª}ûö©ó\Ï?\ê½÷\Ş;µ&«\Ë\å2\Z4hp\"00psNN\Î\Ìşıû¯\çS\0\0\0\Ô<„@%;xğ\à_¶n\İ:+++«aÛ¶m•”\åv\×üEƒ®u\ìñx\\iii-SSS\'$$üê«¯~\È\Ì\Ì5hĞ >\0\0\0@\ÍÁô\n \Å\ÅÅ­<t\èĞ«-Z´hxùå—«Î;«¦M›ÖŠÀ¡Vı\â\ÒõÙ¤IÕ±cGÕ¯_¿ÀN:õ‘Q\ëÖ­B\í\0\0\0\05\èowª\0¨8\è2ä¢‹.R:t)\0TJiß¾½\êÓ§O°\Û\í~o\íÚµ·P#\0\0\0@\Í@\è\0T™Rq\àÀ!=zô0¿÷v:\í˜:ıÚ·şYµw\İ3*nû;*+õW‰\Z5j¤z÷\î\âr¹\Şû\ì³\Ï.¦F\0\0\0€\êG\è\0T,\Z™˜˜8K¦R8ü¼}JKÜ«<y\Ù\ÊğäªŒ¤ŸTÜ¶·	*™]»võøùùÍ¥6\0\0\0€\êG\è\0Tœ¥B”!şEI:ôµ\Ê\ÍN?\ãv	 n +YÇ›ûûûÿ\æó\Ï?¿Š\Ú\0\0\0\0ª¡PArZL9CEqk8d–0šAFA òµn\İ:Ao†R\0\0\0@õ\"t\0*(##£‡œ¡¢8F^Nñ6<T ´lÙ²ƒ\Ş &\0\0\0€\ê\åO\0“BE\Ô úıh§7­¨	\0\0\0 z1\Ò¨ Ç£\ÜnšRúÅ–ÿ~ª\0\0\0\Õı·9U\0øV@ğ9\Å\Ş\çß€\0\0\0\0\ê.BÀÇšw¾Fù6:\ãv¿€`Õ¼K*\0\0\0@E\è\0øX\ãV=U£–=Š¼=¤õET\0\0\0€:‹\Ğ¨y9™gÜ–{ú\0\0\0 N#t\0|(;ı¸Š\Ûö¶J;ş\Ã÷¥ŸØ¯â¶¾i\î\0\0\0\0u¡\à#\'nWq\Û\ŞR\É‹\İ\'#åŠ\İüªJûF†‡J\0\0\0P§pJ9 ’\ÉÈ…cû>1G2”†\'/\Û\Üÿ\ÔñT\ëó« vT\"\0\0\0€:‘@%“‘¥\rœdDÄ¡-¯S\0\0\0\0\êB ’\ÉÈ…ò2<¹T \0\0\0€:ƒ\é@%»`\àsT\0\0\0\0(F:\0\0\0\0\0\0!t\0\0\0\0\0\0>A\è\0\0Ã‡344ô\'j\0\0\0¨8BÀG¾ú\ê+µnİºB\å7\ŞP\'Nœ¨´\×x\æ™g\n.ÏŸ?_\å\æ}!\Ê\ì\ìluøğauüøñ‚\Û>ù\äóv\Ûk¯½¦ö\ï/|÷\Ş{O<x°\È\çLNNV}ôQ©[öŸ7o^Áõ­[·ªeË–•\ég—cLKK«p0 ±4$\éb¸\İ\îûô\Í\çò	\0\0\0*…$Y¸p¡zû\í·İ¶h\Ñ\"uÿı÷ºM:Î§OŸ.öy^~ùe¦zö\ìi^—ğ\â¼ó\Î3/ÿø\ã*..Î¼,Û£Gª„„µj\Õ*õüóÏ«Ÿ~úI\íÙ³GEGG«-Z¨\îİ»«M›6©K/½T<yRy<u\Ë-·¨½{÷ªÁƒ¼\æ?ü F\Ze^^½zµºñ\ÆÕ†\rÔ°a\Ã\Ì\×	/ô³EEE©N:©ŒŒõŸÿü\Ç|üW\\a\Ş7}út3P×·I@Ñ£G‚\ërÿ¤I“\n\êCJÛ¶m\Í\ëÎF³]»vªM›6jÍš5\ê/ùK¹ƒı<\á†aÈ›Ñ˜O+\0\0\0@\è\0\Ô:\Î\ÑB:ù\ŞRRR”¿¿¿j\Ü8¿\ï»b\Å\n¨n¾ùfóú\ã?n\Şoûı\ïone\ÔÄ AƒT\çÎ\Í\ëø\Ã\Ì\ËR\ì»„\Z5R_|ñ…\nHxpñ\Å«\ï¿ÿŞ¼_n=z´\n.òø%<Ò°aCóº\Û\íVK—.US§N-\ØGFW8p@=ğÀjûö\íf0!Aƒ¼–Œ–\ĞüBƒØ¸q£š6mšY¿üò‹YDÈ±Ï=\Ûf†jî›““SP7rÜ²\ï\æÍ›\Õøñ\ãOÂ–ö\íÛŸõı[¨_SÒ•–†ağ\0\0\0€\ÚI¾É—Îµ“„\0\Ş:v\ìX\èzPPjĞ AAG\ÛÛ—_~©233\ÍNú_ÿúWõè£š#N:e\Şcw\ß}·¹¿t\Î%\àb\åÊ•\êĞ¡Cª[·n…‚\Ä\ÄÄ‚Î»ŒŠŸ}ö™jÚ´©\Ùù_°`ùxEqù\å—B¦]<ñ\Äæ”‰\ç{N½ù\æ›\êØ±c\æ}2\â\É\'Ÿ,tüD\È1~÷\İw\æó\Ék¼şú\ë\æô…!\á\Åm·\İV°¿ 	G¤~$˜‘\ã’Q\ê\Ö[oU#FŒ(Îˆûî»¯MZZ\Ú}q´.%hp¹\\|8\0\0\0B ö’x‡\Î\éĞ¯_?u\Çw¨?ü°\Ü\Ï}õ\ÕW«9s\æ¨\åË—›#$p‘²†„L\Åø\ãÿXh9V­Z™ÛªO?ıT­]»V\å\å\å™\Ó!¤\Ão¯\ï £¤\Èóşù\æ”\é\Ø_u\ÕU\æ\Ô	$¤cÿ\ç?ÿ\Ùd”Â¿ÿıoÕ¤IsÚ‡„2ªA‚™†q\ä\È3 0\ãé§Ÿ6_C®‡††š\Ïs\Î9\ç˜\áL§iñññªoß¾f\"£*\Ä\îİ»B	[ö\íÛ§š5kf¤òóŒ9²\Ğ\Ï,õ+SL´„òÖ³>\Æ\Ø\Zü“Ÿ+‹–”Ÿ\Ë\åŠ6#…š\0*$\"***–j\0@\è\0T1Yğ›\ßüFõ\ï\ß\ß¤SoO\Î{yIH AVV–\Ùy—ÿü\ç?Õ!C”|‹o‡ò\Z²„Œ\n!!!\æVÖ\à@¶rŸ\\–\Ç\Ê›,2)¡,\î(|û¸\íN¾ø\àƒ\Ìm\ëÖ­\ÍğC®\Ë\È\n¹.„2*añ\â\Å\æû±²&\Ã\í·\ßn\Â\Ï\Ï\Ï+d”„L#™9s¦JOO7×“0E\êğ…^0Gt{ÔƒL+‘c–0$55õŒz’ ¤t©Á±.´2 bô\ï\Ì\ßQ@¥x†*\0@\è\0T£Ÿş¹ \Ó_Q²¶‚,ø(£z\è!s\í }Y\ÌQ¦sÌ˜1CM<\Ù\\û@:\é2b\ìØ±\æYA¦^\È	{™Ê°~ıú‚×D\È\Ú\ŞaEÂ‰€€\0Õ»wo3`‘Q2\â\ã?VúÓŸ\Ìõ&L˜`\îk/i‡2\ÚBÖ¨‘2Œ3\ÆNœ#$˜ğÆ›<¿Ô«,Š\éM¦[X#*\âP\r\ä\Øbi]@¹\ØaÃ—ºl :€r™J\0 t\0ª‘t¦mr\Ö\éPW9m¥,\î(‹7\ÊdqEp\å•Wšx\å`/6)dê…Œ<\"g~…e4Á5\×\\ci\ÂIFR´l\ÙÒœ¾ $,©\"$EÂq\ãÆ™AˆL±E#_}õUs\Z‡œ†S‚y\âHX!¡…ŒÜ\r/¾ø¢¹„Œ\Ş(nM›Œ&y\å•W\n-ji“),òs,Y²¤\í©S§\æ¸\İ\î\ë\r\ÃhQ–zŠŠ\êZ\Ó>W¡¡¡\ÏX\è\Ép\Öghi@…\Ú\Ñ\ÚP\îvDğ\0€\Ğ¨N²8¤|«/\Ó¤C-[\é\\K§üÁ¬\Ğs\Ë\È	dÄ‚ŒV¸ö\Úk\Õ7\ß|cN5(Š\röôY;AF\0\ÈB\Ş\ë ™ª 2\ÊóÙ¯e/H\éYpÿ;\Ì\Ë2¢AN\Ï)k9\È\Ï.k6Èˆ\n{‘\Ê\â\È:2B\ã\ÙgŸ5\ÃyN}!¯\á;dÊ‡L‘\Å8etC¯^½Ì‘Î…&½½õ\Ö[²¢e˜\\–\Ód¶i\Óf±¾x­\Ë\åjÊ§\0\0\0 t\0j%\é\ä\ËÂˆ=ö˜ú¿ÿû?sqF™\n \Ód-™Š ¬õ %:I\Ç\İy\nMYŒQ\ÖL\éòX\İ Á…ŒBr\êL\é\Ø\ÛS¼\É\ã\í\é€\È(\ç\Ù\'„½„³/£d\İ™¦\á\Í>õ§L‹˜2eŠ¹ö\Â]w\İeœ³i\ÈY)d‚œY\â\Î;\ïTs\ç\Î=\ã,2eB\ê@‡®]»š§É”ŸEÖ›X¶l™¹È¤8yò¤º÷\Ş{\Í)2\åC\È\Ïó\ÓO?™§×”o[d\İ\ï\ÓrzÛ°aCš\Ş¬²©÷Š\Ë\å\n5£¹¾\Ê)-\0\0\0\0B æ“º¬\ã A€œM\Â¤£/gUÛ¶o\ßnvº\å›ü+®¸¢\ĞT\ç\Ô›=\Ú@\ÂY\'AF\"\Èt	!v»3.\ì³>\Ød„=\ÒA¦$\ÈY\'dJ„˜5k–¹õ\Ê ûÉ´\rQ \Ó3¼\íÚµ\Ë\ÜJ˜\"§Ä”\Ñ²À¥,)?›	$|5(d\n†M^S\Â		B\ä4 r\Ür\êM\ï×“ç·CFQ\Èh	9\ìŸ\é‘G1GHH(SÒ¨ozÿñz3\Ş\n ¦†ñWF@\0\0\0\0„@&!BqS\Ä\Ã?\\\èú\Ù\Ö.p²;\ä2¥BŠR\È\È›¬©\à$£-\n\Z¼\Şoô\è\ÑÁ„ı8Y|\ÒIFg”t\\r¦	›BF]\È\çY.„Ş‹=\ÚSLd\íy\\Iœu)û{ŸT\ê[\ÖÍ¨ˆ¨¨¨\'%\ß\à\Ó\0\0\0T.7U\0Tr£rWm³rE]÷fŸµ¢$e	B\Êó³\ÛûÙ§ô,-\çH	\0\0\0\0µ D\0\0\0\0\0\0_ t\0\0\0\0\0\0>A\è\0\0\0\0\0\0|‚\Ğ\0\0\0\0\0ø¡\0\0\0\0\0ğ	B\0\0\0\0\0\à„\0\0\0\0\0À\'\0\0\0\0\0€O:\0\0\0\0\0\0Ÿ t\0\0\0\0\0\0>A\è\0\0\0\0\0\0|‚\Ğ\0\0\0\0\0ø¡P\ÑF\äv‡Š¨AôûqHor©	\0\0\0 šûKTP1AAA¿œ:uŠŠ¨A’““cô&šš\0\0\0\0ª¡PAO<IE\Ô 			©z³š\0\0\0\0ª¡PAYYY\Ó\ã\ã\ãsÃ 2j†Ø¤¤¤>zûU\0\0\0T/B ‚®½ö\í\â|\0\0 \0IDAT\ÚÍ†a\ì:zô(•Q\Ä\Ä\Ä|®ß£\×_ı&j\0\0\0¨^„@%\È\Ê\Ê\Z›‘ššJeT£”””UIII·z<I\Ô\0\0\0Pı€J0hĞ uGwÈ={N<T	v\ï\Ş\İ\Ë\årıY¿1\Ô\0\0\0Pı€J2p\àÀµ¹¹¹w\îÜ¹35...‰5ªLlLLÌ›»v\íºR_~X¿S%\0\0\0@\Í@\è\0T\"	òòò®Œ\İú\í·\ßß¿ÿ\ä\ä\ä\ÇS\ãıÀ\ê\Å_T?ıôS?V]Ÿ‡Nœ8ñŸ={öD~ıõ\×III\İôm\×8\0\0\0\05‹?U\0T.™j!›5k\Öôª\Ë\0}½™.A5ù¸?üğC•œœ¬\æÌ™£†\rVÓ«¹ƒ.\ÇuùA—¹,\Z	\0\0\0\ÔL„€\Üp\Ã\r\Ñz]uÌ˜1AYYY™rYÖ¤X´hQpDDD\ï\"\0\0\0€Š`z\09û\ÆU\Î\ë§OŸ@­\0\0\0\0¨(B\0b¬óŠa÷R%\0\0\0\0*Š\Ğ€¸\É\ëú€aÃ†5¦Z\0\0\0\0T¡PÏ………İ¨7-7\å\Éu??¿©\0\0\0\0A\è\0\Ôs†axrpY\Û!\Ô\0\0\0€Š t\0\ê±\Èl\î-\æ÷\ÂmrVj	\0\0\0@y:\0õX»v\í\ä¬¼o7#[o\ZŸ>}š)\0\0\0\0Ê\Ğ¨\ßşR\ä/·[F@HøÀ\0\0\0\0\åF\è\0\ÔS2µ\Â\ãñ\ÜP\Ô}†a¸­­L±hFm\0\0\0(B jİºõ\0—\ËÕª„]²ôı\Í233ûS[\0\0\0\0Êƒ\Ğ¨§üüün?\Û.ò\Ë\å\ZMm\0\0\0(B òx<wŸe—\0ù\Ç0Œ\ÖY.\0\0\0\0 L€zhøğ\á].WHiö•)mÚ´¹“Z\0\0\0PV„@=\är¹ú•qÿ¨5\0\0\0\0eÅiTš#F<¡;§/s÷\Ô\Ğ\ĞĞ©\Î¢¢¢\\\ÔZõX²dÉ‹zó¢ó6ış<#\ï“.\áú½y†Z\0\0\0PQŒt@\å}˜\Ü\îhj\0\0\0\0P\ĞO¤\nPY\"##W†qª”»¦\Æ\0\0\0\0 n#t@e[~–ûó\ä\Ã0VSU\0\0\0\0P·: R†±ø,»\ä\È?.—kµ\0\0\0\0u¡*UÃ†\r7\êMr	»é’˜°‘\Ú\0\0\0€º\Ğ•*\"\"\"\Ë\år}R\Ì\İù\Ç0Œ•6lÈ¥¶\0\0\0\0 n#t@¥3\ã­b\î2\×sp»\İPK\0\0\0\0P÷: \Ò\É\Ô	\Ã02Š¸+@—\Ãñññk©%\0\0\0\0¨ûP\éd\ê„\Û\íğºÙZ±š©\0\0\0\0P?:À\'<Ï§\Î\ë†ax¬‹_P;\0\0\0\0P?:À\';¶Zo\í\ë.—\Ë_®{<©Ÿ\è­\ËÅ\ërJÒ§¨\0\0\0\0Õ‰\Ğ>!S(\\.\×g^7¯^¶lY\Zµ\ã\ß;.·\Ğ%K—\çuyQ—Ë­r¥.¿¥ª\0\0\0\0TBøŒ÷Y,\\.g­ğY\'\Ã^¼S‚†{­\Ë\Ûtù\Å*\ru9LU\0\0\0¨*şT|%((hSVVVÁõ\r\Zl Vª\Ä\Çe	Y—ı—\0\0\0À\ç\é\0Ÿ‰ˆˆ\È2c¯\\v¹\\ôõjÅ§n\Ñ\å\ïºd\ê\ÒW—Ë¨\0\0\0\0Õ‰‘µ”\î\Ì<xp|ZZÚ¨ŒŒŒ\é\é\éA§\Ægbb¢Ú¼y³º\ì²\Ë\Î;v¬QÓ\Ï\år\r\Z48¸9\'\'gfÿşı\×\×\âEg]®\Öe±\Ê\ÕDK\0\0\0Pj¡ƒşe\ëÖ­³²²²\Z¶m\ÛVI			Qnw\Í¸r\Çw\ÔØºôx<®´´´–©©©ƒ~õ\ÕW?dff\Z4hPL-ühüK—wu™¯òG=ˆsi1\0\0\0\0ª\Ó+j™¸¸¸•‡zµE‹\r/¿ürÕ¹sgÕ´i\Ó\Z8\Ôø \ë­I“&ªcÇª_¿~:u\ê#£Ö­[7¤–şH»tyD—©\Öõ)ºüh•Ot\ÙÊ»\0\0\0 ª0Ò¡‘ÀA—!]t‘\ÙQF\åkß¾½„8Á»v\ízo\íÚµw\r8ğ\ãZtøvò´O—x•¶Š?«ü3[x\ï\0\0\0\0U\ÖIA\r\'S*80¤GE§Ó©\Ã\Ñ\ï©}\ëŸU{\×=£â¶¿£²RPq\åĞ¨Q#Õ»w\ï—\Ëõ\ŞgŸ}vq-:ôÆ\Ëo\è2F„‡w\0\0\0@U!t¨d\Ñ\È\Ä\Ä\ÄY2•¢¸À\á\ç\íTZ\â^\å\É\ËV†\'We$ı¤â¶½MğPN<t\í\Ú\Õ\ã\ç\ç7·v´\×õñN\0\0\0¨N„µ€œ¥B”¡ÿEI:ôµ\Ê\ÍN?\ãv	 n Ë©cÇ\Íıııóù\çŸ_Em\0\0\0\0@\Ù:\ÔrZL9C…\Ë\å*òş\ÌF3\È(”_\ëÖ­ôf(5\0\0\0\0eG\èPdddô3T\Ç\È\Ë)şÁSø+¢eË–ôf\05\0\0\0\0e\Ç\Ù+jôôô *¢\Z\èzo§7­¨	\0\0\0\0(;F:\ÔG¹İ¼U\Õ\Ò@ò\ëp\0\0\0\0\ÊÓ§¢\nj¿€\àsŠ½Ï¿#$\0\0\0\0\0ÕƒĞ¡h\Şù\Z\å\Ø\èŒ\Ûı‚Uó.ı© \0\0\0\0@µ t¨\Z·\ê©\Zµ\ìQ\ä\í!­/¢‚\0\0\0\0\0Õ‚Ğ¡\È\Ë\É<\ã¶\ÜÓ§¨\0\0\0\0@µ©\Ñ\ä†´{÷\î\Ñ;v\ì¸+>>¾\ïÑ£G¦¦¦úgee)#$$$§yó\æGš4iò\ŞıŸû\Û\ßöÖ·70;ı¸Jøñ#•‘|ğŒû\ÒO\ìWq[\ßTm{Ş¦q”¬}ûö)º©Î;“V\0\0\0¨52t°\á\Ã?|\ã¹\ç;q\â„Ï=Õ€T§NTó\æ\ÍUPP\Ê\Ê\Êr:u*ğ\çŸ\î\Ómûö\íwMš4).==ı¹7\Şx\ã­úğ\æ<º]ÿ\ßg*7;½\Ø}2R©\ØÍ¯ªV\İªf®R.ƒ[p¦§Ÿ~:L·¥Y7\İt“Ú°a\Ãô\'Ÿ|òÄ´i\Ó\"¨\0\0\0\0u*tØ´i\Ó\Ø\çŸş_\'Ol\Z\Zªúô\é£üüü\Î\ØO‚)­ZµR—^z©º\ç{\\;w\î\ìõÚ¸q\ãMJJºuÙ²eÿ«‹ošŒn8¶\ïs$Cixò²\ÍıOÿAµ>°\n\ni\Ç\'&O<4..\î½	&¸¥-\é6\ç7o\Ş[“&MÊ={öbj\0\0\0@yÕ˜¯½\r\Ãğ_µjÕºˆˆˆ·u§§Á/¼`†	EÅ‘€B?\Î\ïö\Ûo¿ $$d\×ı÷\ß_]|\Ód\äBi\'™‚qh\Ë\ë|\êQ@F8$&&.0a‚Ÿ´7»=üğ\Ã~\É\É\É\ïNš4\é.j	\0\0\0@yÕˆ\ĞA‡/¿üò›µk\×^÷\È#¨[n¹¥Laƒ·AƒIg*\Ø\ívÏ½\ï¾û¬košŒ\\(w]{rù\Ô\Ã>ú\çŸ~üøñn;p°õ\ê\ÕKMœ8\Ñ|ğÁÛ©-\0\0\0\0\åQ#¦W|ô\ÑGkÖ®]{ù”)S\Ì\é•¡K—.\ê\ÙgŸ\rş\Ç?şñbXX\Ø\É\È\È\È÷\ëÊ›vÁÀ\çø\ä¢B$pˆ]ğğ\Ã»edCQ\äöGy\ÄoÖ¬YK\î¹\ça\ï¾û\î*j\0\0\0@YTûHY\Ã\á\ÓO?½nÂ„	•8\Ø\äù|ò\É\Æó\Â\Â\Â.\à\í\ÌÀa\ä\Ù›Œxxô\ÑGôÅ¥£G¾™\Ú\0\0\0P\Õ\Z:\ÈY*Ö¬Yó\ê\Í7ß¬~ó›\ßø\ä5d\ÄÃˆ#\Z¸\İ\îwx»Q\ßMœ8ñ¶ƒ.”)gl<<ö\ØcgEXXØ\Ô\"\0\0\0€Òª\Ö\Ğa\åÊ•o\Ëi/e\r_\Z<xppÓ¦M»\éÓ¨úúF?ó\Ì3—\çÏŸ¯rsÏ¾¶Cvv¶:|ø°:~üxÁmŸ|ò‰y»·¸¸8•p\Ö\çüè£\ÔW_}U\è¶\éÓ§+İ¡-ry½­[·–ù\ç}ë­·Š<\ÎúLF8¤¤¤,w.\ZYZ<L<9À\ß\ßÅˆ#n¡6\0\0\0\Ô\è\ĞAF9\Ä\Ä\Ä—\ÓbøüõÆw\Û\í~°>½¹\ëÖ­3\Ã\0)?şøcÁe)GU›7oVO=õ”¹\ïO?ıd\Ï>û¬J¬^½ZM›6Mmß¾İ¼¼p\áBu\â\Ä	µw\ï^x\Æku\î\ÜY¾EWkÖ¬)ñ˜\æÍ›§’’’\Ì\ç|\î¹\ç\Ìc8xğ .\Èmöñ\Øn¼ñÆ‚ÀD‚	œE¹]\Â§F\ZVx\ïSŸL™2eTll\ì»\å	œÁÃ£>\Z¤\ßÿe\0\0\0\0jt\è°{÷\îÑ¿üò‹i‡xWT\ïŞ½4h\Ğ-44´kUÿ¬ú5ÿ¡ËÃ†\rk\\•¯ûû\ßÿ\Ş¤3/£Iä²”?ü\á\æöŠ+®0\ÆÜ·{÷\î\æu	\Z6lhvö\ï»\ï>¤š6mj†-Z´PÁÁÁÅ¾^xx¸Š.¸¾iÓ¦B£–-[f†§N2_oÈ!fpğê«¯š\Ïùå—«\çŸ\Ş\ÜWFW,Z´\È1\ä8\î½÷^ó¹$d¸õ\Ö[\ÕÆ\Íò\Ïş\Ó|¾¨¨¨‚\àBŠşl©”””‚\ëÿû\ß\Í}\êk\àpøğá…²†Cy›´\×É“\'<\0\0\0\0(•j;{Å;\î’LENYV½zõJø\î»\ï\î\ÔgVñûO³²ıı=¡¡¡;õÅtÙ ;ô›\"\"\"²|õ¢_~ù¥\Ê\Ì\Ì4G0üõ¯•o©\Í`@:\ér[LLŒ\êÔ©“ºû\î»\ÍıÇo\Z\Ğ\Ç)S_Ô¡C‡T·n\İ\nO¦@$&&\n\äº\ì/dÔ‚\\–N¾\È\Ê\Êÿ\ÑÚ¶mknÛ´i£Ú·oo†²\ß?ü \Ü\îü\ÜK¡Aƒ\ê\ã?6O™*‹€9Ò¼OF\\<ğÀªcÇf‰ŒŒ4ñ\Î;\ï¨s\Î9Ç¼<xğ\à‚c“Q\rò³É·ó¢G…~–ú\ÂZ4rai,C;2ƒ‡\éÓ§/\r»C¿«ùU\n\0\0\0 F…ñññ}P¥¯y\å•W6û\î»\ï~W\r¡ƒ\É0\éa_b•©ºS\Z\Zú¾¼Zw¾\×=z4fÃ†\r¹•õzW_}µš3gZ¾|¹š:uª8\È\Ô-ğò\Ë/«?şñ…ö\ïĞ¡ƒ\ÙÙ—\íÀÕ§Ÿ~ªÖ®]«òòòTFF†zıõ\×­\ïp\ÕUWz¼„\È(‰¢\È(	5$¼°¡q\ãü2\rD_¼ñ\Æ\æqË¨\n!ÁÄ‘#G\Ì\Ñ²öƒ#2u\Â\Ûc=f†ö7ùò¸–-[šÕn•ššªüñ\Z\İõgÁ\Ğ™2?((heDDDJE\Ï^4rÂ„	ƒŒş\0\Ç\ÛÜ¹s=F\Ş]\âM¦\Ş4o\Ş\Üt}\ë÷äƒ°°°¡\0\0\0\0ŠRm\Ó+t»¡œY¢*{\î¹\rõ¦o\rª™«ğ]f\èøv\í\Ú\Ñ¸H\İù3r\äÈyb\é4Êˆm°b\Å\n3©²Îak\"\ÈH{D‚		1·§OŸ6§J\ÈVF%\Èe}|f§µ,\Ç`“i2rAF(È´‰ó\Î;¯ HÈ±xñbÕ¯_?s†8$\'\'«˜Ç¹~ız3XØµk—9\r£(².…R.¾øb38±/\Ëk\ÔıtyG¿\'\Çõ\ç\à]ú•\çIŠ[4²Y³ff\è Sh\n~	¸\İ\êµ\×^+ôx	œk­\Èh’Q£F™ƒ\Í:«ECıø\åLµ\0\0\0\0P”jéšš\ê/ ª¤;Á­t‡;Ww\ä\ÑWe±‚Ş¶\îôÖ\'s”\Ş\'\Ë\år,F /§EFF&úúøô\ë¶Ö›P)2º@g¾ü½,úXVÒ©”\é2šá¡‡2G	H\çRF\È\Ú²ÂŒ3d˜¼$\È™N1v\ìX³Ó¿t\éRsz‚tğ\í\êm·\İfvş$@©<·\ÒQ•\Ññññ\æ¨{t<§=%BY\Ì\Ò&?«|\ËnOÃ°>\æTY\ãaÌ˜1\ê»\ï¾3G@xOù°§gEaOí©#r\\²&…\\–À¤9­?súó0F_£?õ\åùÁÁÁ+K3\'<<|tll\ì\áP\Ô\Z\×^{­¹V†„4\Ög\ß\\\ÌS)	~d±\Ğİ»w«œœœü_şşªI“&E`‘ú=ĞŸ¯¨#F„.Y²\äc~­\0\0\0¨ö\ĞA¾]—aUÚ¶m›ò3Oõ¾\Ï\Ùy\Õûx‡\08o’\0\"\Íq½P€¡\Éi\ì\0£\"\Ó%\ä«\æ>;v\ìğ~ıR‘¤L38p\à€\Ù\Éüşû\ï\ÕĞ¡CešIÁˆ;vX E:\é\Ò\éLOOW\×\\sú\ÏşS\äk\Ø\ë.ˆ³M¯°I\0!‹V:\Ã	\nõºOŸ6\×yQH\È,ö\ï\ßo.8©;·E~†¤sl¿¶ü2\ÒA¦pH‘°¢<\äy¬\×|\Â¢d+Á”µK¬œ$.[¶,­’>ª\r\ä3g‘ó~ö×¯\Ù_ÿ¼iús¡_\ëeıZÿ+)p8\Û\Z2ME\ÎbF‘µ?\ä4£²ö…œa\Ä9JEB§ûï¿¿\ØõWd‘V	—$x»“©\0\0\0\0ª=tĞSCw¢\\U<ô\í\Û÷\Äò\åË›\êœœ\"¡.A ¡£\î\è\Ùõ!··u<Th\é¸\Ş\Ò\ëz\×\â^\Ó;À(+}Lÿ»\ä’K\Î+\Ïc%H‘\àAF,H\ÇQ¾\áş\æ›o\ÎX‹Á&Aƒ\İiOKKS«V­2G	8ƒ…\Ê g¡uœ\"\"\"\Ì\áúöº\Îõ„ŒZE(¥\Ø\ä›z9†­gÏEt:\Îty\Èb™ú\è÷ñ\Ç{r\Æ~\Ò!·‚!	#\ìQ2B$:\Ã	\ëzº]\Öş\Îı¼:\Âù×¯5^¿\Ö\Çú˜\Ş\Î\Í\Í]k‡O?ıtXi!£Jä½–u;$p4\È\Èeb“\é62\Â\ä\Üs\Ï-ñù$x˜<yrŞŒ3\"GŒq7#\0\0\0\0Tk\èĞ¤I“œ”””@\çz_kÔ¨\Ñ	\İQËŒŠŠz¦\"\Ï\ÖRw<;:¡mõó`\È\È\n¹_Yg°(|\Õo•£ózppğû²˜\à}÷\İg”\ç8etÃƒ>¨&L˜`~\ë/óô\ål\ÅuÀe\ÑE\ç™\'d”ƒœZ\ÓIF”–Œ`ø\ïÿ«~÷»\ßº]‚\ï\Ñr:M\ï Á~!S\0\Şÿ}õ\ÄO\Èz \æˆ\n9[†3t5lÎ‘!Œ’ˆ~\Ñz\Í>»ú5°\êjm[Z¡@*!„ª€@\Ç\çË£\å)V\ØñcÓ¦M#\ã\â\âv.\Zy6w\ÜqGÁN„Œl‘i/\ÎPEF>¼TÏ§_7ø\ÑGõÌœ9sñˆ#\î\"x\0\0\0\0Pm¡C³f\Í\âtgî¼ªv\ï\Şı“Õ¹¯k}\ç7Ó±g{Œ\î:\ä\ê¤\Û:«…«u\çz\Ö/¿ü²¡¢g±p\àOú“9²A:\àf\ï¸kW5lØ°‚}œSJ\ì¶Hgÿ\Í7\ßT\ãÆ3¯Ïš5\Ë\ÜÚxù6\\ut†²0¨<§½^ƒ„2µC¾)—©69¥n\Ø$\à(Š„\ròš²ÿÄ‰ó{üú3óê«¯*İ©5\×}(Št %D)\ï›Œşuşö·¿ı½4û3&(++\ËüPK0¥\ßß–Å„\Âmc¤Pš×°>/N\è÷#\\B—¢\Öp(H\É\Úvı;YsE\Öøp.:y6—\\rI£\Ç<múô\é\0\0\0\0ªu¤Ã—111\ç\ÉúUEwÀ¥Gõe5×¹„2~ı©İ©N\ÑÓ·òòò^/n~yØ|™R!E\Èú\ròÍµM:\îNÎ‘²\ß\èÑ£‚	ûq\ÒAµ\Ş?Õ¿ÿB÷!×‹š\Ê!£\Z\äñNÅ­!ƒùw’c’RJ\èü\n:ªŠµ\Ğclyk2³Ì¤>d­²6	edZ‹Œ\Z‘€\Çùœ;v4–,«>}ú4<yr\Òô\é\Ó………`\0\0\0 şª¶Sf<yò…\è\èh÷‚>”µÿşôvy5×¹Ÿ8\ÈŠ	ÁÁÁİ¢¢¢&Wf\àPg\àP\Ôuoeù†»,¼_¨À¡*¸\\®z³D—?ùùù]$·\r\Z4Èœ*Q^r*L\ïu*\äú\í·\ß^\î\ç\ìÛ·oóşıû§\ê\çùtÌ˜1\Í\0\0\0€z©\ÚF:<÷\Üs?=ò\È#w\î\ÜÙ½<\ßĞ–Õš5k>ğx<]t?¶š\ë¼Ò¦P \î(a”ƒŒœØ¢\ËGn·{\íÑ£GcœŸ›ğğğ1úú‚>}ú”z-o‹/6<u2\ÒA½ğ\Â\Ëõœ;v\ì8¶q\ã\Æ&úyo’uIx‡\0\0\0B‡*wò\äÉ¿\ë\Ïb\İYò/\ît|•$e\áÂ…\ê\ĞK\Õø\ãN\Î\Ë\Ë[Y#\ZP{\É\"‘\Ú½ıP_\İ´Éš²Q¤©S§.|ò\É\'3gÏù\ĞC¹\Ë\Z\à\íİ»\×\\›\Ã9µB\È$Y@s\ëÖ­eb!ÃŒ3‚õs\\²d	S+\0\0\0\0B‡\êñö\Ûo/7nÜ”O?ı´\×-·\Ü\â³×™9s¦Œrè‘U]?kTT\ÔL>n(Á]~lĞ ÁÚ²˜6m\ÚÒ‰\'fÏ›7o¹\Şú•vÄƒ¯¼òŠyzT›s\Ä\ÃÉ“\'\Õ;\ï¼cv(\íT›­[·{é¥—\ìÀE$\0\0€z\Î]\İ ;6\\±bE\æşıû}òü»w\ïşhÛ¶m7\ë\ÎÔŸ™Î€š***\ê9]–—w*Âœ9sV6k\Ö\ìN½\ÍÛ¾}{©ó\á‡ªŒŒŒ‚\ë\æâŸ²µ\ÉY@–.]Zª\çÛ·o_ü\ìÙ³	\0\0\0\0Øª=t5²³³ÿ<kÖ¬Œ\ãÇW\ês9r\ä¿/¾øb_—\Ë5922òG\Şn\Ôe<4j\Ôh\ØÜ¹ss\Î<$$$˜§!u†ÁÁÁê©§R!!!·:uJmÚ´\É<õiIdJExxxS\Ã0\Æ8\0\0\0\0°¹k\ÂA¼ÿşû‹\Ò\Ò\Ò&ü\ãÿH«¬2\Â\áñ\Çï”——÷jdd\äû¼Õ¨\æÏŸ¿\"33sğ\Ë/¿œ]Rğğ\Úk¯©\ì\ì\ì‚\ë2}b\ìØ±f\à0~üxTpŸ~>sÿ\â\Î4c¯\á`\ÆH\İ\ÖVò.\0\0\0\0°¹kÊ,Z´hÁÉ“\'ÿü\ì³Ï¦®Zµ*½§\ÒLyé¥—\"¦M›v…\Çã™ºdÉ’y›QŸ,]ºtmNNÎyó\æe<lÜ¸Q>|X\å\æş:Û¨K—.\ê\Ê+¯4/÷\ì\ÙSõ\î\İ\Û<ƒ…p\"==]\Î\0s\Æs\É\Zö¢‘\0\0\0\0jl\è –,Y¥;B}t§\é»|ğØ;2\Ëğğ¬\Ï?ÿ<\ê\î»\ïşß–-[\Îs¹\\\×EEEEğ£>ŠŒŒ\\}úô\é\Û\çÌ™“µs\çÎ‚\ÛSRRd4„\"8\İÿı…®7®\Ğ-’’’Ô»\ï¾knm2\ÂA\ÔG0¥\0\0\0@Qük\Ú\É\Zzó‡\Ğ\Ğ\Ğ;gÎœù@```¯.¸\àxÿşıw\ë\Ö-°yó\æme\èwff\æ1İ:ùı÷\ß\ïß¸q£\ßşıû{\ä\å\åuÔŸğ>‹F‚\à!rõğ\á\Ão5kÖ¿\'Nœ$§\ÓlÖ¬™\Ü~\Ö\Ç\Êt‹’ö³\ÏR!ƒ¼µ\r\0\0\0 V„ğa¹\Ş,9rdÇ;w\Şı[}½Ÿ.YºÈ„ó¦º\ä\è\"\ç÷ûB—¬À€E¦ZHğ0gÎœ?üğ\Ã—]v™_EŸS\ÇY*\0\0\0\0Ë¿¦\à¢E‹\ë\Í«\0(#Gğ°j\âÄ‰\r*<\ØS*8-&\0\0\0€\ÒpS@\İ\'ÁC^^\Ş\Ğ9sæœ.\×*­ö¢‘Š5\0\0\0\0”¡Cmx“\ÜnÃ¹¨ª®÷CzS\'\Ö‘µ$x˜9sfÖ¶mÛ²\ËòXç¢‘¬\á\0\0\0\0 \Ôı\ÙjxÍ—tiU†ıuy\è,ûœ«\Ë_Î²\Ït]\Z\ë\Ò[—«­\ÛnÔ¥EM“‚‚‚~9u\êŸ\Öjœœ£7\Ñu\å\ç‘À \'\'\ç¶Ù³g\ç”6xÀÁqZL\0\0\0\0¥VU¡C˜.ƒ­’muö\åòSºtp\ìw‹.]¬b\r²ÿùE<\çÕ\ç¼@—‹×¥ü\Ék	\Òt‘3\\´±n©Ë‰šş&l<yò$Ÿ\Öjª7\ê\Ò\Ï$S-$xxé¥—²·nİš^Ò¾ûöí‹Ÿ9sfˆ¾8š)\0\0\0\0jj\è !\Ã\'VyB—÷¬\Ë\ít9\î\ØO:5‡¬2WåŸ¥BF\'XÛc\äxN)±\ËÇ¬\ë\â\\+„p\ã!G\à \ì9\Ô\ä7)++kz|||a|b«VlRRR½ı ®ı`<\è\ÏÓ°Ù³g»¶oßZ\Ô>²†Cxxx\ŞOF8¬\ä\ã\0\0\0\0 ¦†nUx‚]º:ö\é \Î©0\Ä\n¬}F;‚‚{t¹\\\å`\Ò\Óq¹“.\ë­ıXA\Ä\ÏV!\ÄeºL±K¦zü]qQ£\\{íµ›u\Ço\×Ñ£Gù\ÄV¡˜˜˜\Ïu½½şú\ë7\ÕÅŸO¦J\ä\æ\æ™5k–kË–-…‚{\r‡¼¼¼{\0\0\0\0\Ôô\ĞÁ£\nJ°‹œ\Ó^¤/Şº­—ÊŸRñ.KTşi=ÿ§ò§Fd9S.\Ë:§¬r\Úqù\æ\"AFMÜ¦òG:l\Ó\åŸ\Öqı?+\Ì8R“ß¨¬¬¬Q±±±©©©|j«@JJÊª¤¤¤[=Ï¤ºüsÊˆ‡\Ü\Ü\Ü;\æÌ™\ãúüó\ÏO._¾\\­_¿>\Ñ^Ã)\0\0\0\0jC\è d\Ç;T\á\Ñr\Û9`\Â\ß\nö8n¿Rıº\î‚÷ñ\Ê(…V\é\ì¸\ì\Ü\ïwº,Ô¥½.ò\í1¯\çø°6¼Qƒ\rúQw‡\ìÙ³\ç$Áƒ\ï‡İ»w÷r¹\\\ÖõS\×^;xX°`A\Ğ| \Şx\ã\0\0\0\0µ)t\Å\ê:ª3G<ôQ…r”\'ŸWù£d‘I S!\ÖX÷öz\Şcª\è5œ\ç—üN—{Uş4‹Zm\àÀ\Ò9¼s\çÎ©qqqI¬ñP\ébcbb\ŞÜµk—]\ëú®7n	\\.\×r¹,[\0\0\0\0µ%thU\Ê¿„ct¦\ËuVÀ0B9mƒ=£©\×cdd„s}ûr‚cŸ,\Çã®¶/.Tù§\æ¬ÁC^^Ş•±±±[¿ıuhm\0\0 \0IDATö\Û\ãû÷\ïß‘œœœ\àñxø4—ƒ®·C\'NœøÏ={\"¿şúëŒ¤¤¤nú¶\ë\ëS\à`3\ã\Î-\0\0\0\0T”¼\Æíº¼¦Ëµº´Vù§Ã´5r\\\Şa…?[\×e!È‰º¼¢òO™y¸ˆ\ĞÁyZL9\İ\æ\'\Ö\åO··°¶öceô\Äı\Öcs­°A_Fb¬©\rošLµÍš5kú\Æ\Ç\Ç\Õe€¾\Ş\Ìú\ÙP6²€¨œA\å]\æ\Ö\ÕE#\0\0\0 .†rJ\ÌoTş\"Ò±“\é‡÷¯p\\ö89=¦„¯YÁ€\ç\çº<X\Âkes»Lß\é;¬\ë_\ë\"óôe\n\Çÿ©ü\Ñ\â\Û\Úö\æ\İp\Ã\r\Ñz\Í\Ç\0\0\0\0Pù:tˆ·Š\ØR\Äı‹Šyœ„\Î\Ñ\n\ß\ëòÛ³¼\Ö\Ä\î{\Ûqù¸U\ì\çı\0\0\0\0\0•\ÏM\0\0\0\0\0\0_ t\0\0\0\0\0\0>A\è\0\Ô]ö8\ÛET\0\0\0€ªB\è\0\Ô]UşM~²®\Ëb­ı©\0\0\0\0UÅŸ*\0\ê„\Çt¹GåŸ†6]\åŸ)fœ.Yº\Ü\ê\Ø/“ª\0\0\0PU€ºA¦M¼¢\ËëºŒ\Ö\å\\\Ç}©\ÖV¦Wœ ª\0\0\0\0TB n»\\\å-¬\í8ª\0\0\0@UaM nK\Ğ\åk;^—ª\0\0\0@U!t\0ê¶Ÿ­\"m}\Õ\0\0\0 *1½¨\ä¬S­\Ë2šAB†?\ê²\\—u™«\ËkT\0\0\0€ªD\è\0\Ô\rË¬\â$k8Ü©KC]&\ë2G—‰T\0\0\0€ª\Âô\n \îjhm3t	\×\åIõ\ëh\0\0\0\0ğ9B \îò^4\Ò\0\0\0\0 J:\0\0\0\0\0\0Ÿ t\0\0\0\0\0\0>A\è\0\0\0\0\0\0|‚\Ğ\0\0\0\0\0ø¡\0\0\0\0\0ğ	B\0\0\0\0\0\à„\0\0\0\0\0À\'\0\0\0\0\0€O:\0\0\0\0\0\0Ÿ t\0\0\0\0\0\0>A\è\0\0\0\0\0\0|‚\Ğ\0\0\0\0\0ø¡\0\0\0\0\0ğ	B\0\0\0\0\0\à„\0\0\0\0\0À\'\0\0\0\0\0€OøS@ı3bÄˆ\'\\.\×\Å\Ü=544tªó†¨¨(µ\0\0\0 ¬\é\0\ÔÇ†\ïvGS\0\0\0\0|\Ş÷ \n€ú\'22rµa§J¹ûaj\0\0\0@y:\0õ\×ò³ÜŸ\'ÿ†±šª\0\0\0P„@=e\Æ\â³\ì’#ÿ¸\\®E\Ô\0\0\0€ò t\0ê©†\rnÔ›\äv	\Ò%1!!a#µ\0\0\0 <€z*\"\"\"\Ë\år}R\Ì\İù\Ç0Œ•6lÈ¥¶\0\0\0\0”¡P†ñV1w™\ë9¸\İ\î¨%\0\0\0\0\åE\è\0\Ôc2u\Â0ŒŒ\"\î\n\Ğ\åp||üZj	\0\0\0@y:\0õ˜Lp»\İ^7\ÛS+V3µ\0\0\0@E:\0õœ\Ç\ãù\Ôy\İ0uñj\0\0\0@E:\0õÜ±c\ÇV\ëM¢}\İ\årù\Ëu\Çó1µ\0\0\0 \"€zN¦P¸\\®Ï¼n^½lÙ²4j\0\0\0@E:\08\ã,.—‹³V\0\0\0\0¨0B\0*((h“ózƒ\r6P+\0\0\0\0*Š\Ğ€Šˆˆ\È2c¯\\v¹\\ôõj\0\0\0@EùS€o\èN|\ĞÁƒÇ§¥¥\Ê\È\Èè‘\äñxj\ìñ&&&ªÍ›7«\Ë.»\ìÜ±c\Ç\Z5õ8].—Ñ Aƒ›srrfö\ï\ß}-ùHôVù§#±®/\Ò\å{]§µÔvT[ĞhG´#\Ú\íˆvD;¡P‹\éÿ”ş²u\ë\ÖYYYY\rÛ¶m«¤„„„(·»f.º\ã;j|\İ\êÿ\à]ú?ü–©©©ƒ~õ\ÕW?dff\Z4hPL\r?tùƒ\î\ër]²¬?ğ^\Ô\Å^C\Ãmı^ş/­¨ö¶£Ú€vD;¢ÑhG´#\Úª\n-¨dqqq+:ôj‹-\Z^~ù\åªs\çÎªiÓ¦ü\ÇTY¿´t=6i\ÒDu\ì\ØQõ\ë\×/°S§N}$_·nİ\Z~è¹ºdX—\å»{­\Ë\Ûtù\Å*\ru9Ì»L;¢ÑhG´#\Ú\íˆvB\0EşÇ¤Ë‹.ºHu\è\ĞA†ŒQ)>Ö¾}{Õ§OŸ`ıŸ\Ö{k×®½¥–ö\Çeù#\ïW¡ÑhG´#\Ú\íˆvD;¢\ĞÀ¯d\èİ†ô\è\Ñ\ÃLl½N;¦G¿§ö­V\í]÷ŒŠ\Ûş\ÊJ=B\ÅU‚F\Z©Ş½{‡\è?\Şû\ì³\Ï.®Á‡*ÿyş]—L]ú\êr\ï\íˆvD;¢ÑhG´#\Ô\ÊvB \ê\È\âB‰‰‰³d\È]qÿ1ı¼}JKÜ«<y\Ù\ÊğäªŒ¤ŸTÜ¶·ùªÿƒ\êÚµ«\Ç\Ï\Ïon\r>\Ì\Î\ÖzÉº¤Z\ìvD;¢ÑhG´#\Új_;¡Pud5cY\\H†„%\é\Ğ\×*7;ıŒ\Û\å?ªÄƒ¨ÀJÒ±c\Ç\æşşş¿ùüóÏ¯ª¡‡ø/]\Ğe¾.Tşb^ ÑhG´#\Ú\íˆv„\Ú×PJœ½¨rú$YÉ¸¸¹~™%¤Ş’–£ò´n\İ:\áÈ‘#Cõ\ÅM5ôw\éòˆ.Su	\×eŠ.W;?Nºô£ÑhG´#\Ú\íˆvD;¢\ÕøvB j\Èùš\å?§\ây9\Å?\Ø\àÏ•©eË–ôNj\è\áÙ£\Ëö\é¯òWÿ³\Ê_I\Ü{\Ú\íˆvD;¢ÑhG´#\ÚQ\ÍmG(\ã/\0$\çkFõ\Ó\ïC;•¿(VM\Ô\Øqù\r]\Æxı\'<´#ĞhG´#\Ú\íˆv„\ZßPJŒt\0*\Ç\ã\á|\Í5„õ>\Ô\Ô\ßm\Ñ^\×ÿ\Å;F;¢ÑhG´#\Ú\íµ²aÄˆO¸\\®Š¹{jhh\èT\ç\rQQQõ\â<¬´& \nŸS\ì}ş\rH\Ò\Ú@;hG¨õk·;šZ(¢^¨À÷šw¾Fù6:\ãv¿€`Õ¼K* ´#€v„Z.22rµ\Ê_„µ4×—z!t\0ª@\ãV=U£–=Š¼=¤õET@;hG\0\íu€aÿ)\å~«\ëK:\0U$/\'óŒ\ÛrOŸ¢b\0\Ú@;hG¨#\Ãx\ë,»d\Ë?.—kQ}©BÀÇ²Ó«¸mo«´\ã?œq_ú‰ı*n\ë›\æ>\0hG\0\í ¡vkØ°\áF—\ËUR¨KbBB\ÂFB\0vò\èvı\Ó[*#ù`±ûd¤R±›_U\Éq\ß(ƒs;´#€vĞPkEDDd†ñYIû\èûWnØ°!·¾\Ô	§\Ì|@\îcû>1\ï\Òğ\äe›ûŸ:şƒj}ş`d’ Ñ\0\Ú@;B-4_—;‹ò\\.—Ÿ\Û\íş >U#\0„»´ÿ19Ir~h\Ë\ëT @;hG\0\íµ”L0ãŒ…?$pĞ›\Ãñññk\ëS}:\0> 	wy\\* ´#€v„ZJ¦N¸\\®\È\"?†±º>M­L¯\0|\à‚\ÏQ	¨t\íÛ·O9zô¨\êÜ¹ó)\Ú@;¢vD;B\Íe\Æ\n—\Ë5\Öq“,ò!_úQ\ßê‚‘\0P<ıô\Óa\É\ÉÉ³nº\é&uüøñ\éO>ù\äj ´# f:v\ì\Øj½Iö\ê{\'z<\ë[]:\0@\r7yò\ä¡qqq\ï?\Ş}\Ï=÷¨‡~Ø­ÿ#{kÒ¤IwU\áa\\§\Ëÿó*\á\İ\íˆv\Ú\í8“5…b½\×Í«—-[–F\è\0\0¨1\ä¥\Ä\ÄÄ¥&Lğ»ô\ÒK\Í\Ûúô\é#\èù%\'\'¿[…\èİ¯\Ëw^\å\Ş!ĞhG Ñ€b\Ís^q¹\\\Ô\ÇJ t\0€\Z*<<|ô\Ï?ÿü¾|£dÿg\ëÕ«—š8q¢ù‡Şƒ>x{\Ò@¯’Á»\Ú\í´#\ÚP´   M\Î\ë\r\Z4\Ø@\è\0\0¨1\à\Å\Æ\Æ.¡«\Ş\à\Ù\ä¦Gy\Ä/--m\É=÷\Ü3¤\n\ë¯’\Ç;\Ú\í´#\Úğ«‘#Gv1b\Äø\Ğ\Ğ\ĞeYYY»Uş’\Â\Ğ\×w\èûV\èû“ı\0\0\Õõ\ŞHû<ùC®$ò\rÓ£>\Z /.=zô\Í>>´Á^\å\ïhG´#ĞhG€Raaaÿ¿½ó€“«ªğ›\ÔE‘€€ˆ\"*\Ö?ˆˆ \ÅB“\"„$RADQ0\n‚(\ÍTAQA˜\0A‚ \Òl@h‰\r‚”P“\0	$\Ùÿı\ŞÎ\Ü}\Ì\Ì\Îfû\æû~¿û\ÛÙ™7o\ŞÜ¹\ç\İs\Î=\çÜ½C»aùò\åÿ)•J¤U\ì\Ú&‰\Í]\nm\Ãğ\Ú\Ş\á\ï÷–-[ö\ĞÈ‘#o\n\ï9P§ƒˆˆt\Çs\Ì\Ş<ğÀ…„°¶¥à¥Š\Ş×¾öµ!a’»\"L\\»vò%\r/+t—ÿ”­\ØnùúĞ\nmºó‰(GÊ‘(GÊ‘¬ŠŒ9r\Ã0Ş¯e‹\Ì\ĞvmH;\Şş‘pü\Å\á·>|\"\"Ò¥°¢´hÑ¢™i‘®FA\Ñ3f\Ì\àAƒ]1bÄˆ\Î,¨uY\Öººuh7g-y³Ã²!­?\nmT¶\"tPD9RD9Rd• Œñ‘¸³¹¹y—ø\ÜÀó”£#8\";ë¬³²Ÿü\ä\'\Ùô\éÓ³Ÿşô§\Ùøñ\ã³ W\ÙV[m•\r<8=Õ¶\á}wõ×¨\"\İÌ~ô£\ì\Èşù\Ïf7\Şxc\Í\ã\î¼ó\Î\ìšk®É®½öÚš\í?øA\Í÷ssû\ïÿ\Ûğuq-³fÍªùúc=–\İs\Ï=•ÿyü\Ë_ş²\æñ|\ÇÅ‹\çÿ÷¿\ê;Í™3\ç\Ïñ½–.]ºÊ‹O<ñÀyó\æ]´2\n^ª\è=ºiÈ!—u²¢·nhÿ—­(\Ô\Åÿd-!ƒg…Ö¤)G\ÊQß“#\åH¤C‡=0uùò\åkEg\Ã{\ì‘Mœ81;vlöÑ~4{ó›ßœ½\æ5¯ÉÕ«^•½\á\roÈ¶\Ùf\"‰²\ïÿûùñ¼¯\Ì\êD=Œ\Z5\ês:Dd¥Y¸p!{öfm´Qöw¼£•±ñ×¿ş5=ò÷¿ÿ=7²l\×]w­4nVk®¹fşøcûX6w\îÜšŸ5mÚ´\ì\á‡nø\Ú¼~:\ä\ë_ÿú\ì‚.\È\î¸\ã\ÜÈ™2eJn²\Ã\íŸøD+\ãŒ\ë\Üq\Çóc=ö\Ø\ì?ÿùOş:\ßó\Üs\Ï\Ís®\'Ÿ|²\Õ\ç¼ô\ÒKù\ëÀõc0\Òüñ\ÇgO<ñD¿Tğ\Âw½°^‘®FÁ³>fÌ˜\ÎVô\ÉZBW\ßSşŸ\íÁ\Ğş\Ú\×B[¬)G\ÊQß’#\åH¤C\"f577\çœ	§Ÿ~:E$³u\×]·¡s0\Çs<\ï\Ã9	\ç<¯¿E<\èt\éFN8\á„l\ÓM7­÷\ß\å1+š¬‚¶e\Ğ,Z´¨¡Ïº\ï¾û\Z>0jššš^ñ\\\Ê7¾ñ\ÜpY°`Av\ÔQG\å\ÇsÌ»\Şõ®\ì[\ßúVö–·¼¥r\ìZk­•}÷»\ß\Í\r\"Á@|\ík_›ı\ë_ÿ\Ê\r½7½\éM¹w7Ş˜o»\í¶ü5ŒÈw\Ş9_1ş\Ç?ş‘\ç×½\îuù¹Ÿz\ê©üq‚Ö¨\à5š3\Û\å\ĞÖ¦Áƒ_\Ú	9µë‡¶Zhç„¶ah‡…¶i\Öz5)Í«U”#\å¨È‘ˆr$²rP\Ãa\àÀ?Œ\æÜ“O>¹•\ã =ğ>Ş¼$‡sƒ\Ìl\Ú_úL¡\é&Xi\ÄXHyù\å—+10Š\\}õ\ÕÙ A-¢Š\áAˆ\Ö\r7Ü\r>¼\î\çZ®øyúé§³[n¹¥b ±r¼\Ùf›U^gE”\×/¾¸¥^\Óoû\Û<Œ\ĞqŞ»ö\Úkg§vZnøpMúĞ‡*\ïı\ãÿ˜K;\ì°Cş¾!C†\ä«\Í\á&yä‘¹ax\ÅW\äF$|ğƒ\Ì\Î>û\ìü\Şû¥/})ÿ?û\Ù\Ï*\ç<\äCúÕ˜ˆEº>úèŠ‚‡û…/|\á\Ç\Ò÷©\çü…^\È;\ì°WGª\0ãŒ‰\ë¸\ã[í¬³Îº<LZûNŸ>ıÚ•¸\ÄõB\Û>´S\ãp\ÌZV˜Ø‡ª\Ìk‡F±$V˜\Æ2\ì”#\åH9\êır$¢‰¬<a.>?¦T\á0zô\èJ\n\Å\ÊB&)8z\è!\"\Õ\â¼ğw»~\Ñg‘®‡•×·½\ím\Ùv\ÛmWÓ°©u³Â€\"œUKŒ|\àyšza„VsÜ³\Ï>[óŒ½÷Ş»r\Î5 \\\Äÿ7\Üp\Ã<¬;‚Á9B³O:\é¤\Ü\Ğ!,\ì\Û\ßşvn\è_\äª~ø\áy:Qš[\r?\Ø`ƒ\rZ]\Ó;\ßù\Îì®»\îÊ®¼òÊ¼\èN\Zf\Î\rx\Ûm·\í7c¢V‘.B\íø˜|’\É-¯aBÿ¤ˆ00<ğÀVy¹Šø«\Âûg®dh+¹—‡Wù¹\ÃCÛ‡!Š¾šuCH«r¤)G\"Ê‘r$=	\Ûb†9ù\ã<¦‚:R\ÇE&“\Z\Ûö—4\"\İ\0y\ÜKL\è\Ñ !4š¿![o½u\İ\Âo\ãÆ\ËW/UQBµ9_£¹\Û\Ï?ÿ|+C†•‹4L|Ø°aÙ«_ı\ê¼\n/«¹ù\ÈGr†\Ç9(\"e\Ïlzo½õ\Ölûí·¯|¯õ\Ö[¯ò\İY‘M?‹°ğ·¿ı\íy\Ø<F7\Ü\Ûo¿=_\r\Æ]½\ç®$ôû7™d\Âw\î”è° \àR¯H}¶şú\ëWşgÜ°ª\Í\n=`”R ®ğó»­±\Æ\Zy¿a\Å\ê¸ã£ŠøŒN.\æ\Õ\åı¤)GÊ‘ˆr¤I/_Œ¨Ã´²)µ\à|i\Äfssó\çu:ˆH»Àh š>¹\ç¬h®³\Î:•\\t\Â\Â}ôÑª\ï\ãõ÷½\ï}­r½\êñ›\ßü&û\ä\'?™\í»ï¾¯X‘¨F\Í\Ç?şñV\Ï=÷\Üs­<·(Lo}\ë[ó0JnŠ¬³jú§?ı)û\â¿\ØjE—ü6¾!\á1§\ïGxügœ‘G<»|¿ë¯¿>û\Û\ßş–\çœ_t\ÑE¹²ƒ‘œû¼ó\Îë©Ÿ\îTö^\Æ\Ş\Â0YQUx\×\áÃ‡¯\Şï‚¶ŠtŸ®.½ø\â‹\ÙùçŸŸÿ.ŒÌ´ÿØ–)ñŒ·\"ôí¨\èuÁ¾\é\Õú\é\Ù\ĞO—w¤Ÿ”#\åH9\êZ9‚®\Ş†s§;vƒ¡j5ˆdŠ†lQŞ‹…RH¥Z0\ê½¡ö\n¤;Î´g7\åhÕ£z*AF\Z\ÙUˆ±I\àt\Ü#\é˜Ev™RH\ãCæª³¹\ŞNIµ\æ´t\×$\ÒôH\Ñk‹¢c»=Å˜{»pÀo\n>\Êc\Æ8N‡®\0§]=´-5$t:ˆH›0qp³g3\ælc8¤\æX\r¥`\\qùóŸÿœO\â\ÕV\rj\İ\ì™tP \È3c…³x\Î\"\çœsNö™\Ï|¦\Õsw\ß}÷+r\ç¹~n‚@Tõÿõ¯]\É3Oa’¢rş3\Ï<SùnÀ÷\Ã\èyğÁ³ƒ>8)Oaµ$®\àòŠ\ÕQŒ‹Eùº“R©Ä¤t`˜¬~¾óCa¢º\É\ê\ĞCmh‹®“N:iTTğ\Ú*\ÒÅª>ı°\Új«UC±£\ÓpZV÷7\Şx\ãº\çC\Ñ3fL¸\ìÓ»z…)ô½ı&\åù¡.mO?)GÊ‘r\Ôur”ñ®\Ş†st\ĞAÃ…\è!nÕ˜:uj>¦›O\Ñ\ÉÁ\'%*‚,r\ZµZ.¼ğ\Â,Œ…Š’\ï\\M˜0¡¦\ÑC´Q\ÑÁQ<9Š+˜\ì\r>®§\'\åG9\ê]rT\r\Æ&\Î)\Z…|\ãc\Zs\Îb\n\nN8\Æ,\éu8%\Ç\Ôù‰u\Æ7cû6óD*“1…\ã\0y\æ9>§XccÆŒ¹ó»]v\Ùeù\çE‡!ò£\áR\Şı\îw·Š¼üò\Ëó{EQnp&½ÇµR¯\è«_ıj+‡$N«ZÎ¾&CK—.\İ;DnŠó{gÁ½µ\àÜ¯¯\ÛB†‰t(ı¬˜–oXù–u<÷\Æ7¾1W\ì\ÂM®\â\ÙLa (7{&¦\Ï}®şÖ½\ßü\æ7³3\Ï<³ò?\Û^{\í•\çwW«X’ÆªD±\â>!\ŞÅ•\ÔC\ï½÷\æŸË”\ÕW_=WHù¼\ßı\îwy\á:&L>‹·£v\Ûm·Š!V&µ?ü\á5W\Èzh²Z3L<£ƒ.^¼ø\Å01_»rşüùW‡‰ÿKAÁ\Ú7(§Eº\ÚbŸ}ö\ÉûU% |e\"|îŠ›ø A\Ùşû\ï\ß\Ğù\Â\ç®6zô\è\å\ãÆûyPô>}\É%—\\\İ\r]E!$ª5ı´$Œu¬‡Kjõ“r¤)G]+G‘tˆ»À\Ä1\ÊX\"7¿\ìˆR/¹\ãı\é\È¿Á)§œ’]„\ß3¾#ôá»óŸG\\ \Ëü&üND2![¢v¸nD!q\r8‘{>ó\Ãşp+C,\Z\\¤JEù ú‚ˆ£x\×\Æyù|¾{\è\ãü/²\Â9¹\ÇDƒGD=9SV-9ªF<P\ëb—]v©\ìHD$i8r\0\'N0\"\ë[Œ\ã÷¼\ç=y´\Zğ<÷ş\Ôá“‚ó#Q›—^zi¾R:\ç \ß\Ô\çÀ™Aeœ|\Ît\Òüb4\\*“\È\ï\î»\ï;Ù·‰\'¶ª\İ¤7\"¯89;\î¸ü¹«®º*?7s¯\ãXG†z3\ÊPxn‡ø8Mo\ì\n8ÿ_şò—ø/Ÿ;®/\ÛBF:ˆt#(0x—Y5!\Z%†\Ë/ù\ËùªNqõ„I€‰	£ƒ‰\å€Jöµø\Îw¾“¯*¥+I¼÷\ÔSO\ÍW†	KC\å˜Dx=N(bd„g£¦^u`b\Â\è\ÊA~Lt¬Ä’;^\\\æ»29aT‘«\Î$\Ä\çGeIo>\Ê]„U®\èG¡M\'5\àøŞ¦\ì•A8”p½ğ{=&«©·œ¥\'Ÿ|ò’Z9³µ t‚B($‘TÁ\Ãş\Ù\Ï~¶U\Øk[l¾ù\æ¯ŠA)œû\ç]½\ÂT…¡\áúG–ûia\è£+Wf\ÅI9R”£\ÉQ#»À0ŞŠ0\Ö\ã\Ø\"R€F\Õv™\"¤$\ã6—?	\Ô;aO<9ûÕ¯~\Õ\ê\Ø)S¦\ä\Î7œ‡jD÷ c\'xb\îD(†¤}„\\:4—/$®‰ó\Ç(ˆ\"8\æb”c!\Zx4œ\Z8\0YBn¸`t‘ş\\[*÷\á=UE9\ê=óQ5p\Î0™¨ÁƒŒ0vHG\á9\îÁ!€û6\ÑBD\n\àO\ï\éŒE\Ò\å ¦\×Aq\İu\×e<òH\î|c\Ì\ãô!Mi\Ë-·lµõ2‘CcÇ\Í\Ç+ó´87¦AZ_\Î\Ã‹\Ó\ç2‡¼\á°hk[\ê\Õ\"(ú\0õd¨²=U[=…B\Ô	›õu\ÈH‘n\0#„É„\Éc	P€UJ¶Ÿ\"?r¿ıö\ËN?ıô<|I&Áû®D¥\ç\'\Ï/v\Ñ[\rd*xLQ,97EùŸK\r+&\nØ¥0\éğX\Ù@	\nBş<ùñ(œŸÿü\çs\ÏyT\ĞPğP\\	7gRD¡\ãxÂ™ kŠ+\Ã\\\Ï\ÇU\ê¢QW¸Û‚‰™\ë	“\Ä\É=ôsmD˜¬F 4|úÓŸ~<ôÙº¬(µGÁ‹0\èc\Â1ScšşÁˆŒı\İ‚Ñ½ú\á‡¾\è\Ç?şñ\Ì0‘\Î\×ú\ï\è\'VœøQ?I?…\ßë¶ò¶•#\åH9\ê¸\Å]`ø\îDik˜8\Îpò!sŒ5Œ~B«[)˜Á\0BÖªE¥)?\\òˆ\Ïö²¤)-Y²$5Ÿ4iRşYE8õ—¸“2Hta\Û8b¤Ctş¥‘\È\r†^;8úˆ @¦\æÍ›—§\ÄUZ\"0hœ\Ç\Ç`¬qmq§\ÎE¤H½H\å¨\ËQ=H1a\ìÎœ938 Â‡ñôû\ßÿ>wº\ï\ÃDÄ¨=\æ\"p\Ô-[¶,O‡}S©\ïÀ\ÜB\ã¼\r\Æ9\ÈX\Ç!NZ)}\È;Q¿‡y\rg5\ÑE8&p20¿1¯01¾I_Â™ÁnJœKü\Æ\Ñyÿ2¿\Å(\r@ö9/óóojˆóşjsi{\éa9j%C|­\Ô\ÑÖ•Î¿Ni“t4\Âÿ\é\n(\á\Şi\È7+­m@\ÎÏ¶}õ`E5®\îr\ã¬u<Jù¼E˜\ä\âJi”&T	aŒy÷1÷ÿü\ç?¯ƒ\âÁhJ¿3[\Æ~HwhC¶|«7üö¡O\Ö\ÃH^/\Â\ÖcIˆ]+LŸúÔ§:2‰¯Y>\Ï½DL6B‘†´r¤)G]#G4Œ¸\ç3v1\ìk™FvIWÁ«ü­\Æ8\éŒı`\ç\ÆFFj\0at±ZŒ\Ó—(\"¢€ó4Ò¡Y\áx€³\ã\ç\ÅòXe.:899c\Ü\à\\ˆ†\"\ï\åz\ZY\ÑWú¯\Õ\'\æŒy¢ˆ’#=~ŠµAp–\Æ\ÑÑ‡\Ã\r\ÇyÇ¼7ub\à#¿D\Z\İw\ß}•\ëL|DK\Äy\r\çÿ3ğ?ò\Ç8&eƒ1ƒ¯\ÑzGE7\æa\îq\î\å»\Å9²3\èerTñ\äw\Ö6™µ(\Ücš²>N‘¢–b\Ô((X(M\Z\äâ±‚\Ë$\'9\Âûê…™krr\ë]/+\\¬~DEe•÷y\ä‘¿I\rj\ßmj»\í¶\Ë\È0AÒ‰?W#],+İª£‚‚ü\äó\Ï?¿ö=÷\Ü\Óp\îl”‚R©\ÔJ‰Gñ¦¾\0y\Ê+\Ã\Úk¯M¬\æk\Ây;se©‘~\âK`q´*m®\ãş`¨o\Ür\Ä*&«?¬^’ö\"D)`°°»C„(VE«¥0®Q\"	/9\ßi¸5!¬bqõ“\ëÀx)VqGY¤\âuºZT3.wÊ‘rTm<³z\ÈcL\Æ]`¢±€B†h§siM„Š×ƒ¾ÆÀñQ&\Ëfşş+®\ÙÁ(c•5V\ëg¾ºé¦›Z€8\n97²‹\ã`Ñ¢E­\"¸ö\â6·|\ßøıˆ6\â³87\Ó-!qB\àT\àuœ E\ç\Ç6šB ­ZóQ4‰P\ÃIE4s\nıƒ¡sñõ½\ï}/O=b\Ü20FHM!*€z¤ş\"\Ç\Îmd B_\ãˆ`.*F\ÓU\ç\éH\È\ÑDU	AºQt8XHIY°`A~ Å‚\è\ä\ry©VÓ¨\Ú<…¼ñ98(ódyeõ\Ş.£•–¡06–‡1š+\Åİ©:›tWn\Ë:D¤G\àN8^oVx(D‡²\ÆóL\é\äpÁ\äÏ¥ù€[l±E«‚^±ğO$Vù\Şf›mj\Ãdš\Ş1p\â„\Å$Eøk±H^W\âŒöõ¯ı\ä\Î:g0$kMP/S©—ú\0\0IDAT2ù„Ihhœ˜\Âÿ‹\Âÿ7†6m\èĞ¡\×N™2eñ	\'œ°ÿÄ‰§¤\İ!­\ä·-\Ö) \ì’I˜ß¦½!­a\â~ü¼ó\Î[-\\\ë~A	ººú‰~YúÅ®„‚W­Ÿ‚\Ò\Õ\Ü“ô¡‡š,(p;µœc$l;\æ§ÀXå½ŒaVW¨\Îa¥ˆ\ãPôIe•c‰\âŒµd)-$\É\ï†ü\Ähƒ\Ô9‘\îÁ\İ\åN9\ê¿r„ñÀö¬\ìƒ.¤‰»À`ì³²YM6\Ù$ÿ‹ñNŒ{Î‡1•\Ö.\ásImÀ‰l¥[\Ú\"S\ÈK­UX\Ş\Ëo\Ç{™c\Ú\\q@¤ò‚|\Äó¤‘´4Ò\íki\ìˆ\ÑV?w†Qœ€{/s\Å\ã\Æ8Í¶\Új«\Ü)F$@\Zµ‡1Î˜¤\á\ÔbS…û±ş	÷|\ÆU½GDFjE~+\Òöp„bAM!tD\Æ?s‡t‰ ÷8\ßx/\rGv”!RŠ\éE8ú¸n\"’\ØIƒz,\Ükø¼\Ô\êl9\êˆ…ùu·7^\ÃiÔ•NÎŸğ”N\éVğHs£\'Ôš÷V§(\Z†+IÕ¼\Ñ¶7\âø4D;Ò*\ßÕ®\rC¥£‰÷2y\âY\Çs\ß\Ş\Õ\Ö^\ÊÿÊŠ\nÕ´VcÅ§ütIøş7…¥\ÕLq\Úi§]z\Ì1Ç¼ŒÜ™\á\ïÀFW˜P\ä\ÈoNW\Ó\Ó&yòùù½\Z]yJ\á\ã&LX-(\"tuµğpKÊ“wVVğ\êöSg@Q|.\æ‘¢Jÿ¥5À`\ÂñFep\ê1KK¢Q/gD\Ñ\ĞAùŠOş(ˆ(t\á7\Í\Ï_T¸‘¡Xƒ\"…÷!©Ò¶\nÊr\ÔCr\Ô»À0/\Ån>#„Â©€1†S€Zr\Î9©ü\åØ¶\"ğb*+Æ¼‡\ëEö0\Æ5G¦\Çb¾u\Ì}‡4\Ò±¸\r`5+\ê?Dn¿ıöÊ®1Ê‘óQ\n\ã’q\Åı7:\é¨T\Ë£!\Êı‹\Ï-n‡Dğ!Oœ/~Vµ4Bô¼øz95!Ÿ+H¢–c¹\'`,RYË‘_-B9%\çœ\r8Yø8»‰ú#ò§\ï\ÇùRtÀ÷Å¹(|¯»£Ó\ï·\Õ\í\n\ÒûM\àn\"Ò­°…+´1Œ›ª\Çl›\ÇdLD°«•Á\Ùn,*–\ÕH)VW¹©\Æ\Õ\'&ª¸z\Ê\äˆ7\ï9¡{\\†\Ç\àf%˜ğ½~\Ä\ê\ÉD53´_\ÅõjVF\ê½iÒ¤I³‚‚·_ø;³Ñª\á¿ø\Å/Z­dIM\0V%c¥yş\É\ê~[…|şÄ‰›ºCÁ++vC\Û\ÛO±\Êj[,(g\äqSğ.…Š\âô%[˜¡xaEƒ<\\©‘€\Ãg\\\ï´\ÓNùš(•GuT6{ö\ìŠÃƒ÷¤PHUÓ”¸!FKª„­br§õ°Q³\0\Çp¬^*\Ìx\"$<\îê®N\Æ]`€]`ÿÕœE8\å0Jp4\Äb­±h*\ã–t¿h\èõp\Æg\ä\ç¦\0_­¨†9s\æ\äòÀu\â\\ˆˆ+µ8\ÓY]\åZ9>%uØ¥‘Ep¼`@\Å|tœ\'\È\×MŠbL+AFIO\éH}\å¨\ÎG\Ø8®‰B`œ¡w1\Şk9·ˆœKS›\ÅÔ¦X\"M\Ëcl}TX¯¼ü>\È#‘¤9A}\n\Ê2g±sÅ—™#‹õ†¢>’\Öh 8%ó\ã\0\ç>ó8¿Q\ä’92•/œ–I±\è-2\Ô\Ü\Üü»0–ö‹Nœ¶R”;\çOı<:D¤\Ûa\Å\Ï5\Ê“^\ë+HQ¹+‚‚D¨t…3\ZF\í­òQ†\Â\È\Ç\ä\Ê\Ç$\ÄJ\Z\ç!\â¢Z\è^_„0»ğ\ç\æ \è^ŒÒ›Û»2‚¢&ğ\áar¿$(öƒ\ë)z((!©’\Çj69[_FŒ”VR\êm\ßD\ë¸q\ã†Q¨«V”:\ÔO+\Ê†¹²)8\æˆ\0b<|òÉ•q±…rÄ¸\Åp‰\Ê+®±Ï‘-Œ ¢0°p\Ô\á\Ğ\ã½TµÅ·b•ğb¸9rÀ\nlq\Õ\'\Ç\â ‰N‡UIî”£•£®\Ş†nV7y…œvj¡DC(\æı\ÇrŒ!¶ËŒN5\"(ä’ôøb8\ÛÑ€!Cˆx-\'\Ôg°òN´Pµº\Ç\ÈV5#0î¢3$\Ş\Î<óÌŠSš)8ùqLv\å*§r\Ô7\ç#\æ\"¾,\0\á\Ü¶>$u(RL5 : :Û%\æR\Z`üøñ•û~\ÑyM\Ú¿[5C>\Ê+!\ïlM?\Ñ‘\'\æLÀÙ€ó‡#òK”=ğQ.qH2·–Ì\Ì9\È^¬#Dú\r\ç&\Zó\0óe\\+\çª\í\ÑWd(Ü›f-[¶ŒÄ¶ÀŒı\âöÃ¤Ù³gFT*?5S§ƒˆtLø(‹l“•GŠÔ¡dÅ½Y\á‰a\ÛJ=(b(Q…²˜N\éş\ìĞ*\ß(#œ—›.+f„ZR\'Å‘\ë Sœ€ú(\'–J¥yMMM3;º2\Õ+ö\ßÿ\İ&Oü«¯|\å+Cj)zlkW6\0BSü–D· \È\ÇU‡_|1?¡X´0*xÁg\ß\é¦OŸ>«/ôS{ X…¤_ˆ\0@FP~¢’G7Vu0XP–	[%§\å\í\Ì\"ô%ÿO˜0¡\å@øh_e9L·ôwJ!ô\ã)\rGF\é\Ãiˆ±R\Í^\äN9\êr\ÔÕ»À¤`d\Ói\í‘F·uó]#\×V„•øz+®\\Cz©¬®l•\åhÕ˜\â¸\Âñ‹#_iš[Q&\Ò(Ã¹ñ}E‡:°j‘:‘4\â‡0§\àø+a~÷´ 1\ÎÂ´ÀrtHF\'E-øœ¢#\ë\ìO:İ´i\Ó5j\Ô\Ía\Îİ‘”#¶7­–\ÓQpò…\ß\":n1c\Æ<\"\Òm0\ÑJ=u\ê\ÔJ7&<\âqRA`\ÂA‘‹\Ê\\,\0\Ä\äG®_½ŒF«|Ã¬Y³²#8\"O\Ù@1Ã«‘F\Åb&I7rŞ‹Jn_\"\Ü\ì¿Ó™\ç»ô\ÒKo“\Ö^gŸ}öG}tSQ\Ñ\Ã\È$t7]=dxV\ì£•q€C	eUB:\Ù!¥;sf;»Ÿ\Z\å,NúŒw”^sŒwú‡oú\ÇCjdÎº4R%\å*\ra\å~œœ“PUR,R%±Ht4°:”\Âj\n«#¬ş}±ªÈr\Ô{\å¨†<û}•£>9U5¶\nsD[uv\Z©‰Q\Ï\á\Ğ\Ö|Ùk\í\È÷\ì\Ç2„2±#p:\à \éÌ¨\'\"–¯º\êªJ”C©Túq¸\ï\ÈD¤\ÏA®jÌ¹%š¡Xµ˜U\Şbu\éT\ÙÂ‰E†jMñüDE\à\È\àq1,\ÏÀØ‰Eûx\Ì\ê+¹}x·	ÿfª/;ºŠ\Ğÿ\×.Y²\äS“&MZLˆ^„:Œc”¶\ÌM\éoŒ{\ÑEU\Â\\%¼ğpDw\ä\Ìöe79V\ß\êŒ]òRc£¨[‘al“»·Œ+Eq{¿(©sÿQHp‚Dˆ² ä›¨„bQH\åN9QDz½¼¡s3Y¨r\Ó\æÂB$\å\äÉ“—yªD9ÌŸ?F\è7#Dúõ¼Ô¬F°}+²õ \Â>9¶ä¢³j[\Ìym´\Ê7…‘XQE,qvPÈ‹\\]B\Ä)(µ*+zû\ï¿ÿ\ãÇ¿\ê˜c\ÉW˜¨¸^\Ï!a%¤\ŞqqE	\ÏYUú”´rHcZy­\Å(\âñ¤¥\ÛJÁºÔ™·¨%!n¥‡Ó€´öGæ‚¢P9qÁ1œ–<pœ\ÕV©”;\åHD9\éP©™2\ëPS‰\Ú\è\ì\Èš8&Mš\Ôü\È#Ä¼¤ÿ•J¥\Ão¾ù\æ¥ı\Â~qÌˆô=\È\ï\Æ8¡alDb\Ê\Å~Š\Î	\ê6gIq<\'&\n\ã\í¾û\îùª(7\Í¯U[q¥rwºz\Z·\å¤r1\Ş^#Œ-\Ò;6\ß|ó\Ü#B_RB[Cÿ\í\É\n\Ó\ìÙ³—u\Æ9QğB¿!„uURğˆj e-\0\ãª\ã\Õ J õ\Øp\n£‡0ş;\ì°\ÜĞ\éD\Z\àÔ vÛQ\å;\ÖV‰°•ş¹\nœµP\î”#\åH¤÷C…\æ\æfª\á\æœÿ¤K’\Z±2ğ¾“N:iÙœ9sb„i_2óş\Òg:DúSTô\Æ	Õ†SX­VµŠ\á&„E²‚\n2¤j°:ŠñUX\å»¬Ä²‚K‘<¶Q\"—<^Å¤nº\é&¸\Æ½%Uôbk0F^•BX™ğ\Ù\n\ì\ÔSOÍ£\È?\Æùpñ\Åg{\î¹g\Í÷x±C‰#DOµ@U|\Æ:\Õ\É\ßQ\îp\âñ|\nJ’\ïÉ®\0ºŒòW\åN9QDz/aL“öpxt<°x‡\Ş1mÚ´ªÛ˜Vƒ\ãH;vl\Z\á@q\è\ã§OŸ>µ?õW\É!#\Òqn¸\á†\æb¥\ß\î#«+Šø4²¯ro\Û{™\ív\Úi§>wF\ë®¼|ô\è\ÑC7\Ûl³\í}_a\í	9Z™\â6›\í}­¯Ér¤‰r¤‰rTŸ#FŒ\Î-•Jk\Æ\çØ¹…HGû\Ø>–T%R/ˆx\Ä\ÑÀVØ³gÏ¦-_²dIª4P…ú\è3fL\éo¿¡5Dú‹0wQ\Õ\àFŒšU­òyWb½}Ç7ó\Øc¼\Å[4Ü±i‘.CXge¶”¬\çT\è‡ƒr§‰(G\"}\"Fù\çR©t[iò©w\Şyg\Ş\ÚR)’Ç·RÃ¡?¥T\Ôú¢\"\"\Ò½—_~y\ï‰\'¾<{ö\ì—\ZUğ\Ø÷ÜœY\åHD9\é^¨ñ\Æ;{i/•J\äY¶§øã­¡}\æ±\Çûhu8\èté…S‹¢7aÂ„—\î¸\ã\ç\ë{\ß}÷\Í7n\å’1gVD9QDz\Ìù0\ç\ÃÀ7*•JÇ†§f†6/´\Å\åCøûph\ÈÇ˜\Ğ6\n\ïÙtŠş²KE-L¯é¥ŠŞ¨Q£†Oœ8‘œ\Úg\ßÿş÷¿\" \\¼©¹¹ù€ò¾\Ñ\"¢‰(G\"=È´i\Óp,L*7ÉŒt\éµšºt\éÒ½Æ_ºıöÛŸM_K«‚«\à‰(G\"Ê‘ˆôVt:ˆˆôbXa\nŠ\Ş>“&M*\İv\Ûm£‚sf\raQD”#‘NgDh$ÿ“!pVï»¶A{\ãĞl\ã>oõ\Ğ\Ş\Ú\Ö\å\çv\rmm\"\"\ÒUŠ\ŞÀşğ‡«\à‰(G\"Ê‘H—²]h&ÿSs\áU\å\Çq7—¦Ğ¶\ÈZœ±Qtõ\Í\Éÿ\ß\rm­òñ8v+·MC{Oò?\í3…kÀ¹À6šo\nm½òs„ö´N\éE/üq\Ë-·0ñŒPÁQD”#‘.a³\Ğ~\Ú\ÛCÛ£\Üp\nlXş{|h\ëf-…!g‡öd\ÙAA{>y\Ì\ë\ßmaù¼—¼&ió’Ç—ÿ‡ËŸó\ÆĞ¾œ8`yù\ï¦:DD¤\Ó!§¶©©i-·!QD”#‘.(†\ÍC›\ÚQ¡İµ\ì6‘:	N);\Z\"We+¢Ş<>»`oÚ–YK\í\Éc¢#n*wùs\n\íûe‡\'–Ï‡\Ã\ãø²SB§ƒˆˆt.S¦LYd/ˆ(G\"Ê‘H—\ĞTv`\Ô_µ¤2Œ(;^m\ï\Ğ*¼\ç‰lE\ÄÂ£\Éc¢ –\'\Çù@‡\ç\ÊmIòx÷\Z\×\Â\ç­W>×©\åó‘úqHhô•Nu\ËL‘,cw–Bû¿\Ğn\ÉZ\"~]~şšò1\ë\Şóº¬\Å)Á{Ş’µ\Ôf :a\ë*\ç\ç½;–¿%yœ\ì\Úg³\çÇ¬ò¹‡$¯ÿ¢¯uªN‘¦†vEhŸ\Z&cBûSù5¢Ş›µ¤XD\Æg-iDDü7k)&yMò”Ç³Î‹İ’ÇŸJùKh\í\ÜşÒ¡:DDDDDDDZ\Ø%kÙ­[™‚ó\ç\0[X¾ºpüoC›\ÚWC;\'´\ëC;#´“k\Øß»•o˜<~,9fq\ë\Ú:±\ß\ßÚ¿³G¯G§ƒˆˆˆˆˆˆHKQGœ¤SPGH¢b\ZDS\áx\Ò%>\Ú\Øl\Å.™üIÖ’–C\âü¬\Åqé¶˜\ìŠ\×®†%ŸyDù½8CHµ`g\rv¹¾/tªN‘–Â‘—„voò\Ü\ÃY‹óˆt\Ø2q	qzrlŒ<`\nŠCR\ça~\Ïz©\Î5\Ú]\åÿ©-1·|3³–4øS_\éT\"\"\"\"\"\"\"-\Ü[øÿg\É\ãÿe+¶¶|º\ìHI#^h\Ã1pL\×~š<~2[±E\ç\ßÊ­Oá–™\"\"\"\"\"\"\"g¹]ğJt:ˆˆˆˆˆˆˆH— \ÓA¤3iÀ€\æ\å\Ëulö\Â\ïğ`\ÖRdG”#Q”#QD9R¤§e\Ê.\é8MMMO<÷\ÜsvD/`\áÂ…Ú¹ÛPD9RD9\åH9’G§ƒH\'0xğ\à[Ÿy\æ;¢ğ\Øc±\Å\Ñ\Íö„r$Ê‘r$Ê‘(GÊ‘ô<:D:Å‹Ÿ5şü—›››íŒeŞ‚Øš\èr»B9\åH9\åH”#\åHz\"Àö\Ûo[˜˜\î}ô\ÑG\íŒd\îÜ¹7„\ß\áÑv\Ú\é\Ïö†r$Ê‘r$Ê‘(GÊ‘ô<:D:‰Å‹8oŞ¼}öY;£X´hÑ•,\Øsùò\å\Ç\ÚÊ‘(GÊ‘r¤‰r¤I\ï` ] \Ò9L:õ©ƒ:è¶§Ÿ~z¯aÃ†5\r:\ÔN\éÆ‰iÎœ9\ï-•JG\í¼óÎ·\Ú#Ê‘(GÊ‘r¤‰r¤‰N‘~\ÇE]tÿx\Ç\ã?¾w¸Q>¿\Æ\Zk¬ş\Ú1]Ç¼¹s\ç^6oŞ¼11\í¸\ãW\Û%Ê‘(G¢)G¢)G\Ò{PjDº€ë®»n\ÓL4h\Ğ\æë®»\î\Ãë¬³\ÎúÃ†\r{}x\Î\Î\é \ì\×\ÌöIT3¦¸¹~„\Ş\í²\Ë.s\í\åH”#Q”#Q”#\Ñ\é ²\Êpıõ\×oV*•ö\r?\ÚBk²W:\ÌÒ¬e¿\æ›C»\Ü\âBÊ‘(G¢)G¢)G\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\Ò\ÇùÀ\Ñ\Ãl^D¾\"\0\0\0\0IEND®B`‚',NULL);
/*!40000 ALTER TABLE `act_ge_bytearray` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ge_property`
--

DROP TABLE IF EXISTS `act_ge_property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ge_property` (
  `NAME_` varchar(64) COLLATE utf8_bin NOT NULL,
  `VALUE_` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  PRIMARY KEY (`NAME_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ge_property`
--

LOCK TABLES `act_ge_property` WRITE;
/*!40000 ALTER TABLE `act_ge_property` DISABLE KEYS */;
INSERT INTO `act_ge_property` VALUES ('next.dbid','150001',61),('schema.history','create(5.22.0.0)',1),('schema.version','5.22.0.0',1);
/*!40000 ALTER TABLE `act_ge_property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_actinst`
--

DROP TABLE IF EXISTS `act_hi_actinst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_actinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin NOT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CALL_PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ACT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime NOT NULL,
  `END_TIME_` datetime DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_ACT_INST_START` (`START_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_PROCINST` (`PROC_INST_ID_`,`ACT_ID_`),
  KEY `ACT_IDX_HI_ACT_INST_EXEC` (`EXECUTION_ID_`,`ACT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_actinst`
--

LOCK TABLES `act_hi_actinst` WRITE;
/*!40000 ALTER TABLE `act_hi_actinst` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_hi_actinst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_attachment`
--

DROP TABLE IF EXISTS `act_hi_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_attachment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `URL_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CONTENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_attachment`
--

LOCK TABLES `act_hi_attachment` WRITE;
/*!40000 ALTER TABLE `act_hi_attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_hi_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_comment`
--

DROP TABLE IF EXISTS `act_hi_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_comment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime NOT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `MESSAGE_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `FULL_MSG_` longblob,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_comment`
--

LOCK TABLES `act_hi_comment` WRITE;
/*!40000 ALTER TABLE `act_hi_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_hi_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_detail`
--

DROP TABLE IF EXISTS `act_hi_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_detail` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TIME_` datetime NOT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_DETAIL_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_ACT_INST` (`ACT_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_TIME` (`TIME_`),
  KEY `ACT_IDX_HI_DETAIL_NAME` (`NAME_`),
  KEY `ACT_IDX_HI_DETAIL_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_detail`
--

LOCK TABLES `act_hi_detail` WRITE;
/*!40000 ALTER TABLE `act_hi_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_hi_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_identitylink`
--

DROP TABLE IF EXISTS `act_hi_identitylink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_identitylink` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_TASK` (`TASK_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_identitylink`
--

LOCK TABLES `act_hi_identitylink` WRITE;
/*!40000 ALTER TABLE `act_hi_identitylink` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_hi_identitylink` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_procinst`
--

DROP TABLE IF EXISTS `act_hi_procinst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_procinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `START_TIME_` datetime NOT NULL,
  `END_TIME_` datetime DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `END_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `PROC_INST_ID_` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PRO_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_PRO_I_BUSKEY` (`BUSINESS_KEY_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_procinst`
--

LOCK TABLES `act_hi_procinst` WRITE;
/*!40000 ALTER TABLE `act_hi_procinst` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_hi_procinst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_taskinst`
--

DROP TABLE IF EXISTS `act_hi_taskinst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_taskinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime NOT NULL,
  `CLAIM_TIME_` datetime DEFAULT NULL,
  `END_TIME_` datetime DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `DUE_DATE_` datetime DEFAULT NULL,
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_TASK_INST_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_taskinst`
--

LOCK TABLES `act_hi_taskinst` WRITE;
/*!40000 ALTER TABLE `act_hi_taskinst` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_hi_taskinst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_varinst`
--

DROP TABLE IF EXISTS `act_hi_varinst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_varinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` datetime DEFAULT NULL,
  `LAST_UPDATED_TIME_` datetime DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_PROCVAR_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PROCVAR_NAME_TYPE` (`NAME_`,`VAR_TYPE_`),
  KEY `ACT_IDX_HI_PROCVAR_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_varinst`
--

LOCK TABLES `act_hi_varinst` WRITE;
/*!40000 ALTER TABLE `act_hi_varinst` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_hi_varinst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_id_group`
--

DROP TABLE IF EXISTS `act_id_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_id_group` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_id_group`
--

LOCK TABLES `act_id_group` WRITE;
/*!40000 ALTER TABLE `act_id_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_id_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_id_info`
--

DROP TABLE IF EXISTS `act_id_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_id_info` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `VALUE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD_` longblob,
  `PARENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_id_info`
--

LOCK TABLES `act_id_info` WRITE;
/*!40000 ALTER TABLE `act_id_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_id_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_id_membership`
--

DROP TABLE IF EXISTS `act_id_membership`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_id_membership` (
  `USER_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `GROUP_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`USER_ID_`,`GROUP_ID_`),
  KEY `ACT_FK_MEMB_GROUP` (`GROUP_ID_`),
  CONSTRAINT `ACT_FK_MEMB_GROUP` FOREIGN KEY (`GROUP_ID_`) REFERENCES `act_id_group` (`ID_`),
  CONSTRAINT `ACT_FK_MEMB_USER` FOREIGN KEY (`USER_ID_`) REFERENCES `act_id_user` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_id_membership`
--

LOCK TABLES `act_id_membership` WRITE;
/*!40000 ALTER TABLE `act_id_membership` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_id_membership` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_id_user`
--

DROP TABLE IF EXISTS `act_id_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_id_user` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `FIRST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LAST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EMAIL_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PWD_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PICTURE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_id_user`
--

LOCK TABLES `act_id_user` WRITE;
/*!40000 ALTER TABLE `act_id_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_id_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_procdef_info`
--

DROP TABLE IF EXISTS `act_procdef_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_procdef_info` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `INFO_JSON_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_INFO_PROCDEF` (`PROC_DEF_ID_`),
  KEY `ACT_IDX_INFO_PROCDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_INFO_JSON_BA` (`INFO_JSON_ID_`),
  CONSTRAINT `ACT_FK_INFO_JSON_BA` FOREIGN KEY (`INFO_JSON_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_INFO_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_procdef_info`
--

LOCK TABLES `act_procdef_info` WRITE;
/*!40000 ALTER TABLE `act_procdef_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_procdef_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_re_deployment`
--

DROP TABLE IF EXISTS `act_re_deployment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_re_deployment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `DEPLOY_TIME_` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_re_deployment`
--

LOCK TABLES `act_re_deployment` WRITE;
/*!40000 ALTER TABLE `act_re_deployment` DISABLE KEYS */;
INSERT INTO `act_re_deployment` VALUES ('135064','æµç¨‹å®¡æ‰¹æµ‹è¯•æµç¨‹',NULL,'','2018-01-07 10:09:38'),('145001','new-process',NULL,'','2018-01-09 11:32:28');
/*!40000 ALTER TABLE `act_re_deployment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_re_model`
--

DROP TABLE IF EXISTS `act_re_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_re_model` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_TIME_` timestamp NULL DEFAULT NULL,
  `VERSION_` int(11) DEFAULT NULL,
  `META_INFO_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_EXTRA_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_MODEL_SOURCE` (`EDITOR_SOURCE_VALUE_ID_`),
  KEY `ACT_FK_MODEL_SOURCE_EXTRA` (`EDITOR_SOURCE_EXTRA_VALUE_ID_`),
  KEY `ACT_FK_MODEL_DEPLOYMENT` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_MODEL_DEPLOYMENT` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_re_deployment` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE` FOREIGN KEY (`EDITOR_SOURCE_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE_EXTRA` FOREIGN KEY (`EDITOR_SOURCE_EXTRA_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_re_model`
--

LOCK TABLES `act_re_model` WRITE;
/*!40000 ALTER TABLE `act_re_model` DISABLE KEYS */;
INSERT INTO `act_re_model` VALUES ('142501',8,'new-process','process',NULL,'2018-01-08 08:10:30','2018-01-09 11:32:30',1,'{\"name\":\"new-process\",\"description\":\"\",\"revision\":1}','145001','142502','142503',''),('145005',4,'new-process','process',NULL,'2018-01-09 12:00:51','2018-01-09 12:01:00',1,'{\"name\":\"new-process\",\"description\":\"\",\"revision\":1}',NULL,'145006','145007',''),('147501',2,'new-process','process',NULL,'2019-04-15 03:47:59','2019-04-15 03:47:59',1,'{\"name\":\"new-process\",\"description\":\"\",\"revision\":1}',NULL,'147502',NULL,''),('57501',31,'æµç¨‹å®¡æ‰¹æµ‹è¯•æµç¨‹','test_audit02',NULL,'2017-11-12 00:42:13','2018-01-07 10:09:38',1,'{\"name\":\"æµç¨‹å®¡æ‰¹æµ‹è¯•æµç¨‹\",\"revision\":1,\"description\":\"\"}','135064','57502','62501','');
/*!40000 ALTER TABLE `act_re_model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_re_procdef`
--

DROP TABLE IF EXISTS `act_re_procdef`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_re_procdef` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DGRM_RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `HAS_START_FORM_KEY_` tinyint(4) DEFAULT NULL,
  `HAS_GRAPHICAL_NOTATION_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_PROCDEF` (`KEY_`,`VERSION_`,`TENANT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_re_procdef`
--

LOCK TABLES `act_re_procdef` WRITE;
/*!40000 ALTER TABLE `act_re_procdef` DISABLE KEYS */;
INSERT INTO `act_re_procdef` VALUES ('process:1:145004',1,'http://www.activiti.org/processdef',NULL,'process',1,'145001','new-process.bpmn20.xml','new-process.process.png',NULL,0,1,1,''),('salary:3:135067',1,'http://www.activiti.org/test','æµç¨‹å®¡æ‰¹æµ‹è¯•æµç¨‹','salary',3,'135064','æµç¨‹å®¡æ‰¹æµ‹è¯•æµç¨‹.bpmn20.xml','æµç¨‹å®¡æ‰¹æµ‹è¯•æµç¨‹.salary.png',NULL,1,1,1,'');
/*!40000 ALTER TABLE `act_re_procdef` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_event_subscr`
--

DROP TABLE IF EXISTS `act_ru_event_subscr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_event_subscr` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EVENT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EVENT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTIVITY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CONFIGURATION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EVENT_SUBSCR_CONFIG_` (`CONFIGURATION_`),
  KEY `ACT_FK_EVENT_EXEC` (`EXECUTION_ID_`),
  CONSTRAINT `ACT_FK_EVENT_EXEC` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_event_subscr`
--

LOCK TABLES `act_ru_event_subscr` WRITE;
/*!40000 ALTER TABLE `act_ru_event_subscr` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_event_subscr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_execution`
--

DROP TABLE IF EXISTS `act_ru_execution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_execution` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_EXEC_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `IS_ACTIVE_` tinyint(4) DEFAULT NULL,
  `IS_CONCURRENT_` tinyint(4) DEFAULT NULL,
  `IS_SCOPE_` tinyint(4) DEFAULT NULL,
  `IS_EVENT_SCOPE_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `CACHED_ENT_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EXEC_BUSKEY` (`BUSINESS_KEY_`),
  KEY `ACT_FK_EXE_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_EXE_PARENT` (`PARENT_ID_`),
  KEY `ACT_FK_EXE_SUPER` (`SUPER_EXEC_`),
  KEY `ACT_FK_EXE_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_EXE_PARENT` FOREIGN KEY (`PARENT_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_EXE_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_EXE_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ACT_FK_EXE_SUPER` FOREIGN KEY (`SUPER_EXEC_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_execution`
--

LOCK TABLES `act_ru_execution` WRITE;
/*!40000 ALTER TABLE `act_ru_execution` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_execution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_identitylink`
--

DROP TABLE IF EXISTS `act_ru_identitylink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_identitylink` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_IDENT_LNK_GROUP` (`GROUP_ID_`),
  KEY `ACT_IDX_ATHRZ_PROCEDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_TSKASS_TASK` (`TASK_ID_`),
  KEY `ACT_FK_IDL_PROCINST` (`PROC_INST_ID_`),
  CONSTRAINT `ACT_FK_ATHRZ_PROCEDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_IDL_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TSKASS_TASK` FOREIGN KEY (`TASK_ID_`) REFERENCES `act_ru_task` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_identitylink`
--

LOCK TABLES `act_ru_identitylink` WRITE;
/*!40000 ALTER TABLE `act_ru_identitylink` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_identitylink` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_job`
--

DROP TABLE IF EXISTS `act_ru_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_job` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
  CONSTRAINT `ACT_FK_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_job`
--

LOCK TABLES `act_ru_job` WRITE;
/*!40000 ALTER TABLE `act_ru_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_task`
--

DROP TABLE IF EXISTS `act_ru_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_task` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DELEGATION_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `CREATE_TIME_` timestamp NULL DEFAULT NULL,
  `DUE_DATE_` datetime DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_TASK_CREATE` (`CREATE_TIME_`),
  KEY `ACT_FK_TASK_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_TASK_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_TASK_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_TASK_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_task`
--

LOCK TABLES `act_ru_task` WRITE;
/*!40000 ALTER TABLE `act_ru_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_variable`
--

DROP TABLE IF EXISTS `act_ru_variable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_variable` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_VARIABLE_TASK_ID` (`TASK_ID_`),
  KEY `ACT_FK_VAR_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_VAR_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_VAR_BYTEARRAY` (`BYTEARRAY_ID_`),
  CONSTRAINT `ACT_FK_VAR_BYTEARRAY` FOREIGN KEY (`BYTEARRAY_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_variable`
--

LOCK TABLES `act_ru_variable` WRITE;
/*!40000 ALTER TABLE `act_ru_variable` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_variable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_content`
--

DROP TABLE IF EXISTS `blog_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blog_content` (
  `cid` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT 'æ ‡é¢˜',
  `slug` varchar(255) DEFAULT NULL,
  `created` bigint(20) DEFAULT NULL COMMENT 'åˆ›å»ºäººid',
  `modified` bigint(20) DEFAULT NULL COMMENT 'æœ€è¿‘ä¿®æ”¹äººid',
  `content` text COMMENT 'å†…å®¹',
  `type` varchar(16) DEFAULT NULL COMMENT 'ç±»å‹',
  `tags` varchar(200) DEFAULT NULL COMMENT 'æ ‡ç­¾',
  `categories` varchar(200) DEFAULT NULL COMMENT 'åˆ†ç±»',
  `hits` int(5) DEFAULT NULL,
  `comments_num` int(5) DEFAULT '0' COMMENT 'è¯„è®ºæ•°é‡',
  `allow_comment` int(1) DEFAULT '0' COMMENT 'å¼€å¯è¯„è®º',
  `allow_ping` int(1) DEFAULT '0' COMMENT 'å…è®¸ping',
  `allow_feed` int(1) DEFAULT '0' COMMENT 'å…è®¸åé¦ˆ',
  `status` int(1) DEFAULT NULL COMMENT 'çŠ¶æ€',
  `author` varchar(100) DEFAULT NULL COMMENT 'ä½œè€…',
  `gtm_create` datetime DEFAULT NULL COMMENT 'åˆ›å»ºæ—¶é—´',
  `gtm_modified` datetime DEFAULT NULL COMMENT 'ä¿®æ”¹æ—¶é—´',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='æ–‡ç« å†…å®¹';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_content`
--

LOCK TABLES `blog_content` WRITE;
/*!40000 ALTER TABLE `blog_content` DISABLE KEYS */;
/*!40000 ALTER TABLE `blog_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oa_notify`
--

DROP TABLE IF EXISTS `oa_notify`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oa_notify` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ç¼–å·',
  `type` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT 'ç±»å‹',
  `title` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT 'æ ‡é¢˜',
  `content` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT 'å†…å®¹',
  `files` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT 'é™„ä»¶',
  `status` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT 'çŠ¶æ€',
  `create_by` bigint(20) DEFAULT NULL COMMENT 'åˆ›å»ºè€…',
  `create_date` datetime DEFAULT NULL COMMENT 'åˆ›å»ºæ—¶é—´',
  `update_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT 'æ›´æ–°è€…',
  `update_date` datetime DEFAULT NULL COMMENT 'æ›´æ–°æ—¶é—´',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'å¤‡æ³¨ä¿¡æ¯',
  `del_flag` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT 'åˆ é™¤æ ‡è®°',
  PRIMARY KEY (`id`),
  KEY `oa_notify_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='é€šçŸ¥é€šå‘Š';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oa_notify`
--

LOCK TABLES `oa_notify` WRITE;
/*!40000 ALTER TABLE `oa_notify` DISABLE KEYS */;
INSERT INTO `oa_notify` VALUES (41,'3','åä¹å¤§å¬å¼€äº†','åä¹å¤§å¬å¼€äº†ï¼Œç«Ÿç„¶æ²¡é‚€è¯·æˆ‘','','1',1,NULL,NULL,'2017-10-10 17:21:11','',NULL),(42,'3','è‹¹æœå‘å¸ƒæ–°æ‰‹æœºäº†','æœ‰å…¨é¢å±çš„iphoneX','','1',1,NULL,NULL,'2017-10-10 18:51:14','',NULL),(43,'3','åä¹å¤§è¦æ¶ˆç­è´«å›°äººå£','æˆ‘è¿˜åªæœ‰ä¸¤ä¸‰å¹´çš„æ´»å¤´äº†','','1',1,NULL,NULL,'2017-10-11 09:56:35','',NULL),(44,'3','éª‘å£«åˆè¾“äº†','æ‰æ€¥','','1',1,NULL,NULL,'2017-10-26 13:59:34','',NULL),(45,'3','ç«ç®­5è¿è´¥','æ²¡æœ‰ä¿ç½—ä¸è¡Œå‘€','','1',1,NULL,NULL,'2017-12-30 12:10:20','',NULL);
/*!40000 ALTER TABLE `oa_notify` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oa_notify_record`
--

DROP TABLE IF EXISTS `oa_notify_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oa_notify_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ç¼–å·',
  `notify_id` bigint(20) DEFAULT NULL COMMENT 'é€šçŸ¥é€šå‘ŠID',
  `user_id` bigint(20) DEFAULT NULL COMMENT 'æ¥å—äºº',
  `is_read` tinyint(1) DEFAULT '0' COMMENT 'é˜…è¯»æ ‡è®°',
  `read_date` date DEFAULT NULL COMMENT 'é˜…è¯»æ—¶é—´',
  PRIMARY KEY (`id`),
  KEY `oa_notify_record_notify_id` (`notify_id`),
  KEY `oa_notify_record_user_id` (`user_id`),
  KEY `oa_notify_record_read_flag` (`is_read`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='é€šçŸ¥é€šå‘Šå‘é€è®°å½•';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oa_notify_record`
--

LOCK TABLES `oa_notify_record` WRITE;
/*!40000 ALTER TABLE `oa_notify_record` DISABLE KEYS */;
INSERT INTO `oa_notify_record` VALUES (18,41,1,1,'2017-10-26'),(19,42,1,1,'2017-10-26'),(20,43,136,0,NULL),(21,43,133,0,NULL),(22,43,130,0,NULL),(23,43,1,1,'2017-10-26'),(24,44,1,1,'2017-12-29'),(25,45,1,1,'2018-01-07'),(26,45,135,0,NULL);
/*!40000 ALTER TABLE `oa_notify_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salary`
--

DROP TABLE IF EXISTS `salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salary` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT 'ç¼–å·',
  `PROC_INS_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT 'æµç¨‹å®ä¾‹ID',
  `USER_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT 'å˜åŠ¨ç”¨æˆ·',
  `OFFICE_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT 'å½’å±éƒ¨é—¨',
  `POST` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'å²—ä½',
  `AGE` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT 'æ€§åˆ«',
  `EDU` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'å­¦å†',
  `CONTENT` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'è°ƒæ•´åŸå› ',
  `OLDA` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'ç°è¡Œæ ‡å‡† è–ªé…¬æ¡£çº§',
  `OLDB` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'ç°è¡Œæ ‡å‡† æœˆå·¥èµ„é¢',
  `OLDC` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'ç°è¡Œæ ‡å‡† å¹´è–ªæ€»é¢',
  `NEWA` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'è°ƒæ•´åæ ‡å‡† è–ªé…¬æ¡£çº§',
  `NEWB` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'è°ƒæ•´åæ ‡å‡† æœˆå·¥èµ„é¢',
  `NEWC` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'è°ƒæ•´åæ ‡å‡† å¹´è–ªæ€»é¢',
  `ADD_NUM` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'æœˆå¢èµ„',
  `EXE_DATE` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'æ‰§è¡Œæ—¶é—´',
  `HR_TEXT` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'äººåŠ›èµ„æºéƒ¨é—¨æ„è§',
  `LEAD_TEXT` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'åˆ†ç®¡é¢†å¯¼æ„è§',
  `MAIN_LEAD_TEXT` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'é›†å›¢ä¸»è¦é¢†å¯¼æ„è§',
  `create_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT 'åˆ›å»ºè€…',
  `create_date` datetime NOT NULL COMMENT 'åˆ›å»ºæ—¶é—´',
  `update_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT 'æ›´æ–°è€…',
  `update_date` datetime NOT NULL COMMENT 'æ›´æ–°æ—¶é—´',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'å¤‡æ³¨ä¿¡æ¯',
  `del_flag` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT 'åˆ é™¤æ ‡è®°',
  PRIMARY KEY (`id`),
  KEY `OA_TEST_AUDIT_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='å®¡æ‰¹æµç¨‹æµ‹è¯•è¡¨';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salary`
--

LOCK TABLES `salary` WRITE;
/*!40000 ALTER TABLE `salary` DISABLE KEYS */;
INSERT INTO `salary` VALUES ('825693cd6c1c4f6b86699fc3f1a54887','','136','','','','','æŠ€èƒ½æé«˜','','','','','100','','','','åŒæ„','åŒæ„','æ€»ç»ç†å®¡æ‰¹','1','2017-12-15 22:01:41','1','2017-12-15 22:01:41',NULL,'1'),('a80e1d9ef35a4502bd65b0e5ba7eafff','','cccc','ccc','ccccc','','','','','','','','','','','','','','','','2017-11-30 16:35:15','','2017-11-30 16:35:15','',''),('b5d228f729f74833883917825749f0d5','','','','','','','','','','','','','é˜²å®ˆæ‰“æ³•','','','','','','','2017-11-30 19:58:36','','2017-11-30 19:58:36','',''),('cc2e8083f9d8478f831b2ea852e5c17b','','','cc','cc','','','xxx','','','','','','','','','','','','','2017-11-30 19:18:59','','2017-11-30 19:18:59','',''),('cebdb316794b48be87d93dd4dbfb7d4b','','','','å‘çš„é¡ºä¸°','','','','','','','','','','','','','','','','2017-11-30 19:58:43','','2017-11-30 19:58:43','','');
/*!40000 ALTER TABLE `salary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dept`
--

DROP TABLE IF EXISTS `sys_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT 'ä¸Šçº§éƒ¨é—¨IDï¼Œä¸€çº§éƒ¨é—¨ä¸º0',
  `name` varchar(50) DEFAULT NULL COMMENT 'éƒ¨é—¨åç§°',
  `order_num` int(11) DEFAULT NULL COMMENT 'æ’åº',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT 'æ˜¯å¦åˆ é™¤  -1ï¼šå·²åˆ é™¤  0ï¼šæ­£å¸¸',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='éƒ¨é—¨ç®¡ç†';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dept`
--

LOCK TABLES `sys_dept` WRITE;
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
INSERT INTO `sys_dept` VALUES (6,0,'ç ”å‘éƒ¨',1,1),(7,6,'ç ”ç™¼ä¸€éƒ¨',1,1),(8,6,'ç ”å‘äºŒéƒ¨',2,1),(9,0,'é”€å”®éƒ¨',2,1),(10,9,'é”€å”®ä¸€éƒ¨',1,1),(11,0,'äº§å“éƒ¨',3,1),(12,11,'äº§å“ä¸€éƒ¨',1,1),(13,0,'æµ‹è¯•éƒ¨',5,1),(14,13,'æµ‹è¯•ä¸€éƒ¨',1,1),(15,13,'æµ‹è¯•äºŒéƒ¨',2,1);
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict`
--

DROP TABLE IF EXISTS `sys_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dict` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT 'ç¼–å·',
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT 'æ ‡ç­¾å',
  `value` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT 'æ•°æ®å€¼',
  `type` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT 'ç±»å‹',
  `description` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT 'æè¿°',
  `sort` decimal(10,0) DEFAULT NULL COMMENT 'æ’åºï¼ˆå‡åºï¼‰',
  `parent_id` bigint(64) DEFAULT '0' COMMENT 'çˆ¶çº§ç¼–å·',
  `create_by` int(64) DEFAULT NULL COMMENT 'åˆ›å»ºè€…',
  `create_date` datetime DEFAULT NULL COMMENT 'åˆ›å»ºæ—¶é—´',
  `update_by` bigint(64) DEFAULT NULL COMMENT 'æ›´æ–°è€…',
  `update_date` datetime DEFAULT NULL COMMENT 'æ›´æ–°æ—¶é—´',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'å¤‡æ³¨ä¿¡æ¯',
  `del_flag` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT 'åˆ é™¤æ ‡è®°',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`value`),
  KEY `sys_dict_label` (`name`),
  KEY `sys_dict_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='å­—å…¸è¡¨';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict`
--

LOCK TABLES `sys_dict` WRITE;
/*!40000 ALTER TABLE `sys_dict` DISABLE KEYS */;
INSERT INTO `sys_dict` VALUES (1,'æ­£å¸¸','0','del_flag','åˆ é™¤æ ‡è®°',10,0,1,NULL,1,NULL,NULL,'0'),(3,'æ˜¾ç¤º','1','show_hide','æ˜¾ç¤º/éšè—',10,0,1,NULL,1,NULL,NULL,'0'),(4,'éšè—','0','show_hide','æ˜¾ç¤º/éšè—',20,0,1,NULL,1,NULL,NULL,'0'),(5,'æ˜¯','1','yes_no','æ˜¯/å¦',10,0,1,NULL,1,NULL,NULL,'0'),(6,'å¦','0','yes_no','æ˜¯/å¦',20,0,1,NULL,1,NULL,NULL,'0'),(7,'çº¢è‰²','red','color','é¢œè‰²å€¼',10,0,1,NULL,1,NULL,NULL,'0'),(8,'ç»¿è‰²','green','color','é¢œè‰²å€¼',20,0,1,NULL,1,NULL,NULL,'0'),(9,'è“è‰²','blue','color','é¢œè‰²å€¼',30,0,1,NULL,1,NULL,NULL,'0'),(10,'é»„è‰²','yellow','color','é¢œè‰²å€¼',40,0,1,NULL,1,NULL,NULL,'0'),(11,'æ©™è‰²','orange','color','é¢œè‰²å€¼',50,0,1,NULL,1,NULL,NULL,'0'),(12,'é»˜è®¤ä¸»é¢˜','default','theme','ä¸»é¢˜æ–¹æ¡ˆ',10,0,1,NULL,1,NULL,NULL,'0'),(13,'å¤©è“ä¸»é¢˜','cerulean','theme','ä¸»é¢˜æ–¹æ¡ˆ',20,0,1,NULL,1,NULL,NULL,'0'),(14,'æ©™è‰²ä¸»é¢˜','readable','theme','ä¸»é¢˜æ–¹æ¡ˆ',30,0,1,NULL,1,NULL,NULL,'0'),(15,'çº¢è‰²ä¸»é¢˜','united','theme','ä¸»é¢˜æ–¹æ¡ˆ',40,0,1,NULL,1,NULL,NULL,'0'),(16,'Flatä¸»é¢˜','flat','theme','ä¸»é¢˜æ–¹æ¡ˆ',60,0,1,NULL,1,NULL,NULL,'0'),(17,'å›½å®¶','1','sys_area_type','åŒºåŸŸç±»å‹',10,0,1,NULL,1,NULL,NULL,'0'),(18,'çœä»½ã€ç›´è¾–å¸‚','2','sys_area_type','åŒºåŸŸç±»å‹',20,0,1,NULL,1,NULL,NULL,'0'),(19,'åœ°å¸‚','3','sys_area_type','åŒºåŸŸç±»å‹',30,0,1,NULL,1,NULL,NULL,'0'),(20,'åŒºå¿','4','sys_area_type','åŒºåŸŸç±»å‹',40,0,1,NULL,1,NULL,NULL,'0'),(21,'å…¬å¸','1','sys_office_type','æœºæ„ç±»å‹',60,0,1,NULL,1,NULL,NULL,'0'),(22,'éƒ¨é—¨','2','sys_office_type','æœºæ„ç±»å‹',70,0,1,NULL,1,NULL,NULL,'0'),(23,'å°ç»„','3','sys_office_type','æœºæ„ç±»å‹',80,0,1,NULL,1,NULL,NULL,'0'),(24,'å…¶å®ƒ','4','sys_office_type','æœºæ„ç±»å‹',90,0,1,NULL,1,NULL,NULL,'0'),(25,'ç»¼åˆéƒ¨','1','sys_office_common','å¿«æ·é€šç”¨éƒ¨é—¨',30,0,1,NULL,1,NULL,NULL,'0'),(26,'å¼€å‘éƒ¨','2','sys_office_common','å¿«æ·é€šç”¨éƒ¨é—¨',40,0,1,NULL,1,NULL,NULL,'0'),(27,'äººåŠ›éƒ¨','3','sys_office_common','å¿«æ·é€šç”¨éƒ¨é—¨',50,0,1,NULL,1,NULL,NULL,'0'),(28,'ä¸€çº§','1','sys_office_grade','æœºæ„ç­‰çº§',10,0,1,NULL,1,NULL,NULL,'0'),(29,'äºŒçº§','2','sys_office_grade','æœºæ„ç­‰çº§',20,0,1,NULL,1,NULL,NULL,'0'),(30,'ä¸‰çº§','3','sys_office_grade','æœºæ„ç­‰çº§',30,0,1,NULL,1,NULL,NULL,'0'),(31,'å››çº§','4','sys_office_grade','æœºæ„ç­‰çº§',40,0,1,NULL,1,NULL,NULL,'0'),(32,'æ‰€æœ‰æ•°æ®','1','sys_data_scope','æ•°æ®èŒƒå›´',10,0,1,NULL,1,NULL,NULL,'0'),(33,'æ‰€åœ¨å…¬å¸åŠä»¥ä¸‹æ•°æ®','2','sys_data_scope','æ•°æ®èŒƒå›´',20,0,1,NULL,1,NULL,NULL,'0'),(34,'æ‰€åœ¨å…¬å¸æ•°æ®','3','sys_data_scope','æ•°æ®èŒƒå›´',30,0,1,NULL,1,NULL,NULL,'0'),(35,'æ‰€åœ¨éƒ¨é—¨åŠä»¥ä¸‹æ•°æ®','4','sys_data_scope','æ•°æ®èŒƒå›´',40,0,1,NULL,1,NULL,NULL,'0'),(36,'æ‰€åœ¨éƒ¨é—¨æ•°æ®','5','sys_data_scope','æ•°æ®èŒƒå›´',50,0,1,NULL,1,NULL,NULL,'0'),(37,'ä»…æœ¬äººæ•°æ®','8','sys_data_scope','æ•°æ®èŒƒå›´',90,0,1,NULL,1,NULL,NULL,'0'),(38,'æŒ‰æ˜ç»†è®¾ç½®','9','sys_data_scope','æ•°æ®èŒƒå›´',100,0,1,NULL,1,NULL,NULL,'0'),(39,'ç³»ç»Ÿç®¡ç†','1','sys_user_type','ç”¨æˆ·ç±»å‹',10,0,1,NULL,1,NULL,NULL,'0'),(40,'éƒ¨é—¨ç»ç†','2','sys_user_type','ç”¨æˆ·ç±»å‹',20,0,1,NULL,1,NULL,NULL,'0'),(41,'æ™®é€šç”¨æˆ·','3','sys_user_type','ç”¨æˆ·ç±»å‹',30,0,1,NULL,1,NULL,NULL,'0'),(42,'åŸºç¡€ä¸»é¢˜','basic','cms_theme','ç«™ç‚¹ä¸»é¢˜',10,0,1,NULL,1,NULL,NULL,'0'),(43,'è“è‰²ä¸»é¢˜','blue','cms_theme','ç«™ç‚¹ä¸»é¢˜',20,0,1,NULL,1,NULL,NULL,'1'),(44,'çº¢è‰²ä¸»é¢˜','red','cms_theme','ç«™ç‚¹ä¸»é¢˜',30,0,1,NULL,1,NULL,NULL,'1'),(45,'æ–‡ç« æ¨¡å‹','article','cms_module','æ ç›®æ¨¡å‹',10,0,1,NULL,1,NULL,NULL,'0'),(46,'å›¾ç‰‡æ¨¡å‹','picture','cms_module','æ ç›®æ¨¡å‹',20,0,1,NULL,1,NULL,NULL,'1'),(47,'ä¸‹è½½æ¨¡å‹','download','cms_module','æ ç›®æ¨¡å‹',30,0,1,NULL,1,NULL,NULL,'1'),(48,'é“¾æ¥æ¨¡å‹','link','cms_module','æ ç›®æ¨¡å‹',40,0,1,NULL,1,NULL,NULL,'0'),(49,'ä¸“é¢˜æ¨¡å‹','special','cms_module','æ ç›®æ¨¡å‹',50,0,1,NULL,1,NULL,NULL,'1'),(50,'é»˜è®¤å±•ç°æ–¹å¼','0','cms_show_modes','å±•ç°æ–¹å¼',10,0,1,NULL,1,NULL,NULL,'0'),(51,'é¦–æ ç›®å†…å®¹åˆ—è¡¨','1','cms_show_modes','å±•ç°æ–¹å¼',20,0,1,NULL,1,NULL,NULL,'0'),(52,'æ ç›®ç¬¬ä¸€æ¡å†…å®¹','2','cms_show_modes','å±•ç°æ–¹å¼',30,0,1,NULL,1,NULL,NULL,'0'),(53,'å‘å¸ƒ','0','cms_del_flag','å†…å®¹çŠ¶æ€',10,0,1,NULL,1,NULL,NULL,'0'),(54,'åˆ é™¤','1','cms_del_flag','å†…å®¹çŠ¶æ€',20,0,1,NULL,1,NULL,NULL,'0'),(55,'å®¡æ ¸','2','cms_del_flag','å†…å®¹çŠ¶æ€',15,0,1,NULL,1,NULL,NULL,'0'),(56,'é¦–é¡µç„¦ç‚¹å›¾','1','cms_posid','æ¨èä½',10,0,1,NULL,1,NULL,NULL,'0'),(57,'æ ç›®é¡µæ–‡ç« æ¨è','2','cms_posid','æ¨èä½',20,0,1,NULL,1,NULL,NULL,'0'),(58,'å’¨è¯¢','1','cms_guestbook','ç•™è¨€æ¿åˆ†ç±»',10,0,1,NULL,1,NULL,NULL,'0'),(59,'å»ºè®®','2','cms_guestbook','ç•™è¨€æ¿åˆ†ç±»',20,0,1,NULL,1,NULL,NULL,'0'),(60,'æŠ•è¯‰','3','cms_guestbook','ç•™è¨€æ¿åˆ†ç±»',30,0,1,NULL,1,NULL,NULL,'0'),(61,'å…¶å®ƒ','4','cms_guestbook','ç•™è¨€æ¿åˆ†ç±»',40,0,1,NULL,1,NULL,NULL,'0'),(62,'å…¬ä¼‘','1','oa_leave_type','è¯·å‡ç±»å‹',10,0,1,NULL,1,NULL,NULL,'0'),(63,'ç—…å‡','2','oa_leave_type','è¯·å‡ç±»å‹',20,0,1,NULL,1,NULL,NULL,'0'),(64,'äº‹å‡','3','oa_leave_type','è¯·å‡ç±»å‹',30,0,1,NULL,1,NULL,NULL,'0'),(65,'è°ƒä¼‘','4','oa_leave_type','è¯·å‡ç±»å‹',40,0,1,NULL,1,NULL,NULL,'0'),(66,'å©šå‡','5','oa_leave_type','è¯·å‡ç±»å‹',60,0,1,NULL,1,NULL,NULL,'0'),(67,'æ¥å…¥æ—¥å¿—','1','sys_log_type','æ—¥å¿—ç±»å‹',30,0,1,NULL,1,NULL,NULL,'0'),(68,'å¼‚å¸¸æ—¥å¿—','2','sys_log_type','æ—¥å¿—ç±»å‹',40,0,1,NULL,1,NULL,NULL,'0'),(69,'è¯·å‡æµç¨‹','leave','act_type','æµç¨‹ç±»å‹',10,0,1,NULL,1,NULL,NULL,'0'),(70,'å®¡æ‰¹æµ‹è¯•æµç¨‹','test_audit','act_type','æµç¨‹ç±»å‹',20,0,1,NULL,1,NULL,NULL,'0'),(71,'åˆ†ç±»1','1','act_category','æµç¨‹åˆ†ç±»',10,0,1,NULL,1,NULL,NULL,'0'),(72,'åˆ†ç±»2','2','act_category','æµç¨‹åˆ†ç±»',20,0,1,NULL,1,NULL,NULL,'0'),(73,'å¢åˆ æ”¹æŸ¥','crud','gen_category','ä»£ç ç”Ÿæˆåˆ†ç±»',10,0,1,NULL,1,NULL,NULL,'1'),(74,'å¢åˆ æ”¹æŸ¥ï¼ˆåŒ…å«ä»è¡¨ï¼‰','crud_many','gen_category','ä»£ç ç”Ÿæˆåˆ†ç±»',20,0,1,NULL,1,NULL,NULL,'1'),(75,'æ ‘ç»“æ„','tree','gen_category','ä»£ç ç”Ÿæˆåˆ†ç±»',30,0,1,NULL,1,NULL,NULL,'1'),(76,'=','=','gen_query_type','æŸ¥è¯¢æ–¹å¼',10,0,1,NULL,1,NULL,NULL,'1'),(77,'!=','!=','gen_query_type','æŸ¥è¯¢æ–¹å¼',20,0,1,NULL,1,NULL,NULL,'1'),(78,'&gt;','&gt;','gen_query_type','æŸ¥è¯¢æ–¹å¼',30,0,1,NULL,1,NULL,NULL,'1'),(79,'&lt;','&lt;','gen_query_type','æŸ¥è¯¢æ–¹å¼',40,0,1,NULL,1,NULL,NULL,'1'),(80,'Between','between','gen_query_type','æŸ¥è¯¢æ–¹å¼',50,0,1,NULL,1,NULL,NULL,'1'),(81,'Like','like','gen_query_type','æŸ¥è¯¢æ–¹å¼',60,0,1,NULL,1,NULL,NULL,'1'),(82,'Left Like','left_like','gen_query_type','æŸ¥è¯¢æ–¹å¼',70,0,1,NULL,1,NULL,NULL,'1'),(83,'Right Like','right_like','gen_query_type','æŸ¥è¯¢æ–¹å¼',80,0,1,NULL,1,NULL,NULL,'1'),(84,'æ–‡æœ¬æ¡†','input','gen_show_type','å­—æ®µç”Ÿæˆæ–¹æ¡ˆ',10,0,1,NULL,1,NULL,NULL,'1'),(85,'æ–‡æœ¬åŸŸ','textarea','gen_show_type','å­—æ®µç”Ÿæˆæ–¹æ¡ˆ',20,0,1,NULL,1,NULL,NULL,'1'),(86,'ä¸‹æ‹‰æ¡†','select','gen_show_type','å­—æ®µç”Ÿæˆæ–¹æ¡ˆ',30,0,1,NULL,1,NULL,NULL,'1'),(87,'å¤é€‰æ¡†','checkbox','gen_show_type','å­—æ®µç”Ÿæˆæ–¹æ¡ˆ',40,0,1,NULL,1,NULL,NULL,'1'),(88,'å•é€‰æ¡†','radiobox','gen_show_type','å­—æ®µç”Ÿæˆæ–¹æ¡ˆ',50,0,1,NULL,1,NULL,NULL,'1'),(89,'æ—¥æœŸé€‰æ‹©','dateselect','gen_show_type','å­—æ®µç”Ÿæˆæ–¹æ¡ˆ',60,0,1,NULL,1,NULL,NULL,'1'),(90,'äººå‘˜é€‰æ‹©','userselect','gen_show_type','å­—æ®µç”Ÿæˆæ–¹æ¡ˆ',70,0,1,NULL,1,NULL,NULL,'1'),(91,'éƒ¨é—¨é€‰æ‹©','officeselect','gen_show_type','å­—æ®µç”Ÿæˆæ–¹æ¡ˆ',80,0,1,NULL,1,NULL,NULL,'1'),(92,'åŒºåŸŸé€‰æ‹©','areaselect','gen_show_type','å­—æ®µç”Ÿæˆæ–¹æ¡ˆ',90,0,1,NULL,1,NULL,NULL,'1'),(93,'String','String','gen_java_type','Javaç±»å‹',10,0,1,NULL,1,NULL,NULL,'1'),(94,'Long','Long','gen_java_type','Javaç±»å‹',20,0,1,NULL,1,NULL,NULL,'1'),(95,'ä»…æŒä¹…å±‚','dao','gen_category','ä»£ç ç”Ÿæˆåˆ†ç±»',40,0,1,NULL,1,NULL,NULL,'1'),(96,'ç”·','1','sex','æ€§åˆ«',10,0,1,NULL,1,NULL,NULL,'0'),(97,'å¥³','2','sex','æ€§åˆ«',20,0,1,NULL,1,NULL,NULL,'0'),(98,'Integer','Integer','gen_java_type','Javaç±»å‹',30,0,1,NULL,1,NULL,NULL,'1'),(99,'Double','Double','gen_java_type','Javaç±»å‹',40,0,1,NULL,1,NULL,NULL,'1'),(100,'Date','java.util.Date','gen_java_type','Javaç±»å‹',50,0,1,NULL,1,NULL,NULL,'1'),(104,'Custom','Custom','gen_java_type','Javaç±»å‹',90,0,1,NULL,1,NULL,NULL,'1'),(105,'ä¼šè®®é€šå‘Š','1','oa_notify_type','é€šçŸ¥é€šå‘Šç±»å‹',10,0,1,NULL,1,NULL,NULL,'0'),(106,'å¥–æƒ©é€šå‘Š','2','oa_notify_type','é€šçŸ¥é€šå‘Šç±»å‹',20,0,1,NULL,1,NULL,NULL,'0'),(107,'æ´»åŠ¨é€šå‘Š','3','oa_notify_type','é€šçŸ¥é€šå‘Šç±»å‹',30,0,1,NULL,1,NULL,NULL,'0'),(108,'è‰ç¨¿','0','oa_notify_status','é€šçŸ¥é€šå‘ŠçŠ¶æ€',10,0,1,NULL,1,NULL,NULL,'0'),(109,'å‘å¸ƒ','1','oa_notify_status','é€šçŸ¥é€šå‘ŠçŠ¶æ€',20,0,1,NULL,1,NULL,NULL,'0'),(110,'æœªè¯»','0','oa_notify_read','é€šçŸ¥é€šå‘ŠçŠ¶æ€',10,0,1,NULL,1,NULL,NULL,'0'),(111,'å·²è¯»','1','oa_notify_read','é€šçŸ¥é€šå‘ŠçŠ¶æ€',20,0,1,NULL,1,NULL,NULL,'0'),(112,'è‰ç¨¿','0','oa_notify_status','é€šçŸ¥é€šå‘ŠçŠ¶æ€',10,0,1,NULL,1,NULL,'','0'),(113,'åˆ é™¤','0','del_flag','åˆ é™¤æ ‡è®°',NULL,NULL,NULL,NULL,NULL,NULL,'',''),(118,'å…³äº','about','blog_type','åšå®¢ç±»å‹',NULL,NULL,NULL,NULL,NULL,NULL,'å…¨urlæ˜¯:/blog/open/page/about',''),(119,'äº¤æµ','communication','blog_type','åšå®¢ç±»å‹',NULL,NULL,NULL,NULL,NULL,NULL,'',''),(120,'æ–‡ç« ','article','blog_type','åšå®¢ç±»å‹',NULL,NULL,NULL,NULL,NULL,NULL,'',''),(121,'ç¼–ç ','code','hobby','çˆ±å¥½',NULL,NULL,NULL,NULL,NULL,NULL,'',''),(122,'ç»˜ç”»','painting','hobby','çˆ±å¥½',NULL,NULL,NULL,NULL,NULL,NULL,'','');
/*!40000 ALTER TABLE `sys_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_file`
--

DROP TABLE IF EXISTS `sys_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT 'æ–‡ä»¶ç±»å‹',
  `url` varchar(200) DEFAULT NULL COMMENT 'URLåœ°å€',
  `create_date` datetime DEFAULT NULL COMMENT 'åˆ›å»ºæ—¶é—´',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8 COMMENT='æ–‡ä»¶ä¸Šä¼ ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_file`
--

LOCK TABLES `sys_file` WRITE;
/*!40000 ALTER TABLE `sys_file` DISABLE KEYS */;
INSERT INTO `sys_file` VALUES (110,0,'/files/d64a62e3-6821-48f1-bac6-a1b9945f4afb.jpg','2017-10-14 16:20:17'),(111,0,'/files/aa2c3dc6-495f-48cc-8e12-446eceb2535e.jpg','2017-10-14 16:20:21'),(118,0,'/files/a973499e-3ec7-4d43-8a52-b6f6517c77e3.jpg','2017-10-20 11:53:52'),(132,0,'/files/e6f13526-e31c-4ebe-a3cf-5fd88dd10be6.jpg','2017-12-18 20:19:51'),(134,0,'/files/cd016e72-77f7-4425-afe2-b79dfbdc3ae9.jpeg','2017-12-18 22:44:07'),(138,0,'/files/9ec12ee7-65b5-4cc5-9900-d2ec6185b336.jpg','2017-12-19 19:55:27'),(139,0,'/files/f6aab9d4-00a1-4bc8-a695-40fc472d4ea9.jpg','2018-01-02 19:53:24'),(141,0,'/files/445c5d98-b3b6-4226-a5af-249d1b2a6a34.jpg','2018-01-09 19:28:53');
/*!40000 ALTER TABLE `sys_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_log`
--

DROP TABLE IF EXISTS `sys_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT 'ç”¨æˆ·id',
  `username` varchar(50) DEFAULT NULL COMMENT 'ç”¨æˆ·å',
  `operation` varchar(50) DEFAULT NULL COMMENT 'ç”¨æˆ·æ“ä½œ',
  `time` int(11) DEFAULT NULL COMMENT 'å“åº”æ—¶é—´',
  `method` varchar(200) DEFAULT NULL COMMENT 'è¯·æ±‚æ–¹æ³•',
  `params` varchar(5000) DEFAULT NULL COMMENT 'è¯·æ±‚å‚æ•°',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IPåœ°å€',
  `gmt_create` datetime DEFAULT NULL COMMENT 'åˆ›å»ºæ—¶é—´',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=559 DEFAULT CHARSET=utf8 COMMENT='ç³»ç»Ÿæ—¥å¿—';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log`
--

LOCK TABLES `sys_log` WRITE;
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
INSERT INTO `sys_log` VALUES (467,NULL,NULL,'error',NULL,'http://localhost/api/goPay','org.springframework.web.HttpRequestMethodNotSupportedException: Request method \'GET\' not supported',NULL,'2019-09-07 17:30:23'),(468,NULL,NULL,'error',NULL,'http://localhost/api/goPay','org.springframework.web.HttpRequestMethodNotSupportedException: Request method \'GET\' not supported',NULL,'2019-11-14 16:13:29'),(469,NULL,NULL,'error',NULL,'http://localhost/api/goPay','org.springframework.web.HttpRequestMethodNotSupportedException: Request method \'GET\' not supported',NULL,'2019-11-14 16:16:28'),(470,NULL,NULL,'error',NULL,'http://localhost/api/goPay','java.lang.RuntimeException: postè¯·æ±‚å¼‚å¸¸,å½“å‰è¯·æ±‚çš„urlä¸ºhttps://api.qapple.io/v2/api/merchant/merchantcenter/pay/prePayå‚æ•°ä¸º{\"vipName\":\"0103069\",\"orderAmountRmb\":100.00,\"orderAmountRmb10000\":1000000,\"subject\":\"äº¤æ˜“å•†å“\",\"outTradeNo\":\"20191114162221088\",\"sign\":\"hRUveuOSqeEhLKJVyBbMUNSb4fIhKUb7dXnrtdw5fDCxq+0KRWxQWnrdjG/I9tX3iInifzJiY2Bd7JQHkP/wLAE0FhPz+O/hIslMs7HZP1WH6Lx6TygGVgHCrJSaElg6mZUypdqLg4FC26czAvoSHq0rP2EKNBZArRnwaOgESYU=\",\"notifyUrl\":\"http://localhost/api/notify\",\"signType\":\"RSA\",\"body\":\"äº¤æ˜“é‡‘é¢\",\"merchantName\":\"xgww213\"}',NULL,'2019-11-14 16:22:42'),(471,NULL,NULL,'error',NULL,'http://localhost/api/goPay','java.lang.RuntimeException: postè¯·æ±‚å¼‚å¸¸,å½“å‰è¯·æ±‚çš„urlä¸ºhttps://api.qapple.io/v2/api/merchant/merchantcenter/pay/prePayå‚æ•°ä¸º{\"vipName\":\"0103069\",\"orderAmountRmb\":100.00,\"orderAmountRmb10000\":1000000,\"subject\":\"äº¤æ˜“å•†å“\",\"outTradeNo\":\"20191114162357821\",\"sign\":\"Q3yCu/GFfVfBjaNQsHt6X59gO0j45eeq5gQYHJURw2SD8sH1eV7jiEr8ODOk60nI9pv6Yz7tn5j/ygSQGe2Qf6aQ2M5QnHnSyFCCoAJQjBk3tpY8ojUDAFCHav797m6Gwxhk6Fkl3xEyRSXXBXvtdqCSUZHrWLoBpBtCBP4a7DI=\",\"notifyUrl\":\"http://localhost/api/notify\",\"signType\":\"RSA\",\"body\":\"äº¤æ˜“é‡‘é¢\",\"merchantName\":\"xgww213\"}',NULL,'2019-11-14 16:23:58'),(472,NULL,NULL,'error',NULL,'http://192.168.0.101/api/myteam','org.apache.catalina.connector.ClientAbortException: java.io.IOException: Broken pipe',NULL,'2020-03-28 19:14:38'),(473,NULL,NULL,'error',NULL,'http://192.168.0.101/api/goPay','java.lang.RuntimeException: postè¯·æ±‚å¼‚å¸¸,å½“å‰è¯·æ±‚çš„urlä¸ºhttps://api.qapple.io/v2/api/merchant/merchantcenter/pay/prePayå‚æ•°ä¸º{\"vipName\":\"0104065\",\"orderAmountRmb\":1000.00,\"orderAmountRmb10000\":10000000,\"subject\":\"äº¤æ˜“å•†å“\",\"outTradeNo\":\"20200329173952799\",\"sign\":\"DT8lN8phBDfMbIfCM455O0tp+D/hscjLglx7TSYMVcQwpvgoCfQy1kWDKMRC7uWwm7aiWObH2J4bZCp7ARwEfbtmleHDrDiM9zdDXgD+fXh4pKVTyZ1EukLHjl0Kc68Dj6ZQIq+Fb4bfxiKFw8y0YYOJ7CWxys5DLs/oQGF0Wms=\",\"notifyUrl\":\"http://localhost/api/notify\",\"signType\":\"RSA\",\"body\":\"äº¤æ˜“é‡‘é¢\",\"merchantName\":\"xgww213\"}',NULL,'2020-03-29 17:39:54'),(474,NULL,NULL,'error',NULL,'http://192.168.0.101/api/goPay','java.lang.NullPointerException',NULL,'2020-03-29 18:25:55'),(475,NULL,NULL,'error',NULL,'http://localhost/api/getRecord','java.lang.IllegalArgumentException: No enum constant com.bootdo.app.zwlenum.TradeTypeEnum.cash_deposit',NULL,'2020-03-30 08:41:31'),(476,1,'admin','ç™»å½•',176,'com.bootdo.system.controller.LoginController.ajaxLogin()',NULL,'127.0.0.1','2020-04-03 10:37:02'),(477,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',50,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"},{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-print\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-sort-amount-asc\",\"url\":\"activiti/model\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"','127.0.0.1','2020-04-03 10:37:02'),(478,-1,'è·å–ç”¨æˆ·ä¿¡æ¯ä¸ºç©º','ç™»å½•',0,'com.bootdo.system.controller.LoginController.ajaxLogin()',NULL,'127.0.0.1','2020-04-03 10:54:14'),(479,-1,'è·å–ç”¨æˆ·ä¿¡æ¯ä¸ºç©º','ç™»å½•',1,'com.bootdo.system.controller.LoginController.ajaxLogin()',NULL,'127.0.0.1','2020-04-03 10:54:18'),(480,1,'admin','ç™»å½•',19,'com.bootdo.system.controller.LoginController.ajaxLogin()',NULL,'127.0.0.1','2020-04-03 10:54:23'),(481,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',36,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"},{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-print\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-sort-amount-asc\",\"url\":\"activiti/model\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"','127.0.0.1','2020-04-03 10:54:23'),(482,1,'admin','ç™»å½•',19,'com.bootdo.system.controller.LoginController.ajaxLogin()',NULL,'127.0.0.1','2020-04-03 11:04:30'),(483,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',53,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"},{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-print\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-sort-amount-asc\",\"url\":\"activiti/model\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"','127.0.0.1','2020-04-03 11:04:30'),(484,1,'admin','ç™»å½•',17,'com.bootdo.system.controller.LoginController.ajaxLogin()',NULL,'127.0.0.1','2020-04-03 21:31:43'),(485,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',29,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"},{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-print\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-sort-amount-asc\",\"url\":\"activiti/model\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"','127.0.0.1','2020-04-03 21:31:43'),(486,1,'admin','ç™»å½•',17,'com.bootdo.system.controller.LoginController.ajaxLogin()',NULL,'127.0.0.1','2020-04-03 22:11:48'),(487,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',30,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"},{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-print\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-sort-amount-asc\",\"url\":\"activiti/model\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"','127.0.0.1','2020-04-03 22:11:48'),(488,1,'admin','ç™»å½•',19,'com.bootdo.system.controller.LoginController.ajaxLogin()',NULL,'127.0.0.1','2020-04-04 14:23:41'),(489,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',34,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"},{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-print\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-sort-amount-asc\",\"url\":\"activiti/model\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"','127.0.0.1','2020-04-04 14:23:41'),(490,NULL,NULL,'error',NULL,'http://localhost:6868/api/getRecord','org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.exceptions.PersistenceException: \n### Error querying database.  Cause: java.lang.IllegalArgumentException: invalid comparison: java.util.Date and java.lang.String\n### Cause: java.lang.IllegalArgumentException: invalid comparison: java.util.Date and java.lang.String',NULL,'2020-04-07 18:13:09'),(491,NULL,NULL,'error',NULL,'http://localhost:6868/api/getRecord','org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.exceptions.PersistenceException: \n### Error querying database.  Cause: java.lang.IllegalArgumentException: invalid comparison: java.util.Date and java.lang.String\n### Cause: java.lang.IllegalArgumentException: invalid comparison: java.util.Date and java.lang.String',NULL,'2020-04-07 18:13:19'),(492,NULL,NULL,'error',NULL,'http://localhost:6868/api/getRecord','org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.exceptions.PersistenceException: \n### Error querying database.  Cause: java.lang.IllegalArgumentException: invalid comparison: java.util.Date and java.lang.String\n### Cause: java.lang.IllegalArgumentException: invalid comparison: java.util.Date and java.lang.String',NULL,'2020-04-07 18:13:20'),(493,NULL,NULL,'error',NULL,'http://localhost:6868/api/login','org.springframework.web.HttpRequestMethodNotSupportedException: Request method \'POST\' not supported',NULL,'2020-04-08 10:41:22'),(494,1,'admin','ç™»å½•',39,'com.bootdo.system.controller.LoginController.ajaxLogin()',NULL,'127.0.0.1','2020-05-02 23:09:25'),(495,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',60,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"},{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-print\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-sort-amount-asc\",\"url\":\"activiti/model\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"','127.0.0.1','2020-05-02 23:09:25'),(496,1,'admin','ç™»å½•',38,'com.bootdo.system.controller.LoginController.ajaxLogin()',NULL,'127.0.0.1','2020-05-02 23:15:22'),(497,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',48,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"},{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-print\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-sort-amount-asc\",\"url\":\"activiti/model\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"','127.0.0.1','2020-05-02 23:15:22'),(498,1,'admin','ç™»å½•',262,'com.bootdo.system.controller.LoginController.ajaxLogin()',NULL,'127.0.0.1','2020-05-02 23:21:18'),(499,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',51,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"},{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-print\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-sort-amount-asc\",\"url\":\"activiti/model\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"','127.0.0.1','2020-05-02 23:21:18'),(500,1,'admin','ç™»å½•',18,'com.bootdo.system.controller.LoginController.ajaxLogin()',NULL,'127.0.0.1','2020-05-20 16:49:47'),(501,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',32,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"},{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-print\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-sort-amount-asc\",\"url\":\"activiti/model\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"','127.0.0.1','2020-05-20 16:49:47'),(502,-1,'è·å–ç”¨æˆ·ä¿¡æ¯ä¸ºç©º','ç™»å½•',26,'com.bootdo.system.controller.LoginController.ajaxLogin()',NULL,'127.0.0.1','2020-11-23 17:26:29'),(503,1,'admin','ç™»å½•',16,'com.bootdo.system.controller.LoginController.ajaxLogin()',NULL,'127.0.0.1','2020-11-23 17:26:33'),(504,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',80,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"},{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-print\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-sort-amount-asc\",\"url\":\"activiti/model\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"','127.0.0.1','2020-11-23 17:26:33'),(505,1,'admin','ç™»å½•',6,'com.bootdo.system.controller.LoginController.ajaxLogin()',NULL,'127.0.0.1','2020-11-23 19:36:51'),(506,1,'admin','ç™»å½•',3,'com.bootdo.system.controller.LoginController.ajaxLogin()',NULL,'127.0.0.1','2020-11-23 19:37:00'),(507,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',13,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"},{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-print\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-sort-amount-asc\",\"url\":\"activiti/model\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"','127.0.0.1','2020-11-23 19:37:00'),(508,1,'admin','ç™»å½•',26,'com.bootdo.system.controller.LoginController.ajaxLogin()',NULL,'127.0.0.1','2020-11-23 19:48:20'),(509,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',77,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"},{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-print\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-sort-amount-asc\",\"url\":\"activiti/model\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"','127.0.0.1','2020-11-23 19:48:21'),(510,1,'admin','æ·»åŠ èœå•',1,'com.bootdo.system.controller.MenuController.add()',NULL,'127.0.0.1','2020-11-23 19:48:55'),(511,1,'admin','æ·»åŠ èœå•',6,'com.bootdo.system.controller.MenuController.add()',NULL,'127.0.0.1','2020-11-23 19:49:08'),(512,1,'admin','ä¿å­˜èœå•',7,'com.bootdo.system.controller.MenuController.save()',NULL,'127.0.0.1','2020-11-23 19:50:23'),(513,1,'admin','ç¼–è¾‘è§’è‰²',4,'com.bootdo.system.controller.RoleController.edit()',NULL,'127.0.0.1','2020-11-23 19:50:31'),(514,1,'admin','æ›´æ–°è§’è‰²',34,'com.bootdo.system.controller.RoleController.update()',NULL,'127.0.0.1','2020-11-23 19:50:36'),(515,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',10,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-barcode\",\"url\":\"/system/ustdLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"115\",\"parentId\":\"3\",\"text\":\"USDTäº¤æ˜“\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"},{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":','127.0.0.1','2020-11-23 19:50:40'),(516,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',10,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-barcode\",\"url\":\"/system/ustdLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"115\",\"parentId\":\"3\",\"text\":\"USDTäº¤æ˜“\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"},{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":','127.0.0.1','2020-11-23 19:50:47'),(517,1,'admin','ç™»å½•',5,'com.bootdo.system.controller.LoginController.ajaxLogin()',NULL,'127.0.0.1','2020-11-23 19:51:00'),(518,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',9,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-barcode\",\"url\":\"/system/ustdLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"115\",\"parentId\":\"3\",\"text\":\"USDTäº¤æ˜“\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"},{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":','127.0.0.1','2020-11-23 19:51:00'),(519,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',11,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-barcode\",\"url\":\"/system/ustdLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"115\",\"parentId\":\"3\",\"text\":\"USDTäº¤æ˜“\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"},{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":','127.0.0.1','2020-11-23 19:54:37'),(520,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',11,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-barcode\",\"url\":\"/system/ustdLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"115\",\"parentId\":\"3\",\"text\":\"USDTäº¤æ˜“\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"},{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":','127.0.0.1','2020-11-23 19:56:03'),(521,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',7,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-barcode\",\"url\":\"/system/ustdLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"115\",\"parentId\":\"3\",\"text\":\"USDTäº¤æ˜“\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"},{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":','127.0.0.1','2020-11-23 19:56:10'),(522,1,'admin','ç™»å½•',278,'com.bootdo.system.controller.LoginController.ajaxLogin()',NULL,'127.0.0.1','2020-11-23 19:58:00'),(523,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',73,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-barcode\",\"url\":\"/system/ustdLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"115\",\"parentId\":\"3\",\"text\":\"USDTäº¤æ˜“\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"},{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":','127.0.0.1','2020-11-23 19:58:00'),(524,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',16,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-barcode\",\"url\":\"/system/ustdLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"115\",\"parentId\":\"3\",\"text\":\"USDTäº¤æ˜“\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"},{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":','127.0.0.1','2020-11-23 20:02:21'),(525,-1,'è·å–ç”¨æˆ·ä¿¡æ¯ä¸ºç©º','ç™»å½•',6,'com.bootdo.system.controller.LoginController.ajaxLogin()',NULL,'127.0.0.1','2020-11-23 20:03:37'),(526,1,'admin','ç™»å½•',120,'com.bootdo.system.controller.LoginController.ajaxLogin()',NULL,'127.0.0.1','2020-11-23 20:03:42'),(527,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',82,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-barcode\",\"url\":\"/system/ustdLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"115\",\"parentId\":\"3\",\"text\":\"USDTäº¤æ˜“\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"},{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":','127.0.0.1','2020-11-23 20:03:42'),(528,1,'admin','ç™»å½•',232,'com.bootdo.system.controller.LoginController.ajaxLogin()',NULL,'127.0.0.1','2020-11-23 20:17:39'),(529,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',68,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-barcode\",\"url\":\"/system/ustdLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"115\",\"parentId\":\"3\",\"text\":\"USDTäº¤æ˜“\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"},{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":','127.0.0.1','2020-11-23 20:17:39'),(530,1,'admin','error',NULL,'http://localhost:6868/system/ustdLog/list','java.lang.IllegalArgumentException: No enum constant com.bootdo.app.zwlenum.USDTLogStatusEnum.pre_pay',NULL,'2020-11-23 20:17:44'),(531,1,'admin','ç™»å½•',336,'com.bootdo.system.controller.LoginController.ajaxLogin()',NULL,'127.0.0.1','2020-11-23 20:20:24'),(532,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',95,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-barcode\",\"url\":\"/system/ustdLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"115\",\"parentId\":\"3\",\"text\":\"USDTäº¤æ˜“\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"},{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":','127.0.0.1','2020-11-23 20:20:24'),(533,1,'admin','ç™»å½•',58,'com.bootdo.system.controller.LoginController.ajaxLogin()',NULL,'127.0.0.1','2020-11-23 20:38:06'),(534,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',47,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-barcode\",\"url\":\"/system/ustdLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"115\",\"parentId\":\"3\",\"text\":\"USDTäº¤æ˜“\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"},{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":','127.0.0.1','2020-11-23 20:38:06'),(535,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',13,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-barcode\",\"url\":\"/system/ustdLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"115\",\"parentId\":\"3\",\"text\":\"USDTäº¤æ˜“\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"},{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":','127.0.0.1','2020-11-23 20:38:30'),(536,1,'admin','ç™»å½•',250,'com.bootdo.system.controller.LoginController.ajaxLogin()',NULL,'127.0.0.1','2020-11-23 20:57:23'),(537,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',61,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-barcode\",\"url\":\"/system/ustdLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"115\",\"parentId\":\"3\",\"text\":\"USDTäº¤æ˜“\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"},{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":','127.0.0.1','2020-11-23 20:57:23'),(538,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',15,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-barcode\",\"url\":\"/system/ustdLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"115\",\"parentId\":\"3\",\"text\":\"USDTäº¤æ˜“\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"},{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":','127.0.0.1','2020-11-23 20:59:12'),(539,1,'admin','ç™»å½•',281,'com.bootdo.system.controller.LoginController.ajaxLogin()',NULL,'127.0.0.1','2020-11-23 21:53:30'),(540,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',74,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-barcode\",\"url\":\"/system/ustdLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"115\",\"parentId\":\"3\",\"text\":\"USDTäº¤æ˜“\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"},{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":','127.0.0.1','2020-11-23 21:53:30'),(541,NULL,NULL,'error',NULL,'http://localhost:6868/api/register','org.springframework.jdbc.BadSqlGrammarException: \n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column \'wallet_address\' in \'field list\'\n### The error may exist in file [/Users/dingbaosheng/Documents/bootdo/target/classes/mybatis/system/MemberMapper.xml]\n### The error may involve defaultParameterMap\n### The error occurred while setting parameters\n### SQL: select `id`,`nick_name`,`username`,`password`,`create_time`,`owner_invite_code`,`source_invite_code`,`total_amount`,`free_amount`,`lock_amount`,`profit_amount`,`transit_amount`,`level`,`commission_rate`,`bank_no`,`bank_name`,`card_no` ,`really_name`,`bank_address`,`bank_branch_name` ,`team_profit_amount`,`day_profit_amount`,`wallet_address` from zwl_member          WHERE  owner_invite_code = ?                            order by id desc\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column \'wallet_address\' in \'field list\'\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column \'wallet_address\' in \'field list\'',NULL,'2020-11-24 15:47:26'),(542,NULL,NULL,'error',NULL,'http://localhost:6868/api/register','java.lang.RuntimeException: æ‰‹æœºå·ç æ ¼å¼é”™è¯¯',NULL,'2020-11-24 15:48:08'),(543,NULL,NULL,'error',NULL,'http://localhost:6868/api/openbank','org.springframework.dao.DataIntegrityViolationException: \n### Error updating database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Column \'id\' cannot be null\n### The error may involve com.bootdo.system.dao.MemberBankDao.save-Inline\n### The error occurred while setting parameters\n### SQL: insert into zwl_member_bank   (    `id`,    `back_name`,    `branch_name`,    `card_no`,    `create_time`,    `status`,    `remark`,    `mid`,    `username`   )   values   (    ?,    ?,    ?,    ?,    ?,    ?,    ?,    ?,    ?   )\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Column \'id\' cannot be null\n; ]; Column \'id\' cannot be null; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Column \'id\' cannot be null',NULL,'2020-11-24 16:22:32'),(544,NULL,NULL,'error',NULL,'http://localhost:6868/api/bankCard','org.springframework.web.HttpRequestMethodNotSupportedException: Request method \'POST\' not supported',NULL,'2020-11-24 16:28:53'),(545,NULL,NULL,'error',NULL,'http://localhost:6868/api/goPay','org.springframework.data.redis.RedisSystemException: Error in execution; nested exception is io.lettuce.core.RedisCommandExecutionException: ERR value is not an integer or out of range',NULL,'2020-11-24 18:13:34'),(546,NULL,NULL,'error',NULL,'http://localhost:6868/api/goPay','org.springframework.data.redis.RedisSystemException: Error in execution; nested exception is io.lettuce.core.RedisCommandExecutionException: ERR value is not an integer or out of range',NULL,'2020-11-24 18:14:53'),(547,NULL,NULL,'error',NULL,'http://localhost:6868/api/goPay','org.springframework.data.redis.RedisSystemException: Error in execution; nested exception is io.lettuce.core.RedisCommandExecutionException: ERR value is not an integer or out of range',NULL,'2020-11-24 18:20:24'),(548,NULL,NULL,'error',NULL,'http://localhost:6868/api/goPay','org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.reflection.ReflectionException: There is no getter for property named \'bankBranch\' in \'class com.bootdo.system.domain.UstdLogDO\'',NULL,'2020-11-24 19:23:45'),(549,NULL,NULL,'error',NULL,'http://localhost:6868/api/goPay','org.springframework.jdbc.BadSqlGrammarException: \n### Error updating database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column \'username\' in \'field list\'\n### The error may involve com.bootdo.system.dao.UstdLogDao.save-Inline\n### The error occurred while setting parameters\n### SQL: insert into zwl_ustd_log   (    `mid`,    `no`,    `amount`,    `status`,    `source`,    `target`,    `create_time`,    `cny_price`,    `total_amount`,    `profile_amount`,    `operator`,    `username`,    `bank_name`,    `bank_branch`,    `bank_no`,    `really_name`   )   values   (    ?,    ?,    ?,    ?,    ?,    ?,    ?,    ?,    ?,    ?,    ?,    ?,    ?,    ?,    ?,    ?   )\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column \'username\' in \'field list\'\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column \'username\' in \'field list\'',NULL,'2020-11-24 19:33:46'),(550,NULL,NULL,'error',NULL,'http://localhost:6868/api/my','org.springframework.web.HttpRequestMethodNotSupportedException: Request method \'POST\' not supported',NULL,'2020-11-24 19:56:05'),(551,-1,'è·å–ç”¨æˆ·ä¿¡æ¯ä¸ºç©º','ç™»å½•',29,'com.bootdo.system.controller.LoginController.ajaxLogin()',NULL,'127.0.0.1','2021-03-13 16:54:05'),(552,1,'admin','ç™»å½•',8,'com.bootdo.system.controller.LoginController.ajaxLogin()',NULL,'127.0.0.1','2021-03-13 16:54:11'),(553,1,'admin','è¯·æ±‚è®¿é—®ä¸»é¡µ',41,'com.bootdo.system.controller.LoginController.index()','{\"menus\":[{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-book\",\"url\":\"/common/dict\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"78\",\"parentId\":\"1\",\"text\":\"æ•°æ®å­—å…¸\"},{\"attributes\":{\"icon\":\"fa fa-folder-open\",\"url\":\"/common/sysFile\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"71\",\"parentId\":\"1\",\"text\":\"æ–‡ä»¶ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"1\",\"parentId\":\"0\",\"text\":\"åŸºç¡€ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-desktop\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-barcode\",\"url\":\"/system/ustdLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"115\",\"parentId\":\"3\",\"text\":\"USDTäº¤æ˜“\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/user/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"6\",\"parentId\":\"3\",\"text\":\"ç”¨æˆ·ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-paw\",\"url\":\"sys/role\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"7\",\"parentId\":\"3\",\"text\":\"è§’è‰²ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-th-list\",\"url\":\"sys/menu/\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"2\",\"parentId\":\"3\",\"text\":\"ç³»ç»Ÿèœå•\"},{\"attributes\":{\"icon\":\"fa fa-users\",\"url\":\"/system/sysDept\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"73\",\"parentId\":\"3\",\"text\":\"éƒ¨é—¨ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"/system/member\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"107\",\"parentId\":\"105\",\"text\":\"ç¼–è¾‘\"}],\"hasChildren\":true,\"hasParent\":true,\"id\":\"105\",\"parentId\":\"3\",\"text\":\"ä¼šå‘˜ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"/system/payGateway\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"108\",\"parentId\":\"3\",\"text\":\"æ¸ é“ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bar-chart-o\",\"url\":\"/system/tradeLog\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"111\",\"parentId\":\"3\",\"text\":\"äº¤æ˜“è®°å½•\"},{\"attributes\":{\"icon\":\"fa fa-archive\",\"url\":\"/system/investMoney\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"112\",\"parentId\":\"3\",\"text\":\"å……å€¼ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-bars\",\"url\":\"/system/withdraw\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"113\",\"parentId\":\"3\",\"text\":\"æç°ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-suitcase\",\"url\":\"/system/agreement\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"114\",\"parentId\":\"3\",\"text\":\"åˆçº¦ç®¡ç†\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"3\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-gear\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"\",\"url\":\"/swagger-ui.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"104\",\"parentId\":\"77\",\"text\":\"swagger\"},{\"attributes\":{\"icon\":\"fa fa-code\",\"url\":\"common/generator\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"48\",\"parentId\":\"77\",\"text\":\"ä»£ç ç”Ÿæˆ\"},{\"attributes\":{\"icon\":\"fa fa-hourglass-1\",\"url\":\"common/job\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"72\",\"parentId\":\"77\",\"text\":\"è®¡åˆ’ä»»åŠ¡\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"77\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿå·¥å…·\"},{\"attributes\":{\"icon\":\"fa fa-video-camera\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-user\",\"url\":\"sys/online\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"92\",\"parentId\":\"91\",\"text\":\"åœ¨çº¿ç”¨æˆ·\"},{\"attributes\":{\"icon\":\"fa fa-warning\",\"url\":\"common/log\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"27\",\"parentId\":\"91\",\"text\":\"ç³»ç»Ÿæ—¥å¿—\"},{\"attributes\":{\"icon\":\"fa fa-caret-square-o-right\",\"url\":\"/druid/index.html\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"57\",\"parentId\":\"91\",\"text\":\"è¿è¡Œç›‘æ§\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"91\",\"parentId\":\"0\",\"text\":\"ç³»ç»Ÿç›‘æ§\"},{\"attributes\":{\"icon\":\"fa fa-laptop\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-envelope-square\",\"url\":\"oa/notify/selfNotify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"90\",\"parentId\":\"84\",\"text\":\"æˆ‘çš„é€šçŸ¥\"},{\"attributes\":{\"icon\":\"fa fa-pencil-square\",\"url\":\"oa/notify\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"85\",\"parentId\":\"84\",\"text\":\"é€šçŸ¥å…¬å‘Š\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"84\",\"parentId\":\"0\",\"text\":\"åŠå…¬ç®¡ç†\"},{\"attributes\":{\"icon\":\"fa fa-rss\",\"url\":\"\"},\"checked\":false,\"children\":[{\"attributes\":{\"icon\":\"fa fa-edit\",\"url\":\"/blog/bContent/add\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"68\",\"parentId\":\"49\",\"text\":\"å‘å¸ƒæ–‡ç« \"},{\"attributes\":{\"icon\":\"fa fa-file-image-o\",\"url\":\"blog/bContent\"},\"checked\":false,\"children\":[],\"hasChildren\":false,\"hasParent\":true,\"id\":\"50\",\"parentId\":\"49\",\"text\":\"æ–‡ç« åˆ—è¡¨\"}],\"hasChildren\":true,\"hasParent\":false,\"id\":\"49\",\"parentId\":\"0\",\"text\":\"åšå®¢ç®¡ç†\"},{\"attributes\":{\"icon\":','127.0.0.1','2021-03-13 16:54:11'),(554,NULL,NULL,'error',NULL,'http://localhost:6868/api/addTask','org.springframework.web.HttpRequestMethodNotSupportedException: Request method \'GET\' not supported',NULL,'2021-03-13 07:48:26'),(555,NULL,NULL,'error',NULL,'http://localhost:6868/api/addTask','java.lang.NullPointerException',NULL,'2021-03-13 08:40:34'),(556,NULL,NULL,'error',NULL,'http://localhost:6868/api/unBindHuobiAccount','java.lang.RuntimeException: æ‰‹æœºå·ç æ ¼å¼é”™è¯¯',NULL,'2021-03-21 00:43:22'),(557,NULL,NULL,'error',NULL,'http://localhost:6868/api/closeTask','org.springframework.web.HttpRequestMethodNotSupportedException: Request method \'GET\' not supported',NULL,'2021-03-21 08:48:13'),(558,NULL,NULL,'error',NULL,'http://localhost:6868/api/addTask','java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String',NULL,'2021-04-03 01:26:39');
/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT 'çˆ¶èœå•IDï¼Œä¸€çº§èœå•ä¸º0',
  `name` varchar(50) DEFAULT NULL COMMENT 'èœå•åç§°',
  `url` varchar(200) DEFAULT NULL COMMENT 'èœå•URL',
  `perms` varchar(500) DEFAULT NULL COMMENT 'æˆæƒ(å¤šä¸ªç”¨é€—å·åˆ†éš”ï¼Œå¦‚ï¼šuser:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT 'ç±»å‹   0ï¼šç›®å½•   1ï¼šèœå•   2ï¼šæŒ‰é’®',
  `icon` varchar(50) DEFAULT NULL COMMENT 'èœå•å›¾æ ‡',
  `order_num` int(11) DEFAULT NULL COMMENT 'æ’åº',
  `gmt_create` datetime DEFAULT NULL COMMENT 'åˆ›å»ºæ—¶é—´',
  `gmt_modified` datetime DEFAULT NULL COMMENT 'ä¿®æ”¹æ—¶é—´',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8 COMMENT='èœå•ç®¡ç†';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,0,'åŸºç¡€ç®¡ç†','','',0,'fa fa-bars',0,'2017-08-09 22:49:47',NULL),(2,3,'ç³»ç»Ÿèœå•','sys/menu/','sys:menu:menu',1,'fa fa-th-list',2,'2017-08-09 22:55:15',NULL),(3,0,'ç³»ç»Ÿç®¡ç†',NULL,NULL,0,'fa fa-desktop',1,'2017-08-09 23:06:55','2017-08-14 14:13:43'),(6,3,'ç”¨æˆ·ç®¡ç†','sys/user/','sys:user:user',1,'fa fa-user',0,'2017-08-10 14:12:11',NULL),(7,3,'è§’è‰²ç®¡ç†','sys/role','sys:role:role',1,'fa fa-paw',1,'2017-08-10 14:13:19',NULL),(12,6,'æ–°å¢','','sys:user:add',2,'',0,'2017-08-14 10:51:35',NULL),(13,6,'ç¼–è¾‘','','sys:user:edit',2,'',0,'2017-08-14 10:52:06',NULL),(14,6,'åˆ é™¤',NULL,'sys:user:remove',2,NULL,0,'2017-08-14 10:52:24',NULL),(15,7,'æ–°å¢','','sys:role:add',2,'',0,'2017-08-14 10:56:37',NULL),(20,2,'æ–°å¢','','sys:menu:add',2,'',0,'2017-08-14 10:59:32',NULL),(21,2,'ç¼–è¾‘','','sys:menu:edit',2,'',0,'2017-08-14 10:59:56',NULL),(22,2,'åˆ é™¤','','sys:menu:remove',2,'',0,'2017-08-14 11:00:26',NULL),(24,6,'æ‰¹é‡åˆ é™¤','','sys:user:batchRemove',2,'',0,'2017-08-14 17:27:18',NULL),(25,6,'åœç”¨',NULL,'sys:user:disable',2,NULL,0,'2017-08-14 17:27:43',NULL),(26,6,'é‡ç½®å¯†ç ','','sys:user:resetPwd',2,'',0,'2017-08-14 17:28:34',NULL),(27,91,'ç³»ç»Ÿæ—¥å¿—','common/log','common:log',1,'fa fa-warning',0,'2017-08-14 22:11:53',NULL),(28,27,'åˆ·æ–°',NULL,'sys:log:list',2,NULL,0,'2017-08-14 22:30:22',NULL),(29,27,'åˆ é™¤',NULL,'sys:log:remove',2,NULL,0,'2017-08-14 22:30:43',NULL),(30,27,'æ¸…ç©º',NULL,'sys:log:clear',2,NULL,0,'2017-08-14 22:31:02',NULL),(48,77,'ä»£ç ç”Ÿæˆ','common/generator','common:generator',1,'fa fa-code',3,NULL,NULL),(49,0,'åšå®¢ç®¡ç†','','',0,'fa fa-rss',6,NULL,NULL),(50,49,'æ–‡ç« åˆ—è¡¨','blog/bContent','blog:bContent:bContent',1,'fa fa-file-image-o',1,NULL,NULL),(51,50,'æ–°å¢','','blog:bContent:add',2,'',NULL,NULL,NULL),(55,7,'ç¼–è¾‘','','sys:role:edit',2,'',NULL,NULL,NULL),(56,7,'åˆ é™¤','','sys:role:remove',2,NULL,NULL,NULL,NULL),(57,91,'è¿è¡Œç›‘æ§','/druid/index.html','',1,'fa fa-caret-square-o-right',1,NULL,NULL),(58,50,'ç¼–è¾‘','','blog:bContent:edit',2,NULL,NULL,NULL,NULL),(59,50,'åˆ é™¤','','blog:bContent:remove',2,NULL,NULL,NULL,NULL),(60,50,'æ‰¹é‡åˆ é™¤','','blog:bContent:batchRemove',2,NULL,NULL,NULL,NULL),(61,2,'æ‰¹é‡åˆ é™¤','','sys:menu:batchRemove',2,NULL,NULL,NULL,NULL),(62,7,'æ‰¹é‡åˆ é™¤','','sys:role:batchRemove',2,NULL,NULL,NULL,NULL),(68,49,'å‘å¸ƒæ–‡ç« ','/blog/bContent/add','blog:bContent:add',1,'fa fa-edit',0,NULL,NULL),(71,1,'æ–‡ä»¶ç®¡ç†','/common/sysFile','common:sysFile:sysFile',1,'fa fa-folder-open',2,NULL,NULL),(72,77,'è®¡åˆ’ä»»åŠ¡','common/job','common:taskScheduleJob',1,'fa fa-hourglass-1',4,NULL,NULL),(73,3,'éƒ¨é—¨ç®¡ç†','/system/sysDept','system:sysDept:sysDept',1,'fa fa-users',3,NULL,NULL),(74,73,'å¢åŠ ','/system/sysDept/add','system:sysDept:add',2,NULL,1,NULL,NULL),(75,73,'åˆªé™¤','system/sysDept/remove','system:sysDept:remove',2,NULL,2,NULL,NULL),(76,73,'ç¼–è¾‘','/system/sysDept/edit','system:sysDept:edit',2,NULL,3,NULL,NULL),(77,0,'ç³»ç»Ÿå·¥å…·','','',0,'fa fa-gear',4,NULL,NULL),(78,1,'æ•°æ®å­—å…¸','/common/dict','common:dict:dict',1,'fa fa-book',1,NULL,NULL),(79,78,'å¢åŠ ','/common/dict/add','common:dict:add',2,NULL,2,NULL,NULL),(80,78,'ç¼–è¾‘','/common/dict/edit','common:dict:edit',2,NULL,2,NULL,NULL),(81,78,'åˆ é™¤','/common/dict/remove','common:dict:remove',2,'',3,NULL,NULL),(83,78,'æ‰¹é‡åˆ é™¤','/common/dict/batchRemove','common:dict:batchRemove',2,'',4,NULL,NULL),(84,0,'åŠå…¬ç®¡ç†','','',0,'fa fa-laptop',5,NULL,NULL),(85,84,'é€šçŸ¥å…¬å‘Š','oa/notify','oa:notify:notify',1,'fa fa-pencil-square',NULL,NULL,NULL),(86,85,'æ–°å¢','oa/notify/add','oa:notify:add',2,'fa fa-plus',1,NULL,NULL),(87,85,'ç¼–è¾‘','oa/notify/edit','oa:notify:edit',2,'fa fa-pencil-square-o',2,NULL,NULL),(88,85,'åˆ é™¤','oa/notify/remove','oa:notify:remove',2,'fa fa-minus',NULL,NULL,NULL),(89,85,'æ‰¹é‡åˆ é™¤','oa/notify/batchRemove','oa:notify:batchRemove',2,'',NULL,NULL,NULL),(90,84,'æˆ‘çš„é€šçŸ¥','oa/notify/selfNotify','',1,'fa fa-envelope-square',NULL,NULL,NULL),(91,0,'ç³»ç»Ÿç›‘æ§','','',0,'fa fa-video-camera',5,NULL,NULL),(92,91,'åœ¨çº¿ç”¨æˆ·','sys/online','',1,'fa fa-user',NULL,NULL,NULL),(93,0,'å·¥ä½œæµç¨‹','','',0,'fa fa-print',6,NULL,NULL),(94,93,'æ¨¡å‹ç®¡ç†','activiti/model','',1,'fa fa-sort-amount-asc',NULL,NULL,NULL),(95,94,'å…¨éƒ¨æƒé™','','activiti:model',2,'',NULL,NULL,NULL),(96,93,'æµç¨‹ç®¡ç†','activiti/process','',1,'fa fa-flag',NULL,NULL,NULL),(97,0,'å›¾è¡¨ç®¡ç†','','',0,'fa fa-bar-chart',7,NULL,NULL),(98,97,'ç™¾åº¦chart','/chart/graph_echarts.html','',1,'fa fa-area-chart',NULL,NULL,NULL),(99,96,'æ‰€æœ‰æƒé™','','act:process',2,'',NULL,NULL,NULL),(101,93,'å¾…åŠä»»åŠ¡','activiti/task/todo','',1,'',NULL,NULL,NULL),(104,77,'swagger','/swagger-ui.html','',1,'',NULL,NULL,NULL),(105,3,'ä¼šå‘˜ç®¡ç†','/system/member','system:member:member',1,'fa fa-user',5,NULL,NULL),(106,105,'æ–°å¢','','system:member:add',2,'fa fa-add',1,NULL,NULL),(107,105,'ç¼–è¾‘','','system:member:edit',1,'fa fa-edit',2,NULL,NULL),(108,3,'æ¸ é“ç®¡ç†','/system/payGateway','system:payGateway:payGateway',1,'fa fa-gear',6,NULL,NULL),(109,108,'æ–°å¢','/system/payGateway/add','system:payGateway:add',2,'fa fa-add',1,NULL,NULL),(110,108,'ç¼–è¾‘','/system/payGateway/edit','/system:payGateway:add	',2,'fa fa-edit',2,NULL,NULL),(111,3,'äº¤æ˜“è®°å½•','/system/tradeLog','',1,'fa fa-bar-chart-o',110,NULL,NULL),(112,3,'å……å€¼ç®¡ç†','/system/investMoney','',1,'fa fa-archive',112,NULL,NULL),(113,3,'æç°ç®¡ç†','/system/withdraw','',1,'fa fa-bars',114,NULL,NULL),(114,3,'åˆçº¦ç®¡ç†','/system/agreement','',1,'fa fa-suitcase',115,NULL,NULL),(115,3,'USDTäº¤æ˜“','/system/ustdLog','',1,'fa fa-barcode',NULL,NULL,NULL);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT 'è§’è‰²åç§°',
  `role_sign` varchar(100) DEFAULT NULL COMMENT 'è§’è‰²æ ‡è¯†',
  `remark` varchar(100) DEFAULT NULL COMMENT 'å¤‡æ³¨',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT 'åˆ›å»ºç”¨æˆ·id',
  `gmt_create` datetime DEFAULT NULL COMMENT 'åˆ›å»ºæ—¶é—´',
  `gmt_modified` datetime DEFAULT NULL COMMENT 'åˆ›å»ºæ—¶é—´',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 COMMENT='è§’è‰²';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'è¶…çº§ç”¨æˆ·è§’è‰²','admin','æ‹¥æœ‰æœ€é«˜æƒé™',2,'2017-08-12 00:43:52','2017-08-12 19:14:59'),(59,'æ™®é€šç”¨æˆ·',NULL,'åŸºæœ¬ç”¨æˆ·æƒé™',NULL,NULL,NULL);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT 'è§’è‰²ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT 'èœå•ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3638 DEFAULT CHARSET=utf8 COMMENT='è§’è‰²ä¸èœå•å¯¹åº”å…³ç³»';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES (367,44,1),(368,44,32),(369,44,33),(370,44,34),(371,44,35),(372,44,28),(373,44,29),(374,44,30),(375,44,38),(376,44,4),(377,44,27),(378,45,38),(379,46,3),(380,46,20),(381,46,21),(382,46,22),(383,46,23),(384,46,11),(385,46,12),(386,46,13),(387,46,14),(388,46,24),(389,46,25),(390,46,26),(391,46,15),(392,46,2),(393,46,6),(394,46,7),(598,50,38),(632,38,42),(737,51,38),(738,51,39),(739,51,40),(740,51,41),(741,51,4),(742,51,32),(743,51,33),(744,51,34),(745,51,35),(746,51,27),(747,51,28),(748,51,29),(749,51,30),(750,51,1),(1064,54,53),(1095,55,2),(1096,55,6),(1097,55,7),(1098,55,3),(1099,55,50),(1100,55,49),(1101,55,1),(1856,53,28),(1857,53,29),(1858,53,30),(1859,53,27),(1860,53,57),(1861,53,71),(1862,53,48),(1863,53,72),(1864,53,1),(1865,53,7),(1866,53,55),(1867,53,56),(1868,53,62),(1869,53,15),(1870,53,2),(1871,53,61),(1872,53,20),(1873,53,21),(1874,53,22),(2084,56,68),(2085,56,60),(2086,56,59),(2087,56,58),(2088,56,51),(2089,56,50),(2090,56,49),(2243,48,72),(2247,63,-1),(2248,63,84),(2249,63,85),(2250,63,88),(2251,63,87),(2252,64,84),(2253,64,89),(2254,64,88),(2255,64,87),(2256,64,86),(2257,64,85),(2258,65,89),(2259,65,88),(2260,65,86),(2262,67,48),(2263,68,88),(2264,68,87),(2265,69,89),(2266,69,88),(2267,69,86),(2268,69,87),(2269,69,85),(2270,69,84),(2271,70,85),(2272,70,89),(2273,70,88),(2274,70,87),(2275,70,86),(2276,70,84),(2277,71,87),(2278,72,59),(2279,73,48),(2280,74,88),(2281,74,87),(2282,75,88),(2283,75,87),(2284,76,85),(2285,76,89),(2286,76,88),(2287,76,87),(2288,76,86),(2289,76,84),(2292,78,88),(2293,78,87),(2294,78,NULL),(2295,78,NULL),(2296,78,NULL),(2308,80,87),(2309,80,86),(2310,80,-1),(2311,80,84),(2312,80,85),(2328,79,72),(2329,79,48),(2330,79,77),(2331,79,84),(2332,79,89),(2333,79,88),(2334,79,87),(2335,79,86),(2336,79,85),(2337,79,-1),(2338,77,89),(2339,77,88),(2340,77,87),(2341,77,86),(2342,77,85),(2343,77,84),(2344,77,72),(2345,77,-1),(2346,77,77),(2974,57,93),(2975,57,99),(2976,57,95),(2977,57,101),(2978,57,96),(2979,57,94),(2980,57,-1),(2981,58,93),(2982,58,99),(2983,58,95),(2984,58,101),(2985,58,96),(2986,58,94),(2987,58,-1),(3232,59,98),(3233,59,101),(3234,59,99),(3235,59,95),(3236,59,90),(3237,59,89),(3238,59,88),(3239,59,87),(3240,59,86),(3241,59,68),(3242,59,60),(3243,59,59),(3244,59,58),(3245,59,51),(3246,59,76),(3247,59,75),(3248,59,74),(3249,59,62),(3250,59,56),(3251,59,55),(3252,59,15),(3253,59,26),(3254,59,25),(3255,59,24),(3256,59,14),(3257,59,13),(3258,59,12),(3259,59,61),(3260,59,22),(3261,59,21),(3262,59,20),(3263,59,83),(3264,59,81),(3265,59,80),(3266,59,79),(3267,59,71),(3268,59,97),(3269,59,96),(3270,59,94),(3271,59,93),(3272,59,85),(3273,59,84),(3274,59,50),(3275,59,49),(3276,59,73),(3277,59,7),(3278,59,6),(3279,59,2),(3280,59,3),(3281,59,78),(3282,59,1),(3283,59,-1),(3564,1,98),(3565,1,101),(3566,1,99),(3567,1,95),(3568,1,92),(3569,1,57),(3570,1,30),(3571,1,29),(3572,1,28),(3573,1,90),(3574,1,89),(3575,1,88),(3576,1,87),(3577,1,86),(3578,1,104),(3579,1,72),(3580,1,48),(3581,1,68),(3582,1,60),(3583,1,59),(3584,1,58),(3585,1,51),(3586,1,114),(3587,1,113),(3588,1,112),(3589,1,111),(3590,1,110),(3591,1,109),(3592,1,107),(3593,1,106),(3594,1,76),(3595,1,75),(3596,1,74),(3597,1,62),(3598,1,56),(3599,1,55),(3600,1,15),(3601,1,26),(3602,1,25),(3603,1,24),(3604,1,14),(3605,1,13),(3606,1,12),(3607,1,61),(3608,1,22),(3609,1,21),(3610,1,20),(3611,1,83),(3612,1,81),(3613,1,80),(3614,1,79),(3615,1,71),(3616,1,97),(3617,1,96),(3618,1,94),(3619,1,93),(3620,1,27),(3621,1,91),(3622,1,85),(3623,1,84),(3624,1,77),(3625,1,50),(3626,1,49),(3627,1,108),(3628,1,105),(3629,1,73),(3630,1,7),(3631,1,6),(3632,1,2),(3633,1,78),(3634,1,1),(3635,1,115),(3636,1,3),(3637,1,-1);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_task`
--

DROP TABLE IF EXISTS `sys_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cronè¡¨è¾¾å¼',
  `method_name` varchar(255) DEFAULT NULL COMMENT 'ä»»åŠ¡è°ƒç”¨çš„æ–¹æ³•å',
  `is_concurrent` varchar(255) DEFAULT NULL COMMENT 'ä»»åŠ¡æ˜¯å¦æœ‰çŠ¶æ€',
  `description` varchar(255) DEFAULT NULL COMMENT 'ä»»åŠ¡æè¿°',
  `update_by` varchar(64) DEFAULT NULL COMMENT 'æ›´æ–°è€…',
  `bean_class` varchar(255) DEFAULT NULL COMMENT 'ä»»åŠ¡æ‰§è¡Œæ—¶è°ƒç”¨å“ªä¸ªç±»çš„æ–¹æ³• åŒ…å+ç±»å',
  `create_date` datetime DEFAULT NULL COMMENT 'åˆ›å»ºæ—¶é—´',
  `job_status` varchar(255) DEFAULT NULL COMMENT 'ä»»åŠ¡çŠ¶æ€',
  `job_group` varchar(255) DEFAULT NULL COMMENT 'ä»»åŠ¡åˆ†ç»„',
  `update_date` datetime DEFAULT NULL COMMENT 'æ›´æ–°æ—¶é—´',
  `create_by` varchar(64) DEFAULT NULL COMMENT 'åˆ›å»ºè€…',
  `spring_bean` varchar(255) DEFAULT NULL COMMENT 'Spring bean',
  `job_name` varchar(255) DEFAULT NULL COMMENT 'ä»»åŠ¡å',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=35570 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_task`
--

LOCK TABLES `sys_task` WRITE;
/*!40000 ALTER TABLE `sys_task` DISABLE KEYS */;
INSERT INTO `sys_task` VALUES (35563,'* 5 13 30 5 ? 2019',NULL,NULL,'{agreementId=5055, mid=55796, tid=128094}',NULL,'','2019-05-23 13:05:26','start','agreement',NULL,NULL,NULL,'agreement_1558587926444'),(35564,'15914',NULL,NULL,'{mid=55796, tid=128095}',NULL,'','2019-05-23 15:30:00','start','purchase',NULL,NULL,NULL,'purchase_back1558596599999'),(35565,'* 7 21 20 6 ? 2019',NULL,NULL,'{agreementId=5059, mid=55797, tid=128151}',NULL,'','2019-06-13 21:07:23','start','agreement',NULL,NULL,NULL,'agreement_1560431242968'),(35566,'* 7 21 20 6 ? 2019',NULL,NULL,'{agreementId=5057, mid=55797, tid=128148}',NULL,'','2019-06-13 21:07:23','start','agreement',NULL,NULL,NULL,'agreement_1560431242967'),(35567,'* 7 21 20 6 ? 2019',NULL,NULL,'{agreementId=5056, mid=55797, tid=128149}',NULL,'','2019-06-13 21:07:23','start','agreement',NULL,NULL,NULL,'agreement_1560431242967'),(35568,'* 7 21 20 6 ? 2019',NULL,NULL,'{agreementId=5058, mid=55797, tid=128150}',NULL,'','2019-06-13 21:07:23','start','agreement',NULL,NULL,NULL,'agreement_1560431242967'),(35569,'* 48 15 25 8 ? 2019',NULL,NULL,'{agreementId=5060, mid=55797, tid=128155}',NULL,'','2019-08-18 15:49:00','start','agreement',NULL,NULL,NULL,'agreement_1566114539894');
/*!40000 ALTER TABLE `sys_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT 'ç”¨æˆ·å',
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL COMMENT 'å¯†ç ',
  `dept_id` bigint(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL COMMENT 'é‚®ç®±',
  `mobile` varchar(100) DEFAULT NULL COMMENT 'æ‰‹æœºå·',
  `status` tinyint(255) DEFAULT NULL COMMENT 'çŠ¶æ€ 0:ç¦ç”¨ï¼Œ1:æ­£å¸¸',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT 'åˆ›å»ºç”¨æˆ·id',
  `gmt_create` datetime DEFAULT NULL COMMENT 'åˆ›å»ºæ—¶é—´',
  `gmt_modified` datetime DEFAULT NULL COMMENT 'ä¿®æ”¹æ—¶é—´',
  `sex` bigint(32) DEFAULT NULL COMMENT 'æ€§åˆ«',
  `birth` datetime DEFAULT NULL COMMENT 'å‡ºèº«æ—¥æœŸ',
  `pic_id` bigint(32) DEFAULT NULL,
  `live_address` varchar(500) DEFAULT NULL COMMENT 'ç°å±…ä½åœ°',
  `hobby` varchar(255) DEFAULT NULL COMMENT 'çˆ±å¥½',
  `province` varchar(255) DEFAULT NULL COMMENT 'çœä»½',
  `city` varchar(255) DEFAULT NULL COMMENT 'æ‰€åœ¨åŸå¸‚',
  `district` varchar(255) DEFAULT NULL COMMENT 'æ‰€åœ¨åœ°åŒº',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'admin','è¶…çº§ç®¡ç†å‘˜','d0af8fa1272ef5a152d9e27763eea293',6,'admin@example.com','17699999999',1,1,'2017-08-15 21:40:39','2017-08-15 21:41:00',96,'2017-12-14 00:00:00',138,'ccc','122;121;','åŒ—äº¬å¸‚','åŒ—äº¬å¸‚å¸‚è¾–åŒº','ä¸œåŸåŒº'),(2,'test','ä¸´æ—¶ç”¨æˆ·','6cf3bb3deba2aadbd41ec9a22511084e',6,'test@bootdo.com',NULL,1,1,'2017-08-14 13:43:05','2017-08-14 21:15:36',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(36,'ldh','åˆ˜å¾·å','bfd9394475754fbe45866eba97738c36',7,'ldh@bootdo.com',NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(123,'zxy','å¼ å­¦å‹','35174ba93f5fe7267f1fb3c1bf903781',6,'zxy@bootdo',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(124,'wyf','å´äº¦å‡¡','e179e6f687bbd57b9d7efc4746c8090a',6,'wyf@bootdo.com',NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(130,'lh','é¹¿æ™—','7924710cd673f68967cde70e188bb097',9,'lh@bootdo.com',NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(131,'lhc','ä»¤ç‹å†²','d515538e17ecb570ba40344b5618f5d4',6,'lhc@bootdo.com',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(132,'lyf','åˆ˜äº¦è²','7fdb1d9008f45950c1620ba0864e5fbd',13,'lyf@bootdo.com',NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(134,'lyh','æå½¦å®','dc26092b3244d9d432863f2738180e19',8,'lyh@bootdo.com',NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(135,'wjl','ç‹å¥æ—','3967697dfced162cf6a34080259b83aa',6,'wjl@bootod.com',NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(136,'gdg','éƒ­å¾·çº²','3bb1bda86bc02bf6478cd91e42135d2f',9,'gdg@bootdo.com',NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_plus`
--

DROP TABLE IF EXISTS `sys_user_plus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_plus` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `payment` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_plus`
--

LOCK TABLES `sys_user_plus` WRITE;
/*!40000 ALTER TABLE `sys_user_plus` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_plus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT 'ç”¨æˆ·ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT 'è§’è‰²ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8 COMMENT='ç”¨æˆ·ä¸è§’è‰²å¯¹åº”å…³ç³»';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (73,30,48),(74,30,49),(75,30,50),(76,31,48),(77,31,49),(78,31,52),(79,32,48),(80,32,49),(81,32,50),(82,32,51),(83,32,52),(84,33,38),(85,33,49),(86,33,52),(87,34,50),(88,34,51),(89,34,52),(106,124,1),(110,1,1),(111,2,1),(113,131,48),(117,135,1),(120,134,1),(121,134,48),(123,130,1),(124,NULL,48),(125,132,52),(126,132,49),(127,123,48),(132,36,48);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zwl_agreement`
--

DROP TABLE IF EXISTS `zwl_agreement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zwl_agreement` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ä¸»é”®',
  `mid` int(11) DEFAULT NULL COMMENT 'ä¼šå‘˜id',
  `type` varchar(32) DEFAULT NULL COMMENT 'ç±»å‹',
  `start_time` datetime DEFAULT NULL COMMENT 'å¼€å§‹æ—¶é—´',
  `end_time` datetime DEFAULT NULL COMMENT 'ç»“æŸæ—¶é—´',
  `percent` varchar(32) DEFAULT NULL COMMENT 'ä½£é‡‘æ¯”ä¾‹',
  `mobile` varchar(32) DEFAULT NULL COMMENT 'æ‰‹æœºå·',
  `status` varchar(32) DEFAULT NULL COMMENT 'åˆçº¦çŠ¶æ€',
  `amount` varchar(64) DEFAULT NULL COMMENT 'åˆçº¦çš„é‡‘é¢',
  `free_amount` varchar(64) DEFAULT NULL COMMENT 'å†»ç»“çš„é‡‘é¢',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10357 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zwl_agreement`
--

LOCK TABLES `zwl_agreement` WRITE;
/*!40000 ALTER TABLE `zwl_agreement` DISABLE KEYS */;
INSERT INTO `zwl_agreement` VALUES (10339,66049,'month','2020-03-08 20:30:24','2020-05-09 22:13:04','0.25','185652535109','disable','7500000','2500000'),(10340,66048,'month','2020-03-08 20:30:24','2020-05-09 22:13:04','0.25','185651482179','disable','7500000','2500000'),(10341,66047,'month','2020-03-08 20:30:24','2020-05-09 22:13:04','0.25','185652835254','disable','7500000','2500000'),(10342,66046,'month','2020-03-08 20:30:24','2020-05-09 22:13:04','0.25','185651873385','disable','7500000','2500000'),(10343,66045,'month','2020-03-08 20:30:24','2020-05-09 22:13:04','0.25','185652457041','disable','7500000','2500000'),(10344,66044,'month','2020-03-08 20:30:24','2020-05-09 22:13:04','0.25','185651463576','disable','7500000','2500000'),(10345,66043,'month','2020-03-08 20:30:24','2020-05-09 22:13:04','0.25','185652622416','disable','7500000','2500000'),(10346,66042,'month','2020-03-08 20:30:24','2020-05-09 22:13:04','0.25','185651595786','disable','7500000','2500000'),(10347,66041,'month','2020-03-08 20:30:24','2020-05-09 22:13:04','0.25','15162603930','disable','7500000','2500000'),(10348,66040,'month','2020-03-08 20:30:24','2020-05-09 22:13:04','0.25','15161424661','disable','7500000','2500000'),(10349,66039,'month','2020-03-08 20:30:24','2020-05-09 22:13:04','0.25','15162431150','disable','7500000','2500000'),(10350,66038,'month','2020-03-08 20:30:24','2020-05-09 22:13:04','0.25','15161137284','disable','7500000','2500000'),(10351,66037,'month','2020-03-08 20:30:24','2020-05-09 22:13:04','0.25','13162769370','disable','7500000','2500000'),(10352,66036,'month','2020-03-08 20:30:24','2020-05-09 22:13:04','0.25','13162900695','disable','7500000','2500000'),(10353,66035,'month','2020-03-08 20:30:24','2020-05-09 22:13:04','0.25','13162016582','disable','7500000','2500000'),(10354,66034,'month','2020-03-08 20:30:24','2020-05-09 22:13:04','0.25','13162074134','disable','7500000','2500000'),(10355,66033,'month','2020-03-08 20:30:24','2020-05-09 22:13:04','0.25','13162727753','disable','7500000','2500000'),(10356,66032,'month','2020-03-08 20:30:24','2020-05-09 22:13:04','0.25','13162789927','disable','7500000','2500000');
/*!40000 ALTER TABLE `zwl_agreement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zwl_coin_task_info`
--

DROP TABLE IF EXISTS `zwl_coin_task_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zwl_coin_task_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ä¸»é”®id',
  `mid` int(11) DEFAULT NULL COMMENT 'ä¼šå‘˜id',
  `total_amount` varchar(16) DEFAULT '0.0' COMMENT 'æŒä»“æ€»é¢',
  `total_position` varchar(16) DEFAULT '0.0' COMMENT 'æŒä»“é‡',
  `current_order_num` int(11) DEFAULT '0',
  `avg_price` varchar(16) DEFAULT '0' COMMENT 'æŒä»“å‡ä»·',
  `current_price` varchar(16) DEFAULT '0' COMMENT 'å½“å‰ä»·æ ¼',
  `total_profit` varchar(16) DEFAULT '0' COMMENT 'æ€»æ”¶ç›Š',
  `profit_loss_ratio` varchar(16) DEFAULT '0' COMMENT 'ç›ˆäºå¹…åº¦',
  `current_profit_loss_amount` varchar(16) DEFAULT '0' COMMENT 'å½“å‰ç›ˆäº',
  `deal_num` int(11) DEFAULT '0' COMMENT 'äº¤æ˜“æ¬¡æ•°',
  `first_amount` varchar(16) DEFAULT NULL COMMENT 'é¦–å•é¢åº¦',
  `plan_order_num` int(11) DEFAULT NULL COMMENT 'è®¡åˆ’åšå•æ•°',
  `cover_ratio` varchar(6) DEFAULT NULL COMMENT 'è¡¥ä»“æ¯”ä¾‹',
  `cover_reset_ratio` varchar(6) DEFAULT NULL COMMENT 'è¡¥ä»“å¤ä½',
  `cover_add_ratio` varchar(6) DEFAULT NULL COMMENT 'è¡¥ä»“å¢å¹…',
  `cover_callback_ratio` varchar(6) DEFAULT NULL COMMENT 'è¡¥ä»“å›è°ƒæ¯”ä¾‹',
  `profit_ratio` varchar(6) DEFAULT NULL COMMENT 'æ­¢ç›ˆæ¯”ä¾‹',
  `profit_callback_ratio` varchar(6) DEFAULT NULL COMMENT 'æ­¢ç›ˆæ¯”ä¾‹å›è°ƒ',
  `create_time` mediumtext COMMENT 'åˆ›å»ºæ—¶é—´',
  `modify_time` mediumtext COMMENT 'æœ€åæ›´æ–°æ—¶é—´',
  `status` varchar(32) DEFAULT NULL COMMENT 'çŠ¶æ€',
  `deleted` smallint(6) DEFAULT NULL COMMENT 'æ˜¯å¦åˆ é™¤',
  `symbol` varchar(12) DEFAULT NULL COMMENT 'ä¹°çš„å¸ç§',
  `type` varchar(45) DEFAULT 'square_market' COMMENT 'ç±»å‹ biå¤šå…ƒï¼Œbiä¹˜æ–¹é™ï¼Œbiä¹˜æ–¹å¸‚',
  `first_price` varchar(16) DEFAULT NULL COMMENT 'é¦–å•ä»·æ ¼',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zwl_coin_task_info`
--

LOCK TABLES `zwl_coin_task_info` WRITE;
/*!40000 ALTER TABLE `zwl_coin_task_info` DISABLE KEYS */;
INSERT INTO `zwl_coin_task_info` VALUES (25,66051,'0.00','0.00',0,NULL,'100.00','0.00','0.00','0.00',0,'100',5,'5','5','5','1','3','1','1617453424472','0','pause_by',0,'ethabc','square_market',NULL);
/*!40000 ALTER TABLE `zwl_coin_task_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zwl_invest_money`
--

DROP TABLE IF EXISTS `zwl_invest_money`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zwl_invest_money` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ä¸»é”®',
  `mid` int(11) DEFAULT NULL COMMENT 'ä¼šå‘˜id',
  `status` varchar(32) CHARACTER SET utf8mb4 NOT NULL COMMENT 'çŠ¶æ€',
  `amount` varchar(64) CHARACTER SET utf8mb4 DEFAULT '0' COMMENT 'é¢åº¦',
  `create_time` datetime DEFAULT NULL COMMENT 'åˆ›å»ºæ—¶é—´',
  `finished_time` datetime DEFAULT NULL COMMENT 'å®Œæˆæ—¶é—´',
  `trade_id` int(11) DEFAULT NULL COMMENT 'æµæ°´è®°å½•id',
  `remark` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT 'å¤‡æ³¨',
  `mobile` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT 'æ‰‹æœºå·',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zwl_invest_money`
--

LOCK TABLES `zwl_invest_money` WRITE;
/*!40000 ALTER TABLE `zwl_invest_money` DISABLE KEYS */;
/*!40000 ALTER TABLE `zwl_invest_money` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zwl_member`
--

DROP TABLE IF EXISTS `zwl_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zwl_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(64) CHARACTER SET latin1 DEFAULT NULL COMMENT 'æ˜µç§°',
  `username` varchar(32) CHARACTER SET latin1 DEFAULT NULL COMMENT 'ç”¨æˆ·å',
  `password` varchar(64) CHARACTER SET latin1 DEFAULT NULL COMMENT 'å¯†ç ',
  `create_time` datetime DEFAULT NULL COMMENT 'åˆ›å»ºæ—¶é—´',
  `owner_invite_code` varchar(32) CHARACTER SET latin1 DEFAULT NULL COMMENT 'é‚€è¯·ç ',
  `source_invite_code` varchar(32) CHARACTER SET latin1 DEFAULT NULL COMMENT 'æ¨èäººé‚€è¯·ç ',
  `total_amount` varchar(64) CHARACTER SET latin1 DEFAULT '0' COMMENT 'æ€»é¢',
  `free_amount` varchar(64) CHARACTER SET latin1 DEFAULT '0' COMMENT 'å¯ç”¨ä½™é¢',
  `lock_amount` varchar(64) CHARACTER SET latin1 DEFAULT '0' COMMENT 'å†»ç»“é¢åº¦',
  `profit_amount` varchar(64) CHARACTER SET latin1 DEFAULT '0' COMMENT 'æ€»ç›ˆåˆ©',
  `transit_amount` varchar(64) CHARACTER SET latin1 DEFAULT '0' COMMENT 'åœ¨é€”èµ„é‡‘',
  `level` smallint(4) DEFAULT '1' COMMENT 'ä¼šå‘˜ç­‰çº§',
  `commission_rate` int(11) DEFAULT NULL COMMENT 'ä½£é‡‘æ¯”ä¾‹',
  `bank_no` varchar(64) CHARACTER SET latin1 DEFAULT NULL COMMENT 'é“¶è¡Œå¡å·',
  `bank_name` varchar(64) DEFAULT NULL COMMENT 'é“¶è¡Œåç§°',
  `card_no` varchar(64) CHARACTER SET latin1 DEFAULT NULL COMMENT 'èº«ä»½è¯',
  `really_name` varchar(64) DEFAULT NULL COMMENT 'è¿™æ˜¯å§“å',
  `bank_branch_name` varchar(64) DEFAULT NULL COMMENT 'æ”¯è¡Œåç§°',
  `bank_address` varchar(255) DEFAULT NULL COMMENT 'é“¶è¡Œåœ°å€',
  `team_profit_amount` varchar(32) DEFAULT '0' COMMENT 'å›¢é˜Ÿæ”¶ç›Š',
  `day_profit_amount` varchar(32) DEFAULT '0' COMMENT 'å½“æ—¥æ”¶ç›Š',
  `wallet_address` varchar(255) DEFAULT NULL COMMENT 'é’±åŒ…åœ°å€',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=66052 DEFAULT CHARSET=utf8 COMMENT='ä¼šå‘˜è¡¨';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zwl_member`
--

LOCK TABLES `zwl_member` WRITE;
/*!40000 ALTER TABLE `zwl_member` DISABLE KEYS */;
INSERT INTO `zwl_member` VALUES (66032,NULL,'13162789927','123456','2020-03-08 22:13:04','92CDW8W',NULL,'31375000','31375000','0','21375000','0',1,NULL,'123748238748374','æ‹›å•†é“¶è¡Œ','411304190871635564','ç‹åˆš','å—äº¬æ”¯è¡Œ','å—äº¬å¸‚','0.00','0.00',NULL),(66051,NULL,'dingbs','123456','2020-11-24 16:13:47','kbogdl','92CDW8W','0','0','0','0','0',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0.00','0.00','1232323123234234234324');
/*!40000 ALTER TABLE `zwl_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zwl_member_bank`
--

DROP TABLE IF EXISTS `zwl_member_bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zwl_member_bank` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `back_name` varchar(64) DEFAULT NULL COMMENT 'é“¶è¡Œåç§°',
  `branch_name` varchar(64) DEFAULT NULL COMMENT 'æ”¯è¡Œåç§°',
  `card_no` varchar(64) DEFAULT NULL COMMENT 'é“¶è¡Œå¡å·',
  `create_time` datetime DEFAULT NULL COMMENT 'åˆ›å»ºæ—¶é—´',
  `status` smallint(4) DEFAULT NULL COMMENT 'çŠ¶æ€',
  `remark` varchar(255) DEFAULT NULL COMMENT 'å¤‡æ³¨',
  `mid` int(11) DEFAULT NULL COMMENT 'ä¼šå‘˜id',
  `username` varchar(64) DEFAULT NULL COMMENT 'åç§°',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zwl_member_bank`
--

LOCK TABLES `zwl_member_bank` WRITE;
/*!40000 ALTER TABLE `zwl_member_bank` DISABLE KEYS */;
INSERT INTO `zwl_member_bank` VALUES (2,'æ‹›å•†é“¶è¡Œ','é¦–ä½“æ”¯è¡Œ','622577013452564','2020-11-24 16:24:20',1,NULL,66051,'ç‹åˆš'),(3,'æ‹›å•†1','é¦–ä½“æ”¯è¡Œ','622577013452532','2020-11-24 20:05:56',0,NULL,66051,'ç‹åˆš');
/*!40000 ALTER TABLE `zwl_member_bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zwl_member_level`
--

DROP TABLE IF EXISTS `zwl_member_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zwl_member_level` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ä¸»é”®',
  `mid` int(11) DEFAULT NULL COMMENT 'ä¼šå‘˜id',
  `parent_mid` int(11) DEFAULT NULL COMMENT 'çˆ¶ä¼šå‘˜id',
  `grand_mid` int(11) DEFAULT NULL COMMENT 'ç¥–çˆ¶id',
  `great_mid` int(11) DEFAULT '0' COMMENT 'æ›¾ç¥–çˆ¶id',
  `status` varchar(32) DEFAULT 'disable' COMMENT 'æ˜¯å¦æœ‰æ•ˆæŠ•èµ„',
  `remark` varchar(255) DEFAULT NULL COMMENT 'å¤‡æ³¨ä¿¡æ¯',
  `type` varchar(32) DEFAULT 'A' COMMENT 'ä»£ç†çº§åˆ«',
  `level1` int(11) DEFAULT '0' COMMENT 'ç›´æ¥ä¼šå‘˜æ•°',
  `level2` int(11) DEFAULT '0' COMMENT 'é—´æ¥ä¼šå‘˜æ•°',
  `level3` int(11) DEFAULT '0' COMMENT 'ä¸‰çº§ä¼šå‘˜ä¸ªæ•°',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=66021 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zwl_member_level`
--

LOCK TABLES `zwl_member_level` WRITE;
/*!40000 ALTER TABLE `zwl_member_level` DISABLE KEYS */;
INSERT INTO `zwl_member_level` VALUES (66001,66032,NULL,NULL,NULL,'enable',NULL,'A',5,4,8),(66002,66033,66032,NULL,NULL,'enable',NULL,'A',2,4,0),(66003,66034,66032,NULL,NULL,'enable',NULL,'A',2,4,0),(66004,66035,66032,NULL,NULL,'enable',NULL,'A',0,0,0),(66005,66036,66032,NULL,NULL,'enable',NULL,'A',0,0,0),(66006,66037,66032,NULL,NULL,'enable',NULL,'A',0,0,0),(66007,66038,66033,66032,NULL,'enable',NULL,'A',2,0,0),(66008,66039,66033,66032,NULL,'enable',NULL,'A',2,0,0),(66009,66040,66034,66032,NULL,'enable',NULL,'A',2,0,0),(66010,66041,66034,66032,NULL,'enable',NULL,'A',2,0,0),(66011,66042,66038,66033,66032,'enable',NULL,'A',0,0,0),(66012,66043,66038,66033,66032,'enable',NULL,'A',0,0,0),(66013,66044,66039,66033,66032,'enable',NULL,'A',0,0,0),(66014,66045,66039,66033,66032,'enable',NULL,'A',0,0,0),(66015,66046,66040,66034,66032,'enable',NULL,'A',0,0,0),(66016,66047,66040,66034,66032,'enable',NULL,'A',0,0,0),(66017,66048,66041,66034,66032,'enable',NULL,'A',0,0,0),(66018,66049,66041,66034,66032,'enable',NULL,'A',0,0,0),(66019,66050,66033,66032,NULL,'disable',NULL,'A',0,0,0),(66020,66051,66032,NULL,NULL,'disable',NULL,'A',0,0,0);
/*!40000 ALTER TABLE `zwl_member_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zwl_pay_gateway`
--

DROP TABLE IF EXISTS `zwl_pay_gateway`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zwl_pay_gateway` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ä¸»é”®',
  `name` varchar(32) DEFAULT NULL COMMENT 'æ”¯ä»˜åç§°',
  `mch_id` varchar(64) DEFAULT NULL COMMENT 'å•†æˆ·åç§°',
  `key` varchar(64) DEFAULT NULL COMMENT 'ç§˜é’¥',
  `type` varchar(32) DEFAULT NULL COMMENT 'æ”¯ä»˜ç±»å‹',
  `logo` varchar(255) DEFAULT NULL COMMENT 'logo',
  `notify` varchar(255) DEFAULT NULL COMMENT 'å›è°ƒurl',
  `result` varchar(255) DEFAULT NULL COMMENT 'ç»“æœ',
  `status` varchar(32) DEFAULT NULL COMMENT 'çŠ¶æ€',
  `remark` varchar(255) DEFAULT NULL COMMENT 'å¤‡æ³¨',
  `description` varchar(255) DEFAULT NULL COMMENT 'è¯´æ˜',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zwl_pay_gateway`
--

LOCK TABLES `zwl_pay_gateway` WRITE;
/*!40000 ALTER TABLE `zwl_pay_gateway` DISABLE KEYS */;
/*!40000 ALTER TABLE `zwl_pay_gateway` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zwl_trade_log`
--

DROP TABLE IF EXISTS `zwl_trade_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zwl_trade_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ä¸»é”®',
  `trade_no` varchar(32) DEFAULT NULL COMMENT 'æµæ°´å•å·',
  `amount` varchar(32) DEFAULT '0' COMMENT 'äº¤æ˜“é¢åº¦',
  `m_free_total` varchar(64) DEFAULT '0' COMMENT 'å¯ç”¨ä½™é¢',
  `fact_amount` varchar(32) DEFAULT '0' COMMENT 'çœŸå®åˆ°è´¦',
  `type` varchar(32) DEFAULT NULL COMMENT 'äº¤æ˜“ç±»å‹',
  `create_time` datetime DEFAULT NULL COMMENT 'äº¤æ˜“æ—¶é—´',
  `finished_time` datetime DEFAULT NULL COMMENT 'å®Œæˆæ—¶é—´',
  `trade_out_no` varchar(32) DEFAULT NULL COMMENT 'å¤–éƒ¨çš„äº¤æ˜“å•å·',
  `remark` varchar(255) DEFAULT NULL COMMENT 'å¤‡æ³¨',
  `mid` int(11) DEFAULT NULL COMMENT 'ä¼šå‘˜id',
  `status` varchar(32) DEFAULT NULL COMMENT 'çŠ¶æ€',
  `mobile` varchar(32) DEFAULT NULL COMMENT 'å½“å‰ç”¨æˆ·æ‰‹æœºå·',
  `agree_id` int(11) DEFAULT NULL COMMENT 'åˆåŒid',
  `task_id` int(11) DEFAULT NULL COMMENT 'ä»»åŠ¡çš„id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zwl_trade_log`
--

LOCK TABLES `zwl_trade_log` WRITE;
/*!40000 ALTER TABLE `zwl_trade_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `zwl_trade_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zwl_ustd_log`
--

DROP TABLE IF EXISTS `zwl_ustd_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zwl_ustd_log` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `mid` bigint(11) DEFAULT NULL COMMENT 'ä¼šå‘˜id',
  `no` varchar(32) DEFAULT NULL COMMENT 'è®¢å•å·',
  `amount` varchar(255) DEFAULT NULL COMMENT 'uå¸ä¸ªæ•°',
  `status` int(11) DEFAULT NULL COMMENT 'çŠ¶æ€',
  `remark` varchar(255) DEFAULT NULL COMMENT 'å¤‡æ³¨',
  `source` varchar(255) DEFAULT NULL COMMENT 'æ¥æºåœ°å€',
  `target` varchar(255) DEFAULT NULL COMMENT 'ç›®æ ‡åœ°å€',
  `create_time` datetime DEFAULT NULL COMMENT 'åˆ›å»ºæ—¶é—´',
  `finished_time` datetime DEFAULT NULL COMMENT 'å®Œæˆæ—¶é—´',
  `cny_price` varchar(32) DEFAULT NULL COMMENT 'æŒ‡å¯¼ä»·æ ¼',
  `total_amount` varchar(255) DEFAULT NULL COMMENT 'æ€»uå¸æ•°',
  `profile_amount` varchar(255) DEFAULT NULL COMMENT 'å¥–åŠ±é‡‘é¢',
  `back_total_amount` varchar(255) DEFAULT NULL COMMENT 'è¿”ç°çš„æ€»åˆ©æ¶¦',
  `operator` varchar(32) DEFAULT NULL COMMENT 'æ“ä½œè€…',
  `username` varchar(255) DEFAULT NULL COMMENT 'ä¼šå‘˜åç§°',
  `bank_name` varchar(255) DEFAULT NULL,
  `bank_branch` varchar(255) DEFAULT NULL,
  `bank_no` varchar(64) DEFAULT NULL,
  `really_name` varchar(64) DEFAULT NULL COMMENT 'æ”¶æ¬¾äºº',
  `current_date` date DEFAULT NULL COMMENT 'å½“å¤©æ—¥æœŸ',
  PRIMARY KEY (`id`),
  KEY `idx_current_date` (`current_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zwl_ustd_log`
--

LOCK TABLES `zwl_ustd_log` WRITE;
/*!40000 ALTER TABLE `zwl_ustd_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `zwl_ustd_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zwl_withdraw`
--

DROP TABLE IF EXISTS `zwl_withdraw`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zwl_withdraw` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ä¸»é”®id',
  `mid` int(11) DEFAULT NULL COMMENT 'ä¼šå‘˜id',
  `amount` varchar(64) CHARACTER SET utf8mb4 DEFAULT '0' COMMENT 'æç°é¢åº¦',
  `bank_no` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT 'é“¶è¡Œå¡å·',
  `bank_name` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT 'æ”¶æ¬¾äººå§“å',
  `third_account` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT 'ç¬¬ä¸‰æ–¹è´¦å·',
  `third_type` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT 'å¾®ä¿¡æˆ–æ”¯ä»˜å®',
  `create_time` datetime DEFAULT NULL COMMENT 'æç°æ—¶é—´',
  `finished_time` datetime DEFAULT NULL COMMENT 'å®Œæˆæ—¶é—´',
  `remark` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT 'å¤‡æ³¨',
  `trade_id` int(11) DEFAULT NULL COMMENT 'äº¤æ˜“id',
  `status` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT 'çŠ¶æ€',
  `fact_amount` varchar(32) CHARACTER SET utf8mb4 DEFAULT '0' COMMENT 'å®é™…é‡‘é¢',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zwl_withdraw`
--

LOCK TABLES `zwl_withdraw` WRITE;
/*!40000 ALTER TABLE `zwl_withdraw` DISABLE KEYS */;
/*!40000 ALTER TABLE `zwl_withdraw` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-08 18:31:42
