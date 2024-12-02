package interface_adapter.artist_search;

import Use_case.artist_search.ArtistSearchOutputBoundary;
import Use_case.artist_search.ArtistSearchOutputData;
import entity.Artist;
import view.ArtistListingView;

public class ArtistSearchPresenter implements ArtistSearchOutputBoundary {
    private Artist[] results;
    private final ArtistListingView artistListingView;
    public ArtistSearchPresenter(ArtistListingView artistListingView) {
        this.artistListingView = artistListingView;
    }

    @Override
    public void presentResults(ArtistSearchOutputData outputData) {
        artistListingView.presentResults(outputData.getArtists());
        this.results = outputData.getArtists();
    }

    // Getter to retrieve the results
    public Artist[] getResults() {
        return results;
    }
}
