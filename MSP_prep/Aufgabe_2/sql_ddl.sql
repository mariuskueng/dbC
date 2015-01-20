-- Aufgabe 2 c) SQL DDL

CREATE TABLE Zutaten (
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name varchar(200) NOT NULL,
  energie varchar(200) NOT NULL,
  eiweiss varchar(200) NOT NULL,
  fett varchar(200) NOT NULL,
  preis float NOT NULL
);

CREATE TABLE Bestellungen (
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  bestellnummer varchar(200) NOT NULL,
  preis float NOT NULL,
  abholzeit date NOT NULL,
  naehrwertinformation text NOT NULL,
  FOREIGN KEY (kunden_id) REFERENCES Kunden(id)
);

CREATE TABLE Kunden (
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name varchar(200) NOT NULL,
  handynr int NOT NULL,
  zahlungsform varchar(200) NOT NULL
);

CREATE TABLE Bestellungen_Zutaten (
  id int(11) NOT NULL PRIMARY KEY auto_increment,
  bestellungen_id int NOT NULL,
  zutaten_id int NOT NULL,
  FOREIGN KEY (bestellungen_id) REFERENCES Bestellungen(id),
  FOREIGN KEY (zutaten_id) REFERENCES Zutaten(id)
);
