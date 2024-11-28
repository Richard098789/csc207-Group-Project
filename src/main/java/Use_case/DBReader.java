package Use_case;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class DBReader {
    static final String ARTIST_COLLECTION_NAME = "artists";

    public static List<Map<String, Object>> readComments(Firestore db, String artistId) {
        List<Map<String, Object>> commentsList = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> future = db.collection(ARTIST_COLLECTION_NAME)
                    .document(artistId)
                    .collection("comments")
                    .get();

            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                commentsList.add(document.getData());
            }
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error reading comments: " + e.getMessage());
        }
        return commentsList;
    }
}
