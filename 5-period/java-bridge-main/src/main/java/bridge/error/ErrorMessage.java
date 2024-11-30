package bridge.error;

public enum ErrorMessage {

    INVALID_BRIDGE_LENGTH_FORMAT("다리 길이는 3부터 20 사이의 숫자여야 합니다."),
    INVALID_GAME_COMMAND_FORMAT("R 또는 Q만 입력 가능합니다."),
    INVALID_MOVE_COMMAND_FORMAT("U 또는 D만 입력 가능합니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(final String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
