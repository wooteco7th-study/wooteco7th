package baseball;

import baseball.controller.Controller;
import baseball.util.Formatter;
import baseball.view.InputView;
import baseball.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        Formatter formatter = new Formatter();
        Controller controller = new Controller(inputView, outputView, formatter);
        controller.process();
    }
}
