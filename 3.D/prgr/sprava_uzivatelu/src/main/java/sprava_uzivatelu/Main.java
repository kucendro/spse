package sprava_uzivatelu;

import javax.swing.SwingUtilities;

public class Main {
        public static void main(String[] args) {

                System.setProperty("apple.laf.useScreenMenuBar", "true");
                System.setProperty("apple.awt.application.name", "Users management");
                System.setProperty("apple.awt.application.appearance", "system");

                SwingUtilities.invokeLater(() -> new GUI().setVisible(true));

                UsersTable users = UsersTable.getInstance();
                PersonsTable persons = PersonsTable.getInstance();

                users.load();
                persons.load();
        }
}

// Pro pana učitele Bajera:

// Dobrý den, v pravidlech klasifikace je uvedeno, že jakékoliv využití AI při
// tvorbě kódu musí být uvedeno v daném míště codebase.
// Během tvorby tohoto projektu jsem používal pouze Dokumentaci Oracle, další
// fóra a integrovaný GitHub Copilot v IDE, který mi například pomáhal s
// obarvováním zpráv,
// jelikož integrace funguje na principu doplňování možných nadcházejících slov
// na základně kontextu a již napsaného textu. Takže je to taková realtime
// nápověda o pár slovech, ale pouze pro omezeno část kontextu.
// Rozhodně ne celou codebase!
// Pro logiku kódu a celou jeho strukturu jsem AI, ať už jakoukoliv formu,
// NEPOUŽÍVAL!!! - chci se sám něco naučit. Díky práci jsem si sám přišel na to,
// že Vibe coding je spíše cesta do prdele.

// Pro tento projekt jsem zakázal používání Chatbotů. Pokud bych je i tak
// použil, zde uvedu jak a proč:
// ----------------------------------------------------------------------------------------------------
// - chyby v propadu podmínek u validací isRemoved() adminem
// - repetitivní kód v GUI