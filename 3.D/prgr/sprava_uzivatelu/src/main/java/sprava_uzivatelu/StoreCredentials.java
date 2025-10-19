package sprava_uzivatelu;

import org.mindrot.jbcrypt.BCrypt;

public class StoreCredentials {

    public static void hash(String username, char[] password) {

        String bcryptHash = BCrypt.hashpw(String.valueOf(password), BCrypt.gensalt(12));

        String id = String.valueOf(System.currentTimeMillis());

        UsersTable users = UsersTable.getInstance();
        users.addUser(new User(id, username, false, bcryptHash, null, false));

    }
}