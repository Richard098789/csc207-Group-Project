package data_access;

import Use_case.read_from_db.ReadDataAccessInterface;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import global_storage.CurrentUser;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public class DBPublicAccessObject implements ReadDataAccessInterface {

    private Firestore db = CurrentUser.db;

    @Override
    public Map<String, Object> readContents(String documentID) {
        try {
            // Get the comments collection under the artist document
            DocumentSnapshot document = db.collection("Public").document(documentID).get().get();
            // Fetch the documents in the comments collection
            if (document.exists()) {

                return document.getData();

            } else {
                System.out.println("Document does not exist");
            }

        }
        catch (InterruptedException | ExecutionException e) {
            System.err.println("Error reading document: " + e.getMessage());
        }
        return null;
    }

}
