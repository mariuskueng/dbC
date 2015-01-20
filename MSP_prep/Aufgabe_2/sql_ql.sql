-- Aufgabe 2 d) SQL DDL

-- make delete possible
SET SQL_SAFE_UPDATES = 0;

-- i.
-- Ein Kunde mit der Handynummer 077 445 83 97 löscht all seine Bestellungem die Tomaten als Zutate haben.

DELETE b FROM bestellungen b
INNER JOIN Kunden k ON b.kunden_id = k.id
INNER JOIN Bestellungen_Zutaten bz ON b.id = bz.bestellungen_id
INNER JOIN Zutaten z ON bz.zutaten_id = z.id
WHERE k.handynr = "077 445 83 97"
AND z.name = "Tomate";

-- ii. Schreiben Sie einen SQL-Ausdruck, welcher den Totalpreis aller Bestellungen aller Kunden in der Datenbank ausgibt.
-- Ihre Lösung soll sich auf Ihre in Teilaufgabe c) definierte Datenbank beziehen.

SELECT k.name , SUM(b.preis) from Bestellungen b
INNER JOIN Kunden k ON k.id = b.kunden_id
WHERE k.id = b.kunden_id
GROUP BY k.name;
