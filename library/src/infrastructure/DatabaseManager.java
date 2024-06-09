package infrastructure;

import features.model.Book;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.community.dialect.SQLiteDialect;
import features.model.User;

public class DatabaseManager {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                        .applySetting(AvailableSettings.DIALECT, SQLiteDialect.class.getName())
                        .applySetting(AvailableSettings.URL, "jdbc:sqlite:C:/Users/Ferfs/Desktop/SQlite/librarydatabase.db")
                        .applySetting(AvailableSettings.DRIVER, "org.sqlite.JDBC")
                        .applySetting(AvailableSettings.USER, "")
                        .applySetting(AvailableSettings.PASS, "")
                        .build();

                MetadataSources sources = new MetadataSources(registry);

                sources.addAnnotatedClass(User.class);
                sources.addAnnotatedClass(Book.class);

                sessionFactory = sources.buildMetadata().buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to initialize Hibernate SessionFactory");
            }
        }
        return sessionFactory;
    }
}
