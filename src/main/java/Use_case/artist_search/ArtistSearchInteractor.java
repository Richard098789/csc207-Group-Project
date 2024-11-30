package Use_case.artist_search;

import entity.Artist;
import repository.ArtistSearchRepository;

public class ArtistSearchInteractor implements ArtistSearchInputBoundary {
    private final ArtistSearchRepository repository;

    public ArtistSearchInteractor(ArtistSearchRepository repository) {
        this.repository = repository;
    }

    @Override
    public void searchArtists(ArtistSearchInputData inputData, ArtistSearchOutputBoundary outputBoundary) {
        Artist[] artists = repository.searchArtists(inputData.getArtistName(), inputData.getCountry(), inputData.getLimit(), inputData.getOffset());
        outputBoundary.presentResults(artists);
    }
}
