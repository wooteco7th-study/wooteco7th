package pairmatching;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.controller.PairController;
import pairmatching.domain.Initializer;
import pairmatching.exception.ExceptionHandler;
import pairmatching.service.PairService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ExceptionHandler exceptionHandler = new ExceptionHandler(outputView);
        PairService pairService = new PairService();
        Initializer initializer = new Initializer();
        PairController controller = new PairController(inputView, outputView, exceptionHandler, pairService, initializer);
        try {
            controller.process();
        } finally {
            Console.close();
        }
    }
}
