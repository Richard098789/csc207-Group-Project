package view;

import data_transfer_object.Event;
import interface_adapter.event_search.EventSearchController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class EventListingView {
    private JFrame frame;
    private JPanel listingPanel;
    private JScrollPane scrollPane;
    private JButton loadMoreButton;
    private JTextField searchField;
    private JTextField locationField;
    private JButton searchButton;
    private int offset = 0; // Start point for pagination
    private final int LIMIT = 10; // Number of results per request
    private boolean hasMore = true; // Flag to indicate if there are more results
    private String searchEvent = ""; // Search filter for event names
    private String searchLocation = ""; // Search filter for location names
    private EventSearchController eventSearchController;

    public EventListingView() {
        frame = new JFrame("Event Listing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 900);
        frame.setLayout(new BorderLayout());

        // Title Label
        JLabel titleLabel = new JLabel("Event Listing", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Search Panel
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(15);
        locationField = new JTextField(10);

        searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchListener());

        searchPanel.add(new JLabel("Event:"));
        searchPanel.add(searchField);
        searchPanel.add(new JLabel("Location:"));
        searchPanel.add(locationField);
        searchPanel.add(searchButton);

        frame.add(searchPanel, BorderLayout.NORTH);

        // Main Panel for Listings
        listingPanel = new JPanel();
        listingPanel.setLayout(new BoxLayout(listingPanel, BoxLayout.Y_AXIS));
        listingPanel.setBackground(Color.WHITE);

        // Scroll Pane
        scrollPane = new JScrollPane(listingPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Load More Button
        loadMoreButton = new JButton("Load More");
        loadMoreButton.setFont(new Font("Arial", Font.PLAIN, 16));
        loadMoreButton.addActionListener(new LoadMoreListener());
        frame.add(loadMoreButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void setEventSearchController(EventSearchController eventSearchController) {
        this.eventSearchController = eventSearchController;
    }

    public void presentResults(Event[] events) {
        if (events.length == 0 && offset == 0) {
            JLabel noDataLabel = new JLabel("No events found!");
            noDataLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            listingPanel.add(noDataLabel);
            hasMore = false;
            loadMoreButton.setEnabled(false);
        } else {
            // Display events
            Arrays.stream(events).forEach(event -> {
                listingPanel.add(createEventPanel(event));
            });

            offset += LIMIT;

            listingPanel.revalidate();
            listingPanel.repaint();

            if (events.length < LIMIT) {
                hasMore = false;
                loadMoreButton.setText("No More Results");
                loadMoreButton.setEnabled(false);
            }
        }
    }

    private JPanel createEventPanel(Event event) {
        JPanel eventPanel = new JPanel();
        eventPanel.setLayout(new BoxLayout(eventPanel, BoxLayout.Y_AXIS));
        eventPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        eventPanel.setMaximumSize(new Dimension(750, 120));
        eventPanel.setBackground(new Color(240, 248, 255)); // Light blue background

        // Event Name
        JLabel nameLabel = new JLabel("<html><b>Event:</b> " + event.getName() + "</html>");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        eventPanel.add(nameLabel);

        // Additional Info
        JLabel additionalInfoLabel = new JLabel("<html><b>Type:</b> " + event.getType() +
                " | <b>Artist:</b> " + event.getArtistName() +
                " | <b>Location:</b> " + event.getPlaceName() + "</html>");
        additionalInfoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        additionalInfoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        eventPanel.add(additionalInfoLabel);

        JLabel additionalInfoLabel1 = new JLabel("<html><b>Begin Date:</b> " + event.getBeginDate() +
                " | <b>End Date:</b> " + event.getEndDate() +
                " | <b>Score:</b> " + event.getScore() + "</html>");
        additionalInfoLabel1.setFont(new Font("Arial", Font.PLAIN, 16));
        additionalInfoLabel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        eventPanel.add(additionalInfoLabel1);

        eventPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Mouse Listener for Clicks
        eventPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Handle event click, e.g., open event detail view
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                eventPanel.setBackground(new Color(200, 220, 240));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                eventPanel.setBackground(new Color(240, 248, 255));
            }
        });

        return eventPanel;
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
            searchEvent = searchField.getText().trim();
            searchLocation = locationField.getText().trim();

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

    private void fetchAndDisplayListings() {
        if (eventSearchController == null) {
            JOptionPane.showMessageDialog(frame, "Search controller not set.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        eventSearchController.searchEvents(searchEvent, searchLocation, LIMIT, offset);
    }
}
