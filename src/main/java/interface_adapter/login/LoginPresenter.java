package interface_adapter.login;


import Use_case.login.LoginOutputBoundary;
import view.LoginView;

public class LoginPresenter implements LoginOutputBoundary {
    private final LoginView view;

    public LoginPresenter(LoginView view) {
        this.view = view;
    }
    @Override
    public void prepareMainMenuView() {
        view.toMainMenuView();
    }

    @Override
    public void prepareFailView() {
        view.loginFailureView();
    }

    @Override
    public void goSignupView() {
        view.toSignup();
    }


}
