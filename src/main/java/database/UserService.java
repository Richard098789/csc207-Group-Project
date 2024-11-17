package database;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserService {

    public static void createUser(String username, String email, String password) {
        try {
            // Create user with email and password
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setPassword(password)
                    .setEmailVerified(false)
                    .setDisplayName(username)
                    .setDisabled(false);

            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            System.out.println("Successfully created new user: " + userRecord.getUid());

            // Save user details in Realtime Database
            saveUserToDatabase(userRecord.getUid(), username, email);

        } catch (Exception e) {
            System.err.println("Error creating user: " + e.getMessage());
        }
    }

    private static void saveUserToDatabase(String userId, String username, String email) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = database.child("users").child(userId);

        userRef.setValueAsync(new User(username, email));
        System.out.println("User saved to database!");
    }

    // User model for database
    static class User {
        public String username;
        public String email;

        public User(String username, String email) {
            this.username = username;
            this.email = email;
        }
    }
}

