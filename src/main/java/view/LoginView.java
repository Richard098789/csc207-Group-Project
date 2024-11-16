package view;

import Controller.LoginController;
import Use_case.UserManager;

import javax.swing.*;
import java.awt.*;

public class LoginView {
    public LoginView(UserManager userManager) {
        LoginController controller = new LoginController(userManager);
        createAndShowGUI(controller, userManager);
    }

    private void createAndShowGUI(LoginController controller, UserManager userManager) {
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

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (controller.login(username, password)) {
                JOptionPane.showMessageDialog(frame, "Login successful!");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        signupButton.addActionListener(e -> {
            frame.dispose();
            new SignupView(userManager); // Pass the instance of UserManager
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginView(new UserManager());
    }
}
