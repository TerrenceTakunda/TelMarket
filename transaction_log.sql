-- phpMyAdmin SQL Dump
-- version 4.8.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 08, 2018 at 11:46 AM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `TelMarket`
--

-- --------------------------------------------------------

--
-- Table structure for table `transaction_log`
--
-- Creation: May 01, 2018 at 10:32 AM
--

DROP TABLE IF EXISTS `transaction_log`;
CREATE TABLE IF NOT EXISTS `transaction_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(50) NOT NULL,
  `address_line1` varchar(100) NOT NULL,
  `address_line2` varchar(100) DEFAULT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(50) DEFAULT NULL,
  `country` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `card_name` varchar(50) NOT NULL,
  `PAN` varchar(100) NOT NULL,
  `expiration_date` varchar(4) NOT NULL,
  `amount` varchar(16) NOT NULL,
  `transaction_date_and_time` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- RELATIONSHIPS FOR TABLE `transaction_log`:
--

--
-- Dumping data for table `transaction_log`
--

INSERT INTO `transaction_log` (`id`, `full_name`, `address_line1`, `address_line2`, `city`, `state`, `country`, `phone`, `card_name`, `PAN`, `expiration_date`, `amount`, `transaction_date_and_time`) VALUES
(1, 'Terrence Munyunguma', '17 Harold Gordon Drive', 'Number 1', 'Hwange', 'MatNorth', 'Zimbabwe', '0773638697', 'Terrence Takunda Munyunguma', '4111111111111111', '1220', '122.37', '0127120249'),
(2, 'Takunda Munyunguma', '16503', 'Johannesburg', 'Norton', 'MatNorth', 'Zimbabwe', '07714994446', 'Terrence Takunda Munyunguma', '4874971111111115', '1120', '865.78', '2018/01/27/12:25:24'),
(3, 'Taken two', 'one two three four', NULL, 'five', 'six', 'Zimbabwe', '1234566789', 'Terrence Munyunguma', 'qTDRGk23kTQ8IMIOR/7gacC+EqfZjMp4R0u/Xkgy4bk=', '1235', '656', 'Tue May 01 12:33:09 CAT 2018'),
(4, 'Terrence munyunguma', '1234 street road\r\n', NULL, 'Harare', '', 'Zimbabwe', '12345578', 'Terrence Takunda Munyunguma', 'sQf4WhMbZHm+9hFVoWVPZcC+EqfZjMp4R0u/Xkgy4bk=', '1233', '765', 'Wed May 02 09:22:42 CAT 2018'),
(5, 'Terrence munyunguma', '1234 street road\r\n', NULL, 'Harare', '', 'Zimbabwe', '12345578', 'Terrence Takunda Munyunguma', 'WgpUMrpHlRI0irq9UtvsIMC+EqfZjMp4R0u/Xkgy4bk=', '1233', '765', 'Wed May 02 09:25:20 CAT 2018'),
(6, 'terrence munyunguma', 'eyrghrr', NULL, 'hararae', 'haffd', 'Zimbabwe', '12234666', 'tareenfhf', 'Kz8RLfOSyn9/0i6f6HAN1MC+EqfZjMp4R0u/Xkgy4bk=', '1234', '765', 'Wed May 02 10:49:11 CAT 2018'),
(7, 'terrence munyunguma', 'eyrghrr', NULL, 'hararae', 'haffd', 'Zimbabwe', '12234666', 'tareenfhf', 'Kz8RLfOSyn9/0i6f6HAN1MC+EqfZjMp4R0u/Xkgy4bk=', '1234', '765', 'Wed May 02 10:52:07 CAT 2018'),
(8, 'Terrence Munyunguma', '17 Harold Gordon Drive Numner 1 things blah blah', NULL, 'Hwange', '', 'Zimbabwe', '0772351543', 'Terrence Takunda Munyunguma', 'L54qBxnelDQsdf07JRDbx8C+EqfZjMp4R0u/Xkgy4bk=', '2353', '56', 'Thu May 31 17:37:21 CAT 2018'),
(9, 'Terrence', 'Terrence', NULL, 'Terrence', '', 'Zimbabwe', '1234663', 'Terrence', 'qTDRGk23kTQ8IMIOR/7gacC+EqfZjMp4R0u/Xkgy4bk=', '1233', '424', 'Fri Jun 01 08:10:49 CAT 2018'),
(10, 'Terrence', 'Terrence', NULL, 'Terrence', '', 'Zimbabwe', '1235678', 'Terrence', 'Kz8RLfOSyn9/0i6f6HAN1MC+EqfZjMp4R0u/Xkgy4bk=', '1234', '424', 'Fri Jun 01 10:02:34 CAT 2018'),
(11, 'Terrence', 'Terrence', NULL, 'Terrence', '', 'Zimbabwe', '1235678', 'Terrence', 'Kz8RLfOSyn9/0i6f6HAN1MC+EqfZjMp4R0u/Xkgy4bk=', '1234', '424', 'Fri Jun 01 10:05:20 CAT 2018'),
(12, 'Terrence', 'Terrence', NULL, 'Terrence', '', 'Zimbabwe', '1235678', 'Terrence', 'Kz8RLfOSyn9/0i6f6HAN1MC+EqfZjMp4R0u/Xkgy4bk=', '1234', '424', 'Fri Jun 01 10:10:25 CAT 2018'),
(13, 'Terrence', 'Terrence', NULL, 'Terrence', '', 'Zimbabwe', '1235678', 'Terrence', 'qTDRGk23kTQ8IMIOR/7gacC+EqfZjMp4R0u/Xkgy4bk=', '1234', '424', 'Fri Jun 01 10:12:27 CAT 2018'),
(14, 'Terrence', 'Terrence', NULL, 'Terrence', '', 'Zimbabwe', '1235678', 'Terrence', 'WgpUMrpHlRI0irq9UtvsIMC+EqfZjMp4R0u/Xkgy4bk=', '1234', '424', 'Fri Jun 01 10:14:13 CAT 2018'),
(15, 'Terrence', 'Terrence', NULL, 'Terrence', '', 'Zimbabwe', '1235678', 'Terrence', 'Kz8RLfOSyn9/0i6f6HAN1MC+EqfZjMp4R0u/Xkgy4bk=', '1234', '424', 'Fri Jun 01 10:17:30 CAT 2018'),
(16, 'Terrence', 'Terrence', NULL, 'Terrence', '', 'Zimbabwe', '1235678', 'Terrence', 'Kz8RLfOSyn9/0i6f6HAN1MC+EqfZjMp4R0u/Xkgy4bk=', '1234', '424', 'Fri Jun 01 10:19:06 CAT 2018'),
(17, 'Terrence', 'Terrence', NULL, 'Terrence', '', 'Zimbabwe', '1235678', 'Terrence', 'Kz8RLfOSyn9/0i6f6HAN1MC+EqfZjMp4R0u/Xkgy4bk=', '1234', '424', 'Fri Jun 01 10:30:26 CAT 2018'),
(18, 'Terrence Munyunguma', '17 Harold Gordon Drive\r\n', NULL, 'Hwange', 'Matebeleland North', 'Zimbabwe', '+263773638696', 'Terrence Takunda Munyunguma', 'Kz8RLfOSyn9/0i6f6HAN1MC+EqfZjMp4R0u/Xkgy4bk=', '1220', '465', 'Wed Jun 06 11:54:14 CAT 2018');


--
-- Metadata
--
USE `phpmyadmin`;

--
-- Metadata for table transaction_log
--
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
