package sprava_uzivatelu;

import java.util.HashMap;

import javax.swing.JOptionPane;

public class UsersTable {

    private static UsersTable instance;

    public HashMap<String, User> users;

    private UsersTable() {
        users = new HashMap<>();
    }

    public static UsersTable getInstance() {

        if (instance == null) {
            instance = new UsersTable();
        }
        return instance;
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public User getUser(String id) {
        return users.get(id);
    }

    public void removeUser(String id) {
        users.remove(id);
    }

    public HashMap<String, User> getAllUsers() {
        return users;
    }

    public int getUserCount() {
        return users.size();
    }

    public boolean isEmpty() {
        return users.isEmpty();
    }

    public User findByUsername(String username) {
        for (User user : users.values()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public Person getPersonByUserId(String userId) {
        User user = getUser(userId);
        if (user != null) {
            PersonsTable personsTable = PersonsTable.getInstance();
            return personsTable.getPerson(user.person_id);
        }
        return null;
    }

    public void printUsers() {
        for (User user : users.values()) {
            System.out.println(user);
        }
    }

    public void printUser(String id) {
        User user = users.get(id);
        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("User with ID " + id + " not found.");
        }
    }

    public void assignPersonToUser(String id, Person person) {
        User user = users.get(id);
        if (user != null) {
            user.setPerson_Id(person.id);
        } else {
            JOptionPane.showMessageDialog(null, "User with ID " + id + " not found.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateUser(User user) {
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
        } else {
            JOptionPane.showMessageDialog(null, "User with ID " + user.getId() + " not found.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
