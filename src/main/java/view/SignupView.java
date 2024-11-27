package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Use_case.UserManager;

public class SignupView {
    private final UserManager userManager;

    public SignupView(UserManager userManager) {
        this.userManager = userManager;
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
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(frame, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (userManager.userExists(username)) {
                JOptionPane.showMessageDialog(frame, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (userManager.addUser(username, password)) {
                    JOptionPane.showMessageDialog(frame, "Account created successfully!");
                } else {JOptionPane.showMessageDialog(frame, "Account already exists!");}
                frame.dispose(); // Close the sign-up window
                new LoginView(userManager); // Return to LoginView
            }
        });

        // Back Button ActionListener
        backButton.addActionListener(e -> {
            frame.dispose(); // Close the sign-up window
            new LoginView(userManager); // Return to LoginView
        });

        frame.setVisible(true);
    }
}
