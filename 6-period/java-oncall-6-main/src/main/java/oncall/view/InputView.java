package oncall.view;

import static oncall.exception.ErrorMessage.INVALID_INPUT;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import oncall.exception.ErrorMessage;
import oncall.util.InputValidator;
import oncall.util.StringParser;

public class InputView {

    private static final String DELIMITER = ",";

    public List<String> readStart() {
        String line = readLine(INVALID_INPUT);
        return StringParser.parseByDelimiter(line, DELIMITER);
    }

    public List<String> readWeekdayTurn() {
        String line = readLine(INVALID_INPUT);
        return StringParser.parseByDelimiter(line, DELIMITER);
    }

    public List<String> readHolidayTurn() {
        String line = readLine(INVALID_INPUT);
        return StringParser.parseByDelimiter(line, DELIMITER);
    }

    private String readLine(ErrorMessage errorMessage) {
        String line = Console.readLine();
        InputValidator.validateNotNullOrBlank(line, errorMessage);
        return line;
    }
}
