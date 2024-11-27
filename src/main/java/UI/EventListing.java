package UI;

import api.MusicBrainzAPI;
import entity.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventListing {
    private JFrame frame;
    private JPanel listingPanel;
    private JScrollPane scrollPane;
    private JButton loadMoreButton;
    private JTextField searchField;
    private JTextField locationField;
    private JComboBox<String> typeDropdown;
    private JButton searchButton;
    private int offset = 0; // Start point for pagination
    private final int LIMIT = 10; // Number of result displayed per request
    private boolean hasMore = true; // Flag to indicate if there is more results
    private String searchEvent = ""; // Search filter for event names
    private String searchLocation = ""; // Search filter for location names
    private String searchType = ""; // Search filter for types of event


    public EventListing() {
        frame = new JFrame("Event Listing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 900);
        frame.setLayout(new BorderLayout());

        // Title Label
        JLabel titleLabel = new JLabel("Event Listing", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Implement Search bar Panel
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(15);
        locationField = new JTextField(10);

        // Drop down menu for type
        String[] types = {"Concert", "Festival", "Stage performance", "Award ceremony", "Launch event", "Convention/Expo", "Masterclass/Clinic"};
        typeDropdown = new JComboBox<>(types);

        searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchListener());

        searchPanel.add(new JLabel("Event:"));
        searchPanel.add(searchField);
        searchPanel.add(new JLabel("Location:"));
        searchPanel.add(locationField);
        searchPanel.add(new JLabel("Type:"));
        searchPanel.add(typeDropdown);
        searchPanel.add(searchButton);

        frame.add(searchPanel, BorderLayout.NORTH);

        // Main panels to hold all listings
        listingPanel = new JPanel();
        listingPanel.setLayout(new BoxLayout(listingPanel, BoxLayout.Y_AXIS));
        listingPanel.setBackground(Color.WHITE);

        // Scroll pane for the listing panel
        scrollPane = new JScrollPane(listingPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Load more button
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
            // Fetch paginated events data using input parameters
            Event[] events = api.getEvents(searchEvent, searchType, searchLocation);

            if (events.length == 0 && offset == 0) {
                JLabel noDataLabel = new JLabel("No events found!");
                noDataLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                listingPanel.add(noDataLabel);
                hasMore = false;
                loadMoreButton.setEnabled(false);
            } else {
                // Display paginated results
                for (Event event : events) {
                    // Filter by type if specified
                    if (searchType.equals("Any") || event.getType().equalsIgnoreCase(searchType)) {
                        listingPanel.add(createEventPanel(event));
                    }
                }

                offset += LIMIT;

                // Refresh UI components
                listingPanel.revalidate();
                listingPanel.repaint();

                if (events.length < LIMIT) {
                    hasMore = false;
                    loadMoreButton.setText("No More Results");
                    loadMoreButton.setEnabled(false);
                }
            }
        } catch (Exception e) {

            JLabel errorLabel = new JLabel("Error fetching events: " + e.getMessage());
            errorLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            listingPanel.add(errorLabel);
        }
    }

    private JPanel createEventPanel(Event event) {
        JPanel eventPanel = new JPanel();
        eventPanel.setLayout(new BorderLayout());
        eventPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        eventPanel.setMaximumSize(new Dimension(750, 120));
        eventPanel.setBackground(new Color(240, 248, 255)); // Light blue background for the event panel

        // Event name label
        JLabel nameLabel = new JLabel("<html><b>Event:</b> " + event.getName() + "</html>");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        eventPanel.add(nameLabel, BorderLayout.NORTH);

        // Additional info label
        JLabel additionalInfoLabel = new JLabel("<html><b>Type:</b> " + event.getType() +
                " | <b>Artist:</b> " + event.getArtistName() +
                " | <b>Location:</b> " + event.getPlaceName() +
                " | <b>Begin Date:</b> " + event.getBeginDate() +
                " | <b>End Date:</b> " + event.getEndDate() +
                " | <b>Score:</b> " + event.getScore() + "</html>");
        additionalInfoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        additionalInfoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        eventPanel.add(additionalInfoLabel, BorderLayout.CENTER);

        // Add MouseListener to detect clicks
        eventPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Navigate to EventDetailView when clicked
                new view.EventDetailView(event); // Pass the event object to the detail view
                //frame.dispose(); // Close the current window (optional)
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Highlight the panel when hovered
                eventPanel.setBackground(new Color(200, 220, 240)); // Slightly darker blue
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Reset the background color when mouse exits
                eventPanel.setBackground(new Color(240, 248, 255)); // Reset to the original color
            }
        });

        return eventPanel;
    }

    private class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get search criteria
            searchEvent = searchField.getText().trim();
            searchLocation = locationField.getText().trim();
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

    private class LoadMoreListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (hasMore) {
                fetchAndDisplayListings();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EventListing::new);
    }

}