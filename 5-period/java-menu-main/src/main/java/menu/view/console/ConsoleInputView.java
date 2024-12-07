package menu.view.console;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import menu.util.StringParser;
import menu.view.InputView;

public class ConsoleInputView implements InputView {

    private static final String DELIMITER_COMMA = ",";

    @Override
    public List<String> readInputs() {
        return StringParser.parseToTokens(readInput().strip(), DELIMITER_COMMA);
    }

    private String readInput() {
        return Console.readLine();
    }
}
