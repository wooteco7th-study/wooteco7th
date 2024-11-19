package baseball.exception;

import static baseball.domain.Number.MAX_RANGE;
import static baseball.domain.Number.MIN_RANGE;
import static java.lang.String.format;

public enum ExceptionMessage {

    INPUT_BLANK("빈 값을 입력하셨습니다."),

    INVALID_NUMBER("유효하지 않은 숫자입니다."),

    NUMBER_OUT_OF_RANGE(
            format("범위는 %d이상 %d이하여야 합니다.",
                    MIN_RANGE,
                    MAX_RANGE)
    ),
    ;

    private static final String ERROR_PREFIX = "[ERROR] ";

    private final String message;

    ExceptionMessage(String message) {
        this.message = ERROR_PREFIX + message;
    }

    public String getMessage() {
        return format(message);
    }
}
