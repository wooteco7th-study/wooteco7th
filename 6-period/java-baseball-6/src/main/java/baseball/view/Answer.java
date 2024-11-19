package baseball.view;

public enum Answer {

    YES("1"),
    NO("2");

    private final String value;

    Answer(final String value) {
        this.value = value;
    }

    public static boolean sayYes(String input) {
        return input.equals(YES.getValue());
    }

    public String getValue() {
        return value;
    }
}
