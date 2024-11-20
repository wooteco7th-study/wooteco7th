package baseball.config;

import baseball.controller.Controller;
import baseball.util.Formatter;
import baseball.view.InputView;
import baseball.view.OutputView;

public class Config {

    public Controller createController() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        Formatter formatter = new Formatter();
        return new Controller(inputView, outputView, formatter);
    }
}
