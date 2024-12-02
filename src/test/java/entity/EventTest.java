package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testEventCreationWithBuilder() {
        // Create an event using the builder
        Event event = Event.builder()
                .id("123e4567-e89b-12d3-a456-426614174000")
                .name("Jazz Festival")
                .type("Concert")
                .beginDate("2023-10-01")
                .endDate("2023-10-02")
                .time("19:00")
                .placeName("Madison Square Garden")
                .placeId("987f6543-b21a-12d3-c456-426614177890")
                .artistName("John Doe Band")
                .artistId("56d12345-a32b-78d9-e101-123456789abc")
                .score(95)
                .build();

        // Verify that the event object was created correctly
        assertEquals("123e4567-e89b-12d3-a456-426614174000", event.getId());
        assertEquals("Jazz Festival", event.getName());
        assertEquals("Concert", event.getType());
        assertEquals("2023-10-01", event.getBeginDate());
        assertEquals("2023-10-02", event.getEndDate());
        assertEquals("19:00", event.getTime());
        assertEquals("Madison Square Garden", event.getPlaceName());
        assertEquals("987f6543-b21a-12d3-c456-426614177890", event.getPlaceId());
        assertEquals("John Doe Band", event.getArtistName());
        assertEquals("56d12345-a32b-78d9-e101-123456789abc", event.getArtistId());
        assertEquals(95, event.getScore());
    }

    @Test
    void testDefaultBuilderValues() {
        // Create an event with minimal fields set
        Event event = Event.builder()
                .name("Minimal Event")
                .build();

        // Verify default values for unset fields
        assertNull(event.getId());
        assertEquals("Minimal Event", event.getName());
        assertNull(event.getType());
        assertNull(event.getBeginDate());
        assertNull(event.getEndDate());
        assertNull(event.getTime());
        assertNull(event.getPlaceName());
        assertNull(event.getPlaceId());
        assertNull(event.getArtistName());
        assertNull(event.getArtistId());
        assertEquals(0, event.getScore());
    }

    @Test
    void testEventWithOnlyMandatoryFields() {
        // Create an event with just the mandatory fields
        Event event = Event.builder()
                .id("123e4567-e89b-12d3-a456-426614174000")
                .name("Mandatory Fields Event")
                .build();

        // Verify that the event is created with only mandatory fields
        assertEquals("123e4567-e89b-12d3-a456-426614174000", event.getId());
        assertEquals("Mandatory Fields Event", event.getName());
        assertNull(event.getType());
        assertNull(event.getBeginDate());
        assertNull(event.getEndDate());
        assertNull(event.getTime());
        assertNull(event.getPlaceName());
        assertNull(event.getPlaceId());
        assertNull(event.getArtistName());
        assertNull(event.getArtistId());
        assertEquals(0, event.getScore());
    }

    @Test
    void testEventWithMissingOptionalFields() {
        // Create an event without optional fields
        Event event = Event.builder()
                .id("abc123")
                .name("Incomplete Event")
                .type("Festival")
                .build();

        // Verify default values for missing optional fields
        assertEquals("abc123", event.getId());
        assertEquals("Incomplete Event", event.getName());
        assertEquals("Festival", event.getType());
        assertNull(event.getBeginDate());
        assertNull(event.getEndDate());
        assertNull(event.getTime());
        assertNull(event.getPlaceName());
        assertNull(event.getPlaceId());
        assertNull(event.getArtistName());
        assertNull(event.getArtistId());
        assertEquals(0, event.getScore());
    }
}
