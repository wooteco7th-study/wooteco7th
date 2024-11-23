package christmas;

import christmas.controller.Controller;
import christmas.support.StringFormatter;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        StringFormatter stringFormatter = new StringFormatter();
        Controller controller = new Controller(inputView, outputView, stringFormatter);
        controller.process();

    }
}
