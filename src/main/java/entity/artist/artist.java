package entity.artist;

/**
 * The representation of an artist in the program.
 */
public interface artist {
    /**
     * Returns the ID of the artist.
     * @return the ID of the artist.
     */
    String getId();

    /**
     * Returns the name of the artist.
     * @return the name of the artist.
     */
    String getArtistName();

    /**
     * Returns the country of the artist.
     * @return the country of the artist.
     */
    String getCountry();

    /**
     * Returns the score of the artist.
     * @return the score of the artist.
     */
    int getScore();

    /**
     * Returns the type of the artist (e.g., person, group).
     * @return the type of the artist.
     */
    String getType();

    /**
     * Returns whether the artist is dead.
     * @return true if the artist is dead, false otherwise.
     */
    boolean isDead();
}
