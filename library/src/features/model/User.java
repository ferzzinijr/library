package features.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private int id;

    private String username;

    private String password;

    public int is_admin;

    public User() {}

    public int GetId() { return this.id; }

    public String GetUsername() { return this.username; }

    public String GetPassword() { return this.password; }

    public int GetIsAdmin() { return this.is_admin; }
}
