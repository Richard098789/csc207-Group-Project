package interface_adapter.login;


import Use_case.login.LoginOutputBoundary;
import view.MainMenuView;

public class LoginPresenter implements LoginOutputBoundary {
    @Override
    public void prepareMainMenuView() {
        new MainMenuView();
    }

    public void prepareFailView() {

    }
}
