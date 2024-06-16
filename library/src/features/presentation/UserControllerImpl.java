package features.presentation;

import features.datasource.UserDatabase;
import features.model.User;

import java.util.List;

public class UserControllerImpl implements UserController {
    private UserView userView;
    private final UserDatabase userDatabase;

    public UserControllerImpl(UserDatabase userDatabase) {this.userDatabase = userDatabase;}

    @Override
    public void setView(UserView view) {this.userView = view;}

    @Override
    public void addUser(String username, String password) {

    }

    @Override
    public void updateUser(String username, String password, boolean isAdmin) {

    }

    @Override
    public void deleteUser(int userId) {

    }

    @Override
    public List<User> getUsers() {
        return userDatabase.getUsers();
    }

}
