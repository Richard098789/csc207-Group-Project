import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField reEnterPasswordField;

    public SignUpPage() {
        setTitle("Sign Up Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Username Label and Field
        add(new JLabel("Username:", SwingConstants.RIGHT));
        usernameField = new JTextField(20);
        add(usernameField);

        // Password Label and Field
        add(new JLabel("Password:", SwingConstants.RIGHT));
        passwordField = new JPasswordField(20);
        add(passwordField);

        // Re-enter Password Label and Field
        add(new JLabel("Re-enter Password:", SwingConstants.RIGHT));
        reEnterPasswordField = new JPasswordField(20);
        add(reEnterPasswordField);

        // Sign Up Button
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement sign-up logic here
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String reEnterPassword = new String(reEnterPasswordField.getPassword());

                if (password.equals(reEnterPassword)) {
                    System.out.println("Signing up with Username: " + username);
                    // Proceed with sign-up logic
                } else {
                    JOptionPane.showMessageDialog(SignUpPage.this,
                            "Passwords do not match!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(signUpButton);

        // Cancel Button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> System.exit(0));
        add(cancelButton);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginPage().setVisible(true); // Go back to the Login Page
                dispose(); // Close the sign-up window
            }
        });
        add(backButton);
    }
}
