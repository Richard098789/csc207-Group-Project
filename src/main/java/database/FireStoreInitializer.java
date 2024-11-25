package database;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;

import java.io.FileInputStream;

public class FireStoreInitializer {

    public static Firestore initializeFirestore() {
        try {
            FileInputStream serviceAccount = new FileInputStream("src/main/java/database/csc207musicapp-firebase-adminsdk-gzeyt-8c0d614d66.json");

            Firestore db = FirestoreOptions.getDefaultInstance()
                    .toBuilder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setProjectId("csc207musicapp")
                    .build()
                    .getService();

            System.out.println("Firestore initialized successfully!");
            // Initialize Firebase
            FileInputStream serviceAccount1 = new FileInputStream("src/main/java/database/csc207musicapp-firebase-adminsdk-gzeyt-8c0d614d66.json");
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount1))
                    .setProjectId("csc207musicapp")
                    .build();
            FirebaseApp.initializeApp(options);

            // Initialize Firestore
            ;
            return db;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
