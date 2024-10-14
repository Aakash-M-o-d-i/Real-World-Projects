-- MariaDB dump 10.19  Distrib 10.11.7-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: logisticSys
-- ------------------------------------------------------
-- Server version	10.11.7-MariaDB-4

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Package`
--

DROP TABLE IF EXISTS `Package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Package` (
  `Package_ID` varchar(20) NOT NULL,
  `Sender_ID` varchar(20) DEFAULT NULL,
  `Receiver_ID` varchar(20) DEFAULT NULL,
  `Package_Weight` decimal(10,2) NOT NULL,
  `Package_Dimensions` varchar(100) DEFAULT NULL,
  `Package_Description` varchar(255) DEFAULT NULL,
  `Delivery_Type` enum('Standard','Express') DEFAULT NULL,
  `Origin_Location` varchar(100) NOT NULL,
  `Destination_Location` varchar(100) NOT NULL,
  `Expected_Delivery_Date` date NOT NULL,
  `Current_Status` enum('Shipped','In Transit','Delivered','Out for Delivery') DEFAULT 'Shipped',
  `Current_Location` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Package_ID`),
  KEY `Package_ibfk_1` (`Sender_ID`),
  KEY `Package_ibfk_2` (`Receiver_ID`),
  CONSTRAINT `Package_ibfk_1` FOREIGN KEY (`Sender_ID`) REFERENCES `Sender` (`Sender_ID`),
  CONSTRAINT `Package_ibfk_2` FOREIGN KEY (`Receiver_ID`) REFERENCES `Receiver` (`Receiver_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Package`
--

LOCK TABLES `Package` WRITE;
/*!40000 ALTER TABLE `Package` DISABLE KEYS */;
INSERT INTO `Package` VALUES
('6mcp8b0q','6mcp8b0q','6mcp8b0q',58.00,'10x15x17','Hello','Express','Pune','Assam','2024-12-01','Shipped','Warehouse A'),
('88tc1vgv','88tc1vgv','88tc1vgv',55.00,'5x5x5','a','Express','a','a','2024-12-01','Shipped','Warehouse A'),
('akrmps60','akrmps60','akrmps60',85.00,'10x15x17','Hello','Express','PUNE','ASSAM','2024-12-01','Shipped','Warehouse A'),
('k0rqhpd5','k0rqhpd5','k0rqhpd5',102.50,'10x25x35','Black color gaming and coding PC','Express','Pune','Assam','2024-12-01','In Transit','KOLKATA'),
('p5u63oeu','p5u63oeu','p5u63oeu',55.00,'4x1x2','a','Express','a','a','2024-12-01','In Transit','PUNE');
/*!40000 ALTER TABLE `Package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Package_History`
--

DROP TABLE IF EXISTS `Package_History`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Package_History` (
  `History_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Package_ID` varchar(100) DEFAULT NULL,
  `Status` enum('Shipped','In Transit','Out for Delivery','Delivered','Delayed') DEFAULT NULL,
  `Location` varchar(100) DEFAULT NULL,
  `Timestamp` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`History_ID`),
  KEY `Package_ID` (`Package_ID`),
  CONSTRAINT `Package_History_ibfk_1` FOREIGN KEY (`Package_ID`) REFERENCES `Package` (`Package_ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Package_History`
--

LOCK TABLES `Package_History` WRITE;
/*!40000 ALTER TABLE `Package_History` DISABLE KEYS */;
INSERT INTO `Package_History` VALUES
(1,'k0rqhpd5','In Transit','Kolkata','2024-10-13 22:56:34'),
(2,'k0rqhpd5','In Transit','Kalkato','2024-10-13 22:58:54'),
(3,'k0rqhpd5','In Transit','KALKATA','2024-10-13 23:05:11'),
(4,'k0rqhpd5','In Transit','KOLKATA','2024-10-13 23:06:31'),
(5,'p5u63oeu','In Transit','PUNE','2024-10-14 11:15:23');
/*!40000 ALTER TABLE `Package_History` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Receiver`
--

DROP TABLE IF EXISTS `Receiver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Receiver` (
  `Receiver_ID` varchar(20) NOT NULL,
  `Receiver_Name` varchar(100) NOT NULL,
  `Receiver_Address` varchar(255) NOT NULL,
  `Receiver_Phone_No` varchar(15) DEFAULT NULL,
  `Receiver_Email_Id` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`Receiver_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Receiver`
--

LOCK TABLES `Receiver` WRITE;
/*!40000 ALTER TABLE `Receiver` DISABLE KEYS */;
INSERT INTO `Receiver` VALUES
('4ggdc081','a','a','9932667263','GAneshaa@gmail.com'),
('6mcp8b0q','Aakash','Assam','1236547890','Aakash@gmail.com'),
('88tc1vgv','a','a','0123456789','@.com'),
('akrmps60','Aakash','Assam','9632587410','Aakash@gmail.com'),
('k0rqhpd5','Aakash Modi','Assam','9932667263','work.aakash.modi@gmail.com'),
('p5u63oeu','a','a','7854129630','@.com'),
('tah0sivr','a','a','a','a');
/*!40000 ALTER TABLE `Receiver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Sender`
--

DROP TABLE IF EXISTS `Sender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Sender` (
  `Sender_ID` varchar(20) NOT NULL,
  `Sender_Name` varchar(100) NOT NULL,
  `Sender_Address` varchar(255) NOT NULL,
  `Sender_Phone_No` varchar(15) DEFAULT NULL,
  `Sender_Email_Id` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`Sender_ID`),
  KEY `Sender_ID` (`Sender_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Sender`
--

LOCK TABLES `Sender` WRITE;
/*!40000 ALTER TABLE `Sender` DISABLE KEYS */;
INSERT INTO `Sender` VALUES
('4ggdc081','a','a','7811950838','aakashmodi@gmail.com'),
('6mcp8b0q','Ganesh','PUNE','7896541230','Ganesh@gmail.com'),
('88tc1vgv','a','a','7896541230','@.com'),
('akrmps60','Ganesh','PUNE','7811950836','GaneshHello@gmail.com'),
('k0rqhpd5','Ganesh','Pune,MR','7811950838','Ganesh@gmail.com'),
('p5u63oeu','GAneshH','ha','0123654789','@.com'),
('tah0sivr','a','a','a','a');
/*!40000 ALTER TABLE `Sender` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-14 11:59:46
