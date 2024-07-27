-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 27, 2024 at 04:06 AM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

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
  `quantity` int(11) NOT NULL,
  `received_date` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`id`, `expiration_date`, `quantity`, `received_date`) VALUES
(52, '2024-07-20 08:00:00.000000', 2, '2024-07-19 08:00:00.000000'),
(53, '2024-07-21 08:00:00.000000', 2, '2024-07-20 12:00:00.000000'),
(102, '2024-07-31 08:00:00.000000', 10, '2024-07-26 08:00:00.000000'),
(152, '2024-07-30 08:00:00.000000', 20, '2024-07-26 08:00:00.000000');

-- --------------------------------------------------------

--
-- Table structure for table `inventory_seq`
--

CREATE TABLE `inventory_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `inventory_seq`
--

INSERT INTO `inventory_seq` (`next_val`) VALUES
(251);

-- --------------------------------------------------------

--
-- Table structure for table `order_item`
--

CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `order_tbl`
--

CREATE TABLE `order_tbl` (
  `id` bigint(20) NOT NULL,
  `order_date` datetime(6) NOT NULL,
  `total_amount` double NOT NULL,
  `user_auth_id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order_tbl`
--

INSERT INTO `order_tbl` (`id`, `order_date`, `total_amount`, `user_auth_id`, `email`) VALUES
(4, '2024-07-27 10:05:45.000000', 1000, 52, 'vt@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `price` double DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `sold` bigint(20) DEFAULT NULL,
  `stock` bigint(20) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `price`, `product_name`, `sold`, `stock`, `photo`) VALUES
(1, 1250, 'Mega', 15, 28, 'https://cdn.vectorstock.com/i/1000v/09/60/piggy-vector-2900960.jpg'),
(2, 1400, 'Cj Supreme Pre', 14, 25, 'https://cdn.vectorstock.com/i/1000v/09/60/piggy-vector-2900960.jpg'),
(3, 1350, 'Muscle Max', 14, 25, 'https://cdn.vectorstock.com/i/1000v/09/60/piggy-vector-2900960.jpg'),
(52, 1100, 'Express', 0, 20, 'https://cdn.vectorstock.com/i/1000v/09/60/piggy-vector-2900960.jpg'),
(102, 1000, 'Oink', 0, 0, 'https://cdn.vectorstock.com/i/1000v/09/60/piggy-vector-2900960.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `product_inventory`
--

CREATE TABLE `product_inventory` (
  `inventory_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product_inventory`
--

INSERT INTO `product_inventory` (`inventory_id`, `product_id`) VALUES
(52, 1),
(53, 2),
(102, 102),
(152, 52);

-- --------------------------------------------------------

--
-- Table structure for table `product_seq`
--

CREATE TABLE `product_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product_seq`
--

INSERT INTO `product_seq` (`next_val`) VALUES
(451);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `userauth_userdetail`
--

CREATE TABLE `userauth_userdetail` (
  `user_auth_id` bigint(20) DEFAULT NULL,
  `user_detail_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `userauth_userdetail`
--

INSERT INTO `userauth_userdetail` (`user_auth_id`, `user_detail_id`) VALUES
(1, 1),
(2, 2),
(52, 52),
(102, 53),
(152, 102),
(202, 152),
(252, 202),
(302, 252),
(403, 302);

-- --------------------------------------------------------

--
-- Table structure for table `user_auth`
--

CREATE TABLE `user_auth` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_auth`
--

INSERT INTO `user_auth` (`id`, `email`, `password`, `username`) VALUES
(1, 'rm@gmail.com', '$2a$10$4qyxm6lfqwC8khhoxeEhCuxDr6SCbsbwc.TdSEbniN6OhW/h7j4GK', 'bossing'),
(2, 'ro@gmail.com', '$2a$10$k.2PqcaZkpYEnZCtLakRP.vByUUu7fLli7W8TCECNCE.IXoSKbVPG', 'rinalyn'),
(52, 'vt@gmail.com', '$2a$10$2K5prfJSHsniprTOt/GLsuHEkLNhENeJwdWUYVFNzwSjjozqVjCXy', 'Vhenus'),
(102, 'yv@gmail.com', '$2a$10$/wFWFOMkTb5EXoyBgzU7i.Q5.5M6JK19orWpB7hcXZo4Pe./hryr2', 'Yana'),
(152, 'rm09@gmail.com', '$2a$10$oS6/VyWk8p5e6KoAUL.KLu8nlBDGVDjHJOnppHtvQvWA1iVVOzmNm', 'roniel'),
(202, 'bossing@gmail.com', '$2a$10$pi4i771KMJWCXeq9PdneMeeMFAM1.OePwEgY5az45dkZmzUA.qg/S', 'rm'),
(252, 'hi@gmail.com', '$2a$10$724emhQ6ekFktTwZEN9N5uKwp0VTmn.2ANxwiElQKpjRDpuVZX5GW', 'hello'),
(302, 'fa@gmail.com', '$2a$10$/4D0.rlLqNaWesonve/FvOow3WpxJTt1wYCoTbkTgOyCi9uLOlqh6', 'franze'),
(403, 'hello@gmail.com', '$2a$10$qqMf58ztcr7qbmEUST42YOAdg05GrOUfganhjlI2p8NGfXOpE.8jy', 'hii');

-- --------------------------------------------------------

--
-- Table structure for table `user_auth_seq`
--

CREATE TABLE `user_auth_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_auth_seq`
--

INSERT INTO `user_auth_seq` (`next_val`) VALUES
(501);

-- --------------------------------------------------------

--
-- Table structure for table `user_detail`
--

CREATE TABLE `user_detail` (
  `user_detail_id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_detail`
--

INSERT INTO `user_detail` (`user_detail_id`, `address`, `created_at`, `phone`, `gender`) VALUES
(1, 'Balayan', '2024-07-22 08:00:00.000000', '09123456789', 'male'),
(2, 'Calaca, city', '2024-07-19 13:40:31.562566', '09871234567', 'female'),
(52, 'Calaca City, Batangas', '2024-07-23 04:24:48.000000', '01234567890', 'female'),
(53, 'Calaca City', '2024-07-23 04:27:04.000000', '09876543210', 'female'),
(102, 'balayan', '2024-07-26 00:50:49.000000', '65654654165', 'male'),
(152, 'batngas', '2024-07-26 01:21:13.000000', '56464123156', 'male'),
(202, '12345678', '2024-07-26 01:32:13.000000', '78945612307', 'male'),
(252, 'BA', '2024-07-26 01:49:44.000000', '65416846484', 'male'),
(302, 'ba', '2024-07-26 04:05:53.000000', '21231564687', 'male');

-- --------------------------------------------------------

--
-- Table structure for table `user_detail_seq`
--

CREATE TABLE `user_detail_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_detail_seq`
--

INSERT INTO `user_detail_seq` (`next_val`) VALUES
(401);

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `used_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`used_id`, `role_id`) VALUES
(1, 1),
(2, 2),
(52, 2),
(102, 2),
(152, 2),
(202, 2),
(252, 1),
(302, 2),
(403, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_item`
--
ALTER TABLE `order_item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKoobh6e7a2npf6n1lwvukx0c83` (`order_id`),
  ADD KEY `FK551losx9j75ss5d6bfsqvijna` (`product_id`);

--
-- Indexes for table `order_tbl`
--
ALTER TABLE `order_tbl`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKk8nvoweyg8tpt6juhpwlkh1d1` (`user_auth_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_inventory`
--
ALTER TABLE `product_inventory`
  ADD PRIMARY KEY (`inventory_id`,`product_id`),
  ADD KEY `FK8echmjvoete36r6q97dr6pl7j` (`product_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `userauth_userdetail`
--
ALTER TABLE `userauth_userdetail`
  ADD PRIMARY KEY (`user_detail_id`),
  ADD UNIQUE KEY `UKiwmgvjt76epi7vu14r8rje5qv` (`user_auth_id`);

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
  ADD PRIMARY KEY (`user_detail_id`);

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
-- AUTO_INCREMENT for table `order_item`
--
ALTER TABLE `order_item`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `order_tbl`
--
ALTER TABLE `order_tbl`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `order_item`
--
ALTER TABLE `order_item`
  ADD CONSTRAINT `FK551losx9j75ss5d6bfsqvijna` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKoobh6e7a2npf6n1lwvukx0c83` FOREIGN KEY (`order_id`) REFERENCES `order_tbl` (`id`);

--
-- Constraints for table `order_tbl`
--
ALTER TABLE `order_tbl`
  ADD CONSTRAINT `FKk8nvoweyg8tpt6juhpwlkh1d1` FOREIGN KEY (`user_auth_id`) REFERENCES `user_auth` (`id`);

--
-- Constraints for table `product_inventory`
--
ALTER TABLE `product_inventory`
  ADD CONSTRAINT `FK8echmjvoete36r6q97dr6pl7j` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKrp29y97hpxviprydwuh5ndrc8` FOREIGN KEY (`inventory_id`) REFERENCES `inventory` (`id`);

--
-- Constraints for table `userauth_userdetail`
--
ALTER TABLE `userauth_userdetail`
  ADD CONSTRAINT `FK2w9echhi8fc2f7vxcrjd6oh1s` FOREIGN KEY (`user_detail_id`) REFERENCES `user_detail` (`user_detail_id`),
  ADD CONSTRAINT `FKid68mfvcxg4lxjbua77p66ixb` FOREIGN KEY (`user_auth_id`) REFERENCES `user_auth` (`id`);

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
