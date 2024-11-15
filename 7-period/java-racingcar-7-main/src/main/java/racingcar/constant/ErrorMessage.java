package racingcar.constant;

public enum ErrorMessage {

    INVALID_EXCEEDS_CAR_NAME_LENGTH("자동차의 이름은 5글자를 초과할 수 없습니다."),
    INVALID_WRONG_NAME_FORMAT("자동차 이름은 빈문자열 혹은 공백으로만 이루어질 수 없습니다."),
    INVALID_EXCEEDS_ATTEMPT_RANGE("시도 횟수는 1이상 10_000이하여야 합니다."),
    INVALID_EXCEEDS_CARS_SIZE("경주에 참여할 수 있는 자동차는 1_000대 이하여야 합니다."),
    INVALID_DUPLICATE_CARS_NAME("자동차 이름은 중복 될 수 없습니다.");


    private static final String PREFIX = "[ERROR] ";
    private final String message;


    ErrorMessage(final String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
