-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: school_project
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `assignments`
--

DROP TABLE IF EXISTS `assignments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignments` (
  `COURSE_ID` int(11) NOT NULL,
  `ASSIGNMENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(30) DEFAULT NULL,
  `DESCRIPTION` varchar(30) DEFAULT NULL,
  `ORAL_MARK` int(11) DEFAULT NULL,
  `TOTAL_MARK` int(11) DEFAULT NULL,
  `SUB_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ASSIGNMENT_ID`),
  UNIQUE KEY `COURSE_ID` (`COURSE_ID`,`ASSIGNMENT_ID`),
  CONSTRAINT `assignments_ibfk_1` FOREIGN KEY (`COURSE_ID`) REFERENCES `courses` (`COURSE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignments`
--

LOCK TABLES `assignments` WRITE;
/*!40000 ALTER TABLE `assignments` DISABLE KEYS */;
INSERT INTO `assignments` VALUES (1,1,'Java Student Project','Individual Full Time',20,100,'2019-12-14 23:59:59'),(2,2,'Java Student Project','Individual Part Time',25,100,'2020-01-02 23:59:59'),(3,3,'C# Student Project','Individual Full Time',20,100,'2019-12-14 23:59:59'),(4,4,'C# Student Project','Individual Part Time',25,100,'2020-01-02 23:59:59'),(1,5,'Front End Development','Individual Full Time',25,100,'2020-02-20 23:59:59'),(2,6,'Web Development','Individual Part Time',30,100,'2020-05-01 23:59:59'),(3,7,'MySQL','Individual Full Time',25,100,'2020-02-20 23:59:59'),(4,8,'SQL Databases','Individual Part Time',30,100,'2020-05-01 23:59:59');
/*!40000 ALTER TABLE `assignments` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-27 21:44:08
