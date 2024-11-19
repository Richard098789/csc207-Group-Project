package view;

import javax.swing.*;
import java.awt.*;

import UI.EnhancedListing;
import entity.Artist;

public class ArtistDetailView {
    private JFrame frame;

    public ArtistDetailView(Artist artist) {
        frame = new JFrame("Artist Details");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("Artist Details", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Artist Details Panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        detailsPanel.add(new JLabel("<html><b>Name:</b> " + artist.getArtistName() + "</html>"));
        detailsPanel.add(new JLabel("<html><b>Type:</b> " + artist.getType() + "</html>"));
        detailsPanel.add(new JLabel("<html><b>Country:</b> " + artist.getCountry() + "</html>"));
        detailsPanel.add(new JLabel("<html><b>Score:</b> " + artist.getScore() + "</html>"));

        frame.add(detailsPanel, BorderLayout.CENTER);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.addActionListener(e -> {
            frame.dispose(); // Close this window
            new EnhancedListing(); // Reopen the main listing page
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
