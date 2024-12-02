package interface_adapter.login;

import Use_case.login.LoginInputBoundary;
import Use_case.login.LoginInputData;

public class LoginController {
    private final LoginInputBoundary loginInteractor;

    public LoginController(LoginInputBoundary loginInteractor) {
        this.loginInteractor = loginInteractor;
    }

    public void execute(String username, String password) {
        final LoginInputData loginInputData = new LoginInputData(username, password);
        loginInteractor.execute(loginInputData);
    }

    public void goSignupView() {
        loginInteractor.goSignupView();
    }
}
