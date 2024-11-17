package database;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SimpleTest {

    public static void main(String[] args) {
        try {
            // Step 1: Initialize Firebase
            FireStoreInitializer.initializeFirestore();

            // Step 2: Create a new user
            String username = "testuser";
            String email = "testuser@example.com";
            String password = "password123";

            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setPassword(password)
                    .setDisplayName(username);

            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            System.out.println("User created successfully: " + userRecord.getUid());

            // Step 3: Add the user to the database
            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
            dbRef.child("users").child(userRecord.getUid()).setValueAsync(username);
            System.out.println("Username added to the database.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
