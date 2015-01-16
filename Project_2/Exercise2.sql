-- Aufgabe 2 database dbcproject --
-- Queries --
-- author: Marius Küng --
-- dbC HS14 HS14 -- FHNW --

USE dbcproject;

-- a) -- Get all relations each project member and their project coworkers

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

-- Result;

-- 'Anila Bircher','Livio Bieri','IP-314'
-- 'Anila Bircher','Marius Küng','IP-314'
-- 'Livio Bieri','Anila Bircher','IP-314'
-- 'Livio Bieri','Marius Küng','IP-314'
-- 'Livio Bieri','Marius Küng','Bla'
-- 'Livio Bieri','Yves Buschor','Bla'
-- 'Marius Küng','Anila Bircher','IP-314'
-- 'Marius Küng','Livio Bieri','IP-314'
-- 'Marius Küng','Livio Bieri','Bla'
-- 'Marius Küng','Yves Buschor','Bla'
-- 'Marius Küng','Yves Buschor','Test'
-- 'Yves Buschor','Livio Bieri','Bla'
-- 'Yves Buschor','Marius Küng','Bla'
-- 'Yves Buschor','Marius Küng','Test'


-- b) -- Get the amount of coworkers each project member has


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

-- Result:

-- 'Anila Bircher','2'
-- 'Livio Bieri','3'
-- 'Marius Küng','3'
-- 'Yves Buschor','2'

