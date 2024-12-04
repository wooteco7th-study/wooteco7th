package subway.exception;

public enum ErrorMessage {

    NULL("입력값은(는) null일 수 없습니다."),
    BLANK("입력값은(는) 빈 문자열이거나 공백일 수 없습니다."),

    INVALID_NUMBER("숫자만 입력할 수 있습니다."),
    INVALID_COMMAND("입력이 잘못되었습니다."),
    INVALID_ARGUMENT("인자가 잘못되었습니다."),

    INVALID_STATION_NAME("존재하지 않은 역 이름입니다."),
    INVALID_STATION_DUPLICATED("출발역과 도착역이 동일합니다."),
    INVALID_STATION_PATH("출발역과 도착역이 연결되어 있지 않습니다."),

    ;

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
