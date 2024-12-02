package interface_adapter.read_from_db;

import view.ArtistListingView;
import Use_case.read_from_db.ReadOutputBoundary;
import Use_case.read_from_db.ReadOutputData;

import java.util.Map;

public class ArtistReadPresenter implements ReadOutputBoundary {
    private final ArtistListingView artistListingView;

    public ArtistReadPresenter(ArtistListingView artistListingView) {
        this.artistListingView = artistListingView;
    }
    @Override
    public void prepareArtistDetailedView(ReadOutputData readOutputData) {

        Map<String, String> comments = readOutputData.getComments();
        double averageRating = readOutputData.getAverageRating();
        artistListingView.createArtistDetailView(readOutputData.getRecording(),
                comments, readOutputData.getArtist(), averageRating);

    }
}
