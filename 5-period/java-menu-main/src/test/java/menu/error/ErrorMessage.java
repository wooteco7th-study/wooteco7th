package menu.error;

public enum ErrorMessage {

    INVALID_COACHES_SIZE("코치는 최소 2명 이상 입력해야 합니다."),
    INVALID_COACH_NAME("코치의 이름은 최소 2글자, 최대 4글자이어야 합니다."),
    INVALID_HATE_MENU("못 먹는 메뉴는 최대 2개까지만 가능합니다."),
    INVALID_NOT_FOUND_MENU_TYPE("메뉴 타입을 찾을 수 없습니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(final String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
