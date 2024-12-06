package pairmatching.controller;

import java.util.List;
import java.util.Optional;
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
        PairOrder pairOrder = makePairOrder();
        if (command.isPairMatching()) {
            processPairMatching(pairOrder);
        }
        if (command.IsPairInquiry()) {
            processPairInquiry(pairOrder);
        }

    }

    private void processPairMatching(PairOrder pairOrder) {
        PairHistory pairHistory = initializer.getHistory();
        while (pairHistory.isExists(pairOrder)) {
            Optional<PairOrder> retryOrder = requestRetryOrder();
            if (retryOrder.isPresent()) {
                pairOrder = retryOrder.get();
                continue;
            }
            break;
        }
        PairMatchResultDto pairMatchResultDto = pairService.matchPair(pairOrder, initializer);
        outputView.showMatchResult(pairMatchResultDto);
    }

    private Optional<PairOrder> requestRetryOrder() {
        outputView.showRequestRetry();
        if (!wantRetry()) {
            outputView.showRetry();
            return Optional.ofNullable(makeRetryOrder());
        }
        return Optional.empty();
    }

    private PairOrder makeRetryOrder() {
        return exceptionHandler.retryOn(() -> {
            List<String> tokens = inputView.readSelect();
            return new PairOrder(tokens.get(0), tokens.get(1), tokens.get(2));
        });
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
        return makeRetryOrder();
    }

    private FunctionCommand makeFunctionCommand() {
        outputView.showTitleFunction();
        return exceptionHandler.retryOn(() -> FunctionCommand.from(inputView.readFunction()));
    }
}
