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
     * Set content information.
     * @param content the content.
     */
    void setContentInfo(Map<String, Map<String, Object>> content);

    /**
     * Get average rating.
     * @return the average rating
     */
    double getAverageRating();
  
    /**
     * Get the list of comments.
     * @return the list of comments
     */
    Map<String, String> getComments();

}
