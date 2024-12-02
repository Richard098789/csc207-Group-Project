package interface_adapter.read_from_db;

import UI.ArtistListing;
import Use_case.read_from_db.ReadOutputBoundary;
import Use_case.read_from_db.ReadOutputData;

import java.util.List;
import java.util.Map;

public class ArtistReadPresenter implements ReadOutputBoundary {

    @Override
    public void prepareArtistDetailedView(ReadOutputData readOutputData) {

        List<Map<String, String>> comments = readOutputData.getComments();
        double averageRating = readOutputData.getAverageRating();

        // Call View to Prepare Artist Detailed View
        ArtistListing artistListing = new ArtistListing();
        // artistListing.displayArtistDetailedView(comments, averageRating);

    }
}
