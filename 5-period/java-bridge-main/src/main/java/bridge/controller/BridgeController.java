package bridge.controller;

import bridge.Bridge;
import bridge.BridgeMaker;
import bridge.Direction;
import bridge.exception.ExceptionHandler;
import bridge.generator.BridgeRandomNumberGenerator;
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
        Direction direction = makeDirection();

    }

    private Direction makeDirection() {
        return exceptionHandler.retryOn(() -> {
            outputView.selectDirection();
            return new Direction(inputView.readMoving().charAt(0));
        });
    }

    private Bridge makeBridge() {
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        return exceptionHandler.retryOn(() -> {
            int bridgeSize = inputView.readBridgeSize();
            return new Bridge(bridgeSize, bridgeMaker);
        });
    }
}
