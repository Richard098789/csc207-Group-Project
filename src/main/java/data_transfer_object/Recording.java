package data_transfer_object;

import java.util.List;

/**
 * This class represents a recording with details such as artist names,
 * song name, country, release date, and release group type.
 */
public class Recording {

    private final String id;
    private  String title;
    private  int length;

    private Recording(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.length = builder.length;
    }


    private  String songName;
    private  List<String> artistNames;
    private  String country;
    private  String releaseDate;
    private  String releaseGroupType;

    public Recording(String id, String songName, List<String> artistNames, String country, String releaseDate, String releaseGroupType) {
        this.id = id;
        this.songName = songName;
        this.artistNames = artistNames;
        this.country = country;
        this.releaseDate = releaseDate;
        this.releaseGroupType = releaseGroupType;
    }

    /**
     * Returns the recording ID.
     * @return the recording ID.
     */

    public String getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public String getFormattedLength() {
        if (length <= 0) {
            return "Unknown";
        }
        int minutes = length / 60000;
        int seconds = (length % 60000) / 1000;
        return String.format("%d:%02d", minutes, seconds);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String title;
        private int length;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder length(int length) {
            this.length = length;
            return this;
        }

        public Recording build() {
            return new Recording(this);

            /**
             * Represents a builder for creating instances of a Recording.
             */
        }
    }
}
