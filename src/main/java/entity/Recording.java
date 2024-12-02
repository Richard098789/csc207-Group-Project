package entity;

import java.util.List;

/**
 * This class represents a recording with details such as artist names,
 * song name, country, release date, and release group type.
 */
public class Recording {

    private final String id;
    private final String title;
    private final int length;

    private Recording(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.length = builder.length;
    }


    private final String id;
    private final String songName;
    private final List<String> artistNames;
    private final String country;
    private final String releaseDate;
    private final String releaseGroupType;

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
     * Returns the song name.
     * @return the song name.
     */
    public String getSongName() {
        return songName;
    }

    /**
     * Returns the list of artist names.
     * @return the list of artist names.
     */
    public List<String> getArtistNames() {
        return artistNames;
    }

    /**
     * Returns the country of the recording release.
     * @return the country of the recording release.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Returns the release date of the recording.
     * @return the release date of the recording.
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * Returns the release group type (e.g., "Album", "Single").
     * @return the release group type.
     */
    public String getReleaseGroupType() {
        return releaseGroupType;
    }

    /**
     * Returns a new RecordingBuilder instance.
     * @return a new RecordingBuilder instance.
     */
    public static RecordingBuilder builder() {
        return new RecordingBuilder();
    }

    /**
     * Represents a builder for creating instances of a Recording.
     */
    public static class RecordingBuilder {
        private String id;
        private String songName;
        private List<String> artistNames;
        private String country;
        private String releaseDate;
        private String releaseGroupType;

        RecordingBuilder() {}

        /**
         * Sets the ID of the recording.
         * @param idInput the ID of the recording.
         * @return the RecordingBuilder instance.
         */
        public RecordingBuilder id(String idInput) {
            this.id = idInput;
            return this;
        }

        /**
         * Sets the song name.
         * @param songNameInput the song name.
         * @return the RecordingBuilder instance.
         */
        public RecordingBuilder songName(String songNameInput) {
            this.songName = songNameInput;
            return this;
        }

        /**
         * Sets the list of artist names.
         * @param artistNamesInput the list of artist names.
         * @return the RecordingBuilder instance.
         */
        public RecordingBuilder artistNames(List<String> artistNamesInput) {
            this.artistNames = artistNamesInput;
            return this;
        }

        /**
         * Sets the country of the recording release.
         * @param countryInput the country.
         * @return the RecordingBuilder instance.
         */
        public RecordingBuilder country(String countryInput) {
            this.country = countryInput;
            return this;
        }

        /**
         * Sets the release date of the recording.
         * @param releaseDateInput the release date.
         * @return the RecordingBuilder instance.
         */
        public RecordingBuilder releaseDate(String releaseDateInput) {
            this.releaseDate = releaseDateInput;
            return this;
        }

        /**
         * Sets the release group type.
         * @param releaseGroupTypeInput the release group type.
         * @return the RecordingBuilder instance.
         */
        public RecordingBuilder releaseGroupType(String releaseGroupTypeInput) {
            this.releaseGroupType = releaseGroupTypeInput;
            return this;
        }

        /**
         * Builds a new Recording instance.
         * @return a new Recording instance.
         */
        public Recording build() {
            return new Recording(id, songName, artistNames, country, releaseDate, releaseGroupType);

        }
    }
}
