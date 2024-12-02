package Use_case.read_from_db;

import data_transfer_object.Artist;
import data_transfer_object.Recording;

import java.util.List;
import java.util.Map;

public class ReadOutputData {

    private List<Map<String, String>> comments;
    private double averageRating;
    private Artist artist;

    private Recording[] recording;

    public ReadOutputData(Recording[] topSongs, List<Map<String, String>> comments, double averageRating, Artist artist) {
        this.comments = comments;
        this.averageRating = averageRating;
        this.artist = artist;
        this.recording = topSongs;
    }

    public List<Map<String, String>> getComments() {
        return comments;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public Recording[] getRecording() {
        return recording;
    }

    public Artist getArtist() {
        return artist;
    }
}
