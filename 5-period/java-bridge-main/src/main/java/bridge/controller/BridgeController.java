package bridge.controller;

import bridge.domain.Bridge;
import bridge.domain.BridgeGame;
import bridge.domain.BridgeGenerator;
import bridge.domain.BridgeLength;
import bridge.domain.log.BridgeLogGroup;
import bridge.domain.log.BridgeLogType;
import bridge.domain.log.BridgeLogsGenerator;
import bridge.domain.GameCommand;
import bridge.domain.MoveCommand;
import bridge.util.LoopTemplate;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.List;

public class BridgeController {

    private final InputView inputView;
    private final OutputView outputView;

    public BridgeController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printIntro();
        final BridgeLength bridgeLength = requestBridgeLength();
        final Bridge bridge = BridgeGenerator.generate(bridgeLength);
        final BridgeLogGroup bridgeLogGroup = BridgeLogsGenerator.generate();
        final BridgeGame bridgeGame = new BridgeGame(bridgeLogGroup, bridge);
        start(bridgeGame);
    }

    private void start(final BridgeGame bridgeGame) {
        do {
            bridgeGame.clear();
            bridgeGame.addAttempt();
            move(bridgeGame);
        } while (isRetry(bridgeGame));
        responseGameResult(bridgeGame);
    }

    private void responseGameResult(final BridgeGame bridgeGame) {
        outputView.printResult(bridgeGame.getUpBridgeLogs(), bridgeGame.getDownBridgeLogs());
        outputView.printClearAndAttempt(bridgeGame.getGameResult(), bridgeGame.getAttempt());
    }

    private void move(final BridgeGame bridgeGame) {
        while (!bridgeGame.isEnd() && !bridgeGame.hasFail()) {
            final MoveCommand moveCommand = requestMoveCommand();
            bridgeGame.move(moveCommand);
            responseBridgeLog(bridgeGame);
        }
    }

    private void responseBridgeLog(final BridgeGame bridgeGame) {
        final List<BridgeLogType> upBridgeLogs = bridgeGame.getUpBridgeLogs();
        final List<BridgeLogType> downBridgeLogs = bridgeGame.getDownBridgeLogs();
        outputView.printMap(upBridgeLogs, downBridgeLogs);
    }

    private BridgeLength requestBridgeLength() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskBridgeLength();
            final int bridgeLength = inputView.readBridgeSize();
            return new BridgeLength(bridgeLength);
        });
    }

    private MoveCommand requestMoveCommand() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskMoveCommand();
            final String input = inputView.readMoving();
            return MoveCommand.findByExpression(input);
        });
    }

    private GameCommand requestGameCommand() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskRetry();
            final String input = inputView.readGameCommand();
            return GameCommand.findByExpression(input);
        });
    }

    private boolean isRetry(final BridgeGame bridgeGame) {
        if (bridgeGame.isClear()) {
            return false;
        }
        return bridgeGame.retry(requestGameCommand());
    }
}
