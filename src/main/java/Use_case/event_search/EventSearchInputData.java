package Use_case.event_search;

/**
 * Event Search Output data.
 */
public class EventSearchInputData {
    private final String eventName;
    private final String location;
    private final int limit;
    private final int offset;

    public EventSearchInputData(String eventName, String location, int limit, int offset) {
        this.eventName = eventName;
        this.location = location;
        this.limit = limit;
        this.offset = offset;
    }

    public String getEventName() {
        return eventName;
    }

    public String getLocation() {
        return location;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }
}
