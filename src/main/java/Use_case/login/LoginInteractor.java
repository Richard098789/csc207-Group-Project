package Use_case.login;

import global_storage.CurrentUser;

/**
 * Login interactor.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final LoginDataAccessInterface loginDataAccessObject;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginDataAccessInterface loginDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.loginDataAccessObject = loginDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String username = loginInputData.getUsername();
        final String password = loginInputData.getPassword();
        if (loginDataAccessObject.validateLogin(username, password)) {
            // Set the current user after successful login
            CurrentUser.username = username;
            loginPresenter.prepareMainMenuView();
        }

        else {
            loginPresenter.prepareFailView();
        }
    }

    @Override
    public void goSignupView() {
        loginPresenter.goSignupView();
    }

}
