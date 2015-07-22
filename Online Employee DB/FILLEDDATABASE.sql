-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: employeedatabase
-- ------------------------------------------------------
-- Server version	5.6.25-log

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
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_detail_id` int(11) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `middle_initial` varchar(1) DEFAULT NULL,
  `level` varchar(45) DEFAULT NULL,
  `work_force` varchar(45) DEFAULT NULL,
  `enterprise_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_employee_employee_detail_idx` (`employee_detail_id`),
  CONSTRAINT `fk_employee_employee_detail` FOREIGN KEY (`employee_detail_id`) REFERENCES `employee_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,1,'Arnolds','Skuja','M','TEST1','TEST1','12564'),(2,2,'Toms','Skudra','H','TEST2','TEST2','12573'),(3,3,'Aina','Zarina','V','TEST3','TEST3','17458'),(4,4,'Antons','Galva','P','TEST4','TEST4','23456'),(5,5,'Jurijs','Lavendels','O','TEST5','TEST5','23564');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_detail`
--

DROP TABLE IF EXISTS `employee_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_detail`
--

LOCK TABLES `employee_detail` WRITE;
/*!40000 ALTER TABLE `employee_detail` DISABLE KEYS */;
INSERT INTO `employee_detail` VALUES (1),(2),(3),(4),(5);
/*!40000 ALTER TABLE `employee_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_project_detail`
--

DROP TABLE IF EXISTS `employee_project_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_project_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_detail_id` int(11) NOT NULL,
  `project_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_employee_project_detail_employee_detail1_idx` (`employee_detail_id`),
  CONSTRAINT `fk_employee_project_detail_employee_detail1` FOREIGN KEY (`employee_detail_id`) REFERENCES `employee_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_project_detail`
--

LOCK TABLES `employee_project_detail` WRITE;
/*!40000 ALTER TABLE `employee_project_detail` DISABLE KEYS */;
INSERT INTO `employee_project_detail` VALUES (1,1,1),(2,2,1),(3,3,1),(4,1,4),(5,2,2),(6,3,2),(7,4,7),(8,4,8),(9,5,9),(10,5,10);
/*!40000 ALTER TABLE `employee_project_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_skill`
--

DROP TABLE IF EXISTS `employee_skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_skill` (
  `id` int(11) NOT NULL,
  `employee_detail_id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_employee_skill_employee_detail1_idx` (`employee_detail_id`),
  CONSTRAINT `fk_employee_skill_employee_detail1` FOREIGN KEY (`employee_detail_id`) REFERENCES `employee_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_skill`
--

LOCK TABLES `employee_skill` WRITE;
/*!40000 ALTER TABLE `employee_skill` DISABLE KEYS */;
INSERT INTO `employee_skill` VALUES (1,1,'programming','Ability to write code in Java',8),(2,2,'programming','Ability to write code in C++',7),(3,3,'project design','Ability to design projects',9),(4,4,'testing','Ability to test code in Java',6),(5,1,'project design','Ability to design projects ',10),(6,2,'testing','Ability to test code in Java',5),(7,3,'testing','Ability to test code in C++',4),(8,5,'team leader','Ability to lead a team in a project',8),(9,5,'programming','Ability to code in Java',9),(10,1,'testing','Ability to test code in Java',10);
/*!40000 ALTER TABLE `employee_skill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `client` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'Apmacibu sistema','Apmacibu sistema RTU studentiem','RTU'),(2,'Lidostas sitema','TEST1','TEST1'),(3,'Policijas sazinu sistema','TEST2','TEST2'),(4,'Restoranu sistema','TEST3','TEST3'),(5,'Apmacibu sistema','TEST4','TEST4'),(6,'Studiju sistema','TEST5','TEST5'),(7,'TEST1','TEST6','TEST6'),(8,'TEST2','TEST7','TEST7'),(9,'TEST3','TEST8','TEST8'),(10,'TEST4','TEST9','TEST9');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_role`
--

DROP TABLE IF EXISTS `project_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_project_detail_id` int(11) NOT NULL,
  `role` varchar(45) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_project_role_employee_project_detail1_idx` (`employee_project_detail_id`),
  CONSTRAINT `fk_project_role_employee_project_detail1` FOREIGN KEY (`employee_project_detail_id`) REFERENCES `employee_project_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_role`
--

LOCK TABLES `project_role` WRITE;
/*!40000 ALTER TABLE `project_role` DISABLE KEYS */;
INSERT INTO `project_role` VALUES (1,1,'programmer','2013-05-03','2015-05-03'),(2,1,'tester','2013-07-03','2015-02-02'),(3,1,'designer','2014-02-02','2015-07-07'),(4,2,'analyst','2013-07-03','2015-05-03'),(5,2,'tester','2013-07-03','2015-05-03'),(6,3,'tester','2013-07-03','2015-05-03'),(7,4,'programmer','2013-05-03','2015-07-07'),(8,5,'programmer','2013-05-03','2015-07-07'),(9,6,'programmer','2014-02-02','2015-07-07'),(10,7,'tester','2014-02-02','2015-07-07');
/*!40000 ALTER TABLE `project_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-22 11:35:26
