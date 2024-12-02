package data_access;

import Use_case.login.LoginDataAccessInterface;
import Use_case.signup.SignupDataAccessInterface;
import Use_case.writer.WriterDataAccessInterface;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import entity.User;
import global_storage.CurrentUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class DBUserAccessObject implements LoginDataAccessInterface,
        SignupDataAccessInterface {

    private final Map<String, User> allUsers;
    private final Firestore db = CurrentUser.db;
    public DBUserAccessObject() {
        allUsers = new HashMap<>();
    }

    private void getAllUsers() {
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

    @Override
    public boolean userExists(String username) {
        getAllUsers();
        return allUsers.containsKey(username);
    }

    @Override
    public boolean addUser(String username, String password) {
        getAllUsers();
        try {
            if (allUsers.containsKey(username)) {
                return false; // Username already exists
            }
            User newUser = new User(username, password);
            allUsers.put(username, newUser); //Update to local usermanager

            Map<String, String> data = newUser.getSignInInfo();
            WriteResult result = db.collection("Users").document(username).set(data, SetOptions.merge()).get(); // SetOption to merge so that it would not override previous data.
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
