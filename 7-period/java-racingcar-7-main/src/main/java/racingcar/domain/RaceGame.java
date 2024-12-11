package racingcar.domain;

import java.util.List;

public class RaceGame {

    private final Cars cars;
    private final Attempt attempt;

    public RaceGame(final Cars cars, final Attempt attempt) {
        this.cars = cars;
        this.attempt = attempt;
    }

    public List<Car> race() {
        cars.moveAll();
        attempt.decreaseAttempt();
        return cars.getValues();
    }

    public List<String> getWinners() {
        return cars.getWinners(cars.calculateMaxScore()).stream()
                .map(Car::getName)
                .toList();
    }

    public boolean isEnd() {
        return attempt.isZero();
    }
}
