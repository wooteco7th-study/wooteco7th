package racingcar;

import racingcar.controller.RacingCarController;
import racingcar.view.console.ConsoleInputView;
import racingcar.view.console.ConsoleOutputView;

public class Application {
    public static void main(String[] args) {
        final ConsoleInputView consoleInputView = new ConsoleInputView();
        final ConsoleOutputView consoleOutputView = new ConsoleOutputView();
        final RacingCarController racingCarController = new RacingCarController(consoleInputView, consoleOutputView);
        racingCarController.run();
    }
}
