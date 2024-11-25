package Controller;

import Use_case.UserManager;

public class SignupController {
    private final UserManager userManager;

    public SignupController(UserManager userManager) {
        this.userManager = userManager;
    }

    public boolean signup(String username, String password) {
        return userManager.addUser(username, password);
    }

    public boolean usernameExists(String username) {
        return userManager.userExists(username);
    }
}
