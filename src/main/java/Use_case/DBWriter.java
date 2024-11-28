package Use_case;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import database.FireStoreInitializer;
import com.google.cloud.firestore.SetOptions;

public class DBWriter {
    static final String ARTIST_COLLECTION_NAME = "artists";

    public static void addComment(Firestore db, String artistId, String username, Double rating, String comment) {
        try {
            // Prepare data to write
            Map<String, Object> commentData = new HashMap<>();
            commentData.put("username", username);
            commentData.put("rating", rating);
            commentData.put("comment", comment);

            // Write data to Firestore under the artist's comments subcollection
            WriteResult result = db.collection(ARTIST_COLLECTION_NAME)
                    .document(artistId)
                    .collection("comments")
                    .document() // Let Firestore auto-generate a unique document ID for each comment
                    .set(commentData, SetOptions.merge()) // Merge in case there are existing fields
                    .get();

            System.out.println("Comment successfully written to Firestore with timestamp: " + result.getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error writing comment to database: " + e.getMessage());
        }
    }

    // Main method for testing purposes
    public static void main(String[] args) {
        Firestore db = FireStoreInitializer.initializeFirestore();
        if (db != null) {
            addComment(db, "artist12345", "JohnDoe", 5.0, "Amazing artist!");
        }
    }
}
