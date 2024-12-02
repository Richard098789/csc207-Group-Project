package interface_adapter.writer;

import Use_case.writer.WriterOutputBoundary;
import view.ArtistDetailView;

public class WriterPresenter implements WriterOutputBoundary {
    private final ArtistDetailView artistDetailView;

    public WriterPresenter(ArtistDetailView artistDetailView) {
        this.artistDetailView = artistDetailView;
    }
    @Override
    public void prepareSuccessView(String message) {
        // Display a success message to the user.
        System.out.println("SUCCESS: " + message);
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // Display an error message to the user.
        System.err.println("ERROR: " + errorMessage);
    }
}
