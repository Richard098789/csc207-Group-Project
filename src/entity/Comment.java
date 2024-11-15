package entity;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * This class represents a user comment on an artist.
 * It contains information such as the comment ID, user ID, artist ID, event ID
 * comment content, timestamp, and the number of likes.
 * The comment content is editable after the comment is created, but other fields like
 * comment ID, user ID, artist ID, event ID, and timestamp are immutable.
 */
public class Comment {
    private final String commentId;
    private final int userId;
    private final String artistId;
    private final String eventId;
    private String content; // Not declared final so I can modify the content.
    private final LocalDateTime time;
    private int likes; // Not declared final so people can like a comment.

    public Comment(int userId, String artistId, String eventId, String content) {
        this.commentId = UUID.randomUUID().toString();
        this.userId = userId;
        this.artistId = artistId;
        this.eventId = eventId;
        this.content = content;
        this.time = LocalDateTime.now();
        this.likes = 0;
    }

    /**
     * Returns the comment ID.
     * @return the comment ID.
     */
    public String getCommentId() {
        return commentId;
    }

    /**
     * Returns the user ID.
     * @return the user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Returns the artist ID.
     * @return the artist ID.
     */
    public String getArtistId() {
        return artistId;
    }

    /**
     * Returns the event ID.
     * @return the event ID.
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * Returns the content of the comment.
     * @return the content of the comment.
     */
    public String getContent() {
        return content;
    }

    /**
     * Returns the time that this comment is posted.
     * @return the time that this comment is posted.
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Returns the number of likes.
     * @return the number of likes.
     */
    public int getLikes() {
        return likes;
    }

    /**
     * Returns a new CommentBuilder instance.
     * @return a new CommentBuilder instance.
     */
    public static Comment.CommentBuilder builder() {
        return new Comment.CommentBuilder();
    }

    // Builder Class
    /**
     * Represents a builder for creating instances of a Comment.
     */
    public static class CommentBuilder{
        private int userId;
        private String artistId;
        private String eventId;
        private String content;

        CommentBuilder() {
        }

        /**
         * Sets the user ID of the Comment.
         * @param userId the user ID.
         * @return the CommentBuilder instance.
         */
        public Comment.CommentBuilder userId(int userId) {
            this.userId = userId;
            return this;
        }

        /**
         * Sets the artist ID of the Comment.
         * @param artistId the artist ID.
         * @return the CommentBuilder instance.
         */
        public Comment.CommentBuilder artistId(String artistId) {
            this.artistId = artistId;
            return this;
        }

        /**
         * Sets the event ID of the Comment.
         * @param eventId the event ID.
         * @return the CommentBuilder instance.
         */
        public Comment.CommentBuilder eventId(String eventId) {
            this.eventId = eventId;
            return this;
        }

        /**
         * Sets the content of the Comment.
         * @param content the content.
         * @return the CommentBuilder instance.
         */
        public Comment.CommentBuilder content(String content) {
            this.content = content;
            return this;
        }

        /**
         * Builds a new Comment instance.
         * @return a new Comment instance.
         */
        public Comment build() {
            return new Comment(userId, artistId, eventId, content);
        }

    }
}
