package Use_case.login;

/**
 * Login Data Access Interface.
 */
public interface LoginDataAccessInterface {

    /**
     * Add user.
     * @param username username
     * @param password password
     * @return boolean indicates success or fail.
     */
    boolean addUser(String username, String password);

    /**
     * Validate login.
     * @param username username
     * @param password password
     * @return boolean indicates success or fail.
     */
    boolean validateLogin(String username, String password);
}
