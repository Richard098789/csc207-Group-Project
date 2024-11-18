package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This class represents a user of the program.
 * It contains information such as the user ID, username, password, and a list of comments.
 * The username, password, and the list of comments are changeable after a User is created,
 * but the user ID is final.
 */
public class User {
    private final String userId;
    private String username;
    private String password;
    private List<String> comments;

    public User(String username, String password) {
        this.userId = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.comments = new ArrayList<>();
    }

    /**
     * Returns the user ID.
     * @return the user ID.
     */
    public String getUserId() {
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
     * Returns the list of comments.
     * @return the list of comments.
     */
    public List<String> getComments() {
        return comments;
    }

    /**
     * Adds a comment to the list of comments.
     * @param comment the comment that the user wanted to add.
     */
    public void addComment(String comment) {
        this.comments.add(comment);
    }

    /**
     * Remove a comment from the list of comments.
     * @param comment the comment that the user wanted to remove.
     */
    public void removeComment(String comment) {
        this.comments.remove(comment);
    }

}