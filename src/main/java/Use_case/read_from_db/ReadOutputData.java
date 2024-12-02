package Use_case.read_from_db;

import java.util.List;
import java.util.Map;

public class ReadOutputData {

    private List<Map<String, String>> comments;
    private double averageRating;

    public ReadOutputData(List<Map<String, String>> comments, double averageRating) {
        this.comments = comments;
        this.averageRating = averageRating;
    }

    public List<Map<String, String>> getComments() {
        return comments;
    }

    public double getAverageRating() {
        return averageRating;
    }
}
