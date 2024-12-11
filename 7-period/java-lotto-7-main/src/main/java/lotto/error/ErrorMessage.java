package lotto.error;

public enum ErrorMessage {

    EXCEEDS_NUMBER("숫자는 1 ~ 45 사이 자연수여야 합니다."),
    DUPLICATED_NUMBER("숫자는 중복될 수 없습니다."),
    INVALID_MONEY_FORMAT("구입 금액은 숫자여야 합니다."),
    EXCEEDS_NUMBERS_SIZE("로또 숫자는 6개여야 합니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(final String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
