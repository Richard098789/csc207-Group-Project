package view;

import Use_case.UserManager;

import javax.swing.*;
import java.awt.*;

public class MainMenuView {
    private final UserManager userManager;

    public MainMenuView(UserManager userManager) {
        this.userManager = userManager; // Pass shared UserManager instance
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JLabel titleLabel = new JLabel("Our Program", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10)); // 3 rows, 1 column

        JButton musicListingsButton = new JButton("Music Listings");
        JButton myAccountButton = new JButton("My Account");
        JButton logoutButton = new JButton("Log Out");

        buttonPanel.add(musicListingsButton);
        buttonPanel.add(myAccountButton);
        buttonPanel.add(logoutButton);

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50)); // Add padding
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Action Listeners for buttons
        musicListingsButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Music Listings clicked!"));
        myAccountButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "My Account clicked!"));

        // Logout Button
        logoutButton.addActionListener(e -> {
            frame.dispose(); // Close the Main Menu
            new LoginView(userManager); // Pass the shared UserManager instance back to LoginView
        });

        frame.setVisible(true);
    }
}
