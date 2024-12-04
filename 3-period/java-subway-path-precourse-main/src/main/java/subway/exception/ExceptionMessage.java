package subway.exception;

public enum ExceptionMessage {
    INPUT_BLANK("빈 값을 입력하셨습니다."),

    STATION_NOT_FOUND("존재하지 않는 역입니다."),

    CONNECTION_NOT_FOUND("존재하지 않는 경로입니다."),

    COMMAND_NOT_FOUND("존재하지 않는 명령어입니다.");
    private static final String ERROR_PREFIX = "[ERROR] ";

    private final String message;

    ExceptionMessage(String message) {
        this.message = ERROR_PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
