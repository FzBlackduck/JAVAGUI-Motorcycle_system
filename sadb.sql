-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 16, 2020 at 08:12 AM
-- Server version: 5.7.15-log
-- PHP Version: 5.6.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sadb`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `Cus_id` int(50) NOT NULL,
  `Cus_name` varchar(50) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `Phone` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`Cus_id`, `Cus_name`, `Address`, `Phone`) VALUES
(1, 'ฟร้อง', '42/1 ม. 2 ต.โรงเข้ จ. สมุทรสาคร', 630855581),
(2, 'ออม', 'fsfsdfsd', 897450903),
(3, 'เจเจ', 'ม.เกษตร', 950391249),
(4, 'กัส', 'KU', 123456789),
(5, 'luffy', 'geta311', 852286366),
(6, 'แดง', 'TEST', 123),
(7, 'TEST', 'TEST', 1150),
(8, 'เก่ง', 'สะพานลอย', 987654321),
(9, 'เฟริน', 'GETA 311', 39);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `Log_id` int(50) NOT NULL,
  `Log_name` varchar(50) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`Log_id`, `Log_name`, `Username`, `Password`, `Status`) VALUES
(1, 'Employee1', 'em', '1234', 'Admin'),
(2, 'frongz', 'fz', '1234', 'CEO'),
(3, 'Employee2', 'em2', '1234', 'Admin'),
(4, 'Employee3', 'em3', '1234', 'Admin'),
(5, 'Employee3', 'em3', '1234', 'CEO'),
(6, '', '', '', 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `Order_id` int(50) NOT NULL,
  `CategoryID` int(50) NOT NULL,
  `ProductName` varchar(50) NOT NULL,
  `Price` int(50) NOT NULL,
  `Quantity` int(50) NOT NULL,
  `Total` int(50) NOT NULL,
  `Date` date NOT NULL,
  `Cus_id` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`Order_id`, `CategoryID`, `ProductName`, `Price`, `Quantity`, `Total`, `Date`, `Cus_id`) VALUES
(11, 1, 'ไส้กรองอากาศ', 300, 1, 300, '2563-02-09', 1),
(14, 2, 'ชุดเปลือกรถ', 1500, 2, 3000, '2563-02-01', 1),
(16, 2, 'ชุดเปลือกรถ', 1500, 2, 3000, '2563-03-01', 1),
(18, 2, 'ท่อ', 1000, 1, 1000, '2563-01-01', 1),
(20, 3, 'สายไฟ', 20, 3, 60, '2563-07-01', 4),
(22, 3, 'ไฟหน้า', 80, 1, 80, '2563-05-01', 4),
(23, 1, 'หัวเทียน', 100, 3, 300, '2563-04-01', 4),
(24, 1, 'คาร์บูเรเตอร์', 1550, 33, 51150, '2563-04-01', 4),
(25, 1, 'เฟืองทด', 20, 2, 40, '2563-05-01', 1),
(38, 2, 'มือเบรก', 550, 1, 550, '2563-06-09', 1),
(40, 2, 'ปั้มเบรกบน', 500, 2, 1000, '2563-02-15', 4),
(42, 4, 'หมวกกันน็อค', 500, 2, 1000, '2563-07-08', 1),
(43, 1, 'เฟืองทด', 20, 1, 20, '2563-02-22', 4),
(48, 4, 'หมวกกันน็อค', 500, 2, 1000, '2563-02-08', 2),
(50, 4, 'หมวกกันน็อค', 500, 2, 1000, '2563-08-04', 2),
(54, 3, 'สายไฟ', 20, 1, 20, '2562-02-06', 4),
(55, 4, 'หมวกกันน็อค', 500, 2, 1000, '2562-02-06', 4),
(56, 4, 'หมวกกันน็อค', 500, 2, 1000, '2562-02-06', 2),
(57, 4, 'แฮนแต่ง', 500, 1, 500, '2562-02-06', 2),
(58, 1, 'ก้านลูกสูบ', 50, 1, 50, '2563-02-25', 4),
(59, 3, 'สายไฟ', 20, 1, 20, '2563-02-25', 4),
(61, 2, 'มือเบรก', 550, 1, 550, '2563-08-04', 1),
(64, 4, 'แฮนแต่ง', 500, 2, 1000, '2563-09-10', 1),
(68, 2, 'โช็คหลัง', 2000, 3, 6000, '2563-02-09', 4),
(71, 1, 'หัวเทียน', 100, 3, 300, '2563-12-08', 4),
(73, 4, 'หมวกกันน็อค', 500, 2, 1000, '2563-12-08', 4),
(74, 1, 'คาร์บูเรเตอร์', 1550, 33, 51150, '2563-02-26', 4),
(77, 3, 'สายไฟ', 20, 1, 20, '2563-02-26', 2),
(78, 1, 'หัวเทียน', 100, 3, 300, '2563-02-03', 4),
(79, 3, 'สายไฟ', 20, 3, 60, '2563-02-03', 4),
(81, 4, 'หมวกกันน็อค', 500, 2, 1000, '2563-02-22', 4),
(82, 1, 'หัวเทียน', 100, 3, 300, '2563-07-10', 2),
(84, 1, 'คาร์บูเรเตอร์', 1550, 33, 51150, '2563-09-14', 2),
(85, 3, 'ไฟซีนอล', 500, 2, 1000, '2563-09-14', 2),
(86, 1, 'ไส้กรองอากาศ', 300, 1, 300, '2563-05-16', 2),
(87, 2, 'โช็คหลัง', 2000, 3, 6000, '2563-05-16', 2),
(88, 1, 'หัวเทียน', 100, 3, 300, '2563-05-17', 2),
(89, 2, 'ชุดเปลือกรถ', 1500, 2, 3000, '2563-05-17', 2),
(92, 3, 'สายไฟ', 20, 1, 20, '2563-08-12', 4),
(93, 1, 'หัวเทียน', 100, 3, 300, '2563-12-01', 2),
(94, 1, 'ชุดข้างรถ 4 จังหวะ', 800, 3, 2400, '2563-12-01', 2),
(97, 3, 'สายไฟ', 20, 2, 40, '2563-12-01', 1),
(99, 3, 'สายถัก', 100, 5, 500, '2563-05-14', 1),
(100, 3, 'สวิตไฟ', 100, 4, 400, '2563-05-14', 1),
(101, 3, 'ไฟหน้า', 80, 1, 80, '2563-02-28', 3),
(102, 3, 'ไฟหลัง', 20, 1, 20, '2563-02-28', 3),
(103, 4, 'ตะกร้า', 500, 1, 500, '2563-02-28', 3),
(104, 1, 'หัวเทียน', 100, 3, 300, '2563-06-04', 4),
(105, 2, 'กันร้อนข้างท่อ', 350, 3, 1050, '2563-06-04', 4),
(106, 1, 'หัวเทียน', 100, 3, 300, '2563-01-02', 4),
(107, 1, 'ชุดข้างรถ 4 จังหวะ', 800, 1, 800, '2563-01-01', 4),
(108, 3, 'สายถัก', 100, 5, 500, '2563-01-01', 4),
(110, 1, 'ชุดข้างรถ 4 จังหวะ', 800, 2, 1600, '2563-01-03', 4),
(111, 1, 'หัวเทียน', 100, 3, 300, '2562-01-01', 1),
(112, 2, 'โช็คหลัง', 2000, 1, 2000, '2563-02-28', 5),
(115, 2, 'กันร้อนข้างท่อ', 350, 3, 1050, '2563-02-20', 5),
(116, 1, 'หัวเทียน', 100, 3, 300, '2563-02-10', 1),
(117, 2, 'ท่อ', 1000, 3, 3000, '2563-02-10', 1),
(118, 1, 'คาร์บูเรเตอร์', 1550, 33, 51150, '2563-02-02', 1),
(119, 1, 'หัวเทียน', 100, 3, 300, '2563-02-07', 2),
(121, 3, 'ไฟหน้า', 80, 4, 320, '2563-02-28', 5),
(122, 2, 'กันร้อนข้างท่อ', 350, 3, 1050, '2563-05-10', 1),
(123, 1, 'ไส้กรองอากาศ', 300, 1, 300, '2563-07-09', 1),
(124, 2, 'กันร้อนข้างท่อ', 350, 3, 1050, '2563-07-09', 1),
(125, 1, 'ไส้กรองอากาศ', 300, 1, 300, '2563-12-09', 4),
(127, 4, 'เบาะแต่ง', 1000, 1, 1000, '2563-12-09', 4),
(128, 4, 'ผ้าคลุมรถ', 300, 2, 600, '2563-12-08', 4),
(129, 4, 'แฮนแต่ง', 500, 1, 500, '2563-12-08', 4),
(130, 4, 'ตะกร้า', 500, 2, 1000, '2563-11-01', 2),
(131, 2, 'ท่อ', 1000, 1, 1000, '2563-11-01', 2),
(132, 1, 'ไส้กรองอากาศ', 300, 1, 300, '2563-06-08', 4),
(133, 1, 'หัวเทียน', 100, 3, 300, '2563-03-01', 6),
(134, 2, 'กันร้อนข้างท่อ', 350, 3, 1050, '2563-03-01', 6),
(135, 1, 'หัวเทียน', 100, 1, 100, '2563-03-02', 6),
(136, 1, 'ชุดข้างรถ 4 จังหวะ', 800, 2, 1600, '2563-03-02', 6),
(137, 4, 'ตะกร้า', 500, 1, 500, '2563-03-03', 6),
(138, 2, 'คอท่อสแตนเลส', 800, 2, 1600, '2563-03-03', 6),
(139, 1, 'ชุดข้างรถ 4 จังหวะ', 800, 1, 800, '2563-03-03', 6),
(140, 2, 'กันร้อนข้างท่อ', 350, 2, 700, '2563-03-03', 6),
(141, 2, 'คอท่อสแตนเลส', 800, 2, 1600, '2563-03-04', 6),
(142, 2, 'กันร้อนข้างท่อ', 350, 1, 350, '2563-03-04', 6),
(143, 1, 'ไส้กรองอากาศ', 300, 1, 300, '2563-03-06', 6),
(145, 2, 'แฮนด์', 150, 10, 1500, '2563-03-06', 6),
(146, 3, 'ไฟเลี่ยวแต่ง', 600, 2, 1200, '2563-03-06', 6),
(147, 3, 'สวิตไฟ', 100, 3, 300, '2563-03-06', 6),
(148, 4, 'น็อตสแตนเลส', 20, 3, 60, '2563-03-06', 6),
(149, 1, 'หัวเทียน', 100, 1, 100, '2563-03-12', 6),
(150, 1, 'ก้านลูกสูบ', 50, 2, 100, '2563-03-12', 6),
(151, 3, 'ไฟกระพริบ LED', 295, 1, 295, '2563-03-12', 6),
(153, 1, 'หัวเทียน', 100, 2, 200, '2563-03-19', 6),
(154, 1, 'น็อตเครื่อง', 20, 11, 220, '2563-03-19', 6),
(155, 3, 'ไฟกระพริบ LED', 295, 1, 295, '2563-03-19', 6),
(156, 2, 'คอท่อสแตนเลส', 800, 1, 800, '2563-03-19', 6),
(157, 2, 'กระจกส่องหลัง', 420, 2, 840, '2563-03-21', 6),
(158, 2, 'ท่อ', 1000, 1, 1000, '2563-03-21', 6),
(159, 4, 'หมวกกันน็อค', 500, 1, 500, '2563-03-21', 6),
(160, 1, 'ไส้กรองอากาศ', 300, 1, 300, '2563-03-22', 6),
(161, 1, 'ลูกสูบ', 300, 2, 600, '2563-03-22', 6),
(162, 1, 'ชุดข้างรถ 4 จังหวะ', 800, 2, 1600, '2563-03-16', 6),
(163, 1, 'ลูกสูบ', 300, 1, 300, '2563-03-16', 6),
(164, 1, 'ไส้กรองอากาศ', 300, 2, 600, '2563-03-16', 6),
(165, 1, 'ไส้กรองอากาศ', 300, 1, 300, '2563-03-26', 6),
(166, 1, 'คาร์บูเรเตอร์', 1550, 33, 51150, '2563-03-26', 6),
(167, 1, 'เฟืองทด', 20, 1, 20, '2563-03-26', 6),
(168, 2, 'กันร้อนข้างท่อ', 350, 1, 350, '2563-03-31', 6),
(169, 2, 'คอท่อสแตนเลส', 800, 1, 800, '2563-03-31', 6),
(170, 2, 'กระจกส่องหลัง', 420, 1, 420, '2563-03-31', 6),
(171, 2, 'เข็มน้ำมันแยก', 600, 1, 600, '2563-03-31', 6),
(172, 2, 'เรือนไมล์', 775, 1, 775, '2563-03-31', 6),
(173, 3, 'ไฟหน้า              ', 80, 2, 160, '2563-03-30', 6),
(174, 3, 'ไฟหลัง              ', 20, 1, 20, '2563-03-30', 6),
(175, 1, 'คาร์บูเรเตอร์', 1550, 33, 51150, '2563-03-30', 6),
(176, 1, 'ลูกสูบ 53 cm    ', 300, 1, 300, '2563-03-30', 6),
(178, 1, 'ปั้มหัวฉีด           ', 500, 1, 500, '2563-03-01', 1),
(179, 1, 'ไส้กรองอากาศ', 300, 2, 600, '2563-03-01', 1),
(180, 1, 'เฟืองทด             ', 20, 1, 20, '2563-03-01', 1),
(181, 2, 'ท่อพร้อมคอท่อ', 1000, 2, 2000, '2563-03-01', 1),
(182, 3, 'สวิตไฟ               ', 100, 1, 100, '2563-03-01', 1),
(183, 3, 'สายไฟ                            ', 20, 2, 40, '2563-03-01', 1),
(184, 3, 'สายถัก              ', 100, 5, 500, '2563-03-01', 1),
(186, 1, 'ปั้มหัวฉีด           ', 500, 1, 500, '2563-08-02', 6),
(187, 1, 'ไส้กรองอากาศ', 300, 2, 600, '2563-08-02', 6),
(188, 1, 'หัวเทียนขนาดเล็ก', 100, 3, 300, '2563-08-02', 6),
(189, 1, 'หัวเทียนขนาดเล็ก', 100, 2, 200, '2563-08-02', 6),
(190, 1, 'คาร์บูเรเตอร์       ', 1550, 33, 51150, '2563-08-02', 6),
(191, 1, 'ชุดข้างรถ 4 จังหวะ', 800, 2, 1600, '2563-08-02', 6),
(192, 1, 'ลูกสูบ 53 cm    ', 300, 1, 300, '2563-08-02', 6),
(193, 1, 'เฟืองทด             ', 20, 2, 40, '2563-08-02', 6),
(194, 1, 'โซ่ราวลิ้น                     ', 800, 1, 800, '2563-08-02', 6),
(196, 1, 'ก้านลูกสูบ          ', 50, 1, 50, '2563-08-02', 6),
(197, 1, 'น็อตเครื่อง          ', 20, 2, 40, '2563-08-02', 6),
(198, 2, 'กันร้อนข้างท่อ', 350, 1, 350, '2563-08-02', 6),
(199, 2, 'ชุดเปลือกรถ        ', 1500, 2, 3000, '2563-08-03', 6),
(200, 2, 'มือเบรก              ', 550, 2, 1100, '2563-08-03', 6),
(201, 2, 'โช็คหลัง             ', 2000, 1, 2000, '2563-08-03', 6),
(202, 2, 'เบรกหน้า           ', 800, 2, 1600, '2563-08-03', 6),
(203, 2, 'ปั้มเบรกบน         ', 500, 1, 500, '2563-08-03', 6),
(204, 2, 'แฮนด์                 ', 150, 2, 300, '2563-08-03', 6),
(205, 2, 'คอท่อสแตนเลส', 800, 1, 800, '2563-08-03', 6),
(206, 2, 'กระจกส่องหลัง', 420, 2, 840, '2563-08-03', 6),
(207, 2, 'เรือนไมล์            ', 775, 1, 775, '2563-08-03', 6),
(208, 2, 'เข็มน้ำมันแยก', 600, 2, 1200, '2563-08-03', 6),
(209, 3, 'ไฟหน้า              ', 80, 1, 80, '2563-06-03', 6),
(210, 3, 'ไฟหลัง              ', 20, 2, 40, '2563-06-03', 6),
(211, 3, 'สายไฟ                            ', 20, 1, 20, '2563-06-03', 6),
(212, 3, 'สายถัก              ', 100, 5, 500, '2563-06-03', 6),
(213, 3, 'ไฟซีนอล            ', 500, 1, 500, '2563-06-03', 6),
(214, 3, 'ไฟเลี่ยวแต่ง       ', 600, 2, 1200, '2563-06-03', 6),
(215, 3, 'สวิตไฟ               ', 100, 1, 100, '2563-06-03', 6),
(216, 3, 'ไฟแต่ง LED              ', 750, 2, 1500, '2563-06-03', 6),
(217, 3, 'ไฟกระพริบ LED', 295, 1, 295, '2563-06-03', 6),
(218, 3, 'ไฟติดข้างรถ', 50, 2, 100, '2563-06-03', 6),
(219, 3, 'ไฟดีเลย์             ', 200, 1, 200, '2563-06-03', 6),
(220, 4, 'ตะกร้า                ', 500, 2, 1000, '2563-06-03', 6),
(222, 4, 'ผ้าคลุมรถ           ', 300, 2, 600, '2563-06-03', 6),
(223, 4, 'หมวกกันน็อค     ', 500, 1, 500, '2563-06-03', 6),
(224, 4, 'แฮนแต่ง             ', 500, 2, 1000, '2563-06-03', 6),
(225, 4, 'เบาะแต่ง            ', 1000, 1, 1000, '2563-06-03', 6),
(227, 4, 'ดุมกลึง              ', 1500, 1, 1500, '2563-06-03', 6),
(228, 4, 'ล้ออลูมิเนียม      ', 500, 2, 1000, '2563-06-03', 6),
(229, 4, 'ล้อแมกซ์            ', 2000, 1, 2000, '2563-06-03', 6),
(230, 4, 'พักเท้าแต่ง        ', 60, 2, 120, '2563-06-03', 6),
(231, 4, 'น็อตสแตนเลส', 20, 1, 20, '2563-06-03', 6),
(232, 4, 'กระจกแต่ง        ', 500, 2, 1000, '2563-06-03', 6),
(233, 4, 'ตัวเกี่ยวของ       ', 50, 1, 50, '2563-06-03', 6),
(234, 3, 'สายไฟ                            ', 20, 0, 0, '2563-06-04', 6),
(235, 3, 'สายถัก              ', 100, 5, 500, '2563-06-04', 6),
(236, 3, 'ไฟติดข้างรถ        ', 50, 2, 100, '2563-06-04', 6),
(237, 1, 'เฟืองทด             ', 20, 1, 20, '2563-03-02', 7),
(238, 1, 'หัวเทียนขนาดเล็ก', 100, 2, 200, '2563-03-02', 7),
(239, 3, 'สายถัก              ', 100, 5, 500, '2563-03-02', 7),
(240, 2, 'ชุดเปลือกรถ        ', 1500, 4, 6000, '2563-03-02', 7),
(242, 1, 'คาร์บูเรเตอร์       ', 1550, 33, 51150, '2563-03-02', 8),
(243, 2, 'ท่อพร้อมคอท่อ', 1000, 1, 1000, '2563-03-02', 8),
(244, 3, 'ไฟหลัง              ', 20, 2, 40, '2563-03-02', 8),
(247, 2, 'ท่อพร้อมคอท่อ', 1000, 1, 1000, '2563-03-06', 9),
(248, 3, 'ไฟแต่ง LED              ', 750, 3, 2250, '2563-03-06', 9),
(249, 2, 'ชุดเปลือกรถ        ', 1500, 1, 1500, '2563-03-08', 7),
(250, 2, 'เบรกหน้า           ', 800, 2, 1600, '2563-03-08', 7),
(251, 2, 'คอท่อสแตนเลส', 800, 1, 800, '2563-03-08', 7),
(252, 1, 'หัวเทียนขนาดเล็ก', 100, 2, 200, '2563-03-11', 1),
(253, 1, 'ลูกสูบ 53 cm    ', 300, 1, 300, '2563-03-11', 1),
(254, 2, 'ท่อพร้อมคอท่อ', 1000, 2, 2000, '2563-03-11', 1),
(255, 2, 'แฮนด์                 ', 150, 1, 150, '2563-03-11', 1),
(256, 1, 'ปั้มหัวฉีด           ', 500, 1, 500, '2563-03-08', 1),
(257, 2, 'กันร้อนข้างท่อ', 350, 2, 700, '2563-03-08', 1),
(258, 3, 'ไฟหน้า              ', 80, 1, 80, '2563-03-08', 1),
(259, 1, 'ปั้มหัวฉีด           ', 500, 1, 500, '2563-03-18', 1),
(260, 2, 'กันร้อนข้างท่อ', 350, 2, 700, '2563-03-18', 1),
(261, 3, 'ไฟหน้า              ', 80, 1, 80, '2563-03-18', 1);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `Product_no` int(50) NOT NULL,
  `CategoryID` int(50) NOT NULL,
  `CategoryName` varchar(50) NOT NULL,
  `ProductName` varchar(50) NOT NULL,
  `Price` int(50) NOT NULL,
  `Stock` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`Product_no`, `CategoryID`, `CategoryName`, `ProductName`, `Price`, `Stock`) VALUES
(1, 1, 'อะไหล่เครื่อง', 'ปั้มหัวฉีด           ', 500, 98),
(2, 1, 'อะไหล่เครื่อง', 'ไส้กรองอากาศ', 300, 98),
(3, 1, 'อะไหล่เครื่อง', 'หัวเทียนขนาดเล็ก', 100, 178),
(4, 2, 'ชุดแต่งภายนอก', 'กันร้อนข้างท่อ', 350, 257),
(5, 2, 'ชุดแต่งภายนอก', 'ชุดเปลือกรถ        ', 1500, 593),
(6, 2, 'ชุดแต่งภายนอก', 'ท่อพร้อมคอท่อ', 1000, 795),
(7, 3, 'ชุดไฟ', 'ไฟหน้า              ', 80, 998),
(8, 3, 'ชุดไฟ', 'ไฟหลัง              ', 20, 294),
(9, 3, 'ชุดไฟ', 'สายไฟ                            ', 20, 599),
(10, 1, 'อะไหล่เครื่อง', 'คาร์บูเรเตอร์       ', 1550, 464),
(11, 1, 'อะไหล่เครื่อง', 'ชุดข้างรถ 4 จังหวะ', 800, 98),
(12, 1, 'อะไหล่เครื่อง', 'ลูกสูบ 53 cm    ', 300, 598),
(13, 1, 'อะไหล่เครื่อง', 'เฟืองทด             ', 20, 497),
(14, 1, 'อะไหล่เครื่อง', 'โซ่ราวลิ้น                     ', 800, 299),
(15, 1, 'อะไหล่เครื่อง', 'แบตเตอรี่            ', 755, 800),
(16, 1, 'อะไหล่เครื่อง', 'ก้านลูกสูบ          ', 50, 490),
(17, 4, 'อุปกรณ์เสริม', 'ตะกร้า                ', 500, 470),
(18, 4, 'อุปกรณ์เสริม', 'ตัวยกโช็ค          ', 80, 299),
(19, 4, 'อุปกรณ์เสริม', 'ผ้าคลุมรถ           ', 300, 98),
(20, 4, 'อุปกรณ์เสริม', 'หมวกกันน็อค     ', 500, 49),
(21, 4, 'อุปกรณ์เสริม', 'แฮนแต่ง             ', 500, 38),
(22, 4, 'อุปกรณ์เสริม', 'เบาะแต่ง            ', 1000, 39),
(23, 4, 'อุปกรณ์เสริม', 'กรองอากาศเปลือย', 80, 96),
(24, 4, 'อุปกรณ์เสริม', 'ดุมกลึง              ', 1500, 299),
(25, 4, 'อุปกรณ์เสริม', 'ล้ออลูมิเนียม      ', 500, 48),
(26, 4, 'อุปกรณ์เสริม', 'ล้อแมกซ์            ', 2000, 99),
(27, 4, 'อุปกรณ์เสริม', 'พักเท้าแต่ง        ', 60, 58),
(28, 4, 'อุปกรณ์เสริม', 'น็อตสแตนเลส', 20, 46),
(29, 4, 'อุปกรณ์เสริม', 'กระจกแต่ง        ', 500, 48),
(30, 3, 'ชุดไฟ', 'สายถัก              ', 100, 209),
(31, 3, 'ชุดไฟ', 'ไฟซีนอล            ', 500, 79),
(32, 3, 'ชุดไฟ', 'ไฟเลี่ยวแต่ง       ', 600, 298),
(33, 3, 'ชุดไฟ', 'สวิตไฟ               ', 100, 269),
(34, 3, 'ชุดไฟ', 'ไฟแต่ง LED              ', 750, 45),
(35, 3, 'ชุดไฟ', 'ไฟกระพริบ LED', 295, 39),
(36, 2, 'ชุดแต่งภายนอก', 'มือเบรก              ', 550, 58),
(37, 2, 'ชุดแต่งภายนอก', 'โช็คหลัง             ', 2000, 59),
(38, 2, 'ชุดแต่งภายนอก', 'เบรกหน้า           ', 800, 196),
(39, 2, 'ชุดแต่งภายนอก', 'ปั้มเบรกบน         ', 500, 49),
(40, 2, 'ชุดแต่งภายนอก', 'แฮนด์                 ', 150, 497),
(41, 2, 'ชุดแต่งภายนอก', 'คอท่อสแตนเลส', 800, 192),
(42, 2, 'ชุดแต่งภายนอก', 'กระจกส่องหลัง', 420, 698),
(43, 2, 'ชุดแต่งภายนอก', 'เรือนไมล์            ', 775, 199),
(45, 3, 'ชุดไฟ', 'ไฟติดข้างรถ        ', 50, 78),
(46, 1, 'อะไหล่เครื่อง', 'น็อตเครื่อง          ', 20, 87),
(47, 2, 'ชุดแต่งภายนอก', 'เข็มน้ำมันแยก', 600, 898),
(48, 3, 'ชุดไฟ', 'ไฟดีเลย์             ', 200, 49),
(49, 4, 'อุปกรณ์เสริม', 'ตัวเกี่ยวของ       ', 50, 99),
(51, 3, 'ชุดไฟ', 'ไฟรุ่นไหม่', 30, 20);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`Cus_id`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`Log_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`Order_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`Product_no`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `Cus_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `Log_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `Order_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=262;
--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `Product_no` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;