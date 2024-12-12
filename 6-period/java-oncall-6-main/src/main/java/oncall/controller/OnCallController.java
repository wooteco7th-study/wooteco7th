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
import oncall.service.OnCallService;
import oncall.util.StringParser;
import oncall.view.InputView;
import oncall.view.OutputView;

public class OnCallController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ExceptionHandler exceptionHandler;
    private final OnCallService oncallService;

    public OnCallController(final InputView inputView, final OutputView outputView,
                            final ExceptionHandler exceptionHandler,
                            final OnCallService oncallService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.exceptionHandler = exceptionHandler;
        this.oncallService = oncallService;
    }

    public void process() {
        Month month = makeMonth();
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
