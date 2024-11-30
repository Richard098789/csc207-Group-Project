package data_access;

import Use_case.login.LoginDataAccessInterface;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;
import com.google.cloud.firestore.WriteResult;
import entity.User;
import global_storage.CurrentUser;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class DBUserAccessObject implements LoginDataAccessInterface {
    private final Map<String, User> allUsers = new HashMap<>();

    public DBUserAccessObject() {
        try {
            ApiFuture<QuerySnapshot> data = CurrentUser.db.collection("Users").get();
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

    @Override
    public boolean addUser(String username, String password) {
        try {
            if (allUsers.containsKey(username)) {
                return false; // Username already exists
            }
            User newUser = new User(username, password);
            allUsers.put(username, newUser); //Update to local usermanager

            Map<String, String> data = newUser.getSignInInfo();
            WriteResult result = CurrentUser.db.collection("Users").document(username).set(data, SetOptions.merge()).get(); // SetOption to merge so that it would not override previous data.
            return true;

        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error writing document: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean validateLogin(String username, String password) {
        User user = allUsers.get(username);
        return user != null && user.getPassword().equals(password);
    }
}
