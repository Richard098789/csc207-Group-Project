package view;

import entity.Artist;

import javax.swing.*;
import java.awt.*;

public class ArtistView extends JFrame {

    private JTextField artistNameField;
    private JTextField countryField;
    private JTextField typeField;
    private JTextField scoreField;
    private JTextField isDeadField;
    private JTextArea artistInfoArea;

    public ArtistView() {
        setTitle("Artist Information");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create input form
        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        formPanel.add(new JLabel("Artist Name:"));
        artistNameField = new JTextField();
        formPanel.add(artistNameField);

        formPanel.add(new JLabel("Country:"));
        countryField = new JTextField();
        formPanel.add(countryField);

        formPanel.add(new JLabel("Type (Person/Group):"));
        typeField = new JTextField();
        formPanel.add(typeField);

        formPanel.add(new JLabel("Score:"));
        scoreField = new JTextField();
        formPanel.add(scoreField);

        formPanel.add(new JLabel("Is Dead (true/false):"));
        isDeadField = new JTextField();
        formPanel.add(isDeadField);

        // Add form and text area to the frame
        add(formPanel, BorderLayout.NORTH);

        // Create button and text area
        JButton displayButton = new JButton("Display Artist Info");
        displayButton.addActionListener(e -> displayArtistInfo());
        add(displayButton, BorderLayout.CENTER);

        artistInfoArea = new JTextArea(10, 40);
        artistInfoArea.setEditable(false);
        add(new JScrollPane(artistInfoArea), BorderLayout.SOUTH);
    }

    private void displayArtistInfo() {
        try {
            String artistName = artistNameField.getText();
            String country = countryField.getText();
            String type = typeField.getText();
            int score = Integer.parseInt(scoreField.getText());
            boolean isDead = Boolean.parseBoolean(isDeadField.getText());

            Artist artist = new Artist("00", artistName, country, score, type, isDead);

            artistInfoArea.setText(String.format(
                    "Artist Name: %s%nCountry: %s%nType: %s%nScore: %d%nIs Dead: %b",
                    artist.getArtistName(), artist.getCountry(), artist.getType(),
                    artist.getScore(), artist.isDead()
            ));
        } catch (NumberFormatException ex) {
            artistInfoArea.setText("Invalid input! Please check the fields.");
        }
    }

    public static void main(String[] args) {
        System.out.println("start");
        SwingUtilities.invokeLater(() -> {
            ArtistView artistView = new ArtistView();
            artistView.setVisible(true);
        });
    }
}

