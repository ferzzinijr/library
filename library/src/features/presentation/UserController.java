package features.presentation;

import features.model.User;

import java.util.List;

public interface UserController {
    void setView(UserView view);
    void addUser(String username, String password);
    void updateUser(String username, String password, boolean isAdmin);
    void deleteUser(int userId);
    List<User> getUsers();
}
