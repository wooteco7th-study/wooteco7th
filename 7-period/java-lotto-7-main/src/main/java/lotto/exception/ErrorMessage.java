package lotto.exception;

public enum ErrorMessage {

    INVALID_INPUT("올바르지 않은 입력입니다."),
    INVALID_LOTTO_NUMBER("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    INVALID_LOTTO_SIZE("로또 번호는 6개여야 합니다."),
    INVALID_LOTTO_NUMBER_DUPLICATED("로또 번호에 중복이 존재합니다."),
    INVALID_BONUS_NUMBER_DUPLICATED("보너스 번호가 당첨 번호가 중복됩니다."),
    INVALID_LOTTO("보너스 번호가 당첨 번호가 중복됩니다."),
    ;

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
