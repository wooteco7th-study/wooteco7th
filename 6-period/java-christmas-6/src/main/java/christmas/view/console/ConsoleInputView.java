package christmas.view.console;

import camp.nextstep.edu.missionutils.Console;
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
    public String readVisitDay() {
        return readInput();
    }

    private String readInput() {
        return Console.readLine();
    }
}
