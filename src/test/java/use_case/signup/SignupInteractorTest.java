package use_case.signup;

import Use_case.signup.SignupDataAccessInterface;
import Use_case.signup.SignupInputData;
import Use_case.signup.SignupInteractor;
import Use_case.signup.SignupOutputBoundary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class SignupInteractorTest {

    private SignupDataAccessInterface mockDataAccess;
    private SignupOutputBoundary mockPresenter;
    private SignupInteractor interactor;

    @BeforeEach
    void setUp() {
        // Mock dependencies
        mockDataAccess = mock(SignupDataAccessInterface.class);
        mockPresenter = mock(SignupOutputBoundary.class);

        // Create interactor
        interactor = new SignupInteractor(mockDataAccess, mockPresenter);
    }

    @Test
    void testSuccessfulSignup() {
        // Prepare test data
        String username = "JohnDoe";
        String password = "securepassword";

        // Mock behavior
        when(mockDataAccess.userExists(username)).thenReturn(false);

        // Execute use case
        SignupInputData inputData = new SignupInputData(username, password, password);
        interactor.execute(inputData);

        // Verify interactions
        verify(mockDataAccess).userExists(username);
        verify(mockDataAccess).addUser(username, password);
        verify(mockPresenter).prepareSuccessView();
        verifyNoMoreInteractions(mockPresenter);
    }

    @Test
    void testSignupWithExistingUser() {
        // Prepare test data
        String username = "JaneDoe";
        String password = "securepassword";

        // Mock behavior: Simulate that the user already exists
        when(mockDataAccess.userExists(username)).thenReturn(true);

        // Execute use case
        SignupInputData inputData = new SignupInputData(username, password, password);
        interactor.execute(inputData);

        // Verify that userExists was called
        verify(mockDataAccess).userExists(username);

        // Verify that prepareFailView was called
        verify(mockPresenter).prepareFailView();

        // Ensure no other interactions occurred
        verifyNoMoreInteractions(mockDataAccess);
        verifyNoMoreInteractions(mockPresenter);
    }

    @Test
    void testSignupWithPasswordMismatch() {
        // Prepare test data
        String username = "JohnDoe";
        String password = "securepassword";
        String confirmPassword = "differentpassword";

        // Execute use case
        SignupInputData inputData = new SignupInputData(username, password, confirmPassword);
        interactor.execute(inputData);

        // Verify interactions
        verifyNoInteractions(mockDataAccess);
        verify(mockPresenter).prepareUnmatchPasswordView();
        verifyNoMoreInteractions(mockPresenter);
    }

    @Test
    void testSwitchToLoginView() {
        // Execute use case
        interactor.switchToLoginView();

        // Verify interactions
        verify(mockPresenter).switchToLoginView();
        verifyNoMoreInteractions(mockPresenter);
    }
}
