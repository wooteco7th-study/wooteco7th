package bridge;

import bridge.controller.BridgeController;
import bridge.exception.ExceptionHandler;
import bridge.view.InputView;
import bridge.view.OutputView;
import camp.nextstep.edu.missionutils.Console;

public class Application {

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ExceptionHandler exceptionHandler = new ExceptionHandler(outputView);
        BridgeController controller = new BridgeController(inputView, outputView, exceptionHandler);
        try {
            controller.process();
        } finally {
            Console.close();
        }
    }
}
