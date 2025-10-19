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

    }
}
