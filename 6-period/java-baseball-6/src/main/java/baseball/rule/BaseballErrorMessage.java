package baseball.rule;

public enum BaseballErrorMessage {

    INVALID_DUPLICATE_NUMBER("중복 숫자는 불가합니다."),
    INVALID_THREE_COUNT_NUMBER("3개의 숫자로 입력해주세요."),
    INVALID_RANGE_NUMBER("1~9사이의 숫자로 입력해 주세요");


    private final String message;

    BaseballErrorMessage(final String message) {
        this.message = "[ERROR]" + message;
    }

    public String getMessage() {
        return message;
    }
}
