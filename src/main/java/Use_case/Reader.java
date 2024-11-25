package Use_case;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;

import entity.Content;

public class Reader {
    static final String PUBLIC_COLLECTION_NAME = "Public";

    public static Content fromPublic(Firestore db, String collectionName, String documentId) {
        try {
            DocumentSnapshot document = db.collection(collectionName).document(documentId).get().get();
            Content content = new Content(documentId);

            if (document.exists()) {
                System.out.println("Document data: " + document.getData());
                Map<String, Map<String, Object>> map = new HashMap<>();
                for (String key : document.getData().keySet()) {
                    Map<String, Object> value = (Map<String, Object>) document.getData().get(key);
                    map.put(key, value);
                }
                content.setContent(map);
                return content;
            } else {
                System.out.println("Document does not exist");
            }
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error reading document: " + e.getMessage());
        }
        return null;
    }
}
