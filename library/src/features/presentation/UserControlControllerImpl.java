package features.presentation;

import features.datasource.UserDatabase;
import features.model.User;

import java.util.List;

public class UserControlControllerImpl implements UserControlController {
    private UserControlView userControlView;
    private final UserDatabase userDatabase;

    public UserControlControllerImpl(UserDatabase userDatabase) {this.userDatabase = userDatabase;}

    @Override
    public void setView(UserControlView view) {

    }

    @Override
    public void addUser(String username, String password, boolean isAdmin) {
        userDatabase.insertUser(username, password, isAdmin);
    }

    @Override
    public void updateUser(int userId, String username, String password, boolean isAdmin) {
        userDatabase.editUser(userId, username, password, isAdmin);
    }

    @Override
    public void deleteUser(int userId) {
        userDatabase.deleteUser(userId);
    }

    @Override
    public List<User> getUsers() {
        return userDatabase.getUsers();
    }
}
