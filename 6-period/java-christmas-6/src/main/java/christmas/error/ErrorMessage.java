package christmas.error;

public enum ErrorMessage {

    INVALID_WRONG_ORDER_FORMAT("유효하지 않은 주문 입니다.다시 입력해 주세요."),
    INVALID_VISIT_DAY_FORMAT("유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(final String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
