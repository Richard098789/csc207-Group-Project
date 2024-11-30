package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArtistTest {

    @Test
    void testArtistCreationWithBuilder() {
        // Create an artist using the builder
        Artist artist = Artist.builder()
                .id("e56fd97e-c18f-4e5e-9b4d-f9fc21b4973f") // Add id field
                .artistName("Fred")
                .type("Group")
                .score(100)
                .country("US")
                .isDead(true)
                .build();

        // Verify that the artist object was created correctly
        assertEquals("e56fd97e-c18f-4e5e-9b4d-f9fc21b4973f", artist.getId()); // Test id
        assertEquals("Fred", artist.getArtistName());
        assertEquals("Group", artist.getType());
        assertEquals(100, artist.getScore());
        assertEquals("US", artist.getCountry());
        assertTrue(artist.isDead());
    }

    @Test
    void testDefaultBuilderValues() {
        // Create an artist with minimal fields set
        Artist artist = Artist.builder()
                .artistName("Minimal Artist")
                .build();

        // Verify default values for unset fields
        assertNull(artist.getId());
        assertNull(artist.getCountry());
        assertEquals(0, artist.getScore());
        assertNull(artist.getType());
        assertFalse(artist.isDead());
    }
}
