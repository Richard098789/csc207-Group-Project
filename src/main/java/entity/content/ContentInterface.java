package entity.content;

import java.util.List;
import java.util.Map;

public interface ContentInterface {

    /**
     * Returns the Content ID.
     * @return the Content ID.
     */
    String getContentID();

    void setContentInfo(Map<String, Map<String, Object>> content);

    double getAverageRating();

    Map<String, String> getComments();
}
