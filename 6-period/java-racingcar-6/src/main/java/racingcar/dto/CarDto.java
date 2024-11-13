package racingcar.dto;

import racingcar.domain.Car;

public record CarDto(
	String name,
	int score
) {

	public static CarDto from(Car car) {
		return new CarDto(car.getName(), car.getScore());
	}
}
