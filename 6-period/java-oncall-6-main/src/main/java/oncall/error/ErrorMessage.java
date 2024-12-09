package oncall.error;

public enum ErrorMessage {

    INVALID_MONTH("유효하지 않은 월 입니다."),
    INVALID_WEEK("유효하지 않은 요일 입니다."),
    INVALID_WORKER("유효하지 않은 입력 값입니다. 다시 입력해 주세요.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(final String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
