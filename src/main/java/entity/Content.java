package entity;

import java.util.*;

/**
 * This class represents the content of an Artist, Event, or Recording.
 * It contains the unique content ID, the list of user IDs who contributed to the comments, the list of comments
 * made by the users, and a map of ratings (values) made by users (keys).
 * The user ID list, comments list, and user rating map are changeable, but the content ID is final.
 */
public class Content {
    private final String contentId;
    private Map<String, Map<String, Object>> content;
    private double averageRating;

    public Content(String contentId) {
        this.contentId = contentId;
        this.content = new HashMap<>();
        this.averageRating = getRating();
    }

    /**
     * Returns the Content ID.
     * @return the Content ID.
     */
    public String getContentId() {
        return contentId;
    }

    /**
     * Returns the list of user IDs for this content.
     *
     * @return the list of user IDs.
     */
    public Map<String, Map<String, Object>> getContent() {
        return content;
    }

    public void setContent(Map<String, Map<String, Object>> content) {
        this.content = content;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    /**
     * Get the average rating of the content made by all the users. The average rating is a one decimal
     * number between 0.0 and 10.0.
     * @return the average rating.
     */
    private double getRating() {
        double rating = 0.0;
        for (Map<String, Object> value : content.values()) {

            rating += (double) value.get("rating");
        }
        // Calculating average rating
        double average = rating / content.size();
        return Math.round(average * 10) / 10.0; // rounded to one decimal place.
    }
}
