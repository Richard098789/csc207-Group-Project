package Use_case.signup;

public interface SignupOutputBoundary {

    /**
     * Prepares the success view for the Signup Use Case.
     * @param successMessage the output data
     */
    void prepareSuccessView(String successMessage);

    /**
     * Prepares the failure view for the Signup Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    void prepareUnmatchPasswordView(String unmatchPasswordMessage);

    /**
     * Switches to the Login View.
     */
    void switchToLoginView();
}
