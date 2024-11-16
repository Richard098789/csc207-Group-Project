package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import api.MusicBrainzAPI;
import entity.Artist;

public class EnhancedListing {
    private JFrame frame;
    private JPanel listingPanel;
    private JScrollPane scrollPane;
    private JButton loadMoreButton;
    private int offset = 0; // Start point for the API request
    private final int LIMIT = 10; // Number of results per request
    private boolean hasMore = true; // Flag to indicate if there are more results

    public EnhancedListing() {
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
        listingPanel.setBackground(Color.WHITE);

        // Scroll pane for the listing panel
        scrollPane = new JScrollPane(listingPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Smooth scrolling
        frame.add(scrollPane, BorderLayout.CENTER);

        // Load More Button
        loadMoreButton = new JButton("Load More");
        loadMoreButton.setFont(new Font("Arial", Font.PLAIN, 16));
        loadMoreButton.addActionListener(new LoadMoreListener());
        frame.add(loadMoreButton, BorderLayout.SOUTH);

        // Fetch and display the first set of results
        fetchAndDisplayListings();

        frame.setVisible(true);
    }

    private void fetchAndDisplayListings() {
        MusicBrainzAPI api = new MusicBrainzAPI();

        try {
            // Fetch paginated artist data
            Artist[] artists = api.getArtistsPaginated("Beatles", LIMIT, offset);

            if (artists.length == 0 && offset == 0) {
                JLabel noDataLabel = new JLabel("No artists found!");
                noDataLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                listingPanel.add(noDataLabel);
                hasMore = false;
                loadMoreButton.setEnabled(false);
            } else {
                // Add each artist to the listing panel
                for (Artist artist : artists) {
                    listingPanel.add(createArtistPanel(artist));
                }

                // Update the offset for the next page
                offset += LIMIT;

                // Refresh UI components
                listingPanel.revalidate();
                listingPanel.repaint();

                if (artists.length < LIMIT) {
                    hasMore = false; // No more results
                    loadMoreButton.setText("No More Results");
                    loadMoreButton.setEnabled(false);
                }
            }
        } catch (Exception e) {
            JLabel errorLabel = new JLabel("Error fetching artists: " + e.getMessage());
            errorLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            listingPanel.add(errorLabel);
        }
    }

    private JPanel createArtistPanel(Artist artist) {
        JPanel artistPanel = new JPanel();
        artistPanel.setLayout(new BorderLayout());
        artistPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        artistPanel.setMaximumSize(new Dimension(550, 100));
        artistPanel.setBackground(new Color(240, 248, 255));

        // Artist name label
        JLabel nameLabel = new JLabel("<html><b>Artist:</b> " + artist.getArtistName() + "</html>");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        artistPanel.add(nameLabel, BorderLayout.WEST);

        // Additional info label
        JLabel additionalInfoLabel = new JLabel("<html><b>Info:</b> " + (artist.getCountry() != null ? artist.getCountry() : "N/A") + "</html>");
        additionalInfoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        additionalInfoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        artistPanel.add(additionalInfoLabel, BorderLayout.CENTER);

        return artistPanel;
    }

    private class LoadMoreListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (hasMore) {
                fetchAndDisplayListings();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EnhancedListing::new);
    }
}
