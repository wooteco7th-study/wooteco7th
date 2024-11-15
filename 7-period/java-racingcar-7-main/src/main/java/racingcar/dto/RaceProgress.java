package racingcar.dto;

import racingcar.domain.Car;

public record RaceProgress(
        String carName,
        int score
) {

    public static RaceProgress of(final Car car) {
        return new RaceProgress(
                car.getName(),
                car.getScore()
        );
    }
}
