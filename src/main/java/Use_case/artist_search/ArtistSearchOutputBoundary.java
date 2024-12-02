package Use_case.artist_search;

/**
 * Artist Search Output Boundary.
 */
public interface ArtistSearchOutputBoundary {

    /**
     * Present results.
     * @param outputData outputdata
     */
    void presentResults(ArtistSearchOutputData outputData);
}
