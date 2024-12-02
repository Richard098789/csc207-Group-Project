package entity.content;

import java.util.List;
import java.util.Map;

/**
 * The interface for content.
 */
public interface ContentInterface {

    /**
     * Returns the Content ID.
     * @return the Content ID.
     */
    String getContentID();

    /**
     * Get the content information.
     * @return a map containing content information
     */
    Map<String, Map<String, Object>> getContentInfo();

    /**
     * Set content information.
     * @param content the content.
     */
    void setContentInfo(Map<String, Map<String, Object>> content);

    /**
     * Sets the average rating of the entity.content directly.
     */
    void setAverageRating();

    /**
     * Get average rating.
     * @return the average rating
     */
    double getAverageRating();

    /**
     * Get the list of comments.
     * @return the list of comments
     */
    List<Map<String, String>> getComments();
}
