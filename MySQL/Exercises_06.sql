 CREATE DATABASE exercises;
 USE exercises;
 
 -- 1 
 
 CREATE TABLE people(  
 `person_id` INT AUTO_INCREMENT UNIQUE NOT NULL,
 `first_name` VARCHAR(20) NOT NULL,
 `salary` DECIMAL(10,2) NOT NULL DEFAULT 0, 
 `passport_id` INT NOT NULL UNIQUE
 );
 
 CREATE TABLE passports (
 `passport_id` INT AUTO_INCREMENT UNIQUE NOT NULL,
 `passport_number` VARCHAR(8) NOT NULL UNIQUE
 )AUTO_INCREMENT = 101;
 
 INSERT INTO people (`first_name`, `salary`, `passport_id`)
		VALUES
        ('Roberto', '43300', '102'),
        ('Tom', '56100', '103'),
        ('Yana', '60200', '101');
INSERT INTO passports (`passport_number`)
	VALUE
    ('N34FG21B'),
    ('K65LO4R7'),
    ('ZE657QP2');
ALTER TABLE people
	ADD CONSTRAINT pk_people
		PRIMARY KEY (`person_id`),
    ADD CONSTRAINT fk_people_passport
		FOREIGN KEY (`passport_id`) 
        REFERENCES passports(`passport_id`);  

-- 2 

CREATE TABLE manufacturers(
`manufacturer_id` INT AUTO_INCREMENT UNIQUE NOT NULL, 
`name` VARCHAR(20) NOT NULL,
`established_on` DATE NOT NULL
);

CREATE TABLE models (
`model_id`INT AUTO_INCREMENT UNIQUE NOT NULL,
`name`VARCHAR(20) NOT NULL,
`manufacturer_id` INT NOT NULL 
) AUTO_INCREMENT = 101;

INSERT INTO manufacturers (`name`, `established_on`)
	VALUES
    ('BMW', '1916-03-01'),
    ('Tesla', '2003-01-01'),
    ('Lada', '1966-05-01');
INSERT INTO models (`name`, `manufacturer_id`)
	VALUES 
    ('X1', 1), 
    ('i6', 1), 
    ('Model S', 2), 
    ('Model X', 2), 
    ('Model 3', 2), 
    ('Nova', 3);
ALTER TABLE manufacturers
	ADD CONSTRAINT pk_manufacturer
    PRIMARY KEY manufacturers(manufacturer_id);
ALTER TABLE models
	ADD CONSTRAINT pk_model
    PRIMARY KEY (model_id),
    ADD CONSTRAINT fk_model_manufacturer
    FOREIGN KEY (manufacturer_id)
	REFERENCES manufacturers(manufacturer_id);

-- 3 

CREATE TABLE exams(
`exam_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(20) NOT NULL
)AUTO_INCREMENT = 101;
CREATE TABLE students(
`student_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(20) NOT NULL
);
CREATE TABLE students_exams(
`student_id`INT NOT NULL,
`exam_id` INT NOT NULL,
CONSTRAINT fk_students_exams
FOREIGN KEY (`student_id`)
REFERENCES students(`student_id`),
CONSTRAINT fk_exams_students
FOREIGN KEY (`exam_id`)
REFERENCES exams(`exam_id`)
);

INSERT INTO exams (`name`)
	VALUE 
    ('Spring MVC'),
	('Neo4j'),
	('Oracle 11g');
INSERT INTO students (`name`)
	VALUE 
    ('Mila'),
	('Toni'),
	('Ron');
INSERT INTO students_exams (`student_id`, `exam_id`)
	VALUES 
    (1, 101),  
    (1, 102),  
    (2, 101),  
    (3, 103),  
    (2, 102),  
    (2, 103);  
    
-- 4

CREATE TABLE teachers (
`teacher_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(20) NOT NULL,
`manager_id` INT DEFAULT NULL
) AUTO_INCREMENT = 101;    

INSERT INTO teachers (`name`, `manager_id`)
	VALUES 
    ('John', NULL),
    ('Maya', 106),
    ('Silvia', 106),
    ('Ted', 105),
    ('Mark', 101),
    ('Greta', 101);
ALTER TABLE teachers
	ADD CONSTRAINT fk_teacher_manager
    FOREIGN KEY (manager_id)
    REFERENCES teachers(teacher_id);
    
-- 5 
CREATE TABLE cities (
`city_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL
);
CREATE TABLE customers (
`customer_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL,
`birthday` DATE,
`city_id` INT,
CONSTRAINT fk_customers_cities
FOREIGN KEY (`city_id`)
REFERENCES cities(`city_id`)
);
CREATE TABLE orders (
`order_id` INT PRIMARY KEY AUTO_INCREMENT,
`customer_id` INT,
CONSTRAINT fk_orders_customers
FOREIGN KEY (customer_id)
REFERENCES customers(customer_id)
);
CREATE TABLE item_types(
`item_type_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50)
);

CREATE TABLE items (
`item_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50),
`item_type_id` INT,
CONSTRAINT fk_items_item_types
FOREIGN KEY (item_type_id)
REFERENCES item_types(item_type_id)
);
CREATE TABLE order_items(
`order_id` INT NOT NULL,
`item_id` INT NOT NULL, 
CONSTRAINT pk_order_items
PRIMARY KEY (order_id, item_id),
CONSTRAINT fk_order_item
FOREIGN KEY (order_id)
REFERENCES orders(order_id),
CONSTRAINT fk_items_orders
FOREIGN KEY (item_id)
REFERENCES items(item_id)
);

