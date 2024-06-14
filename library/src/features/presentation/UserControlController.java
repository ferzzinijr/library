package features.presentation;

import features.model.User;

import java.util.List;

public interface UserControlController {
    void setView(UserControlView view);
    void addUser(String username, String password);
    void updateUser(String username, String password);
    void deleteUser(int userId);
    List<User> getUsers();
}
