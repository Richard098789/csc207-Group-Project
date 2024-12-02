package view;

import app.AppCoordinator;

import javax.swing.*;
import java.awt.*;

public class MainMenuView {

    public MainMenuView() {
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
        AppCoordinator appCoordinator = AppCoordinator.getInstance();
        // Music Listings button action
        musicListingsButton.addActionListener(e -> {
            frame.dispose(); // Close the main menu window
            appCoordinator.createSearchSelectionView();
        });

        // Log Out button action
        logoutButton.addActionListener(e -> {
            frame.dispose(); // Close the main menu
            appCoordinator.createLoginView(); // Go back to the login view
        });

        accountButton.addActionListener(e -> appCoordinator.createUserAccountView());

        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new MainMenuView();
    }
}
