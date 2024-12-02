package app;

import Use_case.login.LoginDataAccessInterface;
import Use_case.login.LoginInputBoundary;
import Use_case.login.LoginInteractor;
import Use_case.login.LoginOutputBoundary;
import Use_case.signup.SignupDataAccessInterface;
import Use_case.signup.SignupInputBoundary;
import Use_case.signup.SignupInteractor;
import Use_case.signup.SignupOutputBoundary;
import data_access.DBUserAccessObject;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import view.LoginView;
import view.MainMenuView;
import view.SignupView;

/**
 * Class for coordinating the app.
 */

public class AppCoordinator {

    /**
     * Create the login view of the program.
     */
    public void createLoginView() {
        final LoginView loginView = new LoginView();
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(loginView);
        final LoginDataAccessInterface loginDataAccessInterface = new DBUserAccessObject();
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                loginDataAccessInterface, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
    }

    /**
     * Create the sign up view of the program.
     */
    public void createSignUpView() {
        final SignupView signupView = new SignupView();
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(signupView);
        final SignupDataAccessInterface signupDataAccessInterface = new DBUserAccessObject();
        final SignupInputBoundary signupInteractor = new SignupInteractor(
                signupDataAccessInterface, signupOutputBoundary);
        final SignupController signupController = new SignupController(signupInteractor);

        signupView.setSignupController(signupController);
    }

    /**
     * Create the main menu view of the program.
     */
    public void createMainMenuView() {
        final MainMenuView mainMenuView = new MainMenuView();
    }
}
