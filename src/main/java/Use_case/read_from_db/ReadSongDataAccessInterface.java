package Use_case.read_from_db;

import data_transfer_object.Recording;

public interface ReadSongDataAccessInterface {
    Recording[] readTopSongs(String documentID);
}
