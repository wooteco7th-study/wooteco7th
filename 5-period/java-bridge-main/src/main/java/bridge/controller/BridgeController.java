package bridge.controller;

import bridge.domain.command.RestartCommand;
import bridge.domain.bridge.BridgeGame;
import bridge.domain.bridge.BridgeMaker;
import bridge.domain.bridge.Result;
import bridge.domain.command.UpDown;
import bridge.domain.generator.BridgeNumberGenerator;
import bridge.dto.ResultDto;
import bridge.exception.ExceptionHandler;
import bridge.view.InputView;
import bridge.view.OutputView;
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
        BridgeGame game = new BridgeGame(bridge);
        processGame(game);
    }

    private void processGame(final BridgeGame game) {
        processEachGame(game);
    }

    private void processEachGame(final BridgeGame game) {
        do {
            game.clear();
            move(game);
        } while (!shouldNotContinue(game));
        outputView.printResult(toResultDto(game.getResults()), game.getAttempt());
    }

    private boolean shouldNotContinue(final BridgeGame game) {
        return game.isSuccess() || !wantRestart(game);
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
            outputView.printMap(toResultDto(game.getResults()));
        } while (game.canContinue());
    }

    public List<ResultDto> toResultDto(List<Result> results) {
        return results.stream()
                .map(ResultDto::from)
                .toList();
    }

    private UpDown makeUpDown() {
        return exceptionHandler.retryOn(() -> {
            outputView.selectDirection();
            return UpDown.from(inputView.readMoving());
        });
    }

    private List<String> makeBridge() {
        return exceptionHandler.retryOn(() -> {
            Integer size = exceptionHandler.retryOn(inputView::readBridgeSize);
            BridgeMaker bridgeMaker = new BridgeMaker(bridgeNumberGenerator);
            return bridgeMaker.makeBridge(size);
        });
    }
}
