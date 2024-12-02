package view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data_transfer_object.Event;


public class EventDetailView {
    private JFrame frame;

    public EventDetailView(Event event) {

        frame = new JFrame("Event Details");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("Event Details", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Event Detail Panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Adding information regarding the event
        detailsPanel.add(new JLabel("<html><b>Name:</b> " + event.getName() + "</html>"));
        detailsPanel.add(new JLabel("<html><b>Artist:</b> " + event.getArtistName() + "</html>"));
        detailsPanel.add(new JLabel("<html><b>Type:</b> " + event.getType() + "</html>"));
        detailsPanel.add(new JLabel("<html><b>Begin Date:</b> " + event.getBeginDate() + "</html>"));
        detailsPanel.add(new JLabel("<html><b>End Date:</b> " + event.getEndDate() + "</html>"));
        detailsPanel.add(new JLabel("<html><b>Time:</b> " + event.getTime() + "</html>"));
        detailsPanel.add(new JLabel("<html><b>Place Name:</b> " + event.getPlaceName() + "</html>"));
        detailsPanel.add(new JLabel("<html><b>Score:</b> " + event.getScore() + "</html>"));

        frame.add(detailsPanel, BorderLayout.CENTER);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.addActionListener(e -> {
            frame.dispose();
            //new EnhancedListing();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

    }

}
