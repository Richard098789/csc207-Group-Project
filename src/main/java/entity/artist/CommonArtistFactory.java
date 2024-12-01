package entity.artist;

import java.util.UUID;

/**
 * Factory for creating CommonArtist objects.
 */
public class CommonArtistFactory implements ArtistFactory {

    @Override
    public artist create(String id, String name, String country, int score, String type, boolean isDead) {
        if (id == null || id.trim().isEmpty()) {
            // Generate a default unique ID if none is provided
            id = UUID.randomUUID().toString();
        }
        return new CommonArtist(id, name, country, score, type, isDead);
    }
}
