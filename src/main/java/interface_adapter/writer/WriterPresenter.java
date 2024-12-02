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
        artistDetailView.commentSuccess(message);
    }

    @Override
    public void prepareFailView(String errorMessage) {
        artistDetailView.commentFailure(errorMessage);
    }
}
