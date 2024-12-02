package Use_case.read_from_db;

import data_transfer_object.Artist;

/**
 * Read input data.
 */
public class ReadInputData {

    private final String documentID;
    private final Artist artist;

    public ReadInputData(String documentID, Artist artist) {
        this.artist = artist;
        this.documentID = documentID;
    }

    String getDocumentID() {
        return documentID;
    }

    Artist getArtist() {
        return artist;
    }
}
