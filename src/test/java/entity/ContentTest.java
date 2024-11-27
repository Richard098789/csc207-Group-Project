package entity;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ContentTest {

    @Test
    void testContentCreation() {
        // Create a new Content instance
        Content content = new Content("testContentId");

        // Verify the initial state
        assertEquals("testContentId", content.getContentId());
        assertTrue(content.getContent().isEmpty());
    }

    @Test
    void testSetContent() {
        // Create a new Content instance
        Content content = new Content("testContentId");

        // Create new content map
        Map<String, Map<String, Object>> newContent = new HashMap<>();
        Map<String, Object> user1Data = new HashMap<>();
        user1Data.put("rating", 8.0);
        newContent.put("user1", user1Data);

        // Set content
        content.setContent(newContent);

        // Verify content is updated
        assertEquals(1, content.getContent().size());
        assertTrue(content.getContent().containsKey("user1"));
        assertEquals(8.0, content.getContent().get("user1").get("rating"));
    }

    @Test
    void testAverageRatingCalculation() {
        // Create a new Content instance
        Content content = new Content("testContentId");

        // Add ratings
        Map<String, Object> user1Data = new HashMap<>();
        user1Data.put("rating", 8.0);

        Map<String, Object> user2Data = new HashMap<>();
        user2Data.put("rating", 9.0);

        Map<String, Map<String, Object>> newContent = new HashMap<>();
        newContent.put("user1", user1Data);
        newContent.put("user2", user2Data);

        content.setContent(newContent);
        content.setAverageRating();

        // Verify the average rating
        assertEquals(8.5, content.getAverageRating());
    }

    @Test
    void testAverageRatingWithNoRatings() {
        // Create a new Content instance
        Content content = new Content("testContentId");

        // Verify average rating when no ratings are present
        content.setAverageRating();
        assertEquals(0.0, content.getAverageRating());
    }
}
