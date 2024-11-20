package racingcar.domain;

import java.util.Collections;
import java.util.List;

public class Race {

    private static final int ZERO = 0;
    private final List<Car> cars;

    public Race(final List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> move() {
        for (Car car : cars) {
            car.move();
        }
        return Collections.unmodifiableList(this.cars);
    }

    public List<String> getWinners() {
        final int score = calculateMaxScore();
        return cars.stream()
                .filter(car -> car.isSameScore(score))
                .map(Car::getName)
                .toList();
    }

    private int calculateMaxScore() {
        return cars.stream()
                .mapToInt(Car::getScore)
                .max()
                .orElse(ZERO);
    }
}
