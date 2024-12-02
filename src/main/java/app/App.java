package app;

import Use_case.login.LoginDataAccessInterface;
import Use_case.login.LoginInputBoundary;
import Use_case.login.LoginInteractor;
import Use_case.login.LoginOutputBoundary;
import data_access.DBUserAccessObject;
import global_storage.CurrentUser;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import view.LoginView;


public class App {

    public static void main(String[] args) {
        CurrentUser currentUser = new CurrentUser();
        AppCoordinator appCoordinator = AppCoordinator.getInstance();
        appCoordinator.createLoginView();
    }
}
