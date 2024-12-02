package data_access;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import Use_case.read_from_db.ReadDataAccessInterface;
import Use_case.writer.WriterDataAccessInterface;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.firestore.SetOptions;
import global_storage.CurrentUser;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
/**
 * The data access object of public information.
 */
public class DBPublicAccessObject implements ReadDataAccessInterface,
        WriterDataAccessInterface {


    private final Firestore db = CurrentUser.db;

    @Override
    public Map<String, Object> readContents(String documentID) {
        try {
            // Get the comments collection under the artist document
            final DocumentSnapshot document = db.collection("Public").document(documentID).get().get();
            // Fetch the documents in the comments collection
            if (document.exists()) {
                return document.getData();

            }

        }
        catch (InterruptedException | ExecutionException ex) {
            System.err.println("Error reading document: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public void addComment(String contentID, String username, double rating, String comment) {
        try {
            // Prepare the data to save
            Map<String, Object> commentData = new HashMap<>();
            commentData.put("rating", rating);
            commentData.put("comment", comment);
            Map<String, Map<String, Object>> data = new HashMap<>();
            data.put(username, commentData);
            // Add the data under the artist's comments collection
            WriteResult result = db.collection("Public")
                    .document(contentID)
                    .set(data, SetOptions.merge())
                    .get();

            System.out.println("Comment added successfully at: " + result.getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error adding comment: " + e.getMessage());
        }
    }


}
