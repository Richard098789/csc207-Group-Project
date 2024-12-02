package Use_case.event_search;

import data_transfer_object.Event;

public interface EventSearchDataAccessInterface {
    Event[] getEvents(String eventName, String location, int limit, int offset);
}
