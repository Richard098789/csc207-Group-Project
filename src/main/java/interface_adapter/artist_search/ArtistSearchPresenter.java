package interface_adapter.artist_search;

import Use_case.artist_search.ArtistSearchOutputBoundary;
import Use_case.artist_search.ArtistSearchOutputData;
import view.ArtistListingView;

public class ArtistSearchPresenter implements ArtistSearchOutputBoundary {
    private final ArtistListingView artistListingView;
    public ArtistSearchPresenter(ArtistListingView artistListingView) {
        this.artistListingView = artistListingView;
    }

    @Override
    public void presentResults(ArtistSearchOutputData outputData) {
        artistListingView.presentResults(outputData.getArtists());
    }
}
