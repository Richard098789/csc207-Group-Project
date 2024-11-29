package Use_case;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class DBReader {

    // Method to read comments for a given artist from Firestore
    public static List<Map<String, Object>> readComments(Firestore db, String artistId) {
        List<Map<String, Object>> commentsList = new ArrayList<>();

        try {
            // Get the comments collection under the artist document
            CollectionReference commentsCollection = db.collection("artists")
                    .document(artistId)
                    .collection("comments");

            // Fetch the documents in the comments collection
            ApiFuture<QuerySnapshot> future = commentsCollection.get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            // Add each document's data to the comments list
            for (QueryDocumentSnapshot document : documents) {
                commentsList.add(document.getData());
            }

        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error reading comments: " + e.getMessage());
        }

        return commentsList;
    }

    // Method to calculate average rating for an artist
    public static double getAverageRating(Firestore db, String artistId) {
        double totalRating = 0.0;
        int count = 0;

        try {
            // Get the comments collection under the artist document
            CollectionReference commentsCollection = db.collection("artists")
                    .document(artistId)
                    .collection("comments");

            // Fetch the documents in the comments collection
            ApiFuture<QuerySnapshot> future = commentsCollection.get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            // Calculate the total rating and count the number of ratings
            for (QueryDocumentSnapshot document : documents) {
                if (document.contains("rating")) {
                    totalRating += document.getDouble("rating");
                    count++;
                }
            }

        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error calculating average rating: " + e.getMessage());
        }

        // Calculate and return the average rating
        return count > 0 ? Math.round((totalRating / count) * 10) / 10.0 : 0.0; // Rounded to one decimal place
    }
}
