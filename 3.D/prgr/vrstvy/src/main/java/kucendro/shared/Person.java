package kucendro.shared;

import java.time.LocalDate;

public class Person {
    private int id;
    private String name;
    private String surname;
    private LocalDate birthDate;

    public Person() {
    };

    public Person(Person otherPerson) {
        this.id = otherPerson.id;
        this.name = otherPerson.name;
        this.surname = otherPerson.surname;
        this.birthDate = otherPerson.birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
