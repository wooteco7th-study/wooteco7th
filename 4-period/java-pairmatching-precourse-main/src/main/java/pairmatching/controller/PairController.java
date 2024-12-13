package pairmatching.controller;

import java.util.List;
import pairmatching.domain.command.FunctionCommand;
import pairmatching.domain.command.RetryCommand;
import pairmatching.domain.crew.Crews;
import pairmatching.domain.order.Course;
import pairmatching.domain.order.PairOrder;
import pairmatching.dto.CrewDto;
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
        while (true) {
            FunctionCommand command = makeFunctionCommand();
            if (command.isQuit()) {
                processQuit();
                return;
            }
            if (command.isRegardingCrew()) {
                processCrews(command);
            }
            if (command.isRegardingPair()) {
                processPairs(command);
            }
            outputView.showBlank();
        }
    }

    private void processQuit() {
        requestSavingCrews();
        requestSavingPairMatching();
    }

    private void requestSavingCrews() {
        outputView.showRequestSaveCrew();
        if (isYes()) {
            pairService.saveCrew();
        }
    }

    private void requestSavingPairMatching() {
        outputView.showRequestSavePairMatching();
        if (isYes()) {
            pairService.savePairMatching();
        }
    }

    private void processPairs(final FunctionCommand command) {
        if (command.isInitialized()) {
            processInitialize();
            return;
        }
        PairOrder pairOrder = makePairOrder();
        if (command.isPairMatching()) {
            processMatching(pairOrder);
        }
        if (command.IsPairInquiry()) {
            processPairInquiry(pairOrder);
        }
    }

    private void processCrews(final FunctionCommand command) {
        // 1. 크루 추가
        if (command.addCrew()) {
            addCrew();
        }
        //2. 크루 조회
        if (command.inquireCrew()) {
            inquireCrew();
        }
        //3. 크루 초기화
        if (command.initializeCrew()) {
            outputView.informExcludeFunction();
        }
    }

    private void initializeCrew() {
        pairService.initializeCrew();
        outputView.showInformReset();
    }

    private void inquireCrew() {
        Course course = makeCourse();
        exceptionHandler.executeWithCatch(() -> {
            CrewDto crewDto = pairService.inquireCrew(course);
            outputView.showCrew(crewDto);
        });
    }

    private void addCrew() {
        while (true) {
            Course course = makeCourse();
            addCrew(course);
            if (!wantCrewNameRetry()) {
                return;
            }
        }
    }

    private boolean wantCrewNameRetry() {
        outputView.showRequestCrewNameRetry();
        return isYes();
    }

    private Boolean isYes() {
        return exceptionHandler.retryUntilSuccess(() -> RetryCommand.from(inputView.readRequestRetry()).isYes());
    }

    private void addCrew(final Course course) {
        exceptionHandler.retryUntilSuccess(() -> {
            outputView.showRequestCrewName();
            Crews crews = Crews.from(course, inputView.readCrewNames());
            pairService.addCrews(crews);
        });
    }

    private Course makeCourse() {
        return exceptionHandler.retryUntilSuccess(() -> {
            outputView.showRequestCourse();
            return Course.from(inputView.readCourse());
        });
    }

    private void processInitialize() {
        pairService.initialize();
        outputView.showInformReset();
    }

    private void processMatching(final PairOrder pairOrder) {
        PairOrder currentOrder = pairOrder;
        while (pairService.hasHistory(currentOrder)) {
            if (wantRetry()) {
                break;
            }
            outputView.showRetry();
            currentOrder = makeRetryOrder();
        }
        matchPair(currentOrder);
    }

    private void matchPair(PairOrder pairOrder) {
        exceptionHandler.executeWithCatch(() -> {
            PairMatchResultDto pairMatchResultDto = pairService.matchPair(pairOrder);
            outputView.showMatchResult(pairMatchResultDto);
        });
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
        return isYes();
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
