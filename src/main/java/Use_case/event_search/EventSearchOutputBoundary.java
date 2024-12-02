package Use_case.event_search;


/**
 * Event Search Output Boundary.
 */
public interface EventSearchOutputBoundary {


    /**
     * Present results.
     * @param outputData output data
     */
    void presentResults(EventSearchOutputData outputData);
}