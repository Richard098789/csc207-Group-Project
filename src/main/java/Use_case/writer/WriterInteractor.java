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
            validateInput(inputData);
            writerDataAccess.addComment(inputData.getArtistId(), inputData.getUsername(), inputData.getRating(), inputData.getComment());
            writerPresenter.prepareSuccessView("Comment successfully added!");
        } catch (IllegalArgumentException e) {
            writerPresenter.prepareFailView("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            writerPresenter.prepareFailView("Failed to add comment: " + e.getMessage());
        }
    }

    private void validateInput(WriterInputData inputData) {
        if (inputData.getArtistId() == null || inputData.getArtistId().trim().isEmpty()) {
            throw new IllegalArgumentException("Artist ID cannot be null or empty.");
        }
        if (inputData.getUsername() == null || inputData.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        if (inputData.getRating() < 0 || inputData.getRating() > 10) {
            throw new IllegalArgumentException("Rating must be between 0 and 10.");
        }
        if (inputData.getComment() == null || inputData.getComment().trim().isEmpty()) {
            throw new IllegalArgumentException("Comment cannot be null or empty.");
        }
    }

}
