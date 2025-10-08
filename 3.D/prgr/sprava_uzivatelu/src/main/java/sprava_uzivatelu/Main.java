package sprava_uzivatelu;

import javax.swing.SwingUtilities;

public class Main {
        public static void main(String[] args) {

                System.setProperty("apple.laf.useScreenMenuBar", "true");
                System.setProperty("apple.awt.application.name", "Users management");
                System.setProperty("apple.awt.application.appearance", "system");

                SwingUtilities.invokeLater(() -> new GUI().setVisible(true));

                // ! --------------------------------------------------------- FOR DEMO

                // ! ADMIN LOGIN = admin / 1234
                UsersTable users = UsersTable.getInstance();
                PersonsTable persons = PersonsTable.getInstance();
                users.addUser(
                                new User("" + System.currentTimeMillis(), "admin", true,
                                                "$2a$12$zmoai/ByAR.37XHRr.O4R.DvHo.9PM/ToN3nGPazhnld/CQuaGIX.", null,
                                                false));

                // ? SOME USERS TO DEMO

                // 1 = kucendro / 1234
                String user1 = "" + System.currentTimeMillis() + 1;
                users.addUser(new User(user1, "kucendro", false,
                                "$2a$12$zmoai/ByAR.37XHRr.O4R.DvHo.9PM/ToN3nGPazhnld/CQuaGIX.", user1, false));

                persons.addPerson(new Person(user1, "Ondřej", "Kučera", "Mr.", "", "03-04-2008", "000000/1000",
                                "Pardubice 142", "Pardubice", "53821", "CZ", "+420721467488", "ondrej@kucendro.eu",
                                false, true,
                                false));

                // 2 = bajer / 1234
                String user2 = "" + System.currentTimeMillis() + 2;
                users.addUser(new User(user2, "bajer", false,
                                "$2a$12$zmoai/ByAR.37XHRr.O4R.DvHo.9PM/ToN3nGPazhnld/CQuaGIX.", user2, false));

                persons.addPerson(new Person(user2, "Libor", "Bajer", "Ing.", "", "12-11-2005", "051112/1234",
                                "Nad Vodovodem 1", "Brno", "61200", "CZ", "+420777123456", "martin@bajer.eu", false,
                                false,
                                false));
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