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
        JButton addButton = new JButton("Adicionar");
        JButton editButton = new JButton("Editar");
        JButton deleteButton = new JButton("Delete");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editBook(bookTable.getSelectedRow());
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBook(bookTable.getSelectedRow());
            }
        });

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadBooks() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                table.setRowCount(0);

                List<Book> books = bookController.getBooks();

                for (Book book : books) {
                    table.addRow(new Object[]{book.getId(), book.getAuthor(), book.getName(), book.isAvailable(), book.getReservedDays()});
                }

                table.fireTableDataChanged();
            }
        });
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

    private void addBook() {
        String author = JOptionPane.showInputDialog(this, "Autor: ");
        String name = JOptionPane.showInputDialog(this, "Nome: : ");
        String[] options = {"Sim", "Não"};
        int selectedOption = JOptionPane.showOptionDialog(this, "Está disponível?", "Escolha uma opção",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, true);
        boolean isAvailable = selectedOption == 0;

        String daysToReserve = JOptionPane.showInputDialog(this, "Máximo de dias para reservar: ");

        bookController.addBook(author, name, isAvailable, Integer.parseInt(daysToReserve));
    }

    private void editBook(int row){
        int bookId = (int) table.getValueAt(row, 0);

        String currentAuthor = (String) table.getValueAt(row, 1);
        String currentName = (String) table.getValueAt(row, 2);
        boolean currentIsAvailable = (Boolean) table.getValueAt(row, 3);
        int currentDaysToReserve = (int) table.getValueAt(row, 4);

        String newAuthor = JOptionPane.showInputDialog(this, "Autor: ", currentAuthor);
        String newName = JOptionPane.showInputDialog(this, "Nome: ", currentName);

        String[] options = {"Sim", "Não"};
        int selectedOption = JOptionPane.showOptionDialog(this, "Está disponível?", "Escolha uma opção",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, currentIsAvailable ? options[0] : options[1]);

        boolean newIsAvailable = selectedOption == 0;

        String newDaysToReserve = JOptionPane.showInputDialog(this, "Máximo de dias para reservar: ", currentDaysToReserve);

        bookController.updateBook(bookId, newAuthor, newName, newIsAvailable, Integer.parseInt(newDaysToReserve));
    }

    private void deleteBook(int row) {
        int bookId = (int) table.getValueAt(row, 0);

        bookController.deleteBook(bookId);
    }
}
