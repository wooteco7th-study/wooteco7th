package racingcar.error;

public abstract class AppException extends IllegalArgumentException{

	protected AppException(ErrorType errorType) {
		super(errorType.getMessage());
	}
}
