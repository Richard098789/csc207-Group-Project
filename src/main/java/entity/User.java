package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents a user of the program.
 * It contains information such as the user ID, username, password, and a list of comment IDs.
 * The username, password, and the list of comment IDs are changeable after an User is created,
 * but the user ID is final.
 */
public class User {
    private static int idCounter = 0;
    private final int userId;
    private String username;
    private String password;
    private List<String> commentId;

    public User(String username, String password) {
        this.userId = idCounter++;
        this.username = username;
        this.password = password;
        this.commentId = new ArrayList<>();

    }

    /**
     * Returns the user ID.
     * @return the user ID.
     */

    public int getUserId() {

        return userId;
    }

    /**
     * Sets the username for the user.
     * @param username the input for username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the username.
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the password for the user.
     * @param password the input for password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the password.
     * @return the poassword.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the list of comment IDs.
     * @return the list of comment IDs.
     */
    public List<String> getCommentId() {
        return commentId;
    }

    /**
     * Adds a comment ID to the list of comment IDs.
     * @param commentID the comment ID that the user wanted to add.
     */
    public void addComment(String commentID) {
        this.commentId.add(commentID);
    }

    /**
     * Remove a comment ID from  the list of comment IDs.
     * @param commentID the comment ID that the user wanted to remove.
     */
    public void removeComment(String commentID) {
        this.commentId.remove(commentID);

    }

    /**
     * Get the sign in information.
     * @return the map containing sign in info.
     */
    public Map<String, String> getSignInInfo() {

        final Map<String, String> result = new HashMap<>();
        result.put("username", username);
        result.put("password", password);
        return result;
    }
}
