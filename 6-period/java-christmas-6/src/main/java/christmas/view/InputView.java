package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.ErrorMessage;
import christmas.util.InputValidator;
import christmas.util.StringParser;
import java.util.List;

public class InputView {

    private static final String DELIMITER = ",";

    public int readVisitDay() {
        String line = readLine(ErrorMessage.INVALID_DATE);
        return StringParser.parseToInteger(line, ErrorMessage.INVALID_DATE);
    }

    public List<String> readOrder() {
        String line = readLine(ErrorMessage.INVALID_ORDER);
        return StringParser.parseByDelimiter(line, DELIMITER);
    }

    private String readLine(ErrorMessage errorMessage) {
        String line = Console.readLine();
        InputValidator.validateNotNullOrBlank(line, errorMessage);
        return line;
    }
}
