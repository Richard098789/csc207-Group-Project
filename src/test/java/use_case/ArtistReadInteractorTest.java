package use_case;

import Use_case.read_from_db.*;
import data_transfer_object.Artist;
import data_transfer_object.Recording;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class ArtistReadInteractorTest {
    private ArtistReadInteractor artistReadInteractor;
    private ReadOutputBoundary mockPresenter;
    private ReadDataAccessInterface mockRepository;
    private ReadSongDataAccessInterface mockReadSongRepository;

    @BeforeEach
    void setUp() {
        // Mock dependencies
        mockRepository = mock(ReadDataAccessInterface.class);
        mockPresenter = mock(ReadOutputBoundary.class);
        mockReadSongRepository = mock(ReadSongDataAccessInterface.class);

        // Initialize the interactor
        artistReadInteractor = new ArtistReadInteractor(mockPresenter, mockRepository, mockReadSongRepository);
    }

    @Test
    void testArtistReadInteractor() {

        // Prepare test of mockRepository
        String documentId = "1";
        String user1 = "richard1";
        String user2 = "nick1";
        String comment1 = "Nice";
        String comment2 = "Cool";
        double rating1 = 10;
        double rating2 = 8;
        Map<String, Object> info1 = new HashMap<>();
        info1.put("comment", comment1);
        info1.put("rating", rating1);
        Map<String, Object> info2 = new HashMap<>();
        info2.put("comment", comment2);
        info2.put("rating", rating2);
        Map<String, Object> Content = new HashMap<>();
        Content.put(user1, info1);
        Content.put(user2, info2);
        Map<String, String> expectedComments = new HashMap<>();
        expectedComments.put(user1, comment1);
        expectedComments.put(user2, comment2);

        when(mockRepository.readContents(documentId)).thenReturn(Content);

        // Prepare test of readSongRepository
        Artist artist = new Artist("artist", "ZL", "dk", 100, "person", false);
        String id1 = "a";
        String title1 = "song1";
        int length1 = 5;
        String id2 = "b";
        String title2 = "song2";
        int length2 = 10;

        Recording recording1 = Recording.builder()
                .id(id1)
                .title(title1)
                .length(length1)
                .build();

        Recording recording2 = Recording.builder()
                .id(id2)
                .title(title2)
                .length(length2)
                .build();

        Recording[] expectedRecordings = {recording1, recording2};
        when(mockReadSongRepository.readTopSongs(documentId)).thenReturn(expectedRecordings);

        // execute
        ReadInputData inputData = new ReadInputData(documentId, artist);
        artistReadInteractor.execute(inputData);

        // Verify
        verify(mockRepository).readContents(documentId);
        verify(mockPresenter).prepareArtistDetailedView(argThat(readOutputData ->
                readOutputData.getArtist().equals(artist) && readOutputData.getRecording().equals(expectedRecordings)
                && readOutputData.getAverageRating() == 9.0 && readOutputData.getComments().equals(expectedComments)));




    }
}
