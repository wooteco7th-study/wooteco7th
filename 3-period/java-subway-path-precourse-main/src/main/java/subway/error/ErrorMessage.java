package subway.error;

public enum ErrorMessage {

    INVALID_NOT_FOUND_STATION("역을 찾을 수 없습니다."),
    INVALID_DUPLICATED_STATION("역은 중복될 수 없습니다."),
    INVALID_COMMAND("올바르지 않은 명령어 입니다."),
    INVALID_NOT_LINKED_STATION("역이 서로 연결되어 있지 않습니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(final String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
