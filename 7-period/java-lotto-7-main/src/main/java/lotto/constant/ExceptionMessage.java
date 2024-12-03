package lotto.constant;

public enum ExceptionMessage {
    INVALID_INPUT_FORM("올바르지 않은 입력 형식입니다."),
    NULL_OR_EMPTY_MESSAGE("값은 null이거나 비어 있을 수 없습니다."),
    INVALID_NUMBER_TYPE_MESSAGE("유효하지 않은 숫자 타입입니다."),
    OUT_OF_RANGE_MESSAGE("범위를 벗어난 값입니다."),
    DUPLICATE_MESSAGE("중복되지 않는 값만 허용합니다."),

    OUT_OF_SIZE_MESSAGE("사이즈를 벗어났습니다."),

    OUT_OF_POSITIVE_MESSAGE("양수만 허용합니다."),
    OUT_OF_UNIT_FORM_MESSAGE("기본 단위를 벗아났습니다.");


    private final String message;

    ExceptionMessage(final String message) {
        this.message = String.format("%s %s %s", "[ERROR]", message, "다시 입력해 주세요.");
    }

    public String getMessage() {
        return message;
    }
}
