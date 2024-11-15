package racingcar.dto;

import racingcar.domain.Car;

public record RacingProgress(
        String carName,
        int score
) {

    public static RacingProgress of(final Car car) {
        return new RacingProgress(
                car.getName(),
                car.getScore()
        );
    }
}
