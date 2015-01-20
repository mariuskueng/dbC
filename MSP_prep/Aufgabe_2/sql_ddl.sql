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

-- add data
insert into Zutaten (name, energie, eiweiss, fett, preis) values ("Tomate", "", "", "", 2.5);
insert into Zutaten (name, energie, eiweiss, fett, preis) values ("Fleisch", "", "", "", 5);
insert into Zutaten (name, energie, eiweiss, fett, preis) values ("Brot", "", "", "", 3);
insert into Zutaten (name, energie, eiweiss, fett, preis) values ("Kaese", "", "", "", 1.5);
insert into Kunden (name, handynr, zahlungsform) values ("Hans", "0", "visa");
insert into Kunden (name, handynr, zahlungsform) values ("Peter", "077 445 83 97", "visa");
insert into Bestellungen (bestellnummer, preis, abholzeit, naehrwertinformation, kunden_id) values ("B01", 5.6, CURDATE(), "asdf", 1);
insert into Bestellungen (bestellnummer, preis, abholzeit, naehrwertinformation, kunden_id) values ("B02", 10.4, CURDATE(), "asdf", 1);
insert into Bestellungen (bestellnummer, preis, abholzeit, naehrwertinformation, kunden_id) values ("B03", 8.2, CURDATE(), "asdf", 2);
insert into Bestellungen_Zutaten (bestellungen_id, zutaten_id) values (1, 1);
insert into Bestellungen_Zutaten (bestellungen_id, zutaten_id) values (1, 2);
insert into Bestellungen_Zutaten (bestellungen_id, zutaten_id) values (2, 1);
insert into Bestellungen_Zutaten (bestellungen_id, zutaten_id) values (2, 2);
insert into Bestellungen_Zutaten (bestellungen_id, zutaten_id) values (2, 3);
insert into Bestellungen_Zutaten (bestellungen_id, zutaten_id) values (2, 4);
