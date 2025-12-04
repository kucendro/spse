<?php

// př. Všechny datumy třídnických hodin na příští kalendářní rok

function getClassMeetingDates($year): array
{
    $dates = [];
    $datum = new DateTime("2026-01-01");

    while ($datum->format("Y") == (string) $year) {
        $datum->modify("first wednesday of" . $datum->format("Y-m"));
        $dates[] = $datum->format("Y-m-d");
        $datum->modify("first day of next month");
    }

    return $dates;
}


// pr. vytvořte pole o 100 časových záznamech. Nalezněte průměrný časový rozdíl mezi všemi záznamy.

function averageTime(): string
{
    $timestamps = [];
    for ($i = 0; $i < 100; $i++) {
        $timestamps[$i] = rand(1, 1000000);
    }

    var_dump($timestamps);

    $totalDiff = 0;
    $pairCount = 0;

    for ($i = 0; $i < count($timestamps); $i++) {
        for ($j = $i + 1; $j < count($timestamps); $j++) {
            $totalDiff += abs($timestamps[$i] - $timestamps[$j]);
            $pairCount++;
        }
    }

    $averageDiff = $totalDiff / $pairCount;

    return gmdate("H:i:s", (int) $averageDiff);
}