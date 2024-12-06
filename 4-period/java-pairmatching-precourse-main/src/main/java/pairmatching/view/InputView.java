package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.regex.Pattern;
import pairmatching.exception.CustomIllegalArgumentException;
import pairmatching.exception.ErrorMessage;
import pairmatching.util.InputValidator;
import pairmatching.util.StringParser;

public class InputView {

    private static final String REGEX = "^([가-힣a-zA-z]+), ([가-힣a-zA-z]+[1-9]\\d*), ([가-힣a-zA-z]+)$";
    private static final Pattern SELECT_PATTERN = Pattern.compile(REGEX);

    public String readFunction() {
        return readLine(ErrorMessage.INVALID_COMMAND);
    }

    public List<String> readSelect() {
        String input = readLine(ErrorMessage.INVALID_FORMAT);
        if (InputValidator.isInvalidPattern(input, SELECT_PATTERN)) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_FORMAT);
        }
        return StringParser.parseByDelimiter(input, ", ");
    }

    public String readRequestRetry() {
        return readLine(ErrorMessage.INVALID_COMMAND);
    }

    private String readLine(ErrorMessage errorMessage) {
        String line = Console.readLine();
        InputValidator.validateNotNullOrBlank(line, errorMessage);
        return line;
    }
}
