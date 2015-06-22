/*
SQLyog Enterprise - MySQL GUI v7.02 
MySQL - 5.0.67-community-nt : Database - ramerp
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`ramerp` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ramerp`;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `user_id` int(11) NOT NULL,
  `house_no` varchar(45) default NULL,
  `line_1` varchar(100) default NULL,
  `line_2` varchar(100) default NULL,
  `city` varchar(45) default NULL,
  `state` varchar(45) default NULL,
  `zip` varchar(10) default NULL,
  KEY `person_id_fk_idx` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `address` */

insert  into `address`(`user_id`,`house_no`,`line_1`,`line_2`,`city`,`state`,`zip`) values (3,'AA-13','Netaji Subhash Nagar II','Hari Marg, Tonk Road','Jaipur','Maharashtra','302015'),(4,'50','Santosh Nagar','Opp. Ganga Jamuna Petrol Pump, Near New Aatish Market, Gopalpura Bypass','Jaipur','Rajasthan','302019'),(9,'50','Santosh Nagar, Opp. Ganga-Jamuna Petrol Pump','Near New Aatish Market','Jaipur','Rajasthan','302019'),(10,'19','Nirman Nagar Extension','Hari Marg, Tonk Road','Jaipur','Rajasthan','302015'),(11,'13','Jay Shankar Colony','New Sanganer Road','Jaipur','Rajasthan','302020');

/*Table structure for table `attendance` */

DROP TABLE IF EXISTS `attendance`;

CREATE TABLE `attendance` (
  `_id` int(11) NOT NULL auto_increment,
  `employee_id` int(11) NOT NULL,
  `date` date default NULL,
  PRIMARY KEY  (`_id`),
  KEY `emp_attend_fk_idx` (`employee_id`),
  CONSTRAINT `emp_attend_fk` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `attendance` */

/*Table structure for table `capital` */

DROP TABLE IF EXISTS `capital`;

CREATE TABLE `capital` (
  `_id` int(11) NOT NULL auto_increment,
  `amount` int(11) default NULL,
  `interest_rate` int(11) default NULL,
  `lender` varchar(255) default NULL,
  PRIMARY KEY  (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `capital` */

insert  into `capital`(`_id`,`amount`,`interest_rate`,`lender`) values (1,NULL,NULL,NULL);

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `_id` int(11) NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY  (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `category` */

insert  into `category`(`_id`,`name`) values (1,'Center Table'),(2,'Dining Set'),(3,'Plastic Chair'),(4,'Double Bed'),(5,'Sofa Set'),(6,'Office Table'),(7,'Office Chair'),(8,'Mattress'),(9,'Corner Table'),(10,'Almirah');

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `_id` int(11) NOT NULL auto_increment,
  `complaint_id` int(11) NOT NULL,
  `commenter` int(11) NOT NULL,
  `text` longtext,
  PRIMARY KEY  (`_id`),
  KEY `comment_complaint_fk_idx` (`complaint_id`),
  KEY `comment_operator_fk_idx` (`commenter`),
  CONSTRAINT `comment_complaint_fk` FOREIGN KEY (`complaint_id`) REFERENCES `complaint` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_operator_fk` FOREIGN KEY (`commenter`) REFERENCES `user_login` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `comment` */

/*Table structure for table `complaint` */

DROP TABLE IF EXISTS `complaint`;

CREATE TABLE `complaint` (
  `_id` int(11) NOT NULL auto_increment,
  `order_id` int(11) NOT NULL,
  `status` varchar(45) default NULL,
  `register_date` datetime default NULL,
  `operator_id` int(11) NOT NULL,
  PRIMARY KEY  (`_id`),
  KEY `order_complaint_fk_idx` (`order_id`),
  CONSTRAINT `op_complaint_fk` FOREIGN KEY (`_id`) REFERENCES `user` (`_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `order_complaint_fk` FOREIGN KEY (`order_id`) REFERENCES `order` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `complaint` */

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `user_id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `mobile` varchar(10) default NULL,
  `email` varchar(255) default NULL,
  PRIMARY KEY  (`user_id`),
  KEY `cust_id_fk_idx` (`user_id`),
  CONSTRAINT `cust_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `customer` */

insert  into `customer`(`user_id`,`name`,`mobile`,`email`) values (3,'Anil Jain','9889778122','aniljain@gmail.com'),(4,'Leeladhar Agarwal','9874665565','leeladhar@gmail.com'),(9,'AjayJain','9414871461','gauravag94@live.com'),(10,'Aruna Agarwal','9414868388','aruna.2011@ymail.com');

/*Table structure for table `daily_expenditure` */

DROP TABLE IF EXISTS `daily_expenditure`;

CREATE TABLE `daily_expenditure` (
  `expenditure_id` int(11) NOT NULL,
  `details` varchar(255) default NULL,
  PRIMARY KEY  (`expenditure_id`),
  KEY `daily_exp_id_idx` (`expenditure_id`),
  CONSTRAINT `daily_exp_id` FOREIGN KEY (`expenditure_id`) REFERENCES `expenditure` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `daily_expenditure` */

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `employee_id` int(11) NOT NULL,
  `role` varchar(45) NOT NULL,
  `date_of_join` date default NULL,
  `salary` int(11) default NULL,
  `status` varchar(45) default NULL,
  `date_of_leave` date default NULL,
  PRIMARY KEY  (`employee_id`),
  KEY `emp_id_fk_idx` (`employee_id`),
  CONSTRAINT `emp_id_fk` FOREIGN KEY (`employee_id`) REFERENCES `user` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `employee` */

/*Table structure for table `expenditure` */

DROP TABLE IF EXISTS `expenditure`;

CREATE TABLE `expenditure` (
  `_id` int(11) NOT NULL auto_increment,
  `transaction_id` int(11) NOT NULL,
  `expenditure_month` varchar(45) NOT NULL,
  PRIMARY KEY  (`_id`),
  KEY `transaction_exp_fk_idx` (`transaction_id`),
  CONSTRAINT `transaction_exp_fk` FOREIGN KEY (`transaction_id`) REFERENCES `transaction` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `expenditure` */

/*Table structure for table `interest_expenditure` */

DROP TABLE IF EXISTS `interest_expenditure`;

CREATE TABLE `interest_expenditure` (
  `expenditure_id` int(11) NOT NULL,
  `capital_id` int(11) NOT NULL,
  KEY `interest_exp_id_idx` (`expenditure_id`),
  KEY `interest_capital_id_idx` (`capital_id`),
  CONSTRAINT `interest_capital_id` FOREIGN KEY (`capital_id`) REFERENCES `capital` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `interest_exp_id` FOREIGN KEY (`expenditure_id`) REFERENCES `expenditure` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `interest_expenditure` */

/*Table structure for table `loan` */

DROP TABLE IF EXISTS `loan`;

CREATE TABLE `loan` (
  `_id` int(11) NOT NULL auto_increment,
  `amont` int(11) default NULL,
  `tenure` int(11) default NULL,
  `installment` int(11) default NULL,
  `interest_rate` int(11) default NULL,
  PRIMARY KEY  (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `loan` */

/*Table structure for table `loan_expenditure` */

DROP TABLE IF EXISTS `loan_expenditure`;

CREATE TABLE `loan_expenditure` (
  `expenditure_id` int(11) NOT NULL,
  `loan_id` int(11) default NULL,
  KEY `loan_exp_fk_idx` (`expenditure_id`),
  KEY `loan_expenditure_loan_fk_idx` (`loan_id`),
  CONSTRAINT `loan_expenditure_loan_fk` FOREIGN KEY (`loan_id`) REFERENCES `loan` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `loan_exp_fk` FOREIGN KEY (`expenditure_id`) REFERENCES `expenditure` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `loan_expenditure` */

/*Table structure for table `merchant` */

DROP TABLE IF EXISTS `merchant`;

CREATE TABLE `merchant` (
  `_id` int(11) NOT NULL auto_increment,
  `merchant_name` varchar(255) default NULL,
  `mobile` varchar(10) default NULL,
  `email` varchar(255) default NULL,
  `tin` varchar(20) default NULL,
  PRIMARY KEY  (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `merchant` */

insert  into `merchant`(`_id`,`merchant_name`,`mobile`,`email`,`tin`) values (11,'S S Trading Co.','9460008990','sstradingcompany@gmail.com','080054646565');

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `_id` int(11) NOT NULL auto_increment,
  `cust_id` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY  (`_id`),
  KEY `cust_order_fk_idx` (`cust_id`),
  CONSTRAINT `FK_order` FOREIGN KEY (`cust_id`) REFERENCES `user` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

/*Data for the table `order` */

insert  into `order`(`_id`,`cust_id`,`amount`,`time`) values (13,3,130000,'2015-06-20 12:19:02'),(14,4,350500,'2015-06-20 12:30:34'),(15,9,120000,'2015-06-20 12:34:10'),(16,4,136500,'2015-06-20 12:45:32'),(17,3,148000,'2015-06-20 12:47:22'),(18,4,14800,'2015-06-20 13:29:50'),(19,10,130000,'2015-06-20 14:05:07'),(20,11,148000,'2015-06-20 20:58:06'),(21,11,148800,'2015-06-21 15:42:57'),(22,11,148000,'2015-06-21 15:50:27'),(23,11,159750,'2015-06-21 16:03:18'),(24,11,57000,'2015-06-21 16:06:02'),(25,11,148000,'2015-06-21 20:00:14'),(26,11,354500,'2015-06-22 00:18:31'),(27,11,240000,'2015-06-22 00:36:16'),(28,11,120000,'2015-06-22 11:14:12'),(29,11,379000,'2015-06-22 21:49:39');

/*Table structure for table `order_item` */

DROP TABLE IF EXISTS `order_item`;

CREATE TABLE `order_item` (
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  PRIMARY KEY  (`order_id`,`product_id`),
  KEY `prod_oi_fk_idx` (`product_id`),
  CONSTRAINT `order_oi_fk` FOREIGN KEY (`order_id`) REFERENCES `order` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `prod_oi_fk` FOREIGN KEY (`product_id`) REFERENCES `product` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `order_item` */

insert  into `order_item`(`order_id`,`product_id`,`quantity`,`amount`) values (13,1,400,325),(14,1,400,370),(14,2,300,400),(14,3,300,275),(15,2,300,400),(16,2,325,420),(17,1,400,370),(18,1,40,370),(19,1,400,325),(20,1,400,370),(21,1,400,372),(22,1,400,370),(23,1,450,355),(24,3,200,285),(25,1,400,370),(26,1,400,355),(26,2,325,400),(26,3,300,275),(27,2,300,400),(27,3,300,400),(28,2,300,400),(29,1,400,370),(29,2,300,410),(29,3,400,270);

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `transaction_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `type` varchar(45) default NULL,
  PRIMARY KEY  (`transaction_id`,`order_id`),
  KEY `transaction_pay_fk_idx` (`transaction_id`),
  KEY `order_payment_fk_idx` (`order_id`),
  CONSTRAINT `order_payment_fk` FOREIGN KEY (`order_id`) REFERENCES `order` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `transaction_pay_fk` FOREIGN KEY (`transaction_id`) REFERENCES `transaction` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `payment` */

/*Table structure for table `privilege` */

DROP TABLE IF EXISTS `privilege`;

CREATE TABLE `privilege` (
  `name` varchar(200) NOT NULL,
  `path` varchar(200) default NULL,
  `mainmenu` int(10) default NULL,
  `_id` int(11) NOT NULL auto_increment,
  `user_type` varchar(50) default NULL,
  `icon` varchar(20) default NULL,
  PRIMARY KEY  (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

/*Data for the table `privilege` */

insert  into `privilege`(`name`,`path`,`mainmenu`,`_id`,`user_type`,`icon`) values ('Employee',NULL,NULL,1,'OPERATOROWNER','md-group'),('Mark Attendance',NULL,1,2,'OPERATOR',NULL),('Orders',NULL,NULL,3,'OPERATOR','md-shopping-cart'),('Take Order','operator/takeorder.jsp',3,4,'OPERATOR',NULL),('Process Order',NULL,3,5,'OPERATOR',NULL),('View Orders',NULL,3,6,'OPERATOR',NULL),('Generate Bill',NULL,3,7,'OPERATOR',NULL),('Generate Challan',NULL,3,8,'OPERATOR',NULL),('Complaints',NULL,NULL,9,'OPERATOR','md-description'),('View Complaints','',9,10,'OPERATOR',NULL),('Register Complaints','operator/complaint/requestComplaint.jsp',9,11,'OPERATOR',NULL),('Process Complaints',NULL,9,12,'OPERATOR',NULL),('Inventory',NULL,NULL,13,'OPERATOR','md-add-shopping-cart'),('Add Stock',NULL,13,14,'OPERATOR',NULL),('Manage Returns',NULL,13,15,'OPERATOR',NULL),('Expenditure',NULL,NULL,16,'OPERATOR','md-my-library-add'),('Add Expenditure',NULL,16,17,'OPERATOR',NULL),('Add Employee',NULL,1,18,'OWNER',NULL),('Manage Employees',NULL,1,21,'OWNER',NULL),('Add DBA',NULL,1,22,'OWNER',NULL),('Manage DBA',NULL,1,23,'OWNER',NULL),('Add Operator',NULL,1,24,'OWNER',NULL),('Manage Operator',NULL,1,25,'OWNER',NULL),('Accounts',NULL,NULL,26,'OWNER','md-my-library-books'),('View Balance Sheet',NULL,26,27,'OWNER',NULL),('View Expenditures',NULL,26,28,'OWNER',NULL),('View Transactions',NULL,26,29,'OWNER',NULL),('Privileges',NULL,NULL,30,'OWNER','md-verified-user'),('Manage Privileges',NULL,30,31,'OWNER',NULL),('Funds',NULL,NULL,32,'OWNER','md-local-grocery-sto'),('Add Loan',NULL,32,33,'OWNER',NULL),('Add Capital',NULL,32,34,'OWNER',NULL),('Backups',NULL,NULL,35,'DBA','md-backup'),('Take Backup',NULL,35,36,'DBA',NULL),('Delete Backup',NULL,35,37,'DBA',NULL),('Manage Database',NULL,NULL,38,'DBA','md-query-builder'),('Fire Query',NULL,38,39,'DBA',NULL);

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `_id` int(11) NOT NULL auto_increment,
  `category` int(11) default NULL,
  `name` varchar(255) default NULL,
  `quantity` int(11) NOT NULL,
  `sell_price` int(11) NOT NULL,
  `cost_price` int(11) default NULL,
  PRIMARY KEY  (`_id`),
  CONSTRAINT `cat_prod_fk` FOREIGN KEY (`_id`) REFERENCES `category` (`_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`_id`,`category`,`name`,`quantity`,`sell_price`,`cost_price`) values (1,3,'Nova Chair',500,375,295),(2,3,'Swagath Model',350,425,310),(3,3,'Neelkamal',450,285,195);

/*Table structure for table `purchase` */

DROP TABLE IF EXISTS `purchase`;

CREATE TABLE `purchase` (
  `_id` int(11) NOT NULL auto_increment,
  `date` date default NULL,
  `expenditure_id` int(11) default NULL,
  `merchant_id` int(11) default NULL,
  PRIMARY KEY  (`_id`),
  KEY `purchase_exp_fk_idx` (`expenditure_id`),
  KEY `purchase_merchant_fk_idx` (`merchant_id`),
  CONSTRAINT `purchase_exp_fk` FOREIGN KEY (`expenditure_id`) REFERENCES `expenditure` (`_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `purchase_merchant_fk` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `purchase` */

/*Table structure for table `purchase_item` */

DROP TABLE IF EXISTS `purchase_item`;

CREATE TABLE `purchase_item` (
  `purchase_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) default NULL,
  `amount` int(11) default NULL,
  PRIMARY KEY  (`purchase_id`,`product_id`),
  KEY `purchase_item_fk_idx` (`purchase_id`),
  KEY `product_purchase_fk_idx` (`product_id`),
  CONSTRAINT `product_purchase_fk` FOREIGN KEY (`product_id`) REFERENCES `product` (`_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `purchase_item_fk` FOREIGN KEY (`purchase_id`) REFERENCES `purchase` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `purchase_item` */

/*Table structure for table `salary_expenditure` */

DROP TABLE IF EXISTS `salary_expenditure`;

CREATE TABLE `salary_expenditure` (
  `expenditure_id` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `recieved_by` varchar(255) default NULL,
  PRIMARY KEY  (`expenditure_id`,`employee_id`),
  KEY `exp_salary_fk_idx` (`expenditure_id`),
  KEY `emp_salary_idx` (`employee_id`),
  CONSTRAINT `emp_salary` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `exp_salary_fk` FOREIGN KEY (`expenditure_id`) REFERENCES `expenditure` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `salary_expenditure` */

/*Table structure for table `transaction` */

DROP TABLE IF EXISTS `transaction`;

CREATE TABLE `transaction` (
  `_id` int(11) NOT NULL auto_increment,
  `timestamp` datetime default NULL,
  `amount` int(11) default NULL,
  `mode` varchar(45) default NULL,
  `paid_by` varchar(255) default NULL,
  PRIMARY KEY  (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `transaction` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `_id` int(11) NOT NULL auto_increment,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) default NULL,
  `email` varchar(255) NOT NULL,
  `mobile` varchar(10) NOT NULL,
  `type` varchar(10) default NULL,
  PRIMARY KEY  (`_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`_id`,`first_name`,`last_name`,`email`,`mobile`,`type`) values (1,'Gaurav','Agarwal','ggauravag@gmail.com','9982166368','OWNER'),(2,'Gaurav','Agarwal','gauravag94@gmail.com','9414871461','OPERATOR'),(3,'Anil','Jain','anilji@gmail.com','9999999999','CUSTOMER'),(4,'Leeladhar','Agarwal','leeladhar@gmail.com','9415789332','CUSTOMER'),(5,'Chaggan','Lal','chagganlal@gmail.com','9876552355','CUSTOMER'),(6,'Anuj','Jain','anujjain@gmail.com','7655555598','CUSTOMER'),(7,'Manoj','Verma','manojverma@gmail.com','9876652331','CUSTOMER'),(9,'Ajay','Jain','gauravag94@live.com','9414871461','CUSTOMER'),(10,'Aruna','Agarwal','aruna.2011@ymail.com','9414868388','CUSTOMER'),(11,'S','S Trading Co.','sstradingcompany@gmail.com','9460008990','MERCHANT');

/*Table structure for table `user_login` */

DROP TABLE IF EXISTS `user_login`;

CREATE TABLE `user_login` (
  `user_id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `log_ip` varchar(45) default NULL,
  `status` varchar(3) default NULL,
  `type` varchar(45) NOT NULL,
  `st_time` time default NULL,
  `end_time` time default NULL,
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `user_id_fk_idx` (`user_id`),
  CONSTRAINT `user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_login` */

insert  into `user_login`(`user_id`,`email`,`password`,`log_ip`,`status`,`type`,`st_time`,`end_time`) values (2,'gauravag94@gmail.com','5f4dcc3b5aa765d61d8327deb882cf99','0:0:0:0:0:0:0:1','Y','OPERATOR','00:01:00','23:59:00'),(1,'ggauravag@gmail.com','5f4dcc3b5aa765d61d8327deb882cf99','0:0:0:0:0:0:0:1','Y','OWNER','12:00:00','15:30:00');

/* Procedure structure for procedure `CreateCustomer` */

/*!50003 DROP PROCEDURE IF EXISTS  `CreateCustomer` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `CreateCustomer`(IN fname varchar(255),in lname varchar(255),IN mobile varchar(255),IN email varchar(255),
        in house varchar(255),in line1 varchar(255),in line2 varchar(255),in city varchar(255),in state varchar(255),in zip varchar(255),out cust_id INT)
BEGIN
    insert into user(first_name,last_name,email,mobile,`type`) values(fname,lname,email,mobile,'CUSTOMER');
    select LAST_INSERT_ID() into cust_id;
    insert into address values(cust_id,house,line1,line2,city,state,zip);
    insert into customer values(cust_id,CONCAT(fname,' ',lname),mobile,email);
    END */$$
DELIMITER ;

/* Procedure structure for procedure `CreateMerchant` */

/*!50003 DROP PROCEDURE IF EXISTS  `CreateMerchant` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `CreateMerchant`(IN fname varchar(255),in lname varchar(255),IN mobile varchar(255),IN email varchar(255),
        in house varchar(255),in line1 varchar(255),in line2 varchar(255),in city varchar(255),in state varchar(255),in zip varchar(255),out cust_id INT)
BEGIN
    insert into user(first_name,last_name,email,mobile,`type`) values(fname,lname,email,mobile,'MERCHANT');
    select LAST_INSERT_ID() into cust_id;
    insert into merchant values(cust_id,concat(fname,lname),mobile,email,'');
    insert into address values(cust_id,house,line1,line2,city,state,zip);
    END */$$
DELIMITER ;

/* Procedure structure for procedure `CreateOrder` */

/*!50003 DROP PROCEDURE IF EXISTS  `CreateOrder` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `CreateOrder`(in cust int(10),out order_id int(10))
BEGIN
       insert into `order`(cust_id,amount,time) values(cust,0,now());
       select last_insert_id() into order_id;
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
