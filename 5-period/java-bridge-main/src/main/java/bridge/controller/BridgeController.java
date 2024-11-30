package bridge.controller;

import bridge.Bridge;
import bridge.BridgeGame;
import bridge.BridgeMaker;
import bridge.Direction;
import bridge.RestartCommand;
import bridge.dto.MoveResultDto;
import bridge.dto.TotalResultDto;
import bridge.exception.ExceptionHandler;
import bridge.generator.BridgeRandomNumberGenerator;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.ArrayList;
import java.util.List;

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
        BridgeGame game = new BridgeGame(bridge);
        processGame(game);
    }

    private void processGame(final BridgeGame game) {
        int tryCount = 0;
        processEachGame(game, tryCount);
    }

    private void processEachGame(final BridgeGame game, int tryCount) {
        while (true) {
            tryCount++;
            TotalResultDto totalResultDto = move(game);
            if (gameFail(tryCount, totalResultDto) || gameSuccess(tryCount, totalResultDto,
                    !game.isNotEnd(totalResultDto.pos()))) {
                break;
            }
        }
    }

    private boolean gameSuccess(final int tryCount, final TotalResultDto totalResultDto, final boolean gameEnd) {
        if (gameEnd && totalResultDto.isSuccess()) {
            showResult(totalResultDto, tryCount);
            return true;
        }
        return false;
    }

    private boolean gameFail(final int tryCount, final TotalResultDto totalResultDto) {
        if (!totalResultDto.isSuccess()) {
            if (!wantRestart()) {
                showResult(totalResultDto, tryCount);
                return true;
            }
        }
        return false;
    }

    private void showResult(final TotalResultDto totalResultDto, final int tryCount) {
        outputView.showFinalResultMessage();
        outputView.printResult(totalResultDto, tryCount);
    }

    private boolean wantRestart() {
        outputView.restartMessage();
        return exceptionHandler.retryOn(() -> {
            RestartCommand restartCommand = new RestartCommand(inputView.readGameCommand().charAt(0));
            return restartCommand.doesContinue();
        });
    }

    private TotalResultDto move(final BridgeGame game) {
        int pos = 0;
        List<MoveResultDto> moveResultDtos = new ArrayList<>();
        do {
            Direction direction = makeDirection();
            moveResultDtos.add(MoveResultDto.of(game.move(direction, pos++), direction));
            outputView.printMap(moveResultDtos);
        } while (game.canContinue(pos, moveResultDtos.getLast().isRightMove()));
        return TotalResultDto.from(pos, moveResultDtos);
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
