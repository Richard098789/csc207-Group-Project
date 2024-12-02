package Use_case.signup;

/**
 * Signup Data Access Interface.
 */
public interface SignupDataAccessInterface {

    /**
     * Checks if the given username exists.
     * @param username the username to look for
     * @return true if a user with the given username exists; false otherwise
     */
    boolean userExists(String username);

    /**
     * Saves the user.
     * @param username the username entered.
     * @param password the password entered
     * @return true if a user is added successfully
     */
    boolean addUser(String username, String password);
}
