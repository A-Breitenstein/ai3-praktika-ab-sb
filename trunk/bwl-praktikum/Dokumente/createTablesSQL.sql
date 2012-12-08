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
	menge     INT,
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
     menge     INT,
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
  
  
  CREATE TABLE monitorentry
  (
     id    INT PRIMARY KEY auto_increment,
     hits     INT,
     daily   BOOLEAN,
     page    varchar(255),
     timestamp BIGINT
  );
  
  CREATE TABLE IF NOT EXISTS `auftrag` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TeilNr` int(11) NOT NULL,
  `Datum` int(11) NOT NULL,
  `Menge` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
  );
  CREATE TABLE IF NOT EXISTS `bedarf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TeilNr` int(11) NOT NULL,
  `Datum` int(11) NOT NULL,
  `Menge` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
  );
  CREATE TABLE IF NOT EXISTS `bedarfsableitung` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `OTNr` int(11) NOT NULL,
  `UTNr` int(11) NOT NULL,
  `AuftragsDatum` int(11) NOT NULL,
  `BedarfsDatum` int(11) NOT NULL,
  `MengeUT` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
  );
  CREATE TABLE IF NOT EXISTS `bedarfsdeckung` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TeilNr` int(11) NOT NULL,
  `BedarfsDatum` int(11) NOT NULL,
  `AuftragsDatum` int(11) NOT NULL,
  `Menge` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
  );
  
  
INSERT INTO `teile` (`Bezeichnung` , `Preis` , `Typ` , `Angeboten` , `Bild` , `Beschreibung` )
VALUES ('Zylinder A4', '90.80', 'Kleinteil', NULL , 'application/view/images/170px-Cylinder.svg.png', 'Maße 55mm 106mm 22mm' ) 

INSERT INTO `teile` (`Bezeichnung` , `Preis` , `Typ` , `Angeboten` , `Bild` , `Beschreibung` ) VALUES ('BMW', '30000.00', 'Auto', true , 'application/view/images/bmw.jpg', 'Ein Auto das fähr, Brum Brum. Aus Bayern!!!' )
INSERT INTO `teile` (`Bezeichnung` , `Preis` , `Typ` , `Angeboten` , `Bild` , `Beschreibung` ) VALUES ('Reifen', '450.23', 'Autozubehoer', true , 'application/view/images/reifen.jpg', 'Ist Rund und passt an fast jedes Auto.' )
INSERT INTO `teile` (`Bezeichnung` , `Preis` , `Typ` , `Angeboten` , `Bild` , `Beschreibung` ) VALUES ('Hupe', '34.56', 'Autozubehoer', false , 'application/view/images/hupe.jpg', 'Macht geraeusche und ist laut.' )
INSERT INTO `teile` (`Bezeichnung` , `Preis` , `Typ` , `Angeboten` , `Bild` , `Beschreibung` ) VALUES ('Scheinwerfer', '23.53', 'Autozubehoer', false , 'application/view/images/scheinwerfer.jpg', 'Hell, achtung!!! Nicht direkt reingucken!' )
INSERT INTO `teile` (`Bezeichnung` , `Preis` , `Typ` , `Angeboten` , `Bild` , `Beschreibung` ) VALUES ('Jaguar', '23000000.00', 'Auto', true , 'application/view/images/jaguar.jpg', 'Ein sehr tolles und altes Auto' )
INSERT INTO `teile` (`Bezeichnung` , `Preis` , `Typ` , `Angeboten` , `Bild` , `Beschreibung` ) VALUES ('Putzmittel', '9.67', 'Autoputzmittel', true , 'application/view/images/putzmittel.jpg', 'Macht das Auto super duper glitzernd.' )
INSERT INTO `teile` (`Bezeichnung` , `Preis` , `Typ` , `Angeboten` , `Bild` , `Beschreibung` ) VALUES ('Lenkrad', '45.23', 'Autozubehoer', false , 'application/view/images/lenkrad.jpg', 'Ohne das Teil wirds schwer.' )




INSERT INTO `teile` (`Bezeichnung` , `Preis` , `Typ` , `Angeboten` , `Bild` , `Beschreibung` ) 
VALUES ('Autositz', '699.25', 'Autozubehoer', true , 'application/view/images/autositz.jpg', 'Ohne das Teil wirds schwer.' );


INSERT INTO `teile` (`Bezeichnung` , `Preis` , `Typ` , `Angeboten` , `Bild` , `Beschreibung` )
VALUES ('Kopfstütze', '55.55', 'Autozubehoer', false , 'application/view/images/kopfstuetze.jpg', 'Haelt den Kopf' );


INSERT INTO `teile` (`Bezeichnung` , `Preis` , `Typ` , `Angeboten` , `Bild` , `Beschreibung` )
VALUES ('Sitzschiene', '22.50', 'Autozubehoer', false , 'application/view/images/sitzschiene.jpg', 'Zum anlehnen');


INSERT INTO `teile` (`Bezeichnung` , `Preis` , `Typ` , `Angeboten` , `Bild` , `Beschreibung` )
VALUES ('Rueckenlehne', '90.99', 'Autozubehoer', false , 'application/view/images/rueckenlehne.jpg', 'Ohne das Teil wirds schwer.' );


INSERT INTO `teile` (`Bezeichnung` , `Preis` , `Typ` , `Angeboten` , `Bild` , `Beschreibung` )
VALUES ('U-Stahl', '70.50', 'Autozubehoer', false , 'application/view/images/ustahl.jpg', 'Ohne das Teil wirds schwer.' );


INSERT INTO `teile` (`Bezeichnung` , `Preis` , `Typ` , `Angeboten` , `Bild` , `Beschreibung` )
VALUES ('Schrauben', '10.25', 'Autozubehoer', false , 'application/view/images/schrauben.jpg', 'Ohne das Teil wirds schwer.' );


INSERT INTO `teile` (`Bezeichnung` , `Preis` , `Typ` , `Angeboten` , `Bild` , `Beschreibung` )
VALUES ('Motor', '699.25', 'Autozubehoer', false , 'application/view/images/motor.jpg', 'Ohne das Teil wirds schwer.' );

