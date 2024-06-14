package features.datasource;

import features.model.Book;

import java.util.List;

public interface BookDatabase {
    void insertBook(String author, String name, boolean isAvailable, int daystoReserve);
    void updateBook(int bookId, String author, String name, boolean isAvailable, int daysToReserve);
    void deleteBook(int bookId);
    List<Book> getBooks();
    void markBookAvailable(int bookId);
}
