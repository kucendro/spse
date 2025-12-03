<?php 
// index.php
// C:/xampp/htdocs
// localhost

//phpinfo(); vypisuje informace o konfiguraci PHP


    // PHP - PHP Hypertext processor
    // Dynamicky typovací objektově orientovaný skriptovací jazyk
    // nikdy v prohlížeči nevidíme PHP příkazy
    // každý příkaz končí středníkem

    // Výpis v PHP
    // funkce echo - přímý výstup do HTML
    // funkce var_dump - diagnostický výstup přímo do HTML - poskytuje info o typu proměnné, délce, hodnotách / Pole /, ...

    echo "Hello world!<br>";
    echo "Hello world!<br>";
    echo "Hello world!<br>";
    echo "Hello world!<br>";

    var_dump("Hello world!");

    echo <<<EOL
        <h1>Hello World!</h1>
        <p>Toto je odstavec</p>
    EOL;

    //Proměnné
    $promenna = 10; //$nazev
    const KONSTANTA = 25; //const NAZEV_KONSTANTY = hodnota

    // Operátory v PHP - unární, binární, ternární
    var_dump(true + true); // 2
    //+,-,*,/,%
    var_dump(1 + 2);
    var_dump(1 * 2);
    var_dump(1 - 2);
    var_dump(1 / 2);

    var_dump(1 ** 2); // mocnina, odmocnina 2 ** 0.5
    // => binární

    $a = 10;
    var_dump($a++);
    var_dump(++$a);

    //spojování řetězců - Spojuje se pomocí tečky "."
    $text_a = "Hello";
    $text_b = "World";
    var_dump($text_a . $text_b);

    //Řídící struktury
    // if, for, while, do-while

    if ($a >10){
        echo "Wassup";
    }

    for ($i = 0; $i < 10; $i++){
        echo $i; //0,1,2,...,9
    }


    //Operátory
    // Porovnávací == vs. === (rovnost hodnot vs. rovnost hodnot i datového typu)
    // dynamicky typovaný = typ proměnné je určen okamžikem přiřazení hodnoty
    // $a = 10 -> a == int

    //skalární vs. složené vs. speciální
    // int, float, string, bool
    // array, object, callable = cokoliv co je volatelné (metody / funkce), 
    //     iterable = cokoliv co lze procházet
    // null, resource = speciální zdroje (db, soubory,...)
    // smíšený/obecný typ => mixed = libovolný datový typ
    // void - metoda/funkce nevrací nic
    // true/false -> metoda/funkce vrací true/false

    // Operátor koalescence
    $vyraz = null ?? "nic";   //null -> použij hodnotu za ??
    var_dump($vyraz);         //nic
    $vyraz = "null" ?? "nic"; //null -> použij hodnotu za ??
    var_dump($vyraz);         // null

    //Operátor ??=
    $vyraz ??= "CZ";    // pokud není proměnná nastavena, použij hodnotu za ??=
    var_dump($vyraz);   // "null"

    // Spaceship operátor <=>
    // vrací 1,-1,0 na základě porovnání
    var_dump(1<=>1); //  0
    var_dump(0<=>1); // -1
    var_dump(1<=>0); //  1

    var_dump("Abcde" <=> "abcde"); //-1
    var_dump("Abcde" <=> "abcd"); //-1

    // ?: -> zkrácený ternární operátor
    $promenna = "false" ?: "hodnota";
    // > pokud je výraz před ?: false, pak se použije pravá strana
    var_dump($promenna);

    // Nullsafe operátor
    // ?-> slouží k volání metody nebo vlastnosti pouze pokud není null
    // OOP!

    // match
    $stav = 3;

    $vysledek = match ($stav){
        1 => "A",
        2 => "B",
        default => "X"
    };

    var_dump($vysledek);

    // v php je defaultně false vždy
    $a = false;
    $a = 0;
    $a = 0.0;
    $a = "";
    $a = "0";
    $a = null;
    $a = [];

    // vše ostatní je vyhodnoceno jako true
    if ("A") {
        echo "Pravda";
    }

    //Pole v PHP
    $pole = []; // Deklarace pole v PHP

    // a) indexované - od 0, délka pole - count(pole): int
    $pole = [2, 5, 6, 996, 105,"Gamma", 7];
    var_dump($pole);
    //0 => int 2
    //0 => int 5
    //0 => int 6
    //0 => int 996
    //0 => int 105
    //0 => string "Gamma" (length = 5)
    //0 => int 7
    var_dump($pole [0]);
    $pole[0] = 5;
    $pole[100] = 741; // vložení prvku na konkrétní index v poli
    $pole[] = 74;     // vložení prvku na konec pole
    var_dump($pole);

    //for-each ($pole as klíč => hodnota) {}
    // = foreach cyklus je určen výhradně pro průchod pole, nikoliv pro jeho modifikaci
    foreach ($pole as $key => $value){
        var_dump($value);
    }

    // b) asociativní
    // pole typu klíč x hodnota
    $arr = [
        "První" => "A",
        "Druhá" => "XSASA",
        "Třetí" => "YAAS",
    ];
    $arr ["První"] = "ASDF";
    $arr ["Čtvrtá"] = "SAFASWA";
    $arr []="APKPSAMF";
    var_dump($arr) ;

    // Př.: Vytvořte pole pro malou násobilku
$poleNasobilky = [];
for ($i = 1; $i < 10; $i++){
    $nasobilka[$i] = [];
    for ($j = 1; $j < 10; $j++){
        $poleNasobilky[$i][$j] = ($i+1) * ($j+1);
    }
}


// Další zápisy jsou na index_2.php
?>






