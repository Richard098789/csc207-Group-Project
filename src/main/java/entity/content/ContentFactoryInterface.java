package entity.content;

public interface ContentFactoryInterface {
    /**
     * Creates a new User.
     * @param contentId the contentID
     * @return the new content entity
     */
    Content create(String contentId);
}
