package entity;

import java.util.ArrayList;
import java.util.List;

public class ArtistDetailModel {
    private final Artist artist;
    private final List<String> comments;
    private final List<Integer> ratings;

    public ArtistDetailModel(Artist artist) {
        this.artist = artist;
        this.comments = new ArrayList<>();
        this.ratings = new ArrayList<>();
    }

    public Artist getArtist() {
        return artist;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    public void addRating(int rating) {
        ratings.add(rating);
    }

    public List<String> getComments() {
        return comments;
    }

    public List<Integer> getRatings() {
        return ratings;
    }

    public double getAverageRating() {
        return ratings.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }
}
