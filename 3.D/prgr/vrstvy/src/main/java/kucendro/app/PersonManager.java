package kucendro.app;

import java.util.Map;

import kucendro.shared.Person;

public class PersonManager {
    private Map<Integer, Person> persons;

    public PersonManager(Map<Integer, Person> persons) {
        this.persons = persons;
    }

    public Map<Integer, Person> getPersons() {
        return persons;
    }
}
