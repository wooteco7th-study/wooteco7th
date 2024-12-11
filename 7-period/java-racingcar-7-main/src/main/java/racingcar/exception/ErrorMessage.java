package racingcar.exception;

public enum ErrorMessage {

    INVALID_INPUT("유효한 입력이 아닙니다."),
    NOT_FOUND_CAR("자동차가 없습니다.");

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
