-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 05, 2019 at 04:48 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 5.6.39

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `stock_broker_application`
--

-- --------------------------------------------------------

--
-- Table structure for table `bankdetails`
--

CREATE TABLE `bankdetails` (
  `email` varchar(100) NOT NULL,
  `accountno` int(11) NOT NULL,
  `routingno` bigint(20) DEFAULT NULL,
  `balance` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bankdetails`
--

INSERT INTO `bankdetails` (`email`, `accountno`, `routingno`, `balance`) VALUES
('kunamtheja@gmail.com', -1, -1, NULL),
('kunamtheja@gmail.com', 11111, 2222222, NULL),
('saahith', 1, 2, NULL),
('saahith12', 54, 5454, NULL),
('saahith12', 12222, 12344444, NULL),
('saahith12', 777777, 888888, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(40),
(40);

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE `schedule` (
  `email` varchar(255) NOT NULL,
  `accountno` bigint(20) DEFAULT NULL,
  `buyorsell` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `recurringvalue` varchar(255) DEFAULT NULL,
  `ticekrsymbol` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `stocks`
--

CREATE TABLE `stocks` (
  `tickerSymbol` varchar(100) NOT NULL,
  `stockName` varchar(100) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `stockTableId` int(11) NOT NULL,
  `currentprice` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stocks`
--

INSERT INTO `stocks` (`tickerSymbol`, `stockName`, `quantity`, `email`, `stockTableId`, `currentprice`) VALUES
('abc', 'sony', 5, 'kunamtheja@gmail.com', 8, NULL),
('abc', 'sony', 5, 'saahith12', 11, NULL),
('ads', 'sony', 5, 'saahith', 14, NULL),
('def', 'dddd', 3, 'saahith', 12, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `firstname` varchar(100) DEFAULT NULL,
  `lastname` varchar(100) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  `question` varchar(100) DEFAULT NULL,
  `answer` varchar(100) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`firstname`, `lastname`, `password`, `question`, `answer`, `id`, `email`) VALUES
('saahith11111', 'hegde222222', '12345', 'what is your pet', 'nll', 2, 'saahith12'),
(NULL, NULL, '1234', NULL, NULL, 3, 'saahith123'),
('Theja', 'Kunam', 'password', NULL, NULL, 20, 'javainuse@123'),
('rrrrr', 'ttttttt', 'password', NULL, NULL, 27, 'javainuse'),
('theja', 'kunam', '232321', NULL, NULL, 36, 'tk123'),
('theja', 'kkkkk', '12345', '', '', 38, 'kunamtheja@gmail.com'),
('ththth', 'kukuku', '112233', 'what is your pet', 'name', 39, 'tk1122');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bankdetails`
--
ALTER TABLE `bankdetails`
  ADD PRIMARY KEY (`email`,`accountno`);

--
-- Indexes for table `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`email`);

--
-- Indexes for table `stocks`
--
ALTER TABLE `stocks`
  ADD PRIMARY KEY (`tickerSymbol`,`email`),
  ADD UNIQUE KEY `stockTableId` (`stockTableId`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `stocks`
--
ALTER TABLE `stocks`
  MODIFY `stockTableId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
