package pairmatching.controller;

import java.util.ArrayList;
import java.util.List;
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

    public PairController(final InputView inputView, final OutputView outputView,
                          final ExceptionHandler exceptionHandler,
                          final PairService pairService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.exceptionHandler = exceptionHandler;
        this.pairService = pairService;
    }

    public void process() {
        PairHistory pairHistory = new PairHistory(new ArrayList<>());
        while (true) {
            FunctionCommand command = makeFunctionCommand();
            if (command.isQuit()) {
                return;
            }
            if (command.isInitialized()) {
                // 초기화 수행
                continue;
            }
            processWithCommand(command, pairHistory);
        }
    }

    private void processWithCommand(final FunctionCommand command, final PairHistory pairHistory) {
        boolean isInMiddle = false;
        while (!isInMiddle) {
            PairOrder pairOrder = makePairOrder();
            if (command.isPairMatching()) {
                PairMatchResultDto pairMatchResultDto = pairService.matchPair(pairOrder, pairHistory);
                outputView.showMatchResult(pairMatchResultDto);
            }
            if (command.IsPairInquiry()) {
                isInMiddle = processPairInquiry(pairHistory, pairOrder);
            }
        }
    }

    private boolean processPairInquiry(final PairHistory pairHistory, final PairOrder pairOrder) {
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
