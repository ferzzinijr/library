package features.datasource;

import features.model.Book;

import java.util.List;

public interface BookDatabase {
    void insertBook(String author, String name, boolean isAvailable);
    void updateBook(String author, String name, boolean isAvailable);
    void deleteBook(int bookId);
    List<Book> getBooks();
    void markBookAvailable(int bookId);
}
