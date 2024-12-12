package oncall.exception;

public enum ErrorMessage {

    INVALID_INPUT("유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
