package features.presentation;

import features.datasource.UserListener;
import features.datasource.UserSubscriber;
import features.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserControlViewImpl extends JFrame implements UserControlView, UserListener {
    private DefaultTableModel table;
    private final UserControlController userControlController;

    public UserControlViewImpl(UserSubscriber userSubscriber, UserControlController userControlController) {
        setTitle("Login");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        userSubscriber.subscribe(this);
        this.userControlController = userControlController;
        userControlController.setView(this);

        InitializeUI();

        loadUsers();
    }

    private void InitializeUI() {
        setLayout(new BorderLayout());

        table = new DefaultTableModel(new Object[]{"ID", "USERNAME", "PASSWORD", "EH_ADMIN"}, 0);
        JTable userTable = new JTable(table);
        JScrollPane scrollPane = new JScrollPane(userTable);
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
                addUser();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editUser(userTable.getSelectedRow());
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUser(userTable.getSelectedRow());
            }
        });

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadUsers() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                table.setRowCount(0);

                List<User> users = userControlController.getUsers();

                for (User user : users) {
                    table.addRow(new Object[]{user.GetId(), user.GetUsername(), user.GetPassword(), user.is_admin});
                }

                table.fireTableDataChanged();
            }
        });
    }

    private void addUser() {
        String username = JOptionPane.showInputDialog(this, "Username: ");
        String password = JOptionPane.showInputDialog(this, "Password: ");
        String[] options = {"Sim", "Não"};
        int selectedOption = JOptionPane.showOptionDialog(this, "É admin?", "Escolha uma opção",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, true);
        boolean isAdmin = selectedOption == 0;

        userControlController.addUser(username, password, isAdmin);
    }

    private void editUser(int row) {
        int userId = (int) table.getValueAt(row, 0);

        String currentUsername = (String) table.getValueAt(row, 1);
        String currentPassword = (String) table.getValueAt(row, 2);
        boolean currentIsAdmin = (Boolean) table.getValueAt(row, 3);

        String newUsername = JOptionPane.showInputDialog(this, "Username: ", currentUsername);
        String newPassword = JOptionPane.showInputDialog(this, "Password: ", currentPassword);

        String[] options = {"Sim", "Não"};
        int selectedOption = JOptionPane.showOptionDialog(this, "É admin?", "Escolha uma opção",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, currentIsAdmin ? options[0] : options[1]);

        boolean newIsAdmin = selectedOption == 0;

        userControlController.updateUser(userId, newUsername, newPassword, newIsAdmin);
    }

    private void deleteUser(int row) {
        int userId = (int) table.getValueAt(row, 0);

        userControlController.deleteUser(userId);
    }

    @Override
    public void open() {
        setVisible(true);
    }

    @Override
    public void showErrorMessage(String msg) {

    }

    @Override
    public void updatedata() {
        loadUsers();
    }
}
