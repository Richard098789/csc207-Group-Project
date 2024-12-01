package UI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Use_case.UserManager;
import Controller.ArtistSearchController;
import Use_case.artist_search.ArtistSearchInteractor;
import Use_case.artist_search.ArtistSearchPresenter;
import Use_case.artist_search.MusicBrainzArtistRepository;
import view.MainMenuView;

public class SearchSelection {

    private JFrame frame;

    public SearchSelection() {
        frame = new JFrame("Music Listing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Select Categories", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton artistButton = new JButton("Artist Listings");
        JButton eventButton = new JButton("Event Listings");
        JButton mainMenuButton = new JButton("Main Menu");

        buttonPanel.add(artistButton);
        buttonPanel.add(eventButton);
        buttonPanel.add(mainMenuButton);

        frame.add(buttonPanel, BorderLayout.CENTER);

        // Initialize dependencies for ArtistListing
        MusicBrainzArtistRepository repository = new MusicBrainzArtistRepository();
        ArtistSearchPresenter presenter = new ArtistSearchPresenter();
        ArtistSearchInteractor interactor = new ArtistSearchInteractor(repository, presenter);
        ArtistSearchController controller = new ArtistSearchController(interactor);

        // Listings button handlers
        artistButton.addActionListener(e -> new ArtistListing(controller, presenter)); // Pass the required arguments
        eventButton.addActionListener(e -> new EventListing()); // Assuming EventListing requires no arguments for now
        mainMenuButton.addActionListener(e -> {
            // Close the current frame
            frame.dispose();

            // Open the main menu page
            new MainMenuView(new UserManager()); // UserManager must be passed appropriately
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SearchSelection::new);
    }

}
