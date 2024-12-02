package view;

import data_transfer_object.Artist;
import data_transfer_object.Recording;
import global_storage.CurrentUser;
import interface_adapter.writer.WriterController;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class ArtistDetailView {
    private JFrame frame;
    private JPanel commentsPanel;  // Keeping reference for updating comments
    private JScrollPane commentsScrollPane;  // ScrollPane reference for comments
    private WriterController writeController;

    public ArtistDetailView(Recording[] topSongs, List<Map<String, String>> comments,
                            Artist artist, Double averageRating) {
        frame = new JFrame("Artist Details");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 800); // Increased size to fit all elements comfortably
        frame.setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("Artist Details", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Artist Details Panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        updateDetailsPanel(detailsPanel, artist, averageRating);

        frame.add(detailsPanel, BorderLayout.CENTER);

        // User Input Panel (Rating and Comment)
        JPanel userInputPanel = createUserInputPanel(detailsPanel, artist);
        frame.add(userInputPanel, BorderLayout.SOUTH);
        System.out.println(comments+"berfore load comments");
        // Load existing comments
        loadComments(comments);

        // Load songs
        loadSongs(topSongs);

        frame.setVisible(true);
    }

    private void updateDetailsPanel(JPanel detailsPanel, Artist artist, Double averageRating) {
        detailsPanel.removeAll();

        detailsPanel.add(createDetailLabel("Artist Name: ", artist.getArtistName()));
        detailsPanel.add(createDetailLabel("ID: ", artist.getId()));
        detailsPanel.add(createDetailLabel("Country: ", artist.getCountry()));
        detailsPanel.add(createDetailLabel("Type: ", artist.getType()));
        detailsPanel.add(createDetailLabel("Average Rating: ", String.valueOf(averageRating)));

        detailsPanel.revalidate();
        detailsPanel.repaint();
    }

    private JPanel createUserInputPanel(JPanel detailsPanel, Artist artist) {
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
        JScrollPane scrollPane = new JScrollPane(commentBox);
        userInputPanel.add(scrollPane);

        // Emoji Button
        JButton emojiButton = new JButton("Emoji");
        emojiButton.setFont(new Font("Arial", Font.PLAIN, 16));
        emojiButton.addActionListener(e -> {
            // Adding emojis
            String[] emojis = {"😀", "😂", "😍", "😢", "👍", "🔥", "💯", "🎶", "😎", "🤘", "❤️", "💔", "👏", "🙏", "🥳", "🤩", "😡", "🎉", "👀", "💥"};
            String selectedEmoji = (String) JOptionPane.showInputDialog(frame, "Select an emoji:", "Emoji Picker", JOptionPane.PLAIN_MESSAGE, null, emojis, emojis[0]);

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
                writeController.execute(artist.getId(), CurrentUser.username, comment, rating);

                // Clear fields
                ratingDropdown.setSelectedIndex(0);
                commentBox.setText("");

            }
        });
        userInputPanel.add(addButton);

        return userInputPanel;
    }

    private void loadComments(List<Map<String, String>> comments) {
        if (commentsPanel != null) {
            frame.remove(commentsScrollPane);  // Remove the existing scroll pane to refresh
        }

        commentsPanel = new JPanel();
        commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));
        commentsPanel.setBorder(BorderFactory.createTitledBorder("Comments"));

        if (!comments.isEmpty()) {
            System.out.println(comments+"inside load comments");
            for (Map<String, String> comment : comments) {
                System.out.println(comment.get("Richard1"));
                String username = comment.get("username");
                String text = comment.get("comment");
                JLabel commentLabelItem = new JLabel("<html><b>" + username + ":</b> " + text + "</html>");
                commentLabelItem.setFont(new Font("Arial", Font.PLAIN, 14));
                commentsPanel.add(commentLabelItem);
            }
        }
        commentsScrollPane = new JScrollPane(commentsPanel);
        commentsScrollPane.setPreferredSize(new Dimension(400, 250)); // Adjusted the size to fit more comments

        frame.add(commentsScrollPane, BorderLayout.EAST);
        frame.revalidate();
        frame.repaint();
    }

    private void loadSongs(Recording[] topSongs) {
        JPanel songsPanel = new JPanel();
        songsPanel.setLayout(new BoxLayout(songsPanel, BoxLayout.Y_AXIS));
        songsPanel.setBorder(BorderFactory.createTitledBorder("Top Songs"));


        if (topSongs.length == 0) {
            songsPanel.add(new JLabel("No songs found."));
        } else {
            for (Recording song : topSongs) {
                JLabel songLabel = new JLabel(song.getTitle() + " (" + song.getFormattedLength() + ")");
                songLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                songsPanel.add(songLabel);
            }
        }

        JScrollPane songsScrollPane = new JScrollPane(songsPanel);
        songsScrollPane.setPreferredSize(new Dimension(400, 250)); // Adjusted the size to fit more songs

        frame.add(songsScrollPane, BorderLayout.WEST);
        frame.revalidate();
        frame.repaint();
    }

    private JLabel createDetailLabel(String field, String value) {
        JLabel label = new JLabel("<html><b>" + field + "</b>: " + value + "</html>");
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        return label;
    }

    public void commentSuccess(String message) {
        JOptionPane.showMessageDialog(frame, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void commentFailure(String message) {
        JOptionPane.showMessageDialog(frame, message, "Failure", JOptionPane.ERROR_MESSAGE);
    }

    public void setWriterController(WriterController writerController) {
        this.writeController = writerController;
    }
}
