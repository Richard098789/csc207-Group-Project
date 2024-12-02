package Use_case.login;

/**
 * Login output boundary.
 */
public interface LoginOutputBoundary {

    /**
     * Prepare main menu view.
     */
    void prepareMainMenuView();

    /**
     * Prepare fail view.
     */
    void prepareFailView();

    /**
     * Go to signup view.
     */
    void goSignupView();
}
