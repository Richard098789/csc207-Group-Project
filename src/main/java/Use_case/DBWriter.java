package Use_case;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.SetOptions;
import com.google.cloud.firestore.WriteResult;
import database.FireStoreInitializer;
public class DBWriter {
    static final String PUBLIC_COLLECTION_NAME = "Public";

    public static void toPublic(Firestore db, String contentID, String UserName, Double rating, String comment) {
        try {
            // Prepare data to write
            Map<String, Map<String, Object>> data = new HashMap<>();
            Map<String, Object> content = new HashMap<>();
            content.put("rating", rating);
            content.put("comment", comment);
            data.put(UserName, content);

            // Write data to Firestore
            WriteResult result = db.collection(PUBLIC_COLLECTION_NAME).document(contentID).set(data, SetOptions.merge()).get(); // SetOption to merge so that it would not override previous data.
            System.out.println("Document written successfully");
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error writing document: " + e.getMessage());
        }
    }

     public static void main(String[] args) {
         Firestore db = FireStoreInitializer.initializeFirestore();
         if (db != null) {
             DBWriter.toPublic(db, "98765432", "Richard2", 1.0, "This is a fucking shit artist");
         }
     }
    
}
