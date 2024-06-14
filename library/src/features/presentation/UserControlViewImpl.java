package features.presentation;

import features.datasource.UserListener;
import features.datasource.UserSubscriber;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserControlViewImpl extends JFrame implements UserControlView, UserListener {
    private DefaultTableModel table;
    private final UserControlController userControlController;

    public UserControlViewImpl(UserSubscriber userSubscriber, UserControlController userControlController) {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        userSubscriber.subscribe(this);
        this.userControlController = userControlController;
        userControlController.setView(this);

        InitializeUI();
    }

    private void InitializeUI() {
        setLayout(new BorderLayout());

        table = new DefaultTableModel(new Object[]{"ID", "USERNAME", "EH_ADMIN"}, 0);
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

            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        add(buttonPanel, BorderLayout.SOUTH);
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

    }
}
