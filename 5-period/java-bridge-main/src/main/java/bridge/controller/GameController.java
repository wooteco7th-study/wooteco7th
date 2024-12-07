package bridge.controller;

import bridge.domain.BridgeGame;
import bridge.domain.CurrentMap;
import bridge.domain.bridgemaker.BridgeMaker;
import bridge.domain.bridgemaker.BridgeRandomNumberGenerator;
import bridge.dto.response.ResultResponse;
import bridge.view.InputView;
import bridge.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class GameController {

    private final InputView inputView;
    private final OutputView outputView;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> bridgeAnswer = retryOnInvalidInput(this::getBridgeAnswer);
        BridgeGame bridgeGame = new BridgeGame(bridgeAnswer);
        CurrentMap currentMap = new CurrentMap();

        processGame(bridgeGame, currentMap);
        ResultResponse resultResponse = ResultResponse.of(currentMap, bridgeGame);
        outputView.printResult(resultResponse);
    }

    private List<String> getBridgeAnswer() {
        int bridgeSize = inputView.readBridgeSize();
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        return bridgeMaker.makeBridge(bridgeSize);
    }

    private void processGame(final BridgeGame bridgeGame, final CurrentMap currentMap) {
        while (bridgeGame.keepGame()) {
            boolean isCorrect = moveAndCompare(bridgeGame, currentMap);
            if (isCorrect) {
                continue;
            }
            if (isNotRetry(bridgeGame, currentMap)) {
                break;
            }
        }
    }

    private boolean moveAndCompare(final BridgeGame bridgeGame, final CurrentMap currentMap) {
        String moving = retryOnInvalidInput(() -> getMoving(bridgeGame));
        boolean isSame = bridgeGame.compare();
        currentMap.addMap(moving, isSame);
        outputView.printMap(currentMap);
        return isSame;
    }

    private String getMoving(final BridgeGame bridgeGame) {
        String moving = inputView.readMoving();
        bridgeGame.move(moving);
        return moving;
    }

    private boolean isNotRetry(final BridgeGame bridgeGame, final CurrentMap currentMap) {
        boolean isRetry = retryOnInvalidInput(() -> isRetry(bridgeGame));
        if (isRetry) {
            currentMap.clearMap();
            return false;
        }
        return true;
    }

    private boolean isRetry(final BridgeGame bridgeGame) {
        String retryOrNot = inputView.readGameCommand();
        return bridgeGame.retry(retryOrNot);
    }

    private <T> T retryOnInvalidInput(Supplier<T> input) {
        while (true) {
            try {
                return input.get();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
