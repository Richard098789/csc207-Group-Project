package entity;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserCreation() {
        // Create a user
        User user = new User("john_doe", "password123");

        // Verify initial values
        assertNotNull(user.getUserId()); // UUID should not be null
        assertEquals("john_doe", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertTrue(user.getComments().isEmpty());
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
        user.addComment("This is the first comment.");
        user.addComment("This is the second comment.");

        // Verify the comments list
        List<String> comments = user.getComments();
        assertEquals(2, comments.size());
        assertTrue(comments.contains("This is the first comment."));
        assertTrue(comments.contains("This is the second comment."));
    }

    @Test
    void testRemoveComment() {
        // Create a user
        User user = new User("john_doe", "password123");

        // Add comments
        user.addComment("First comment");
        user.addComment("Second comment");

        // Remove a comment
        user.removeComment("First comment");

        // Verify the comments list
        List<String> comments = user.getComments();
        assertEquals(1, comments.size());
        assertFalse(comments.contains("First comment"));
        assertTrue(comments.contains("Second comment"));
    }

    @Test
    void testEmptyCommentsList() {
        // Create a user
        User user = new User("john_doe", "password123");

        // Verify that the comments list is initially empty
        assertTrue(user.getComments().isEmpty());

        // Add and then remove a comment
        user.addComment("Temporary comment");
        user.removeComment("Temporary comment");

        // Verify that the comments list is empty again
        assertTrue(user.getComments().isEmpty());
    }
}
