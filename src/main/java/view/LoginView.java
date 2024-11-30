package view;

import UI.ArtistListing;
import Use_case.UserManager;
import Controller.LoginController;

import javax.swing.*;
import java.awt.*;

public class LoginView {
    private LoginController loginController;

    public LoginView() {
        userManager.loadUsersFromDB(); // Load all users
        LoginController controller = new LoginController(userManager);
        createAndShowGUI(controller);
    }

    private void createAndShowGUI(LoginController controller) {
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
            String password = new String(passwordField.getPassword()).trim();
            loginController.login(username, password);
        });

        // Sign-Up Button ActionListener
        signupButton.addActionListener(e -> {
            frame.dispose(); // Close login window
            new SignupView(); // Pass shared UserManager instance to SignupView
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        UserManager userManager = new UserManager(); // Shared instance
        new LoginView();
    }
}
