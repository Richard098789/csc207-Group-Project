package data_access;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import Use_case.read_from_db.ReadDataAccessInterface;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import global_storage.CurrentUser;

/**
 * The data access object of public information.
 */
public class DBPublicAccessObject implements ReadDataAccessInterface {

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

}
