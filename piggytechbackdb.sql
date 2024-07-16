-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 16, 2024 at 04:00 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `piggytechbackdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `id` bigint(20) NOT NULL,
  `expiration_date` datetime(6) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `received_date` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`id`, `expiration_date`, `product_id`, `quantity`, `received_date`) VALUES
(1, '2025-07-15 08:00:00.000000', 1, 15, '2024-07-15 08:00:00.000000'),
(2, '2025-07-16 08:00:00.000000', 2, 10, '2024-07-16 08:00:00.000000'),
(3, '2025-07-17 08:00:00.000000', 3, 25, '2024-07-17 08:00:00.000000');

-- --------------------------------------------------------

--
-- Table structure for table `inventory_seq`
--

CREATE TABLE `inventory_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `inventory_seq`
--

INSERT INTO `inventory_seq` (`next_val`) VALUES
(101);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `price` double DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `sold` bigint(20) DEFAULT NULL,
  `stock` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `price`, `product_name`, `sold`, `stock`) VALUES
(1, 1250, 'Mega', 14, 25),
(2, 1400, 'Cj Supreme Pre', 14, 25),
(3, 1350, 'Muscle Max', 14, 25);

-- --------------------------------------------------------

--
-- Table structure for table `product_seq`
--

CREATE TABLE `product_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product_seq`
--

INSERT INTO `product_seq` (`next_val`) VALUES
(101);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE `sales` (
  `id` bigint(20) NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sales_seq`
--

CREATE TABLE `sales_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sales_seq`
--

INSERT INTO `sales_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Table structure for table `user_auth`
--

CREATE TABLE `user_auth` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_auth`
--

INSERT INTO `user_auth` (`id`, `email`, `password`, `username`) VALUES
(1, 'rm@gmail.com', '$2a$10$4qyxm6lfqwC8khhoxeEhCuxDr6SCbsbwc.TdSEbniN6OhW/h7j4GK', 'bossing'),
(2, 'ro@gmail.com', '$2a$10$k.2PqcaZkpYEnZCtLakRP.vByUUu7fLli7W8TCECNCE.IXoSKbVPG', 'rinalyn');

-- --------------------------------------------------------

--
-- Table structure for table `user_auth_seq`
--

CREATE TABLE `user_auth_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_auth_seq`
--

INSERT INTO `user_auth_seq` (`next_val`) VALUES
(101);

-- --------------------------------------------------------

--
-- Table structure for table `user_detail`
--

CREATE TABLE `user_detail` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `user_auth_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_detail`
--

INSERT INTO `user_detail` (`id`, `address`, `created_at`, `phone`, `photo`, `user_auth_id`) VALUES
(1, 'Bb', '2024-07-15 08:00:00.000000', '09', 'assets/images', 1),
(2, 'Bb', '2024-07-15 08:00:00.000000', '09', 'assets/images', 2);

-- --------------------------------------------------------

--
-- Table structure for table `user_detail_seq`
--

CREATE TABLE `user_detail_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_detail_seq`
--

INSERT INTO `user_detail_seq` (`next_val`) VALUES
(101);

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `used_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`used_id`, `role_id`) VALUES
(1, 1),
(2, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_auth`
--
ALTER TABLE `user_auth`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKj8ur28wo49fm2qryn8adaktbi` (`username`),
  ADD UNIQUE KEY `UKpou0ngjxlvv2r6yd8td3idhqk` (`email`);

--
-- Indexes for table `user_detail`
--
ALTER TABLE `user_detail`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`used_id`,`role_id`),
  ADD KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK8q6vlw6jh62y12kln2gscp2iu` FOREIGN KEY (`used_id`) REFERENCES `user_auth` (`id`),
  ADD CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
