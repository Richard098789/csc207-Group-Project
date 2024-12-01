package entity.artist;

/**
 * A simple implementation of the Artist interface.
 */
public class CommonArtist implements artist {
    private final String id;
    private final String artistName;
    private final String country;
    private final int score;
    private final String type;
    private final boolean isDead;

    public CommonArtist(String id, String artistName, String country, int score, String type, boolean isDead) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Artist ID cannot be null or empty.");
        }
        this.id = id;
        this.artistName = artistName;
        this.country = country;
        this.score = score;
        this.type = type;
        this.isDead = isDead;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getArtistName() {
        return artistName;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public boolean isDead() {
        return isDead;
    }
}
