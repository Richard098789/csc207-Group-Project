package database;

import java.io.FileInputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;

/**
 * Firestore Initializer class.
 */
public class FireStoreInitializer {

    /**
     * Firestore Initializer.
     * @return the databse is initialized.
     */
    public static Firestore initializeFirestore() {
        try {
            final FileInputStream serviceAccount = new FileInputStream(
                    "src/main/java/database/csc207musicapp-firebase-adminsdk-gzeyt-8c0d614d66.json");

            final Firestore db = FirestoreOptions.getDefaultInstance()
                    .toBuilder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setProjectId("csc207musicapp")
                    .build()
                    .getService();

            System.out.println("Firestore initialized successfully!");
            
            return db;
        }

        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
