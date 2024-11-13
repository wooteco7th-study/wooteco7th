package racingcar.util.validator;

import static racingcar.constant.Rule.*;
import static racingcar.error.ErrorType.*;

import racingcar.error.exception.InvalidCarException;

public class RacingCarStringValidator implements StringValidator {

	private RacingCarStringValidator() {
	}

	private static class BillPughSingleton {
		private static final RacingCarStringValidator INSTANCE = new RacingCarStringValidator();
	}

	public static RacingCarStringValidator getInstance() {
		return BillPughSingleton.INSTANCE;
	}

	@Override
	public StringValidator validateLength(final String name) {
		if (isInvalidName(name)) {
			throw new InvalidCarException(OVER_LENGTH_CAR_NAME);
		}
		return this;
	}

	@Override
	public StringValidator validateFormat(final String name) {
		if (name.isBlank()) {
			throw new InvalidCarException(BLANK_CAR_NAME);
		}
		return this;
	}

	private boolean isInvalidName(final String name) {
		return name.length() > NAME_LENGTH;
	}
}

