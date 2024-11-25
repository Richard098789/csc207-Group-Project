package database;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

public class UserLogin {

    public static String loginUser(String email, String password) {
        try {
            FirebaseAuth auth = FirebaseAuth.getInstance();

            UserRecord userRecord = auth.getUserByEmail(email);

            System.out.println("Login successful for email: " + email);
            return userRecord.getUid(); // Return the UID for further operations
        } catch (FirebaseAuthException e) {
            System.err.println("Error during login: " + e.getMessage());
            return null;
        }
    }
}
