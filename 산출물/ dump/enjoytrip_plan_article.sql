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
-- Table structure for table `plan_article`
--

DROP TABLE IF EXISTS `plan_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plan_article` (
  `plan_article_id` char(36) NOT NULL,
  `plan` mediumtext,
  `creation_date` date DEFAULT NULL,
  `img_src` longtext,
  `plan_title` varchar(255) DEFAULT NULL,
  `uuid` char(36) DEFAULT NULL,
  `group_id` char(36) DEFAULT NULL,
  PRIMARY KEY (`plan_article_id`),
  KEY `plan_article_ibfk_2` (`uuid`),
  KEY `plan_article_ibfk_1` (`group_id`),
  CONSTRAINT `plan_article_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `travel_group` (`group_id`) ON DELETE CASCADE,
  CONSTRAINT `plan_article_ibfk_2` FOREIGN KEY (`uuid`) REFERENCES `user` (`uuid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan_article`
--

LOCK TABLES `plan_article` WRITE;
/*!40000 ALTER TABLE `plan_article` DISABLE KEYS */;
INSERT INTO `plan_article` VALUES ('09b99361-8a70-11ee-abd8-0242ac110002','[{\"contentId\":1603100,\"contentTypeId\":12,\"title\":\"불국사\",\"addr1\":\"서울특별시 강남구 광평로10길 30-71\",\"addr2\":\"(일원동)\",\"zipcode\":\"6359\",\"tel\":\"\",\"firstImage\":\"http://tong.visitkorea.or.kr/cms/resource/58/1567758_image2_1.jpg\",\"firstImage2\":\"http://tong.visitkorea.or.kr/cms/resource/58/1567758_image3_1.jpg\",\"readcount\":21307,\"sidoCode\":1,\"gugunCode\":1,\"latitude\":37.47759964,\"longitude\":127.0750814,\"mlevel\":\"6\",\"likes\":0,\"overview\":\"고려 공민왕 2년(1385년)에 진정국사가 약사절 창건하였다. 조선말 고종황제께서 불국정토를 이루자는 뜻에서 불국사란 사명을 내렸고,한국전쟁으로 약사 부처님만 남고 사찰 소실되어 1964년 풍곡화상이 중창하였다.\"}]','2023-11-24','[{\"src\":\"http://tong.visitkorea.or.kr/cms/resource/58/1567758_image2_1.jpg\",\"alt\":\"불국사\",\"contentId\":1603100}]','싸피 그룹 3 여행 계획~','c4da3951-8995-11ee-a6c2-0242ac110002','c59eea9f-8a6f-11ee-abd8-0242ac110002'),('2298304d-8a70-11ee-abd8-0242ac110002','[{\"contentId\":125565,\"contentTypeId\":12,\"title\":\"강화 고인돌 유적 [유네스코 세계문화유산]\",\"addr1\":\"인천광역시 강화군 하점면 부근리\",\"addr2\":\"\",\"zipcode\":\"23014\",\"tel\":\"\",\"firstImage\":\"http://tong.visitkorea.or.kr/cms/resource/77/1876577_image2_1.jpg\",\"firstImage2\":\"http://tong.visitkorea.or.kr/cms/resource/77/1876577_image3_1.jpg\",\"readcount\":74382,\"sidoCode\":2,\"gugunCode\":1,\"latitude\":37.77343442,\"longitude\":126.437473,\"mlevel\":\"6\",\"likes\":0,\"overview\":\"청동기시대의 대표적인 묘제의 하나로서 사적으로 지정되었다. 길이 710cm, 높이 260cm, 넓이 550cm의 커다란 돌을 사용했으며, 형태는 북방식 고인돌로서 상고사와 고대사의 좋은 연구 자료가 되고 있다. 2000년 11월 29일 호주 케인즈 제 24차 유네스코 세계문화유산위원회에서 고창, 화순의 고인돌과 함께 세계문화유산으로 등록되었다.\"},{\"contentId\":129810,\"contentTypeId\":14,\"title\":\"강화역사박물관\",\"addr1\":\"인천광역시 강화군 하점면 강화대로 994-19\",\"addr2\":\"\",\"zipcode\":\"23014\",\"tel\":\"\",\"firstImage\":\"http://tong.visitkorea.or.kr/cms/resource/81/1876581_image2_1.jpg\",\"firstImage2\":\"http://tong.visitkorea.or.kr/cms/resource/81/1876581_image3_1.jpg\",\"readcount\":52633,\"sidoCode\":2,\"gugunCode\":1,\"latitude\":37.7737399,\"longitude\":126.4352815,\"mlevel\":\"6\",\"likes\":0,\"overview\":\"강화역사박물관은 강화의 문화유산을 보존·연구하여 전시할 목적으로 세워진 공립박물관이다. 갑곶돈대 옆에 위치해 있다가 이전하여 2010년 개관하였다. 상설전시실에는 강화의 선사시대 유적지와 고려왕릉에서 출토 된 유물과 향교, 전통사찰 소장품 등의 문화재가 전시되어 있으며, 기획전시실에서는 해마다 다채로운 주제의 특별전이 열리고 있다. 그리고 강화 역사의 이해를 돕기 위해 영상실과 교육프로그램을 함께 운영하고 있어 복합문화공간으로서 역할을 하고 있다.\"},{\"contentId\":128823,\"contentTypeId\":12,\"title\":\"강화 장정리 석조여래입상\",\"addr1\":\"인천광역시 강화군 하점면 장정리\",\"addr2\":\"\",\"zipcode\":\"23014\",\"tel\":\"\",\"firstImage\":\"http://tong.visitkorea.or.kr/cms/resource/03/183103_image2_1.jpg\",\"firstImage2\":\"http://tong.visitkorea.or.kr/cms/resource/03/183103_image3_1.jpg\",\"readcount\":24819,\"sidoCode\":2,\"gugunCode\":1,\"latitude\":37.78088612,\"longitude\":126.42561,\"mlevel\":\"6\",\"likes\":0,\"overview\":\"하점면 장정리에 소재한 이 불상은 두꺼운 판석 위에 조각된 고려시대의 석조여래입상이다. 전체적으로 장대하고 불신의 비례가 좋은 편이나 큰 얼굴, 좁은 어깨, 큰 손 등이 다소 균형감을 깨고 있다. 지금은 전각을 세우고 그안에 모시고 있다. 높이 2.8m의 마애불로 머리 위에 큼직한 육계가 솟아 있고 얼굴은 둥근편으로 입가부터 양쪽 볼과 눈매에 이르기까지 미소를 가득 머금고 있다. 전체적으로 입상의 하반부는 간략하게 조각되어 있다. 얼굴표현, 법의 층단식처리, 광배, 화염문 등의 표현방법으로 보아 제작시기를 11세기 경으로 추정하고 있다. 전설에 의하면 고려 예종1년(1106) 3월 7일(음)에 한 노파가 연못가에서 빨래를 하고 있을때 갑자기 오색 무지개가 찬란하게 비추더니 연못에서 옥함이 떠올라 열어 보니 옥동자가 비단에 쌓여 있었다 한다. 노파가 신기하여 아이를 임금에게 바치고 궁중에서 기르게 하였다. 왕은 이 아이의 성을『봉』이름을『우』라 하였고,총명한 이 소년은 10세에 등과한 후 고려 조정에서 큰 벼슬에 올랐다. 이후 5대손 봉천우가 정승에 올라 조상의 은공을 기리기 위하여 봉은사라는 사찰을 짓고 노파의 갸륵한 은공을 받들기위해 오층석탑을 쌓고 또한 이 석조여래입상(일명-석상각)을 새겨 매년 제사을 올려 은혜에 보답하고자 하였다 한다.\"},{\"contentId\":2770369,\"contentTypeId\":28,\"title\":\"창후리수로\",\"addr1\":\"인천광역시 강화군 하점면 창후리 1237\",\"addr2\":\"\",\"zipcode\":\"23015\",\"tel\":\"\",\"firstImage\":\"\",\"firstImage2\":\"\",\"readcount\":0,\"sidoCode\":2,\"gugunCode\":1,\"latitude\":37.76084023,\"longitude\":126.368388,\"mlevel\":\"6\",\"likes\":0,\"overview\":\"강화섬과 별립산에서 모인 물들이 바다로 흘러가는 강줄기로 강화군 하점리에서 시작하여 이강리를 거쳐 창후리에 이르는 총길이 4km의 큰 규모의 수로이다. 교동도로 들어가기 위한 창후리 선착장의 끝지점 하류권이다. 정식 행정구역 명칭으로는 삼거천(三巨川)이다. 수로를 따라 도로가 나 있으며 강화섬의 들판과 물줄기를 따라 낚시를 즐기는 사람들에게 좋은 경험을 줄 수 있는 곳이다. 강화 수로는 월척 붕어가 많이 잡히는 곳으로 알려져 있다. 강화섬이 주는 바다 정취와 별립산의 풍채가 어우러져 소박하면서도 아름다운 정서를 불러일으키는 곳이다.\"}]','2023-11-24','[{\"src\":\"http://tong.visitkorea.or.kr/cms/resource/77/1876577_image2_1.jpg\",\"alt\":\"강화 고인돌 유적 [유네스코 세계문화유산]\",\"contentId\":125565},{\"src\":\"http://tong.visitkorea.or.kr/cms/resource/81/1876581_image2_1.jpg\",\"alt\":\"강화역사박물관\",\"contentId\":129810},{\"src\":\"http://tong.visitkorea.or.kr/cms/resource/03/183103_image2_1.jpg\",\"alt\":\"강화 장정리 석조여래입상\",\"contentId\":128823}]','싸피 그룹 1 여행 계획!','c4da3951-8995-11ee-a6c2-0242ac110002','8d23becb-8a6f-11ee-abd8-0242ac110002');
/*!40000 ALTER TABLE `plan_article` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-24 11:53:22
