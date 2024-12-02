package Use_case.artist_search;

import entity.Artist;

public class ArtistSearchInteractor implements ArtistSearchInputBoundary {
    private final MusicBrainzArtistRepository repository;
    private final ArtistSearchOutputBoundary outputBoundary;

    public ArtistSearchInteractor(MusicBrainzArtistRepository repository, ArtistSearchOutputBoundary outputBoundary) {
        this.repository = repository;
        this.outputBoundary = outputBoundary;
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
        outputBoundary.presentResults(outputData);
    }
}
