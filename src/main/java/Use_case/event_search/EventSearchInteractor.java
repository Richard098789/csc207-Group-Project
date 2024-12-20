package Use_case.event_search;

import data_transfer_object.Event;

/**
 * Event Search interactor.
 */
public class EventSearchInteractor implements EventSearchInputBoundary {
    private final EventSearchDataAccessInterface repository;
    private final EventSearchOutputBoundary presenter;

    public EventSearchInteractor(EventSearchDataAccessInterface repository,
                                 EventSearchOutputBoundary outputBoundary) {
        this.repository = repository;
        this.presenter = outputBoundary;
    }

    @Override
    public void execute(EventSearchInputData inputData) {
        final Event[] events = repository.getEvents(
                inputData.getEventName(),
                inputData.getLocation(),
                inputData.getLimit(),
                inputData.getOffset()
        );

        final EventSearchOutputData outputData = new EventSearchOutputData(events);
        presenter.presentResults(outputData);
    }
}
