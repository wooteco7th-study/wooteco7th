package menu.constant;


public enum ExceptionMessage {
    CANT_NULL_OR_EMPTY("값은 null이거나 비어 있을 수 없습니다."),
    INVALID_INPUT_FORM("유효하지 않은 형식 입니다."),

    //

    INVALID_DUPLICATE_NO_MENU("못 먹는 음식은 중복 될 수 없습니다."),
    INVALID_COACH_NAME_RANGE("유효하지 않은 코치이름 길이입니다."),
    INVALID_MENU_NAME_RANGE("유효하지 않은 메뉴이름 입니다."),
    INVALID_COACH_SIZE("유효하지 않은 코치 수 입니다.");


    private final String message;

    ExceptionMessage(final String message) {
        this.message = String.format("%s %s %s", "[ERROR]", message, "다시 입력해 주세요.");
    }

    public String getMessage() {
        return message;
    }
}
