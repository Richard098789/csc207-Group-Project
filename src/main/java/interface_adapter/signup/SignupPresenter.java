package interface_adapter.signup;

import Use_case.signup.SignupOutputBoundary;
import view.SignupView_New;

public class SignupPresenter implements SignupOutputBoundary {

    @Override
    public void prepareSuccessView(String successMessage) {

    }

    @Override
    public void prepareFailView(String errorMessage) {


    }

    @Override
    public void prepareUnmatchPasswordView(String unmatchPasswordMessage) {

    }

    @Override
    public void switchToLoginView() {
        SignupView_New signupView = new SignupView_New();
        signupView.createAndShowGUI();
    }
}