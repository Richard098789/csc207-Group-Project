package entity.content;

import java.util.Map;

public interface ContentInterface {


    /**
     * Returns the Content ID.
     * @return the Content ID.
     */
    String getContentID();

    Map<String, Map<String, Object>> getContentInfo();

    void setContentInfo(Map<String, Map<String, Object>> content);

    /**
     * Sets the average rating of the entity.content directly.
     */
    void setAverageRating();

    double getAverageRating();
}
