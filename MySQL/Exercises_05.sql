SELECT COUNT(id) FROM wizzard_deposits;

-- ----------------------
SELECT MAX(magic_wand_size) AS 'longest_magic_wand' FROM wizzard_deposits;

-- -----------------------
SELECT 
    deposit_group, MAX(magic_wand_size) 'longest_magic_wand'
FROM
    wizzard_deposits
GROUP BY deposit_group
ORDER BY longest_magic_wand ASC, deposit_group ASC;

-- ------------------------
SELECT deposit_group from wizzard_deposits
GROUP BY deposit_group
ORDER BY AVG(magic_wand_size)
LIMIT 1;

-- ------------------------
SELECT 
    deposit_group, SUM(deposit_amount) 'total_sum'
FROM
    wizzard_deposits
WHERE magic_wand_creator = 'Ollivander family'
GROUP BY deposit_group
HAVING total_sum < 150000
ORDER BY total_sum DESC;

-- -------------------------
SELECT 
    CASE
        WHEN age <= 10 THEN '[0-10]'
        WHEN age <= 20 THEN '[11-20]'
        WHEN age <= 30 THEN '[21-30]'
        WHEN age <= 40 THEN '[31-40]'
        WHEN age <= 50 THEN '[41-50]'
        WHEN age <= 60 THEN '[51-60]'
        ELSE '[61+]'
    END AS 'age_group',
    COUNT(*) 'wizard_count'
FROM
    wizzard_deposits
GROUP BY age_group
ORDER BY wizard_count;

-- --------------------------
SELECT LEFT(first_name, 1) AS 'first_letter' FROM wizzard_deposits
WHERE deposit_group LIKE 'Troll Chest'
GROUP BY first_letter
ORDER BY first_letter ASC;

-- ---------------------------
SELECT 
    deposit_group, is_deposit_expired, AVG(deposit_interest)
FROM
    wizzard_deposits
WHERE deposit_start_date > '1985-01-01'
GROUP BY deposit_group, is_deposit_expired
ORDER BY deposit_group DESC, is_deposit_expired ASC
;

-- ---------------------------
SELECT  
    department_id, IF(department_id = 1,
    AVG(salary) + 5000, AVG(salary)) 'avg_salary'
FROM
    employees
WHERE salary > 30000 AND manager_id != 42
GROUP BY department_id
ORDER BY department_id; 

-- ----------------------------
SELECT 
    department_id,
    (SELECT DISTINCT
            salary
        FROM
            employees AS e2
            WHERE e1.department_id = e2.department_id
        ORDER BY salary DESC
        LIMIT 2 , 1) AS 'third_highest_salary'
FROM
    employees AS e1
GROUP BY department_id
HAVING third_highest_salary IS NOT NULL
ORDER BY e1.department_id;

-- ----------------------------
SELECT 
    e1.first_name, e1.last_name, e1.department_id
FROM
    employees AS e1
    JOIN(SELECT 
    e2.department_id, AVG(e2.salary) AS salary
FROM 
    employees AS e2
GROUP BY e2.department_id) 
	AS dep_average ON e1.department_id = 
		dep_average.department_id
WHERE e1.salary > dep_average.salary
ORDER BY e1.department_id, e1.employee_id
LIMIT 10;

-- ----------------------------------- -----
SELECT 
    department_id, MAX(salary) AS max_salary
FROM
    employees
GROUP BY department_id
HAVING NOT max_salary BETWEEN 30000 AND 70000
ORDER BY department_id ASC;










