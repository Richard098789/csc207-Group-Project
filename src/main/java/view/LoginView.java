package view;


import Use_case.UserManager;
import Use_case.login.LoginInputBoundary;
import Use_case.login.LoginInteractor;
import Use_case.login.LoginOutputBoundary;
import app.AppCoordinator;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;



import javax.swing.*;
import java.awt.*;

public class LoginView {

    private LoginController loginController;
    private JFrame frame;

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

            String password = new String(passwordField.getPassword()).trim();
            loginController.execute(username, password);

        });

        // Sign Up Button ActionListener
        signupButton.addActionListener(e -> {

            loginController.goSignupView();
            frame.dispose(); // Close login window

        });

        frame.setVisible(true);
    }


    public void toSignup() {
        AppCoordinator coordinator = new AppCoordinator();
        coordinator.createSignUpView();
    }


    public void loginFailureView() {
        JOptionPane.showMessageDialog(frame, "Invalid username or password!",
                "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void toMainMenuView() {
        JOptionPane.showMessageDialog(frame, "Login successful!");
        AppCoordinator coordinator = new AppCoordinator();
        coordinator.createMainMenuView();

    }
    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;}


}
