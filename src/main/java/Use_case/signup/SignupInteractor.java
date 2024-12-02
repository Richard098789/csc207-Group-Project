package Use_case.signup;

public class SignupInteractor implements SignupInputBoundary {

    private final SignupDataAccessInterface userDataAccessObject;
    private final SignupOutputBoundary userPresenter;

    public SignupInteractor(SignupDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        String username = signupInputData.getUsername();
        String password = signupInputData.getPassword();
        String confirmPassword = signupInputData.getRepeatPassword();

        // First, check if passwords match
        if (!password.equals(confirmPassword)) {
            userPresenter.prepareUnmatchPasswordView();
            return; // Exit early to prevent unnecessary database query
        }

        // Next, check if the username already exists
        if (userDataAccessObject.userExists(username)) {
            userPresenter.prepareFailView();
            return;
        }

        // If all checks pass, add the new user
        userDataAccessObject.addUser(username, password);
        userPresenter.prepareSuccessView();
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }
}
