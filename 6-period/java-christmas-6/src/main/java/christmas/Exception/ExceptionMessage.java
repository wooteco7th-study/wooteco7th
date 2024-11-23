package christmas.Exception;

public enum ExceptionMessage {

    ERROR("[ERROR] "),
    RE_INPUT_MESSAGE("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR.message + message;
    }
}
