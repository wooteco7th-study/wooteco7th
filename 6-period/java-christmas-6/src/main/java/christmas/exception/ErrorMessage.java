package christmas.exception;

public enum ErrorMessage {

    ERROR_PREFIX("[ERROR] "),

    NULL("인자 값은 null일 수 없습니다."),
    BLANK("인자 값은 비어있거나 공백일 수 없습니다."),
    EMPTY("인자 값은 빈 문자열일 수 없습니다."),
    NULL_OR_BLANK("인자 값은 null이거나 공백일 수 없습니다"),
    NULL_OR_EMPTY("인자 값은 null이거나 비어있을 수 없습니다"),

    NOT_A_NUMBER("숫자만 입력할 수 있습니다."),

    INVALID_DAY("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ;

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
