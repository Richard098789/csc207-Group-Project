package view;

import entity.Event;

public class ExampleOfEventDetailedView {
    // Sample Display
    public static void main(String[] args) {
        // Example usage
        Event event = new Event(
                "001",
                "abc",
                "Concert",
                "2024-01-01",
                "2024-01-05",
                "20:30:00",
                "Toronto",
                "001",
                "Nick",
                "001",
                100
        );

        new EventDetailView(event);
    }
}
