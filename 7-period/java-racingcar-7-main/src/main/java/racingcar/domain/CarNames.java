package racingcar.domain;

import java.util.List;
import racingcar.exception.AppException;
import racingcar.exception.ErrorMessage;
import racingcar.util.NumberValidator;

public class CarNames {

    private static final int MIN = 1;
    private static final int MAX = 100;

    private final List<CarName> names;

    public CarNames(final List<CarName> names) {
        NumberValidator.validateRange(names.size(), MIN, MAX, ErrorMessage.EXCEEDS_CAR_SIZE);
        validate(names);
        this.names = names;
    }

    public static CarNames of(final List<String> values) {
        return new CarNames(values.stream()
                .map(CarName::new)
                .toList());
    }

    public List<CarName> getNames() {
        return names;
    }

    private void validate(final List<CarName> names) {
        if (isDuplicated(names)) {
            throw new AppException(ErrorMessage.INVALID_INPUT);
        }
    }

    private boolean isDuplicated(final List<CarName> names) {
        return names.size() != names.stream().distinct().count();
    }
}
