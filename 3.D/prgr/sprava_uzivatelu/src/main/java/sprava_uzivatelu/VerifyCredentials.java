package sprava_uzivatelu;

import org.mindrot.jbcrypt.BCrypt;

public class VerifyCredentials {
    public static Object verify(String logUserName, char[] logPassword) {

        UsersTable users = UsersTable.getInstance();
        User user = users.findByUsername(logUserName);

        if (user != null && BCrypt.checkpw(String.valueOf(logPassword), user.getPassword())) {
            return user;
        } else {
            return null;
        }

        // TODO Use this later for storing collections to CSVs

        // try {
        // File csv = new File(StoreCredentials.storeDir + "/" +
        // StoreCredentials.csvCredentials);
        // String storeUser = null;
        // String storeHash = null;
        // java.util.Scanner scanik = new Scanner(csv);
        // scanik.nextLine();
        // while (scanik.hasNextLine()) {
        // String[] data = scanik.nextLine().split(", ");
        // storeUser = data[0];
        // storeHash = data[1];
        // }

        // scanik.close();

        // if (logUserName.equals(storeUser) &&
        // BCrypt.checkpw(String.valueOf(logPassword), storeHash)) {
        // return storeUser;
        // } else if (logUserName.equals(storeUser) &&
        // !BCrypt.checkpw(String.valueOf(logPassword), storeHash)) {
        // System.out.println("\u001B[31m" + "Wrong password." + "\u001B[0m");
        // return null;
        // } else {
        // System.out.println("\u001B[31m" + "User not found." + "\u001B[0m");
        // return null;
        // }
        // } catch (Exception e) {
        // System.err.println("\u001B[31m" + "Error reading CSV file: " + e.getMessage()
        // + "\u001B[0m");
        // return null;
        // }
    }
}
