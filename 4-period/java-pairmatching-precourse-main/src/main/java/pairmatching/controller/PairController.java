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
                // 초기화 수행
                continue;
            }
            processWithCommand(command);
        }
    }

    private void processWithCommand(final FunctionCommand command) {
        boolean isInMiddle = false;
        while (!isInMiddle) {
            PairOrder pairOrder = makePairOrder();
            if (command.isPairMatching()) {
                PairMatchResultDto pairMatchResultDto = pairService.matchPair(pairOrder);
                outputView.showMatchResult(pairMatchResultDto);
            }
            if (command.IsPairInquiry()) {
                isInMiddle = processPairInquiry(pairOrder);
            }
        }
    }

    private boolean processPairInquiry(final PairOrder pairOrder) {
        PairHistory pairHistory = initializer.getHistory();
        if (pairHistory.isExists(pairOrder)) {
            outputView.showRequestRetry();
            if (!wantRetry()) {
                return false;
            }
        }
        pairService.inquirePair(pairOrder, pairHistory);
        return true;
    }

    private boolean wantRetry() {
        return RetryCommand.from(inputView.readRequestRetry()).isYes();
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
