package pairmatching.error;

public enum ErrorMessage {

    INVALID_LEVEL("유효하지 않은 레벨 입니다."),
    INVALID_COURSE("유효하지 않은 과정 입니다."),
    INVALID_MISSION("유효하지 않은 미션 입니다."),
    INVALID_NOT_FOUND_PAIR_RESULT("매칭 이력이 없습니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(final String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
