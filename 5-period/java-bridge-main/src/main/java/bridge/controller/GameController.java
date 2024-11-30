package bridge.controller;

import bridge.domain.BridgeGame;
import bridge.domain.CurrentMap;
import bridge.domain.bridgemaker.BridgeMaker;
import bridge.domain.bridgemaker.BridgeRandomNumberGenerator;
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
        outputView.printResult(bridgeGame, currentMap);
    }

    private void processGame(final BridgeGame bridgeGame, final CurrentMap currentMap) {
        while (bridgeGame.keepGame()) {
            String moving = inputView.readMoving();
            bridgeGame.move(moving);
            boolean isSame = bridgeGame.compare();
            currentMap.addMap(moving, isSame);
            outputView.printMap(currentMap);
            if (!isSame) {
                boolean isRetry = bridgeGame.retry(inputView.readGameCommand());
                if (isRetry) {
                    currentMap.clearMap();
                    processGame(bridgeGame, currentMap);
                }
                break;
            }
        }
    }

    private List<String> getBridgeAnswer() {
        int bridgeSize = inputView.readBridgeSize();
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        return bridgeMaker.makeBridge(bridgeSize);
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
