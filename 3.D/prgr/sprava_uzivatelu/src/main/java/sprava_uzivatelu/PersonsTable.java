package sprava_uzivatelu;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;

public class PersonsTable {

    public static String personsCSV = "persons.csv";

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

    private void clearFile() {
        try {
            new PrintWriter(new FileWriter(UsersTable.storeDir + "/" + personsCSV)).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void store() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(UsersTable.storeDir + "/" + personsCSV, true));
            for (Person person : persons.values()) {
                writer.println(person.getId() + "; " + person.getName() + "; " + person.getSurname() + "; "
                        + person.getTitleBefore() + "; "
                        + person.getTitleAfter() + "; " + person.getDateOfBirth() + "; " + person.getBirthNumber()
                        + "; " + person.getAddress() + "; " + person.getCountryCode() + "; " + person.getCity()
                        + "; " + person.getPostalCode() + "; " + person.getPhoneNumber() + "; " + person.getEmail()
                        + "; " + person.isZTP() + "; " + person.isStudent() + "; " + person.isRetired());
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        if (new File(UsersTable.storeDir).isDirectory() && new File(UsersTable.storeDir + "/" + personsCSV).exists()) {
            clearFile();
            store();
        } else if (new File(UsersTable.storeDir).isDirectory()
                && !new File(UsersTable.storeDir + "/" + personsCSV).exists()) {
            new File(UsersTable.storeDir + "/" + personsCSV);
            store();
        } else {
            // create dir and file
            new File(UsersTable.storeDir).mkdirs();
            new File(UsersTable.storeDir + "/" + personsCSV);
            store();
        }
    }

    public void load() {
        try {
            File file = new File(UsersTable.storeDir + "/" + personsCSV);
            if (!file.exists()) {
                return;
            }
            java.util.Scanner scanner = new java.util.Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("; ");
                Person person = new Person(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6],
                        parts[7], parts[8], parts[9], parts[10], parts[11], parts[12],
                        Boolean.parseBoolean(parts[13]), Boolean.parseBoolean(parts[14]),
                        Boolean.parseBoolean(parts[15]));
                persons.put(person.getId(), person);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}