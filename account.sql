-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 28, 2019 at 05:39 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hr-payroll`
--

-- --------------------------------------------------------




--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `name`, `password`, `role`, `username`) VALUES
(1, 'aji', '$2a$10$weV24hccvfNVrMLm7wBMMevKyV1aodUOPuGgJ/96oWEk1uXZbwq0e', 'manager', 'arkthusk'),
(3, 'jojo', '$2a$10$sONEVwO4xixSdemleE0dAedZ2h08eHepgyVR7WEkDyxEwmIrYaLoi', 'viewer', 'arkthusk3'),
(5, 'pelamar', '$2a$10$VhyTQrdLsKJavJT0/wF8YeVST3WVTlsBjAJaX2Qqa5LqJnvoVHLTO', 'pelamar', 'pelamar'),
(6, 'jimbo', '$2a$10$q2nsxH9euH3ERl1BIzWi4e332n9Spq7Zre61Ir8MePkA5.SBPHovm', 'hr', 'arkthusk5'),
(7, 'kytheon', '$2a$10$JFJw2G5y9FTVFWx0m4LH/ejcEc0Vnt6xsEGVf7MRxKHDUmZK24ErW', 'busdev', 'arkthusk2'),
(8, 'lily', '$2a$10$bQ3kVR/7qxVWF4mp4VNpjejArT8qC9KtPfsjES.ugPQxzamCv4u.G', 'admin', 'arkthusk4');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
