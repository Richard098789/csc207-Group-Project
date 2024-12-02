package interface_adapter.signup;

import Use_case.signup.SignupOutputBoundary;
import view.SignupView;

public class SignupPresenter implements SignupOutputBoundary {
    private final SignupView view;
    public SignupPresenter(SignupView view) {
        this.view = view;
    }

    @Override
    public void prepareSuccessView() {
        view.signupSuccess();
    }

    @Override
    public void prepareFailView() {
        view.signupFailure();
    }

    @Override
    public void prepareUnmatchPasswordView() {
        view.passwordUnmatched();
    }

    @Override
    public void switchToLoginView() {
        view.goLoginView();
    }
}