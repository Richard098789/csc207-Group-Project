package Use_case.login;

public interface LoginDataAccessInterface {
    boolean addUser(String username, String password);
    boolean validateLogin(String username, String password);
}
