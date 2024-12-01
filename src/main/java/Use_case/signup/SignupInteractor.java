package Use_case.signup;

public class SignupInteractor implements SignupInputBoundary{

    private final SignupDataAccessInterface userDataAccessObject;
    private final SignupOutputBoundary userPresenter;

    public SignupInteractor(SignupDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
    }

    @Override
    public void execute(SignupInputData signupInputData){
        String username = signupInputData.getUsername();
        String password = signupInputData.getPassword();
        String confirmPassword = signupInputData.getRepeatPassword();

        if (userDataAccessObject.userExists(username)) {
            userPresenter.prepareFailView();

        }
        else if (!password.equals(confirmPassword)) {
            userPresenter.prepareUnmatchPasswordView();
        }

        else {
            userDataAccessObject.addUser(username, password);
            userPresenter.prepareSuccessView();
        }
    }

    @Override
    public void switchToLoginView() {

        userPresenter.switchToLoginView();

    }
}
