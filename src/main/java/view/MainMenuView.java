package view;

import app.AppCoordinator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        // Music Listings button action
        musicListingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the main menu window
                AppCoordinator appCoordinator = AppCoordinator.getInstance();
                appCoordinator.createSearchSelectionView();
            }
        });

        // Log Out button action
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the main menu
                new LoginView(); // Go back to the login view
            }
        });

        frame.setVisible(true);
    }
}
