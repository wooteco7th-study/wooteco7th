package racingcar.config;

import racingcar.controller.RacingCarController;
import racingcar.domain.Car;
import racingcar.util.convertor.InputConvertor;
import racingcar.util.validator.ListValidator;
import racingcar.util.generator.NumberGenerator;
import racingcar.util.convertor.RacingCarInputConvertor;
import racingcar.util.validator.RacingCarListValidator;
import racingcar.util.validator.RacingCarStringValidator;
import racingcar.util.generator.RandomNumberGenerator;
import racingcar.util.validator.StringValidator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class AppConfig {

	public InputConvertor<String> inputConvertor() {
		return RacingCarInputConvertor.getInstance();
	}

	public ListValidator<Car> listValidator() {
		return RacingCarListValidator.getInstance();
	}

	public NumberGenerator numberGenerator() {
		return RandomNumberGenerator.getInstance();
	}

	public StringValidator stringValidator() {
		return RacingCarStringValidator.getInstance();
	}

	public InputView inputView() {
		return new InputView();
	}

	public OutputView outputView() {
		return new OutputView();
	}

	public RacingCarController racingCarController() {
		return RacingCarController.of(stringValidator(), listValidator(), numberGenerator(), inputConvertor(),
			inputView(), outputView());
	}
}
