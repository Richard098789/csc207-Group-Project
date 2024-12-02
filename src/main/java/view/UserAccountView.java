package view;

import app.AppCoordinator;
import global_storage.CurrentUser;

import javax.swing.*;
import java.awt.*;

public class UserAccountView {
    public UserAccountView() {
        show();}
    private void show() {
        JFrame frame = new JFrame("User Account");
        frame.setSize(400, 300);
        JLabel titleLabel = new JLabel("Username:"+ CurrentUser.username, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        JButton backMenu = new JButton("Back");
        JPanel panel = new JPanel();
        panel.add(backMenu);
        frame.add(panel, BorderLayout.SOUTH);

        backMenu.addActionListener(e -> {
            AppCoordinator appCoordinator = AppCoordinator.getInstance();
            appCoordinator.createMainMenuView();
        });
        frame.setVisible(true);
    }

}
