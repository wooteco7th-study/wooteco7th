package bridge.exception;

public enum ErrorMessage {

    NULL("입력값은(는) null일 수 없습니다."),
    BLANK("입력값은(는) 빈 문자열이거나 공백일 수 없습니다."),

    INVALID_NUMBER("숫자만 입력할 수 있습니다."),

    INVALID_BRIDGE_LENGTH("유효하지 않은 다리 길이입니다. 다시 입력해 주세요."),
    INVALID_DIRECTION("유효하지 않은 방향입니다. 다시 입력해 주세요."),
    INVALID_DIRECTION_VALUE("유효하지 않은 방향값입니다. 다시 입력해 주세요."),
    INVALID_RESTART_COMMAND("유효하지 않은 커맨드입니다. 다시 입력해 주세요."),
    ;

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
