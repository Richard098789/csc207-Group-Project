package view;

import Controller.ArtistDetailController;
import entity.ArtistDetailModel;

import javax.swing.*;
import java.awt.*;

public class ArtistDetailView {
    private JFrame frame;
    private final ArtistDetailModel model;
    private final ArtistDetailController controller;

    public ArtistDetailView(ArtistDetailModel model, ArtistDetailController controller) {
        this.model = model;
        this.controller = controller;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("Artist Details");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("Artist Details", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Artist Details Panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        detailsPanel.add(createDetailLabel("Artist Name: ", model.getArtist().getArtistName()));
        detailsPanel.add(createDetailLabel("ID: ", model.getArtist().getId()));
        detailsPanel.add(createDetailLabel("Country: ", model.getArtist().getCountry()));
        detailsPanel.add(createDetailLabel("Score: ", String.valueOf(model.getArtist().getScore())));
        detailsPanel.add(createDetailLabel("Type: ", model.getArtist().getType()));

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
        JScrollPane scrollPane = new JScrollPane(commentBox);
        userInputPanel.add(scrollPane);

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
            }
        });
        userInputPanel.add(addButton);

        frame.add(userInputPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private JLabel createDetailLabel(String field, String value) {
        JLabel label = new JLabel("<html><b>" + field + "</b>" + value + "</html>");
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        return label;
    }
}
