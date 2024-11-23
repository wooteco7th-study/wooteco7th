package christmas;

import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        final OutputView outputView = new OutputView();
        final InputView inputView = new InputView(outputView);
    }
}
