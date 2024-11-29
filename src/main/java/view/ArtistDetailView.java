package view;

import Controller.ArtistDetailController;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class ArtistDetailView {
    private JFrame frame;
    private final ArtistDetailController controller;
    private JPanel detailsPanel; // Store reference to details panel for updates
    private JPanel commentsPanel; // Store reference to comments panel

    public ArtistDetailView(ArtistDetailController controller) {
        this.controller = controller;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("Artist Details");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("Artist Details", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Artist Details Panel
        detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        updateDetailsPanel();

        frame.add(detailsPanel, BorderLayout.CENTER);

        // User Input Panel (Rating and Comment)
        JPanel userInputPanel = new JPanel();
        userInputPanel.setLayout(new BoxLayout(userInputPanel, BoxLayout.Y_AXIS));
        userInputPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Rating Panel
        JPanel ratingPanel = new JPanel();
        ratingPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel rateLabel = new JLabel("Rate:");
        rateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        ratingPanel.add(rateLabel);

        JComboBox<Integer> ratingDropdown = new JComboBox<>();
        for (int i = 0; i <= 10; i++) {
            ratingDropdown.addItem(i);
        }
        ratingPanel.add(ratingDropdown);
        userInputPanel.add(ratingPanel);

        // Comment Panel
        JLabel commentLabel = new JLabel("Comment:");
        commentLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        userInputPanel.add(commentLabel);

        JTextArea commentBox = new JTextArea(3, 20);
        commentBox.setLineWrap(true);
        commentBox.setWrapStyleWord(true);
        JScrollPane commentScrollPane = new JScrollPane(commentBox);
        commentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        userInputPanel.add(commentScrollPane);

        // Emoji Button
        JButton emojiButton = new JButton("Emoji");
        emojiButton.setFont(new Font("Arial", Font.PLAIN, 16));
        emojiButton.addActionListener(e -> {
            String[] emojis = {
                    "😀", "😂", "😍", "😢", "😡", "👍", "👎", "👏", "🙌", "🔥",
                    "💯", "😎", "🤔", "😭", "🥳", "😇", "🤩", "🥺", "😤", "🤗",
                    "😱", "😜", "💖", "🎶", "🎉"
            };
            String selectedEmoji = (String) JOptionPane.showInputDialog(
                    frame,
                    "Choose an emoji",
                    "Emoji Picker",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    emojis,
                    emojis[0]);
            if (selectedEmoji != null) {
                commentBox.append(selectedEmoji);
            }
        });
        userInputPanel.add(emojiButton);

        // Add Button
        JButton addButton = new JButton("Add");
        addButton.setFont(new Font("Arial", Font.PLAIN, 16));
        addButton.addActionListener(e -> {
            int rating = (int) ratingDropdown.getSelectedItem();
            String comment = commentBox.getText().trim();

            if (comment.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please add a comment before submitting.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                controller.submitFeedback(rating, comment);
                JOptionPane.showMessageDialog(frame, "Thank you for your feedback!", "Success", JOptionPane.INFORMATION_MESSAGE);

                // Clear fields
                ratingDropdown.setSelectedIndex(0);
                commentBox.setText("");

                // Refresh average rating and comments
                updateDetailsPanel();
                loadComments();
            }
        });
        userInputPanel.add(addButton);

        frame.add(userInputPanel, BorderLayout.SOUTH);

        // Load existing comments
        loadComments();

        frame.setVisible(true);
    }

    private void updateDetailsPanel() {
        // Remove all components and add updated artist details
        detailsPanel.removeAll();

        detailsPanel.add(createDetailLabel("Artist Name: ", controller.getArtist().getArtistName()));
        detailsPanel.add(createDetailLabel("ID: ", controller.getArtist().getId()));
        detailsPanel.add(createDetailLabel("Country: ", controller.getArtist().getCountry()));
        detailsPanel.add(createDetailLabel("Score: ", String.valueOf(controller.getArtist().getScore())));
        detailsPanel.add(createDetailLabel("Type: ", controller.getArtist().getType()));
        detailsPanel.add(createDetailLabel("Average Rating: ", String.valueOf(controller.getAverageRating())));

        detailsPanel.revalidate();
        detailsPanel.repaint();
    }

    private void loadComments() {
        // Clear previous comments
        if (commentsPanel != null) {
            frame.remove(commentsPanel);
        }

        // Create a scrollable text area for comments
        commentsPanel = new JPanel();
        commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));
        commentsPanel.setBorder(BorderFactory.createTitledBorder("Comments"));

        JTextArea commentsTextArea = new JTextArea(10, 30);
        commentsTextArea.setEditable(false);
        commentsTextArea.setLineWrap(true);
        commentsTextArea.setWrapStyleWord(true);

        List<Map<String, Object>> comments = controller.getComments();
        StringBuilder allComments = new StringBuilder();
        for (Map<String, Object> comment : comments) {
            String username = (String) comment.get("username");
            String text = (String) comment.get("comment");
            allComments.append(username).append(": ").append(text).append("\n");
        }

        commentsTextArea.setText(allComments.toString());
        JScrollPane commentsScrollPane = new JScrollPane(commentsTextArea);
        commentsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        commentsPanel.add(commentsScrollPane);

        frame.add(commentsPanel, BorderLayout.EAST);
        frame.revalidate();
        frame.repaint();
    }

    private JLabel createDetailLabel(String field, String value) {
        JLabel label = new JLabel("<html><b>" + field + "</b>: " + value + "</html>");
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        return label;
    }
}
