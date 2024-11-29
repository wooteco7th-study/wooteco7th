package baseball;

import baseball.controller.BaseBallController;
import baseball.view.console.ConsoleInputView;
import baseball.view.console.ConsoleOutputView;

public class Application {
    public static void main(String[] args) {
        final ConsoleInputView consoleInputView = new ConsoleInputView();
        final ConsoleOutputView consoleOutputView = new ConsoleOutputView();
        final BaseBallController baseBallController = new BaseBallController(consoleInputView, consoleOutputView);
        baseBallController.run();
    }
}
