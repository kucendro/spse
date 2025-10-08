package sprava_uzivatelu;

import java.util.HashMap;

public class PersonsTable {

    private static PersonsTable instance;

    public HashMap<String, Person> persons;

    private PersonsTable() {
        persons = new HashMap<>();
    }

    public static PersonsTable getInstance() {
        if (instance == null) {
            instance = new PersonsTable();
        }
        return instance;
    }

    public void addPerson(Person person) {
        persons.put(person.getId(), person);
    }

    public Person getPerson(String id) {
        return persons.get(id);
    }

    public void removePerson(String id) {
        persons.remove(id);
    }

    public HashMap<String, Person> getAllPersons() {
        return persons;
    }

    public int getPersonCount() {
        return persons.size();
    }

    public boolean isEmpty() {
        return persons.isEmpty();
    }


    public void printPersons() {
        for (Person person : persons.values()) {
            System.out.println(person);
        }
    }
}
