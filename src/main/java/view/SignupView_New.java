package view;

import interface_adapter.signup.SignupController;

import javax.swing.*;
import java.awt.*;

public class SignupView_New {
    private SignupController signupController;

    public void createAndShowGUI() {
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

        frame.setVisible(true);

        // Create Account Button ActionListener
        createAccountButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            signupController.execute(username, password, confirmPassword);

        });

        // Back Button ActionListener
        backButton.addActionListener(e -> {
            frame.dispose(); // Close the sign-up window
            signupController.switchToLoginView(); // Return to LoginView
        });

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            SignupView_New signupView = new SignupView_New();
            signupView.createAndShowGUI();
        });
    }

}