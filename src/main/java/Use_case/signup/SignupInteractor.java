package Use_case.signup;

/**
 * Signup interactor.
 */
public class SignupInteractor implements SignupInputBoundary {

    private final SignupDataAccessInterface userDataAccessObject;
    private final SignupOutputBoundary userPresenter;

    public SignupInteractor(SignupDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary userPresenter) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = userPresenter;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        String username = signupInputData.getUsername();
        String password = signupInputData.getPassword();
        String confirmPassword = signupInputData.getRepeatPassword();

        if (!password.equals(confirmPassword)) {
            userPresenter.prepareUnmatchPasswordView();
            return; // Avoid further execution
        }

        if (userDataAccessObject.userExists(username)) {
            userPresenter.prepareFailView();
        } else {
            userDataAccessObject.addUser(username, password);
            userPresenter.prepareSuccessView();
        }
    }

    @Override
    public void switchToLoginView() {

        userPresenter.switchToLoginView();

    }
}
