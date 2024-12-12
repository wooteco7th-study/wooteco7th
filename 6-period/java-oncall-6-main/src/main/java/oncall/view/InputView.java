package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.regex.Pattern;
import oncall.exception.ErrorMessage;
import oncall.util.InputValidator;
import oncall.util.StringParser;

public class InputView {

    private static final String DELIMITER = ",";
    private static final String REGEX = "([1-9]\\d*)+,([가-힣]{1})";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    public List<String> readStartDate() {
        String line = readLine(ErrorMessage.INVALID_INPUT);
        InputValidator.validatePattern(line, PATTERN, ErrorMessage.INVALID_INPUT);
        return StringParser.parseByDelimiter(line, DELIMITER);
    }

    public List<String> readTurn() {
        String line = readLine(ErrorMessage.INVALID_INPUT);
        return StringParser.parseByDelimiter(line, DELIMITER);
    }

    private String readLine(ErrorMessage errorMessage) {
        String line = Console.readLine();
        InputValidator.validateNotNullOrBlank(line, errorMessage);
        return line;
    }
}
