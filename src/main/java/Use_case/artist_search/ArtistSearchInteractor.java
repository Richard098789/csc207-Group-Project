package Use_case.artist_search;

import data_transfer_object.Artist;

/**
 * Arist search interactor.
 */
public class ArtistSearchInteractor implements ArtistSearchInputBoundary {
    private final ArtistSearchDataAccessInterface repository;
    private final ArtistSearchOutputBoundary presenter;

    /**
     * Constructer.
     * @param repository the repository.
     * @param outputBoundary the output boundary.
     */
    public ArtistSearchInteractor(ArtistSearchDataAccessInterface repository,
                                  ArtistSearchOutputBoundary outputBoundary) {
        this.repository = repository;
        this.presenter = outputBoundary;
    }

    @Override
    public void execute(ArtistSearchInputData inputData) {
        final Artist[] artists = repository.getArtists(
                inputData.getArtistName(),
                inputData.getCountry(),
                inputData.getLimit(),
                inputData.getOffset()
        );

        final ArtistSearchOutputData outputData = new ArtistSearchOutputData(artists);
        presenter.presentResults(outputData);
    }
}
