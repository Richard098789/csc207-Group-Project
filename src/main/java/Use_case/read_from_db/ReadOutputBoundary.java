package Use_case.read_from_db;

/**
 * Read output boundary.
 */
public interface ReadOutputBoundary {

    /**
     * Prepare artist detailed view.
     * @param readOutputData the output data.
     */
    void prepareArtistDetailedView(ReadOutputData readOutputData);
}
