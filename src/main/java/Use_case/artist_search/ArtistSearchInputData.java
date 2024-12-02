package Use_case.artist_search;

/**
 * Artist search input data.
 */
public class ArtistSearchInputData {
    private final String artistName;
    private final String country;
    private final int limit;
    private final int offset;

    public ArtistSearchInputData(String artistName, String country, int limit, int offset) {
        this.artistName = artistName;
        this.country = country;
        this.limit = limit;
        this.offset = offset;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getCountry() {
        return country;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }
}
