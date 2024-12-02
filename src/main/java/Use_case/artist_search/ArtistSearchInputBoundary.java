package Use_case.artist_search;

/**
 * Artist search input boundary.
 */
public interface ArtistSearchInputBoundary {

    /**
     * Execute user command.
     * @param inputData the input data.
     */
    void execute(ArtistSearchInputData inputData);
}
