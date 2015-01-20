-- Aufgabe 2 d) SQL DDL

-- make delete possible
SET SQL_SAFE_UPDATES = 0;

-- i.
-- Ein Kunde mit der Handynummer 077 445 83 97 löscht all seine Bestellungem die Tomaten als Zutate haben.

delete b from bestellungen b
INNER JOIN Kunden k ON b.kunden_id = k.id
INNER JOIN Bestellungen_Zutaten bz ON b.id = bz.bestellungen_id
INNER JOIN Zutaten z ON bz.zutaten_id = z.id
WHERE k.handynr = "077 445 83 97"
AND z.name = "Tomate";
