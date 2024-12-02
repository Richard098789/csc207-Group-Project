package Use_case.artist_search;

import entity.Artist;

public interface ArtistSearchDataAccessInterface {
    Artist[] getArtists(String artistName, String country, int limit, int offset);
}
