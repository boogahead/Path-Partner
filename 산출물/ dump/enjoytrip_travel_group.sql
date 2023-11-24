-- MySQL dump 10.13  Distrib 8.0.33, for macos13 (arm64)
--
-- Host: 127.0.0.1    Database: enjoytrip
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `travel_group`
--

DROP TABLE IF EXISTS `travel_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `travel_group` (
  `group_id` char(36) NOT NULL,
  `group_name` varchar(255) NOT NULL,
  `creation_date` date DEFAULT NULL,
  `uuid` char(36) NOT NULL,
  `group_master` tinyint(1) DEFAULT NULL,
  `accepted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`group_id`,`uuid`),
  KEY `uuid` (`uuid`),
  CONSTRAINT `travel_group_ibfk_1` FOREIGN KEY (`uuid`) REFERENCES `user` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `travel_group`
--

LOCK TABLES `travel_group` WRITE;
/*!40000 ALTER TABLE `travel_group` DISABLE KEYS */;
INSERT INTO `travel_group` VALUES ('8d23becb-8a6f-11ee-abd8-0242ac110002','싸피그룹1','2023-11-24','b3ef3fe1-8995-11ee-a6c2-0242ac110002',1,1),('8d23becb-8a6f-11ee-abd8-0242ac110002','싸피그룹1','2023-11-24','c4da3951-8995-11ee-a6c2-0242ac110002',0,1),('9eee11e0-8a6f-11ee-abd8-0242ac110002','싸피그룹5','2023-11-24','dcf43ebb-8995-11ee-a6c2-0242ac110002',1,1),('c59eea9f-8a6f-11ee-abd8-0242ac110002','싸피그룹3','2023-11-24','c4da3951-8995-11ee-a6c2-0242ac110002',1,1),('c59eea9f-8a6f-11ee-abd8-0242ac110002','싸피그룹3','2023-11-24','ce393df8-8995-11ee-a6c2-0242ac110002',0,1);
/*!40000 ALTER TABLE `travel_group` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-24 11:53:21
