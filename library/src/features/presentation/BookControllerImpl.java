package features.presentation;

import features.datasource.BookDatabase;
import features.model.Book;

import java.util.List;

public class BookControllerImpl implements BookController {
    private BookView bookView;

    private final BookDatabase bookDatabase;

    public BookControllerImpl(BookDatabase bookDatabase) {this.bookDatabase = bookDatabase;}

    @Override
    public void setView(BookView view) {this.bookView = view;}

    @Override
    public void addBook(String author, String name, boolean isAvailable) {
        bookDatabase.insertBook(author, name, isAvailable);
    }

    @Override
    public void updateBook(String author, String name, boolean isAvailable) {
        bookDatabase.updateBook(author, name, isAvailable);
    }

    @Override
    public void deleteBook(int bookId) {
        bookDatabase.deleteBook(bookId);
    }

    @Override
    public void setAvailable(int bookId) {
        bookDatabase.markBookAvailable(bookId);
    }

    @Override
    public List<Book> getBooks() {
        return bookDatabase.getBooks();
    }

    private void ShowErrorMessage(String msg){
        if (bookView != null)
            bookView.showErrorMessage(msg);
    }
}
