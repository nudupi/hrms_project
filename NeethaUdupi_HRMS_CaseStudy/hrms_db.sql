-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.5.8-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for hrms_test
CREATE DATABASE IF NOT EXISTS `hrms_test` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `hrms_test`;

-- Dumping structure for table hrms_test.address
CREATE TABLE IF NOT EXISTS `address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `address_line1` varchar(255) DEFAULT NULL,
  `address_line2` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `user_state` varchar(255) DEFAULT NULL,
  `zipcode` int(11) NOT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table hrms_test.department
CREATE TABLE IF NOT EXISTS `department` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_desc` varchar(255) DEFAULT NULL,
  `dept_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table hrms_test.employee_personal
CREATE TABLE IF NOT EXISTS `employee_personal` (
  `emp_id` int(11) NOT NULL,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  KEY `FK4ycv6vfjt23t3vi0cx0yqfsms` (`address_id`),
  CONSTRAINT `FK4ycv6vfjt23t3vi0cx0yqfsms` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`),
  CONSTRAINT `FK9e4fngme9c0oybky2yn5vie64` FOREIGN KEY (`emp_id`) REFERENCES `employee_professional` (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table hrms_test.employee_professional
CREATE TABLE IF NOT EXISTS `employee_professional` (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `date_of_joining` date DEFAULT NULL,
  `dept_date_of_joining` date DEFAULT NULL,
  `emp_type` varchar(255) DEFAULT NULL,
  `job_title` varchar(255) DEFAULT NULL,
  `ssn_number` int(11) NOT NULL,
  `dept_id` int(11) DEFAULT NULL,
  `manager_emp_id` int(11) DEFAULT NULL,
  `pay_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  KEY `FKpyluwqwviqj7fu2q1i5ipm4ls` (`dept_id`),
  KEY `FK63twsqqs0yfkjk89jfy581xi4` (`manager_emp_id`),
  KEY `FK2am6rcr1rxf9vt2ncldadicvq` (`pay_id`),
  KEY `FKmdq1j93vcppjn79wsykfmbo8j` (`user_id`),
  CONSTRAINT `FK2am6rcr1rxf9vt2ncldadicvq` FOREIGN KEY (`pay_id`) REFERENCES `payment` (`pay_id`),
  CONSTRAINT `FK63twsqqs0yfkjk89jfy581xi4` FOREIGN KEY (`manager_emp_id`) REFERENCES `employee_professional` (`emp_id`),
  CONSTRAINT `FKmdq1j93vcppjn79wsykfmbo8j` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKpyluwqwviqj7fu2q1i5ipm4ls` FOREIGN KEY (`dept_id`) REFERENCES `department` (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table hrms_test.employee_transaction
CREATE TABLE IF NOT EXISTS `employee_transaction` (
  `trans_id` int(11) NOT NULL AUTO_INCREMENT,
  `action` varchar(255) DEFAULT NULL,
  `action_desc` varchar(255) DEFAULT NULL,
  `trans_date` date DEFAULT NULL,
  `employee_professional_emp_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`trans_id`),
  KEY `FKo2cda57ykn4dt0gmnke2rjsjc` (`employee_professional_emp_id`),
  CONSTRAINT `FKo2cda57ykn4dt0gmnke2rjsjc` FOREIGN KEY (`employee_professional_emp_id`) REFERENCES `employee_professional` (`emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table hrms_test.payment
CREATE TABLE IF NOT EXISTS `payment` (
  `pay_id` int(11) NOT NULL AUTO_INCREMENT,
  `account_number` int(11) NOT NULL,
  `allowance` double NOT NULL,
  `basic_salary` double NOT NULL,
  `bonus` double NOT NULL,
  `deductions` double NOT NULL,
  PRIMARY KEY (`pay_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table hrms_test.user
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `user_role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
