package use_case.artist_search;

import Use_case.artist_search.ArtistSearchDataAccessInterface;
import Use_case.artist_search.ArtistSearchInputData;
import Use_case.artist_search.ArtistSearchInteractor;
import Use_case.artist_search.ArtistSearchOutputBoundary;
import data_transfer_object.Artist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ArtistSearchInteractorTest {

    private ArtistSearchDataAccessInterface mockRepository;
    private ArtistSearchOutputBoundary mockPresenter;
    private ArtistSearchInteractor interactor;

    @BeforeEach
    void setUp() {
        // Mock dependencies
        mockRepository = mock(ArtistSearchDataAccessInterface.class);
        mockPresenter = mock(ArtistSearchOutputBoundary.class);

        // Initialize the interactor
        interactor = new ArtistSearchInteractor(mockRepository, mockPresenter);
    }



    @Test
    void testValidArtistSearch() {
        // Prepare test data
        String artistName = "Beatles";
        String country = "UK";
        int limit = 10;
        int offset = 0;

        Artist[] expectedArtists = {
                new Artist("1", "Beatles", "UK", 95, "group", false),
                new Artist("2", "John Lennon", "UK", 85, "person", true)
        };

        // Mock behavior
        when(mockRepository.getArtists(artistName, country, limit, offset)).thenReturn(expectedArtists);

        // Execute use case
        ArtistSearchInputData inputData = new ArtistSearchInputData(artistName, country, limit, offset);
        interactor.execute(inputData);

        // Verify repository interaction
        verify(mockRepository).getArtists(artistName, country, limit, offset);

        // Verify presenter interaction
        verify(mockPresenter).presentResults(argThat(outputData ->
                outputData.getArtists().length == expectedArtists.length &&
                        outputData.getArtists()[0].getArtistName().equals("Beatles") &&
                        outputData.getArtists()[0].getScore() == 95
        ));
    }

    @Test
    void testEmptyArtistSearchResults() {
        // Prepare test data
        String artistName = "NonExistentArtist";
        String country = "US";
        int limit = 10;
        int offset = 0;

        Artist[] expectedArtists = new Artist[0];

        // Mock behavior
        when(mockRepository.getArtists(artistName, country, limit, offset)).thenReturn(expectedArtists);

        // Execute use case
        ArtistSearchInputData inputData = new ArtistSearchInputData(artistName, country, limit, offset);
        interactor.execute(inputData);

        // Verify repository interaction
        verify(mockRepository).getArtists(artistName, country, limit, offset);

        // Verify presenter interaction
        verify(mockPresenter).presentResults(argThat(outputData ->
                outputData.getArtists().length == 0
        ));
    }

    @Test
    void testRepositoryInteractionOnly() {
        // Prepare test data
        String artistName = "Queen";
        String country = "UK";
        int limit = 5;
        int offset = 2;

        Artist[] expectedArtists = {
                new Artist("3", "Queen", "UK", 90, "group", false)
        };

        // Mock behavior
        when(mockRepository.getArtists(artistName, country, limit, offset)).thenReturn(expectedArtists);

        // Execute use case
        ArtistSearchInputData inputData = new ArtistSearchInputData(artistName, country, limit, offset);
        interactor.execute(inputData);

        // Verify repository interaction
        verify(mockRepository).getArtists(artistName, country, limit, offset);

        // Verify no extra interactions
        verifyNoMoreInteractions(mockRepository);
    }
}
