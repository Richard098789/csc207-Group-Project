package data_access;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import Use_case.login.LoginDataAccessInterface;
import Use_case.signup.SignupDataAccessInterface;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;
import entity.User;
import global_storage.CurrentUser;

/**
 * The data access object of user information.
 */
public class DBUserAccessObject implements LoginDataAccessInterface,
        SignupDataAccessInterface {
    private final Map<String, User> allUsers = new HashMap<>();

    public DBUserAccessObject() {
        try {
            final ApiFuture<QuerySnapshot> data = CurrentUser.db.collection("Users").get();
            for (QueryDocumentSnapshot document : data.get().getDocuments()) {
                final String password = document.getString("password");
                final String username = document.getString("username");
                final User user = new User(username, password);
                allUsers.put(username, user);
            }
        }

        catch (InterruptedException | ExecutionException ex) {
            System.err.println("Error loading userInfo: " + ex.getMessage());
        }
    }

    @Override
    public boolean userExists(String username) {

        return allUsers.containsKey(username);
    }

    @Override
    public void addUser(String username, String password) {
        try {
            if (allUsers.containsKey(username)) {
                // Username already exists
                return;
            }
            final User newUser = new User(username, password);

            // Update to local usermanager
            allUsers.put(username, newUser);

            final Map<String, String> data = newUser.getSignInInfo();

            // SetOption to merge so that it would not override previous data.
            CurrentUser.db.collection("Users")
                    .document(username).set(data, SetOptions.merge()).get();

        }

        catch (InterruptedException | ExecutionException ex) {
            System.err.println("Error writing document: " + ex.getMessage());
        }
    }

    @Override
    public boolean validateLogin(String username, String password) {
        final User user = allUsers.get(username);
        return user != null && user.getPassword().equals(password);
    }
}
