-- Aufgabe 1 database dbcproject --
-- Queries --
-- author: Marius Küng --
-- dbC HS14 HS14 -- FHNW --

USE dbcproject;

-- Aufgabe d)

-- SELECT * FROM Persons;
-- SELECT * FROM Projects;
-- SELECT * FROM Project_Teams;


-- Aufgabe d) a. Get all project titles from User (Marius Küng)
-- SELECT title FROM Projects WHERE id = (SELECT project_id FROM Project_Teams WHERE person_id=(SELECT id FROM Persons WHERE name="Marius Küng"));

SELECT person.name, project.title
FROM Projects project
INNER JOIN Project_Teams team
	ON project.id=team.project_id
INNER JOIN Persons person
	ON team.person_id=person.id
WHERE person.name="Marius Küng";

-- 'Marius Küng','IP-314'
-- 'Marius Küng','Bla'
-- 'Marius Küng','Test'



-- Aufgabe d) b. Get all emails from persons who are currently working on a project

SELECT person.email
FROM Persons person
INNER JOIN Project_Teams team
	on person.id=team.person_id
INNER JOIN Projects project
	on team.project_id=project.id
WHERE project.enddate>=NOW()
GROUP BY person.email
ORDER BY person.email;

-- 'anila.bircher@students.fhnw.ch'
-- 'livio.bieri@students.fhnw.ch'
-- 'marius.kueng@students.fhnw.ch'
-- 'yves.buschor@students.fhnw.ch'


-- Aufgabe d) c. Get the amount of projects each person is working on

SELECT person.name,
	COUNT(team.person_id)
FROM Persons person
INNER JOIN Project_Teams team
	on person.id=team.person_id
INNER JOIN Projects project
	on team.project_id=project.id
WHERE project.enddate>=NOW()
GROUP BY person.name
ORDER BY person.name;

-- 'Anila Bircher','1'
-- 'Livio Bieri','2'
-- 'Marius Küng','3'
-- 'Yves Buschor','2'

