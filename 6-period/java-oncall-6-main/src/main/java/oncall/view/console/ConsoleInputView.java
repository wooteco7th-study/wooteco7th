package oncall.view.console;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import oncall.domain.DayOfWeek;
import oncall.domain.Month;
import oncall.domain.WorkDay;
import oncall.error.ErrorMessage;
import oncall.util.StringParser;
import oncall.util.StringValidator;
import oncall.view.InputView;

public class ConsoleInputView implements InputView {

    private static final String DELIMITER_COMMA = ",";
    private static final int MONTH_INDEX = 0;
    private static final int DAY_OF_WEEK_INDEX = 1;
    private static final int INITIAL_DAY_OF_MONTH = 1;

    @Override
    public WorkDay readWorkDay() {
        final String input = readInput();
        StringValidator.validateFormat(input, ErrorMessage.INVALID_WORK_DAY);
        final List<String> tokens = StringParser.parseToTokens(input, DELIMITER_COMMA);
        final Month month = Month.findByNumber(
                StringParser.parseToInt(tokens.get(MONTH_INDEX), ErrorMessage.INVALID_MONTH));
        final DayOfWeek dayOfWeek = DayOfWeek.findByName(tokens.get(DAY_OF_WEEK_INDEX));
        return new WorkDay(month, dayOfWeek, INITIAL_DAY_OF_MONTH);
    }

    @Override
    public List<String> readWorkers() {
        return StringParser.parseToTokens(readInput(), DELIMITER_COMMA);
    }

    private String readInput() {
        return Console.readLine();
    }
}
