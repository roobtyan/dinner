-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: dinner
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu18.04.1

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
-- Table structure for table `dinner_table`
--

DROP TABLE IF EXISTS `dinner_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dinner_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tableName` varchar(45) DEFAULT NULL,
  `table_status` int(11) DEFAULT '0',
  `orderDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dinner_table`
--

LOCK TABLES `dinner_table` WRITE;
/*!40000 ALTER TABLE `dinner_table` DISABLE KEYS */;
INSERT INTO `dinner_table` VALUES (4,'hha ',0,NULL),(5,'迷失',0,NULL),(6,'乱舞',0,NULL);
/*!40000 ALTER TABLE `dinner_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `food` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `foodName` varchar(45) DEFAULT NULL,
  `food_type_id` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `mprice` double DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `img` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_food_type_id` (`food_type_id`),
  CONSTRAINT `fk_food_type_id` FOREIGN KEY (`food_type_id`) REFERENCES `food_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (1,'生鱼',2,20,11,'11','11'),(2,'烤肉',2,20,11,'11','11'),(3,'木瓜',1,11,10,'11','11'),(4,'炸鸡',2,20,15,'1235','e0515f1e-7451-4fe8-bc21-719ff2172f2a#下载 (1).jpeg'),(5,'馒头',1,22,33,'想','15442e0a-df8a-4b1e-b18a-895340658fc1#u=2510612972,105399836&fm=27&gp=0.jpg'),(8,'糖醋里脊',1,30,15,'好吃','96745a53-3943-4c4b-bdb6-a4455da69508#u=2827568409,2801879908&fm=27&gp=0.jpg'),(9,'炸馒头',2,33,11,NULL,'f9c4f411-839c-4848-8fe5-0267a15da815#u=3591731441,1465359234&fm=27&gp=0.jpg'),(10,'烤肉u',2,66,33,'好吃的烤肉','166c4540-ead2-4014-a1ac-6b3fccb9b90d#u=2510612972,105399836&fm=27&gp=0.jpg'),(11,'红烧排骨',2,20,15,'无','无图片'),(12,'烧麦',1,33,22,'烧麦','无图片'),(13,'最散仙',2,66,44,'哈爱吃','无图片');
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_type`
--

DROP TABLE IF EXISTS `food_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `food_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_type`
--

LOCK TABLES `food_type` WRITE;
/*!40000 ALTER TABLE `food_type` DISABLE KEYS */;
INSERT INTO `food_type` VALUES (1,'哈哈'),(2,'留点');
/*!40000 ALTER TABLE `food_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `food_id` int(11) DEFAULT NULL,
  `food_count` int(11) DEFAULT NULL,
  `order_status` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_food_id_idx` (`food_id`),
  KEY `fk_order_id_idx` (`order_id`),
  CONSTRAINT `fk_food_id` FOREIGN KEY (`food_id`) REFERENCES `food` (`id`),
  CONSTRAINT `fk_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (6,1,5,1,0),(7,1,10,1,0),(8,1,8,2,0),(9,2,3,2,0),(10,2,11,1,0),(11,2,12,1,0),(12,2,4,1,0),(13,3,8,1,0),(14,3,2,1,0),(15,3,5,5,0),(16,3,12,1,0),(17,3,1,1,0);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `table_id` int(11) DEFAULT NULL,
  `order_date` datetime DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `order_status` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_table_id` (`table_id`),
  CONSTRAINT `fk_table_id` FOREIGN KEY (`table_id`) REFERENCES `dinner_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,5,'2018-05-11 17:46:56',148,0),(2,6,'2018-05-11 17:51:53',95,0),(3,4,'2018-05-12 09:05:30',213,0);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `view_food`
--

DROP TABLE IF EXISTS `view_food`;
/*!50001 DROP VIEW IF EXISTS `view_food`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `view_food` AS SELECT 
 1 AS `id`,
 1 AS `foodName`,
 1 AS `food_type_id`,
 1 AS `price`,
 1 AS `mprice`,
 1 AS `remark`,
 1 AS `img`,
 1 AS `typeName`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `view_foods`
--

DROP TABLE IF EXISTS `view_foods`;
/*!50001 DROP VIEW IF EXISTS `view_foods`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `view_foods` AS SELECT 
 1 AS `id`,
 1 AS `table_id`,
 1 AS `tableName`,
 1 AS `order_date`,
 1 AS `food_id`,
 1 AS `foodName`,
 1 AS `count`,
 1 AS `price`,
 1 AS `total_price`,
 1 AS `order_status`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `view_orders`
--

DROP TABLE IF EXISTS `view_orders`;
/*!50001 DROP VIEW IF EXISTS `view_orders`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `view_orders` AS SELECT 
 1 AS `id`,
 1 AS `table_id`,
 1 AS `tableName`,
 1 AS `order_date`,
 1 AS `total_price`,
 1 AS `order_status`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `view_food`
--

/*!50001 DROP VIEW IF EXISTS `view_food`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_food` AS select `a`.`id` AS `id`,`a`.`foodName` AS `foodName`,`a`.`food_type_id` AS `food_type_id`,`a`.`price` AS `price`,`a`.`mprice` AS `mprice`,`a`.`remark` AS `remark`,`a`.`img` AS `img`,`b`.`typeName` AS `typeName` from (`food` `a` join `food_type` `b`) where (`a`.`food_type_id` = `b`.`id`) group by `a`.`id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_foods`
--

/*!50001 DROP VIEW IF EXISTS `view_foods`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_foods` AS select `a`.`id` AS `id`,`a`.`table_id` AS `table_id`,`d`.`tableName` AS `tableName`,`a`.`order_date` AS `order_date`,`b`.`food_id` AS `food_id`,`c`.`foodName` AS `foodName`,`b`.`food_count` AS `count`,`c`.`price` AS `price`,`a`.`total_price` AS `total_price`,`a`.`order_status` AS `order_status` from (((`orders` `a` join `order_detail` `b`) join `food` `c`) join `dinner_table` `d`) where ((`a`.`id` = `b`.`order_id`) and (`a`.`table_id` = `d`.`id`) and (`b`.`food_id` = `c`.`id`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_orders`
--

/*!50001 DROP VIEW IF EXISTS `view_orders`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_orders` AS select `a`.`id` AS `id`,`a`.`table_id` AS `table_id`,`b`.`tableName` AS `tableName`,`a`.`order_date` AS `order_date`,`a`.`total_price` AS `total_price`,`a`.`order_status` AS `order_status` from (`orders` `a` join `dinner_table` `b`) where (`a`.`table_id` = `b`.`id`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-12 10:03:25
