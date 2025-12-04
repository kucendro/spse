<?php
include 'datum.php';
?>

<html>

<body>
    <h2>Tento skript vypisuje všechny datumy třídnických hodin na příští kalendářní rok (2026), které připadnou na první
        středu každého měsíce.</h2>
    <pre><?php var_dump(getClassMeetingDates(2026)); ?></pre>

    <pre><?php
    var_dump(averageTime()); ?></pre>
</body>

</html>

<style>
    body {
        font-family: Arial, sans-serif;
        margin: 20px;
    }

    h2 {
        color: #333;
    }

    pre {
        background-color: #f4f4f4;
        padding: 10px;
        border-radius: 5px;
    }

    p {
        color: #555;
        font-size: 14px;
        line-height: 1.6;
    }
</style>