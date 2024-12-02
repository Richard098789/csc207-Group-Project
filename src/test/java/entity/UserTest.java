package entity;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserCreation() {
        // Create a user
        User user = new User("john_doe", "password123");

        // Verify initial values
        assertNotNull(user.getUserId());
        assertEquals("john_doe", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertTrue(user.getCommentId().isEmpty());
    }

    @Test
    void testSetUsername() {
        // Create a user
        User user = new User("john_doe", "password123");

        // Change the username
        user.setUsername("jane_doe");

        // Verify the updated username
        assertEquals("jane_doe", user.getUsername());
    }

    @Test
    void testSetPassword() {
        // Create a user
        User user = new User("john_doe", "password123");

        // Change the password
        user.setPassword("new_password");

        // Verify the updated password
        assertEquals("new_password", user.getPassword());
    }

    @Test
    void testAddComment() {
        // Create a user
        User user = new User("john_doe", "password123");

        // Add comments
        user.addComment("comment1");
        user.addComment("comment2");

        // Verify the comments list
        List<String> comments = user.getCommentId();
        assertEquals(2, comments.size());
        assertTrue(comments.contains("comment1"));
        assertTrue(comments.contains("comment2"));
    }

    @Test
    void testRemoveComment() {
        // Create a user
        User user = new User("john_doe", "password123");

        // Add comments
        user.addComment("comment1");
        user.addComment("comment2");

        // Remove a comment
        user.removeComment("comment1");

        // Verify the comments list
        List<String> comments = user.getCommentId();
        assertEquals(1, comments.size());
        assertFalse(comments.contains("comment1"));
        assertTrue(comments.contains("comment2"));
    }

    @Test
    void testEmptyCommentsList() {
        // Create a user
        User user = new User("john_doe", "password123");

        // Verify that the comments list is initially empty
        assertTrue(user.getCommentId().isEmpty());

        // Add and then remove a comment
        user.addComment("Temporary comment");
        user.removeComment("Temporary comment");

        // Verify that the comments list is empty again
        assertTrue(user.getCommentId().isEmpty());
    }

    @Test
    void testGetSignInInfo() {
        // Create a user
        User user = new User("john_doe", "password123");

        // Get sign-in info
        Map<String, String> signInInfo = user.getSignInInfo();

        // Verify the map contents
        assertEquals(2, signInInfo.size());
        assertEquals("john_doe", signInInfo.get("username"));
        assertEquals("password123", signInInfo.get("password"));
    }
}
