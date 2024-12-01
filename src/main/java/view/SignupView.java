package view;

import app.AppCoordinator;
import interface_adapter.signup.SignupController;

import javax.swing.*;
import java.awt.*;

public class SignupView {
    private SignupController signupController;
    private JFrame frame;

    public SignupView() {
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

    public void setSignupController(SignupController signupController) {this.signupController = signupController;}

    public void goLoginView() {
        AppCoordinator appCoordinator = new AppCoordinator();
        appCoordinator.createLoginView();
    }

    public void signupSuccess() {
        JOptionPane.showMessageDialog(frame, "Account created successfully!");
        AppCoordinator appCoordinator = new AppCoordinator();
        appCoordinator.createLoginView();
    }

    public void signupFailure() {
        JOptionPane.showMessageDialog(frame, "User already exists.");
    }

    public void passwordUnmatched() {
        JOptionPane.showMessageDialog(frame, "Password is unmatched");
    }
}