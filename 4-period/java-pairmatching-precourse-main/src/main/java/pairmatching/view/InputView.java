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
    private static final String DELIMITER = ",";

    public List<String> readCrewNames() {
        String line = readLine(ErrorMessage.INVALID_INPUT);
        return StringParser.parseByDelimiter(line, DELIMITER);
    }

    public String readCourse() {
        return readLine(ErrorMessage.INVALID_INPUT);
    }

    public String readFunction() {
        return readLine(ErrorMessage.INVALID_COMMAND);
    }

    public List<String> readSelect() {
        String input = readLine(ErrorMessage.INVALID_INPUT);
        if (InputValidator.isInvalidPattern(input, SELECT_PATTERN)) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT);
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
