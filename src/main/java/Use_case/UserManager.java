package Use_case;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;
import com.google.cloud.firestore.WriteResult;

import database.FireStoreInitializer;
import entity.User;

public class UserManager {
    private final static String USER_COLLECTION_NAME = "Users";

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
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error loading userInfo: " + e.getMessage());
        }
    }

    public boolean addUser(String username, String password) {
        try {
            if (allUsers.containsKey(username)) {
                return false; // Username already exists
            }
            User newUser = new User(username, password);
            allUsers.put(username, newUser); //Update to local usermanager
            
            Map<String, String> data = newUser.getSignInInfo();
            WriteResult result = db.collection(USER_COLLECTION_NAME).document(username).set(data, SetOptions.merge()).get(); // SetOption to merge so that it would not override previous data.
            return true;
            
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error writing document: " + e.getMessage());
            return false;
        }
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
