package baseball.exception;

public enum ExceptionMessage {

    UNIQUE_NUMBERS("숫자는 중복되지 않은 %d개여야 합니다."),
    VALID_RANGE("숫자는 %d과 %d 사이여야 합니다."),
    VALID_NUMBER("숫자여야 합니다."),
    NULL_VALUE("null일 수 없습니다."),
    BLANK_VALUE("공백일 수 없습니다."),
    EMPTY_VALUE("빈 값일 수 없습니다.");

    private String message;

    ExceptionMessage(final String message) {
        this.message = "[ERROR] " + message;
    }

    public String getMessage() {
        return message;
    }


    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
