package racingcar.error;

public enum ErrorType {

	BLANK_CAR_NAME("자동차 이름은 공백으로만 이루어질 수 없습니다."),
	OVER_LENGTH_CAR_NAME("자동차 이름은 5글자 이하여야합니다."),
	DUPLICATE_CAR("자동차는 중복될 수 없습니다."),
	INVALID_ATTEMPT("시도 횟수는 숫자만 가능 합니다.");

	private static final String PREFIX = "[ERROR] ";
	private final String message;

	ErrorType(final String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
