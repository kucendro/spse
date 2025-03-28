/*  TABULKY STUDENTI NEATOMICKE*/

/* Vytvorte tyto tabulky. */
create table student (ID INTEGER, jmeno VARCHAR(45));
create table ucitel (ID INTEGER, jmeno VARCHAR(45));
create table uci (ID_studenta INTEGER, ID_ucitele INTEGER, predmet VARCHAR(45));
create table knihy (ID INTEGER, ID_studenta INTEGER, nazev VARCHAR(100));

/* Vlozte do kazde tabulky 2-4 radky. */
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
/*  TABULKY STUDENTI ATOMICKE*/
/* Upravte tabulky student a ucitel tak, aby příjmení a jméno bylo samostatném sloupečku  */
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
