package entity;

import java.util.UUID;

public class Artist {

    private final String artistName;
    private final String country; // two-letter country code.
    private final int score;
    private final String type; // person or group.
    private final boolean isDead;
    private final String id;

    public Artist(String id, String name, String country, int score, String type, boolean isDead) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Artist ID cannot be null or empty.");
        }
        this.id = id;
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

    public String getArtistName() {
        return artistName;
    }

    public String getCountry() {
        return country;
    }

    public int getScore() {
        return score;
    }

    public String getId() {
        return id;
    }

    public static ArtistBuilder builder() {
        return new ArtistBuilder();
    }

    public static class ArtistBuilder {
        private String artistName;
        private String country;
        private int score;
        private String type;
        private boolean isDead;
        private String id;

        ArtistBuilder() {
            // Generate a default unique ID
            this.id = UUID.randomUUID().toString();
        }

        public ArtistBuilder artistName(String artistNameInput) {
            this.artistName = artistNameInput;
            return this;
        }

        public ArtistBuilder country(String countryInput) {
            this.country = countryInput;
            return this;
        }

        public ArtistBuilder score(int scoreInput) {
            this.score = scoreInput;
            return this;
        }

        public ArtistBuilder type(String typeInput) {
            this.type = typeInput;
            return this;
        }

        public ArtistBuilder isDead(boolean isDeadInput) {
            this.isDead = isDeadInput;
            return this;
        }

        public ArtistBuilder id(String idInput) {
            if (idInput != null && !idInput.trim().isEmpty()) {
                this.id = idInput;
            }
            return this;
        }

        public Artist build() {
            return new Artist(id, artistName, country, score, type, isDead);
        }
    }
}
