-- phpMyAdmin SQL Dump
-- version 4.7.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jan 27, 2018 at 11:52 AM
-- Server version: 10.1.29-MariaDB
-- PHP Version: 7.2.0

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
CREATE DATABASE IF NOT EXISTS `TelMarket` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `TelMarket`;

-- --------------------------------------------------------

--
-- Table structure for table `transaction_log`
--

DROP TABLE IF EXISTS `transaction_log`;
CREATE TABLE IF NOT EXISTS `transaction_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(30) NOT NULL,
  `address_line1` varchar(40) NOT NULL,
  `address_line2` varchar(40) NOT NULL,
  `city` varchar(20) NOT NULL,
  `state` varchar(20) NOT NULL,
  `country` varchar(20) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `card_name` varchar(30) NOT NULL,
  `PAN` varchar(19) NOT NULL,
  `expiration_date` varchar(4) NOT NULL,
  `amount` varchar(16) NOT NULL,
  `transaction_date_and_time` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction_log`
--

INSERT INTO `transaction_log` (`id`, `full_name`, `address_line1`, `address_line2`, `city`, `state`, `country`, `phone`, `card_name`, `PAN`, `expiration_date`, `amount`, `transaction_date_and_time`) VALUES
(1, 'Terrence Munyunguma', '17 Harold Gordon Drive', 'Number 1', 'Hwange', 'MatNorth', 'Zimbabwe', '0773638697', 'Terrence Takunda Munyunguma', '4111111111111111', '1220', '122.37', '0127120249'),
(2, 'Takunda Munyunguma', '16503', 'Johannesburg', 'Norton', 'MatNorth', 'Zimbabwe', '07714994446', 'Terrence Takunda Munyunguma', '4874971111111115', '1120', '865.78', '2018/01/27/12:25:24');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`) VALUES
(1, 'Terrence', 'terrycorp@yahoo.com', 'admin');


--
-- Metadata
--
USE `phpmyadmin`;

--
-- Metadata for table transaction_log
--

--
-- Metadata for table users
--

--
-- Metadata for database TelMarket
--
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
