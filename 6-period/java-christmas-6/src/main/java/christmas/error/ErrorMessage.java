package christmas.error;

public enum ErrorMessage {


    WRONG_ORDER_FORMAT("유효하지 않은 주문입니다."),
    VISIT_DAY_FORMAT("유효하지 않은 날짜입니다.");

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
