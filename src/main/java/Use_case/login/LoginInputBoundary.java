package Use_case.login;

/**
 * Login input boundary.
 */
public interface LoginInputBoundary {

    /**
     * Execute user command.
     * @param loginInputData login input data
     */
    void execute(LoginInputData loginInputData);

    /**
     * Go to signup view.
     */
    void goSignupView();
}
