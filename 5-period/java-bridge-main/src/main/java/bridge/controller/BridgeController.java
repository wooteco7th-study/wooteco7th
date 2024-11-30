package bridge.controller;

import bridge.Bridge;
import bridge.exception.ExceptionHandler;
import bridge.view.InputView;
import bridge.view.OutputView;

public class BridgeController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ExceptionHandler exceptionHandler;

    public BridgeController(final InputView inputView, final OutputView outputView,
                            final ExceptionHandler exceptionHandler) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.exceptionHandler = exceptionHandler;
    }

    public void process() {
        // 다리 입력
        outputView.startMessage();
        Bridge bridge = makeBridge();
    }

    private Bridge makeBridge() {
        return exceptionHandler.retryOn(() -> {
            int bridgeSize = inputView.readBridgeSize();
            return new Bridge(bridgeSize);
        });
    }
}
