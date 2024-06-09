package features.presentation;

import di.ServiceLocator;
import features.datasource.BookSubscriber;
import features.datasource.UserDAO;
import features.datasource.UserListener;
import features.datasource.UserSubscriber;
import features.model.User;
import infrastructure.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserViewImpl extends JFrame implements UserView, UserListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private final UserController userController;

    public UserViewImpl(UserSubscriber userSubscriber, UserController userController) {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        userSubscriber.subscribe(this);
        this.userController = userController;
        userController.setView(this);

        InitializeUI();
    }

    private void InitializeUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JLabel usernameLabel = new JLabel("Username:");
        panel.add(usernameLabel);

        usernameField = new JTextField();
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        panel.add(passwordField);

        loginButton = new JButton("Login");
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                User user = getUser(username, password);

                if (user == null)
                    showErrorMessage("Credenciais inválidas");
                else {
                    if (user.is_admin == 1) {
                        closeWindow();
                        SwingUtilities.invokeLater(() -> {
                            new AdminViewImpl().setVisible(true);
                        });
                    }
                    else
                        showErrorMessage("Usuário normal");
                }
            }
        });

        add(panel);
    }

    private User getUser(String username, String password){
        User user = null;
        try {
            user = DatabaseManager.getSessionFactory().fromTransaction(session -> {
                String hql = "FROM User WHERE username = :username AND password = :password";
                return session.createQuery(hql, User.class)
                        .setParameter("username", username)
                        .setParameter("password", password)
                        .uniqueResult();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void updatedata() {

    }

    @Override
    public void open() {
        setVisible(true);
    }

    @Override
    public void showErrorMessage(String msg) {

    }

    private void closeWindow(){
        this.dispose();
    }
}
