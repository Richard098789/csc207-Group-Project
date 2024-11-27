package Controller;

import entity.ArtistDetailModel;

public class ArtistDetailController {
    private final ArtistDetailModel model;

    public ArtistDetailController(ArtistDetailModel model) {
        this.model = model;
    }

    public void submitFeedback(int rating, String comment) {
        model.addRating(rating);
        model.addComment(comment);
    }

    public double getAverageRating() {
        return model.getAverageRating();
    }
}
