package racingcar.util.validator;

import static racingcar.error.ErrorType.*;

import java.util.List;

import racingcar.domain.Car;
import racingcar.error.exception.InvalidCarException;

public class RacingCarListValidator implements ListValidator<Car> {

	private RacingCarListValidator() {
	}

	private static class BillPughSingleton {
		private static final RacingCarListValidator INSTANCE = new RacingCarListValidator();
	}

	public static RacingCarListValidator getInstance() {
		return BillPughSingleton.INSTANCE;
	}

	@Override
	public void validateDuplicate(final List<Car> cars) {
		if (isDuplicate(cars)) {
			throw new InvalidCarException(DUPLICATE_CAR);
		}
	}

	private boolean isDuplicate(final List<Car> cars) {
		return cars.stream()
			.distinct()
			.count() != cars.size();
	}
}

