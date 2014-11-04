-- Aufgabe 2 database dbcproject --
-- Queries --
-- author: Marius Küng --
-- dbC HS14 HS14 -- FHNW --

USE dbcproject;

-- a) --

SELECT 
	person.name AS person,
	coworker.name AS coworker,
	projects.title AS title

FROM Project_Teams e

INNER JOIN Project_Teams cw ON cw.project_id = e.project_id
INNER JOIN Persons person ON person.id = e.person_id
INNER JOIN Persons coworker ON coworker.id = cw.person_id
INNER JOIN Projects projects ON Projects.id = e.project_id

WHERE person.id != coworker.id
ORDER BY person.name, coworker.name
;


-- b) --


SELECT person, COUNT(*) as amount_coworkers FROM

(SELECT DISTINCT
	person.name AS person,
	coworker.name AS coworker

FROM Project_Teams e

INNER JOIN Project_Teams cw ON cw.project_id = e.project_id
INNER JOIN Persons person ON person.id = e.person_id
INNER JOIN Persons coworker ON coworker.id = cw.person_id
INNER JOIN Projects projects ON Projects.id = e.project_id

WHERE person.id != coworker.id) count_coworkers
GROUP BY person
;
