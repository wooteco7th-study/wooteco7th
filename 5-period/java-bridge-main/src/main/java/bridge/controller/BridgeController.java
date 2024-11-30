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
        int pos = 0;
        TotalResultDto totalResultDto;
        int tryCount = 0;
        do {
            tryCount++;
            totalResultDto = move(game);
            pos = totalResultDto.pos();
        } while (game.canContinue(pos) && wantRestart());
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
        boolean isSuccess = true;
        List<MoveResultDto> moveResultDtos = new ArrayList<>();
        while (game.canContinue(pos)) {
            Direction direction = makeDirection();
            boolean isRightmove = game.move(direction, pos);
            moveResultDtos.add(MoveResultDto.of(isRightmove, direction));
            outputView.printMap(moveResultDtos);
            if (!isRightmove) {
                isSuccess = false;
                break;
            }
            pos++;
        }
        return new TotalResultDto(pos, moveResultDtos, isSuccess);
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
