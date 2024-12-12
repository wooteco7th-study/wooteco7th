package oncall.controller;

import java.util.List;
import oncall.domain.DayType;
import oncall.domain.Month;
import oncall.domain.MonthType;
import oncall.exception.ErrorMessage;
import oncall.exception.ExceptionHandler;
import oncall.service.OncallService;
import oncall.util.InputValidator;
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
        // 월, 시작요일 입력 기능 구현
        Month month = makeMonth();
    }

    private Month makeMonth() {
        return exceptionHandler.retryUntilSuccess(() -> {
            outputView.showRequestStartDay();
            List<String> tokens = inputView.readStartDate();
            MonthType monthType = MonthType.from(
                    StringParser.parseToInteger(tokens.get(0), ErrorMessage.INVALID_INPUT));
            DayType dayType = DayType.from(tokens.get(1));
            return new Month(monthType, dayType);
        });
    }
}
