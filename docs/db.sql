-- MySQL dump 10.13  Distrib 8.0.26, for Linux (x86_64)
--
-- Host: localhost    Database: manage_company
-- ------------------------------------------------------
-- Server version	8.0.26-0ubuntu0.20.04.2

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
-- Table structure for table `chat_box`
--

DROP TABLE IF EXISTS `chat_box`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_box` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `is_group` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `image_id` bigint DEFAULT NULL,
  `is_new` bit(1) DEFAULT NULL,
  `media_entity_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpml00niy4tmc2p91grqkwgf2a` (`image_id`),
  KEY `FKu84il80piibt7iel8idby5kv` (`media_entity_id`),
  CONSTRAINT `FKpml00niy4tmc2p91grqkwgf2a` FOREIGN KEY (`image_id`) REFERENCES `media` (`id`),
  CONSTRAINT `FKu84il80piibt7iel8idby5kv` FOREIGN KEY (`media_entity_id`) REFERENCES `media` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_box`
--

LOCK TABLES `chat_box` WRITE;
/*!40000 ALTER TABLE `chat_box` DISABLE KEYS */;
INSERT INTO `chat_box` VALUES (1,NULL,NULL,NULL,'2021-08-17 18:39:58','PINK',_binary '\0',NULL,NULL,_binary '\0',NULL),(2,NULL,NULL,NULL,'2021-08-17 23:46:41',NULL,_binary '\0',NULL,NULL,_binary '\0',NULL);
/*!40000 ALTER TABLE `chat_box` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media`
--

DROP TABLE IF EXISTS `media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `media` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `content_type` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media`
--

LOCK TABLES `media` WRITE;
/*!40000 ALTER TABLE `media` DISABLE KEYS */;
INSERT INTO `media` VALUES (1,'anonymousUser','2021-08-15 08:00:59','anonymousUser','2021-08-15 08:00:59','jpg','avatar_1.jpg','/images/20210815/avatar_1.jpg'),(2,'anonymousUser','2021-08-15 15:45:09','anonymousUser','2021-08-15 15:45:09','jpg','avatar_2.jpg','/images/20210815/avatar_2.jpg'),(3,'son','2021-08-22 22:37:07','son','2021-08-22 22:37:07','jpg','image_picker1163228467634360105.jpg','/images/20210822/image_picker1163228467634360105.jpg'),(4,'son','2021-08-23 15:07:51','son','2021-08-23 15:07:51','jpg','image_picker4209753965902440003.jpg','/images/20210823/image_picker4209753965902440003.jpg'),(5,'truong02.bp','2021-08-23 15:09:50','truong02.bp','2021-08-23 15:09:50','jpg','image_picker3443800192048146989.jpg','/images/20210823/image_picker3443800192048146989.jpg'),(6,'anonymousUser','2021-08-24 20:33:43','anonymousUser','2021-08-24 20:33:43','mp4','video_test.mp4','/images/20210824/video_test.mp4'),(7,'son','2021-08-27 21:18:40','son','2021-08-27 21:18:40','jpg','CAP7508899540016653996.jpg','/images/20210827/CAP7508899540016653996.jpg'),(8,'son','2021-08-27 21:24:35','son','2021-08-27 21:24:35','jpg','CAP4071041398632213071.jpg','/images/20210827/CAP4071041398632213071.jpg'),(9,'son','2021-08-27 21:26:41','son','2021-08-27 21:26:41','jpg','image_picker8621167222291709394.jpg','/images/20210827/image_picker8621167222291709394.jpg'),(10,'son','2021-08-27 21:51:24','son','2021-08-27 21:51:24','jpg','CAP4691480601378112807.jpg','/images/20210827/CAP4691480601378112807.jpg'),(11,'son','2021-08-27 22:00:13','son','2021-08-27 22:00:13','jpg','image_picker4164353695303339100.jpg','/images/20210827/image_picker4164353695303339100.jpg'),(12,'son','2021-08-27 22:17:22','son','2021-08-27 22:17:22','jpg','image_picker1500419303340784915.jpg','/images/20210827/image_picker1500419303340784915.jpg'),(13,'son','2021-08-27 23:03:28','son','2021-08-27 23:03:28','mp4','8039e194-b17a-41a1-a0ea-bc54254723114488872639501552890.mp4','/images/20210827/8039e194-b17a-41a1-a0ea-bc54254723114488872639501552890.mp4'),(14,'son','2021-08-27 23:04:31','son','2021-08-27 23:04:31','mp4','image_picker316501795761874672.jpg','/images/20210827/image_picker316501795761874672.jpg'),(15,'son','2021-08-27 23:06:22','son','2021-08-27 23:06:22','mp4','image_picker7229355828039573852.jpg','/images/20210827/image_picker7229355828039573852.jpg'),(16,'son','2021-08-27 23:09:51','son','2021-08-27 23:09:51','mp4','image_picker3686828107942634007.jpg','/images/20210827/image_picker3686828107942634007.jpg'),(17,'son','2021-08-27 23:13:06','son','2021-08-27 23:13:06','mp4','image_picker7599654879179191121.jpg','/images/20210827/image_picker7599654879179191121.jpg'),(18,'son','2021-08-27 23:15:22','son','2021-08-27 23:15:22','mp4','image_picker4174249871202717404.jpg','/images/20210827/image_picker4174249871202717404.jpg'),(19,'son','2021-08-27 23:20:00','son','2021-08-27 23:20:00','mp4','image_picker1778221030767417552.jpg','/images/20210827/image_picker1778221030767417552.jpg'),(20,'son','2021-08-27 23:24:19','son','2021-08-27 23:24:19','mp4','image_picker3491345747974247791.jpg','/images/20210827/image_picker3491345747974247791.jpg'),(21,'son','2021-08-27 23:34:08','son','2021-08-27 23:34:08','mp4','image_picker5777168323582587871.jpg','/images/20210827/image_picker5777168323582587871.jpg'),(22,'son','2021-08-27 23:34:36','son','2021-08-27 23:34:36','mp4','4c3e84cb-5fe8-49e2-8627-ce22fc955b788131023541355891832.mp4','/images/20210827/4c3e84cb-5fe8-49e2-8627-ce22fc955b788131023541355891832.mp4'),(23,'son','2021-08-27 23:39:24','son','2021-08-27 23:39:24','mp4','image_picker7444955120247516293.jpg','/images/20210827/image_picker7444955120247516293.jpg'),(24,'son','2021-08-27 23:48:48','son','2021-08-27 23:48:48','mp4','20210822_094040_05.mp4','/images/20210827/20210822_094040_05.mp4'),(25,'son','2021-08-28 08:24:15','son','2021-08-28 08:24:15','jpg','image_picker1034538138257644391.jpg','/images/20210828/image_picker1034538138257644391.jpg'),(26,'son','2021-08-28 08:25:17','son','2021-08-28 08:25:17','mp4','Screen_Recording_20210823-151113.mp4','/images/20210828/Screen_Recording_20210823-151113.mp4'),(27,'son','2021-08-28 08:29:23','son','2021-08-28 08:29:23','jpg','image_picker1799655838621288561.jpg','/images/20210828/image_picker1799655838621288561.jpg'),(28,'son','2021-08-28 08:44:27','son','2021-08-28 08:44:27','mp4','d4aeb396-c150-4140-87a6-a2fa8eea676b671115101907639193.mp4','/images/20210828/d4aeb396-c150-4140-87a6-a2fa8eea676b671115101907639193.mp4');
/*!40000 ALTER TABLE `media` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `media_id` bigint DEFAULT NULL,
  `sender_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnkbwo9ai8uia66e8r0uaj6acl` (`sender_id`),
  KEY `FKqv4xae3l4o697y2mjfrfwr8oy` (`media_id`),
  CONSTRAINT `FKnkbwo9ai8uia66e8r0uaj6acl` FOREIGN KEY (`sender_id`) REFERENCES `messenger` (`id`),
  CONSTRAINT `FKqv4xae3l4o697y2mjfrfwr8oy` FOREIGN KEY (`media_id`) REFERENCES `media` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (23,NULL,'2021-08-17 18:39:58',NULL,'2021-08-17 18:39:58','he va lo',NULL,1),(24,NULL,'2021-08-17 18:41:55',NULL,'2021-08-17 18:41:55','chao ban nhe',NULL,2),(25,NULL,'2021-08-17 22:46:18',NULL,'2021-08-17 22:46:18','chao',NULL,2),(26,NULL,'2021-08-17 22:46:30',NULL,'2021-08-17 22:46:30','oki ban',NULL,1),(27,NULL,'2021-08-17 22:50:11',NULL,'2021-08-17 22:50:11','haha',NULL,2),(28,NULL,'2021-08-17 23:13:03',NULL,'2021-08-17 23:13:03','hello',NULL,1),(29,NULL,'2021-08-17 23:46:41',NULL,'2021-08-17 23:46:41','Xach ba lo va di',NULL,4),(30,NULL,'2021-08-17 23:46:49',NULL,'2021-08-17 23:46:49','haha',NULL,4),(31,NULL,'2021-08-17 23:47:04',NULL,'2021-08-17 23:47:04','hi',NULL,1),(32,NULL,'2021-08-17 23:49:41',NULL,'2021-08-17 23:49:41','hihi',NULL,4),(33,NULL,'2021-08-17 23:54:24',NULL,'2021-08-17 23:54:24','hahahahaha',NULL,4),(34,NULL,'2021-08-17 23:54:35',NULL,'2021-08-17 23:54:35','nho cai mat may nhe',NULL,1),(35,NULL,'2021-08-17 23:58:19',NULL,'2021-08-17 23:58:19','helo va lo',NULL,1),(36,NULL,'2021-08-17 23:58:26',NULL,'2021-08-17 23:58:26','a',NULL,1),(37,NULL,'2021-08-17 23:58:33',NULL,'2021-08-17 23:58:33','kkkkk',NULL,4),(38,NULL,'2021-08-18 11:13:36',NULL,'2021-08-18 11:13:36','hihi',NULL,1),(39,NULL,'2021-08-18 23:00:26',NULL,'2021-08-18 23:00:26','hello',NULL,2),(40,NULL,'2021-08-18 23:01:45',NULL,'2021-08-18 23:01:45','hahaa',NULL,2),(41,NULL,'2021-08-18 23:02:25',NULL,'2021-08-18 23:02:25','eeee',NULL,2),(42,NULL,'2021-08-18 23:02:42',NULL,'2021-08-18 23:02:42','eeeeee',NULL,2),(43,NULL,'2021-08-18 23:02:50',NULL,'2021-08-18 23:02:50','eeee',NULL,2),(44,NULL,'2021-08-18 23:02:58',NULL,'2021-08-18 23:02:58','eeeesadsd',NULL,2),(45,NULL,'2021-08-18 23:15:20',NULL,'2021-08-18 23:15:20','haha',NULL,2),(46,NULL,'2021-08-18 23:15:27',NULL,'2021-08-18 23:15:27','sds',NULL,2),(47,NULL,'2021-08-18 23:16:20',NULL,'2021-08-18 23:16:20','haha',NULL,2),(48,NULL,'2021-08-18 23:18:10',NULL,'2021-08-18 23:18:10','123456',NULL,2),(49,NULL,'2021-08-18 23:18:38',NULL,'2021-08-18 23:18:38','haha',NULL,2),(50,NULL,'2021-08-18 23:21:19',NULL,'2021-08-18 23:21:19','hi',NULL,2),(51,NULL,'2021-08-18 23:22:23',NULL,'2021-08-18 23:22:23','haha',NULL,2),(52,NULL,'2021-08-18 23:24:49',NULL,'2021-08-18 23:24:49','hello',NULL,1),(53,NULL,'2021-08-18 23:26:22',NULL,'2021-08-18 23:26:22','haha',NULL,2),(54,NULL,'2021-08-18 23:30:19',NULL,'2021-08-18 23:30:19','the a',NULL,2),(55,NULL,'2021-08-18 23:32:17',NULL,'2021-08-18 23:32:17','hahaaaaaa',NULL,2),(56,NULL,'2021-08-18 23:37:24',NULL,'2021-08-18 23:37:24','hoho',NULL,2),(57,NULL,'2021-08-19 00:17:26',NULL,'2021-08-19 00:17:26','haha',NULL,1),(58,NULL,'2021-08-19 00:20:30',NULL,'2021-08-19 00:20:30','hahaha',NULL,1),(59,NULL,'2021-08-19 00:26:50',NULL,'2021-08-19 00:26:50','yo',NULL,1),(60,NULL,'2021-08-19 00:28:51',NULL,'2021-08-19 00:28:51','ha',NULL,1),(61,NULL,'2021-08-19 21:33:35',NULL,'2021-08-19 21:33:35','test',NULL,2),(62,NULL,'2021-08-19 21:34:32',NULL,'2021-08-19 21:34:32','testtt',NULL,2),(63,NULL,'2021-08-19 21:36:00',NULL,'2021-08-19 21:36:00','testtt',NULL,2),(64,NULL,'2021-08-19 21:38:14',NULL,'2021-08-19 21:38:14','hello',NULL,1),(65,NULL,'2021-08-19 21:39:00',NULL,'2021-08-19 21:39:00','abc',NULL,1),(66,NULL,'2021-08-19 21:39:02',NULL,'2021-08-19 21:39:02','dyz',NULL,1),(67,NULL,'2021-08-19 22:06:21',NULL,'2021-08-19 22:06:21','xach ba lo va di',NULL,2),(68,NULL,'2021-08-20 23:33:10',NULL,'2021-08-20 23:33:10','hello',NULL,1),(69,NULL,'2021-08-20 23:33:44',NULL,'2021-08-20 23:33:44','hahahaha',NULL,1),(70,NULL,'2021-08-20 23:37:03',NULL,'2021-08-20 23:37:03','chao bạn nhé',NULL,1),(71,NULL,'2021-08-20 23:38:07',NULL,'2021-08-20 23:38:07','hi',NULL,1),(72,NULL,'2021-08-20 23:38:24',NULL,'2021-08-20 23:38:24','sàefe',NULL,1),(73,NULL,'2021-08-20 23:41:42',NULL,'2021-08-20 23:41:42','xin chao',NULL,2),(74,NULL,'2021-08-20 23:41:52',NULL,'2021-08-20 23:41:52','xin chao',NULL,1),(75,NULL,'2021-08-20 23:49:52',NULL,'2021-08-20 23:49:52','hello',NULL,2),(76,NULL,'2021-08-20 23:50:01',NULL,'2021-08-20 23:50:01','oke bạn',NULL,1),(77,NULL,'2021-08-20 23:50:07',NULL,'2021-08-20 23:50:07','haa',NULL,1),(78,NULL,'2021-08-20 23:50:12',NULL,'2021-08-20 23:50:12','rfd',NULL,1),(79,NULL,'2021-08-20 23:50:26',NULL,'2021-08-20 23:50:26','egegr',NULL,1),(80,NULL,'2021-08-20 23:52:26',NULL,'2021-08-20 23:52:26','a the a',NULL,2),(81,NULL,'2021-08-20 23:52:33',NULL,'2021-08-20 23:52:33','m lam sao',NULL,2),(82,NULL,'2021-08-20 23:52:55',NULL,'2021-08-20 23:52:55','chaooo',NULL,1),(83,NULL,'2021-08-21 00:06:10',NULL,'2021-08-21 00:06:10','ok ban, chao ban cua toi nhe',NULL,2),(84,NULL,'2021-08-21 00:06:45',NULL,'2021-08-21 00:06:45','e',NULL,1),(85,NULL,'2021-08-21 00:09:38',NULL,'2021-08-21 00:09:38','eooo',NULL,2),(86,NULL,'2021-08-21 00:12:18',NULL,'2021-08-21 00:12:18','heloooooooo',NULL,2),(87,NULL,'2021-08-21 00:19:11',NULL,'2021-08-21 00:19:11','haha',NULL,2),(88,NULL,'2021-08-21 00:20:08',NULL,'2021-08-21 00:20:08','hi',NULL,1),(89,NULL,'2021-08-21 00:20:14',NULL,'2021-08-21 00:20:14','ok day',NULL,2),(90,NULL,'2021-08-21 00:20:27',NULL,'2021-08-21 00:20:27','the bay gio thi sao',NULL,2),(91,NULL,'2021-08-21 00:21:10',NULL,'2021-08-21 00:21:10','seen roi a',NULL,2),(92,NULL,'2021-08-21 00:21:19',NULL,'2021-08-21 00:21:19','tu ki me mat',NULL,2),(93,NULL,'2021-08-21 00:37:30',NULL,'2021-08-21 00:37:30','haha',NULL,2),(94,NULL,'2021-08-21 15:35:21',NULL,'2021-08-21 15:35:21','kakaka',NULL,2),(95,NULL,'2021-08-22 14:48:14',NULL,'2021-08-22 14:48:14','hê và lô nhé',NULL,2),(97,NULL,'2021-08-22 17:06:43',NULL,'2021-08-22 17:06:43','âhhaa',NULL,2),(98,NULL,'2021-08-22 21:48:23',NULL,'2021-08-22 21:48:23','helo',NULL,2),(99,NULL,'2021-08-22 22:37:07',NULL,'2021-08-22 22:37:07',NULL,3,2),(100,NULL,'2021-08-23 15:07:51',NULL,'2021-08-23 15:07:51',NULL,4,2),(101,NULL,'2021-08-23 15:09:50',NULL,'2021-08-23 15:09:50',NULL,5,1),(102,NULL,'2021-08-23 15:09:50',NULL,'2021-08-23 15:09:50',NULL,6,1),(103,NULL,'2021-08-27 21:18:40',NULL,'2021-08-27 21:18:40',NULL,7,2),(104,NULL,'2021-08-27 21:24:35',NULL,'2021-08-27 21:24:35',NULL,8,2),(105,NULL,'2021-08-27 21:26:42',NULL,'2021-08-27 21:26:42',NULL,9,2),(106,NULL,'2021-08-27 21:51:24',NULL,'2021-08-27 21:51:24',NULL,10,2),(107,NULL,'2021-08-27 22:00:13',NULL,'2021-08-27 22:00:13',NULL,11,2),(108,NULL,'2021-08-27 22:17:22',NULL,'2021-08-27 22:17:22',NULL,12,2),(109,NULL,'2021-08-27 23:03:28',NULL,'2021-08-27 23:03:28',NULL,13,2),(110,NULL,'2021-08-27 23:04:31',NULL,'2021-08-27 23:04:31',NULL,14,2),(111,NULL,'2021-08-27 23:06:22',NULL,'2021-08-27 23:06:22',NULL,15,2),(112,NULL,'2021-08-27 23:09:51',NULL,'2021-08-27 23:09:51',NULL,16,2),(113,NULL,'2021-08-27 23:13:06',NULL,'2021-08-27 23:13:06',NULL,17,2),(114,NULL,'2021-08-27 23:15:23',NULL,'2021-08-27 23:15:23',NULL,18,2),(115,NULL,'2021-08-27 23:20:01',NULL,'2021-08-27 23:20:01',NULL,19,2),(116,NULL,'2021-08-27 23:24:19',NULL,'2021-08-27 23:24:19',NULL,20,2),(117,NULL,'2021-08-27 23:34:08',NULL,'2021-08-27 23:34:08',NULL,21,2),(118,NULL,'2021-08-27 23:34:36',NULL,'2021-08-27 23:34:36',NULL,22,2),(119,NULL,'2021-08-27 23:39:24',NULL,'2021-08-27 23:39:24',NULL,23,2),(120,NULL,'2021-08-27 23:48:48',NULL,'2021-08-27 23:48:48',NULL,24,2),(121,NULL,'2021-08-28 08:24:15',NULL,'2021-08-28 08:24:15',NULL,25,2),(122,NULL,'2021-08-28 08:25:18',NULL,'2021-08-28 08:25:18',NULL,26,2),(123,NULL,'2021-08-28 08:29:24',NULL,'2021-08-28 08:29:24',NULL,27,2),(124,NULL,'2021-08-28 08:37:47',NULL,'2021-08-28 08:37:47','helo',NULL,2),(125,NULL,'2021-08-28 08:38:22',NULL,'2021-08-28 08:38:22','hihi',NULL,2),(126,NULL,'2021-08-28 08:40:32',NULL,'2021-08-28 08:40:32','haha',NULL,2),(127,NULL,'2021-08-28 08:41:52',NULL,'2021-08-28 08:41:52','chào bạn nha',NULL,2),(128,NULL,'2021-08-28 08:41:56',NULL,'2021-08-28 08:41:56','?',NULL,2),(129,NULL,'2021-08-28 08:44:27',NULL,'2021-08-28 08:44:27',NULL,28,2);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_detail`
--

DROP TABLE IF EXISTS `message_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `reaction_id` bigint DEFAULT NULL,
  `seen_by` bigint DEFAULT NULL,
  `message_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `seen_by_message_index` (`seen_by`,`message_id`),
  UNIQUE KEY `reaction_seen_by_message_index` (`reaction_id`,`seen_by`,`message_id`),
  KEY `FK95bgockxqmvika6y10cdued93` (`message_id`),
  CONSTRAINT `FK79cg7knn9ysdmelh1j3sk0pc6` FOREIGN KEY (`reaction_id`) REFERENCES `reaction` (`id`),
  CONSTRAINT `FK95bgockxqmvika6y10cdued93` FOREIGN KEY (`message_id`) REFERENCES `message` (`id`),
  CONSTRAINT `FKryjlxxo65xeo7psk4th7exkug` FOREIGN KEY (`seen_by`) REFERENCES `messenger` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=219 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_detail`
--

LOCK TABLES `message_detail` WRITE;
/*!40000 ALTER TABLE `message_detail` DISABLE KEYS */;
INSERT INTO `message_detail` VALUES (12,NULL,'2021-08-17 18:39:58',NULL,'2021-08-17 18:39:58',NULL,1,23),(13,NULL,'2021-08-17 18:41:55',NULL,'2021-08-17 18:41:55',NULL,2,24),(14,NULL,'2021-08-17 22:46:18',NULL,'2021-08-17 22:46:18',NULL,2,25),(15,NULL,'2021-08-17 22:46:30',NULL,'2021-08-17 22:46:30',NULL,1,26),(16,NULL,'2021-08-17 22:50:11',NULL,'2021-08-17 22:50:11',NULL,2,27),(17,NULL,'2021-08-17 23:13:03',NULL,'2021-08-17 23:13:03',NULL,1,28),(18,NULL,'2021-08-17 23:46:41',NULL,'2021-08-17 23:46:41',NULL,4,29),(19,NULL,'2021-08-17 23:46:49',NULL,'2021-08-17 23:46:49',NULL,4,30),(20,NULL,'2021-08-17 23:47:04',NULL,'2021-08-17 23:47:04',NULL,1,31),(21,NULL,'2021-08-17 23:49:41',NULL,'2021-08-17 23:49:41',NULL,4,32),(22,NULL,'2021-08-17 23:54:24',NULL,'2021-08-17 23:54:24',NULL,4,33),(23,NULL,'2021-08-17 23:54:35',NULL,'2021-08-17 23:54:35',NULL,1,34),(24,NULL,'2021-08-17 23:58:19',NULL,'2021-08-17 23:58:19',NULL,1,35),(25,NULL,'2021-08-17 23:58:26',NULL,'2021-08-17 23:58:26',NULL,1,36),(26,NULL,'2021-08-17 23:58:33',NULL,'2021-08-17 23:58:33',NULL,4,37),(27,NULL,'2021-08-18 11:13:36',NULL,'2021-08-18 11:13:36',NULL,1,38),(28,NULL,'2021-08-18 12:21:34',NULL,'2021-08-18 12:21:34',NULL,2,28),(29,NULL,'2021-08-18 12:21:34',NULL,'2021-08-18 12:21:34',NULL,2,31),(30,NULL,'2021-08-18 12:21:34',NULL,'2021-08-18 12:21:34',NULL,2,34),(31,NULL,'2021-08-18 12:21:34',NULL,'2021-08-18 12:21:34',NULL,2,35),(32,NULL,'2021-08-18 12:21:34',NULL,'2021-08-18 12:21:34',NULL,2,36),(33,NULL,'2021-08-18 12:21:34',NULL,'2021-08-18 12:21:34',NULL,2,38),(34,NULL,'2021-08-18 23:00:27',NULL,'2021-08-18 23:00:27',NULL,2,39),(35,NULL,'2021-08-18 23:00:30',NULL,'2021-08-18 23:00:30',NULL,1,39),(36,NULL,'2021-08-18 23:01:45',NULL,'2021-08-18 23:01:45',NULL,2,40),(37,NULL,'2021-08-18 23:01:47',NULL,'2021-08-18 23:01:47',NULL,1,40),(38,NULL,'2021-08-18 23:02:25',NULL,'2021-08-18 23:02:25',NULL,2,41),(39,NULL,'2021-08-18 23:02:29',NULL,'2021-08-18 23:02:29',NULL,1,41),(40,NULL,'2021-08-18 23:02:42',NULL,'2021-08-18 23:02:42',NULL,2,42),(41,NULL,'2021-08-18 23:02:50',NULL,'2021-08-18 23:02:50',NULL,2,43),(42,NULL,'2021-08-18 23:02:58',NULL,'2021-08-18 23:02:58',NULL,2,44),(43,NULL,'2021-08-18 23:03:03',NULL,'2021-08-18 23:03:03',NULL,1,44),(44,NULL,'2021-08-18 23:03:03',NULL,'2021-08-18 23:03:03',NULL,1,43),(45,NULL,'2021-08-18 23:03:03',NULL,'2021-08-18 23:03:03',NULL,1,42),(46,NULL,'2021-08-18 23:15:20',NULL,'2021-08-18 23:15:20',NULL,2,45),(47,NULL,'2021-08-18 23:15:27',NULL,'2021-08-18 23:15:27',NULL,2,46),(48,NULL,'2021-08-18 23:16:20',NULL,'2021-08-18 23:16:20',NULL,2,47),(49,NULL,'2021-08-18 23:18:10',NULL,'2021-08-18 23:18:10',NULL,2,48),(50,NULL,'2021-08-18 23:18:38',NULL,'2021-08-18 23:18:38',NULL,2,49),(51,NULL,'2021-08-18 23:21:19',NULL,'2021-08-18 23:21:19',NULL,2,50),(52,NULL,'2021-08-18 23:22:23',NULL,'2021-08-18 23:22:23',NULL,2,51),(53,NULL,'2021-08-18 23:22:31',NULL,'2021-08-18 23:22:31',NULL,1,51),(54,NULL,'2021-08-18 23:22:31',NULL,'2021-08-18 23:22:31',NULL,1,50),(55,NULL,'2021-08-18 23:22:31',NULL,'2021-08-18 23:22:31',NULL,1,49),(56,NULL,'2021-08-18 23:22:31',NULL,'2021-08-18 23:22:31',NULL,1,48),(57,NULL,'2021-08-18 23:22:31',NULL,'2021-08-18 23:22:31',NULL,1,47),(58,NULL,'2021-08-18 23:22:31',NULL,'2021-08-18 23:22:31',NULL,1,46),(59,NULL,'2021-08-18 23:22:31',NULL,'2021-08-18 23:22:31',NULL,1,45),(60,NULL,'2021-08-18 23:24:49',NULL,'2021-08-18 23:24:49',NULL,1,52),(61,NULL,'2021-08-18 23:24:52',NULL,'2021-08-18 23:24:52',NULL,2,52),(62,NULL,'2021-08-18 23:26:22',NULL,'2021-08-18 23:26:22',NULL,2,53),(63,NULL,'2021-08-18 23:26:28',NULL,'2021-08-18 23:26:28',NULL,1,53),(64,NULL,'2021-08-18 23:30:19',NULL,'2021-08-18 23:30:19',NULL,2,54),(65,NULL,'2021-08-18 23:30:22',NULL,'2021-08-18 23:30:22',NULL,1,54),(66,NULL,'2021-08-18 23:32:17',NULL,'2021-08-18 23:32:17',NULL,2,55),(67,NULL,'2021-08-18 23:32:19',NULL,'2021-08-18 23:32:19',NULL,1,55),(68,NULL,'2021-08-18 23:37:24',NULL,'2021-08-18 23:37:24',NULL,2,56),(69,NULL,'2021-08-18 23:37:25',NULL,'2021-08-18 23:37:25',NULL,1,56),(70,NULL,'2021-08-19 00:17:26',NULL,'2021-08-19 00:17:26',NULL,1,57),(71,NULL,'2021-08-19 00:17:31',NULL,'2021-08-19 00:17:31',NULL,2,57),(72,NULL,'2021-08-19 00:20:30',NULL,'2021-08-19 00:20:30',NULL,1,58),(73,NULL,'2021-08-19 00:20:56',NULL,'2021-08-19 00:20:56',NULL,2,58),(74,NULL,'2021-08-19 00:26:50',NULL,'2021-08-19 00:26:50',NULL,1,59),(75,NULL,'2021-08-19 00:26:52',NULL,'2021-08-19 00:26:52',NULL,2,59),(76,NULL,'2021-08-19 00:28:51',NULL,'2021-08-19 00:28:51',NULL,1,60),(77,NULL,'2021-08-19 00:28:53',NULL,'2021-08-19 00:28:53',NULL,2,60),(78,NULL,'2021-08-19 21:33:35',NULL,'2021-08-19 21:33:35',NULL,2,61),(79,NULL,'2021-08-19 21:33:40',NULL,'2021-08-19 21:33:40',NULL,1,61),(80,NULL,'2021-08-19 21:34:32',NULL,'2021-08-19 21:34:32',NULL,2,62),(81,NULL,'2021-08-19 21:34:34',NULL,'2021-08-19 21:34:34',NULL,1,62),(82,NULL,'2021-08-19 21:36:00',NULL,'2021-08-19 21:36:00',NULL,2,63),(83,NULL,'2021-08-19 21:36:07',NULL,'2021-08-19 21:36:07',NULL,1,63),(84,NULL,'2021-08-19 21:38:14',NULL,'2021-08-19 21:38:14',NULL,1,64),(85,NULL,'2021-08-19 21:38:26',NULL,'2021-08-19 21:38:26',NULL,2,64),(86,NULL,'2021-08-19 21:39:00',NULL,'2021-08-19 21:39:00',NULL,1,65),(87,NULL,'2021-08-19 21:39:02',NULL,'2021-08-19 21:39:02',NULL,1,66),(88,NULL,'2021-08-19 21:39:06',NULL,'2021-08-19 21:39:06',NULL,2,66),(89,NULL,'2021-08-19 21:39:06',NULL,'2021-08-19 21:39:06',NULL,2,65),(90,NULL,'2021-08-19 22:06:21',NULL,'2021-08-19 22:06:21',NULL,2,67),(91,NULL,'2021-08-19 22:08:27',NULL,'2021-08-19 22:08:27',NULL,1,67),(92,NULL,'2021-08-20 23:33:10',NULL,'2021-08-20 23:33:10',NULL,1,68),(93,NULL,'2021-08-20 23:33:14',NULL,'2021-08-20 23:33:14',NULL,2,68),(94,NULL,'2021-08-20 23:33:44',NULL,'2021-08-20 23:33:44',NULL,1,69),(95,NULL,'2021-08-20 23:33:48',NULL,'2021-08-20 23:33:48',NULL,2,69),(96,NULL,'2021-08-20 23:37:03',NULL,'2021-08-20 23:37:03',NULL,1,70),(97,NULL,'2021-08-20 23:37:32',NULL,'2021-08-20 23:37:32',NULL,2,70),(98,NULL,'2021-08-20 23:38:07',NULL,'2021-08-20 23:38:07',NULL,1,71),(99,NULL,'2021-08-20 23:38:09',NULL,'2021-08-20 23:38:09',NULL,2,71),(100,NULL,'2021-08-20 23:38:24',NULL,'2021-08-20 23:38:24',NULL,1,72),(101,NULL,'2021-08-20 23:38:26',NULL,'2021-08-20 23:38:26',NULL,2,72),(102,NULL,'2021-08-20 23:41:42',NULL,'2021-08-20 23:41:42',NULL,2,73),(103,NULL,'2021-08-20 23:41:44',NULL,'2021-08-20 23:41:44',NULL,1,73),(104,NULL,'2021-08-20 23:41:52',NULL,'2021-08-20 23:41:52',NULL,1,74),(105,NULL,'2021-08-20 23:42:37',NULL,'2021-08-20 23:42:37',NULL,2,74),(106,NULL,'2021-08-20 23:49:52',NULL,'2021-08-20 23:49:52',NULL,2,75),(107,NULL,'2021-08-20 23:49:52',NULL,'2021-08-20 23:49:52',NULL,1,75),(108,NULL,'2021-08-20 23:50:01',NULL,'2021-08-20 23:50:01',NULL,1,76),(109,NULL,'2021-08-20 23:50:01',NULL,'2021-08-20 23:50:01',NULL,2,76),(110,NULL,'2021-08-20 23:50:07',NULL,'2021-08-20 23:50:07',NULL,1,77),(111,NULL,'2021-08-20 23:50:07',NULL,'2021-08-20 23:50:07',NULL,2,77),(112,NULL,'2021-08-20 23:50:12',NULL,'2021-08-20 23:50:12',NULL,1,78),(113,NULL,'2021-08-20 23:50:12',NULL,'2021-08-20 23:50:12',NULL,2,78),(114,NULL,'2021-08-20 23:50:26',NULL,'2021-08-20 23:50:26',NULL,1,79),(115,NULL,'2021-08-20 23:50:26',NULL,'2021-08-20 23:50:26',NULL,2,79),(116,NULL,'2021-08-20 23:52:26',NULL,'2021-08-20 23:52:26',NULL,2,80),(117,NULL,'2021-08-20 23:52:26',NULL,'2021-08-20 23:52:26',NULL,1,80),(118,NULL,'2021-08-20 23:52:33',NULL,'2021-08-20 23:52:33',NULL,2,81),(119,NULL,'2021-08-20 23:52:37',NULL,'2021-08-20 23:52:37',NULL,1,81),(120,NULL,'2021-08-20 23:52:55',NULL,'2021-08-20 23:52:55',NULL,1,82),(121,NULL,'2021-08-20 23:52:58',NULL,'2021-08-20 23:52:58',NULL,2,82),(122,NULL,'2021-08-21 00:06:10',NULL,'2021-08-28 08:17:33',2,2,83),(123,NULL,'2021-08-21 00:06:16',NULL,'2021-08-21 22:21:10',NULL,1,83),(124,NULL,'2021-08-21 00:06:45',NULL,'2021-08-21 00:06:45',NULL,1,84),(125,NULL,'2021-08-21 00:06:46',NULL,'2021-08-21 00:06:46',NULL,2,84),(126,NULL,'2021-08-21 00:09:38',NULL,'2021-08-21 22:11:29',NULL,2,85),(127,NULL,'2021-08-21 00:09:42',NULL,'2021-08-21 00:09:42',NULL,1,85),(128,NULL,'2021-08-21 00:12:18',NULL,'2021-08-22 14:53:30',2,2,86),(129,NULL,'2021-08-21 00:12:23',NULL,'2021-08-21 00:12:23',NULL,1,86),(130,NULL,'2021-08-21 00:19:11',NULL,'2021-08-22 14:48:37',NULL,2,87),(131,NULL,'2021-08-21 00:19:17',NULL,'2021-08-21 00:19:17',NULL,1,87),(132,NULL,'2021-08-21 00:20:08',NULL,'2021-08-21 00:20:08',NULL,1,88),(133,NULL,'2021-08-21 00:20:08',NULL,'2021-08-21 22:20:51',2,2,88),(134,NULL,'2021-08-21 00:20:14',NULL,'2021-08-21 00:20:14',NULL,2,89),(135,NULL,'2021-08-21 00:20:14',NULL,'2021-08-21 22:20:55',2,1,89),(136,NULL,'2021-08-21 00:20:27',NULL,'2021-08-21 00:20:27',NULL,2,90),(137,NULL,'2021-08-21 00:20:33',NULL,'2021-08-21 22:20:37',2,1,90),(138,NULL,'2021-08-21 00:21:10',NULL,'2021-08-21 00:21:10',NULL,2,91),(139,NULL,'2021-08-21 00:21:11',NULL,'2021-08-21 00:21:11',NULL,1,91),(140,NULL,'2021-08-21 00:21:19',NULL,'2021-08-22 21:48:08',NULL,2,92),(141,NULL,'2021-08-21 00:21:24',NULL,'2021-08-21 00:21:24',NULL,1,92),(142,NULL,'2021-08-21 00:37:30',NULL,'2021-08-21 00:37:30',NULL,2,93),(143,NULL,'2021-08-21 15:35:21',NULL,'2021-08-21 15:35:21',NULL,2,94),(144,NULL,'2021-08-21 16:03:13',NULL,'2021-08-21 16:03:13',NULL,1,94),(145,NULL,'2021-08-21 16:03:13',NULL,'2021-08-21 16:03:13',NULL,1,93),(146,NULL,'2021-08-22 14:48:14',NULL,'2021-08-22 17:11:45',NULL,2,95),(148,NULL,'2021-08-22 17:06:43',NULL,'2021-08-22 17:06:43',NULL,2,97),(149,NULL,'2021-08-22 21:48:23',NULL,'2021-08-22 21:48:23',NULL,2,98),(150,NULL,'2021-08-22 22:37:07',NULL,'2021-08-22 22:48:32',NULL,2,99),(151,NULL,'2021-08-23 15:07:51',NULL,'2021-08-23 15:07:51',NULL,2,100),(152,NULL,'2021-08-23 15:09:37',NULL,'2021-08-23 15:09:37',NULL,1,100),(153,NULL,'2021-08-23 15:09:37',NULL,'2021-08-23 15:09:37',NULL,1,99),(154,NULL,'2021-08-23 15:09:37',NULL,'2021-08-23 15:09:37',NULL,1,98),(155,NULL,'2021-08-23 15:09:37',NULL,'2021-08-23 15:09:37',NULL,1,97),(156,NULL,'2021-08-23 15:09:37',NULL,'2021-08-23 15:09:37',NULL,1,95),(157,NULL,'2021-08-23 15:09:50',NULL,'2021-08-23 15:09:50',NULL,1,101),(158,NULL,'2021-08-23 15:16:19',NULL,'2021-08-23 15:16:19',NULL,2,101),(159,NULL,'2021-08-25 22:12:52',NULL,'2021-08-26 21:21:45',NULL,2,102),(160,NULL,'2021-08-27 21:18:40',NULL,'2021-08-27 21:18:40',NULL,2,103),(161,NULL,'2021-08-27 21:24:35',NULL,'2021-08-27 21:24:35',NULL,2,104),(162,NULL,'2021-08-27 21:26:42',NULL,'2021-08-27 21:26:42',NULL,2,105),(163,NULL,'2021-08-27 21:51:24',NULL,'2021-08-27 21:51:24',NULL,2,106),(164,NULL,'2021-08-27 22:00:13',NULL,'2021-08-27 22:00:13',NULL,2,107),(165,NULL,'2021-08-27 22:17:22',NULL,'2021-08-27 22:17:22',NULL,2,108),(166,NULL,'2021-08-27 23:03:28',NULL,'2021-08-27 23:03:28',NULL,2,109),(167,NULL,'2021-08-27 23:04:31',NULL,'2021-08-27 23:04:31',NULL,2,110),(168,NULL,'2021-08-27 23:06:22',NULL,'2021-08-27 23:06:22',NULL,2,111),(169,NULL,'2021-08-27 23:09:51',NULL,'2021-08-27 23:09:51',NULL,2,112),(170,NULL,'2021-08-27 23:13:06',NULL,'2021-08-27 23:13:06',NULL,2,113),(171,NULL,'2021-08-27 23:15:23',NULL,'2021-08-27 23:15:23',NULL,2,114),(172,NULL,'2021-08-27 23:20:01',NULL,'2021-08-27 23:20:01',NULL,2,115),(173,NULL,'2021-08-27 23:24:19',NULL,'2021-08-27 23:24:19',NULL,2,116),(174,NULL,'2021-08-27 23:34:08',NULL,'2021-08-27 23:34:08',NULL,2,117),(175,NULL,'2021-08-27 23:34:36',NULL,'2021-08-27 23:34:36',NULL,2,118),(176,NULL,'2021-08-27 23:39:24',NULL,'2021-08-28 08:19:21',NULL,2,119),(177,NULL,'2021-08-27 23:48:48',NULL,'2021-08-28 08:07:02',4,2,120),(178,NULL,'2021-08-28 08:24:15',NULL,'2021-08-28 08:24:15',NULL,2,121),(179,NULL,'2021-08-28 08:25:18',NULL,'2021-08-28 08:25:18',NULL,2,122),(180,NULL,'2021-08-28 08:29:24',NULL,'2021-08-28 08:29:24',NULL,2,123),(181,NULL,'2021-08-28 08:37:47',NULL,'2021-08-28 08:37:47',NULL,2,124),(182,NULL,'2021-08-28 08:38:22',NULL,'2021-08-28 08:38:22',NULL,2,125),(183,NULL,'2021-08-28 08:40:32',NULL,'2021-08-28 08:40:32',NULL,2,126),(184,NULL,'2021-08-28 08:41:52',NULL,'2021-08-28 08:41:52',NULL,2,127),(185,NULL,'2021-08-28 08:41:56',NULL,'2021-08-28 08:41:56',NULL,2,128),(186,NULL,'2021-08-28 08:44:27',NULL,'2021-08-28 08:44:27',NULL,2,129),(187,NULL,'2021-08-29 23:48:32',NULL,'2021-08-29 23:48:32',NULL,1,129),(188,NULL,'2021-08-29 23:48:32',NULL,'2021-08-29 23:48:32',NULL,1,128),(189,NULL,'2021-08-29 23:48:32',NULL,'2021-08-29 23:48:32',NULL,1,127),(190,NULL,'2021-08-29 23:48:32',NULL,'2021-08-29 23:48:32',NULL,1,126),(191,NULL,'2021-08-29 23:48:32',NULL,'2021-08-29 23:48:32',NULL,1,125),(192,NULL,'2021-08-29 23:48:32',NULL,'2021-08-29 23:48:32',NULL,1,124),(193,NULL,'2021-08-29 23:48:32',NULL,'2021-08-29 23:48:32',NULL,1,123),(194,NULL,'2021-08-29 23:48:32',NULL,'2021-08-29 23:48:32',NULL,1,122),(195,NULL,'2021-08-29 23:48:32',NULL,'2021-08-29 23:48:32',NULL,1,121),(196,NULL,'2021-08-29 23:48:32',NULL,'2021-08-29 23:48:32',NULL,1,120),(197,NULL,'2021-08-29 23:48:32',NULL,'2021-08-29 23:48:32',NULL,1,119),(198,NULL,'2021-08-29 23:48:32',NULL,'2021-08-29 23:48:32',NULL,1,118),(199,NULL,'2021-08-29 23:48:32',NULL,'2021-08-29 23:48:32',NULL,1,117),(200,NULL,'2021-08-29 23:48:32',NULL,'2021-08-29 23:48:32',NULL,1,116),(201,NULL,'2021-08-29 23:48:32',NULL,'2021-08-29 23:48:32',NULL,1,115),(202,NULL,'2021-08-29 23:48:32',NULL,'2021-08-29 23:48:32',NULL,1,114),(203,NULL,'2021-08-29 23:48:32',NULL,'2021-08-29 23:48:32',NULL,1,113),(204,NULL,'2021-08-29 23:48:32',NULL,'2021-08-29 23:48:32',NULL,1,112),(205,NULL,'2021-08-29 23:48:32',NULL,'2021-08-29 23:48:32',NULL,1,111),(206,NULL,'2021-08-29 23:48:32',NULL,'2021-08-29 23:48:32',NULL,1,110),(207,NULL,'2021-08-29 23:48:32',NULL,'2021-08-29 23:48:32',NULL,1,109),(208,NULL,'2021-08-29 23:48:32',NULL,'2021-08-29 23:48:32',NULL,1,108),(209,NULL,'2021-08-29 23:48:32',NULL,'2021-08-29 23:48:32',NULL,1,107),(210,NULL,'2021-08-29 23:48:32',NULL,'2021-08-29 23:48:32',NULL,1,106),(211,NULL,'2021-08-29 23:48:32',NULL,'2021-08-29 23:48:32',NULL,1,105),(212,NULL,'2021-08-29 23:48:32',NULL,'2021-08-29 23:48:32',NULL,1,104),(213,NULL,'2021-08-29 23:48:32',NULL,'2021-08-29 23:48:32',NULL,1,103),(214,NULL,'2021-08-31 14:08:41',NULL,'2021-08-31 14:08:41',NULL,3,37),(215,NULL,'2021-08-31 14:08:41',NULL,'2021-08-31 14:08:41',NULL,3,33),(216,NULL,'2021-08-31 14:08:41',NULL,'2021-08-31 14:08:41',NULL,3,32),(217,NULL,'2021-08-31 14:08:41',NULL,'2021-08-31 14:08:41',NULL,3,30),(218,NULL,'2021-08-31 14:08:41',NULL,'2021-08-31 14:08:41',NULL,3,29);
/*!40000 ALTER TABLE `message_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messenger`
--

DROP TABLE IF EXISTS `messenger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messenger` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `chat_box_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `chat_box_user_index` (`chat_box_id`,`user_id`),
  KEY `FKjd8noe30g6xsx3ej87ept4aog` (`user_id`),
  CONSTRAINT `FKbqrwd4w3otvgecdegoalj5ndp` FOREIGN KEY (`chat_box_id`) REFERENCES `chat_box` (`id`),
  CONSTRAINT `FKjd8noe30g6xsx3ej87ept4aog` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messenger`
--

LOCK TABLES `messenger` WRITE;
/*!40000 ALTER TABLE `messenger` DISABLE KEYS */;
INSERT INTO `messenger` VALUES (1,NULL,NULL,NULL,NULL,NULL,1,1),(2,NULL,NULL,NULL,NULL,NULL,1,2),(3,NULL,NULL,NULL,NULL,NULL,2,3),(4,NULL,NULL,NULL,NULL,NULL,2,1);
/*!40000 ALTER TABLE `messenger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reaction`
--

DROP TABLE IF EXISTS `reaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reaction` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reaction`
--

LOCK TABLES `reaction` WRITE;
/*!40000 ALTER TABLE `reaction` DISABLE KEYS */;
INSERT INTO `reaction` VALUES (1,NULL,NULL,NULL,NULL,'heart','Yêu thích'),(2,NULL,NULL,NULL,NULL,'haha','Haha'),(3,NULL,NULL,NULL,NULL,'wow','Ngạc nhiên'),(4,NULL,NULL,NULL,NULL,'sad','Buồn'),(5,NULL,NULL,NULL,NULL,'angry','Phẫn nộ');
/*!40000 ALTER TABLE `reaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,NULL,NULL,NULL,NULL,'admin','quản trị'),(2,NULL,NULL,NULL,NULL,'user','người dùng');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  KEY `FKt7e7djp752sqn6w22i6ocqy6q` (`role_id`),
  KEY `FKj345gk1bovqvfame88rcx7yyx` (`user_id`),
  CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKt7e7djp752sqn6w22i6ocqy6q` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,2),(2,1),(3,1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `avatar_id` bigint DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `last_online` datetime DEFAULT NULL,
  `online` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK19lflpg5seis4dwrm2lvjlxfv` (`avatar_id`),
  CONSTRAINT `FK19lflpg5seis4dwrm2lvjlxfv` FOREIGN KEY (`avatar_id`) REFERENCES `media` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'anonymousUser','2021-08-15 08:41:43','anonymousUser','2021-08-15 08:41:43','Bình Phú','Nguyễn Huy Trường','$2a$10$ezfeME7Ztk/cEXMfUfWre.8nE73B0emMZL48IREPXWOAWlGCL7mlC','truong02.bp',1,_binary '',NULL,_binary ''),(2,'anonymousUser','2021-08-15 15:40:54','son','2021-08-30 22:14:18','Nam Định','Hoàng Thái Sơn','$2a$10$WxjsZt0.eSK1f1bTdauk1u5wRyUBzXyvpATEGiYZLKrsKd8GGC.Da','son',2,_binary '','2021-08-30 00:22:51',_binary ''),(3,'anonymousUser','2021-08-16 11:03:52','thai','2021-08-31 14:08:39','Ninh Bình','Phạm Hồng Thái','$2a$10$ipzewlJ/LR5pzU8nKwBvt.cNjDdfaG87RW6Jq3etYn9lyjQjlHlY6','thai',2,_binary '','2021-08-31 14:08:37',_binary '');
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

-- Dump completed on 2021-08-31 21:15:30