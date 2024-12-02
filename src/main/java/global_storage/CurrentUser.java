package global_storage;

import com.google.cloud.firestore.Firestore;
import database.FireStoreInitializer;

/**
 * The current user storage class.
 */
public class CurrentUser {
    public static String username = "";
    public static Firestore db;

    public CurrentUser() {
        db = FireStoreInitializer.initializeFirestore();
    }
}
