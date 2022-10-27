SELECT 
    e.employee_id, e.job_title, a.address_id, a.address_text
FROM
    employees AS e
        JOIN
    addresses AS a ON e.address_id = a.address_id 
    ORDER BY a.address_id ASC
    LIMIT 5;
    
-- 4 
SELECT 
    e.employee_id, e.first_name, e.salary, d.name
FROM
    employees AS e
        JOIN
    departments AS d ON e.department_id = d.department_id
	WHERE e.salary > 15000
    ORDER BY d.department_id DESC
    LIMIT 5;

-- 7

SELECT  
    e.employee_id, e.first_name, p.name
FROM
    employees AS e
        JOIN
    employees_projects AS ep ON e.employee_id = ep.employee_id
        JOIN
    projects AS p ON p.project_id = ep.project_id
	WHERE DATE(p.start_date) > '2002-08-13'	
		AND p.end_date IS NULL
	ORDER BY e.first_name ASC, p.name ASC 
    LIMIT 5;

-- 9 

SELECT 
    e.employee_id, e.first_name, e.manager_id, m.first_name
FROM
    employees AS e
    JOIN employees AS m ON e.manager_id = m.employee_id
    WHERE e.manager_id IN(3,7)
    ORDER BY e.first_name ASC;
 
-- 10

SELECT 
    e.employee_id, 
    CONCAT(e.first_name, ' ', e.last_name) AS 'employee_name',
    CONCAT(m.first_name, ' ', m.last_name) AS 'manager_name',
    d.name
    
FROM
    employees AS e 
		JOIN
	employees AS m ON e.manager_id = m.employee_id
		JOIN
	departments AS d ON e.department_id = d.department_id
ORDER BY e.employee_id
LIMIT 5;

-- 11 

SELECT 
    AVG(e.salary) AS 'min_average_salary'
FROM
    employees AS e
GROUP BY department_id
ORDER BY min_average_salary
LIMIT 1;

-- 12

SELECT 
    c.country_code, m.mountain_range, p.peak_name, p.elevation
FROM
    countries AS c
        JOIN
    mountains_countries AS mc ON mc.country_code = c.country_code
        JOIN
    mountains AS m ON m.id = mc.mountain_id
        JOIN
    peaks AS p ON p.mountain_id = m.id
    WHERE c.country_code LIKE 'BG' AND elevation > 2835
ORDER BY elevation DESC;


-- 13 
SELECT 
	c.country_code,
    COUNT(mc.mountain_id) AS 'mountain_range'
FROM
    countries AS c
        JOIN
    mountains_countries AS mc ON c.country_code = mc.country_code
		
GROUP BY c.country_code
HAVING c.country_code IN ('BG', 'RU', 'US')
ORDER BY mountain_range DESC;

-- 14 

SELECT 
    c.country_name,
    r.river_name
FROM
    countries AS c 
		LEFT JOIN 
	countries_rivers AS cr ON c.country_code = cr.country_code
		LEFT JOIN
    rivers AS r ON r.id = cr.river_id    
		JOIN 
	continents AS con ON con.continent_code = c.continent_code 
WHERE c.continent_code LIKE 'AF'
ORDER BY c.country_name ASC
LIMIT 5;

-- 15

SELECT 
    c.continent_code,
    c.currency_code,
    COUNT(*) AS 'currency_usage'
FROM
    countries AS c
GROUP BY c.continent_code , c.currency_code
HAVING currency_usage > 1
    AND currency_usage = (SELECT 
		COUNT(*) 'most_used_curr'
    FROM
        countries AS c2
    WHERE
        c2.continent_code = c.continent_code
    GROUP BY c2.currency_code
    ORDER BY most_used_curr DESC
    LIMIT 1)
ORDER BY c.continent_code , c.currency_code;

-- 17

SELECT 
    c.country_name,
    MAX(p.elevation) AS 'highest_peak_elevation',
    MAX(r.length) AS 'longest_river_length'
FROM
    countries AS c
        LEFT JOIN
    mountains_countries AS mc ON mc.country_code = c.country_code
		LEFT JOIN 
    peaks AS p ON mc.mountain_id = p.mountain_id    
		LEFT JOIN 
    countries_rivers AS cr ON cr.country_code = c.country_code
		LEFT JOIN 
    rivers AS r ON r.id = cr.river_id
GROUP BY c.country_code
ORDER BY highest_peak_elevation DESC, longest_river_length DESC, c.country_name ASC
LIMIT 5;    
		










