-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: logging
-- ------------------------------------------------------
-- Server version	5.6.24-log

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
-- Table structure for table `logs`
--

DROP TABLE IF EXISTS `logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logs` (
  `DATED` date NOT NULL,
  `LOGGER` varchar(50) NOT NULL,
  `LEVEL` varchar(10) NOT NULL,
  `MESSAGE` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logs`
--

LOCK TABLES `logs` WRITE;
/*!40000 ALTER TABLE `logs` DISABLE KEYS */;
INSERT INTO `logs` VALUES ('2015-05-25','servlet.Servlet','INFO','init finished'),('2015-05-25','servlet.Servlet','INFO','doGet'),('2015-05-25','servlet.Servlet','INFO','Token TN11EN'),('2015-05-25','servlet.Servlet','INFO','GET - Response status: 304'),('2015-05-25','servlet.Servlet','INFO','doGet'),('2015-05-25','servlet.Servlet','INFO','Token TN11EN'),('2015-05-25','servlet.Servlet','INFO','GET - Response status: 304'),('2015-05-25','servlet.Servlet','INFO','doGet'),('2015-05-25','servlet.Servlet','INFO','Token TN11EN'),('2015-05-25','servlet.Servlet','INFO','GET - Response status: 304'),('2015-05-25','servlet.Servlet','INFO','doGet'),('2015-05-25','servlet.Servlet','INFO','Token TN11EN'),('2015-05-25','servlet.Servlet','INFO','GET - Response status: 304'),('2015-05-25','servlet.Servlet','INFO','doGet'),('2015-05-25','servlet.Servlet','INFO','Token TN11EN'),('2015-05-25','servlet.Servlet','INFO','GET - Response status: 304'),('2015-05-25','servlet.Servlet','INFO','doGet'),('2015-05-25','servlet.Servlet','INFO','Token TN11EN'),('2015-05-25','servlet.Servlet','INFO','GET - Response status: 304'),('2015-05-25','servlet.Servlet','INFO','doPost'),('2015-05-25','servlet.Servlet','INFO','{\"description\":\"kljsdf\",\"user\":\"skldjfn\",\"id\":\"0\",\"delete\":false}'),('2015-05-25','servlet.Servlet','INFO','{\"id\":\"1\",\"description\":\"kljsdf\",\"user\":\"skldjfn\",\"delete\":\"false\"}'),('2015-05-25','servlet.Servlet','INFO','doPost finished'),('2015-05-25','servlet.Servlet','INFO','doGet'),('2015-05-25','servlet.Servlet','INFO','Token TN11EN'),('2015-05-25','servlet.Servlet','INFO','doGet'),('2015-05-25','servlet.Servlet','INFO','Token TN19EN'),('2015-05-25','servlet.Servlet','INFO','GET - Response status: 304'),('2015-05-25','servlet.Servlet','INFO','doGet'),('2015-05-25','servlet.Servlet','INFO','Token TN11EN'),('2015-05-25','servlet.Servlet','INFO','doGet'),('2015-05-25','servlet.Servlet','INFO','Token TN19EN'),('2015-05-25','servlet.Servlet','INFO','GET - Response status: 304'),('2015-05-25','servlet.Servlet','INFO','doGet'),('2015-05-25','servlet.Servlet','INFO','Token TN19EN'),('2015-05-25','servlet.Servlet','INFO','GET - Response status: 304'),('2015-05-25','servlet.Servlet','INFO','doPost'),('2015-05-25','servlet.Servlet','INFO','{\"description\":\"asjdnf\",\"user\":\"jnadsf\",\"id\":\"0\",\"delete\":false}'),('2015-05-25','servlet.Servlet','INFO','{\"id\":\"2\",\"description\":\"asjdnf\",\"user\":\"jnadsf\",\"delete\":\"false\"}'),('2015-05-25','servlet.Servlet','INFO','doPost finished'),('2015-05-25','servlet.Servlet','INFO','doGet'),('2015-05-25','servlet.Servlet','INFO','Token TN19EN'),('2015-05-25','servlet.Servlet','INFO','doGet'),('2015-05-25','servlet.Servlet','INFO','doGet'),('2015-05-25','servlet.Servlet','INFO','Token TN27EN'),('2015-05-25','servlet.Servlet','INFO','Token TN19EN'),('2015-05-25','servlet.Servlet','INFO','GET - Response status: 304'),('2015-05-25','servlet.Servlet','INFO','doGet'),('2015-05-25','servlet.Servlet','INFO','Token TN27EN'),('2015-05-25','servlet.Servlet','INFO','GET - Response status: 304'),('2015-05-25','servlet.Servlet','INFO','doPost'),('2015-05-25','servlet.Servlet','INFO','{\"description\":\"njxyj&\",\"user\":\"skldjfn\",\"id\":\"0\",\"delete\":false}'),('2015-05-25','servlet.Servlet','INFO','{\"id\":\"3\",\"description\":\"njxyj&\",\"user\":\"skldjfn\",\"delete\":\"false\"}'),('2015-05-25','servlet.Servlet','INFO','doPost finished'),('2015-05-25','servlet.Servlet','INFO','doGet'),('2015-05-25','servlet.Servlet','INFO','Token TN27EN'),('2015-05-25','servlet.Servlet','INFO','doGet'),('2015-05-25','servlet.Servlet','INFO','Token TN27EN'),('2015-05-25','servlet.Servlet','INFO','doPost'),('2015-05-25','servlet.Servlet','INFO','{\"description\":\"lf)\",\"user\":\"jnadsf\",\"id\":\"0\",\"delete\":false}'),('2015-05-25','servlet.Servlet','INFO','{\"id\":\"4\",\"description\":\"lf)\",\"user\":\"jnadsf\",\"delete\":\"false\"}'),('2015-05-25','servlet.Servlet','INFO','doPost finished'),('2015-05-25','servlet.Servlet','INFO','doGet'),('2015-05-25','servlet.Servlet','INFO','Token TN35EN'),('2015-05-25','servlet.Servlet','INFO','doGet'),('2015-05-25','servlet.Servlet','INFO','doGet'),('2015-05-25','servlet.Servlet','INFO','Token TN43EN'),('2015-05-25','servlet.Servlet','INFO','Token TN35EN'),('2015-05-25','servlet.Servlet','INFO','GET - Response status: 304'),('2015-05-25','servlet.Servlet','INFO','doGet'),('2015-05-25','servlet.Servlet','INFO','Token TN43EN'),('2015-05-25','servlet.Servlet','INFO','GET - Response status: 304');
/*!40000 ALTER TABLE `logs` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-26 19:20:20
