package pairmatching.exception;

public enum ExceptionMessage {

    FILE_NOT_EXISTS("파일이 존재하지 않습니다."),

    INPUT_BLANK("빈 값을 입력하셨습니다."),

    INVALID_FORMAT("유효하지 않은 포맷입니다."),

    MATCHING_HISTORY_NOT_FOUND("매칭 이력이 없습니다."),

    COMMAND_NOT_FOUND("존재하지 않는 명령어 입니다."),

    INFO_NOT_FOUND("존재하지 않는 과정 또는 레벨 또는 미션입니다."),

    MATCHING_IMPOSSIBLE("매칭이 더이상 불가합니다.");

    private static final String ERROR_PREFIX = "[ERROR] ";

    private final String message;

    ExceptionMessage(String message) {
        this.message = ERROR_PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
