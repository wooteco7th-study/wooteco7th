package vendingmachine.excpetion;

public enum ErrorMessage {

    ERROR("[ERROR] "),
    INPUT_ERROR("잘못된 입력입니다. 다시 입력해주세요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR.message + message;
    }
}
