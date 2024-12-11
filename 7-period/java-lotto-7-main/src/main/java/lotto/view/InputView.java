package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import lotto.exception.ErrorMessage;
import lotto.util.InputValidator;
import lotto.util.StringParser;

public class InputView {

    private static final String DELIMITER = ",";

    public int readAmount() {
        String line = readLine(ErrorMessage.INVALID_INPUT);
        return StringParser.parseToInteger(line, ErrorMessage.INVALID_INPUT);
    }

    public List<Integer> readWinningNumber() {
        String line = readLine(ErrorMessage.INVALID_INPUT);
        return convertToTokens(line);
    }

    private List<Integer> convertToTokens(final String line) {
        List<String> tokens = StringParser.parseByDelimiter(line, DELIMITER);
        return tokens.stream()
                .map(input -> StringParser.parseToInteger(input, ErrorMessage.INVALID_INPUT))
                .toList();
    }

    public int readBonusNumber() {
        String line = readLine(ErrorMessage.INVALID_INPUT);
        return StringParser.parseToInteger(line, ErrorMessage.INVALID_INPUT);
    }

    private String readLine(ErrorMessage errorMessage) {
        String line = Console.readLine();
        InputValidator.validateNotNullOrBlank(line, errorMessage);
        return line;
    }
}
