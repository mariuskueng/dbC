-- Aufgabe 1 database dbcproject --
-- Queries --
-- author: Marius Küng --
-- dbC HS14 HS14 -- FHNW --

USE dbcproject;

-- Aufgabe d)

SELECT * FROM Persons;
SELECT * FROM Projects;
SELECT * FROM Project_Teams;


-- USE INNER JOINS AND GROUP BY FOR PERSON ID!

-- Aufgabe d) a. Get all project titles from User (Marius Küng)
-- SELECT title FROM Projects WHERE id = (SELECT project_id FROM Project_Teams WHERE person_id=(SELECT id FROM Persons WHERE name="Marius Küng"));

SELECT person.name, project.title
FROM Projects project
INNER JOIN Project_Teams team 
	ON project.id=team.project_id
INNER JOIN Persons person
	ON team.person_id=person.id
WHERE person.name="Marius Küng";


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