package baseball.error;

public enum ErrorMessage {

    INVALID_WRONG_NUMBER_FORMAT("숫자는 1~9 사이 자연수만 입력 가능합니다."),
    INVALID_EXCEEDS_NUMBERS_SIZE("숫자는 3자리만 가능합니다."),
    INVALID_WRONG_GAME_COMMAND("1 또는 2만 입력 가능합니다.");

    private static final String PREFIX = "[ERROR] ";

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
