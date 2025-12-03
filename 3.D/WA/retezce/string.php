<?php
mb_internal_encoding("UTF-8");

//! Textové řetezce v PHP

$text = "Hoj!";

//? délka text řetezce
// strlen(string &text): int

var_dump(strlen("Text"));
var_dump(mb_strlen("Raníčko")); // Multibytové znaky

// mb_strtoupper(string $text): string
// mb_strtolower(string $text): string
//? -> Převod na velká/malá písmena

var_dump(mb_strtoupper("Ahojky"));
var_dump(mb_strtolower("Ahojky"));

//? mb_ucfirst(string $text): string
var_dump(mb_ucfirst("ahojky"));

// ucwords(string $text): string
//? -> Převod na velká písmena na začátku slov
var_dump(ucwords("ahojky jak se máš"));

//? Získání znaku z textového řetezce
var_dump($text[0]); // První znak

// odstranení bílých znaků
var_dump(trim("   Ahojky   "));

//? Převod znaků na čísla a naopak

// mb_chr(int $znak): string //! číslo -> znak
// mb_ord(string $znak): int //! znak -> číslo

//! Vygenerovat heslo
//? Heslo musí obsahovat alespoň 1x Velké písmeno, 1x malé, apespoň 1x číslo, min 8 znaků max 16

//? mt_rand = náhodné číslo

$heslo = ""; // definice prázdného řetězce
$delkaHesla = mt_rand(8, 16); // náhodné číslo v rozmezí
$maleZnaky = "abcdefghijklmnopqrstuvwxyz";
$velkeZnaky = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
$cisla = "0123456789";
$vsechnyZnaky = $maleZnaky . $velkeZnaky . $cisla; // spojení řetezců

$heslo .= $maleZnaky[mt_rand(0, strlen(($maleZnaky)) - 1)]; // přídání znaku na konec řetezce
$heslo .= $velkeZnaky[mt_rand(0, strlen(($velkeZnaky)) - 1)]; // přídání znaku na konec řetezce
$heslo .= $cisla[mt_rand(0, strlen(($cisla)) - 1)]; // přídání znaku na konec řetezce

for ($i = 3; $i < $delkaHesla; $i++) {
    $heslo .= $vsechnyZnaky[mt_rand(0, strlen($vsechnyZnaky) - 1)];
} // cyklus pro doplnění zbylých znaků

$heslo = str_shuffle($heslo); // promíchání znaků v řetezci

var_dump($heslo);

//? dalsi
$abeceda = "abcdefghijklmnopqrstuvwxyz";
$arr = mb_str_split($abeceda);
shuffle($arr);
var_dump(implode("", $arr));


//? referenční parametry
$a = 2;
$b = 3;
function prohod(int &$a, int &$b): void
{
    $pomocna = $a;
    $a = $b;
    $b = $pomocna;
}

prohod($a, $b);

var_dump(["a" => $a, "b" => $b]);

//? explode(string $delimiter, array $array): array
// rozdělí text na pole dle oddělovače

$text = "jeden,dva,tři,čtyři,pět";

var_dump(explode(",", $text));

//? mb_strtr - nahrazení dle slovníku

// mb_strtr(string &text, array $slovnik): string

$slovník = [
    "é" => "e",
    "á" => "a",
    "č" => "c",
    "ď" => "d",
    "ě" => "e",
    "í" => "i",
    "ň" => "n",
    "ó" => "o",
    "ř" => "r",
    "š" => "s",
    "ť" => "t",
    "ů" => "u",
    "ú" => "u",
    "ž" => "z"
];

var_dump(strtr("Příliš žluťoučký kůň úpěl ďábelské ódy.", $slovník));

// Př. Fn, která zjistí, zda je text palindrom

function isPalindrom(string $text): bool
{
    $text = mb_strtolower($text);
    $text = strtr($text, [
        " " => "",
        "," => "",
        "." => "",
        "!" => "",
        "?" => "",
        "-" => "",
        "'" => "",
        "\"" => "",
        "\n" => "",
        "ě" => "e",
        "š" => "s",
        "č" => "c",
        "ř" => "r",
        "ž" => "z",
        "ý" => "y",
        "á" => "a",
        "í" => "i",
        "é" => "e",
        "ú" => "u",
        "ů" => "u",
        "ó" => "o",
        "ť" => "t",
        "ď" => "d",
        "ň" => "n",
    ]);
    $reverzníText = strrev($text);
    return $text === $reverzníText;
}

var_dump(isPalindrom("Kobyla má malý bok."));