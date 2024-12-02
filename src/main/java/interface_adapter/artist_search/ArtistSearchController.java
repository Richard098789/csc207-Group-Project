package interface_adapter.artist_search;

import Use_case.artist_search.ArtistSearchInputBoundary;
import Use_case.artist_search.ArtistSearchInputData;

public class ArtistSearchController {
    private final ArtistSearchInputBoundary inputBoundary;

    public ArtistSearchController(ArtistSearchInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void searchArtists(String artistName, String country, int limit, int offset) {
        ArtistSearchInputData inputData = new ArtistSearchInputData(artistName, country, limit, offset);
        inputBoundary.execute(inputData);
    }
}
