package global_storage;

import com.google.cloud.firestore.Firestore;

public class CurrentUser {
    public static String username = "";
    public static Firestore db;
}
