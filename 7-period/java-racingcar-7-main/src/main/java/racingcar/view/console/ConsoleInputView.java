package racingcar.view.console;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import racingcar.util.StringParser;
import racingcar.view.InputView;

public class ConsoleInputView implements InputView {

    private static final String DELIMITER_COMMA = ",";

    @Override
    public List<String> readCarNames() {
        final String input = readInput();
        return StringParser.parseToTokens(input, DELIMITER_COMMA);
    }

    @Override
    public int readAttempt() {
        final String input = readInput();
        return StringParser.parseToInt(input);
    }

    private String readInput() {
        return Console.readLine();
    }
}
