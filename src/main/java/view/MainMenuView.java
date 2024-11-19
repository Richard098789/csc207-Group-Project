package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Use_case.UserManager;
import UI.EnhancedListing; // Import the music listing UI

public class MainMenuView {
    private final UserManager userManager;

    public MainMenuView(UserManager userManager) {
        this.userManager = userManager;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton musicListingsButton = new JButton("Music Listings");
        JButton accountButton = new JButton("My Account");
        JButton logoutButton = new JButton("Log Out");

        panel.add(musicListingsButton);
        panel.add(accountButton);
        panel.add(logoutButton);

        frame.add(panel, BorderLayout.CENTER);

        // Music Listings button action
        musicListingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the main menu window
                new EnhancedListing(); // Open the music listing window
            }
        });

        // Log Out button action
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the main menu
                new LoginView(userManager); // Go back to the login view
            }
        });

        frame.setVisible(true);
    }
}
