package baseball.exception;

public enum ExceptionMessage {

    INVALID_LENGTH("%d자리 숫자만 입력할 수 있습니다."),
    OUT_OF_RANGE("%d부터 %d까지의 숫자만 입력할 수 있습니다."),
    NOT_A_NUMBER("숫자만 입력할 수 있습니다."),
    NULL_INPUT("null일 수 없습니다."),
    BLANK_INPUT("공백일 수 없습니다."),
    INVALID_COMMAND("%s 또는 %s만 입력할 수 있습니다.");

    private static final String PREFIX = "[ERROR] ";

    private final String message;

    ExceptionMessage(final String message) {
        this.message = PREFIX + message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
