package Use_case.writer;

public interface WriterDataAccessInterface {
    void addComment(String artistId, String username, double rating, String comment);
    double getAverageRating(String artistId);
}
