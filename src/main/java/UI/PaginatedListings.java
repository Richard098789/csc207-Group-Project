package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import api.MusicBrainzAPI;
import entity.Artist;

public class PaginatedListings {
    private JFrame frame;
    private JPanel listingPanel;
    private JButton loadMoreButton;
    private int offset = 0; // Start point for the API request
    private final int LIMIT = 10; // Number of results per request

    public PaginatedListings() {
        frame = new JFrame("Music Listings");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 800);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Music Listings", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Main panel to hold all listings
        listingPanel = new JPanel();
        listingPanel.setLayout(new BoxLayout(listingPanel, BoxLayout.Y_AXIS));
        listingPanel.setBackground(Color.WHITE); // Set background for a clean UI

        // Scroll pane for the listing panel
        JScrollPane scrollPane = new JScrollPane(listingPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Smooth scrolling
        frame.add(scrollPane, BorderLayout.CENTER);

        // "Load More" button
        loadMoreButton = new JButton("Load More");
        loadMoreButton.setFont(new Font("Arial", Font.BOLD, 16));
        loadMoreButton.setBackground(new Color(173, 216, 230)); // Light blue color
        loadMoreButton.setForeground(Color.BLACK);
        loadMoreButton.addActionListener(new LoadMoreListener());
        frame.add(loadMoreButton, BorderLayout.SOUTH);

        // Fetch and display the first set of music listings
        fetchAndDisplayListings();

        frame.setVisible(true);
    }

    private void fetchAndDisplayListings() {
        MusicBrainzAPI api = new MusicBrainzAPI();

        try {
            // Fetch artist data with pagination
            Artist[] artists = api.getArtistsPaginated("Beatles", LIMIT, offset);

            if (artists.length == 0 && offset == 0) {
                JLabel noDataLabel = new JLabel("No artists found!");
                noDataLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                listingPanel.add(noDataLabel);
            } else {
                // Display each artist in a custom panel
                for (Artist artist : artists) {
                    JPanel artistPanel = createArtistPanel(artist);
                    listingPanel.add(artistPanel);
                }
                offset += LIMIT; // Update the offset for the next batch
            }

            listingPanel.revalidate();
            listingPanel.repaint();

            // If no more results are available, disable the "Load More" button
            if (artists.length < LIMIT) {
                loadMoreButton.setEnabled(false);
                loadMoreButton.setText("No More Results");
            }
        } catch (Exception e) {
            JLabel errorLabel = new JLabel("Error fetching artists: " + e.getMessage());
            errorLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            listingPanel.add(errorLabel);
        }
    }

    private JPanel createArtistPanel(Artist artist) {
        JPanel artistPanel = new JPanel();
        artistPanel.setLayout(new BorderLayout());
        artistPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        artistPanel.setMaximumSize(new Dimension(550, 100)); // Set a fixed size for consistent layout
        artistPanel.setBackground(new Color(240, 248, 255)); // Light blue background for the artist panel

        // Artist name label
        JLabel nameLabel = new JLabel("<html><b>Artist:</b> " + artist.getArtistName() + "</html>");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        artistPanel.add(nameLabel, BorderLayout.WEST);

        // Country label
        JLabel countryLabel = new JLabel("<html><b>Country:</b> " + artist.getCountry() + "</html>");
        countryLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        countryLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        artistPanel.add(countryLabel, BorderLayout.CENTER);

        // Rate label (placeholder)
        JLabel rateLabel = new JLabel("<html><b>Rate:</b> X/10</html>", JLabel.RIGHT);
        rateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        rateLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        artistPanel.add(rateLabel, BorderLayout.EAST);

        return artistPanel;
    }

    // Listener for the "Load More" button
    private class LoadMoreListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            fetchAndDisplayListings();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PaginatedListings::new);
    }
}
