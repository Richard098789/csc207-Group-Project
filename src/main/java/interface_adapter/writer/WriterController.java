package interface_adapter.writer;

import Use_case.writer.WriterInputBoundary;
import Use_case.writer.WriterInputData;

public class WriterController {
    private final WriterInputBoundary writerInteractor;

    public WriterController(WriterInputBoundary writerInteractor) {
        this.writerInteractor = writerInteractor;
    }

    /**
     * Handles the submission of a comment and rating.
     *
     * @param artistId The ID of the artist.
     * @param username The username of the user submitting the feedback.
     * @param comment  The comment text.
     * @param rating   The rating value.
     */
    public void execute(String artistId, String username, String comment, double rating) {
        WriterInputData inputData = new WriterInputData(artistId, username, comment, rating);
        writerInteractor.executeComment(inputData);
        writerInteractor.executeRating(inputData);
    }
}
