package Use_case;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import entity.User;

import java.util.HashMap;
import java.util.Map;

import database.FireStoreInitializer;
import com.google.cloud.firestore.QuerySnapshot;

public class UserManager {
    private final Map<String, User> allUsers = new HashMap<>(); // Simulates a database
    public static User currentUser = null;
    public static Firestore db = FireStoreInitializer.initializeFirestore();

    public void loadUsersFromDB() {
        try {
            ApiFuture<QuerySnapshot> data = db.collection("Users").get();
            for (QueryDocumentSnapshot document : data.get().getDocuments()) {
                String password = document.getString("password");
                String username = document.getString("username");
                User user = new User(username, password);
                allUsers.put(username, user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addUser(String username, String password) {
        if (allUsers.containsKey(username)) {
            return false; // Username already exists
        }
        allUsers.put(username, new User(username, password));
        return true;
    }

    public void setCurrentUser(String username, String password) {
        currentUser = new User(username, password);
    }

    public boolean validateLogin(String username, String password) {
        User user = allUsers.get(username);
        return user != null && user.getPassword().equals(password);
    }

    public boolean userExists(String username) {
        return allUsers.containsKey(username);
    }
}
