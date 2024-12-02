package interface_adapter.event_search;

import Use_case.event_search.EventSearchInputBoundary;
import Use_case.event_search.EventSearchInputData;

public class EventSearchController {
    private final EventSearchInputBoundary interactor;

    public EventSearchController(EventSearchInputBoundary inputBoundary) {
        this.interactor = inputBoundary;
    }

    public void searchEvents(String eventName, String location, int limit, int offset) {
        EventSearchInputData inputData = new EventSearchInputData(eventName, location, limit, offset);
        interactor.execute(inputData);
    }
}
