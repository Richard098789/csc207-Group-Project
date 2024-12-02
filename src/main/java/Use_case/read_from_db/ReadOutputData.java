package Use_case.read_from_db;

import java.util.List;
import java.util.Map;

import data_transfer_object.Artist;
import data_transfer_object.Recording;

/**
 * Read output data.
 */
public class ReadOutputData {

    private Map<String, String> comments;
    private double averageRating;
    private Artist artist;

    private Recording[] recording;

    public ReadOutputData(Recording[] topSongs, Map<String, String> comments, double averageRating, Artist artist) {

        this.comments = comments;
        this.averageRating = averageRating;
        this.artist = artist;
        this.recording = topSongs;
    }

    public Map<String, String> getComments() {
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
