package Use_case.artist_search;

import data_transfer_object.Artist;

public class ArtistSearchOutputData {
    private final Artist[] artists;

    public ArtistSearchOutputData(Artist[] artists) {
        this.artists = artists;
    }

    public Artist[] getArtists() {
        return artists;
    }
}
