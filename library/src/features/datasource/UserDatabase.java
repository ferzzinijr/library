package features.datasource;

import features.model.User;

public interface UserDatabase {
     User getUser(String username, String password);
}
