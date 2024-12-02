package Use_case.writer;

public class WriterOutputData {
    private final String message;
    private final boolean success;

    public WriterOutputData(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
