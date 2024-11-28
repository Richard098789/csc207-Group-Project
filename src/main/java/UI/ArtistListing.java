package UI;

import api.API_v2;
import entity.Artist;
import entity.ArtistDetailModel;
import Controller.ArtistDetailController;
import view.ArtistDetailView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArtistListing {
    private JFrame frame;
    private JPanel listingPanel;
    private JScrollPane scrollPane;
    private JButton loadMoreButton;
    private JTextField searchField;
    private JTextField countryField;
    private JComboBox<String> typeDropdown;
    private JButton searchButton;
    private int offset = 0; // Start point for pagination
    private final int LIMIT = 10; // Number of results per request
    private boolean hasMore = true; // Flag to indicate if there are more results
    private String searchArtist = ""; // Search filter for artist name
    private String searchCountry = ""; // Search filter for country
    private String searchType = ""; // Search filter for type

    public ArtistListing() {
        frame = new JFrame("Music Listings");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 900);
        frame.setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Music Listings", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Search bar panel
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(15);
        countryField = new JTextField(10);

        // Dropdown for type
        String[] types = {"Any", "Group", "Person", "Other"};
        typeDropdown = new JComboBox<>(types);

        searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchListener());

        searchPanel.add(new JLabel("Artist:"));
        searchPanel.add(searchField);
        searchPanel.add(new JLabel("Country:"));
        searchPanel.add(countryField);
        searchPanel.add(new JLabel("Type:"));
        searchPanel.add(typeDropdown); // Add dropdown to panel
        searchPanel.add(searchButton);
        frame.add(searchPanel, BorderLayout.NORTH);

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
        API_v2 api = new API_v2();

        try {
            // Fetch paginated artist data using correct parameters
            Artist[] artists = api.getArtists(searchArtist, searchCountry, LIMIT, offset);

            if (artists.length == 0 && offset == 0) {
                JLabel noDataLabel = new JLabel("No artists found!");
                noDataLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                listingPanel.add(noDataLabel);
                hasMore = false;
                loadMoreButton.setEnabled(false);
            } else {
                // Display paginated results
                for (Artist artist : artists) {
                    // Filter by type if specified
                    if (searchType.equals("Any") || artist.getType().equalsIgnoreCase(searchType)) {
                        listingPanel.add(createArtistPanel(artist));
                    }
                }

                offset += LIMIT;

                // Refresh UI components
                listingPanel.revalidate();
                listingPanel.repaint();

                if (artists.length < LIMIT) {
                    hasMore = false;
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
        artistPanel.setMaximumSize(new Dimension(750, 120));
        artistPanel.setBackground(new Color(240, 248, 255)); // Light blue background for the artist panel

        // Artist name label
        JLabel nameLabel = new JLabel("<html><b>Artist:</b> " + artist.getArtistName() + "</html>");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        artistPanel.add(nameLabel, BorderLayout.NORTH);

        // Additional info label
        JLabel additionalInfoLabel = new JLabel("<html><b>Type:</b> " + artist.getType() +
                " | <b>Country:</b> " + artist.getCountry() +
                " | <b>Score:</b> " + artist.getScore() + "</html>");
        additionalInfoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        additionalInfoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        artistPanel.add(additionalInfoLabel, BorderLayout.CENTER);

        // Add MouseListener to detect clicks
        artistPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ArtistDetailModel model = new ArtistDetailModel(artist);
                ArtistDetailController controller = new ArtistDetailController(model);
                new ArtistDetailView(model, controller);
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                artistPanel.setBackground(new Color(200, 220, 240)); // Slightly darker blue
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                artistPanel.setBackground(new Color(240, 248, 255)); // Reset to the original color
            }
        });

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

    private class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get search criteria
            searchArtist = searchField.getText().trim();
            searchCountry = countryField.getText().trim();
            searchType = (String) typeDropdown.getSelectedItem();

            // Reset state for new search
            offset = 0;
            hasMore = true;
            listingPanel.removeAll();
            loadMoreButton.setText("Load More");
            loadMoreButton.setEnabled(true);

            // Fetch and display results
            fetchAndDisplayListings();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ArtistListing::new);
    }
}
