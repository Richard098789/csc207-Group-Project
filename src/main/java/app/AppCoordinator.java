package app;

import Use_case.login.LoginDataAccessInterface;
import Use_case.login.LoginInputBoundary;
import Use_case.login.LoginInteractor;
import Use_case.login.LoginOutputBoundary;
import Use_case.signup.SignupDataAccessInterface;
import Use_case.signup.SignupInputBoundary;
import Use_case.signup.SignupInteractor;
import Use_case.signup.SignupOutputBoundary;
import Use_case.writer.WriterDataAccessInterface;
import Use_case.writer.WriterInputBoundary;
import Use_case.writer.WriterInteractor;
import Use_case.writer.WriterOutputBoundary;
import data_access.DBUserAccessObject;
import data_access.DBWriterAccessObject;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.writer.WriterController;
import interface_adapter.writer.WriterPresenter;
import view.ArtistDetailView;
import view.LoginView;
import view.MainMenuView;
import view.SignupView;

public class AppCoordinator {
    // use singleton pattern to save view info.
    private static AppCoordinator instance;
    private AppCoordinator() {}

    public static AppCoordinator getInstance() {
        if (instance == null) {
            instance = new AppCoordinator();
        }
        return instance;
    }

    public void createLoginView() {
        LoginView loginView = new LoginView();
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(loginView);
        final LoginDataAccessInterface loginDataAccessInterface = new DBUserAccessObject();
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                loginDataAccessInterface, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
    }

    public void createSignUpView() {
        SignupView signupView = new SignupView();
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(signupView);
        final SignupDataAccessInterface signupDataAccessInterface = new DBUserAccessObject();
        final SignupInputBoundary signupInteractor = new SignupInteractor(
                signupDataAccessInterface, signupOutputBoundary);
        final SignupController signupController = new SignupController(signupInteractor);

        signupView.setSignupController(signupController);
    }

    public void createMainMenuView() {
        MainMenuView mainMenuView = new MainMenuView();
    }

    public void createArtistDetailView() {
        ArtistDetailView artistDetailView = new ArtistDetailView();
        final WriterOutputBoundary writerOutputBoundary = new WriterPresenter(artistDetailView);
        final WriterDataAccessInterface writerDataAccessInterface = new DBWriterAccessObject();
        final WriterInputBoundary writerInteractor = new WriterInteractor(
                writerDataAccessInterface, writerOutputBoundary);
        final WriterController writerController = new WriterController(writerInteractor);

        artistDetailView.setWriterController(writerController);
    }
}
