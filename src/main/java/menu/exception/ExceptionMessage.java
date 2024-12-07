package menu.exception;

public enum ExceptionMessage {
    INPUT_BLANK("빈 값을 입력하셨습니다."),

    INVALID_NAME_FORMAT("이름은 최소 2글자에서 최대 5글자까지 입력 가능합니다."),

    INVALID_COACHES_SIZE("코치는 최소 2명에서 최대 5명까지 입력 가능합니다."),

    DUPLICATED_NAME("중복된 이름이 존재합니다."),

    MENU_NOT_FOUND("존재하지 않는 메뉴입니다."),

    MENU_OUT_OF_RANGE("못 먹는 메뉴는 최소 0개에서 최대 2개까지 입력 가능합니다.");
    private static final String ERROR_PREFIX = "[ERROR] ";

    private final String message;

    ExceptionMessage(String message) {
        this.message = ERROR_PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
