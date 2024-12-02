package Use_case.writer;

public class WriterInputData {
    private final String artistId;
    private final String username;
    private final String comment;
    private final double rating;

    public WriterInputData(String artistId, String username, String comment, double rating) {
        this.artistId = artistId;
        this.username = username;
        this.comment = comment;
        this.rating = rating;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getUsername() {
        return username;
    }

    public String getComment() {
        return comment;
    }

    public double getRating() {
        return rating;
    }
}
