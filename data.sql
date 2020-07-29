-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: quanlythuvien
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idgroupbook` int DEFAULT NULL,
  `idbook` text,
  `state` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `c_idx` (`idgroupbook`),
  CONSTRAINT `c` FOREIGN KEY (`idgroupbook`) REFERENCES `groupbook` (`idgroupbook`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compensate`
--

DROP TABLE IF EXISTS `compensate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compensate` (
  `idcompensate` int NOT NULL AUTO_INCREMENT,
  `idreader` int DEFAULT NULL,
  `idbook` text,
  `compensatefee` double DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `state` int DEFAULT NULL,
  PRIMARY KEY (`idcompensate`),
  KEY `f_idx` (`idreader`),
  CONSTRAINT `f` FOREIGN KEY (`idreader`) REFERENCES `reader` (`idreader`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compensate`
--

LOCK TABLES `compensate` WRITE;
/*!40000 ALTER TABLE `compensate` DISABLE KEYS */;
/*!40000 ALTER TABLE `compensate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groupbook`
--

DROP TABLE IF EXISTS `groupbook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `groupbook` (
  `idgroupbook` int NOT NULL AUTO_INCREMENT,
  `namebook` text,
  `typebook` int DEFAULT NULL,
  `authorbook` text,
  `publishdate` datetime DEFAULT NULL,
  `importdate` datetime DEFAULT NULL,
  `publisher` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `isavailable` tinyint DEFAULT NULL,
  PRIMARY KEY (`idgroupbook`),
  KEY `a_idx` (`publisher`),
  KEY `b_idx` (`typebook`),
  CONSTRAINT `a` FOREIGN KEY (`publisher`) REFERENCES `publisher` (`idpublisher`),
  CONSTRAINT `b` FOREIGN KEY (`typebook`) REFERENCES `typebook` (`idtypebook`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groupbook`
--

LOCK TABLES `groupbook` WRITE;
/*!40000 ALTER TABLE `groupbook` DISABLE KEYS */;
INSERT INTO `groupbook` VALUES (2,'1',1,NULL,NULL,NULL,1,1,NULL);
/*!40000 ALTER TABLE `groupbook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `history` (
  `idhistory` int NOT NULL,
  `idreader` int DEFAULT NULL,
  `listbook` text,
  `listtimerent` text,
  PRIMARY KEY (`idhistory`),
  KEY `e_idx` (`idreader`),
  CONSTRAINT `e` FOREIGN KEY (`idreader`) REFERENCES `reader` (`idreader`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publisher`
--

DROP TABLE IF EXISTS `publisher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publisher` (
  `idpublisher` int NOT NULL AUTO_INCREMENT,
  `name` text,
  PRIMARY KEY (`idpublisher`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publisher`
--

LOCK TABLES `publisher` WRITE;
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;
INSERT INTO `publisher` VALUES (1,'Nhã Nam');
/*!40000 ALTER TABLE `publisher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `queuerentbook`
--

DROP TABLE IF EXISTS `queuerentbook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `queuerentbook` (
  `idqueuerentbook` int NOT NULL AUTO_INCREMENT,
  `idgroupbook` int DEFAULT NULL,
  `idreader` int DEFAULT NULL,
  `point` double DEFAULT NULL,
  PRIMARY KEY (`idqueuerentbook`),
  KEY `h_idx` (`idgroupbook`),
  KEY `j_idx` (`idreader`),
  CONSTRAINT `h` FOREIGN KEY (`idgroupbook`) REFERENCES `groupbook` (`idgroupbook`),
  CONSTRAINT `j` FOREIGN KEY (`idreader`) REFERENCES `reader` (`idreader`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `queuerentbook`
--

LOCK TABLES `queuerentbook` WRITE;
/*!40000 ALTER TABLE `queuerentbook` DISABLE KEYS */;
/*!40000 ALTER TABLE `queuerentbook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reader`
--

DROP TABLE IF EXISTS `reader`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reader` (
  `idreader` int NOT NULL AUTO_INCREMENT,
  `name` text,
  `address` text,
  `dob` datetime DEFAULT NULL,
  `phone` text,
  `email` text,
  `datemember` datetime DEFAULT NULL,
  `type` int DEFAULT NULL,
  `ismarked` tinyint DEFAULT NULL,
  `point` int DEFAULT NULL,
  PRIMARY KEY (`idreader`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reader`
--

LOCK TABLES `reader` WRITE;
/*!40000 ALTER TABLE `reader` DISABLE KEYS */;
/*!40000 ALTER TABLE `reader` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regulation`
--

DROP TABLE IF EXISTS `regulation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `regulation` (
  `idregulation` int NOT NULL AUTO_INCREMENT,
  `name` text,
  `detail` int DEFAULT NULL,
  PRIMARY KEY (`idregulation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regulation`
--

LOCK TABLES `regulation` WRITE;
/*!40000 ALTER TABLE `regulation` DISABLE KEYS */;
/*!40000 ALTER TABLE `regulation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rentbook`
--

DROP TABLE IF EXISTS `rentbook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rentbook` (
  `idrentbook` int NOT NULL AUTO_INCREMENT,
  `listrentbook` text,
  `numberbooks` int DEFAULT NULL,
  `idreader` int DEFAULT NULL,
  `rentdate` datetime DEFAULT NULL,
  `returndate` datetime DEFAULT NULL,
  `rentfee` double DEFAULT NULL,
  `depositfee` double DEFAULT NULL,
  `state` tinyint DEFAULT NULL,
  PRIMARY KEY (`idrentbook`),
  KEY `d_idx` (`idreader`),
  CONSTRAINT `d` FOREIGN KEY (`idreader`) REFERENCES `reader` (`idreader`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rentbook`
--

LOCK TABLES `rentbook` WRITE;
/*!40000 ALTER TABLE `rentbook` DISABLE KEYS */;
/*!40000 ALTER TABLE `rentbook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `idreport` int NOT NULL,
  `idreader` int DEFAULT NULL,
  `idbook` text,
  `detail` text,
  `publisher` int DEFAULT NULL,
  PRIMARY KEY (`idreport`),
  KEY `g_idx` (`idreader`),
  CONSTRAINT `g` FOREIGN KEY (`idreader`) REFERENCES `reader` (`idreader`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `idstaff` int NOT NULL AUTO_INCREMENT,
  `username` text,
  `password` text,
  `email` text,
  `phone` text,
  `address` text,
  `isadmin` tinyint DEFAULT NULL,
  PRIMARY KEY (`idstaff`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `typebook`
--

DROP TABLE IF EXISTS `typebook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `typebook` (
  `idtypebook` int NOT NULL AUTO_INCREMENT,
  `nametypebook` text,
  PRIMARY KEY (`idtypebook`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typebook`
--

LOCK TABLES `typebook` WRITE;
/*!40000 ALTER TABLE `typebook` DISABLE KEYS */;
INSERT INTO `typebook` VALUES (1,'tiểu thuyết');
/*!40000 ALTER TABLE `typebook` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-29 23:04:13
