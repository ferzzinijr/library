package features.presentation;

import features.datasource.BookListener;
import features.datasource.BookSubscriber;
import features.model.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BookViewImpl extends JFrame implements BookView, BookListener {

    private DefaultTableModel table;
    private final BookController bookController;

    public BookViewImpl(BookSubscriber bookSubscriber, BookController bookController) {
        setTitle("Book List");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        bookSubscriber.subscribe(this);
        this.bookController = bookController;
        bookController.setView(this);

        InitializeUI();

        loadBooks();
    }

    private void InitializeUI() {
        setLayout(new BorderLayout());

        table = new DefaultTableModel(new Object[]{"ID", "AUTHOR", "NAME", "IS_AVAILABLE", "DAYS_TO_RESERVE"}, 0);
        JTable bookTable = new JTable(table);
        JScrollPane scrollPane = new JScrollPane(bookTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para editar um livro
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTask(bookTable.getSelectedRow());
            }
        });

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadBooks() {
        table.setRowCount(0);

        List<Book> books = bookController.getBooks();

        for (Book book : books)
            table.addRow(new Object[]{book.getId(), book.getAuthor(), book.getName(), book.isAvailable(), book.getReservedDays()});
    }

    @Override
    public void updatedata() {
        loadBooks();
    }

    @Override
    public void open() {
        setVisible(true);
    }

    @Override
    public void showErrorMessage(String msg) {

    }

    private void deleteTask(int row) {
        int bookId = (int) table.getValueAt(row, 0);

        bookController.deleteBook(bookId);
    }
}
