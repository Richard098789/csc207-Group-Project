package database;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;

import java.util.concurrent.ExecutionException;

public class FireStoreReader {

    public static void readDocument(Firestore db, String collectionName, String documentId) {
        try {
            // Retrieve the document from Firestore
            DocumentSnapshot document = db.collection(collectionName).document(documentId).get().get();

            if (document.exists()) {
                // Print the document data
                System.out.println("Document data: " + document.getData());

                // Access specific fields
                String name = document.getString("name");
                Long age = document.getLong("age");
                String email = document.getString("email");

                System.out.println("Name: " + name);
                System.out.println("Age: " + age);
                System.out.println("Email: " + email);
            } else {
                System.out.println("No such document!");
            }
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error reading document: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Firestore db = FireStoreInitializer.initializeFirestore();
        if (db != null) {
            readDocument(db, "users", "user1");
        }
    }
}
