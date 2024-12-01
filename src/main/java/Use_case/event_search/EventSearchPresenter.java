package Use_case.event_search;

import entity.Event;

public class EventSearchPresenter implements EventSearchOutputBoundary {
    private Event[] results;

    @Override
    public void presentResults(EventSearchOutputData outputData) {
        // Store the results for access by the UI
        this.results = outputData.getEvents();
    }

    // Getter to retrieve the results
    public Event[] getResults() {
        return results;
    }
}
