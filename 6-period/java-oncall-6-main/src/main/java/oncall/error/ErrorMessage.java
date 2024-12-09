package oncall.error;

public enum ErrorMessage {

    INVALID_INPUT("유효하지 않은 입력 값입니다.");

    private static final String PREFIX = "[ERROR] ";
    private static final String SUFFIX = " 다시 입력해 주세요.";
    private final String message;

    ErrorMessage(final String message) {
        this.message = PREFIX + message + SUFFIX;
    }

    public String getMessage() {
        return message;
    }
}
