package Use_case.artist_search;

import entity.Artist;

public class ArtistSearchPresenter implements ArtistSearchOutputBoundary {
    private Artist[] results;

    @Override
    public void presentResults(ArtistSearchOutputData outputData) {
        // Store the results for access by the UI
        this.results = outputData.getArtists();
    }

    // Getter to retrieve the results
    public Artist[] getResults() {
        return results;
    }
}
