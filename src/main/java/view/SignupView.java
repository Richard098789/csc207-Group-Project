package view;

import global_storage.CurrentUser;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SignupView {

    public SignupView() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Sign-Up Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        panel.add(new JLabel("Username:"));
        JTextField usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        JPasswordField passwordField = new JPasswordField();
        panel.add(passwordField);

        panel.add(new JLabel("Confirm Password:"));
        JPasswordField confirmPasswordField = new JPasswordField();
        panel.add(confirmPasswordField);

        JButton createAccountButton = new JButton("Create Account");
        JButton backButton = new JButton("Back to Login");
        panel.add(createAccountButton);
        panel.add(backButton);

        frame.add(panel, BorderLayout.CENTER);

        // Create Account Button ActionListener
        createAccountButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Username and password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(frame, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if username already exists
            if (userExists(username)) {
                JOptionPane.showMessageDialog(frame, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Add user to Firestore
            if (addUser(username, password)) {
                JOptionPane.showMessageDialog(frame, "Account created successfully!");
                frame.dispose();
                new LoginView();
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to create account. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Back Button ActionListener
        backButton.addActionListener(e -> {
            frame.dispose();
            new LoginView();
        });

        frame.setVisible(true);
    }

    private boolean userExists(String username) {
        try {
            return CurrentUser.db.collection("Users")
                    .document(username)
                    .get()
                    .get()
                    .exists();
        } catch (Exception e) {
            System.err.println("Error checking user existence: " + e.getMessage());
            return false;
        }
    }

    private boolean addUser(String username, String password) {
        try {
            Map<String, String> userData = new HashMap<>();
            userData.put("username", username);
            userData.put("password", password);

            CurrentUser.db.collection("Users")
                    .document(username)
                    .set(userData)
                    .get();

            return true;
        } catch (Exception e) {
            System.err.println("Error adding user: " + e.getMessage());
            return false;
        }
    }
}
