package sprava_uzivatelu;

public class User {
    public String id;
    public String username;
    public boolean admin;
    public String password;
    public String person_id = null;
    public boolean removed;

    public User(String id, String username, boolean admin, String password, String person_id, boolean removed) {
        this.id = id;
        this.username = username;
        this.admin = admin;
        this.password = password;
        this.person_id = person_id;
        this.removed = removed;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersonId() {
        return person_id;
    }

    public void setPerson_Id(String person_id) {
        this.person_id = person_id;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", admin=" + admin +
                ", personid=" + person_id +
                '}';
    }
}
