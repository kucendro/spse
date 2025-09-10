// Napište regulární výraz, který bude validní pro jméno a příjmení. (uvažujeme jméno a příjmení po jednom slovu)
// Jméno a příjmení
// Ohraničení - zarážky, ....

let regex = /^[A-Z][a-z]{2,}+ [A-Z][a-z]{2,0}$/;

//Napište regulární výraz takový, že daný textový řetezec o libovolné délce obsahuje právě jeden znak "X".

let regex2 = /^(?=^[^X]*X[^X]*$).+$/;

// Napište regulární výraz, který ověří, že daný textový řetězec je rodným číslem.

let regex3 = /^(0[1-9]|[12]|[0-9]|3[01])([01][0-9]|2[0-3])([0-9]{2})?([0-9]{3})?$/;
// ženy mají v měsících přičtenou 50, takže musí být podmínky 0 nebo +50

console.log(regex3.test("010101/1234"));

// naoište funkci, která ověří, že číslo je dělitelné 11

function isDivisibleBy11(num) {
    return num % 11 === 0;
}

birthNumber = "010101/1234";
// funkce pro ověření rodného čísla
function isValidBirthNumber(birthNumber) {
    // rozdělení rodného čísla na části
    const parts = birthNumber.split("/");
    if (parts.length !== 2) {
        return false;
    }
    const datePart = parts[0];
    const serialPart = parts[1];

    // ověření formátu data
    if (!/^\d{6}$/.test(datePart)) {
        return false;
    }

    // ověření formátu sériového čísla
    if (!/^\d{4}$/.test(serialPart)) {
        return false;
    }

    // ověření dělitelnosti 11
    const number = parseInt(datePart + serialPart, 10);
    return isDivisibleBy11(number);
}