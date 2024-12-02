package Use_case.writer;

import entity.artist.artist;
import entity.artist.ArtistFactory;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class WriterInteractorTest {

    @Test
    void testExecuteComment() {
        // Mock dependencies
        ArtistFactory mockFactory = mock(ArtistFactory.class);
        WriterDataAccessInterface mockDataAccess = mock(WriterDataAccessInterface.class);
        WriterOutputBoundary mockPresenter = mock(WriterOutputBoundary.class);

        // Mock Artist creation
        artist mockArtist = mock(artist.class);
        when(mockFactory.create("123", "ArtistName", "US", 100, "Group", false)).thenReturn(mockArtist);

        // Initialize interactor with mocks
        WriterInteractor interactor = new WriterInteractor(mockDataAccess, mockPresenter);

        // Input data for the test
        WriterInputData inputData = new WriterInputData("123", "JohnDoe", "Amazing!", 5.0);

        // Execute the use case
        interactor.executeComment(inputData);

        // Verify interactions
        verify(mockDataAccess).addComment("123", "JohnDoe", 5.0, "Amazing!");
        verify(mockPresenter).prepareSuccessView("Comment successfully added!");
    }
}
