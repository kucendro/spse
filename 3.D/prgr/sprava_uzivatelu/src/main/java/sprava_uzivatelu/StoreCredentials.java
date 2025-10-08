package sprava_uzivatelu;

import org.mindrot.jbcrypt.BCrypt;

public class StoreCredentials {

    // public static String storeDir = "store";
    // public static String csvCredentials = "credentials.csv";

    public static void hash(String username, char[] password) {

        String bcryptHash = BCrypt.hashpw(String.valueOf(password), BCrypt.gensalt(12));

        String id = String.valueOf(System.currentTimeMillis());

        UsersTable users = UsersTable.getInstance();
        users.addUser(new User(id, username, false, bcryptHash, null, false));

        // TODO Use this later for storing collections to CSVs

        // if (new File(storeDir).exists() && new File(storeDir + "/" +
        // csvCredentials).exists()) {
        // try {
        // PrintWriter writer = new PrintWriter(new FileWriter(storeDir + "/" +
        // csvCredentials, true));
        // writer.println(username + ", " + bcryptHash);
        // writer.close();
        // } catch (Exception e) {
        // System.err.println("\u001B[31m" + "Error creating CSV file: " +
        // e.getMessage() + "\u001B[0m");
        // }

        // } else if (new File(storeDir).isDirectory() && !new File(storeDir + "/" +
        // csvCredentials).exists()) {

        // File csv = new File(storeDir + "/" + csvCredentials);
        // try {
        // PrintWriter writer = new PrintWriter(csv);
        // writer.println(username + ", " + bcryptHash);
        // writer.close();
        // } catch (Exception e) {
        // System.err.println("\u001B[31m" + "Error creating CSV file: " +
        // e.getMessage() + "\u001B[0m");
        // }

        // } else {
        // new File(storeDir).mkdir();
        // File csv = new File(storeDir + "/" + csvCredentials);

        // try {
        // PrintWriter writer = new PrintWriter(csv);
        // writer.println(username + ", " + bcryptHash);
        // writer.close();
        // } catch (Exception e) {
        // System.err.println("\u001B[31m" + "Error creating CSV file: " +
        // e.getMessage() + "\u001B[0m");
        // }
        // }

    }
}