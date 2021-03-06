-- Generated by Oracle SQL Developer Data Modeler 4.1.5.907
--   at:        2018-01-14 14:32:08 CET
--   site:      DB2/UDB 9
--   type:      DB2/UDB 9




CREATE
  TABLE Dyrektor
  (
    id INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY  ,
    Imie     VARCHAR (40) ,
    Nazwisko VARCHAR (40)
  ) ;
ALTER TABLE Dyrektor ADD CONSTRAINT Dyrektor_PK PRIMARY KEY (id) ;


CREATE
  TABLE Klasa
  (
    id INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY  ,
    Nazwa         VARCHAR (40) ,
    id_nauczyciel INTEGER NOT NULL
  ) ;
ALTER TABLE Klasa ADD CONSTRAINT Klasa_PK PRIMARY KEY (id) ;


CREATE
  TABLE Nauczyciel
  (
    id INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY  ,
    Imie     VARCHAR (40) ,
    Nazwisko VARCHAR (40)
  ) ;
ALTER TABLE Nauczyciel ADD CONSTRAINT Nauczyciel_PK PRIMARY KEY (id) ;


CREATE
  TABLE Ocena
  (
    id INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY  ,
    id_sprawdzian INTEGER NOT NULL ,
    id_uczen      INTEGER NOT NULL ,
    Wartosc       DECIMAL (3,2)
  ) ;
ALTER TABLE Ocena ADD CONSTRAINT Ocena_PK PRIMARY KEY (id) ;


CREATE
  TABLE Przedmiot
  (
    id INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY  ,
    Nazwa VARCHAR (40)
  ) ;
ALTER TABLE Przedmiot ADD CONSTRAINT Przedmiot_PK PRIMARY KEY (id) ;


CREATE
  TABLE Przedmiot_klas
  (
    id INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY  ,
    id_przedmiot  INTEGER NOT NULL ,
    id_klasa      INTEGER NOT NULL ,
    id_nauczyciel INTEGER NOT NULL
  ) ;
ALTER TABLE Przedmiot_klas ADD CONSTRAINT Przedmiot_klas_PK PRIMARY KEY (id) ;


CREATE
  TABLE Przydzialy
  (
    id INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY  ,
    id_nauczyciel INTEGER NOT NULL ,
    id_przedmiot  INTEGER NOT NULL
  ) ;
ALTER TABLE Przydzialy ADD CONSTRAINT Przydzialy_PK PRIMARY KEY (id) ;


CREATE
  TABLE Sprawdzian
  (
    id INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY  ,
    id_przedmiotu_klasa INTEGER NOT NULL ,
    Nazwa               VARCHAR (40) ,
    Data_spr              DATE
  ) ;
ALTER TABLE Sprawdzian ADD CONSTRAINT Sprawdzian_PK PRIMARY KEY (id) ;


CREATE
  TABLE Uczen
  (
    id INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY ,
    Imie     VARCHAR (40) ,
	Nazwisko VARCHAR (40) ,
    id_klasa INTEGER NOT NULL
  ) ;
ALTER TABLE Uczen ADD CONSTRAINT Uczen_PK PRIMARY KEY (id) ;


ALTER TABLE Klasa ADD CONSTRAINT Klasa_Nauczyciel_FK FOREIGN KEY (
id_nauczyciel ) REFERENCES Nauczyciel ( id ) ON
DELETE CASCADE ON
UPDATE
  NO ACTION  ;

ALTER TABLE Ocena ADD CONSTRAINT Ocena_Sprawdzian_FK FOREIGN KEY (
id_sprawdzian ) REFERENCES Sprawdzian ( id ) ON
DELETE CASCADE ON
UPDATE
  NO ACTION ;

ALTER TABLE Ocena ADD CONSTRAINT Ocena_Uczen_FK FOREIGN KEY ( id_uczen )
REFERENCES Uczen ( id ) ON
DELETE CASCADE ON
UPDATE
  NO ACTION ;

ALTER TABLE Przedmiot_klas ADD CONSTRAINT Przedmiot_klas_Klasa_FK FOREIGN KEY (
id_klasa ) REFERENCES Klasa ( id ) ON
DELETE CASCADE ON
UPDATE
  NO ACTION ;

ALTER TABLE Przedmiot_klas ADD CONSTRAINT Przedmiot_klas_Nauczyciel_FK FOREIGN
KEY ( id_nauczyciel ) REFERENCES Nauczyciel ( id ) ON
DELETE CASCADE ON
UPDATE
  NO ACTION ;

ALTER TABLE Przedmiot_klas ADD CONSTRAINT Przedmiot_klas_Przedmiot_FK FOREIGN
KEY ( id_przedmiot ) REFERENCES Przedmiot ( id ) ON
DELETE CASCADE ON
UPDATE
  NO ACTION ;

ALTER TABLE Przydzialy ADD CONSTRAINT Przydzialy_Nauczyciel_FK FOREIGN KEY (
id_nauczyciel ) REFERENCES Nauczyciel ( id ) ON
DELETE CASCADE ON
UPDATE
  NO ACTION ;

ALTER TABLE Przydzialy ADD CONSTRAINT Przydzialy_Przedmiot_FK FOREIGN KEY (
id_przedmiot ) REFERENCES Przedmiot ( id ) ON
DELETE CASCADE ON
UPDATE
  NO ACTION ;

ALTER TABLE Sprawdzian ADD CONSTRAINT Sprawdzian_Przedmiot_klas_FK FOREIGN KEY
( id_przedmiotu_klasa ) REFERENCES Przedmiot_klas ( id ) ON
DELETE CASCADE ON
UPDATE
  NO ACTION ;

ALTER TABLE Uczen ADD CONSTRAINT Uczen_Klasa_FK FOREIGN KEY ( id_klasa )
REFERENCES Klasa ( id ) ON
DELETE CASCADE ON
UPDATE
  NO ACTION ;


-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                             9
-- CREATE INDEX                             0
-- ALTER TABLE                             19
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE STRUCTURED TYPE                   0
-- CREATE ALIAS                             0
-- CREATE BUFFERPOOL                        0
-- CREATE DATABASE                          0
-- CREATE DISTINCT TYPE                     0
-- CREATE INSTANCE                          0
-- CREATE DATABASE PARTITION GROUP          0
-- CREATE SCHEMA                            0
-- CREATE SEQUENCE                          0
-- CREATE TABLESPACE                        0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0

CREATE TABLE Users
(
id INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
Login VARCHAR(100) UNIQUE,
Password VARCHAR(100),
id_nauczyciel int UNIQUE,
id_uczen int UNIQUE,
id_dyrektor int UNIQUE
);

CREATE TABLE Roles
(
id INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
Login VARCHAR(100),
Role_name VARCHAR(100)
);

ALTER TABLE Users ADD CONSTRAINT Users_Nauczyciel_FK FOREIGN KEY (id_nauczyciel ) 
REFERENCES Nauczyciel ( id ) ON
DELETE CASCADE ON
UPDATE
  NO ACTION ;

ALTER TABLE Users ADD CONSTRAINT Users_Uczen_FK FOREIGN KEY( id_uczen ) 
REFERENCES Uczen ( id ) ON
DELETE CASCADE ON
UPDATE
  NO ACTION ;

ALTER TABLE Users ADD CONSTRAINT Users_Dyrektor_FK FOREIGN KEY ( id_dyrektor )
REFERENCES Dyrektor ( id ) ON
DELETE CASCADE ON
UPDATE
  NO ACTION ;

insert into Dyrektor(Imie,Nazwisko) values ('Kamil','Przysowa');

insert into Nauczyciel(Imie,Nazwisko) values ('Tomasz','Las');
insert into Nauczyciel(Imie,Nazwisko) values ('Ilona','Stag');
insert into Nauczyciel(Imie,Nazwisko) values ('Leroy','Jenkins');
insert into Nauczyciel(Imie,Nazwisko) values ('Karol','Starg');
insert into Nauczyciel(Imie,Nazwisko) values ('Milena','Lakas');
insert into Nauczyciel(Imie,Nazwisko) values ('Donald','Trump');
insert into Nauczyciel(Imie,Nazwisko) values ('Tokyo','Nairobi');

insert into Klasa(Nazwa,id_nauczyciel) values ('Sportowa',1);
insert into Klasa(Nazwa,id_nauczyciel) values ('Mat-fiz',2);

insert into Przedmiot(Nazwa) values ('W-f');
insert into Przedmiot(Nazwa) values ('Matematyka');
insert into Przedmiot(Nazwa) values ('Fizyka');
insert into Przedmiot(Nazwa) values ('J.Polski');
insert into Przedmiot(Nazwa) values ('J.Angielski');
insert into Przedmiot(Nazwa) values ('Biologia');
insert into Przedmiot(Nazwa) values ('Chemia');

insert into Przedmiot_klas(id_przedmiot,id_klasa,id_nauczyciel) values (1,1,1);
insert into Przedmiot_klas(id_przedmiot,id_klasa,id_nauczyciel) values (2,1,2);
insert into Przedmiot_klas(id_przedmiot,id_klasa,id_nauczyciel) values (3,1,3);
insert into Przedmiot_klas(id_przedmiot,id_klasa,id_nauczyciel) values (4,1,4);
insert into Przedmiot_klas(id_przedmiot,id_klasa,id_nauczyciel) values (5,1,5);
insert into Przedmiot_klas(id_przedmiot,id_klasa,id_nauczyciel) values (6,1,6);
insert into Przedmiot_klas(id_przedmiot,id_klasa,id_nauczyciel) values (7,1,7);
insert into Przedmiot_klas(id_przedmiot,id_klasa,id_nauczyciel) values (1,2,1);
insert into Przedmiot_klas(id_przedmiot,id_klasa,id_nauczyciel) values (2,2,2);
insert into Przedmiot_klas(id_przedmiot,id_klasa,id_nauczyciel) values (3,2,3);
insert into Przedmiot_klas(id_przedmiot,id_klasa,id_nauczyciel) values (4,2,4);
insert into Przedmiot_klas(id_przedmiot,id_klasa,id_nauczyciel) values (5,2,5);
insert into Przedmiot_klas(id_przedmiot,id_klasa,id_nauczyciel) values (6,2,6);
insert into Przedmiot_klas(id_przedmiot,id_klasa,id_nauczyciel) values (7,2,7);

insert into Przydzialy(id_nauczyciel,id_przedmiot) values (1,1);
insert into Przydzialy(id_nauczyciel,id_przedmiot) values (2,2);
insert into Przydzialy(id_nauczyciel,id_przedmiot) values (3,3);
insert into Przydzialy(id_nauczyciel,id_przedmiot) values (4,4);
insert into Przydzialy(id_nauczyciel,id_przedmiot) values (5,5);
insert into Przydzialy(id_nauczyciel,id_przedmiot) values (6,6);
insert into Przydzialy(id_nauczyciel,id_przedmiot) values (7,7);
insert into Przydzialy(id_nauczyciel,id_przedmiot) values (3,5);
insert into Przydzialy(id_nauczyciel,id_przedmiot) values (4,7);

insert into Sprawdzian(id_przedmiotu_klasa,Nazwa,Data_spr) values(1,'Test sprawnosci','08/04/2017');
insert into Sprawdzian(id_przedmiotu_klasa,Nazwa,Data_spr) values(2,'Ulamki','12/03/2017');
insert into Sprawdzian(id_przedmiotu_klasa,Nazwa,Data_spr) values(3,'Termodynamika','01/05/2017');

insert into Uczen(Imie,Nazwisko,id_klasa) values('Karol','Przybylski',1);
insert into Uczen(Imie,Nazwisko,id_klasa) values('Tomasz','Wysocki',1);
insert into Uczen(Imie,Nazwisko,id_klasa) values('Natalia','Kowalska',1);
insert into Uczen(Imie,Nazwisko,id_klasa) values('Oliwier','Tomczak',1);
insert into Uczen(Imie,Nazwisko,id_klasa) values('Kacper','Olszewski',1);
insert into Uczen(Imie,Nazwisko,id_klasa) values('Maciej','Krawczyk',1);
insert into Uczen(Imie,Nazwisko,id_klasa) values('Piotr','Krawczyk',1);
insert into Uczen(Imie,Nazwisko,id_klasa) values('Kamila','Szymczak',1);
insert into Uczen(Imie,Nazwisko,id_klasa) values('Kajetan','Grabowski',1);
insert into Uczen(Imie,Nazwisko,id_klasa) values('Natalia','Rutkowska',1);
insert into Uczen(Imie,Nazwisko,id_klasa) values('Aleksandra','Kaczmarczyk',2);
insert into Uczen(Imie,Nazwisko,id_klasa) values('Maja','Gajewska',2);
insert into Uczen(Imie,Nazwisko,id_klasa) values('Hubert','Jarosz',2);
insert into Uczen(Imie,Nazwisko,id_klasa) values('Hubert','Szewczyk',2);
insert into Uczen(Imie,Nazwisko,id_klasa) values('Ernest','Nowicki',2);
insert into Uczen(Imie,Nazwisko,id_klasa) values('Szymon','Morawski',2);
insert into Uczen(Imie,Nazwisko,id_klasa) values('Zofia','Ciesielska',2);
insert into Uczen(Imie,Nazwisko,id_klasa) values('Eryk','Kowal',2);
insert into Uczen(Imie,Nazwisko,id_klasa) values('Emilia','Krupa',2);
insert into Uczen(Imie,Nazwisko,id_klasa) values('Nadia','Borowska',2);

insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(1,1,4);
insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(1,2,3);
insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(1,3,4);
insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(1,4,3);
insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(1,5,4);
insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(1,6,3);
insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(1,7,4);
insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(1,8,3);
insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(1,9,4);
insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(1,10,3);
insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(2,11,4);
insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(2,12,5);
insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(2,13,4);
insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(2,14,3);
insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(2,15,4);
insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(2,16,5);
insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(2,17,4);
insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(2,18,3);
insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(2,19,4);
insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(2,20,5);
insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(3,1,5);
insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(3,2,4);
insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(3,3,3);
insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(3,4,4);
insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(3,5,5);
insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(3,6,4);
insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(3,7,3);
insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(3,8,4);
insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(3,9,5);
insert into Ocena(id_sprawdzian,id_uczen,Wartosc) values(3,10,4);

INSERT INTO Users(Login,Password,id_dyrektor) VALUES ('Dyrektor','Dyrektor',1);
INSERT INTO Users(Login,Password,id_nauczyciel) VALUES ('Nauczyciel','Nauczyciel',1);
INSERT INTO Users(Login,Password,id_uczen) VALUES ('Uczen','Uczen',1);
INSERT INTO Users(Login,Password) VALUES ('Admin','Admin');

INSERT INTO Roles(Login,Role_name) VALUES ('Dyrektor','Dyrektor');
INSERT INTO Roles(Login,Role_name) VALUES ('Nauczyciel','Nauczyciel');
INSERT INTO Roles(Login,Role_name) VALUES ('Uczen','Uczen');
INSERT INTO Roles(Login,Role_name) VALUES ('Admin','Admin');