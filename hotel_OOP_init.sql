-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: hotel_oop
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
-- Table structure for table `chitietdatphong`
--

DROP TABLE IF EXISTS `chitietdatphong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitietdatphong` (
  `madatphong` int unsigned NOT NULL,
  `maphong` int unsigned NOT NULL,
  `dongiadat` int unsigned DEFAULT NULL,
  PRIMARY KEY (`madatphong`,`maphong`),
  KEY `maphong_fk_idx` (`maphong`),
  CONSTRAINT `dondatphong_fk` FOREIGN KEY (`madatphong`) REFERENCES `dondatphong` (`madatphong`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `maphong_fk` FOREIGN KEY (`maphong`) REFERENCES `phong` (`maphong`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitietdatphong`
--

LOCK TABLES `chitietdatphong` WRITE;
/*!40000 ALTER TABLE `chitietdatphong` DISABLE KEYS */;
INSERT INTO `chitietdatphong` VALUES (16,1,499000),(17,2,499000),(18,4,499000),(20,5,499000),(20,12,499000),(21,3,599000),(21,6,599000),(22,3,599000),(23,11,699000),(24,1,499000),(25,5,499000),(26,3,599000);
/*!40000 ALTER TABLE `chitietdatphong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dichvu`
--

DROP TABLE IF EXISTS `dichvu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dichvu` (
  `madv` int(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `tendv` varchar(45) DEFAULT NULL,
  `dvtinh` char(15) DEFAULT NULL,
  `dongia` int unsigned DEFAULT NULL,
  PRIMARY KEY (`madv`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='Dịch vụ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dichvu`
--

LOCK TABLES `dichvu` WRITE;
/*!40000 ALTER TABLE `dichvu` DISABLE KEYS */;
INSERT INTO `dichvu` VALUES (00001,'Giặt là','Lượt',100000),(00004,'Bữa sáng - Thường','Suất',30000),(00005,'Bữa sáng - Cao cấp','Suất',50000);
/*!40000 ALTER TABLE `dichvu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dichvuphong`
--

DROP TABLE IF EXISTS `dichvuphong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dichvuphong` (
  `mathuephong` int(10) unsigned zerofill NOT NULL,
  `madv` int(5) unsigned zerofill NOT NULL,
  `dongia` int unsigned DEFAULT NULL,
  `ngaydung` datetime NOT NULL,
  `maphong` int DEFAULT NULL,
  `soluong` int DEFAULT NULL,
  PRIMARY KEY (`mathuephong`,`madv`,`ngaydung`),
  KEY `fk_dichvuphong_dichvu1_idx` (`madv`),
  CONSTRAINT `fk_dichvuphong_dichvu` FOREIGN KEY (`madv`) REFERENCES `dichvu` (`madv`) ON UPDATE CASCADE,
  CONSTRAINT `fk_dichvuphong_hosothuephong1` FOREIGN KEY (`mathuephong`) REFERENCES `hosothuephong` (`mathuephong`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dichvuphong`
--

LOCK TABLES `dichvuphong` WRITE;
/*!40000 ALTER TABLE `dichvuphong` DISABLE KEYS */;
INSERT INTO `dichvuphong` VALUES (0000000014,00004,30000,'2020-06-07 22:39:00',1,1),(0000000014,00004,30000,'2020-06-18 13:08:31',1,1),(0000000014,00005,50000,'2020-06-03 17:56:14',1,1),(0000000014,00005,50000,'2020-06-07 22:39:00',1,1),(0000000014,00005,50000,'2020-06-18 13:08:31',1,2),(0000000015,00001,100000,'2020-06-18 14:46:40',5,2),(0000000015,00004,30000,'2020-06-18 14:46:40',5,1),(0000000016,00001,100000,'2020-07-05 21:13:51',3,1),(0000000016,00004,30000,'2020-07-05 21:13:51',3,2),(0000000016,00005,50000,'2020-07-05 21:13:51',3,2);
/*!40000 ALTER TABLE `dichvuphong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dondatphong`
--

DROP TABLE IF EXISTS `dondatphong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dondatphong` (
  `madatphong` int unsigned NOT NULL AUTO_INCREMENT,
  `makh` int unsigned DEFAULT NULL,
  `ngaydat` datetime DEFAULT NULL,
  `ngaynhan` datetime DEFAULT NULL,
  `ngaytra` datetime DEFAULT NULL,
  `tongcoc` int unsigned DEFAULT NULL,
  `trangthai` int unsigned DEFAULT '0',
  PRIMARY KEY (`madatphong`),
  KEY `makh_fk_idx` (`makh`),
  CONSTRAINT `makh_fk` FOREIGN KEY (`makh`) REFERENCES `khachhang` (`makh`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dondatphong`
--

LOCK TABLES `dondatphong` WRITE;
/*!40000 ALTER TABLE `dondatphong` DISABLE KEYS */;
INSERT INTO `dondatphong` VALUES (16,1,'2020-04-24 00:54:13','2020-05-26 14:00:00','2020-05-28 12:00:00',499000,2),(17,1,'2020-05-27 10:15:35','2020-05-27 14:00:00','2020-05-28 12:00:00',499000,2),(18,3,'2020-05-27 22:36:04','2020-05-27 14:00:00','2020-05-28 12:00:00',499000,2),(19,4,'2020-05-28 22:15:59','2020-05-28 14:00:00','2020-05-29 12:00:00',0,3),(20,2,'2020-05-28 22:26:04','2020-05-28 14:00:00','2020-05-29 12:00:00',998000,2),(21,1,'2020-05-31 17:21:26','2020-05-31 14:00:00','2020-06-01 12:00:00',1198000,2),(22,5,'2020-06-02 21:40:35','2020-06-02 14:00:00','2020-06-03 12:00:00',599000,3),(23,1,'2020-06-03 01:27:59','2020-06-03 14:00:00','2020-06-04 12:00:00',699000,2),(24,6,'2020-06-03 17:38:47','2020-06-03 14:00:00','2020-06-04 12:00:00',499000,2),(25,1,'2020-06-18 14:46:11','2020-06-18 14:00:00','2020-06-19 12:00:00',499000,2),(26,7,'2020-07-05 21:12:49','2020-07-05 14:00:00','2020-07-09 12:00:00',599000,2);
/*!40000 ALTER TABLE `dondatphong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hosothuephong`
--

DROP TABLE IF EXISTS `hosothuephong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hosothuephong` (
  `mathuephong` int unsigned NOT NULL AUTO_INCREMENT,
  `madatphong` int unsigned DEFAULT NULL,
  `thucnhan` datetime DEFAULT NULL,
  `thuctra` datetime DEFAULT NULL,
  `tongthanhtoan` bigint unsigned DEFAULT NULL,
  PRIMARY KEY (`mathuephong`),
  KEY `dondatphong_fk_idx` (`madatphong`),
  CONSTRAINT `fk_hosothuephong_dondatphong1` FOREIGN KEY (`madatphong`) REFERENCES `dondatphong` (`madatphong`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hosothuephong`
--

LOCK TABLES `hosothuephong` WRITE;
/*!40000 ALTER TABLE `hosothuephong` DISABLE KEYS */;
INSERT INTO `hosothuephong` VALUES (7,16,'2020-05-27 00:54:19','2020-05-27 22:08:48',0),(8,17,'2020-05-27 10:15:50','2020-05-27 22:15:39',149700),(9,18,'2020-05-27 22:36:32','2020-05-27 22:38:41',0),(11,20,'2020-05-28 22:26:17','2020-05-31 17:19:36',2295400),(12,21,'2020-05-31 17:21:44','2020-06-02 21:40:43',1198000),(13,23,'2020-06-03 01:40:54','2020-06-03 17:32:27',209700),(14,24,'2020-06-03 17:39:31','2020-06-18 14:44:34',7135700),(15,25,'2020-06-18 14:46:21','2020-07-05 21:48:14',8214000),(16,26,'2020-07-05 21:13:14',NULL,NULL);
/*!40000 ALTER TABLE `hosothuephong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khachhang`
--

DROP TABLE IF EXISTS `khachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khachhang` (
  `makh` int unsigned NOT NULL AUTO_INCREMENT,
  `hotenkh` varchar(45) DEFAULT NULL,
  `gioitinh` tinyint DEFAULT '0',
  `cmnd` char(12) DEFAULT NULL,
  `diachi` varchar(100) DEFAULT NULL,
  `sdt` char(10) DEFAULT NULL,
  PRIMARY KEY (`makh`),
  UNIQUE KEY `cmnd_UNIQUE` (`cmnd`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
INSERT INTO `khachhang` VALUES (1,'Nguyễn Việt Khôi',0,'123456789000','Hà nội','0944121847'),(2,'Someone',0,'123123123123','Vi?t Nam','Vi?t Nam'),(3,'someone that i used to know',1,'123456321654','sure kèo việt nam','0321456123'),(4,'aaaaaaaaaaaa',0,'321123321123','zzzzzzzzzzzzz','0215642313'),(5,'hatohata',0,'123456123456','fsdfsfasf','3214563210'),(6,'john cena',0,'111122223333','My','0989876514'),(7,'Nguyễn Trung Đức',0,'111122224444','Nam Định ','0848484848');
/*!40000 ALTER TABLE `khachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loaiphong`
--

DROP TABLE IF EXISTS `loaiphong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loaiphong` (
  `maloaiphong` int unsigned NOT NULL AUTO_INCREMENT,
  `tenloaiphong` varchar(45) DEFAULT NULL,
  `dongia` int unsigned DEFAULT NULL,
  PRIMARY KEY (`maloaiphong`),
  UNIQUE KEY `tenloaiphong_UNIQUE` (`tenloaiphong`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaiphong`
--

LOCK TABLES `loaiphong` WRITE;
/*!40000 ALTER TABLE `loaiphong` DISABLE KEYS */;
INSERT INTO `loaiphong` VALUES (1,'Single',499000),(2,'Double',599000),(3,'Triple',799000),(4,'Twin',699000);
/*!40000 ALTER TABLE `loaiphong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phong`
--

DROP TABLE IF EXISTS `phong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phong` (
  `maphong` int unsigned NOT NULL AUTO_INCREMENT,
  `tenphong` varchar(45) DEFAULT NULL,
  `maloaiphong` int unsigned DEFAULT NULL,
  `mahientai` int DEFAULT '0',
  `tinhtrang` tinyint DEFAULT '1',
  PRIMARY KEY (`maphong`),
  UNIQUE KEY `tenphong_UNIQUE` (`tenphong`),
  KEY `loaiphong_fk_idx` (`maloaiphong`),
  CONSTRAINT `loaiphong_fk` FOREIGN KEY (`maloaiphong`) REFERENCES `loaiphong` (`maloaiphong`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phong`
--

LOCK TABLES `phong` WRITE;
/*!40000 ALTER TABLE `phong` DISABLE KEYS */;
INSERT INTO `phong` VALUES (1,'S1',1,0,1),(2,'S2',1,0,1),(3,'D1',2,16,1),(4,'S3',1,0,0),(5,'s44',1,0,1),(6,'D2',2,0,1),(7,'D3',2,0,1),(8,'T1',3,0,1),(9,'T2',3,0,1),(10,'T3',3,0,1),(11,'TW1',4,0,1),(12,'S5',1,0,1),(13,'T4',3,0,1),(14,'TW2',4,0,1),(15,'TW3',4,0,1),(16,'D4',2,0,1),(17,'TW4',4,0,1);
/*!40000 ALTER TABLE `phong` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-06 22:09:26
