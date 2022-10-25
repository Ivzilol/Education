
CREATE SCHEMA fsd;

-- 01

CREATE TABLE countries(
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(45) NOT NULL
);

CREATE TABLE towns (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(45) NOT NULL,
country_id INT NOT NULL,
CONSTRAINT fk_towns_countries
FOREIGN KEY (country_id)
REFERENCES countries(id)
);

CREATE TABLE stadiums  (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(45) NOT NULL,
capacity INT NOT NULL,
town_id INT NOT NULL,
CONSTRAINT fk_stadiums_towns
FOREIGN KEY (town_id)
REFERENCES towns(id)
);

CREATE TABLE teams   (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(45) NOT NULL,
established DATE NOT NULL,
fan_base BIGINT NOT NULL DEFAULT 0,
stadium_id INT NOT NULL,
CONSTRAINT fk_stadiums_team
FOREIGN KEY (stadium_id)
REFERENCES stadiums(id)
);

CREATE TABLE skills_data (
id INT PRIMARY KEY AUTO_INCREMENT,
dribbling INT DEFAULT 0,
pace INT DEFAULT 0,
passing INT DEFAULT 0,
shooting INT DEFAULT 0,
speed INT DEFAULT 0,
strength INT DEFAULT 0
);

CREATE TABLE coaches (
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(10) NOT NULL,
last_name VARCHAR(20) NOT NULL,
salary DECIMAL(10, 2) NOT NULL DEFAULT 0,
coach_level INT NOT NULL DEFAULT 0
);

CREATE TABLE players (
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(10) NOT NULL,
last_name VARCHAR(20) NOT NULL, 
age INT NOT NULL DEFAULT 0,
position CHAR(1) NOT NULL,
salary DECIMAL(10, 2) NOT NULL DEFAULT 0,
hire_date DATETIME,
skills_data_id INT NOT NULL,
team_id INT,
CONSTRAINT fk_players_teams
FOREIGN KEY (team_id)
REFERENCES teams(id),
CONSTRAINT fk_players_skills_data
FOREIGN KEY (skills_data_id)
REFERENCES skills_data(id)
);

CREATE TABLE players_coaches (
player_id INT,
coach_id INT,
CONSTRAINT fk_maping_player
FOREIGN KEY (player_id)
REFERENCES players (id),
CONSTRAINT fk_maping_coaches
FOREIGN KEY (coach_id)
REFERENCES coaches(id),
CONSTRAINT pk_mappint PRIMARY KEY (player_id, coach_id)
);

-- 2

INSERT INTO coaches (first_name, last_name, salary, coach_level)
(SELECT first_name, last_name, salary * 2, 
character_length(first_name) AS coach_level FROM players WHERE age  >= 45);

-- 3

UPDATE coaches 
SET 
    coach_level = coach_level + 1
WHERE
    first_name LIKE 'A%'
        AND (SELECT 
            COUNT(*)
        FROM
            players_coaches
        WHERE
            coach_id = id) > 0; 

-- 4

DELETE FROM players WHERE age  >= 45;

-- 5

SELECT 
    first_name, age, salary
FROM
    players
ORDER BY salary DESC;

-- 6

SELECT 
    p.id, CONCAT(first_name, ' ', last_name) AS full_name,p.age, p.position, p.hire_date
FROM
    players AS p
JOIN skills_data AS sd
ON p.skills_data_id = sd.id
WHERE sd.strength > 50 
AND position = 'A'
AND p.age < 23 
AND p.hire_date IS NULL
ORDER BY salary ASC, age
;

-- 7

SELECT name AS team_name, established, fan_base,
(SELECT COUNT(*) FROM players WHERE team_id = t.id) AS cnt
FROM teams AS t
ORDER BY cnt DESC, fan_base DESC;

-- 8 

SELECT 
    MAX(sd.speed) AS spd, t.name
FROM
    skills_data AS sd
        RIGHT JOIN
    players AS p ON p.skills_data_id = sd.id
		RIGHT JOIN
	teams AS tm ON p.team_id = tm.id
		RIGHT JOIN
	stadiums AS s ON tm.stadium_id = s.id
		RIGHT JOIN
	towns AS t ON s.town_id = t.id
WHERE tm.name != 'Devify'    
GROUP BY t.name
ORDER BY spd DESC, t.name 
;

-- 9 

SELECT 
    c.name, COUNT(p.id) AS total_count_of_players, 
    SUM(salary) AS total_sum_of_salaries
FROM
    players AS p
        RIGHT JOIN
	teams AS tm ON p.team_id = tm.id
		RIGHT JOIN
	stadiums AS s ON tm.stadium_id = s.id
		RIGHT JOIN
	towns AS t ON s.town_id = t.id
		RIGHT JOIN
    countries AS c ON t.country_id = c.id    
GROUP BY c.id
ORDER BY total_count_of_players DESC, c.name  
;


-- 10 

DELIMITER $$
CREATE FUNCTION `udf_stadium_players_count` (stadiumname VARCHAR(50))
RETURNS INTEGER
DETERMINISTIC
BEGIN

RETURN (SELECT 
     COUNT(p.id) AS cnt
FROM
   players AS p
        RIGHT JOIN
	teams AS tm ON p.team_id = tm.id
		RIGHT JOIN
	stadiums AS s ON tm.stadium_id = s.id
	WHERE s.name = stadiumname);
END
$$
SELECT udf_stadium_players_count('Jaxworks')

-- 11 
DELIMITER $$
CREATE PROCEDURE `udp_find_playmaker` 
(dribblingmin INT, teamname VARCHAR(50))
BEGIN
SELECT 
    CONCAT(p.first_name, ' ', p.last_name) AS full_name,
    age, salary, dribbling, speed, t.name
FROM skills_data AS sd
JOIN players AS p ON p.skills_data_id = sd.id    	
RIGHT JOIN teams AS t 
ON t.id = p.team_id
WHERE t.name = teamname
AND speed > (SELECT AVG(speed) FROM skills_data)
AND dribbling > dribblingmin 
ORDER BY sd.speed DESC
LIMIT 1
;
END $$















