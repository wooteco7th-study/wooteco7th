package lotto.error;

public enum ErrorMessage {

    INVALID_EXCEEDS_LOTTO_NUMBER_RANGE("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    INVALID_DUPLICATED_LOTTO_NUMBER("로또 번호는 중복 될 수 없습니다."),
    INVALID_WRONG_NUMBER_FORMAT("구입 금액과 번호는 숫자여야 합니다."),
    INVALID_WRONG_MONEY_UNIT("구입 금액은 1,000원 단위여야 합니다."),
    INVALID_EXCEEDS_MONEY_RANGE("구입 금액은 1,000원 이상이여야 합니다.");


    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(final String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
