package entity.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents the entity.content of an Artist, Event, or Recording.
 * It contains the unique entity.content ID, the list of user IDs who contributed to the comments, the list of comments
 * made by the users, and a map of ratings (values) made by users (keys).
 * The user ID list, comments list, and user rating map are changeable, but the entity.content ID is final.
 */
public class Content implements ContentInterface {

    private final String contentId;
    private Map<String, Map<String, Object>> contentInfo;
    private double averageRating;

    public Content(String contentId) {
        this.contentId = contentId;
        this.contentInfo = new HashMap<>();
        this.averageRating = 0.0;
    }

    @Override
    public String getContentID() {
        return contentId;
    }

    @Override
    public void setContentInfo(Map<String, Map<String, Object>> content) {
        this.contentInfo = content;
        this.averageRating = getRating();
    }

    @Override
    public double getAverageRating() {
        return this.averageRating;
    }

    @Override

    public Map<String, String> getComments() {
        Map<String, String> comment = new HashMap<>();

        for (String key : contentInfo.keySet()) {
            if (contentInfo.get(key).get("comment") != null) {
                comment.put(key, (String) contentInfo.get(key).get("comment"));
            }
        }
        return comment;
    }

    /**
     * Get the average rating of the entity.content made by all the users. The average rating is a one decimal
     * number between 0.0 and 10.0.
     *
     * @return the average rating.
     */
    private double getRating() {
        double rating = 0.0;
        if (this.contentInfo.size() > 0) {
            for (Map<String, Object> value : contentInfo.values()) {
                rating += (double) value.get("rating");
            }

            // Calculating average rating
            final double average = rating / contentInfo.size();
            // Rounded to one decimal place.
            return Math.round(average * 10) / 10.0;
        }
        return rating;

    }
}
