package Use_case.artist_search;

import data_transfer_object.Artist;

/**
 * Artist search DAI.
 */
public interface ArtistSearchDataAccessInterface {

    /**
     * Return a list of artists.
     * @param artistName artist name
     * @param country country of interest
     * @param limit limit
     * @param offset offset
     * @return a list of artists.
     */
    Artist[] getArtists(String artistName, String country, int limit, int offset);
}
