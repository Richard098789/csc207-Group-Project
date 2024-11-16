package Use_case;

import entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private final Map<String, User> users = new HashMap<>(); // Simulates a database

    public boolean addUser(String username, String password) {
        if (users.containsKey(username)) {
            return false; // Username already exists
        }
        users.put(username, new User(username, password));
        return true;
    }

    public boolean validateLogin(String username, String password) {
        User user = users.get(username);
        return user != null && user.getPassword().equals(password);
    }

    public boolean userExists(String username) {
        return users.containsKey(username);
    }
}
