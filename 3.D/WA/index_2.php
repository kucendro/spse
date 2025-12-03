<?php

// Striktní typy v PHP na parametry funkcí, návratové hodnoty, parametry atributů ve třídách
declare(strict_types=1);
//declare() -> vynucení kontroly typů -> musí být umístěn ihned pod značkou <?php

// Funkce = zaváděny z důvodu redukce redundance kódu
// Funkce v PHP

// function jmenoFunkce(typ $parametr = vychoziHodnota): nahratovýTyp {... tělo funkce}

//Př.: Funkce Hello World!
function helloWorld(): void { // void: nevrací nic
    echo "Hello World". "<br>";
}

helloWorld();

//Př.: Funkce pro součet dvou celých čísel
function secti(int | float $x, int $y): false|float|int { 
    if( $x == 0){
        return false;
    } else {
        return secti($x, $y);
    }
}

//Př.: funkce pro podíl dvou celých čísel
// Union type = sjednocené typy -> pro parametry funkcí, atributy třídy, návratové hodnoty
// Union type se rozumí 2 a více datové typy
function vydel (int $x, int $y): float | int {
    return $x / $y;
};

// int, string, float, array, bool, void
// jméno Třídy
// self -> na vlastní třídu
// object -> libovolný object
// callable -> validní zpětné volání
// iterable -> iterovatelný objekt
// mixed -> smíšený datový typ

function podilNull(int $a, int $b): float|int|null {
    if( $b == 0){
        return null;
    } else {
        return $a / $b;
    }
};

echo podilNull(b:0, a:2);
// proměnná délka parametru funkce (...)
// spread operátor

function suma(int ...$a):int {
    $soucet = 0;
    foreach($a as $value){
        $soucet += $value;
    }
    return $soucet;
}
// při volání můžeme uvést libovolný počet parametrů
echo suma (1,2,3,4,7,12,79,10). "<br>";
// anonymní funkce ->
$arr = [1,2,3,4];

$arr = array_map(function(int $a): int{
    return $a*2;
}, $arr);

// Arrow funkce -> zkrácený zápis anonymní funkce. Pouze na 1 řádek a tělo je zároveň return statement
$arr = array_map(callback: fn(int $a): int => $a * 2, array: $arr);

//Př.: Napiště funcki, která v číselném poli nalezne všechna čísla, která jsou větší než průměr prvků celého pole
//Můžete použít funkce array_sum, count
$pole = [7,8,5,1,32,15,0,9,6,14,11,13,4,3,44,22,67];

function nalezni(array $pole): array{
    $retarr = [];
    $avg = array_sum(array: $pole) / count($pole);
    foreach ($pole as $cislo) {
        if ($cislo > $avg) {
            $retarr[] = $cislo;
        }
    }
    return $retarr;
}
?>