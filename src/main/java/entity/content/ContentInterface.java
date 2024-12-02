package entity.content;

import java.util.Map;

/**
 * The interface for content.
 */
public interface ContentInterface {

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
     * @return the of comments
     */
    Map<String, String> getComments();

}
