package Use_case.writer;

public interface WriterOutputBoundary {
    void prepareSuccessView(String message);
    void prepareFailView(String errorMessage);
}
