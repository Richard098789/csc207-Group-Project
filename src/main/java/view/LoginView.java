package view;

import global_storage.CurrentUser;
import javax.swing.*;
import java.awt.*;

public class LoginView {

    public LoginView() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        panel.add(new JLabel("Username:"));
        JTextField usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        JPasswordField passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Sign Up");
        panel.add(loginButton);
        panel.add(signupButton);

        frame.add(panel, BorderLayout.CENTER);

        // Login Button ActionListener
        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (authenticateUser(username, password)) {
                JOptionPane.showMessageDialog(frame, "Login successful!");
                CurrentUser.setCurrentUser(username);
                frame.dispose();
                new MainMenuView();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Sign Up Button ActionListener
        signupButton.addActionListener(e -> {
            frame.dispose();
            new SignupView();
        });

        frame.setVisible(true);
    }

    private boolean authenticateUser(String username, String password) {
        try {
            var userDoc = CurrentUser.db.collection("Users")
                    .document(username)
                    .get()
                    .get();

            return userDoc.exists() && password.equals(userDoc.getString("password"));
        } catch (Exception e) {
            System.err.println("Error during authentication: " + e.getMessage());
            return false;
        }
    }
}
