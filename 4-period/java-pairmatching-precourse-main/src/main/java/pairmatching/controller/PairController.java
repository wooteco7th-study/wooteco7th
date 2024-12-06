package pairmatching.controller;

import java.util.List;
import pairmatching.domain.Initializer;
import pairmatching.domain.command.FunctionCommand;
import pairmatching.domain.command.RetryCommand;
import pairmatching.domain.pair.PairHistory;
import pairmatching.domain.pair.PairOrder;
import pairmatching.dto.PairMatchResultDto;
import pairmatching.exception.ExceptionHandler;
import pairmatching.service.PairService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ExceptionHandler exceptionHandler;
    private final PairService pairService;
    private final Initializer initializer;

    public PairController(final InputView inputView, final OutputView outputView,
                          final ExceptionHandler exceptionHandler,
                          final PairService pairService, final Initializer initializer) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.exceptionHandler = exceptionHandler;
        this.pairService = pairService;
        this.initializer = initializer;
    }

    public void process() {
        while (true) {
            FunctionCommand command = makeFunctionCommand();
            if (command.isQuit()) {
                return;
            }
            if (command.isInitialized()) {
                processInitialize();
                continue;
            }
            exceptionHandler.retryOn(() -> processWithCommand(command));
        }
    }

    private void processInitialize() {
        initializer.clearHistory();
        outputView.showInformReset();
    }

    private void processWithCommand(final FunctionCommand command) {
        boolean isInMiddle = false;
        while (!isInMiddle) {
            PairOrder pairOrder = makePairOrder();
            if (command.isPairMatching()) {
                // 페어 매칭 후 정상 종료 -> isInMiddle = true;
                // 이미 존재할 경우 -> 아니오 누를 경우  재시도, isInMiddle= false
                isInMiddle = processPairMatching(pairOrder);
            }
            if (command.IsPairInquiry()) {
                processPairInquiry(pairOrder);
                break;
            }
        }
    }

    private boolean processPairMatching(final PairOrder pairOrder) {
        PairHistory pairHistory = initializer.getHistory();
        if (pairHistory.isExists(pairOrder)) {
            outputView.showRequestRetry();
            if (!wantRetry()) {
                return false;
            }
        }
        PairMatchResultDto pairMatchResultDto = pairService.matchPair(pairOrder, initializer);
        outputView.showMatchResult(pairMatchResultDto);
        return true;
    }

    private void processPairInquiry(final PairOrder pairOrder) {
        PairHistory pairHistory = initializer.getHistory();
        PairMatchResultDto pairMatchResultDto = pairService.inquirePair(pairOrder, pairHistory);
        outputView.showMatchResult(pairMatchResultDto);
    }

    private boolean wantRetry() {
        return exceptionHandler.retryOn(() -> RetryCommand.from(inputView.readRequestRetry()).isYes());
    }

    private PairOrder makePairOrder() {
        outputView.showTitleSelect();
        return exceptionHandler.retryOn(() -> {
            List<String> tokens = inputView.readSelect();
            return new PairOrder(tokens.get(0), tokens.get(1), tokens.get(2));
        });
    }

    private FunctionCommand makeFunctionCommand() {
        outputView.showTitleFunction();
        return exceptionHandler.retryOn(() -> FunctionCommand.from(inputView.readFunction()));
    }
}
