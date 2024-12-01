package Use_case.writer;

import java.util.Map;

public interface WriterDataAccessInterface {
    void addComment(String artistId, String username, double rating, String comment);
    double getAverageRating(String artistId);
    Map<String, Object> getComments(String artistId); // Optional: Retrieve all comments for an artist
}
