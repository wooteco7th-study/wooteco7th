package oncall.controller;

import java.util.List;
import oncall.domain.Month;
import oncall.exception.ErrorMessage;
import oncall.exception.ExceptionHandler;
import oncall.service.OncallService;
import oncall.util.StringParser;
import oncall.view.InputView;
import oncall.view.OutputView;

public class OncallController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ExceptionHandler exceptionHandler;
    private final OncallService oncallService;

    public OncallController(final InputView inputView, final OutputView outputView,
                            final ExceptionHandler exceptionHandler,
                            final OncallService oncallService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.exceptionHandler = exceptionHandler;
        this.oncallService = oncallService;
    }

    public void process() {
        Month month = makeMonth();

    }

    private Month makeMonth() {
        // 비상 근무 배정 월, 시작요일 입력
        return exceptionHandler.retryUntilSuccess(() -> {
            outputView.showRequestStart();
            List<String> tokens = inputView.readStart();
            int month = StringParser.parseToInteger(tokens.get(0), ErrorMessage.INVALID_INPUT);
            String day = tokens.get(1);
            return new Month(month, day);
        });
    }
}
