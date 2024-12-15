/*
 *                      JAVA
	striktně typový jazyk vyšší úrovně
	kontrolovaný přístup při programování
	rychlý přístup za běhu programu

    závorky:
o	{} = popis class, funkce, konstruktor, bloku, kódu (for, while, if,…)
o	()= funkce (parametry), konstruktor (parametry), if (podmínka), while (podmínka), for  (;podmínka;). do{}while (podmínka);
o	[] = pro index v poli
o	<> = pro referenční typ v šabloně generického typu

    pole:
o	Datový typ, který obsahuje více objektů stejného typu
o	Index určuje umístění v poli
o	Jméno umístění je název pole[i] kde i = index umístění, číslo int od 0 do pole.lenght-1

    výraz:

o	(součástí algoritmů)
o	skládá se z operandů a operátorů
o	operand = proměnná/konstanta/hodnota


operátor (podle akce):

o	dosazovací		=  +=  -=  *=  /=
o	aritmetické	+  -  *  /  %  ++  --
o	logické		!  >  >=  &&  ||  ==  !=


operátor (podle počtu operandů):

o	unární		++  --  !
o	binární		+  -  =  ==
o	ternální		?:	(podmínka)?výraz:výraz;


algoritmy:

o	píší se do funkcí (dílčí úloha = jedna funkce)
o	sekvence (krok za krokem, za sebou)

o	větvení (po podmínce s výrazem s boolean výstupem)
	za if je blok true
	za else je blok false
	if
	if-else
	if-else-if

o	opakování (po podmínce s výrazem s boolean výstupem)
	vykonávají se na true
	končí na false

	for je cyklus s podmínkou na začátku s lokálním indexem (univerzální pro podmínku na začátku)

	while je cyklus s podmínkou na začátku bez indexu

	do-while je cyklus s podmínkou na konci bez indexu (slouží ke kontrolám)



funkce:
o	obsahují algoritmus pro konkrétní část úkolu, vždy jen jednu část, ne více

o	mají:
	hlavičku = návratový typ, jméno funkce, kulaté závorky pro argumenty se vstupními parametry

	tělo (popis) = popis chování funkce = algoritmus

o	typy funkcí:

	podle návratové hodnoty:

•	s návratovou hodnotou – mají return (jeden, více v if, switch) způsob volání: y=f(x) nebo f(x) (není využita návratová hodnota)

•	bez návratové hodnoty – procedury, explicitně uveden návrat void způsob volání: f(x) (není návratová hodnota)

	podle existence:

•	třídní (class) = návěští static před typem návratu funkce, existují ve třídě, volání: NázevTřídy.f(x)

•	objektové (instance) = bez návěští, náleží objektu, existují až uvnitř objektu poté, co byl vytvořen pomocí new a konstruktoru, volání: názevObjektu.f(x)

	pojmy:

•	deklarace = prohlášení: NázevTřídy názevObjektu;

•	vytvoření objektu = umístění objektu do paměti:

o	anonymní: new NázevTřídy (); Konstruktor má stejné jméno jako NázevTřídy, je to funkce operující s operátorem new

o	pojmenované: NázevTřídy názevObjektu = new NázevTřídy ();


 */
package pkg7_11_2023_opakovani;

/**
 *
 * @author mpmp
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
       
    
    }
    
}
