package features.datasource;

import features.model.User;

import java.util.List;

public interface UserDatabase {
     User getUser(String username, String password);
     List<User> getUsers();
     void insertUser(String username, String password, boolean isAdmin);
     void editUser(int userId, String username, String password, boolean isAdmin);
     void deleteUser(int userId);
}
