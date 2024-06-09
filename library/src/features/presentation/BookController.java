package features.presentation;

import features.model.Book;
import java.util.List;

public interface BookController {
    void setView(BookView view);
    void addBook(String author, String name, boolean isAvailable);
    void updateBook(String author, String name, boolean isAvailable);
    void deleteBook(int bookId);
    void setAvailable(int bookId);
    List<Book> getBooks();
}
