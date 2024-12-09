package pairmatching.error;

public enum ErrorMessage {

    INVALID_LEVEL("유효하지 않은 레벨 입니다."),
    INVALID_COURSE("유효하지 않은 과정 입니다."),
    INVALID_MISSION("유효하지 않은 미션 입니다."),
    INVALID_COMMAND("유효하지 않은 기능 입니다."),
    INVALID_INPUT_FORMAT("유효하지 않은 입력 입니다."),
    INVALID_FAIL_MATCH("매칭 시도가 3회를 초과하여 매칭에 실패하였습니다."),
    INVALID_NOT_FOUND_PAIR_RESULT("매칭 이력이 없습니다."),
    INVALID_FAIL_FILE_LOAD("파일을 읽는데 실패하였습니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(final String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
