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
	Bezeichung varchar(25),
	Preis int,
	Typ varchar(25),
	Angeboten boolean,
	Bild BLOB,
	Beschreibung text
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
     bezeichung   VARCHAR(25),
     preis        INT,
     typ          VARCHAR(25),
     angeboten    BOOLEAN,
     bild         BLOB,
     beschreibung TEXT
  );

CREATE TABLE struktur
  (
     otnr  INT REFERENCES teile(tnr),
     utnr  INT REFERENCES teile(tnr),
     menge INT NOT NULL,
     PRIMARY KEY(otnr, utnr)
  );  