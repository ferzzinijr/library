package features.datasource;

import features.model.User;
import infrastructure.DatabaseManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDAO implements UserDatabase, UserSubscriber {

    private final List<UserListener> listeners;

    public UserDAO() {this(new ArrayList<>());}
    public UserDAO(List<UserListener> listeners) {this.listeners = listeners;}

    @Override
    public void subscribe(UserListener userListener) {listeners.add(userListener);}

    private void notifyDataChanged() {
        for (UserListener listener : listeners)
            listener.updatedata();
    }

    @Override
    public User getUser(String username, String password) {
        User user = null;
        SessionFactory sessionFactory = DatabaseManager.getSessionFactory();

        try (Session session = DatabaseManager.getSessionFactory().openSession()) {
            String sql = "FROM User WHERE username = :username AND password = :password";
            return session.createQuery(sql, User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public List<User> getUsers() {
        List<User> result = new ArrayList<>();

        try {
            result = DatabaseManager.getSessionFactory().fromTransaction(session -> {
                return session.createSelectionQuery("from User", User.class)
                        .getResultList();
            });
        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void insertUser(String username, String password, boolean isAdmin) {
        try {
            DatabaseManager.getSessionFactory().inTransaction(session -> {
                var user = new User(username, password, isAdmin);
                session.persist(user);
            });

            notifyDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editUser(int userId, String username, String password, boolean isAdmin) {
        try {
            DatabaseManager.getSessionFactory().fromTransaction(session -> {
                var user = session.get(User.class, userId);
                user.setUsername(username);
                user.setPassword(password);
                user.setIsAdmin(isAdmin);
                session.persist(user);

                notifyDataChanged();
                return null;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int userId) {
        try {
            DatabaseManager.getSessionFactory().fromTransaction(session -> {
                User userToDelete = session.find(User.class, userId);

                session.remove(userToDelete);

                notifyDataChanged();

                return null;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
