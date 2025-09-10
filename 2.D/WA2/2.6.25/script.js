// set timeout - za určený daný časový úsek spustí obsah funkce
// set interval - každá časový úsek spustí obsah vnitřní funkce


// př. Při kliknutí do tabulky, se obarví buňka, na kterou jsme klikli červenou varvu za 3 sekundy.

    // document.addEventListener("DOMContentLoaded",() => {
    //     const table = document.getElementById("table");
    //     table.addEventListener("click", (event) => {
    //         const cell = event.target;
    //         if (cell.tagName === "TD") {
    //             setTimeout(() => {
    //                 cell.style.backgroundColor = "red";
    //             }, 3000);
    //         }
    //     });
    // });

    // document.addEventListener("DOMContentLoaded", () => {
    //     const table = document.querySelector("table");
    //     table.addEventListener("click", (event) => {
    //         const cell = event.target;
    //         if (cell.tagName === "TD") {
    //             setTimeout(() => {
    //                 cell.style.backgroundColor = "red";
    //             }, 3000);
    //         }
    //     });
    // });

// set interval - každou sekundu se změní barva buňky na náhodnou barvu

let interval;
let changes = -1;


document.addEventListener("DOMContentLoaded", () => {
    const table = document.querySelector("table");
    table.addEventListener("click", (event) => {
        const cell = event.target;
        if (cell.tagName === "TD") {
            interval = setInterval(() => {
                cell.style.backgroundColor = getRandomColor();
                changes ++;
                if (changes >= 10) {
                    clearInterval(interval);
                    cell.style.backgroundColor = "transparent";
                    changes = -1;
                }
            }, 1000);
        }
    });
});

function getRandomColor() {
    const letters = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
};

// Tabulka se postupně naplní čísly 1 až 100, postupně po 500ms.
let currentNumber = 1
let columnsInRow = 10;
let fillInterval;

document.addEventListener("DOMContentLoaded", () => {
    const table = document.querySelector("table");
    fillInterval = setInterval(() => {
        const row = document.createElement("tr");
        for (let i = 0; i < columnsInRow; i++) {
            const cell = document.createElement("td");
            cell.textContent = currentNumber;
            row.appendChild(cell);
            currentNumber++;
            if (currentNumber > 100) {
                clearInterval(fillInterval);
                break;
            }
        }
        table.appendChild(row);
    }, 500);
});