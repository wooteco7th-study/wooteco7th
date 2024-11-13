package racingcar.domain;

import static racingcar.constant.Rule.*;

import java.util.Objects;

import racingcar.util.generator.NumberGenerator;
import racingcar.util.validator.StringValidator;

public class Car {

	private final NumberGenerator numberGenerator;
	private final String name;
	private int score;

	private Car(final StringValidator stringValidator, final NumberGenerator numberGenerator, final String name, final int score) {
		stringValidator.validateLength(name)
			.validateFormat(name);

		this.numberGenerator = numberGenerator;
		this.name = name;
		this.score = score;
	}

	public static Car of(final StringValidator stringValidator, final NumberGenerator numberGenerator, final String name, final int score) {
		return new Car(stringValidator, numberGenerator, name, score);
	}

	public void move() {
		final int number = numberGenerator.generate();
		if (number >= GO_CONDITION) {
			score++;
		}
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Car))
			return false;
		final Car car = (Car)o;
		return Objects.equals(name, car.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
