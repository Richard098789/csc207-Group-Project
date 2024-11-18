package view;

import Use_case.UserManager;
import javax.swing.*;
import java.awt.*;
import UI.EnhancedListing; // Assuming EnhancedListing is your artist listing page class

public class MainMenuView {
    private final UserManager userManager;

    public MainMenuView(UserManager userManager) {
        this.userManager = userManager; // Shared UserManager instance
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton listingsButton = new JButton("View Artist Listings");
        JButton accountButton = new JButton("My Account");
        JButton logoutButton = new JButton("Log Out");

        panel.add(listingsButton);
        panel.add(accountButton);
        panel.add(logoutButton);

        frame.add(panel, BorderLayout.CENTER);

        // ActionListener for "View Artist Listings" button
        listingsButton.addActionListener(e -> {
            frame.dispose(); // Close main menu window
            new EnhancedListing(); // Navigate to the artist listing page
        });

        // ActionListener for "My Account" button
        accountButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Account management is under construction!");
        });

        // ActionListener for "Log Out" button
        logoutButton.addActionListener(e -> {
            frame.dispose(); // Close main menu window
            new LoginView(userManager); // Navigate back to login page
        });

        frame.setVisible(true);
    }
}
