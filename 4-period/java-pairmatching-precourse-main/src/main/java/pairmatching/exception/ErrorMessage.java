package pairmatching.exception;

public enum ErrorMessage {

    INVALID_NO_HISTORY("매칭 이력이 없습니다."),
    INVALID_COMMAND("올바르지 않은 커맨드입니다."),
    INVALID_COURSE("존재하지 않는 과정입니다. 다시 입력해주세요."),
    INVALID_LEVEL("올바르지 않은 레벨입니다."),
    INVALID_MISSION("올바르지 않은 미션입니다."),

    INVALID_CREW_NAME("중복된 크루원이 존재합니다. 다시 입력해주세요."),
    INVALID_NAME_LENGTH("크루원 이름은 최소 2글자 이상 5글자 이하여야 합니다. 다시 입력해주세요."),
    INVALID_NAME_FORMAT("이름은 한글이어야합니다."),

    PAIR_MATCH_FAILED("페어매칭이 3번 이상 실패했습니다."),
    PAIR_MATCH_ORDER_FAILED("페어 매칭 주문을 찾을 수 없습니다."),
    PAIR_DUPLICATED("페어 내에서 이름이 중복될 수 없습니다."),

    INVALID_INPUT("올바르지 않은 입력입니다."),
    INVALID_NO_CREW("크루원이 존재하지 않습니다."),
    CREWS_COURSE_NOT_SAME("크루들의 코스가 동일하지 않습니다."),

    INVALID_FILE_FORMAT("파일 형식이 다릅니다.");

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
