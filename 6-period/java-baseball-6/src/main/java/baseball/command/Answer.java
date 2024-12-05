package baseball.command;

import baseball.exception.ExceptionMessage;
import java.util.Arrays;

public enum Answer {

    YES("1"),
    NO("2");

    private final String value;

    Answer(final String value) {
        this.value = value;
    }

    public static Answer from(String input) {
        return Arrays.stream(values())
                .filter(answer -> answer.value.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        ExceptionMessage.INVALID_COMMAND.getMessage(YES.value, NO.value)));
    }

    public boolean isYes() {
        return this == YES;
    }
}
