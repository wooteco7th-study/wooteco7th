package oncall;

import camp.nextstep.edu.missionutils.Console;
import oncall.controller.OnCallController;
import oncall.exception.ExceptionHandler;
import oncall.service.OnCallService;
import oncall.view.InputView;
import oncall.view.OutputView;

public class Application {

    public static void main(String[] args) {

        OnCallController oncallController = makeController();
        try {
            oncallController.process();
        } finally {
            Console.close();
        }
    }

    private static OnCallController makeController() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ExceptionHandler exceptionHandler = new ExceptionHandler(outputView);
        OnCallService oncallService = new OnCallService();
        return new OnCallController(inputView, outputView, exceptionHandler,
                oncallService);
    }
}
