package oncall;

import camp.nextstep.edu.missionutils.Console;
import oncall.controller.OncallController;
import oncall.exception.ExceptionHandler;
import oncall.service.OncallService;
import oncall.view.InputView;
import oncall.view.OutputView;

public class Application {

    public static void main(String[] args) {

        OncallController oncallController = makeController();
        try {
            oncallController.process();
        } finally {
            Console.close();
        }
    }

    private static OncallController makeController() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ExceptionHandler exceptionHandler = new ExceptionHandler(outputView);
        OncallService oncallService = new OncallService();
        return new OncallController(inputView, outputView, exceptionHandler,
                oncallService);
    }
}
