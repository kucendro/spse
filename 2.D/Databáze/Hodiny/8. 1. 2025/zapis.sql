/* Zkušební tabulky pro atomickou databázi z hodin */

/* Vytvoření tabulek */
create table student (ID INTEGER, jmeno VARCHAR(45));
create table ucitel (ID INTEGER, jmeno VARCHAR(45));
create table uci (ID_studenta INTEGER, ID_ucitele INTEGER, predmet VARCHAR(45));
create table knihy (ID INTEGER, ID_studenta INTEGER, nazev VARCHAR(100));

/* Vložení dat. */
INSERT INTO student (ID, jmeno) VALUES (1, 'Petr Blazek');
INSERT INTO student (ID, jmeno) VALUES (2, 'Petr Moravec');
INSERT INTO student (ID, jmeno) VALUES (3, 'Martin Rychly');
INSERT INTO student (ID, jmeno) VALUES (4, 'Pavel Stanek');

INSERT INTO ucitel (ID, jmeno) VALUES (1, 'Simeon Karamazov');
INSERT INTO ucitel (ID, jmeno) VALUES (2, 'Martin Zeman');
INSERT INTO ucitel (ID, jmeno) VALUES (3, 'Kamil Pulpan');
INSERT INTO ucitel (ID, jmeno) VALUES (4, 'David Zak');

INSERT INTO uci (ID_studenta,ID_ucitele, predmet) VALUES (1, 2, 'IZITE');
INSERT INTO uci (ID_studenta,ID_ucitele, predmet) VALUES (2, 1, 'C++');
INSERT INTO uci (ID_studenta,ID_ucitele, predmet) VALUES (3, 2, 'AJ');
INSERT INTO uci (ID_studenta,ID_ucitele, predmet) VALUES (4, 1, 'DS1');
INSERT INTO uci (ID_studenta,ID_ucitele, predmet) VALUES (2, 2, 'IZITE');
INSERT INTO uci (ID_studenta,ID_ucitele, predmet) VALUES (1, 3, 'C++');
INSERT INTO uci (ID_studenta,ID_ucitele, predmet) VALUES (3, 1, 'DS1');
INSERT INTO uci (ID_studenta,ID_ucitele, predmet) VALUES (2, 1, 'DS1');

INSERT INTO knihy (ID,ID_studenta,nazev) VALUES (3001, 3, 'Databazove systemy');
INSERT INTO knihy (ID,ID_studenta,nazev) VALUES (3002, 3, 'MySQL snadno a rychle');
INSERT INTO knihy (ID,ID_studenta,nazev) VALUES (3003, 3, 'Anglictina');
INSERT INTO knihy (ID,ID_studenta,nazev) VALUES (3004, 1, 'Nemcina');
INSERT INTO knihy (ID,ID_studenta,nazev) VALUES (3005, 1, 'SQL za 21 dni');
INSERT INTO knihy (ID,ID_studenta,nazev) VALUES (3006, 3, 'Relacni databaze');
INSERT INTO knihy (ID,ID_studenta,nazev) VALUES (3007, 3, 'Mistrovstvi v SQL');
INSERT INTO knihy (ID,ID_studenta,nazev) VALUES (3008, 1, 'MySQL Programming');

/* Zadání od učitele:*/
/* Upravte tabulky student a ucitel tak, aby příjmení a jméno bylo samostatném sloupečku */

ALTER TABLE student ADD (prijmeni VARCHAR(25));
UPDATE student SET prijmeni='Blazek' WHERE jmeno='Petr Blazek';
UPDATE student SET jmeno='Petr' WHERE prijmeni='Blazek';
UPDATE student SET prijmeni='Moravec' WHERE jmeno='Petr Moravec';
UPDATE student SET jmeno='Petr' WHERE prijmeni='Moravec';
UPDATE student SET prijmeni='Rychly' WHERE jmeno='Martin Rychly';
UPDATE student SET jmeno='Martin' WHERE prijmeni='Rychly';
UPDATE student SET prijmeni='Stanek' WHERE jmeno='Pavel Stanek';
UPDATE student SET jmeno='Pavel' WHERE prijmeni='Stanek';

ALTER TABLE ucitel ADD (prijmeni VARCHAR(25));
UPDATE ucitel SET prijmeni='Karamazov' WHERE jmeno='Simeon Karamazov';
UPDATE ucitel SET jmeno='Simeon' WHERE prijmeni='Karamazov';
UPDATE ucitel SET prijmeni='Zeman' WHERE jmeno='Martin Zeman';
UPDATE ucitel SET jmeno='Martin' WHERE prijmeni='Zeman';
UPDATE ucitel SET prijmeni='Pulpan' WHERE jmeno='Kamil Pulpan';
UPDATE ucitel SET jmeno='Kamil' WHERE prijmeni='Pulpan';
UPDATE ucitel SET prijmeni='Zak' WHERE jmeno='David Zak';
UPDATE ucitel SET jmeno='David' WHERE prijmeni='Zak';


----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

/* 8.1. 2025*/

/* Zadání od učitele*/


/* 1) Z tabulky student vypište sloupec prijmeni s nadpisem Příjmení studentů setříděné vzestupně*/

SELECT prijmeni as "Příjmení studentů" FROM student order by prijmeni;


/* 2) Z tabulky student vypište sloupec jmeno s nadpisem Jménu setříděné sestupně*/

SELECT jmeno as "Jméno studenta" FROM student order by jmeno desc;


/* 3) Z tabulky student vypište všechny atributy studentů, kteří se jmenují Petr */

SELECT * FROM student WHERE jmeno = "Petr";


/* 4) Z tabulky student vypište všechny studenty, které mají ID<2 a jmenují se Petr */

SELECT * FROM student WHERE id <2 AND jmeno = "Petr";


/* 5) Z tabulky student vypište všechna jména studentů s nadpisem Kméno studenta, setříděné a bez duplicit */

SELECT DISTINCT jmeno as "Jméno studenta" FROM student order by jmeno;


/* 6) Z tabulky student vypište počet studentů s nadpisem Počet studentů*/

SELECT COUNT(*) as "Počet studentů" FROM student;


/* 7) Z tabulky student vypište PRIJMENI studentů s názvem příjmení, malými písmeny jmena studentů s názvem jméno. Všechno bude setříděno dle příjmení.*/

SELECT UPPER (prijmeni) as "Příjmení",
	LOWER (jmeno) as "Jméno"
FROM student order by prijmeni;


/* 8) Délka příjmení*/

SELECT jmeno,
	prijmeni,
LENGTH (prijmeni) as "Délka" FROM student
WHERE id<2 AND (jmeno="Petr" OR jmeno="Karel")


/* 9) Vypsat všechna jména a příjmení, kde ID = 1-6 vč. jmenují se Petr nebo Karel a mají příjmení delší než 6 znaků */

SELECT
    jmeno,
    prijmeni
FROM
    student
WHERE
    id BETWEEN 1 AND 6
    AND (jmeno = "Petr" OR jmeno = "Karel")
    AND LENGTH(prijmeni) > 6;


----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------