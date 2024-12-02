package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import UI.EventListing;
import Use_case.artist_search.ArtistSearchInteractor;
import data_access.MusicBrainzArtistRepository;

public class SearchSelection {

    private JFrame frame;

    public SearchSelection() {
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

        // Listings button handlers
        artistButton.addActionListener(e -> new ArtistListingView()); // Pass the required arguments
        eventButton.addActionListener(e -> new EventListing()); // Assuming EventListing requires no arguments for now
        mainMenuButton.addActionListener(e -> {
            // Close the current frame
            frame.dispose();

            // Open the main menu page

            new MainMenuView();

        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SearchSelection::new);
    }

}
