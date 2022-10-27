DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above_35000()
BEGIN
SELECT e.first_name, e.last_name
FROM employees AS e
WHERE salary > 35000 
ORDER BY e.first_name, e.last_name, e.employee_id;
END $$
DELIMITER ;

CALL usp_get_employees_salary_above_35000();

-- 2

DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above(salary_limit DOUBLE(19, 4))
BEGIN 
	SELECT e.first_name, e.last_name
    FROM employees AS e
    WHERE e.salary >= salary_limit
    ORDER BY e.first_name, e.last_name, e.employee_id;
END $$
DELIMITER ;


CALL usp_get_employees_salary_above(45000);

-- 3 
DELIMITER $$
CREATE PROCEDURE usp_get_towns_starting_with(town_name_start TEXT)
BEGIN
SELECT t.name 
FROM towns AS t
WHERE t.name LIKE CONCAT(town_name_start, '%')
ORDER BY t.name;
END $$
DELIMITER ;

CALL usp_get_towns_starting_with('b');
DROP PROCEDURE usp_get_towns_starting_with;
-- 4 
DELIMITER $$ 
CREATE PROCEDURE usp_get_employees_from_town(town_name TEXT)
BEGIN
	SELECT e.first_name, e.last_name FROM employees AS e 
		JOIN
    addresses AS e ON e.address_id = a.address_id
		JOIN
    towns AS t ON t.town_id = a.town_id
    WHERE t.name = town_name
    ORDER BY e.first_name, e.last_name, e.employee_id;
END
DELIMITER ;
















