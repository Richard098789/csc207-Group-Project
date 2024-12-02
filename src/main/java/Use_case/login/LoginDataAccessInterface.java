package Use_case.login;

/**
 * Login Data Access Interface.
 */
public interface LoginDataAccessInterface {

    /**
     * Validate login.
     * @param username username
     * @param password password
     * @return boolean indicates success or fail.
     */
    boolean validateLogin(String username, String password);
}
