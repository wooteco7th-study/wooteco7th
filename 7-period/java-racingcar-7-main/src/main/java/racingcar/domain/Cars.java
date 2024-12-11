package racingcar.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import racingcar.exception.ErrorMessage;

public class Cars {

    private final List<Car> values;

    private Cars(final List<Car> values) {
        this.values = new ArrayList<>(values);
    }

    public static Cars of(final NumberGenerator numberGenerator, final List<CarName> carNames) {
        final List<Car> cars = carNames.stream()
                .map(carName -> new Car(numberGenerator, carName, 0))
                .toList();
        return new Cars(cars);
    }

    public int calculateMaxScore() {
        return values.stream()
                .mapToInt(Car::getScore)
                .max()
                .orElseThrow(() -> new IllegalStateException(ErrorMessage.NOT_FOUND_CAR.getMessage()));
    }

    public List<Car> getWinners(final int score) {
        return values.stream()
                .filter(car -> car.isMatchedScore(score))
                .toList();
    }

    public void moveAll() {
        for (Car car : values) {
            car.move();
        }
    }

    public List<Car> getValues() {
        return Collections.unmodifiableList(values);
    }
}
