package lotto.view.console;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import lotto.util.StringParser;
import lotto.view.InputView;

public class ConsoleInputView implements InputView {

    private static final String DELIMITER_COMMA = ",";

    @Override
    public int readNumber() {
        return StringParser.parseToInt(readInput());
    }

    @Override
    public List<Integer> readNumbers() {
        return StringParser.parseToNumberTokens(readInput(),DELIMITER_COMMA);
    }

    private String readInput() {
        return Console.readLine();
    }
}
