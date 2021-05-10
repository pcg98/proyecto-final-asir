-- MariaDB dump 10.17  Distrib 10.4.6-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: deportes
-- ------------------------------------------------------
-- Server version	10.4.6-MariaDB

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
-- Table structure for table `articulos`
--

DROP TABLE IF EXISTS `articulos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articulos` (
  `CodArt` varchar(4) COLLATE utf8_spanish2_ci NOT NULL,
  `Descripcion` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `PrecioCompra` decimal(6,2) NOT NULL,
  `PVP` decimal(6,2) NOT NULL,
  `FechaCompra` date NOT NULL,
  `Stock` int(11) NOT NULL,
  `Proveedor` varchar(3) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`CodArt`),
  KEY `Proveedor` (`Proveedor`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulos`
--

LOCK TABLES `articulos` WRITE;
/*!40000 ALTER TABLE `articulos` DISABLE KEYS */;
INSERT INTO `articulos` VALUES ('H003','Raqueta Head Graphene Xt Prestige',56.00,205.00,'2018-12-12',2,'HEA'),('A020','Chanclas Hombre Adilette ',3.95,24.95,'2017-07-15',6,'ADI'),('H002','Pala Padel Head Evolution ',19.00,55.95,'2018-12-12',6,'HEA'),('A103','Gorra con visera ',2.25,14.75,'2017-03-15',12,'ADI'),('J004','Camiseta Termica Negra L',2.25,8.06,'2017-09-30',14,'JOM'),('J008','Chaqueton Snow Negro XL ',38.95,105.22,'2017-10-30',2,'JOM'),('N001','Zapatillas Running 40-43',23.50,69.95,'2017-06-15',3,'NIK'),('N010','Zapatillas Basket 44-46',21.95,65.95,'2017-06-15',2,'NIK'),('R504','Malla Gimnasio Mujer',5.50,17.90,'2017-08-15',7,'REE'),('R512','Calcetines Unisex 38-42 P',1.25,5.75,'2018-10-09',8,'REE'),('H020','Pala Padel Head Omega Pro',38.00,99.95,'2018-12-12',3,'HEA'),('M010','Mizuno Wave Unitus 4 ',38.95,65.45,'2017-11-28',0,'MIZ'),('N002','Chandal Nike Rojo',29.00,59.00,'2018-10-30',6,'NIK'),('H100','Zapatillas Head Revolt Pro',23.00,94.00,'2018-12-12',7,'HEA'),('N011','Zapatillas P',10.00,54.95,'2019-09-23',1,'NIK'),('n111','prueba fecha1',1.00,50.00,'2019-10-07',1,'NIK'),('n222','prueba fecha1',1.00,50.00,'2019-10-07',1,'NIK'),('N203','Bici nueva',57.98,202.42,'2021-03-20',2,'ADI'),('N205','Bici nueva',57.98,202.42,'2021-03-20',2,'ADI'),('N206','Bici nose',57.98,202.42,'2021-03-20',2,'ADI');
/*!40000 ALTER TABLE `articulos` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `metefecha` BEFORE INSERT ON `articulos` FOR EACH ROW begin
set new.fechacompra=curdate();
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `copia_articulos` AFTER DELETE ON `articulos` FOR EACH ROW begin
insert into articulos2 values (old.codart,old.descripcion,old.Preciocompra,old.pvp,old.fechacompra,old.stock,old.proveedor);
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
  `DNI` varchar(9) COLLATE utf8_spanish2_ci NOT NULL,
  `Nombre` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `FechaNac` date NOT NULL,
  `Tfno` varchar(9) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`DNI`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES ('24783650Q','Jesus Martinez','1982-10-16','123456789'),('26489961J','Felix Avila','2000-05-25','619625586'),('26567981F','Mario Lopez','1990-11-01','953758722'),('26788741S','Ana Rodriguez','1970-10-16','619500487'),('27701802L','Carmen Martinez','1985-07-11','619669335'),('28664092D','Patricia Fernandez','2000-09-19','602557441'),('29854789A','Carlos Martinez','2001-04-18','625879954'),('33874995Z','Violeta Ruiz','2000-09-03','687558905'),('22456008S','Cristobal Cano','1970-10-15','666789003');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedores`
--

DROP TABLE IF EXISTS `proveedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedores` (
  `Codigo` varchar(3) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `descripcion` varchar(25) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `tlfn` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `web` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`Codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16 COLLATE=utf16_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedores`
--

LOCK TABLES `proveedores` WRITE;
/*!40000 ALTER TABLE `proveedores` DISABLE KEYS */;
INSERT INTO `proveedores` VALUES ('HEA','\0H\0e\0a\0d','\06\06\02\02\08\05\03\09\00','\0w\0w\0w\0.\0h\0e\0a\0d\0.\0e\0s'),('ADI','\0A\0d\0i\0d\0a\0s','\06\06\08\02\08\05\03\09\01','\0w\0w\0w\0.\0a\0d\0i\0d\0a\0s\0.\0e\0s'),('JOM','\0J\0o\0m\0a','\06\06\02\02\08\05\03\09\02','\0w\0w\0w\0.\0j\0o\0m\0a\0-\0e\0s\0.\0e\0s'),('MIZ','\0M\0i\0z\0u\0n\0o','\06\06\02\02\08\05\03\09\03','\0w\0w\0w\0.\0m\0i\0z\0u\0n\0o\0.\0e\0s'),('NIK','\0N\0i\0k\0e','\06\06\02\02\08\05\03\09\04','\0w\0w\0w\0.\0n\0i\0k\0e\0.\0e\0s'),('REE','\0R\0e\0e\0b\0o\0k','\06\06\02\02\08\05\03\09\05','\0w\0w\0w\0.\0r\0e\0e\0b\0o\0k'),('PEP','PepePhone','662285399','www.pepephone.es'),('DEC',NULL,NULL,NULL);
/*!40000 ALTER TABLE `proveedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ventas` (
  `Dni` varchar(9) COLLATE utf8_spanish2_ci NOT NULL,
  `CodArt` varchar(4) COLLATE utf8_spanish2_ci NOT NULL,
  `Unidades` int(2) NOT NULL,
  `Descuento` decimal(5,2) NOT NULL,
  `FechaVenta` date NOT NULL,
  PRIMARY KEY (`Dni`,`CodArt`,`FechaVenta`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
INSERT INTO `ventas` VALUES ('26489961J','N001',1,5.00,'2017-10-15'),('33874995Z','N045',2,0.00,'2017-11-03'),('29854789A','N010',1,3.75,'2017-11-12'),('26567981F','J008',1,10.95,'2017-11-14'),('28664092D','R504',2,1.95,'2017-11-15'),('28664092D','R512',1,0.00,'2017-11-15'),('27783650Q','A025',1,0.00,'2017-11-03'),('27783650Q','J004',2,0.00,'2017-11-03'),('26788741S','N010',1,3.00,'2017-11-02'),('33874995Z','M010',2,5.25,'2018-01-12'),('26489961J','M010',2,5.00,'2018-06-14'),('26489961J','M010',3,3.00,'2018-09-24'),('26489961J','H002',1,25.00,'2018-11-13'),('33874995Z','H003',3,0.00,'2018-12-09'),('33874995Z','R512',2,0.00,'2018-10-10');
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `actualiza_stock` AFTER INSERT ON `ventas` FOR EACH ROW begin
UPDATE articulos set stock=stock-NEW.unidades where articulos.codart=NEW.codart;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-04 19:45:20
