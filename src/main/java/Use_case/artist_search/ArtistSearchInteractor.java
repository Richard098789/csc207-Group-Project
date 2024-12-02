package Use_case.artist_search;

import data_transfer_object.Artist;

public class ArtistSearchInteractor implements ArtistSearchInputBoundary {
    private final ArtistSearchDataAccessInterface repository;
    private final ArtistSearchOutputBoundary presenter;

    public ArtistSearchInteractor(ArtistSearchDataAccessInterface repository, ArtistSearchOutputBoundary outputBoundary) {
        this.repository = repository;
        this.presenter = outputBoundary;
    }

    @Override
    public void execute(ArtistSearchInputData inputData) {
        Artist[] artists = repository.getArtists(
                inputData.getArtistName(),
                inputData.getCountry(),
                inputData.getLimit(),
                inputData.getOffset()
        );

        ArtistSearchOutputData outputData = new ArtistSearchOutputData(artists);
        presenter.presentResults(outputData);
    }
}
