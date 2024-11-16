package Controller;

import Use_case.UserManager;

public class LoginController {
    private final UserManager userManager;

    public LoginController(UserManager userManager) {
        this.userManager = userManager;
    }

    public boolean login(String username, String password) {
        return userManager.validateLogin(username, password);
    }
}
