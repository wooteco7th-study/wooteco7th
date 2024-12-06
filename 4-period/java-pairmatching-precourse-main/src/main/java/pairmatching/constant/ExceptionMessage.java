package pairmatching.constant;

public enum ExceptionMessage {
    CANT_NULL_OR_EMPTY("값은 null이거나 비어 있을 수 없습니다."),
    INVALID_INPUT_FORM("유효하지 않은 형식 입니다."),
    NO_MATCHING_HISTORY("매칭 이력이 없습니다."),
    HAS_MATCH_INFO_BUT_TRY_REMATCH("매칭 정보가 있습니다. 다시 매칭하시겠습니까?");


    private final String message;

    ExceptionMessage(final String message) {
        this.message = String.format("%s %s", "[ERROR]", message);
    }

    public String getMessage() {
        return message;
    }
}
