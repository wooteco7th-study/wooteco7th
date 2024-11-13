package racingcar.domain;

import java.util.LinkedList;
import java.util.List;

import racingcar.dto.CarDto;
import racingcar.util.validator.ListValidator;

public class Race {

	private final LinkedList<Car> cars;

	private Race(final ListValidator<Car> listValidator, final LinkedList<Car> cars) {
		listValidator.validateDuplicate(cars);
		this.cars = cars;
	}

	public static Race of(final ListValidator<Car> listValidator, final LinkedList<Car> cars) {
		return new Race(listValidator, cars);
	}

	public void moveCars() {
		for (Car car : cars) {
			car.move();
		}
	}

	public List<CarDto> getCarsStatus() {
		return cars.stream()
			.map(CarDto::from)
			.toList();
	}

	public List<String> getWinners() {
		final int maxScore = calculateMaxScore();
		return cars.stream()
			.filter(car -> car.getScore() == maxScore)
			.map(Car::getName)
			.toList();
	}

	private int calculateMaxScore() {
		return cars.stream()
			.mapToInt(Car::getScore)
			.max()
			.orElse(0);
	}
}
