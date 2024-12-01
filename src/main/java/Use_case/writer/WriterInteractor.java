package Use_case.writer;

public class WriterInteractor implements WriterInputBoundary {
    private final WriterDataAccessInterface writerDataAccess;
    private final WriterOutputBoundary writerPresenter;

    public WriterInteractor(WriterDataAccessInterface writerDataAccess, WriterOutputBoundary writerPresenter) {
        this.writerDataAccess = writerDataAccess;
        this.writerPresenter = writerPresenter;
    }

    @Override
    public void executeComment(WriterInputData inputData) {
        try {
            writerDataAccess.addComment(inputData.getArtistId(), inputData.getUsername(), inputData.getRating(), inputData.getComment());
            writerPresenter.prepareSuccessView("Comment successfully added!");
        } catch (Exception e) {
            writerPresenter.prepareFailView("Failed to add comment: " + e.getMessage());
        }
    }

    @Override
    public void executeRating(WriterInputData inputData) {
        try {
            double averageRating = writerDataAccess.getAverageRating(inputData.getArtistId());
            writerPresenter.prepareSuccessView("Rating successfully added! New average rating: " + averageRating);
        } catch (Exception e) {
            writerPresenter.prepareFailView("Failed to add rating: " + e.getMessage());
        }
    }
}
