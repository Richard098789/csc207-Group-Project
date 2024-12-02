package Use_case.read_from_db;

import data_transfer_object.Recording;

/**
 * Read Song Data Access Interface.
 */
public interface ReadSongDataAccessInterface {

    /**
     * Read top songs.
     * @param documentID document ID
     * @return a list of recordings
     */
    Recording[] readTopSongs(String documentID);
}
