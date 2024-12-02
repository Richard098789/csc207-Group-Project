package Use_case.event_search;

import data_transfer_object.Event;

public class EventSearchInteractor implements EventSearchInputBoundary {
    private final MusicBrainzEventRepository repository;
    private final EventSearchOutputBoundary outputBoundary;

    public EventSearchInteractor(MusicBrainzEventRepository repository, EventSearchOutputBoundary outputBoundary) {
        this.repository = repository;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(EventSearchInputData inputData) {
        Event[] events = repository.getEvents(
                inputData.getEventName(),
                inputData.getLocation(),
                inputData.getLimit(),
                inputData.getOffset()
        );

        EventSearchOutputData outputData = new EventSearchOutputData(events);
        outputBoundary.presentResults(outputData);
    }
}
