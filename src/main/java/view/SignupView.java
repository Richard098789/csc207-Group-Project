package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupView {
    public SignupView() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        // Create the main frame
        JFrame frame = new JFrame("Sign-Up Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create a panel for the form
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10)); // 4 rows, 2 columns

        // Add components to the panel
        panel.add(new JLabel("Username:"));
        JTextField usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        JPasswordField passwordField = new JPasswordField();
        panel.add(passwordField);

        panel.add(new JLabel("Confirm Password:"));
        JPasswordField confirmPasswordField = new JPasswordField();
        panel.add(confirmPasswordField);

        // Buttons
        JButton createAccountButton = new JButton("Create Account");
        JButton backButton = new JButton("Back to Login");
        panel.add(createAccountButton);
        panel.add(backButton);

        // Add panel to the frame
        frame.add(panel, BorderLayout.CENTER);

        // Button Actions
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(frame, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Add logic for account creation here
                    JOptionPane.showMessageDialog(frame, "Account created for Username: " + username);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the sign-up window
                new LoginView(); // Open the login window
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SignupView();
    }
}
