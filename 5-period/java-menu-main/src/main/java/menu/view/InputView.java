package menu.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import menu.exception.ErrorMessage;
import menu.util.InputValidator;
import menu.util.StringParser;

public class InputView {

    private static final String LINE = System.lineSeparator();
    private static final String DELIMITER = ",";

    public List<String> readCoachName(ErrorMessage errorMessage) {
        String line = readLine(errorMessage);
        return StringParser.parseByDelimiter(line, DELIMITER);
    }

    public List<String> readMenuCannotEat(ErrorMessage errorMessage) {
        String line = readLine(errorMessage);
        return StringParser.parseByDelimiter(line, DELIMITER);
    }

    private String readLine(ErrorMessage errorMessage) {
        String line = Console.readLine();
        InputValidator.validateNotNull(line, errorMessage);
        return line;
    }
}
