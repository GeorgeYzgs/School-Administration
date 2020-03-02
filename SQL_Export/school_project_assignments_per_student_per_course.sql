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
-- Table structure for table `assignments_per_student_per_course`
--

DROP TABLE IF EXISTS `assignments_per_student_per_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignments_per_student_per_course` (
  `ASSIGNMENT_ID` int(11) NOT NULL,
  `STUDENT_ID` int(11) NOT NULL,
  `TOTAL_MARK` int(11) DEFAULT NULL,
  `SUB_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ASSIGNMENT_ID`,`STUDENT_ID`),
  KEY `STUDENT_ID` (`STUDENT_ID`),
  CONSTRAINT `assignments_per_student_per_course_ibfk_1` FOREIGN KEY (`ASSIGNMENT_ID`) REFERENCES `assignments` (`ASSIGNMENT_ID`),
  CONSTRAINT `assignments_per_student_per_course_ibfk_2` FOREIGN KEY (`STUDENT_ID`) REFERENCES `students` (`STUDENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignments_per_student_per_course`
--

LOCK TABLES `assignments_per_student_per_course` WRITE;
/*!40000 ALTER TABLE `assignments_per_student_per_course` DISABLE KEYS */;
INSERT INTO `assignments_per_student_per_course` VALUES (1,1,67,'2019-12-14 23:59:59'),(1,2,70,'2019-12-14 23:59:59'),(1,3,92,'2019-12-14 23:59:59'),(1,4,89,'2019-12-14 23:59:59'),(2,5,60,'2020-01-02 23:59:59'),(2,6,82,'2020-01-02 23:59:59'),(2,7,76,'2020-01-02 23:59:59'),(2,8,84,'2020-01-02 23:59:59'),(3,4,74,'2019-12-14 23:59:59'),(3,8,68,'2019-12-14 23:59:59'),(3,9,52,'2019-12-14 23:59:59'),(3,10,91,'2019-12-14 23:59:59'),(4,11,81,'2020-01-02 23:59:59'),(4,12,42,'2020-01-02 23:59:59'),(4,13,83,'2020-01-02 23:59:59'),(5,1,67,'2020-01-02 23:59:59'),(5,2,71,'2020-02-20 23:59:59'),(5,3,79,'2020-02-20 23:59:59'),(5,4,82,'2020-02-20 23:59:59'),(6,5,93,'2020-02-20 23:59:59'),(6,6,75,'2020-05-01 23:59:59'),(6,7,59,'2020-05-01 23:59:59'),(6,8,68,'2020-05-01 23:59:59'),(7,4,88,'2020-02-20 23:59:59'),(7,8,73,'2020-02-20 23:59:59'),(7,9,61,'2020-02-20 23:59:59'),(7,10,70,'2020-02-20 23:59:59'),(8,11,81,'2020-05-01 23:59:59'),(8,12,78,'2020-05-01 23:59:59'),(8,13,98,'2020-05-01 23:59:59');
/*!40000 ALTER TABLE `assignments_per_student_per_course` ENABLE KEYS */;
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
