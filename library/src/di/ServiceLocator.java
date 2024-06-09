package di;

import features.datasource.*;
import features.presentation.*;

public class ServiceLocator {
    private static ServiceLocator instance;

    public static ServiceLocator getInstance() {
        if (instance == null)
            instance = new ServiceLocator();

        return instance;
    }

    private UserDAO userDAO;
    private BookDAO bookDAO;

    private UserDAO getUserDAO() {
        if (userDAO == null)
            userDAO = new UserDAO();

        return userDAO;
    }

    private BookDAO getBookDao() {
        if (bookDAO == null) {
            bookDAO = new BookDAO();
        }

        return bookDAO;
    }

    public UserDatabase getUserDatabase() {return getUserDAO();}
    public UserSubscriber getUserSubscriber() {return getUserDAO();}
    public UserController getUserController() {return new UserControllerImpl(getUserDatabase());}
    public UserView getUserView() {return new UserViewImpl(getUserSubscriber(), getUserController());}

    public BookDatabase getBookDatabase() {return getBookDao();}
    public BookSubscriber getBookSubscriber() {return getBookDao();}
    public BookController getBookController() {return new BookControllerImpl(getBookDatabase());}
    public BookView getBookView() {return new BookViewImpl(getBookSubscriber(), getBookController());}
}
