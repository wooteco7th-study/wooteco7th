package baseball;

import baseball.controller.GameController;
import baseball.domain.RandomNumbersGenerator;
import baseball.view.InputView;
import baseball.view.OutputView;

public class Application {
    public static void main(String[] args) {
        GameController gameController = new GameController(
                new InputView(),
                new OutputView(),
                new RandomNumbersGenerator());
        gameController.run();
    }
}
