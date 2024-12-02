package entity.artist;

/**
 * Factory for creating Artist objects.
 */
public interface ArtistFactory {
    /**
     * Creates a new Artist.
     * @param id the ID of the artist
     * @param name the name of the artist
     * @param country the country of the artist
     * @param score the score of the artist
     * @param type the type of the artist (person, group)
     * @param isDead whether the artist is dead
     * @return the new artist
     */
    artist create(String id, String name, String country, int score, String type, boolean isDead);
}
