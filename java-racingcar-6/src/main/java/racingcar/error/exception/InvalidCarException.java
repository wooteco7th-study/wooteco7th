package racingcar.error.exception;

import racingcar.error.AppException;
import racingcar.error.ErrorType;

public class InvalidCarException extends AppException {

	public InvalidCarException(final ErrorType errorType) {
		super(errorType);
	}
}
