package racingcar.domain;

import java.util.List;
import racingcar.constant.ErrorMessage;
import racingcar.util.ListValidator;

public class CarFactory {

    private static final int INITIAL_SCORE = 0;
    private final NumberGenerator numberGenerator;
    private final List<String> carNames;

    public CarFactory(final NumberGenerator numberGenerator, final List<String> carNames) {
        this.numberGenerator = numberGenerator;
        validate(carNames);
        this.carNames = carNames;
    }

    private void validate(final List<String> carNames) {
        ListValidator.validateDuplicate(carNames, ErrorMessage.INVALID_DUPLICATE_CARS_NAME);
    }

    public List<Car> generateCars() {
        return carNames.stream()
                .map(CarName::new)
                .map(carName -> new Car(carName, new Score(INITIAL_SCORE), numberGenerator))
                .toList();
    }
}
