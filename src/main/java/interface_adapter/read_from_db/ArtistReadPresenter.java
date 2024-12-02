package interface_adapter.read_from_db;

import view.ArtistDetailView;
import view.ArtistListingView;
import Use_case.read_from_db.ReadOutputBoundary;
import Use_case.read_from_db.ReadOutputData;

import java.util.List;
import java.util.Map;

public class ArtistReadPresenter implements ReadOutputBoundary {
    private ArtistListingView artistListingView;

    public ArtistReadPresenter(ArtistListingView artistListingView) {
        this.artistListingView = artistListingView;
    }
    @Override
    public void prepareArtistDetailedView(ReadOutputData readOutputData) {

        List<Map<String, String>> comments = readOutputData.getComments();
        System.out.println(comments+"in presenter");
        double averageRating = readOutputData.getAverageRating();
        artistListingView.createArtistDetailView(readOutputData.getRecording(),
                comments, readOutputData.getArtist(), averageRating);

    }
}
