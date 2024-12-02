package data_access;

import Use_case.writer.WriterDataAccessInterface;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.SetOptions;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import database.FireStoreInitializer;
import global_storage.CurrentUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class DBWriterAccessObject implements WriterDataAccessInterface {

    private final Firestore db = CurrentUser.db;


    @Override
    public void addComment(String artistId, String username, double rating, String comment) {
        try {
            // Prepare the data to save
            Map<String, Object> commentData = new HashMap<>();
            commentData.put("username", username);
            commentData.put("rating", rating);
            commentData.put("comment", comment);

            // Add the data under the artist's comments collection
            WriteResult result = db.collection("Artists")
                    .document(artistId)
                    .collection("Comments")
                    .document() // Auto-generate document ID
                    .set(commentData, SetOptions.merge())
                    .get();

            System.out.println("Comment added successfully at: " + result.getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error adding comment: " + e.getMessage());
        }
    }

    @Override
    public double getAverageRating(String artistId) {
        try {
            // Get all comments for the artist
            List<QueryDocumentSnapshot> documents = db.collection("Artists")
                    .document(artistId)
                    .collection("Comments")
                    .get()
                    .get()
                    .getDocuments();

            // Calculate the average rating
            double totalRating = 0.0;
            for (QueryDocumentSnapshot doc : documents) {
                totalRating += (double) doc.get("rating");
            }
            return documents.size() > 0 ? totalRating / documents.size() : 0.0;

        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error calculating average rating: " + e.getMessage());
        }
        return 0.0;
    }

    @Override
    public Map<String, Object> getComments(String artistId) {
        try {
            // Get all comments for the artist
            List<QueryDocumentSnapshot> documents = db.collection("Artists")
                    .document(artistId)
                    .collection("Comments")
                    .get()
                    .get()
                    .getDocuments();

            Map<String, Object> comments = new HashMap<>();
            for (QueryDocumentSnapshot doc : documents) {
                comments.put(doc.getId(), doc.getData());
            }
            return comments;

        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error retrieving comments: " + e.getMessage());
        }
        return null;
    }
}
