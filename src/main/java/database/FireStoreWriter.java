package database;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

public class FireStoreWriter {

    public static void writeNewDocument(Firestore db, String collectionName, String documentId) {
        try {
            // Prepare data to write
            Map<String, Object> data = new HashMap<>();
            data.put("name", "John");
            data.put("age", 30);
            data.put("email", "john.doe@example.com");

            // Write data to Firestore
            WriteResult result = db.collection(collectionName).document(documentId).set(data).get();
            System.out.println("Document written successfully");
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error writing document: " + e.getMessage());
        }
    }

    // public static void main(String[] args) {
    //     Firestore db = FireStoreInitializer.initializeFirestore();
    //     if (db != null) {
    //         String email = "testuser@example.com";
    //         String password = "password123";

    //         // Log in the user
    //         String uid = UserLogin.loginUser(email, password);

    //         if (uid != null) {
    //             // Write data to Firestore
    //             FireStoreWriter.writeNewDocument(db, uid);

    //             // Read data from Firestore
    //             FireStoreReader.readUserData(db, uid);
    //         }
    //     }
    // }
    
}




