package interface_adapter.event_search;

import Use_case.event_search.EventSearchOutputBoundary;
import Use_case.event_search.EventSearchOutputData;
import data_transfer_object.Event;
import view.EventListingView;

public class EventSearchPresenter implements EventSearchOutputBoundary {
    private final EventListingView eventListingView;

    public EventSearchPresenter(EventListingView eventListingView) {
        this.eventListingView = eventListingView;
    }

    @Override
    public void presentResults(EventSearchOutputData outputData) {
        Event[] results = outputData.getEvents();
        eventListingView.presentResults(results);
    }
}
