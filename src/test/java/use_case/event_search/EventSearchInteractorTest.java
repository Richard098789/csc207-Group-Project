package use_case.event_search;


import Use_case.event_search.EventSearchDataAccessInterface;
import Use_case.event_search.EventSearchInputData;
import Use_case.event_search.EventSearchInteractor;
import Use_case.event_search.EventSearchOutputBoundary;

import data_transfer_object.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

public class EventSearchInteractorTest {
    private EventSearchDataAccessInterface mockRepository;
    private EventSearchOutputBoundary mockPresenter;
    private EventSearchInteractor interactor;

    @BeforeEach
    void setUp() {
        // Mock dependencies
        mockRepository = mock(EventSearchDataAccessInterface.class);
        mockPresenter = mock(EventSearchOutputBoundary.class);

        // Initialize the interactor
        interactor = new EventSearchInteractor(mockRepository, mockPresenter);
    }



    @Test
    void testValidArtistSearch() {
        // Prepare test data
        String eventName = "Beatles";
        String country = "UK";
        int limit = 10;
        int offset = 0;

        Event[] expectedEvents = {
                new Event("1", "Beatles", "person", "10.11", "10.22", "1",
                        "US", "11", "Richard", "1", 1),
                new Event("2", "Halloween", "group", "11.11", "22.22", "2",
                        "DE", "22", "Nick", "2", 2)
        };

        // Mock behavior
        when(mockRepository.getEvents(eventName, country, limit, offset)).thenReturn(expectedEvents);

        // Execute use case
        EventSearchInputData inputData = new EventSearchInputData(eventName, country, limit, offset);
        interactor.execute(inputData);

        // Verify repository interaction
        verify(mockRepository).getEvents(eventName, country, limit, offset);

        // Verify presenter interaction
        verify(mockPresenter).presentResults(argThat(outputData ->
                outputData.getEvents().length == expectedEvents.length &&
                        outputData.getEvents()[0].getArtistName().equals("Richard") &&
                        outputData.getEvents()[0].getScore() == 1
        ));
    }

    @Test
    void testEmptyEventSearchResults() {
        // Prepare test data
        String eventName = "NonExistentEvent";
        String country = "US";
        int limit = 10;
        int offset = 0;

        Event[] expectedEvents = new Event[0];

        // Mock behavior
        when(mockRepository.getEvents(eventName, country, limit, offset)).thenReturn(expectedEvents);

        // Execute use case
        EventSearchInputData inputData = new EventSearchInputData(eventName, country, limit, offset);
        interactor.execute(inputData);

        // Verify repository interaction
        verify(mockRepository).getEvents(eventName, country, limit, offset);

        // Verify presenter interaction
        verify(mockPresenter).presentResults(argThat(outputData ->
                outputData.getEvents().length == 0
        ));
    }

    @Test
    void testRepositoryInteractionOnly() {
        // Prepare test data
        String eventName = "Queen";
        String country = "UK";
        int limit = 5;
        int offset = 2;

        Event[] expectedEvents = {
                new Event("2", "Halloween", "group", "11.11", "22.22", "2",
                        "DE", "22", "Nick", "2", 2)
        };

        // Mock behavior
        when(mockRepository.getEvents(eventName, country, limit, offset)).thenReturn(expectedEvents);

        // Execute use case
        EventSearchInputData inputData = new EventSearchInputData(eventName, country, limit, offset);
        interactor.execute(inputData);

        // Verify repository interaction
        verify(mockRepository).getEvents(eventName, country, limit, offset);

        // Verify no extra interactions
        verifyNoMoreInteractions(mockRepository);
    }
}
