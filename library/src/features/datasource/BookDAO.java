package features.datasource;

import features.model.Book;
import infrastructure.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

public class BookDAO implements BookDatabase, BookSubscriber {
    private final List<BookListener> listeners;

    public BookDAO() {this(new ArrayList<>());}
    public BookDAO(List<BookListener> listeners) {this.listeners = listeners;}

    @Override
    public void subscribe(BookListener bookListener) {listeners.add(bookListener);}

    private void notifyDataChanged() {
        for (BookListener listener : listeners)
            listener.updatedata();
    }

    @Override
    public void insertBook(String author, String name, boolean isAvailable) {

    }

    @Override
    public void updateBook(String author, String name, boolean isAvailable) {

    }

    @Override
    public void deleteBook(int bookId) {
        try {
            DatabaseManager.getSessionFactory().fromTransaction(session -> {
                Book bookToDelete = session.find(Book.class, bookId);

                session.remove(bookToDelete);

                notifyDataChanged();

                return null;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> getBooks() {
        List<Book> result = new ArrayList<>();

        try {
            result = DatabaseManager.getSessionFactory().fromTransaction(session -> {
                return session.createSelectionQuery("from Book", Book.class)
                        .getResultList();
            });
        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void markBookAvailable(int bookId) {

    }
}
