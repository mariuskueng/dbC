-- Aufgabe 2 c) SQL DDL

DROP DATABASE IF EXISTS sandwich;
CREATE DATABASE IF NOT EXISTS sandwich;

USE sandwich;

CREATE TABLE Zutaten (
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name varchar(200) NOT NULL,
  energie varchar(200) NOT NULL,
  eiweiss varchar(200) NOT NULL,
  fett varchar(200) NOT NULL,
  preis float NOT NULL
);

CREATE TABLE Kunden (
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name varchar(200) NOT NULL,
  handynr varchar(200) NOT NULL,
  zahlungsform varchar(200) NOT NULL
);

CREATE TABLE Bestellungen (
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  bestellnummer varchar(200) NOT NULL,
  preis float NOT NULL,
  abholzeit date NOT NULL,
  naehrwertinformation text NOT NULL,
  kunden_id int NOT NULL,
  FOREIGN KEY (kunden_id) REFERENCES Kunden(id)
);

CREATE TABLE Bestellungen_Zutaten (
  id int(11) NOT NULL PRIMARY KEY auto_increment,
  bestellungen_id int NOT NULL,
  zutaten_id int NOT NULL,
  -- add a constrain with cascading to remove all foreignkeys to Bestellungen
  CONSTRAINT fk_bestellungen_id
  FOREIGN KEY (bestellungen_id) REFERENCES Bestellungen(id)
  ON DELETE CASCADE,
  FOREIGN KEY (zutaten_id) REFERENCES Zutaten(id)
);

insert into Zutaten (name, energie, eiweiss, fett, preis) values ("Tomate", "", "", "", 1);
insert into Kunden (name, handynr, zahlungsform) values ("asdf", "0", "visa");
insert into Kunden (name, handynr, zahlungsform) values ("asdf", "077 445 83 97", "visa");
insert into Bestellungen (bestellnummer, preis, abholzeit, naehrwertinformation, kunden_id) values ("B01", 1, CURDATE(), "asdf", 1);
insert into Bestellungen (bestellnummer, preis, abholzeit, naehrwertinformation, kunden_id) values ("B02", 1, CURDATE(), "asdf", 2);
insert into Bestellungen_Zutaten (bestellungen_id, zutaten_id) values (2, 1);
