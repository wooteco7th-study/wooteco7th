package bridge.controller;

import bridge.domain.RestartCommand;
import bridge.domain.bridge.BridgeGame;
import bridge.domain.bridge.Result;
import bridge.domain.generator.BridgeNumberGenerator;
import bridge.dto.ResultDto;
import bridge.dto.TotalResultDto;
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
        // 다리 입력
        outputView.startMessage();
        int size = makeBridgeSize();
        BridgeGame game = new BridgeGame(size, bridgeNumberGenerator);
        processGame(game);
    }

    private void processGame(final BridgeGame game) {
        int tryCount = 0;
        processEachGame(game, tryCount);
    }

    private void processEachGame(final BridgeGame game, int tryCount) {
        do {
            tryCount++;
            game.clear();
            move(game);
        } while (!shouldNotContinue(game));
    }

    private boolean shouldNotContinue(final BridgeGame game) {
        return game.isSuccess() || !wantRestart(game);
    }

    private boolean wantRestart(final BridgeGame game) {
        outputView.restartMessage();
        return exceptionHandler.retryOn(() -> {
            RestartCommand restartCommand = new RestartCommand(inputView.readGameCommand().charAt(0));
            return game.retry(restartCommand);
        });
    }

    private void move(final BridgeGame game) {
        do {
            String direction = makeDirection();
            game.move(direction);
            outputView.printMap(toResultDto(game.getResults()));
        } while (game.canContinue());
    }

    public List<ResultDto> toResultDto(List<Result> results) {
        return results.stream()
                .map(ResultDto::from)
                .toList();
    }

    private String makeDirection() {
        return exceptionHandler.retryOn(() -> {
            outputView.selectDirection();
            return inputView.readMoving();
        });
    }

    private int makeBridgeSize() {
        return exceptionHandler.retryOn(inputView::readBridgeSize);
    }
}
