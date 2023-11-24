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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `uuid` char(36) NOT NULL,
  `id` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `profile_img` longblob,
  `user_type` int NOT NULL,
  `join_date` date DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `nickname` (`nickname`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('4ac3efb8-8a6e-11ee-abd8-0242ac110002','ssafy7','$2a$10$56DFjtIHDQtpjh0eXOyV8.9qdcnfH.SG4WMMxwonWJNUCCZIiwA8C','싸피7','ssafy7@ssafy.com',NULL,1,'2023-11-24'),('b080460d-8a6e-11ee-abd8-0242ac110002','ssafy8','$2a$10$r7xdoDxF3ETjYAwXW12bqe8A4aChsYl2gdhQWJRtexLEavoZaYC9C','싸피8','ssafy8@ssafy.com',NULL,1,'2023-11-24'),('b3ef3fe1-8995-11ee-a6c2-0242ac110002','ssafy1','$2a$10$k.6d4dls5xcFOgHTW0CvCOPlxFBq3dys18cg4sN3YE3Ip5NzINdwK','싸피1','ssafy1@ssafy.com',NULL,0,'2023-11-23'),('bbe78071-8995-11ee-a6c2-0242ac110002','ssafy2','$2a$10$iPv45DfmC/mkYKvmPdu0UOUVawoK1RYe3N7JPz7DJxcjuu.i0J6ke','싸피2','ssafy2@ssafy.com',NULL,1,'2023-11-23'),('c4da3951-8995-11ee-a6c2-0242ac110002','ssafy3','$2a$10$XI2WiKQRk/SPSRJrkvC0E.nLdQmj961fbtJUK2Dr95aYFSclY.mfO','싸피3','ssafy3@ssafy.com',NULL,1,'2023-11-23'),('ce393df8-8995-11ee-a6c2-0242ac110002','ssafy4','$2a$10$bXyFnMZjlwKJ/Li8MproIu.8.vcHKBCe0KJKdknhdPI1jdBxcxUda','싸피4','ssafy4@ssafy.com',NULL,1,'2023-11-23'),('dcf43ebb-8995-11ee-a6c2-0242ac110002','ssafy5','$2a$10$I8Uw8pfC9gRCGuqvAJA.auysBtg5JmjI45b0PmQThjFVoAIKsXvjK','싸피5','ssafy5@ssafy.com',NULL,1,'2023-11-23'),('e60afe20-8995-11ee-a6c2-0242ac110002','ssafy6','$2a$10$C5q2sYu4t2VjnIedvubH5uT/1LdgWvwR4OYGfsF.0bfvaqz4hKZ/.','싸피6','ssafy6@ssafy.com',NULL,1,'2023-11-23'),('e8faddca-8a6e-11ee-abd8-0242ac110002','ssafy9','$2a$10$K6cnJmhWeAKlAschFRfRSebjMR6y1gzM4Y8Vgk06Rp6TbrPa6/i5y','싸피9','ssafy9@ssafy.com',NULL,1,'2023-11-24');
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

-- Dump completed on 2023-11-24 11:53:21
