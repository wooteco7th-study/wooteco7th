package oncall.controller;

import java.util.List;
import oncall.domain.date.DayType;
import oncall.domain.date.Month;
import oncall.domain.date.MonthType;
import oncall.domain.name.Turn;
import oncall.domain.name.Turns;
import oncall.dto.TurnDto;
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
        // 월, 시작요일 입력 기능 구현
        Month month = makeMonth();
        // 근무 순번 입력 기능 구현
        Turns turns = makeTurns();
        List<TurnDto> turnDtos = oncallService.processSchedule(month, turns);
        outputView.showResult(turnDtos);
    }

    private Turn makeWeekdayTurn() {
        outputView.showRequestWeekdayTurn();
        return new Turn(inputView.readTurn());
    }

    private Turns makeTurns() {
        return exceptionHandler.retryUntilSuccess(() -> {
            Turn weekdayTurn = makeWeekdayTurn();
            Turn holidayTurn = makeHolidayTurn();
            return new Turns(weekdayTurn, holidayTurn);
        });
    }

    private Turn makeHolidayTurn() {
        outputView.showRequestHolidayTurn();
        return new Turn(inputView.readTurn());
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
