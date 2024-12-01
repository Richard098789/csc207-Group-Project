package Use_case.signup;

public interface SignupInputBoundary {

    /**
     * Executes the interface_adapter.signup use case.
     * @param signupInputData the input data
     */
    void execute(SignupInputData signupInputData);

    /**
     * Executes the switch to login view use case.
     */
    void switchToLoginView();
}
