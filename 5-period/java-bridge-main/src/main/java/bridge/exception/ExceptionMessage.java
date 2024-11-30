package bridge.exception;

import static bridge.domain.bridgemaker.BridgeMaker.MAX_SIZE;
import static bridge.domain.bridgemaker.BridgeMaker.MIN_SIZE;
import static java.lang.String.format;

public enum ExceptionMessage {
    INPUT_BLANK("빈 값을 입력하셨습니다."),

    INVALID_NUMBER("유효하지 않은 숫자입니다."),

    INVALID_FORMAT("유효하지 않은 포맷입니다."),

    SIZE_OUT_OF_RANGE(
            format("다리 길이는 %d에서 %d사이여야 합니다.",
                    MIN_SIZE, MAX_SIZE
            )),

    COMMAND_NOT_FOUND("존재하지 않는 게임 명령어 입니다."),


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
