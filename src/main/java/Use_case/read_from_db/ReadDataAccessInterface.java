package Use_case.read_from_db;

import java.util.Map;

/**
 * Read Data Access Interface.
 */
public interface ReadDataAccessInterface {

    /**
     * Read contents.
     * @param documentID document ID
     * @return a map of content.
     */
    Map<String, Object> readContents(String documentID);

}
