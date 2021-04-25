-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2021 at 07:15 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `paymentservicedb`
--

-- --------------------------------------------------------

--
-- Table structure for table `bankcard`
--

CREATE TABLE `bankcard` (
  `userId` int(11) NOT NULL,
  `nameOncard` varchar(50) NOT NULL,
  `cardNumber` varchar(30) NOT NULL,
  `expDate` date NOT NULL,
  `SecurityCode` int(10) NOT NULL,
  `postalCode` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bankcard`
--

INSERT INTO `bankcard` (`userId`, `nameOncard`, `cardNumber`, `expDate`, `SecurityCode`, `postalCode`) VALUES
(22, 'cccc', '22222', '2090-09-02', 111, 1111);

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `pid` int(11) NOT NULL,
  `cardNo` varchar(20) NOT NULL,
  `buyerId` int(10) NOT NULL,
  `productId` int(10) NOT NULL,
  `amount` double NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payments`
--

INSERT INTO `payments` (`pid`, `cardNo`, `buyerId`, `productId`, `amount`, `date`, `time`) VALUES
(2, '222', 2, 2, 200, '2021-04-24', '03:52:07');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bankcard`
--
ALTER TABLE `bankcard`
  ADD PRIMARY KEY (`userId`);

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`pid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `pid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
