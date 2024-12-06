package pairmatching;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.controller.PairController;
import pairmatching.exception.ExceptionHandler;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ExceptionHandler exceptionHandler = new ExceptionHandler(outputView);
        PairController controller = new PairController(inputView, outputView, exceptionHandler);
        try {
            controller.process();
        } finally {
            Console.close();
        }
    }
}
