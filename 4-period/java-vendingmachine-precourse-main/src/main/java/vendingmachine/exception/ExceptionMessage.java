package vendingmachine.exception;

import static java.lang.String.format;

public enum ExceptionMessage {

    INPUT_BLANK("빈 값을 입력하셨습니다."),

    INVALID_NUMBER("유효하지 않은 숫자입니다."),

    INVALID_AMOUNT("금액은 숫자여야 합니다."),

    COIN_NOT_FOUND("해당 종류의 코인이 존재하지 않습니다."),

    INVALID_FORMAT("유효하지 않은 포맷입니다."),

    AMOUNT_MUST_BE_POSITIVE("금액은 양수여야 합니다.");

    private static final String ERROR_PREFIX = "[ERROR] ";

    private final String message;

    ExceptionMessage(String message) {
        this.message = ERROR_PREFIX + message;
    }

    public String getMessage() {
        return format(message);
    }
}
