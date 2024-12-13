package christmas;

import christmas.domain.controller.ChristmasController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();
        final ChristmasController christmasController = new ChristmasController(inputView, outputView);
        christmasController.run();
    }
}
