package pairmatching.controller;

import java.util.HashMap;
import java.util.List;
import pairmatching.domain.command.FunctionCommand;
import pairmatching.domain.command.RetryCommand;
import pairmatching.domain.order.PairOrder;
import pairmatching.domain.pair.PairHistory;
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

    public PairController(final InputView inputView, final OutputView outputView,
                          final ExceptionHandler exceptionHandler,
                          final PairService pairService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.exceptionHandler = exceptionHandler;
        this.pairService = pairService;
    }

    public void process() {
        processSession();
    }

    private void processSession() {
        while (true) {
            FunctionCommand command = makeFunctionCommand();
            if (command.isQuit()) {
                return;
            }
            if (command.isInitialized()) {
                processInitialize();
                continue;
            }
            processWithCommand(command);
        }
    }

    private void processInitialize() {
        pairService.initialize();
        outputView.showInformReset();
    }

    private void processWithCommand(final FunctionCommand command) {
        PairOrder pairOrder = makePairOrder();
        if (command.isPairMatching()) {
            pairOrder = retryWhenHistoryExists(pairOrder);
            matchPair(pairOrder);
        }
        if (command.IsPairInquiry()) {
            processPairInquiry(pairOrder);
        }
    }

    private void matchPair(PairOrder pairOrder) {
        exceptionHandler.executeWithCatch(() -> {
            PairMatchResultDto pairMatchResultDto = pairService.matchPair(pairOrder);
            outputView.showMatchResult(pairMatchResultDto);
        });
    }


    private PairOrder retryWhenHistoryExists(PairOrder pairOrder) {
        while (pairService.hasHistory(pairOrder)) {
            if (wantRetry()) {
                break;
            }
            outputView.showRetry();
            pairOrder = makeRetryOrder();
        }
        return pairOrder;
    }

    private PairOrder makeRetryOrder() {
        return exceptionHandler.retryUntilSuccess(() -> {
            List<String> tokens = inputView.readSelect();
            return new PairOrder(tokens.get(0), tokens.get(1), tokens.get(2));
        });
    }

    private void processPairInquiry(final PairOrder pairOrder) {
        exceptionHandler.executeWithCatch(() -> {
            PairMatchResultDto pairMatchResultDto = pairService.inquirePair(pairOrder);
            outputView.showMatchResult(pairMatchResultDto);
        });
    }

    private boolean wantRetry() {
        outputView.showRequestRetry();
        return exceptionHandler.retryUntilSuccess(() -> RetryCommand.from(inputView.readRequestRetry()).isYes());
    }

    private PairOrder makePairOrder() {
        return exceptionHandler.retryUntilSuccess(() -> {
            outputView.showTitleSelect();
            return makeRetryOrder();
        });
    }

    private FunctionCommand makeFunctionCommand() {
        outputView.showTitleFunction();
        return exceptionHandler.retryUntilSuccess(() -> FunctionCommand.from(inputView.readFunction()));
    }
}
