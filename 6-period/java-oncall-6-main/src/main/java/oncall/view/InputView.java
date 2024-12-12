package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import oncall.domain.DayOfWeek;
import oncall.domain.Month;
import oncall.domain.Schedule;
import oncall.domain.WorkDay;
import oncall.error.ErrorMessage;
import oncall.util.StringParser;

public class InputView {

    private static final String DELIMITER_COMMA = ",";
    private static final String SPACE = " ";
    private static final int MONTH_INDEX = 0;
    private static final int DAY_OF_WEEK_INDEX = 1;

    public WorkDay readWorkDay() {
        final List<String> tokens = StringParser.parseToTokens(StringParser.removeAllPattern(readInput(), SPACE),
                DELIMITER_COMMA);
        final Month month = Month.findByNumber(
                StringParser.parseToNumber(tokens.get(MONTH_INDEX), ErrorMessage.INVALID_INPUT));
        final DayOfWeek dayOfWeek = DayOfWeek.findByName(tokens.get(DAY_OF_WEEK_INDEX));
        return new WorkDay(month, dayOfWeek, 1);
    }

    public Schedule readSchedule() {
        return new Schedule(StringParser.parseToTokens(readInput(), DELIMITER_COMMA));
    }

    private String readInput() {
        return Console.readLine();
    }
}
