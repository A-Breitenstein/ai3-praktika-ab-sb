create table kunde(
	KundenNr int primary key AUTO_INCREMENT,
	Name varchar(25),
	Vorname varchar(25),
	eMail varchar(50),
	ErstellDatum TimeStamp
	);

create table bestellung(
	BestellNr int primary key AUTO_INCREMENT,
	KundenNr int references kunde(KundenNr),
    BestellDatum Timestamp
	);
	
create table bestellung_teile(
	BestellNr int references bestellung(BestellNr),
	TNr int references teile(TNr),
	
	primary key(BestellNr, TNr)
	);
	
create table teile(
	TNr int primary key AUTO_INCREMENT,
	Bezeichung varchar(50),
	Preis int,
	Typ varchar(25),
	Verkaufstyp varchar(25),
	Bild BLOB,
	Beschreibung varchar(25)
	);
	
create table struktur(
	OTNr int references teile(TNr),
	UTNr int references teile(TNr),
	menge int NOT NULL,
	
	primary key(OTNr, UTNr)
	);
	
::Formatted SQL Code ------------------
CREATE TABLE kunde
  (
     kundennr     INT PRIMARY KEY auto_increment,
     name         VARCHAR(25),
     vorname      VARCHAR(25),
     email        VARCHAR(50),
     erstelldatum TIMESTAMP
  );

CREATE TABLE bestellung
  (
     bestellnr    INT PRIMARY KEY auto_increment,
     kundennr     INT REFERENCES kunde(kundennr),
     bestelldatum TIMESTAMP
  );

CREATE TABLE bestellung_teile
  (
     bestellnr INT REFERENCES bestellung(bestellnr),
     tnr       INT REFERENCES teile(tnr),
     PRIMARY KEY(bestellnr, tnr)
  );

CREATE TABLE teile
  (
     tnr          INT PRIMARY KEY auto_increment,
     bezeichung   VARCHAR(50),
     preis        INT,
     typ          VARCHAR(25),
     verkaufstyp  VARCHAR(25),
     bild         BLOB,
     beschreibung VARCHAR(25)
  );

CREATE TABLE struktur
  (
     otnr  INT REFERENCES teile(tnr),
     utnr  INT REFERENCES teile(tnr),
     menge INT NOT NULL,
     PRIMARY KEY(otnr, utnr)
  );  