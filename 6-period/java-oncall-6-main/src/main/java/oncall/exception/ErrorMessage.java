package oncall.exception;

public enum ErrorMessage {

    INVALID_INPUT("유효하지 않은 입력 값입니다. 다시 입력해 주세요."),
    CANNOT_CALCULATE_DAYTYPE("적절한 DayType을 반환할 수 없습니다.");

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
