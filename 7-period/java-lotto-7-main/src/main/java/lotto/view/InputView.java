package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import lotto.error.ErrorMessage;
import lotto.util.StringParser;

public class InputView {

    private static final String DELIMITER_COMMA = ",";
    private static final String SPACE = " ";

    public List<Integer> readNumbers() {
        final String input = StringParser.removePattern(readInput(), SPACE);
        return StringParser.parseToNumberTokens(input, DELIMITER_COMMA, ErrorMessage.EXCEEDS_NUMBER);
    }

    public int readNumber() {
        final String input = StringParser.removePattern(readInput(), SPACE);
        return StringParser.parseToInt(input, ErrorMessage.INVALID_MONEY_FORMAT);
    }

    private String readInput() {
        return Console.readLine();
    }
}
