package store.error;

public enum ErrorMessage {

    INVALID_NUMBER_FORMAT("유효하지 않은 숫자 입니다."),
    INVALID_NOT_FOUND_PROMOTION("프로모션을 찾을 수 없습니다."),
    INVALID_INPUT("올바르지 않은 입력 입니다. 다시 입력해주세요");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(final String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
