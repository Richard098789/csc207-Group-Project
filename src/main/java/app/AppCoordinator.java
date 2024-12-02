package app;

import Use_case.read_from_db.*;
import Use_case.writer.WriterDataAccessInterface;
import Use_case.writer.WriterInputBoundary;
import Use_case.writer.WriterInteractor;
import Use_case.writer.WriterOutputBoundary;
import data_access.DBPublicAccessObject;
import data_transfer_object.Artist;
import data_transfer_object.Recording;
import interface_adapter.read_from_db.ArtistReadPresenter;
import interface_adapter.read_from_db.ReadController;
import interface_adapter.writer.WriterController;
import interface_adapter.writer.WriterPresenter;
import view.*;
import Use_case.artist_search.ArtistSearchDataAccessInterface;
import Use_case.artist_search.ArtistSearchInputBoundary;
import Use_case.artist_search.ArtistSearchInteractor;
import Use_case.artist_search.ArtistSearchOutputBoundary;
import data_access.MusicBrainzArtistRepository;
import Use_case.login.LoginDataAccessInterface;
import Use_case.login.LoginInputBoundary;
import Use_case.login.LoginInteractor;
import Use_case.login.LoginOutputBoundary;
import Use_case.signup.SignupDataAccessInterface;
import Use_case.signup.SignupInputBoundary;
import Use_case.signup.SignupInteractor;
import Use_case.signup.SignupOutputBoundary;
import data_access.DBUserAccessObject;
import interface_adapter.artist_search.ArtistSearchController;
import interface_adapter.artist_search.ArtistSearchPresenter;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;

import java.util.List;
import java.util.Map;

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

    public void createArtistListingView() {
        ArtistListingView artistListingView = new ArtistListingView();
        final ArtistSearchOutputBoundary artistSearchOutputBoundary = new ArtistSearchPresenter(artistListingView);
        final ArtistSearchDataAccessInterface artistSearchDataAccessInterface = new MusicBrainzArtistRepository();
        final ArtistSearchInputBoundary artistSearchInteractor = new ArtistSearchInteractor(
                artistSearchDataAccessInterface, artistSearchOutputBoundary);
        final ArtistSearchController artistSearchController = new ArtistSearchController(artistSearchInteractor);

        artistListingView.setArtistSearchController(artistSearchController);

        final ReadOutputBoundary readOutputBoundary = new ArtistReadPresenter(artistListingView);
        final ReadDataAccessInterface readDataAccessInterface = new DBPublicAccessObject();
        final ReadSongDataAccessInterface musicBrainZAPI = new MusicBrainzArtistRepository();
        final ReadInputBoundary readInteractor = new ArtistReadInteractor(
                readOutputBoundary,readDataAccessInterface, musicBrainZAPI);
        final ReadController readController = new ReadController(readInteractor);

        artistListingView.setReadController(readController);
    }

    public void createArtistDetailView(Recording[] topSongs,
                                       List<Map<String, String>> comments, Artist artist,
                                       Double averageRating) {
        ArtistDetailView artistDetailView = new ArtistDetailView(topSongs, comments, artist, averageRating);
        final WriterOutputBoundary writerPresenter = new WriterPresenter(artistDetailView);
        final WriterDataAccessInterface writerDataAccessInterface = new DBPublicAccessObject();
        final WriterInputBoundary writerInteractor = new WriterInteractor(
                writerDataAccessInterface, writerPresenter);
        final WriterController writerController = new WriterController(writerInteractor);

        artistDetailView.setWriterController(writerController);
    }

    public void createSearchSelectionView() {
        SearchSelection searchSelection = new SearchSelection();
    }
}


