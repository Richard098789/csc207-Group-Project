package entity;

import data_transfer_object.Recording;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecordingTest {

    @Test
    void testRecordingCreationWithBuilder() {
        // Create a recording using the builder
        List<String> artistNames = Arrays.asList("Dua Lipa");
        Recording recording = Recording.builder()
                .id("026fa041-3917-4c73-9079-ed16e36f20f8")
                .songName("Blow Your Mind (Mwah)")
                .artistNames(artistNames)
                .country("XW")
                .releaseDate("2016-08-26")
                .releaseGroupType("Single")
                .build();

        // Verify that the recording object was created correctly
        assertEquals("026fa041-3917-4c73-9079-ed16e36f20f8", recording.getId());
        assertEquals("Blow Your Mind (Mwah)", recording.getSongName());
        assertEquals(artistNames, recording.getArtistNames());
        assertEquals("XW", recording.getCountry());
        assertEquals("2016-08-26", recording.getReleaseDate());
        assertEquals("Single", recording.getReleaseGroupType());
    }

    @Test
    void testRecordingWithMultipleArtists() {
        // Create a recording with multiple artists
        List<String> artistNames = Arrays.asList("Artist A", "Artist B");
        Recording recording = Recording.builder()
                .id("12345678-abcd-1234-efgh-56789ijklmno")
                .songName("Collaboration Song")
                .artistNames(artistNames)
                .country("US")
                .releaseDate("2022-01-01")
                .releaseGroupType("Album")
                .build();

        // Verify multiple artists are handled correctly
        assertEquals(artistNames, recording.getArtistNames());
        assertTrue(recording.getArtistNames().contains("Artist A"));
        assertTrue(recording.getArtistNames().contains("Artist B"));
    }
}
