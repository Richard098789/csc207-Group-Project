package Use_case.read_from_db;

import com.google.cloud.firestore.Firestore;

import java.util.Map;

public interface ReadDataAccessInterface {

    Map<String, Object> readContents(String documentID);

}
