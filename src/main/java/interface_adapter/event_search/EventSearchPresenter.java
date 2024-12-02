package interface_adapter.event_search;

import Use_case.event_search.EventSearchOutputBoundary;
import Use_case.event_search.EventSearchOutputData;
import data_transfer_object.Event;

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
