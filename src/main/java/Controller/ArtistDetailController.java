package Controller;

import Use_case.DBWriter;
import Use_case.UserManager;
import com.google.cloud.firestore.Firestore;
import entity.Artist;
import entity.Content;
import Use_case.DBReader;

import java.util.List;
import java.util.Map;

public class ArtistDetailController {
    private final Artist artist;
    private final Content content;
    private final Firestore db;

    public ArtistDetailController(Artist artist, Content content, Firestore db) {
        this.artist = artist;
        this.content = content;
        this.db = db;
    }

    public Artist getArtist() {
        return artist;
    }

    public Content getContent() {
        return content;
    }

    public void submitFeedback(int rating, String comment) {
        if (UserManager.currentUser == null) {
            System.err.println("No user is currently logged in. Feedback submission aborted.");
            return;
        }

        String username = UserManager.currentUser.getUsername();

        // Add comment to Firestore using the new addComment() method
        DBWriter.addComment(db, artist.getId(), username, (double) rating, comment);

        // Update the local content object with the new comment
        content.getContent().put(username, Map.of("rating", rating, "comment", comment));
        content.setAverageRating();
    }

    public List<Map<String, Object>> getComments() {
        // Logic to fetch and return comments for the current artist from Firestore
        return DBReader.readComments(db, artist.getId());
    }

    public double getAverageRating() {
        return content.getAverageRating();
    }
}
