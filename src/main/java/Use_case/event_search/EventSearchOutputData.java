package Use_case.event_search;

import entity.Event;

public class EventSearchOutputData {
    private final Event[] events;

    public EventSearchOutputData(Event[] events) {
        this.events = events;
    }

    public Event[] getEvents() {
        return events;
    }
}
