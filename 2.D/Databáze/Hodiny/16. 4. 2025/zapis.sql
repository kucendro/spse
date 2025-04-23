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
/* Upravte tabulky student a ucitel tak, aby příjmení a jméno bylo v samostatném sloupečku */

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

/* 8.1. 2025 */

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

----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

/* 15.1. 2025 */

/* Zadání od učitele*/

/* 1) Vypište id ucitelu, kteří někoho učí.*/

SELECT ID_ucitele FROM uci;

/* 2) Vypište jména a příjmení učitelů, kterří někoho učí.*/

SELECT ucitel.jmeno, ucitel.prijmeni
FROM ucitel
JOIN uci ON ucitel.ID = uci.ID_ucitele;

/* DRUHÝ ZPŮSOB*/

SELECT ucitel.jmeno, ucitel.prijmeni
FROM ucitel
WHERE ucitel.ID IN (SELECT ID_ucitele FROM uci);

/* DALŠÍ ZPŮSOB*/

SELECT ucitel.jmeno, ucitel.prijmeni
FROM ucitel
INNER JOIN uci ON ucitel.ID = uci.ID_ucitele

/* 3) Vypište bez duplicit jména a příjmení studentů, kteří studují. */

SELECT DISTINCT student.jmeno, student.prijmeni
FROM student
JOIN uci ON student.ID = uci.ID_studenta;-

/* Vypište bez duplicit jména a příjmení studentů, kteří studují a názvy knih, které mají studenti půjčeny.
Výpis atributů bude obsahovat nadpis. Pro spojení použijte příkaz where.*/

SELECT DISTINCT student.jmeno as "Jméno studenta", student.prijmeni as "Příjmení studenta", knihy.nazev as "Název knihy"
FROM student, uci, knihy
WHERE student.ID = uci.ID_studenta AND student.ID = knihy.ID_studenta;

/* Vypište bez duplicit jména a příjmení studentů, kteří studují a názvy knih, které mají studenti půjčeny.
Výpis atributů bude obsahovat nadpis. Pro spojení použijte příkaz inner join.*/

SELECT DISTINCT student.jmeno as "Jméno studenta", student.prijmeni as "Příjmení studenta", knihy.nazev as "Název knihy"
FROM student
INNER JOIN uci ON student.ID = uci.ID_studenta
INNER JOIN knihy ON student.ID = knihy.ID_studenta;

SELECT DISTINCT student.jmeno AS "Jméno studenta", student.prijmeni AS "Příjmení studenta", knihy.nazev as "Název knihy"
FROM student, uci. knihy
WHERE student.ID = uci.ID_studenta AND student.ID = knihy.ID_studenta


----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

/* 22.1. 2025 */

/* Zadání od učitele*/

/* 1) Vypište bez duplicit jména a příjmení studentů, kteří studují, mají ID<2 a jmenují se Petr*/

SELECT DISTINCT student.jmeno as "Jméno", student.prijmeni as "Příjmení"
FROM student, uci
WHERE student.ID = ID_studenta AND ID<2 AND student.jmeno = "Petr"

SELECT DISTINCT student.jmeno as "Jméno", student.prijmeni as "Příjmení"
FROM student
INNER JOIN uci ON student.ID = uci.ID_studenta
WHERE ID<2 AND student.jmeno = "Petr"



/* 2) Vypište jména a příjmení učitelů a k nim jména a příjmení studentů, které učitel učí.
Použijte spojeni pomoci where nebo join.*/

SELECT DISTINCT
    student.jmeno AS "Jméno studenta",
    student.prijmeni AS "Příjmení studenta",
    ucitel.jmeno AS "Jméno učitele",
    ucitel.prijmeni AS "Příjmení učitele"
FROM
    student,
    ucitel,
    uci
WHERE ucitel.ID = uci.ID_ucitele AND student.ID = uci.ID_studenta;



/* 3) Vypište bez duplicit jména a příjmení studentů, kteří studují a názvy knih, které mají studenti zapůjčeni.
Názvy knih delší než 10 znaků*/

SELECT DISTINCT
    student.jmeno AS "Jméno studenta",
    student.prijmeni AS "Příjmení studenta",
    knihy.nazev AS "Název knihy"
FROM
    student,
    uci,
    knihy
WHERE
    student.ID = uci.ID_studenta AND student.ID = knihy.ID_studenta AND LENGTH(knihy.nazev) > 10

    /* JOIN */

SELECT DISTINCT
    student.jmeno AS "Jméno studenta",
    student.prijmeni AS "Příjmení studenta",
    knihy.nazev AS "Název knihy"
FROM
    student
INNER JOIN uci ON student.ID = uci.ID_studenta
INNER JOIN knihy ON student.ID = knihy.ID_studenta AND LENGTH(knihy.nazev) > 10


----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

/* 29.1. 2025 */

/* Zadání od učitele*/

/* 1) Vypište bez duplicit jména a příjmení studentů a názvy knih, které mají studenti půjčeny.
Půjčené knihy, musí mít id v rozmezí 3000 a 3002 včetně.*/

SELECT DISTINCT
    student.jmeno AS "Jméno studenta",
    student.prijmeni AS "Příjmení studenta",
    knihy.nazev AS "Název knihy"
FROM
    student,
    uci,
    knihy
WHERE
    student.ID = uci.ID_studenta AND student.ID = knihy.ID_studenta AND knihy.ID BETWEEN 3000 AND 3002

    /* JOIN */

SELECT DISTINCT
    student.jmeno AS "Jméno studenta",
    student.prijmeni AS "Příjmení studenta",
    knihy.nazev AS "Název knihy"
FROM
    student
INNER JOIN uci ON student.ID = uci.ID_studenta
INNER JOIN knihy ON student.ID = knihy.ID_studenta AND knihy.ID BETWEEN 3000 AND 3002

/* 2) Vypište bez duplicit jména a příjmení učitelů, kteří učí studenty, kteří mají půjčenou knihu*/

SELECT DISTINCT
    ucitel.jmeno AS "Jméno učitele",
    ucitel.prijmeni AS "Příjmení učitele"
FROM
    ucitel,
    uci,
    knihy
WHERE
    ucitel.ID = uci.ID_ucitele AND uci.ID_studenta = knihy.ID_studenta

    /* JOIN */

SELECT DISTINCT
    ucitel.jmeno AS "Jméno učitele",
    ucitel.prijmeni AS "Příjmení učitele"
FROM
    ucitel
INNER JOIN uci ON ucitel.ID = uci.ID_ucitele
INNER JOIN knihy ON uci.ID_studenta = knihy.ID_studenta

----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

/* Opakování na test */

SELECT DISTINCT
    student.jmeno AS "Jméno studenta",
    student.prijmeni AS "Příjmení studenta",
    ucitel.jmeno AS "Jméno učitele",
    ucitel.prijmeni AS "Příjmení učitele"
    FROM
    student,
    ucitel,
    uci
    WHERE ucitel.ID = uci.ID_ucitele AND student.ID = uci.ID_studenta;

SELECT DISTINCT
student.jmeno AS "jméno studenta",
student.prijmeni AS "Příjmení studenta",
ucitel.jmeno AS "Jméno učitele",
ucitel.prijmeni AS "Příjmené učitele"

FROM
student,
ucitel
INNER JOIN uci ON ucitel.ID = uci.ID_ucitele


/* 3) Vypište bez duplicit jména a příjmení studentů, kteří studují a názvy knih, které mají studenti zapůjčeni.
Názvy knih delší než 10 znaků*/

    SELECT DISTINCT
        student.jmeno AS "Jméno studenta",
        student.prijmeni AS "Příjmení studenta",
        knihy.nazev AS "Název knihy"
    FROM
    student,
    uci,
    knihy
    WHERE student.ID = uci.ID_studenta AND student.ID = knihy.ID_studenta

    SELECT DISTINCT
        student.jmeno AS "Jméno studenta",
        student.prijmeni AS "Příjmené studenta",
    knihy.nazev AS "název knihy"

    FROM
    student
    INNER JOIN uci ON student.ID = uci.ID_studenta
    INNER JOIN knihy On student.ID = knihy.ID_studenta

    /* 1) Vypište bez duplicit jména a příjmení studentů a názvy knih, které mají studenti půjčeny.
Půjčené knihy, musí mít id v rozmezí 3000 a 3002 včetně.*/

    SELECT DISTINCT
    student.jmeno AS "Jméno studenta",
    student.prijmeni AS "Příjmení studenta",
    knihy.nazev AS "Název knihy"

    FROM
    student,
    knihy

    WHERE student.ID = knihy.ID_studenta AND knihy.ID BETWEEN 3000 AND 3002

    SELECT DISTINCT
    student.jmeno AS "Jméno studenta",
    student.prijmeni AS "Příjmení studenta",
    knihy.nazev AS "Název knihy"

    FROM
    student
    INNER JOIN knihy ON student.ID = knihy.ID_studenta AND knihy.ID BETWEEN 3000 AND 3002

    /* 3) Vypište bez duplicit jména a příjmení studentů, kteří studují a názvy knih, které mají studenti zapůjčeni.
Názvy knih delší než 10 znaků*/

    SELECT DISTINCT
    student.jmeno AS "Jméno studenta",
    student.prijmeni AS "Příjmení studenta",
    knihy.nazev AS "Název knihy"
    FROM
    student,
    knihy,
    uci
    WHERE student.ID = knihy.ID_studenta AND student.ID = uci.ID_studenta AND LENGTH(knihy.nazev) > 10

    SELECT DISTINCT
    student.jmeno AS "Jméno studenta",
    student.prijmeni AS "Příjmení studenta",
    knihy.nazev AS "Název knihy"
    FROM
    student
    INNER JOIN uci ON student.ID = uci.ID_studenta
    INNER JOIN knihy ON student.ID = knihy.ID_studenta
    WHERE LENGTH(knihy.nazev) > 10

/* Vypište bez duplicit jména a příjmení studentů, kteří studují a názvy knih, které mají studenti půjčeny.
Výpis atributů bude obsahovat nadpis. Pro spojení použijte příkaz inner join.*/

    SELECT DISTINCT
    student.jmeno AS "Jméno studenta",
    student.prijmeni AS "Příjmení studenta",
    knihy.nazev AS "Název knihy"
    FROM
    student,
    knihy,
    uci
    WHERE student.ID = knihy.ID_studenta AND student.ID = uci.ID_studenta

    SELECT DISTINCT
    student.jmeno AS "Jmeno studenta",
    student.prijmeni AS "Příjemní studenta",
    knihy.nazev AS "Název knihy"
    FROM
    student
    INNER JOIN knihy ON student.ID = knihy.ID_studenta
    INNER JOIN uci ON student.ID = uci.ID_studenta

----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

/* 19.2. 2025 */

/* Zadání od učitele*/

/* 1) Napište select, který vypíše názvy vyučovaných předmětů, přijmení učitele,
který daný předmět uvyučuje, kolik studentů v daném předmětu učitel vyučuje.
Zobrazte pouze záznamy, kdy učitel vyučuje více než jednoho studenta..*/

SELECT
    uci.predmet AS "Název předmětu",
    ucitel.prijmeni AS "Příjmení učitele",
    COUNT(uci.ID_studenta) AS "Počet studentů"
FROM
    ucitel
INNER JOIN uci ON ucitel.ID = uci.ID_ucitele
GROUP BY
    uci.predmet, ucitel.prijmeni
HAVING
    COUNT(uci.ID_studenta) > 1

/* Vypiště seznam jmen a příjení všech studentům kteří nemají půjčenou žádnou knihu.*/

SELECT
    student.jmeno AS "Jméno studenta",
    student.prijmeni AS "Příjmení studenta"
FROM
    student

WHERE
    student.ID NOT IN (SELECT ID_studenta FROM knihy)

/* Nebo*/

SELECT
    student.jmeno AS "Jméno studenta",
    student.prijmeni AS "Příjmení studenta"
FROM
    student
LEFT JOIN knihy ON student.ID = knihy.ID_studenta
WHERE
    knihy.ID_studenta IS NULL

----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

/* 1) Napište select, který vypíše názvy vyučovaných předmětů, přijmení učitele,
který daný předmět vyučuje, kolik studentů v daném předmětu učitel vyučuje.
Zobrazte pouze záznamy, kdy učitel vyučuje více než jednoho studenta..*/

SELECT
    uci.predmet AS "Název předmětu",
    ucitel.prijmeni AS "Příjmeníé učitele",
    COUNT(uci.ID_studenta) AS "Počet studentů"
FROM
    ucitel
INNER JOIN uci ON ucitel.ID = uci.ID_ucitele

GROUP BY
    uci.predmet, ucitel.prijmeni
HAVING
    COUNT(uci.ID_studenta) > 1


SELECT
    uci.predmet AS "Název předmětu",
    ucitel.prijmeni AS "Příjmení učitele",
    COUNT(uci.ID_studenta) AS "Počet studentů"
FROM
    ucitel
INNER JOIN uci ON ucitel.ID = uci.ID_ucitele
GROUP BY
    uci.predmet, ucitel.prijmeni
HAVING
    COUNT(uci.ID_studenta) > 1

/* 1) Napište select, který vypíše názvy vyučovaných předmětů, přijmení učitele,
který daný předmět vyučuje, kolik studentů v daném předmětu učitel vyučuje.
Zobrazte pouze záznamy, kdy učitel vyučuje více než jednoho studenta..*/

SELECT
uci.predmet As "Nátev předmětu",
ucitel.prijmeni AS "Příjmení učitele",
COUNT(uci.ID_studenta) AS "Počet studentů"
FROM
ucitel
INNER JOIN uci ON ucitel.ID = uci.ID_ucitele
GROUP BY
uci.predmet, ucitel.prijmeni
HAVING
COUNT(uci.ID_studenta) > 1

SELECT
uci.predmet AS "Název předmětu",
ucitel.prijmeni AS "Učitel",
COUNT(uci.ID_studenta) AS "Počet studentů"

FROM
ucitel

INNER JOIN uci ON ucitel.ID = uci.ID_ucitele

GROUP BY
uci.predmet, ucitel.prijmeni
HAVING
COUNT(uci.ID_studenta) > 1

/*vyberte jmeno a prijmeni studenta a predmety ktere se student uci.
vyberte pouze ty studenty, kteri nemaji pujcenou knihu. pouzijte JOIN pro spojeni*/

SELECT
student.jmeno AS "Jméno studenta",
student.prijmeni AS "Příjmení studenta",
uci.predmet AS "Předmět"
FROM
student
INNER jOIN uci ON student.ID = uci.ID_studenta
INNER JOIN knihy ON student.ID = knihy.ID_studenta
WHERE knihy.ID_studenta = NULL;

SELECT
student.jmeno AS "Jméno",
student.prijmeni AS "Příjmení",
uci.predmet AS "Předmět"
FROM
student
INNER JOIN uci ON student.ID = uci.ID_studenta
INNER JOIN knihy ON student.ID = knihy.ID_studenta
WHERE
knihy.ID_studenta = NULL;


SELECT
student.jmeno AS "Jméno",
student.prijmeni AS "Příjmení",
uci.predmet AS "Předmět"
FROM
student
INNER JOIN uci ON student.ID = uci.ID_studenta
INNER JOIN knihy ON student.ID = knihy.ID_studenta
WHERE knihy.ID_studenta = NULL;

----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------


/* 5.3. 2025 */

/* Zadání od učitele*/

SELECT
    ucitel.prijmeni AS "Příjmení učitele",
    ucitel.jmeno AS "Jméno učitele"
FROM
    ucitel
INNER JOIN uci ON ucitel.ID = uci.ID_ucitele
WHERE
    uci.ID_studenta = NULL;

/* Vypiste jmena a prijmeni ucitelů, kteří učí studenty, kteří nemají půjčenou žádnou knohu.*/

SELECT
ucitel.jmeno AS "Jméno",
ucitel.prijmeni AS "Příjmení"
FROM
INNER JOIN uci ON ucitel.ID = uci.ID_ucitele
WHERE knihy.ID_studenta = NULL;


----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------


/* 2.4. 2025 */

/* Zadání od učitele*/

/* 1) Vypište id, jméno, příjmení učitelů a předměty, které keždý učitel učí a kolik studentů učí v každém předmětu.
Vypište pouze učitele, kteří učí v předěmtu více než jednoho studenta. Studenti, kteří se jmenují Petr nebudou zahrnuti do výběru.
Jméno studentů musí začínat na písmeno "P"*/

SELECT
    ucitel.ID AS "ID učitele",
    ucitel.jmeno AS "Jméno učitele",
    ucitel.prijmeni AS "Příjmení učitele",
    uci.predmet AS "Předmět",
    COUNT(uci.predmet) AS "Počet studentů"

FROM
    ucitel

INNER JOIN uci ON ucitel.ID = uci.ID_ucitele
INNER JOIN student ON uci.ID_studenta = student.ID

WHERE
    student.jmeno LIKE "P%"

GROUP BY
    ucitel.ID, uci.predmet

HAVING
    COUNT(uci.ID_studenta) > 1;


/* 2) Vypište jména a příjmení učitelu, k nim jména a příjmení studentů, které učitel učí a uveďte v kolika předmětech učitel každého studenta učí.
Uveďte názvy sloupců: Jméno učitele, Příjmení učitele, Jméno studenta, Příjmení studenta, Počet předmětů.
Vypište pouze učitele a studenty, kdy učitel učí studenta ve více než jednom předmětu. ID studenta musí být v intervalu 1 až 10 včetně.begin*/

SELECT
    ucitel.jmeno AS "Jméno učitele",
    ucitel.prijmeni AS "Příjmení učitele",
    student.jmeno AS "Jméno studenta",
    student.prijmeni AS "Příjmení studenta",
    COUNT(uci.predmet) AS "Počet předmětů"

FROM
    ucitel

INNER JOIN uci ON ucitel.ID = uci.ID_ucitele
INNER JOIN student ON uci.ID_studenta = student.ID

WHERE
    student.ID BETWEEN 1 AND 10

GROUP BY
    ucitel.jmeno, ucitel.prijmeni, student.jmeno, student.prijmeni

HAVING
    COUNT(uci.predmet) > 1;



----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------


/* 16.4. 2025 */

/* Zadání od učitele*/


/* 1) Vypište jména a příjmení učitelů, kteří učí studenty, kteří se jmenují Petr a nemají půjčenou knihu.
Za jméno a příjmení každého učitele vypište jméno a příjmení těchto studentů a počet předmětů (s nadpisem "Počet vyučovaných předmětů"),
ve kterých je dalý učitel vyučuje. Vypište pouze učitele, kteří učí takové studenty v jednom až maximálně pěti předmětech včetně.begin
  Pro spojení na rovnost použijte INNER JOIN. Pro vnější spojení použijte LEFT JOIN.*/


SELECT
    ucitel.jmeno AS "Jméno učitele",
    ucitel.prijmeni AS "Příjmení učitele",
    student.jmeno AS "Jméno studenta",
    student.prijmeni AS "Příjmení studenta",
    COUNT(uci.predmet) AS "Počet vyučovaných předmětů"
FROM
    ucitel

INNER JOIN uci ON ucitel.ID = uci.ID_ucitele
INNER JOIN student ON uci.ID_studenta = student.ID
INNER JOIN knihy ON student.ID = knihy.ID_studenta
WHERE
    student.jmeno = "Petr" AND knihy.ID_studenta IS NULL
GROUP BY
    ucitel.jmeno, ucitel.prijmeni, student.jmeno, student.prijmeni
HAVING
    COUNT(uci.predmet) BETWEEN 1 AND 5;

