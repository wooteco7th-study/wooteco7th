package christmas.view.console;

import camp.nextstep.edu.missionutils.Console;
import christmas.error.ErrorMessage;
import christmas.util.StringParser;
import christmas.view.InputView;
import java.util.List;

public class ConsoleInputView implements InputView {

    private static final String DELIMITER_COMMA = ",";

    @Override
    public List<String> readOrderMenu() {
        final String input = readInput();
        return StringParser.parseToTokens(input, DELIMITER_COMMA);
    }

    @Override
    public int readVisitDay() {
        final String input = readInput();
        return StringParser.parseToInt(input, ErrorMessage.INVALID_VISIT_DAY_FORMAT);
    }

    private String readInput() {
        return Console.readLine();
    }
}
