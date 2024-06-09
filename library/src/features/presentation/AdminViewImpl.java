package features.presentation;

import di.ServiceLocator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminViewImpl extends JFrame {
    public AdminViewImpl() {
        setTitle("Biblioteca");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        JButton userButton = new JButton("Gerenciar Usuários");
        JButton bookButton = new JButton("Gerenciar Livros");

        panel.add(userButton);
        panel.add(bookButton);

        add(panel);

        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        ServiceLocator.getInstance().getBookView().open();
                    }
                });
            }
        });
    }
}
