package Use_case.signup;

public interface SignupOutputBoundary {

    /**
     * Prepares the success view for the Signup Use Case.
     */
    void prepareSuccessView();

    /**
     * Prepares the failure view for the Signup Use Case.
     */
    void prepareFailView();

    void prepareUnmatchPasswordView();

    /**
     * Switches to the Login View.
     */
    void switchToLoginView();
}
