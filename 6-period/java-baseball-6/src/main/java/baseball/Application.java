package baseball;

import baseball.controller.BaseballController;
import baseball.view.InputView;
import baseball.view.OutputView;

public class Application {
    public static void main(String[] args) {
        OutputView outputView = new OutputView();
        InputView inputView = new InputView(outputView);

        BaseballController baseballController = new BaseballController(inputView, outputView);
        baseballController.start();
    }
}
