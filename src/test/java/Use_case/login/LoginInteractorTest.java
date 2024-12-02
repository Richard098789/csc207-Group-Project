package Use_case.login;

import global_storage.CurrentUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class LoginInteractorTest {

    private LoginDataAccessInterface mockDataAccess;
    private LoginOutputBoundary mockPresenter;
    private LoginInteractor interactor;

    @BeforeEach
    void setUp() {
        // Mock dependencies
        mockDataAccess = mock(LoginDataAccessInterface.class);
        mockPresenter = mock(LoginOutputBoundary.class);

        // Create interactor
        interactor = new LoginInteractor(mockDataAccess, mockPresenter);
    }

    @Test
    void testSuccessfulLogin() {
        // Prepare test data
        String username = "JohnDoe";
        String password = "securepassword";

        // Mock behavior
        when(mockDataAccess.validateLogin(username, password)).thenReturn(true);

        // Execute use case
        LoginInputData inputData = new LoginInputData(username, password);
        interactor.execute(inputData);

        // Verify presenter interactions
        verify(mockDataAccess).validateLogin(username, password);
        verify(mockPresenter).prepareMainMenuView();
        verifyNoMoreInteractions(mockPresenter);

        // Verify global state
        assertEquals(username, CurrentUser.username);
    }

    @Test
    void testFailedLogin() {
        // Prepare test data
        String username = "JohnDoe";
        String password = "wrongpassword";

        // Mock behavior
        when(mockDataAccess.validateLogin(username, password)).thenReturn(false);

        // Execute use case
        LoginInputData inputData = new LoginInputData(username, password);
        interactor.execute(inputData);

        // Verify presenter interactions
        verify(mockDataAccess).validateLogin(username, password);
        verify(mockPresenter).prepareFailView();
        verifyNoMoreInteractions(mockPresenter);

        // Verify global state
        assertEquals("", CurrentUser.username);
    }

    @Test
    void testGoSignupView() {
        // Execute use case
        interactor.goSignupView();

        // Verify presenter interactions
        verify(mockPresenter).goSignupView();
        verifyNoMoreInteractions(mockPresenter);
    }
}
