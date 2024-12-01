package Use_case.signup;

public class SignupInteractor implements SignupInputBoundary{

    private final SignupDataAccessInterface userDataAccessObject;
    private final SignupOutputBoundary userPresenter;

    public SignupInteractor(SignupDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary userPresenter) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = userPresenter;
    }

    @Override
    public void execute(SignupInputData signupInputData){
        String username = signupInputData.getUsername();
        String password = signupInputData.getPassword();
        String confirmPassword = signupInputData.getRepeatPassword();

        if (userDataAccessObject.userExists(username)) {
            userPresenter.prepareFailView("User already exists.");

        }
        else if (!password.equals(confirmPassword)) {
            userPresenter.prepareUnmatchPasswordView("Passwords don't match.");
        }

        else {
            userDataAccessObject.addUser(username, password);
            userPresenter.prepareSuccessView("Account created successfully!");
        }
    }

    @Override
    public void switchToLoginView() {

        userPresenter.switchToLoginView();

    }
}
