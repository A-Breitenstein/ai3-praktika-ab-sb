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
	Bezeichnung varchar(25),
	Preis numeric(8,2),
	Typ varchar(25),
	Angeboten boolean,
	Bild varchar(100),
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
     bezeichnung   VARCHAR(25),
     preis        NUMERIC(8,2),
     typ          VARCHAR(25),
     angeboten    BOOLEAN,
     bild         VARCHAR(100),
     beschreibung TEXT
  );

CREATE TABLE struktur
  (
     otnr  INT REFERENCES teile(tnr),
     utnr  INT REFERENCES teile(tnr),
     menge INT NOT NULL,
     PRIMARY KEY(otnr, utnr)
  );  
  
  
  INSERT INTO `teile` (`Bezeichnung` , `Preis` , `Typ` , `Angeboten` , `Bild` , `Beschreibung` )
VALUES ('Zylinder A4', '90.80', 'Kleinteil', NULL , 'view/images/170px-Cylinder.svg.png', 'Maße 55mm 106mm 22mm' ) 