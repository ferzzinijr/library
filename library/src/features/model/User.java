package features.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    public boolean is_admin;

    public User() {}

    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        is_admin = isAdmin;
    }

    public int GetId() { return this.id; }

    public String GetUsername() { return this.username; }

    public String GetPassword() { return this.password; }

    public boolean GetIsAdmin() { return this.is_admin; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.is_admin = isAdmin;
    }
}
