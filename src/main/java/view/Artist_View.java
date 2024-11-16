package view;

import entity.Artist;

import javax.swing.*;
import java.awt.*;

public class Artist_View {
    private final Artist artist;

    public Artist_View(Artist artist) {
        this.artist = artist;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        // Create the main frame
        JFrame frame = new JFrame("Artist Details");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create a panel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10)); // 6 rows, 2 columns, spacing 10px

        // Add labels and artist details
        panel.add(new JLabel("Artist ID:"));
        panel.add(new JLabel(artist.getId()));

        panel.add(new JLabel("Artist Name:"));
        panel.add(new JLabel(artist.getArtistName()));

        panel.add(new JLabel("Country:"));
        panel.add(new JLabel(artist.getCountry()));

        panel.add(new JLabel("Score:"));
        panel.add(new JLabel(String.valueOf(artist.getScore())));

        panel.add(new JLabel("Type:"));
        panel.add(new JLabel(artist.getType()));

        panel.add(new JLabel("Is Dead:"));
        panel.add(new JLabel(artist.isDead() ? "Yes" : "No"));

        // Add panel to the frame
        frame.add(panel, BorderLayout.CENTER);

        // Add a Close button at the bottom
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> frame.dispose());
        frame.add(closeButton, BorderLayout.SOUTH);

        // Make the frame visible
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Example usage with a sample artist
        Artist artist = new Artist(
                "001",
                "The Beatles",
                "UK",
                95,
                "Group",
                false
        );

        new Artist_View(artist);
    }
}
