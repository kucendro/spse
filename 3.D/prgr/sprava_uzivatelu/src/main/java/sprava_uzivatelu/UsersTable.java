package sprava_uzivatelu;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class UsersTable {

    public static String storeDir = "store";
    public static String usersCSV = "users.csv";

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

    public void assignPersonToUser(String id, String personId) {
        User user = users.get(id);
        if (user != null) {
            user.setPerson_Id(personId);
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

    public void setRemoved(String id, boolean removed) {
        User user = users.get(id);
        if (user != null) {
            user.setRemoved(removed);
        } else {
            JOptionPane.showMessageDialog(null, "User with ID " + id + " not found.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFile() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(storeDir + "/" + usersCSV));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void store() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(storeDir + "/" + usersCSV, true));
            for (User user : users.values()) {
                writer.println(user.getId() + "; " + user.getUsername() + "; " + user.isAdmin() + "; "
                        + user.getPassword() + "; " + user.getPersonId() + "; " + user.isRemoved());
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void save() {
        if (new File(storeDir).isDirectory() && new File(storeDir + "/" + usersCSV).exists()) {
            clearFile();
            store();
        } else if (new File(storeDir).isDirectory() && !new File(storeDir + "/" + usersCSV).exists()) {
            new File(storeDir + "/" + usersCSV);
            store();
        } else {
            new File(storeDir).mkdir();
            new File(storeDir + "/" + usersCSV);
            store();
        }

    }

    public void load() {
        try {
            File file = new File(storeDir + "/" + usersCSV);
            if (!file.exists()) {
                return;
            }
            java.util.Scanner scan = new java.util.Scanner(file);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] parts = line.split("; ");
                if (parts.length >= 6) {
                    String id = parts[0];
                    String username = parts[1];
                    boolean isAdmin = Boolean.parseBoolean(parts[2]);
                    String password = parts[3];
                    String personId = parts[4];
                    boolean isRemoved = Boolean.parseBoolean(parts[5]);
                    User user = new User(id, username, isAdmin, password, personId, isRemoved);
                    addUser(user);
                }
            }
            scan.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
