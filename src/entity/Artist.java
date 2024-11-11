package entity;

public class Artist {

    private final String artistName;
    private final String country; // two-letter country code.
    private final int score;
    private final String type; // person or group.
    private final boolean isDead;

    public Artist(String name, String country, int score, String type, boolean isDead) {
        this.artistName = name;
        this.country = country;
        this.score = score;
        this.type = type;
        this.isDead = isDead;
    }

    public boolean isDead() {
        return isDead;
    }

    public String getType() {
        return type;
    }

    /**
     * Returns the name of the artist.
     * @return the name of the artist.
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     * Returns the country of the artist.
     * @return the country of the artist.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Returns the grade of the artist.
     * @return the grade of the artist.
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns a new ArtistBuilder instance.
     * @return a new ArtistBuilder instance.
     */
    public static ArtistBuilder builder() {
        return new ArtistBuilder();
    }

    /**
     * Represents a builder for creating instances of a Grade.
     */
    public static class ArtistBuilder {
        private String artistName;
        private String country; //
        private int score;
        private String type;
        private boolean isDead;

        ArtistBuilder() {
        }

        /**
         * Sets the artistName of the artist.
         * @param artistNameInput the name of the artist.
         * @return the ArtistBuilder instance.
         */
        public ArtistBuilder artistName(String artistNameInput) {
            this.artistName = artistNameInput;
            return this;
        }

        /**
         * Sets the country of the artist.
         * @param countryInput the country of the artist.
         * @return the ArtistBuilder instance.
         */
        public ArtistBuilder country(String countryInput) {
            this.country = countryInput;
            return this;
        }

        /**
         * Sets the artistName of the artist.
         * @param scoreInput the name of the artist.
         * @return the ArtistBuilder instance.
         */
        public ArtistBuilder score(int scoreInput) {
            this.score = scoreInput;
            return this;
        }


        /**
         * Sets the artistName of the artist.
         * @param typeInput the name of the artist.
         * @return the ArtistBuilder instance.
         */
        public ArtistBuilder type(String typeInput) {
            this.type = typeInput;
            return this;
        }

        /**
         * Sets the artistName of the artist.
         * @param isDeadInput the name of the artist.
         * @return the ArtistBuilder instance.
         */
        public ArtistBuilder isDead(boolean isDeadInput) {
            this.isDead = isDeadInput;
            return this;
        }

        /**
         * Builds a new Grade instance.
         * @return a new Grade instance.
         */
        public Artist build() {
            return new Artist(artistName, country, score, type, isDead );
        }
    }
}
