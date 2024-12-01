package Controller;

import Use_case.event_search.EventSearchInputBoundary;
import Use_case.event_search.EventSearchInputData;

public class EventSearchController {
    private final EventSearchInputBoundary inputBoundary;

    public EventSearchController(EventSearchInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void searchEvents(String eventName, String location, int limit, int offset) {
        EventSearchInputData inputData = new EventSearchInputData(eventName, location, limit, offset);
        inputBoundary.execute(inputData);
    }
}
