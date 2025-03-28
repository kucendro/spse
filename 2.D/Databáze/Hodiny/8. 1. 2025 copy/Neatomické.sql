create table student (ID INTEGER, jmeno VARCHAR(45));
create table ucitel (ID INTEGER, jmeno VARCHAR(45));
create table uci (ID_studenta INTEGER, ID_ucitele INTEGER, predmet VARCHAR(45));
create table knihy (ID INTEGER, ID_studenta INTEGER, nazev VARCHAR(100));

INSERT INTO student (ID, jmeno) VALUES (1, 'Petr Blazek');
INSERT INTO student (ID, jmeno) VALUES (2, 'Petr Moravec');
INSERT INTO student (ID, jmeno) VALUES (3, 'Martin Rychly');
INSERT INTO student (ID, jmeno) VALUES (4, 'Pavel Stanek');

INSERT INTO ucitel (ID, jmeno) VALUES (1, 'Simeon Karamazov');
INSERT INTO ucitel (ID, jmeno) VALUES (2, 'Martin Zeman');
INSERT INTO ucitel (ID, jmeno) VALUES (3, 'Kamil Pulpan');
INSERT INTO ucitel (ID, jmeno) VALUES (4, 'David Zak');

INSERT INTO uci (ID_studenta,ID_ucitele, predmet) VALUES (1, 2, 'IZITE');
INSERT INTO uci (ID_studenta,ID_ucitele, predmet) VALUES (2, 3, 'C++');
INSERT INTO uci (ID_studenta,ID_ucitele, predmet) VALUES (3, 2, 'AJ');
INSERT INTO uci (ID_studenta,ID_ucitele, predmet) VALUES (4, 1, 'DS1');

INSERT INTO knihy (ID,ID_studenta,nazev) VALUES (3001, 2, 'Kniha 1');
INSERT INTO knihy (ID,ID_studenta,nazev) VALUES (3002, 3, 'Kniha 2');
INSERT INTO knihy (ID,ID_studenta,nazev) VALUES (3003, 3, 'Kniha 3');
INSERT INTO knihy (ID,ID_studenta,nazev) VALUES (3004, 1, 'Kniha 4');