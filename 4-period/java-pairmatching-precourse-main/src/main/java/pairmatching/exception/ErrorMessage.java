package pairmatching.exception;

public enum ErrorMessage {

    INVALID_NO_HISTORY("매칭 이력이 없습니다."),
    INVALID_COMMAND("올바르지 않은 커맨드입니다."),
    INVALID_COURSE("올바르지 않은 과정입니다."),
    INVALID_LEVEL("올바르지 않은 레벨입니다."),
    INVALID_MISSION("올바르지 않은 미션입니다."),

    INVALID_FORMAT("올바르지 않은 입력 형식입니다."),

    INVALID_FILE_FORMAT("파일 형식이 다릅니다.")
    ;

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
