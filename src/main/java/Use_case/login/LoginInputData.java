package Use_case.login;

/**
 * Login input data.
 */
public class LoginInputData {
    private final String username;
    private final String password;

    public LoginInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Get username.
     * @return username
     */
    String getUsername() {
        return username;
    }

    /**
     * Get password.
     * @return password
     */
    String getPassword() {
        return password;
    }
}
