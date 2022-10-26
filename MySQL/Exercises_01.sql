CREATE DATABASE `minions`;
use `minions`;
CREATE TABLE `minions`(
`id` INT PRIMARY KEY AUTO_INCREMENT, 
`name` VARCHAR(30),
`age` INT 	  
);

DROP TABLE `towns`;

CREATE TABLE `towns`(
`town_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30)
);

ALTER TABLE `towns`
CHANGE COLUMN `town_id` `id` INT;

ALTER TABLE `minions`
ADD COLUMN `town_id` INT;
ALTER TABLE `minions`
ADD CONSTRAINT fk_minions_towns FOREIGN KEY(town_id)
REFERENCES towns(id);

INSERT INTO `towns` (`id`, `name`)
VALUES 
( 1, 'Sofia'),
( 2, 'Plovdiv'),
( 3, 'Varna');

INSERT INTO `minions` (`id`, `name`, `age`, `town_id`)
VALUES 
(1, 'Kevin', 22, 1),
(2, 'Bob', 15, 3),
(3, 'Steward', NULL , 2);

TRUNCATE TABLE `minions`;

DROP TABLE `minions`;
DROP TABLE `towns`;

CREATE TABLE `people`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHARACTER(200) NOT NULL,
`picture` BLOB,
`height` DOUBLE(10, 2),
`weight`  DOUBLE(10, 2),
`gender` CHAR(1) NOT NULL,
`birthdate` DATE NOT NULL,
`biography` TEXT
);

INSERT INTO `people` (`name`, `gender`, `birthdate`)
VALUES
('Test', 'M', DATE(NOW())),
('Test', 'M', DATE(NOW())),
('Test', 'M', DATE(NOW())),
('Test', 'M', DATE(NOW())),
('Test', 'M', DATE(NOW()));

CREATE TABLE `users`(
`id` INT AUTO_INCREMENT, 
`username` VARCHAR(30) NOT NULL,
`password` VARCHAR(26) NOT NULL,
`profile_picture` BLOB,
`last_login_time` TIME,
`is_deleted` BOOLEAN,
CONSTRAINT pk_users
PRIMARY KEY `users` (`id`)
);
INSERT INTO `users` (`username`, `password`)
VALUES 
('Test1', 'Pass'),
('Test2', 'Pass'),
('Test3', 'Pass'),
('Test4', 'Pass'),
('Test5', 'Pass');

ALTER TABLE `users`
DROP PRIMARY KEY,
ADD CONSTRAINT pk_users2
PRIMARY KEY `users`(`id`, `username`);

ALTER TABLE `users`
CHANGE COLUMN `last_login_time` `last_login_time` DATETIME DEFAULT NOW();

ALTER TABLE `users`
DROP PRIMARY KEY, 
ADD CONSTRAINT pk_users 
PRIMARY KEY `users`(`id`), 
CHANGE COLUMN `username` `username` VARCHAR(50) UNIQUE;

CREATE DATABASE `movies`;
USE `movies`;

CREATE TABLE `directors`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`director_name` VARCHAR(50) NOT NULL,
`notes` TEXT
);

INSERT INTO `directors`(`director_name`, `notes`)
VALUES 
('TestName1', 'TestNotes'),
('TestName2', 'TestNotes'),
('TestName3', 'TestNotes'),
('TestName4', 'TestNotes'),
('TestName5', 'TestNotes');

CREATE TABLE `genres` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`genre_name` VARCHAR(20) NOT NULL, 
`notes` TEXT
);

INSERT INTO `genres`(`genre_name`, `notes`)
VALUES 
('TestName1', 'TestNotes'),
('TestName2', 'TestNotes'),
('TestName3', 'TestNotes'),
('TestName4', 'TestNotes'),
('TestName5', 'TestNotes');

CREATE TABLE `categories` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`category_name` VARCHAR(20) NOT NULL,
`notes` TEXT
);

INSERT INTO `categories`(`category_name`, `notes`)
VALUES 
('TestName1', 'TestNotes'),
('TestName2', 'TestNotes'),
('TestName3', 'TestNotes'),
('TestName4', 'TestNotes'),
('TestName5', 'TestNotes');

CREATE TABLE `movies`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`title` VARCHAR(40) NOT NULL, 
    `director_id` INT,
    `copyright_year` INT,
    `length` INT,
    `genre_id` INT,
    `category_id` INT,
    `rating` DOUBLE, 
    `notes` TEXT
);

INSERT INTO `movies` (`title`)
VALUES 
('TestMovie1'),
('TestMovie2'),
('TestMovie3'),
('TestMovie4'),
('TestMovie5');

CREATE DATABASE `car_rental`;
USE `car_rental`;

CREATE TABLE `categories` (
`id` INT PRIMARY KEY AUTO_INCREMENT, 
`category` VARCHAR(30), 
`daily_rate` DOUBLE, 
`weekly_rate` DOUBLE, 
`monthly_rate` DOUBLE, 
`weekend_rate` DOUBLE
);

INSERT INTO `categories` (`category`)
VALUES 
('TestName1'),
('TestName2'),
('TestName3');

CREATE TABLE `cars` (
	`id` INT PRIMARY KEY AUTO_INCREMENT, 
    `plate_number` VARCHAR(20),
    `make` VARCHAR(20),
    `model` VARCHAR(20),
    `car_year` INT,
    `category_id` INT,
    `doors` INT,
    `picture` BLOB,
    `car_condition` VARCHAR(30),
    `available` BOOLEAN   
);

INSERT INTO `cars` (`plate_number`)
VALUES 
('TestName1'),
('TestName2'),
('TestName3');

CREATE TABLE `employees` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(50),
    `last_name` VARCHAR(50),
    `title` VARCHAR(50),
    `notes` TEXT
);

INSERT INTO `employees` (`first_name`, `last_name`)
VALUES 
('TestName1', 'TestName1'),
('TestName2', 'TestName2'),
('TestName3', 'TestName3');

CREATE TABLE `customers` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `driver_license` VARCHAR(20),
	`full_name` VARCHAR(50),
	`address` VARCHAR(50),
	`city` VARCHAR(10),
	`zip_code` VARCHAR(10),
	`notes` TEXT
);

INSERT INTO `customers` (`driver_license`, `full_name`)
VALUES 
('TestName1', 'TestName1'),
('TestName2', 'TestName2'),
('TestName3', 'TestName3');

CREATE TABLE `rental_orders` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `employee_id` INT,
    `customer_id` INT,
    `car_id` INT,
    `car_condition` VARCHAR(50),
    `tank_level` VARCHAR(20),
	`kilometrage_start` INT,
    `kilometrage_end` INT,
    `total_kilometrage` INT,
    `start_date` DATE, 
    `end_date` DATE, 
    `total_days` INT,
    `rate_applied` DOUBLE,
    `tax_rate` DOUBLE,
    `order_status` VARCHAR(20),
    `notes` TEXT
);

INSERT INTO `rental_orders` (`employee_id`, `customer_id`)
VALUES 
(1, 2),
(2, 3),
(3, 1);




