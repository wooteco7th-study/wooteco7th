package racingcar.controller;

import java.util.LinkedList;
import java.util.List;

import racingcar.domain.Car;
import racingcar.domain.Race;
import racingcar.dto.CarDto;
import racingcar.error.ErrorType;
import racingcar.error.exception.InvalidAttemptException;
import racingcar.util.convertor.InputConvertor;
import racingcar.util.validator.ListValidator;
import racingcar.util.generator.NumberGenerator;
import racingcar.util.validator.StringValidator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarController {

	private final StringValidator stringValidator;
	private final ListValidator<Car> listValidator;
	private final NumberGenerator numberGenerator;
	private final InputConvertor<String> inputConvertor;
	private final InputView inputView;
	private final OutputView outputView;

	private RacingCarController(final StringValidator stringValidator, final ListValidator<Car> listValidator,
		final NumberGenerator numberGenerator, final InputConvertor<String> inputConvertor, final InputView inputView,
		final OutputView outputView) {
		this.stringValidator = stringValidator;
		this.listValidator = listValidator;
		this.numberGenerator = numberGenerator;
		this.inputConvertor = inputConvertor;
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public static RacingCarController of(final StringValidator stringValidator, final ListValidator<Car> listValidator,
		final NumberGenerator numberGenerator, final InputConvertor<String> inputConvertor, final InputView inputView,
		final OutputView outputView) {
		return new RacingCarController(stringValidator, listValidator, numberGenerator, inputConvertor, inputView,
			outputView);
	}

	public void run() {
		final List<Car> cars = requestCars();
		final Race race = Race.of(listValidator, (LinkedList<Car>)cars);
		final int attempt = requestAttempt();
		outputView.printResultTitle();
		for (int turn = 0; turn < attempt; turn++) {
			race.moveCars();
			final List<CarDto> carsStatus = race.getCarsStatus();
			responseResult(carsStatus);
		}
		final List<String> winners = race.getWinners();
		responseWinners(winners);

	}

	private void responseWinners(final List<String> winners) {
		outputView.printWinners(winners);
	}

	private void responseResult(List<CarDto> carDtos) {
		outputView.printResult(carDtos);
	}


	private LinkedList<Car> requestCars() {
		outputView.printIntro();
		final String input = inputView.readInput();
		final List<String> carNames = inputConvertor.covertToList(input);
		final List<Car> list = carNames.stream()
			.map(carName -> Car.of(stringValidator, numberGenerator, carName, 0))
			.toList();
		return new LinkedList<>(list);
	}

	private int requestAttempt() throws InvalidAttemptException {
		outputView.printAskAttempt();
		final String input = inputView.readInput();
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			throw new InvalidAttemptException(ErrorType.INVALID_ATTEMPT);
		}
	}
}
