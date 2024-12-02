package Use_case.artist_search;

import data_transfer_object.Artist;

public interface ArtistSearchDataAccessInterface {
    Artist[] getArtists(String artistName, String country, int limit, int offset);
}
