package vendingmachine.exception;

public enum ErrorMessage {

    ERROR_PREFIX("[ERROR] "),

    NULL("입력값은(는) null일 수 없습니다."),
    BLANK("입력값은(는) 빈 문자열이거나 공백일 수 없습니다."),
    EMPTY("입력값은(는) 비어있을 수 없습니다."),

    NOT_A_NUMBER("숫자만 입력할 수 있습니다."),

    INVALID_HOLDING_AMOUNT("유효하지 않은 보유 금액입니다. 다시 입력해 주세요."),
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),

    INVALID_HOLDING_PRICE_DIVIDE_BY_TEN("10원으로 나누어 떨어져야 합니다.")
    ;

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
