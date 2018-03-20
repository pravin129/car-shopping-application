-- MySQL dump 10.13  Distrib 5.7.18, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `car_product_details`
--

DROP TABLE IF EXISTS `car_product_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `car_product_details` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `product_price` float DEFAULT NULL,
  `product_status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `product_specifications` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_product_details`
--

LOCK TABLES `car_product_details` WRITE;
/*!40000 ALTER TABLE `car_product_details` DISABLE KEYS */;
INSERT INTO `car_product_details` VALUES (1,'indica_vista-1',400000,'Active',NULL),(2,'vista_basic1',450000,'Active',NULL),(3,'getimage',400000,'Active',NULL),(4,'vista_basic',450000,'Active',NULL),(5,'vista_1topmodel',500000,'Active',NULL),(6,'vista_2topmodel',550000,'Active',NULL),(7,'vista_3topmodel',600000,'Active',NULL),(8,'vista_4topmodel',650000,'Active',NULL),(9,'TataHexa_basic1',450000,'Active',NULL),(10,'TataHexa_basic2',450000,'Active',NULL),(11,'TataHexa_basic3',450000,'Active',NULL),(12,'TataHexa_basic4',450000,'Active',NULL),(13,'TataHexa_top1',500000,'Active',NULL),(14,'TataHexa_top2',600000,'Active',NULL),(15,'TataHexa_top3',650000,'Active',NULL),(16,'TataHexa_top4',650000,'Active',NULL),(17,'nano_basic1',100000,'Active',NULL),(18,'nano_basic2',100000,'Active',NULL),(19,'nano_basic3',100000,'Active',NULL),(20,'nano_basic4',100000,'Active',NULL),(21,'nano_top1',175000,'Active',NULL),(22,'nano_top2',175000,'Active',NULL),(23,'nano_top3',175000,'Active',NULL),(24,'nano_top4',175000,'Active',NULL),(25,'tiago_basic1',350000,'Active',NULL),(26,'tiago_basic2',350000,'Active',NULL),(27,'tiago_basic3',350000,'Active',NULL),(28,'tiago_basic4',350000,'Active',NULL),(29,'tiago_top1',400000,'Active',NULL),(30,'tiago_top2',400000,'Active',NULL),(31,'tiago_top3',400000,'Active',NULL),(32,'tiago_top4',400000,'Active',NULL),(33,'mahindraTUV_basic1',600000,'Active',NULL),(34,'mahindraTUV_basic2',600000,'Active',NULL),(35,'mahindraTUV_basic3',600000,'Active',NULL),(36,'mahindraTUV_basic4',600000,'Active',NULL),(37,'mahindraThar_basic1',700000,'Active',NULL),(38,'mahindraThar_basic2',700000,'Active',NULL),(39,'mahindraThar_basic3',700000,'Active',NULL),(40,'mahindraThar_basic4',700000,'Active',NULL),(41,'mahindrascorpio_basic1',800000,'Active',NULL),(42,'mahindrascorpio_basic2',800000,'Active',NULL),(43,'mahindrascorpio_basic3',800000,'Active',NULL),(44,'mahindrascorpio_basic4',800000,'Active',NULL),(45,'mahindraxylo_basic1',750000,'Active',NULL),(46,'mahindraxylo_basic2',750000,'Active',NULL),(47,'mahindraxylo_basic3',750000,'Active',NULL),(48,'mahindraxylo_basic4',750000,'Active',NULL),(49,'MarutiSuzuki_swift_basic1',600000,'Active',NULL),(50,'MarutiSuzuki_swift_basic2',600000,'Active',NULL),(51,'MarutiSuzuki_swift_basic3',600000,'Active',NULL),(52,'MarutiSuzuki_swift_basic4',600000,'Active',NULL),(53,'MarutiSuzuki_breeza_basic1',900000,'Active',NULL),(54,'MarutiSuzuki_breeza_basic2',900000,'Active',NULL),(55,'MarutiSuzuki_breeza_basic3',900000,'Active',NULL),(56,'MarutiSuzuki_breeza_basic4',900000,'Active',NULL),(57,'MarutiSuzuki_dzire_basic1',600000,'Active',NULL),(58,'MarutiSuzuki_dzire_basic2',600000,'Active',NULL),(59,'MarutiSuzuki_dzire_basic3',600000,'Active',NULL),(60,'MarutiSuzuki_dzire_basic4',600000,'Active',NULL),(61,'MarutiSuzuki_alto_basic1',300000,'Active',NULL),(62,'MarutiSuzuki_alto_basic2',300000,'Active',NULL),(63,'MarutiSuzuki_alto_basic3',300000,'Active',NULL),(64,'MarutiSuzuki_alto_basic4',300000,'Active',NULL),(65,'volvo_xc90_basic1',1200000,'Active',NULL),(66,'volvo_xc90_basic2',1200000,'Active',NULL),(67,'volvo_xc90_basic3',1200000,'Active',NULL),(68,'volvo_xc90_basic4',1200000,'Active',NULL),(69,'volvo_xc60_basic1',1500000,'Active',NULL),(70,'volvo_xc60_basic2',1500000,'Active',NULL),(71,'volvo_xc60_basic3',1500000,'Active',NULL),(72,'volvo_xc60_basic4',1500000,'Active',NULL),(73,'volvo_v90_basic1',1700000,'Active',NULL),(74,'volvo_v90_basic2',1700000,'Active',NULL),(75,'volvo_v90_basic3',1700000,'Active',NULL),(76,'volvo_v90_basic4',1700000,'Active',NULL),(77,'volvo_s60_basic1',2000000,'Active',NULL),(78,'volvo_s60_basic2',2000000,'Active',NULL),(79,'volvo_s60_basic3',2000000,'Active',NULL),(80,'volvo_s60_basic4',2000000,'Active',NULL);
/*!40000 ALTER TABLE `car_product_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-08 16:14:39
