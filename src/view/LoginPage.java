import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPage() {
        setTitle("Log In Page");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        // Username Label and Field
        add(new JLabel("Username:", SwingConstants.RIGHT));
        usernameField = new JTextField(20);
        add(usernameField);

        // Password Label and Field
        add(new JLabel("Password:", SwingConstants.RIGHT));
        passwordField = new JPasswordField(20);
        add(passwordField);

        // Log In Button
        JButton loginButton = new JButton("Log In");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement login logic here
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                System.out.println("Logging in with Username: " + username + " and Password: " + password);
            }
        });
        add(loginButton);

        // Cancel Button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> System.exit(0));
        add(cancelButton);

        // Sign Up Button
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignUpPage().setVisible(true); // Open the Sign-Up Page
                dispose(); // Close the current login window
            }
        });
        add(signUpButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginPage loginPage = new LoginPage();
            loginPage.setVisible(true);
        });
    }
}
