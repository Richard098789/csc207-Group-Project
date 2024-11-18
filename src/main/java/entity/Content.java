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
    private List<String> userIds;
    private List<String> comments;
    private Map<String, Double> userRatings;

    public Content() {
        this.contentId = UUID.randomUUID().toString();
        this.userIds = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.userRatings = new HashMap<>();
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
     * @return the list of user IDs.
     */
    public List<String> getUserIds() {
        return userIds;
    }

    /**
     * Returns the list of comments for this content.
     * @return the list of comments.
     */
    public List<String> getComments() {
        return comments;
    }

    /**
     * Returns the map with user ID as the key and their ratings as the values.
     * @return the map with user ID as the key and their ratings as the values.
     */
    public Map<String, Double> getUserRatings() {
        return userRatings;
    }

    /**
     * Add or update the user rating.
     * @param userId the user ID to add or update the rating.
     * @param rating the rating.
     */
    public void addOrUpdateUserRating(String userId, double rating) {
        if (rating < 0.0 || rating > 10.0) {
            throw new IllegalArgumentException("rating must be between 0.0 and 10.0");
        }
        // round to one decimal place and add or update the rating
        this.userRatings.put(userId, Math.round(rating * 10) / 10.0);
    }

    /**
     * Returns number of ratings for this content.
     * @return the number of ratings.
     */
    public int getRatingCount() {
        return userRatings.size();
    }

    /**
     * Remove a user's rating.
     * @param userId the user ID of the rating to be removed.
     */
    public void removeUserRating(String userId) {
        this.userRatings.remove(userId);
    }

    /**
     * Get the average rating of the content made by all the users. The average rating is a one decimal
     * number between 0.0 and 10.0.
     * @return the average rating.
     */
    public double getRating() {
        if (this.userRatings.isEmpty()) {
            return 0.0; // no ratings yet.
        }
        // Calculating average rating
        double sum = 0.0;
        for (double rating : this.userRatings.values()) {
            sum += rating;
        }
        double average = sum / this.getRatingCount();
        return Math.round(average * 10) / 10.0; // rounded to one decimal place.
    }

    /**
     * Adds a comment to the list of comments.
     * @param comment the comment to be added.
     */
    public void addComment(String comment) {
        if (comment != null && !comment.isEmpty()) {
            this.comments.add(comment);
        } else {
            throw new IllegalArgumentException("comment cannot be empty");
        }
    }

    /**
     * Remove a comment from the list of comments.
     * @param comment the comment to be removed.
     */
    public void removeComment(String comment) {
        this.comments.remove(comment);
    }

    /**
     * Adds a user ID to the list of user IDs.
     * @param userId the user ID to be added.
     */
    public void addUserId(String userId) {
        this.userIds.add(userId);
    }

    /**
     * Remove a user ID from the list of user IDs.
     * @param userId the user ID to be removed.
     */
    public void removeUserId(String userId) {
        this.userIds.remove(userId);
    }

}
