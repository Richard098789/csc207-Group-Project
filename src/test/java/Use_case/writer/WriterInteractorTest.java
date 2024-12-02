package Use_case.writer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class WriterInteractorTest {
    private WriterDataAccessInterface mockDataAccess;
    private WriterOutputBoundary mockPresenter;
    private WriterInteractor writerInteractor;

    @BeforeEach
    void setUp() {
        // Mock dependencies
        mockDataAccess = mock(WriterDataAccessInterface.class);
        mockPresenter = mock(WriterOutputBoundary.class);

        // Initialize interactor
        writerInteractor = new WriterInteractor(mockDataAccess, mockPresenter);
    }

    @Test
    void testExecuteComment_Success() {
        // Prepare test data
        WriterInputData inputData = new WriterInputData("artist123", "user1", "Great song!", 8);

        // Execute use case
        writerInteractor.executeComment(inputData);

        // Verify interactions
        verify(mockDataAccess).addComment("artist123", "user1", 8, "Great song!");
        verify(mockPresenter).prepareSuccessView("Comment successfully added!");
        verifyNoMoreInteractions(mockDataAccess);
        verifyNoMoreInteractions(mockPresenter);
    }

    @Test
    void testExecuteComment_InvalidArtistId() {
        // Prepare test data with invalid artist ID
        WriterInputData inputData = new WriterInputData("", "user1", "Great song!", 8);

        // Execute use case
        writerInteractor.executeComment(inputData);

        // Verify presenter interactions for failure
        verify(mockPresenter).prepareFailView("Invalid input: Artist ID cannot be null or empty.");
        verifyNoInteractions(mockDataAccess);
    }

    @Test
    void testExecuteComment_InvalidUsername() {
        // Prepare test data with invalid username
        WriterInputData inputData = new WriterInputData("artist123", "", "Great song!", 8);

        // Execute use case
        writerInteractor.executeComment(inputData);

        // Verify presenter interactions for failure
        verify(mockPresenter).prepareFailView("Invalid input: Username cannot be null or empty.");
        verifyNoInteractions(mockDataAccess);
    }

    @Test
    void testExecuteComment_InvalidRating() {
        // Prepare test data with invalid rating
        WriterInputData inputData = new WriterInputData("artist123", "user1", "Great song!", 11);

        // Execute use case
        writerInteractor.executeComment(inputData);

        // Verify presenter interactions for failure
        verify(mockPresenter).prepareFailView("Invalid input: Rating must be between 0 and 10.");
        verifyNoInteractions(mockDataAccess);
    }

    @Test
    void testExecuteComment_EmptyComment() {
        // Prepare test data with an empty comment
        WriterInputData inputData = new WriterInputData("artist123", "user1", "", 8);

        // Execute use case
        writerInteractor.executeComment(inputData);

        // Verify presenter interactions for failure
        verify(mockPresenter).prepareFailView("Invalid input: Comment cannot be null or empty.");
        verifyNoInteractions(mockDataAccess);
    }

    @Test
    void testExecuteComment_ExceptionThrownByDataAccess() {
        // Prepare test data
        WriterInputData inputData = new WriterInputData("artist123", "user1", "Great song!", 8);

        // Mock behavior to throw exception
        doThrow(new RuntimeException("Database error")).when(mockDataAccess).addComment(anyString(), anyString(), anyDouble(), anyString());

        // Execute use case
        writerInteractor.executeComment(inputData);

        // Verify presenter interactions for failure
        verify(mockDataAccess).addComment("artist123", "user1", 8, "Great song!");
        verify(mockPresenter).prepareFailView("Failed to add comment: Database error");
    }
}
