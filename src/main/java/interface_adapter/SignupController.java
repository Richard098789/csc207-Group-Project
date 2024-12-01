package interface_adapter;

import Use_case.signup.SignupInputBoundary;
import Use_case.signup.SignupInputData;

public class SignupController {

    private final SignupInputBoundary signupInteractor;

    public SignupController(SignupInputBoundary signupInteractor) {
        this.signupInteractor = signupInteractor;
    }

    /**
     * Executes the Signup Use Case.
     * @param username the username to sign up
     * @param password1 the password
     * @param password2 the password repeated
     */
    public void execute(String username, String password1, String password2) {
        final SignupInputData signupInputData = new SignupInputData(
                username, password1, password2);

        signupInteractor.execute(signupInputData);
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToLoginView() {
        signupInteractor.switchToLoginView();
    }
}
