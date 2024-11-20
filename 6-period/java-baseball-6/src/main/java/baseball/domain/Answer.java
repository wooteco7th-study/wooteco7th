package baseball.domain;

import baseball.exception.ExceptionMessage;

public enum Answer {

    YES("1"),
    NO("2");

    private final String value;

    Answer(final String value) {
        this.value = value;
    }

    public static boolean sayYes(String input) {
        if (input.equals(YES.value)) {
            return true;
        }
        if (input.equals(NO.value)) {
            return false;
        }
        throw new IllegalArgumentException(ExceptionMessage.INVALID_COMMAND.getMessage(YES.value, NO.value));
    }
}
