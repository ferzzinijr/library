package features.presentation;

import features.model.User;

import java.util.List;

public interface UserControlController {
    void setView(UserControlView view);
    void addUser(String username, String password, boolean isAdmin);
    void updateUser(int userId, String username, String password, boolean isAdmin);
    void deleteUser(int userId);
    List<User> getUsers();
}
