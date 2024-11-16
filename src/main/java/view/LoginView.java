package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView {
    public LoginView() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        // Create the main frame
        JFrame frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create a panel for the form
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10)); // 3 rows, 2 columns

        // Add components to the panel
        panel.add(new JLabel("Username:"));
        JTextField usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        JPasswordField passwordField = new JPasswordField();
        panel.add(passwordField);

        // Buttons
        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Sign Up");
        panel.add(loginButton);
        panel.add(signupButton);

        // Add panel to the frame
        frame.add(panel, BorderLayout.CENTER);

        // Button Actions
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Add logic for validating login here
                JOptionPane.showMessageDialog(frame, "Login attempted with Username: " + username);
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the login window
                new SignupView(); // Open the sign-up window
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginView();
    }
}
