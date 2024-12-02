package Use_case.signup;

/**
 * Signup output boundary.
 */
public interface SignupOutputBoundary {

    /**
     * Prepares the success view for the Signup Use Case.
     */
    void prepareSuccessView();

    /**
     * Prepares the failure view for the Signup Use Case.
     */
    void prepareFailView();

    /**
     * Prepare Unmatch Password View.
     */
    void prepareUnmatchPasswordView();

    /**
     * Switches to the Login View.
     */
    void switchToLoginView();
}
