package christmas.controller;

import christmas.domain.Day;
import christmas.exception.ExceptionHandler;
import christmas.service.ChristmasService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ExceptionHandler exceptionHandler;
    private final ChristmasService christmasService;

    public ChristmasController(final InputView inputView, final OutputView outputView,
                               final ExceptionHandler exceptionHandler,
                               final ChristmasService christmasService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.exceptionHandler = exceptionHandler;
        this.christmasService = christmasService;
    }

    public void process() {
        // 식당 예상 방문 날짜 입력 기능 구현
        Day day = makeDay();

    }

    private Day makeDay() {
        outputView.showRequestVisitDay();
        return exceptionHandler.retryUntilSuccess(() -> new Day(inputView.readVisitDay()));
    }

}
