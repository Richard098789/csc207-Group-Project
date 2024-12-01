package Use_case.login;

public interface LoginInputBoundary {
    void execute(LoginInputData loginInputData);

    void goSignupView();
}
