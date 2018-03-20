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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `USER_ID` int(10) unsigned NOT NULL,
  `USERNAME` varchar(40) NOT NULL,
  `PASSWORD` varchar(150) NOT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'vk','$2a$10$4Qon2P4cWrVxpEIvvg2tVu6Evcq4pgokecRhluqWgX7rpLP9TFosi',1,NULL,'vk@gmail.com'),(2,'pravin','$2a$10$Yp4XB.Hj3b1WBZjp7A9.euKfbcCjQlGEuZJz3Z9/UH8AB/lypaF3S',1,NULL,'pravinghadge065@gmail.com'),(3,'rj','$2a$10$u8b922GOWJfbU8R4SH4nieFByrv3Lk7ZhRKbxay.6g4E9M1XfbH42',1,NULL,'rj@gmail.com'),(4,'pk','$2a$10$MRXj.0DT0XU7iw3mzQOksuwINrBcgdMxh1uPSlBF1RvtcSozaOh8y',1,NULL,'pk@gmail.com'),(5,'a','$2a$10$OWBLs3H4MOmCsTwOSyVKy.MGXMTaKAHN1RYa/lmVFQqsaYZdocmtm',1,NULL,'a@gmail.com'),(6,'b','$2a$10$TAKegoAEMNYi8VGJm0vnx.uViSlnZIUmWNPZYe.7y770CLjYF7Xye',1,NULL,'b@gmail.com'),(7,'ak','$2a$10$tdCNiykl78SJleaNH29kJOpx9XdtkduhNSsV52UdoAcnu4VDbWFGm',1,NULL,'ak@gmail.com'),(8,'nikita','$2a$10$C0dtUEeMF3pQyCPre5D0MOs/6QEfCML2hsQwMlQ4Ey851YppbXsXS',1,NULL,'nikita@gmail.com'),(9,'sagar','$2a$10$EfswZNw7j64J8nwSa/KOy.sokH.lWNQX942n6.yL2kAa2X0yyNG1i',1,NULL,NULL),(10,'salman','$2a$10$8KWFtkU42Ar0JtzfWY623eXeAhl1uVL1zkfMm8sZQkpZ/P9X3LyyS',1,'ROLE_USER','salman123@gmail.com'),(11,'a1','$2a$10$QnKUev43MHu3rB1B6swR9OPT4ijQDWSnOvt6TKjPQpu5N2ppI2jQq',1,'ROLE_USER','a1@gmail.com');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-08 16:13:33
