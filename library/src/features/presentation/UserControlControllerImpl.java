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
    public void addUser(String username, String password) {

    }

    @Override
    public void updateUser(String username, String password) {

    }

    @Override
    public void deleteUser(int userId) {

    }

    @Override
    public List<User> getUsers() {
        return null;
    }
}
