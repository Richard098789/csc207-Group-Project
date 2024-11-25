package entity;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ContentTest {

    @Test
    void testContentCreation() {
        // Create a new Content instance
        Content content = new Content();

        // Verify the default state
        assertNotNull(content.getContentId());
        assertEquals(0, content.getUserIds().size());
        assertEquals(0, content.getComments().size());
        assertEquals(0, content.getUserRatings().size());
        assertEquals(0.0, content.getRating());
    }

    @Test
    void testAddUserId() {
        Content content = new Content();

        content.addUserId("user1");
        content.addUserId("user2");

        List<String> userIds = content.getUserIds();
        assertEquals(2, userIds.size());
        assertTrue(userIds.contains("user1"));
        assertTrue(userIds.contains("user2"));
    }

    @Test
    void testRemoveUserId() {
        Content content = new Content();

        content.addUserId("user1");
        content.addUserId("user2");
        content.removeUserId("user1");

        List<String> userIds = content.getUserIds();
        assertEquals(1, userIds.size());
        assertFalse(userIds.contains("user1"));
        assertTrue(userIds.contains("user2"));
    }

    @Test
    void testAddComment() {
        Content content = new Content();

        content.addComment("Great content!");
        content.addComment("Very useful.");

        List<String> comments = content.getComments();
        assertEquals(2, comments.size());
        assertTrue(comments.contains("Great content!"));
        assertTrue(comments.contains("Very useful."));
    }

    @Test
    void testRemoveComment() {
        Content content = new Content();

        content.addComment("Great content!");
        content.addComment("Very useful.");
        content.removeComment("Great content!");

        List<String> comments = content.getComments();
        assertEquals(1, comments.size());
        assertFalse(comments.contains("Great content!"));
        assertTrue(comments.contains("Very useful."));
    }

    @Test
    void testAddOrUpdateUserRating() {
        Content content = new Content();

        content.addOrUpdateUserRating("user1", 8.5);
        content.addOrUpdateUserRating("user2", 9.0);

        Map<String, Double> ratings = content.getUserRatings();
        assertEquals(2, ratings.size());
        assertEquals(8.5, ratings.get("user1"));
        assertEquals(9.0, ratings.get("user2"));

        // Update a rating
        content.addOrUpdateUserRating("user1", 7.5);
        assertEquals(7.5, ratings.get("user1"));
    }

    @Test
    void testRemoveUserRating() {
        Content content = new Content();

        content.addOrUpdateUserRating("user1", 8.5);
        content.addOrUpdateUserRating("user2", 9.0);
        content.removeUserRating("user1");

        Map<String, Double> ratings = content.getUserRatings();
        assertEquals(1, ratings.size());
        assertFalse(ratings.containsKey("user1"));
        assertTrue(ratings.containsKey("user2"));
    }

    @Test
    void testGetRating() {
        Content content = new Content();

        content.addOrUpdateUserRating("user1", 8.5);
        content.addOrUpdateUserRating("user2", 9.0);

        assertEquals(8.8, content.getRating());
    }

    @Test
    void testGetRatingWithNoRatings() {
        Content content = new Content();

        assertEquals(0.0, content.getRating());
    }

    @Test
    void testAddCommentInvalid() {
        Content content = new Content();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> content.addComment(""));
        assertEquals("comment cannot be empty", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> content.addComment(null));
        assertEquals("comment cannot be empty", exception.getMessage());
    }

    @Test
    void testAddOrUpdateUserRatingInvalid() {
        Content content = new Content();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> content.addOrUpdateUserRating("user1", -1));
        assertEquals("rating must be between 0.0 and 10.0", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> content.addOrUpdateUserRating("user1", 11));
        assertEquals("rating must be between 0.0 and 10.0", exception.getMessage());
    }
}
