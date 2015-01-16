-- create database dbcproject2 --
-- author: Marius Küng --
-- dbC HS14 HS14 -- FHNW --

-- Aufgabe 1

-- Aufgabe a)
DROP DATABASE IF EXISTS dbcproject;
CREATE DATABASE IF NOT EXISTS dbcproject;

-- Aufgabe b)
USE dbcproject;

DROP TABLE IF EXISTS Projects;
DROP TABLE IF EXISTS Persons;

CREATE TABLE IF NOT EXISTS Persons (
    id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name varchar(200) NOT NULL,
    email varchar(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS Projects (
    id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title varchar(200),
    startdate DATE,
    enddate DATE,
    revenue double
);

CREATE TABLE IF NOT EXISTS Project_Teams (
	id int NOT NULL PRIMARY KEY auto_increment,
	person_id int NOT NULL,
	project_id int NOT NULL,
	FOREIGN KEY (project_id) REFERENCES projects(id),
	FOREIGN KEY (person_id) REFERENCES persons(id)
);

-- Aufgabe c)

-- Insert data into persons table
INSERT INTO Persons (name, email) VALUES
("Marius Küng", "marius.kueng@students.fhnw.ch"),
("Livio Bieri", "livio.bieri@students.fhnw.ch"),
("Yves Buschor", "yves.buschor@students.fhnw.ch"),
("Anila Bircher", "anila.bircher@students.fhnw.ch");

-- Insert data into project table
INSERT INTO Projects (title, startdate, enddate, revenue) VALUES
("IP-314", "2014-09-16", "2015-01-01", 1000),
("Bla", "2014-09-16", "2015-01-01", 1000),
("Test", "2014-09-16", "2015-01-01", 1000),
("Closed Project", "2014-09-16", "2014-03-01", 1000);

-- Assign persons to projects
INSERT INTO Project_Teams (person_id, project_id) values
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(2, 2),
(3, 2),
(3, 3),
(4, 1),
(4, 4);
