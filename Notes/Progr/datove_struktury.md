# Datové struktury

---

- Datové struktury jsou způsoby, jakým uvováváme a organizejeme data, aby byla eleftivně přístupná a manipulovantelná.
- Každá datová struktura má své [**výhody**]() a [**nevýhody**](), které závisí na tom, jakým způsobem ji chceme používat


[Array List](#array-list) | [Linked List](#linked-list) | [BST - Binary Search Tree]() | [Hash Table]()
:--:|:----------------:|:----------------------------:|:-:

___

## Typy datových struktur

### Seznamy
- Umožňují uložit kolekci prvků a manipolovat s nimi.
- V závoslosti na implementaci se liší v rychlosti přístupů a efektivitě operací.

### Stromy
- Umožnují uspořídat data ve frome hiearchie.
Poskytují rychlý přístup k datům na základě určitých pravidel.

### Hashovací Tabulka
- Používá klíče a hashovací funkce k rychlému vyhledávání dat, i když neudržuje žádné konkrétní pořadí.

---

## Notace O
**O()** je způsob, jak popisujeme **časovou složitost algoritmů**. Pomáhá nám odhadnout, jak rychle roste doba běhu algoritmu, když se zvyšuje počet vstupních dat.

Je to způsob, jak vyjádřit, jak se výkon algoritmu chová v závislosti na velikosti vstupních dat (označovaných jako n). Tato notace se používá pro analýzu efektivity algoritmů.

### Jak to funguje:

`O(n)` – Časová složitost roste lineárně s velikostí vstupních dat. Tedy, pokud zdvojnásobíš počet dat, čas potřebný pro výpočet se také zdvojnásobí.

- *Algoritmus, který prochází všechny prvky v seznamu jednou (například hledání v seznamu bez jakéhokoliv řazení).*
---

`O(1)` - Časová složitost nezávisí na velikosti vstupních dat. Algoritmus je vždy stejně rychlý, bez ohledu na to, kolik dat máš.

- *Algoritmus, který potřebuje pouze jednu operaci bez ohledu na velikost dat (například přístup k jednomu prvku v poli).*

---

`O(n^2)` - Čas roste čtvercově s počtem dat. Pokud máš 10 dat, algoritmus bude trvat 100 kroků, při 100 datech 10 000 kroků.

- *Algoritmus, který porovnává každý prvek se všemi ostatními (například některé seřazovací algoritmy, jako bublinkové seřazení).*

---

`O(log n)` - Časová složitost roste logaritmicky. Čas se zvětšuje pomaleji než u lineární složitosti. Tento typ složitosti je běžný u algoritmů jako binární vyhledávání.

`Tato notace nám pomáhá pochopit, jak efektivní je algoritmus při práci s velkými daty. Když se velikost dat (n) zvětšuje, je dobré mít algoritmus s co nejnižší složitostí, aby výpočet zůstal co nejrychlejší i pro obrovské množství informací.`

---

### ARRAY LIST
`Jako řada lidí v autobuse`

Představ si, že máš řadu lidí, kteří stojí v autobuse. Všichni mají pevně dané místo, které je přímo vedle sebe. Takto to funguje u ArrayListu – každý prvek (člověk) je na pevném místě a má přesně určitou pozici.

- **Rychlý přístup:**
  - *Když chceš oslovit nějakého člověka v řadě, jednoduše si spočítáš, na jakém místě je, a jdeš tam přímo. Takže pokud víš, že chceš člověka na 5. místě, jdeš přímo k němu.*


- **Problém při přidávání/mazání:**
  - *Když chceš někoho do té řady přidat nebo vyndat, ostatní lidi musí trochu popojet nebo ustoupit, což může být pomalejší, protože musíš přeskupit všechny ostatní.*


### LINKED LIST
`Jako řetěz s odkazy`

Teď si představ řetěz, kde každý článek (člověk) drží v ruce odkaz na dalšího. Takto funguje **LinkedList** – každý prvek (člověk) není na pevném místě, ale má odkaz na následující prvek.

- **Rychlé přidávání/mazání:**

  - *Když chceš přidat nového člověka do řetězu, můžeš ho jednoduše připojit mezi dva články, aniž bys musel přesouvat ostatní. To je super pro případy, kdy často přidáváš nebo odebíráš lidi z různých míst.*


- **Pomalejší přístup:**
  - *Když chceš oslovit nějakého konkrétního člověka, musíš jít krok za krokem, protože nemáš přímý přístup k místu, kde je. Takže když chceš člověka na 5. místě, musíš se dostat k prvnímu, druhému, třetímu, a až pak k tomu pátému.*

### BINARY SEARCH TREE
`Strom, který roste podle pravidel.`

Představ si, že máš strom a každý uzel (větvičku) v tomto stromu má hodnotu, a zároveň je ve správném pořadí. To znamená, že pokud se podíváš na nějaký uzel, všechny hodnoty v jeho levém podstromu (na levých větvích) jsou menší, a všechny hodnoty v pravém podstromu (na pravých větvích) jsou větší.

- **Vkládání:**
  - *Pokud chceš přidat novou hodnotu, začneš u kořene (vrchol stromu) a podle toho, jestli je hodnota menší nebo větší než ta, kterou zrovna procházíš, se rozhodneš, jestli půjdeš doleva nebo doprava. Opakuješ to, dokud nenajdeš místo pro novou hodnotu.*


- **Hledání:**
  - *Když hledáš hodnotu, opět začínáš u kořene a podle toho, jestli hledáš menší nebo větší hodnotu, jdeš buď doleva nebo doprava. Rychle se dostaneš k hledané hodnotě, pokud je strom dobře vyvážený.*

### HASH TABLE
`Seznam s klíčem pro rychlý přístup`

Představ si knihovnu, kde každý regál má přiřazené číslo, a podle tohoto čísla hledáš konkrétní knihu. Hashovací tabulka používá klíče a hashovací funkce k tomu, aby rychle zjistila, kam daný objekt uložit, nebo jak ho najít.

- **Vkládání:**
  - *Když chceš vložit hodnotu, použiješ hashovací funkci, která vezme tvůj klíč (například jméno nebo ID) a přepočítá ho na číselnou hodnotu (hash). Tento hash ti pak ukáže konkrétní místo (index) v tabulce, kam hodnotu uložíš.*


- **Hledání:**
  - *Když hledáš hodnotu, opět použiješ hashovací funkci na klíč a dostaneš místo, kde se hledaná hodnota nachází.*


- **Kollize:**
  -  *Co když dvě různé hodnoty vyústí v ten samý hash? To se nazývá kolize, a pro tento případ existují různé metody, jak je řešit, například řetězení (kde se v tomto místě v tabulce ukládá více hodnot do seznamu).*


- **Rychlé vyhledávání:**
  - *Pokud je dobře implementovaná, můžeš najít hodnotu za O(1) čas, což znamená, že nezáleží na velikosti tabulky.*