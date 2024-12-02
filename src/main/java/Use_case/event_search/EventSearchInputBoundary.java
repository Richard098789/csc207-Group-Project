package Use_case.event_search;

/**
 * Event Search Iutput Boundary.
 */
public interface EventSearchInputBoundary {

    /**
     * Execute user command.
     * @param inputData input data
     */
    void execute(EventSearchInputData inputData);
}