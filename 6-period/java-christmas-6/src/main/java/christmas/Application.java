package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.ChristmasController;
import christmas.exception.ExceptionHandler;
import christmas.service.ChristmasService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {

    public static void main(String[] args) {
        ChristmasController christmasController = createController();
        try {
            christmasController.process();
        } finally {
            Console.close();
        }
    }

    private static ChristmasController createController() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ExceptionHandler exceptionHandler = new ExceptionHandler(outputView);
        ChristmasService christmasService = new ChristmasService();
        return new ChristmasController(inputView, outputView, exceptionHandler,
                christmasService);
    }
}
