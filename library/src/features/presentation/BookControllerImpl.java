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
    public void addBook(String author, String name, boolean isAvailable, int daysToReserve) {
        bookDatabase.insertBook(author, name, isAvailable, daysToReserve);
    }

    @Override
    public void updateBook(int bookId, String author, String name, boolean isAvailable, int daysToReserve) {
        bookDatabase.updateBook(bookId, author, name, isAvailable, daysToReserve);
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
    public void reserveBook(int bookId) {
        bookDatabase.reserveBook(bookId);
    }

    @Override
    public void returnBook(int bookId) {
        bookDatabase.returnBook(bookId);
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
