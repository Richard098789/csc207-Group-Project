package Use_case;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.SetOptions;
import com.google.cloud.firestore.WriteResult;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class DBWriter {
    static final String COLLECTION_NAME = "artists";

    public static void addComment(Firestore db, String artistId, String username, double rating, String comment) {
        try {
            // Prepare data to write
            Map<String, Object> commentData = new HashMap<>();
            commentData.put("username", username);
            commentData.put("rating", rating);
            commentData.put("comment", comment);

            // Add to Firestore under the artist's collection
            WriteResult result = db.collection(COLLECTION_NAME)
                    .document(artistId)
                    .collection("comments")
                    .document()  // Auto-generate document ID for each comment
                    .set(commentData, SetOptions.merge())
                    .get();

            System.out.println("Document written successfully: " + result.getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error writing document: " + e.getMessage());
        }
    }
}
