package entity;

public class Event {

    private final String id;
    private final String name;
    private final String type;
    private final String beginDate;
    private final String endDate;
    private final String time;
    private final String placeName;
    private final String placeId;
    private final String artistName;
    private final String artistId;
    private final int score;

    public Event(String id, String name, String type, String beginDate, String endDate, String time,
                 String placeName, String placeId, String artistName, String artistId, int score) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.time = time;
        this.placeName = placeName;
        this.placeId = placeId;
        this.artistName = artistName;
        this.artistId = artistId;
        this.score = score;
    }

    /**
     * Returns the event ID.
     * @return the event ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the event.
     * @return the name of the event.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the type of the event.
     * @return the type of the event.
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the begin date of the event.
     * @return the begin date of the event.
     */
    public String getBeginDate() {
        return beginDate;
    }

    /**
     * Returns the end date of the event.
     * @return the end date of the event.
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Returns the time of the event.
     * @return the time of the event.
     */
    public String getTime() {
        return time;
    }

    /**
     * Returns the name of the place where the event is held.
     * @return the place name.
     */
    public String getPlaceName() {
        return placeName;
    }

    /**
     * Returns the ID of the place where the event is held.
     * @return the place ID.
     */
    public String getPlaceId() {
        return placeId;
    }

    /**
     * Returns the name of the main artist associated with the event.
     * @return the artist name.
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     * Returns the ID of the main artist associated with the event.
     * @return the artist ID.
     */
    public String getArtistId() {
        return artistId;
    }

    /**
     * Returns the score of the event.
     * @return the event score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns a new EventBuilder instance.
     * @return a new EventBuilder instance.
     */
    public static EventBuilder builder() {
        return new EventBuilder();
    }

    /**
     * Represents a builder for creating instances of an Event.
     */
    public static class EventBuilder {
        private String id;
        private String name;
        private String type;
        private String beginDate;
        private String endDate;
        private String time;
        private String placeName;
        private String placeId;
        private String artistName;
        private String artistId;
        private int score;

        EventBuilder() {}

        /**
         * Sets the event ID.
         * @param idInput the event ID.
         * @return the EventBuilder instance.
         */
        public EventBuilder id(String idInput) {
            this.id = idInput;
            return this;
        }

        /**
         * Sets the name of the event.
         * @param nameInput the name of the event.
         * @return the EventBuilder instance.
         */
        public EventBuilder name(String nameInput) {
            this.name = nameInput;
            return this;
        }

        /**
         * Sets the type of the event.
         * @param typeInput the type of the event.
         * @return the EventBuilder instance.
         */
        public EventBuilder type(String typeInput) {
            this.type = typeInput;
            return this;
        }

        /**
         * Sets the begin date of the event.
         * @param beginDateInput the begin date of the event.
         * @return the EventBuilder instance.
         */
        public EventBuilder beginDate(String beginDateInput) {
            this.beginDate = beginDateInput;
            return this;
        }

        /**
         * Sets the end date of the event.
         * @param endDateInput the end date of the event.
         * @return the EventBuilder instance.
         */
        public EventBuilder endDate(String endDateInput) {
            this.endDate = endDateInput;
            return this;
        }

        /**
         * Sets the time of the event.
         * @param timeInput the time of the event.
         * @return the EventBuilder instance.
         */
        public EventBuilder time(String timeInput) {
            this.time = timeInput;
            return this;
        }

        /**
         * Sets the name of the place where the event is held.
         * @param placeNameInput the place name.
         * @return the EventBuilder instance.
         */
        public EventBuilder placeName(String placeNameInput) {
            this.placeName = placeNameInput;
            return this;
        }

        /**
         * Sets the ID of the place where the event is held.
         * @param placeIdInput the place ID.
         * @return the EventBuilder instance.
         */
        public EventBuilder placeId(String placeIdInput) {
            this.placeId = placeIdInput;
            return this;
        }

        /**
         * Sets the name of the main artist associated with the event.
         * @param artistNameInput the artist name.
         * @return the EventBuilder instance.
         */
        public EventBuilder artistName(String artistNameInput) {
            this.artistName = artistNameInput;
            return this;
        }

        /**
         * Sets the ID of the main artist associated with the event.
         * @param artistIdInput the artist ID.
         * @return the EventBuilder instance.
         */
        public EventBuilder artistId(String artistIdInput) {
            this.artistId = artistIdInput;
            return this;
        }

        /**
         * Sets the score of the event.
         * @param scoreInput the event score.
         * @return the EventBuilder instance.
         */
        public EventBuilder score(int scoreInput) {
            this.score = scoreInput;
            return this;
        }

        /**
         * Builds a new Event instance.
         * @return a new Event instance.
         */
        public Event build() {
            return new Event(id, name, type, beginDate, endDate, time, placeName, placeId, artistName, artistId, score);
        }
    }
}