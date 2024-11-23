package christmas.rule;


public enum ErrorMessage {
    //1~31
    INVALID_RANGE_DATE("유효하지 않은 날짜입니다."),

    INVALID_NO_EXIST_MENU("유효하지 않은 주문입니다."),
    INVALID_MENU_QUANTITY("유효하지 않은 주문입니다."),
    INVALID_MENU_FORMAT("유효하지 않은 주문입니다."),
    INVALID_DUPLICATE_MENU("유효하지 않은 주문입니다.");


    private final String message;

    ErrorMessage(final String message) {
        this.message = String.format("[ERROR] %s 다시 입력해 주세요.", message);
    }

    public String getMessage() {
        return message;
    }
}
