package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.Artist;

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
        frame.setSize(500, 400);
        frame.getContentPane().setBackground(new Color(30, 30, 30)); // Background color for the frame

        // Title Label
        JLabel titleLabel = new JLabel("Artist Details", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(new EmptyBorder(10, 0, 20, 0));

        // Main Panel with details
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10)); // 6 rows, 2 columns
        panel.setBackground(new Color(45, 45, 45));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Add components with custom styling
        addLabelPair(panel, "Artist ID:", artist.getId());
        addLabelPair(panel, "Artist Name:", artist.getArtistName());
        addLabelPair(panel, "Country:", artist.getCountry());
        addLabelPair(panel, "Score:", String.valueOf(artist.getScore()));
        addLabelPair(panel, "Type:", artist.getType());
        addLabelPair(panel, "Is Dead:", artist.isDead() ? "Yes" : "No");

        // Close Button
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.PLAIN, 16));
        closeButton.setBackground(new Color(220, 53, 69));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        closeButton.addActionListener(e -> frame.dispose());

        // Add components to the frame
        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(closeButton, BorderLayout.SOUTH);

        // Make the frame visible
        frame.setVisible(true);
    }

    // Utility method to add a label pair to the panel
    private void addLabelPair(JPanel panel, String labelText, String valueText) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.LIGHT_GRAY);

        JLabel value = new JLabel(valueText);
        value.setFont(new Font("Arial", Font.PLAIN, 16));
        value.setForeground(Color.WHITE);

        panel.add(label);
        panel.add(value);
    }

    public static void main(String[] args) {
        // Example usage
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
