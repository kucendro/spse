# Dokumentace k projektu Správa uživatelů

## Úvod

Tato dokumantace k ručně psanému kódu byla generována pomocí AI inbuild in IDE.
S dokumentací se mi opravdu nechtělo vymýšlet od píky, když už ji budu přepisovat ručně a již teď jsem na projektu strávil více jak 40 (neplacených) hodin.

## 1. Principy a možnosti práce s regulárními výrazy

### 1.1 Použití třídy String s regulárními výrazy

V projektu jsou regulární výrazy využívány především prostřednictvím metody `matches()` třídy String pro validaci uživatelských vstupů. Tento přístup je efektivní pro jednoduché ověřování formátu dat.

**Příklady implementace:**

**Validace jména a příjmení:**

```java
text.matches("^[A-Za-záčďéěíňóřšťúůýžÁČĎÉĚÍŇÓŘŠŤÚŮÝŽ]+$")
```

Tento výraz zajišťuje, že pole obsahuje pouze písmena včetně českých diakritických znamének, bez mezer.

**Validace rodného čísla:**

```java
text.matches("\\d{6}/\\d{4}")
```

Ověřuje formát rodného čísla ve tvaru 6 číslic, lomítko a 4 číslice.

**Validace telefonního čísla:**

```java
text.matches("\\+\\d{12,15}")
```

Kontroluje formát mezinárodního telefonního čísla začínajícího znakem + následovaným 12-15 číslicemi.

### 1.2 Použití RowFilter s regulárními výrazy

Pro filtrování v tabulkách je využívána třída `RowFilter` z balíčku Swing, která podporuje regulární výrazy:

**Filtrování bez rozlišování velikosti písmen:**

```java
RowFilter.regexFilter("(?i)" + text, columnIndex)
```

Použití modifikátoru `(?i)` zajišťuje case-insensitive vyhledávání.

**Přesné párování pro filtry:**

```java
RowFilter.regexFilter("^" + selectedCountry + "$", 7)
```

Kotvy `^` a `$` zajišťují přesnou shodu celého řetězce.

### 1.3 Výhody současného přístupu

Použití metody `matches()` třídy String přináší několik výhod:

- **Jednoduchost implementace:** Přímé volání bez nutnosti vytváření Pattern objektů
- **Čitelnost kódu:** Intuitivní syntax pro základní validace
- **Výkon:** Pro jednorázové použití je `String.matches()` efektivní
- **Integrace s GUI:** Snadné napojení na DocumentListener pro real-time validaci

### 1.4 Implementace real-time validace

Projekt obsahuje sofistikovaný systém okamžité validace vstupů:

```java
nameField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
    private void validate() {
        String text = nameField.getText();
        boolean valid = text.matches("^[A-Za-záčďéěíňóřšťúůýžÁČĎÉĚÍŇÓŘŠŤÚŮÝŽ]+$") && !text.contains(" ");
        nameField.setForeground(valid ? Color.BLACK : Color.RED);
    }
    // implementace všech tří metod DocumentListener
});
```

Tento přístup zajišťuje:

- Okamžitou vizuální odezvu při neplatném vstupu
- Prevenci odeslání nevalidních dat
- Lepší uživatelskou zkušenost

### 1.5 Alternativní přístupy s Pattern a Matcher

Ačkoliv projekt nevyužívá přímo třídy `Pattern` a `Matcher`, tyto by mohly být implementovány pro komplexnější validace:

**Pattern.compile() pro opakované použití:**

```java
private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-záčďéěíňóřšťúůýžÁČĎÉĚÍŇÓŘŠŤÚŮÝŽ]+$");
private static final Pattern PHONE_PATTERN = Pattern.compile("\\+\\d{12,15}");
```

**Matcher pro pokročilé operace:**

- `Matcher.find()` pro hledání vzorů v textu
- `Matcher.group()` pro extrakci částí textu
- `Matcher.replaceAll()` pro nahrazování podle vzoru

### 1.6 Regulární výrazy v kontextu aplikace

Jednotlivé regex vzory jsou navrženy s ohledem na specifické požadavky:

**Mezinárodní podpora znaků:**
Vzor pro jména zahrnuje kompletní sadu českých diakritických znamének, což zajišťuje správnou validaci lokalizovaných jmen.

**Flexible telefonnní čísla:**
Rozsah 12-15 číslic pokrývá většinu mezinárodních formátů, přičemž požadavek na prefix + zajišťuje standardizovaný mezinárodní formát.

## 2. Struktura Java Collections Framework a Stream API

### 2.1 Využité kolekce z JCF

Projekt primárně využívá `HashMap<String, Object>` pro ukládání dat:

**UsersTable:**

```java
public HashMap<String, User> users;
```

**PersonsTable:**

```java
public HashMap<String, Person> persons;
```

Výhody HashMap:

- O(1) průměrná složitost pro základní operace (get, put, remove)
- Efektivní vyhledávání podle klíče (ID uživatele/osoby)
- Vhodné pro často měněná data

### 2.2 Implementace Stream API

Stream API je využíváno především ve třídě `Filtering` pro zpracování kolekcí:

**Získání unikátních kódů zemí:**

```java
Stream<String> countryStream = personsTable.getAllPersons().values().stream()
    .map(Person::getCountryCode)
    .distinct()
    .sorted();
```

**Slučování streamů:**

```java
String[] countryCodes = Stream.concat(Stream.of("All"), countryStream)
    .toArray(String[]::new);
```

### 2.3 Operace se streamy

Projekt demonstruje následující Stream operace:

- **Intermediate operations:** `map()`, `distinct()`, `sorted()`
- **Terminal operations:** `toArray()`
- **Stream kombinace:** `Stream.concat()`

### 2.4 Metoda odkazování (Method Reference)

Použití `Person::getCountryCode` představuje method reference, což je zkrácený zápis lambda výrazu `person -> person.getCountryCode()`.

**Výhody method references:**

- **Čitelnost:** Kód je stručnější a expresivnější
- **Výkon:** Potenciálně lepší optimalizace ze strany JVM
- **Type safety:** Kompilační ověření existence metody

### 2.5 Detailed analýza Stream pipeline

Kompletní pipeline pro získání kódů zemí:

```java
Stream<String> countryStream = personsTable.getAllPersons().values().stream()
    .map(Person::getCountryCode)    // Transformace Person -> String
    .distinct()                     // Odstranění duplicit
    .sorted();                      // Seřazení abecedně
```

**Krok za krokem:**

1. `getAllPersons().values()` - získání Collection<Person>
2. `.stream()` - konverze na Stream<Person>
3. `.map(Person::getCountryCode)` - transformace na Stream<String>
4. `.distinct()` - filtrování unikátních hodnot
5. `.sorted()` - řazení podle natural ordering

### 2.6 Výkonnost a optimalizace kolekcí

**HashMap charakteristiky:**

- **Load factor:** Default 0.75 poskytuje dobrý kompromis mezi pamětí a výkonem
- **Initial capacity:** Pro malé kolekce (typicky <100 záznamů) je default 16 dostatečný
- **Hash funkce:** String klíče poskytují dobré rozložení hashe

**Singleton pattern pro kolekce:**
Centralizovaná správa zajišťuje:

- Konzistenci dat napříč aplikací
- Lazy initialization pro úsporu paměti
- Thread-safe přístup (při správné implementaci)

### 2.7 Kolekce vs. tradiční přístupy

Projekt demonstruje přechod od imperativního k deklarativnímu stylu:

**Imperativní přístup (nepoužito):**

```java
List<String> countries = new ArrayList<>();
for (Person person : persons.values()) {
    String country = person.getCountryCode();
    if (!countries.contains(country)) {
        countries.add(country);
    }
}
Collections.sort(countries);
```

**Stream API přístup (použito):**

```java
String[] countries = persons.values().stream()
    .map(Person::getCountryCode)
    .distinct()
    .sorted()
    .toArray(String[]::new);
```

## 3. Vlastní řešení

### 3.1 Architektura aplikace

Aplikace využívá **Singleton pattern** pro správu dat:

- `UsersTable.getInstance()` - centralizovaná správa uživatelů
- `PersonsTable.getInstance()` - správa osobních údajů

### 3.2 Oddělení zodpovědností

Projekt dodržuje princip **Separation of Concerns**:

- **Model:** `User`, `Person` - datové struktury
- **Data Access:** `UsersTable`, `PersonsTable` - správa dat
- **Business Logic:** `VerifyCredentials`, `StoreCredentials` - autentizace
- **Presentation:** `GUI` - uživatelské rozhraní
- **Utility:** `Filtering` - pomocné funkce pro filtrování

### 3.3 Bezpečnost

Implementace bezpečnostních opatření:

- **BCrypt hashing** pro ukládání hesel
- **Validace vstupů** pomocí regulárních výrazů v reálném čase
- **Vizuální feedback** - červené zbarvení nevalidních vstupů

### 3.4 Uživatelské rozhraní

GUI využívá **Swing framework** s následujícími prvky:

- **Real-time validace** s okamžitou vizuální odezvou
- **Dynamické filtrování** tabulek pomocí `DocumentListener`
- **Modální dialogy** pro potvrzení kritických operací

### 3.5 Detailní analýza Singleton pattern

Implementace Singleton pattern v projektu:

```java
public static UsersTable getInstance() {
    if (instance == null) {
        instance = new UsersTable();
    }
    return instance;
}
```

**Výhody implementace:**

- **Lazy initialization:** Instance se vytvoří až při prvním použití
- **Jednoduchost:** Minimalistická implementace bez synchronizace
- **Paměťová efektivita:** Pouze jedna instance pro celou aplikaciju

**Potenciální zlepšení:**
Pro produkční použití by bylo vhodné implementovat thread-safe verzi:

```java
private static volatile UsersTable instance;
public static UsersTable getInstance() {
    if (instance == null) {
        synchronized (UsersTable.class) {
            if (instance == null) {
                instance = new UsersTable();
            }
        }
    }
    return instance;
}
```

### 3.6 Bezpečnostní architektura

**BCrypt implementace:**

```java
String bcryptHash = BCrypt.hashpw(String.valueOf(password), BCrypt.gensalt(12));
```

Parametry zabezpečení:

- **Salt rounds: 12** - dostatečné pro současné bezpečnostní požadavky
- **Automatic salt generation** - každé heslo má unikátní salt
- **Secure password verification** - `BCrypt.checkpw()` zabraňuje timing attacks

**Password handling best practices:**

- Použití `char[]` místo `String` pro hesla v paměti
- Okamžité mazání hesel z paměti po použití
- Žádné logování nebo ukládání plain-text hesel

### 3.7 Event-driven architektura GUI

Aplikace využívá pokročilé event handling:

**DocumentListener pro real-time validaci:**

```java
Search.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
    private void updateFilter() {
        String text = Search.getText();
        TableRowSorter<?> sorter = (TableRowSorter<?>) table.getRowSorter();
        if (text.trim().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, columnIndex));
        }
    }
    // implementace všech metod
});
```

**ActionListener pro user actions:**

- Lambda expressions pro stručnější kód
- Callback pattern pro refresh operace
- Modal dialogs pro kritické akce

### 3.8 Modularita a rozšiřitelnost

**Filtering utility class:**
Statické metody pro opakovaně používané filtry:

- `searchfilter()` - obecný textový filtr
- `booleanfilter()` - filtr pro boolean hodnoty
- `countryfilter()` - specializovaný filtr pro země

**Výhody modularizace:**

- **DRY principle:** Znovupoužitelný kód
- **Maintenance:** Centralizované změny
- **Testing:** Izolované testování jednotlivých komponent

### 3.9 Efektivita a optimalizace

**Memory management:**

- **Lazy initialization** u Singleton instancí
- **Efficient data structures** - HashMap pro O(1) přístup
- **Event-driven updates** - pouze když je potřeba

**Performance optimizations:**

- **Stream API** pro deklarativní zpracování kolekcí
- **Method references** pro lepší JVM optimalizace
- **TableRowSorter** s indexovaným přístupem k datům

**UI responsiveness:**

- **SwingUtilities.invokeLater()** pro thread-safe UI updates
- **Non-blocking validation** pomocí DocumentListener
- **Efficient table filtering** bez kompletního refresh

## Technické specifikace

### 4.1 Použité technologie a závislosti

**Core technologies:**

- **Java SE 17** - Využití moderních Java features
- **Swing Framework** - Native desktop GUI
- **Maven** - Build management a dependency resolution

**External dependencies:**

- **BCrypt (org.mindrot.jbcrypt:0.4)** - Secure password hashing
- **Podpora pouze bezpečných, ověřených knihoven**

### 4.2 Struktura projektu

```
src/main/java/sprava_uzivatelu/
├── Main.java              - Entry point s demo daty
├── GUI.java               - Hlavní UI komponenta
├── User.java              - Model pro uživatele
├── Person.java            - Model pro osobní údaje
├── UsersTable.java        - Data access pro uživatele
├── PersonsTable.java      - Data access pro osoby
├── VerifyCredentials.java - Autentizace
├── StoreCredentials.java  - Registrace
├── Filtering.java         - Utility pro filtrování
```

### 4.3 Design patterns použité v projektu

**Singleton Pattern:**

- Zajišťuje jedinou instanci pro data tables
- Globální přístup k datům
- Lazy initialization pro efektivitu

**Observer Pattern:**

- DocumentListener pro real-time validaci
- ActionListener pro UI events
- Callback pattern pro refresh operace

**Utility Pattern:**

- Statické metody ve Filtering class
- Reusable validation logic
- Centralizované pomocné funkce

### 4.4 Datový model a relationships

**User entity:**

```java
- id: String (unique identifier)
- username: String (login name)
- admin: boolean (permission level)
- password: String (BCrypt hash)
- person_id: String (foreign key)
- removed: boolean (soft delete)
```

**Person entity:**

```java
- id: String (matches user.person_id)
- personal info: name, surname, titles
- birth info: dateOfBirth, birthNumber
- contact info: address, phone, email
- status flags: ZTP, student, retired
```

**Relationships:**

- One-to-One: User ↔ Person (optional)
- Foreign key relationship via person_id

## Zdroje

https://www.itnetwork.cz/java/swing/swing-bez-navrhare/tutorial-layouty-java-okenni-aplikace

https://stackoverflow.com/questions/27687427/how-to-create-a-swing-application-with-multiple-pages

https://docs.oracle.com/javase/8/docs/api/javax/swing/JPanel.html

https://www.w3schools.com/java/java_hashmap.asp

https://www.geeksforgeeks.org/java/write-hashmap-to-a-text-file-in-java/

https://www.geeksforgeeks.org/java/stream-filter-java-examples/

https://docs.oracle.com/javase/8/docs/api/javax/swing/table/TableRowSorter.html
