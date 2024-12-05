package bridge.controller;

import bridge.domain.bridge.BridgeGame;
import bridge.domain.bridge.BridgeLog;
import bridge.domain.bridge.BridgeMaker;
import bridge.domain.bridge.BridgeSize;
import bridge.domain.command.RestartCommand;
import bridge.domain.command.UpDown;
import bridge.domain.generator.BridgeNumberGenerator;
import bridge.dto.GameBoardDto;
import bridge.dto.GameResultDto;
import bridge.exception.ExceptionHandler;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BridgeController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ExceptionHandler exceptionHandler;
    private final BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeController(final InputView inputView, final OutputView outputView,
                            final ExceptionHandler exceptionHandler,
                            final BridgeNumberGenerator bridgeNumberGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.exceptionHandler = exceptionHandler;
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    public void process() {
        outputView.startMessage();
        List<String> bridge = makeBridge();
        BridgeGame game = new BridgeGame(bridge, new BridgeLog(new ArrayList<>(), new ArrayList<>()));
        processGame(game);
    }

    private void processGame(final BridgeGame game) {
        do {
            game.clear();
            move(game);
        } while (shouldContinue(game));
        outputView.printResult(GameResultDto.from(game));
    }

    private boolean shouldContinue(final BridgeGame game) {
        return !game.isSuccess() && wantRestart(game);
    }

    private boolean wantRestart(final BridgeGame game) {
        outputView.restartMessage();
        return exceptionHandler.retryOn(() -> {
            RestartCommand restartCommand = RestartCommand.from(inputView.readGameCommand());
            return game.retry(restartCommand);
        });
    }

    private void move(final BridgeGame game) {
        do {
            UpDown upDown = makeUpDown();
            game.move(upDown);
            outputView.printMap(GameBoardDto.from(game.getBridgeLog()));
        } while (game.canContinue());
    }

    private UpDown makeUpDown() {
        return exceptionHandler.retryOn(() -> {
            outputView.selectDirection();
            return UpDown.from(inputView.readMoving());
        });
    }

    private List<String> makeBridge() {
        return exceptionHandler.retryOn(() -> {
            BridgeSize size = exceptionHandler.retryOn(() -> new BridgeSize(inputView.readBridgeSize()));
            BridgeMaker bridgeMaker = new BridgeMaker(bridgeNumberGenerator);
            return bridgeMaker.makeBridge(size.getSize());
        });
    }
}
