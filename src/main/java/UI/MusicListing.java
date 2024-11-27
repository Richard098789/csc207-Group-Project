package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicListing {

    private JFrame frame;

    public MusicListing() {
        frame = new JFrame("Music Listing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Select Categories", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton artistButton = new JButton("Artist Listings");
        JButton eventButton = new JButton("Event Listings");
        JButton mainMenuButton = new JButton("Main Menu");

        buttonPanel.add(artistButton);
        buttonPanel.add(eventButton);
        buttonPanel.add(mainMenuButton);

        frame.add(buttonPanel, BorderLayout.CENTER);

        // Listings button handler
        artistButton.addActionListener(e -> new EnhancedListing());
        eventButton.addActionListener(e -> new EventListing());
        mainMenuButton.addActionListener(e -> {
            // Close the current frame
            frame.dispose();

            // Open the main menu page
            new MainProgram();

        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MusicListing::new);
    }

}
