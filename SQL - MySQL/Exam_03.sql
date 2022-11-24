
CREATE SCHEMA softuni_imdb;

-- 1

CREATE TABLE countries (
id INT PRIMARY KEY AUTO_INCREMENT, 
name VARCHAR(30) NOT NULL UNIQUE, 
continent VARCHAR(30) NOT NULL,
currency VARCHAR(5) NOT NULL
);

CREATE TABLE genres (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE actors (
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
birthdate DATE NOT NULL,
height INT, 
awards INT,
country_id INT NOT NULL,
CONSTRAINT fk_actors_countries
FOREIGN KEY (country_id)
REFERENCES countries(id)
);

CREATE TABLE movies_additional_info (
id INT PRIMARY KEY AUTO_INCREMENT,
rating DECIMAL(10, 2) NOT NULL,
runtime INT NOT NULL, 
picture_url VARCHAR(80) NOT NULL,
budget DECIMAL(10, 2) DEFAULT 0,
release_date DATE NOT NULL, 
has_subtitles BOOLEAN, 
description TEXT
);

CREATE TABLE movies (
id INT PRIMARY KEY AUTO_INCREMENT,
title VARCHAR(70) NOT NULL UNIQUE,
country_id INT NOT NULL,
CONSTRAINT fk_movies_countries
FOREIGN KEY (country_id)
REFERENCES countries(id),
movie_info_id INT NOT NULL UNIQUE,
CONSTRAINT fk_movies_movies_additional_info
FOREIGN KEY(movie_info_id)
REFERENCES movies_additional_info(id)
);

CREATE TABLE movies_actors(
movie_id INT, 
CONSTRAINT fk_movies_actors_movies
FOREIGN KEY (movie_id)
REFERENCES movies(id),
actor_id INT,
CONSTRAINT fk_movies_actors_actors
FOREIGN KEY (actor_id)
REFERENCES actors(id)
);

CREATE TABLE genres_movies(
genre_id INT,
CONSTRAINT fk_genres_movies_genres
FOREIGN KEY (genre_id)
REFERENCES genres(id),
movie_id INT,
CONSTRAINT fk_genres_movies_movies
FOREIGN KEY (movie_id)
REFERENCES movies(id)
);

-- 2 

INSERT INTO actors(first_name, last_name, birthdate, height, awards, country_id)
SELECT (REVERSE(a.first_name)),
(REVERSE(a.last_name)),
(DATE (a.birthdate - 2)),
(a.height + 10),
(a.country_id),
(3) FROM actors AS a
WHERE a.id <= 10;

-- 3 

UPDATE movies_additional_info AS m

SET m.runtime = m.runtime - 10
WHERE 
	m.id >= 15 AND m.id <= 25;
    
-- 4

DELETE c 
	FROM countries AS c
        LEFT JOIN
    movies AS m ON c.id = m.country_id 
WHERE
    m.country_id IS NULL;
    
-- 5 

SELECT
    c.id, c.name, c.continent, c.currency
FROM
    countries AS c
    ORDER BY c.currency DESC, c.id;

-- 6 

SELECT mi.id, m.title, mi.runtime, mi.budget, mi.release_date
FROM movies_additional_info AS mi
JOIN movies AS m ON mi.id = m.movie_info_id
WHERE YEAR (mi.release_date) >= 1996 AND YEAR (mi.release_date) <= 1999
ORDER BY mi.runtime, mi.id
LIMIT 20;

-- 7 

SELECT 
    CONCAT(a.first_name, ' ', a.last_name) AS full_name,
    CONCAT(REVERSE(a.last_name),
            LENGTH(a.last_name),
            '@cast.com') email,
    (2022 - YEAR(a.birthdate)) age,
    a.height
FROM
    actors AS a
        LEFT JOIN
    movies_actors AS ma ON a.id = ma.actor_id
WHERE
    ma.movie_id IS NULL
ORDER BY a.height;

-- 8

SELECT c.name, COUNT(m.title) movies_count
FROM movies m
JOIN countries AS c ON c.id = m.country_id
GROUP BY c.name 
HAVING movies_count >= 7
ORDER BY c.name DESC;

-- 9 

SELECT m.title, 
	(CASE 
		WHEN mi.rating <= 4 THEN 'poor'
        WHEN mi.rating <= 7 THEN 'good'
        ELSE 'excellent'
        END) AS rating,
        if(mi.has_subtitles, 'english', '-') subtitles,
        mi.budget
FROM movies_additional_info AS mi 
JOIN movies AS m ON mi.id = m.movie_info_id
ORDER BY budget DESC;


-- 10 

DELIMITER $$
CREATE FUNCTION udf_actor_history_movies_count(full_name VARCHAR(50))
    RETURNS INT
    DETERMINISTIC
BEGIN
    DECLARE movies_count INT;
    SET movies_count := (
        SELECT COUNT(g.name) movies
        FROM actors a
                 JOIN movies_actors ma on a.id = ma.actor_id
                 JOIN genres_movies gm on ma.movie_id = gm.movie_id
                 JOIN genres g on g.id = gm.genre_id
        WHERE CONCAT(a.first_name, ' ', a.last_name) = full_name AND g.name = 'History'
        GROUP BY  g.name);
    RETURN movies_count;
END 
$$
SELECT udf_actor_history_movies_count('Stephan Lundberg')  AS 'history_movies';
SELECT udf_actor_history_movies_count('Jared Di Batista')  AS 'history_movies';
SELECT udf_actor_history_movies_count('Lucretia Binks')  AS 'history_movies';

-- 11 

DELIMITER $$
CREATE PROCEDURE `udp_award_movie`(`movie_title` VARCHAR(50))
BEGIN
    UPDATE actors a
        JOIN movies_actors ma on a.id = ma.actor_id
        JOIN movies m on m.id = ma.movie_id
    SET  a.awards = a.awards + 1
    WHERE m.title = movie_title;
END $$
DELIMITER ;

CALL udp_award_movie('Tea For Two');
CALL udp_award_movie('Miss You Can Do It');

 






