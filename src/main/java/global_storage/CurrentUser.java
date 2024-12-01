package global_storage;

import com.google.cloud.firestore.Firestore;
import database.FireStoreInitializer;

public class CurrentUser {
    public static String username = "";
    public static Firestore db = FireStoreInitializer.initializeFirestore();

    public static void setCurrentUser(String username) {
        CurrentUser.username = username;
    }

    public static void clear() {
        CurrentUser.username = "";
    }

    public static boolean isLoggedIn() {
        return !username.isEmpty();
    }
}
