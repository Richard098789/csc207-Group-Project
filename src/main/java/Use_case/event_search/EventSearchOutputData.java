package Use_case.event_search;

import data_transfer_object.Event;

/**
 * Event Search Output Data.
 */
public class EventSearchOutputData {
    private final Event[] events;

    public EventSearchOutputData(Event[] events) {
        this.events = events;
    }

    public Event[] getEvents() {
        return events;
    }
}