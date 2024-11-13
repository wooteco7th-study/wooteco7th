package racingcar.error.exception;

import racingcar.error.AppException;
import racingcar.error.ErrorType;

public class InvalidAttemptException extends AppException {

	public InvalidAttemptException(final ErrorType errorType) {
		super(errorType);
	}
}
