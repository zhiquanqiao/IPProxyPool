-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: proxy
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `proxy_ip`
--

DROP TABLE IF EXISTS `proxy_ip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `proxy_ip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(16) NOT NULL,
  `port` varchar(6) NOT NULL,
  `types` varchar(20) NOT NULL,
  `protocol` varchar(20) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `area` varchar(100) DEFAULT NULL,
  `updatetime` datetime NOT NULL,
  `speed` decimal(5,2) DEFAULT NULL,
  `score` tinyint(4) DEFAULT '20',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proxy_ip`
--

LOCK TABLES `proxy_ip` WRITE;
/*!40000 ALTER TABLE `proxy_ip` DISABLE KEYS */;
INSERT INTO `proxy_ip` VALUES (1,'119.101.113.96','9999','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,8),(2,'119.101.117.119','9999','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,3),(3,'111.177.173.76','9999','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,4),(4,'111.177.187.122','9999','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,4),(5,'223.215.186.142','9999','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,4),(6,'180.103.251.130','9999','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,4),(7,'221.1.84.171','9000','匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,4),(8,'113.105.203.150','3128','匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,4),(9,'144.255.12.196','9999','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,4),(10,'112.85.169.99','9999','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,4),(11,'111.177.172.54','9999','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,4),(12,'119.101.112.189','9999','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,4),(13,'111.177.185.33','9999','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,4),(14,'223.215.186.152','9999','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,4),(15,'222.18.165.69','8888','匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,8),(16,'111.177.170.113','9999','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,7),(17,'122.7.129.13','9999','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,4),(18,'111.177.188.58','9999','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,4),(19,'49.86.182.15','9999','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,4),(20,'117.87.178.183','9000','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,7),(21,'58.49.134.202','41521','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,7),(22,'58.252.2.10','8888','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,7),(23,'117.70.38.134','9999','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,7),(24,'113.128.9.56','9999','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,7),(25,'47.107.129.228','8118','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,7),(26,'117.90.1.163','9999','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,7),(27,'111.177.183.234','9999','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,7),(28,'123.207.43.128','1080','匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,8),(29,'119.29.177.120','1080','匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,9),(30,'111.177.191.178','9999','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,7),(31,'61.189.242.243','55484','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,9),(32,'218.64.152.24','9000','匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,7),(33,'182.44.220.178','9999','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,7),(34,'60.189.153.124','9000','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,7),(35,'121.225.65.112','9999','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',NULL,10),(36,'122.243.12.242','9000','高匿名','http',NULL,NULL,'2019-01-04 03:03:11',0.00,7),(37,'27.24.197.129','9999','高匿名','http',NULL,NULL,'2019-01-04 03:03:14',0.00,7),(38,'219.159.38.208','56210','高匿名','http',NULL,NULL,'2019-01-04 03:03:14',0.00,7),(39,'163.125.28.61','8118','高匿名','http',NULL,NULL,'2019-01-04 03:03:14',0.00,7),(40,'61.164.39.69','53281','高匿名','http',NULL,NULL,'2019-01-04 03:03:14',0.00,7),(41,'58.56.149.198','53281','高匿名','http',NULL,NULL,'2019-01-04 03:03:14',0.00,7),(42,'111.177.175.114','9999','高匿名','http',NULL,NULL,'2019-01-04 03:03:14',0.00,7),(43,'111.177.168.140','9999','高匿名','http',NULL,NULL,'2019-01-04 03:03:14',0.00,7),(44,'111.177.178.192','9999','高匿名','http',NULL,NULL,'2019-01-04 03:03:14',0.00,7),(45,'117.28.97.157','808','高匿名','http',NULL,NULL,'2019-01-04 03:03:14',0.00,7),(46,'117.90.137.110','9000','高匿名','http',NULL,NULL,'2019-01-04 03:03:14',0.00,7),(47,'125.40.238.181','56738','高匿名','http',NULL,NULL,'2019-01-04 03:03:14',0.00,7),(48,'111.177.177.216','9999','高匿名','http',NULL,NULL,'2019-01-04 03:03:14',0.00,7),(49,'58.210.94.242','50514','高匿名','http',NULL,NULL,'2019-01-04 03:03:14',0.00,7),(50,'117.70.38.248','9999','高匿名','http',NULL,NULL,'2019-01-04 03:03:14',0.00,7);
/*!40000 ALTER TABLE `proxy_ip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proxy_ip_net_ref`
--

DROP TABLE IF EXISTS `proxy_ip_net_ref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `proxy_ip_net_ref` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip_id` int(11) NOT NULL,
  `net_id` int(11) NOT NULL,
  `score` tinyint(4) DEFAULT NULL,
  `speed` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proxy_ip_net_ref`
--

LOCK TABLES `proxy_ip_net_ref` WRITE;
/*!40000 ALTER TABLE `proxy_ip_net_ref` DISABLE KEYS */;
INSERT INTO `proxy_ip_net_ref` VALUES (1,1,1,4,0.00),(2,1,2,4,NULL),(3,1,7,5,NULL),(4,2,1,2,0.00),(5,2,2,3,NULL),(6,2,7,3,NULL),(7,3,1,3,NULL),(8,3,2,3,NULL),(9,3,7,3,NULL),(10,4,1,3,NULL),(11,4,2,3,NULL),(12,4,7,3,NULL),(13,5,1,3,NULL),(14,5,2,3,NULL),(15,5,7,3,NULL),(16,6,1,3,NULL),(17,6,2,3,NULL),(18,6,7,3,NULL),(19,7,1,3,NULL),(20,7,2,3,NULL),(21,7,7,3,NULL),(22,8,1,3,NULL),(23,8,2,3,NULL),(24,8,7,3,NULL),(25,9,1,3,NULL),(26,9,2,3,NULL),(27,9,7,3,NULL),(28,10,1,3,NULL),(29,10,2,3,NULL),(30,10,7,3,NULL),(31,11,1,3,NULL),(32,11,2,3,NULL),(33,11,7,3,NULL),(34,12,1,3,NULL),(35,12,2,3,NULL),(36,12,7,3,NULL),(37,13,1,3,NULL),(38,13,2,3,NULL),(39,13,7,3,NULL),(40,14,1,3,NULL),(41,14,2,3,NULL),(42,14,7,3,NULL),(43,15,1,3,NULL),(44,15,2,5,NULL),(45,15,7,5,NULL),(46,16,1,4,NULL),(47,16,2,4,NULL),(48,16,7,4,NULL),(49,17,1,3,NULL),(50,17,2,3,NULL),(51,17,7,3,NULL),(52,18,1,3,NULL),(53,18,2,3,NULL),(54,18,7,3,NULL),(55,19,1,3,NULL),(56,19,2,3,NULL),(57,19,7,3,NULL),(58,20,1,4,NULL),(59,20,2,4,NULL),(60,20,7,4,NULL),(61,21,1,4,NULL),(62,21,2,4,NULL),(63,21,7,4,NULL),(64,22,1,4,NULL),(65,22,2,4,NULL),(66,22,7,4,NULL),(67,23,1,4,NULL),(68,23,2,4,NULL),(69,23,7,4,NULL),(70,24,1,4,NULL),(71,24,2,4,NULL),(72,24,7,4,NULL),(73,25,1,4,NULL),(74,25,2,4,NULL),(75,25,7,4,NULL),(76,26,1,4,NULL),(77,26,2,4,NULL),(78,26,7,4,NULL),(79,27,1,4,NULL),(80,27,2,4,NULL),(81,27,7,4,NULL),(82,28,1,4,NULL),(83,28,2,4,NULL),(84,28,7,5,NULL),(85,29,1,5,NULL),(86,29,2,4,NULL),(87,29,7,5,NULL),(88,30,1,4,NULL),(89,30,2,4,NULL),(90,30,7,4,NULL),(91,31,1,4,NULL),(92,31,2,5,NULL),(93,31,7,5,NULL),(94,32,1,4,NULL),(95,32,2,4,NULL),(96,32,7,4,NULL),(97,33,1,4,NULL),(98,33,2,4,NULL),(99,33,7,4,NULL),(100,34,1,4,NULL),(101,34,2,4,NULL),(102,34,7,4,NULL),(103,35,1,5,NULL),(104,35,2,5,NULL),(105,35,7,5,NULL),(106,36,1,4,NULL),(107,36,2,4,NULL),(108,36,7,4,NULL),(109,37,1,4,NULL),(110,37,2,4,NULL),(111,37,7,4,NULL),(112,38,1,4,NULL),(113,38,2,4,NULL),(114,38,7,4,NULL),(115,39,1,4,NULL),(116,39,2,4,NULL),(117,39,7,4,NULL),(118,40,1,4,NULL),(119,40,2,4,NULL),(120,40,7,4,NULL),(121,41,1,4,NULL),(122,41,2,4,NULL),(123,41,7,4,NULL),(124,42,1,4,NULL),(125,42,2,4,NULL),(126,42,7,4,NULL),(127,43,1,4,NULL),(128,43,2,4,NULL),(129,43,7,4,NULL),(130,44,1,4,NULL),(131,44,2,4,NULL),(132,44,7,4,NULL),(133,45,1,4,NULL),(134,45,2,4,NULL),(135,45,7,4,NULL),(136,46,1,4,NULL),(137,46,2,4,NULL),(138,46,7,4,NULL),(139,47,1,4,NULL),(140,47,2,4,NULL),(141,47,7,4,NULL),(142,48,1,4,NULL),(143,48,2,4,NULL),(144,48,7,4,NULL),(145,49,1,4,NULL),(146,49,2,4,NULL),(147,49,7,4,NULL),(148,50,1,4,NULL),(149,50,2,4,NULL),(150,50,7,4,NULL);
/*!40000 ALTER TABLE `proxy_ip_net_ref` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proxy_net`
--

DROP TABLE IF EXISTS `proxy_net`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `proxy_net` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proxy_net`
--

LOCK TABLES `proxy_net` WRITE;
/*!40000 ALTER TABLE `proxy_net` DISABLE KEYS */;
INSERT INTO `proxy_net` VALUES (1,'西刺代理','http://www.xicidaili.com'),(2,'快代理','http://www.kuaidaili.com'),(7,'福建雨情','http://58.22.3.131:9003/rain?no_data_visible=false&hour_duration=24&time=%5B2019-01-03T11%3A00%3A00%2C2019-01-03T11%3A30%3A00%5D');
/*!40000 ALTER TABLE `proxy_net` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proxys`
--

DROP TABLE IF EXISTS `proxys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `proxys` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `urls` varchar(255) NOT NULL,
  `type` varchar(32) NOT NULL,
  `pattern` varchar(255) NOT NULL,
  `position` varchar(155) NOT NULL,
  `params` varchar(255) DEFAULT NULL,
  `test_url` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proxys`
--

LOCK TABLES `proxys` WRITE;
/*!40000 ALTER TABLE `proxys` DISABLE KEYS */;
INSERT INTO `proxys` VALUES (1,'www.kuaidaili.com','http://www.kuaidaili.com/proxylist/%s/','xpath','//*[@id=\"freelist\"]/table//tr[position()>1]','{\"ip\": \"./td[1]\", \"port\": \"./td[2]\", \"type\": \"./td[3]\", \"protocol\": \"./td[4]\"}','[1,4]',NULL),(2,'www.kuaidaili.com','http://www.kuaidaili.com/free/%s/%s/','xpath','//*[@id=\"list\"]/table/tbody/tr[position()>1]','{\"ip\": \"./td[1]\", \"port\": \"./td[2]\", \"type\": \"./td[3]\", \"protocol\": \"./td[4]\"}','[inha,intr],[1,4]',NULL),(5,'www.xicidaili.com','http://www.xicidaili.com/%s/%s','xpath','//*[@id=\"ip_list\"]/tbody/tr[position() >1]','{\"ip\": \"./td[2]\", \"port\": \"./td[3]\", \"type\": \"./td[5]\", \"protocol\": \"./td[6]\"}','[nn,nt,wn,wt],[1,2]',NULL);
/*!40000 ALTER TABLE `proxys` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-04 11:58:38
