-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 05, 2016 at 05:51 AM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 7.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotelsbookingsapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `hotels`
--

CREATE TABLE `hotels` (
  `id` int(11) NOT NULL,
  `adminid` int(11) NOT NULL,
  `region` varchar(50) NOT NULL,
  `rating` varchar(5) NOT NULL,
  `name` varchar(50) NOT NULL,
  `tel` varchar(14) NOT NULL,
  `website` varchar(50) NOT NULL,
  `restaurent` varchar(3) NOT NULL,
  `parking` varchar(3) NOT NULL,
  `swimming_pool` varchar(3) NOT NULL,
  `celebration_hall` varchar(3) NOT NULL,
  `wifi` varchar(3) NOT NULL,
  `gym` varchar(3) NOT NULL,
  `games_room` varchar(3) NOT NULL,
  `helicopter_landing` varchar(3) NOT NULL,
  `night_price` varchar(50) NOT NULL DEFAULT 'none',
  `restau_table_price` varchar(50) NOT NULL DEFAULT 'none',
  `swimp_price` varchar(50) NOT NULL DEFAULT 'none',
  `party_hall_price` varchar(50) NOT NULL DEFAULT 'none'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hotels`
--

INSERT INTO `hotels` (`id`, `adminid`, `region`, `rating`, `name`, `tel`, `website`, `restaurent`, `parking`, `swimming_pool`, `celebration_hall`, `wifi`, `gym`, `games_room`, `helicopter_landing`, `night_price`, `restau_table_price`, `swimp_price`, `party_hall_price`) VALUES
(1, 2, 'Hammamet', '*****', 'Sultan', '71546023', 'www.marinahammaet.tn', 'No', 'Yes', 'Yes', 'No', 'No', 'Yes', 'Yes', 'No', '26.9', 'none', '5.0', 'none'),
(2, 2, 'Sousse', '***', 'Houria Palace', '71451321', 'www.houriapalace.tn', 'Yes', 'Yes', 'Yes', 'No', 'No', 'Yes', 'Yes', 'No', '22.33', '15.0', '4.5', 'none'),
(3, 2, 'Sousse', '*****', 'Marhaba Beach', '71451233', 'www.soussekantaoui.tn', 'No', 'No', 'Yes', 'No', 'No', 'Yes', 'Yes', 'No', '23.9', 'none', '6.0', 'none'),
(5, 2, 'Hammamet', '****', 'Sindbad', '71269338', 'www.trs.tn', 'Yes', 'No', 'Yes', 'Yes', 'No', 'No', 'No', 'No', '26.33', '10.0', '5.0', '966.33'),
(7, 2, 'Tunis', '*****', 'El Hana', '71456321', 'www.elhana.tn', 'Yes', 'Yes', 'Yes', 'Yes', 'Yes', 'Yes', 'Yes', 'No', '30.0', '20.0', '7.0', '1200.0');

-- --------------------------------------------------------

--
-- Table structure for table `hotelsrooms`
--

CREATE TABLE `hotelsrooms` (
  `numroom` int(11) NOT NULL,
  `idhotel` int(11) NOT NULL,
  `capacity` int(11) NOT NULL,
  `isoccupied` varchar(3) NOT NULL DEFAULT 'No'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hotelsrooms`
--

INSERT INTO `hotelsrooms` (`numroom`, `idhotel`, `capacity`, `isoccupied`) VALUES
(1, 5, 4, 'Yes'),
(2, 5, 3, 'Yes'),
(3, 2, 1, 'No'),
(3, 5, 2, 'Yes'),
(4, 5, 4, 'Yes'),
(5, 1, 2, 'Yes'),
(5, 2, 2, 'No'),
(5, 3, 4, 'No'),
(5, 5, 1, 'Yes'),
(8, 2, 1, 'No'),
(11, 1, 4, 'Yes'),
(12, 1, 1, 'Yes'),
(14, 1, 4, 'No'),
(22, 1, 1, 'Yes'),
(52, 1, 1, 'Yes');

-- --------------------------------------------------------

--
-- Table structure for table `hotels_images`
--

CREATE TABLE `hotels_images` (
  `idhotel` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `ismain` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hotels_images`
--

INSERT INTO `hotels_images` (`idhotel`, `name`, `ismain`) VALUES
(1, '20160827_074234.png', 1),
(1, '20160831_114937.png', 0),
(2, '20160831_115729.png', 1),
(2, '20160903_152636.png', 0),
(3, '20160827_074245.png', 1),
(5, '20160903_152532.png', 0),
(5, '20160903_152535.png', 0),
(7, '20160903_151642.png', 0),
(7, '20160903_152248.png', 0);

-- --------------------------------------------------------

--
-- Table structure for table `recoverycodes`
--

CREATE TABLE `recoverycodes` (
  `mail` varchar(50) NOT NULL,
  `recovcode` varchar(8) NOT NULL,
  `senttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `reserv_partyhall`
--

CREATE TABLE `reserv_partyhall` (
  `idhotel` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `arrday` date NOT NULL,
  `nbpersons` int(11) NOT NULL,
  `cost` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reserv_partyhall`
--

INSERT INTO `reserv_partyhall` (`idhotel`, `iduser`, `arrday`, `nbpersons`, `cost`) VALUES
(5, 2, '2016-09-03', 36, 966.33);

-- --------------------------------------------------------

--
-- Table structure for table `reserv_restau`
--

CREATE TABLE `reserv_restau` (
  `idhotel` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `arrtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `nbpers` varchar(20) NOT NULL,
  `cost` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reserv_restau`
--

INSERT INTO `reserv_restau` (`idhotel`, `iduser`, `arrtime`, `nbpers`, `cost`) VALUES
(2, 1, '2016-09-03 14:14:00', '10', 60),
(2, 2, '2016-08-28 11:14:00', '5', 30);

-- --------------------------------------------------------

--
-- Table structure for table `reserv_room`
--

CREATE TABLE `reserv_room` (
  `idhotel` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `numroom` int(11) NOT NULL,
  `nbadults` int(11) NOT NULL,
  `nbkids` int(11) NOT NULL,
  `arrday` date NOT NULL,
  `days` int(11) NOT NULL,
  `daysleft` int(11) NOT NULL,
  `cost` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reserv_room`
--

INSERT INTO `reserv_room` (`idhotel`, `iduser`, `numroom`, `nbadults`, `nbkids`, `arrday`, `days`, `daysleft`, `cost`) VALUES
(1, 2, 11, 1, 2, '2016-09-01', 1, 0, 53.8),
(1, 2, 12, 1, 0, '2016-09-15', 1, 1, 26.9),
(1, 2, 22, 1, 0, '2016-08-27', 1, 0, 26.9),
(1, 2, 52, 1, 0, '2016-09-01', 1, 0, 26.9),
(2, 2, 5, 0, 2, '2016-09-02', 1, 0, 26.9),
(5, 2, 1, 1, 0, '2016-09-01', 1, 0, 26.33),
(5, 2, 2, 1, 2, '2016-08-17', 1, 0, 52.66),
(5, 2, 3, 0, 2, '2016-08-02', 1, 0, 26.33),
(5, 2, 4, 1, 0, '2016-09-01', 1, 0, 26.33),
(5, 2, 5, 1, 0, '2016-08-03', 1, 0, 26.33);

--
-- Triggers `reserv_room`
--
DELIMITER $$
CREATE TRIGGER `del_reserv_room_trig` BEFORE DELETE ON `reserv_room` FOR EACH ROW UPDATE hotelsrooms HR set HR.isoccupied='No' where HR.idhotel= OLD.idhotel and HR.numroom=OLD.numroom
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `new_reserv_room_trig` AFTER INSERT ON `reserv_room` FOR EACH ROW UPDATE hotelsrooms HR set HR.isoccupied='Yes' where HR.idhotel= NEW.idhotel and HR.numroom=NEW.numroom and NEW.arrday=(CURRENT_DATE)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `reserv_swimp`
--

CREATE TABLE `reserv_swimp` (
  `id` int(11) NOT NULL,
  `idhotel` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `arrday` date NOT NULL,
  `nbadults` int(11) NOT NULL,
  `nbkids` int(11) NOT NULL,
  `cost` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reserv_swimp`
--

INSERT INTO `reserv_swimp` (`id`, `idhotel`, `iduser`, `arrday`, `nbadults`, `nbkids`, `cost`) VALUES
(1, 1, 2, '2016-09-12', 12, 0, 60);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `isadmin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `firstname`, `lastname`, `password`, `isadmin`) VALUES
(1, 'riadh.bh@yandex.com', 'John', 'Doe', '12345678', 0),
(2, 'john@doe.com', 'John', 'Doe', '12345678', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `hotels`
--
ALTER TABLE `hotels`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `hotelsrooms`
--
ALTER TABLE `hotelsrooms`
  ADD PRIMARY KEY (`numroom`,`idhotel`),
  ADD KEY `idhotel` (`idhotel`);

--
-- Indexes for table `hotels_images`
--
ALTER TABLE `hotels_images`
  ADD PRIMARY KEY (`idhotel`,`name`);

--
-- Indexes for table `recoverycodes`
--
ALTER TABLE `recoverycodes`
  ADD PRIMARY KEY (`mail`);

--
-- Indexes for table `reserv_partyhall`
--
ALTER TABLE `reserv_partyhall`
  ADD PRIMARY KEY (`idhotel`,`iduser`,`arrday`),
  ADD KEY `reserv_partyhall_ibfk_2` (`iduser`);

--
-- Indexes for table `reserv_restau`
--
ALTER TABLE `reserv_restau`
  ADD PRIMARY KEY (`idhotel`,`iduser`,`arrtime`),
  ADD KEY `reserv_restau_ibfk_2` (`iduser`);

--
-- Indexes for table `reserv_room`
--
ALTER TABLE `reserv_room`
  ADD PRIMARY KEY (`idhotel`,`iduser`,`numroom`,`arrday`),
  ADD KEY `reserv_room_ibfk_2` (`iduser`);

--
-- Indexes for table `reserv_swimp`
--
ALTER TABLE `reserv_swimp`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `hotels`
--
ALTER TABLE `hotels`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `reserv_swimp`
--
ALTER TABLE `reserv_swimp`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `hotelsrooms`
--
ALTER TABLE `hotelsrooms`
  ADD CONSTRAINT `hotelsrooms_ibfk_1` FOREIGN KEY (`idhotel`) REFERENCES `hotels` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `hotels_images`
--
ALTER TABLE `hotels_images`
  ADD CONSTRAINT `hotels_images_ibfk_1` FOREIGN KEY (`idhotel`) REFERENCES `hotels` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `recoverycodes`
--
ALTER TABLE `recoverycodes`
  ADD CONSTRAINT `recoverycodes_ibfk_1` FOREIGN KEY (`mail`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `reserv_partyhall`
--
ALTER TABLE `reserv_partyhall`
  ADD CONSTRAINT `reserv_partyhall_ibfk_1` FOREIGN KEY (`idhotel`) REFERENCES `hotels` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reserv_partyhall_ibfk_2` FOREIGN KEY (`iduser`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `reserv_restau`
--
ALTER TABLE `reserv_restau`
  ADD CONSTRAINT `reserv_restau_ibfk_1` FOREIGN KEY (`idhotel`) REFERENCES `hotels` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reserv_restau_ibfk_2` FOREIGN KEY (`iduser`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `reserv_room`
--
ALTER TABLE `reserv_room`
  ADD CONSTRAINT `reserv_room_ibfk_1` FOREIGN KEY (`idhotel`) REFERENCES `hotels` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reserv_room_ibfk_2` FOREIGN KEY (`iduser`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

DELIMITER $$
--
-- Events
--
CREATE DEFINER=`root`@`localhost` EVENT `recovcode_handler` ON SCHEDULE EVERY 1 MINUTE STARTS '2016-08-25 19:07:23' ON COMPLETION NOT PRESERVE ENABLE DO DELETE from recoverycodes WHERE senttime < ((CURRENT_TIMESTAMP) - INTERVAL 30 MINUTE)$$

CREATE DEFINER=`root`@`localhost` EVENT `resrv_restau_handler` ON SCHEDULE EVERY 1 MINUTE STARTS '2016-08-25 19:34:34' ON COMPLETION NOT PRESERVE ENABLE DO DELETE FROM reserv_restau WHERE (arrtime+INTERVAL 31 DAY)<(CURRENT_TIMESTAMP)$$

CREATE DEFINER=`root`@`localhost` EVENT `reserv_rooms_daysl_handler` ON SCHEDULE EVERY 1 DAY STARTS '2016-08-26 00:03:00' ON COMPLETION NOT PRESERVE ENABLE DO UPDATE reserv_room set daysleft=daysleft-1 where arrday<=(CURRENT_DATE)$$

CREATE DEFINER=`root`@`localhost` EVENT `reserv_room_expire_handler` ON SCHEDULE EVERY 1 DAY STARTS '2016-08-26 00:01:00' ON COMPLETION NOT PRESERVE ENABLE DO UPDATE hotelsrooms hr, reserv_room rr set hr.isoccupied='No' where hr.numroom=rr.numroom and hr.idhotel=rr.idhotel 
and rr.daysleft=0$$

CREATE DEFINER=`root`@`localhost` EVENT `reserv_swimp_handler` ON SCHEDULE EVERY 1 DAY STARTS '2016-08-26 00:02:00' ON COMPLETION NOT PRESERVE ENABLE DO DELETE FROM reserv_swimp WHERE (arrday+INTERVAL 31  DAY)<(CURRENT_DATE)$$

CREATE DEFINER=`root`@`localhost` EVENT `reserv_party_hall_handler` ON SCHEDULE EVERY 1 DAY STARTS '2016-08-26 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO DELETE FROM reserv_partyhall WHERE (arrday+INTERVAL 31 DAY)<(CURRENT_DATE)$$

CREATE DEFINER=`root`@`localhost` EVENT `reserv_room_exp_handler` ON SCHEDULE EVERY 1 DAY STARTS '2016-09-05 00:04:56' ON COMPLETION NOT PRESERVE ENABLE DO DELETE from reserv_room where ((arrday+INTERVAL(days+31) DAY)<(CURRENT_DATE))$$

DELIMITER ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
