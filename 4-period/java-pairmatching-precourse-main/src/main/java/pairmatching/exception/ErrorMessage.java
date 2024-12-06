package pairmatching.exception;

public enum ErrorMessage {

    INVALID_NO_HISTORY("매칭 이력이 없습니다."),
    INVALID_COMMAND("올바르지 않은 커맨드입니다."),
    ;

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
