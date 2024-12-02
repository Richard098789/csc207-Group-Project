
package entity;

public class Recording {
    private final String id;
    private final String title;
    private final int length;

    private Recording(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.length = builder.length;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public String getFormattedLength() {
        if (length <= 0) {
            return "Unknown";
        }
        int minutes = length / 60000;
        int seconds = (length % 60000) / 1000;
        return String.format("%d:%02d", minutes, seconds);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String title;
        private int length;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder length(int length) {
            this.length = length;
            return this;
        }

        public Recording build() {
            return new Recording(this);
        }
    }
}