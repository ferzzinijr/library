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
}
