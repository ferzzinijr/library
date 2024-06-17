package features.presentation;

import features.model.Book;
import java.util.List;

public interface BookController {
    void setView(BookView view);
    void addBook(String author, String name, boolean isAvailable, int daysToReserve);
    void updateBook(int bookId, String author, String name, boolean isAvailable, int daysToReserve);
    void deleteBook(int bookId);
    void setAvailable(int bookId);
    void reserveBook(int bookId);
    void returnBook(int bookId);
    List<Book> getBooks();
}
