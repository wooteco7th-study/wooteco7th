package bridge;

import bridge.controller.BridgeController;
import bridge.domain.generator.BridgeNumberGenerator;
import bridge.domain.generator.BridgeRandomNumberGenerator;
import bridge.exception.ExceptionHandler;
import bridge.view.InputView;
import bridge.view.OutputView;
import camp.nextstep.edu.missionutils.Console;

public class Application {

    public static void main(String[] args) {
        BridgeController controller = createController();
        try {
            controller.process();
        } finally {
            Console.close();
        }
    }

    private static BridgeController createController() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ExceptionHandler exceptionHandler = new ExceptionHandler(outputView);
        BridgeNumberGenerator numberGenerator = new BridgeRandomNumberGenerator();
        return new BridgeController(inputView, outputView, exceptionHandler, numberGenerator);
    }
}
